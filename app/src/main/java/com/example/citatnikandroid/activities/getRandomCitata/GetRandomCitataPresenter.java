package com.example.citatnikandroid.activities.getRandomCitata;

import androidx.annotation.NonNull;

import com.example.citatnikandroid.models.Citata;
import com.example.citatnikandroid.server.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRandomCitataPresenter implements GetRandomCitataContract.Presenter {

    private GetRandomCitataContract.View view;

    GetRandomCitataPresenter(GetRandomCitataContract.View view) {
        this.view = view;
    }

    @Override
    public void onGetRandomCitataButtonClick() {
        int id = (int) ((Math.random() * 4) + 1);

        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(id)
                .enqueue(new Callback<Citata>() {
                    @Override
                    public void onResponse(@NonNull Call<Citata> call, @NonNull Response<Citata> response) {
                        Citata citata = response.body();

                        view.setFields(citata.getAuthor(), citata.getContent(), citata.getCreationDate());
                    }

                    @Override
                    public void onFailure(Call<Citata> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
