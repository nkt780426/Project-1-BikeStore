package model;

public class Stocks {
	private Integer storeId;
	private Integer producId;
	private Integer quantity;

	public Stocks() {
		super();
	}

	public Stocks(Integer storeId, Integer producId, Integer quantity) {
		super();
		this.storeId = storeId;
		this.producId = producId;
		this.quantity = quantity;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProducId() {
		return producId;
	}

	public void setProducId(Integer producId) {
		this.producId = producId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
