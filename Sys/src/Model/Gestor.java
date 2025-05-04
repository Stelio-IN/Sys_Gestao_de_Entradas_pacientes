
package Model;


import java.io.Serializable;

/**
 * Class representing a system manager
 */
public class Gestor extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cod;
    private String email;
    private String password;

    public Gestor(String cod, String email, String password, String BI, String nome, String telefone, String endereco) {
        super(BI, nome, telefone, endereco);
        this.cod = cod;
        this.email = email;
        this.password = password;
    }

    public Gestor(String cod, String email, String password) {
        this.cod = cod;
        this.email = email;
        this.password = password;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
