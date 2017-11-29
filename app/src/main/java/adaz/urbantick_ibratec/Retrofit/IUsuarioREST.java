package adaz.urbantick_ibratec.Retrofit;

import java.util.List;

import adaz.urbantick_ibratec.Model.Fornecedor;
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

    @POST("recuperar/senha")
    Call<Void> resetPassword(@Body Usuario usuario);

    /*@POST("avaliar")
    Call<Void> avaliarFornecedor(@Body Fornecedor fornecedor);*/

    @GET("avaliar/{id_fornecedor}/{pontuacao}")
    Call<Void> avaliarFornecedor(
            @Path("id_fornecedor") int id_fornecedor,
            @Path("pontuacao") float pontuacao
    );

    @GET("provider/get/{uf}/{cidade}/{categoria}/{subcategoria}")
    Call<List<Fornecedor>> listarFiltroFornecedor(
            @Path("uf") String uf,
            @Path("cidade") String cidade,
            @Path("categoria") String categoria,
            @Path("subcategoria") String subcategoria
    );

    @GET("provider/detail/{id}")
    Call<Fornecedor> listarFornecedor(@Path("id") int id);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.urbantick.com.br/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
