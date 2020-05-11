package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
// Здрствуйте, Дмитрий Андреевич, Специально для вас сделал коменнтарии
public class Main {
    //Обьявлиния листа которые будет показывать табоицу
    private static List<List<String>> table;
    //Массив String для определиния строк в файле с оценками
    private static String[] arrayFileMarks;
    // Это две мапы для получение оценок
    private static Map<Integer, Integer> nameAndSubject1;
    private static Map<Integer, Integer> nameAndSubject2;
    //Это мапа для хранения имен
    private static Map<Integer, String> nameAndNumber;
    //Это мапа для хранения предметов
    private static Map<Integer, String> subjectAndNumber;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //Три стринги путей к файлам, для проверки можете указать свои пути
        String studentPath = "C:\\Users\\eislo\\OneDrive\\Desktop\\Final Work\\student.txt";
        String marksPath = "C:\\Users\\eislo\\OneDrive\\Desktop\\Final Work\\marks.txt";
        String subjectPath = "C:\\Users\\eislo\\OneDrive\\Desktop\\Final Work\\subject.txt";
        //Три BufferedReader, чтобы считать текст с файлов
        BufferedReader inStudent = new BufferedReader(new FileReader(studentPath));
        BufferedReader inMarks = new BufferedReader(new FileReader(marksPath));
        BufferedReader inSubject = new BufferedReader(new FileReader(subjectPath));
        //Три болваники
        String strForStudent, strForMarks, StrForSubject;
        //Три list для хранения файлов
        List<String> listStudent = new ArrayList<>();
        List<String> listMarks = new ArrayList<>();
        List<String> listSubject = new ArrayList<>();
        //Получинеи лайнов
        while((strForStudent = inStudent.readLine()) != null)
            listStudent.add(strForStudent);
        while((strForMarks = inMarks.readLine()) != null)
            listMarks.add(strForMarks);
        while((StrForSubject = inSubject.readLine()) != null)
            listSubject.add(StrForSubject);
        //Добавляем все в массивы
        String[] arrayFileStudent = listStudent.toArray(new String[0]);
        String[] arrayFileSubject = listSubject.toArray(new String[0]);
        arrayFileMarks = listMarks.toArray(new String[0]);
        //Массивы стрингов для каждого слова с файла
        String[] splitArrayFileStudent;
        String[] splitArrayFileMarks;
        String[] splitArrayFileSubject;
        //List куда добавляем имена
        List<String> fullNames = new ArrayList<>(); fullNames.add("");
        //Объявляем HashMaps
        nameAndNumber = new HashMap<>();
        subjectAndNumber = new HashMap<>();
        nameAndSubject1 = new HashMap<>();
        nameAndSubject2 = new HashMap<>();
        //Получаем имена
        for(int i = 0; i < arrayFileStudent.length; i++){
            splitArrayFileStudent = arrayFileStudent[i].split("\\s+");
            String surname = splitArrayFileStudent[1];
            String name = splitArrayFileStudent[2];
            String patronymic = splitArrayFileStudent[3];
            String finalName =
                    surname+" " +
                    findFirstLetter(name) + "." +
                    findFirstLetter(patronymic)+ ".";
            nameAndNumber.put(i + 1, finalName);
            fullNames.add(finalName);
        }
        //Получаем предметы
        for(int i = 0; i < arrayFileSubject.length; i++){
            splitArrayFileSubject = arrayFileSubject[i].split("\\s+");
            int count = i + 1;
            String str = splitArrayFileSubject[1];
            subjectAndNumber.put(count, str);
        }
        //Обрабытваем оценки
        for(int i = 0; i < arrayFileMarks.length; i++){
            splitArrayFileMarks = arrayFileMarks[i].split("\\s+");
            String str = splitArrayFileMarks[0]+""+splitArrayFileMarks[1];
            String answer = splitArrayFileMarks[2];
            int count = i+1;
            nameAndSubject1.put(Integer.parseInt(str), Integer.parseInt(answer));
            nameAndSubject2.put(count, Integer.parseInt(str));
        }
        //Объявляем столбы
        List<String> row1 = Arrays.asList(subjectAndNumber.get(1),
                determineMarks(fullNames.get(1), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(1))
        );
        List<String> row2 = Arrays.asList(subjectAndNumber.get(2),
                determineMarks(fullNames.get(1), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(2))
        );
        List<String> row3 = Arrays.asList(subjectAndNumber.get(3),
                determineMarks(fullNames.get(1), subjectAndNumber.get(3)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(3)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(3)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(3)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(3))
        );
        List<String> row4 = Arrays.asList(subjectAndNumber.get(4),
                determineMarks(fullNames.get(1), subjectAndNumber.get(4)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(4)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(4)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(4)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(4))
        );
        //Определяем таблицу
        table = Arrays.asList(fullNames, row1,row2, row3, row4);
        //Установка отсупа
        int spacing = 3;
        //Build таблицы
        printTable(spacing);
        //Получение среднего
        System.out.println("Ну, что бандит. Оценочки Оценочками, но предмет твой я не знаю, так что скажи мне его.");
        System.out.println("Чтобы я сказал тебе твой средний балл.");
        System.out.println("Вот названия предметов: Rus; Math; Phy; Lit");
        String answer = scanner.nextLine();
        int commonCount = arrayFileStudent.length;
        int commonCountDouble = arrayFileStudent.length;
        switch (answer){
            case "Rus":
                System.out.println("Вот тебе твой средний балл.");
                double averageRus = 0;
                for(int i = 0; i < commonCount; i++)
                    averageRus += Double.valueOf(determineMarks(fullNames.get(i+1), subjectAndNumber.get(1)));
                System.out.println("Rus: "+ averageRus/commonCountDouble);
                break;
            case "Math":
                System.out.println("Вот тебе твой средний балл.");
                double averageMath = 0;
                for(int i = 0; i < commonCount; i++)
                    averageMath += Double.valueOf(determineMarks(fullNames.get(i+1), subjectAndNumber.get(2)));
                System.out.println("Math: "+ averageMath/commonCountDouble);
                break;
            case "Phy":
                System.out.println("Вот тебе твой средний балл.");
                double averagePhy = 0;
                for(int i = 0; i < commonCount; i++)
                    averagePhy += Double.valueOf(determineMarks(fullNames.get(i+1), subjectAndNumber.get(3)));
                System.out.println("Phy: "+ averagePhy/commonCountDouble);
                break;
            case "Lit":
                System.out.println("Вот тебе твой средний балл.");
                double averageLit = 0;
                for(int i = 0; i < commonCount; i++)
                    averageLit += Double.valueOf(determineMarks(fullNames.get(i+1), subjectAndNumber.get(4)));
                System.out.println("Lit: "+ averageLit/commonCountDouble);
                break;
            default:
                System.out.println("Ты что дурак, не знаешь свои предметы");
                System.out.println("Аааа, запусти програму заново и напиши правильно свой предмет");
                System.out.println("Пусть это будет для тебя уроком!!!");
                break;
        }
    }
    //Функция получения оценок
    private static String determineMarks(String numOfMan, String numOfSubject){
        final int[] interimName = new int[1];
        final int[] interimSubject = new int[1];
        nameAndNumber.forEach((key, value) -> {
            if (value.equals(numOfMan)) {
                interimName[0] = key;
            }
        });
        subjectAndNumber.forEach((key, value) -> {
            if (value.equals(numOfSubject)) {
                interimSubject[0] = key;
            }
        });
        String finalStr = interimName[0]+""+interimSubject[0];
        final int[] answer = new int[1];
        int finalAnswer = Integer.parseInt(finalStr);
        for(int i = 0; i < arrayFileMarks.length; i++){
            if(finalAnswer == nameAndSubject2.get(i+1)){
                answer[0] = nameAndSubject1.get(finalAnswer);
            }
        }
       return String.valueOf(answer[0]);
    }
    //Поиск первой буквы
    private static char findFirstLetter(String str) {
        for (int i = 0; i < str.length(); i++)
            if (Character.isUpperCase(str.charAt(i)))
                return str.charAt(i);
        return 0;
    }
    //Функция билда
    private static void printTable(int spacing) {
        List<Integer> maxLengths = findMaxLengths();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.get(0).size(); i++) {
            for (int j = 0; j < table.size(); j++) {
                String currentValue = table.get(j).get(i);
                sb.append(currentValue);
                for (int k = 0; k < (maxLengths.get(j) - currentValue.length() + spacing); k++) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
    //Поиск максисальной длины
    private static List<Integer> findMaxLengths() {
        List<Integer> maxLengths = new ArrayList<>();
        for (List<String> row : table) {
            int maxLength = 0;
            for (String value : row) {
                if (value.length() > maxLength) {
                    maxLength = value.length();
                }
            }
            maxLengths.add(maxLength);
        }
        return maxLengths;
    }
}
