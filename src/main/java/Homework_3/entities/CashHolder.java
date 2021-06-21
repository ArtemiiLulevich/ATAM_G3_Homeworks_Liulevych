package Homework_3.entities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class CashHolder {

    private final static Logger LOGGER = LogManager.getLogger("Robot accountant");

    private final List<String> appendableCurrencies = Arrays.asList(
            "UAH",
            "USD",
            "EUR");

    private List<Integer> cardList = new ArrayList<>(5);

    private Map<String, List<Currency>> cash = new HashMap<>();

    private double getBalance(String currencyName){
        double balance = 0.00;
        List<Currency> result = this.cash.get(currencyName);

        for (Currency rest:
                result) {
            balance += rest.getNominal();
        }

        return balance;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{\n\"Sum in holder\": \n");
        this.cash.forEach((k, v) -> builder.append("\"").
                append(k).
                append(": ").
                append(this.getBalance(k)).
                append(",\n"));
        builder.append("}");
        return builder.toString();
    }

    public List<Currency> getCashInCurrency(String currency) {
/*        Map<Currency, Double> result = new HashMap<>();

        for (String name: cash.keySet()){
            if(name.equals(currency)){
                result.putAll(cash.get(name));
            }
        }
//        cash.entrySet()
//                .stream()
//                .filter(nameAndCurrencyMapping -> nameAndCurrencyMapping
//                        .getKey()
//                        .equals(currency))
//                .collect(toMap);*/
        return this.cash.get(currency) != null
                ? this.cash.get(currency) : new ArrayList<>();
    }

    public CashHolder putCashToCashHolder(Currency currency, Double sum){

        if (appendableCurrencies.contains(currency.getName())){
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
            this.cash.put(name, temp);

        } else {
            LOGGER.info("There is no possibility to create an CashHolder with currency {}.\n" +
                            "Possible currencies {}",
                    currency.getName(),
                    appendableCurrencies);
        }
        return this;
    }

    public List<Currency> getMoneyFromCashHolder(String currencyName, double sumOfMoney){
        List<Currency> result = this.cash.get(currencyName);

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
                double balance = 0;
                for (Currency rest:
                     result) {
                    balance += rest.getNominal();
                }
                LOGGER.info("Requested sum returned. Balance: {}", balance);
                return returnedCurrency;
            }
        } else {
            return new ArrayList<>();
        }
    }

    public void addCard(int cardNumber, String currency){
        if (cardList.size() < 5){
            if (appendableCurrencies.contains(currency)){
                cardList.add(cardNumber);
                LOGGER.info("Card {} added to the cashHolder.\n" +
                                "Connected with currency {}.",
                        cardNumber,
                        currency);
            } else {
                LOGGER.info("Card currency is not supported by the cashHolder.");
            }
        } else {
            LOGGER.info("The limit of the number of cards is exceeded.\n" +
                    "Card limit is 5");
        }
    }
// #TODO complete this method
    public List<Currency> getMoneyFromCashHolderByCard(String currencyName, double sumOfMoney, int cardNumber){
        List<Currency> result = this.cash.get(currencyName);

        if(result != null && !result.isEmpty() && cardList.contains(cardNumber)){
            double currentSumOfCurrencyInCashHolder = 0;

            for(Currency currency: result){
                currentSumOfCurrencyInCashHolder += currency.getNominal();
            }

            if(currentSumOfCurrencyInCashHolder < sumOfMoney){
                LOGGER.info("Available sum {} low than {}. No money will be issued.",
                        currentSumOfCurrencyInCashHolder,
                        sumOfMoney);
                return new ArrayList<>();
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

                LOGGER.info("Requested sum returned. Balance: {}", this.getBalance(currencyName));
                return returnedCurrency;
            }
        } else {
            LOGGER.info("Card not in card list. Access denied.");
            return new ArrayList<>();
        }
    }
}
