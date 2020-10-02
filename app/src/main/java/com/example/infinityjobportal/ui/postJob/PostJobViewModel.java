package com.example.infinityjobportal.ui.postJob;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostJobViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PostJobViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Let's get started!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}