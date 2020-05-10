package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    Scanner scanner = new Scanner(System.in);
    private static List<List<String>> table;
    private static String[] arrayFileStudent;
    private static String[] arrayFileMarks;
    private static String[] arrayFileSubject;
    private static Map<Integer, Integer> nameAndSubject1;
    private static Map<Integer, Integer> nameAndSubject2;
    private static Map<Integer, String> nameAndNumber;
    private static Map<Integer, String> subjectAndNumber;

    public static void main(String[] args) throws IOException {
        String studentPath = "C:\\Users\\eislo\\OneDrive\\Desktop\\Final Work\\student.txt";
        String marksPath = "C:\\Users\\eislo\\OneDrive\\Desktop\\Final Work\\marks.txt";
        String subjectPath = "C:\\Users\\eislo\\OneDrive\\Desktop\\Final Work\\subject.txt";
        BufferedReader inStudent = new BufferedReader(new FileReader(studentPath));
        BufferedReader inMarks = new BufferedReader(new FileReader(marksPath));
        BufferedReader inSubject = new BufferedReader(new FileReader(subjectPath));
        String strForStudent, strForMarks, StrForSubject;
        List<String> listStudent = new ArrayList<>();
        List<String> listMarks = new ArrayList<>();
        List<String> listSubject = new ArrayList<>();
        while((strForStudent = inStudent.readLine()) != null)
            listStudent.add(strForStudent);
        while((strForMarks = inMarks.readLine()) != null)
            listMarks.add(strForMarks);
        while((StrForSubject = inSubject.readLine()) != null)
            listSubject.add(StrForSubject);
        arrayFileStudent = listStudent.toArray(new String[0]);
        arrayFileMarks = listMarks.toArray(new String[0]);
        arrayFileSubject = listSubject.toArray(new String[0]);
        String[] splitArrayFileStudent;
        String[] splitArrayFileMarks;
        String[] splitArrayFileSubject;
        List<String> fullNames = new ArrayList<>(); fullNames.add("");
        nameAndNumber = new HashMap<>();
        subjectAndNumber = new HashMap<>();
        nameAndSubject1 = new HashMap<>();
        nameAndSubject2 = new HashMap<>();

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

        for(int i = 0; i < arrayFileSubject.length; i++){
            splitArrayFileSubject = arrayFileSubject[i].split("\\s+");
            int count = i + 1;
            String str = splitArrayFileSubject[1];
            subjectAndNumber.put(count, str);
        }

        for(int i = 0; i < arrayFileMarks.length; i++){
            splitArrayFileMarks = arrayFileMarks[i].split("\\s+");
            String str = splitArrayFileMarks[0]+""+splitArrayFileMarks[1];
            String answer = splitArrayFileMarks[2];
            int count = i+1;
            nameAndSubject1.put(Integer.parseInt(str), Integer.parseInt(answer));
            nameAndSubject2.put(count, Integer.parseInt(str));
        }

        List<String> row1 = Arrays.asList("Rus",
                determineMarks(fullNames.get(1), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(1)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(1))
        );
        List<String> row2 = Arrays.asList("Mat",
                determineMarks(fullNames.get(1), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(2))
        );
        List<String> row3 = Arrays.asList("Phy",
                determineMarks(fullNames.get(1), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(2))
        );
        List<String> row4 = Arrays.asList("Lit",
                determineMarks(fullNames.get(1), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(2), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(3), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(4), subjectAndNumber.get(2)),
                determineMarks(fullNames.get(5), subjectAndNumber.get(2))
        );
        table = Arrays.asList(fullNames, row1,row2, row3, row4);


        int spacing = 3;
        printTable(spacing);
        determineMarks(fullNames.get(1), subjectAndNumber.get(1));
    }
    public static String determineMarks(String numOfMan, String numOfSubject){
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
    static char findFirstLetter(String str) {
        for (int i = 0; i < str.length(); i++)
            if (Character.isUpperCase(str.charAt(i)))
                return str.charAt(i);
        return 0;
    }


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
