package Homework_3.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditCard extends BaseEntity{

    private final String currency;
    private final int cardNumber;
    private CashHolder cashHolder;
    private boolean creditOn;
    private double creditLimit;
    private Map<String, List<Currency>> credits = new HashMap<>();

    public CreditCard(String loggerName, String currency, int cardNumber) {
        super(loggerName);
        this.currency = currency;
        this.cardNumber = cardNumber;
        this.creditOn = false;
        this.creditLimit = 1000.00;
    }

    public String getCurrency() {
        return currency;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCreditOn(){
        if (!creditOn) {
            creditOn = true;
            putCreditCash(new Currency("UAH"), this.creditLimit);
            log.info("Loan funds are credited. Credit balance {}", this.getCreditBalance("UAH"));
        } else {
            log.info("Credits on the card are already activated.");
        }
    }

    private void putCreditCash(Currency currency, Double sum){

        if (this.cashHolder.appendableCurrencies.contains(currency.getName())){
            int count = (int) (sum - (sum % 1.00));
            String name = currency.getName();
            List<Currency> temp = new ArrayList<>();

            for(int i = 0; i < count + 1; i++){
                Currency tempCur = currency.clone();
                if(i < count){
                    tempCur.setNominal(1.00);
                } else {
                    tempCur.setNominal(sum % 1.00);
                }
                temp.add(tempCur);
            }
            this.credits.put(name, temp);
        } else {
            log.info("There is no possibility to create an CashHolder with currency {}.\n" +
                            "Possible currencies {}",
                    currency.getName(),
                    this.cashHolder.appendableCurrencies);
        }
    }

    private List<Currency> getMoneyFromCredit(String currencyName, double sumOfMoney){
        List<Currency> result = this.credits.get(currencyName);

        if(result != null && !result.isEmpty()){
            double currentSumOfCurrencyInCashHolder = 0;

            for(Currency currency: result){
                currentSumOfCurrencyInCashHolder += currency.getNominal();
            }

            if(currentSumOfCurrencyInCashHolder < sumOfMoney){
                log.info("Available sum {} low than {}. Return all cash.",
                        currentSumOfCurrencyInCashHolder,
                        sumOfMoney);
                return result;
            } else {
                List<Currency> returnedCurrency = new ArrayList<>();
                double returnedSum = 0;
                for (Currency currency: result){
                    if(returnedSum < sumOfMoney){
                        returnedCurrency.add(currency);
                        returnedSum += currency.getNominal();
                    } else {
                        break;
                    }
                }
                result.removeAll(returnedCurrency);
                log.info("The requested amount exceeds the available amount\n" +
                        "on a credit card. Loan funds issued. Credit balance - {}. Returned credit sum {}",
                        this.getCreditBalance(currency),
                        returnedSum);
                return returnedCurrency;
            }
        } else {
            return new ArrayList<>();
        }
    }

    public double getCreditBalance(String currencyName){
        double balance = 0.00;
        List<Currency> result = this.credits.get(currencyName);
        if (creditOn) {
            for (Currency rest :
                    result) {
                balance += rest.getNominal();
            }
        }
        return balance;
    }

    public double getCashHolderBalance(String currencyName) {
        return this.cashHolder.getBalance(currencyName);
    }

    public double getCreditCardBalance(String currencyName) {
        double balance = 0.00;
        List<Currency> result = this.credits.get(currencyName);
        if (creditOn) {
            for (Currency rest :
                    result) {
                balance += rest.getNominal();
            }
        }
        balance += this.cashHolder.getBalance(currencyName);
        return balance;
    }


    public void addCardToCashHolder(CashHolder cashHolder){
        this.cashHolder = cashHolder;
        cashHolder.addCard(this);
    }

    public List<Currency> getMoneyFromCreditCard(String currency, double sum){

        if(sum <= this.cashHolder.getBalance(currency)){
            return this.cashHolder.getMoneyFromCashHolderByCard(currency, sum, this.cardNumber);
        } else {
            List<Currency> mixedMoney = new ArrayList<>();
            double balance = this.cashHolder.getBalance(currency);
            double difference = sum - balance;
            mixedMoney.addAll(this.cashHolder.getMoneyFromCashHolderByCard(currency, balance, this.cardNumber));
            mixedMoney.addAll(this.getMoneyFromCredit(currency, difference));
            return mixedMoney;
        }

    }

}
