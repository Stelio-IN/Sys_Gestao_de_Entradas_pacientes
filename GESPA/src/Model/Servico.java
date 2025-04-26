
package Model;

import java.io.Serializable;


public class Servico implements Serializable {
    private String id;
    private String nomeServico;
    private String descricao;
    private boolean status;

    public Servico(String id, String nomeServico, String descricao) {
        this.id = id;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
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
    
  
}
