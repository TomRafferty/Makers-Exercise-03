import myMix.{Account, StatementFormatter}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{LocalDateTime, ZoneId}
class StatementFormatterTest extends AnyWordSpec with Matchers {
  val testDate = LocalDateTime.now(ZoneId.of("UTC")).toLocalDate
  "StatementFormatter" should {
    "send statement" in {
      val acc = new Account
      acc.deposit(5.0, testDate)
      val newStatement = new StatementFormatter
      val formattedStatement = newStatement.format(acc.transactions.toSeq)
      assert(formattedStatement.contains(s"Amount,Date,Balance\n5.0,$testDate,5.0"))
    }
  }
}