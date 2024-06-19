package org.example;
import Log.LogMonitoramento;
import processos.*;
import rede.RedeService;
import volume.VolumeService;
import processador.ProcessadorService;
import ram.RamService;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static Log.LogMonitoramento.generateLog;

public class ConsoleService {
    public ConsoleService() {
    }

    public static void main(String[] args) throws IOException {

        Console console = new Console();
        String usuario = null;
        String senha = null;
        Scanner in = new Scanner(System.in);
        Scanner escolhaScanner = new Scanner(System.in);
        Timer timer = new Timer();
        Timer timer2 = new Timer();

        Console finalConsole = console;
        Boolean executar = true;
        DateTimeFormatter formatadorDeData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


        new Timer();
        if (console.getUser() == null && console.getPassword() == null) {

            System.out.println("""
                     /* ╔═════════════════════════════════════════╗ */
                     /* ║                                         ║ */
                     /* ║   ░█▀▀▄░█▀▀░█▄█░░░█░█░▀█▀░█▀█░█▀▄░█▀█   ║ */
                     /* ║   ░█▀▀▄░█▀▀░█░█░░░█ █░░█░░█░█░█░█░█░█   ║ */
                     /* ║   ░▀▀▀░░▀▀▀░▀░▀░░░░▀░░▀▀▀░▀░▀░▀▀░░▀▀▀   ║ */
                     /* ║                                         ║ */
                     /* ║    ░░░░█░█░█▀▀░█░█░█▀█░█▀▄░▀█▀░█▀█      ║ */
                     /* ║    ░░░░█░█░▀▀█░█░█░█▀█░█▀▄░░█░░█░█      ║ */
                     /* ║    ░░░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀▀░▀▀▀      ║ */
                     /* ║                                         ║ */
                     /* ╚═════════════════════════════════════════╝ */
                    """);

            System.out.println("""
                    ═══════════════════════════════════════════════════
                    """);

            System.out.println("""
                    /* ╔══════════════════════════╗ */
                    /* ║   ░█░░░█▀█░█▀▀░▀█▀░█▀█   ║ */
                    /* ║   ░█░░░█░█░█░█░░█░░█░█   ║ */
                    /* ║   ░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀   ║ */
                    /* ╚══════════════════════════╝
                    /* ╔══════════════════════════╗ */ 
                    /* ║ E-mail:                  ║ */""");

            usuario = in.nextLine();
            System.out.println("""
                    /* ╔══════════════════════════╗ */ 
                    /* ║  Senha:                  ║ */""");
            senha = in.nextLine();

            System.out.println("""
                    /* ╚══════════════════════════╝ */""");

        }
        console.setUser(usuario);
        console.setPassword(senha);
        new ConsoleDAOVm().login(console);
        //new ConsoleDAO().login(console);

        if (!console.getEntrou()) {
            System.out.println("""
                    /* ╔═════════════════════════════════════════╗ */
                    /* ║                                         ║ */
                    /* ║                  ERRO                   ║ */
                    /* ║     LOGIN INVALIDO, TENTE NOVAMENTE     ║ */
                    /* ║                                         ║ */
                    /* ╚═════════════════════════════════════════╝ */
                      """);

        } else {
            System.out.println("""
                    /* ╔═════════════════════════════════════════╗ */
                    /* ║                                         ║ */
                    /* ║                 LOGADO                  ║ */
                    /* ║        LOGIN REALIZADO COM SUCESSO      ║ */
                    /* ║                                         ║ */
                    /* ╚═════════════════════════════════════════╝ */
                      """);
            while (executar) {
                System.out.println("""
                                            
                         /* ╔═════════════════════════════════════╗ */
                         /* ║    Oque deseja realizar, %s    ║ */
                         /* ║═════════════════════════════════════║ */
                         /* ║   1 - Coletar Dados                 ║ */
                         /* ║   2 - Encerrar Processos            ║ */
                         /* ║   3 - Sair                          ║ */
                         /* ║═════════════════════════════════════║ */
                         /* ║  Digite uma opção:                  ║ */
                         /* ╚═════════════════════════════════════╝ */
                        """.formatted(console.getNome()));
                Integer opcao = in.nextInt();
                console.setOpcao(opcao);
                LogMonitoramento.LogOpcao(console, true);

                switch (opcao) {

                    case 1:
                        System.out.println("""
                         /* ╔═════════════════════════════════════╗ */
                         /* ║    Oque deseja realizar, %s    ║ */
                         /* ║═════════════════════════════════════║ */
                         /* ║   1 - Iniciar Coleta de Dados       ║ */
                         /* ║   2 - Parar                         ║ */
                         /* ║═════════════════════════════════════║ */
                         /* ║  Digite uma opção:                  ║ */
                         /* ╚═════════════════════════════════════╝ */
                        """.formatted(console.getNome()));

                        Integer escolhaInt = escolhaScanner.nextInt();
                        console.setEscolha(escolhaInt);

                        if (escolhaInt == 1) {
                            LocalDateTime localDateTime = LocalDateTime.now();
                            generateLog("{" + formatadorDeData.format(localDateTime) + "} " +"[INFO] " + " Iniciando coleta... " + "\n");
                            LogMonitoramento.LogEscolha(finalConsole);

                            System.out.println("""
                             /* ╔═════════════════════════════════════╗ */
                             /* ║   2 - Parar                         ║ */
                             /* ║═════════════════════════════════════║ */
                             /* ║  Digite a opção:                    ║ */
                             /* ╚═════════════════════════════════════╝ */
                            """);

                            TimerTask tarefa = new TimerTask() {
                                Integer contagem = 0;

                                public void run() {
                                    contagem++;
                                    System.out.println("Executando tarefa... Contagem: " + contagem);
                                    VolumeService.coletarDadosDisco(finalConsole);
                                    try {
                                        ProcessadorService.coletaDeProcessador(finalConsole);
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    RamService.coletaDeRam(finalConsole);
                                    RedeService.coletaRede(finalConsole);

                                    TimerTask task = new TimerTask() {
                                        @Override
                                        public void run() {
                                            try {
                                                finalConsole.setContagem(contagem);
                                                LogMonitoramento.LogEscolha(finalConsole);

                                            } catch (IOException e) {
                                                System.out.println("ERROR");
                                            }
                                        }
                                    };

                                    timer2.scheduleAtFixedRate(task, 1000, 10000);


                                }
                            };
                            timer.scheduleAtFixedRate(tarefa, 0, 1000L);


                            boolean coletaAtiva = true;
                            while (coletaAtiva) {
                                Integer novaEscolhaInt = escolhaScanner.nextInt();
                                if (novaEscolhaInt == 2) {
                                    console.setEscolha(novaEscolhaInt);
                                    timer.cancel();
                                    System.out.println("""
                                     /* ╔═════════════════════════════════════════╗ */
                                     /* ║                                         ║ */
                                     /* ║        COLETA DE DADOS CANCELADA        ║ */
                                     /* ║                                         ║ */
                                     /* ╚═════════════════════════════════════════╝ */
                                    """);
                                    coletaAtiva = false;
                                    LocalDateTime localDate = LocalDateTime.now();
                                    generateLog("{" + formatadorDeData.format(localDate) + "} " +"[INFO] " + " Coleta de dados cancelada. " + "\n");
                                }
                            }
                        }
                        break;
                    case 2:
                        System.out.println("""
                         /* ╔═════════════════════════════════════════╗ */
                         /* ║                                         ║ */
                         /* ║        ENCERRAR PROCESSOS INCIADO       ║ */
                         /* ║                                         ║ */
                         /* ╚═════════════════════════════════════════╝ */
                        """);
                        Navegadores.EncerrarProcessos();
                        Outros.EncerrarProcessos();
                        Utilitarios.EncerrarProcessos();
                        break;
                    case 3:
                        executar = false;
                        System.out.println("""
                                 /* ╔═════════════════════════════════════════╗ */
                                 /* ║                                         ║ */
                                 /* ║                  ADEUS                  ║ */
                                 /* ║                                         ║ */
                                 /* ╚═════════════════════════════════════════╝ */
                                """.formatted(console.getUser()));
                    break;
                }
            }
        }
    }
}
