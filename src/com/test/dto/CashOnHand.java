package com.test.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.test.util.MenuConst;

public class CashOnHand {
	
	private int total;
	private Map<Integer, Integer> cashMap;
	
	public int getTotal() {
		return total;
	}
	
	public Map<Integer, Integer> getCashMap() {
		
		return cashMap;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setCashMap(Map<Integer, Integer> cashMap) {
		
		this.cashMap = cashMap;
	}
	
	public void initialize(){
		if(cashMap == null){
			cashMap = new TreeMap<>();
			//initialize
			cashMap.put(MenuConst.TWENTY, 0);
			cashMap.put(MenuConst.TEN, 0);
			cashMap.put(MenuConst.FIVE, 0);
			cashMap.put(MenuConst.TWO, 0);
			cashMap.put(MenuConst.ONE, 0);
		}
	}
}
