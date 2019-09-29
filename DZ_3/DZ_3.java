import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DZ_3 {
    public static void main(String[] args) {
        String[] animals = {"cat", "dog", "frog", "cat", "dog", "pig", "cow",
                "horse", "rat", "mouse", "rat", "sheep", "mouse", "cat",
                "pig", "dog", "cow", "frog", "cat", "mouse", "cow"};

        Unique(animals);
        HowMany(animals);
    }

    private static void Unique(String[] animals) {
        Set<String> uniq = new HashSet<>(Arrays.asList(animals));
        System.out.println("Cписок слов, из которых состоит текст (без дубликатов):");
        System.out.println(uniq);
    }

    private static void HowMany(String[] animals) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String animal : animals) {
            map.put(animal, map.getOrDefault(animal, 0) + 1);
        }
        System.out.println("Считаем сколько раз встречается каждое слово:");
        System.out.println(map);
    }
}