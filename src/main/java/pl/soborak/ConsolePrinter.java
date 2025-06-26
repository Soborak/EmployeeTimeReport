package pl.soborak;

public class ConsolePrinter {

    public static void printAllRankings(RankingCalculator.Rankings rankings) {
        System.out.println("=== Ranking pracowników wg przepracowanych godzin ===");
        printRanking(rankings.employeeRanking, "godzin");

        System.out.println("\n=== Ranking miesięcy wg przepracowanych godzin ===");
        printRanking(rankings.monthRanking, "godzin");

        System.out.println("\n=== 10 najbardziej pracowitych dni ===");
        printRanking(rankings.topDaysRanking, "godzin");
    }

    private static void printRanking(java.util.Map<String, Double> ranking, String unit) {
        int pos = 1;
        for (var entry : ranking.entrySet()) {
            System.out.printf("%2d. %-30s - %.2f %s%n", pos++, entry.getKey(), entry.getValue(), unit);
        }
    }
}
