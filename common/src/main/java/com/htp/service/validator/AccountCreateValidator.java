package com.htp.service.validator;

import com.htp.domain.to.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreateValidator implements ValidatorInterface<User> {

    private static final ValidatorInterface<User> instance = new AccountCreateValidator();

    private AccountCreateValidator() {
    }

    public static ValidatorInterface<User> getInstance() {
        return instance;
    }

    private static final String REGEXP_LOGIN = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGEXP_PASSWORD = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGEXP_USERNAME = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGEXP_SURNAME = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGEXP_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String REGEXP_MOBILEPHONE = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";

    private static final Pattern patternLogin = Pattern.compile(REGEXP_LOGIN);
    private static final Pattern patternPassword = Pattern.compile(REGEXP_PASSWORD);
    private static final Pattern patternUsername = Pattern.compile(REGEXP_USERNAME);
    private static final Pattern patternSurname = Pattern.compile(REGEXP_SURNAME);
    private static final Pattern patternEmail = Pattern.compile(REGEXP_EMAIL);
    private static final Pattern patternMobilePhone = Pattern.compile(REGEXP_MOBILEPHONE);

    @Override
    public boolean isValid(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        Matcher matcherLogin = patternLogin.matcher(login);
        Matcher matcherPassword = patternPassword.matcher(password);
        return matcherLogin.matches() & matcherPassword.matches();
    }
}
