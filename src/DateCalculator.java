public class DateCalculator {
    public static Date addToDate(Date date, int num) {
        if (num == 0)
            return date;
        if(num < 0)
            return addToDate(new Date(1,1,2001), 0);
        if (date.getMonth() == 1 || date.getMonth() == 3 || date.getMonth() == 5 ||
                date.getMonth() == 7 || date.getMonth() == 8 || date.getMonth() == 10 || date.getMonth() == 12) {
            if (date.getDay() + 1 <= 31)
                return addToDate(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
            if (date.getDay() + 1 > 31) {
                if (date.getMonth() == 12) {
                    return addToDate(new Date(1, 1, date.getYear() + 1), num-1);
                } else
                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            }
        }
        if (date.getMonth() == 4 || date.getMonth() == 6 || date.getMonth() == 9 || date.getMonth() == 11) {
            if (date.getDay() + 1 <= 30)
                return addToDate(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
            if (date.getDay() + 1 > 30)
                return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
        }

        if(date.getMonth() == 2)
        {
            if ((date.getYear() % 4 == 0 && date.getYear() % 100 != 0) || (date.getYear() % 400 == 0)) {
                if (date.getDay() + 1 <= 29)
                    return addToDate(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
                if (date.getDay() + 1 > 29)
                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            }
            else{
                if (date.getDay() + 1 <= 28)
                    return addToDate(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
                if (date.getDay() + 1 > 28)
                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            }
        }
        return date;
    }
}
