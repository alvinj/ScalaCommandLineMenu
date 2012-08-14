package com.alvinalexander.menu

object Main extends App {

  val mainMenuCtrl = new MainMenuController
  val menuRunner = new MenuRunner("\n", "")
  menuRunner.displayMenu(mainMenuCtrl)
  
}

class MainMenuController extends MenuController {
  def getMenu = {
    val stocks = new ShowMenuMenuItem("Stocks", stocksMenuController)
    val agg = new ShowMenuMenuItem("Aggregate", aggregateMenuController)
    new Menu("Main Menu", Array(stocks, agg))
  }
  val stocksMenuController = new StocksMenuController
  val aggregateMenuController = new AggregateMenuController
}

class StocksMenuController extends MenuController {
  def getMenu = {
    val list = new ExecuteFunctionMenuItem("List", listStocks)
    val add = new ExecuteFunctionMenuItem("Add", addStock)
    val delete = new ExecuteFunctionMenuItem("Delete", deleteStock)
    val foo = new ShowMenuMenuItem("Foo Menu", new FooController)
    new Menu("Stocks Menu", Array(list, add, delete, foo))
  }
  def doNothing() { println("Doing nothing ...") }
  def listStocks() { println("Here are your stocks ...") }
  def addStock() { println("Add a stock ...") }
  def deleteStock() { println("Delete a stock ...") }
}

class FooController extends MenuController {
  def getMenu = {
    val list = new ExecuteFunctionMenuItem("Foo", doNothing)
    val add = new ExecuteFunctionMenuItem("Bar", doNothing)
    val delete = new ExecuteFunctionMenuItem("Baz", doNothing)
    new Menu("Foo Menu", Array(list, add, delete))
  }
  def doNothing() { println("Doing nothing ...") }
}

class AggregateMenuController extends MenuController {
  def getMenu = {
    val showPrices = new ExecuteFunctionMenuItem("Show Prices", doNothing)
    val showNewsHeadlines = new ExecuteFunctionMenuItem("Show News Headlines", doNothing)
    new Menu("Agg Menu", Array(showPrices, showNewsHeadlines))
  }
  def doNothing() { println("Doing nothing ...") }
}















