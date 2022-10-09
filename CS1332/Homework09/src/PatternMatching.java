import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Joshua Wainwright
 * @version 1.0
 * @userid jwainwright3
 * @GTID 903584588
 *
 * Collaborators:None
 *
 * Resources:csvistool, modules
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the buildFailureTable() method before implementing
     * this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("Inputted pattern is null");
        }
        if (pattern.length() == 0) {
            throw new java.lang.IllegalArgumentException("Inputted pattern has a length of zero");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        if (text == null) {
            throw new java.lang.IllegalArgumentException("Inputted text is null");
        }
        List<Integer> strt = new LinkedList<>();
        if (pattern.length() > text.length()) {
            return strt;
        }
        int[] fauilureTable = buildFailureTable(pattern, comparator);
        int j = 0;
        int i = 0;
        while (i < text.length()) {
            if (i + (pattern.length() - j) > text.length()) {
                return strt;
            }
            if (comparator.compare(pattern.charAt(j), text.charAt(i)) == 0) {
                if (j == pattern.length() - 1) {
                    strt.add(i - j);
                    j = fauilureTable[j];
                    i++;
                } else {
                    j++;
                    i++;
                }
            } else if (j == 0) {
                i++;
            } else {
                j = fauilureTable[j - 1];
            }
        }
        return strt;
        throw new j
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input pattern.
     *
     * Note that a given index i will contain the length of the largest prefix
     * of the pattern indices [0..i] that is also a suffix of the pattern
     * indices [1..i]. This means that index 0 of the returned table will always
     * be equal to 0
     *
     * Ex. pattern = ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this to check if characters are equal
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("Inputted pattern is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        int[] failureTable = new int[pattern.length()];
        if (pattern.length() == 0) {
            return failureTable;
        }
        failureTable[0] = 0;
        int i = 0;
        int j = 1;
        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                i++;
                failureTable[j] = i;
                j++;
            } else {
                if (i == 0) {
                    j++;
                } else {
                    i = failureTable[i - 1];
                }
            }
        }
        return failureTable;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the buildLastTable() method before implementing
     * this method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class
     * useful.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("Inputted pattern is null");
        }
        if (pattern.length() == 0) {
            throw new java.lang.IllegalArgumentException("Inputted pattern has a length of zero");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        if (text == null) {
            throw new java.lang.IllegalArgumentException("Inputted text is null");
        }
        List<Integer> strt = new LinkedList<>();
        if (pattern.length() > text.length()) {
            return strt;
        }
        Map<Character, Integer> last = buildLastTable(pattern);
        int i = 0;
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= 0 && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j = j - 1;
            }
            if (j == -1) {
                strt.add(i);
                i++;
            } else {
                int shift = last.getOrDefault(text.charAt(i + j), -1);
                if (shift < j) {
                    i = i + j - shift;
                } else {
                    i = i + 1;
                }
            }
        }
        return strt;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("Inputted pattern is null");
        }
        Map<Character, Integer> lastOcc = new HashMap<>();
        if (pattern.length() == 0) {
            return lastOcc;
        }
        for (int i = 0; i < pattern.length(); i++) {
            lastOcc.put(pattern.charAt(i), i);
        }
        return lastOcc;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 113;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i)
     *   c is the integer value of the current character, and
     *   i is the index of the character
     *
     * We recommend building the hash for the pattern and the first m characters
     * of the text by starting at index (m - 1) to efficiently exponentiate the
     * BASE. This allows you to avoid using Math.pow().
     *
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow; you will not need to handle this case.
     * You may assume that all powers and calculations CAN be done without
     * overflow. However, be careful with how you carry out your calculations.
     * For example, if BASE^(m - 1) is a number that fits into an int, it's
     * possible for BASE^m will overflow. So, you would not want to do
     * BASE^m / BASE to calculate BASE^(m - 1).
     *
     * Ex. Hashing "bunn" as a substring of "bunny" with base 113
     * = (b * 113 ^ 3) + (u * 113 ^ 2) + (n * 113 ^ 1) + (n * 113 ^ 0)
     * = (98 * 113 ^ 3) + (117 * 113 ^ 2) + (110 * 113 ^ 1) + (110 * 113 ^ 0)
     * = 142910419
     *
     * Another key point of this algorithm is that updating the hash from
     * one substring to the next substring must be O(1). To update the hash,
     * subtract the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar as shown by this formula:
     * (oldHash - oldChar * BASE ^ (pattern.length - 1)) * BASE + newChar
     *
     * Ex. Shifting from "bunn" to "unny" in "bunny" with base 113
     * hash("unny") = (hash("bunn") - b * 113 ^ 3) * 113 + y
     *              = (142910419 - 98 * 113 ^ 3) * 113 + 121
     *              = 170236090
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^(m - 1) is for updating the hash.
     *
     * Do NOT use Math.pow() in this method.
     *
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new java.lang.IllegalArgumentException("Inputted pattern is null");
        }
        if (pattern.length() == 0) {
            throw new java.lang.IllegalArgumentException("Inputted pattern has a length of zero");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        if (text == null) {
            throw new java.lang.IllegalArgumentException("Inputted text is null");
        }
        List<Integer> strt = new LinkedList<>();
        if (pattern.length() > text.length()) {
            return strt;
        }
        int tHash = buildHash(text.subSequence(0, pattern.length()))    [0];
        int[] pHashArr = buildHash(pattern);
        int pHash = pHashArr[0];
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            if (tHash == pHash) {
                int j = 0;
                while (j < pattern.length() && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                    j++;
                }
                if (j == pattern.length()) {
                    strt.add(i);
                }
            }
            if (i < text.length() - pattern.length()) {
                tHash = updateHash(tHash, text.subSequence(i, i + 1), text.subSequence(i + pattern.length(),
                        i + 1 + pattern.length()), pHashArr[1]);
            }
        }
        return strt;
    }

    /**
     * Helper method for rabinKarp method to build initial text and pattern hash
     * @param text character sequence to be turned into hash
     * @return int hash of character sequence
     */
    private static int[] buildHash(CharSequence text) {
        int hash = 0;
        int high = 1;
        for (int i = text.length() - 1; i >= 0; i--) {
            hash += text.subSequence(i, i + 1).hashCode() * high;
            if (i != 0) {
                high *= BASE;
            }
        }
        return new int[]{hash, high};
    }

    /**
     * Helper method for the rabinKarb method to update the hashcode of the text
     * @param hash original int of hash that is to be updated
     * @param remove character to be removed from hash
     * @param add character to be added to hash
     * @param multiplier int of BASE^(pattern.length() - 1), which is the Base to the greatest exponent
     * @return updated int of hash
     */
    private static int updateHash(int hash, CharSequence remove, CharSequence add, int multiplier) {
        return (hash - remove.hashCode() * multiplier) * BASE + add.hashCode();
    }
}
