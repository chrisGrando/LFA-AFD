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
            @Override public void println(Object obj) {
                stdout.println(obj);
                super.print(obj);
                super.print("\n");
            }
            @Override public void println(String s) {
                stdout.println(s);
                super.print(s);
                super.print("\n");
            }
            @Override public void println(boolean b) {
                stdout.println(b);
                super.print(b);
                super.print("\n");
            }
            @Override public void println(char c) {
                stdout.println(c);
                super.print(c);
                super.print("\n");
            }
            @Override public void println(char[] cv) {
                stdout.println(cv);
                super.print(cv);
                super.print("\n");
            }
            @Override public void println(double d) {
                stdout.println(d);
                super.print(d);
                super.print("\n");
            }
            @Override public void println(float f) {
                stdout.println(f);
                super.print(f);
                super.print("\n");
            }
            @Override public void println(int i) {
                stdout.println(i);
                super.print(i);
                super.print("\n");
            }
            @Override public void println(long l) {
                stdout.println(l);
                super.print(l);
                super.print("\n");
            }
            //PRINT
            @Override public void print(Object obj) {
                stdout.print(obj);
                super.print(obj);
            }
            @Override public void print(String s) {
                stdout.print(s);
                super.print(s);
            }
            @Override public void print(boolean b) {
                stdout.print(b);
                super.print(b);
            }
            @Override public void print(char c) {
                stdout.print(c);
                super.print(c);
            }
            @Override public void print(char[] cv) {
                stdout.print(cv);
                super.print(cv);
            }
            @Override public void print(double d) {
                stdout.print(d);
                super.print(d);
            }
            @Override public void print(float f) {
                stdout.print(f);
                super.print(f);
            }
            @Override public void print(int i) {
                stdout.print(i);
                super.print(i);
            }
            @Override public void print(long l) {
                stdout.print(l);
                super.print(l);
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
            @Override public void println(Object obj) {
                stderr.println(obj);
                super.print(obj);
                super.print("\n");
            }
            @Override public void println(String s) {
                stderr.println(s);
                super.print(s);
                super.print("\n");
            }
            @Override public void println(boolean b) {
                stderr.println(b);
                super.print(b);
                super.print("\n");
            }
            @Override public void println(char c) {
                stderr.println(c);
                super.print(c);
                super.print("\n");
            }
            @Override public void println(char[] cv) {
                stderr.println(cv);
                super.print(cv);
                super.print("\n");
            }
            @Override public void println(double d) {
                stderr.println(d);
                super.print(d);
                super.print("\n");
            }
            @Override public void println(float f) {
                stderr.println(f);
                super.print(f);
                super.print("\n");
            }
            @Override public void println(int i) {
                stderr.println(i);
                super.print(i);
                super.print("\n");
            }
            @Override public void println(long l) {
                stderr.println(l);
                super.print(l);
                super.print("\n");
            }
            //PRINT
            @Override public void print(Object obj) {
                stderr.print(obj);
                super.print(obj);
            }
            @Override public void print(String s) {
                stderr.print(s);
                super.print(s);
            }
            @Override public void print(boolean b) {
                stderr.print(b);
                super.print(b);
            }
            @Override public void print(char c) {
                stderr.print(c);
                super.print(c);
            }
            @Override public void print(char[] cv) {
                stderr.print(cv);
                super.print(cv);
            }
            @Override public void print(double d) {
                stderr.print(d);
                super.print(d);
            }
            @Override public void print(float f) {
                stderr.print(f);
                super.print(f);
            }
            @Override public void print(int i) {
                stderr.print(i);
                super.print(i);
            }
            @Override public void print(long l) {
                stderr.print(l);
                super.print(l);
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
