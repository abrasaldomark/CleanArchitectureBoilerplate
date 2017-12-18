package com.tobz.clean.data.network;


import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tobz.clean.data.network.error.ErrorResponse;
import com.tobz.clean.data.network.error.RestApiException;

import java.io.IOException;

import retrofit2.Response;

public class ResponseHandler {

    private static final String TAG = "ResponseHandler";

    /**
     * Handle the rest api error response.
     * @param response
     */
    protected void handleResponseError(Response response) {
        if (!response.isSuccessful()) {
            try {
                JsonObject json = (JsonObject) new JsonParser().parse(response.errorBody().string());
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create();
                ErrorResponse error = gson.fromJson(json, ErrorResponse.class);
                throw new RestApiException(error.getMessage(), error.getStatus(), error.getStatusCode());
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "handleRestError: ", e);
                throw new RestApiException(response.message(), response.message(), response.code());
            }
        }
    }
}
