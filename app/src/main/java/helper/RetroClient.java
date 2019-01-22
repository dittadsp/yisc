package helper;

import connection.Endpoint;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    /********
     * URLS
     *******/

    private static final String ROOT_URL = "https://internal.yisc-alazhar.or.id/";

    private static Endpoint service;
    private static RetroClient retroClient;
    public static Retrofit retrofit;

    private RetroClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Endpoint.class);
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RetroClient getInstance() {
        if (retroClient == null) {
            retroClient = new RetroClient();
        }
        return retroClient;
    }


}
