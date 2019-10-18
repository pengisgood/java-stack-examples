package tech.codinglife.javastack;

import static tech.codinglife.javastack.Day.MONDAY;

/**
 * JDK 13 introduced a new syntax form for switch expression: <code>case L -></code>
 * There is no FALL THROUGH now!!
 * <p>
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019/10/15
 * Time: 5:55 PM
 */
public class SwitchFallThrough {
    public static void main(String[] args) {
        Day day = MONDAY;

        // before Java 13
        // It is necessary to remember add the `break;`, otherwise may result in a bug!
        switch (day) {
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                System.out.println(7); //output: 7
                break;
            case TUESDAY:
                System.out.println(2);
                break;
            case THURSDAY:
            case SATURDAY:
                System.out.println(6);
                break;
            case WEDNESDAY:
                System.out.println(3);
                break;
        }

        // Java 13
        // No need to use `break;` now. There is no fall through by default.
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> System.out.println(7); //output: 7
            case TUESDAY                -> System.out.println(2);
            case THURSDAY, SATURDAY     -> System.out.println(6);
            case WEDNESDAY              -> System.out.println(3);
        }
    }
}
