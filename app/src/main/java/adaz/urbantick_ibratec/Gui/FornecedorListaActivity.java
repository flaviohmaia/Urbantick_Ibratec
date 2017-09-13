package adaz.urbantick_ibratec;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import adaz.urbantick_ibratec.Model.Fornecedor;
import adaz.urbantick_ibratec.Services.FornecedorHttp;

public class FornecedorListaActivity extends Fragment{

    FornecedorTask mTask;
    List<Fornecedor> listaFornecedor;
    ArrayAdapter<Fornecedor> adapterFornecedor;
    ListView listView;
    TextView textView;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //setContentView(R.layout.activity_fornecedor_lista);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        View layout = inflater.inflate(R.layout.activity_fornecedor_lista, container, false);
        textView = (TextView) layout.findViewById(android.R.id.empty);
        progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        listView = (ListView) layout.findViewById(R.id.list);
        listView.setEmptyView(textView);
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstaceState){
        super.onActivityCreated(savedInstaceState);

        if(listaFornecedor == null){
            listaFornecedor = new ArrayList<Fornecedor>();
        }

        adapterFornecedor = new ArrayAdapter<Fornecedor>(getActivity(), android.R.layout.simple_list_item_1, listaFornecedor);
        listView.setAdapter(adapterFornecedor);

        if(mTask == null){
            if(FornecedorHttp.temConexao(getActivity())){
                iniciarDownload();
            }else{
                textView.setText("Sem Conexão");
            }
        }else if(mTask.getStatus() == AsyncTask.Status.RUNNING){
            exibirProgress(true);
        }
    }

    public void exibirProgress(boolean exibir){
        if(exibir){
            textView.setText("Baixando informações. Por favor aguarde...");
        }
        textView.setVisibility(exibir ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    public void iniciarDownload(){
        if(mTask == null || mTask.getStatus() != AsyncTask.Status.RUNNING){
            mTask = new FornecedorTask();
            mTask.execute();
        }
    }


    //Classe auxiliar para implementar os métodos do AsyncTask
    class FornecedorTask extends AsyncTask<Void, Void, List<Fornecedor>>{

        @Override
        protected List<Fornecedor> doInBackground(Void... strings) {
            return FornecedorHttp.carregarFornecedorJson();
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            exibirProgress(true);
        }

        protected void onPostExecute(List<Fornecedor> f){
            super.onPostExecute(f);
            exibirProgress(false);
            if(f != null){
                listaFornecedor.clear();
                listaFornecedor.addAll(f);
                adapterFornecedor.notifyDataSetChanged();
            }else{
                textView.setText("Falha ao obter Fornecedores");
            }
        }
    }
}
