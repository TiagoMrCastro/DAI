
package backend;

public class Utilizador {
    private String id;
    private String pw;
    private String tipo;

    public Utilizador() {}

    public Utilizador(String id, String pw, String tipo) {
        this.id = id;
        this.pw = pw;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

 
    public String getPassword() {
        return pw;
    }

    public void setPassword(String pw) {
        this.pw = pw;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

        
}