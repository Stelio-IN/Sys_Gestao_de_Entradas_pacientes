package Controller;

import Model.Pessoa;
import Model.Recepcionista;
import java.util.ArrayList;

public class RecepcionistaController extends PessoaController {

    @Override
    public boolean atualizarPessoa(Pessoa pessoaAtualizada) {
        return super.atualizarPessoa(pessoaAtualizada); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public ArrayList<Pessoa> listarTodos() {
        return super.listarTodos(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Pessoa buscarPorBi(String bi) {
        return super.buscarPorBi(bi); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public boolean cadastrarRecepcionista(String codFunc, String password, String bi, 
                                        String nome, String telefone, String endereco) {
        if (buscarPorBi(bi) != null) {
            return false; // Recepcionista já existe
        }
        
        Recepcionista novoRecepcionista = new Recepcionista(codFunc, password, bi, nome, telefone, endereco);
        pessoas.add(novoRecepcionista);
        return true;
    }
    
    public Recepcionista buscarPorCodigoFuncionario(String codFunc) {
        for (Pessoa p : pessoas) {
            if (p instanceof Recepcionista) {
                Recepcionista recepcionista = (Recepcionista) p;
                if (recepcionista.getCod_func().equals(codFunc)) {
                    return recepcionista;
                }
            }
        }
        return null;
    }
    
    public boolean autenticarRecepcionista(String codFunc, String password) {
        Recepcionista recepcionista = buscarPorCodigoFuncionario(codFunc);
        return recepcionista != null && recepcionista.getPassword().equals(password);
    }
}