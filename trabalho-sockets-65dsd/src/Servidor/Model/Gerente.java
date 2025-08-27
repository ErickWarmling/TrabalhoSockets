package Servidor.Model;

public class Gerente extends Pessoa {

    private String departamento;

    public Gerente(String cpf, String nome, String endereco, String departamento) {
        super(cpf, nome, endereco);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + departamento;
    }
}