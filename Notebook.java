import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Notebook {

    //    поля класса Notebook
    int id, ram, hdd, price;
    double weight;
    String brand, model, colour, os;

    //    конструктор класса Notebook. на входе HashMap с параметрами
    public Notebook(Map<String, String> dbMap) {
        this.id = Integer.parseInt(dbMap.get("id"));
        this.brand = dbMap.get("brand");
        this.model = dbMap.get("model");
        this.colour = dbMap.get("colour");
        this.os = dbMap.get("os");
        this.ram = Integer.parseInt(dbMap.get("ram"));
        this.hdd = Integer.parseInt(dbMap.get("hdd"));
        this.weight = Double.parseDouble(dbMap.get("weight"));
        this.price = Integer.parseInt(dbMap.get("price"));
    }

    // String экземпляра класса Notebook
    public String toString(int number) {
        return "Ноутбук номер: " + number + "\n" +
                "Бренд: " + brand + "\n" +
                "Модель: " + model + "\n" +
                "Цвет: " + colour + "\n" +
                "Операционная система: " + os + "\n" +
                "RAM: " + ram + " Gb" + "\n" +
                "HDD: " + hdd + " Gb" + "\n" +
                "Вес: " + weight + " kg" + "\n" +
                "Цена: " + price + " Rub" + "\n";
    }

    // String экземпляра класса Notebook
    public String selectedToString(int number) {
        return brand + " " + model + " всего за " + price + " рублей";
    }


    //    подбор ноутбука по фильтру. на входе HashSet ноутбуков и HashMap фильтров
    public static Set<Notebook> getNotebooksByFilter(Set<Notebook> notebooks, Map<String, String> filters) {
        Set<Notebook> result = new HashSet<>();
//        наименование фильтра
        String nameOfFilter = filters.get("name");
//        фильтр
        String filter = filters.get(nameOfFilter);
//        ищем в множестве ноутбуков те экземпляры, которые удовлетворяют фильтру
        for (Notebook notebook : notebooks) {
            switch (nameOfFilter) {
//                brand, model, colour, os - по совпадению значения соответствующего ключа
                case "бренд":
                    if (Objects.equals(notebook.brand.toLowerCase(), filter)) {
                        result.add(notebook);
                    }
                    break;
                case "модель":
                    if (Objects.equals(notebook.model.toLowerCase(), filter)) {
                        result.add(notebook);
                    }
                    break;
                case "цвет":
                    if (Objects.equals(notebook.colour.toLowerCase(), filter)) {
                        result.add(notebook);
                    }
                    break;
                case "операционная система":
                    if (Objects.equals(notebook.os.toLowerCase(), filter)) {
                        result.add(notebook);
                    }
                    break;
//                    ram, hdd, weight, price - по диапазону [min, max]
                case "RAM":
                    int minRam = Integer.parseInt(filters.get("min"));
                    int maxRam = Integer.parseInt(filters.get("max"));
                    if (notebook.ram >= minRam & notebook.ram <= maxRam) {
                        result.add(notebook);
                    }
                    break;
                case "HDD":
                    int minHdd = Integer.parseInt(filters.get("min"));
                    int maxHdd = Integer.parseInt(filters.get("max"));
                    if (notebook.hdd >= minHdd & notebook.hdd <= maxHdd) {
                        result.add(notebook);
                    }
                    break;
                case "вес":
                    double minW = Double.parseDouble(filters.get("min"));
                    double maxW = Double.parseDouble(filters.get("max"));
                    if (notebook.weight >= minW & notebook.weight <= maxW) {
                        result.add(notebook);
                    }
                    break;
                case "цена":
                    int minP = Integer.parseInt(filters.get("min"));
                    int maxP = Integer.parseInt(filters.get("max"));
                    if (notebook.price >= minP & notebook.price <= maxP) {
                        result.add(notebook);
                    }
            }
        }
        return result;
    }
}
