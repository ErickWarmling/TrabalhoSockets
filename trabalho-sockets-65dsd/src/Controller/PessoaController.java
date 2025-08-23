package Controller;

import Model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaController {

    private List<Pessoa> pessoas;

    public PessoaController() {
        this.pessoas = new ArrayList<>();
    }

    public void insertPessoa(String cpf, String nome, String endereco) {
        Pessoa pessoa = new Pessoa(cpf, nome,endereco);
        if (!pessoas.contains(pessoa)) {
            pessoas.add(pessoa);
        }
    }

    public String updatePessoa(String cpf, String nome, String endereco) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                pessoa.setNome(nome);
                pessoa.setEndereco(endereco);
                return "Pessoa atualizada com sucesso";
            }
        }
        return "Pessoa não encontrada";
    }

    public String getPessoa(String cpf) {
        if (pessoas.isEmpty()) {
            return "Sem pessoas cadastradas";
        }

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf)) {
                return pessoa.toString();
            }
        }
        return "Pessoa não encontrada";
    }

    public String deletePessoa(String cpf) {
        if (pessoas.isEmpty()) {
            return "Sem pessoas cadastradas";
        }

        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getCpf().equals(cpf)) {
                pessoas.remove(i);
                return "Pessoa removida com sucesso";
            }
        }
        return "Sem pessoas cadastradas";
    }

    public String listPessoas() {
        if (pessoas.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%02d", pessoas.size())).append("\n");
        for (Pessoa pessoa : pessoas) {
            stringBuilder.append(pessoa.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
}
