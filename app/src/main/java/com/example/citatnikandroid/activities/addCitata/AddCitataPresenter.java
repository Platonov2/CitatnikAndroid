package com.example.citatnikandroid.activities.addCitata;

import androidx.annotation.NonNull;

import com.example.citatnikandroid.models.Citata;
import com.example.citatnikandroid.server.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCitataPresenter implements AddCitataContract.Presenter {

    private AddCitataContract.View view;

    AddCitataPresenter(AddCitataContract.View view) {
        this.view = view;
    }

    @Override
    public void onSaveCitataButtonClick() {
        view.performPostClickAnimation();

        String author = view.getAuthor();
        String creationDate = view.getCreationData();
        String content = view.getContent();

        if (author.trim().equals("") || creationDate.trim().equals("") || content.trim().equals("")){
            view.performWrongFieldAnimation();
            return;
        }

        Citata citata = new Citata(null, author, content, creationDate);

        NetworkService.getInstance()
                .getJSONApi()
                .postCitata(citata)
                .enqueue(new Callback<Citata>() {
                    @Override
                    public void onResponse(@NonNull Call<Citata> call, @NonNull Response<Citata> response) {
                        view.performPostClickAnimation();
                    }

                    @Override
                    public void onFailure(Call<Citata> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
