package adaz.urbantick_ibratec.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 09/10/17.
 */

public class Endereco implements Parcelable {

    private int id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String complemento;

    //CONSTRUTORES
    public Endereco(){

    }

    public Endereco(String logradouro, String numero, String bairro, String cidade, String uf, String cep, String complemento){
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.complemento = complemento;
    }

    protected Endereco(Parcel in) {
        id = in.readInt();
        logradouro = in.readString();
        numero = in.readString();
        bairro = in.readString();
        cidade = in.readString();
        uf = in.readString();
        cep = in.readString();
        complemento = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(logradouro);
        dest.writeString(numero);
        dest.writeString(bairro);
        dest.writeString(cidade);
        dest.writeString(uf);
        dest.writeString(cep);
        dest.writeString(complemento);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Endereco> CREATOR = new Creator<Endereco>() {
        @Override
        public Endereco createFromParcel(Parcel in) {
            return new Endereco(in);
        }

        @Override
        public Endereco[] newArray(int size) {
            return new Endereco[size];
        }
    };

    //GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
