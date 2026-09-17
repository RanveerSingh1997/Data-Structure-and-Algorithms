package Arrays;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("Was it a car or a cat I saw?"));
        System.out.println(isPalindrome("tab a cat"));
        System.out.println(isPalindrome(""));
    }

//    static boolean isPalindrome(String string) {
//        int left = 0;
//        int right = string.length() - 1;
//        char[] s = string.toCharArray();
//        while (left < right) {
//            if (s[left] != s[right]) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        System.out.println(left);
//        System.out.println(right);
//        return true;
//    }

    static boolean isPalindrome(String string) {
        int left = 0;
        int right = string.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(string.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(string.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(string.charAt(left)) != Character.toLowerCase(string.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
