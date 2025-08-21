package Model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

    private String razaoSocial;
    private double capitalSocial;
    private List<Pessoa> pessoas;

    public Empresa(String razaoSocial, double capitalSocial) {
        this.razaoSocial = razaoSocial;
        this.capitalSocial = capitalSocial;
        this.pessoas = new ArrayList<>();
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

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void adicionarPessoa(Pessoa pessoa) {
        if (!this.pessoas.contains(pessoa)) {
            this.pessoas.add(pessoa);
            pessoa.setEmpresa(this);
        }
    }

    public void removerPessoa(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
        pessoa.setEmpresa(null);
    }

    @Override
    public String toString() {
        return razaoSocial + ';' + capitalSocial;
    }
}
