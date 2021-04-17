package sina.shariati;

import sina.shariati.exceptions.InvalidExpiryDateException;
import sina.shariati.exceptions.NoSuchProductException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    private static Inventory appInventory;
    private static Basket appBasket;

    public static void main(String[] args) throws InvalidExpiryDateException, ParseException, NoSuchProductException {
//        creating products
        DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        Product carrot = new Product("carrot",
                "vegetables",
                5,
                formatter.parse("15-3-2020"),
                formatter.parse("15-3-2021"),
                10
        );
        Product apple = new Product("apple",
                "Fruits",
                10,
                formatter.parse("1-4-2020"),
                formatter.parse("1-8-2020"),
                10
        );
        Product egg = new Product("egg",
                "egg",
                100,
                formatter.parse("1-1-2020"),
                formatter.parse("1-6-2020"),
                50
        );
        Product oat = new Product("oat",
                "grains",
                100,
                formatter.parse("1-6-2020"),
                formatter.parse("1-1-2021"),
                100
        );
        Product salmon = new Product("salmon",
                "seafood",
                150,
                formatter.parse("1-1-2020"),
                formatter.parse("1-2-2020"),
                250
        );
        Product stake = new Product("stake",
                "meat",
                800,
                formatter.parse("1-3-2020"),
                formatter.parse("1-9-2020"),
                1000
        );
        Product milk = new Product("milk",
                "diary",
                100,
                formatter.parse("10-3-2020"),
                formatter.parse("25-1-2020"),
                20
        );
        Product cheese = new Product("cheese",
                "diary",
                150,
                formatter.parse("1-2-2020"),
                formatter.parse("15-3-2020"),
                20
        );

//        adding products to inventory
        Inventory inventory = new Inventory();
        inventory.addProduct(carrot, 10);
        inventory.addProduct(apple, 50);
        inventory.addProduct(egg, 20);
        inventory.addProduct(oat, 45);
        inventory.addProduct(salmon, 5);
        inventory.addProduct(stake, 5);
        inventory.addProduct(milk, 20);
        inventory.addProduct(cheese, 50);


//        creating the basket
        Basket basket = new Basket();

        appInventory = inventory;
        appBasket = basket;

        startUi();
    }

    private static void startUi() throws NoSuchProductException {
        System.out.println(appInventory);
        String mainMenuCommand;

        do {
            mainMenuCommand = getMainMenuOrder();
            String command = mainMenuCommand.split("\\s+")[0];

            if (command.equals("add")) {
                int index = Integer.parseInt(mainMenuCommand.split("\\s+")[1]) - 1;
                addProductToBasketWithIndex(index);
            } else if (command.equals("remove")) {
                int index = Integer.parseInt(mainMenuCommand.split("\\s+")[1]) - 1;
                removeProductFromBasketWithIndex(index);
            } else if (command.equals("cart")) {
                System.out.println(appBasket);
            } else if (command.equals("products")) {
                System.out.println(appInventory);
            } else {
                System.out.println("Invalid command! please try again!");
            }
        } while (!mainMenuCommand.equals("checkout"));

        System.out.println("It was a pleasure doing business with you");
        System.out.println("your basket price is : " + appBasket.calculateTotalPrice());
    }

    private static void removeProductFromBasketWithIndex(int index) throws NoSuchProductException {
        Product product = (Product) appInventory.getProductSupply().keySet().toArray()[index];
        int supplyCount = appInventory.getProductSupply().get(product);

//        removing item to the basket
        appBasket.removeProduct(product);

//        returning deleted item to inventory
        appInventory.changeProductSupply(product, supplyCount);
    }

    private static void addProductToBasketWithIndex(int index) throws NoSuchProductException {
        Product product = (Product) appInventory.getProductSupply().keySet().toArray()[index];
        int supplyCount = appInventory.getProductSupply().get(product);

        if (supplyCount == 0) {
            System.out.println("Item out of stock!");
            return;
        }

//        adding item to basket
        appBasket.addProduct(product);

//        removing one from the stocks for that item
        appInventory.changeProductSupply(product, supplyCount - 1);
    }

    private static String getMainMenuOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please input your order: ");
        return scanner.nextLine();
    }

}
