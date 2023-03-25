package com.example.callbackecample;
public interface OnApiresposne {
    void onSuccessResponse(String result, int statuscode);

    void onErrorResponse(String errorMessage);

}
