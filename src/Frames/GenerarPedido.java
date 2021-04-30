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



public class GenerarPedido extends javax.swing.JFrame {
    

    
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
        Logger.getLogger(GenerarPedido.class.getName()).log(Level.SEVERE, null, ex);
    }

    try {
        Connection cn=DriverManager.getConnection(url, user, password);
        //PreparedStatement pst=cn.prepareStatement("insert into prueba values (?,?)");//instrucciónes a la base de datos. Ingreso los cinco valores de la tabla (Instituto-Departamento-Servicio-Equipo-n° UTN
        JOptionPane.showMessageDialog(null, "BIENVENIDO", "Conexión exitosa a BBDD",DEFAULT_OPTION);


    } catch (SQLException ex) {
        Logger.getLogger(GenerarPedido.class.getName()).log(Level.SEVERE, null, ex);
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
    public GenerarPedido() {
        initComponents();
        this.setTitle("Sistema de seguimiento de tareas OT-UTN");//título de ventana
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        this.setLocationRelativeTo(null);//centra la ventana 
        //Pongo la fecha actual por default en el calendario
        Calendar c2 = new GregorianCalendar();
        fecha.setCalendar(c2);
        
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
        
        //limita
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
        jButton1 = new javax.swing.JButton();
        nUtn = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboPersonal = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();

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

        jButton1.setText("Seguir →");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nUtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nUtnActionPerformed(evt);
            }
        });
        nUtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nUtnKeyTyped(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Comentarios:");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nota.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        nota.setToolTipText("Brevemente: falla, sector, comentarios de usuario, etc.");
        nota.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nota.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notaActionPerformed(evt);
            }
        });
        nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                notaKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel8.setText("Generación de nuevo pedido...");

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

        jLabel12.setText("← Cambiar Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboDepto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboServicio, 0, 449, Short.MAX_VALUE)
                            .addComponent(nUtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipos, 0, 449, Short.MAX_VALUE)
                            .addComponent(comboEquipos, 0, 449, Short.MAX_VALUE)
                            .addComponent(nota, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(comboInstituto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboPersonal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
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
                    .addComponent(comboPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboInstituto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nUtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nota, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nUtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nUtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nUtnActionPerformed

    private void comboInstitutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboInstitutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboInstitutoActionPerformed

    private void comboServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboServicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboServicioActionPerformed

    private void comboEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEquiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEquiposActionPerformed

    private void comboTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTiposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboTiposActionPerformed

    private void notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notaActionPerformed
        // TODO add your handling code here:
        //Limito cantidad de caracteres del la observación
        
        
    }//GEN-LAST:event_notaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // primero establezco conexión con base de datos
        String personal=comboPersonal.getSelectedItem().toString();
        int seleccionP = comboPersonal.getSelectedIndex();
        String inst=comboInstituto.getSelectedItem().toString();
        int seleccionI = comboInstituto.getSelectedIndex();
        String dept=comboDepto.getSelectedItem().toString();
        int seleccionD = comboDepto.getSelectedIndex();
        String serv=comboServicio.getSelectedItem().toString();
        int seleccionS = comboServicio.getSelectedIndex();
        String equip=comboEquipos.getSelectedItem().toString();
        int seleccionE = comboEquipos.getSelectedIndex();
        String seleccionT = comboTipos.getSelectedItem().toString();
        Boolean tipo;
        if(seleccionT.equals("Equipos de frío") || seleccionT.equals("Equipos para aire acondicionado"))
        {
            tipo = true;
        }else
        {
            tipo = false;
        }
        
        String utn=nUtn.getText().trim();//trim elimina espacios ingresados por error al principio y al final de la cadena
        int seleccionU = nUtn.getText().length();
        String obs=nota.getText().trim();
        int dia,mes,año;
        dia=fecha.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha.getCalendar().get(Calendar.YEAR);
        String fech;
        fech=dia+"/"+mes+"/"+año;
        String cerrado="NO";
        
        //Evalúo si los combobox están vacíos
        boolean vacioP=comboPersonal.getSelectedItem().toString().isEmpty();
        boolean vacioI=comboInstituto.getSelectedItem().toString().isEmpty();
        boolean vacioD=comboDepto.getSelectedItem().toString().isEmpty();
        boolean vacioS=comboServicio.getSelectedItem().toString().isEmpty();
        boolean vacioE=comboEquipos.getSelectedItem().toString().isEmpty();
        boolean vacioF=false;
        if(fecha.getDate()==null)
        {
            vacioF=true;
        }
        
        //Evalúo si los combobox están vacío o le faltan selección o el n°UTN está vacío
        if ((seleccionP==0)||(seleccionI==0)||(seleccionD==0)||(seleccionS==0)||(seleccionE==0)||(seleccionU==0)||(vacioP==true)||(vacioI==true)||(vacioD==true)||(vacioS==true)||(vacioE==true)||(vacioF==true))
        {
            JOptionPane.showMessageDialog(null, "Faltan ítems...");
        }else
        {
        
        //Confirmación del usuario sobre los datos ingresados
        int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-Responsable: "+personal +"\n-Instituto: "+inst +"\n-Departamento: "+dept +"\n-Servicio: "+serv +"\n-Equipo: "+equip +"\n-n° UTN: "+utn +"\n-Observaciones: "+obs+"\n-Fecha: "+fech, "Confirmar...", YES_NO_OPTION, 2);
        
        if ((confirmar == JOptionPane.YES_OPTION)) 
        {
            
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        //Ingreso los datos a la BBDD
        try{
            String sql = "insert into datos_iniciales (nombre,instituto,depto,servicio,equipo,n_utn,obs,fecha,cierre,refrigerado) values (?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, personal);
            ps.setString(2, inst);
            ps.setString(3, dept);
            ps.setString(4, serv);
            ps.setString(5, equip);
            ps.setString(6, utn);
            ps.setString(7, obs);
            ps.setString(8, fech);
            ps.setString(9, cerrado);
            ps.setBoolean(10, tipo);
            int res = ps.executeUpdate();
            if (res>0){
                JOptionPane.showMessageDialog(null, "Registro generado con éxito");
            } else{
                JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
            }
            
            
        }catch (SQLException ex)
            {
                System.err.println(ex.toString());
            }
        
        //Genero id en tabla COMENTARIOS
        
        int pedido=0;
        // 1º) rescato el número de pedido de la tabla datos iniciales
        try{
            String sql="";
            sql = "select id_pedido from datos_iniciales where id_pedido =(select max(id_pedido) from datos_iniciales)";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                pedido=rs.getInt("id_pedido");
                System.out.println(pedido);
            }
            
        }catch (SQLException ex)
            {
                System.err.println(ex.toString());
            }
        // 2º) vuelco el dato en la tabla comentarios
        try{
            String sql = "";
            sql = "insert into comentarios (id_pedido8) values (?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,(pedido));
            int res = ps.executeUpdate();
            System.out.println(pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        
        ////////////////////////////////////////////////////////////////////
        
        ////////Abro la siguiente pantalla
        
        pedido_gral abrir=new pedido_gral();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    }//GEN-LAST:event_comboPersonalActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //botón volver a la pantalla anterior
        Inicio abrir=new Inicio();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
    }//GEN-LAST:event_jButton3ActionPerformed

    private void notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notaKeyTyped
        // TODO add your handling code here:
        
        int numeroCaracteres = 100;
        if(nota.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "La observación se limita a 100 caracteres cómo máximo");
        }
    }//GEN-LAST:event_notaKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosing

    private void nUtnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nUtnKeyTyped
        // TODO add your handling code here:
        
        int numeroCaracteres = 5;
        if(nUtn.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El n° de UTN se limita a 5 caracteres cómo máximo");
        }
        
    }//GEN-LAST:event_nUtnKeyTyped

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
            java.util.logging.Logger.getLogger(GenerarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarPedido().setVisible(true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField nUtn;
    private javax.swing.JTextField nota;
    // End of variables declaration//GEN-END:variables

    
}
