package adaz.urbantick_ibratec.Model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String email;
    private String senha;

    public Usuario(int id, String email, String senha){
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    //GETTERS E SETTERS
    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
