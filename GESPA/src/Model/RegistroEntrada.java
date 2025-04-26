
package Model;

import java.io.Serializable;
import java.util.Date;


public class RegistroEntrada  implements Serializable{
    String idRegistro;
    Date dataEntrada;
    Recepcionista recepcionista;
    String estadoPaciente;
    Servico servico;
    Acompanhate acompanhate;
    Paciente paciente;

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

    public Acompanhate getAcompanhate() {
        return acompanhate;
    }

    public void setAcompanhate(Acompanhate acompanhate) {
        this.acompanhate = acompanhate;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

        
    
}
