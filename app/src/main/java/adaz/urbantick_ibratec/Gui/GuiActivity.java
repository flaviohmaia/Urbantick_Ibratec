package adaz.urbantick_ibratec.Gui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import adaz.urbantick_ibratec.R;

public class GuiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Organizando o menu Drawer

    private NavigationView mNagivationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mOpcaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui);
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tlb);
        getSupportActionBar().setTitle(R.string.urbantick);

        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, tlb, R.string.app_name, R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNagivationView = (NavigationView) findViewById(R.id.navigation_view);
        mNagivationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selecionarOpcaoMenu(menuItem);
                        return true;
                    }
                }
        );

        if (savedInstanceState == null){
            mOpcaoSelecionada = R.id.action_opca4;
        }else{
            mOpcaoSelecionada = savedInstanceState.getInt("menuItem");
        }
        selecionarOpcaoMenu(mNagivationView.getMenu().findItem(mOpcaoSelecionada));
    }

    //Selecionando Opção do menu
    private void selecionarOpcaoMenu(MenuItem menuItem){
        mOpcaoSelecionada = menuItem.getItemId();
        menuItem.setCheckable(true);
        mDrawerLayout.closeDrawers();
//        if(mOpcaoSelecionada == R.id.action_opcao1){
//            Intent intent = new Intent(this, PromocoesActivity.class);
//            startActivity(intent);
//        }else if(mOpcaoSelecionada == R.id.action_opcao2){
//            Intent intent = new Intent(this, FavoritosActivity.class);
//            startActivity(intent);
//        }else if (mOpcaoSelecionada == R.id.action_opcao3){
//            Intent intent = new Intent(this, SugestoesActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
