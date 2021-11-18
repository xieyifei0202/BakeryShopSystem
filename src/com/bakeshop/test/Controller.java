package com.bakeshop.test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Controller {
    private static Scanner x;
    Scanner input = new Scanner(System.in);

    public static void main (String[] args)
    {
        Controller s = new Controller();
        Boundary b = new Boundary();
        b.loginPage();
        s.login();
    }

    public void login()
    {
        Shop shop = new Shop();
        shop.validateUser();
    }

    public void home(String userId, String userName, String storeId, String userType)
    {
        Boundary b = new Boundary();
        b.homePage(userId, userName, storeId, userType);

        //ask for user option
        System.out.print("Your option: ");
        String option = input.next();

        //if option is 1, then create a new order
        if (option.trim().equals("1")) {
            if(userType.equals("Owner"))
            {
                System.out.println("Press a store Id to enter: ");
                String store = input.next();
                if(store.trim().equals("x")) {
                    this.home(userId, userName, storeId, userType);
                }
                if(store.trim().equals("0")) {
                    b.loginPage();
                    login();
                }
                storeId = store;
            }
            createOrder(userId, userName, storeId, userType);
        }

        //if option is 3, then delete a certain order
        if (option.trim().equals("3")) {
            deleteOrder(userId, userName, storeId, userType);
        }

        //if option is 5678, remember to check userType
        //if option is 6, then view low items
        if (option.trim().equals("6"))
        {
            if(userType.equals("Owner"))
            {
                viewLowItems(userId, userName, storeId, userType);
            }
            else
            {
                System.out.println("You have no access to this option!");
                this.home(userId, userName, storeId, userType);
            }
        }

        //if option is 7, then view revenue
        if (option.trim().equals("7"))
        {
            if(userType.equals("Owner"))
            {
                viewRevenue(userId, userName, storeId, userType);
            }
            else
            {
                System.out.println("You have no access to this option!");
                this.home(userId, userName, storeId, userType);
            }
        }

        //if option is 8, then view sales
        if (option.trim().equals("8")) {
            if(userType.equals("Owner"))
            {
                viewSales(userId, userName, storeId, userType);
            }
            else
            {
                System.out.println("You have no access to this option!");
                this.home(userId, userName, storeId, userType);
            }
        }

        //if option is 0, then log out
        if (option.trim().equals("0")) {
            b.loginPage();
            login();
        }
        else
        {
            System.out.println("");
            System.out.println("ERROR!!!");
            System.out.println("Your option is not valid, please try again");
            this.home(userId, userName, storeId, userType);
        }
    }

    public void createOrder(String userId, String userName, String storeId, String userType)
    {
        Order order = new Order();
        int uid = Integer.parseInt(userId);
        int sid = Integer.parseInt(storeId);
        boolean found = false;
        boolean complete = false;
        String tempStoreId = "";
        String tempItemId = "";
        String tempItemName = "";
        String tempItemPrice = "";
        String tempSort = "";
        String tempRemainNum = "";
        String tempItemSales = "";
        int amount = 0;
        ArrayList<String> itemList = new ArrayList<>();
        ArrayList<Integer> quantityList = new ArrayList<>();
        ArrayList<Integer> priceList = new ArrayList<>();

        while(!complete) {
            System.out.println("Please enter an item name to choose: ");
            String enteredItem = input.next();

            //press c to complete the order
            if(enteredItem.trim().equals("x")) {
                this.home(userId, userName, storeId, userType);
            }
            if(enteredItem.trim().equals("0")) {
                Boundary b = new Boundary();
                b.loginPage();
                login();
            }
            if(enteredItem.equals("c"))
            {
                complete = true;
                break;
            }

            try {
                x = new Scanner(new File("Items.txt"));
                x.useDelimiter("[,\n]");

                while (x.hasNext() && !found) {
                    tempStoreId = x.next();
                    tempItemId = x.next();
                    tempItemName = x.next();
                    tempItemPrice = x.next();
                    tempSort = x.next();
                    tempRemainNum = x.next();
                    tempItemSales = x.next();
                    tempItemSales = tempItemSales.replace("\r","");

                    if (tempItemName.trim().equals(enteredItem) && tempStoreId.trim().equals(storeId)) {
                        found = true;
                    }
                }


                if (found) {
                    x.close();
                    int remain = Integer.parseInt(tempRemainNum);
                    if (remain > 0) {
                        System.out.println(tempItemName + " remains: " + tempRemainNum);
                        boolean choose = false;

                        while(!choose) {
                            System.out.print("Please enter the quantity: ");
                            String enteredQuantity = input.next();
                            //check quantity enough
                            if(enteredQuantity.trim().equals("x")) {
                                this.home(userId, userName, storeId, userType);
                            }
                            if(enteredQuantity.trim().equals("0")) {
                                Boundary b = new Boundary();
                                b.loginPage();
                                login();
                            }
                            if (remain >= Integer.parseInt(enteredQuantity)) {
                                choose = true;

                                //add Item to ArrayList
                                itemList.add(enteredItem);
                                order.setItems(itemList);
                                //add Quantity to ArrayList
                                quantityList.add(Integer.parseInt(enteredQuantity));
                                order.setQuantities(quantityList);
                                //add Price to ArrayList
                                priceList.add(Integer.parseInt(tempItemPrice));
                                order.setPrices(priceList);

                                //print the list
                                System.out.println("List is: ");
                                //here should list all the ordered products + quantities
                                System.out.println(order.getItems());
                                System.out.println(order.getQuantities());
                                //print total amount
                                amount += (Integer.parseInt(tempItemPrice)) * (Integer.parseInt(enteredQuantity));
                                System.out.println("Total Amount: " + amount);

                                found = false;

                            } else {
                                System.out.println("Item not enough, reduce or change");
                                continue;
                            }
                        }

                    } else {
                        System.out.println("Sold out, please choose another one");
                        continue;
                    }
                } else {
                    System.out.println("Item not exist, please choose another one");
                    continue;
                }

            } catch (Exception e) {
                System.out.println("Item Error");
            }
        }

        //print the rest order details
        System.out.print("Customer name: ");
        String customerName = input.next();
        System.out.print("Customer phone number: ");
        String customerNum = input.next();
        System.out.println("Status: READY");
        System.out.println("Staff name: " + userName);
        System.out.println("Staff ID: " + userId);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(date);
        System.out.println("Date: " + d);
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        String t = formatTime.format(date);
        System.out.println("Time: " + t);

        //finish creating order
        boolean finish = false;
        while(!finish) {
            System.out.println("Press p to confirm payment: ");
            String pay = input.next();

            if(pay.trim().equals("x")) {
                this.home(userId, userName, storeId, userType);
            }
            if(pay.trim().equals("0")) {
                Boundary b = new Boundary();
                b.loginPage();
                login();
            }
            if (pay.trim().equals("p")) {
                String lastId = "";
                String line = "";
                int maxOrderId = 0;

                //try catch the largest order id and +1
                try
                {
                    x = new Scanner(new File("Order.txt"));
                    x.useDelimiter("[\n]");

                    while(x.hasNext())
                    {
                        line = x.next();
                        String[] l = line.split(",");
                        int ch = Integer.parseInt(l[1]);

                        if(ch >= maxOrderId)
                        {
                            maxOrderId = ch;
                        }
                    }
                    x.close();

                }
                catch(Exception e)
                {
                    System.out.println("Order Error");
                }

                //generate the order ID
                maxOrderId += 1;
                System.out.println("Order ID: " + maxOrderId);
                System.out.println("Successful Payment!");
                System.out.println("The order has been created");

                //write to Order file
                StringBuffer sb = new StringBuffer();
                sb.append(storeId + ",");
                sb.append(maxOrderId + ",");
                sb.append(userId + ",");

                String i = String.valueOf(order.getItems());
                i = i.replace(", ","|");
                i = i.replace("[","");
                i = i.replace("]","");
                sb.append(i + ",");

                String q = String.valueOf(order.getQuantities());
                q = q.replace(", ","|");
                q = q.replace("[","");
                q = q.replace("]","");
                sb.append(q + ",");

                String p = String.valueOf(order.getPrices());
                p = p.replace(", ","|");
                p = p.replace("[","");
                p = p.replace("]","");
                sb.append(p + ",");

                sb.append(amount + ",");
                sb.append(d + ",");
                sb.append(t + ",");
                sb.append("READY,");
                sb.append(customerName + ",");
                sb.append(customerNum + "\r\n");

                try
                {
                    FileWriter fw = new FileWriter("Order.txt", true);
                    fw.write(String.valueOf(sb));
                    fw.close();
                }
                catch(IOException e)
                {
                    System.out.println("Write to Order error");
                    e.printStackTrace();
                }

                //update the item quantity in item.txt
                tempStoreId = "";
                tempItemId = "";
                tempItemName = "";
                tempItemPrice = "";
                tempSort = "";
                tempRemainNum = "";
                tempItemSales = "";
                String[] iList = i.split("\\|");
                String[] qList = q.split("\\|");
                StringBuffer sbuffer = new StringBuffer();

                try
                {
                    x = new Scanner(new File("Items.txt"));
                    x.useDelimiter("[,\n]");

                    while(x.hasNext()) {
                        tempStoreId = x.next();
                        tempItemId = x.next();
                        tempItemName = x.next();
                        tempItemPrice = x.next();
                        tempSort = x.next();
                        tempRemainNum = x.next();
                        tempItemSales = x.next();
                        tempItemSales = tempItemSales.replace("\r", "");

                        for(int l = 0; l < iList.length; l++)
                        {
                            if(iList[l].trim().equals(tempItemName) && tempStoreId.trim().equals(storeId))
                            {
                                sbuffer.append(tempStoreId + ",");
                                sbuffer.append(tempItemId + ",");
                                sbuffer.append(tempItemName + ",");
                                sbuffer.append(tempItemPrice + ",");
                                sbuffer.append(tempSort + ",");

                                int rn = Integer.valueOf(tempRemainNum);
                                rn -= Integer.valueOf(qList[l]);
                                sbuffer.append(rn + ",");//subtract sales

                                int is = Integer.valueOf(tempItemSales);
                                is += Integer.valueOf(qList[l]);
                                sbuffer.append(is + "\r\n");//add sales
                            }
                            else
                            {
                                sbuffer.append(tempStoreId + ",");
                                sbuffer.append(tempItemId + ",");
                                sbuffer.append(tempItemName + ",");
                                sbuffer.append(tempItemPrice + ",");
                                sbuffer.append(tempSort + ",");
                                sbuffer.append(tempRemainNum + ",");
                                sbuffer.append(tempItemSales + "\r\n");
                            }
                        }
                    }
                    x.close();
                }
                catch(Exception e)
                {
                    System.out.println("Read Item error");
                }

                //write to Item file...
                try
                {
                    FileWriter fw = new FileWriter("Items.txt");
                    fw.write(String.valueOf(sbuffer));
                    fw.close();
                }
                catch(IOException e)
                {
                    System.out.println("Write to Item error");
                    e.printStackTrace();
                }

                finish = true;
                waitUser(userId, userName, storeId, userType);
            }
        }
    }

    public void deleteOrder(String userId, String userName, String storeId, String userType)
    {
        System.out.println("Please enter an order ID to choose: ");
        String enteredOrderId = input.next();
        if(enteredOrderId.trim().equals("x")) {
            this.home(userId, userName, storeId, userType);
        }
        if(enteredOrderId.trim().equals("0")) {
            Boundary b = new Boundary();
            b.loginPage();
            login();
        }

        String tempStoreId = "";
        String tempOrderId = "";
        String tempUserId = "";
        String tempItems = "";
        String tempQuantities = "";
        String tempPrices = "";
        String tempTotalAmount = "";
        String tempDate = "";
        String tempTime = "";
        String tempCustomerName = "";
        String tempStatus = "";
        String tempCustomerNum = "";
        StringBuffer sb = new StringBuffer();
        String deletedOrder = "";

        try
        {
            x = new Scanner(new File("Order.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext())
            {
                tempStoreId = x.next();
                tempOrderId = x.next();
                tempUserId = x.next();
                tempItems = x.next();
                tempQuantities = x.next();
                tempPrices = x.next();
                tempTotalAmount = x.next();
                tempDate = x.next();
                tempTime = x.next();
                tempCustomerName = x.next();
                tempStatus = x.next();
                tempCustomerNum = x.next();
                tempCustomerNum = tempCustomerNum.replace("\r","");

                if(!tempOrderId.trim().equals(enteredOrderId))
                {
                    sb.append(tempStoreId + ",");
                    sb.append(tempOrderId + ",");
                    sb.append(tempUserId + ",");
                    sb.append(tempItems + ",");
                    sb.append(tempQuantities + ",");
                    sb.append(tempPrices + ",");
                    sb.append(tempTotalAmount + ",");
                    sb.append(tempDate + ",");
                    sb.append(tempTime + ",");
                    sb.append(tempCustomerName + ",");
                    sb.append(tempStatus + ",");
                    sb.append(tempCustomerNum + "\r\n");
                }
                else
                {
                    deletedOrder = enteredOrderId;
                    System.out.println("The order details are: ");
                    System.out.println("StoreID: " + tempStoreId);
                    System.out.println("OrderID: " + tempOrderId);
                    System.out.println("UserID: " + tempUserId);
                    System.out.println("Items: " + tempItems);
                    System.out.println("Quantities: " + tempQuantities);
                    System.out.println("Prices: " + tempPrices);
                    System.out.println("Total amount: " + tempTotalAmount);
                    System.out.println("Date: " + tempDate);
                    System.out.println("Time: " + tempTime);
                    System.out.println("Customer name: " + tempCustomerName);
                    System.out.println("Status: " + tempStatus);
                    System.out.println("Customer phone number: " + tempCustomerNum);
                }
            }
            x.close();
        }
        catch (Exception e)
        {
            System.out.println("Delete Error");
        }

        boolean delete = false;
        while(!delete) {
            //ask user to confirm
            System.out.println("Please press c to confirm deleting this order");
            String confirm = input.next();

            if(confirm.trim().equals("x")) {
                this.home(userId, userName, storeId, userType);
            }
            if(confirm.trim().equals("0")) {
                Boundary b = new Boundary();
                b.loginPage();
                login();
            }
            if (confirm.trim().equals("c")) {
                //write new data to order
                try {
                    FileWriter fw = new FileWriter("Order.txt");
                    fw.write(String.valueOf(sb));
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Write to error");
                    e.printStackTrace();
                }
                System.out.println("Order " + deletedOrder + " has been deleted successfully!");

                System.out.println("Please press x to back to home screen");
                System.out.println("or press 0 to log out");
                waitUser(userId, userName, storeId, userType);
            }
        }
    }

    public void viewSales(String userId, String userName, String storeId, String userType)
    {

        ArrayList<Integer> itemStoreList = new ArrayList<>();
        ArrayList<Integer> coffeeSalesList = new ArrayList<>();
        ArrayList<Integer> coffeeBeanSalesList = new ArrayList<>();
        ArrayList<Integer> foodSalesList = new ArrayList<>();
        ArrayList<String> coffeeMostList = new ArrayList<>();
        ArrayList<Integer> mostCoffeeList = new ArrayList<>();


        String tempItemStoreId = "";
        String tempItemId = "";
        String tempItemName = "";
        String tempItemPrice = "";
        String tempSort = "";
        String tempRemainNum = "";
        String tempItemSales = "";

        try
        {
            x = new Scanner(new File("Items.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext())
            {
                tempItemStoreId = x.next();
                tempItemId = x.next();
                tempItemName = x.next();
                tempItemPrice = x.next();
                tempSort = x.next();
                tempRemainNum = x.next();
                tempItemSales = x.next();
                tempItemSales = tempItemSales.replace("\r","");

                if(!itemStoreList.contains(Integer.valueOf(tempItemStoreId))) {
                    itemStoreList.add(Integer.valueOf(tempItemStoreId));

                    if (tempSort.equals("coffee")) {
                        coffeeSalesList.add(Integer.parseInt(tempItemSales));
                        mostCoffeeList.add(Integer.parseInt(tempItemSales));
                        coffeeMostList.add(tempItemName);
                    }
                    if (tempSort.equals("coffee beans")) {
                        coffeeBeanSalesList.add(Integer.parseInt(tempItemSales));
                    }
                    if (tempSort.equals("food")) {
                        foodSalesList.add(Integer.parseInt(tempItemSales));
                    }
                }
                else
                {
                    int index = itemStoreList.indexOf(Integer.valueOf(tempItemStoreId));

                    if (tempSort.equals("coffee")) {
                        if(coffeeSalesList.size() > index) {
                            coffeeSalesList.set(index, coffeeSalesList.get(index) + Integer.parseInt(tempItemSales));
                            if (Integer.parseInt(tempItemSales) >= mostCoffeeList.get(index)) {
                                mostCoffeeList.set(index, Integer.parseInt(tempItemSales));
                                coffeeMostList.set(index, tempItemName);
                            }
                        }
                        else
                        {
                            coffeeSalesList.add(Integer.parseInt(tempItemSales));
                            mostCoffeeList.add(Integer.parseInt(tempItemSales));
                            coffeeMostList.add(tempItemName);
                        }
                    }
                    if (tempSort.equals("coffee beans")) {
                        if(coffeeBeanSalesList.size() > index) {
                            coffeeBeanSalesList.set(index, coffeeBeanSalesList.get(index) + Integer.parseInt(tempItemSales));
                        }
                        else
                        {
                            coffeeBeanSalesList.add(Integer.parseInt(tempItemSales));
                        }
                    }
                    if (tempSort.equals("food")) {
                        if(foodSalesList.size() > index) {
                            foodSalesList.set(index, foodSalesList.get(index) + Integer.parseInt(tempItemSales));
                        }
                        else
                        {
                            foodSalesList.add(Integer.parseInt(tempItemSales));
                        }
                    }
                }

            }
            x.close();
        }
        catch(Exception e)
        {
            System.out.println("");
        }

        System.out.println("Store: " + itemStoreList);
        System.out.println("Coffee sales: " + coffeeSalesList);
        System.out.println("Coffee bean sales: " + coffeeBeanSalesList);
        System.out.println("Food sales: " + foodSalesList);
        System.out.println("Most sold coffee: " + coffeeMostList);

        //find the day of week that sales most
        String tempStoreId = "";
        String tempOrderId = "";
        String tempUserId = "";
        String tempItems = "";
        String tempQuantities = "";
        String tempPrices = "";
        String tempTotalAmount = "";
        String tempDate = "";
        String tempTime = "";
        String tempCustomerName = "";
        String tempStatus = "";
        String tempCustomerNum = "";
        ArrayList<Integer> storeList = new ArrayList<>();
        ArrayList<Integer> saleList = new ArrayList<>();
        ArrayList<String> dayList = new ArrayList<>();
        String week = "";

        try
        {
            x = new Scanner(new File("Order.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext())
            {
                tempStoreId = x.next();
                tempOrderId = x.next();
                tempUserId = x.next();
                tempItems = x.next();
                tempQuantities = x.next();
                tempPrices = x.next();
                tempTotalAmount = x.next();
                tempDate = x.next();
                tempTime = x.next();
                tempCustomerName = x.next();
                tempStatus = x.next();
                tempCustomerNum = x.next();
                tempCustomerNum = tempCustomerNum.replace("\r","");

                //transform "2020-10-23" into "Friday"
                String[] str = tempDate.split("-");
                int year = Integer.parseInt(str[0]);
                int month = Integer.parseInt(str[1]);
                int day = Integer.parseInt(str[2]);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                Date date = new Date(year-1900,month-1,day);
                week = sdf.format(date);

                if(!storeList.contains(Integer.valueOf(tempStoreId)))
                {
                    storeList.add(Integer.valueOf(tempStoreId));
                    saleList.add(Integer.valueOf(tempTotalAmount));
                    dayList.add(week);
                }
                else
                {
                    int a = storeList.indexOf(Integer.valueOf(tempStoreId));
                    if(Integer.valueOf(tempTotalAmount) >= saleList.get(a))
                    {
                        saleList.set(a, Integer.valueOf(tempTotalAmount));
                        dayList.set(a, week);
                    }
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("");
        }

        System.out.println("Day sell most in each store: ");
        System.out.println("Store: " + storeList);
        System.out.println("Sales: " + saleList);
        System.out.println("Day: " + dayList);
        System.out.println("");
        System.out.println("Please press x to back to home screen");
        System.out.println("or press 0 to log out");
        waitUser(userId, userName, storeId, userType);
    }

    public void viewLowItems(String userId, String userName, String storeId, String userType)
    {
        String tempStoreId = "";
        String tempMaterialId = "";
        String tempMaterialName = "";
        String tempMaterialNum = "";
        String tempAddNum = "";
        String tempAddDate = "";
        String lowItem = "";

        System.out.println("Low items (less than 20%): ");

        try
        {
            x = new Scanner(new File("Inventory.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext())
            {
                tempStoreId = x.next();
                tempMaterialId = x.next();
                tempMaterialName = x.next();
                tempMaterialNum = x.next();
                tempAddNum = x.next();
                tempAddDate = x.next();

                int a = Integer.parseInt(tempMaterialNum);
                int b = Integer.parseInt(tempAddNum);
                if(b/a >= 5)
                {
                    System.out.println("Store: " + tempStoreId + ", " + tempMaterialName + ": " + tempMaterialNum + "kg");
                }
            }
            x.close();
        }
        catch(Exception e)
        {
            System.out.println("");
        }

        System.out.println("");
        System.out.println("Please press x to back to home screen");
        System.out.println("or press 0 to log out");
        waitUser(userId, userName, storeId, userType);
    }

    public void viewRevenue(String userId, String userName, String storeId, String userType)
    {
        String tempStoreId = "";
        String tempItemId = "";
        String tempItemName = "";
        String tempItemPrice = "";
        String tempSort = "";
        String tempRemainNum = "";
        String tempItemSales = "";
        ArrayList<Integer> storeList = new ArrayList<>();
        ArrayList<Integer> revenueList = new ArrayList<>();
        int totalSale = 0;

        try
        {
            x = new Scanner(new File("Items.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext())
            {
                tempStoreId = x.next();
                tempItemId = x.next();
                tempItemName = x.next();
                tempItemPrice = x.next();
                tempSort = x.next();
                tempRemainNum = x.next();
                tempItemSales = x.next();
                tempItemSales = tempItemSales.replace("\r","");

                int a = Integer.parseInt(tempItemSales);
                a = a * Integer.parseInt(tempItemPrice);
                totalSale += a;

                if(!storeList.contains(Integer.valueOf(tempStoreId)))
                {
                    storeList.add(Integer.valueOf(tempStoreId));
                    revenueList.add(a);
                }
                else
                {
                    int index = storeList.indexOf(Integer.valueOf(tempStoreId));
                    revenueList.set(index, revenueList.get(index) + a);
                }
            }
            x.close();

        }
        catch(Exception e)
        {
            System.out.println("");
        }

        //mark the current date like "October, 2020"
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMMMM, y");
        String d = format.format(date);

        System.out.println("Revenue last month in $: ");
        System.out.println("Store: " + storeList);
        System.out.println("Revenue: " + revenueList);
        System.out.println("Total Revenue: $" + totalSale);
        System.out.println("Date: " + d);
        System.out.println("");

        System.out.println("Please press x to back to home screen");
        System.out.println("or press 0 to log out");
        waitUser(userId, userName, storeId, userType);
    }

    public void waitUser(String userId, String userName, String storeId, String userType)
    {
        boolean jump = false;
        while(!jump) {
            String option = input.next();

            if(option.trim().equals("x")) {
                this.home(userId, userName, storeId, userType);
            }
            if(option.trim().equals("0")) {
                Boundary b = new Boundary();
                b.loginPage();
                login();
            }
        }
    }

}
