package Servidor.Controller;

import Servidor.Model.Funcionario;

import java.util.HashMap;
import java.util.Map;

public class FuncionarioController {

    private Map<String, Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = new HashMap<>();
    }

    public String insertFuncionario(String cpf, String nome, String endereco, double salario){
        if (!funcionarios.containsKey(cpf)) {
            funcionarios.put(cpf, new Funcionario(cpf, nome, endereco, salario));
            return "Funcionário incluído com sucesso";
        }
        return "Funcionário já existente";
    }

    public String updateFuncionario(String cpf, String nome, String endereco, double salario){
        Funcionario fun = funcionarios.get(cpf);
        if (fun != null) {
            fun.setNome(nome);
            fun.setEndereco(endereco);
            fun.setSalario(salario);
            return "Funcionário atualizado com sucesso";
        } else {
            return "Funcionário não encontrado";
        }
    }

    public String getFuncionario(String cpf){
        if (funcionarios.isEmpty()) {
            return "Sem funcionários cadastrados";
        }

        Funcionario fun = funcionarios.get(cpf);
        if(fun != null){
            return fun.toString();
        }else{
            return "Funcionário não encontrado";
        }
    }

    public String deleteFuncionario(String cpf){
        if (funcionarios.isEmpty()) {
            return "Sem funcionários cadastrados";
        }

        Funcionario fun = funcionarios.get(cpf);
        if(fun != null){
            if (fun.getEmpresa() != null) {
                fun.getEmpresa().removerFuncionario(fun);
            }
            funcionarios.remove(cpf);
            return "Funcionário removido com sucesso";
        }
        return "Funcionário não encontrado";
    }

    public String listFuncionarios(){
        if (funcionarios.isEmpty()) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%02d", funcionarios.size())).append("\n");
        for(Funcionario fun : funcionarios.values()){
            stringBuilder.append(fun.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public Map<String, Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
