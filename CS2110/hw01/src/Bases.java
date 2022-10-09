/**
 * CS 2110 Spring 2022 HW1
 * Part 2 - Coding with bases
 *
 * @author Joshua Wainwright
 *
 * Global rules for this file:
 * - You may not use more than 2 conditionals per method. Conditionals are
 *   if-statements, if-else statements, or ternary expressions. The else block
 *   associated with an if-statement does not count toward this sum.
 * - You may not use more than 2 looping constructs per method. Looping
 *   constructs include for loops, while loops and do-while loops.
 * - You may not use nested loops.
 * - You may not declare any file-level variables.
 * - You may not use switch statements.
 * - You may not use the unsigned right shift operator (>>>)
 * - You may not write any helper methods, or call any method from this or
 *   another file to implement any method. Recursive solutions are not
 *   permitted.
 * - The only Java API methods you are allowed to invoke are:
 *     String.length()
 *     String.charAt()
 * - You may not invoke the above methods from string literals.
 *     Example: "12345".length()
 * - When concatenating numbers with Strings, you may only do so if the number
 *   is a single digit.
 *
 * Method-specific rules for this file:
 * - You may not use multiplication, division or modulus in any method, EXCEPT
 *   decimalStringToInt (where you may use multiplication only)
 * - You may declare exactly one String variable each in intToOctalString and
 *   and binaryStringToHexString.
 */
public class Bases
{
    /**
     * Convert a string containing ASCII characters (in binary) to an int.
     *
     * You do not need to handle negative numbers. The Strings we will pass in
     * will be valid binary numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: binaryStringToInt("111"); // => 7
     */
    public static int binaryStringToInt(String binary)
    {
        int sum = 0;
        int power = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                sum += power;
            }
            power += power;
        }
        return sum;
    }

    /**
     * Convert a string containing ASCII characters (in decimal) to an int.
     *
     * You do not need to handle negative numbers. The Strings we will pass in
     * will be valid decimal numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: decimalStringToInt("46"); // => 46
     *
     * You may use multiplication in this method.
     */
    public static int decimalStringToInt(String decimal)
    {
        int sum = 0;
        int power = 1;
        for (int i = decimal.length() - 1; i >= 0; i--) {
            int nine = decimal.charAt(i) - 48;
            sum += power*nine;
            power *= 10;
        }
        return sum;
    }

    /**
     * Convert a string containing ASCII characters (in hex) to an int.
     * The input string will only contain numbers and uppercase letters A-F.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid hexadecimal numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: hexStringToInt("A6"); // => 166
     */
    public static int hexStringToInt(String hex) {
        int shift = 0;
        int sum = 0;
        for (int i = hex.length() - 1; i >=0 ; i--) {
            int curr;
            if (hex.charAt(i) >= 65 && hex.charAt(i) <= 70) {
                curr = hex.charAt(i) - 55;
            } else {
                curr = hex.charAt(i) - 48;
            }
            sum += (curr << shift);
            shift += 4;
        }
        return sum;
    }

    /**
     * Convert a int into a String containing ASCII characters (in octal).
     *
     * You do not need to handle negative numbers.
     * The String returned should contain the minimum number of characters
     * necessary to represent the number that was passed in.
     *
     * Example: intToOctalString(166); // => "246"
     *
     * You may declare one String variable in this method.
     */
    public static String intToOctalString(int octal) {
        String ret = "";
        int dec = octal;
        while (dec != 0) {
            int quo = (dec >> 3);
            int rem = dec - (quo << 3);
            ret = (char) (rem + 48) + ret;
            dec = quo;
        }
        return ret;
    }

    /**
     * Convert a String containing ASCII characters representing a number in
     * binary into a String containing ASCII characters that represent that same
     * value in hex.
     *
     * The output string should only contain numbers and capital letters.
     * You do not need to handle negative numbers.
     * All binary strings passed in will contain exactly 32 characters.
     * The octal string returned should contain exactly 8 characters.
     *
     * Example: binaryStringToHexString("00001111001100101010011001011100"); // => "0F32A65C"
     *
     * You may declare one String variable in this method.
     */
    public static String binaryStringToHexString(String binary) {
        String ret = "";
        int shift = 0;
        int hexSum = 0;
        for (int i = binary.length() - 1; i >= 0; i--) {
            hexSum += ((binary.charAt(i) - 48) << shift);
            shift++;
            if (shift == 4) {
                if (hexSum >= 10 && hexSum <= 15) {
                    ret = (char) (hexSum + 55) + ret;
                } else {
                    ret = (char) (hexSum + 48) + ret;
                }
                shift = 0;
                hexSum = 0;
            }
        }
        return ret;
    }
}
