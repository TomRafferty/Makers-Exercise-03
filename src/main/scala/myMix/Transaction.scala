package myMix

import java.time.LocalDate

class Transaction(transactionAmount: Double, transactionDate: LocalDate){
  def getAmount: Double = {
    transactionAmount
  }
  def getDate: LocalDate = {
    transactionDate
  }
}
