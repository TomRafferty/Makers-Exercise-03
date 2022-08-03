package myMix

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

class Account {
  var transactions: ArrayBuffer[Transaction] = ArrayBuffer()
  def balance {
    transactions.map(txn => txn.getAmount).sum
  }

  def deposit(amount: Double, date: LocalDate): Unit = {
    transactions += new Transaction(amount, date)
    balance
  }

  def withdraw(amount: Double, date: LocalDate): Unit = {
    transactions += new Transaction(-amount, date)
    balance
  }
}