package tech.codinglife.javastack;

import static tech.codinglife.javastack.Day.MONDAY;

/**
 * Created by IntelliJ IDEA.
 * Author: maxpeng
 * Date: 2019/10/16
 * Time: 11:32 AM
 */
public class SwitchExpression {

    public static void main(String[] args) {
        Day day = MONDAY;

        // This is `switch statement`
        switch (day) {
            case MONDAY:
            case TUESDAY:
                int temp = 2;     // The scope of 'temp' continues to the }
                break;
            case WEDNESDAY:
                return;
            case THURSDAY:
                int temp2 = 4;    // Can't call this variable 'temp'
                break;
            default:
                int temp3 = 0;    // Can't call this variable 'temp'
        }

        int numLetters;
        // This is `switch statement`
        switch (day) {
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                numLetters = 7;
                break;
            case TUESDAY:
                numLetters = 2;
                break;
            case THURSDAY:
            case SATURDAY:
                numLetters = 6;
                break;
            case WEDNESDAY:
                numLetters = 3;
                break;
            default:
                throw new IllegalStateException("Wat: " + day);
        }
        System.out.println(numLetters);

        // This is `switch statement`
        numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 7;
            case TUESDAY                -> 2;
            case THURSDAY, SATURDAY     -> 6;
            case WEDNESDAY              -> 3;
        };
        System.out.println(numLetters);

        // This is `switch statement`
        // inline the variable numLetters
        System.out.println(switch (day){
            case MONDAY, FRIDAY, SUNDAY -> 7;
            case TUESDAY                -> 2;
            case THURSDAY, SATURDAY     -> 6;
            case WEDNESDAY              -> 3;
        });

    }
}
