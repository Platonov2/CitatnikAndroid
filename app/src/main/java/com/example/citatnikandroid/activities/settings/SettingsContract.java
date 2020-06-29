package com.example.citatnikandroid.activities.settings;

public interface SettingsContract {
    interface View {
        String getMaxListLengthText();

        void performPostSaveAnimation(String text);
    }

    interface Presenter {
        void onDialogApply();
        void onDialogDismiss();
        String getSavedMaxListLength();
    }
}
