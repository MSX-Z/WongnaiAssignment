package com.example.wongnaiassignment.Presenter;

import com.example.wongnaiassignment.Model.Data;
import com.example.wongnaiassignment.View.InterView;

import java.util.List;

public class CPresenter implements InterPresenter{

    private InterView interView;

    public CPresenter(InterView interView) {
        this.interView = interView;
    }

    @Override
    public void loadData(List<Data> list) {
        interView.resultData(list);
    }
}
