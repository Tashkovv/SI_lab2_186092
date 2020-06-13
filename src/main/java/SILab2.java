import java.util.List;

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

public class SILab2 {

    public static boolean function(User user, List<String> allUsers) {
        String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}";// 1
        if (user == null) {// 2
            throw new RuntimeException("The user is not instantiated");// 3
        }
        if (user.getUsername() == null || user.getPassword() == null)// 4
            throw new RuntimeException("The user is missing some mandatory information");// 5
        String password = user.getPassword();// 6
        String passwordLower = password.toLowerCase();// 6
        if (passwordLower.contains(user.getUsername().toLowerCase())) {// 6
            return false;// 7
        } else if (passwordLower.length() < 8)// 8
            return false;// 9
        else {// 10
            boolean digit = false, upper = false, special = false;// 10

            for (int i = 0; i < password.length(); i++) { // 11.1, 11.2, 11.3 (11)
                if (Character.isDigit(password.charAt(i))) //12
                    digit = true;// 13
                if (Character.isUpperCase(password.charAt(i)))// 14
                    upper = true;// 15
                if (specialCharacters.contains(String.valueOf(password.charAt(i))))// 16
                    special = true;// 17
            }// 18
            if (!digit || !upper || !special)// 19
                return false;// 20
        }// 21
        return true;// 21
    }// 22
}