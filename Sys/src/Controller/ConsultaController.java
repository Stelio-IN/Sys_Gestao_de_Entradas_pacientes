package Controller;

import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Model.Servico;
import java.util.ArrayList;
import java.util.Date;

public class ConsultaController {
    private ArrayList<Consulta> consultas = new ArrayList<>();
    
    public boolean agendarConsulta(Paciente paciente, Medico medico, Servico servico, 
                                 Date data, String hora, String status) {
        String novoId = "CONS" + (consultas.size() + 1);
        
        Consulta novaConsulta = new Consulta(novoId, paciente, medico, servico, 
                                           data, hora, status, null, null, null);
        
        consultas.add(novaConsulta);
        return true;
    }
    
    public boolean cancelarConsulta(String idConsulta) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus("Cancelada");
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Consulta> listarConsultasPorPaciente(String biPaciente) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        
        for (Consulta c : consultas) {
            if (c.getPaciente().getBI().equals(biPaciente)) {
                resultados.add(c);
            }
        }
        
        return resultados;
    }
    
    public ArrayList<Consulta> listarConsultasPorMedico(String crmMedico) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        
        for (Consulta c : consultas) {
            if (c.getMedico().getCrm().equals(crmMedico)) {
                resultados.add(c);
            }
        }
        
        return resultados;
    }
    
    public boolean atualizarStatusConsulta(String idConsulta, String novoStatus) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus(novoStatus);
                return true;
            }
        }
        return false;
    }
    
    public boolean registrarDiagnostico(String idConsulta, String diagnostico, 
                                      String medicacao, String observacoes) {
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setDiagnostico(diagnostico);
                c.setMedicacao(medicacao);
                c.setObservacoes(observacoes);
                c.setStatus("Finalizada");
                return true;
            }
        }
        return false;
    }

    public Consulta buscarConsultaPorId(String idConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}