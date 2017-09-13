package adaz.urbantick_ibratec.Services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adaz.urbantick_ibratec.Model.Fornecedor;

public class FornecedorHttp {

    public static final String FORNECEDOR_URL_JSON = "COLOCAR AQUI O ENDEREÇO QUE JUNIOR VAI PASSAR COM A LISTA DE JSON";

    private static HttpURLConnection conectar(String urlArquivo) throws IOException{
        final int SEGUNDOS = 1000;
        URL url = new URL(urlArquivo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("GET");
        conexao.setDoInput(true);
        conexao.setDoOutput(false);
        conexao.connect();
        return conexao;
    }

    public static boolean temConexao(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    public static List<Fornecedor> carregarFornecedorJson(){
        try{
            HttpURLConnection conexao = conectar(FORNECEDOR_URL_JSON);
            int resposta = conexao.getResponseCode();
            if(resposta == HttpURLConnection.HTTP_OK){
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerArquivoJson(json);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Fornecedor> lerArquivoJson(JSONObject json) throws JSONException{
        List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();

        String fornecedorJson;

        JSONArray jsonFornecedor = json.getJSONArray("fornecedor");
        for(int i = 0; i < jsonFornecedor.length(); i++){
            JSONObject jsonObject = jsonFornecedor.getJSONObject(i);
            fornecedorJson = jsonObject.getString("fornecedor");

                Fornecedor fornecedor = new Fornecedor(
                        jsonObject.getInt("id"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("apelido"),
                        jsonObject.getString("categoria"),
                        jsonObject.getString("subcategoria"),
                        jsonObject.getString("telefone"),
                        jsonObject.getString("descricao")
                );
                listaFornecedor.add(fornecedor);
        }

        return listaFornecedor;
    }

    private static String bytesParaString(InputStream is) throws IOException{
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();

        int bytesLidos;

        while ((bytesLidos = is.read(buffer)) != -1){
            bufferzao.write(buffer, 0, bytesLidos);
        }
        return new String(bufferzao.toByteArray(), "UTF-8");
    }
}
