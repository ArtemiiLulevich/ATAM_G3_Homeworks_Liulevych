package Homework_3.entities;

public class User {

    private String name;
    private CashHolder cashHolder;


    public User(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }


    public User setCashHolder(CashHolder cashHolder) {
        this.cashHolder = cashHolder;
        return this;
    }

    public User putMoneyToCashHolder(Currency currency, Double sum){
        this.cashHolder.putCashToCashHolder(currency, sum);
        return this;
    }


}
