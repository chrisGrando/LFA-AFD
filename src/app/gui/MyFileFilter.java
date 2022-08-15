/** 
*** @author chrisGrando
*** Classe destinada ao filtro de extensão de arquivo para abrir/salvar.
**/
package app.gui;

import java.io.File;

public class MyFileFilter extends javax.swing.filechooser.FileFilter {
    
    @Override
    public boolean accept(File file) {
        //Permite apenas diretórios ou arquivos com extensão ".csv"
        return file.isDirectory() || file.getAbsolutePath().endsWith(".csv");
    }
    
    @Override
    public String getDescription() {
        //Esta descrição será exibida na caixa de diálogo
        return "Tabelas CSV (*.csv)";
    }
}
