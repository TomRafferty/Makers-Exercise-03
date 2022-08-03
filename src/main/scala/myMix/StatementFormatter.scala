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
  private def sortTransactions(txns: Seq[Transaction]) =
    txns.sortBy(tx => tx.getDate)

  //I don't understand the syntax used here.
  //is it like 2 functions running on a
  //conditional depending on the params passed?
  private def formatTransactions: Double => Seq[Transaction] => String = (startingBalance: Double) => {
    case Nil => ""
    case tx :: txs =>
      formatTransactions(startingBalance + tx.getAmount)(txs)
        .concat(formatLine(tx.getAmount, tx.getDate, startingBalance + tx.getAmount))
  }

  //TODO the count here is indexing wrong -> figure out where it's going wrong
  @tailrec
  private def newFormatter(transactions: Seq[Transaction], thisString: String = "", count: Int = 0, startingBalance: Double = 0): String ={
    val newCount = count + 1
    val currentTxn = transactions(count)
    if(count == transactions.length){
      thisString
    }else{
      val newString = s"$thisString\n${currentTxn.getAmount}, ${currentTxn.getDate}, ${currentTxn.getAmount + startingBalance}"
      newFormatter(transactions = transactions,thisString = newString, count = newCount, startingBalance = startingBalance+currentTxn.getAmount)
    }
  }

  private def addHeader(formattedTxns: String): String = StatementHeader.concat(formattedTxns)

  private def formatLine(a: Any, b: Any, c: Any) = s"$a,$b,$c\n"
}
