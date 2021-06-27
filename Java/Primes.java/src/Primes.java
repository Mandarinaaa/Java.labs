public class Primes {
    public static void main(String[] args) { //создаем метод main, будет выводить все простые числа
        for (int n = 1; n<100; n++){         //запускаем цикл
            if (isPrime(n)) {                //вызываем метод isPrime и проверяем является ли число простым
                System.out.print(n+" ");     //выводим простое число
            }
        }
    }
    public static boolean isPrime(int n)     //создаем метод isPrime, проверяет является ли число простым
    {
        for (int j = 2; j<n; j++){           //запускаем цикл от 2 до проверяемого числа
            if (n%j == 0)                    //проверяем есть ли остаток от деления
                return false;                //остаток есть, возвращаем false
        }
        return true;                         //остатка нет, число простое, возвращаем true
    }
}
