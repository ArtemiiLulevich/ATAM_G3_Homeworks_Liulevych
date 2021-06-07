package Homework_1;


import javax.management.relation.RoleList;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        DataClass dataClass = new DataClass();
        List<String> convertedList = dataClass.arrayToListConverter(dataClass.anArrayOfCities);

        //Homework_1
        System.out.println("=============Homework_1=============");
        SubroutineClass.printList(convertedList);

        //Homework_2
        System.out.println("=============Homework_2=============");
        SubroutineClass.streamList(null);
        SubroutineClass.streamList(convertedList);
        SubroutineClass.streamList(new ArrayList<String>());
    }

}
