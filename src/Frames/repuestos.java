/*cant
 Esta ventana hace referencia a la ventana pedido_gral
 *///pedido_s
package Frames;

import static Frames.pedido_gral.cant;
import static Frames.pedido_gral.testigo;
import static Frames.pedido_gral.nrep;
import static Frames.pedido_gral.dato_pedido;
import static Frames.Consulta.pedido_s;
import static Frames.Consulta.testigo_consulta;
import static Frames.pedido_gral.t_repuesto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelos.Conexion;
public class repuestos extends javax.swing.JFrame {

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

    public repuestos() {
        initComponents();
        obs_repuesto.setLineWrap(true);//Para habilitar el salto de línea automático con la condición de la siguiente línea...
        obs_repuesto.setWrapStyleWord(true);
        jLabel20.hide();
        fecha_resp_serv.hide();
        this.setTitle("Sistema de seguimiento de tareas OT-UTN");//título de ventana
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        this.setLocationRelativeTo(null);//centra la ventana
        obs_repuesto.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        Date date = new Date();
        fecha_adq.setDate(date);
        fecha_envio_cot.setDate(date);
        fecha_p_cot.setDate(date);
        fecha_resp_proov.setDate(date);
        fecha_resp_serv.setDate(date);
        
        cotizar_no.setSelected(true);
        envio_no.setSelected(true);
        adq_no.setSelected(true);
        
        //Muestro en número de pedido en la ventana
        Integer d_pedido=pedido_gral.dato_pedido;
        String n_pedido=String.valueOf(d_pedido);
        if(pedido_gral.guardar_repuesto==0){
            b_g_repuesto.setEnabled(false);
        }
        label_pedido.setText("PEDIDO #"+n_pedido);
        
        soloNum(cant_repuesto);
        soloNum(precio);
        
        nom_proov.setEnabled(false);
        fecha_p_cot.setEnabled(false);
        fecha_resp_proov.setEnabled(false);
        precio.setEnabled(false);
        envio_no.setEnabled(false);
        envio_si.setEnabled(false);
        fecha_envio_cot.setEnabled(false);
        fecha_resp_serv.setEnabled(false);
        fecha_adq.setEnabled(false);
        fecha_envio_cot.setEnabled(false);
        fecha_resp_serv.setEnabled(false);
        fecha_adq.setEnabled(false);
        /////Si se llamó a esta ventana para consultar un repuesto, debo rescatar sus datos desde la BBDD
        if(testigo==1){//si consultè un repuesto existente
            label_pedido.setText("PEDIDO #"+n_pedido+" - REPUESTO #"+nrep);
            try{
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    Conexion conn = new Conexion();
                    Connection con = conn.getConexion();
                    String sql="";
                    sql = "SELECT * FROM repuesto WHERE id_pedido4 = "+dato_pedido+" and id_repuesto = "+nrep;
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    if(rs.next()){
                        //Si existe, lleno los campos...
                        ///////////////////////////MÉTODO PARA DAR FORMATO A LA FECHA RESCATADA DESDE BBDD PARA PONERLA EN EL JDATECHOOSER
            
                        String formato_fecha = fecha_p_cot.getDateFormatString();//rescato el formato de fecha que maneja el jdatechooser
                        Date fpc = null ;
                        Date frp = null ;
                        Date fes = null ;
                        Date frs = null ;
                        Date fadq = null ;
                        
                        SimpleDateFormat sdf = new SimpleDateFormat(formato_fecha);
                            try {
                                fpc = sdf.parse(rs.getString("fecha_pedido"));
                                frp = sdf.parse(rs.getString("fecha_resp_prov"));
                                fes = sdf.parse(rs.getString("fecha_envio_serv"));
                                frs = sdf.parse(rs.getString("fecha_resp_serv"));
                                fadq = sdf.parse(rs.getString("fecha_adq"));
                                //Date miFechaYHora = new Date(rs.getTimestamp("fecha_traslado").getTime());
                            } catch (ParseException ex) {
                                Logger.getLogger(pedido_gral.class.getName()).log(Level.SEVERE, null, ex);
                            }
                
            ///////////////////////////
                        fecha_p_cot.setDate(fpc);
                        fecha_resp_proov.setDate(frp);
                        fecha_envio_cot.setDate(fes);
                        fecha_resp_serv.setDate(frs);
                        fecha_adq.setDate(fadq);
                        boolean csn = rs.getBoolean("cot_si_no");
                        boolean esn = rs.getBoolean("envio_si_no");
                        boolean asn = rs.getBoolean("adq_si_no");
                        nom_repuesto.setText(rs.getString("nombre"));
                        cant_repuesto.setText(rs.getString("cantidad"));
                        nom_proov.setText(rs.getString("nombre_prov"));
                        precio.setText(rs.getString("precio"));
                        obs_repuesto.setText(rs.getString("obs_repuesto"));
                        if(csn == true){
                            cotizar_si.setSelected(true);
                            nom_proov.setEnabled(true);
                            fecha_p_cot.setEnabled(true);
                            fecha_resp_proov.setEnabled(true);
                            precio.setEnabled(true);
                            envio_no.setEnabled(true);
                            envio_si.setEnabled(true);
                        }else{
                            cotizar_no.setSelected(true);
                        }
                        if(esn == true){
                            envio_si.setSelected(true);
                            fecha_envio_cot.setEnabled(true);
                            fecha_resp_serv.setEnabled(true);
                        }else{
                            envio_no.setSelected(true);
                        }
                        if(asn == true){
                            adq_si.setSelected(true);
                            if(adq_si.isSelected())
                            {
                                fecha_adq.setEnabled(true);
                            }
                        }else{
                            adq_no.setSelected(true);
                        }
                    }
                    else{
                        //Si no existe, no hago nada
                    }
                    
            }
        catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        }
    }//////Lo que sigue es la actualización del repuesto seleccionado. Esto lo hago mediante el mismo botón de la ventana pero
     //// usando un alter ya que el registro ya existe. Ver que en la acción del botón se usa el alter cuando testigo==1

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        label_pedido = new javax.swing.JLabel();
        fecha_adq = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        fecha_p_cot = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        fecha_resp_proov = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        nom_proov = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        fecha_envio_cot = new com.toedter.calendar.JDateChooser();
        fecha_resp_serv = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        b_g_repuesto = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        nom_repuesto = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cant_repuesto = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cotizar_si = new javax.swing.JRadioButton();
        cotizar_no = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        envio_si = new javax.swing.JRadioButton();
        envio_no = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        adq_si = new javax.swing.JRadioButton();
        adq_no = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        obs_repuesto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N

        label_pedido.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        label_pedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_pedido.setText("PEDIDO # ");
        label_pedido.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Fecha de repuesto adquirido:");
        jLabel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Fecha en que se pidió cotización:");
        jLabel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha_p_cot.setDateFormatString("dd/MM/yyyy");

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Nombre del proveedor:");
        jLabel28.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha_resp_proov.setDateFormatString("dd/MM/yyyy");

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Fecha respuesta proveedor:");
        jLabel29.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nom_proov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom_proovKeyTyped(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Fecha envío de cotización a servicio:");
        jLabel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha_envio_cot.setDateFormatString("dd/MM/yyyy");

        fecha_resp_serv.setDateFormatString("dd/MM/yyyy");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Fecha de respuesta de servicio:");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        b_g_repuesto.setText("GUARDAR REPUESTO");
        b_g_repuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_g_repuestoActionPerformed(evt);
            }
        });

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Nombre del repuesto:");
        jLabel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nom_repuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nom_repuestoKeyTyped(evt);
            }
        });

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Cantidad:");
        jLabel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cant_repuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cant_repuestoKeyTyped(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("¿Es necesario pedir cotización?");
        jLabel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(cotizar_si);
        cotizar_si.setText("Si");
        cotizar_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cotizar_siActionPerformed(evt);
            }
        });

        buttonGroup1.add(cotizar_no);
        cotizar_no.setText("No");
        cotizar_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cotizar_noActionPerformed(evt);
            }
        });
        cotizar_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cotizar_noKeyTyped(evt);
            }
        });

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Precio:");
        jLabel33.setToolTipText("Precio final con iva incluido y en pesos argentinos");
        jLabel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioKeyTyped(evt);
            }
        });

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("¿Se enviará cotización al servicio?");
        jLabel34.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup2.add(envio_si);
        envio_si.setText("Si");
        envio_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envio_siActionPerformed(evt);
            }
        });

        buttonGroup2.add(envio_no);
        envio_no.setText("No");
        envio_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envio_noActionPerformed(evt);
            }
        });

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("¿Repuesto adquirido?");
        jLabel35.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup3.add(adq_si);
        adq_si.setText("Si");
        adq_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adq_siActionPerformed(evt);
            }
        });

        buttonGroup3.add(adq_no);
        adq_no.setText("No");
        adq_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adq_noActionPerformed(evt);
            }
        });

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Observaciones:");
        jLabel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        obs_repuesto.setColumns(20);
        obs_repuesto.setRows(5);
        obs_repuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                obs_repuestoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(obs_repuesto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cotizar_no)
                                    .addComponent(cotizar_si)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nom_repuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cant_repuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(30, 30, 30)
                                    .addComponent(fecha_adq, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(envio_no)
                                    .addComponent(envio_si)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(fecha_envio_cot, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(fecha_resp_serv, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(fecha_p_cot, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(fecha_resp_proov, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(nom_proov, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adq_no)
                                    .addComponent(adq_si))))
                        .addGap(0, 15, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(b_g_repuesto)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(label_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nom_repuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cant_repuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(cotizar_no))
                            .addComponent(cotizar_si)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nom_proov, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_p_cot, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_resp_proov, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(envio_no))
                    .addComponent(envio_si)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha_envio_cot, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha_resp_serv, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(adq_no))
                    .addComponent(adq_si)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha_adq, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(b_g_repuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nom_proovKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom_proovKeyTyped
        // TODO add your handling code here:
        int numeroCaracteres = 50;
        if(nom_proov.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El nombre del proveedor se limita a 50 caracteres cómo máximo");
        }
    }//GEN-LAST:event_nom_proovKeyTyped

    private void b_g_repuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_g_repuestoActionPerformed
        // 
        String sql="";
        String nombre_r=nom_repuesto.getText().trim();
        int seleccionR = nom_repuesto.getText().length(); 
        String cant_r=cant_repuesto.getText().trim();
        int seleccionCR = cant_repuesto.getText().length(); 
        String n_r=nom_proov.getText().trim();
        int seleccionNR = nom_proov.getText().length(); 
        String n_p=precio.getText().trim();
        int seleccionP = precio.getText().length(); 
        String obs_r=obs_repuesto.getText().trim();
        boolean c_si = cotizar_si.isSelected();
        boolean c_no = cotizar_no.isSelected();
        boolean cot;
        boolean e_si = envio_si.isSelected();
        boolean e_no = envio_no.isSelected();
        boolean env;
        boolean a_si = adq_si.isSelected();
        boolean a_no = adq_no.isSelected();
        boolean adq;
        int dia,mes,año;
        String fpcot,frp,fec,frs,fadq;
        
        dia=fecha_p_cot.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_p_cot.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_p_cot.getCalendar().get(Calendar.YEAR);
        fpcot=dia+"/"+mes+"/"+año;
        
        dia=fecha_resp_proov.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_resp_proov.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_resp_proov.getCalendar().get(Calendar.YEAR);
        frp=dia+"/"+mes+"/"+año;
        
        dia=fecha_envio_cot.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_envio_cot.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_envio_cot.getCalendar().get(Calendar.YEAR);
        fec=dia+"/"+mes+"/"+año;
        
        dia=fecha_resp_serv.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_resp_serv.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_resp_serv.getCalendar().get(Calendar.YEAR);
        frs=dia+"/"+mes+"/"+año;
        
        dia=fecha_adq.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_adq.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_adq.getCalendar().get(Calendar.YEAR);
        fadq=dia+"/"+mes+"/"+año;
        
        String c_conf="";
        String e_conf="";
        String a_conf="";
        
        if(cotizar_si.isSelected())
        {
            cot = true;
            c_conf = "Si";
        }else
        {
            cot = false;
            c_conf = "No";
        }
        if(envio_si.isSelected())
        {
            env = true;
            e_conf = "Si";
        }else
        {
            env = false;
            e_conf = "No";
        }
        if(adq_si.isSelected())
        {
            adq = true;
            a_conf = "Si";
        }else
        {
            adq = false;
            a_conf = "No";
        }
        
        
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        if((seleccionR == 0) || (seleccionCR==0))
        {
            JOptionPane.showMessageDialog(null, "Al menos debe llenar Nombre del repuesto y Cantidad");
        }
        else
        {
            
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-Nombre del repuesto: "+nombre_r +"\n-Cantidad: "+cant_r, "Confirmar...", YES_NO_OPTION, 2);
        
        if ((confirmar == JOptionPane.YES_OPTION)) 
        {
        
        try{
            if(testigo==1){//Si testigo == 1 es porque estoy actualizando un repuesto ya existente
                //
                sql = "update repuesto set id_pedido4 = ?, nombre = ?,cantidad = ?,fecha_pedido = ?,fecha_resp_prov = ?,nombre_prov = ?,fecha_envio_serv = ?,fecha_resp_serv = ?,fecha_adq = ?,obs_repuesto = ?,cot_si_no = ?,envio_si_no = ?,adq_si_no = ?,id_repuesto = ?, precio = ? WHERE id_pedido4 = "+dato_pedido+" and id_repuesto = "+(nrep);
            }else{
                sql = "insert into repuesto (id_pedido4,nombre,cantidad,fecha_pedido,fecha_resp_prov,nombre_prov,fecha_envio_serv,fecha_resp_serv,fecha_adq,obs_repuesto,cot_si_no,envio_si_no,adq_si_no,id_repuesto,precio) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
            
            ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(dato_pedido));
            ps.setString(2,nombre_r);
            ps.setInt(3, Integer.valueOf(cant_r));
            ps.setString(4, fpcot);
            ps.setString(5, frp);
            ps.setString(6, n_r);
            ps.setString(7, fec);
            ps.setString(8, frs);
            ps.setString(9, fadq);
            ps.setString(10, obs_r);
            ps.setBoolean(11, Boolean.valueOf(cot));
            ps.setBoolean(12, Boolean.valueOf(env));
            ps.setBoolean(13,Boolean.valueOf(adq));
            if(testigo==1){
                ps.setInt(14,Integer.valueOf(pedido_gral.nrep));
            }else{
                ps.setInt(14,Integer.valueOf(pedido_gral.cant));
            }
            
            if(seleccionP==0){
                ps.setInt(15,0);
            }else{
                ps.setInt(15,Integer.valueOf(n_p));
            }
            
            int res = ps.executeUpdate();
            if (res>0){
                JOptionPane.showMessageDialog(null, "Datos del REPUESTO guardados con éxito");
                testigo=0;
            } else{
                JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
            }
            
            //con.close();
            dispose();//Cierro la ventana anterior
            
            
        }catch (SQLException ex)
            {
                System.err.println(ex.toString());
            }
        //////////////Actualizo tabla REPUESTOS/////////////////////////////
            try
        {
            DefaultTableModel modelo = new DefaultTableModel();
            t_repuesto.setModel(modelo);
            sql = "select nombre,cantidad,id_repuesto from repuesto where id_pedido4 = "+ dato_pedido+" order by id_repuesto asc";
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            modelo.addColumn("REPUESTO");
            modelo.addColumn("CANTIDAD");
            modelo.addColumn("ID");
            t_repuesto.setAutoResizeMode(t_repuesto.AUTO_RESIZE_ALL_COLUMNS);
            /*int anchos[] = {100,30};
            for(int x = 0; x<cantidadColumnas; x++)
            {
                t_datos.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }*/
            
            while (rs.next()) 
            {
                Object[] filas = new Object[cantidadColumnas];  
                for (int i = 0; i < cantidadColumnas; i++) 
                {
                    filas [i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
            ///////////////////////////////////////////////////////////////////////////////////////////////
            
        }
        //pedido_gral abrir=new pedido_gral();
        //abrir.setVisible(true);
        
        
        }
    }//GEN-LAST:event_b_g_repuestoActionPerformed

    private void nom_repuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nom_repuestoKeyTyped
        // TODO add your handling code here:
        int numeroCaracteres = 45;
        if(nom_repuesto.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El nombre del REPUESTO se limita a 45 caracteres cómo máximo");
        }
    }//GEN-LAST:event_nom_repuestoKeyTyped

    private void cant_repuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cant_repuestoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cant_repuestoKeyTyped

    private void cotizar_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cotizar_siActionPerformed
        // TODO add your handling code here:
        if(cotizar_si.isSelected())
        {
            nom_proov.setEnabled(true);
            fecha_p_cot.setEnabled(true);
            fecha_resp_proov.setEnabled(true);
            precio.setEnabled(true);
            envio_no.setEnabled(true);
            envio_si.setEnabled(true);
        }

    }//GEN-LAST:event_cotizar_siActionPerformed

    private void cotizar_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cotizar_noActionPerformed
        // TODO add your handling code here:
        if(cotizar_no.isSelected())
        {
            nom_proov.setEnabled(false);
            fecha_p_cot.setEnabled(false);
            fecha_resp_proov.setEnabled(false);
            precio.setEnabled(false);
            envio_no.setEnabled(false);
            envio_si.setEnabled(false);
            fecha_envio_cot.setEnabled(false);
            fecha_resp_serv.setEnabled(false);
        }
    }//GEN-LAST:event_cotizar_noActionPerformed

    private void precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_precioKeyTyped

    private void envio_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envio_siActionPerformed
        // TODO add your handling code here:
        if(envio_si.isSelected())
        {
            fecha_envio_cot.setEnabled(true);
            fecha_resp_serv.setEnabled(true);
        }
    }//GEN-LAST:event_envio_siActionPerformed

    private void envio_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envio_noActionPerformed
        // TODO add your handling code here:
        if(envio_no.isSelected())
        {
            fecha_envio_cot.setEnabled(false);
            fecha_resp_serv.setEnabled(false);
        }
    }//GEN-LAST:event_envio_noActionPerformed

    private void adq_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adq_siActionPerformed
        // TODO add your handling code here:
        if(adq_si.isSelected())
        {
            fecha_adq.setEnabled(true);
        }
    }//GEN-LAST:event_adq_siActionPerformed

    private void adq_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adq_noActionPerformed
        // TODO add your handling code here:
        if(adq_no.isSelected())
        {
            fecha_adq.setEnabled(false);
        }
    }//GEN-LAST:event_adq_noActionPerformed

    private void obs_repuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obs_repuestoKeyTyped
        // TODO add your handling code here:
        int numeroCaracteres = 110;
        if(obs_repuesto.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "La OBSERVACIÓN se limita a 110 caracteres cómo máximo");
        }
        
    }//GEN-LAST:event_obs_repuestoKeyTyped

    private void cotizar_noKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cotizar_noKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cotizar_noKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(repuestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new repuestos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adq_no;
    private javax.swing.JRadioButton adq_si;
    private javax.swing.JButton b_g_repuesto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField cant_repuesto;
    private javax.swing.JRadioButton cotizar_no;
    private javax.swing.JRadioButton cotizar_si;
    private javax.swing.JRadioButton envio_no;
    private javax.swing.JRadioButton envio_si;
    private com.toedter.calendar.JDateChooser fecha_adq;
    private com.toedter.calendar.JDateChooser fecha_envio_cot;
    private com.toedter.calendar.JDateChooser fecha_p_cot;
    private com.toedter.calendar.JDateChooser fecha_resp_proov;
    private com.toedter.calendar.JDateChooser fecha_resp_serv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_pedido;
    private javax.swing.JTextField nom_proov;
    private javax.swing.JTextField nom_repuesto;
    private javax.swing.JTextArea obs_repuesto;
    private javax.swing.JTextField precio;
    // End of variables declaration//GEN-END:variables
}
