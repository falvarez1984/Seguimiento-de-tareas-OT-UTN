/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.pedido_gral.dato_pedido;
import java.awt.Color;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelos.Conexion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

public class Gráficas extends javax.swing.JFrame {
    
    
    
    //MÉTODO PARA PREGUNTAR ANTES DE CERRAR LA VENTANA MEDIANTE EL BOTÓN X    
    public void cerrar(){
         Object [] opciones ={"Aceptar","Cancelar"};
         int eleccion = JOptionPane.showOptionDialog(rootPane,"Desea cerrar la aplicacion?","Mensaje de Confirmacion",
         JOptionPane.YES_NO_OPTION,
         JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
         if (eleccion == JOptionPane.YES_OPTION)
         {
         System.exit(0);
         }else{
         }
    }
    
    public Gráficas() {
        initComponents();
        this.setTitle("Sistema de seguimiento de tareas OT-UTN");//título de ventana
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        this.setExtendedState(MAXIMIZED_BOTH);
        Integer cierreTotal=0;
        
        //rescato el listado de institutos para graficar todos
        ArrayList insts = new ArrayList();
        try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "select * from aa1instituto";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    insts.add(rs.getString("n_inst"));
                }
            }
        catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        
        //MUESTRO LOS INSTITUTOS DE LA LISTA
        for(int i = 0;i<insts.size();i++){
            System.out.println(insts.get(i));
        }
        
        //rescato el total de pedidos de los institutos rescatados en la anterior lista
        for(int i = 0;i<insts.size();i++){
            try{
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    Conexion conn = new Conexion();
                    Connection con = conn.getConexion();
                    String sql="";
                    sql = "select count (cierre) from datos_iniciales where instituto='"+ insts.get(i)+"'";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    if(rs.next()){
                        cierreTotal=(rs.getInt("count"));
                    }
                    System.out.println(cierreTotal);
                }
            catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }

            //rescato el total de pedidos cerrados de los institutos
            Integer cierreSI=0;
            try{
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    Conexion conn = new Conexion();
                    Connection con = conn.getConexion();
                    String sql="";
                    sql = "select count (cierre) from datos_iniciales where cierre='SI' and instituto='"+ insts.get(i)+"'";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    if(rs.next()){
                        cierreSI=(rs.getInt("count"));
                    }
                    System.out.println(cierreSI);
                }
            catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }

            //GRAFICO EN BASE A LOS RESCATADO
            float cierreNO = cierreTotal-cierreSI;
            if(cierreTotal==0){
                cierreTotal++;
            }
            String porcentajeSI = Float.toString(Math.round(cierreSI*100/cierreTotal));
            String porcentajeNO = Float.toString(Math.round(cierreNO*100/cierreTotal));
            String si="SI: "+cierreSI+" ("+porcentajeSI+"%)" ;
            String no="NO: "+cierreNO+" ("+porcentajeNO+"%)" ;
            ////////////// GRAFICA DE TORTA
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue(si, cierreSI);
            dataset.setValue(no, cierreNO);
            //JFreeChart chart = ChartFactory.createRingChart("Cerrado", dataset, rootPaneCheckingEnabled, rootPaneCheckingEnabled, Locale.ITALY);
            JFreeChart chart = ChartFactory.createPieChart3D("Cerrados "+insts.get(i), dataset, true, true, false);

            ///////////// EFECTOS (solo para piechart)
            PiePlot3D plot = (PiePlot3D) chart.getPlot();//obtengo la forma del grafico anterior para darle efecto...
            plot.setStartAngle(0);//ángulo de rotación
            plot.setForegroundAlpha(.60f);//fondo semitransparente
            plot.setInteriorGap(0.02);//separación entre porciones
            plot.setSectionPaint(si, new Color(0, 0, 255));
            plot.setSectionPaint(no, new Color(255, 0, 0));
            plot.setExplodePercent(no, 0.20);
            //plot.setSimpleLabels(true);
            //plot.setCircular(false);
            chart.setBackgroundPaint(Color.white);
            chart.removeLegend();
            ChartPanel oPanel = new ChartPanel(chart);
            switch (i){
                case 0: 
                {
                    Panel1.setLayout(new java.awt.BorderLayout());
                    Panel1.add(oPanel);
                    Panel1.validate();
                    break;
                }
                case 1: 
                {
                    Panel2.setLayout(new java.awt.BorderLayout());
                    Panel2.add(oPanel);
                    Panel2.validate();
                    break;
                }
                case 2: 
                {
                    Panel3.setLayout(new java.awt.BorderLayout());
                    Panel3.add(oPanel);
                    Panel3.validate();
                    break;
                }
                case 3: 
                {
                    Panel4.setLayout(new java.awt.BorderLayout());
                    Panel4.add(oPanel);
                    Panel4.validate();
                    break;
                }
                case 4: 
                {
                    Panel5.setLayout(new java.awt.BorderLayout());
                    Panel5.add(oPanel);
                    Panel5.validate();
                    break;
                }
                case 5: 
                {
                    Panel6.setLayout(new java.awt.BorderLayout());
                    Panel6.add(oPanel);
                    Panel6.validate();
                    break;
                }
                case 6: 
                {
                    Panel7.setLayout(new java.awt.BorderLayout());
                    Panel7.add(oPanel);
                    Panel7.validate();
                    break;
                }
                case 7: 
                {
                    Panel8.setLayout(new java.awt.BorderLayout());
                    Panel8.add(oPanel);
                    Panel8.validate();
                    break;
                }
                case 8: 
                {
                    Panel9.setLayout(new java.awt.BorderLayout());
                    Panel9.add(oPanel);
                    Panel9.validate();
                    break;
                }
                case 9: 
                {
                    Panel10.setLayout(new java.awt.BorderLayout());
                    Panel10.add(oPanel);
                    Panel10.validate();
                    break;
                }
                case 10: 
                {
                    Panel11.setLayout(new java.awt.BorderLayout());
                    Panel11.add(oPanel);
                    Panel11.validate();
                    break;
                }
                case 11: 
                {
                    Panel12.setLayout(new java.awt.BorderLayout());
                    Panel12.add(oPanel);
                    Panel12.validate();
                    break;
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Panel1 = new javax.swing.JPanel();
        Panel4 = new javax.swing.JPanel();
        Panel7 = new javax.swing.JPanel();
        Panel2 = new javax.swing.JPanel();
        Panel5 = new javax.swing.JPanel();
        Panel8 = new javax.swing.JPanel();
        Panel3 = new javax.swing.JPanel();
        Panel6 = new javax.swing.JPanel();
        Panel9 = new javax.swing.JPanel();
        Panel11 = new javax.swing.JPanel();
        Panel10 = new javax.swing.JPanel();
        Panel12 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1370, 768));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jButton1.setText("← Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(614, 667, 116, 30);

        Panel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel1.setPreferredSize(new java.awt.Dimension(294, 203));

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel1);
        Panel1.setBounds(10, 10, 300, 200);

        Panel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel4.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel4Layout = new javax.swing.GroupLayout(Panel4);
        Panel4.setLayout(Panel4Layout);
        Panel4Layout.setHorizontalGroup(
            Panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel4Layout.setVerticalGroup(
            Panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel4);
        Panel4.setBounds(10, 220, 300, 200);

        Panel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel7.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel7Layout = new javax.swing.GroupLayout(Panel7);
        Panel7.setLayout(Panel7Layout);
        Panel7Layout.setHorizontalGroup(
            Panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel7Layout.setVerticalGroup(
            Panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel7);
        Panel7.setBounds(10, 430, 300, 200);

        Panel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel2.setPreferredSize(new java.awt.Dimension(294, 203));

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel2);
        Panel2.setBounds(330, 10, 300, 200);

        Panel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel5.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel5Layout = new javax.swing.GroupLayout(Panel5);
        Panel5.setLayout(Panel5Layout);
        Panel5Layout.setHorizontalGroup(
            Panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel5Layout.setVerticalGroup(
            Panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel5);
        Panel5.setBounds(330, 220, 300, 200);

        Panel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel8.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel8Layout = new javax.swing.GroupLayout(Panel8);
        Panel8.setLayout(Panel8Layout);
        Panel8Layout.setHorizontalGroup(
            Panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel8Layout.setVerticalGroup(
            Panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel8);
        Panel8.setBounds(330, 430, 300, 200);

        Panel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel3.setPreferredSize(new java.awt.Dimension(294, 203));

        javax.swing.GroupLayout Panel3Layout = new javax.swing.GroupLayout(Panel3);
        Panel3.setLayout(Panel3Layout);
        Panel3Layout.setHorizontalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel3Layout.setVerticalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel3);
        Panel3.setBounds(650, 10, 300, 200);

        Panel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel6.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel6Layout = new javax.swing.GroupLayout(Panel6);
        Panel6.setLayout(Panel6Layout);
        Panel6Layout.setHorizontalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel6Layout.setVerticalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel6);
        Panel6.setBounds(650, 220, 300, 200);

        Panel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel9.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel9Layout = new javax.swing.GroupLayout(Panel9);
        Panel9.setLayout(Panel9Layout);
        Panel9Layout.setHorizontalGroup(
            Panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel9Layout.setVerticalGroup(
            Panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel9);
        Panel9.setBounds(650, 430, 300, 200);

        Panel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel11.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel11Layout = new javax.swing.GroupLayout(Panel11);
        Panel11.setLayout(Panel11Layout);
        Panel11Layout.setHorizontalGroup(
            Panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel11Layout.setVerticalGroup(
            Panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel11);
        Panel11.setBounds(970, 220, 300, 200);

        Panel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel10.setPreferredSize(new java.awt.Dimension(294, 203));

        javax.swing.GroupLayout Panel10Layout = new javax.swing.GroupLayout(Panel10);
        Panel10.setLayout(Panel10Layout);
        Panel10Layout.setHorizontalGroup(
            Panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel10Layout.setVerticalGroup(
            Panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel10);
        Panel10.setBounds(970, 10, 300, 200);

        Panel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Panel12.setPreferredSize(new java.awt.Dimension(322, 203));

        javax.swing.GroupLayout Panel12Layout = new javax.swing.GroupLayout(Panel12);
        Panel12.setLayout(Panel12Layout);
        Panel12Layout.setHorizontalGroup(
            Panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        Panel12Layout.setVerticalGroup(
            Panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        getContentPane().add(Panel12);
        Panel12.setBounds(970, 430, 300, 200);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Inicio abrir=new Inicio();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    
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
            java.util.logging.Logger.getLogger(Gráficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gráficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gráficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gráficas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gráficas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel10;
    private javax.swing.JPanel Panel11;
    private javax.swing.JPanel Panel12;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel3;
    private javax.swing.JPanel Panel4;
    private javax.swing.JPanel Panel5;
    private javax.swing.JPanel Panel6;
    private javax.swing.JPanel Panel7;
    private javax.swing.JPanel Panel8;
    private javax.swing.JPanel Panel9;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
