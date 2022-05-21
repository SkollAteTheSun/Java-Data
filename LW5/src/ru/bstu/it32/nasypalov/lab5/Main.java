package ru.bstu.it32.nasypalov.lab5;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.println(" _____________________________\n" +
                "|                             |\n" +
                "|            Меню:            |\n" +
                "|    _____________________    |\n" +
                "|                             |\n" +
                "|             XML             |\n" +
                "|    _____________________    |\n" +
                "|1 - Просмотреть XML          |\n" +
                "|2 - Добавить в XML           |\n" +
                "|3 - Редактировать по ID в XML|\n" +
                "|4 - Поиск XML                |\n" +
                "|5 - Удаление XML             |\n" +
                "|6 - Конвертация XML в SQL    |\n" +
                "|    _____________________    |\n" +
                "|                             |\n" +
                "|           MySQL             |\n" +
                "|    _____________________    |\n" +
                "|7 - Вывод SQL                |\n" +
                "|8 - Добавление данных SQL    |\n" +
                "|9 - Редактирование данных SQL|\n" +
                "|10 - Поиск SQL               |\n" +
                "|11 - Удаление данных SQL     |\n" +
                "|12 - Конвертация SQL в XML   |\n" +
                "|    _____________________    |\n" +
                "|                             |\n" +
                "|           Опции             |\n" +
                "|    _____________________    |\n" +
                "|                             |\n" +
                "|         0 - Выход           |\n" +
                "|_____________________________|\n");
        int n = sc.nextInt();
        MySQLParser mySQLParser = new MySQLParser();
        XMLParser xmlParser = new XMLParser();
        switch (n){
            case 0:
                break;
            case 1:
                xmlParser.parseXML();
                break;
            case 2: {
                System.out.println("Марку, Модель, Цвет, Гос. Номер, Имя, Фамилию, Отчество Владельца:");
                String Brand = sc.next();
                String Model = sc.next();
                String Color = sc.next();
                String Plate = sc.next();
                String FirstName = sc.next();
                String SecondName = sc.next();
                String MiddleName = sc.next();
                xmlParser.writeXML(Brand, Model, Color, Plate, FirstName, SecondName, MiddleName);
                break;
            }
            case 3: {
                System.out.println("Введите id записи и новые данные(Марку, Модель, Цвет, Гос. Номер, Имя, Фамилию, Отчество Владельца):");
                int id = sc.nextInt();
                String Brand = sc.next();
                String Model = sc.next();
                String Color = sc.next();
                String Plate = sc.next();
                String FirstName = sc.next();
                String SecondName = sc.next();
                String MiddleName = sc.next();
                xmlParser.editXML(id, Brand, Model, Color, Plate, FirstName, SecondName, MiddleName);
                break;
            }
            case 4: {
                System.out.println("Поиск по параметрам(0, если параметр не нужен):");
                String[] params = new String[7];
                String temp;
                for (int i = 0; i < 7; i++) {
                    switch (i) {
                        case 0:
                            System.out.println("Марка = ");
                            break;
                        case 1:
                            System.out.println("Модель = ");
                            break;
                        case 2:
                            System.out.println("Цвет = ");
                            break;
                        case 3:
                            System.out.println("Гос. Номер = ");
                            break;
                        case 4:
                            System.out.println("Имя = ");
                            break;
                        case 5:
                            System.out.println("Фамилия = ");
                            break;
                        case 6:
                            System.out.println("Отчество = ");
                            break;
                    }
                    temp = sc.next();
                    params[i] = temp;
                }
                xmlParser.findXML(params);
                break;
            }
            case 5:
                xmlParser.deleteXML(4);
                break;
            case 6:
                xmlParser.convertToSQL();
                break;
            case 7:
                mySQLParser.selectSQL();
                break;
            case 8:{
                System.out.println("Добавление данных:");
                String[] params = new String[7];
                String temp;
                for (int i = 0; i < 7; i++) {
                    switch (i) {
                        case 0:
                            System.out.println("Марка = ");
                            break;
                        case 1:
                            System.out.println("Модель = ");
                            break;
                        case 2:
                            System.out.println("Цвет = ");
                            break;
                        case 3:
                            System.out.println("Гос. Номер = ");
                            break;
                        case 4:
                            System.out.println("Имя = ");
                            break;
                        case 5:
                            System.out.println("Фамилия = ");
                            break;
                        case 6:
                            System.out.println("Отчество = ");
                            break;
                    }
                    temp = sc.next();
                    params[i] = temp;
                }
                mySQLParser.insertSQL(params);
                break;
            }
            case 9:
            {
                System.out.println("Редактирование по ID");
                System.out.println("ID = ");
                int id = sc.nextInt();
                String[] params = new String[7];
                String temp;
                for (int i = 0; i < 7; i++) {
                    switch (i) {
                        case 0:
                            System.out.println("Марка = ");
                            break;
                        case 1:
                            System.out.println("Модель = ");
                            break;
                        case 2:
                            System.out.println("Цвет = ");
                            break;
                        case 3:
                            System.out.println("Гос. Номер = ");
                            break;
                        case 4:
                            System.out.println("Имя = ");
                            break;
                        case 5:
                            System.out.println("Фамилия = ");
                            break;
                        case 6:
                            System.out.println("Отчество = ");
                            break;
                    }
                    temp = sc.next();
                    params[i] = temp;
                }
                mySQLParser.updateSQL(id, params);
                break;
            }
            case 10:{
                System.out.println("Поиск по параметрам(0, если параметр не нужен):");
                String[] params = new String[7];
                String temp;
                System.out.println("ID = ");
                int id = sc.nextInt();
                for (int i = 0; i < 7; i++) {
                    switch (i) {
                        case 0:
                            System.out.println("Марка = ");
                            break;
                        case 1:
                            System.out.println("Модель = ");
                            break;
                        case 2:
                            System.out.println("Цвет = ");
                            break;
                        case 3:
                            System.out.println("Гос. Номер = ");
                            break;
                        case 4:
                            System.out.println("Имя = ");
                            break;
                        case 5:
                            System.out.println("Фамилия = ");
                            break;
                        case 6:
                            System.out.println("Отчество = ");
                            break;
                    }
                    temp = sc.next();
                    params[i] = temp;
                }
                mySQLParser.findSQL(id, params);
                break;
            }
            case 11:
            {
                System.out.println("Удаление данных по ID:");
                System.out.println("ID = ");
                int id = sc.nextInt();
                mySQLParser.deleteSQL(id);
                break;
            }
            case 12:
                mySQLParser.convertToXML();
                break;
        }
    }
}
