/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Stelio Mondlane
 */
public class Medico extends Pessoa {
    private Especialidade especialidade;
    private String crm;
    private String cod_func;
    private String password;
    private boolean status;  

    public Medico(Especialidade especialidade, String crm, String cod_func, String password, String BI, String nome, String telefone, String endereco) {
        super(BI, nome, telefone, endereco);
        this.especialidade = especialidade;
        this.crm = crm;
        this.cod_func = cod_func;
        this.password = password;
        status = true;
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


    public String getNome() {
        return getNome();
    }

    public void setNome(String nome) {
        this.setNome(nome);
    }

    public String getTelefone() {
        return getTelefone();
    }

    public void setTelefone(String telefone) {
        this.setTelefone(telefone);
    }

    public String getEndereco() {
        return getEndereco();
    }

    public void setEndereco(String endereco) {
        this.setEndereco(endereco);
    }

    @Override
    public String toString() {
        return "Medico{" + "especialidade=" + especialidade + ", crm=" + crm + ", cod_func=" + cod_func + ", password=" + password + ", status=" + status + '}';
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
