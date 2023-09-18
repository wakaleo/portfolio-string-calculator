package dojo.portfolio.stringcalculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("When calculating results with the String Calculator")
public class WhenCalculatingResults {
    /*
    - "" => 0
    - 1 => 1
    - 1 + 1 => 2
    - 1 + 2 => 3
    - 1 + 2 + 3 => 6
    - 10 - 6 => 4
    - 10 * 5 => 50
     */

    StringCalculator calculator;

    @BeforeEach
    void setupCalculator() {
        this.calculator = new StringCalculator();
    }

    @DisplayName("The calculator should return zero for empty string")
    @Test
    void shouldReturnZeroForEmptyString() {
        double result = calculator.calculate("");

        assertThat(result).isEqualTo(0.0);
    }

    @ParameterizedTest
    @DisplayName("The calculator should return the number for a single integer number")
    @CsvSource(
            value = {
                    "1 = 1.0",
                    "1.5 = 1.5",
                    "1234.567 = 1234.567"
            },
            delimiterString = "="
    )
    void shouldReturnTheNumberForASingleNumber(String expression, double expected) {

        double result = calculator.calculate(expression);

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("The calculator should return the calculated result for two numbers")
    @CsvSource(
            value = {
                    // Addition
                    "1 + 2 = 3.0",
                    "1.5 + 2.4 = 3.9",

                    // Subtraction
                    "10 - 4 = 6.0",
                    "10.5 - 4.5 = 6.0",
                    "4 - 6.5 = -2.5",

                    // Multiplication
                    "10 * 5 = 50.0",
                    "10.5 * 5.5 = 57.75",
                    "4 * 6.5 = 26.0",

                    // Division
                    "10 / 5 = 2.0",
                    "10.5 / 5.5 = 1.9090909090909092",
                    "4 / 6.5 = 0.6153846153846154"
            },
            delimiterString = "="
    )
    void shouldReturnTheCalculatedResultForTwoNumbers(String expression, double expected) {

        double result = calculator.calculate(expression);

        assertThat(result).isEqualTo(expected);
    }

    @Nested
    class AddingTwoNumbers {
        @Test
        void shouldReturnTheSumOfTwoNumbers() {
            double result = calculator.calculate("1 + 2");

            assertThat(result).isEqualTo(3.0);
        }

        @Test
        void shouldReturnTheSumOfTwoDecimalNumbers() {
            double result = calculator.calculate("1.5 + 2.5");

            assertThat(result).isEqualTo(4.0);
        }
    }

    @Nested
    class AddingThreeNumbers {
        @Test
        void shouldReturnTheSumOfThreeNumbers() {
            double result = calculator.calculate("1 + 2 + 3");

            assertThat(result).isEqualTo(6.0);
        }
    }
}
