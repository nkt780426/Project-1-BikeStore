package model;

import java.sql.Date;

public class Orders {
	private Integer orderId;
	private Integer customerId;
	private Integer orderStatus;
	private Date orderDate;
	private Date shippedDate;
	private Integer storeId;
	private Integer staffId;

	public Orders() {
	}

	public Orders(Integer orderId, Integer customerId, Integer orderStatus, Date orderDate, Date shippedDate,
			Integer storeId, Integer staffId) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.shippedDate = shippedDate;
		this.storeId = storeId;
		this.staffId = staffId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	@Override
	public String toString() {
		return "[orderId=" + orderId + ", customerId=" + customerId + ", orderStatus=" + orderStatus + ", orderDate="
				+ orderDate + ", shippedDate=" + shippedDate + ", storeId=" + storeId + ", staffId=" + staffId + "]";
	}

}
