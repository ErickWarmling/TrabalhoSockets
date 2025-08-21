package Model;

public class Funcionario extends Pessoa {

    private double salario;

    public Funcionario(String cpf, String nome, String endereco, double salario) {
        super(cpf, nome, endereco);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() + ';' + salario;
    }
}