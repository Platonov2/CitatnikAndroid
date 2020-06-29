package com.example.citatnikandroid.activities.addCitata;

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

public class AddCitataFragment extends Fragment implements AddCitataContract.View {

    private AddCitataContract.Presenter presenter;

    private TextView newCitataGreetings;

    private EditText authorAddEditText, creationDateAddEditText, contentAddEditText;

    private Button addCitataButton;

    private Animation performAnimation, animationAlphaVanish, animationAlphaArise;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_citata, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        performAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.empty_animation);
        animationAlphaVanish = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_vanish);
        animationAlphaArise = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_arise);

        RelativeLayout layout = view.findViewById(R.id.relativeLayout);

        layout.startAnimation(animationAlphaArise);

        presenter = new AddCitataPresenter(this);

        newCitataGreetings = view.findViewById(R.id.newCitataGreetings);
        authorAddEditText = view.findViewById(R.id.authorAddEditText);
        creationDateAddEditText = view.findViewById(R.id.creationDateAddEditText);
        contentAddEditText = view.findViewById(R.id.contentAddEditText);
        addCitataButton = view.findViewById(R.id.addCitataButton);

        View.OnClickListener buttonOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveCitataButtonClick();
            }
        };
        addCitataButton.setOnClickListener(buttonOnClick);
    }


    @Override
    public void performPostClickAnimation() {
        performAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                authorAddEditText.startAnimation(animationAlphaVanish);
                creationDateAddEditText.startAnimation(animationAlphaVanish);
                contentAddEditText.startAnimation(animationAlphaVanish);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                newCitataGreetings.setText("Цитата успешно сохранена");
                authorAddEditText.setText("");
                creationDateAddEditText.setText("");
                contentAddEditText.setText("");

                newCitataGreetings.startAnimation(animationAlphaArise);
                authorAddEditText.startAnimation(animationAlphaArise);
                creationDateAddEditText.startAnimation(animationAlphaArise);
                contentAddEditText.startAnimation(animationAlphaArise);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        newCitataGreetings.startAnimation(performAnimation);
    }

    @Override
    public void performWrongFieldAnimation() {
        performAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                newCitataGreetings.setText("Ты что, дурак? \n Поля не заполнены!");

                newCitataGreetings.startAnimation(animationAlphaArise);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        newCitataGreetings.startAnimation(performAnimation);
    }

    @Override
    public String getContent() {
        return contentAddEditText.getText().toString();
    }

    @Override
    public String getAuthor() {
        return authorAddEditText.getText().toString();
    }

    @Override
    public String getCreationData() {
        return creationDateAddEditText.getText().toString();
    }

}
