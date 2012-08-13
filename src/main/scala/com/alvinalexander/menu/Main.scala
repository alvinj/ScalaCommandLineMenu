package com.alvinalexander.menu

object Main extends App {

  val mainMenuCtrl = new MainMenuController
  val menuRunner = new MenuRunner
  menuRunner.displayMenu(mainMenuCtrl)
  
}

class MainMenuController extends MenuController {
  def getMenu = {
    val stocks = MenuItem("Stocks").setController(stocksMenuController)
    val agg = MenuItem("Aggregate").setController(aggregateMenuController)
    new Menu(Array(stocks, agg))
  }
  val stocksMenuController = new StocksMenuController
  val aggregateMenuController = new AggregateMenuController
}

class StocksMenuController extends MenuController {
  def getMenu = {
    val list = MenuItem("List").setCallbackFunction(listStocks)
    val add = MenuItem("Add").setCallbackFunction(addStock)
    val delete = MenuItem("Delete").setCallbackFunction(deleteStock)
    new Menu(Array(list, add, delete))
  }
  def doNothing() {}
  def listStocks() {}
  def addStock() {}
  def deleteStock() {}
}

class AggregateMenuController extends MenuController {
  def getMenu = {
    val showPrices = MenuItem("Show Prices").setCallbackFunction(doNothing)
    val showNewsHeadlines = MenuItem("Show News Headlines").setCallbackFunction(doNothing)
    new Menu(Array(showPrices, showNewsHeadlines))
  }
  def doNothing() {}
}

