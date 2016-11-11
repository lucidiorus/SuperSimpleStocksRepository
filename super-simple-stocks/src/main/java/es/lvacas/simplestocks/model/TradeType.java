package es.lvacas.simplestocks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TradeType entity to store the type of trade, SELL or BUY
 * @author lvacas
 */
@Entity
public class TradeType {
	
	@Id
	@Column
	private int tradeTypeId;
	
	@Column
	private String tradeTypeName;

	
	/* Constructors */
	public TradeType() {
		
	}
			
	public TradeType(int tradeTypeId, String tradeTypeName) {
		this.tradeTypeId = tradeTypeId;
		this.tradeTypeName = tradeTypeName;
	}

	
	/* Getters and Setters */
	public int getTradeTypeId() {
		return tradeTypeId;
	}

	public void setTradeTypeId(int tradeTypeId) {
		this.tradeTypeId = tradeTypeId;
	}

	public String getTradeTypeName() {
		return tradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName) {
		this.tradeTypeName = tradeTypeName;
	}

	@Override
	public String toString() {
		return "TradeType [tradeTypeId=" + tradeTypeId + ", tradeTypeName=" + tradeTypeName + "]";
	}
}
