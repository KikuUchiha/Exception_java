import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Введите данные пользователя на английском(Фамилия Имя Отчество дата_рождения номер_телефона пол): ");
                String userData = scanner.nextLine();

                try {
                    User user = new User(userData);
                    user.writeToFile();
                    System.out.println("Данные пользователя успешно записаны в файл!");
                } catch (InvalidUserDataException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Ошибка при работе с файлом:");
                    e.printStackTrace();
                }
            }
        

    }
}
