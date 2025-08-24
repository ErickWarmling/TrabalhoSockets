package Controller;

import Model.Empresa;
import Model.Funcionario;
import Model.Gerente;

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

    public void insertEmpresa (String razaoSocial, double capitalSocial) {
        if (!empresas.containsKey(razaoSocial)) {
            empresas.put(razaoSocial, new Empresa(razaoSocial, capitalSocial));
        }
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

        empresa.adicionarFuncionario(funcionario);
        return "Funcionário vinculado à empresa " + razaoSocial;
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

        empresa.adicionarGerente(gerente);
        return "Gerente vinculado à empresa " + razaoSocial;
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
