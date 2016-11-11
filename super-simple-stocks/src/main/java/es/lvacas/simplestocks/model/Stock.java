package es.lvacas.simplestocks.model;

import java.util.ArrayList;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Stock entity containing all the information of a stock object
 * @author lvacas
 */
@Entity
public class Stock{	
	
	@Id
	@Column
	private int stockId;
	
	@Column
	private String symbol;
	
	@ManyToOne
	@JoinColumn
	private StockType stockType;
	
	@Column
	private double lastDividendInPennies;
	
	@Column
	private double fixedDividendInPercentage;
	
	@Column
	private double parValueInPennies;
	
	@OneToMany
	private ArrayList<Trade> stockTrades;
	
	/* Constructor */
	public Stock() {
		stockTrades = new ArrayList<Trade>();		
	}

	/* Getters and Setters */
	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public double getLastDividendInPennies() {
		return lastDividendInPennies;
	}

	public void setLastDividendInPennies(double lastDividendInPennies) {
		this.lastDividendInPennies = lastDividendInPennies;
	}

	public double getFixedDividendInPercentage() {
		return fixedDividendInPercentage;
	}

	public void setFixedDividendInPercentage(double fixedDividendInPercentage) {
		this.fixedDividendInPercentage = fixedDividendInPercentage;
	}

	public double getParValueInPennies() {
		return parValueInPennies;
	}

	public void setParValueInPennies(double parValueInPennies) {
		this.parValueInPennies = parValueInPennies;
	}

	public ArrayList<Trade> getStockTrades() {
		return stockTrades;
	}

	public void setStockTrades(ArrayList<Trade> stockTrades) {
		this.stockTrades = stockTrades;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", symbol=" + symbol + ", stockType=" + stockType
				+ ", lastDividendInPennies=" + lastDividendInPennies + ", fixedDividendInPercentage="
				+ fixedDividendInPercentage + ", parValueInPennies=" + parValueInPennies + ", stockTrades="
				+ stockTrades + "]";
	}
	
	
	
	
}
