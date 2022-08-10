/**
*** @author chrisGrando
*** Classe destinada ao controle geral dos arquivos e tabelas CSV.
**/
package csv;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TableManager {
    private String[][] inputTable;
    
    //Lê e carrega arquivo de entrada
    public void readInputFile(String path)
      throws IOException, CsvValidationException, FileNotFoundException {
        TableReader tableReader = new TableReader();
        tableReader.read(path);
        this.inputTable = tableReader.getFullTable();
    }
    
    //Cria e grava o arquivo de saída
    public void createOutputFile(String path, String[][] newTable) {
        TableWriter tableWriter = new TableWriter();
        tableWriter.write(path, newTable);
    }
    
    //Exibe toda a tabela de entrada
    public void printTable() {
        System.out.println("\nTabela:");
        for (String[] row : this.inputTable) {
            System.out.print("| ");
            for (String cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.print("\n");
        }
    }
    
    //Retorna a tabela de entrada
    public String[][] getTable() {
        return this.inputTable;
    }
}
