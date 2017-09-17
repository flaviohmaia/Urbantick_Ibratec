package adaz.urbantick_ibratec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final String TAG = SQLiteHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Urbantick";
    private static final String TABELA_USUARIO = "usuario";

    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SENHA = "senha";

    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABELA_USUARIO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_SENHA + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "Banco de dados criado");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIO);
        onCreate(db);
    }

    public void adicionar(String nome, String email, String senha){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, nome);
        values.put(KEY_EMAIL, email);
        values.put(KEY_SENHA, senha);

        long id = db.insert(TABELA_USUARIO, null, values);
        db.close();

        Log.d(TAG, "Novo usuário inserido no SQLite: " + id);
    }

    public HashMap<String, String> getDetalhesUsuario(){
        HashMap<String, String> usuario = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABELA_USUARIO;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            usuario.put("nome", cursor.getString(1));
            usuario.put("email", cursor.getString(2));
            usuario.put("senha", cursor.getString(3));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Detalhes do usuário: " + usuario.toString());

        return usuario;
    }

    public void deteteUsuario(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_USUARIO,null, null);
        db.close();

        Log.d(TAG, "Deletadas todas informações dos usuários do SQLite");
    }
}
