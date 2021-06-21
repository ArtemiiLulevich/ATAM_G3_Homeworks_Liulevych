package Homework_3.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashHolder {

    private final static Logger LOGGER = LogManager.getLogger("Robot");


    private Map<String, List<Currency>> cash = new HashMap<>();


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


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("{\n\"Sum in holder\": \n");
        this.cash.forEach((k, v) -> builder.append("\"").
                append(k).
                append(": ").
                append(v.size()).// #TODO Fix that bug. Create new method???
                append(",\n"));
        builder.append("}");
        return builder.toString();
    }


}
