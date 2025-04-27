/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Stelio Mondlane
 */
public class Especialidade implements Serializable{
    private String especialidade;
    private String descricao;

    public Especialidade(String especialidade, String descricao) {
        this.especialidade = especialidade;
        this.descricao = descricao;
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
    
}
