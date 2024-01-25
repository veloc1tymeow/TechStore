import java.util.*;

class Notebook {
    private String model;
    private int ramSize;
    private int storageSize;
    private String operatingSystem;
    private String color;

    public Notebook(String model, int ramSize, int storageSize, String operatingSystem, String color) {
        this.model = model;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }


    public String getModel() {
        return model;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "модель='" + model + '\'' +
                ", ОЗУ=" + ramSize +
                ", объём памяти=" + storageSize +
                ", операционная система='" + operatingSystem + '\'' +
                ", цвет='" + color + '\'' +
                '}';
    }
}

public class TechStore {
    public static void main(String[] args) {
        Set<Notebook> notebooks = createNotebookSet();

        Map<String, Object> filter = requestFilterCriteria();
        Map<String, Object> filterParameters = requestMinimumValues(filter);

        Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, filter, filterParameters);

        displayResults(filteredNotebooks);
    }

    private static Set<Notebook> createNotebookSet() {
        Set<Notebook> notebooks = new HashSet<>();

        notebooks.add(new Notebook("Dell", 8, 512, "Windows", "чёрный"));
        notebooks.add(new Notebook("HP", 16, 1024, "Linux", "серый"));

        return notebooks;
    }

    private static Map<String, Object> requestFilterCriteria() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filter = new HashMap<>();
        System.out.println("Введите число, соответствующее критерию фильтра:");
        System.out.println("1 - ОЗУ\n2 - объём памяти\n3 - операционная система\n4 - цвет");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                filter.put("ОЗУ", null);
                break;
            case 2:
                filter.put("объём памяти", null);
                break;
            case 3:
                filter.put("операционная система", null);
                break;
            case 4:
                filter.put("цвет", null);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        return filter;
    }

    private static Map<String, Object> requestMinimumValues(Map<String, Object> filter) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filterParameters = new HashMap<>();

        for (String criterion : filter.keySet()) {
            System.out.printf("Введите значение для категории %s: ", criterion);
            Object value = scanner.nextLine();
            filterParameters.put(criterion, value);
        }

        return filterParameters;
    }

    private static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filter, Map<String, Object> parameters) {
        Set<Notebook> filteredNotebooks = new HashSet<>();

        for (Notebook notebook : notebooks) {
            boolean matchesFilter = true;

            for (Map.Entry<String, Object> entry : filter.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();

                if (value != null && !matchesCriterion(notebook, criterion, value)) {
                    matchesFilter = false;
                    break;
                }
            }

            if (matchesFilter) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }

    private static boolean matchesCriterion(Notebook notebook, String criterion, Object value) {
        switch (criterion) {
            case "ОЗУ":
                return notebook.getRamSize() >= (int) value;
            case "объём памяти":
                return notebook.getStorageSize() >= (int) value;
            case "операционная система":
                return notebook.getOperatingSystem().equalsIgnoreCase((String) value);
            case "цвет":
                return notebook.getColor().equalsIgnoreCase((String) value);
            default:
                return false;
        }
    }

    private static void displayResults(Set<Notebook> notebooks) {
        System.out.println("Обработка результатов:");
        for (Notebook notebook : notebooks) {
            System.out.println(notebook);
        }
    }
}
