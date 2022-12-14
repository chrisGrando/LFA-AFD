/**
*** @author chrisGrando
*** Classe destinada à lógica global do aplicativo.
**/
package app;

import app.gui.SimpleGUI;
import lfa.DFA;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AppLogic {
    
    //Inicia a aplicação
    public void start(String[] args) {
        //Log de início
        this.initLog();
        
        //Parâmetros passados por linha de comando
        for(String currentArg : args) {
            /*
            Parâmetro contém hífem.
            ======================================================
            Usado para ativação de gatilhos.
            Exemplo: -gui => interface gráfica
            */
            if(currentArg.startsWith("-")) {
                //Ativar interface gráfica
                if(currentArg.regionMatches(1, "gui", 0, 3)) {
                    Globals.GUI = true;
                    System.out.println("GUI => ON");
                }
            }
            /*
            Parâmetro NÃO contém hífem.
            ======================================================
            Usado como caminho de diretório relativo/absoluto para
            planílhas de entrada e/ou saída.
            */
            else {
                //Arquivo de entrada
                if(Globals.INPUT == null) {
                    Globals.INPUT = currentArg;
                    System.out.println("Input => OK");
                }
                //Arquivo de saída
                else if(Globals.OUTPUT == null) {
                    Globals.OUTPUT = currentArg;
                    System.out.println("Output => OK");
                }
            }
        }
        /*
        Ativar interface gráfica se não existirem parâmetos, ou então,
        caso o caminho de diretório de entrada/saída seja nulo.
        */
        if(args == null || Globals.INPUT == null || Globals.OUTPUT == null) {
            Globals.GUI = true;
            System.out.println("Parâmetros inexistentes ou não satisfeitos...");
            System.out.println("GUI => ON");
        }
    }
    
    //Executa a aplicação
    public void exec() {
        //MODO: Interface Gráfica
        if(Globals.GUI) {
            SimpleGUI hud = new SimpleGUI();
            hud.startWindow();
        }
        //MODO: Linha de Comando
        else {
            DFA dfa = new DFA();
            dfa.cmdMode();
        }
    }
    
    //Log de início
    private void initLog() {
        System.out.println("################## LOG ##################");
        
        //Sistema Operacional
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String osVersion = System.getProperty("os.version");
        System.out.println("SO: " + osName + " | " + osArch + " | " + osVersion);
        
        //Data e Hora
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println("Data: " + dtf.format(now));
        
        //Diretório local
        String myDir = System.getProperty("user.dir");
        System.out.println("Diretório atual: " + myDir);
        
        //Classe de lógica iniciada
        System.out.println("AppLogic => ON");
    }
}
