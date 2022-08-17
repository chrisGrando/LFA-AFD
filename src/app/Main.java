/**
*** @author chrisGrando
*** Classe destinada somente à inicialização básica.
*** (Lógica do software fica armazenada em AppLogic)
**/
package app;

public class Main {

    public static void main(String[] args) {
        //Inicializa classe de lógica do aplicativo
        AppLogic app = new AppLogic();
        app.start(args);
        
        //Executa o programa
        app.exec();
    }
}
