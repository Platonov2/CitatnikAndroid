package com.example.citatnikandroid.activities.getRandomCitata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.citatnikandroid.R;

public class GetRandomCitataFragment extends Fragment implements GetRandomCitataContract.View {

    private GetRandomCitataContract.Presenter presenter;

    private TextView greetingsTextView, authorTextView, contentTextView, creationDateTextView;
    private Button getRandomCitataButton;

    private Animation performAnimation, animationAlphaVanish, animationAlphaArise;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_get_random_citata, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        performAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.empty_animation);
        animationAlphaVanish = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_vanish);
        animationAlphaArise = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_arise);

        RelativeLayout layout = view.findViewById(R.id.relativeLayout);

        layout.startAnimation(animationAlphaArise);

        presenter = new GetRandomCitataPresenter(this);

        greetingsTextView = view.findViewById(R.id.greetingsTextView);
        authorTextView = view.findViewById(R.id.authorTextView);
        contentTextView = view.findViewById(R.id.contentTextView);
        creationDateTextView = view.findViewById(R.id.creationDateTextView);
        getRandomCitataButton = view.findViewById(R.id.getCitataButton);

        View.OnClickListener buttonOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onGetRandomCitataButtonClick();
            }
        };
        getRandomCitataButton.setOnClickListener(buttonOnClick);
    }

    @Override
    public void setFields(final String author, final String content, final String creationDate) {

        performAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (greetingsTextView.getVisibility() == View.VISIBLE){
                    greetingsTextView.startAnimation(animationAlphaVanish);
                }

                contentTextView.startAnimation(animationAlphaVanish);
                creationDateTextView.startAnimation(animationAlphaVanish);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                greetingsTextView.setVisibility(View.INVISIBLE);

                authorTextView.setText(author);
                contentTextView.setText(content);
                creationDateTextView.setText(creationDate);

                authorTextView.startAnimation(animationAlphaArise);
                contentTextView.startAnimation(animationAlphaArise);
                creationDateTextView.startAnimation(animationAlphaArise);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        authorTextView.startAnimation(performAnimation);
    }
}
