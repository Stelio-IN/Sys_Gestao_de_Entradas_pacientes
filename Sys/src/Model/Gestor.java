
package Model;


import java.io.Serializable;

/**
 * Class representing a system manager
 */
public class Gestor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cod;
    private String email;
    private String password;
    
    public Gestor(String cod, String email, String password) {
        this.cod = cod;
        this.email = email;
        this.password = password;
    }

    public Gestor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
