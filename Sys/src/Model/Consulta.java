package Model;
import java.io.Serializable;
import java.util.Date;

/**
 * Class representing a medical consultation in the hospital system
 */
public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    public Consulta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Defining status as an enum for better type safety
    public enum StatusConsulta {
        PENDENTE("Pendente"),
        EM_ANDAMENTO("Em Andamento"),
        FINALIZADA("Finalizada"),
        CANCELADA("Cancelada");
        
        private final String descricao;
        
        StatusConsulta(String descricao) {
            this.descricao = descricao;
        }
        
        public String getDescricao() {
            return descricao;
        }
        
        @Override
        public String toString() {
            return descricao;
        }
    }
    
    private String id;
    private Paciente paciente;
    private Medico medico;
    private Servico servico;
    private Date data;
    private String hora;
    private StatusConsulta status;
    private String diagnostico;
    private String medicacao;
    private String observacoes;
    
    /**
     * Constructor with status as enum
     */
    public Consulta(String id, Paciente paciente, Medico medico, Servico servico, 
                   Date data, String hora, StatusConsulta status, String diagnostico, 
                   String medicacao, String observacoes) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.servico = servico;
        this.data = data;
        this.hora = hora;
        this.status = status;
        this.diagnostico = diagnostico;
        this.medicacao = medicacao;
        this.observacoes = observacoes;
    }
    
    /**
     * Constructor with status as String (for backward compatibility)
     */
    public Consulta(String id, Paciente paciente, Medico medico, Servico servico, 
                   Date data, String hora, String statusStr, String diagnostico, 
                   String medicacao, String observacoes) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.servico = servico;
        this.data = data;
        this.hora = hora;
        this.setStatus(statusStr);
        this.diagnostico = diagnostico;
        this.medicacao = medicacao;
        this.observacoes = observacoes;
    }
    
    /**
     * Checks if the consultation is active (pending or in progress)
     * 
     * @return true if the consultation is active, false otherwise
     */
    public boolean isAtiva() {
        return status == StatusConsulta.PENDENTE || status == StatusConsulta.EM_ANDAMENTO;
    }
    
    /**
     * Checks if the consultation can be canceled
     * 
     * @return true if the consultation can be canceled, false otherwise
     */
    public boolean isCancelavel() {
        return status == StatusConsulta.PENDENTE || status == StatusConsulta.EM_ANDAMENTO;
    }
    
    /**
     * Checks if the consultation can be started
     * 
     * @return true if the consultation can be started, false otherwise
     */
    public boolean isIniciavel() {
        return status == StatusConsulta.PENDENTE;
    }
    
    /**
     * Checks if the consultation can be finalized
     * 
     * @return true if the consultation can be finalized, false otherwise
     */
    public boolean isFinalizavel() {
        return status == StatusConsulta.EM_ANDAMENTO;
    }
    
    /**
     * Calculates the consultation value
     * 
     * @return consultation value
     */
    public double getValor() {
        if (servico != null) {
            return servico.getValor();
        }
        return 0.0;
    }

    // Getters and Setters
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }



    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public StatusConsulta getStatus() {
        return status;
    }
    
    public String getStatusDescricao() {
        return status.getDescricao();
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }
    
    // For backward compatibility
    public void setStatus(String statusStr) {
        for (StatusConsulta s : StatusConsulta.values()) {
            if (s.getDescricao().equals(statusStr)) {
                this.status = s;
                return;
            }
        }
        this.status = StatusConsulta.PENDENTE; // Default value
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String medicacao) {
        this.medicacao = medicacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    @Override
    public String toString() {
        return "Consulta [ID=" + id + ", Paciente=" + paciente.getNome() + 
               ", Médico=" + medico.getNome() + ", Data=" + data + 
               ", Hora=" + hora + ", Status=" + status + "]";
    }
}