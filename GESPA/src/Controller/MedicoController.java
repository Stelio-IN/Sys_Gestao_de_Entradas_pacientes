package Controller;

import Model.Especialidade;
import Model.Medico;
import Model.Paciente;
import Model.Consulta;
import Model.Consulta.StatusConsulta;
import Model.Pessoa;
import Model.Servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class MedicoController {
    
    // Simulando um repositório de dados
    private static ArrayList<Medico> medicos = new ArrayList<>();
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Consulta> consultas = new ArrayList<>();
    private static ArrayList<Servico> servicos = new ArrayList<>();
    private static ArrayList<Especialidade> especialidades = new ArrayList<>();
    
    // Construtor
    public MedicoController() {
        // Inicializar alguns dados para teste se necessário
        if (medicos.isEmpty()) {
            inicializarDadosTeste();
        }
    }
    
    // Método para inicializar dados de teste
    private void inicializarDadosTeste() {
        // Adicionar algumas especialidades de teste
        Especialidade cardio = new Especialidade("CARDIOLOGIA", "Especialidade que trata de doenças do coração");
        Especialidade pediatria = new Especialidade("PEDIATRIA", "Especialidade que trata de crianças e adolescentes");
        
        especialidades.add(cardio);
        especialidades.add(pediatria);
        
        // Adicionar alguns médicos de teste
        Medico medico1 = new Medico(cardio, "CRM12345", "F001", "senha123", "12345", "Dr. João Silva", "923456789", "Av. Principal, 123");
        Medico medico2 = new Medico(pediatria, "CRM67890", "F002", "senha456", "67890", "Dra. Maria Santos", "934567890", "Rua Secundária, 456");
        
        medicos.add(medico1);
        medicos.add(medico2);
        
        // Adicionar alguns pacientes de teste
        Paciente paciente1 = new Paciente("23456789", "Carlos Pereira", "912345678", "Rua da Saúde, 789");
        paciente1.setDataNasc("1980"); // Ajustado para usar o formato int conforme a classe Paciente
        paciente1.setGenero("M");
        
        Paciente paciente2 = new Paciente("34567890", "Ana Costa", "923456789", "Av. Central, 567");
        paciente2.setDataNasc("1990");
        paciente2.setGenero("F");
        
        pacientes.add(paciente1);
        pacientes.add(paciente2);
        
        // Adicionar alguns serviços de teste
        Servico servico1 = new Servico("S001", "Consulta de Rotina", "Consulta médica de rotina", 50.0);
        Servico servico2 = new Servico("S002", "Exame Cardíaco", "Exame de avaliação cardíaca", 100.0);
        
        servicos.add(servico1);
        servicos.add(servico2);
        
        // Adicionar algumas consultas de teste usando o enum StatusConsulta
        Consulta consulta1 = new Consulta("C001", paciente1, medico1, servico1, 
                "2025-04-25", "10:00", StatusConsulta.EM_ANDAMENTO, null, null, null);
        Consulta consulta2 = new Consulta("C002", paciente2, medico2, servico2, 
                "2025-04-26", "14:30", StatusConsulta.PENDENTE, null, null, null);
        Consulta consulta3 = new Consulta("C003", paciente1, medico2, servico1, 
                "2025-04-24", "09:15", StatusConsulta.FINALIZADA, "Gripe comum", "Paracetamol 500mg", "Repouso por 3 dias");
        
        consultas.add(consulta1);
        consultas.add(consulta2);
        consultas.add(consulta3);
    }
    
    // Método para buscar especialidade por nome
    public Especialidade buscarEspecialidadePorNome(String nome) {
        for (Especialidade esp : especialidades) {
            if (esp.getEspecialidade().equals(nome)) {
                return esp;
            }
        }
        return null;
    }
    
    Ficheiro f1 = new Ficheiro();
    ArrayList<Pessoa> lista = new ArrayList<>();
    /*
    for (Pessoa a : lista) {
            if (a instanceof Medico) {
                Medico med = (Medico) a;
                if (med.getCrm().equals(crm)) {
                   return m;
                }
            }
        }
    */
  
    // Buscar médico pelo CRM
    public Medico buscarMedicoPorCrm(String crm) {
        lista = f1.carregarDoArquivo("pessoa");
        for (Pessoa a : lista) {
            if (a instanceof Medico) {
                Medico med = (Medico) a;
                if (med.getCrm().equals(crm)) {
                   return med;
                }
            }
        }
        return null;
    }
    
    // Atualizar perfil do médico
    public boolean atualizarPerfilMedico(String crm, String password, String endereco, String telefone) {
        Medico medico = buscarMedicoPorCrm(crm);
        if (medico != null) {
            medico.setPassword(password);
            medico.setEndereco(endereco);
            medico.setTelefone(telefone);
            return true;
        }
        return false;
    }
    
    // Listar pacientes por médico
    public ArrayList<Paciente> listarPacientesPorMedico(String crm) {
        ArrayList<Paciente> pacientesMedico = new ArrayList<>();
        
        // Em um sistema real, haveria uma relação médico-paciente no banco de dados
        // Aqui vamos simular isso através das consultas
        for (Consulta c : consultas) {
            if (c.getMedico().getCrm().equals(crm) && !pacientesMedico.contains(c.getPaciente())) {
                pacientesMedico.add(c.getPaciente());
            }
        }
        
        return pacientesMedico;
    }
    
    // Buscar paciente por BI (equivalente ao "número de processo" no código original)
    public Paciente buscarPacientePorBI(String bi) {
        for (Paciente p : pacientes) {
            if (p.getBI().equals(bi)) {
                return p;
            }
        }
        return null;
    }
    
    // Pesquisar pacientes por nome ou BI
    public ArrayList<Paciente> pesquisarPacientes(String crm, String termo) {
        ArrayList<Paciente> resultado = new ArrayList<>();
        ArrayList<Paciente> pacientesMedico = listarPacientesPorMedico(crm);
        
        for (Paciente p : pacientesMedico) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase()) || 
                p.getBI().toLowerCase().contains(termo.toLowerCase())) {
                resultado.add(p);
            }
        }
        
        return resultado;
    }
    
    // Listar consultas por médico
    public ArrayList<Consulta> listarConsultasPorMedico(String crm) {
        ArrayList<Consulta> consultasMedico = new ArrayList<>();
        
        for (Consulta c : consultas) {
            if (c.getMedico().getCrm().equals(crm)) {
                consultasMedico.add(c);
            }
        }
        
        return consultasMedico;
    }
    
    // Filtrar consultas por critério
    public ArrayList<Consulta> filtrarConsultas(String crm, String filtro) {
        ArrayList<Consulta> consultasMedico = listarConsultasPorMedico(crm);
        ArrayList<Consulta> resultado = new ArrayList<>();
        
        // Data atual para comparação (formato YYYY-MM-DD)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dataAtual = sdf.format(new Date());
        
        switch(filtro) {
            case "Todas as Consultas":
                return consultasMedico;
                
            case "Consultas para Hoje":
                for (Consulta c : consultasMedico) {
                    if (c.getData().equals(dataAtual)) {
                        resultado.add(c);
                    }
                }
                break;
                
            case "Consultas Pendentes":
                for (Consulta c : consultasMedico) {
                    if (c.getStatus() == StatusConsulta.PENDENTE) {
                        resultado.add(c);
                    }
                }
                break;
                
            case "Consultas Realizadas":
                for (Consulta c : consultasMedico) {
                    if (c.getStatus() == StatusConsulta.FINALIZADA) {
                        resultado.add(c);
                    }
                }
                break;
                
            case "Consultas Canceladas":
                for (Consulta c : consultasMedico) {
                    if (c.getStatus() == StatusConsulta.CANCELADA) {
                        resultado.add(c);
                    }
                }
                break;
                
            case "Consultas Em Andamento":
                for (Consulta c : consultasMedico) {
                    if (c.getStatus() == StatusConsulta.EM_ANDAMENTO) {
                        resultado.add(c);
                    }
                }
                break;
        }
        
        return resultado;
    }
    
    // Atualizar status de uma consulta
    public boolean atualizarStatusConsulta(String idConsulta, StatusConsulta novoStatus) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus(novoStatus);
                return true;
            }
        }
        return false;
    }
    
    // Overload para compatibilidade com String status
    public boolean atualizarStatusConsulta(String idConsulta, String novoStatus) {
        try {
            StatusConsulta status = StatusConsulta.valueOf(novoStatus);
            return atualizarStatusConsulta(idConsulta, status);
        } catch (IllegalArgumentException e) {
            System.err.println("Status inválido: " + novoStatus);
            return false;
        }
    }
    
    // Finalizar uma consulta com diagnóstico e medicação
    public boolean finalizarConsulta(String idConsulta, String diagnostico, String medicacao, String observacoes) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus(StatusConsulta.FINALIZADA);
                c.setDiagnostico(diagnostico);
                c.setMedicacao(medicacao);
                c.setObservacoes(observacoes);
                return true;
            }
        }
        return false;
    }
    
    // Cancelar uma consulta
    public boolean cancelarConsulta(String idConsulta, String motivo) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus(StatusConsulta.CANCELADA);
                c.setObservacoes(motivo); // Usando o campo observações para o motivo do cancelamento
                return true;
            }
        }
        return false;
    }
    
    // Listar histórico de consultas de um paciente
    public ArrayList<Consulta> listarHistoricoConsultas(String bi) {
        ArrayList<Consulta> historico = new ArrayList<>();
        
        for (Consulta c : consultas) {
            if (c.getPaciente().getBI().equals(bi) && c.getStatus() == StatusConsulta.FINALIZADA) {
                historico.add(c);
            }
        }
        
        return historico;
    }
    
    // Adicionar observação ao histórico de um paciente
    public boolean adicionarObservacaoHistorico(String bi, String crm, String observacao) {
        // Em um sistema real, isso poderia criar um novo registro de histórico
        // Aqui vamos apenas criar uma nova consulta "finalizada" para simular
        
        Paciente paciente = buscarPacientePorBI(bi);
        Medico medico = buscarMedicoPorCrm(crm);
        
        if (paciente == null || medico == null) {
            return false;
        }
        
        // Criar um novo ID
        String novoId = gerarIdConsulta();
        
        // Data atual para a observação
        String dataAtual = obterDataAtual();
        String horaAtual = obterHoraAtual();
        
        // Criar nova consulta/observação
        Consulta novaObservacao = new Consulta(
            novoId,
            paciente,
            medico,
            buscarOuCriarServicoObservacao(), // Usando um serviço específico para observações
            dataAtual,
            horaAtual,
            StatusConsulta.FINALIZADA,
            "Observação Médica",
            "N/A",
            observacao
        );
        
        consultas.add(novaObservacao);
        return true;
    }
    
    // Método para buscar ou criar um serviço de observação médica
    private Servico buscarOuCriarServicoObservacao() {
        // Verifica se já existe um serviço de observação
        for (Servico s : servicos) {
            if (s.getNomeServico().equals("Observação Médica")) {
                return s;
            }
        }
        
        // Se não existe, cria um novo
        Servico servicoObservacao = new Servico("S-OBS", "Observação Médica", "Registro de observação médica", 0.0);
        servicos.add(servicoObservacao);
        return servicoObservacao;
    }
    
    // Método para gerar ID de consulta
    private String gerarIdConsulta() {
        String novoId = "C" + (consultas.size() + 1);
        while (novoId.length() < 4) {
            novoId = novoId.substring(0, 1) + "0" + novoId.substring(1);
        }
        return novoId;
    }
    
    // Método para obter a data atual formatada
    private String obterDataAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
    
    // Método para obter a hora atual formatada
    private String obterHoraAtual() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }
    
    // Métodos adicionais para acesso aos dados
    public ArrayList<Medico> getMedicos() {
        return medicos;
    }
    
    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
    
    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }
    
    public ArrayList<Servico> getServicos() {
        return servicos;
    }
    
    public ArrayList<Especialidade> getEspecialidades() {
        return especialidades;
    }
    
    // Adicionar novos médicos
    public boolean adicionarMedico(Medico medico) {
        if (buscarMedicoPorCrm(medico.getCrm()) == null) {
            medicos.add(medico);
            return true;
        }
        return false;
    }
    
    // Adicionar novos pacientes
    public boolean adicionarPaciente(Paciente paciente) {
        if (buscarPacientePorBI(paciente.getBI()) == null) {
            pacientes.add(paciente);
            return true;
        }
        return false;
    }
    
    // Adicionar novas consultas
    public boolean agendarConsulta(Consulta consulta) {
        // Verificar se já existe consulta com o mesmo ID
        for (Consulta c : consultas) {
            if (c.getId().equals(consulta.getId())) {
                return false;
            }
        }
        
        consultas.add(consulta);
        return true;
    }
    
    // Adicionar novos serviços
    public boolean adicionarServico(Servico servico) {
        for (Servico s : servicos) {
            if (s.getId().equals(servico.getId())) {
                return false;
            }
        }
        
        servicos.add(servico);
        return true;
    }
    
    // Adicionar novas especialidades
    public boolean adicionarEspecialidade(Especialidade especialidade) {
        for (Especialidade e : especialidades) {
            if (e.getEspecialidade().equals(especialidade.getEspecialidade())) {
                return false;
            }
        }
        
        especialidades.add(especialidade);
        return true;
    }

    // Método para adicionar observação ao histórico de um paciente
    public boolean adicionarObservacaoAoHistorico(String bi, String crm, String observacao) {
        try {
            // Verifica se o paciente existe
            Paciente paciente = buscarPacientePorBI(bi);
            if (paciente == null) {
                return false;
            }
            
            // Verifica se o médico existe
            Medico medico = buscarMedicoPorCrm(crm);
            if (medico == null) {
                return false;
            }
            
            // Cria uma nova consulta do tipo "OBSERVACAO" para registrar no histórico
            Consulta novaObservacao = new Consulta();
            novaObservacao.setId(gerarIdConsulta());
            novaObservacao.setPaciente(paciente);
            novaObservacao.setMedico(medico);
            novaObservacao.setData(obterDataAtual());
            novaObservacao.setHora(obterHoraAtual());
            
            // Cria ou usa um serviço genérico para observação
            Servico servicoObservacao = buscarOuCriarServicoObservacao();
            novaObservacao.setServico(servicoObservacao);
            
            // Define detalhes da observação
            novaObservacao.setStatus(StatusConsulta.FINALIZADA); // Já finalizada por ser apenas uma observação
            novaObservacao.setDiagnostico("Observação Adicional");
            novaObservacao.setObservacoes(observacao);
            
            // Adiciona a observação ao repositório de consultas
            consultas.add(novaObservacao);
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao adicionar observação ao histórico: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar histórico de um paciente
    public ArrayList<Consulta> buscarHistoricoPaciente(String bi) {
        try {
            // Verifica se o paciente existe
            Paciente paciente = buscarPacientePorBI(bi);
            if (paciente == null) {
                return new ArrayList<>(); // Retorna lista vazia se o paciente não for encontrado
            }
            
            // Obtém todas as consultas do paciente
            ArrayList<Consulta> consultasDoPaciente = new ArrayList<>();
            
            // Filtra apenas as consultas do paciente especificado
            for (Consulta consulta : consultas) {
                if (consulta.getPaciente().getBI().equals(bi)) {
                    consultasDoPaciente.add(consulta);
                }
            }
            
            // Ordena as consultas por data (mais recente primeiro)
            Collections.sort(consultasDoPaciente, new Comparator<Consulta>() {
                @Override
                public int compare(Consulta c1, Consulta c2) {
                    try {
                        // Comparação de data (assumindo formato YYYY-MM-DD)
                        String[] data1Parts = c1.getData().split("-");
                        String[] data2Parts = c2.getData().split("-");
                        
                        int ano1 = Integer.parseInt(data1Parts[0]);
                        int mes1 = Integer.parseInt(data1Parts[1]);
                        int dia1 = Integer.parseInt(data1Parts[2]);
                        
                        int ano2 = Integer.parseInt(data2Parts[0]);
                        int mes2 = Integer.parseInt(data2Parts[1]);
                        int dia2 = Integer.parseInt(data2Parts[2]);
                        
                        if (ano1 != ano2) return ano2 - ano1; // Ordem decrescente por ano
                        if (mes1 != mes2) return mes2 - mes1; // Ordem decrescente por mês
                        return dia2 - dia1; // Ordem decrescente por dia
                    } catch (Exception e) {
                        System.err.println("Erro ao comparar datas: " + e.getMessage());
                        return 0;
                    }
                }
            });
            
            return consultasDoPaciente;
        } catch (Exception e) {
            System.err.println("Erro ao buscar histórico do paciente: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retorna lista vazia em caso de erro
        }
    }
}