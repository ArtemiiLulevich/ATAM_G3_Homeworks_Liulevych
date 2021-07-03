package Homework_3.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bank extends BaseEntity {

    public Bank(String loggerName) {
        super(loggerName);
    }

    private final static String BASE_CURRENCY = "UAH";

    private final Map<String, Double[]> currencyCourse = new HashMap<>(){
        {
            put("USD", new  Double[]{27.5, 28.5});
            put("EUR", new  Double[]{30.5, 31.5});
        }
    };

    public List<Currency> changeFromUah(String requestedCurrency, List<Currency> uahs) {
        List<Currency> result = new ArrayList<>();
        if(currencyCourse.containsKey(requestedCurrency)){
            List<Currency> uahList = uahs.stream()
                    .filter(currency -> currency.getName().equals(BASE_CURRENCY))
                    .collect(Collectors.toList());
            log.info("There are {} of {} in list of cash." +
                    "Converting to {}", uahList.size(), BASE_CURRENCY, requestedCurrency);
            double course = currencyCourse.get(requestedCurrency)[1];
            double sumOfUah = 0;
            for (Currency uah: uahList) {
                sumOfUah +=uah.getNominal();
            }
            Double sumOfReqCur = sumOfUah / course;
            Currency requested = new Currency(requestedCurrency);
            int intSum = sumOfReqCur.intValue();
            double doubleSum = sumOfReqCur % 1.00;
            List<Double> range = new ArrayList<>();
            for (int i = 0; i < intSum; i++){
                range.add(1.00);
            }
            if (doubleSum != 0.0){
                range.add(doubleSum);
            }
            for (Double nominal: range) {
                Currency tempCur = requested.clone();
                tempCur.setNominal(nominal);
                result.add(tempCur);
            }
            log.info("Cash {}: {} available", requested.getName(), sumOfReqCur);
        }
        return result;
    }

    public List<Currency> changeToUah(List<Currency> currencies) {
        List<Currency> result = new ArrayList<>();
        List<Currency> filtered = currencies
                .stream()
                .filter(currency -> currencyCourse.containsKey(currency.getName()))
                .collect(Collectors.toList());
        double sum = 0.00;
        if (!filtered.isEmpty()) {
            currencyCourse.keySet()
                    .forEach(convCurrency -> {
                        List<Currency> temp = filtered.stream()
                                .filter(currency -> currency.getName().equals(convCurrency))
                                .collect(Collectors.toList());
                        log.info("There are {} of {} in list of cash." +
                                "Converting {}", temp.size(), convCurrency, BASE_CURRENCY);
                        double course = currencyCourse.get(convCurrency)[0];
                        double sumOfNoUah = 0;
                        for (Currency currency: temp) {
                            sumOfNoUah +=currency.getNominal();
                        }
                        Double sumOfReqCur = sumOfNoUah * course;
                        Currency requestedCurrency = new Currency(BASE_CURRENCY);
                        int intSum = sumOfReqCur.intValue();
                        double doubleSum = sumOfReqCur % 1.00;
                        List<Double> range = new ArrayList<>();
                        for (int i = 0; i < intSum; i++){
                            range.add(1.00);
                        }
                        if (doubleSum != 0.0){
                            range.add(doubleSum);
                        }
                        for (Double nominal: range) {
                            Currency tempCur = requestedCurrency.clone();
                            tempCur.setNominal(nominal);
                            result.add(tempCur);
                        }
                    });
            for (Currency uah: result) {
                sum += uah.getNominal();
            }
        }
        log.info("Cash {}: {} available", BASE_CURRENCY, sum);
        return result;
    }
}

