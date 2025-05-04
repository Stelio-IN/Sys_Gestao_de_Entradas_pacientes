package Model;

import Controller.Ficheiro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Paciente paciente;
    private Medico medico;
    private Servico servico;
    private Date data;
    private String hora;
    private String status; // Agora status é uma string
    private String diagnostico;
    private String medicacao;
    private String observacoes;

    public Consulta() {}

    public Consulta(String id, Paciente paciente, Medico medico, Servico servico,
                    Date data, String hora, String status, String diagnostico,
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", Hora=" + hora + ", Status=" + status + "]" +
                "    Diag: " + diagnostico + "  Medi: " + medicacao +
                "    OBS: " + observacoes;
    }
       public static void main(String[] args) {
        Ficheiro f1 = new Ficheiro();
        ArrayList<Consulta> consultas = f1.carregarDoArquivo("consulta");

        for (Consulta c : consultas) {
            System.out.println(c);
        }
    }
}
