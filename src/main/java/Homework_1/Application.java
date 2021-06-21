package Homework_1;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        Homework_1.DataClass dataClass = new Homework_1.DataClass();
        Homework_1.SubroutineClass subroutineClass = new Homework_1.SubroutineClass();
        List<String> convertedList = dataClass.arrayToListConverter(dataClass.anArrayOfCities);

        //Homework_1
        System.out.println("=============Homework_1=============");
        subroutineClass.printList(convertedList);

        //Homework_2
        System.out.println("=============Homework_2=============");
        subroutineClass.streamList(null);
        subroutineClass.streamList(convertedList);
        subroutineClass.streamList(new ArrayList<>());
    }

}
