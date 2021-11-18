package com.bakeshop.test;

public class Boundary {
    public void loginPage() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("|                                     |");
        System.out.println("|              Bake Shop              |");
        System.out.println("|                                     |");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("                                       ");
        System.out.println("=============Please log in=============");
        System.out.println("                                       ");
    }

    public void homePage(String userId, String userName, String storeId, String userType) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("|                                     |");
        System.out.println("|              Bake Shop              |");
        System.out.println("|                                     |");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("               Store: " + storeId);
        System.out.println("********Welcome, " + userName + "(" + userType + ")" + "!********");
        System.out.println("Please choose one of the options below:");
        System.out.println("                                       ");
        System.out.println("      Feature 1: Create Order          ");
        System.out.println("      Feature 2: Modify Order(X)       ");
        System.out.println("      Feature 3: Delete Order          ");
        System.out.println("      Feature 4: View all Orders(X)    ");

        if(userType.equals("Manager") || userType.equals("Owner")) {
            System.out.println("      Feature 5: View roast orders(X)  ");
        }

        if(userType.equals("Owner")) {
            System.out.println("      Feature 6: View low items        ");
            System.out.println("      Feature 7: View revenue          ");
            System.out.println("      Feature 8: View sales            ");
        }

        System.out.println("                                       ");
        System.out.println("      Press 0 to log out           ");
        System.out.println("                                       ");
    }

    public void errorPage() {
        System.out.println("");
        System.out.println("             !!! ERROR !!!             ");
        System.out.println("           User is not valid           ");
        System.out.println(" Please try again or contact the owner ");
        System.out.println("        to reset your password         ");
        System.out.println("");
    }

}
