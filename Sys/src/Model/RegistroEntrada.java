
package Model;

import java.io.Serializable;
import java.util.Date;


public class RegistroEntrada  implements Serializable{
    private String idRegistro;
    private Date dataEntrada;
    private Recepcionista recepcionista;
    private String estadoPaciente;
    private Servico servico;
    private Acompanhante acompanhate;
    private Paciente paciente;

    public RegistroEntrada(String idRegistro, Date dataEntrada, Recepcionista recepcionista, String estadoPaciente, Servico servico, Acompanhante acompanhate, Paciente paciente) {
        this.idRegistro = idRegistro;
        this.dataEntrada = dataEntrada;
        this.recepcionista = recepcionista;
        this.estadoPaciente = estadoPaciente;
        this.servico = servico;
        this.acompanhate = acompanhate;
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Acompanhante getAcompanhate() {
        return acompanhate;
    }

    public void setAcompanhate(Acompanhante acompanhate) {
        this.acompanhate = acompanhate;
    }

    
 
    
}
