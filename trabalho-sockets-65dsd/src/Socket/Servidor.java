package Socket;

import Controller.EmpresaController;
import Controller.FuncionarioController;
import Controller.GerenteController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private FuncionarioController funcionarioController;
    private GerenteController gerenteController;
    private EmpresaController empresaController;

    public Servidor() {
        this.funcionarioController = new FuncionarioController();
        this.gerenteController = new GerenteController();
        this.empresaController = new EmpresaController(funcionarioController, gerenteController);
    }

    public void inicializarServidor(int porta) throws IOException {
       try (ServerSocket serverSocket = new ServerSocket(porta);) {
           System.out.println("Servidor rodando na porta " + porta);

           while (true) {
               Socket socket = serverSocket.accept();
               System.out.println("Conexão recebida");

               BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

               String mensagem = in.readLine();
               System.out.println(mensagem);

               String resposta = processarMensagem(mensagem);
               out.println(resposta);

               in.close();
               out.close();
               socket.close();
               System.out.println("Socket encerrado pelo servidor");
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public String processarMensagem(String msg) {
        if (msg == null || msg.isEmpty()) {
            return "Mensagem inválida";
        }

        String[] camposMensagem = msg.split(";");
        String operacao = camposMensagem[0].toUpperCase();

        switch (operacao) {
            case "INSERT_FUNCIONARIO":
                funcionarioController.insertFuncionario(camposMensagem[1], camposMensagem[2], camposMensagem[3], Double.parseDouble(camposMensagem[4]));
                return "Funcionário incluído com sucesso";
            case "UPDATE_FUNCIONARIO":
                return funcionarioController.updateFuncionario(camposMensagem[1], camposMensagem[2], camposMensagem[3], Double.parseDouble(camposMensagem[4]));
            case "GET_FUNCIONARIO":
                return funcionarioController.getFuncionario(camposMensagem[1]);
            case "DELETE_FUNCIONARIO":
                return funcionarioController.deleteFuncionario(camposMensagem[1]);
            case "LIST_FUNCIONARIO":
                return funcionarioController.listFuncionarios();

            case "INSERT_GERENTE":
               gerenteController.insertGerente(camposMensagem[1], camposMensagem[2], camposMensagem[3], camposMensagem[4]);
               return "Gerente incluído com sucesso";
            case "UPDATE_GERENTE":
                return gerenteController.updateGerente(camposMensagem[1], camposMensagem[2], camposMensagem[3], camposMensagem[4]);
            case "GET_GERENTE":
                return gerenteController.getGerente(camposMensagem[1]);
            case "DELETE_GERENTE":
                return gerenteController.deleteGerente(camposMensagem[1]);
            case "LIST_GERENTE":
                return gerenteController.listGerentes();

            case "INSERT_EMPRESA":
                empresaController.insertEmpresa(camposMensagem[1], Double.parseDouble(camposMensagem[2]));
                return "Empresa incluída com sucesso";
            case "UPDATE_EMPRESA":
                return empresaController.updateEmpresa(camposMensagem[1], Double.parseDouble(camposMensagem[2]));
            case "GET_EMPRESA":
                return empresaController.getEmpresa(camposMensagem[1]);
            case "DELETE_EMPRESA":
                return empresaController.deleteEmpresa(camposMensagem[1]);
            case "LIST_EMPRESA":
                return empresaController.listEmpresas();
            case "VINCULAR_PESSOA_EMPRESA":
                String razaoSocial = camposMensagem[1];
                String cpf = camposMensagem[2];

                if (funcionarioController.getFuncionarios().containsKey(cpf)) {
                    return empresaController.vincularFuncionario(razaoSocial, cpf);
                } else if (gerenteController.getGerentes().containsKey(cpf)) {
                    return empresaController.vincularGerente(razaoSocial, cpf);
                } else {
                    return "Pessoa não encontrada";
                }
            default:
                return "Operação inválida!";
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.inicializarServidor(65000);
    }
}
