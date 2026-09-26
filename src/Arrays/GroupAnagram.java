package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagram {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"act", "pots", "tops", "cat", "stop", "hat"}));
        System.out.println(groupAnagrams(new String[]{"x"}));
        System.out.println(groupAnagrams(new String[]{""}));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String str = Arrays.toString(chars);
            List<String> values = anagrams.getOrDefault(str, new ArrayList<>());
            values.add(s);
            anagrams.put(str, values);
        }
        return new ArrayList<>(anagrams.values());
    }
}
