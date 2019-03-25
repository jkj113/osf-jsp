package com.osf.test.vo;

public class Food {
	private Integer foodNum;
	private String foodName;
	private Integer foodPrice;
	
	//eltag에서 FoodNum 이 이름으로 가져와야한다.
	public Integer getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(Integer foodNum) {
		this.foodNum = foodNum;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Integer getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(Integer foodPrice) {
		this.foodPrice = foodPrice;
	}
	@Override
	public String toString() {
		return "Food [foodNum=" + foodNum + ", foodName=" + foodName + ", foodPrice=" + foodPrice + "]";
	}
}
