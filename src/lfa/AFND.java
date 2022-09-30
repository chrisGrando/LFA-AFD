/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Não Determinísticos.
**/
package lfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AFND {
    //Listas
    private List<String[]> myListAFND = new ArrayList<>(); //Tabela Completa
    private List<String> labels = new ArrayList<>();       //Lista de rótulos
    private List<String> tokenList = new ArrayList<>();    //Lista de tokens
    private List<String> grammarList = new ArrayList<>();  //Lista de gramáticas
    //Alfabetos
    private final char[] lowerAlphabet;
    private final char[] upperAlphabet;
    private long auxLabel = 1;
    
    //Construtor
    public AFND() {
        lowerAlphabet = new char[] {
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
          'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
        upperAlphabet = new char[] {
          'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
          'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
    }
    
    //Cria tabela de AFND
    public void create(String[][] srcTable) {
        //Lista de Linhas
        List<String> currentLine = new ArrayList<>();        
        
        /*
        RÓTULOS
        =================================================================
        Monta a primeira linha da tabela, composta por letras minúsculas.
        */
        currentLine.add("δ"); //Primeira cédula da tabela
        
        /*
        FIM
        ==================================================================
        Adiciona todas as linhas na tabela.
        */
        /*this.myAFND = new String[rowList.size()][];
        for(int i = 0; i < rowList.size(); i++) {
            this.myAFND[i] = rowList.get(i);
        }*/
    }
    
    //Checa se a sintaxe da gramática está correta
    private boolean isGrammarCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            
            switch (check) {
                //Começa com letra minúscula?
                case 0 -> {
                    String l = this.findAlphabetLetter(Character.toString(c), this.lowerAlphabet);
                    if(l != null)
                        check++;
                }
                //Segue com "<"?
                case 1 -> {
                    if(Character.compare(c, '<') == 0)
                        check++;
                    else
                        check = 0;
                }
                //Possui letra maiúscula?
                case 2 -> {
                    String l = this.findAlphabetLetter(Character.toString(c), this.upperAlphabet);
                    if(l != null)
                        check++;
                    else
                        check = 0;
                }
                //Termina com ">"?
                case 3 -> {
                    if(Character.compare(c, '>') == 0)
                        check++;
                    else
                        check = 0;
                }
            }
        }
        
        //Gramática está correta
        if(check == 4) {
            result = true;
        }
        
        return result;
    }
    //Adiciona nova gramática (SEM letra(s) específica(s))
    private String[] addNewGrammar(List<String[]> list) {
        return this.addNewGrammar(list, null);
    }
    
    //Adiciona nova gramática (COM letra(s) específica(s))
    private String[] addNewGrammar(List<String[]> list, String custom) {
        char[] auxAlphabet;
        String[] grammar = null;
        int size = list.get(0).length;
        
        //Se for pedido uma ou várias letras específicas
        if(custom != null) {
            auxAlphabet = custom.toCharArray();
        }
        //Usar alfabeto padrão se não houverem especificações
        else {
            auxAlphabet = this.upperAlphabet;
        }
        
        //Procura por uma letra não usada para adicionar à lista
        for(char c : auxAlphabet) {
            if(!this.isAlreadyIncluded(c, list)) {
                grammar = new String[size];
                //Letra da nova gramática
                grammar[0] = Character.toString(c);
                //Interrompe loop
                break;
            }
        }
        
        //Caso nenhuma letra disponível seja encontrada...
        if(grammar == null) {
            grammar = new String[size];
            //...Será usado um número no lugar
            grammar[0] = Long.toString(this.auxLabel);
            this.auxLabel++;
        }
        
        //Preenche todas as outras cédulas com "–"
        for(int i = 1; i < size; i++) 
            grammar[i] = "–";
        
        //Retorna nova gramática
        return grammar;
    }
    
    //Checa se uma gramática já foi inclusa
    private boolean isAlreadyIncluded(char c, List<String[]> list) {
        
        for(String[] l: list) {
            //Estado normal
            if(l[0].length() == 1) {
                if(Character.compare(c, l[0].charAt(0)) == 0)
                    //Já foi inclusa
                    return true;
            }
            //Estado final
            if(l[0].length() > 1) {
                if(Character.compare(c, l[0].charAt(1)) == 0)
                    //Já foi inclusa
                    return true;
            }
        }
        
        //Não foi inclusa
        return false;
    }
    
    //Procura por letra do alfabeto
    private String findAlphabetLetter(String txt, char[] alphabet) {
        String result = "";
        
        for(char t : txt.toCharArray()) {
            for(char a : alphabet) {
                if(Character.compare(t, a) == 0)
                    result += Character.toString(t);
            }
        }
        
        //Caso seja encontrada, no mínimo, 1 letra
        if(!result.isBlank())
            return result;
        
        //Caso nenhuma letra seja encontrada
        return null;
    }
    
    //Retorna AFND criado
    public List<String[]> getAFND() {
        return this.myListAFND;
    }
}
