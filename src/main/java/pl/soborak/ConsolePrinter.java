package pl.soborak;

import java.util.Map;


public class ConsolePrinter {

    public static void printAllRankings(RankingCalculator.Rankings rankings) {

        System.out.println("\n\n=== Ranking pracowników wg przepracowanych godzin ===\n");
        printRanking(rankings.employeeRanking, "Imię i nazwisko", "godzin");

        System.out.println("\n\n=== Ranking miesięcy wg przepracowanych godzin ===\n");
        printRanking(rankings.monthRanking, "Miesiąc", "godzin");

        System.out.println("\n\n=== 10 najbardziej pracowitych dni ===\n");
        printRanking(rankings.topDaysRanking, "Data", "godzin");

    }

    private static void printRanking(Map<String, Double> ranking, String keyHeader, String unit) {
        // Nagłówek tabeli
        System.out.printf("| %-3s | %-30s | %-13s |\n", "LP", keyHeader, "Liczba " + unit);
        System.out.println("|-----|--------------------------------|---------------|");

        int pos = 1;
        for (var entry : ranking.entrySet()) {
            System.out.printf("| %3d | %-30s | %13.2f |\n",
                    pos++, entry.getKey(), entry.getValue());
        }
    }
}
