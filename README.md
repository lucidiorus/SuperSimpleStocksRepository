# SuperSimpleStocks
Spring Java application which provides a set of services to carry out different operations on a stock object 
or a list of stocks objects, such as calculate the dividend yield, P/E ratio, stock price, all share index, and record trades.

## Initialization
To load the application in eclipse it is necessary to go to File -> Import -> Existing Maven Projects and to select 
the project super-simple-stocks

## Structure of project
### 1 es.lvacas.simplestocks.model
This package contains the business model classes. It has been defined the classes needed to store the objects
Stocks and Trades. A next step to this project scope would be to store this entities in a database instead of in memory.

### 2 es.lvacas.simplestocks.service
This package contains the interface and implementation of the required services carried out to fullfil the 
requested requirements of the assignment.

### 3 es.lvacas.simplestocks.stocksmanager
As no database was required, it has been implemented a bean to store in memory and get from it all the stocks used in the exercise.

### 4 es.lvacas.simplestocks.tests
To check the correct operation of the services, it has been implemented a JUnit test which make different assertions over
the operations of the application. 

It has been designed 6 tests, that are passed in order.

1 - The first test is carried out to insert the sample stocks given with the exercise and make different assertions.

2 - The second test is carried out to record a set of trades for each stock, and check the records have been done correctly.

3 - The third test is carried out to calculate the stock price for some stocks and check the calculation in correct.

4 - The fourth test is carried out to calculate de dividend yield for some stocks and check the calculation is correct.

5 - The fifth test is carried out to calculate the P/E ratio for some stocks and check the calculation is correct.

6 - The sixth test is carried out to calculate the GBCE all share index and check the calculation is correct.

To be able of check the correct operation of the methods implemented it has been design an excel sheet where the same
data sample is inserted and the same operations are calculated
https://github.com/lucidiorus/SuperSimpleStocksRepository/blob/master/Data%20Calculation%20for%20Testing.xlsx


