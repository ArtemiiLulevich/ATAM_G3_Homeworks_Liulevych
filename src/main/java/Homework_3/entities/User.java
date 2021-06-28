package Homework_3.entities;

import java.util.List;

public class User extends BaseEntity{

    private String name;
    private CashHolder cashHolder;
    private Bag bag;
    public CreditCard creditCard;


    public User(String name) {
        super(name);
        this.name = name;
        this.cashHolder = new CashHolder( name + " User cashHolder");
        this.bag = new Bag(name + " User bag");
        this.creditCard = new CreditCard("Credit card 1", "UAH", 12345678);
        this.creditCard.addCardToCashHolder(this.cashHolder);
        log.debug("{} {} created",this.getClass().getName(), name);
    }


    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setCashHolder(CashHolder cashHolder) {
        this.cashHolder = cashHolder;
        return this;
    }

    public Bag getBag() {
        return bag;
    }

    public User setBag(Bag bag) {
        this.bag = bag;
        return this;
    }

    public User putMoneyToCashHolder(Currency currency, Double sum){
        log.info("Put money {}: {} to {}",
                currency.getName(), sum, this.cashHolder.getClass().getSimpleName());
        this.cashHolder.putCashToCashHolder(currency, sum);
        return this;
    }

    public User putMoneyToCashHolder(String name, List<Currency> money) {
        this.cashHolder.putCashToCashHolder(name, money);
        return this;
    }

    public User putMoneyToCashHolder(List<Currency> money) {
        this.cashHolder.putCashToCashHolder(money);
        return this;
    }

    public List<Currency> getMoneyFromCashHolder(String currency, double sum) {
        log.info("Out money {}: {}", currency, sum);
        return this.cashHolder.getMoneyFromCashHolder(currency, sum);
    }

    public String getCountMoneyFromCashHolder() {
        return this.cashHolder.toString();
    }

    public User putItemInBag(Item item) {
        this.bag.putItem(item);
        return this;
    }


    public User putItemsInBag(List<Item> items) {
        this.bag.putItems(items);
        return this;
    }


}
