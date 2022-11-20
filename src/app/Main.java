/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <https://unlicense.org>
 */

/**
*** @author chrisGrando
*** Classe destinada somente à inicialização básica.
*** (Lógica do software fica armazenada em AppLogic)
**/
package app;

import java.io.PrintStream;

public class Main {

    public static void main(String[] args)
      throws Exception {
        //Configura caracteres como "Unicode"
        System.setOut(new PrintStream(System.out, true, "UTF8"));
        System.setErr(new PrintStream(System.err, true, "UTF8"));
        
        //Salva todos os prints e logs de erro em um arquivo de texto
        TextOutput textOutput = new TextOutput();
        textOutput.changeOUT("/log/console.log");
        textOutput.changeERR("/log/error.log");
        
        //Inicializa classe de lógica do aplicativo
        AppLogic app = new AppLogic();
        app.start(args);
        
        //Executa o programa
        app.exec();
    }
}
