package com.tobz.clean.data.network.interceptor;


import com.tobz.clean.util.PrefUtil;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Interceptor for http request.
 */

public class HttpInterceptor implements Interceptor {

    private PrefUtil prefUtil;

    @Inject
    public HttpInterceptor(PrefUtil prefUtil) {
        this.prefUtil = prefUtil;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        // Add custom header if necessary
        /*String token = this.prefUtil.getPrefToken();
        // Add custom header.
        builder.addHeader("X-App-Name", "");
        builder.addHeader("X-App-Device-Id", this.prefUtil.getPrefDeviceId());
        // Add authorization header if their is token save in preference.
        if (!TextUtils.isEmpty(token)) {
            String auth = "Bearer " + token;
            builder.addHeader("Authorization", auth);
        }*/


        Request newRequest = builder.build();
        Response response = chain.proceed(newRequest);
        return response;
    }
}
