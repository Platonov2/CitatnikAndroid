package com.example.citatnikandroid.activities.addCitata;

import com.example.citatnikandroid.models.Citata;

public interface AddCitataContract {
    interface View {
        String getContent();
        String getAuthor();
        String getCreationData();

        void performPostClickAnimation();
        void performWrongFieldAnimation();

    }

    interface Presenter {
        void onSaveCitataButtonClick();
    }
}
