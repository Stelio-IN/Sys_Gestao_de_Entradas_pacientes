package Controller;

import Model.Especialidade;
import Model.Medico;
import Model.Pessoa;
import java.util.ArrayList;

public class MedicoController extends PessoaController {

    private ArrayList<Especialidade> especialidades = new ArrayList<>();

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
 
    public boolean cadastrarMedico(Especialidade especialidade, String crm, String codFunc,
            String password, String bi, String nome, String telefone,
            String endereco) {
        if (buscarPorBi(bi) != null) {
            return false; // Médico já existe
        }

        Medico novoMedico = new Medico(especialidade, crm, codFunc, password, bi, nome, telefone, endereco);
        pessoas.add(novoMedico);
        return true;
    }

    public Medico buscarPorCrm(String crm) {
        for (Pessoa p : pessoas) {
            if (p instanceof Medico) {
                Medico medico = (Medico) p;
                if (medico.getCrm().equals(crm)) {
                    return medico;
                }
            }
        }
        return null;
    }

    public ArrayList<Medico> listarMedicosPorEspecialidade(String especialidade) {
        ArrayList<Medico> resultados = new ArrayList<>();

        for (Pessoa p : pessoas) {
            if (p instanceof Medico) {
                Medico medico = (Medico) p;
                if (medico.getEspecialidade().getEspecialidade().equals(especialidade)) {
                    resultados.add(medico);
                }
            }
        }

        return resultados;
    }

    public boolean adicionarEspecialidade(String nome, String descricao) {
        for (Especialidade e : especialidades) {
            if (e.getEspecialidade().equals(nome)) {
                return false; // Especialidade já existe
            }
        }

        especialidades.add(new Especialidade(nome, descricao));
        return true;
    }
}
