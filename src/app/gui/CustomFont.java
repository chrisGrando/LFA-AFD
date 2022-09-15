/** 
*** @author chrisGrando
*** Classe destinada para carregar fontes (ttf) personalizadas.
**/
package app.gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class CustomFont {
    public final int REGULAR = 0;
    public final int BOLD = 1;
    public final int ITALIC = 2;
    public final int BOLD_ITALIC = 3;
    
    //Carrega a fonte normal
    public Font load(String name) {
        return this.load(name, this.REGULAR);
    }
    
    //Carrega a fonte com estilo específico
    public Font load(String name, int style) {
        String fullName = name + "-";
        Font myFont = null;
        
        //Seleciona o estilo da fonte
        switch (style) {
            //Normal
            case 0:
                fullName += "Regular.ttf";
                break;
            //Negrito
            case 1:
                fullName += "Bold.ttf";
                break;
            //Itálico
            case 2:
                fullName += "Italic.ttf";
                break;
            //Negrito + Itálico
            case 3:
                fullName += "BoldItalic.ttf";
                break;
            //Inválido
            default:
                System.err.println("Invalid style, using fallback to REGULAR...");
                fullName += "Regular.ttf";
        }
        
        //Carrega o arquivo da fonte
        try {
            myFont = Font.createFont(
                Font.TRUETYPE_FONT,
                this.getClass().getResourceAsStream("resources/" + fullName)
            );
        }
        catch (IOException | FontFormatException error) {
            //Mensagem de erro
            String msg = "[ERRO] ";
            msg += "| Formato incorreto | OU | Fonte personalizada não encontrada |\n";
            msg += "Cheque o arquivo \"error.log\" para mais detalhes...";
            System.out.println(msg);
            
            //Log de erro
            System.err.println("### Unable to load custom font ###");
            error.printStackTrace();
        }
        
        return myFont;
    }
}
