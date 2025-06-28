package pl.soborak;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsolePrinter {

    public static void printAllRankings(RankingCalculator.Rankings rankings) {

        System.out.println("\n=== Ranking pracowników wg przepracowanych godzin ===\n");
        printRanking(rankings.employeeRanking, "Imię i nazwisko", "godzin");

        System.out.println("\n\n=== Ranking miesięcy wg przepracowanych godzin ===\n");
        printRanking(rankings.monthRanking, "Miesiąc", "godzin");

        System.out.println("\n\n=== Ranking 10-u najbardziej pracowitych dni ===\n");
        printRanking(rankings.topDaysRanking, "Data", "godzin");
    }

    private static void printRanking(Map<String, Double> ranking, String keyHeader, String unit) {
        // lista wpisów z mapy
        List<Map.Entry<String, Double>> entries = new ArrayList<>(ranking.entrySet());

        // klucz (założenie: ma zaczynać się od cyfry)
        boolean isDate = !entries.isEmpty() && entries.get(0).getKey().matches("\\d{1,2} .*");

        // Sortowanie
        entries.sort((e1, e2) -> {
            int cmp = Double.compare(e2.getValue(), e1.getValue()); // malejąco po godzinach
            if (cmp != 0) return cmp;
            if (isDate) {
                // Jeżeli klucz to data, porównaj po dacie rosnąco
                return parseDate(e1.getKey()).compareTo(parseDate(e2.getKey()));
            } else {
                // W innym przypadku porównaj alfabetycznie
                return e1.getKey().compareTo(e2.getKey());
            }
        });

        // Nagłówek tabeli
        System.out.printf("| %-3s | %-30s | %-13s |\n", "LP", keyHeader, "Liczba " + unit);
        System.out.println("|-----|--------------------------------|---------------|");

        int pos = 1;
        for (var entry : entries) {
            System.out.printf("| %3d | %-30s | %13.2f |\n",
                    pos++, entry.getKey(), entry.getValue());
        }
    }

    // Funkcja pomocnicza do parsowania daty z polskiego formatu "15 lutego 2012"
    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("pl"));
        return LocalDate.parse(dateStr, formatter);
    }
}
