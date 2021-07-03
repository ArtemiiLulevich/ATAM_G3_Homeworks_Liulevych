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

        holder.putCashToCashHolder(new Currency("UAH"), 2.5);

        CreditCard card_1 = new CreditCard("Credit Card 1","UAH", 123456);

        card_1.addCardToCashHolder(holder);
        LOGGER.info(card_1.getMoneyFromCreditCard("UAH", 2.50));



        LOGGER.info(card_1.getMoneyFromCreditCard("UAH", 400.00).size());
        card_1.setCreditOn();
        LOGGER.info(card_1.getMoneyFromCreditCard("UAH", 400.00).size());
        card_1.getCreditBalance("UAH");
        System.out.println("===================Homework_3=====================\n");


        System.out.println("===================Homework_4=====================");

        List<Item> itemForSale = new ArrayList<>();
        itemForSale.add(new Item("Grechka", 12.00));
        itemForSale.add(new Item("Water", 13.50));

        User buyer = new User("Vasilisa", "UAH", 1234565);
        Seller seller = new Seller("Petya",itemForSale);

        buyer.putMoneyToCashHolder(new Currency("UAH"), 30.00);

//        LOGGER.info(buyer.getMoneyFromCashHolder("UAH", 30).size());

//        buyer.creditCard.setCreditOn();

        buyer.putItemInBag(seller.saleItem("Water",
                buyer.getCreditCard().getMoneyFromCreditCard("UAH", 13.50)));

        LOGGER.info(buyer.getCountMoneyFromCashHolder());

        buyer.getBag().showBagEntry();

        buyer.putItemInBag(seller.saleItem("Grechka", buyer.getCreditCard(), 12.00));

        buyer.getBag().showBagEntry();

    }

}
