package View;
import Model.*;
import Controller.*;
import View.TelaMedico;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        // Inicializar controladores
        MedicoController medicoController = new MedicoController();
        PacienteController pacienteController = new PacienteController();
        ServicoController servicoController = new ServicoController();
        ConsultaController consultaController = new ConsultaController();
        RegistroEntradaController registroController = new RegistroEntradaController();
        AcompanhanteController acompanhanteController = new AcompanhanteController();
        RecepcionistaController recepcionistaController = new RecepcionistaController();

        // Cadastrar especialidades médicas
        Especialidade cardiologia = new Especialidade("CARDIOLOGIA", "Doenças do coração");
        Especialidade pediatria = new Especialidade("PEDIATRIA", "Atendimento infantil");
        Especialidade ortopedia = new Especialidade("ORTOPEDIA", "Problemas ósseos e musculares");
        
        // Cadastrar médicos
        Medico medico1 = new Medico(cardiologia, "CRM12345", "MED001", "senha123", 
                                  "111111111", "Dr. João Silva", "919999999", "Av. Principal, 100");
        Medico medico2 = new Medico(pediatria, "CRM54321", "MED002", "senha456", 
                                  "222222222", "Dra. Maria Santos", "918888888", "Rua Secundária, 200");
        Medico medico3 = new Medico(ortopedia, "CRM98765", "MED003", "senha789", 
                                  "333333333", "Dr. Carlos Oliveira", "917777777", "Av. Central, 300");
        
        medicoController.adicionarEspecialidade(cardiologia.getEspecialidade(), cardiologia.getDescricao());
        medicoController.adicionarEspecialidade(pediatria.getEspecialidade(), pediatria.getDescricao());
        medicoController.adicionarEspecialidade(ortopedia.getEspecialidade(), ortopedia.getDescricao());
        
        medicoController.cadastrarMedico(cardiologia, medico1.getCrm(), medico1.getCod_func(), 
                                       medico1.getPassword(), medico1.getBI(), medico1.getNome(), 
                                       medico1.getTelefone(), medico1.getEndereco());
        medicoController.cadastrarMedico(pediatria, medico2.getCrm(), medico2.getCod_func(), 
                                       medico2.getPassword(), medico2.getBI(), medico2.getNome(), 
                                       medico2.getTelefone(), medico2.getEndereco());
        medicoController.cadastrarMedico(ortopedia, medico3.getCrm(), medico3.getCod_func(), 
                                       medico3.getPassword(), medico3.getBI(), medico3.getNome(), 
                                       medico3.getTelefone(), medico3.getEndereco());

        // Cadastrar recepcionistas
        Recepcionista recepcionista1 = new Recepcionista("REC001", "senha123", "444444444", 
                                                       "Ana Souza", "916666666", "Rua Teste, 123");
        Recepcionista recepcionista2 = new Recepcionista("REC002", "senha456", "555555555", 
                                                       "Carlos Mendes", "915555555", "Av. Teste, 456");
        
        recepcionistaController.cadastrarRecepcionista(recepcionista1.getCod_func(), recepcionista1.getPassword(), 
                                                      recepcionista1.getBI(), recepcionista1.getNome(), 
                                                      recepcionista1.getTelefone(), recepcionista1.getEndereco());
        recepcionistaController.cadastrarRecepcionista(recepcionista2.getCod_func(), recepcionista2.getPassword(), 
                                                      recepcionista2.getBI(), recepcionista2.getNome(), 
                                                      recepcionista2.getTelefone(), recepcionista2.getEndereco());

        // Cadastrar serviços médicos
        Servico servico1 = new Servico("SER001", "Consulta de Rotina", "Consulta básica", 150.00);
        Servico servico2 = new Servico("SER002", "Eletrocardiograma", "Exame cardíaco", 250.00);
        Servico servico3 = new Servico("SER003", "Consulta Pediátrica", "Consulta para crianças", 180.00);
        Servico servico4 = new Servico("SER004", "Raio-X", "Exame de imagem", 200.00);
        
        servicoController.cadastrarServico(servico1.getId(), servico1.getNomeServico(), 
                                         servico1.getDescricao(), servico1.getValor());
        servicoController.cadastrarServico(servico2.getId(), servico2.getNomeServico(), 
                                         servico2.getDescricao(), servico2.getValor());
        servicoController.cadastrarServico(servico3.getId(), servico3.getNomeServico(), 
                                         servico3.getDescricao(), servico3.getValor());
        servicoController.cadastrarServico(servico4.getId(), servico4.getNomeServico(), 
                                         servico4.getDescricao(), servico4.getValor());

        // Cadastrar pacientes
        Paciente paciente1 = new Paciente("666666666", "Pedro Alves", "914444444", "Rua A, 100");
        paciente1.setDataNasc("15/05/1980");
        paciente1.setGenero("Masculino");
        paciente1.setEstado("Vivo");
        
        Paciente paciente2 = new Paciente("777777777", "Mariana Costa", "913333333", "Av. B, 200");
        paciente2.setDataNasc("22/10/1995");
        paciente2.setGenero("Feminino");
        paciente2.setEstado("Vivo");
        
        Paciente paciente3 = new Paciente("888888888", "Lucas Mendes", "912222222", "Travessa C, 300");
        paciente3.setDataNasc("10/03/2010");
        paciente3.setGenero("Masculino");
        paciente3.setEstado("Vivo");
        
        Paciente paciente4 = new Paciente("999999999", "Ana Oliveira", "911111111", "Alameda D, 400");
        paciente4.setDataNasc("05/12/1975");
        paciente4.setGenero("Feminino");
        paciente4.setEstado("Vivo");
        
        pacienteController.cadastrarPaciente(paciente1.getBI(), paciente1.getNome(), 
                                           paciente1.getTelefone(), paciente1.getEndereco(),
                                           paciente1.getDataNasc(), paciente1.getGenero(), 
                                           paciente1.getEstado());
        pacienteController.cadastrarPaciente(paciente2.getBI(), paciente2.getNome(), 
                                           paciente2.getTelefone(), paciente2.getEndereco(),
                                           paciente2.getDataNasc(), paciente2.getGenero(), 
                                           paciente2.getEstado());
        pacienteController.cadastrarPaciente(paciente3.getBI(), paciente3.getNome(), 
                                           paciente3.getTelefone(), paciente3.getEndereco(),
                                           paciente3.getDataNasc(), paciente3.getGenero(), 
                                           paciente3.getEstado());
        pacienteController.cadastrarPaciente(paciente4.getBI(), paciente4.getNome(), 
                                           paciente4.getTelefone(), paciente4.getEndereco(),
                                           paciente4.getDataNasc(), paciente4.getGenero(), 
                                           paciente4.getEstado());

        // Cadastrar acompanhantes
        Acompanhante acompanhante1 = new Acompanhante("101010101", "Sofia Pereira", "910101010", 
                                                     "Rua E, 500", "Familiar", "Feminino");
        Acompanhante acompanhante2 = new Acompanhante("202020202", "Ricardo Nunes", "920202020", 
                                                     "Av. F, 600", "Responsável Legal", "Masculino");
        
        acompanhanteController.cadastrarAcompanhante(acompanhante1.getBI(), acompanhante1.getNome(), 
                                                    acompanhante1.getTelefone(), acompanhante1.getEndereco(),
                                                    acompanhante1.getTipo(), acompanhante1.getSexo());
        acompanhanteController.cadastrarAcompanhante(acompanhante2.getBI(), acompanhante2.getNome(), 
                                                    acompanhante2.getTelefone(), acompanhante2.getEndereco(),
                                                    acompanhante2.getTipo(), acompanhante2.getSexo());

        // Criar registros de entrada
        registroController.registrarEntrada(paciente1, recepcionista1, "Vivo", servico1, acompanhante1);
        registroController.registrarEntrada(paciente2, recepcionista1, "Vivo", servico2, null);
        registroController.registrarEntrada(paciente3, recepcionista2, "Vivo", servico3, acompanhante2);
        registroController.registrarEntrada(paciente4, recepcionista2, "Vivo", servico4, null);

        // Criar consultas
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            // Consultas passadas
            Consulta consulta1 = new Consulta("CONS001", paciente1, medico1, servico1, 
                                            sdf.parse("01/06/2023"), "09:00", 
                                            Consulta.StatusConsulta.FINALIZADA, 
                                            "Hipertensão arterial", 
                                            "Losartana 50mg 1x ao dia", 
                                            "Controlar pressão e retornar em 30 dias");
            
            Consulta consulta2 = new Consulta("CONS002", paciente2, medico2, servico3, 
                                            sdf.parse("05/06/2023"), "14:30", 
                                            Consulta.StatusConsulta.FINALIZADA, 
                                            "Resfriado comum", 
                                            "Paracetamol 500mg 6/6h", 
                                            "Repouso e hidratação");
            
            // Consultas futuras
            Consulta consulta3 = new Consulta("CONS003", paciente3, medico2, servico3, 
                                            sdf.parse("15/06/2023"), "10:00", 
                                            Consulta.StatusConsulta.PENDENTE, 
                                            null, null, null);
            
            Consulta consulta4 = new Consulta("CONS004", paciente4, medico3, servico4, 
                                            sdf.parse("20/06/2023"), "15:30", 
                                            Consulta.StatusConsulta.PENDENTE, 
                                            null, null, null);
            
            // Consulta em andamento
            Consulta consulta5 = new Consulta("CONS005", paciente1, medico1, servico2, 
                                            new Date(), "11:00", 
                                            Consulta.StatusConsulta.EM_ANDAMENTO, 
                                            null, null, null);
            
            consultaController.agendarConsulta(consulta1.getPaciente(), consulta1.getMedico(), 
                                             consulta1.getServico(), consulta1.getData(), 
                                             consulta1.getHora(), consulta1.getStatus().toString());
            consultaController.registrarDiagnostico("CONS001", consulta1.getDiagnostico(), 
                                                  consulta1.getMedicacao(), consulta1.getObservacoes());
            
            consultaController.agendarConsulta(consulta2.getPaciente(), consulta2.getMedico(), 
                                             consulta2.getServico(), consulta2.getData(), 
                                             consulta2.getHora(), consulta2.getStatus().toString());
            consultaController.registrarDiagnostico("CONS002", consulta2.getDiagnostico(), 
                                                  consulta2.getMedicacao(), consulta2.getObservacoes());
            
            consultaController.agendarConsulta(consulta3.getPaciente(), consulta3.getMedico(), 
                                             consulta3.getServico(), consulta3.getData(), 
                                             consulta3.getHora(), consulta3.getStatus().toString());
            
            consultaController.agendarConsulta(consulta4.getPaciente(), consulta4.getMedico(), 
                                             consulta4.getServico(), consulta4.getData(), 
                                             consulta4.getHora(), consulta4.getStatus().toString());
            
            consultaController.agendarConsulta(consulta5.getPaciente(), consulta5.getMedico(), 
                                             consulta5.getServico(), consulta5.getData(), 
                                             consulta5.getHora(), consulta5.getStatus().toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Iniciar interface do usuário
        SwingUtilities.invokeLater(() -> {
            // Tela de login simulada - vamos direto para a tela do médico de teste
            new TelaMedico(medico1);
            
            // Para testar a tela do recepcionista também:
            // new TelaAtendente(recepcionista1);
        });
    }
}