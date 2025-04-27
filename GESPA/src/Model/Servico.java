
package Model;

import java.io.Serializable;

/**
 * Class representing a medical service
 */
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String nomeServico;
    private String descricao;
    private boolean status;
    private double valor; // Added valor field
    
    public Servico(String id, String nomeServico, String descricao, double valor) {
        this.id = id;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.valor = valor;
        this.status = true;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNomeServico() {
        return nomeServico;
    }
    
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return nomeServico;
    }
}
