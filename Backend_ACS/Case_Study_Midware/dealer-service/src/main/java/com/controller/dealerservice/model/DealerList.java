package com.controller.dealerservice.model;

import java.util.List;

public class DealerList {
	
	private List<Dealer> dealerList;
	
	public DealerList()
	{
		super();
	}
	public List<Dealer> getDealerList()
	{
		return dealerList;
	}
	
	public void setDealerList(List<Dealer> dealerList)
	{
		this.dealerList = dealerList;
	}
	
}
