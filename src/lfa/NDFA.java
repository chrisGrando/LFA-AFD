/** 
*** @author chrisGrando
*** Classe destinada para operações com Autômatos Finitos Não Determinísticos
*** (AFND), ou em inglês, Non-deterministic Finite Automaton (NDFA).
**/
package lfa;

import java.util.ArrayList;
import java.util.List;

public class NDFA {
    //Listas
    private List<String[]> myListNDFA = new ArrayList<>(); //Tabela Completa
    private List<String> labels = new ArrayList<>();       //Lista de rótulos
    //Alfabetos
    private final char[] lowerAlphabet;
    private final char[] upperAlphabet;
    private long numericAlphabet = 0;
    
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
    public void create(List<String[]> srcTable) {
        /*
        RÓTULOS
        =================================================================
        Monta a primeira linha da tabela, composta por letras minúsculas
        e números.
        */
        this.labels = new ArrayList<>();
        this.labels.add("δ"); //Primeira cédula da tabela
        
        //Percorre cada linha da tabela de entrada
        for(String[] line : srcTable) {
            String cell;
            
            //Tokens
            if(line[0].equals("#") && line.length > 1) {
                cell = line[1];
                
                //Percorre e registra cada letra do token como rótulo
                for(char c : cell.toCharArray()) {
                    //Checa se é número ou letra minúscula
                    if(this.isValidLabel(c)) {
                        String l = Character.toString(c);
                        //Adiciona rótulo (sem repetir)
                        if(!this.labels.contains(l))
                            this.labels.add(l);
                    }
                }
            }
            
            //Gramáticas
            else if(this.isGrammarRuleCorrect(line[0]) && line.length > 1) {
                //Registra o primeiro símbolo de cada gramática como rótulo
                for(int i = 1; i < line.length; i++) {
                    //Pula a cédula se for um símbolo terminal
                    if(line[i].contains("ε"))
                        continue;
                    
                    //Checa se a sintaxe da gramática está correta
                    if(this.isGrammarSyntaxCorrect(line[i]))
                        cell = line[i];
                    //Pula a cédula caso incorreta
                    else
                        continue;
                    
                    //Adiciona rótulo (sem repetir)
                    char c = cell.charAt(0);
                    String l = Character.toString(c);
                    if(!this.labels.contains(l))
                        this.labels.add(l);
                }
            }
        }
        
        //Salva rótulos na tabela
        this.myListNDFA.add(this.arrayListToVector(this.labels));
        
        /*
        TOKENS
        =================================================================
        Monta as próximas linhas da tabela, criando gramáticas a partir
        de tokens.
        */
    }
    
    //Checa se é número ou letra minúscula válida como rótulo
    private boolean isValidLabel(char c) {
        //É número?
        if(Character.isDigit(c))
            return true;
        
        //É letra minúscula?
        else if(this.findAlphabetLetter(c, this.lowerAlphabet) != null)
            return true;
        
        //Charactere inválido
        return false;
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
    
    //Checa se a sintaxe da gramática está correta (ex.: x<S>, 1<A>, 0<10>, ε)
    private boolean isGrammarSyntaxCorrect(String txt) {
        int check = 0;
        boolean result = false;
        
        for(char c : txt.toCharArray()) {
            /*
            Condições para pular o loop atual:
            1) Espaço em branco.
            2) Número com mais de um dígito (ex.: 101).
            3) Símbolo terminal (ε).
            */
            
            //Espaço em branco
            if(Character.isSpaceChar(c))
                continue;
            
            //Número com mais de um dígito
            if(check == 3 && Character.isDigit(c)) {
                continue;
            }
            
            //Símbolo terminal
            if(check == 0 && Character.compare(c, 'ε') == 0) {
                check = 4;
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
                //Sintaxe incorreta OU qualquer coisa além do ">" ou do "ε"
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
        
        /*
        Caso nenhuma letra disponível seja encontrada, e não tenha sido pedido
        um alfabeto específico, será usado um número no lugar.
        */
        if(grammar == null && custom == null) {
            //Executa até encontrar um número disponível
            while(grammar == null) {
                //Converte número longo em String
                String long_na = Long.toString(this.numericAlphabet);
                
                //Número já foi usado
                if(this.isAlreadyIncluded(long_na, list)) {
                   this.numericAlphabet++;
                   continue;
                }
                
                //Número disponível
                grammar = new String[size];
                grammar[0] = long_na;
                this.numericAlphabet++;
            }
        }
        
        /*
        Caso uma gramática tenha sido criada (valor não-nulo),
        preenche todas as outras cédulas com "–".
        */
        if(grammar != null) {
            for(int i = 1; i < size; i++) 
                grammar[i] = "–";
        }
        
        //Retorna nova gramática
        return grammar;
    }
    
    //Checa se uma gramática já foi inclusa (versão char)
    private boolean isAlreadyIncluded(char c, List<String[]> list) {
        String grammar = Character.toString(c);
        return this.isAlreadyIncluded(grammar, list);
    }
    
    //Checa se uma gramática já foi inclusa (versão string)
    private boolean isAlreadyIncluded(String grammarName, List<String[]> list) {
        String tmp_grammar;
        
        for(String[] l: list) {
            //Estado final (*)
            if(l[0].contains("*"))
                tmp_grammar = l[0].substring(1); //Remove o asterísco
            //Estado normal
            else
                tmp_grammar = l[0];
            
            //Checa se já existe
            if(grammarName.equals(tmp_grammar))
                //Já foi inclusa
                return true;
        }
        
        //Não foi inclusa
        return false;
    }
    
    //Procura por letra do alfabeto especificada (versão char)
    private String findAlphabetLetter(char c, char[] alphabet) {
        String str_char = Character.toString(c);
        return this.findAlphabetLetter(str_char, alphabet);
    }
    
    //Procura por letra do alfabeto especificada (versão string)
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
    
    //Converte ArrayList de Strings para vetor de Strings
    private String[] arrayListToVector(List<String> list) {
        final int size = list.size();
        String[] vector = new String[size];
        
        for(int i = 0; i < size; i++) {
            vector[i] = list.get(i);
        }
        
        return vector;
    }
    
    //Retorna AFND criado
    public List<String[]> getNDFA() {
        return this.myListNDFA;
    }
}
