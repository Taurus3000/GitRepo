public class Main {
    public static void main(String[] args) {

        PhoneBook pb = new PhoneBook();
        pb.add("Ivanov", "12345");
        pb.add("Petrov", "67890");
        pb.add("Petrov", "13579");
        pb.add("Sidorov", "24680");
        pb.add("Kuznecov", "77777");

        pb.adde("Ivanov", "a@a.ru");
        pb.adde("Petrov", "b@b.ru");
        pb.adde("Petrov", "c@c.ru");
        pb.adde("Sidorov", "d@d.ru");
        pb.adde("Kuznecov", "e@e.ru");

        System.out.println("Ivanov: " + pb.get("Ivanov"));
        System.out.println("Petrov: " + pb.get("Petrov"));
        System.out.println("Sidorov: " + pb.get("Sidorov"));
        System.out.println("Kuznecov: " + pb.get("Kuznecov"));

    }

}
