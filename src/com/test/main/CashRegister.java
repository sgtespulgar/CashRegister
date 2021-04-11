package com.test.main;

import java.util.Scanner;

import com.test.dto.CashOnHand;
import com.test.util.MenuConst;
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
        	CashOnHand money = new CashOnHand();
        	money.initialize();
        	
        	boolean runRegister = true;
        	int[] denominationList = {0, 0, 0, 0, 0};
        	
        	displayMessage();
            while (runRegister) {
            	System.out.print(MenuConst.MENU);
                String line = scanner.nextLine();
                
                if(MenuConst.QUIT.equalsIgnoreCase(line.trim())){
                	runRegister = false;
                	System.out.println(MenuConst.MSG_QUIT);
                } else {
                	String[] input = line.split(" ");
                	
                	if(MenuConst.SHOW.equalsIgnoreCase(input[0])){
                		menu.computeTotalFunds(money);
                	} else if(MenuConst.PUT.equalsIgnoreCase(input[0])){
                		String listString = line.substring(4);                	    
                		
                		money = menu.deposit(listString, money);
                		menu.computeTotalFunds(money);
                	} else if(MenuConst.TAKE.equalsIgnoreCase(input[0])){
                		String listString = line.substring(5);
                		
                		money = menu.withdraw(listString, money);
                		menu.computeTotalFunds(money);
                	} else if(MenuConst.CHANGE.equalsIgnoreCase(input[0])){
                		String listString = line.substring(6);
                		//denominationList = menu.computeChange(Integer.parseInt(listString.trim()), denominationList);
                		money = menu.computeChange(Integer.parseInt(listString.trim()), money);
                	
                	} else {
                		System.out.println(MenuConst.ERR_INVALID_INPUT);
                	}
                }
            }
        } catch(Exception e) {
        	System.out.println(MenuConst.ERR_GENERAL);
        	e.printStackTrace();
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
