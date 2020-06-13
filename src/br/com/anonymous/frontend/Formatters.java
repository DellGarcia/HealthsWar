package br.com.anonymous.frontend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Formatters {
    public static DateFormat DATE_SQL = new SimpleDateFormat("yyyy-MM-dd");    
    public static DateFormat DATE_HOUR_SQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static DateFormat DATE_BR = new SimpleDateFormat("dd-MM-yyyy");    
    public static DateFormat DATE_BR_YY = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    public static DateFormat DATE_BR_FULL = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static DateFormat TIME = new SimpleDateFormat("HH:mm:ss");
    public static DateFormat DATE_DAY = new SimpleDateFormat("dd");

}