package com.htp.service.validator;

import com.htp.domain.to.Candidate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CandidateAccountValidator implements ValidatorInterface<Candidate> {

    private CandidateAccountValidator(){}

    private static final ValidatorInterface<Candidate> instance = new CandidateAccountValidator();

    public static ValidatorInterface<Candidate> getInstance(){
        return instance;
    }

    private static final String REGEXP_NAME = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGEXP_SURNAME = "[а-яА-Яa-zA-Z]{2,25}";
    private static final String REGEXP_AGE = "^(0?[1-9]|[1-9][0-9]|[1][1-9][1-9]|150)$";

    private static final Pattern patternName = Pattern.compile(REGEXP_NAME);
    private static final Pattern patternSurname = Pattern.compile(REGEXP_SURNAME);
    private static final Pattern patternAge= Pattern.compile(REGEXP_AGE);

    @Override
    public boolean isValid(Candidate candidate) {
        String name = String.valueOf(candidate.getCandidateName());
        String surname = String.valueOf(candidate.getCandidateSurname());

        Matcher matcherName = patternName.matcher(name);
        Matcher matcherSurname = patternSurname.matcher(surname);

        return matcherName.find() &
                matcherSurname.find();

    }
}
