package com.electricsheeps.myreception.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alex on 30.08.17.
 */
public class Question {

    public static final int TYPE_INCRIMENTAL = 0;
    public static final int TYPE_SMILE = 1;
    public static final int TYPE_NEW_SIMPTOM = 2;
    public static final int TYPE_TEXT = 3;

    @SerializedName("type")
    public int type;
    @SerializedName("id")
    public int id;
    @SerializedName("after")
    public int after;
    @SerializedName("text")
    public String text;
    @SerializedName("answer")
    public List<Answer> answers;

    public Answer myAnswer;

    public Question(int type, String text, List<Answer> answers) {
        this.type = type;
        this.text = text;
        this.answers = answers;
    }
}
