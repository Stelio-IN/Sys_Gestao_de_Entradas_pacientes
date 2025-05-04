package Controller;

import Model.Consulta;
import Model.Medico;
import Model.Paciente;
import Model.Servico;
import java.util.ArrayList;
import java.util.Date;

public class ConsultaController {

    Ficheiro f1 = new Ficheiro();
    private static ArrayList<Consulta> consultas = new ArrayList<>();

    public boolean agendarConsulta(Paciente paciente, Medico medico, Servico servico,
            Date data, String hora, String status) {
        String novoId = "CONS" + (consultas.size() + 1);

        consultas = f1.carregarDoArquivo("consulta");

        Consulta novaConsulta = new Consulta(novoId, paciente, medico, servico,
                data, hora, status, null, null, null);

        consultas.add(novaConsulta);
        f1.gravarEmArquivo(consultas, "consulta");
        return true;
    }

    public boolean cancelarConsulta(String idConsulta) {
        consultas = f1.carregarDoArquivo("consulta");
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus("Cancelada");
                f1.gravarEmArquivo(consultas, "consulta");
                return true;

            }
        }
        return false;
    }

    public ArrayList<Consulta> listarConsultasPorPaciente(String biPaciente) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        consultas = f1.carregarDoArquivo("consulta");
        for (Consulta c : consultas) {
            if (biPaciente.equals("")) {
                resultados.add(c);
            } else if (c.getPaciente().getBI().equals(biPaciente)) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    public ArrayList<Consulta> listarConsultasPorMedico(String crmMedico) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        consultas = f1.carregarDoArquivo("consulta");
        for (Consulta c : consultas) {
            if (c.getMedico().getCrm().equals(crmMedico)) {
                resultados.add(c);
            }
        }

        return resultados;
    }

    public boolean atualizarStatusConsulta(String idConsulta, String novoStatus) {
        consultas = f1.carregarDoArquivo("consulta");
        boolean atualizado = false;

        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setStatus(novoStatus);
                atualizado = true;
                break; // Se IDs são únicos, podemos parar aqui
            }
        }

        if (atualizado) {
            f1.gravarEmArquivo(consultas, "consulta");
            System.out.println("Consulta atualizada");
        }

        return atualizado;
    }

    public boolean registrarDiagnostico(String idConsulta, String diagnostico,
            String medicacao, String observacoes) {
        consultas = f1.carregarDoArquivo("consulta");
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                c.setDiagnostico(diagnostico);
                c.setMedicacao(medicacao);
                c.setObservacoes(observacoes);
                c.setStatus("Finalizada");
                f1.gravarEmArquivo(consultas, "consulta");
                return true;
            }
        }
        return false;
    }

    public Consulta buscarConsultaPorId(String idConsulta) {
        ArrayList<Consulta> resultados = new ArrayList<>();
        consultas = f1.carregarDoArquivo("consulta");
        for (Consulta c : consultas) {
            if (c.getId().equals(idConsulta)) {
                return c;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ConsultaController controller = new ConsultaController();
        //ArrayList<Consulta> resultados = controller.listarConsultasPorMedico("123");
        controller.atualizarStatusConsulta("CONS1", "Em Andamento");
        // for (Consulta a : resultados) {
        //     System.out.println(a);
        //  }
    }

}
