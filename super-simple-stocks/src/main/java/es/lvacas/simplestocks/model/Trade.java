package es.lvacas.simplestocks.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Trade entity to store the information of a trade of shares object
 * @author lvacas
 */
@Entity
public class Trade {
	
	@Id
	@Column
	private int tradeId;
	
	@Column
	private Stock stockId;
	
	@Column
	private Date timestamp;
	
	@Column
	private int quantityOfShares;
	
	@ManyToOne
	@JoinColumn
	private TradeType tradeType;
	
	@Column
	private double priceInPennies;

	
	/* Constructors */
	public Trade() {
		
	}
		
	public Trade(int tradeId, Stock stockId, Date timestamp, int quantityOfShares, TradeType tradeType, double priceInPennies) {
		this.tradeId = tradeId;
		this.stockId = stockId;
		this.timestamp = timestamp;
		this.quantityOfShares = quantityOfShares;
		this.tradeType = tradeType;
		this.priceInPennies = priceInPennies;
	}
	
	
	/* Getters and Setters */
	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public Stock getStockId() {
		return stockId;
	}

	public void setStockId(Stock stockId) {
		this.stockId = stockId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getQuantityOfShares() {
		return quantityOfShares;
	}

	public void setQuantityOfShares(int quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public double getPriceInPennies() {
		return priceInPennies;
	}

	public void setPriceInPennies(double priceInPennies) {
		this.priceInPennies = priceInPennies;
	}

	
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", stockId=" + stockId.getSymbol() + ", timestamp=" + timestamp + ", quantityOfShares="
				+ quantityOfShares + ", tradeType=" + tradeType + ", priceInPennies=" + priceInPennies + "]";
	}
	
	
}
