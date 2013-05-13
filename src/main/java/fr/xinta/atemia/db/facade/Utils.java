package fr.xinta.atemia.db.facade;

import fr.xinta.atemia.db.entity.Week;
import java.util.LinkedList;
import java.util.List;

public class Utils {
    
    public static List<Week> getWeeks(Week startWeek, Week endWeek) {
        List<Week> list = new LinkedList<Week>();
        int week = endWeek.getNumber();
        int year = endWeek.getYear();
        while ((year == startWeek.getYear() && week >= startWeek.getNumber())
                || year > startWeek.getYear()) {
            list.add(0, new Week(week, year));
            
            if (week > 1) {
                week--;
            } else {
                week = 52;
                year--;
            }
        }
        return list;
    }
}
