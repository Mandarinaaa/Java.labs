import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) { //метод main получает строку, делит её на слова и узнает является ли слово палиндромом
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] words = s.split(" ");
        for (String word : words) {
            System.out.println(isPalindrome(word));
        }
        //System.out.println(isPalindrome(s));
         }
    public static String reverseString(String s){ // метод reverseString переворачивает слово
        String r = "";
        for (int i = s.length()-1; i >= 0; --i){
            r += s.charAt(i);
        }
        return r;
    }
    public static boolean isPalindrome(String s) { // метод isPalindrome проверяет совпадают ли исходное слово и перевернутое
        String b = reverseString(s);
        return s.equals(b);

    }

}
