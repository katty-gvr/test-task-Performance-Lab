import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class TaskThreeSolution {
    public static void main(String[] args) throws FileNotFoundException {

        String testsFile = args[0];
        String valuesFile = args[1];
        String reportFile = args[2];

        Gson gson = new GsonBuilder().setPrettyPrinting().create(); //настройка красивой печати для json

        JsonObject valuesObject = gson.fromJson(new FileReader(valuesFile), JsonObject.class); //чтение values.json
        JsonObject testsObject = gson.fromJson(new FileReader(testsFile), JsonObject.class); // чтение tests.json

        JsonArray valuesArray = valuesObject.getAsJsonArray("values"); // извлечение массива значений
        JsonArray testsArray = testsObject.getAsJsonArray("tests"); // извлечение массива тестов

        for (int i = 0; i < testsArray.size(); i++) { // заполение соответсвующих значений для каждого теста
            JsonObject testObj = testsArray.get(i).getAsJsonObject();
            Long testId = testObj.get("id").getAsLong();
            fillValues(testObj, testId, valuesArray);
        }

        try (FileWriter file = new FileWriter(reportFile)) { // заполнение report.json
            gson.toJson(testsObject, file);
            System.out.println("Файл отчета успешно заполнен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void fillValues(JsonObject testObj, Long testId, JsonArray valuesArray) {
        for (int i = 0; i < valuesArray.size(); i++) {
            JsonObject valueObj = valuesArray.get(i).getAsJsonObject();
            Long valueId = valueObj.get("id").getAsLong();
            if (valueId.equals(testId)) {
                testObj.addProperty("value", valueObj.get("value").getAsString());
            }
        }

        JsonArray nestedValues = testObj.getAsJsonArray("values"); // проверка наличия вложенных тестов
        if (nestedValues != null) {
            for (int i = 0; i < nestedValues.size(); i++) {
                JsonObject nestedTestObj = nestedValues.get(i).getAsJsonObject();
                Long nestedTestId = nestedTestObj.get("id").getAsLong();
                fillValues(nestedTestObj, nestedTestId, valuesArray);
            }
        }
    }
}