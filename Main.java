package com.viljoen.mark.sales_message_processor_version2;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        int messageCounter = 1;
        int salesCounter = 1;
        int numberOfItemsInList = 1;

        String message = "start";
        String[] messageToProcess = new String[4];
        messageToProcess[0] = "Start";

        String[] messageConstructor;
        String[] messageHistory;
        String[] messageHistoryBuffer = new String[messageCounter];
        String[][] salesHistory;
        String[][] salesHistoryBuffer = new String[6][salesCounter];
        String[] itemTypeList;
        String[] itemTypeListBuffer = new String[numberOfItemsInList];

        clearConsoleScreen();
        printWelcomeScreenToConsole();
        programOperationDescriptionToConsole();

        while(true) {

            messageConstructor = new String[7];

            messageHistory = new String[messageCounter];
            salesHistory = new String[6][salesCounter];
            itemTypeList = new String[numberOfItemsInList];

            for(int counter = 0; counter <= messageCounter - 1; counter++) {
                messageHistory[counter] = messageHistoryBuffer[counter];
            }

            for(int column = 0; column <= 5; column++) {
                for(int row = 0; row <= salesCounter - 1; row++) {
                    salesHistory[column][row] = salesHistoryBuffer[column][row];
                }
            }

            for(int counter = 0; counter <= numberOfItemsInList - 1; counter++) {
                itemTypeList[counter] = itemTypeListBuffer[counter];
            }

            messageHistoryBuffer = new String[messageCounter + 1];

            for(int counter = 0; counter <= messageCounter - 1; counter++) {
                messageHistoryBuffer[counter] = messageHistory[counter];
            }

            salesHistoryBuffer = new String[6][salesCounter + 1];

            for(int column = 0; column <= 5; column++) {
                for(int row = 0; row <= salesCounter - 1; row++) {
                    salesHistoryBuffer[column][row] = salesHistory[column][row];
                }
            }

            itemTypeListBuffer = new String[numberOfItemsInList + 1];

            for(int counter = 0; counter <= numberOfItemsInList - 1; counter++) {
                itemTypeListBuffer[counter] = itemTypeList[counter];
            }

            if(messageCounter == 1) {
                commandPromptToConsole();
                Scanner in = new Scanner(System.in);            // Input from console
                message = in.nextLine();
                messageCounter++;
            }
            else if(messageCounter - 1 == 50) {
                clearConsoleScreen();
                printSaleProductSummaryToConsole(salesHistory, itemTypeList);
                printSaleAdjustmentsLog(salesHistory);

                while(true) {
                    System.out.print("Application PAUSED, please type run to continue: ");
                    Scanner in = new Scanner(System.in);            // Input from console
                    message = in.nextLine();
                    if(message.equalsIgnoreCase("run"))
                        break;
                }
                messageCounter++;
            }
            else if((messageCounter - 1)% 10 == 0) {
                clearConsoleScreen();
                printSaleProductSummaryToConsole(salesHistory, itemTypeList);
                programOperationShortDescriptionToConsole();
                commandPromptToConsole();

                if(messageToProcess[0] == "Type1" || messageToProcess[0] == "Type2" || messageToProcess[0] == "Type3" )
                    commandPromptToConsole();
                else {
                    commandPromptToConsole();
                    System.out.print(messageToProcess[0]);
                }

                Scanner in = new Scanner(System.in);            // Input from console
                message = in.nextLine();
                messageCounter++;
            }
            else if((messageCounter - 1 ) >= 1 && messageCounter % 10 != 0) {
                clearConsoleScreen();
                printSalesHistoryToConsole(salesHistory, salesCounter);
                programOperationShortDescriptionToConsole();

                if(messageToProcess[0] == "Type1" || messageToProcess[0] == "Type2" || messageToProcess[0] == "Type3")
                    commandPromptToConsole();
                else if (messageToProcess[0] == "Start") {
                    commandPromptToConsole();
                    System.out.println(messageToProcess[0]);
                }
                else {
                    commandPromptToConsole();
                    System.out.println("\n" + "Error: " +messageToProcess[0] + ": ");
                    System.out.print("Please enter number [1-3] to select which Message Type who would like to enter: ");
                }

                Scanner in = new Scanner(System.in);            // Input from console
                message = in.nextLine();
                messageCounter++;
            }

            switch (message) {
                case "1": {
                    System.out.print("Please enter product type: ");    // apple at 10p
                    Scanner in = new Scanner(System.in);            // Input from console
                    messageConstructor[0] = in.nextLine();
                    messageConstructor[1] = "at";
                    System.out.print("Please enter product price: ");
                    Scanner in1 = new Scanner(System.in);            // Input from console
                    messageConstructor[2] = in1.nextLine();
                    message = messageConstructor[0] + " " + messageConstructor[1] + " " + messageConstructor[2];
                    salesCounter = processType1Message(salesCounter, message, messageToProcess);
                    break;
                }
                case "2":
                    System.out.print("Please number of sales: ");   //20 sales of apple at 10p each
                    Scanner in2 = new Scanner(System.in);            // Input from console
                    messageConstructor[0] = in2.nextLine();
                    messageConstructor[1] = "sales";
                    messageConstructor[2] = "of";
                    System.out.print("Please enter product type: ");
                    Scanner in3 = new Scanner(System.in);            // Input from console
                    messageConstructor[3] = in3.nextLine();
                    messageConstructor[4] = "at";
                    System.out.print("Please enter product price: ");
                    Scanner in4 = new Scanner(System.in);            // Input from console
                    messageConstructor[5] = in4.nextLine();
                    messageConstructor[6] = "each";
                    message = messageConstructor[0] + " " + messageConstructor[1] + " " + messageConstructor[2] + " " +
                            messageConstructor[3] + " " + messageConstructor[4] + " " + messageConstructor[5] + " " + messageConstructor[6];
                    salesCounter = processType2Message(salesCounter, message, messageToProcess);
                    break;
                case "3":
                    System.out.print("Please enter adjustment operation: ");    // Add 20p apples
                    Scanner in = new Scanner(System.in);            // Input from console
                    messageConstructor[0] = in.nextLine();
                    System.out.print("Please enter adjustment price or multiplication value: ");
                    Scanner in5 = new Scanner(System.in);            // Input from console
                    messageConstructor[1] = in5.nextLine();
                    System.out.print("Please enter product type: ");
                    Scanner in6 = new Scanner(System.in);            // Input from console
                    messageConstructor[2] = in6.nextLine();
                    message = messageConstructor[0] + " " + messageConstructor[1] + " " + messageConstructor[2];
                    salesCounter = processType3Message(salesCounter, message, messageToProcess);
                    break;
                default:
                    messageToProcess[0] = "Incorrect choice: Enter a number from 1 to 3";

            }

            if(messageToProcess[0] != null) {
                if(messageToProcess[0] == "Type1"||messageToProcess[0] == "Type2") {
                    salesHistoryBuffer[0][salesCounter - 1] = salesCounter + "";    // ID
                    salesHistoryBuffer[1][salesCounter - 1] = messageToProcess[1];    // Item type
                    salesHistoryBuffer[2][salesCounter - 1] = messageToProcess[2];    // Number of sales
                    salesHistoryBuffer[3][salesCounter - 1] = messageToProcess[3];    // Original price in pence
                    salesHistoryBuffer[4][salesCounter - 1] = 0 + "";               // Current adjustment in pence
                    salesHistoryBuffer[5][salesCounter - 1] = (Integer.parseInt(messageToProcess[2]) * Integer.parseInt(messageToProcess[3])) + "";              // Current price in pence
                    salesCounter++;
                    numberOfItemsInList = itemTypeList(messageToProcess[1],itemTypeListBuffer,numberOfItemsInList);
                }

                else if(messageToProcess[0] == "Type3") {

                    if(messageToProcess[2].equalsIgnoreCase("add")) {

                        salesHistoryBuffer = new String[6][salesCounter + 1];

                        for (int row = 0; row <= salesCounter - 2; row++) {
                            if(salesHistory[1][row].equalsIgnoreCase(messageToProcess[1])) {
                                salesHistoryBuffer[4][row] = (Integer.parseInt(salesHistory[4][row]) + Integer.parseInt(messageToProcess[3]) * Integer.parseInt(salesHistory[2][row])) + "";  // Current adjustment in pence
                                salesHistoryBuffer[5][row] = (Integer.parseInt(salesHistory[3][row]) * Integer.parseInt(salesHistory[2][row]) + Integer.parseInt(salesHistoryBuffer[4][row])) + "";  // Current price in pence
                            }
                            else {
                                salesHistoryBuffer[4][row] = salesHistory[4][row];
                                salesHistoryBuffer[5][row] = salesHistory[5][row];
                            }

                            for(int column = 0; column <= 3; column++) {
                                salesHistoryBuffer[column][row] = salesHistory[column][row];
                            }

                        }

                    }
                    else if(messageToProcess[2].equalsIgnoreCase("subtract")) {
                        salesHistoryBuffer = new String[6][salesCounter + 1];

                        for (int row = 0; row <= salesCounter - 2; row++) {
                            if(salesHistory[1][row].equalsIgnoreCase(messageToProcess[1])) {
                                salesHistoryBuffer[4][row] = (Integer.parseInt(salesHistory[4][row]) - Integer.parseInt(messageToProcess[3]) * Integer.parseInt(salesHistory[2][row])) + "";  // Current adjustment in pence
                                salesHistoryBuffer[5][row] = (Integer.parseInt(salesHistory[3][row]) * Integer.parseInt(salesHistory[2][row]) + Integer.parseInt(salesHistoryBuffer[4][row])) + "";  // Current price in pence
                            }
                            else {
                                salesHistoryBuffer[4][row] = salesHistory[4][row];
                                salesHistoryBuffer[5][row] = salesHistory[5][row];
                            }

                            for(int column = 0; column <= 3; column++) {
                                salesHistoryBuffer[column][row] = salesHistory[column][row];
                            }

                        }

                    }
                    else if(messageToProcess[2].equalsIgnoreCase("multiply")) {
                        salesHistoryBuffer = new String[6][salesCounter + 1];

                        for (int row = 0; row <= salesCounter - 2; row++) {
                            if(salesHistory[1][row].equalsIgnoreCase(messageToProcess[1])) {
                                salesHistoryBuffer[4][row] = (int)(Double.parseDouble(salesHistory[5][row]) * Double.parseDouble(messageToProcess[3]) - Double.parseDouble(salesHistory[3][row])) + "";  // Current adjustment in pence
                                salesHistoryBuffer[5][row] = (int)(Double.parseDouble(salesHistory[3][row]) * Double.parseDouble(salesHistory[2][row]) + Double.parseDouble(salesHistoryBuffer[4][row])) + "";  // Current price in pence
                            }
                            else {
                                salesHistoryBuffer[4][row] = salesHistory[4][row];
                                salesHistoryBuffer[5][row] = salesHistory[5][row];
                            }

                            for(int column = 0; column <= 3; column++) {
                                salesHistoryBuffer[column][row] = salesHistory[column][row];
                            }

                        }

                    }

                }

            }

            System.out.println(messageToProcess[0]);

            if(message.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }

    public static void clearConsoleScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printWelcomeScreenToConsole() {
        System.out.println("-------------------------------------------------------");
        System.out.println("     Welcome to Sales Message Processor Version 2.0    ");
        System.out.println("-------------------------------------------------------\n");
    }

    public static void programOperationDescriptionToConsole() {
        System.out.println("This program is a message processing application which allows an external company to send");
        System.out.println("input messages describing a sale or an adjustment to previous sales to the company.\n");
        System.out.println("There are three types of messages which this application can accept;\n");
        System.out.println("1.) Message Type 1: This is a message which contains details of a single sale");
        System.out.println("    <Product> at <Price in pence>p or <Product> at £<Price in pounds>");
        System.out.println("    Note: All product names should be entered in singular form");
        System.out.println("          e.g. Apple at 10p or Apple at £0.10\n");
        System.out.println("2.) Message Type 2: This is a message which contains details and the number of occurrences of a sale");
        System.out.println("    <Number of sales> sales of <Product> at <Price in pence>p or <Number of sales> sales of <Product> at £<Price in pounds>");
        System.out.println("    Note: The number of sales should be greater than 1 and the product name should be entered in singular form");
        System.out.println("          e.g. 20 sales of apple at 10p each or 20 sales of apple at £0.10 each\n");
        System.out.println("3.) Message Type 3: This is a message which contains the details and an adjustment operation to be made");
        System.out.println("    to all previous sales of a specific product type");
        System.out.println("    <Adjustment operation> <Price in pence>p <Product> or <Adjustment operation> £<Price in pounds> <Product>");
        System.out.println("    Note: There are only three adjustment types available, namely; Add / Subtract / Multiply and the product name should");
        System.out.println("          be entered in singular form");
        System.out.println("          e.g. Add 20p apple or Add £0.20 apple");
        System.out.println("\n\nIf you wish to exit the program, please type the word exit");

    }

    public static void commandPromptToConsole() {
        System.out.print("\nPlease enter number [1-3] to select which Message Type who would like to enter: ");
    }

    public static void programOperationShortDescriptionToConsole() {
        System.out.println("There are three types of messages which this application can accept;\n");
        System.out.println("1.) Message Type 1: This is a message which contains details of a single sale");
        System.out.println("    Note: Price should contain suffix 'p' for pence or prefix '£' for pound;");
        System.out.println("          e.g. Apple at 10p or Apple at £0.10\n");
        System.out.println("2.) Message Type 2: This is a message which contains details and the number of occurrences of a sale");
        System.out.println("    Note: The number of sales should be greater than 1 and the product name should be entered in singular form");
        System.out.println("          e.g. 20 sales of apple at 10p each or 20 sales of apple at £0.10 each\n");
        System.out.println("3.) Message Type 3: This is a message which contains the details and an adjustment operation to be made");
        System.out.println("    to all previous sales of a specific product type");
        System.out.println("    Note: There are only three adjustment types available, namely; Add / Subtract / Multiply");
        System.out.println("          e.g. Add 20p apple or Add £0.20 apple");
        System.out.println("\n\nIf you wish to exit the program, please type the word exit");

    }

    static int processType1Message(int saleCounterInput, String messageInput, String[] messageToProcessInput) {
        int saleCounter = saleCounterInput;
        int itemPriceInPence = 0;
        int numberOfSales;

        double itemPriceInPounds = 0.0d;

        String[] parts = messageInput.split(" ");
        String[] priceCurrencySplit;
        String[] messageToProcess = messageToProcessInput;
        String itemType;
        String currencyVerification = verifyCurrency(parts[2], messageToProcess);

        DecimalFormat df = new DecimalFormat("#.##");

        if(currencyVerification.equalsIgnoreCase("poundPrice")) {
                priceCurrencySplit = parts[2].split("£");
                itemPriceInPounds = Double.parseDouble(priceCurrencySplit[1]);
                itemPriceInPounds = Double.valueOf(df.format(itemPriceInPounds));
                itemPriceInPence = (int)(itemPriceInPounds * 100.0d);
                itemType = parts[0];
                numberOfSales = 1;
                messageToProcess[0] = "Type1";
                messageToProcess[1] = itemType;
                messageToProcess[2] = numberOfSales + "";
                messageToProcess[3] = itemPriceInPence + "";
        }
            else if (currencyVerification.equalsIgnoreCase("pencePrice")) {
                priceCurrencySplit = parts[2].split("p");
                itemPriceInPence = (int)Double.parseDouble(priceCurrencySplit[0]);
                itemType = parts[0];
                numberOfSales = 1;
                messageToProcess[0] = "Type1";
                messageToProcess[1] = itemType;
                messageToProcess[2] = numberOfSales + "";
                messageToProcess[3] = itemPriceInPence + "";
            }

        return saleCounter;
    }

    static int processType2Message(int saleCounterInput, String messageInput, String[] messageToProcessInput) {
        int saleCounter = saleCounterInput;
        int itemPriceInPence = 0;
        int numberOfSales;

        double itemPriceInPounds = 0.0d;

        String[] parts = messageInput.split(" ");
        String[] priceCurrencySplit;
        String[] messageToProcess = messageToProcessInput;
        String itemType;
        String currencyVerification = verifyCurrency(parts[5], messageToProcess);

        DecimalFormat df = new DecimalFormat("#.##");

        if(currencyVerification.equalsIgnoreCase("poundPrice")) {
            if(!parts[0].matches("[a-zA-Z]+")) {
                priceCurrencySplit = parts[5].split("£");
                itemPriceInPounds = Double.parseDouble(priceCurrencySplit[1]);
                itemPriceInPounds = Double.valueOf(df.format(itemPriceInPounds));
                itemPriceInPence = (int) (itemPriceInPounds * 100.0d);
                itemType = parts[3];
                numberOfSales = (int) Double.parseDouble(parts[0]);
                messageToProcess[0] = "Type2";
                messageToProcess[1] = itemType;
                messageToProcess[2] = numberOfSales + "";
                messageToProcess[3] = itemPriceInPence + "";
            }
            else {
                messageToProcess[0] = "Number of sales contains alphabetic characters";
            }
        }
        else if (currencyVerification.equalsIgnoreCase("pencePrice")) {
            if(!parts[0].matches("[a-zA-Z]+")) {
            priceCurrencySplit = parts[5].split("p");
            itemPriceInPence = (int) Double.parseDouble(priceCurrencySplit[0]);
            itemType = parts[3];
            numberOfSales = (int) Double.parseDouble(parts[0]);
            messageToProcess[0] = "Type2";
            messageToProcess[1] = itemType;
            messageToProcess[2] = numberOfSales + "";
            messageToProcess[3] = itemPriceInPence + "";
            }
            else {
                messageToProcess[0] = "Number of sales contains alphabetic characters";
            }
        }

        return saleCounter;
    }

    static int processType3Message(int saleCounterInput, String messageInput, String[] messageToProcessInput) {
        int saleCounter = saleCounterInput;
        int itemPriceInPence = 0;
        int numberOfSales;

        double itemPriceInPounds = 0.0d;
        double multiplyAdjustmentOperation = 0.0d;

        String[] parts = messageInput.split(" ");
        String[] priceCurrencySplit;
        String[] messageToProcess = messageToProcessInput;
        String itemType;
        String currencyVerification = verifyCurrency(parts[1], messageToProcess);

        DecimalFormat df = new DecimalFormat("#.##");

        AdjustmentOperation adjustmentOperation;

        if(currencyVerification.equalsIgnoreCase("poundPrice")) {
            priceCurrencySplit = parts[1].split("£");
            itemPriceInPounds = Double.parseDouble(priceCurrencySplit[1]);
            itemPriceInPounds = Double.valueOf(df.format(itemPriceInPounds));
            itemPriceInPence = (int)(itemPriceInPounds * 100.0d);
            itemType = parts[2];
            numberOfSales = 0;
            messageToProcess[0] = "Type3";
            messageToProcess[1] = itemType;
            messageToProcess[2] = parts[0];
            messageToProcess[3] = itemPriceInPence + "";
        }
        else if (currencyVerification.equalsIgnoreCase("pencePrice")) {
            priceCurrencySplit = parts[1].split("p");
            itemPriceInPence = (int)Double.parseDouble(priceCurrencySplit[0]);
            itemType = parts[2];
            numberOfSales = 0;
            messageToProcess[0] = "Type3";
            messageToProcess[1] = itemType;
            messageToProcess[2] = parts[0];
            messageToProcess[3] = itemPriceInPence + "";
        }
        else if(parts[0].equalsIgnoreCase("multiply")) {
            if(!parts[1].matches("[a-zA-Z]+")) {
                multiplyAdjustmentOperation = Double.parseDouble(parts[1]);
                itemType = parts[2];
                messageToProcess[0] = "Type3";
                messageToProcess[1] = itemType;
                messageToProcess[2] = parts[0];
                messageToProcess[3] = Double.parseDouble(parts[1]) + "";
            }
            else {
                messageToProcess[0] = "Number of sales contains alphabetic characters";
            }
        }

        return saleCounter;
    }

    static String verifyCurrency(String priceStringInput, String[] messageToProcessInput) {
        String priceString = priceStringInput;
        String[] messageToProcess = messageToProcessInput;
        if (Pattern.matches("£.*?", priceString) && Pattern.matches(".*?p", priceString)) {
            messageToProcess[0] = "Price contains both currency symbols, prefix '£' and suffix 'p'";
            return "incorrectPrice";
        }
        else if (Pattern.matches("£.*?", priceString) && !priceString.equalsIgnoreCase("£"))
            return "poundPrice";

        else if (Pattern.matches(".*?p", priceString) && !priceString.equalsIgnoreCase("p"))
            return "pencePrice";

        else if (priceString.equalsIgnoreCase("p")) {
            messageToProcess[0] = "Price just currency symbols, there is no number";
            return "incorrectPrice";
        }

        else if (priceString.equalsIgnoreCase("£")) {
            messageToProcess[0] = "Price just currency symbols, there is no number";
            return "incorrectPrice";
        }

        else {
            messageToProcess[0] = "Price does not contain a currency symbol prefix '£' or suffix 'p'";
            return "incorrectPrice";
        }
    }

    static int processType2Message(int saleCounterInput) {
        int saleCounter = saleCounterInput;
        return saleCounter;
    }

    static int processType3Message(int saleCounterInput) {
        int saleCounter = saleCounterInput;
        return saleCounter;
    }

    static int itemTypeList(String itemTypeInput, String[] itemTypeListInput, int numberOfItemsInList) {
        String itemType = itemTypeInput;
        String[] itemTypeList = itemTypeListInput;
        boolean itemIsPresent = false;
        int numberOfItems = numberOfItemsInList;

        if(numberOfItems == 1)
            itemIsPresent = false;
        else {
            for (int counter = 0; counter <= numberOfItems - 2; counter++) {
                if (itemTypeList[counter].equalsIgnoreCase(itemType)) {
                    itemIsPresent = true;
                    break;
                }
                else
                    itemIsPresent = false;
            }
        }

        if(itemIsPresent)
            ;
        else {
            itemTypeList[numberOfItems - 1] = itemType;
            numberOfItems++;
        }
        return numberOfItems;
    }

    static void printSaleProductSummaryToConsole(String salesHistoryInput[][],String itemTypeListInput[]) {
        String saleHistory[][] = salesHistoryInput;
        String itemTypeList[] = itemTypeListInput;
        int numberOfSales = salesHistoryInput[0].length;
        int numberOfItems = itemTypeList.length;
        int numberOfSalesforEachItemType;
        int totalValueOfSalesForEachItemType;

        System.out.println("");

        System.out.println("-------------------------------------------------------");
        System.out.println("               Total sales and Value Log               ");
        System.out.println("-------------------------------------------------------");
        System.out.println("");

        for (int itemCounter = 0; itemCounter <= numberOfItems - 2; itemCounter++) {
            numberOfSalesforEachItemType = 0;
            totalValueOfSalesForEachItemType = 0;
            for (int saleCounter = 0; saleCounter <= numberOfSales - 2; saleCounter++) {
                if (itemTypeList[itemCounter].equalsIgnoreCase(saleHistory[1][saleCounter])) {
                    numberOfSalesforEachItemType = numberOfSalesforEachItemType + Integer.parseInt(saleHistory[2][saleCounter]);
                    totalValueOfSalesForEachItemType = totalValueOfSalesForEachItemType + Integer.parseInt(saleHistory[5][saleCounter]);
                }
            }
            System.out.println("   " + numberOfSalesforEachItemType + " sales of " + itemTypeList[itemCounter] + " for " + totalValueOfSalesForEachItemType + "p");
        }
        System.out.println("");
        System.out.println("");
    }

    static void printSaleAdjustmentsLog(String salesHistoryInput[][]) {
        String saleHistory[][] = salesHistoryInput;
        int numberOfSales = salesHistoryInput[0].length;
        int numberOfItemsOfEachSale;
        int totalAdjustmentsOfEachSale;

        System.out.println("");

        System.out.println("-------------------------------------------------------");
        System.out.println("        Log of all adjustments made to each sale       ");
        System.out.println("-------------------------------------------------------\n");
        System.out.println("");

        for (int saleCounter = 0; saleCounter <= numberOfSales - 2; saleCounter++) {
            if (Integer.parseInt(saleHistory[2][saleCounter]) == 1) {
                System.out.println("   An adjustment of " + Integer.parseInt(saleHistory[4][saleCounter]) + "p was made to " + Integer.parseInt(saleHistory[2][saleCounter]) + " sale of " + saleHistory[1][saleCounter]);
            }
            else {
                System.out.println("   An adjustment of " + Integer.parseInt(saleHistory[4][saleCounter]) + "p was made to " + Integer.parseInt(saleHistory[2][saleCounter]) + " sales of " + saleHistory[1][saleCounter]);
            }
        }
        System.out.println("");
    }

    static void printSalesHistoryToConsole(String salesHistoryInput[][],int salesCounterInput) {
        String salesHistory[][] = salesHistoryInput;
        int salesCounter = salesCounterInput;

        System.out.println("-------------------------------------------------------");
        System.out.println("                     Sales History                     ");
        System.out.println("-------------------------------------------------------\n");

        for(int row = 0; row <= salesCounter - 2; row++) {
            for(int column = 0; column <= 5; column++) {
                System.out.print(" " + salesHistory[column][row] + "  ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------\n");
    }

}
