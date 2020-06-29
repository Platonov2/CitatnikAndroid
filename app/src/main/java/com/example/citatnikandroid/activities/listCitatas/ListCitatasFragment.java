package com.example.citatnikandroid.activities.listCitatas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.citatnikandroid.R;
import com.example.citatnikandroid.models.Citata;
import com.example.citatnikandroid.server.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCitatasFragment extends Fragment{

    private ListView listView;
    private View view;
    private int loader = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    //
    //  Retrofit является потоконебезопасной библиотекой, так что при вызове NetworkService,
    //      главный поток не ждёт получения ответа в onResponse, а выполняется дальше.
    //  А так как мне необходимо сразу же наполнить ListAdapter списком цитат,
    //      то приходится объединять уровни presenter и view.
    //
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

        NetworkService.getInstance()
                .getJSONApi()
                .getAllCitatas()
                .enqueue(new Callback<ArrayList<Citata>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Citata>> call, @NonNull Response<ArrayList<Citata>> response) {
                        ArrayList<Citata> list = response.body();

                        setAdapter(list);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Citata>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void setAdapter(ArrayList<Citata> list) {
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("APP_PREFERENCES", Context.MODE_PRIVATE);
        int maxLength = Integer.parseInt(sharedPreferences.getString("maxListLength", "15"));

        ArrayList<Citata> newList = new ArrayList<>();
        int counter = 0;
        for (Citata element : list) {
            if (counter == maxLength) break;

            newList.add(element);
            counter += 1;
        }

        listView = view.findViewById(R.id.listCitatas);

        ListAdapter myListAdaptery = new ListAdapter(this.getContext(), newList);
        listView.setAdapter(myListAdaptery);
    }
}