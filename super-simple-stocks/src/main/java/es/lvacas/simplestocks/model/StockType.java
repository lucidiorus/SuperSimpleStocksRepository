package es.lvacas.simplestocks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * StockType entity containing the type of stock, common or preferred
 * @author lvacas
 */
@Entity
public class StockType {
	
	
	@Id
	@Column
	private int stockTypeId;
	
	
	/* Can be "COMMON" or "PREFERRED" */
	@Column
	private String stockTypeName;

	
	/* Constructors */
	public StockType() {
		
	}
	
	public StockType(int stockTypeId, String type) {
		this.stockTypeId = stockTypeId;
		this.stockTypeName = type;
	}


	/* Getters and Setters */
	public int getStockTypeId() {
		return stockTypeId;
	}

	public void setStockTypeId(int stockTypeId) {
		this.stockTypeId = stockTypeId;
	}

	public String getStockTypeName() {
		return stockTypeName;
	}

	public void setStockTypeName(String stockTypeName) {
		this.stockTypeName = stockTypeName;
	}
	

	@Override
	public String toString() {
		return "StockType [stockTypeId=" + stockTypeId + ", stockTypeName=" + stockTypeName + "]";
	}	
}
