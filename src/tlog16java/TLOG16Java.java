package tlog16java;

/**
 * This is the main part of the TimeLogger<br>
 * This class creates a Timelogger and opens a menu
 * 
 * @author Kovács Kornél
 * @version 0.1.0
 * @since 2016-11-03
 */


public class TLOG16Java {
    public static void main(String[] args) {
     tlog16java.TimeLoggerUI tlui = new tlog16java.TimeLoggerUI();
     TimeLogger tl = new TimeLogger();
     tlui.menu(tl);
    }
}