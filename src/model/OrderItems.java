package model;

import java.math.BigDecimal;

public class OrderItems {
	private Integer orderId; // primary key
	private Integer itemId; // primary key
	private Integer productId; // foreign key
	private Integer quantity;
	private BigDecimal listPrice;

	public OrderItems() {
		super();
	}

	public OrderItems(Integer orderId, Integer itemId, Integer productId, Integer quantity, BigDecimal listPrice) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.productId = productId;
		this.quantity = quantity;
		this.listPrice = listPrice;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

}
