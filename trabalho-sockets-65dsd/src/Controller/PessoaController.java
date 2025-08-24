//package Controller;
//
//import Model.Pessoa;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class PessoaController {
//
//    private Map<String, Pessoa> pessoas;
//
//    public PessoaController() {
//        this.pessoas = new HashMap<>();
//    }
//
//    public void insertPessoa(String cpf, String nome, String endereco) {
//        if (!pessoas.containsKey(cpf)) {
//            pessoas.put(cpf, new Pessoa(cpf, nome, endereco));
//        }
//    }
//
//    public String updatePessoa(String cpf, String nome, String endereco) {
//        Pessoa pessoa = pessoas.get(cpf);
//        if (pessoa != null) {
//            pessoa.setNome(nome);
//            pessoa.setEndereco(endereco);
//            return "Pessoa atualizada com sucesso";
//        }
//        return "Pessoa não encontrada";
//    }
//
//    public String getPessoa(String cpf) {
//        if (pessoas.isEmpty()) {
//            return "Sem pessoas cadastradas";
//        }
//
//        Pessoa pessoa = pessoas.get(cpf);
//        if (pessoa != null) {
//            return pessoa.toString();
//        }
//        return "Pessoa não encontrada";
//    }
//
//    public String deletePessoa(String cpf) {
//        if (pessoas.isEmpty()) {
//            return "Sem pessoas cadastradas";
//        }
//
//        Pessoa pessoa = pessoas.remove(cpf);
//        if (pessoa != null) {
//            return "Pessoa removida com sucesso";
//        }
//        return "Pessoa não encontrada";
//    }
//
//    public String listPessoas() {
//        if (pessoas.isEmpty()) {
//            return "0";
//        }
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(String.format("%02d", pessoas.size())).append("\n");
//        for (Pessoa pessoa : pessoas.values()) {
//            stringBuilder.append(pessoa.toString()).append("\n");
//        }
//        return stringBuilder.toString();
//    }
//
//    public Pessoa getPessoaCpf(String cpf) {
//        return pessoas.get(cpf);
//    }
//
//    public Map<String, Pessoa> getPessoas() {
//        return pessoas;
//    }
//}
