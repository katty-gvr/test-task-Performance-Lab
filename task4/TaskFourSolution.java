import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskFourSolution {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Необходимо указать путь к файлу в качестве аргумента командной строки.");
            return;
        }

        String filePath = args[0];
        List<Integer> numbers = new ArrayList<>();

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = fileReader.readLine()) != null) {
                int number = Integer.parseInt(line.trim());
                numbers.add(number); //добавляем каждый элемент в динамический массив
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Превращаем список в обычный массив
        int[] array = numbers.stream().mapToInt(i -> i).toArray();

        System.out.println(getMinMoves(array));
    }

    private static int getMinMoves(int[] nums) {
        // Сортируем массив для нахождения медианы
        Arrays.sort(nums);
        int median = nums[nums.length / 2]; // Находим медиану

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}