package adaz.urbantick_ibratec.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Fornecedor implements Parcelable{

    private int id;
    private String nome;
    private String apelido;
    private String categoria;
    private String subcategoria;
    private String telefone;
    private String descricao;
    private float pontuacao;
    private String email;
    private Endereco endereco;
    private Usuario usuario;

    public Fornecedor(){ }

    public Fornecedor(int id, String nome, String apelido, String categoria, String subcategoria,
                      String telefone, String descricao, float pontuacao, String email, Endereco endereco, Usuario usuario){
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.telefone = telefone;
        this.descricao = descricao;
        this.pontuacao = pontuacao;
        this.email = email;
        this.usuario = usuario;
        this.endereco = endereco;
    }

    protected Fornecedor(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        apelido = in.readString();
        categoria = in.readString();
        subcategoria = in.readString();
        telefone = in.readString();
        descricao = in.readString();
        pontuacao = in.readFloat();
        email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nome);
        parcel.writeString(apelido);
        parcel.writeString(categoria);
        parcel.writeString(subcategoria);
        parcel.writeString(telefone);
        parcel.writeString(descricao);
        parcel.writeFloat(pontuacao);
        parcel.writeString(email);
        //parcel.write(descricao);
        //parcel.writeString(descricao);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Fornecedor createFromParcel(Parcel in) {
            return new Fornecedor(in);
        }

        public Fornecedor[] newArray(int size) {
            return new Fornecedor[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(float pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
