import myMix.{Account, StatementFormatter}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{LocalDate, LocalDateTime, ZoneId}
class StatementFormatterTest extends AnyWordSpec with Matchers {

  val testDate = LocalDateTime.now(ZoneId.of("UTC")).toLocalDate

  "StatementFormatter" should {

    "Contains all relevant header columns" in {
      val acc = new Account
      val newStatement = new StatementFormatter
      val formattedStatement = newStatement.format(acc.transactions.toSeq)
      assert(formattedStatement.contains("Amount,Date,Balance"))
    }

    "Send formatted statement" in {
      val acc = new Account
      acc.deposit(5.0, testDate)
      val newStatement = new StatementFormatter
      val formattedStatement = newStatement.format(acc.transactions.toSeq)
      assert(formattedStatement.contains(s"Amount,Date,Balance\n5.0,$testDate,5.0"))
    }

    "Order transactions by date" in {
      val date2 = LocalDate.parse("2012-08-02")
      val date3 = LocalDate.parse("2011-01-01")
      val date4 = LocalDate.parse("2010-01-01")
      val acc = new Account
      acc.deposit(1,date4)
      acc.withdraw(1, date3)
      acc.deposit(50.0, date2)
      acc.deposit(5.0, testDate)
      val newStatement = new StatementFormatter
      val formattedStatement = newStatement.format(acc.transactions.toSeq)
      println(formattedStatement)
      println(acc.balance)
      assert(formattedStatement.contains(f"""|Amount,Date,Balance
                                             |1.0,$date4,1.0
                                             |-1.0,$date3,0.0
                                             |50.0,$date2,50.0
                                             |5.0,$testDate,55.0""".stripMargin))
    }
  }
}