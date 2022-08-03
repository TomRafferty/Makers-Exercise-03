package myMix

import scala.annotation.tailrec

class StatementFormatter{
  val StatementHeader: String = formatLine("Amount", "Date", "Balance")

  def format(transactions: Seq[Transaction]): String = {
    if(transactions.length > 0){
      val sorted = sortTransactions(transactions)
      val formatted = newFormatter(sorted)
      addHeader(formatted)
    }else{
      StatementHeader
    }
  }

  //TODO this is not functioning correctly.
  private def sortTransactions(txns: Seq[Transaction]) = txns.sortBy(tx => tx.getDate)

  @tailrec
  private def newFormatter(transactions: Seq[Transaction], thisString: String = "", count: Int = 0, startingBalance: Double = 0): String ={
    val newCount = count + 1
    if(count == transactions.length){
      thisString
    }else{
      val currentTxn = transactions(count)
      val newString = {
        if(thisString.length > 0){
          s"\n$thisString\n${currentTxn.getAmount},${currentTxn.getDate},${currentTxn.getAmount + startingBalance}"
        }else{
          s"${currentTxn.getAmount},${currentTxn.getDate},${currentTxn.getAmount + startingBalance}"
        }
      }
      newFormatter(transactions = transactions,thisString = newString, count = newCount, startingBalance = startingBalance+currentTxn.getAmount)
    }
  }

  private def addHeader(formattedTxns: String): String = StatementHeader.concat(formattedTxns)

  private def formatLine(a: Any, b: Any, c: Any) = s"$a,$b,$c\n"
}
