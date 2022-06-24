package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable{
	private int id;
	private double currBalance;
	private int accOwner;
	private boolean active;
	
	public Account() {
	}

	public Account(int id, double currBalance, int accOwner, boolean active) {
		super();
		this.id = id;
		this.currBalance = currBalance;
		this.accOwner = accOwner;
		this.active = active;
	}

	public Account(double currBalance, int accOwner, boolean active) {
		super();
		this.currBalance = currBalance;
		this.accOwner = accOwner;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCurrBalance() {
		return currBalance;
	}

	public void setCurrBalance(double currBalance) {
		this.currBalance = currBalance;
	}

	public int getAccOwner() {
		return accOwner;
	}

	public void setAccOwner(int accOwner) {
		this.accOwner = accOwner;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accOwner;
		result = prime * result + (active ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(currBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accOwner != other.accOwner)
			return false;
		if (active != other.active)
			return false;
		if (Double.doubleToLongBits(currBalance) != Double.doubleToLongBits(other.currBalance))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", currBalance=" + currBalance + ", accOwner=" + accOwner + ", active=" + active
				+ "]";
	}
	
	
	
	
	
	
}
