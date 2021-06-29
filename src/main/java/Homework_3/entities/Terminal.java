package Homework_3.entities;

public class Terminal extends BaseEntity{
    public Terminal(String loggerName) {
        super(loggerName);
    }

    public boolean getPaymentByCard(Seller seller, CreditCard clientCard, double priceOfItem) {
        double cardBalance = clientCard.getCreditCardBalance(seller.getCurForSelling());

        if(cardBalance >= priceOfItem){
            seller.getCashHolder()
                    .putCashToCashHolder(
                            clientCard
                                    .getMoneyFromCreditCard(
                                            seller.getCurForSelling(),
                                            priceOfItem));
            return true;
        } else {
            log.info("Not enough money on the card.");
            return false;
        }
    }

}
