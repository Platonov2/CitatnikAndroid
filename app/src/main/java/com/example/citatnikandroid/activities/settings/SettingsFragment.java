package com.example.citatnikandroid.activities.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.citatnikandroid.R;

public class SettingsFragment extends Fragment implements SettingsContract.View {

    private SettingsPresenter presenter;

    private TextView settingsGreetings;

    private EditText maxListLengthEditText;

    private Button saveSettingsButton;

    private Animation performAnimation, animationAlphaArise;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        performAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.empty_animation);
        animationAlphaArise = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_arise);

        RelativeLayout layout = view.findViewById(R.id.relativeLayout);

        layout.startAnimation(animationAlphaArise);

        presenter = new SettingsPresenter(this, this.getContext());

        settingsGreetings = view.findViewById(R.id.settingsGreetings);
        maxListLengthEditText = view.findViewById(R.id.maxListLengthEditText);
        saveSettingsButton = view.findViewById(R.id.saveSettingsButton);

        maxListLengthEditText.setText(presenter.getSavedMaxListLength());

        final SubmitDialog dialog = new SubmitDialog(this);

        View.OnClickListener buttonOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(getActivity().getSupportFragmentManager(), "dialog");

            }
        };
        saveSettingsButton.setOnClickListener(buttonOnClick);
    }

    void onDialogApply() {
        presenter.onDialogApply();
    }

    void onDialogDismiss() {
        presenter.onDialogDismiss();
    }

    @Override
    public void performPostSaveAnimation(final String text) {
        performAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settingsGreetings.setText(text);

                settingsGreetings.startAnimation(animationAlphaArise);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        settingsGreetings.startAnimation(performAnimation);
    }

    @Override
    public String getMaxListLengthText() {
        return maxListLengthEditText.getText().toString();
    }

}
