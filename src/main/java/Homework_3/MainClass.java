package Homework_3;

import Homework_3.entities.CashHolder;
import Homework_3.entities.CreditCard;
import Homework_3.entities.Currency;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClass {

    private final static Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) {
        CashHolder holder = new CashHolder();

        holder.putCashToCashHolder(new Currency("UAH"), 250.0);
//        LOGGER.info(holder);
//        LOGGER.info(holder.getCashInCurrency("UAH"));
//
//        LOGGER.info(holder.getMoneyFromCashHolder(
//                "UAH",
//                20.50).size());
        CreditCard card_1 = new CreditCard("UAH", 123456);
        card_1.addCardToCashHolder(holder);
        LOGGER.info(card_1.getMoney("UAH", 18.00).size());
        LOGGER.info(card_1.getMoney("UAH", 20.00).size());

        card_1.setCreditOn();
        LOGGER.info(card_1.getMoney("UAH", 400.00).size());
        card_1.getCreditBalance("UAH");
    }

}
