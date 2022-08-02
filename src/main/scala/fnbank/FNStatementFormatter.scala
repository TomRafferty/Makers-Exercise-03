package fnbank

import oobank.OOTransaction

object FNStatementFormatter {
  val StatementHeader: String = formatLine("Amount", "Date", "Balance")

  def format: Seq[OOTransaction] => String =
    sortOOTransactions _ andThen
      formatOOTransactions(0) andThen
      addHeader

  private def sortOOTransactions(txns: Seq[OOTransaction]) =
    txns.sortBy(tx => tx.date)

  private def formatOOTransactions: Int => Seq[OOTransaction] => String = (startingBalance: Int) => {
    case Nil => ""
    case tx :: txs =>
      formatOOTransactions(startingBalance + tx.amount)(txs)
        .concat(formatLine(tx.amount, tx.date, startingBalance + tx.amount))
  }

  private def addHeader(formattedTxns: String): String = StatementHeader.concat(formattedTxns)

  private def formatLine(a: Any, b: Any, c: Any) = s"$a,$b,$c\n"
}
