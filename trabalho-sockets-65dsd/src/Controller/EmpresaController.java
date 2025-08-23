package Controller;

import Model.Empresa;
import Model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class EmpresaController {

    private List<Empresa> empresas;
    private PessoaController pessoaController;

    public EmpresaController() {
        this.empresas = new ArrayList<>();
        this.pessoaController = new PessoaController();
    }

    public void insertEmpresa (String razaoSocial, double capitalSocial) {
        Empresa empresa = new Empresa(razaoSocial, capitalSocial);
        if (!empresas.contains(empresa)) {
            empresas.add(empresa);
        }
    }

    public String updateEmpresa(String razaoSocial, double capitalSocial) {
        for (Empresa empresa : empresas) {
            if (empresa.getRazaoSocial().equalsIgnoreCase(razaoSocial)) {
                empresa.setCapitalSocial(capitalSocial);
                return "Empresa atualizada com sucesso";
            }
        }
        return "Empresa não encontrada";
    }

    public String getEmpresa(String razaoSocial) {
        if (empresas.isEmpty()) {
            return "Sem empresas cadastradas";
        }

        for (Empresa empresa : empresas) {
            if (empresa.getRazaoSocial().equalsIgnoreCase(razaoSocial)) {
                return empresa.toString();
            }
        }
        return "Empresa não encontrada";
    }

    public String deleteEmpresa(String razaoSocial) {
        if (empresas.isEmpty()) {
            return "Sem empresas cadastradas";
        }

        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getRazaoSocial().equalsIgnoreCase(razaoSocial)) {
                empresas.remove(i);
                return "Empresa removida com sucesso";
            }
        }
        return "Empresa não encontrada";
    }

    public String listEmpresas() {
        if (empresas.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%02d", empresas.size())).append("\n");
        for (Empresa empresa : empresas) {
            stringBuilder.append(empresa.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public String vincularPessoa(String cpf, String razaoSocial) {
        Empresa empresa = null;
        for (Empresa emp : empresas) {
            if (emp.getRazaoSocial().equalsIgnoreCase(razaoSocial)) {
                empresa = emp;
                break;
            }
        }

        if (empresa == null) {
            return "Empresa não encontrada";
        }

        Pessoa pessoa = null;
        for (Pessoa pes : pessoaController.getPessoas()) {
            if (pes.getCpf().equals(cpf)) {
                pessoa = pes;
                break;
            }
        }

        if (pessoa == null) {
            return "Pessoa não encontrada";
        }

        empresa.adicionarPessoa(pessoa);
        return "Pessoa vinculada a empresa com sucesso";
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }
}
