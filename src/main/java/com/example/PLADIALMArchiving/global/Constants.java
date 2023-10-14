package com.example.PLADIALMArchiving.global;

public class Constants {
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    public static class Booking{
        public static final String BOOKED_TIMES = "bookedTimes";
    }

    public static class JWT{
        public static final String AUTHORIZATION_HEADER = "Authorization";
        public static final String BEARER_PREFIX = "Bearer ";
        public static final String CLAIM_NAME = "userIdx";
        public static final String LOGOUT = "logout";
        public static final String SIGNOUT = "signout";
    }

    public static class EXTENSION{
        public static final String IMAGE = "jpeg jpg png gif";
        public static final String VIDEO = "mp4 mov avi webm html5";
        public static final String DOCS = "doc docx txt ppt pptx xls pdf ai psd hwp";
    }
}
