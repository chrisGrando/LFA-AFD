/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Não Determinísticos
*** (AFND), ou em inglês, Non-deterministic Finite Automaton (NDFA).
**/
package lfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NDFA {
    //Listas
    private List<String[]> myListNDFA = new ArrayList<>(); //Tabela Completa
    private List<String> labels = new ArrayList<>();       //Lista de rótulos
    private List<String> tokenList = new ArrayList<>();    //Lista de tokens
    private List<String> grammarList = new ArrayList<>();  //Lista de gramáticas
    //Alfabetos
    private final char[] lowerAlphabet;
    private final char[] upperAlphabet;
    private long numericAlphabet = 1;
    
    //Construtor
    public NDFA() {
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
    
    //Checa se a nomeação da regra de gramática está correta (ex.: <S>::=, <11>::=)
    private boolean isGrammarRuleCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            /*
            Condições para pular o loop atual:
            1) Espaço em branco.
            2) Número com mais de um dígito (ex.: 101).
            */
            
            //Espaço em branco
            if(Character.isSpaceChar(c))
                continue;
            
            //Número com mais de um dígito
            if(check == 2 && Character.isDigit(c))
                continue;
            
            //Checa regra
            switch (check) {
                //Começa com "<"?
                case 0 -> {
                    if(Character.compare(c, '<') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Segue com letra maiúscula ou número?
                case 1 -> {
                    //Letra maiúscula
                    String curChar = this.findAlphabetLetter(
                        Character.toString(c),
                        this.upperAlphabet
                    );
                    if(curChar != null)
                        check++;
                    
                    //Número
                    else if(Character.isDigit(c))
                        check++;
                    
                    //Regra inválida
                    else
                        check = -1;
                }
                //Segue com ">"?
                case 2 -> {
                    if(Character.compare(c, '>') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Segue com ":"?
                case 3 -> {
                    if(Character.compare(c, ':') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Segue com outro ":"?
                case 4 -> {
                    if(Character.compare(c, ':') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Termina com "="?
                case 5 -> {
                    if(Character.compare(c, '=') == 0)
                        check++;
                    else
                        check = -1; //Regra inválida
                }
                //Regra inválida OU qualquer coisa além do "="
                default -> {
                    check = -1;
                    break;
                }
            }
        }
        
        //Gramática está correta
        if(check == 6) {
            result = true;
        }
        
        return result;
    }
    
    //Checa se a sintaxe da gramática está correta (ex.: x<S>, 1<A>, 0<10>)
    private boolean isGrammarSyntaxCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            /*
            Condições para pular o loop atual:
            1) Espaço em branco.
            2) Número com mais de um dígito (ex.: 101).
            */
            
            //Espaço em branco
            if(Character.isSpaceChar(c))
                continue;
            
            //Número com mais de um dígito
            if(check == 1 || check == 3) {
                if(Character.isDigit(c))
                    continue;
            }
            
            //Checa sintaxe
            switch (check) {
                //Começa com letra minúscula ou número?
                case 0 -> {
                    //Letra minúscula
                    String curChar = this.findAlphabetLetter(
                        Character.toString(c),
                        this.lowerAlphabet
                    );
                    if(curChar != null)
                        check++;
                    
                    //Número
                    else if(Character.isDigit(c))
                        check++;
                    
                    //Sintaxe incorreta
                    else
                        check = -1;
                }
                //Segue com "<"?
                case 1 -> {
                    if(Character.compare(c, '<') == 0)
                        check++;
                    else
                        check = -1; //Sintaxe incorreta
                }
                //Segue com letra maiúscula ou número?
                case 2 -> {
                    //Letra maiúscula
                    String curChar = this.findAlphabetLetter(
                        Character.toString(c),
                        this.upperAlphabet
                    );
                    if(curChar != null)
                        check++;
                    
                    //Número
                    else if(Character.isDigit(c))
                        check++;
                    
                    //Sintaxe incorreta
                    else
                        check = -1;
                }
                //Termina com ">"?
                case 3 -> {
                    if(Character.compare(c, '>') == 0)
                        check++;
                    else
                        check = -1; //Sintaxe incorreta
                }
                //Sintaxe incorreta OU qualquer coisa além do ">"
                default -> {
                    check = -1;
                    break;
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
            grammar[0] = Long.toString(this.numericAlphabet);
            this.numericAlphabet++;
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
        return this.myListNDFA;
    }
}
