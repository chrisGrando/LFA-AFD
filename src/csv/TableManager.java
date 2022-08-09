/**
*** @author chrisGrando
*** Classe destinada ao controle geral dos arquivos CSV.
**/
package csv;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class TableManager {
    private TableReader tableReader;
    private TableWriter tableWriter;
    private String[][] table;
    
    //Lê e carrega arquivo de entrada
    public void readInputFile(String path) throws IOException, CsvValidationException, FileNotFoundException {
        this.tableReader = new TableReader();
        this.tableReader.read(path);
        this.table = this.tableReader.getFullTable();
    }
    
    //Cria e grava o arquivo de saída
    public void createOutputFile(String path) {
        this.tableWriter = new TableWriter();
        this.tableWriter.write(path, this.table);
    }
    
    //Exibe toda a tabela
    public void printTable() {
        System.out.println("\nTabela:");
        for (String[] row : this.table) {
            System.out.print("| ");
            for (String cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.print("\n");
        }
    }
}
