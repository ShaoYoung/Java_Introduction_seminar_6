// ДЗ. Итоговый проект
//Создать класс Ноутбук для магазина техники.
//Добавить атрибуты класса с соответствующими типами.
//Например,
//- идентификатор
//- производитель
//- название модели
//- RAM
//- объем HD
//- операционная система
//- и т.д.
//Добавить конструктор класса.
//Добавить необходимые методы класса.
//Создать множество ноутбуков (множество объектов класса ноутбук).
//3-10 штук
//Написать метод, который будет запрашивать у пользователя критерий фильтрации (один) и выведет ноутбуки, отвечающие критерию.
//Например:
//“Введите цифру, соответствующую необходимому критерию:
//1 - RAM
//2 - Объем HD
//3 - Операционная система
//4 - Цвет …
//Для критериев объем и память необходимо запросить дополнительно диапазон.
//Например,
//"Введите минимальное значение"
//"Введите максимальное значение"
//Отфильтровать ноутбуки и вывести проходящие по критерию.
//Пример вывода:
//Ноутбуки с объем HD от 256 до 512
//1 HP EliteBook...
//2..
//3...
//
//Пример вывода:
//Ноутбуки с цвет Серебристый
//1 Honor ...
//2..
//3...

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SolutionHW_6_1 {

    //    Чтение из файла. На выходе StringBuilder
    public static StringBuilder loadFromFile(String fileName) {
        StringBuilder fileContent = new StringBuilder();
        try (FileReader fr = new FileReader(fileName)) {
            // читаем посимвольно
            int symb;
//            пока не конец файла
            while ((symb = fr.read()) != -1) {
                fileContent.append((char) symb);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fileContent;
    }

    public static Set<Notebook> getSetNotebooks(StringBuilder sbNotebooks) {

        Set<Notebook> dbNotebooks = new HashSet<>();
        //        преобразуем StringBuilder в String, затем разбиваем её. Разделитель "},{"
        String[] strNotebooks = sbNotebooks.toString().split("},\\{");
        for (String notebook : strNotebooks) {
//            удаляем лишние символы (", [, ], {, })
            notebook = notebook.replace("\"", "");
            if (notebook.contains("[")) {
                notebook = notebook.replace("[", "");
            }
            if (notebook.contains("]")) {
                notebook = notebook.replace("]", "");
            }
            if (notebook.contains("{")) {
                notebook = notebook.replace("{", "");
            }
            if (notebook.contains("}")) {
                notebook = notebook.replace("}", "");
            }
            dbNotebooks.add(getNotebook(notebook));
        }
        return dbNotebooks;
    }

    // создаёт экземпляр класса Notebook
    public static Notebook getNotebook(String strNote) {
        Map<String, String> db = new HashMap<>();
        // разбиваем на параметры. Разделитель ",".
        String[] parameters = strNote.split(",");
        for (String parameter : parameters) {
//            делим параметр по ":"
            String[] pair = parameter.split(":");
//            записываем в Map
            db.put(pair[0], pair[1]);
        }
//        System.out.println("----------------");
//        System.out.println(strNote);
//        System.out.println(db);
        Notebook notebook = new Notebook(db);
//        System.out.printf("id %s\n", notebook.id);
        return notebook;
    }

    //    печать всех моделей ноутбуков с параметрами
    public static void viewAll(Set<Notebook> notebooks) {
        for (Notebook notebook : notebooks) {
            System.out.println(notebook.toString(notebook.id));
        }
    }


    public static void viewSelected(Set<Notebook> notebooks) {
        System.out.println("Могу предложить следующие модели ноутбуков:");
        for (Notebook notebook : notebooks) {
            System.out.println(notebook.selectedToString(notebook.id));
        }
    }


    //    получаем фильтры поиска от пользователя
    public static Map<String, String> getFilter() {
        Map<String, String> filter = new HashMap<>();
        Scanner iScanner = new Scanner(System.in);
//        while (true) {
        System.out.println("Введите цифру, соответствующую необходимому критерию, или нажмите Enter для выхода:");
        System.out.println("1-Бренд");
        System.out.println("2-Модель");
        System.out.println("3-Цвет");
        System.out.println("4-Операционная система");
        System.out.println("5-RAM");
        System.out.println("6-HDD");
        System.out.println("7-Вес");
        System.out.println("8-Цена");
        String str = iScanner.nextLine();
        String filterName = "";
        String min = "";
        String max = "";
        int intNumber;
        double doubleNumber;
        String range = "";
        switch (str) {
            case "":
                break;
            case "1":
                System.out.print("Введите наименование бренда:");
                filterName = iScanner.nextLine();
                filter.put("name", "бренд");
                filter.put(filter.get("name"), filterName.toLowerCase());
                break;
            case "2":
                System.out.print("Введите модель ноутбука:");
                filterName = iScanner.nextLine();
                filter.put("name", "модель");
                filter.put(filter.get("name"), filterName.toLowerCase());
                break;
            case "3":
                System.out.print("Введите цвет:");
                filterName = iScanner.nextLine();
                filter.put("name", "цвет");
                filter.put(filter.get("name"), filterName.toLowerCase());
                break;
            case "4":
                System.out.print("Введите операционную систему:");
                filterName = iScanner.nextLine();
                filter.put("name", "операционная система");
                filter.put(filter.get("name"), filterName.toLowerCase());
                break;
            case "5":
                try {
                    System.out.print("Введите минимальное значение RAM (целое число):");
                    min = iScanner.nextLine();
                    intNumber = Integer.parseInt(min);
                    System.out.print("Введите максимальное значение RAM (целое число):");
                    max = iScanner.nextLine();
                    intNumber = Integer.parseInt(max);
                    filter.put("name", "RAM");
                    range = "от " + min + " до " + max + " Гб";
                    filter.put(filter.get("name"), range);
                    filter.put("min", min);
                    filter.put("max", max);
                    break;
                } catch (Exception e) {
                    System.out.println("Не корректный ввод числа.");
                    break;
                }
            case "6":
                try {
                    System.out.print("Введите минимальное значение HDD (целое число):");
                    min = iScanner.nextLine();
                    intNumber = Integer.parseInt(min);
                    System.out.print("Введите максимальное значение HDD (целое число):");
                    max = iScanner.nextLine();
                    intNumber = Integer.parseInt(max);
                    filter.put("name", "HDD");
                    range = "от " + min + " до " + max + " Гб";
                    filter.put(filter.get("name"), range);
                    filter.put("min", min);
                    filter.put("max", max);
                    break;
                } catch (Exception e) {
                    System.out.println("Не корректный ввод числа.");
                    break;
                }
            case "7":
                try {
                    System.out.print("Введите максимальный вес (можно дробное число):");
                    max = iScanner.nextLine();
                    doubleNumber = Double.parseDouble(max);
                    filter.put("name", "вес");
                    range = "до " + max + " кг.";
                    filter.put(filter.get("name"), range);
                    filter.put("min", "0");
                    filter.put("max", max);
                    break;
                } catch (Exception e) {
                    System.out.println("Не корректный ввод числа.");
                    break;
                }
            case "8":
                try {
                    System.out.print("Введите минимальную цену (целое число):");
                    min = iScanner.nextLine();
                    intNumber = Integer.parseInt(min);
                    System.out.print("Введите максимальную цену (целое число):");
                    max = iScanner.nextLine();
                    intNumber = Integer.parseInt(max);
                    filter.put("name", "цена");
                    range = "от " + min + " до " + max + " руб.";
                    filter.put(filter.get("name"), range);
                    filter.put("min", min);
                    filter.put("max", max);
                    break;
                } catch (Exception e) {
                    System.out.println("Не корректный ввод числа.");
                    break;
                }
            default:
                break;
        }
        return filter;
    }


    public static void main(String[] args) {

        String fileName = "notebooks.json";
        StringBuilder sbNotebooks = loadFromFile(fileName);
//        System.out.printf("SB из файла %s\n", sbNotebooks);

        Set<Notebook> notebooks = getSetNotebooks(sbNotebooks);

        Scanner iScanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Введите действие (Enter - завершение программы, 1 - вывод всех моделей ноутбуков, 2 - ввод фильтра поиска ноутбука): ");
            String str = iScanner.nextLine();
            if (str.equals("")) {
                break;
            } else if (str.equals("1")) {
                viewAll(notebooks);
            } else if (str.equals("2")) {
                Map<String, String> filter = getFilter();
                if (filter.size() != 0) {
                    Set<Notebook> selectedNotebooks = Notebook.getNotebooksByFilter(notebooks, filter);
                    System.out.printf("Ноутбуки с %s %s:\n", filter.get("name"), filter.get(filter.get("name")));
                    if (selectedNotebooks.size() > 0) viewSelected(selectedNotebooks);
                    else System.out.println("В нашем магазине таковых не имеем!");
                } else System.out.println("Вы ничего не выбрали.");
            } else System.out.println("Я вас не понял, повторите ввод.");
        }
        iScanner.close();
    }
}
