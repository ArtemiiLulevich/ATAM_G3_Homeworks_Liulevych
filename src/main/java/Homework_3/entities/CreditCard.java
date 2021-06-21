package Homework_3.entities;

import java.util.List;

public class CreditCard {

    private final String currency;
    private final int cardNumber;
    private CashHolder cashHolder;

    public CreditCard(String currency, int cardNumber) {
        this.currency = currency;
        this.cardNumber = cardNumber;
    }

    public void addCardToCashHolder(CashHolder cashHolder){
        this.cashHolder = cashHolder;
        cashHolder.addCard(cardNumber, this.currency);
    }

    public List<Currency> getMoney(String currency, double sum){
        return this.cashHolder.getMoneyFromCashHolderByCard(currency, sum, this.cardNumber);
    }



}
