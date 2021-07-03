package Homework_3.entities;

public class Currency {

    private String name;
    private double nominal;


    public Currency(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Currency setName(String name) {
        this.name = name;
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
                "\"Nominal\": \"%s\"}",
                this.name, this.nominal);
    }

}
