package adaz.urbantick_ibratec.Gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import adaz.urbantick_ibratec.R;
import adaz.urbantick_ibratec.Model.Fornecedor;
import adaz.urbantick_ibratec.Retrofit.IUsuarioREST;
import adaz.urbantick_ibratec.Util.FornecedorAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lucasnascimento on 08/10/17.
 */

public class ListagemFornecedorActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_fornecedor);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String uf = bundle.getString("uf");
        String cidade = bundle.getString("cidade");
        String categoria = bundle.getString("categoria");
        String subcategoria = bundle.getString("subcategoria");

        final ListView lista = (ListView) findViewById(R.id.lvMusicos);

        IUsuarioREST iUsuarioREST = IUsuarioREST.retrofit.create(IUsuarioREST.class);
        dialog = new ProgressDialog(ListagemFornecedorActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();

        final Call<List<Fornecedor>> call = iUsuarioREST.listarFiltroFornecedor(uf, cidade, categoria, subcategoria);

        call.enqueue(new Callback<List<Fornecedor>>() {
            @Override
            public void onResponse(Call<List<Fornecedor>> call, Response<List<Fornecedor>> response) {
                if (dialog.isShowing())
                    dialog.dismiss();

                final List<Fornecedor> listaFornecedor = response.body();

                if (listaFornecedor != null) {
                    FornecedorAdapter adapter = new FornecedorAdapter(getBaseContext(), listaFornecedor);
                    lista.setAdapter(adapter);
                    /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(ListagemFornecedorActivity.this, TelaAbasActivity.class);
                        intent.putExtra("ID", listaFornecedor.get(i).getId());
                        startActivity(intent);
                        }
                    });*/
                }
            }

            @Override
            public void onFailure(Call<List<Fornecedor>> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Log.e("TAG", "ERRO: " + t.getMessage());
                Toast.makeText(getBaseContext(), "Problema de acesso", Toast.LENGTH_LONG).show();
            }
        });
    }
}
