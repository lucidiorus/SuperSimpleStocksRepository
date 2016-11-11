package es.lvacas.simplestocks.stocksmanager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import es.lvacas.simplestocks.model.Stock;
import es.lvacas.simplestocks.services.SuperSimpleStocksService;

/**
 * This class is used as an entity manager to store in memory all the stock objects
 * @author lvacas
 */
@Component
@Scope("singleton")
public class StocksManager {
	
	private ArrayList<Stock> stocks;
	
	public StocksManager() {
		stocks = new ArrayList<Stock>();
	}

	public ArrayList<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public void addStock(Stock stock){
		stocks.add(stock);
	}
	
	public Stock getStockBySymbol(String symbol){
		Stock result = null;
		for(Stock stock : stocks){
			if(stock.getSymbol().equals(symbol)){
				result = stock;
			}
		}		
		return result;		
	}

	@Override
	public String toString() {
		return "StocksManager [stocks=" + stocks + "]";
	}
	
}
