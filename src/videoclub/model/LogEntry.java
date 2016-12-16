/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclub.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author cheikh
 */
public class LogEntry {

    private String type;
    private String nomEmploye;
    private LocalDateTime date;
    private String dateFormatted;
    private String details;

    public LogEntry(String type, String employe, String details) {
        this.type = type;
        this.nomEmploye = employe;
        this.details = details;
        this.date = LocalDateTime.now();
        this.dateFormatted = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));
    }

    public LogEntry(String type, String employe, LocalDateTime date, String details) {
        this.type = type;
        this.nomEmploye = employe;
        this.date = date;
        this.details = details;
        this.dateFormatted = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm"));

    }

    public String getType() {
        return type;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDateFormatted() {
        return dateFormatted;
    }

    public String getDetails() {
        return details;
    }

}
