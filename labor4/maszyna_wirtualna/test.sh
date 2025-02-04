#!/bin/bash
# Upewnij się, że skrypt uruchamiasz przez bash (#!/bin/bash)

# Ścieżka do Twojego programu
PROGRAM="./maszyna-wirtualna"
wynikiPlik="wyniki4.txt"

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

    # 1. Sprawdzenie długości kodu
    if [ -f "$file" ]; then
        new_code_length=$(wc -l < "$file")
        #echo "Długość kodu (linie): $new_code_length"
    else
        echo "Plik $file nie istnieje!"
        continue
    fi

    # 2. Uruchomienie programu z danymi wejściowymi
    output=$(printf "%b\n" "$input" | "$PROGRAM" "$file")
    clean_output=$(echo "$output" | sed 's/\x1B\[[0-9;]*[a-zA-Z]//g'| tr -d '\r')

    # 3. Wyodrębnianie wyników (linie zawierające znak '>' i liczbę po nim)
    results=()
    while IFS= read -r line; do
        if [[ "$line" =~ \>[[:space:]]*([0-9]+) ]]; then
            wynik="${BASH_REMATCH[1]}"
            results+=("$wynik")
        fi
    done <<< "$clean_output"

    # 4. (Opcjonalnie) Porównanie wyników z oczekiwanymi
    IFS=' ' read -r -a expected <<< "${expected_results[$file]}"
    if [ "${#expected[@]}" -gt 0 ]; then
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
    fi

    # 5. Wyodrębnianie kosztu z ostatniej linii
    last_line=$(echo "$clean_output" | tail -n 1)
    new_cost=$(echo "$last_line" | awk -F'koszt: ' '{split($2, a, ";"); print a[1]}')

    # 6. Aktualizacja (nadpisanie) wpisu w pliku wyniki.txt
    existing_line=$(grep "^$file " "$wynikiPlik")
    if [ -z "$existing_line" ]; then
        echo "$file $new_cost $new_code_length" >> "$wynikiPlik"
        echo "Zapisano nowy rekord dla $file: koszt = $new_cost, długość kodu = $new_code_length"
    else
        old_cost=$(echo "$existing_line" | awk '{print $2}')
        old_length=$(echo "$existing_line" | awk '{print $3}')

        # Porównanie kosztu
        if [ "$new_cost" -eq "$old_cost" ]; then
            echo "Koszt dla $file się nie zmienił: $new_cost"
        elif [ "$new_cost" -lt "$old_cost" ]; then
            diff=$(echo "scale=2; (($old_cost - $new_cost) * 100)/$old_cost" | bc)
            echo "Koszt dla $file jest lepszy o $diff% (poprzedni: $old_cost, nowy: $new_cost)"
        else
            diff=$(echo "scale=2; (($new_cost - $old_cost) * 100)/$old_cost" | bc)
            echo "Koszt dla $file jest gorszy o $diff% (poprzedni: $old_cost, nowy: $new_cost)"
        fi

        # Porównanie długości kodu
        if [ "$new_code_length" -eq "$old_length" ]; then
            echo "Długość kodu dla $file się nie zmieniła: $new_code_length"
        elif [ "$new_code_length" -lt "$old_length" ]; then
            diff_length=$(echo "scale=2; (($old_length - $new_code_length) * 100)/$old_length" | bc)
            echo "Długość kodu dla $file jest mniejsza o $diff_length% (poprzednia: $old_length, nowa: $new_code_length)"
        else
            diff_length=$(echo "scale=2; (($new_code_length - $old_length) * 100)/$old_length" | bc)
            echo "Długość kodu dla $file jest większa o $diff_length% (poprzednia: $old_length, nowa: $new_code_length)"
        fi

        # Nadpisujemy istniejący rekord (zastępujemy całą linię)
        sed -i "s/^$file .*/$file $new_cost $new_code_length/" "$wynikiPlik"
    fi

done

echo "Koniec testów. Aktualne wyniki zapisane w $wynikiPlik."