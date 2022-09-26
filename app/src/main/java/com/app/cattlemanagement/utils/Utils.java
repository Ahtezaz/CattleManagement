package com.app.cattlemanagement.utils;

import android.util.Log;
import android.widget.EditText;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    public static boolean validEt(EditText etUserName, String strEtUserName) {
        if (strEtUserName.isEmpty()) {
            etUserName.setError("Field Empty");
            return false;
        }
        return true;
    }

    public static boolean validateUserName(EditText etEmail, String strEtEmail) {
        // Regex to check valid username.
        final String regex = "^[A-Za-z]\\w{5,29}$";

        // Compile the ReGex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strEtEmail);
        if (!matcher.matches()) {
            Log.d("TAG", "Matches True");
            etEmail.setError("INVALID USERNAME");
        }
        return matcher.matches();
    }

    public static boolean validateName(String generalString) {
        final String regex = "^[A-Za-z]{5,29}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(generalString);
        return matcher.matches();
    }
    public static boolean validateDate(String generalString) {
        final String regex = "\\b(\\d{1,2}-[A-Za-z]{3}-\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(generalString);
        return matcher.matches();
    }

    public static boolean validateIsNumber(String generalString) {
        final String regex = "(\\d{1})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(generalString);
        return matcher.matches();
    }
    public static boolean validateWeight(String generalString) {
        final String regex = "^[0-9]{1,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(generalString);
        return matcher.matches();
    }

    public static boolean validateEmail(EditText etPhone, String strEtPhone) {
        // Regex to check valid username.
        final String regex = "^(.+)@(.+)$";
        // Compile the ReGex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strEtPhone);
        if (!matcher.matches()) {
            etPhone.setError("INVALID EMAIL FORMAT");
        }
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(EditText etUserName, String strEtUserName) {
        // Regex to check valid username.
        final String regex = "^((\\+92)?(0092)?(92)?(0)?)(3)([0-9]{9})$";
        // Compile the ReGex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strEtUserName);
        if (!matcher.matches()) {
            etUserName.setError("INVALID PHONE NUMBER");
        }
        return matcher.matches();
    }

    public static boolean validatePassword(EditText etPassword, String strEtPassword) {
        // Regex to check valid username.
        final String regex = "^(?=.*[0-9])"
                                     + "(?=.*[a-z])(?=.*[A-Z])"
                                     + "(?=.*[@#$%^&+=])"
                                     + "(?=\\S+$).{8,20}$";
        // Compile the ReGex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(strEtPassword);
        if (!matcher.matches()) {
            etPassword.setError("Should Have Upper, lower , Special");
        }
        return matcher.matches();
    }

}
