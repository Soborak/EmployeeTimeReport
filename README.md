# EmployeeTimeReport

Instrukcja obsługi:

Pobierz plik .jar z katalogu release.

Uruchom program z konsoli, podając ścieżkę do katalogu z danymi jako parametr,
np.:  

java -jar release/reporter.jar reporter-dane


Program automatycznie wczyta wszystkie pliki Excela z wybranego katalogu
i podkatalogów, a następnie wyświetli na ekranie:

- ranking pracowników wg przepracowanych godzin,
- ranking miesięcy wg przepracowanych godzin,
- ranking 10-u najbardziej pracowitych dni.

Kod źródłowy znajduje się w katalogu src tego repozytorium.

Gotowy plik .jar do pobrania: release/reporter.jar
