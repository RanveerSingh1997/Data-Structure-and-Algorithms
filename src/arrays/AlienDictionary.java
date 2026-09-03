package arrays;

import java.util.HashMap;
import java.util.Map;

/// [...](https://leetcode.com/problems/verifying-an-alien-dictionary/description/)
public class AlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (j >= words[i + 1].length()) {
                    return false;
                }

                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    int currentLetter = orderMap.get(words[i].charAt(j));
                    int nextLetter = orderMap.get(words[i + 1].charAt(j));
                    if (nextLetter < currentLetter) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        AlienDictionary solver = new AlienDictionary();

        String[] words1 = {"hello", "leetcode"};
        String order1 = "hlabcdefgijkmnopqrstuvwxyz";
        System.out.println("Test 1 (Expected true): " + solver.isAlienSorted(words1, order1));

        String[] words2 = {"word", "world", "row"};
        String order2 = "worldabcefghijkmnpqstuvxyz";
        System.out.println("Test 2 (Expected false): " + solver.isAlienSorted(words2, order2));

        String[] words3 = {"apple", "app"};
        String order3 = "abcdefghijklmnopqrstuvwxyz";
        System.out.println("Test 3 (Expected false): " + solver.isAlienSorted(words3, order3));
    }
}
