package pl.soborak;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class RankingCalculator {

    public static class Rankings {
        public LinkedHashMap<String, Double> employeeRanking;
        public LinkedHashMap<String, Double> monthRanking;
        public LinkedHashMap<String, Double> topDaysRanking;
    }

    // Oblicz wszystkie rankingi na podstawie listy wpisów
    public static Rankings calculateAllRankings(List<EmployeeRecord> records) {
        Rankings rankings = new Rankings();

        // Ranking pracowników
        Map<String, Double> employeeMap = new HashMap<>();
        for (EmployeeRecord rec : records) {
            employeeMap.merge(rec.employeeName, rec.hours, Double::sum);
        }
        rankings.employeeRanking = sortDescending(employeeMap);

        // Ranking miesięcy
        SimpleDateFormat monthFormat = new SimpleDateFormat("LLLL yyyy", new Locale("pl"));
        Map<String, Double> monthMap = new HashMap<>();
        for (EmployeeRecord rec : records) {
            String month = monthFormat.format(rec.date);
            monthMap.merge(month, rec.hours, Double::sum);
        }
        rankings.monthRanking = sortDescending(monthMap);

        // Ranking 10 najbardziej pracowitych dni
        SimpleDateFormat dayFormat = new SimpleDateFormat("d MMMM yyyy", new Locale("pl"));
        Map<String, Double> dayMap = new HashMap<>();
        for (EmployeeRecord rec : records) {
            String day = dayFormat.format(rec.date);
            dayMap.merge(day, rec.hours, Double::sum);
        }
        rankings.topDaysRanking = sortDescending(dayMap).entrySet()
                .stream().limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return rankings;
    }

    // Pomocnicza metoda do sortowania malejąco
    private static LinkedHashMap<String, Double> sortDescending(Map<String, Double> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new
                ));
    }
}
