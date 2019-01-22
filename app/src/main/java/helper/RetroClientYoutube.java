package helper;

import connection.Endpoint;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientYoutube {
    /********
     * URLS
     *******/

    private static final String ROOT_URL = "https://www.googleapis.com/youtube/v3/search/";

    private static Endpoint service;
    private static RetroClientYoutube retroClientYoutube;
    public static Retrofit retrofit;

    private RetroClientYoutube() {

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

    public static RetroClientYoutube getInstance() {
        if (retroClientYoutube == null) {
            retroClientYoutube = new RetroClientYoutube();
        }
        return retroClientYoutube;
    }


}
