
package Model;

import java.io.Serializable;


public class Especialidade implements Serializable {
    private static final long serialVersionUID = 1L;
    private String especialidade;
    private String descricao;
    private boolean status;
    
    public Especialidade(String especialidade, String descricao) {
        this.especialidade = especialidade;
        this.descricao = descricao;
        this.status = true;
    }
    
    public String getEspecialidade() {
        return especialidade;
    }
    
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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
    
    
    @Override
    public String toString() {
        return especialidade;
    }
}
