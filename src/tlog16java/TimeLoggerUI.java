package tlog16java;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;


/**
 * Contains methods for the user interface
 * 
 * @author Kovács Kornél
 * @version 0.1.0
 * @since 2016-11-03
 */

public class TimeLoggerUI {

private void listMonths(TimeLogger tl)
{
            if(tl.getMonths().isEmpty())
            {
                System.out.println("No months available");
            }
            else
            {
                for(int i=0;i<tl.getMonths().size();i++)
                {
                    System.out.println((i+1) + "\t" + tl.getMonths().get(i).getDate());
                }   
            }
}
    
private void createDay(int month, int day, long workh, TimeLogger tl)
{
    WorkMonth WM = tl.getMonths().get(month-1);
    WorkDay wd = new WorkDay(LocalDate.of(WM.getDate().getYear(), WM.getDate().getMonth(), day), workh);
    WM.addWorkDay(wd);
}

private void listDays(int Month, TimeLogger tl)
{
        WorkMonth WM = tl.getMonths().get(Month-1);
            if(WM.getDays().isEmpty())
            {
                System.out.println("No days available");
            }
            else
            {
                System.out.println("Workdays of the selected month:");
                for(int i=0;i<WM.getDays().size();i++)
                {                        
                            System.out.println((i+1) + "\t" + WM.getDate() + "-" + WM.getDays().get(i).getActualDay().getDayOfMonth());
                }   
            }
}

private void addMonth(int y, int m,TimeLogger tl)
{
                WorkMonth WM = new WorkMonth(YearMonth.of(y,m));
                tl.addMonth(WM);
}

private void listTask(int month, int day,TimeLogger tl)
{
            WorkMonth WM = tl.getMonths().get(month-1);
            WorkDay WD = WM.getDays().get(day-1);
            if(WD.getTasks().isEmpty())
            {
                System.out.println("No tasks available");
            }
            else
            {
                for(int i=0;i<WD.getTasks().size();i++)
                {
                    System.out.println((i+1) + "\t" + WD.getTasks().get(i).getTaskId());
                }   
           }   
}

private void listUnfinishedTask(int month, int day,TimeLogger tl)
{
            WorkMonth WM = tl.getMonths().get(month-1);
            WorkDay WD = WM.getDays().get(day-1);
            boolean x = false;
            if(WD.getTasks().isEmpty())
            {
                System.out.println("No tasks available");
            }
            else
            {
                for(int i=0;i<WD.getTasks().size();i++)
                {
                    if(WD.getTasks().get(i).getEndTime() == null)
                    {
                    System.out.println((i+1) + "\t" + WD.getTasks().get(i).getTaskId());
                    x = true;
                    }
                }
                if (x == false) System.out.println("No unfinished tasks available");
           }   
}

private void startTask(int m, int d, String taskid,String sTime, String comment, TimeLogger tl)
{
    WorkMonth WM = tl.getMonths().get(m-1);
    Task task = new Task(taskid, sTime, comment);
    WM.getDays().get(d-1).addTask(task);
}

private void delTask(int m, int d, int tind,TimeLogger tl)
{
    WorkMonth WM = tl.getMonths().get(m-1);
    WorkDay WD = WM.getDays().get(d-1);
    WD.getTasks().remove(tind-1);
}

private void menuSelect(TimeLogger tl)
{
    System.out.println("Please select an option");
    Scanner user_input = new Scanner(System.in);
    int selection;
    selection = user_input.nextInt();
    switch(selection)
    {
        case 0:
        {
            break;
        }
        case 1:
        {
            listMonths(tl);
            menu(tl);
            break;
        }
        case 2:
        {
            listMonths(tl);
            System.out.println("Please select a month (row number) ");
            int selected = user_input.nextInt();
            listDays(selected, tl);
            menu(tl);
            break;
        }
        case 3:
        {
            listMonths(tl);
            System.out.println("Please give me the month (row number)");
            int month = user_input.nextInt();
            listDays(month, tl);
            System.out.println("Please give me the day (row number)");
            int day = user_input.nextInt();
            listTask(month, day, tl);
            menu(tl);
            break;
        }
        case 4:
        {
            System.out.println("Please tell me the year and the month(with numbers) you want to add:");
            int year ,month;
            year = user_input.nextInt();
            month = user_input.nextInt();
            addMonth(year, month, tl);
            menu(tl);
            break;
        }
        case 5:
        {
            listMonths(tl);
            if(!tl.getMonths().isEmpty())
            {
            System.out.println("Please give me the month's row number");
            int month = user_input.nextInt();
            System.out.println("Please give me the day you want to add");
            int day = user_input.nextInt();
            System.out.println("Please tell me the required working hours in minutes (type 0 for default value)");
            long workh = user_input.nextLong();
            if(workh == 0) workh = 450;
            createDay(month, day, workh, tl);
            }
            menu(tl);
            break;
        }
        case 6:
        {
            int m,d;
            String taskid, comment, sTime;
            listMonths(tl);
            System.out.println("Please give me the month (row number)");
            m = user_input.nextInt();
            listDays(m, tl);
             System.out.println("Please give me the day (row number)");           
            d = user_input.nextInt();
            System.out.println("Please give me the Task ID");
            taskid = user_input.next();
            System.out.println("Please tell me what you do");
            comment = user_input.next();
            System.out.println("Please tell me when you start (HH:MM)");
            sTime = user_input.next();
            startTask(m,d,taskid,sTime,comment, tl);
            menu(tl);
            break;
        }
        case 7:
        {
            int m,d;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            listUnfinishedTask(m,d,tl);
            System.out.println("Please tell me what task you ended (row number)");
            int ta = user_input.nextInt();
            System.out.println("Please tell me the end time (HH:MM)");
            String end = user_input.next();
            tl.getMonths().get(m-1).getDays().get(d-1).getTasks().get(ta-1).setEndTime(end);
            menu(tl);
            break;
        }
        case 8:
        {
            int m,d;
            String conf;
            boolean cont = false;
            listMonths(tl);
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
            listDays(m, tl);
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            listTask(m,d,tl);
            System.out.println("Please tell me the task's index");
            int tind = user_input.nextInt();
            do{
            System.out.println("Are you sure you want to delete the " + tl.getMonths().get(m-1).getDays().get(d-1).getTasks().get(tind-1).getTaskId() + " task? (yes/no)");
            conf = user_input.next();
                switch (conf) {
                    case "yes":
                        delTask(m,d,tind,tl);
                        cont = true;
                        menu(tl);
                        break;
                    case "no":
                        menu(tl);
                        cont=true;
                        break;
                    default:
                        System.out.println("Please choose yes or no");
                        break;
                }
            }while(cont != true);
            break;
        }

        case 9:
        {
            Task task;
            int m,d;
            String nc;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
             System.out.println("Please tell me the day");           
            d = user_input.nextInt();
            listTask(m,d,tl);
            System.out.println("Please select a tasks index");
            int tind = user_input.nextInt();
            task = tl.getMonths().get(m-1).getDays().get(d-1).getTasks().get(tind-1);
            System.out.println("Please set the taskID("+task.getTaskId()+")");
            String ntid = user_input.next();
            task.setTaskId(ntid);
            System.out.println("Please set the Start Time ("+task.getStartTime()+")( HH:MM )");
            String st = user_input.next();
            task.setStartTime(st);
            System.out.println("Please set the Finishing Time ("+task.getEndTime()+")( HH:MM )");
            String et = user_input.next();
            task.setEndTime(et);
            System.out.println("Please set the comment("+task.getComment()+")");
            nc = user_input.next();
            task.setComment(nc);
            break;
        }

        case 10:
        {
            int m,i,j;
            System.out.println("Please tell me the month");
            m = user_input.nextInt();
            WorkMonth WM = tl.getMonths().get(m-1);
            System.out.println("\t\t\t\t\t" + WM.getExtraMinPerMonth());
            for(i=0;i<WM.getDays().size();i++)
            {
            WorkDay WD = WM.getDays().get(i);
            System.out.println(WD.getActualDay() + "\t" + WD.getSumPerDay() + "\t" + WD.getTasks().get(0).getStartTime() + "\t" + WD.getExtraMinPerDay());
                for (j=0;j<WD.getTasks().size();j++)
                {
                    Task t = WD.getTasks().get(j);
                    System.out.println(t.getMinPerTask() + "\t" + t.getTaskId() + "\t" + t.getComment() + "\t" + t.getEndTime());
                }
               System.out.println();
            }
            break;
        }
        
    }
}    
   
    /**
     * Prints the menu
     * @param tl {@link TimeLogger}
     */
    public void menu(TimeLogger tl)
{
    System.out.println("0. Exit");
    System.out.println("1. List months");
    System.out.println("2. List days");
    System.out.println("3. List task for a specific day");
    System.out.println("4. Add new month");
    System.out.println("5. Add day to a specific month");
    System.out.println("6. Start a task for a day");
    System.out.println("7. Finish a specific task");
    System.out.println("8. Delete a task");
    System.out.println("9. Modify task");
    System.out.println("10. Statistics");
    menuSelect(tl);
}
}
