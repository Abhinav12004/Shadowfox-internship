
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    public void setup() {
        account = new BankAccount();
    }

    @Test
    void getBalance() {
        assertEquals(0.0, account.getBalance(), "Initial balance should be 0");

        account.deposit(500);
        assertEquals(500.0, account.getBalance(), "Balance after depositing 500 should be 500");
    }

    @Test
    void deposit() {
        account.deposit(1000);
        assertEquals(1000.0, account.getBalance(), "Balance after depositing 1000 should be 1000");

        account.deposit(0);
        assertEquals(1000.0, account.getBalance(), "Balance should remain 1000 after depositing 0");

        account.deposit(-500);
        assertEquals(1000.0, account.getBalance(),
                "Balance should remain 1000 after trying to deposit a negative amount");
    }

    @Test
    void withdraw() {
        account.deposit(1000);
        account.withdraw(500);
        assertEquals(500.0, account.getBalance(), "Balance after withdrawing 500 should be 500");

        account.withdraw(0);
        assertEquals(500.0, account.getBalance(), "Balance should remain 500 after withdrawing 0");

        account.withdraw(1000);
        assertEquals(500.0, account.getBalance(),
                "Balance should remain 500 after trying to withdraw more than balance");

        account.withdraw(-200);
        assertEquals(500.0, account.getBalance(),
                "Balance should remain 500 after trying to withdraw a negative amount");
    }

    @Test
    void getTransactionHistory() {
        account.deposit(1000);
        account.withdraw(500);
        String expectedHistory = "Deposited: 1000.0\nWithdrew: 500.0\n";
        assertEquals(expectedHistory, account.getTransactionHistory(),
                "Transaction history should match the deposit and withdrawal actions");
    }
}