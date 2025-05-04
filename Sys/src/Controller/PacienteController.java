package Controller;

import Model.Medico;
import Model.Paciente;
import Model.Pessoa;
import java.util.ArrayList;

public class PacienteController extends PessoaController {

    Ficheiro f1 = new Ficheiro();
    ArrayList<Pessoa> lista = new ArrayList<>();
     
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

    
    public boolean cadastrarPaciente(String bi, String nome, String telefone, String endereco, 
                                   String dataNasc, String genero, String estado) {
        if (buscarPorBi(bi) != null) {
            return false; // Paciente já existe
        }
        
        lista = f1.carregarDoArquivo("pessoa");
        Paciente novoPaciente = new Paciente(bi, nome, telefone, endereco);
       
        novoPaciente.setDataNasc(dataNasc);
        novoPaciente.setGenero(genero);
        novoPaciente.setEstado(estado);
        
        lista.add(novoPaciente);
        f1.gravarEmArquivo(lista, "pessoa");
       // pessoas.add(novoPaciente);
        return true;
    }
    
    public ArrayList<Paciente> filtrarPacientes(String filtro) {
        ArrayList<Paciente> resultados = new ArrayList<>();
        pessoas = f1.carregarDoArquivo("pessoa");
        for (Pessoa p : pessoas) {
            if (p instanceof Paciente) {
                Paciente paciente = (Paciente) p;
                if (paciente.getNome().toLowerCase().contains(filtro.toLowerCase()) ||
                    paciente.getBI().toLowerCase().contains(filtro.toLowerCase())) {
                    resultados.add(paciente);
                }
            }
        }
        
        return resultados;
    }
    
    public boolean atualizarEstadoPaciente(String bi, String novoEstado) {
        Paciente paciente = (Paciente) buscarPorBi(bi);
        if (paciente != null) {
            paciente.setEstado(novoEstado);
            return true;
        }
        return false;
    }
    
}