import myMix.Account
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{LocalDateTime, ZoneId}
class AccountTest extends AnyWordSpec with Matchers{

  val testDate = LocalDateTime.now(ZoneId.of("UTC"))
  val testDateLocal = testDate.toLocalDate

  "OOAccount" should {

    "Have a balance" in {
      val acc = new Account
      assert(acc.balance === 0.0)
    }

    "deposit money via transaction" in {
      val acc =  new Account
      acc.deposit(5.0, testDateLocal)
      assert(acc.balance === 5.0)
    }

    "withdraw money via transaction" in {
      val acc =  new Account
      acc.deposit(50.0, testDateLocal)
      acc.withdraw(5.0, testDateLocal)
      assert(acc.balance === 45.0)
    }

    "have the correct date" in {
      val acc = new Account
      acc.deposit(5.0, testDateLocal)
      assert(acc.transactions.toArray.head.getDate === testDateLocal)
    }

  }
}
