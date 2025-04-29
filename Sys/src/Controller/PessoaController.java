package Controller;

import Model.Pessoa;
import java.util.ArrayList;

public abstract class PessoaController {
    protected ArrayList<Pessoa> pessoas = new ArrayList<>();
    
    public Pessoa buscarPorBi(String bi) {
        for (Pessoa p : pessoas) {
            if (p.getBI().equals(bi)) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Pessoa> listarTodos() {
        return pessoas;
    }
    
    public boolean atualizarPessoa(Pessoa pessoaAtualizada) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getBI().equals(pessoaAtualizada.getBI())) {
                pessoas.set(i, pessoaAtualizada);
                return true;
            }
        }
        return false;
    }
}