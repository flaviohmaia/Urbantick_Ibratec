package adaz.urbantick_ibratec.Retrofit;

import java.util.List;

import adaz.urbantick_ibratec.Model.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lucasnascimento on 24/09/17.
 */

public interface IUsuarioREST {

    @POST("user/login")
    Call<Void> login(@Body Usuario usuario);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.urbantick.com.br/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
