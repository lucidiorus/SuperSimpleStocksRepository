package es.lvacas.simplestocks.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import es.lvacas.simplestocks.model.Stock;
import es.lvacas.simplestocks.model.Trade;

/**
 * Implementation of the super simple stock service
 * @author lvacas
 */
@Service
public class SuperSimpleStocksServiceImpl implements SuperSimpleStocksService {
	
	Logger logger = Logger.getLogger(SuperSimpleStocksServiceImpl.class);

	
	/*
	 * (non-Javadoc)
	 * @see es.lvacas.simplestocks.services.SuperSimpleStocksService#CalculateDividendYield(es.lvacas.simplestocks.model.Stock)
	 */
	public double CalculateDividendYield(Stock stock) throws Exception {
		double dividendYieldResult = 0;
				
		try {
			logger.debug("Calculating dividend yield of stock");
			
			if(stock==null){
				throw new Exception("Stock parameter can not be null");
			}
			
			double stockPriceInPennies = CalculateStockPrice(stock);
			if(stockPriceInPennies<=0){
				throw new Exception("Stock price must be greater than 0");
			}
			
			String stockTypeName = stock.getStockType().getStockTypeName();
			if(stockTypeName.equals("COMMON")){
				double lastDividendInPennies = stock.getLastDividendInPennies();
				dividendYieldResult = lastDividendInPennies / stockPriceInPennies;
			}
			else if(stockTypeName.equals("PREFERRED")){
				double stockFixedDividend = stock.getFixedDividendInPercentage()/100;
				double stockParValueInPennies = stock.getParValueInPennies();
				dividendYieldResult = (stockFixedDividend * stockParValueInPennies) / stockPriceInPennies;
			}
			
			logger.debug("Calculating dividend yield done");
			
		} catch (Exception e) {
			logger.error("Error Calculating dividend yield of stock");
			throw new Exception("Error Calculating dividend yield of stock");
		}
		
		return dividendYieldResult;
	}

	
	/*
	 * (non-Javadoc)
	 * @see es.lvacas.simplestocks.services.SuperSimpleStocksService#CalculatePERatio(es.lvacas.simplestocks.model.Stock)
	 */
	public double CalculatePERatio(Stock stock) throws Exception {
		double peRatioResult = 0;
		
		try {
			logger.debug("Calculating P/E Ratio of stock");
			
			if(stock==null){
				throw new Exception("Stock parameter can not be null");
			}
			
			double stockPriceInPennies = CalculateStockPrice(stock);
			double dividendInPennies = CalculateDividend(stock);
			
			if(dividendInPennies<=0){
				throw new Exception("Dividend must be greater than 0 for calculating P/E Ratio");
			}
						
			peRatioResult = stockPriceInPennies / dividendInPennies;
			logger.debug("Calculating P/E Ratio done");
		} catch (Exception e) {
			logger.error("Error Calculating P/E Ratio of stock");
			throw new Exception("Error Calculating P/E Ratio of stock");
		}
		
		return peRatioResult;
	}

	
	/*
	 * (non-Javadoc)
	 * @see es.lvacas.simplestocks.services.SuperSimpleStocksService#RecordTrade(es.lvacas.simplestocks.model.Trade)
	 */
	public boolean RecordTrade(Trade trade) {
	
		Stock stock = trade.getStockId();
		stock.getStockTrades().add(trade);
		
		return true;
		
	}

	
	/*
	 * (non-Javadoc)
	 * @see es.lvacas.simplestocks.services.SuperSimpleStocksService#CalculateStockPrice(es.lvacas.simplestocks.model.Stock)
	 */
	public double CalculateStockPrice(Stock stock) throws Exception {
		double stockPriceResultInPennies = 0;
			
		try {
			logger.debug("Calculating stock price");
			int minutesToCalculateStockPrice = 15;
			
			if(stock==null){
				throw new Exception("Stock can not be null");
			}
			
			ArrayList<Trade> stockTrades = stock.getStockTrades();
			
			// Get current Date in the moment of carrying out the operation
			Date currentDate = new Date();
			
			int volumeOfSharesTradedInLastGivenMinutes = 0;
			double totalValueOfTradesDoneInLasGivenMinutes = 0f;
			
			// Iterate over all stored trades to get into account only those carried out in the last 15 minutes
			for(Trade trade : stockTrades){
				int minutesSinceTrade = (int)((currentDate.getTime() - trade.getTimestamp().getTime()) / (1000 * 60));
				if(minutesSinceTrade<=minutesToCalculateStockPrice){
					int quantityOfSharesOfTrade = trade.getQuantityOfShares();
					double priceOfTradeInPennis = trade.getPriceInPennies();
					double valueOfTradeInPennis = priceOfTradeInPennis * quantityOfSharesOfTrade;
					volumeOfSharesTradedInLastGivenMinutes += quantityOfSharesOfTrade;
					totalValueOfTradesDoneInLasGivenMinutes += valueOfTradeInPennis;
				}
			}
			
			if(volumeOfSharesTradedInLastGivenMinutes!=0){
				stockPriceResultInPennies = totalValueOfTradesDoneInLasGivenMinutes/volumeOfSharesTradedInLastGivenMinutes;
			}
			logger.debug("Calculating stock price done");
		} catch (Exception e) {
			logger.error("Error Calculating stock price");
			throw new Exception("Error Calculating stock price");
		}
		return stockPriceResultInPennies;
	}

	
	/*
	 * (non-Javadoc)
	 * @see es.lvacas.simplestocks.services.SuperSimpleStocksService#CalculateGBCEallShareIndex(java.util.List)
	 */
	public double CalculateGBCEallShareIndex(List<Stock> allStocksOfGBCE) throws Exception {
		double gbceAllShareIndex = 0;
		
		try{
			logger.debug("Calculatin GBCE all share index");
			
			double totalNumberOfStocks = allStocksOfGBCE.size();
			if(totalNumberOfStocks<=0){
				throw new Exception("To calculate the GBCE index, it must exist at least 1 stock");
			}
			
			double multiplicationOfAllStockPrices = 1;
			
			for(Stock stock : allStocksOfGBCE){
				multiplicationOfAllStockPrices *= CalculateStockPrice(stock);
			}
			
			// One way of calculating geometric mean -> geometricMean = (P1*P2*...*Pn)^(1/n)
			gbceAllShareIndex = Math.pow(multiplicationOfAllStockPrices, 1/totalNumberOfStocks);
			
			logger.debug("Calculating GBCE all share index done");
		} catch(Exception e){
			logger.error("Error Calculating GBCE all share index");
			throw new Exception("Error Calculating GBCE all share index");
		}
		
		return gbceAllShareIndex;
	}
	
	
	/**
	 * Class used internally to get the dividend of a given stock depending on its type
	 * @param stock
	 * @return the dividend of the stock
	 */
	private double CalculateDividend(Stock stock){
		double dividendResultInPennies = 0;
		
		if(stock.getStockType().getStockTypeName().equals("COMMON")){
			dividendResultInPennies = stock.getLastDividendInPennies();
		}
		else{
			dividendResultInPennies = stock.getParValueInPennies() * stock.getFixedDividendInPercentage() / 100;
		}
		
		return dividendResultInPennies;
	}
	

}
