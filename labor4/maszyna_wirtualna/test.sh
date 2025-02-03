#!/bin/bash
# Upewnij się, że skrypt uruchamiasz przez bash (#!/bin/bash)

# Ścieżka do Twojego programu
PROGRAM="./maszyna-wirtualna"
wynikiPlik="wyniki.txt"

# Lista plików, dla których chcesz uruchomić testy
files=(
    "example1.mr"
    "example2.mr"
    "example3.mr"
    "example4.mr"
    "example5.mr"
    "example6.mr"
    "example7.mr"
    "example9.mr"
    "program0.mr"
    "program3.mr"
)

# Dane wejściowe (to, co normalnie wpisywałby użytkownik po uruchomieniu programu).
# Dla każdego testu definiujemy ciąg znaków z sekwencjami \n.
testInput=(
    "30\n20"   # Dla example1.mr
    "0\n1"     # Dla example2.mr (dostosuj według potrzeb)
    "1"        # Dla example3.mr (dostosuj według potrzeb)
    "20\n9"
    "1234567890\n1234567890987654321\n987654321"
    "20"
    "0\n0\n0"
    "20\n9"
    "34563"
    "12345678903"

)

# Oczekiwane wyniki zapisane jako ciągi liczb (oddzielone spacjami) – kluczem jest nazwa pliku.
declare -A expected_results
expected_results["example1.mr"]="1 1 10"
expected_results["example2.mr"]="46368 28657"
expected_results["example3.mr"]="121393"
expected_results["example4.mr"]="167960"
expected_results["example5.mr"]="674106858"
expected_results["example6.mr"]="2432902008176640000 6765"
expected_results["example7.mr"]="31000 40900 2222010"
expected_results["example9.mr"]="167960"
expected_results["program0.mr"]="1 1 0 0 0 0 0 0 1 1 1 0 0 0 0 1"
expected_results["program3.mr"]="3 1 4115226301 1"


# Upewnij się, że plik wynikowy istnieje (jeśli nie, utwórz pusty)
if [ ! -f "$wynikiPlik" ]; then
    touch "$wynikiPlik"
fi

for i in "${!files[@]}"; do
    file="${files[i]}"
    input="${testInput[i]}"

    echo "================================================="
    echo "Przetwarzam plik: $file"

    # 1. Sprawdzenie długości kodu (ilość linii w pliku)
    if [ -f "$file" ]; then
        code_length=$(wc -l < "$file")
        echo "Długość kodu (linie): $code_length"
    else
        echo "Plik $file nie istnieje!"
        continue
    fi

    # 2. Uruchomienie programu z danymi wejściowymi
    output=$(printf "%b\n" "$input" | "$PROGRAM" "$file")
    # Usuwamy ewentualne sekwencje ANSI, jeśli występują
    clean_output=$(echo "$output" | sed 's/\x1B\[[0-9;]*[a-zA-Z]//g')

    # 3. Wyodrębnianie wyników (linie zawierające znak '>' i liczbę po nim)
    results=()
    while IFS= read -r line; do
        if [[ "$line" =~ \>[[:space:]]*([0-9]+) ]]; then
            wynik="${BASH_REMATCH[1]}"
            results+=("$wynik")
        fi
    done <<< "$clean_output"

    if [ "${#results[@]}" -eq 0 ]; then
        echo "Brak wyekstrahowanych wyników dla $file."
    else
        echo -n "Wyniki dla $file: "
        for r in "${results[@]}"; do
            echo -n "$r "
        done
        echo ""
    fi

    # 4. Porównanie wyników z oczekiwanymi
    IFS=' ' read -r -a expected <<< "${expected_results[$file]}"
    if [ "${#results[@]}" -ne "${#expected[@]}" ]; then
        echo "Wynik: NIE - liczba wyekstrahowanych wyników nie zgadza się (oczekiwano ${#expected[@]}, otrzymano ${#results[@]})"
    else
        all_ok=true
        for j in "${!expected[@]}"; do
            if [ "${results[j]}" -ne "${expected[j]}" ]; then
                echo "Wynik: NIE - dla pozycji $((j+1)) oczekiwano ${expected[j]}, otrzymano ${results[j]}"
                all_ok=false
            fi
        done
        if $all_ok; then
            echo "Wynik: OK - wszystkie wyniki się zgadzają dla $file."
        else
            echo "Wynik: NIE - nie wszystkie wyniki są zgodne dla $file."
        fi
    fi

    # 5. Wyodrębnianie kosztu z ostatniej linii (przyjmujemy, że ostatnia linia ma format:
    #    "Skończono program (koszt: 1481; w tym i/o: 200).")
    last_line=$(echo "$clean_output" | tail -n 1)
    new_cost=$(echo "$last_line" | awk -F'koszt: ' '{split($2, a, ";"); print a[1]}')
    if [ -n "$new_cost" ]; then
        echo "Wyodrębniony koszt dla $file: $new_cost"
    else
        echo "Nie udało się wyodrębnić kosztu dla $file."
        new_cost=""
    fi

    # 6. Aktualizacja (koszt) w pliku wyniki.txt
    # Struktura linii w wyniki.txt:
    # fileName [cost1 codeLength1] [cost2 codeLength2] ...
    existing_line=$(grep "^$file " "$wynikiPlik")
    if [ -z "$existing_line" ]; then
        # Nie ma wpisu – zapisujemy nową linię (najpierw kolumna z kosztem)
        echo "$file $new_cost" >> "$wynikiPlik"
        echo "Nie znaleziono wcześniejszego kosztu. Zapisano nowy koszt dla $file: $new_cost"
    else
        # Jeśli wpis już istnieje, pobieramy ostatnią kolumnę aby sprawdzić, czy jest to koszt czy długość
        col_count=$(echo "$existing_line" | awk '{print NF}')
        if [ $((col_count % 2)) -eq 0 ]; then
            # Parzysta liczba kolumn → ostatnia kolumna to kod (koszt, długość) – tutaj brak jeszcze długości
            last_cost=$(echo "$existing_line" | awk '{print $NF}')
        else
            # Nieparzysta liczba kolumn → ostatnia kolumna to koszt
            last_cost=$(echo "$existing_line" | awk '{print $(NF)}')
        fi

        if [ -n "$last_cost" ]; then
            if [ "$new_cost" -eq "$last_cost" ]; then
                echo "Koszt dla $file się nie zmienił: $new_cost"
            elif [ "$new_cost" -lt "$last_cost" ]; then
                diff=$(echo "scale=2; (($last_cost - $new_cost) * 100)/$last_cost" | bc)
                echo "Koszt dla $file jest lepszy o $diff% (poprzedni: $last_cost, nowy: $new_cost)"
            else
                diff=$(echo "scale=2; (($new_cost - $last_cost) * 100)/$last_cost" | bc)
                echo "Koszt dla $file jest gorszy o $diff% (poprzedni: $last_cost, nowy: $new_cost)"
            fi
        fi
        # Dołączamy nowy koszt jako kolejną kolumnę
        sed -i "/^$file / s/$/ $new_cost/" "$wynikiPlik"
    fi

    # 7. Aktualizacja długości kodu (liczba linii)
    new_code_length=$(wc -l < "$file")
    echo "Długość kodu dla $file: $new_code_length"

    # Pobieramy ponownie linię z wyniki.txt dla danego pliku
    existing_line=$(grep "^$file " "$wynikiPlik")
    # Liczba kolumn w tej linii
    col_count=$(echo "$existing_line" | awk '{print NF}')
    # Zakładamy, że struktura to: file, cost1, codeLength1, cost2, codeLength2, ...
    if [ "$col_count" -eq 1 ]; then
        # To nie powinno wystąpić, bo już dodaliśmy kolumnę z kosztem
        echo "$existing_line $new_code_length" > "$wynikiPlik.tmp"
        mv "$wynikiPlik.tmp" "$wynikiPlik"
        echo "Nie znaleziono wcześniejszej długości kodu. Zapisano nową długość: $new_code_length"
    elif [ $((col_count % 2)) -eq 0 ]; then
        # Parzysta liczba kolumn → ostatnia kolumna jest kosztem, brak kolumny dla długości
        sed -i "/^$file / s/$/ $new_code_length/" "$wynikiPlik"
        echo "Zapisano długość kodu dla $file: $new_code_length"
    else
        # Nieparzysta liczba kolumn → ostatnia kolumna to długość kodu
        last_code_length=$(echo "$existing_line" | awk '{print $NF}')
        if [ "$new_code_length" -eq "$last_code_length" ]; then
            echo "Długość kodu dla $file się nie zmieniła: $new_code_length"
        elif [ "$new_code_length" -lt "$last_code_length" ]; then
            diff=$(echo "scale=2; (($last_code_length - $new_code_length) * 100)/$last_code_length" | bc)
            echo "Długość kodu dla $file jest mniejsza o $diff% (poprzednia: $last_code_length, nowa: $new_code_length)"
        else
            diff=$(echo "scale=2; (($new_code_length - $last_code_length) * 100)/$last_code_length" | bc)
            echo "Długość kodu dla $file jest większa o $diff% (poprzednia: $last_code_length, nowa: $new_code_length)"
        fi
        sed -i "/^$file / s/$/ $new_code_length/" "$wynikiPlik"
    fi

done

echo "Koniec testów. Aktualne wyniki zapisane w $wynikiPlik."