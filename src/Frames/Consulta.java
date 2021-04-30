/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.pedido_gral.dato_pedido;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.chart.PieChart;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jtable.updow.java.ColorearFilas;
import modelos.Conexion;
import modelos.MiRenderer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Consulta extends javax.swing.JFrame {
    
    TableRowSorter trs;//para el filtro de la tabla (en conjunto al combobox)
    DefaultTableModel modelo = new DefaultTableModel();//el modelo de la tabla lo pongo global así lo uso también en el filtro
    String texto = "";
    String selec = "";
    String check = "";
    
    
    //METODO PARA IMPRIMIR TABLA
    public void imprimir_tabla(JTable jTable, String header, String footer, boolean showPrintDialog){        
    boolean fitWidth = true;        
    boolean interactive = true;
    // We define the print mode (Definimos el modo de impresión)
    JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
    try {
        // Print the table (Imprimo la tabla)             
        boolean complete = jTable.print(mode,
                new MessageFormat(header),
                new MessageFormat(footer),
                showPrintDialog,
                null,
                interactive);                 
        if (complete) {
            // Mostramos el mensaje de impresión existosa
            JOptionPane.showMessageDialog(jTable,
                    "Print complete (Impresión completa)",
                    "Print result (Resultado de la impresión)",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostramos un mensaje indicando que la impresión fue cancelada                 
            JOptionPane.showMessageDialog(jTable,
                    "Print canceled (Impresión cancelada)",
                    "Print result (Resultado de la impresión)",
                    JOptionPane.WARNING_MESSAGE);
        }
    } catch (PrinterException pe) {
        JOptionPane.showMessageDialog(jTable, 
                "Print fail (Fallo de impresión): " + pe.getMessage(), 
                "Print result (Resultado de la impresión)", 
                JOptionPane.ERROR_MESSAGE);
    }
}//
    
    
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
    /////
    //Método para colorear celdas
    public void pintarColumnaTabla(){
        ColorearFilas color = new ColorearFilas(9);
        t_consulta.getColumnModel().getColumn(9).setCellRenderer(color);
    }
    /////
    public static String pedido_s = "";//variable que guarda el pedido seleccionado para mostrar sus datos    
    public static Integer testigo_consulta=0; //Si testigo_consulta==1, aviso que al abrir la ventana Consulta2 debo primero resctar los datos desde la BBDD del pedido en cuestión
    
    public Consulta() {
        initComponents();
        this.setTitle("Sistema de seguimiento de tareas OT-UTN");//título de ventana
        this.setExtendedState(MAXIMIZED_BOTH);//ventana maximizada
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        //Establezco título de la ventana según la consulta realizada
        jLabel8.setText("Consulta por "+PantallaConsulta.dato_consulta);
        
        //t_consulta.setEnabled(false); //evitar que se pueda editar la tabla
    ////Lo siguiente rescata los registros de la tabla datos_iniciales de la BBDD según la consulta realizada////
        try
        {
            //DefaultTableModel modelo = new DefaultTableModel();
            t_consulta.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql = "select id_pedido,nombre,instituto,depto,servicio,equipo,n_utn, obs,fecha,cierre,refrigerado from datos_iniciales where "+PantallaConsulta.dato_consulta+"='"+PantallaConsulta.combo_consulta+"' order by id_pedido desc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            modelo.addColumn("PEDIDO n°");
            modelo.addColumn("RESPONSABLE");
            modelo.addColumn("INSTITUTO");
            modelo.addColumn("DEPARTAMENTO");
            modelo.addColumn("SERVICIO");
            modelo.addColumn("EQUIPO");
            modelo.addColumn("UTN n°");
            modelo.addColumn("OBSERVACIONES");
            modelo.addColumn("FECHA");
            modelo.addColumn("¿CERRADO?");
            modelo.addColumn("¿REFRIGERADO?");
            t_consulta.setAutoResizeMode(t_consulta.AUTO_RESIZE_ALL_COLUMNS);
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
                pintarColumnaTabla();//Pinto las celdas CERRADO por "SI" (verde) o por "NO" (rojo)
            }
            
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        
        ///////////////////////////////////////CUENTO CANTIDAD DE FILAS CON SI Y CON NO/////////////////////////////
        Integer numSI=0;
        Integer numNO=0;
        for (int f = 0; f < t_consulta.getRowCount(); f++)
        {
          for(int c = 0; c < t_consulta.getColumnCount(); c++)
          {
            if (t_consulta.getValueAt(f, c).equals("SI")) 
            {
              numSI++;
            }else if(t_consulta.getValueAt(f, c).equals("NO")){
                numNO++;
            }
          }
        }
        System.out.println("SI: "+numSI);
        System.out.println("NO: "+numNO);
        float total = numNO+numSI;
        System.out.println("Total: "+total);
        System.out.println(total);
        String porcentajeSI = Float.toString(Math.round(numSI*100/total));
        String porcentajeNO = Float.toString(Math.round(numNO*100/total));
        System.out.println("Porcentaje de SI: "+porcentajeSI);
        System.out.println("Porcentaje de NO: "+porcentajeNO);
        String si="SI: "+numSI+" ("+porcentajeSI+"%)" ;
        String no="NO: "+numNO+" ("+porcentajeNO+"%)" ;
        /*//////////// GRAFICA LINEAL
        XYSeries oSeries = new XYSeries("Estado de trabajos cerrados");
        oSeries.add(1,numSI);
        oSeries.add(2,numNO);
        XYSeriesCollection oDataset = new XYSeriesCollection();
        oDataset.addSeries(oSeries);
        JFreeChart oChart = ChartFactory.createXYLineChart("FER", "si", "no", oDataset);
        ChartPanel oPanel = new ChartPanel(oChart);
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(oPanel);
        jPanel1.validate();*/
        
        ////////////// GRAFICA DE TORTA
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue(si, numSI);
        dataset.setValue(no, numNO);
        //JFreeChart chart = ChartFactory.createRingChart("Cerrado", dataset, rootPaneCheckingEnabled, rootPaneCheckingEnabled, Locale.ITALY);
        JFreeChart chart = ChartFactory.createPieChart3D("Trabajos cerrados", dataset, true, true, false);
        
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
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(oPanel);
        jPanel1.validate();
        
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_consulta = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        Combo_cerrado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        filtro = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel8.setText("Consulta por ...");

        t_consulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PEDIDO N°", "INSTITUTO", "SERVICIO", "EQUIPO", "Title 5", "Title 6", "Title 7", "Title 8", "UTN n°"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_consulta.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                t_consultaMouseMoved(evt);
            }
        });
        t_consulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_consultaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_consulta);

        jButton1.setText("← Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("FILTRAR POR TRABAJO CERRADO?");

        Combo_cerrado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SI", "NO" }));
        Combo_cerrado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Combo_cerradoItemStateChanged(evt);
            }
        });
        Combo_cerrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Combo_cerradoActionPerformed(evt);
            }
        });
        Combo_cerrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Combo_cerradoKeyTyped(evt);
            }
        });

        jLabel3.setText("FILTRO (respetar minúsculas/mayúsculas/tildes/espacios):");

        filtro.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                filtroInputMethodTextChanged(evt);
            }
        });
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });
        filtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filtroKeyTyped(evt);
            }
        });

        jButton2.setText("IMPRIMIR TABLA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("Mostrar sólo refrigerados");

        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel8)
                        .addGap(0, 1153, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(31, 31, 31)
                                        .addComponent(Combo_cerrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(590, 590, 590)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(39, 39, 39)
                                        .addComponent(jCheckBox1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(86, 86, 86)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Combo_cerrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PantallaConsulta abrir=new PantallaConsulta();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
        testigo_consulta=0;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t_consultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_consultaMouseClicked
        // al seleccionar una fila, paso a la ventana de pedido_gral correspondiente a ese pedido
        
        int fila = t_consulta.getSelectedRow();//rescato el número de fila seleccionado
        pedido_s = t_consulta.getValueAt(fila, 0).toString();//guardo el número de pedido
        //Abro la ventana pedido_gral correspondiente a ese pedido
        testigo_consulta=1;//si testigo_consulta=1= abro la ventana pedido_gral avisando que vengo desde una consulta
        pedido_gral abrir=new pedido_gral();
        abrir.setVisible(true);
        dispose();//Cierro la ventana anterior
        
    }//GEN-LAST:event_t_consultaMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void Combo_cerradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Combo_cerradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Combo_cerradoActionPerformed

    private void Combo_cerradoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Combo_cerradoKeyTyped
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_Combo_cerradoKeyTyped

    private void Combo_cerradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Combo_cerradoItemStateChanged
        /////FILTRADO DE TABLA POR TRABAJO CERRADO SI/NO
        
        filtro.setText("");//Si selecciono un item del combo, borro el filtro
        selec=Combo_cerrado.getSelectedItem().toString();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel> (modelo);
        t_consulta.setRowSorter(trs);
        if(selec!=" "){
            trs.setRowFilter(RowFilter.regexFilter(selec));
        }else{
            t_consulta.setRowSorter(trs);
        }
    }//GEN-LAST:event_Combo_cerradoItemStateChanged

    private void filtroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroKeyTyped
        // TODO add your handling code here:
        Combo_cerrado.setSelectedIndex(0);//Si escribo en el filtro, reinicio el Combobox de CIERRE
        int numeroCaracteres = 20;
        if(filtro.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El filtro se limita a 20 caracteres cómo máximo");
        }
        
        texto=filtro.getText();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel> (modelo);
        t_consulta.setRowSorter(trs);
        if(texto!=" "){
            trs.setRowFilter(RowFilter.regexFilter(texto));
        }else{
            t_consulta.setRowSorter(trs);
        }
        
    }//GEN-LAST:event_filtroKeyTyped

    private void filtroInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_filtroInputMethodTextChanged
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_filtroInputMethodTextChanged

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        imprimir_tabla(t_consulta, "Sistema de seguimiento de tareas OT-UTN",jLabel8.getText(),true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        /////MOSTRAR SÓLO EQUIPOS REFRIGERADOS
        
        
        if (jCheckBox1.isSelected()==true){
            check="true";
        }else{
            check="";
        }
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel> (modelo);
        t_consulta.setRowSorter(trs);
        if(check!=" "){
            trs.setRowFilter(RowFilter.regexFilter(check+selec));
        }else{
            t_consulta.setRowSorter(trs);
        }
        
        /*filtro.setText("");//Si selecciono un item del combo, borro el filtro
        selec=Combo_cerrado.getSelectedItem().toString();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel> (modelo);
        t_consulta.setRowSorter(trs);
        if(selec!=" "){
            trs.setRowFilter(RowFilter.regexFilter(selec));
        }else{
            t_consulta.setRowSorter(trs);
        }*/
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void t_consultaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_consultaMouseMoved
        // TODO add your handling code here:
        //this.t_consulta.setDefaultRenderer(Object.class, new MiRenderer());

    }//GEN-LAST:event_t_consultaMouseMoved

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
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Combo_cerrado;
    private javax.swing.JTextField filtro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_consulta;
    // End of variables declaration//GEN-END:variables
}
