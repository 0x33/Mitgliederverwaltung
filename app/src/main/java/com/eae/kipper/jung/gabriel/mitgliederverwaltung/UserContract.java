package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

public class UserContract {
    public static abstract class NewUserInfo{
        public static final String USER_NAME = "user_name";
        public static final String USER_NUMMERP = "user_nummerp";
        public static final String USER_NUMMERM = "user_nummerm";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_STRASSE = "user_strasse";
        public static final String USER_PLZ = "user_plz";
        public static final String USER_ORT = "user_ort";

        //TODO
        //Mitgliederstatus fehlt noch

        public static final String TABLE_NAME= "user_info";
    }
}
