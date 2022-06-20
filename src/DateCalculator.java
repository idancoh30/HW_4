public class DateCalculator {
    public static Date addToDate(Date date, int num) {
        if(num > 0)
            return addPositive(date, num);
        if(num < 0)
            return addNegative(date, num);
        return date;
    }

    public static boolean isLeapYear(Date date) {
        return ((date.getYear() % 4 == 0 && date.getYear() % 100 != 0) || (date.getYear() % 400 == 0));
    }
    public static int getDaysInMonth(Date date)
    {
        if (date.getMonth() == 4 || date.getMonth() == 6
                || date.getMonth() == 9 || date.getMonth() == 11)
            return 30;
        if (date.getMonth() == 1 || date.getMonth() == 3 || date.getMonth() == 5
                || date.getMonth() == 7 || date.getMonth() == 8
                || date.getMonth() == 10 || date.getMonth() == 12)
            return 31;
        if(date.getMonth() == 2)
        {
            if(isLeapYear(date))
                return 29;
            else
                return 28;
        }
        return -1;
    }

    public static Date addPositive(Date date, int num)
    {
        if(num == 0)
            return date;
        if(num >= 365) // Necessary check to prevent many recursive calls that will cause stack overflow.
        {//
            if(isLeapYear(date)) // leap year contains 366 days.
                return addPositive(new Date(date.getDay(), date.getMonth(), date.getYear()+1), num-366);
            else
                return addPositive(new Date(date.getDay(), date.getMonth(), date.getYear()+1), num-365);
        }
        if(date.getDay() + 1 > getDaysInMonth(date))
        {
            if(date.getMonth() == 12)
                return addPositive(new Date(1,1,date.getYear()+1), num-1);
            else
                return addPositive(new Date(1,date.getMonth()+1, date.getYear()), num-1);
        }
        else
            return addPositive(new Date(date.getDay()+1, date.getMonth(), date.getYear()),num-1);
    }

    public static Date addNegative(Date date, int num) {
        if (num == 0)
            return date;
        if (num <= -365) {
            if (isLeapYear(date)) // leap year contains 366 days.
                return addNegative(new Date(date.getDay(), date.getMonth(), date.getYear() - 1), num + 366);
            else
                return addNegative(new Date(date.getDay(), date.getMonth(), date.getYear() - 1), num + 365);
        }
        if (date.getDay() - 1 == 0) {
            if(date.getMonth() == 1) // We're on january, need to take one year back.
                return addNegative(new Date(31,12,date.getYear()-1),num+1);
            int daysInNewMonth = getDaysInMonth(new Date(1, date.getMonth()-1,date.getYear()));
            return addNegative(new Date(daysInNewMonth,date.getMonth()-1,date.getYear()), num+1);
        }
        return addNegative(new Date(date.getDay()-1, date.getMonth(), date.getYear()), num+1);
    }
}