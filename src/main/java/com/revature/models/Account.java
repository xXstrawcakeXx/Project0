package com.revature.models;

import java.io.Serializable;

public class Account implements Serializable{
	private int id;
	private double Balance;
	private int accOwner;
	private boolean active;
	
	public Account() {
	}

	public Account(int id, double Balance, int accOwner, boolean active) {
		super();
		this.id = id;
		this.Balance = Balance;
		this.accOwner = accOwner;
		this.active = active;
	}

	public Account(double Balance, int accOwner, boolean active) {
		super();
		this.Balance = Balance;
		this.accOwner = accOwner;
		this.active = active;
	}

//*****************************************************************************************	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double Balance) {
		this.Balance = Balance;
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

//*****************************************************************************************	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accOwner;
		result = prime * result + (active ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(Balance);
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
		if (Double.doubleToLongBits(Balance) != Double.doubleToLongBits(other.Balance))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", Balance=" + Balance + ", accOwner=" + accOwner + ", active=" + active
				+ "]";
	}
	
	
}
