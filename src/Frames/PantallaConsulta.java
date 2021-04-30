/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;
//import java.sql.*;//librería sql

import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.DEFAULT_OPTION;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Conexion;
import modelos.Depto;
import modelos.Equipos;
import modelos.Instituto;
import modelos.Servicio;
import modelos.tequipos;


public class PantallaConsulta extends javax.swing.JFrame {

public static String dato_consulta = "";//variable que guarda un valor que determina que botón presiono para luego armar la tabla en CONSULTA    
public static String combo_consulta = "";//variable que guarda el valor del combobox seleccionado para luego armar la tabla en CONSULTA    
    /*
//Datos de la base de datos para establecer conexión
Connection connection;
String url = "jdbc:postgresql://localhost:5432/postgres";
String user = "postgres";
String password = "2010Anlis";

//Método para verificar PRIMERA CONEXIÓN CON BBDD
public Connection dbConnection()
{
    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(PantallaConsulta.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
        Connection cn=DriverManager.getConnection(url, user, password);
        //PreparedStatement pst=cn.prepareStatement("insert into prueba values (?,?)");//instrucciónes a la base de datos. Ingreso los cinco valores de la tabla (Instituto-Departamento-Servicio-Equipo-n° UTN
        JOptionPane.showMessageDialog(null, "BIENVENIDO", "Conexión exitosa a BBDD",DEFAULT_OPTION);


    } catch (SQLException ex) {
        Logger.getLogger(PantallaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error al conectar con BBDD. \nConsulte con el administrador");
    }

    return connection;
}    
*/
//Método para verificar que un JTextField sólo admita letras
public void soloLetras (JTextField a){
    a.addKeyListener(new KeyAdapter(){
        public void keyTyped (KeyEvent e){
            char c=e.getKeyChar();
            if(Character.isDigit(c)){
                getToolkit().beep();
                e.consume();
            }
        }
    });
}

//Método para verificar que un JTextField sólo admita números
public void soloNum (JTextField a){
    a.addKeyListener(new KeyAdapter(){
        public void keyTyped (KeyEvent e){
            char c=e.getKeyChar();
            if(!Character.isDigit(c)){
                getToolkit().beep();
                e.consume();
            }
        }
    });
}
    public PantallaConsulta() {
        initComponents();
        this.setTitle("Sistema de seguimiento de tareas OT-UTN");//título de ventana
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        //Pongo la fecha actual por default en el calendario
        Calendar c2 = new GregorianCalendar();
        fecha.setCalendar(c2);
        
        //desaxctivo todos los botones para que no puedan tocarse mientras no se hayan seleccionado los jcombobox
        
        jButton2.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        
        //impongo que el campo de n° de utn admita sólo números
        soloNum(nUtn); 
            //Lleno combobox "Personal"
    
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
    
            try{
                
                String sql = "select * from aa6personal";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                comboPersonal.addItem("Seleccione responsable...");
                
                while(rs.next())
                {
                    comboPersonal.addItem(rs.getString("n_personal"));
                    
                }
                rs.close();
                
            }catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }
        
        //Lleno combobox "Instituto"
        
        Instituto cc = new Instituto();
        DefaultComboBoxModel modeloInst = new DefaultComboBoxModel(cc.mostrarInstitutos());
        comboInstituto.setModel(modeloInst);
        
        //Lleno combobox "tipo de equipos"
        
        tequipos dd = new tequipos();
        DefaultComboBoxModel modelotequipos = new DefaultComboBoxModel(dd.mostrarTequipos());
        comboTipos.setModel(modelotequipos);
        
        
        this.setLocationRelativeTo(null);//centra la ventana del programa
    }
      
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboInstituto = new javax.swing.JComboBox<>();
        comboDepto = new javax.swing.JComboBox<>();
        comboServicio = new javax.swing.JComboBox<>();
        comboTipos = new javax.swing.JComboBox<>();
        comboEquipos = new javax.swing.JComboBox<>();
        nUtn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboPersonal = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INSTITUTO");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SERVICIO");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DEPARTAMENTO");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("n° UTN");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TIPO DE EQUIPO");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EQUIPO");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comboInstituto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        comboInstituto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboInstitutoItemStateChanged(evt);
            }
        });
        comboInstituto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboInstitutoActionPerformed(evt);
            }
        });

        comboDepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboDepto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDeptoItemStateChanged(evt);
            }
        });
        comboDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDeptoActionPerformed(evt);
            }
        });

        comboServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboServicioItemStateChanged(evt);
            }
        });
        comboServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboServicioActionPerformed(evt);
            }
        });

        comboTipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar..." }));
        comboTipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTiposItemStateChanged(evt);
            }
        });
        comboTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTiposActionPerformed(evt);
            }
        });

        comboEquipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboEquipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEquiposItemStateChanged(evt);
            }
        });
        comboEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEquiposActionPerformed(evt);
            }
        });

        nUtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nUtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel8.setText("Consulta por...");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("RESPONSABLE");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comboPersonal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonalItemStateChanged(evt);
            }
        });
        comboPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPersonalActionPerformed(evt);
            }
        });

        jButton3.setText("← Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Fecha");
        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha.setDateFormatString("dd/MMM/yyyy");

        jButton2.setText("Responsable");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Instituto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Departamento");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Servicio");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Equipo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("n° UTN");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Fecha");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nUtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(comboEquipos, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboTipos, javax.swing.GroupLayout.Alignment.LEADING, 0, 316, Short.MAX_VALUE)
                                    .addComponent(comboServicio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboDepto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboInstituto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboPersonal, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 487, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboInstituto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nUtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nUtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nUtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_nUtnActionPerformed

    private void comboInstitutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboInstitutoActionPerformed
        // TODO add your handling code here:
        
        int seleccion = comboInstituto.getSelectedIndex();
        if(seleccion>0)
        {
            jButton4.setEnabled(true);
        }else
        {
            jButton4.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboInstitutoActionPerformed

    private void comboServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboServicioActionPerformed
        // TODO add your handling code here:
        
        int seleccion = comboServicio.getSelectedIndex();
        if(seleccion>0)
        {
            jButton6.setEnabled(true);
        }else
        {
            jButton6.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboServicioActionPerformed

    private void comboEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEquiposActionPerformed
        // TODO add your handling code here:
        
        int seleccion = comboEquipos.getSelectedIndex();
        if(seleccion>0)
        {
            jButton7.setEnabled(true);
        }else
        {
            jButton7.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboEquiposActionPerformed

    private void comboTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTiposActionPerformed

    private void comboInstitutoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboInstitutoItemStateChanged
        
        //Lleno el combobox Departamento a partir de la seleccion de Instituto con los datos de la BBDD
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            Instituto inst = (Instituto) comboInstituto.getSelectedItem();
            Depto depto = new Depto ();
            DefaultComboBoxModel modelDepto = new DefaultComboBoxModel(depto.mostrarDeptos(inst.getId_inst()));
            comboDepto.setModel(modelDepto);
        }   
    }//GEN-LAST:event_comboInstitutoItemStateChanged

    private void comboServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboServicioItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboServicioItemStateChanged

    private void comboDeptoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDeptoItemStateChanged
        // TODO add your handling code here:
        
        //Lleno el combobox Servicio a partir de la seleccion de Departamento con los datos de la BBDD
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            Depto depto = (Depto) comboDepto.getSelectedItem();
            Servicio serv = new Servicio ();
            DefaultComboBoxModel modelServicio = new DefaultComboBoxModel(serv.mostrarServicios(depto.getId_inst()));
            comboServicio.setModel(modelServicio);
        }
    }//GEN-LAST:event_comboDeptoItemStateChanged

    private void comboDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDeptoActionPerformed
        // TODO add your handling code here:
        
        int seleccion = comboDepto.getSelectedIndex();
        if(seleccion>0)
        {
            jButton5.setEnabled(true);
        }else
        {
            jButton5.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboDeptoActionPerformed

    private void comboEquiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEquiposItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEquiposItemStateChanged

    private void comboTiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTiposItemStateChanged
        // TODO add your handling code here:
        
        //Lleno el combobox Equipos a partir de la seleccion de Tipo de Equipos con los datos de la BBDD
        if (evt.getStateChange() == ItemEvent.SELECTED)
        {
            tequipos tequip = (tequipos) comboTipos.getSelectedItem();
            Equipos equipp = new Equipos ();
            DefaultComboBoxModel modelEquipo = new DefaultComboBoxModel(equipp.mostrarEquipos(tequip.getId_inst()));
            comboEquipos.setModel(modelEquipo);
        }
    }//GEN-LAST:event_comboTiposItemStateChanged

    private void comboPersonalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPersonalItemStateChanged

    private void comboPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPersonalActionPerformed
        // TODO add your handling code here:
        
        int seleccion = comboPersonal.getSelectedIndex();
        if(seleccion>0)
        {
            jButton2.setEnabled(true);
        }else
        {
            jButton2.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboPersonalActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //botón volver a la pantalla anterior
        Inicio abrir=new Inicio();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        combo_consulta=comboPersonal.getSelectedItem().toString();
        dato_consulta="nombre";
        Consulta abrir=new Consulta();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        int seleccion = nUtn.getText().length();
        if(seleccion==0)
        {
            JOptionPane.showMessageDialog(null, "Escriba un n° de UTN para poder realizar la consulta");
        }else
        {
            combo_consulta=nUtn.getText().trim();
            dato_consulta="n_utn";
            Consulta abrir=new Consulta();
            abrir.setVisible(true);
            dispose();//Cierro la ventana anterior
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
        if(fecha.getDate()==null)
        {
            JOptionPane.showMessageDialog(null, "Establezca una fecha para poder realizar la consulta");
        }else
        {
            int dia,mes,año;
            dia=fecha.getCalendar().get(Calendar.DAY_OF_MONTH);
            mes=fecha.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
            mes++;//corrijo mes
            año=fecha.getCalendar().get(Calendar.YEAR);
            combo_consulta=dia+"/"+mes+"/"+año;
            dato_consulta="fecha";
            Consulta abrir=new Consulta();
            abrir.setVisible(true);
            dispose();//Cierro la ventana anterior
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        combo_consulta=comboInstituto.getSelectedItem().toString();
        dato_consulta="instituto";
        Consulta abrir=new Consulta();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        combo_consulta=comboDepto.getSelectedItem().toString();
        dato_consulta="depto";
        Consulta abrir=new Consulta();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
        combo_consulta=comboServicio.getSelectedItem().toString();
        dato_consulta="servicio";
        Consulta abrir=new Consulta();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        combo_consulta=comboEquipos.getSelectedItem().toString();
        dato_consulta="equipo";
        Consulta abrir=new Consulta();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton7ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        // Antes de comenzar a usar el programa, verifico la conexión con la base de datos
        
        
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
            java.util.logging.Logger.getLogger(PantallaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboDepto;
    private javax.swing.JComboBox<String> comboEquipos;
    public static javax.swing.JComboBox<String> comboInstituto;
    public static javax.swing.JComboBox<String> comboPersonal;
    private javax.swing.JComboBox<String> comboServicio;
    private javax.swing.JComboBox<String> comboTipos;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField nUtn;
    // End of variables declaration//GEN-END:variables

    
}
