package es.lvacas.simplestocks.services;

import java.util.List;

import es.lvacas.simplestocks.model.Stock;
import es.lvacas.simplestocks.model.Trade;

/**
 * Service which offers all the methods required by the super simple stock application
 * @author lvacas
 */
public interface SuperSimpleStocksService {
	
	/** 
	 * Calculate the dividend yield of a stock using the given simplified formula.
	 * The formula used depends on the type of stock, COMMON or PREFERRED.
	 * The dividend yield indicates how much a company pays out in dividens
	 * relative to the price of its share
	 * @param stock
	 * @return the dividend yield of the stock
	 * @throws Exception 
	 */
	public double CalculateDividendYield(Stock stock) throws Exception;
	
	/**
	 * Calculate the P/E Ratio of a stock using the given simplified formula.
	 * The P/E Ratio (Price-Earnings Ratio) is used to value a company
	 * measuring its current stock price relative to its per-share earnings
	 * @param stock
	 * @return the P/E Ratio of the stock
	 * @throws Exception 
	 */
	public double CalculatePERatio(Stock stock) throws Exception;
	
	/**
	 * Record a trade, with timestamp, quantity of shares, type of transaction 
	 * (BUY or SELL) and the price of the transaction
	 * @param trade
	 * @return TRUE if the trade is recorded properly in memory, FALSE otherwise
	 */
	public boolean RecordTrade(Trade trade);
	
	/**
	 * Calculate the price of a stock based on trades recorded in the
	 * last 15 minutes, using the given simplified formula
	 * @param stockSymbol
	 * @return the price of the stock
	 * @throws Exception 
	 */
	public double CalculateStockPrice(Stock stock) throws Exception;
	
	
	/**
	 * Calculate the GBCE all share index using the geometic mean of prices
	 * for all stocks using the given simplified formula
	 * @param allStocksOfGBCE
	 * @return the GBCE all share index
	 * @throws Exception 
	 */
	public double CalculateGBCEallShareIndex(List<Stock> allStocksOfGBCE) throws Exception;
	
}
