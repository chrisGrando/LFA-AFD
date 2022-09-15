/**
*** @author chrisGrando
*** Classe destinada a modificar o método de saída de texto (a.k.a. print).
**/
package app;

import java.io.File;
import java.io.PrintStream;

public class TextOutput {
    
    //Texto normal
    public void changeOUT(String path)
      throws Exception {
        final PrintStream stdout = System.out;
        
        //Diretório do arquivo
        File cd = new File(System.getProperty("user.dir"));
        File txtLog = new File(cd.getAbsolutePath() + path);
        
        //Cria arquivo de log de output caso não exista
        if(!txtLog.exists())
            this.createNewFile(txtLog);
        
        //Cria novas saídas de texto
        PrintStream ps = new PrintStream(txtLog) {
            //PRINTLN
            @Override public void println() {
                stdout.println();
                super.print("\n");
            }
            @Override public void println(Object x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(String x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(boolean x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(char x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(char[] x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(double x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(float x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(int x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(long x) {
                stdout.println(x);
                super.print(x);
                super.print("\n");
            }
            //PRINT
            @Override public void print(Object x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(String x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(boolean x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(char x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(char[] x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(double x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(float x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(int x) {
                stdout.print(x);
                super.print(x);
            }
            @Override public void print(long x) {
                stdout.print(x);
                super.print(x);
            }
        };
        
        //Substitui antiga saída de texto
        System.setOut(ps);
        
    }
    
    //Log de erros
    public void changeERR(String path)
      throws Exception {
        final PrintStream stderr = System.err;
        
        //Diretório do arquivo
        File cd = new File(System.getProperty("user.dir"));
        File txtLog = new File(cd.getAbsolutePath() + path);
        
        //Cria arquivo de log de erros caso não exista
        if(!txtLog.exists())
            this.createNewFile(txtLog);
        
        //Cria novas saídas de texto
        PrintStream ps = new PrintStream(txtLog) {
            //PRINTLN
            @Override public void println() {
                stderr.println();
                super.print("\n");
            }
            @Override public void println(Object x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(String x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(boolean x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(char x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(char[] x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(double x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(float x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(int x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            @Override public void println(long x) {
                stderr.println(x);
                super.print(x);
                super.print("\n");
            }
            //PRINT
            @Override public void print(Object x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(String x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(boolean x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(char x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(char[] x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(double x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(float x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(int x) {
                stderr.print(x);
                super.print(x);
            }
            @Override public void print(long x) {
                stderr.print(x);
                super.print(x);
            }
        };
        
        //Substitui antiga saída de texto
        System.setErr(ps);
    }
    
    //Cria novo arquivo de log
    private void createNewFile(File file)
      throws Exception {
        //Cria diretório(s)
        File fileDir = new File(file.getParent());
        fileDir.mkdirs();
        
        //Cria arquivo
        file.createNewFile();
    }
}
