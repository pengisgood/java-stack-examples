package tech.codinglife.javastack;

import static tech.codinglife.javastack.Day.MONDAY;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019/10/18
 * Time: 11:06 AM
 */
public class SwitchYield {
    public static void main(String[] args) {
        Day day = MONDAY;

        System.out.println(switch (day){
            case MONDAY, FRIDAY, SUNDAY -> 7;
            case TUESDAY                -> 2;
            case THURSDAY, SATURDAY     -> 6;
            case WEDNESDAY              -> 3;
        });

        //If a code block is needed then use `yield` to return the result of the switch expression
        System.out.println(switch (day){
            case MONDAY, FRIDAY, SUNDAY -> {
                System.out.println("Yield");
                yield 7;
            }
            case TUESDAY                -> 2;
            case THURSDAY, SATURDAY     -> 6;
            case WEDNESDAY              -> 3;
        });
    }
}
