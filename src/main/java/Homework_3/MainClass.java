package Homework_3;

import Homework_3.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    private final static Logger LOGGER = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) {
        System.out.println("===================Homework_3=====================");
        CashHolder holder = new CashHolder("Just cashHolder");

        holder.putCashToCashHolder(new Currency("UAH"), 250.0);
//        LOGGER.info(holder);
//        LOGGER.info(holder.getCashInCurrency("UAH"));
//
//        LOGGER.info(holder.getMoneyFromCashHolder(
//                "UAH",
//                20.50).size());
        CreditCard card_1 = new CreditCard("Credit Card 1","UAH", 123456);
//        holder.addCard(card_1);
        card_1.addCardToCashHolder(holder);
        LOGGER.info(card_1.getMoneyFromCreditCard("UAH", 18.00).size());
        LOGGER.info(card_1.getMoneyFromCreditCard("UAH", 20.00).size());

        card_1.setCreditOn();
        LOGGER.info(card_1.getMoneyFromCreditCard("UAH", 400.00).size());
        card_1.getCreditBalance("UAH");
        System.out.println("===================Homework_3=====================\n");
        System.out.println("===================Homework_4=====================");

        List<Item> itemForSale = new ArrayList<>();
        itemForSale.add(new Item("Grechka", 12.00));


        User buyer = new User("Vasilisa");
        Seller seller = new Seller("Petya",itemForSale);



//        buyer.putItemInBag(seller.saleItem("Grechka", buyer.))

    }

}
