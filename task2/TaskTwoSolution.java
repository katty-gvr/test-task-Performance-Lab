import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TaskTwoSolution {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Необходимо указать пути к двум файлам в качестве аргументов командной строки.");
            return;
        }

        String circleCoordinatesFile = args[0];
        String dotsCoordinatesFile = args[1];

        try {
            // Чтение данных из файла 1 (координаты центра окружности и его радиус)
            BufferedReader fileReader1 = new BufferedReader(new FileReader(circleCoordinatesFile));
            String[] centerCoordinates = fileReader1.readLine().split(" ");
            int centerX = Integer.parseInt(centerCoordinates[0]);
            int centerY = Integer.parseInt(centerCoordinates[1]);
            int radius = Integer.parseInt(fileReader1.readLine());
            fileReader1.close();

            // Чтение данных из файла 2 (координаты точек)
            BufferedReader fileReader2 = new BufferedReader(new FileReader(dotsCoordinatesFile));
            String line;
            while ((line = fileReader2.readLine()) != null) {
                String[] pointCoordinates = line.split(" ");
                int pointX = Integer.parseInt(pointCoordinates[0]);
                int pointY = Integer.parseInt(pointCoordinates[1]);
                int position = getPointPosition(centerX, centerY, radius, pointX, pointY);
                System.out.println(position);
            }
            fileReader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPointPosition(int centerX, int centerY, int radius, int pointX, int pointY) {
        double distance = Math.sqrt(Math.pow((pointX - centerX), 2) + Math.pow((pointY - centerY), 2));

        if (distance == radius) {
            return 0; // точка лежит на окружности
        }
        else if (distance < radius) {
            return 1; // точка внутри окружности
        } else {
            return 2; // точка снаружи (вне окружности)
        }
    }
}
