package com.alvinalexander.menu

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class MenuRunnerTests extends FunSuite with BeforeAndAfter {
  
  var menuRunner: MenuRunner = _

  before {
    menuRunner = new MenuRunner("\n", "")
  }

  test("one level menu has expected size") {
    val ctrl = new TestController
    menuRunner.displayMenu(ctrl)
    assert(menuRunner.stack.size == 1)
  }
  test("one level menu has expected content") {
    val ctrl = new TestController
    menuRunner.displayMenu(ctrl)
    val menuText = menuRunner.buildDisplayableMenu
    println(":" + menuText + ":")
    val expectedMenuText ="""
      |
      |Foo Menu
      |
      |1. Foo
      |2. Bar
      |x. Exit
      |""".stripMargin
    assert(menuText == expectedMenuText)
  }

}

class TestController extends MenuController {
  def getMenu = {
    val list = new ExecuteFunctionMenuItem("Foo", doNothing)
    val add = new ExecuteFunctionMenuItem("Bar", doNothing)
    new Menu("Foo Menu", Array(list, add))
  }
  def doNothing() { println("Doing nothing ...") }
}
