package Socket;

import Controller.EmpresaController;
import Controller.PessoaController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private PessoaController pessoaController;
    private EmpresaController empresaController;

    public Servidor() {
        this.pessoaController = new PessoaController();
        this.empresaController = new EmpresaController();
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
            case "INSERT":
               pessoaController.insertPessoa(camposMensagem[1], camposMensagem[2], camposMensagem[3]);
               return "Pessoa incluída com sucesso";
            case "UPDATE":
                return pessoaController.updatePessoa(camposMensagem[1], camposMensagem[2], camposMensagem[3]);
            case "GET":
                return pessoaController.getPessoa(camposMensagem[1]);
            case "DELETE":
                return pessoaController.deletePessoa(camposMensagem[1]);
            case "LIST":
                return pessoaController.listPessoas();

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
                return empresaController.vincularPessoa(camposMensagem[1], camposMensagem[2]);
            default:
                return "Operação inválida!";
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor servidor = new Servidor();
        servidor.inicializarServidor(65000);
    }
}
