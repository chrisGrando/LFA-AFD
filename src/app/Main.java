/**
*** @author chrisGrando
*** Classe destinada somente à inicialização básica.
*** (Lógica do software fica armazenada em AppLogic)
**/
package app;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        //Configura caracteres como "Unicode"
        try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        }
        catch(UnsupportedEncodingException error) {
            String msg = "Invalid Charset...";
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, msg, error);
        }
        
        //Inicializa classe de lógica do aplicativo
        AppLogic app = new AppLogic();
        app.start(args);
        
        //Executa o programa
        app.exec();
    }
}
