/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 * Class representing a medical specialty
 */
public class Especialidade implements Serializable {
    private static final long serialVersionUID = 1L;
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
    
    @Override
    public String toString() {
        return especialidade;
    }
}
