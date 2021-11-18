package com.bakeshop.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    private static Scanner x;
    Scanner input = new Scanner(System.in);

    ArrayList<String> userList = new ArrayList<>();
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<Integer> storeList = new ArrayList<>();
    ArrayList<Integer> orderList = new ArrayList<>();

    public void validateUser() {
        boolean validate = false;
        String tempUserId = "";
        String tempUserName = "";
        String tempEmail = "";
        String tempPassword = "";
        String tempUserType = "";
        String tempTFN = "";
        String tempUserAddress = "";
        String tempUserCity = "";
        String tempUserState = "";
        String tempUserPostal = "";
        String tempUserNum = "";
        String tempStoreId = "";
        Boundary b = new Boundary();
        Controller c = new Controller();

        System.out.print("Enter Your User ID: ");
        String userId = input.next();
        System.out.print("Enter Your Password: ");
        String password = input.next();

        try
        {
            x = new Scanner(new File("Users.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext() && !validate)
            {
                tempUserId = x.next();
                tempUserName = x.next();
                tempEmail = x.next();
                tempPassword = x.next();
                tempUserType = x.next();
                tempTFN = x.next();
                tempUserAddress = x.next();
                tempUserCity = x.next();
                tempUserState = x.next();
                tempUserPostal = x.next();
                tempUserNum = x.next();
                tempStoreId = x.next();
                tempStoreId = tempStoreId.replace("\r","");

                if(tempUserId.trim().equals(userId.trim()) && tempPassword.trim().equals(password.trim()))
                {
                    validate = true;
                }
            }
            x.close();
        }
        catch(Exception e)
        {
            b.errorPage();
            c.login();
        }

        if(validate)
        {
            c.home(tempUserId, tempUserName, tempStoreId, tempUserType);
        }
        else
        {
            b.errorPage();
            c.login();
        }
    }

    public void listAllUsers() {

    }
    public void listAllOrders() {

    }
    public void listAllItems() {

    }
    public void listAllStores() {

    }
}
