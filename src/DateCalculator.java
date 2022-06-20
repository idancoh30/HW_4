public class DateCalculator {
    public static Date addToDate(Date date, int num) {
        if (num < 0) return DateCalculator.addNegative(date, num);
        if (num > 0) return DateCalculator.addPositive(date, num);
        return date;
    }

    private static int whichMonth(Date date) {
        if (date.getMonth() == 4 || date.getMonth() == 6
                || date.getMonth() == 9 || date.getMonth() == 11)
            return 30;
        if (date.getMonth() == 1 || date.getMonth() == 3 || date.getMonth() == 5
                || date.getMonth() == 7 || date.getMonth() == 8
                || date.getMonth() == 10 || date.getMonth() == 12)
            return 31;
        return 2;
    }

    private static Date addPositive(Date date, int num) {
        if (num == 0) return date;
        if (num >= 365) {
            return addPositive(new Date(date.getDay(), date.getMonth(), date.getYear() + (num / 365)), num % 365);
        }
        if (whichMonth(date) == 31) {
            if (date.getDay() + 1 <= 31)
                return addPositive(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
            if (date.getDay() + 1 > 31) {
                if (date.getMonth() == 12) {
                    return addPositive(new Date(1, 1, date.getYear() + 1), num - 1);
                } else return addPositive(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            }
        }

        if (whichMonth(date) == 30) {
            if (date.getDay() + 1 <= 30)
                return addPositive(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
            if (date.getDay() + 1 > 30) return addPositive(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
        }

        if (whichMonth(date) == 2) {
            if ((date.getYear() % 4 == 0 && date.getYear() % 100 != 0) || (date.getYear() % 400 == 0)) {
                if (date.getDay() + 1 <= 29)
                    return addPositive(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
                if (date.getDay() + 1 > 29)
                    return addPositive(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            } else {
                if (date.getDay() + 1 <= 28)
                    return addPositive(new Date(date.getDay() + 1, date.getMonth(), date.getYear()), num - 1);
                if (date.getDay() + 1 > 28)
                    return addPositive(new Date(1, date.getMonth() + 1, date.getYear()), num - 1);
            }
        }
        return date;
    }

    private static Date addNegative(Date date, int num) {
        if (num == 0)
            return date;
        if (num <= -365) {
            return addNegative(new Date(date.getDay(), date.getMonth(), date.getYear() + (num / 365)), num % 365);
        }
        if (date.getDay() - 1 <= 0) {
            if (date.getMonth() - 1 == 1 || date.getMonth() - 1 == 3 || date.getMonth() - 1 == 5
                    || date.getMonth() - 1 == 7 || date.getMonth() - 1 == 8
                    || date.getMonth() - 1 == 10 || date.getMonth() - 1 == 12) {
                return addNegative(new Date(31, date.getMonth() - 1, date.getYear()), num + 1);
            }
            if (date.getMonth()-1 == 4 || date.getMonth()-1 == 6
                    || date.getMonth()-1 == 9 || date.getMonth()-1 == 11) {
                return addNegative(new Date(30, date.getMonth() - 1, date.getYear()), num + 1);
            }
            if(date.getMonth()-1 == 2) {
                if ((date.getYear() % 4 == 0 && date.getYear() % 100 != 0) || (date.getYear() % 400 == 0)) {
                    return addNegative(new Date(29, date.getMonth() - 1, date.getYear()), num + 1);
                } else {
                    return addNegative(new Date(28, date.getMonth() - 1, date.getYear()), num + 1);
                }
            }
            if (date.getMonth() - 1 == 0) {
                return addNegative(new Date(31, 12, date.getYear() - 1), num + 1);
            }
        }
        return addNegative(new Date(date.getDay() - 1, date.getMonth(), date.getYear()), num + 1);
    }
}
