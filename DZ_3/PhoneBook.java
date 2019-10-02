import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> pbook = new HashMap<>();
    private List<String> numbers;
    private List<String> emails;

    public void add(String surname, String phone_number) {
        if (pbook.containsKey(surname)) {
            numbers = pbook.get(surname);
            numbers.add(phone_number);
            pbook.put(surname, numbers);
        } else {
            numbers = new ArrayList<>();
            numbers.add(phone_number);
            pbook.put(surname, numbers);
        }
    }
    public void adde(String surname, String email) {
        if (pbook.containsKey(surname)) {
            emails = pbook.get(surname);
            emails.add(email);
            pbook.put(surname, emails);
        } else {
            emails = new ArrayList<>();
            emails.add(email);
            pbook.put(surname, emails);
        }
    }

    public List<String> get(String surname) {
        return pbook.get(surname);
    }

}
