package View;
import Model.*;
import Controller.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class HospitalSystemTest {
    public static void main(String[] args) {
        try {
            // Inicializar todos os controladores
            PacienteController pacienteController = new PacienteController();
            MedicoController medicoController = new MedicoController();
            RecepcionistaController recepcionistaController = new RecepcionistaController();
            ServicoController servicoController = new ServicoController();
            ConsultaController consultaController = new ConsultaController();
            RegistroEntradaController registroController = new RegistroEntradaController();
            AcompanhanteController acompanhanteController = new AcompanhanteController();

            // Formatador de datas
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            System.out.println("=== INICIALIZANDO SISTEMA HOSPITALAR ===");

            // 1. Cadastrar especialidades médicas
            System.out.println("\n1. Cadastrando especialidades médicas...");
            medicoController.adicionarEspecialidade("CARDIOLOGIA", "Especialidade em doenças do coração");
            medicoController.adicionarEspecialidade("PEDIATRIA", "Atendimento infantil");
            medicoController.adicionarEspecialidade("CLINICA_GERAL", "Clínica geral");

            // 2. Cadastrar médicos
            System.out.println("\n2. Cadastrando médicos...");
            Especialidade cardiologia = new Especialidade("CARDIOLOGIA", "Especialidade em doenças do coração");
            Especialidade pediatria = new Especialidade("PEDIATRIA", "Atendimento infantil");
            
            medicoController.cadastrarMedico(cardiologia, "CRM12345", "MED001", "senha123", 
                                           "111111111", "Dr. João Silva", "919999999", "Av. Principal, 100");
            medicoController.cadastrarMedico(pediatria, "CRM54321", "MED002", "senha456", 
                                           "222222222", "Dra. Maria Santos", "918888888", "Rua Secundária, 200");

            // 3. Cadastrar recepcionistas
            System.out.println("\n3. Cadastrando recepcionistas...");
            recepcionistaController.cadastrarRecepcionista("REC001", "senha123", "333333333", 
                                                         "Ana Souza", "917777777", "Av. Central, 300");
            recepcionistaController.cadastrarRecepcionista("REC002", "senha456", "444444444", 
                                                         "Carlos Oliveira", "916666666", "Rua Lateral, 400");

            // 4. Cadastrar serviços médicos
            System.out.println("\n4. Cadastrando serviços médicos...");
            servicoController.cadastrarServico("SER001", "Consulta de Rotina", "Consulta básica de avaliação", 150.00);
            servicoController.cadastrarServico("SER002", "Eletrocardiograma", "Exame cardíaco", 250.00);
            servicoController.cadastrarServico("SER003", "Consulta Pediátrica", "Consulta para crianças", 180.00);

            // 5. Cadastrar pacientes
            System.out.println("\n5. Cadastrando pacientes...");
            pacienteController.cadastrarPaciente("555555555", "Pedro Alves", "915555555", "Rua Nova, 500", 
                                               "15/05/1980", "Masculino", "Vivo");
            pacienteController.cadastrarPaciente("666666666", "Mariana Costa", "914444444", "Av. Moderna, 600", 
                                               "22/10/1995", "Feminino", "Vivo");
            pacienteController.cadastrarPaciente("777777777", "Lucas Mendes", "913333333", "Travessa Antiga, 700", 
                                               "10/03/2010", "Masculino", "Vivo");

            // 6. Cadastrar acompanhantes
            System.out.println("\n6. Cadastrando acompanhantes...");
            acompanhanteController.cadastrarAcompanhante("888888888", "Sofia Pereira", "912222222", 
                                                       "Rua Velha, 800", "Familiar", "Feminino");
            acompanhanteController.cadastrarAcompanhante("999999999", "Ricardo Nunes", "911111111", 
                                                       "Alameda Seca, 900", "Responsável Legal", "Masculino");

            // 7. Simular registro de entrada de pacientes
            System.out.println("\n7. Simulando registros de entrada...");
            
            // Buscar entidades necessárias
            Recepcionista recepcionista = recepcionistaController.buscarPorCodigoFuncionario("REC001");
            Paciente paciente1 = (Paciente) pacienteController.buscarPorBi("555555555");
            Paciente paciente2 = (Paciente) pacienteController.buscarPorBi("666666666");
            Servico servico1 = servicoController.buscarServicoPorId("SER001");
            Servico servico2 = servicoController.buscarServicoPorId("SER002");
            Acompanhante acompanhante1 = acompanhanteController.buscarAcompanhantePorBi("888888888");
            
            // Registrar entradas
            registroController.registrarEntrada(paciente1, recepcionista, "Vivo", servico1, acompanhante1);
            registroController.registrarEntrada(paciente2, recepcionista, "Vivo", servico2, null);
            
            System.out.println("Registros de entrada criados com sucesso!");

            // 8. Simular marcação de consultas
            System.out.println("\n8. Simulando marcação de consultas...");
            
            // Buscar entidades necessárias
            Medico medico1 = medicoController.buscarPorCrm("CRM12345");
            System.out.println(medico1);
            Medico medico2 = medicoController.buscarPorCrm("CRM54321");
            Date dataConsulta1 = dateFormat.parse("25/05/2023");
            Date dataConsulta2 = dateFormat.parse("26/05/2023");
            String horaConsulta1 = "09:00";
            String horaConsulta2 = "14:30";
            
            // Marcar consultas
            consultaController.agendarConsulta(paciente1, medico1, servico1, dataConsulta1, horaConsulta1, "Pendente");
            consultaController.agendarConsulta(paciente2, medico2, servico2, dataConsulta2, horaConsulta2, "Pendente");
            
            System.out.println("Consultas agendadas com sucesso!");

            // 9. Simular atendimento médico
            System.out.println("\n9. Simulando atendimento médico...");
            
            // Buscar consulta
            Consulta consulta = consultaController.listarConsultasPorPaciente("555555555").get(0);
            
            // Atualizar status e registrar diagnóstico
            consultaController.atualizarStatusConsulta(consulta.getId(), "Em Andamento");
            consultaController.registrarDiagnostico(consulta.getId(), 
                                                   "Hipertensão arterial", 
                                                   "Losartana 50mg 1x ao dia", 
                                                   "Controlar pressão e retornar em 30 dias");
            
            System.out.println("Atendimento médico registrado para consulta ID: " + consulta.getId());

            // 10. Exibir relatórios
            System.out.println("\n10. Exibindo relatórios...");
            
            System.out.println("\n=== PACIENTES CADASTRADOS ===");
            for (Pessoa p : pacienteController.listarTodos()) {
                if (p instanceof Paciente) {
                    Paciente pac = (Paciente) p;
                    System.out.println("BI: " + pac.getBI() + " | Nome: " + pac.getNome() + 
                                     " | Telefone: " + pac.getTelefone() + " | Estado: " + pac.getEstado());
                }
            }
            
            System.out.println("\n=== MÉDICOS CADASTRADOS ===");
            for (Pessoa p : medicoController.listarTodos()) {
                if (p instanceof Medico) {
                    Medico med = (Medico) p;
                    System.out.println("CRM: " + med.getCrm() + " | Nome: " + med.getNome() + 
                                     " | Especialidade: " + med.getEspecialidade().getEspecialidade());
                }
            }
            
            System.out.println("\n=== CONSULTAS AGENDADAS ===");
            for (Consulta c : consultaController.listarConsultasPorPaciente("555555555")) {
                System.out.println("ID: " + c.getId() + " | Paciente: " + c.getPaciente().getNome() + 
                                 " | Médico: " + c.getMedico().getNome() + " | Data: " + 
                                 dateFormat.format(c.getData()) + " " + c.getHora() + 
                                 " | Status: " + c.getStatus());
            }
            
            System.out.println("\n=== REGISTROS DE ENTRADA ===");
            for (RegistroEntrada r : registroController.listarRegistrosPorRecepcionista("REC001")) {
                System.out.println("ID: " + r.getIdRegistro() + " | Paciente: " + r.getPaciente().getNome() + 
                                 " | Data: " + dateFormat.format(r.getDataEntrada()) + 
                                 " | Serviço: " + r.getServico().getNomeServico() + 
                                 " | Acompanhante: " + (r.getAcompanhate() != null ? r.getAcompanhate().getNome() : "Nenhum"));
            }

            System.out.println("\n=== TESTES FINALIZADOS COM SUCESSO ===");

        } catch (ParseException e) {
            System.err.println("Erro ao formatar data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}