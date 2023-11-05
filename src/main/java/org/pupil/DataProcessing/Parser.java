package org.pupil.DataProcessing;

//интерпретация входных данных из csv файла и определение их типа (имя, группа, оценки и тд)
// по их расположению в строке csv файла
public class Parser {

    // возвращаем оценки как массив строк
    public String[] getRatingFromCSVLine(String[] csvLine) {
        String[] rating = new String[6];
        System.arraycopy(csvLine, 4, rating, 0, rating.length);
        return rating;
    }

    public String getLastNameFromCSVLine(String[] csvLine) {
        return csvLine[0];
    }

    public String getNameFromCSVLine(String[] csvLine) {
        return csvLine[1];
    }

    public int getAgeFromCSVLine(String[] csvLine) {
        return Integer.parseInt(csvLine[2]);
    }

    public int getGroupFromCSVLine(String[] csvLine) {
        return Integer.parseInt(csvLine[3]);
    }
}
