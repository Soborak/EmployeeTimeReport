package pl.soborak;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ExcelReader {

    // Wczytaj wszystkie wpisy ze wszystkich plików Excela (.xls i .xlsx) z katalogu i podkatalogów
    public static List<EmployeeRecord> readAllRecords(String rootDir) {
        List<EmployeeRecord> records = new ArrayList<>();
        try {
            // Szukaj plików .xls i .xlsx w całym drzewie katalogów
            Files.walk(Paths.get(rootDir))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xls") || path.toString().endsWith(".xlsx"))
                    .forEach(path -> {
                        String employeeName = extractEmployeeName(path.getFileName().toString());
                        records.addAll(readRecordsFromFile(path.toString(), employeeName));
                    });
        } catch (IOException e) {
            System.err.println("Błąd podczas przeszukiwania katalogów: " + e.getMessage());
        }
        return records;
    }

    // Wczytaj wpisy z pojedynczego pliku Excela
    private static List<EmployeeRecord> readRecordsFromFile(String filePath, String employeeName) {
        List<EmployeeRecord> records = new ArrayList<>();
        try (InputStream fis = new FileInputStream(filePath);
             Workbook workbook = filePath.endsWith(".xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow) { // Pomijamy nagłówki
                    firstRow = false;
                    continue;
                }
                EmployeeRecord record = EmployeeRecord.fromRow(row, employeeName);
                if (record != null) {
                    records.add(record);
                }
            }
        } catch (Exception e) {
            System.err.println("Błąd podczas czytania pliku: " + filePath + " (" + e.getMessage() + ")");
        }
        return records;
    }

    // Wyciąganie nazwy pracownika z nazwy pliku (np. "Kowalski_Jan.xls" -> "Kowalski Jan")
    private static String extractEmployeeName(String fileName) {
        String name = fileName.replace(".xls", "").replace(".xlsx", "");
        return name.replace("_", " ");
    }
}
