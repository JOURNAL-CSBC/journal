package com.journal;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.nio.charset.StandardCharsets;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Додаток для редагування бази даних розкладу коледжу.
 * 
 * @SpringBootApplication - анотація для позначення головного класу Spring Boot додатку.
 * 
 * Клас реалізує CommandLineRunner для виконання коду після запуску додатку.
 * 
 * Методи:
 * - main(String[] args): запускає додаток Spring Boot.
 * - run(String... args): метод, що виконується після запуску додатку. Виводить меню для користувача.
 * - addjournalFromCsv(): додає розклад з CSV-файлу до бази даних.
 * - viewAlljournals(): виводить всі розклади з бази даних.
 * - dropAlljournals(): видаляє всі розклади з бази даних.
 * 
 * Поля:
 * - journalRepository: репозиторій для роботи з розкладами.
 * 
 * Використовує:
 * - Scanner для зчитування вводу користувача.
 * - CSVReader для зчитування даних з CSV-файлу.
 * - journal для представлення документу розкладу.
 * - journalRepository для взаємодії з базою даних.
 */
@SpringBootApplication
public class JournalApplication implements CommandLineRunner {

    @Autowired
    private JournalRepository journalRepository;

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати розклад з CSV-файлу");
            System.out.println("2. Подивитись розклад");
            System.out.println("3. Видалити розклад");
            System.out.println("4. Вихід");
            System.out.print("Введіть номер команди (1-4): ");
            if (!scanner.hasNextInt()) {
                String invalidInput = scanner.nextLine();
                System.out.println("Некоректне введення \"" + invalidInput + "\". Введіть число від 1 до 4.");
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addjournalFromCsv();
                    break;
                case 2:
                    viewAlljournals();
                    break;
                case 3:
                    dropAlljournals();
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Номер команди некоректний. Спробуй ще.");
            }
        }
    }

    private void addjournalFromCsv() {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
            getClass().getClassLoader().getResourceAsStream("journal.csv"),
            StandardCharsets.UTF_8)
        )) {
            List<String[]> records = reader.readAll();
            
            records.remove(0); // Видалити перший рядок з назвами стовпців

            List<Journal> journalList = new ArrayList<>();
            for (String[] record : records) {
                Journal journal = new Journal(
                    record[0], // studentFirstName
                    record[1], // studentLastName
                    record[2], // teacherFirstName
                    record[3], // teacherLastName
                    record[4], // courseName
                    record[5], // departmentName
                    record[6], // roomNumber
                    Double.parseDouble(record[7]), // semester
                    record[8], // year
                    record[9] // startTime
                );

                journalList.add(journal);
            }
            
            // Очистити існуючий розклад, щоб уникнути дублювання при повторному імпорті
            journalRepository.deleteAll();
            journalRepository.saveAll(journalList);
            System.out.println(journalList.size() + " документів з рядками з розкладу завантажено з CSV.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не вдалось завантажити рядок розкладу з CSV.");
        }
    }

    private void viewAlljournals() {
        List<Journal> journals = journalRepository.findAll();
        if (journals.isEmpty()) {
            System.out.println("Документи з рядками розкладу не знайдено.");
        } else {
            System.out.println("Знайдено " + journals.size() + " документів розкладу:");
            journals.forEach(j -> System.out.println(j.toString()));
        }
    }

    private void dropAlljournals() {
        journalRepository.deleteAll();
        System.out.println("Розклад видалено.");
    }
}