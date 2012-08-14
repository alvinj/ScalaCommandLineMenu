package com.alvinalexander.menu
import scala.collection.mutable.Stack

class MenuRunner(val prefix:String = "", val suffix:String = "") {

  var menuController:MenuController = _
  var menu: Menu = _
  var toldToExit = false
  var stack = new Stack[Menu]

  def displayMenu(menuController: MenuController) {
    this.menuController = menuController
    this.menu = menuController.getMenu
    stack.push(menu)
    while (!toldToExit) {
      val response = displayMenuAndWaitForResponse
      response match {
        case "r" | "R" =>
          // when stack.size is 1, we're at the main menu, don't use 'r'
          if (stack.size != 1) {
            stack.pop
            menu = stack.head
            return
          }
        case "x" | "X" => 
          toldToExit = true
        case s: String => // anything else (1, 2, 1000, "foo", "")
          handleUsersEntry(s)
      }
      println("")
    }
  }

  // user could have type anything, we only know it was a string
  private def handleUsersEntry(s: String) {
    if (s.trim == "") return
    val menuItem = getSelectedMenuItem(s)
    menuItem match {
      case Some(mi:ExecuteFunctionMenuItem) => mi.executeCallback
      case Some(mi:ShowMenuMenuItem) => displayMenu(mi.menuController)
      case _ => println("I don't know what you selected.")
    }
  }
  
  /**
   * Returns the MenuItem the user selected, or Unknown if it
   * can't figure out what the user selected.
   */
  private def getSelectedMenuItem(s: String): Option[MenuItem] = {
    try {
      val selection = s.toInt
      if (selection > menu.menuItems.size) None
      Some(menu.menuItems(selection - 1))
    } catch {
      case e => None
    }
  }
  
  private def displayMenuAndWaitForResponse: String = {
    println(buildDisplayableMenu)
    return readLine("Choice: ")
  }

  private def buildDisplayableMenu = {
    var sb = new StringBuilder
    sb.append("\n")
    sb.append(prefix + menu.title + suffix + "\n\n")
    var count = 1
    for (item <- menu.menuItems) {
      sb.append("%s. %s\n".format(count, item.text))
      count += 1
    }
    if (stack.size != 1) sb.append("r. Return\n")
    sb.append("x. Exit\n")
    sb.append("")
    sb.toString
  }
  
}









