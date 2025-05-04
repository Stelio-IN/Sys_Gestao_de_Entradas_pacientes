package Controller;

import Model.Servico;
import java.util.ArrayList;

public class ServicoController {
    
    Ficheiro f1 = new Ficheiro();
    private ArrayList<Servico> servicos = new ArrayList<>();
    
    public boolean cadastrarServico(String id, String nome, String descricao, double valor) {
        for (Servico s : servicos) {
            if (s.getId().equals(id) || s.getNomeServico().equalsIgnoreCase(nome)) {
                return false; // Serviço já existe
            }
        }
        
        servicos.add(new Servico(id, nome, descricao, valor));
        return true;
    }
    
    public Servico buscarServicoPorId(String id) {
        for (Servico s : servicos) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
    
    public Servico buscarServicoPorNome(String nome) {
        for (Servico s : servicos) {
            if (s.getNomeServico().equalsIgnoreCase(nome)) {
                return s;
            }
        }
        return null;
    }
    
    public ArrayList<Servico> listarTodosServicos() {
        servicos = f1.carregarDoArquivo("servico");
        return servicos;
    }
    
    public boolean atualizarServico(String id, String novoNome, String novaDescricao, double novoValor) {
        Servico servico = buscarServicoPorId(id);
        if (servico != null) {
            servico.setNomeServico(novoNome);
            servico.setDescricao(novaDescricao);
            servico.setValor(novoValor);
            return true;
        }
        return false;
    }
}