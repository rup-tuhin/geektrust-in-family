package com.geektrust.in.family.utils;

import com.geektrust.in.family.model.Gender;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.geektrust.in.family.model.Gender.Female;
import static com.geektrust.in.family.model.Gender.Male;

public final class Constants {
    public static final String NONE = "NONE";
    public static final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";
    public static final String CHILD_ADDITION_SUCCEEDED = "CHILD_ADDITION_SUCCEEDED";
    public static final String CHILD_ADDITION_FAILED = "CHILD_ADDITION_FAILED";
    public static final String ADD_CHILD = "ADD_CHILD";
    public static final String GET_RELATIONSHIP = "GET_RELATIONSHIP";

    public static Map<String, Gender> shanChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Chit", Male);
                put("Ish", Male);
                put("Vich", Male);
                put("Aras", Male);
                put("Satya", Female);
            }};

    public static Map<String, Gender> chitChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Dritha", Female);
                put("Tritha", Female);
                put("Vritha", Male);
            }};
    public static Map<String, Gender> vichChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Vila", Female);
                put("Chika", Female);
            }};

    public static Map<String, Gender> arasChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Jnki", Female);
                put("Ahit", Male);
            }};

    public static Map<String, Gender> vyanChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Asva", Male);
                put("Vyas", Male);
                put("Atya", Female);
            }};

    public static Map<String, Gender> jayaChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Yodhan", Male);
            }};

    public static Map<String, Gender> aritChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Laki", Male);
                put("Lavnya", Female);
            }};

    public static Map<String, Gender> asvaChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Vasa", Male);
            }};

    public static Map<String, Gender> vyasChildMap =
            new LinkedHashMap<String, Gender>() {{
                put("Kriya", Male);
                put("Krithi", Female);
            }};
}



