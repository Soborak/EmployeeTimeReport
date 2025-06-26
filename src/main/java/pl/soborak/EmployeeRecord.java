package pl.soborak;

import org.apache.poi.ss.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeRecord {
    public String employeeName;
    public Date date;
    public String task;
    public double hours;

    public EmployeeRecord(String employeeName, Date date, String task, double hours) {
        this.employeeName = employeeName;
        this.date = date;
        this.task = task;
        this.hours = hours;
    }

    // Tworzenie obiektu z wiersza Excela
    public static EmployeeRecord fromRow(Row row, String employeeName) {
        try {
            Cell dateCell = row.getCell(0);
            Cell taskCell = row.getCell(1);
            Cell hoursCell = row.getCell(2);

            Date date = dateCell.getDateCellValue();
            String task = taskCell.getStringCellValue();
            double hours = hoursCell.getNumericCellValue();

            return new EmployeeRecord(employeeName, date, task, hours);
        } catch (Exception e) {
            // Możesz tu dodać logowanie błędów, jeśli wiersz jest niepoprawny
            return null;
        }
    }
}
