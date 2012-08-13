package com.alvinalexander.menu

class MenuRunner {

  var menuController:MenuController = _
  var menu: Menu = _
  
  def displayMenu(menuController: MenuController) {
    this.menuController = menuController
    this.menu = menuController.getMenu
    while (true) {
      val response = displayMenuAndWaitForResponse
      response match {
        case "r" | "R" =>
          println("return ...")
          Return
        case "x" | "X" => 
          println("exit ...")
          Exit
        case s: String => // anything else (1, 2, 1000, "foo", "")
          handleUsersEntry(s)
      }
    }
  }

  // user could have type anything, we only know it was a string
  private def handleUsersEntry(s: String) {
    if (s.trim == "") return
    val menuItem = getSelectedMenuItem(s)
    if (menuItem.isInstanceOf[MenuItem]) {
      val mi = menuItem.asInstanceOf[MenuItem]
      mi.executeCallback
    } else {
      println("Couldn't understand selection: " + menuItem)
    }
  }
  
  /**
   * Returns the MenuItem the user selected, or Unknown if it
   * can't figure out what the user selected.
   */
  private def getSelectedMenuItem(s: String): MenuItemInterface = {
    try {
      val selection = s.toInt
      if (selection > menu.menuItems.size) Unknown
      menu.menuItems(selection - 1)
    } catch {
      case e => Unknown
    }
  }
  
  private def displayMenuAndWaitForResponse: String = {
    println(buildDisplayableMenu)
    return readLine("Choice: ")
  }

  private def buildDisplayableMenu = {
    var sb = new StringBuilder
    var count = 1
    for (item <- menu.menuItems) {
      sb.append("%s. %s".format(count, item.text))
      count += 1
    }
    sb.append("r. Return")
    sb.append("x. Exit")
    sb.append("")
    sb.toString
  }
  
}









