package es.lvacas.simplestocks.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import es.lvacas.simplestocks.model.Stock;
import es.lvacas.simplestocks.model.StockType;
import es.lvacas.simplestocks.model.Trade;
import es.lvacas.simplestocks.model.TradeType;
import es.lvacas.simplestocks.services.SuperSimpleStocksService;
import es.lvacas.simplestocks.stocksmanager.StocksManager;


/**
 * Unit test for super simple stocks application.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application-context.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleStocksUnitaryTests {

	Logger logger = Logger.getLogger(SimpleStocksUnitaryTests.class);

	@Autowired StocksManager stocksManager;	
	@Autowired SuperSimpleStocksService superSimpleStocksService;
	
	
	@Test
	public void test1_InsertingStocksInMemory() {
		
		logger.info("TEST1 - INSERTING STOCKS IN MEMORY");
		
		// Declaring the type of stocks, common or preferred
		StockType stockTypeCommon = new StockType(1, "COMMON");
		StockType stockTypePreferred = new StockType(2, "PREFERRED");

		/////////////////////////////////////////////////////////
		// Inserting the stocks and data given in the example  //
		/////////////////////////////////////////////////////////
		
		// We used the stocksManager bean to hold the stocks in memory
		
		Stock stockTEA = new Stock();
		stockTEA.setStockId(1);
		stockTEA.setSymbol("TEA");
		stockTEA.setStockType(stockTypeCommon);
		stockTEA.setLastDividendInPennies(0);
		stockTEA.setParValueInPennies(100);
		stocksManager.addStock(stockTEA);
		logger.info("Inserted Stock TEA in memory");

		Stock stockPOP = new Stock();
		stockPOP.setStockId(2);
		stockPOP.setSymbol("POP");
		stockPOP.setStockType(stockTypeCommon);
		stockPOP.setLastDividendInPennies(8);
		stockPOP.setParValueInPennies(100);
		stocksManager.addStock(stockPOP);
		logger.info("Inserted Stock POP in memory");

		Stock stockALE = new Stock();
		stockALE.setStockId(3);
		stockALE.setSymbol("ALE");
		stockALE.setStockType(stockTypeCommon);
		stockALE.setLastDividendInPennies(23);
		stockALE.setParValueInPennies(60);
		stocksManager.addStock(stockALE);
		logger.info("Inserted Stock ALE in memory");

		Stock stockGIN = new Stock();
		stockGIN.setStockId(4);
		stockGIN.setSymbol("GIN");
		stockGIN.setStockType(stockTypePreferred);
		stockGIN.setLastDividendInPennies(8);
		stockGIN.setFixedDividendInPercentage(2);
		stockGIN.setParValueInPennies(100f);
		stocksManager.addStock(stockGIN);
		logger.info("Inserted Stock GIN in memory");

		Stock stockJOE = new Stock();
		stockJOE.setStockId(5);
		stockJOE.setSymbol("JOE");
		stockJOE.setStockType(stockTypeCommon);
		stockJOE.setLastDividendInPennies(13);
		stockJOE.setParValueInPennies(250);
		stocksManager.addStock(stockJOE);
		logger.info("Inserted Stock JOE in memory");
		logger.info("All stocks stored in memory!");
		
		
		///////////////////////////////////////////////////////////
		// Some assertions for the stored stocks                 //
		///////////////////////////////////////////////////////////
		assertTrue("It must be 5 stocks in the stocks manager", stocksManager.getStocks().size() == 5);
		assertNotNull("JOE stock must exist", stocksManager.getStockBySymbol("JOE"));
		assertTrue("Stock POP last dividend must be 8", stocksManager.getStockBySymbol("POP").getLastDividendInPennies() == 8);
		assertTrue("Stock ALE par value must be 60", stocksManager.getStockBySymbol("ALE").getParValueInPennies() == 60);
		assertTrue("Stock TEA type must be COMMON", stocksManager.getStockBySymbol("TEA").getStockType().getStockTypeName().equals("COMMON"));
		assertTrue("Stock GIN fixed dividend must be 2%", stocksManager.getStockBySymbol("GIN").getFixedDividendInPercentage() == 2);
		logger.info("All assertions checked succesfully!");
		
		logger.info("TEST1 - INSERTING STOCKS IN MEMORY DONE");		
	}
	
	
	@Test
	public void test2_RecordingTrades() {
		
		logger.info("TEST2 - RECORDING TRADES SAMPLES");
		
		// Insert type of trades in Memory
		TradeType buyTrade = new TradeType(1, "BUY");
		TradeType sellTrade = new TradeType(2, "SELL");

		// Creating some dates in the last 20 minutes
		Date date20MinsAgo = new Date(System.currentTimeMillis() - (20 * 60 * 1000));
		Date date12MinsAgo = new Date(System.currentTimeMillis() - (12 * 60 * 1000));
		Date date10MinsAgo = new Date(System.currentTimeMillis() - (10 * 60 * 1000));
		Date date5MinsAgo = new Date(System.currentTimeMillis() - (5 * 60 * 1000));
		Date date2MinsAgo = new Date(System.currentTimeMillis() - (2 * 60 * 1000));
		
		
		/////////////////////////////////////////////////////////
		// Inserting a set of trades for every stock           //
		/////////////////////////////////////////////////////////

		// Trades for TEA stock
		Stock stockTEA = stocksManager.getStockBySymbol("TEA");
		Trade tradeTEA1 = new Trade(1, stockTEA, date20MinsAgo, 50, sellTrade, 25.50);
		Trade tradeTEA2 = new Trade(2, stockTEA, date12MinsAgo, 100, buyTrade, 25.51);
		Trade tradeTEA3 = new Trade(3, stockTEA, date10MinsAgo, 200, buyTrade, 25.52);
		Trade tradeTEA4 = new Trade(4, stockTEA, date5MinsAgo, 120, buyTrade, 25.53);
		Trade tradeTEA5 = new Trade(5, stockTEA, date2MinsAgo, 70, sellTrade, 25.53);

		// Add TEA stock trades to TEA stock object
		superSimpleStocksService.RecordTrade(tradeTEA1);
		superSimpleStocksService.RecordTrade(tradeTEA2);
		superSimpleStocksService.RecordTrade(tradeTEA3);
		superSimpleStocksService.RecordTrade(tradeTEA4);
		superSimpleStocksService.RecordTrade(tradeTEA5);
		logger.info("Inserted trades for TEA in memory");
		
		// Trades for POP stock
		Stock stockPOP = stocksManager.getStockBySymbol("POP");
		Trade tradePOP1 = new Trade(1, stockPOP, date20MinsAgo, 100, sellTrade, 12.20);
		Trade tradePOP2 = new Trade(2, stockPOP, date12MinsAgo, 250, sellTrade, 12.19);
		Trade tradePOP3 = new Trade(3, stockPOP, date10MinsAgo, 50, buyTrade, 12.20);
		Trade tradePOP4 = new Trade(4, stockPOP, date5MinsAgo, 200, buyTrade, 12.21);
		Trade tradePOP5 = new Trade(5, stockPOP, date2MinsAgo, 500, buyTrade, 12.22);

		// Add POP stock trades to POP stock object
		superSimpleStocksService.RecordTrade(tradePOP1);
		superSimpleStocksService.RecordTrade(tradePOP2);
		superSimpleStocksService.RecordTrade(tradePOP3);
		superSimpleStocksService.RecordTrade(tradePOP4);
		superSimpleStocksService.RecordTrade(tradePOP5);
		logger.info("Inserted trades for POP in memory");
		
		// Trades for ALE stock
		Stock stockALE = stocksManager.getStockBySymbol("ALE");
		Trade tradeALE1 = new Trade(1, stockALE, date20MinsAgo, 200, sellTrade, 40.10);
		Trade tradeALE2 = new Trade(2, stockALE, date12MinsAgo, 500, sellTrade, 40.09);
		Trade tradeALE3 = new Trade(3, stockALE, date10MinsAgo, 70, sellTrade, 40.08);
		Trade tradeALE4 = new Trade(4, stockALE, date5MinsAgo, 150, sellTrade, 40.07);
		Trade tradeALE5 = new Trade(5, stockALE, date2MinsAgo, 200, buyTrade, 40.08);

		// Add ALE stock trades to ALE stock object
		superSimpleStocksService.RecordTrade(tradeALE1);
		superSimpleStocksService.RecordTrade(tradeALE2);
		superSimpleStocksService.RecordTrade(tradeALE3);
		superSimpleStocksService.RecordTrade(tradeALE4);
		superSimpleStocksService.RecordTrade(tradeALE5);
		logger.info("Inserted trades for ALE in memory");
		
		// Trades for GIN stock
		Stock stockGIN = stocksManager.getStockBySymbol("GIN");
		Trade tradeGIN1 = new Trade(1, stockGIN, date20MinsAgo, 100, buyTrade, 7.65);
		Trade tradeGIN2 = new Trade(2, stockGIN, date12MinsAgo, 250, buyTrade, 7.66);
		Trade tradeGIN3 = new Trade(3, stockGIN, date10MinsAgo, 50, sellTrade, 7.65);
		Trade tradeGIN4 = new Trade(4, stockGIN, date5MinsAgo, 200, buyTrade, 7.66);
		Trade tradeGIN5 = new Trade(5, stockGIN, date2MinsAgo, 500, buyTrade, 7.67);

		// Add GIN stock trades to GIN stock object
		superSimpleStocksService.RecordTrade(tradeGIN1);
		superSimpleStocksService.RecordTrade(tradeGIN2);
		superSimpleStocksService.RecordTrade(tradeGIN3);
		superSimpleStocksService.RecordTrade(tradeGIN4);
		superSimpleStocksService.RecordTrade(tradeGIN5);
		logger.info("Inserted trades for GIN in memory");
		
				
		// Trades for JOE stock
		Stock stockJOE = stocksManager.getStockBySymbol("JOE");
		Trade tradeJOE1 = new Trade(1, stockJOE, date20MinsAgo, 150, sellTrade, 30.55);
		Trade tradeJOE2 = new Trade(2, stockJOE, date12MinsAgo, 200, buyTrade, 30.56);
		Trade tradeJOE3 = new Trade(3, stockJOE, date10MinsAgo, 300, buyTrade, 30.57);
		Trade tradeJOE4 = new Trade(4, stockJOE, date5MinsAgo, 120, buyTrade, 30.58);
		Trade tradeJOE5 = new Trade(5, stockJOE, date2MinsAgo, 250, buyTrade, 30.59);

		// Add JOE stock trades to JOE stock object
		superSimpleStocksService.RecordTrade(tradeJOE1);
		superSimpleStocksService.RecordTrade(tradeJOE2);
		superSimpleStocksService.RecordTrade(tradeJOE3);
		superSimpleStocksService.RecordTrade(tradeJOE4);
		superSimpleStocksService.RecordTrade(tradeJOE5);
		logger.info("Inserted trades for JOE in memory");
		logger.info("All stocks trades stored in memory!");
		
		
		///////////////////////////////////////////////////////////
		// Some assertions for the stored trades                 //
		///////////////////////////////////////////////////////////
		assertTrue("It must be 5 trades stored for the TEA stock", stocksManager.getStockBySymbol("TEA").getStockTrades().size() == 5);
		assertTrue("The second trade of POP stock had a transaction price of 12.19 pennies", 
				stocksManager.getStockBySymbol("POP").getStockTrades().get(1).getPriceInPennies() == 12.19);
		assertTrue("The number of shares traded in the third trade of ALE stock was 70", 
				stocksManager.getStockBySymbol("ALE").getStockTrades().get(2).getQuantityOfShares() == 70);
		assertTrue("The fourth trade of GIN stock was of type BUY", 
				stocksManager.getStockBySymbol("GIN").getStockTrades().get(3).getTradeType().getTradeTypeName().equals("BUY"));
		logger.info("All assertions checked succesfully!");
		
		
		logger.info("TEST2 - RECORDING TRADES SAMPLES DONE");			
	}
	
	
	@Test
	public void test3_CalculateStockPriceTest() {
		
		try{
			logger.info("TEST3 - CALCULATING STOCK PRICE");
					
			Stock stockTEA = stocksManager.getStockBySymbol("TEA");
			double stockTEAPrice = superSimpleStocksService.CalculateStockPrice(stockTEA);
			double stockTEAPrice3decimals = Math.round(stockTEAPrice*1000)/1000.0;
			logger.info("Calculated stock price of stock TEA: " + stockTEAPrice3decimals + " pennies");
			
			Stock stockPOP = stocksManager.getStockBySymbol("POP");
			double stockPOPPrice = superSimpleStocksService.CalculateStockPrice(stockPOP);
			double stockPOPPrice3decimals = Math.round(stockPOPPrice*1000)/1000.0;
			logger.info("Calculated stock price of stock POP: " + stockPOPPrice3decimals + " pennies");
			
			
			//Assertions
			assertTrue("StockPrice of stockTEA must be 25,5218367346939", stockTEAPrice3decimals == 25.522);
			assertTrue("StockPrice of stockPOP must be 12,2095", stockPOPPrice3decimals == 12.210);
			logger.info("All assertions checked succesfully!");
			
			logger.info("TEST3 - CALCULATING STOCK PRICE DONE");
		
		} catch (Exception e) {
			logger.error("Exception in Test3");
			assertTrue("Exception in Test3", false);
		}			
	}
	
	
	@Test
	public void test4_CalculateDividendYieldTest() {
		
		try{
			logger.info("TEST4 - CALCULATING DIVIDEND YIELD");
			
			// Calculate Dividend Yield of Stock of type COMMON
			Stock stockPOP = stocksManager.getStockBySymbol("POP");
			double stockPOPDividendYield = superSimpleStocksService.CalculateDividendYield(stockPOP); 
			double stockPOPDividendYield3decimals = Math.round(stockPOPDividendYield*1000)/1000.0;
			logger.info("Dividend Yield of POP: " + stockPOPDividendYield3decimals);
			
			// Calculate Dividend Yield of Stock of type PREFERRED
			Stock stockGIN = stocksManager.getStockBySymbol("GIN");
			double stockGINDividendYield = superSimpleStocksService.CalculateDividendYield(stockGIN); 
			double stockGINDividendYield3decimals = Math.round(stockGINDividendYield*1000)/1000.0;
			logger.info("Dividend Yield of GIN: " + stockGINDividendYield3decimals);
			
			//Assertions
			assertTrue("Dividend yield of stockPOP must be 0,6552274868", stockPOPDividendYield3decimals == 0.655);
			assertTrue("Dividend yield of stockGIN must be 0,2609433101", stockGINDividendYield3decimals == 0.261);
			logger.info("All assertions checked succesfully!");
			
			logger.info("TEST4 - CALCULATING DIVIDEND YIELD DONE");
		
		}catch (Exception e) {
			logger.error("Exception in Test4");
			assertTrue("Exception in Test4", false);
		}
		
	}
	
	
	@Test
	public void test5_CalculatePERatioTest() {
		
		try{
			logger.info("TEST5 - CALCULATING P/E RATIO");
			
			// Calculate P/E Ratio
			Stock stockPOP = stocksManager.getStockBySymbol("POP");
			double stockPOPPeRatio = superSimpleStocksService.CalculatePERatio(stockPOP); 
			double stockPOPPeRatio3decimals = Math.round(stockPOPPeRatio*1000)/1000.0;
			logger.info("P/E Ratio of POP: " + stockPOPPeRatio3decimals);
			
			// Calculate Dividend Yield of Stock of type Common
			Stock stockGIN = stocksManager.getStockBySymbol("GIN");
			double stockGINPeRatio = superSimpleStocksService.CalculatePERatio(stockGIN); 
			double stockGINPeRatio3decimals = Math.round(stockGINPeRatio*1000)/1000.0;
			logger.info("P/E Ratio of GIN: " + stockGINPeRatio3decimals);
			
			//Assertions
			assertTrue("P/E Ratio of stockPOP must be 1,5261875", stockPOPPeRatio3decimals == 1.526);
			assertTrue("P/E Ratio of stockGIN must be 3,83225", stockGINPeRatio3decimals == 3.832);
			logger.info("All assertions checked succesfully!");
			
			logger.info("TEST5 - CALCULATING P/E RATIO DONE");
		
		}catch (Exception e) {
			logger.error("Exception in Test5");
			assertTrue("Exception in Test5", false);
		}
	}
	
	
	@Test
	public void test6_CalculateGBCEAllShareIndex(){
		
		try{
			logger.info("TEST6 - CALCULATING GBCE ALL SHARE INDEX");
			
			ArrayList<Stock> allStocksList = stocksManager.getStocks();
			
			double gbceAllShareIndex = superSimpleStocksService.CalculateGBCEallShareIndex(allStocksList);
			double gbceAllShareIndex3decimals = Math.round(gbceAllShareIndex*1000)/1000.0;
			logger.info("GBCE all share index: " + gbceAllShareIndex3decimals);
			
			//Assertions
			assertTrue("GBCE All share Index must be 19,64650562", gbceAllShareIndex3decimals == 19.647);
			
			logger.info("All assertions checked succesfully!");	
			
			logger.info("TEST6 - CALCULATING GBCE ALL SHARE INDEX DONE");
			logger.info("--------------------------------------------------");
			logger.info("ALL TEST PASSED");
			logger.info("--------------------------------------------------");
		
		}catch (Exception e) {
			logger.error("Exception in Test6");
			assertTrue("Exception in Test6", false);
		}
		
		
	}
	
	

	
}
