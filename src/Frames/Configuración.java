
package Frames;

import javax.swing.ImageIcon;

public class Configuración extends javax.swing.JFrame {
    public static String config = "";//variable que guarda el pedido seleccionado para mostrar sus datos 
    
    public Configuración() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        this.setLocationRelativeTo(null);//centra la ventana 
        bPed.hide();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        bResp = new javax.swing.JButton();
        bServ = new javax.swing.JButton();
        bPed = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(695, 674));

        jLabel8.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel8.setText("Configuración");

        jButton3.setText("← Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        bResp.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        bResp.setText("Agregar responsable");
        bResp.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        bResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRespActionPerformed(evt);
            }
        });

        bServ.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        bServ.setText("Agregar servicio");
        bServ.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        bServ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bServActionPerformed(evt);
            }
        });

        bPed.setFont(new java.awt.Font("Agency FB", 0, 24)); // NOI18N
        bPed.setText("Generar Pedido");
        bPed.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        bPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bServ, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bResp, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bPed, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(bResp, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bServ, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bPed, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //botón volver a la pantalla anterior
        Inicio abrir=new Inicio();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRespActionPerformed
        // TODO add your handling code here:
        config = "resp";
        System.out.println("resp");
        Nuevo_Responsable abrir=new Nuevo_Responsable();//para abrir la ventana...
        abrir.setVisible(true);//...cuando apreto "Ingresar"
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_bRespActionPerformed

    private void bServActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bServActionPerformed
        // TODO add your handling code here:
        config = "serv";
        System.out.println("serv");
        Nuevo_Servicio abrir=new Nuevo_Servicio();//para abrir la ventana...
        abrir.setVisible(true);//...cuando apreto "Ingresar"
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_bServActionPerformed

    private void bPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bPedActionPerformed

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
            java.util.logging.Logger.getLogger(Configuración.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuración.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuración.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuración.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuración().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bPed;
    private javax.swing.JButton bResp;
    private javax.swing.JButton bServ;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
