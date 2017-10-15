package adaz.urbantick_ibratec.Model;

public class Fornecedor {

    private int id;
    private String nome;
    private String apelido;
    private String categoria;
    private String subcategoria;
    private String telefone;
    private String descricao;
    private Endereco endereco;
    private Usuario usuario;

    public Fornecedor(){ }

    public Fornecedor(int id, String nome, String apelido, String categoria, String subcategoria,
                      String telefone, String descricao, Endereco endereco, Usuario usuario){
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.telefone = telefone;
        this.descricao = descricao;
        this.usuario = usuario;
        this.endereco = endereco;
    }

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
