package adaz.urbantick_ibratec.Gui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import adaz.urbantick_ibratec.Model.Fornecedor;
import adaz.urbantick_ibratec.R;
import adaz.urbantick_ibratec.Retrofit.IUsuarioREST;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lucasnascimento on 11/11/17.
 */

public class DetalhesFornecedorActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    ProgressDialog dialog;
    TextView mTvApelido;
    TextView mTvEmail;
    TextView mTvDescricao;
    RatingBar rtbPontuacao;
    Button btnSalvarPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_fornecedor);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mTvApelido = (TextView) findViewById(R.id.tv_apelido);
        mTvEmail = (TextView) findViewById(R.id.tv_email);
        mTvDescricao = (TextView) findViewById(R.id.tv_descricao);
        rtbPontuacao = (RatingBar) findViewById(R.id.rtbPontuacao);
        btnSalvarPontuacao = (Button) findViewById(R.id.btn_salvar_pontuacao);
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tlb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Urbantick");

        btnSalvarPontuacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new ProgressDialog(DetalhesFornecedorActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();

                Fornecedor fornecedor = new Fornecedor();
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                int id = bundle.getInt("id");
                fornecedor.setPontuacao(rtbPontuacao.getRating());
                float pontuacao = fornecedor.getPontuacao();

                IUsuarioREST iUsuarioREST = IUsuarioREST.retrofit.create(IUsuarioREST.class);
                final Call<Void> call = iUsuarioREST.avaliarFornecedor(id, pontuacao);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        int code = response.code();

                        if(response.isSuccessful()) {
                            Toast.makeText(getBaseContext(), "Avaliação enviada com sucesso!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), "ERRO: " + String.valueOf(code), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Log.e("TAG", "ERRO: " + t.getMessage());
                        Toast.makeText(getBaseContext(), "Sem acesso a internet!", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }

    public HashMap getLocationFromAddress(String strAddress) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> address;
        HashMap<String, String> latlng = new HashMap<String, String>();

        try {
            address = geocoder.getFromLocationName(strAddress, 1);
            if (address.isEmpty()) {
                return null;
            }

            Address location = address.get(0);
            latlng.put("lat", location.getLatitude() + "");
            latlng.put("lng", location.getLongitude() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latlng;
    }



    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        Log.i("TAG", String.valueOf(id));

        IUsuarioREST iUsuarioREST = IUsuarioREST.retrofit.create(IUsuarioREST.class);
        dialog = new ProgressDialog(DetalhesFornecedorActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();

        final Call<Fornecedor> call = iUsuarioREST.listarFornecedor(id);

        call.enqueue(new Callback<Fornecedor>() {
            @Override
            public void onResponse(Call<Fornecedor> call, Response<Fornecedor> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                int code = response.code();

                final Fornecedor fornecedor = response.body();

                if(fornecedor != null) {

                    String strAddress = fornecedor.getLogradouro();

                    getLocationFromAddress(strAddress);

                    //Toast.makeText(getBaseContext(), "Usuário: " + fornecedor.getApelido(), Toast.LENGTH_LONG).show();
                    mTvApelido.setText(fornecedor.getApelido());
                    mTvEmail.setText(fornecedor.getEmail());
                    mTvDescricao.setText(fornecedor.getDescricao());
                    rtbPontuacao.setRating(fornecedor.getPontuacao());
                    //Log.i("TAG", "teste:" + fornecedor.getLogradouro());
                } else {
                    Toast.makeText(getBaseContext(), "ERRO: " + String.valueOf(code), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Fornecedor> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Log.e("TAG", "ERRO: " + t.getMessage());
                Toast.makeText(getBaseContext(), "Sem acesso a internet!", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng recife = new LatLng(-8.1515521,-34.9221166);
        mMap.addMarker(new MarkerOptions().position(recife).title("Unibratec"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(recife));
        float zoomLevel = (float) 16.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(recife, zoomLevel));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
