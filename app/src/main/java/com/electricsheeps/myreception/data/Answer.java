package com.electricsheeps.myreception.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 30.08.17.
 */
public class Answer {

    @SerializedName("text")
    public String text;
    @SerializedName("value")
    public float count;

    public int qId;

    public Answer(String text, float count) {
        this.text = text;
        this.count = count;
    }
}
