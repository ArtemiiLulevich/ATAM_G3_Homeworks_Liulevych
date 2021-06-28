package Homework_3.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bag extends BaseEntity{

    public Bag(String loggerName) {
        super(loggerName);
        log.info("{} created", loggerName);
    }

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public Bag setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public  Bag putItem(Item item) {
        this.items.add(item);
        return this;
    }

    public Bag putItems(List<Item> items) {
        this.items.addAll(items);
        return this;
    }

    public Item getItemByName(String name) {
        return this.items.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Item> getItemsByName(String name) {
        return this.items.stream()
                .filter(item -> item.getName().equals(name))
                .collect(Collectors.toList());
    }

    public void showBagEntry() {
        log.info("{} in bag now.", this.items);
    }

}
