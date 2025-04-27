/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.io.Serializable;

/**
 * Class representing a doctor
 */
public class Medico extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private Especialidade especialidade;
    private String crm;
    private String cod_func;
    private String password;
    private boolean status;
    
    public Medico(Especialidade especialidade, String crm, String cod_func, 
                 String password, String BI, String nome, String telefone, String endereco) {
        super(BI, nome, telefone, endereco);
        this.especialidade = especialidade;
        this.crm = crm;
        this.cod_func = cod_func;
        this.password = password;
        this.status = true;
    }
    
    public Especialidade getEspecialidade() {
        return especialidade;
    }
    
    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
    
    public String getCrm() {
        return crm;
    }
    
    public void setCrm(String crm) {
        this.crm = crm;
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
    
    // Remove redundant overrides that cause infinite recursion
    // Using super.method() is unnecessary since there's no override
    
    @Override
    public String toString() {
        return "Medico{" + super.toString() + ", especialidade=" + especialidade + 
               ", crm=" + crm + ", cod_func=" + cod_func + ", status=" + status + '}';
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
}
