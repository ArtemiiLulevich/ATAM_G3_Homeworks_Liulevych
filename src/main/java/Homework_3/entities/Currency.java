package Homework_3.entities;

public class Currency {

    private String name;
    private double nominal;
    private static int buyCourse;
    private static int sellCourse;


    public Currency(String name) {
        this.name = name;
        switch (name) {
            case "UAH" -> {
                buyCourse = 1;
                sellCourse = 1;
            }
            case "USD" -> {
                buyCourse = 2;
                sellCourse = 2;
            }
            case "EUR" -> {
                buyCourse = 3;
                sellCourse = 3;
            }
            default -> {
                buyCourse = 0;
                sellCourse = 0;
            }
        }
    }


    public String getName() {
        return name;
    }

    public Currency setName(String name) {
        this.name = name;
        return this;
    }

    public int getBuyCourse() {
        return buyCourse;
    }

    public Currency setBuyCourse(int buyCourse) {
        Currency.buyCourse = buyCourse;
        return this;
    }

    public int getSellCourse() {
        return sellCourse;
    }

    public Currency setSellCourse(int sellCourse) {
        Currency.sellCourse = sellCourse;
        return this;
    }

    public double getNominal() {
        return nominal;
    }

    public Currency setNominal(double nominal) {
        this.nominal = nominal;
        return this;
    }

    public Currency clone(){
        return new Currency(this.name);
    }


    @Override
    public String toString() {
        return String.format("{\"Name\": \"%s\"," +
                "\"Nominal\": \"%s\"," +
                "\"Buy Course\": \"%s\"," +
                "\"Sell Course\": \"%s\"}",
                this.name, this.nominal, buyCourse, sellCourse);
    }

}
