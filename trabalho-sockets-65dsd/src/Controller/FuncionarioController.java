package Controller;

import Model.Funcionario;

import java.util.HashMap;
import java.util.Map;

public class FuncionarioController {
    private Map<String, Funcionario> funcionarios = new HashMap<>();

    public String insertFuncionario(String cpf, String nome, String endereco, double salario){
        cpf = cpf.trim();

        if(funcionarios.containsKey(cpf)){
            return "Funcionário já existe";
        }

        funcionarios.put(cpf, new Funcionario(cpf, nome, endereco, salario));
        return "Funcionário inserido com sucesso";
    }

    public String updateFuncionario(String cpf, String nome, String endereco, double salario){
        cpf = cpf.trim();
        Funcionario fun = funcionarios.get(cpf);
        if(fun != null){
            fun.setNome(nome);
            fun.setEndereco(endereco);
            fun.setSalario(salario);
            return "Funcionário atualizado";
        }else{
            return "Funcionário não encontrado";
        }
    }

    public String getFuncionario(String cpf){
        cpf = cpf.trim();
        Funcionario fun = funcionarios.get(cpf);
        if(fun != null){
            return fun.toString();
        }else{
            return "Funcionário não encontrado";
        }
    }

    public String deleteFuncionario(String cpf){
        cpf = cpf.trim();
        Funcionario fun = funcionarios.remove(cpf);
        if(fun != null){
            return "Funcionário removido com sucesso";
        }else{
            return "Funcionário não encontrado";
        }
    }

    public String listFuncionarios(){
        if(!funcionarios.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%02d", funcionarios.size())).append("\n");
            for(Funcionario fun : funcionarios.values()){
                sb.append(fun.toString()).append("\n");
            }
            return sb.toString();
        }else{
            return "0";
        }
    }

    public Map<String, Funcionario> getFuncionarios(){
        return funcionarios;
    }
}
