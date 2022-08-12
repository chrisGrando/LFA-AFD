/**
*** @author chrisGrando
*** Classe destinada ao UI do aplicativo.
*** (Contém código gerado automaticamente pela IDE. Recomenda-se não editar
*** esses segmentos com avisos/comentários em inglês)
**/
package app.gui;

import app.Globals;
import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
import javax.swing.JFileChooser;
import java.awt.Toolkit;

public class SimpleGUI extends javax.swing.JFrame {
    private String currentLog = "";

    /*
    Creates new form SimpleGUI
    */
    public SimpleGUI() {
        initComponents();
        setCenter();
        setIcon();
    }

    /*
    ** This method is called from within the constructor to initialize the form.
    ** WARNING: Do NOT modify this code. The content of this method is always
    ** regenerated by the Form Editor.
    */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser_Open = new javax.swing.JFileChooser();
        FileChooser_Save = new javax.swing.JFileChooser();
        jPanel = new javax.swing.JPanel();
        Label_Input = new javax.swing.JLabel();
        Label_Output = new javax.swing.JLabel();
        Label_Log = new javax.swing.JLabel();
        Field_Input = new javax.swing.JTextField();
        Field_Output = new javax.swing.JTextField();
        Button_OpenFile = new javax.swing.JButton();
        Button_SaveFile = new javax.swing.JButton();
        Button_Start = new javax.swing.JButton();
        ScrollPane_Log = new javax.swing.JScrollPane();
        TextPane_Log = new javax.swing.JTextPane();

        FileChooser_Open.setApproveButtonText("Ok");
        FileChooser_Open.setCurrentDirectory(null);
        FileChooser_Open.setDialogTitle("Abrir arquivo CSV");
        FileChooser_Open.setMinimumSize(new java.awt.Dimension(600, 400));
        FileChooser_Open.setPreferredSize(new java.awt.Dimension(600, 400));

        FileChooser_Save.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        FileChooser_Save.setApproveButtonText("Ok");
        FileChooser_Save.setCurrentDirectory(null);
        FileChooser_Save.setDialogTitle("Salvar arquivo CSV");
        FileChooser_Save.setMinimumSize(new java.awt.Dimension(600, 400));
        FileChooser_Save.setPreferredSize(new java.awt.Dimension(600, 400));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[LFA] Autômato Finito Determinístico");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 640, 480));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setName("frameMainWindow"); // NOI18N
        setPreferredSize(new java.awt.Dimension(640, 480));
        setResizable(false);
        setSize(new java.awt.Dimension(640, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel.setMaximumSize(new java.awt.Dimension(640, 480));
        jPanel.setMinimumSize(new java.awt.Dimension(640, 480));
        jPanel.setPreferredSize(new java.awt.Dimension(640, 480));

        Label_Input.setBackground(new java.awt.Color(255, 255, 255));
        Label_Input.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Label_Input.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Input.setText("Input File");

        Label_Output.setBackground(new java.awt.Color(255, 255, 255));
        Label_Output.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Label_Output.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Output.setText("Output File");

        Label_Log.setBackground(new java.awt.Color(255, 255, 255));
        Label_Log.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Label_Log.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Log.setText("Log");

        Field_Input.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Field_Input.setToolTipText("");
        Field_Input.setMargin(new java.awt.Insets(3, 6, -1, 6));
        Field_Input.setMaximumSize(new java.awt.Dimension(15, 19));

        Field_Output.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Field_Output.setToolTipText("");
        Field_Output.setMargin(new java.awt.Insets(3, 6, -1, 6));
        Field_Output.setMaximumSize(new java.awt.Dimension(15, 19));

        Button_OpenFile.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Button_OpenFile.setText("...");
        Button_OpenFile.setMargin(new java.awt.Insets(0, 14, 0, 14));
        Button_OpenFile.setMaximumSize(new java.awt.Dimension(51, 17));
        Button_OpenFile.setMinimumSize(new java.awt.Dimension(51, 17));
        Button_OpenFile.setPreferredSize(new java.awt.Dimension(51, 17));
        Button_OpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_OpenFileActionPerformed(evt);
            }
        });

        Button_SaveFile.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Button_SaveFile.setText("...");
        Button_SaveFile.setMargin(new java.awt.Insets(0, 14, 0, 14));
        Button_SaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_SaveFileActionPerformed(evt);
            }
        });

        Button_Start.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Button_Start.setText("Iniciar");
        Button_Start.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Button_Start.setMargin(new java.awt.Insets(5, 0, 0, 0));

        ScrollPane_Log.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ScrollPane_Log.setAlignmentX(0.0F);
        ScrollPane_Log.setAlignmentY(0.0F);
        ScrollPane_Log.setAutoscrolls(true);

        TextPane_Log.setEditable(false);
        TextPane_Log.setBorder(null);
        TextPane_Log.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        ScrollPane_Log.setViewportView(TextPane_Log);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Button_Start, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(Label_Log, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Output, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Field_Output, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                            .addComponent(Field_Input, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Button_OpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Button_SaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ScrollPane_Log, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Field_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Input)))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(Button_OpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Field_Output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Label_Output))
                    .addComponent(Button_SaveFile, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Button_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Label_Log)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollPane_Log, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Field_Output.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 622, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Botão para abrir arquivo
    private void Button_OpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_OpenFileActionPerformed
        int returnVal = FileChooser_Open.showOpenDialog(this);
        System.out.println(evt.toString());
        
        //Mensagem na área de log
        this.printLog("Abrindo arquivo...");
        
        //Obtém o caminho de diretório completo do arquivo
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = FileChooser_Open.getSelectedFile();
            Field_Input.setText(file.getAbsolutePath());
            Globals.INPUT = Field_Input.getText();
        }
        else {
            //Mensagem na área de log
            this.printLog("Operação cancelada...");
        }
    }//GEN-LAST:event_Button_OpenFileActionPerformed
    
    //Botão para salvar arquivo
    private void Button_SaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_SaveFileActionPerformed
        int returnVal = FileChooser_Save.showOpenDialog(this);
        System.out.println(evt.toString());
        
        //Mensagem na área de log
        this.printLog("Salvando arquivo...");
        
        //Obtém o caminho de diretório completo do arquivo
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = FileChooser_Save.getSelectedFile();
            Field_Output.setText(file.getAbsolutePath());
            Globals.OUTPUT = Field_Output.getText();
        }
        else {
            //Mensagem na área de log
            this.printLog("Operação cancelada...");
        }
    }//GEN-LAST:event_Button_SaveFileActionPerformed

    /*
    Executa automaticamente ao abrir a janela e faz o seguinte:
    [*] Preenche os campos de texto, dos arquivos de entrada
    e saída, com os valores passados por linha de comando (se houverem).
    [*] Configura o diretório atual da aplicação como diretório padrão
    para abrir e salvar arquivos.
    */
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        File myDir = new File(System.getProperty("user.dir"));
        System.out.println(evt.toString());
        
        //Campos de texto
        Field_Input.setText(Globals.INPUT);
        Field_Output.setText(Globals.OUTPUT);
        //Diretório padrão
        FileChooser_Open.setCurrentDirectory(myDir);
        FileChooser_Save.setCurrentDirectory(myDir);
    }//GEN-LAST:event_formWindowActivated

    //Exibe mensagens na área de log
    public void printLog(String log) {
        this.currentLog = TextPane_Log.getText();
        TextPane_Log.setText(currentLog + log + "\n");
    }
    
    //Exibe o ícone do aplicativo
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icon.png")));
    }
    
    //Centraliza a janela
    private void setCenter() {
        setLocationRelativeTo(null);
    }

    //Launch app window
    public void startWindow() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimpleGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SimpleGUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_OpenFile;
    private javax.swing.JButton Button_SaveFile;
    private javax.swing.JButton Button_Start;
    private javax.swing.JTextField Field_Input;
    private javax.swing.JTextField Field_Output;
    private javax.swing.JFileChooser FileChooser_Open;
    private javax.swing.JFileChooser FileChooser_Save;
    private javax.swing.JLabel Label_Input;
    private javax.swing.JLabel Label_Log;
    private javax.swing.JLabel Label_Output;
    private javax.swing.JScrollPane ScrollPane_Log;
    private javax.swing.JTextPane TextPane_Log;
    private javax.swing.JPanel jPanel;
    // End of variables declaration//GEN-END:variables
}
