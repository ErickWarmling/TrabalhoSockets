package Cliente;

import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("cliente.properties")) {
            props.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Não foi possível ler o cliente.properties");
        }

        String servidor = props.getProperty("servidor");
        int porta = Integer.parseInt(props.getProperty("porta"));

        Scanner s = new Scanner(System.in);

        while (true) {
            Socket socket = null;

            System.out.println("\n===== TRABALHO 1 SOCKET (65DSD) =====");
            System.out.println("1. Inserir Funcionário");
            System.out.println("2. Atualizar Funcionário");
            System.out.println("3. Buscar Funcionário");
            System.out.println("4. Remover Funcionário");
            System.out.println("5. Listar Funcionários");
            System.out.println("6. Inserir Gerente");
            System.out.println("7. Atualizar Gerente");
            System.out.println("8. Buscar Gerente");
            System.out.println("9. Remover Gerente");
            System.out.println("10. Listar Gerentes");
            System.out.println("11. Inserir Empresa");
            System.out.println("12. Atualizar Empresa");
            System.out.println("13. Buscar Empresa");
            System.out.println("14. Remover Empresa");
            System.out.println("15. Listar Empresas");
            System.out.println("16. Vincular Pessoa à Empresa");
            System.out.println("17. Desvincular Pessoa da Empresa");
            System.out.println("0. Sair do sistema");
            System.out.println("Escolha uma opcão: ");

            int opcao = Integer.parseInt(s.nextLine());
            String mensagem = "";

            switch (opcao) {
                case 1:
                    System.out.println("CPF: ");
                    String cpf = s.nextLine();
                    System.out.println("Nome: ");
                    String nome = s.nextLine();
                    System.out.println("Endereço: ");
                    String endereco = s.nextLine();
                    System.out.println("Salário: ");
                    double salario = Double.parseDouble(s.nextLine());
                    mensagem = "INSERT_FUNCIONARIO;" + cpf + ";" + nome + ";" + endereco + ";" + salario;
                    break;
                case 2:
                    System.out.println("CPF: ");
                    cpf = s.nextLine();
                    System.out.println("Novo nome: ");
                    nome = s.nextLine();
                    System.out.println("Novo endereço: ");
                    endereco = s.nextLine();
                    System.out.println("Novo salário: ");
                    salario = Double.parseDouble(s.nextLine());
                    mensagem = "UPDATE_FUNCIONARIO;" + cpf + ";" + nome + ";" + endereco + ";" + salario;
                    break;
                case 3:
                    System.out.println("CPF do Funcionário: ");
                    cpf = s.nextLine();
                    mensagem = "GET_FUNCIONARIO;" + cpf;
                    break;
                case 4:
                    System.out.println("CPF do Funcionário: ");
                    cpf = s.nextLine();
                    mensagem = "DELETE_FUNCIONARIO;" + cpf;
                    break;
                case 5:
                    mensagem = "LIST_FUNCIONARIO";
                    break;

                case 6:
                    System.out.println("CPF: ");
                    cpf = s.nextLine();
                    System.out.println("Nome: ");
                    nome = s.nextLine();
                    System.out.println("Endereço: ");
                    endereco = s.nextLine();
                    System.out.println("Departamento: ");
                    String departamento = s.nextLine();
                    mensagem = "INSERT_GERENTE;" + cpf + ";" + nome + ";" + endereco + ";" + departamento;
                    break;
                case 7:
                    System.out.println("CPF: ");
                    cpf = s.nextLine();
                    System.out.println("Novo nome: ");
                    nome = s.nextLine();
                    System.out.println("Novo endereço: ");
                    endereco = s.nextLine();
                    System.out.println("Novo departamento: ");
                    departamento = s.nextLine();
                    mensagem = "UPDATE_GERENTE;" + cpf + ";" + nome + ";" + endereco + ";" + departamento;
                    break;
                case 8:
                    System.out.println("CPF do Gerente: ");
                    cpf = s.nextLine();
                    mensagem = "GET_GERENTE;" + cpf;
                    break;
                case 9:
                    System.out.println("CPF do Gerente: ");
                    cpf = s.nextLine();
                    mensagem = "DELETE_GERENTE;" + cpf;
                    break;
                case 10:
                    mensagem = "LIST_GERENTE";
                    break;

                case 11:
                    System.out.println("Razão Social: ");
                    String razaoSocial = s.nextLine();
                    System.out.println("Capital Social: ");
                    double capitalSocial = Double.parseDouble(s.nextLine());
                    mensagem = "INSERT_EMPRESA;" + razaoSocial + ";" + capitalSocial;
                    break;
                case 12:
                    System.out.println("Razão Social: ");
                    razaoSocial = s.nextLine();
                    System.out.println("Novo capital social: ");
                    capitalSocial = s.nextDouble();
                    s.nextLine();
                    mensagem = "UPDATE_EMPRESA;" + razaoSocial + ";" + capitalSocial;
                    break;
                case 13:
                    System.out.println("Razão Social: ");
                    razaoSocial = s.nextLine();
                    mensagem = "GET_EMPRESA;" + razaoSocial;
                    break;
                case 14:
                    System.out.println("Razão Social: ");
                    razaoSocial = s.nextLine();
                    mensagem = "DELETE_EMPRESA;" + razaoSocial;
                    break;
                case 15:
                    mensagem = "LIST_EMPRESA";
                    break;
                case 16:
                    System.out.println("Razão Social da Empresa: ");
                    razaoSocial = s.nextLine();
                    System.out.println("CPF da Pessoa: ");
                    cpf = s.nextLine();
                    mensagem = "VINCULAR_PESSOA_EMPRESA;" + razaoSocial + ";" + cpf;
                    break;
                case 17:
                    System.out.println("Razão Social da Empresa: ");
                    razaoSocial = s.nextLine();
                    System.out.println("CPF da Pessoa: ");
                    cpf = s.nextLine();
                    mensagem = "DESVINCULAR_PESSOA_EMPRESA;" + razaoSocial + ";" + cpf;
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    s.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            try {
                socket = new Socket(servidor, porta);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println(mensagem);

                StringBuilder resposta = new StringBuilder();
                String linha;
                while ((linha = in.readLine()) != null) {
                    resposta.append(linha).append("\n");
                }
                System.out.print("Resposta Servidor:\n" + resposta);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    socket.close();
                    System.out.println("Socket encerrado");
                }
            }
        }
    }
}