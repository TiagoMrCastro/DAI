
package backend;

/**
 *
 * @author tiago0
 */
public class Administrador extends Utilizador {
    private String nome;
    private String email;

    public Administrador() {
    }

    public Administrador(String nome, String email, String id, String pw, String tipo) {
        super(id, pw, tipo);
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    
    
    
}