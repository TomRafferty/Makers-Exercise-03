package oobank

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

class OOAccount {
  var balance = 0
  var transactions: ArrayBuffer[OOTransaction] = ArrayBuffer()

  def deposit(amount: Int, date: LocalDate): Unit = {
    balance += amount
    transactions += new OOTransaction(amount, date)
  }

  def withdraw(amount: Int, date: LocalDate): Unit = {
    balance -= amount
    transactions += new OOTransaction(-amount, date)
  }
}
