package com.example.citatnikandroid.activities.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View view;

    private SharedPreferences sharedPreferences;

    SettingsPresenter(SettingsContract.View view, Context context) {
        this.view = view;
        sharedPreferences = context.getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE);
    }

    @Override
    public void onDialogApply() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String maxListLengthText = view.getMaxListLengthText();

        if (maxListLengthText.trim().equals("")) {
            view.performPostSaveAnimation("Ты что, дурак? \nПоля не заполнены!");
            return;
        }

        view.performPostSaveAnimation("Настройки сохранены.\nТребуется перезапуск.");
        editor.putString("maxListLength", maxListLengthText);

        editor.apply();
    }

    public void onDialogDismiss() {
        view.performPostSaveAnimation("Настройки не были сохранены.");
    }

    @Override
    public String getSavedMaxListLength() {
        return sharedPreferences.getString("maxListLength", "5");
    }
}
