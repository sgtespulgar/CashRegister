package com.test.util;

public class MenuUtils {
	
	public void computeTotalFunds(int[] denominationList){
		int totalFunds = 0;
		String arrayD = "";
		
		for(int i=0; i<denominationList.length; i++){
			if(i==0){
				totalFunds = totalFunds + (denominationList[i] * 20);
			}else if(i==1){
				totalFunds = totalFunds + (denominationList[i] * 10);
			}else if(i==2){
				totalFunds = totalFunds + (denominationList[i] * 5);
			}else if(i==3){
				totalFunds = totalFunds + (denominationList[i] * 2);
			}else if(i==4){
				totalFunds = totalFunds + (denominationList[i] * 1);
			}
			
			arrayD = arrayD + " "+denominationList[i];
		}
		
		System.out.println("$"+totalFunds + arrayD);
	}

	public int[] deposit(String input, int[] denominationList){
		
		String[] inputArray = input.split(" ");
		
		for(int i=0; i<inputArray.length; i++){
			denominationList[i] = denominationList[i] + Integer.parseInt(inputArray[i]);
		}
		
		return denominationList;
	}
	
	public int[] withdraw(String input, int[] denominationList){
		
		String[] inputArray = input.split(" ");
		
		for(int i=0; i<inputArray.length; i++){
			if(denominationList[i] - Integer.parseInt(inputArray[i]) < 0){
				System.out.println("Invalid amount");
				break;
			} else {
				denominationList[i] = denominationList[i] - Integer.parseInt(inputArray[i]);
			}
		}
		
		return denominationList;
	}
	
	public int[] computeChange(int amount, int denominationList[]) {
	    
		int[] array = {20, 10, 5, 2, 1};
		int[] newArray = {0, 0, 0, 0, 0};
		int[] denominationListCopy = copyArray(denominationList);
		
		int index = 0;
		int prevAmount = amount; //holder
		int prevIndex = index -1;
		
		while(amount > 0){
			if(index > denominationList.length-1){  //revert
				
				if((denominationList[prevIndex] * array[prevIndex]) < amount){
					System.out.print("Sorry!");
					System.out.println();
					
					return denominationListCopy;
				} else {
					amount = prevAmount; //revert to previous values
					index = prevIndex+1;
					denominationList[prevIndex]++;
					newArray[prevIndex]--;
				}
				
			} else if(amount >= array[index] && denominationList[index] > 0){
				prevAmount = amount;
				amount = amount - array[index];	
				
				if(amount < 0)  { //revert to previous values, since we can no longer subtract with current denomination
					amount = prevAmount;
					index = prevIndex;
					denominationList[prevIndex]++;
					newArray[prevIndex]--;
				} else {
					newArray[index]++;
					denominationList[index]--;
					prevIndex = index;
				}
			} else {
				index++; //continue
			}
		}
		
		for(int i=0; i<newArray.length; i++){
			System.out.print(newArray[i] + " ");
		}
		
		System.out.println();
		return denominationList;
		
	}
	
	public int[] copyArray(int[] originaArray){
		int[] copied = new int[originaArray.length];
		
		for (int i = 0; i < originaArray.length; i++){
			copied[i] = originaArray[i];
		}
		
		return copied;
	}
	
	
	
	
}
