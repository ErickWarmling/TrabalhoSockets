package Controller;

import Model.Empresa;

import java.util.ArrayList;
import java.util.List;

public class EmpresaContoller {

    private List<Empresa> empresas;

    public EmpresaContoller() {
        this.empresas = new ArrayList<>();
    }

    public void insertEmpresa (String razaoSocial, double capitalSocial) {
        Empresa empresa = new Empresa(razaoSocial, capitalSocial);
        empresas.add(empresa);
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
        stringBuilder.append(empresas.size());
        for (Empresa empresa : empresas) {
            stringBuilder.append(empresa.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
