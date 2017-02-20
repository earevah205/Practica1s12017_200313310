/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import com.thoughtworks.xstream.XStream;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import scrabble.models.Casilla;
import scrabble.models.Scrabble;

/**
 *
 * @author estuardoarevalo
 */
public class frmStartup extends javax.swing.JFrame {

    /**
     * Creates new form frmStartup
     */
    public frmStartup() {
        initComponents();
        
        //----------------------------------------------------------------
        // Inicializando el JFrame
        //color de fondo
        this.getContentPane().setBackground(new Color(191, 30, 45));
        //colocar en el centro de la pantalla
        setLocationRelativeTo(null);
        //----------------------------------------------------------------
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblMainTitle = new javax.swing.JLabel();
        btnOpenFile = new javax.swing.JButton();
        txtFilePath = new javax.swing.JTextField();
        lblFilePath = new javax.swing.JLabel();
        txtContinue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scrabble - 200313310 - EDD - USAC");
        setBackground(new java.awt.Color(191, 30, 45));
        setForeground(new java.awt.Color(191, 30, 45));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scrabble/images/scrabble-logo.png"))); // NOI18N

        lblMainTitle.setText("Seleccione una Archivo de Configuración");

        btnOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scrabble/images/files-folder.png"))); // NOI18N
        btnOpenFile.setBorderPainted(false);
        btnOpenFile.setContentAreaFilled(false);
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });

        lblFilePath.setText("Archivo:");

        txtContinue.setText("CONTINUAR");
        txtContinue.setBorderPainted(false);
        txtContinue.setContentAreaFilled(false);
        txtContinue.setOpaque(true);
        txtContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContinueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(lblLogo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(lblMainTitle))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(lblFilePath)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 67, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtContinue)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo)
                .addGap(18, 18, 18)
                .addComponent(lblMainTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOpenFile)
                    .addComponent(txtFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFilePath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(txtContinue))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos XML", "xml", "xml"));
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            txtFilePath.setText(selectedFile.getAbsolutePath());
            
        }


        
    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void txtContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContinueActionPerformed
        
        String filePath = txtFilePath.getText();
        
        // revisamos que haya seleccionar un path de archivo
        if (filePath.compareTo("")==0){
            //No existe un path
            JOptionPane.showMessageDialog(this, "Debes seleccionar un archivo para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //verificamos que el archivo exista 
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) { 
            
            String ext1 = FilenameUtils.getExtension(filePath); 
            if (ext1.compareToIgnoreCase("xml")==0){
                
                //todo read xml file
                XStream xstream = new XStream();

                try {
                    //converting xml to object
                    xstream.alias("scrabble", Scrabble.class);
                    Scrabble scrabble = (Scrabble)xstream.fromXML(FileUtils.readFileToString(f, Charset.defaultCharset()));
                    Casilla c = new Casilla();
                    c.setX(10);
                    c.setY(29);
                    String s = xstream.toXML(c);
                    
                    System.out.println("dimension scrabble = " + s+"00"+scrabble.getDimension());
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(frmStartup.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "Extensión del archivo es incorreta.  Por favor selecciona un archivo XML", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }else{
            //No existe un path
            JOptionPane.showMessageDialog(this, "El Archivo seleccionado es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
    }//GEN-LAST:event_txtContinueActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmStartup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmStartup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmStartup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmStartup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmStartup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JLabel lblFilePath;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMainTitle;
    private javax.swing.JButton txtContinue;
    private javax.swing.JTextField txtFilePath;
    // End of variables declaration//GEN-END:variables
}
