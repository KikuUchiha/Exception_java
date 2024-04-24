import java.io.*;
import java.util.regex.Pattern;

public class User {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String dob;
    private Long phoneNumber;
    private char gender;

    // Конструктор
    public User(String data) throws InvalidUserDataException {
        parseUserData(data);
    }

    // Парсинг данных пользователя
    private void parseUserData(String data) throws InvalidUserDataException {
        String[] userData = data.split(" ");

        if (userData.length != 6) {
            throw new InvalidUserDataException("Неверное количество данных. Ожидается 6, получено: " + userData.length);
        }

        lastName = userData[0];
        firstName = userData[1];
        patronymic = userData[2];
        dob = userData[3];
        phoneNumber = Long.parseLong(userData[4]);
        gender = userData[5].charAt(0);

        validateData();
    }

    // Проверка формата данных
    private void validateData() throws InvalidUserDataException {
        if (!Pattern.matches("\\d{2}\\.\\d{2}\\.\\d{4}", dob)) {
            throw new InvalidUserDataException("Неверный формат даты рождения. Ожидается dd.mm.yyyy, получено: " + dob);
        }

        if (phoneNumber instanceof Long == false) {
            throw new InvalidUserDataException("Неверный формат номера телефона. Ожидается целое число, получено: " + phoneNumber);
        }

        if (gender !='f' && gender !='m') {
            throw new InvalidUserDataException("Неверный пол. Ожидается 'f' или 'm', получено: " + gender);
        }
    }

    // Запись данных в файл
    public void writeToFile() throws IOException {
        String fileName = lastName + ".txt";
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(toString() + "\n");
        }
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + patronymic + " " + dob + " " + phoneNumber + " " + gender;
    }
}

