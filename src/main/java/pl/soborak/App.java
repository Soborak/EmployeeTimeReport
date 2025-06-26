package pl.soborak;

public class App {
  public static void main(String[] args) {
    // Sprawdzenie, czy podano katalog z danymi jako argument
    if (args.length != 1) {
      System.out.println("Użycie: java -jar EmployeeHoursRanking.jar <ścieżka_do_katalogu_z_danymi>");
      System.exit(1);
    }
    String dataDir = args[0];

    // Wczytaj wszystkie wpisy pracowników z plików Excela
    var records = ExcelReader.readAllRecords(dataDir);

    // Oblicz rankingi
    var rankings = RankingCalculator.calculateAllRankings(records);

    // Wyświetl rankingi na ekranie
    ConsolePrinter.printAllRankings(rankings);
  }
}
