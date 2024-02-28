package src;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testInfixToPostfix() {
        String infixExpression = "(3 + 4) * 5";
        String expectedPostfix = "3 4 + 5 *";
        String actualPostfix = Main.infixToPostfix(infixExpression);
        assertEquals(expectedPostfix, actualPostfix);
    }

    @Test
    public void testEvaluatePostfix() {
        String postfixExpression = "3 4 + 5 *";
        int expectedValue = 35;
        int actualValue = Main.evaluatePostfix(postfixExpression);
        assertEquals(expectedValue, actualValue);
    }
}
