package com.test.model;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.test.util.MenuUtils;

/**
 * Rocketmiles Software Engineering Coding Take-home
 * Author: Suzerain Gem Espulgar
 * */

public class CashRegister {
	
	
	public static void main(String[] args) {
		MenuUtils menu = new MenuUtils();
		Scanner scanner = new Scanner(System.in);
        try {
        	boolean runRegister = true;
        	int[] denominationList = {0, 0, 0, 0, 0};
        	
        	displayMessage();
            while (runRegister) {
            	System.out.print("> ");
                String line = scanner.nextLine();
                
                if(line.trim().equalsIgnoreCase("exit") || line.trim().toLowerCase() == "exit"){
                	runRegister = false;
                	System.out.println("Bye!");
                } else {
                	String[] input = line.split(" ");
                	
                	if(input[0].equalsIgnoreCase("show")){
                		menu.computeTotalFunds(denominationList);
                	} else if(input[0].equalsIgnoreCase("put")){
                		String listString = line.substring(4);                	    
                		denominationList = menu.deposit(listString, denominationList);
                		menu.computeTotalFunds(denominationList);
                	} else if(input[0].equalsIgnoreCase("take")){
                		String listString = line.substring(5);
                		denominationList = menu.withdraw(listString, denominationList);
                		menu.computeTotalFunds(denominationList);
                	} else if(input[0].equalsIgnoreCase("change")){
                		String listString = line.substring(6);
                		denominationList = menu.computeChange(Integer.parseInt(listString.trim()), denominationList);
                	} else if(input[0].equalsIgnoreCase("quit")){
                		 scanner.close();
                	}
                }
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
        	System.out.println("Bye");
        } finally{
        	scanner.close();
        }
	}
	
	public static void displayMessage(){
		System.out.println("Cash Register");
        System.out.println("Please use the following commands");
        System.out.println("***********************************************");
        System.out.println("show => to show current balance");
        System.out.println("exit => to exit the application");
        System.out.println("put x x x x x => to deposit, x corresponds to the number of each denomination");
        System.out.println("take x x x x x => to withdraw, x corresponds to the number of each denomination");
        System.out.println("denomination order: #$20 #$10 #$5 #$2 #$1");
        System.out.println("***********************************************");
        
	}
	
}
