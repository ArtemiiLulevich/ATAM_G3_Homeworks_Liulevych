package Homework_1;

public class Application {
    public static void main(String[] args) {

        DataClass dataClass = new DataClass();

        SubroutineClass.printList(dataClass.arrayToListConverter(dataClass.anArrayOfCities));

    }
}
