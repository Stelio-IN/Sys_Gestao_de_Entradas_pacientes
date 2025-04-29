package Controller;

import Model.Acompanhante;
import java.util.ArrayList;

public class AcompanhanteController {
    private ArrayList<Acompanhante> acompanhantes = new ArrayList<>();
    
    public boolean cadastrarAcompanhante(String bi, String nome, String telefone, 
                                       String endereco, String tipo, String sexo) {
        for (Acompanhante a : acompanhantes) {
            if (a.getBI().equals(bi)) {
                return false; // Acompanhante já existe
            }
        }
        
        Acompanhante novoAcompanhante = new Acompanhante(tipo, sexo, bi, nome, telefone, endereco);
        acompanhantes.add(novoAcompanhante);
        return true;
    }
    
    public Acompanhante buscarAcompanhantePorBi(String bi) {
        for (Acompanhante a : acompanhantes) {
            if (a.getBI().equals(bi)) {
                return a;
            }
        }
        return null;
    }
    
    public ArrayList<Acompanhante> listarAcompanhantesPorPaciente(String biPaciente) {
        // Em um sistema real, essa relação seria mantida em um repositório
        // Aqui retornamos todos os acompanhantes como exemplo
        return acompanhantes;
    }
}