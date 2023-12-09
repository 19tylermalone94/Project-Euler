public class DateClock {

    int year = 1901;
    int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int monthIndex = 0; // January
    int day = 2; // Monday
    int date = 0;

    public void nextDay() {
        months[1] = ((year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) ? 29 : 28;
        day = (day + 1) % 7;
        date = (date == months[monthIndex] - 1) ? 0 : date + 1;
        monthIndex = (date == 0) ? (monthIndex + 1) % 12 : monthIndex;
        year = (date == 0 && monthIndex == 0) ? year + 1 : year;
    }

    public static void main(String[] args) {
        DateClock clock = new DateClock();
        int numSundaysOnFirst = 0;
        while (clock.year < 2001) {
            if (clock.day == 0 && clock.date == 0) ++numSundaysOnFirst;
            clock.nextDay();
        }
        
        System.out.println(numSundaysOnFirst);
    }

} 