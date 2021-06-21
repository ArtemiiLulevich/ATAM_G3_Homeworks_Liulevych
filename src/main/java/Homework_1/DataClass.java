package Homework_1;

import java.util.ArrayList;
import java.util.List;

public class DataClass {
    /**
     * Класс создан по первому пункту задания.
     * anArrayOfCities - массив строк.
     * arrayToListConverter - метод конвертирующий массив строк в список
     * строк. Возвращает список строк.
     * */
    public String[] anArrayOfCities = {
            "Kiev", "London", "Rome",
            "Minsk", "Warsaw", "Berlin",
            "Paris", "Milan", "Rio"
    };//  shortcut из документации.

    public List<String> arrayToListConverter(String[] ConvertingArray){
        List<String> aListOfCities = new ArrayList<>();

        int index = 0;

        while (index != 6){
            if (index == 3){
                index++;
                continue;
            }

            aListOfCities.add(ConvertingArray[index]);

            index++;
        }
        return aListOfCities;
    }
}
