package co.edu.uac.apmoviles.serviciosrest.json;

import android.os.Handler;
import android.os.Looper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONConnection {
    public static void getJsonString(final String url, final String apiToken, final JSONResponseListener listener){
        new Thread(){
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .addHeader("X-Auth-Token",apiToken)
                            .url(url).build();
                    TLSSocketFactory tlsSocketFactory= new TLSSocketFactory();
                    OkHttpClient client = new OkHttpClient.Builder()
                            .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.getTrustManager())
                            .build();
                    final Response response = client.newCall(request).execute();
                    final String jsonString = response.body().string();
                    Handler handler = new Handler(Looper.getMainLooper());
                    if(response.code()==200){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onSuccess(jsonString);
                            }
                        });
                    }else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(new Exception("Invalid Response code ("+response.code()+")"));
                            }
                        });
                    }
                } catch (Exception e) {
                    listener.onError(e);
                }
            }
        }.start();
    }

    public interface JSONResponseListener{
        void onSuccess(String jsonString);
        void onError(Exception e);
    }
}
