import myMix.{Account, StatementFormatter}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{LocalDate, LocalDateTime, ZoneId}
class StatementFormatterTest extends AnyWordSpec with Matchers {
  val testDate = LocalDateTime.now(ZoneId.of("UTC")).toLocalDate
  val oldDate = LocalDate.parse("2012-08-02")
  "StatementFormatter" should {
    "Send formatted statement" in {
      val acc = new Account
      acc.deposit(5.0, testDate)
      val newStatement = new StatementFormatter
      val formattedStatement = newStatement.format(acc.transactions.toSeq)
      assert(formattedStatement.contains(s"Amount,Date,Balance\n5.0,$testDate,5.0"))
    }
    "Order transactions by date" in {
      val acc = new Account
      acc.deposit(5.0, testDate)
      acc.deposit(50.0, oldDate)
      val newStatement = new StatementFormatter
      val formattedStatement = newStatement.format(acc.transactions.toSeq)
      assert(formattedStatement.contains(f"""|Amount,Date,Balance
                                             |5.0,$testDate,55.0
                                             |50.0,$oldDate,50.0""".stripMargin))
    }
  }
}