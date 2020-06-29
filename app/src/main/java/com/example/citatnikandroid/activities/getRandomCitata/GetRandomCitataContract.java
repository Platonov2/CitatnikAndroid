package com.example.citatnikandroid.activities.getRandomCitata;

public interface GetRandomCitataContract {

    interface View {
        void setFields(String author, String content, String creationDate);
    }

    interface Presenter {
        void onGetRandomCitataButtonClick();
    }
}
