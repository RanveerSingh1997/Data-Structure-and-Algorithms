package arrays;
/*
 * Brute Force Solution:
 *
 * 1. Convert both strings into character arrays.
 * 2. Sort both character arrays.
 * 3. If lengths are different, return false.
 * 4. Compare characters at each index.
 * 5. If all characters match, return true.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
boolean isAnagram(String str1, String str2) {
    char[] chars1 = str1.toCharArray();
    char[] chars2 = str2.toCharArray();

    Arrays.sort(chars1);
    Arrays.sort(chars2);

    if (chars1.length != chars2.length) {
        return false;
    }

    for (int i = 0; i < chars1.length; i++) {
        if (chars1[i] != chars2[i]) {
            return false;
        }
    }

    return true;
}
*/

/// Strings contain only lowercase English letters (a-z).
public class Anagram {
     static boolean isAnagram(String str1, String str2){
         if(str1.length() != str2.length()){
             return  false;
         }
         int[] count = new int[26];
         for(int i=0; i<str1.length();i++){
             count[str1.charAt(i)-'a']++;
             count[str2.charAt(i)-'a']--;
         }

         for(int value: count){
           if(value!= 0){
               return false;
           }
         }
         return  true;
     }

    static void main() {
         System.out.println(isAnagram("rat","cat"));
         System.out.println(isAnagram("tea","eat"));
    }
}
