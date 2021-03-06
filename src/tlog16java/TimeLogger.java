package tlog16java;

import java.util.List;

import java.util.ArrayList;
import tlog16java.Exceptions.NotNewMonthException;
/**
 * TimeLogger is the class which contains the working months
 * @author Kovács Kornél
 * @version 0.1.0
 * @since 2016-11-03
 */
public class TimeLogger{
    @lombok.Getter
    private final List<WorkMonth> months = new ArrayList<>();
    
    /**
     * Checks if the given working month exists
     * @param wm Working month
     * @return true if the WorkMonth already added to TimeLogger<br>false if it is a new WorkMonth
     */

    public boolean isNewMonth(WorkMonth wm) {
        boolean isnew = true;
        int i;
        for (i = 0; i < months.size(); i++) {
            if (months.get(i).date.equals(wm.date)) {
                isnew = false;
                break;
            }
        }
        return isnew;
    }
    
    /**
     *Adds a month to the TimeLogger
     * @param wm Working month
     * @exception NotNewMonthException if the month already exists
     */
    public void addMonth(WorkMonth wm) {
        if (isNewMonth(wm)) {
            if (months.add(wm)) {
                System.out.println("Successfully added a WorkMonth");
            } else {   
                System.out.println("Not added!");
            }
        } else {
            throw new NotNewMonthException();
        }
    }
    
}