package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        String servidor = "127.0.0.1";
        int porta = 65000;

        Scanner s = new Scanner(System.in);

        while (true) {
            Socket socket = null;

            System.out.println("\n===== TRABALHO 1 SOCKET (65DSD) =====");
            System.out.println("1. Inserir Pessoa");
            System.out.println("2. Atualizar Pessoa");
            System.out.println("3. Buscar Pessoa");
            System.out.println("4. Remover Pessoa");
            System.out.println("5. Listar Pessoas");
            System.out.println("6. Inserir Empresa");
            System.out.println("7. Atualizar Empresa");
            System.out.println("8. Buscar Empresa");
            System.out.println("9. Remover Empresa");
            System.out.println("10. Listar Empresas");
            System.out.println("11. Vincular Pessoa a Empresa");
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
                    mensagem = "INSERT;" + cpf + ";" + nome + ";" + endereco;
                    break;
                case 2:
                    System.out.println("CPF: ");
                    cpf = s.nextLine();
                    System.out.println("Novo nome: ");
                    nome = s.nextLine();
                    System.out.println("Novo endereço: ");
                    endereco = s.nextLine();
                    mensagem = "UPDATE;" + cpf + ";" + nome + ";" + endereco;
                    break;
                case 3:
                    System.out.println("CPF: ");
                    cpf = s.nextLine();
                    mensagem = "GET;" + cpf;
                    break;
                case 4:
                    System.out.println("CPF: ");
                    cpf = s.nextLine();
                    mensagem = "DELETE;" + cpf;
                    break;
                case 5:
                    mensagem = "LIST";
                    break;

                case 6:
                    System.out.println("Razão Social: ");
                    String razaoSocial = s.nextLine();
                    System.out.println("Capital Social: ");
                    double capitalSocial = s.nextDouble();
                    s.nextLine();
                    mensagem = "INSERT_EMPRESA;" + razaoSocial + ";" + capitalSocial;
                    break;
                case 7:
                    System.out.println("Razão Social: ");
                    razaoSocial = s.nextLine();
                    System.out.println("Novo capital social: ");
                    capitalSocial = s.nextDouble();
                    s.nextLine();
                    mensagem = "UPDATE_EMPRESA;" + razaoSocial + ";" + capitalSocial;
                    break;
                case 8:
                    System.out.println("Razão Social: ");
                    razaoSocial = s.nextLine();
                    mensagem = "GET_EMPRESA;" + razaoSocial;
                    break;
                case 9:
                    System.out.println("Razão Social: ");
                    razaoSocial = s.nextLine();
                    mensagem = "DELETE_EMPRESA;" + razaoSocial;
                    break;
                case 10:
                    mensagem = "LIST_EMPRESA";
                    break;
                case 11:
                    System.out.println("CPF da Pessoa: ");
                    cpf = s.nextLine();
                    System.out.println("Razão Social da Empresa: ");
                    razaoSocial = s.nextLine();
                    mensagem = "VINCULAR_PESSOA_EMPRESA;" + cpf + ";" + razaoSocial;
                    break;
                case 0:
                    System.out.println("Encerrando cliente...");
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