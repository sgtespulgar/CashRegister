package com.test.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.test.dto.CashOnHand;

public class MenuUtils {
	
	public void computeTotalFunds(CashOnHand money){
		
		// sort map by key desc
		Map<Integer, Integer> sortedMap = new TreeMap<Integer,Integer>(Collections.reverseOrder());
		sortedMap.putAll(money.getCashMap());
		
		String txtTotal = "$"+money.getTotal();
		
		for (Map.Entry<Integer,Integer> me : sortedMap.entrySet()) {
			txtTotal = txtTotal +" " + me.getValue();
	    }
		
		System.out.println(txtTotal);
	}
	
	public CashOnHand deposit(String input, CashOnHand money) throws Exception{
		
		Map<Integer,Integer> inputMap = readInput(input);
		int key, newVal;
		int total = 0;
		
		for (Map.Entry<Integer,Integer> me : inputMap.entrySet()) {
	          key = me.getKey();
	          newVal = money.getCashMap().get(key) + me.getValue();
	          money.getCashMap().replace(key, newVal);
	          total = total + (newVal * key); 
	    }
		
		money.setTotal(total);
		
		return money;
	}
	
	public CashOnHand withdraw(String input, CashOnHand money){
		
		Map<Integer,Integer> inputMap = readInput(input);
		int key, newVal;
		int total = 0;
		
		for (Map.Entry<Integer,Integer> me : inputMap.entrySet()) {
	          key = me.getKey();
	          if(money.getCashMap().get(key) - me.getValue() < 0){
	        	System.out.println("Invalid amount");
				break;
	          }else{
	        	 newVal = money.getCashMap().get(key) - me.getValue();
	        	 money.getCashMap().replace(key, newVal);
	        	 total = total + (newVal * key);
	          }
	    }
		money.setTotal(total);
		return money;
	}
	
	public CashOnHand computeChange(int amount, CashOnHand money) {
		
		// sort map by key desc
		Map<Integer, Integer> sortedMap = new TreeMap<Integer,Integer>(Collections.reverseOrder());
		sortedMap.putAll(money.getCashMap());
		
		if(money.getTotal() >= amount){
			ArrayList<Integer> changeList = getChangeList(sortedMap, amount);
			Map<Integer,Integer> inputMap = readInput(changeList);
			int key, newVal;
			int total = 0;
			
			for (Map.Entry<Integer,Integer> me : inputMap.entrySet()) {
		          key = me.getKey();
		          if(money.getCashMap().get(key) - me.getValue() < 0){
		        	System.out.println("Invalid amount");
					break;
		          }else{
		        	 newVal = money.getCashMap().get(key) - me.getValue();
		        	 money.getCashMap().replace(key, newVal);
		        	 total = total + (newVal * key);
		          }
		    }
			money.setTotal(total);
			
		} else {  // if more than cashOnHand
			System.out.println("Insufficient funds");
		}
		
		return money;
	}
	
	public ArrayList<Integer> getChangeList(Map<Integer, Integer> map, int amount) {
		Map<Integer, Integer> tempMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		Iterator it = map.entrySet().iterator();
		
		ArrayList<Integer> aList = new ArrayList<Integer>();
		ArrayList<Integer> denominationList = new ArrayList<Integer>();
		ArrayList<Integer> changeList = new ArrayList<Integer>();
		
		while(it.hasNext()){
			Map.Entry<Integer, Integer> pair = (Map.Entry) it.next();
			int key = pair.getKey();
			int val = pair.getValue();
			
			aList.add(key);
			denominationList.add(val);
			changeList.add(0);
		}
		
		int index = 0;
		int prevAmount = amount; //holder
		int prevIndex = index -1;
		
		while(amount > 0){
			if(index > denominationList.size()-1){  //revert
				
				if((denominationList.get(prevIndex)* aList.get(prevIndex)) < amount){
					System.out.print("Sorry!");
					System.out.println();
					
					//return denominationListCopy;
					break;
				} else {
					amount = prevAmount; //revert to previous values
					index = prevIndex+1;
					denominationList.set(prevIndex, denominationList.get(prevIndex)+1);
					changeList.set(prevIndex, changeList.get(prevIndex)-1);
				}
				
			} else if(amount >= aList.get(index) && denominationList.get(index) > 0){
				prevAmount = amount;
				amount = amount - aList.get(index);	
				
				if(amount < 0)  { //revert to previous values, since we can no longer subtract with current denomination
					amount = prevAmount;
					index = prevIndex;
					denominationList.set(prevIndex, denominationList.get(prevIndex)+1);
					changeList.set(prevIndex, changeList.get(prevIndex)-1);
				} else {
					denominationList.set(index, denominationList.get(index)-1);
					changeList.set(index, changeList.get(index)+1);
					prevIndex = index;
				}
			} else {
				index++; //continue
			}
		}
		
		for(int i=0; i<changeList.size(); i++){
			System.out.print(changeList.get(i) + " ");
		}
		
		System.out.println();
		return changeList;
		
	}

	public Map<Integer, Integer> readInput(String input){
		Map<Integer, Integer> inputMap = new TreeMap<>();
		
		String[] inputArray = input.split(" ");
		
		for(int i=0; i<inputArray.length; i++){
			if(i == 0){
				inputMap.put(MenuConst.TWENTY, Integer.parseInt(inputArray[i]));
			} else if(i == 1){
				inputMap.put(MenuConst.TEN, Integer.parseInt(inputArray[i]));
			} else if(i == 2){
				inputMap.put(MenuConst.FIVE, Integer.parseInt(inputArray[i]));
			} else if(i == 3){
				inputMap.put(MenuConst.TWO, Integer.parseInt(inputArray[i]));
			} else if(i == 4){
				inputMap.put(MenuConst.ONE, Integer.parseInt(inputArray[i]));
			} 
		}
		
		return inputMap;
	}
	
	public Map<Integer, Integer> readInput(ArrayList<Integer> inputArray){
		Map<Integer, Integer> inputMap = new TreeMap<>();
		
		
		for(int i=0; i<inputArray.size(); i++){
			if(i == 0){
				inputMap.put(MenuConst.TWENTY, inputArray.get(i));
			} else if(i == 1){
				inputMap.put(MenuConst.TEN, inputArray.get(i));
			} else if(i == 2){
				inputMap.put(MenuConst.FIVE, inputArray.get(i));
			} else if(i == 3){
				inputMap.put(MenuConst.TWO, inputArray.get(i));
			} else if(i == 4){
				inputMap.put(MenuConst.ONE, inputArray.get(i));
			} 
		}
		
		return inputMap;
	}
	
	public Map<Integer, Integer> sortMap(HashMap<Integer, Integer> unsortedMap){
		
		Map<Integer, Integer> sortedMap = new TreeMap<Integer,Integer>(Collections.reverseOrder());
		sortedMap.putAll(unsortedMap);
		
		return unsortedMap;
	}
	
}
