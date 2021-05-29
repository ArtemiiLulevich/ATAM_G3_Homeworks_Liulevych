package Homework_1;

import java.util.List;

public class SubroutineClass {
    /**
     *  Класс созданый по второму пункту задания.
     *
     *  printList - метод выводящий в консоль данные из списка.
     */

    public static void printList(List<String> anyList){
        for (String someData: anyList){
            System.out.println(someData);
        }
    }
}
