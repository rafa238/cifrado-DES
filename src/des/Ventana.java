package des;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import sun.misc.BASE64Encoder;

public class Ventana extends javax.swing.JFrame {

    public Ventana() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cifrado de ficheros con DES");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setText("Cifrado de Ficheros con algoritmo DES");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 13)); // NOI18N
        jLabel6.setText("Clave de 8 caracteres:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 51, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cifrar DES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setBackground(new java.awt.Color(204, 51, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Descifrar DES");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String clave2=jTextField1.getText();
        if(clave2.length()!=8){
            JOptionPane.showMessageDialog(rootPane, "Serciorate de poner 8");
        }else{
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File archivo=fc.getSelectedFile();
        
            String textoT="";
            try{
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
                DESKeySpec kspec = new DESKeySpec(clave2.getBytes());
                SecretKey clave= skf.generateSecret(kspec);
                textoT=textoT+" La codificacion es: "+clave.getEncoded()+"\n";
                Cipher cifrado=Cipher.getInstance("DES/ECB/PKCS5Padding");
                String nuearchivo="";
                for (int i = 0; i < archivo.getAbsolutePath().length()-4; i++) {
                    nuearchivo=nuearchivo+archivo.getAbsolutePath().charAt(i);
                }
                textoT=textoT+"Puedes encontrar el archivo en: " + nuearchivo + "_cifrado.txt"+"\n";
                cifrado.init(Cipher.ENCRYPT_MODE,clave);

                byte[] buffer = new byte[1000];
                byte[] buffercifrado;
                FileInputStream in= new FileInputStream(archivo);
                FileOutputStream out = new FileOutputStream(nuearchivo+"_cifrado.txt");
                int bytesleidos = in.read(buffer, 0, 1000);
                while(bytesleidos !=-1){
                    buffercifrado = cifrado.update(buffer,0,bytesleidos);
                    out.write(buffercifrado);
                    bytesleidos = in.read(buffer,0,1000);
                }
                buffercifrado=cifrado.doFinal();
                out.write(buffercifrado);

                in.close();
                out.close();

                jTextArea1.setText(textoT);
            }catch(Exception e){
                System.out.println("Error al cifrar "+e);
                JOptionPane.showMessageDialog(rootPane, "Error");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String clave2=jTextField1.getText();
        String textoT="";
        if(clave2.length()!=8){
            JOptionPane.showMessageDialog(rootPane, "Deben de ser de 8 caracteres >:c");
        }else{
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File archivo=fc.getSelectedFile();
        
            try{
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
                DESKeySpec kspec = new DESKeySpec(clave2.getBytes());
                SecretKey clave= skf.generateSecret(kspec);
                String nuearchivo="";
                for (int i = 0; i < archivo.getAbsolutePath().length()-4; i++) {
                    nuearchivo=nuearchivo+archivo.getAbsolutePath().charAt(i);
                }
                textoT=textoT+"La clave con la codificacion : "+clave.getEncoded()+"\n";
                Cipher cifrado=Cipher.getInstance("DES/ECB/PKCS5Padding");
                textoT=textoT+"El archivo descifrado esta en: "+nuearchivo+"descifrado.txt"+"\n";

                cifrado.init(Cipher.DECRYPT_MODE,clave);

                byte[] buffer = new byte[1000];
                byte[] buffercifrado;

                byte[] bufferplano;

                FileInputStream in= new FileInputStream(archivo);
                FileOutputStream out = new FileOutputStream(nuearchivo+"descifrado.txt");

                int bytesleidos = in.read(buffer, 0, 1000);
                while(bytesleidos !=-1){
                    bufferplano = cifrado.update(buffer, 0, bytesleidos);
                    out.write(bufferplano);
                    bytesleidos = in.read(buffer,0,1000);
                }
                bufferplano=cifrado.doFinal();
                out.write(bufferplano);
                in.close();
                out.close();
                jTextArea1.setText(textoT);
            }catch(Exception e){
                System.out.println("Error al descifrar " + e);
                JOptionPane.showMessageDialog(rootPane, "Error");
            }
        }
    
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        
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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
