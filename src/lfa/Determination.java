/** 
*** @author chrisGrando
*** Classe destinada para realizar a "determinização" a partir do AFND.
**/
package lfa;

import java.util.ArrayList;
import java.util.List;

public class Determination {
    private List<String[]> dtTable;
    
    //Realiza a determinização
    public void generate(List<String[]> srcTable) {
        //TODO: Implementar o algoritmo da determinização
        this.dtTable = srcTable;
    }
    
    //Retorna a tabela com a determinização
    public List<String[]> getDeterminationTable() {
        return this.dtTable;
    }
    
}
