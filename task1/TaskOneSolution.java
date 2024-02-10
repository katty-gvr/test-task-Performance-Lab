import java.util.Scanner;

public class TaskOneSolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число элементов в массиве (n):");
        int n = scanner.nextInt();
        System.out.println("Введите длину интервала (m):");
        int m = scanner.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i + 1;
        }

        int currentPathElement = 0;
        System.out.print("Путь: ");
        do {
            System.out.print(array[currentPathElement]);
            currentPathElement = (currentPathElement + m - 1) % n;
        } while (currentPathElement != 0);
    }
}