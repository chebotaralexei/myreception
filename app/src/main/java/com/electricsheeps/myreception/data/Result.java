package com.electricsheeps.myreception.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alex on 30.08.17.
 */
public class Result {

    @SerializedName("questions")
    public List<Question> questions;

}
