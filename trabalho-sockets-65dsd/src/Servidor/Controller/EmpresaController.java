package Servidor.Controller;

import Servidor.Model.Empresa;
import Servidor.Model.Funcionario;
import Servidor.Model.Gerente;

import java.util.HashMap;
import java.util.Map;

public class EmpresaController {

    private Map<String, Empresa> empresas;
    private FuncionarioController funcionarioController;
    private GerenteController gerenteController;


    public EmpresaController(FuncionarioController funcionarioController, GerenteController gerenteController) {
        this.empresas = new HashMap<>();
        this.funcionarioController = funcionarioController;
        this.gerenteController = gerenteController;
    }

    public String insertEmpresa (String razaoSocial, double capitalSocial) {
        if (!empresas.containsKey(razaoSocial)) {
            empresas.put(razaoSocial, new Empresa(razaoSocial, capitalSocial));
            return "Empresa incluída com sucesso";
        }
        return "Empresa já existente";
    }

    public String updateEmpresa(String razaoSocial, double capitalSocial) {
        Empresa empresa = empresas.get(razaoSocial);
        if (empresa != null) {
            empresa.setCapitalSocial(capitalSocial);
            return "Empresa atualizada com sucesso";
        }
        return "Empresa não encontrada";
    }

    public String getEmpresa(String razaoSocial) {
        if (empresas.isEmpty()) {
            return "Sem empresas cadastradas";
        }

        Empresa empresa = empresas.get(razaoSocial);
        if (empresa == null) {
            return "Empresa não encontrada";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(empresa.toString()).append("\n");

        int totalPessoas = empresa.getGerentes().size() + empresa.getFuncionarios().size();
        sb.append(String.format("%02d", totalPessoas)).append("\n");

        sb.append("Funcionários:\n");
        for (Funcionario fun : empresa.getFuncionarios().values()){
            sb.append(fun.toString()).append("\n");
        }

        sb.append("Gerentes:\n");
        for (Gerente gerente : empresa.getGerentes().values()) {
            sb.append(gerente.toString()).append("\n");
        }

        return sb.toString();
    }

    public String deleteEmpresa(String razaoSocial) {
        if (empresas.isEmpty()) {
            return "Sem empresas cadastradas";
        }

        Empresa empresa = empresas.remove(razaoSocial);
        if (empresa != null) {
            for (Funcionario funcionario : empresa.getFuncionarios().values()) {
                empresa.removerFuncionario(funcionario);
            }

            for (Gerente gerente : empresa.getGerentes().values()) {
                empresa.removerGerente(gerente);
            }

            return "Empresa removida com sucesso";
        }
        return "Empresa não encontrada";
    }

    public String listEmpresas() {
        if (empresas.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%02d", empresas.size())).append("\n");
        for (Empresa empresa : empresas.values()) {
            stringBuilder.append(empresa.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public String vincularFuncionario(String razaoSocial, String cpfFuncionario) {
        Empresa empresa = empresas.get(razaoSocial);
        if (empresa == null) {
            return "Empresa não encontrada";
        }

        Funcionario funcionario = funcionarioController.getFuncionarios().get(cpfFuncionario);
        if (funcionario == null) {
            return "Funcionário não encontrado";
        }

        if (funcionario.getEmpresa() == empresa) {
            return "Funcionário já está vinculado à empresa " + razaoSocial;
        }

        empresa.adicionarFuncionario(funcionario);
        return "Funcionário vinculado à empresa " + razaoSocial;
    }

    public String desvincularFuncionario(String razaoSocial, String cpfFuncionario) {
        Empresa empresa = empresas.get(razaoSocial);
        if (empresa == null) {
            return "Empresa não encontrada";
        }

        Funcionario funcionario = funcionarioController.getFuncionarios().get(cpfFuncionario);
        if (funcionario == null) {
            return "Funcionário não encontrado";
        }

        if (funcionario.getEmpresa() != empresa) {
            return "Funcionário não está vinculado à empresa " + razaoSocial;
        }

        empresa.removerFuncionario(funcionario);
        return "Funcionário desvinculado da empresa " + razaoSocial;
    }

    public String vincularGerente(String razaoSocial, String cpfGerente) {
        Empresa empresa = empresas.get(razaoSocial);
        if (empresa == null) {
            return "Empresa não encontrada";
        }

        Gerente gerente = gerenteController.getGerentes().get(cpfGerente);
        if (gerente == null) {
            return "Gerente não encontrado";
        }

        if (gerente.getEmpresa() == empresa) {
            return "Gerente já está vinculado à empresa " + razaoSocial;
        }

        empresa.adicionarGerente(gerente);
        return "Gerente vinculado à empresa " + razaoSocial;
    }

    public String desvincularGerente(String razaoSocial, String cpfGerente) {
        Empresa empresa = empresas.get(razaoSocial);
        if (empresa == null) {
            return "Empresa não encontrado";
        }

        Gerente gerente = gerenteController.getGerentes().get(cpfGerente);
        if (gerente == null) {
            return "Gerente não encontrado";
        }

        if (gerente.getEmpresa() != empresa) {
            return "Gerente não está vinculado à empresa " + razaoSocial;
        }

        empresa.removerGerente(gerente);
        return "Gerente desvinculado da empresa " + razaoSocial;
    }

    public Map<String, Empresa> getEmpresas() {
        return empresas;
    }

    public FuncionarioController getFuncionarioController() {
        return funcionarioController;
    }

    public GerenteController getGerenteController() {
        return gerenteController;
    }
}
