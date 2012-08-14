package com.alvinalexander.menu

// public abstract java.lang.String text();
// public abstract void text_$eq(java.lang.String);
trait MenuItem {
  val text: String
}

/**
 * Execute a function when the menu item is selected.
 */
class ExecuteFunctionMenuItem(val text: String, val callback:() => Unit) extends MenuItem {
  def executeCallback { callback() }
}

/**
 * Use this when a menu item selection leads to another menu.
 */
class ShowMenuMenuItem(val text: String, val menuController: MenuController) extends MenuItem {
  def getControllerMenu = menuController.getMenu
}


/**
 * A Menu is an ordered collection of MenuItems.
 */
class Menu (val title: String, var menuItems: Array[MenuItem])

/**
 * Simple interface for a menu controller. 
 */
trait MenuController {
  def getMenu:Menu
}




