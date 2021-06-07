package Homework_1;

import java.util.Arrays;
import java.util.List;

public class SubroutineClass {
    /**
     *  Класс созданый по второму пункту задания.
     *
     *  printList - метод выводящий в консоль данные из списка.
     *
     *  streamList - метод выводящий в консоль данные в формате массива букв.
     *  Так же, обрабатывает исключения.
     */

    public static void printList(List<String> anyList){
        for (String someData: anyList){
            System.out.println(someData);
        }
    }

    public static void streamList(List<String> anyList){
        try {
            anyList.stream().
                    map(element -> Arrays.toString(element.split(""))).
                    forEach(System.out::println);
        } catch (NullPointerException e){
            System.out.println("The array is undefined. Please check " +
                    "if the entered values are correct.");
        } catch (Exception e){
            System.out.println("An unknown error has occurred. " +
                    "More details: " + e.getMessage());
        }

    }
}
