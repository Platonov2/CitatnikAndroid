package com.example.citatnikandroid.activities.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SubmitDialog extends DialogFragment {

    private SettingsFragment fragment;

    SubmitDialog(SettingsFragment fragment){
        this.fragment = fragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        builder.setMessage("Сохранить изменения настроек?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fragment.onDialogApply();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        fragment.onDialogDismiss();
                    }
                });

        return builder.create();
    }
}
