package Homework_3.entities;

import java.util.List;

public class Seller extends User{
    private CashHolder cashHolder;
    private List<Item> itemsForSale;
    private final String curForSelling = "UAH";
    public Terminal terminal;

    public Seller(String name, List<Item> items) {
        super(name);
        this.itemsForSale = items;
        this.cashHolder = new CashHolder("Seller cashHolder" + name);
        this.terminal = new Terminal("Seller " + name + " terminal");
        log.debug("Seller {} created ", name);
    }

    public String getCurForSelling() {
        return curForSelling;
    }

    public CashHolder getCashHolder() {
        return cashHolder;
    }

    public List<Item> getItemsForSale() {
        return itemsForSale;
    }

    public Seller setItemsForSale(List<Item> itemsForSale) {
        this.itemsForSale = itemsForSale;
        return this;
    }

    @Override
    public Seller setCashHolder(CashHolder cashHolder) {
        this.cashHolder = cashHolder;
        return this;
    }


    public Item saleItem(String itemName, List<Currency> currencies) {
        double amount = 0.0;
        log.info("Get {}", curForSelling);
        for(Currency currency: currencies) {
           if(currency.getName().equals(curForSelling)) {
               amount+=currency.getNominal();
           }
        }

        return saleItem(itemName, amount);
    }


    // by cash
    public Item saleItem(String name, Double amount) {
        for (Item item: this.itemsForSale){
            if(item.getName().equals(name)){
                log.info("Goods {} in", name);
                if(item.getPrice() == amount){
                    log.info("Sold!");
                    this.cashHolder.putCashToCashHolder(new Currency(curForSelling), amount);
                    return item;
                } else if (item.getPrice() < amount) {
                    log.info("To much money to buy item. {}",
                            item.getPrice() - amount);
                    return null;
                } else {
                    log.info("To low money. {}", amount - item.getPrice());
                    return null;
                }
            }
        }
        log.info("I don't have this item.");
        return null;
    }

    // by credit card
    public Item saleItem(String name, CreditCard clientCard, Double amount) {
        for (Item item: this.itemsForSale){
            if(item.getName().equals(name)){
                log.info("Goods {} in", name);
                if(item.getPrice() == amount){
                    if (this.terminal.getPaymentByCard(this, clientCard, amount)) {
                        log.info("Sold!");
                        return item;
                    }
                } else if (item.getPrice() < amount) {
                    log.info("To much money to buy item. {}",
                            item.getPrice() - amount);
                    return null;
                } else {
                    log.info("To low money. {}", amount - item.getPrice());
                    return null;
                }
            }
        }
        log.info("I don't have this item.");
        return null;
    }

    public void showProfit() {
        log.info("Sum is {}",
                this.cashHolder.getCashInCurrency(curForSelling).size());
    }

}
