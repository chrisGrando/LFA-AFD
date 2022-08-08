/**
*** @author chrisGrando
*** Classe destinada à leitura do arquivo CSV.
**/
package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TableReader {
    private String tableString = "";
    private CSVReader csvTable = null;
    
    //Abre o arquivo
    public void read(String path) throws IOException, CsvValidationException {
        try {
            //Classes, objetos e variáveis necessárias para a leitura do arquivo
            FileReader filereader = new FileReader(path);
            this.csvTable = new CSVReader(filereader);
            String[] nextRecord;
            
            //Lê linha por linha o arquivo e converte para String
            while ((nextRecord = this.csvTable.readNext()) != null) {
                for (String cell : nextRecord) {
                    this.tableString = this.tableString + cell + "\t";
                }
                this.tableString = this.tableString + "\n";
            }
        }
        catch (FileNotFoundException error) {
            System.out.println(error);
        }
    }
    
    //String com o conteúdo do arquivo
    public String getTableString(){
        return this.tableString;
    }
    
    //Arquivo CSV carregado
    public CSVReader getCSV(){
        return this.csvTable;
    }
}
