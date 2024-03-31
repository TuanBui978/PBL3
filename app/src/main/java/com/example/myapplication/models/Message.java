package com.example.myapplication.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Messager {
    @SerializedName("cod")
    private int cod;
    @SerializedName("message")
    private String message;
    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Messager(int cod, @Nullable String message, @Nullable String error) {
        this.cod = cod;
        this.message = message;
        this.error = error;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
