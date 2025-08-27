package Servidor.Controller;

import Servidor.Model.Gerente;

import java.util.HashMap;
import java.util.Map;

public class GerenteController {

    private Map<String, Gerente> gerentes;

    public GerenteController() {
        this.gerentes = new HashMap<>();
    }

    public String insertGerente(String cpf, String nome, String endereco, String departamento) {
        if (!gerentes.containsKey(cpf)) {
            gerentes.put(cpf, new Gerente(cpf, nome, endereco,departamento));
            return "Gerente incluído com sucesso";
        }
        return "Gerente já existente";
    }

    public String updateGerente(String cpf, String nome, String endereco, String departamento) {
        Gerente gerente = gerentes.get(cpf);
        if (gerente != null) {
            gerente.setNome(nome);
            gerente.setEndereco(endereco);
            gerente.setDepartamento(departamento);
            return "Gerente atualizado com sucesso";
        }
        return "Gerente não encontrado";
    }

    public String getGerente(String cpf) {
        if (gerentes.isEmpty()) {
            return "Sem gerentes cadastrados";
        }

        Gerente gerente = gerentes.get(cpf);
        if (gerente != null) {
            return gerente.toString();
        }
        return "Gerente não encontrado";
    }

    public String deleteGerente(String cpf) {
        if (gerentes.isEmpty()) {
            return "Sem gerentes cadastrados";
        }

        Gerente gerente = gerentes.get(cpf);
        if (gerente != null) {
            if (gerente.getEmpresa() != null) {
                gerente.getEmpresa().removerGerente(gerente);
            }
            gerentes.remove(cpf);
            return "Gerente removido com sucesso";
        }
        return "Gerente não encontrado";
    }

    public String listGerentes() {
        if (gerentes.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%02d", gerentes.size())).append("\n");
        for (Gerente gerente : gerentes.values()) {
            stringBuilder.append(gerente.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public Map<String, Gerente> getGerentes() {
        return gerentes;
    }
}
