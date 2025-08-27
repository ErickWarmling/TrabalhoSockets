package Servidor;

import Servidor.Controller.EmpresaController;
import Servidor.Controller.FuncionarioController;
import Servidor.Controller.GerenteController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

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
               System.out.println("Aguardando conexão...");
               Socket socket = serverSocket.accept();
               System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

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
                return funcionarioController.insertFuncionario(camposMensagem[1], camposMensagem[2], camposMensagem[3], Double.parseDouble(camposMensagem[4]));
            case "UPDATE_FUNCIONARIO":
                return funcionarioController.updateFuncionario(camposMensagem[1], camposMensagem[2], camposMensagem[3], Double.parseDouble(camposMensagem[4]));
            case "GET_FUNCIONARIO":
                return funcionarioController.getFuncionario(camposMensagem[1]);
            case "DELETE_FUNCIONARIO":
                return funcionarioController.deleteFuncionario(camposMensagem[1]);
            case "LIST_FUNCIONARIO":
                return funcionarioController.listFuncionarios();

            case "INSERT_GERENTE":
               return gerenteController.insertGerente(camposMensagem[1], camposMensagem[2], camposMensagem[3], camposMensagem[4]);
            case "UPDATE_GERENTE":
                return gerenteController.updateGerente(camposMensagem[1], camposMensagem[2], camposMensagem[3], camposMensagem[4]);
            case "GET_GERENTE":
                return gerenteController.getGerente(camposMensagem[1]);
            case "DELETE_GERENTE":
                return gerenteController.deleteGerente(camposMensagem[1]);
            case "LIST_GERENTE":
                return gerenteController.listGerentes();

            case "INSERT_EMPRESA":
                return empresaController.insertEmpresa(camposMensagem[1], Double.parseDouble(camposMensagem[2]));
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
            case "DESVINCULAR_PESSOA_EMPRESA":
                razaoSocial = camposMensagem[1];
                cpf = camposMensagem[2];

                if (funcionarioController.getFuncionarios().containsKey(cpf)) {
                    return empresaController.desvincularFuncionario(razaoSocial, cpf);
                } else if (gerenteController.getGerentes().containsKey(cpf)) {
                    return empresaController.desvincularGerente(razaoSocial, cpf);
                } else {
                    return "Pessoa não encontrada";
                }
            default:
                return "Operação inválida!";
        }
    }

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("servidor.properties")) {
            props.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Não foi possível ler o servidor.properties");
        }

        int porta = Integer.parseInt(props.getProperty("porta"));
        Servidor servidor = new Servidor();
        servidor.inicializarServidor(porta);
    }
}
