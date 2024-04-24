package com.example.myapplication.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("cod")
    private String cod;
    @SerializedName("msg")
    private String message;
    @SerializedName("errors")
    private Errors error;

    public class Errors {
        @SerializedName("msg")
        private String msg;

        public Errors(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }

    public Message(String cod, @Nullable String message, @Nullable Errors error) {
        this.cod = cod;
        this.message = message;
        this.error = error;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
