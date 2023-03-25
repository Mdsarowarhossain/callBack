package com.example.callbackecample;


import android.os.Handler;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiCall {

    private Handler mHandler;

    public ApiCall(Handler handler) {
        mHandler = handler;
    }

    public void getApiRequest(String url, OnApiresposne onApiresposne) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onApiresposne.onErrorResponse(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseBody = response.body().string();
                    final int responseCode = response.code();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onApiresposne.onSuccessResponse(responseBody, responseCode);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onApiresposne.onErrorResponse(response.message());
                        }
                    });
                }
            }
        });
    }

    public void postApiRequest(String url, String jsondata, OnApiresposne onApiresposne) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsondata);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onApiresposne.onErrorResponse(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String responseBody = response.body().string();
                    final int responseCode = response.code();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onApiresposne.onSuccessResponse(responseBody, responseCode);
                        }
                    });
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                onApiresposne.onErrorResponse(new JSONObject(response.body().string()).getString("message"));
                            } catch (JSONException e) {
                                onApiresposne.onErrorResponse(response.message());

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        });
    }


}
