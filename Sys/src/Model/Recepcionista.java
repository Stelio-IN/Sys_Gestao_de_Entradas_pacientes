/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.io.Serializable;

/**
 * Class representing a receptionist
 */
public class Recepcionista extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cod_func;
    private String password;
    private boolean status;
    
    public Recepcionista(String cod_func, String BI, String nome, String telefone, String endereco) {
        super(BI, nome, telefone, endereco);
        this.cod_func = cod_func;
        this.status = true;
        this.password = "0000";
    }

    public Recepcionista(String cod_func, String password, String BI, String nome, String telefone, String endereco) {
        super(BI, nome, telefone, endereco);
        this.cod_func = cod_func;
        this.password = password;
        this.status = true;
    }
    
    
    public String getCod_func() {
        return cod_func;
    }
    
    public void setCod_func(String cod_func) {
        this.cod_func = cod_func;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
}
