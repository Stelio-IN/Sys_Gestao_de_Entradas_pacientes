package Controller;

import Model.Acompanhante;
import Model.Paciente;
import Model.Recepcionista;
import Model.RegistroEntrada;
import Model.Servico;
import java.util.ArrayList;
import java.util.Date;

public class RegistroEntradaController {
    private ArrayList<RegistroEntrada> registros = new ArrayList<>();
    
    public boolean registrarEntrada(Paciente paciente, Recepcionista recepcionista, 
                                  String estadoPaciente, Servico servico, 
                                  Acompanhante acompanhante) {
        String novoId = "REG" + (registros.size() + 1);
        Date dataAtual = new Date();
        
        RegistroEntrada novoRegistro = new RegistroEntrada(novoId, dataAtual, recepcionista, 
                                                         estadoPaciente, servico, acompanhante, paciente);
        
        registros.add(novoRegistro);
        return true;
    }
    
    public ArrayList<RegistroEntrada> listarRegistrosPorPaciente(String biPaciente) {
        ArrayList<RegistroEntrada> resultados = new ArrayList<>();
        
        for (RegistroEntrada r : registros) {
            if (r.getPaciente().getBI().equals(biPaciente)) {
                resultados.add(r);
            }
        }
        
        return resultados;
    }
    
    public ArrayList<RegistroEntrada> listarRegistrosPorRecepcionista(String codFunc) {
        ArrayList<RegistroEntrada> resultados = new ArrayList<>();
        
        for (RegistroEntrada r : registros) {
            if (r.getRecepcionista().getCod_func().equals(codFunc)) {
                resultados.add(r);
            }
        }
        
        return resultados;
    }
    
    public ArrayList<RegistroEntrada> filtrarRegistrosPorData(Date data) {
        ArrayList<RegistroEntrada> resultados = new ArrayList<>();
        
        for (RegistroEntrada r : registros) {
            if (r.getDataEntrada().equals(data)) {
                resultados.add(r);
            }
        }
        
        return resultados;
    }
}