package com.alvinalexander.menu

trait MenuItemInterface

case object Return extends MenuItemInterface
case object Exit extends MenuItemInterface
case object Unknown extends MenuItemInterface

/**
 * At least two types of MenuItem:
 * 
 *   MenuItem(text, functionToExecute)
 *   MenuItem(text, menuController)
 * 
 */
case class MenuItem (val text: String) extends MenuItemInterface {

  var callbackFunction:() => Unit = _
  var menuController: MenuController = _

  def executeCallback {
    if (callbackFunction != null) {
      callbackFunction 
    } else {
      // TODO log the callback was null
    }
  }
  
  def getControllerMenu = {
    if (menuController != null) {
      menuController.getMenu
    } else {
      // TODO fix this with Option/Some/None
      null
    }
  }
  
  def setCallbackFunction(callback:() => Unit): this.type = {
    callbackFunction = callback
    this
  }
  
  def setController(menuController: MenuController): this.type = {
    this.menuController = menuController
    this
  } 
  
}

/**
 * A Menu is an ordered collection of MenuItems.
 */
class Menu (var menuItems: Array[MenuItem])

/**
 * Simple interface for a controller. 
 */
trait MenuController {
  def getMenu:Menu
}




