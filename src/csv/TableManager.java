/**
*** @author chrisGrando
*** Classe destinada ao controle geral dos arquivos e tabelas CSV.
**/
package csv;

import app.Globals;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.List;

public class TableManager {
    private List<String[]> inputTable = null;
    
    //Lê e carrega arquivo de entrada
    public void readInputFile(String path) {
        try {
            TableReader tableReader = new TableReader();
            tableReader.read(path);
            this.inputTable = tableReader.getFullTable();
            
            //Flag de erro
            Globals.ERROR = false;
        }
        catch (IOException | CsvValidationException error) {
            //Mensagem de erro
            String msg = "[ERRO] ";
            msg += "Arquivo de entrada não pode ser lido.\n";
            msg += "Cheque o arquivo \"error.log\" para mais detalhes...";
            System.out.println(msg);
            
            //Log de erro
            System.err.println("### Unable to read input file ###");
            error.printStackTrace();
            
            //Flag de erro
            Globals.ERROR = true;
        }
    }
    
    //Cria e grava o arquivo de saída
    public void createOutputFile(String path, List<String[]> newTable) {
        try {
            TableWriter tableWriter = new TableWriter();
            tableWriter.write(path, newTable);
            
            //Flag de erro
            Globals.ERROR = false;
        }
        catch (IOException error) {
            //Mensagem de erro
            String msg = "[ERRO] ";
            msg += "Arquivo de saída não pode ser gravado.\n";
            msg += "Cheque o arquivo \"error.log\" para mais detalhes...";
            System.out.println(msg);
            
            //Log de erro
            System.err.println("### Unable to write output file ###");
            error.printStackTrace();
            
            //Flag de erro
            Globals.ERROR = true;
        }
    }
    
    //Retorna a tabela lida
    public List<String[]> getReadedTable() {
        return this.inputTable;
    }
}
