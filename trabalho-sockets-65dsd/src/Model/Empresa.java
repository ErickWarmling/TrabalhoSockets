package Model;

import java.util.*;

public class Empresa {

    private String razaoSocial;
    private double capitalSocial;
    private Map<String, Funcionario> funcionarios;
    private Map<String, Gerente> gerentes;

    public Empresa(String razaoSocial, double capitalSocial) {
        this.razaoSocial = razaoSocial;
        this.capitalSocial = capitalSocial;
        this.funcionarios = new HashMap<>();
        this.gerentes = new HashMap<>();
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public double getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public Map<String, Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Map<String, Gerente> getGerentes() {
        return gerentes;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        String cpf = funcionario.getCpf();
        if (!this.funcionarios.containsKey(cpf)) {
            this.funcionarios.put(cpf, funcionario);
            funcionario.setEmpresa(this);
        }
    }

    public void removerFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario.getCpf());
        funcionario.setEmpresa(null);
    }

    public void adicionarGerente(Gerente gerente) {
        String cpf = gerente.getCpf();
        if (!this.gerentes.containsKey(cpf)) {
            this.gerentes.put(cpf, gerente);
            gerente.setEmpresa(this);
        }
    }

    public void removerGerente(Gerente gerente) {
        this.gerentes.remove(gerente.getCpf());
        gerente.setEmpresa(null);
    }

    @Override
    public String toString() {
        return razaoSocial + ';' + capitalSocial;
    }
}