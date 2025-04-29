package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RecepcionistaController {
    
    // Simulando um repositório de dados
    private static ArrayList<Recepcionista> recepcionistas = new ArrayList<>();
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Consulta> consultas = new ArrayList<>();
    private static ArrayList<Servico> servicos = new ArrayList<>();
    private static ArrayList<RegistroEntrada> registros = new ArrayList<>();
    private static ArrayList<Acompanhante> acompanhantes = new ArrayList<>();
    
    // Construtor
    public RecepcionistaController() {
        // Inicializar alguns dados para teste se necessário
        if (recepcionistas.isEmpty()) {
            inicializarDadosTeste();
        }
    }
    
    // Método para inicializar dados de teste
    private void inicializarDadosTeste() {
        // Adicionar alguns recepcionistas de teste
        Recepcionista recepcionista1 = new Recepcionista("R001", "senha123","12345", "Ana Silva", "923456789", "Av. Principal, 123");
        Recepcionista recepcionista2 = new Recepcionista("R002", "senha456","67890", "Carlos Santos", "934567890", "Rua Secundária, 456");
        
        recepcionistas.add(recepcionista1);
        recepcionistas.add(recepcionista2);
        
        // Adicionar alguns pacientes de teste
        Paciente paciente1 = new Paciente("23456789", "João Pereira", "912345678", "Rua da Saúde, 789");
        paciente1.setDataNasc("15/05/1980");
        paciente1.setGenero("Masculino");
        paciente1.setEstado("Vivo");
        
        Paciente paciente2 = new Paciente("34567890", "Maria Costa", "923456789", "Av. Central, 567");
        paciente2.setDataNasc("22/10/1990");
        paciente2.setGenero("Feminino");
        paciente2.setEstado("Vivo");
        
        pacientes.add(paciente1);
        pacientes.add(paciente2);
        
        // Adicionar alguns serviços de teste
        Servico servico1 = new Servico("S001", "Consulta de Clínica Geral", "Consulta médica geral", 50.0);
        Servico servico2 = new Servico("S002", "Exame de Sangue", "Coleta e análise de sangue", 30.0);
        Servico servico3 = new Servico("S003", "Raio-X", "Exame de imagem por raio-x", 80.0);
        
        servicos.add(servico1);
        servicos.add(servico2);
        servicos.add(servico3);
        
        // Adicionar alguns acompanhantes de teste
        Acompanhante acompanhante1 = new Acompanhante("45678901", "José Pereira", "912345670", "Rua da Saúde, 789", "Familiar", "Masculino");
        Acompanhante acompanhante2 = new Acompanhante("56789012", "Sofia Costa", "923456701", "Av. Central, 567", "Responsável Legal", "Feminino");
        
        acompanhantes.add(acompanhante1);
        acompanhantes.add(acompanhante2);
        
        // Adicionar alguns registros de entrada de teste
        RegistroEntrada registro1 = new RegistroEntrada("REG001", new Date(), recepcionista1, "Vivo",servico1, acompanhante1,paciente1);
        RegistroEntrada registro2 = new RegistroEntrada("REG002", new Date(), recepcionista1, "Vivo",servico2, acompanhante2,paciente2);
        
        registros.add(registro1);
        registros.add(registro2);
        
        // Adicionar algumas consultas de teste
        // (Assumindo que temos médicos já cadastrados em outro controller)
        Medico medico1 = new Medico(new Especialidade("CLINICA_GERAL", "Clínica Geral"), "CRM12345", "M001", "senha123", "12345", "Dr. João Silva", "923456789", "Av. Principal, 123");
        Medico medico2 = new Medico(new Especialidade("PEDIATRIA", "Pediatria"), "CRM67890", "M002", "senha456", "67890", "Dra. Maria Santos", "934567890", "Rua Secundária, 456");
        public Consulta(String id, Paciente paciente, Medico medico, Servico servico, 
                   String data, String hora, StatusConsulta status, String diagnostico, 
                   String medicacao, String observacoes) {
        
        
        Consulta consulta1 = new Consulta("C001", paciente1, medico1, servico1, 
                new Date(), new Date(), Consulta.StatusConsulta.PENDENTE, null, null, null);
        Consulta consulta2 = new Consulta("C002", paciente2, medico2, servico3, 
                new Date(), new Date(), Consulta.StatusConsulta.AGENDADA, null, null, null);
        
        consultas.add(consulta1);
        consultas.add(consulta2);
    }
    
    // Método para buscar recepcionista por ID
    public Recepcionista buscarRecepcionistaPorId(String idFuncionario) {
        for (Recepcionista r : recepcionistas) {
            if (r.getCod_func().equals(idFuncionario)) {
                return r;
            }
        }
        return null;
    }
    
    // Atualizar perfil do recepcionista
    public boolean atualizarRecepcionista(Recepcionista recepcionista) {
        for (int i = 0; i < recepcionistas.size(); i++) {
            if (recepcionistas.get(i).getCod_func().equals(recepcionista.getCod_func())) {
                recepcionistas.set(i, recepcionista);
                return true;
            }
        }
        return false;
    }
    
    // Buscar paciente por BI
    public Paciente buscarPacientePorBi(String bi) {
        for (Paciente p : pacientes) {
            if (p.getBI().equals(bi)) {
                return p;
            }
        }
        return null;
    }
    
    // Listar todos os pacientes
    public ArrayList<Paciente> listarPacientes() {
        return pacientes;
    }
    
    // Pesquisar pacientes por termo (nome ou BI)
    public ArrayList<Paciente> buscarPacientes(String termo) {
        ArrayList<Paciente> resultados = new ArrayList<>();
        
        for (Paciente p : pacientes) {
            if (p.getNome().toLowerCase().contains(termo.toLowerCase()) || 
                p.getBI().toLowerCase().contains(termo.toLowerCase())) {
                resultados.add(p);
            }
        }
        
        return resultados;
    }
    
    // Salvar novo paciente
    public boolean salvarPaciente(Paciente paciente) {
        if (buscarPacientePorBi(paciente.getBI()) != null) {
            return false; // Paciente já existe
        }
        pacientes.add(paciente);
        return true;
    }
    
    // Listar todos os serviços
    public ArrayList<Servico> listarServicos() {
        return servicos;
    }
    
    // Buscar serviço por nome
    public Servico buscarServicoPorNome(String nomeServico) {
        for (Servico s : servicos) {
            if (s.getNomeServico().equalsIgnoreCase(nomeServico)) {
                return s;
            }
        }
        return null;
    }
    
    // Registrar entrada de paciente
    public boolean salvarRegistroEntrada(RegistroEntrada registro) {
        // Verificar se o paciente existe
        if (buscarPacientePorBi(registro.getPaciente().getBI()) == null) {
            // Se não existir, adicionar ao sistema
            pacientes.add(registro.getPaciente());
        }
        
        // Verificar se tem acompanhante e se ele já está cadastrado
        if (registro.getAcompanhante() != null) {
            boolean acompanhanteExiste = false;
            for (Acompanhante a : acompanhantes) {
                if (a.getBI().equals(registro.getAcompanhante().getBI())) {
                    acompanhanteExiste = true;
                    break;
                }
            }
            if (!acompanhanteExiste) {
                acompanhantes.add(registro.getAcompanhante());
            }
        }
        
        // Gerar ID para o novo registro
        String novoId = "REG" + (registros.size() + 1);
        registro.setId(novoId);
        
        registros.add(registro);
        return true;
    }
    
    // Buscar registros por paciente
    public ArrayList<RegistroEntrada> buscarRegistrosPorPaciente(String biPaciente) {
        ArrayList<RegistroEntrada> resultados = new ArrayList<>();
        
        for (RegistroEntrada r : registros) {
            if (r.getPaciente().getBI().equals(biPaciente)) {
                resultados.add(r);
            }
        }
        
        return resultados;
    }
    
    // Listar todas as consultas
    public ArrayList<Consulta> listarConsultas() {
        return consultas;
    }
    
    // Filtrar consultas por status
    public ArrayList<Consulta> filtrarConsultas(String filtro) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        
        for (Consulta c : consultas) {
            switch(filtro) {
                case "Todas as Consultas":
                    resultados.add(c);
                    break;
                case "Consultas de Hoje":
                    if (isHoje(c.getDataConsulta())) {
                        resultados.add(c);
                    }
                    break;
                case "Pendentes":
                    if (c.getStatus() == Consulta.StatusConsulta.PENDENTE) {
                        resultados.add(c);
                    }
                    break;
                case "Em Andamento":
                    if (c.getStatus() == Consulta.StatusConsulta.EM_ANDAMENTO) {
                        resultados.add(c);
                    }
                    break;
                case "Finalizadas":
                    if (c.getStatus() == Consulta.StatusConsulta.FINALIZADA) {
                        resultados.add(c);
                    }
                    break;
                case "Canceladas":
                    if (c.getStatus() == Consulta.StatusConsulta.CANCELADA) {
                        resultados.add(c);
                    }
                    break;
            }
        }
        
        return resultados;
    }
    
    // Verificar se uma data é hoje
    private boolean isHoje(Date data) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(data).equals(fmt.format(new Date()));
    }
    
    // Buscar consultas por BI do paciente
    public ArrayList<Consulta> buscarConsultas(String biPaciente, String filtro) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        
        for (Consulta c : consultas) {
            if (c.getPaciente().getBI().equals(biPaciente)) {
                switch(filtro) {
                    case "Todas as Consultas":
                        resultados.add(c);
                        break;
                    case "Consultas de Hoje":
                        if (isHoje(c.getDataConsulta())) {
                            resultados.add(c);
                        }
                        break;
                    case "Pendentes":
                        if (c.getStatus() == Consulta.StatusConsulta.PENDENTE) {
                            resultados.add(c);
                        }
                        break;
                    case "Em Andamento":
                        if (c.getStatus() == Consulta.StatusConsulta.EM_ANDAMENTO) {
                            resultados.add(c);
                        }
                        break;
                    case "Finalizadas":
                        if (c.getStatus() == Consulta.StatusConsulta.FINALIZADA) {
                            resultados.add(c);
                        }
                        break;
                    case "Canceladas":
                        if (c.getStatus() == Consulta.StatusConsulta.CANCELADA) {
                            resultados.add(c);
                        }
                        break;
                }
            }
        }
        
        return resultados;
    }
    
    // Buscar consulta por ID
    public Consulta buscarConsultaPorId(String idConsulta) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                return c;
            }
        }
        return null;
    }
    
    // Cancelar consulta
    public boolean cancelarConsulta(String idConsulta) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus(Consulta.StatusConsulta.CANCELADA);
                return true;
            }
        }
        return false;
    }
    
    // Agendar nova consulta
    public boolean agendarConsulta(Consulta consulta) {
        // Verificar se o paciente existe
        if (buscarPacientePorBi(consulta.getPaciente().getBI()) == null) {
            return false;
        }
        
        // Gerar ID para a nova consulta
        String novoId = "C" + (consultas.size() + 1);
        consulta.setId(novoId);
        
        consultas.add(consulta);
        return true;
    }
    
    // Buscar histórico de consultas de um paciente
    public ArrayList<Consulta> buscarHistoricoConsultas(String biPaciente) {
        ArrayList<Consulta> historico = new ArrayList<>();
        
        for (Consulta c : consultas) {
            if (c.getPaciente().getBI().equals(biPaciente)) {
                historico.add(c);
            }
        }
        
        return historico;
    }
    
    // Métodos adicionais para acesso aos dados
    public ArrayList<Recepcionista> getRecepcionistas() {
        return recepcionistas;
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
    
    public ArrayList<RegistroEntrada> getRegistros() {
        return registros;
    }
    
    public ArrayList<Acompanhante> getAcompanhantes() {
        return acompanhantes;
    }
}