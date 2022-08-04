package myMix

import java.time.LocalDate
import scala.collection.mutable.ArrayBuffer

class Account {
  var transactions: ArrayBuffer[Transaction] = ArrayBuffer()
  def balance():Double = {
    if(transactions.length > 0) {
      transactions.sortBy(txn => txn.getDate).map(txn => txn.getAmount).sum //cutting my losses here -> can't get the balance to sum relative to the date
    }else{
      0.0
    }
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