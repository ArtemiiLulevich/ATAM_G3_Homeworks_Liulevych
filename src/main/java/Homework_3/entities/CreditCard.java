package Homework_3.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditCard {

    private final String currency;
    private final int cardNumber;
    private CashHolder cashHolder;
    private boolean creditOn;
    private double creditLimit;
    private Map<String, List<Currency>> credits = new HashMap<>();


    private final static Logger LOGGER = LogManager.getLogger("Credit card");

    public CreditCard(String currency, int cardNumber) {
        this.currency = currency;
        this.cardNumber = cardNumber;
        this.creditOn = false;
        this.creditLimit = 1000.00;
    }

    public void setCreditOn(){
        if (!creditOn) {
            creditOn = true;
            putCreditCash(new Currency("UAH"), this.creditLimit);
            LOGGER.info("Loan funds are credited. Credit balance {}", this.getCreditBalance("UAH"));
        } else {
            LOGGER.info("Credits on the card are already activated.");
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
            LOGGER.info("There is no possibility to create an CashHolder with currency {}.\n" +
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
                LOGGER.info("Available sum {} low than {}. Return all cash.",
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
                LOGGER.info("The requested amount exceeds the available amount\n" +
                        "on a credit card. Loan funds issued. Credit balance - {}", this.getCreditBalance(currency));
                return returnedCurrency;
            }
        } else {
            return new ArrayList<>();
        }
    }


    public double getCreditBalance(String currencyName){
        double balance = 0.00;
        List<Currency> result = this.credits.get(currencyName);

        for (Currency rest:
                result) {
            balance += rest.getNominal();
        }

        return balance;
    }

    public void addCardToCashHolder(CashHolder cashHolder){
        this.cashHolder = cashHolder;
        cashHolder.addCard(cardNumber, this.currency);
    }

    public List<Currency> getMoney(String currency, double sum){

        if(sum <= this.cashHolder.getBalance(currency)){
            return this.cashHolder.getMoneyFromCashHolderByCard(currency, sum, this.cardNumber);
        } else {

            return this.getMoneyFromCredit(currency, sum);
        }

    }

}