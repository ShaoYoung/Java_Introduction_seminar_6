import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 1.
// 1. Напишите метод, который заполнит массив из 1000 элементов случайными цифрами от 0 до 24.
// 2. Создайте метод, в который передайте заполненный выше массив и с помощью Set вычислите процент уникальных значений в данном массиве и верните его в виде числа с плавающей запятой.
// Для вычисления процента используйте формулу:
// процент уникальных чисел = количество уникальных чисел * 100 / общее количество чисел в массиве.
public class Task_6_1 {
    public static void main(String[] args) {

        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }

        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            al.add((int) (Math.random() * array.length));
        }

//        al = Arrays.;
//        Set<Integer> mySet = new HashSet<T>(Arrays.asList(array));
        Set<Integer> set = new HashSet<>(al);

        System.out.printf("ArrayList %s\n", al);
        System.out.printf("set %s\n", set);
        System.out.printf("ArrayList.size %s\n",al.size());
        System.out.printf("set.size %s\n",set.size());
        System.out.printf("Уникальных элементов %s \n", (double)set.size()/(double)al.size());




    }
}
