package PasswordGeneration;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ArrayList<String> allPass = new ArrayList<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Pattern pattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}");
        String pass = randomPass();
        Matcher matcher = pattern.matcher(pass);
        if (matcher.matches() && Collections.frequency(allPass,pass) == 0) {
            allPass.add(pass);
            byteArrayOutputStream.writeBytes(pass.getBytes());
            return byteArrayOutputStream;
        } else {
            return getPassword();
        }

    }

    public static String randomPass(){
        int length = 8;
        Random random = new Random();
        String randomPass = random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return randomPass;
    }
}
