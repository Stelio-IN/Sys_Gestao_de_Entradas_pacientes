package Controller;

import Model.Especialidade;
import Model.Gestor;
import Model.Medico;
import Model.Pessoa;
import Model.Recepcionista;
import Model.Servico;
import java.util.ArrayList;

public class GestorController {

    Ficheiro f1 = new Ficheiro();
    ArrayList<Pessoa> lista = new ArrayList<>();
    ArrayList<Servico> listaServico = new ArrayList<>();
    ArrayList<Gestor> listaGestor = new ArrayList<>();
   
    public boolean loginGestor(String email, String password){
       listaGestor  = f1.carregarDoArquivo("gestor");
       Gestor a = listaGestor.get(0);
        if(a.getEmail().equals(email) && a.getPassword().equals(password)){
            return true;
        }
        return false; 
    }
    
    
    
    // ==================== MÉDICOS ====================
    public void cadastrarMedico(Especialidade especialidade, String crm, String cod_func, String password, String BI, String nome, String telefone, String endereco) {
        lista = f1.carregarDoArquivo("pessoa");
        Medico a = new Medico(especialidade, crm, cod_func, password, BI, nome, telefone, endereco);
        lista.add(a);
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void editarMedico(String crm, Especialidade especialidade, String password, String endereco, String telefone) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Medico) {
                Medico med = (Medico) a;
                if (med.getCrm().equals(crm)) {
                    med.setEndereco(endereco);
                    med.setTelefone(telefone);
                    med.setPassword(password);
                }
            }
        }
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void desativarMedico(String crm) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Medico) {
                Medico med = (Medico) a;
                if (med.getCrm().equals(crm)) {
                    med.setStatus(false);
                }
            }
        }
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void reativarMedico(String crm) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Medico) {
                Medico med = (Medico) a;
                if (med.getCrm().equals(crm)) {
                    med.setStatus(true);
                }
            }
        }
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void listarMedico() {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Medico) {
                Medico med = (Medico) a;
                if (med.isStatus()) {
                    System.out.println(med);
                }
            }
        }
    }

    // ==================== RECEPCIONISTAS ====================
    public void cadastrarRecepcionista(String cod_func, String BI, String nome, String telefone, String endereco) {
        lista = f1.carregarDoArquivo("pessoa");
        Recepcionista r = new Recepcionista(cod_func,BI, nome, telefone, endereco);
        lista.add(r);
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void editarRecepcionista(String cod_func, String password, String endereco, String telefone) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Recepcionista) {
                Recepcionista r = (Recepcionista) a;
                if (r.getCod_func().equals(cod_func)) {
                    r.setPassword(password);
                    r.setEndereco(endereco);
                    r.setTelefone(telefone);
                }
            }
        }
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void desativarRecepcionista(String cod_func) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Recepcionista) {
                Recepcionista r = (Recepcionista) a;
                if (r.getCod_func().equals(cod_func)) {
                    r.setStatus(false);
                }
            }
        }
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void reativarRecepcionista(String cod_func) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Recepcionista) {
                Recepcionista r = (Recepcionista) a;
                if (r.getCod_func().equals(cod_func)) {
                    r.setStatus(true);
                }
            }
        }
        f1.gravarEmArquivo(lista, "pessoa");
    }

    public void listarRecepcionistas() {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Recepcionista) {
                Recepcionista r = (Recepcionista) a;
                if (r.isStatus()) {
                    System.out.println(r);
                }
            }
        }
    }

    // ==================== SERVIÇOS ====================
    public void cadastrarServico(String id, String nomeServico, String descricao) {
        listaServico = f1.carregarDoArquivo("servico");
        Servico s = new Servico(id, nomeServico, descricao);
        listaServico.add(s);
        f1.gravarEmArquivo(listaServico, "servico");
    }

    public void editarServico(String id, String novoNomeServico, String novaDescricao) {
        listaServico = f1.carregarDoArquivo("servico");
        for (Servico s : listaServico) {
            if (s.getId().equals(id)) {
                s.setNomeServico(novoNomeServico);
                s.setDescricao(novaDescricao);
            }
        }
        f1.gravarEmArquivo(listaServico, "servico");
    }

    public void desativarServico(String id) {
        listaServico = f1.carregarDoArquivo("servico");
        for (Servico s : listaServico) {
            if (s.getId().equals(id)) {
                s.setStatus(false);
            }
        }
        f1.gravarEmArquivo(listaServico, "servico");
    }

    public void activarServico(String id) {
        listaServico = f1.carregarDoArquivo("servico");
        for (Servico s : listaServico) {
            if (s.getId().equals(id)) {
                s.setStatus(true);
            }
        }
        f1.gravarEmArquivo(listaServico, "servico");
    }

    public void listarServicos() {
        listaServico = f1.carregarDoArquivo("servico");
        for (Servico s : listaServico) {
            if (s.isStatus()) {
                System.out.println(s);
            }
        }
    }
}
