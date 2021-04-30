/* CERRADO
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
    TRASLADO: no requirió repuestos
 */
package Frames;

import static Frames.Consulta.pedido_s;
import static Frames.Consulta.testigo_consulta;
//import static Frames.Consulta.dato_pedido;
import static Frames.GenerarPedido.comboPersonal;
import java.awt.Component;
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
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelos.Conexion;



/**
 *
 * @author alvar
 */

public class pedido_gral extends javax.swing.JFrame {

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

public static Integer dato_pedido = null;//variable que guarda el pedido generado para usarlo en repuestos
public static Integer guardar_repuesto=1;
public static Integer nrep = null;//variable que guarda el repuesto seleccionado para rescatar sus datos desde la BBDD
public static Integer cant;//para contar cantidad de filas de tabla REPUESTOS para establecer luego id de repuesto nuevo
public static Integer testigo=0; //Si testigo==1, es xq consulté un repuesto => en la ventana de repuestos rescato los datos del repuesto desde la BBDD
Integer tipoInforme;
Integer t_informe;
    /**
     * Creates new form pedido_gral
     */
    

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
    
    public pedido_gral() {
        initComponents();
        jCheckBox1.setSelected(false);
        jCheckBox1.setEnabled(false);
        jCheckBox2.setSelected(false);
        jCheckBox2.setEnabled(false);
        jRadioButton1.setSelected(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setSelected(false);
        jRadioButton2.setEnabled(false);
        jRadioButton3.setSelected(false);
        jRadioButton3.setEnabled(false);
        infTemp.setEnabled(false);
        motivo_p.setLineWrap(true);//Para habilitar el salto de línea automático con la condición de la siguiente línea...
        motivo_p.setWrapStyleWord(true);
        diag.setLineWrap(true);//Para habilitar el salto de línea automático con la condición de la siguiente línea...
        diag.setWrapStyleWord(true);
        obs_f.setLineWrap(true);//Para habilitar el salto de línea automático con la condición de la siguiente línea...
        obs_f.setWrapStyleWord(true);
        comentarios.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        comentarios.setWrapStyleWord(true);
        this.setTitle("Sistema de seguimiento de tareas OT-UTN");//título de ventana
        guardar_repuesto=1;
        setIconImage(new ImageIcon(getClass().getResource("/imagen/UTN.png")).getImage());//cambio ícono de barra de estado
        //setDefaultCloseOperation(pedido_gral.DO_NOTHING_ON_CLOSE);//Para evitar que la ventana se cierre con la cruz X
        soloNum(n_planilla);
        this.setExtendedState(MAXIMIZED_BOTH);
        t_repuesto.setEnabled(true); //evitar que se pueda editar la tabla
        t_datos.setEnabled(false); //evitar que se pueda editar la tabla
        obs_f.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        comentarios.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        motivo_p.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        diag.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        //PEDIDO
        Calendar c2 = new GregorianCalendar();
        confirmacion_cierre.hide();
        fecha_pc.setCalendar(c2);
        //fecha_t.setCalendar(c2);
        //hoja_no.setSelected(true);
        //arch_no.setSelected(true);
        //planilla_no.setSelected(true);
        //repuesto_no.setSelected(true);
        hoja_si.setEnabled(false);
        hoja_no.setEnabled(false);
        arch_si.setEnabled(false);
        arch_no.setEnabled(false);
        planilla_si.setEnabled(false);
        planilla_no.setEnabled(false);
        n_planilla.setEnabled(false);
        fecha_t.setEnabled(false);
        
        //REVISIÓN
        /*fecha_adq.setCalendar(c2);
        fecha_envio_cot.setCalendar(c2);
        fecha_p_cot.setCalendar(c2);
        fecha_resp_proov.setCalendar(c2);
        fecha_resp_serv.setCalendar(c2);
        repuesto_no.setSelected(true);
        fecha_adq.setEnabled(false);
        fecha_envio_cot.setEnabled(false);
        fecha_p_cot.setEnabled(false);
        fecha_resp_proov.setEnabled(false);
        fecha_resp_serv.setEnabled(false);
        n_proov.setEnabled(false);*/
        
        //CIERRE
        obs_f.setLineWrap(true);//para limitar el área de escritura. Cuando llega al borde hace un salto de línea
        reparado_no.setSelected(true);
        reparado_no.setEnabled(false);
        reparado_si.setEnabled(false);
        reparado_na.setEnabled(false);
        diag.setEnabled(false);
        repuesto_no.setEnabled(false);
        repuesto_si.setEnabled(false);
        b_traslado.setEnabled(false);
        b_cerrar.setEnabled(false);
        b_revision.setEnabled(false);
        b_repuesto.setEnabled(true);
        fecha_pc.setEnabled(false);
        p_cerrada_no.setEnabled(false);
        p_cerrada_na.setSelected(true);
        p_cerrada_si.setEnabled(false);
        p_cerrada_na.setEnabled(false);
        inf_f_no.setEnabled(false);
        inf_f_no.setSelected(true);
        inf_f_si.setEnabled(false);
        informe_si.setEnabled(false);
        informe_no.setEnabled(false);
        obs_f.setEnabled(false);
        
        //Integer dato_pedido = null;//variable que guarda el pedido generado para usarlo en repuestos
        ////////////////////////////////////////////////////////////////////////////////////////////
        
        ////LLENO TABLA SUPERIOR CON DATOS INICIALES
        try
        {
            String sql="";
            DefaultTableModel modelo = new DefaultTableModel();
            t_datos.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            if(testigo_consulta==1 || testigo_consulta==2){
                sql = "select id_pedido,instituto,depto,servicio,equipo,n_utn, obs,fecha from datos_iniciales where id_pedido = "+pedido_s;
            }else{
                sql = "select id_pedido,instituto,depto,servicio,equipo,n_utn, obs,fecha from datos_iniciales where id_pedido =(select max(id_pedido) from datos_iniciales)";
            }
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            modelo.addColumn("PEDIDO n°");
            modelo.addColumn("INSTITUTO");
            modelo.addColumn("DEPARTAMENTO");
            modelo.addColumn("SERVICIO");
            modelo.addColumn("EQUIPO");
            modelo.addColumn("UTN n°");
            modelo.addColumn("OBSERVACIONES");
            modelo.addColumn("FECHA");
            t_datos.setAutoResizeMode(t_datos.AUTO_RESIZE_ALL_COLUMNS);
            while (rs.next()) 
            {
                Object[] filas = new Object[cantidadColumnas];  
                for (int i = 0; i < cantidadColumnas; i++) 
                {
                    if(i==0)//////////Primero rescato el número del pedido para usarlo en REPUESTOS
                    {
                        dato_pedido = (Integer) rs.getObject(i+1);
                    }
                    filas [i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        
        
        ////Lo siguiente rescata los registros de la tabla REPUESTO de la BBDD////
        try
        {
            String sql="";
            DefaultTableModel modelo = new DefaultTableModel();
            t_repuesto.setModel(modelo);
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            sql = "select nombre,cantidad,id_repuesto from repuesto where id_pedido4 = "+ dato_pedido+" order by id_repuesto asc";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            modelo.addColumn("REPUESTO");
            modelo.addColumn("CANTIDAD");
            modelo.addColumn("ID");
            t_repuesto.setAutoResizeMode(t_repuesto.AUTO_RESIZE_ALL_COLUMNS);
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
        ////////////////////////////////////////////////////////////////////////////////////////////
        if(testigo_consulta==1  || testigo_consulta==2){//SI VENGO DE LA PANTALLA DE CONSULTA....
            
        ///////////LLENADO DE PLANILLA CONSULTA A PARTIR DEL REGISTRO SELECCIONADO///////////    
        
        //PRIMERO: VERIFICO SI PARA EL PEDIDO BUSCADO SE INGRESÓ DATOS DE PEDIDO. SI ES ASÍ, SE LOS LLENA, SINO, HABRÁ QUE LLENARLO
        try{
            
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql="";
            sql = "SELECT * FROM datos_pedido WHERE id_pedido1 = "+dato_pedido;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                //Si existe, lleno los campos...
                nombre_p.setText(rs.getString("nombre_usuario"));
                medio_p.setSelectedItem(rs.getString("medio"));
                motivo_p.setText(rs.getString("motivo"));
                boolean tras = rs.getBoolean("traslado");
                if (tras == true){
                    traslado_si.setSelected(true);
                    hoja_si.setEnabled(true);
                    hoja_no.setEnabled(true);
                    arch_si.setEnabled(true);
                    arch_no.setEnabled(true);
                    planilla_si.setEnabled(true);
                    planilla_no.setEnabled(true);
                    n_planilla.setEnabled(true);
                    fecha_t.setEnabled(true);
                    //Calendar c2 = new GregorianCalendar();
                    fecha_t.setCalendar(c2);
                    b_traslado.setEnabled(true);
                }else{
                    traslado_no.setSelected(true);
                    diag.setEnabled(true);
                    repuesto_no.setEnabled(true);
                    repuesto_si.setEnabled(true);
                    b_revision.setEnabled(true);
                    jCheckBox1.setEnabled(true);
                    jLabel7.setText("2) TRASLADO: no requiere traslado");
                }
                nombre_p.setEnabled(false);
                motivo_p.setEnabled(false);
                medio_p.setEnabled(false);
                traslado_no.setEnabled(false);
                traslado_si.setEnabled(false);
                b_Guardar.setEnabled(false);
                
            }
            else{
                //Si no existe, no hago nada
            }
        }
        catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        
        //Lleno COMENTARIOS:
        try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "SELECT comentario FROM comentarios WHERE id_pedido8 = "+dato_pedido;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    comentarios.setText(rs.getString("comentario"));
                }
            }
        catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
        
        /////SI LLENÉ LOS DATOS DEL PEDIDO => SI SE REQUIERE TRASLADO, SIGO CON TRASLADO. SINO, CON REVISIÓN.
        
        if(traslado_si.isSelected()){
            try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql="";
            sql = "SELECT * FROM traslado WHERE id_pedido2 = "+dato_pedido;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                
            ///////////////////////////MÉTODO PARA DAR FORMATO A LA FECHA RESCATADA DESDE BBDD PARA PONERLA EN EL JDATECHOOSER
            
            String formato_fecha = fecha_t.getDateFormatString();//rescato el formato de fecha que maneja el jdatechooser
            Date date = null ;
            SimpleDateFormat sdf = new SimpleDateFormat(formato_fecha);
                try {
                    date = sdf.parse(rs.getString("fecha_traslado"));
                    //Date miFechaYHora = new Date(rs.getTimestamp("fecha_traslado").getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(pedido_gral.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            ///////////////////////////////////////////////////////////////////////////////////////////////////////
            
            fecha_t.setDate(date);
            boolean hd = rs.getBoolean("hoja_desc");
            boolean ha = rs.getBoolean("hoja_archivada");
            boolean p = rs.getBoolean("planilla");
            n_planilla.setText(rs.getString("n_planilla"));
            if (hd == true){
                hoja_si.setSelected(true);
            }else{
                hoja_no.setSelected(true);
            }
            if (ha == true){
                arch_si.setSelected(true);
            }else{
                arch_no.setSelected(true);
            }
            if (p == true){
                planilla_si.setSelected(true);
            }else{
                planilla_no.setSelected(true);
            }
            fecha_t.setEnabled(false);
            hoja_si.setEnabled(false);
            hoja_no.setEnabled(false);
            traslado_no.setEnabled(false);
            arch_si.setEnabled(false);
            arch_no.setEnabled(false);
            planilla_si.setEnabled(false);
            planilla_no.setEnabled(false);
            n_planilla.setEnabled(false);
            b_traslado.setEnabled(false);
            
            diag.setEnabled(true);
            repuesto_no.setEnabled(true);
            repuesto_si.setEnabled(true);
            b_revision.setEnabled(true);
            jCheckBox1.setEnabled(true);
            /*if (jCheckBox1.isSelected()){
                jCheckBox2.setEnabled(true);
            }*/
            
            if(hoja_no.isSelected() || arch_no.isSelected() || planilla_no.isSelected()){
                    jLabel7.setText("2) TRASLADO: incompleto");
                }
            if(planilla_si.isSelected()){
                    p_cerrada_na.hide();
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
        
        /////SIGO CON REVISIÓN.
        
        
        //PRIMERO VERIFICO SI SE REQUIERE DESCONTAMINACIÓN IN SITU
                       
            try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "SELECT desc_insitu FROM comentarios WHERE id_pedido8 = "+dato_pedido;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    jCheckBox1.setSelected(rs.getBoolean("desc_insitu"));
                }
            }
            catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }
        //LUEGO VERIFICO si está hecha la DESCONTAMINACIÓN IN SITU
            if(jCheckBox1.isSelected()){           
            try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "SELECT ok_desc_insitu FROM comentarios WHERE id_pedido8 = "+dato_pedido;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    jCheckBox2.setSelected(rs.getBoolean("ok_desc_insitu"));
                }
            }
            catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }
            }else{
                jCheckBox2.setSelected(false);
            }
            
        if(traslado_no.isSelected() || (traslado_si.isSelected() && (hoja_no.isSelected() || hoja_si.isSelected()))){
            try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "SELECT * FROM revisión WHERE id_pedido3 = "+dato_pedido;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    //Si existe, lleno los campos...
                    diag.setText(rs.getString("diag"));
                    boolean rep = rs.getBoolean("repuesto");
                    if (rep == true){
                        repuesto_si.setSelected(true);
                        b_repuesto.setEnabled(true);
                        t_repuesto.setEnabled(true);
                    }else{
                        repuesto_no.setSelected(true);
                        b_repuesto.setEnabled(false);
                        jLabel16.setText("4) REPUESTOS: no requiere repuestos");
                    }
                    diag.setEnabled(false);
                    repuesto_si.setEnabled(false);
                    repuesto_no.setEnabled(false);
                    b_revision.setEnabled(false);
                    jCheckBox1.setEnabled(false);
                    jCheckBox2.setEnabled(false);
                    reparado_na.setEnabled(true);
                    reparado_no.setEnabled(true);
                    reparado_si.setEnabled(true);
                    
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
        
        /////SIGO CON EL CIERRE:
        
        if((repuesto_si.isSelected()) || (repuesto_no.isSelected())){
            //PRIMERO VERIFICO SI HAY INFORME TEMPORAL
            infTemp.setEnabled(true);
            
            try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "SELECT informe_temp FROM comentarios WHERE id_pedido8 = "+dato_pedido;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    infTemp.setSelected(rs.getBoolean("informe_temp"));
                }
            }
            catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }
            
            jRadioButton1.setEnabled(true);
            jRadioButton2.setEnabled(true);
            jRadioButton3.setEnabled(true);
            
            //VERIFICO SI HAY INGRESADO UN TIPO DE INFORME RALIZADO
            try{
                PreparedStatement ps = null;
                ResultSet rs = null;
                Conexion conn = new Conexion();
                Connection con = conn.getConexion();
                String sql="";
                sql = "SELECT tipo_informe FROM comentarios WHERE id_pedido8 = "+dato_pedido;
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    t_informe=rs.getInt("tipo_informe");
                }
                if(t_informe==1){
                    jRadioButton1.setSelected(true);
                }
                if(t_informe==2){
                    jRadioButton2.setSelected(true);
                }
                if(t_informe==3){
                    jRadioButton3.setSelected(true);
                }
                
            }
            catch(SQLException ex)
            {
                System.err.println(ex.toString());
            }
            
            try{
            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String sql="";
            sql = "SELECT * FROM cierre WHERE id_pedido5 = "+dato_pedido;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                
            ///////////////////////////MÉTODO PARA DAR FORMATO A LA FECHA RESCATADA DESDE BBDD PARA PONERLA EN EL JDATECHOOSER
            
            String formato_fecha = fecha_pc.getDateFormatString();//rescato el formato de fecha que maneja el jdatechooser
            Date date = null ;
            SimpleDateFormat sdf = new SimpleDateFormat(formato_fecha);
                //System.out.println(sdf);
                try {
                    date = sdf.parse(rs.getString("fecha_cierre"));
                    //Date miFechaYHora = new Date(rs.getTimestamp("fecha_traslado").getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(pedido_gral.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            ///////////////////////////
                //System.out.println(date);
            fecha_pc.setDate(date);
            boolean er = rs.getBoolean("equip_rep");
            boolean rs2 = rs.getBoolean("reg_sal");
            boolean ih = rs.getBoolean("informe_h");
            boolean if2 = rs.getBoolean("informe_f");
            obs_f.setText(rs.getString("obs_f"));
            if (er == true){
                reparado_si.setSelected(true);
            }else if(er == false){
                reparado_na.setSelected(true);
            }else{
                reparado_no.setSelected(true);
            }
            if (rs2 == true){
                p_cerrada_si.setSelected(true);
            }else if(rs2 == false){
                p_cerrada_no.setSelected(true);
            }else{
                p_cerrada_na.setSelected(true);
            }
            if (ih == true){
                informe_si.setSelected(true);
            }else{
                informe_no.setSelected(true);
            }
            if (if2 == true){
                inf_f_si.setSelected(true);
            }else{
                inf_f_no.setSelected(true);
            }
            reparado_na.setEnabled(false);
            reparado_no.setEnabled(false);
            reparado_si.setEnabled(false);
            jButton2.setEnabled(false);
            //confirmacion_cierre.setEnabled(true);
            confirmacion_cierre.show();
            confirmacion_cierre.setText("TRABAJO CERRADO");
            infTemp.setEnabled(false);
            b_repuesto.setEnabled(false);
            guardar_repuesto=0;
            if (repuesto_no.isSelected()){
                jLabel16.setText("4) REPUESTOS: no requirió repuestos");
            }else{
                jLabel16.setText("4) REPUESTOS: requirió repuestos");
            }
            if (traslado_no.isSelected()){
                jLabel7.setText("2) TRASLADO: no requirió traslado");
            }else{
                jLabel7.setText("2) TRASLADO: requirió traslado");
            }
            if(reparado_na.isSelected()){
                jLabel30.setText("5) CIERRE: trabajo no concluido");
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
            
        }
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        fecha_t = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombre_p = new javax.swing.JTextField();
        medio_p = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        traslado_si = new javax.swing.JRadioButton();
        traslado_no = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        hoja_no = new javax.swing.JRadioButton();
        hoja_si = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        arch_no = new javax.swing.JRadioButton();
        arch_si = new javax.swing.JRadioButton();
        planilla_no = new javax.swing.JRadioButton();
        planilla_si = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        n_planilla = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        repuesto_si = new javax.swing.JRadioButton();
        repuesto_no = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        reparado_si = new javax.swing.JRadioButton();
        reparado_no = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        p_cerrada_si = new javax.swing.JRadioButton();
        p_cerrada_no = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        fecha_pc = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        informe_si = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        inf_f_si = new javax.swing.JRadioButton();
        inf_f_no = new javax.swing.JRadioButton();
        informe_no = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        reparado_na = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        obs_f = new javax.swing.JTextArea();
        p_cerrada_na = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        motivo_p = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        diag = new javax.swing.JTextArea();
        b_Guardar = new javax.swing.JButton();
        b_traslado = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        b_revision = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        b_cerrar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        t_repuesto = new javax.swing.JTable();
        b_repuesto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        confirmacion_cierre = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        comentarios = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        infTemp = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel25 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1370, 768));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        t_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "PEDIDO N°", "INSTITUTO", "SERVICIO", "EQUIPO", "UTN n°"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(t_datos);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FECHA TRASLADO:");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha_t.setDateFormatString("dd/MM/yyyy");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NOMBRE");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MEDIO");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("¿Requiere traslado a OT-UTN?");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nombre_p.setToolTipText("Nombre del usuario que realizó el pedido");
        nombre_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_pActionPerformed(evt);
            }
        });
        nombre_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_pKeyTyped(evt);
            }
        });

        medio_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione medio...", "Mail", "Teléfono", "Personalmente" }));
        medio_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medio_pActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("¿Planilla de entrada a OT-UTN realizada?");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(traslado_si);
        traslado_si.setText("Si");
        traslado_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traslado_siActionPerformed(evt);
            }
        });

        buttonGroup1.add(traslado_no);
        traslado_no.setText("No");
        traslado_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traslado_noActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("2) TRASLADO:");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("MOTIVO");
        jLabel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup2.add(hoja_no);
        hoja_no.setText("No");
        hoja_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoja_noActionPerformed(evt);
            }
        });

        buttonGroup2.add(hoja_si);
        hoja_si.setText("Si");
        hoja_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoja_siActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("¿Hoja de descontaminación hecha y firmada?");
        jLabel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("¿Hoja de descontaminación archivada?");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup3.add(arch_no);
        arch_no.setText("No");
        arch_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arch_noActionPerformed(evt);
            }
        });

        buttonGroup3.add(arch_si);
        arch_si.setText("Si");
        arch_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arch_siActionPerformed(evt);
            }
        });

        buttonGroup4.add(planilla_no);
        planilla_no.setText("No");
        planilla_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planilla_noActionPerformed(evt);
            }
        });

        buttonGroup4.add(planilla_si);
        planilla_si.setText("Si");
        planilla_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planilla_siActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("N° planilla:");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        n_planilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n_planillaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("1) DATOS DEL PEDIDO:");
        jLabel10.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("PRIMER DIAGNÓSTICO");
        jLabel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("¿Requiere compra de repuestos?");
        jLabel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup5.add(repuesto_si);
        repuesto_si.setText("Si");
        repuesto_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repuesto_siActionPerformed(evt);
            }
        });

        buttonGroup5.add(repuesto_no);
        repuesto_no.setText("No");
        repuesto_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repuesto_noActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("3) REVISIÓN:");
        jLabel15.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel16.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("4) REPUESTOS:");
        jLabel16.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("¿Pedido concluido?");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup6.add(reparado_si);
        reparado_si.setText("Si");
        reparado_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reparado_siActionPerformed(evt);
            }
        });

        buttonGroup6.add(reparado_no);
        reparado_no.setText("No");
        reparado_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reparado_noActionPerformed(evt);
            }
        });

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("¿Planilla de entrada a OT-UTN cerrada?");
        jLabel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup7.add(p_cerrada_si);
        p_cerrada_si.setText("Si");
        p_cerrada_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_cerrada_siActionPerformed(evt);
            }
        });

        buttonGroup7.add(p_cerrada_no);
        p_cerrada_no.setText("No");
        p_cerrada_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_cerrada_noActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Fecha de cierre:");
        jLabel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha_pc.setDateFormatString("dd/MM/yyyy");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("¿Se realizó informe del trabajo?");
        jLabel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup8.add(informe_si);
        informe_si.setText("Si");
        informe_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informe_siActionPerformed(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("¿El informe fue firmado?");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup9.add(inf_f_si);
        inf_f_si.setText("Si");
        inf_f_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inf_f_siActionPerformed(evt);
            }
        });

        buttonGroup9.add(inf_f_no);
        inf_f_no.setText("No");
        inf_f_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inf_f_noActionPerformed(evt);
            }
        });

        buttonGroup8.add(informe_no);
        informe_no.setText("No");
        informe_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informe_noActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("6) OBSERVACIONES FINALES:");
        jLabel24.setPreferredSize(new java.awt.Dimension(99, 19));

        jLabel30.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("5) CIERRE:");
        jLabel30.setPreferredSize(new java.awt.Dimension(99, 19));

        buttonGroup6.add(reparado_na);
        reparado_na.setText("No aplica");
        reparado_na.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reparado_naActionPerformed(evt);
            }
        });

        obs_f.setColumns(20);
        obs_f.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        obs_f.setRows(5);
        obs_f.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                obs_fKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(obs_f);

        buttonGroup7.add(p_cerrada_na);
        p_cerrada_na.setText("No aplica");
        p_cerrada_na.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_cerrada_naActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        motivo_p.setColumns(20);
        motivo_p.setRows(5);
        motivo_p.setToolTipText("Motivo por el cual se realizó el pedido");
        motivo_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                motivo_pKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(motivo_p);

        diag.setColumns(20);
        diag.setRows(5);
        diag.setToolTipText("Diagnóstico luego de la primera revisión");
        diag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                diagKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(diag);

        b_Guardar.setText("GUARDAR PEDIDO");
        b_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_GuardarActionPerformed(evt);
            }
        });

        b_traslado.setText("GUARDAR TRASLADO");
        b_traslado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_trasladoActionPerformed(evt);
            }
        });

        b_revision.setText("GUARDAR REVISIÓN");
        b_revision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_revisionActionPerformed(evt);
            }
        });

        b_cerrar.setText("CERRAR TRABAJO");
        b_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cerrarActionPerformed(evt);
            }
        });

        t_repuesto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Cantidad", "ID"
            }
        ));
        t_repuesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_repuestoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_repuestoMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(t_repuesto);

        b_repuesto.setText("AGREGAR REPUESTO");
        b_repuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_repuestoActionPerformed(evt);
            }
        });

        jButton1.setText("← Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        confirmacion_cierre.setFont(new java.awt.Font("Agency FB", 1, 24)); // NOI18N
        confirmacion_cierre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirmacion_cierre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        jLabel11.setText("Incluir traslado incompleto y/o trabajo no concluido");

        comentarios.setColumns(20);
        comentarios.setRows(5);
        jScrollPane6.setViewportView(comentarios);

        jLabel18.setFont(new java.awt.Font("Dialog", 3, 10)); // NOI18N
        jLabel18.setText("COMENTARIOS (agregar fecha y nombre en cada renglón nuevo generado y no borrar lo existente):");

        jButton2.setText("Guardar comentario");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        infTemp.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        infTemp.setText("Temporal");
        infTemp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                infTempItemStateChanged(evt);
            }
        });
        infTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infTempActionPerformed(evt);
            }
        });

        jLabel17.setText("¿Requiere descontaminación para trabajo in situ?");

        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });

        jLabel19.setText("¿Hoja de dscontaminación firmada?");

        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Tipo de informe realizado:");
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup10.add(jRadioButton1);
        jRadioButton1.setText("Correctivo");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        buttonGroup10.add(jRadioButton2);
        jRadioButton2.setText("Técnico");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        buttonGroup10.add(jRadioButton3);
        jRadioButton3.setText("Salida a terceros");
        jRadioButton3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(traslado_no)
                                    .addComponent(traslado_si)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(medio_p, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(n_planilla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hoja_no)
                                    .addComponent(hoja_si)
                                    .addComponent(arch_no)
                                    .addComponent(arch_si)
                                    .addComponent(planilla_no)
                                    .addComponent(planilla_si)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(nombre_p, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_Guardar)
                            .addComponent(b_traslado)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(fecha_t, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(b_revision))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(repuesto_si)
                                            .addComponent(repuesto_no)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox2))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(b_repuesto))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(confirmacion_cierre, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(informe_no)
                                                    .addComponent(informe_si))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(infTemp))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(inf_f_no)
                                                    .addComponent(inf_f_si)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(reparado_no)
                                                    .addComponent(reparado_si))
                                                .addGap(2, 2, 2)
                                                .addComponent(reparado_na, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(p_cerrada_no)
                                                    .addComponent(p_cerrada_si))
                                                .addGap(2, 2, 2)
                                                .addComponent(p_cerrada_na))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fecha_pc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(b_cerrar, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(127, 127, 127))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButton2)
                                        .addGap(4, 4, 4)
                                        .addComponent(jRadioButton3))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(nombre_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(medio_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(traslado_no))
                                            .addComponent(traslado_si))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b_Guardar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fecha_t, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(20, 20, 20)
                                                        .addComponent(hoja_no))
                                                    .addComponent(hoja_si))
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(20, 20, 20)
                                                        .addComponent(arch_no))
                                                    .addComponent(arch_si))
                                                .addGap(10, 10, 10)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(20, 20, 20)
                                                        .addComponent(planilla_no))
                                                    .addComponent(planilla_si))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(n_planilla, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(b_traslado))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(reparado_no))
                                            .addComponent(reparado_si)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(reparado_na)))
                                        .addGap(17, 17, 17)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(p_cerrada_no))
                                            .addComponent(p_cerrada_si)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(p_cerrada_na)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fecha_pc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(8, 8, 8)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(informe_si)
                                            .addComponent(infTemp)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(informe_no)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(inf_f_no))
                                            .addComponent(inf_f_si))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jRadioButton2)
                                            .addComponent(jRadioButton1)
                                            .addComponent(jRadioButton3))
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(b_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 64, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jCheckBox2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(repuesto_si)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(repuesto_no))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_revision)))
                        .addGap(11, 11, 11)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_repuesto)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmacion_cierre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombre_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_pActionPerformed

    private void traslado_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traslado_siActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_traslado_siActionPerformed

    private void traslado_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traslado_noActionPerformed
        // TODO add your handling code here:
        if(traslado_no.isSelected())
        {
            hoja_si.setEnabled(false);
            hoja_no.setEnabled(false);
            arch_si.setEnabled(false);
            arch_no.setEnabled(false);
            planilla_si.setEnabled(false);
            planilla_no.setEnabled(false);
            n_planilla.setEnabled(false);
            fecha_t.setEnabled(false);
        }
    }//GEN-LAST:event_traslado_noActionPerformed

    private void hoja_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoja_siActionPerformed
        // TODO add your handling code here:
        arch_si.setEnabled(true);
        arch_no.setEnabled(true);
    }//GEN-LAST:event_hoja_siActionPerformed

    private void arch_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arch_siActionPerformed
        // TODO add your handling code here:
        planilla_si.setEnabled(true);
        planilla_no.setEnabled(true);
        planilla_no.setSelected(true);
        n_planilla.setEnabled(false);
        n_planilla.setText("-");
    }//GEN-LAST:event_arch_siActionPerformed

    private void planilla_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planilla_siActionPerformed
        // TODO add your handling code here:
        n_planilla.setEnabled(true);
        n_planilla.setText("");
    }//GEN-LAST:event_planilla_siActionPerformed

    private void n_planillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n_planillaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_n_planillaActionPerformed

    private void repuesto_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repuesto_siActionPerformed
        // TODO add your handling code here:

        if(repuesto_si.isSelected())
        {
            t_repuesto.setEnabled(true);
            /*fecha_adq.setEnabled(true);
            fecha_envio_cot.setEnabled(true);
            fecha_p_cot.setEnabled(true);
            fecha_resp_proov.setEnabled(true);
            fecha_resp_serv.setEnabled(true);
            n_proov.setEnabled(true);*/
        }
    }//GEN-LAST:event_repuesto_siActionPerformed

    private void repuesto_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repuesto_noActionPerformed
        // TODO add your handling code here:
        if(repuesto_no.isSelected())
        {
            t_repuesto.setEnabled(false);
            /*fecha_adq.setEnabled(false);
            fecha_envio_cot.setEnabled(false);
            fecha_p_cot.setEnabled(false);
            fecha_resp_proov.setEnabled(false);
            fecha_resp_serv.setEnabled(false);
            n_proov.setEnabled(false);*/
        }
    }//GEN-LAST:event_repuesto_noActionPerformed

    private void reparado_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reparado_siActionPerformed
        // TODO add your handling code here:
        if(reparado_si.isSelected())
        {
            //fecha_pc.setEnabled(true);
            
            fecha_pc.setEnabled(true);
            
            if(planilla_si.isSelected())
            {
                p_cerrada_no.setEnabled(true);
                p_cerrada_si.setEnabled(true);
                p_cerrada_na.setEnabled(true);
            }
            Calendar c2 = new GregorianCalendar();
            fecha_pc.setCalendar(c2);
            reparado_no.setEnabled(true);
            reparado_si.setEnabled(true);
            informe_si.setEnabled(true);
            informe_no.setEnabled(true);
            //inf_f_no.setEnabled(true);
            //inf_f_si.setEnabled(true);
            obs_f.setEnabled(true);
            b_cerrar.setEnabled(true);
            jRadioButton1.setSelected(false);
            jRadioButton1.setEnabled(true);
            jRadioButton2.setSelected(false);
            jRadioButton2.setEnabled(true);
            jRadioButton3.setSelected(false);
            jRadioButton3.setEnabled(true);
        }

    }//GEN-LAST:event_reparado_siActionPerformed

    private void reparado_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reparado_noActionPerformed
        // TODO add your handling code here:
        if(reparado_no.isSelected())
        {
            infTemp.setEnabled(true);
            fecha_pc.setEnabled(false);
            fecha_pc.setEnabled(false);
            p_cerrada_no.setEnabled(false);
            p_cerrada_si.setEnabled(false);
            inf_f_no.setEnabled(false);
            inf_f_si.setEnabled(false);
            informe_si.setEnabled(false);
            informe_no.setEnabled(false);
            obs_f.setEnabled(false);
            b_cerrar.setEnabled(false);
        }

    }//GEN-LAST:event_reparado_noActionPerformed

    private void p_cerrada_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_cerrada_siActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_cerrada_siActionPerformed

    private void p_cerrada_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_cerrada_noActionPerformed
        // TODO add your handling code here:

        if(p_cerrada_no.isSelected())
        {
            fecha_pc.setEnabled(false);
        }
    }//GEN-LAST:event_p_cerrada_noActionPerformed

    private void informe_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informe_siActionPerformed
        // TODO add your handling code here:

        if(informe_si.isSelected())
        {
            inf_f_no.setEnabled(true);
            inf_f_si.setEnabled(true);
        }
    }//GEN-LAST:event_informe_siActionPerformed

    private void inf_f_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inf_f_siActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inf_f_siActionPerformed

    private void inf_f_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inf_f_noActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_inf_f_noActionPerformed

    private void informe_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informe_noActionPerformed
        // TODO add your handling code here:

        if(informe_no.isSelected())
        {
            inf_f_no.setEnabled(false);
            inf_f_si.setEnabled(false);
        }
    }//GEN-LAST:event_informe_noActionPerformed

    private void reparado_naActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reparado_naActionPerformed
        // TODO add your handling code here:

        if(reparado_na.isSelected())
        {
            fecha_pc.setEnabled(false);
            p_cerrada_no.setEnabled(false);
            p_cerrada_si.setEnabled(false);
            p_cerrada_na.setEnabled(false);
            informe_si.setEnabled(false);
            informe_no.setEnabled(false);
            inf_f_no.setEnabled(false);
            inf_f_si.setEnabled(false);
            fecha_pc.setEnabled(true);
            obs_f.setEnabled(true);
            b_cerrar.setEnabled(true);
            jRadioButton1.setSelected(false);
            jRadioButton1.setEnabled(false);
            jRadioButton2.setSelected(false);
            jRadioButton2.setEnabled(false);
            jRadioButton3.setSelected(false);
            jRadioButton3.setEnabled(false);
        }
    }//GEN-LAST:event_reparado_naActionPerformed

    private void obs_fKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obs_fKeyTyped
        // TODO add your handling code here:

        int numeroCaracteres = 200;
        if(obs_f.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "La OBSERVACIÓN se limita a 200 caracteres cómo máximo");
        }
    }//GEN-LAST:event_obs_fKeyTyped

    private void p_cerrada_naActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_cerrada_naActionPerformed
        // TODO add your handling code here:

        if(p_cerrada_na.isSelected())
        {
            fecha_pc.setEnabled(false);
            fecha_pc.setEnabled(false);
            p_cerrada_no.setEnabled(false);
            p_cerrada_si.setEnabled(false);
            p_cerrada_na.setEnabled(false);
            informe_si.setEnabled(false);
            informe_no.setEnabled(false);
            inf_f_no.setEnabled(false);
            inf_f_si.setEnabled(false);
            fecha_pc.setEnabled(true);
            obs_f.setEnabled(true);
            b_cerrar.setEnabled(true);
        }
    }//GEN-LAST:event_p_cerrada_naActionPerformed

    private void nombre_pKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_pKeyTyped
        // TODO add your handling code here:
        int numeroCaracteres = 70;
        if(nombre_p.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El NOMBRE se limita a 70 caracteres cómo máximo");
        }
    }//GEN-LAST:event_nombre_pKeyTyped

    private void motivo_pKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_motivo_pKeyTyped
        // TODO add your handling code here:
        
        int numeroCaracteres = 100;
        if(motivo_p.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El MOTIVO se limita a 100 caracteres cómo máximo");
        }
    }//GEN-LAST:event_motivo_pKeyTyped

    private void diagKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagKeyTyped
        // TODO add your handling code here:
        int numeroCaracteres = 120;
        if(diag.getText().length()>=numeroCaracteres){
            evt.consume();
            JOptionPane.showMessageDialog(null, "El MOTIVO se limita a 120 caracteres cómo máximo");
        }
    }//GEN-LAST:event_diagKeyTyped

    private void b_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_GuardarActionPerformed
        // TODO add your handling code here:
        
        //Integer numPedido= dato_pedido;
        String nombre_u=nombre_p.getText().trim();
        int seleccionN = nombre_p.getText().length();  
        String medio=medio_p.getSelectedItem().toString();;
        int seleccionM = medio_p.getSelectedIndex();
        String motivo=motivo_p.getText().trim();;
        int seleccionMo = motivo_p.getText().length();  
        boolean t_si = traslado_si.isSelected();
        boolean t_no = traslado_no.isSelected();
        boolean tras;
        String tras_conf="";
        
        if(traslado_si.isSelected())
        {
            tras = true;
            tras_conf = "Si";
        }else
        {
            tras = false;
            tras_conf = "No";
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        //Tiene que llenarse todo para poder guardarse el registro, incluido si requiere o no traslado
        if((seleccionN == 0)|| (seleccionM  == 0)|| (seleccionMo == 0) || ((t_no==false)&& (t_si==false)))
        {
            JOptionPane.showMessageDialog(null, "Faltan ítems...");
        }
        else
        {
            
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-Nombre: "+nombre_u +"\n-Medio: "+medio +"\n-Motivo: "+motivo +"\n-¿Requiere traslado?: "+tras_conf, "Confirmar...", YES_NO_OPTION, 2);
        
        if ((confirmar == JOptionPane.YES_OPTION)) 
        {
        //Ingreso los datos a la BBDD
        try{
            String sql = "insert into datos_pedido (id_pedido1,nombre_usuario,medio,motivo,traslado) values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(dato_pedido) );//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
            ps.setString(2, nombre_u);
            ps.setString(3, medio);
            ps.setString(4, motivo);
            ps.setBoolean(5, Boolean.valueOf(tras));
            int res = ps.executeUpdate();
            if (res>0){
                JOptionPane.showMessageDialog(null, "Datos del PEDIDO guardados con éxito");
                nombre_p.setEnabled(false);
                motivo_p.setEnabled(false);
                medio_p.setEnabled(false);
                traslado_no.setEnabled(false);
                traslado_si.setEnabled(false);
                b_Guardar.setEnabled(false);
                
                if(traslado_si.isSelected())
                {
                    hoja_si.setEnabled(true);
                    hoja_no.setEnabled(true);
                    arch_si.setEnabled(true);
                    arch_no.setEnabled(true);
                    planilla_si.setEnabled(true);
                    planilla_no.setEnabled(true);
                    n_planilla.setEnabled(true);
                    fecha_t.setEnabled(true);
                    Calendar c2 = new GregorianCalendar();
                    fecha_t.setCalendar(c2);
                    b_traslado.setEnabled(true);
                }else
                {
                    diag.setEnabled(true);
                    repuesto_no.setEnabled(true);
                    repuesto_si.setEnabled(true);
                    b_revision.setEnabled(true);
                    jLabel20.setEnabled(false);
                    p_cerrada_na.setEnabled(false);
                    p_cerrada_no.setEnabled(false);
                    p_cerrada_si.setEnabled(false);
                    jCheckBox1.setEnabled(true);
                    if (jCheckBox1.isSelected()){
                    jCheckBox2.setEnabled(true);
            }
                }
                
            } else{
                JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
            }
            
            con.close();
        }catch (SQLException ex)
            {
                System.err.println(ex.toString());
            }
        }
        }
    }//GEN-LAST:event_b_GuardarActionPerformed

    private void b_trasladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_trasladoActionPerformed
        // TODO add your handling code here:
        
        int dia,mes,año;
        dia=fecha_t.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_t.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_t.getCalendar().get(Calendar.YEAR);
        String fecht;
        fecht=dia+"/"+mes+"/"+año;
        boolean h_si = hoja_si.isSelected();
        boolean h_no = hoja_no.isSelected();
        boolean hoja;
        boolean a_si = arch_si.isSelected();
        boolean a_no = arch_no.isSelected();
        boolean arch;
        boolean  p_si= planilla_si.isSelected();
        boolean  p_no= planilla_no.isSelected();
        boolean planill;
        int seleccionPl = n_planilla.getText().length(); 
        String n_planill=n_planilla.getText().trim();
                
        String hoja_conf="";
        String arch_conf="";
        String planill_conf="";
        
        if(hoja_si.isSelected())
        {
            hoja = true;
            hoja_conf = "Si";
        }else
        {
            hoja = false;
            hoja_conf = "No";
        }
        
        if(arch_si.isSelected())
        {
            arch = true;
            arch_conf = "Si";
        }else
        {
            arch = false;
            arch_conf = "No";
        }
        
        if(planilla_si.isSelected())
        {
            planill = true;
            planill_conf = "Si";
        }else
        {
            planill = false;
            planill_conf = "No";
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        if(hoja_no.isSelected() || arch_no.isSelected() || planilla_no.isSelected()){
            JOptionPane.showMessageDialog(null,"Está por cerrar el TRASLADO del equipo sin los siguientes requerimientos de traslado:\n- Hoja de descontaminación hecha y/o firmada\n - Hoja de descontaminación archivada\n - Planilla de entrada realizada \nDeberá justificar en las observaciones finales del trabajo o seleccionar NO en la siguiente ventana...");
        }
        
        //Tiene que llenarse todo para poder guardarse el registro
        if((seleccionPl == 0) || ((h_no==false)&& (h_si==false)) || ((a_no==false)&& (a_si==false)) || ((p_no==false)&& (p_si==false)))
        {
            JOptionPane.showMessageDialog(null, "Faltan ítems...");
        }
        else
        {
            
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-Fecha de traslado: "+fecht +"\n-¿Hoja de descontaminación hecha y firmada?: "+hoja_conf+"\n-¿Hoja de descontaminación archivada?: "+arch_conf+"\n-¿Planilla de entrada a OT-UTN realizada?: "+planill_conf+"\n-N° planilla: "+n_planill, "Confirmar...", YES_NO_OPTION, 2);
        
        if ((confirmar == JOptionPane.YES_OPTION)) 
        {
        //Ingreso los datos a la BBDD
        try{
            String sql = "insert into traslado (id_pedido2,fecha_traslado,hoja_desc,hoja_archivada,planilla,n_planilla) values (?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(dato_pedido) );//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
            ps.setString(2,fecht);
            ps.setBoolean(3, Boolean.valueOf(hoja));
            ps.setBoolean(4, Boolean.valueOf(arch));
            ps.setBoolean(5, Boolean.valueOf(planill));
            if (n_planill.equals("-")){
                ps.setInt(6,0000);
            }else{
                ps.setInt(6,Integer.valueOf(n_planill));
            }
            int res = ps.executeUpdate();
            if (res>0){
                JOptionPane.showMessageDialog(null, "Datos del TRASLADO guardados con éxito");
                fecha_t.setEnabled(false);
                hoja_si.setEnabled(false);
                hoja_no.setEnabled(false);
                traslado_no.setEnabled(false);
                arch_si.setEnabled(false);
                arch_no.setEnabled(false);
                planilla_si.setEnabled(false);
                planilla_no.setEnabled(false);
                n_planilla.setEnabled(false);
                b_traslado.setEnabled(false);
                
                diag.setEnabled(true);
                repuesto_no.setEnabled(true);
                repuesto_si.setEnabled(true);
                b_revision.setEnabled(true);
                jCheckBox1.setEnabled(true);
                jCheckBox2.setEnabled(false);
                if(hoja_no.isSelected() || arch_no.isSelected() || planilla_no.isSelected()){
                    jLabel7.setText("TRASLADO: incompleto");
                }
                if(planilla_si.isSelected()){
                    p_cerrada_na.hide();
                }
                
            } else{
                JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
            }
            
            con.close();
        }catch (SQLException ex)
            {
                System.err.println(ex.toString());
            }
        }
        }
                                      
    }//GEN-LAST:event_b_trasladoActionPerformed

    private void b_revisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_revisionActionPerformed
        // TODO add your handling code here:
        
        int diagnos = diag.getText().length(); 
        //int cant=0;
        boolean rep_si = repuesto_si.isSelected();
        boolean rep_no = repuesto_no.isSelected();
        boolean repu;
        String diagnostico=diag.getText().trim();
        String repu_conf="";
        
        if(repuesto_si.isSelected())
        {
            repu = true;
            repu_conf = "Si";
            //cant=1; //Si se confirma que se necesita repuesto, confirmo que mínimo se necesita uno
        }else
        {
            repu = false;
            repu_conf = "No";
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
         //Tiene que llenarse todo para poder guardarse el registro, incluido si requiere o no repuesto
        if((diagnos == 0) || ((rep_no==false)&& (rep_si==false)))
        {
            JOptionPane.showMessageDialog(null, "Faltan ítems...");
        }
        else
        {
            
            int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-Primer diagnóstico: "+diagnostico +"\n-¿Requiere repuesto?: "+repu_conf, "Confirmar...", YES_NO_OPTION, 2);
        
        if ((confirmar == JOptionPane.YES_OPTION)) 
        {
        //Ingreso los datos a la BBDD
        try{
            String sql = "insert into revisión (id_pedido3,diag,repuesto) values (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,Integer.valueOf(dato_pedido) );//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
            ps.setString(2, diagnostico);
            ps.setBoolean(3, Boolean.valueOf(repu));
            int res = ps.executeUpdate();
            if (res>0){
                JOptionPane.showMessageDialog(null, "Datos de la REVISIÓN guardados con éxito");
                
                diag.setEnabled(false);
                jCheckBox1.setEnabled(false);
                jCheckBox2.setEnabled(false);
                repuesto_no.setEnabled(false);
                repuesto_si.setEnabled(false);
                b_revision.setEnabled(false);
                //b_cerrar.setEnabled(true);
                reparado_na.setEnabled(true);
                reparado_no.setEnabled(true);
                reparado_si.setEnabled(true);
                fecha_pc.setEnabled(true);
                infTemp.setEnabled(true);
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                jRadioButton3.setEnabled(true);
                //infTemp.setSelected(false);
                if(repuesto_si.isSelected())
                {
                    t_repuesto.setEnabled(true);
                    b_repuesto.setEnabled(true);
                }
                
            } else{
                JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
            }
            
            con.close();
        }catch (SQLException ex)
            {
                System.err.println(ex.toString());
            }
        }
        }
    }//GEN-LAST:event_b_revisionActionPerformed

    private void b_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cerrarActionPerformed
        // TODO add your handling code here:
        
        int dia,mes,año;
        dia=fecha_pc.getCalendar().get(Calendar.DAY_OF_MONTH);
        mes=fecha_pc.getCalendar().get(Calendar.MONTH);//los meses se entregan de 0 a 11
        mes++;//corrijo mes
        año=fecha_pc.getCalendar().get(Calendar.YEAR);
        String fechc;
        String obs_final=obs_f.getText().trim();
        fechc=dia+"/"+mes+"/"+año;
        boolean r_si = reparado_si.isSelected();
        boolean r_no = reparado_no.isSelected();
        boolean reparado=false;
        boolean p_si = planilla_si.isSelected();
        boolean p_no = planilla_no.isSelected();
        boolean planilla=false;
        boolean  i_si= informe_si.isSelected();
        boolean  i_no= informe_no.isSelected();
        boolean informe=false;
        boolean  f_si= inf_f_si.isSelected();
        boolean  f_no= inf_f_no.isSelected();
        boolean firma;
                
        String rep_conf="";
        String pla_conf="";
        String inf_conf="";
        String fir_conf="";
        
        if(reparado_si.isSelected())
        {
            reparado = true;
            rep_conf = "Si";
        }
        if(reparado_no.isSelected())
        {
            reparado = false;
            rep_conf = "No";
        }
        if(reparado_na.isSelected())
        {
            reparado = false;
        }
        
        if(p_cerrada_si.isSelected())
        {
            planilla = true;
            pla_conf = "Si";
        }
        if(p_cerrada_no.isSelected())
        {
            planilla = false;
            pla_conf = "No";
        }
        /*if(p_cerrada_na.isSelected())
        {
            planilla = null;
        }*/
        
        if(informe_si.isSelected())
        {
            informe = true;
            inf_conf = "Si";
        }else
        {
            informe = false;
            inf_conf = "No";
        }
        if(inf_f_si.isSelected())
        {
            firma = true;
            fir_conf = "Si";
        }else
        {
            firma = false;
            fir_conf = "No";
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        if(reparado_si.isSelected())
        {
            if((reparado==false) || (informe==false) || (firma==false))
            {
                JOptionPane.showMessageDialog(null, "Faltan ítems y/o realizar informe y/o cerrar planilla de entrada");
            }else
            {
                int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-¿Trabajo concluido?: "+rep_conf +"\n-¿Planilla de entrada a OT-UTN cerrada?: "+pla_conf+"\n-Fechas de cierre: "+fechc+"\n-¿Se realizó informe del trabajo?: "+inf_conf, "Confirmar...", YES_NO_OPTION, 2);
                if ((confirmar == JOptionPane.YES_OPTION)) 
                {
                    try 
                    {
                        String sql = "insert into cierre (id_pedido5,equip_rep,reg_sal,fecha_cierre,informe_h,informe_f,obs_f) values (?,?,?,?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1,Integer.valueOf(dato_pedido) );//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
                        ps.setBoolean(2,Boolean.valueOf(reparado));
                        ps.setBoolean(3, Boolean.valueOf(planilla));
                        ps.setString(4, fechc);
                        ps.setBoolean(5, Boolean.valueOf(informe));
                        ps.setBoolean(6,Boolean.valueOf(firma));
                        ps.setString(7,obs_final);
                        int res = ps.executeUpdate();
                        if (res>0)
                        {
                            JOptionPane.showMessageDialog(null, "Datos del Cierre guardados con éxito");
                            reparado_na.setEnabled(false);
                            reparado_no.setEnabled(false);
                            reparado_si.setEnabled(false);
                            p_cerrada_na.setEnabled(false);
                            p_cerrada_no.setEnabled(false);
                            p_cerrada_si.setEnabled(false);
                            fecha_pc.setEnabled(false);
                            informe_no.setEnabled(false);
                            informe_si.setEnabled(false);
                            inf_f_no.setEnabled(false);
                            inf_f_si.setEnabled(false);
                            b_cerrar.setEnabled(false);
                            obs_f.setEnabled(false);
                            fecha_pc.setEnabled(false);
                            confirmacion_cierre.show();
                            confirmacion_cierre.setText("TRABAJO CERRADO");
                            guardar_repuesto=0;
                            if (traslado_no.isSelected()){
                                jLabel7.setText("2) TRASLADO: no requirió traslado");
                            }
                            if (repuesto_no.isSelected()){
                                jLabel16.setText("4) REPUESTOS: no requirió repuestos");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
                        }
                        //con.close();
                    } catch (SQLException ex) 
                    {
                        System.err.println(ex.toString());
                    }
                    ///////////confirmo cierre de trabajo en tabla datos_iniciales////////
                    try{
                        String cierro="SI";
                        String sql = "update datos_iniciales set cierre=? where id_pedido="+dato_pedido;
                        ps = con.prepareStatement(sql);
                        ps.setString(1,cierro);//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
                        int res = ps.executeUpdate();
                        if(res>0){
                            JOptionPane.showMessageDialog(null, "TRABAJO CERRADO");
                            infTemp.setEnabled(false);
                        }else{
                            JOptionPane.showMessageDialog(null, "ERROR en la conclusión del trabajo. Consulte al administrador");
                        }
                        con.close();
                    }catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                    //////////////////////////////////////////////////////////////////////
                }
            }
        }
        
        if(reparado_na.isSelected())
        {
            JOptionPane.showMessageDialog(null,"Está por cerrar el trabajo sin haberlo concluido (No aplica)\nSi está seguro pero no justificó el porqué en las observaciones finales, seleccione NO en la siguiente ventana...");
            
                int confirmar= JOptionPane.showConfirmDialog(null, "¿Son correctos los datos ingresados?\n\n-¿Trabajo concluido?: No aplica", "Confirmar...", YES_NO_OPTION, 2);
                if ((confirmar == JOptionPane.YES_OPTION)) 
                {
                    try 
                    {
                        String sql = "insert into cierre (id_pedido5,equip_rep,fecha_cierre,obs_f) values (?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1,Integer.valueOf(dato_pedido) );//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
                        ps.setBoolean(2,Boolean.valueOf(reparado));
                        ps.setString(3,fechc);
                        ps.setString(4,obs_final);
                        int res = ps.executeUpdate();
                        if (res>0)
                        {
                            JOptionPane.showMessageDialog(null, "Datos del Cierre guardados con éxito");
                            reparado_na.setEnabled(false);
                            reparado_no.setEnabled(false);
                            reparado_si.setEnabled(false);
                            b_cerrar.setEnabled(false);
                            obs_f.setEnabled(false);
                            confirmacion_cierre.show();
                            fecha_pc.setEnabled(false);
                            jButton2.setEnabled(false);
                            confirmacion_cierre.setText("TRABAJO CERRADO");
                            infTemp.setEnabled(false);
                            guardar_repuesto=0;
                            if (traslado_no.isSelected()){
                                jLabel7.setText("2) TRASLADO: no requirió traslado");
                            }
                            if (repuesto_no.isSelected()){
                                jLabel16.setText("4) REPUESTOS: no requirió repuestos");
                            }
                            jLabel30.setText("5) CIERRE: trabajo no concluido");
                        }else{
                            JOptionPane.showMessageDialog(null, "ERROR en la generación del pedido. Consulte al administrador");
                        }
                        //con.close();
                    } catch (SQLException ex) 
                    {
                        System.err.println(ex.toString());
                    }
                    ///////////confirmo cierre de trabajo en tabla datos_iniciales////////CERRADO
                    try{
                        String cierro="SI";
                        String sql = "update datos_iniciales set cierre=? where id_pedido="+dato_pedido;
                        ps = con.prepareStatement(sql);
                        ps.setString(1,cierro);//la columna en la tabla es integer, la variable también pero a la BBDD se pasa una cadena String, entonces debo convertir
                        int res = ps.executeUpdate();
                        if(res>0){
                            JOptionPane.showMessageDialog(null, "TRABAJO CERRADO");
                        }else{
                            JOptionPane.showMessageDialog(null, "ERROR en la conclusión del trabajo. Consulte al administrador");
                        }
                        con.close();
                    }catch (SQLException ex)
                    {
                        System.err.println(ex.toString());
                    }
                    //////////////////////////////////////////////////////////////////////
                }
                
            
        }
    }//GEN-LAST:event_b_cerrarActionPerformed

    private void medio_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medio_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medio_pActionPerformed

    private void b_repuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_repuestoActionPerformed
        // El botón AGREGAR responde a la ventana REPUESTOS mientras que REPUESTOS2 es sólo para la consulta
        testigo=0;
        cant=t_repuesto.getRowCount();//antes de abrir la ventana, guardo la cantidad de filas, es decir, cantidad de repuestos existentes
        cant=cant+1;
        repuestos abrir=new repuestos();
        abrir.setVisible(true);
        
    }//GEN-LAST:event_b_repuestoActionPerformed

    private void t_repuestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_repuestoMouseClicked
        // al seleccionar una fila, paso a la ventana de REPUESTOS correspondiente a ese pedido
        int fila = t_repuesto.getSelectedRow();//rescato el número de fila seleccionado
        
        nrep = (Integer) t_repuesto.getValueAt(fila, 2);//guardo el número de repuesto
        
        
        
        //nrep=fila+1;//variable que guarda el repuesto seleccionado para rescatar sus datos desde la BBDD
        testigo=1;//si testigo==1, la ventana Repuesto se cargarà con los datos del repuesto seleccionado
        repuestos abrir=new repuestos();
        abrir.setVisible(true);
        //dispose();//Cierro la ventana anterior
        //rescato los datos del repuesto desde la BBDD
        
        
        
        
    }//GEN-LAST:event_t_repuestoMouseClicked

    private void t_repuestoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_repuestoMousePressed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_t_repuestoMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(testigo_consulta==1)
        {
            Consulta abrir=new Consulta();
            abrir.setVisible(true);
            dispose();//Cierro la ventana anterior
        }
        if(testigo_consulta==0){
            GenerarPedido abrir=new GenerarPedido();
            abrir.setVisible(true);
            dispose();//Cierro la ventana anterior
        }
        if(testigo_consulta==2){
            listaRepuestos abrir=new listaRepuestos();
            abrir.setVisible(true);
            dispose();//Cierro la ventana anterior
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void hoja_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoja_noActionPerformed
        // TODO add your handling code here:
        arch_si.setEnabled(false);
        arch_no.setEnabled(false);
        arch_no.setSelected(true);
        planilla_no.setSelected(true);
        n_planilla.setEnabled(false);
        n_planilla.setText("-");
        planilla_si.setEnabled(false);
        planilla_no.setEnabled(false);
    }//GEN-LAST:event_hoja_noActionPerformed

    private void planilla_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planilla_noActionPerformed
        // TODO add your handling code here:
        n_planilla.setEnabled(false);
        n_planilla.setText("-");
    }//GEN-LAST:event_planilla_noActionPerformed

    private void arch_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arch_noActionPerformed
        // TODO add your handling code here:
        planilla_si.setEnabled(false);
        planilla_no.setEnabled(false);
        planilla_no.setSelected(true);
        n_planilla.setEnabled(false);
        n_planilla.setText("-");
        
    }//GEN-LAST:event_arch_noActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
        cerrar();//Pregunto antes de cerrar la ventana
        
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String texto=comentarios.getText().trim();;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set comentario = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setString(1,(texto));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void infTempItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_infTempItemStateChanged
        // TODO add your handling code here:
        Boolean inf_temp;
        
        if(infTemp.isSelected()){
            inf_temp=true;
        }else{
            inf_temp=false;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set informe_temp = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setBoolean(1, Boolean.valueOf(inf_temp));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_infTempItemStateChanged

    private void infTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infTempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_infTempActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        // TODO add your handling code here:
        Boolean descInSItu;
        
        if(jCheckBox1.isSelected()){
            descInSItu=true;
            jCheckBox2.setEnabled(true);
        }else{
            descInSItu=false;
            jCheckBox2.setEnabled(false);
            jCheckBox2.setSelected(false);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set desc_insitu = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setBoolean(1, Boolean.valueOf(descInSItu));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        // TODO add your handling code here:
        Boolean okDescInSItu;
        
        if(jCheckBox1.isSelected()){
            okDescInSItu=true;
        }else{
            okDescInSItu=false;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set ok_desc_insitu = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setBoolean(1, Boolean.valueOf(okDescInSItu));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        // TODO add your handling code here:
        
        
        if(jRadioButton1.isSelected()){
            tipoInforme=1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set tipo_informe = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setInt(1, Integer.valueOf(tipoInforme));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        // TODO add your handling code here:
        if(jRadioButton2.isSelected()){
            tipoInforme=2;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set tipo_informe = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setInt(1, Integer.valueOf(tipoInforme));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        // TODO add your handling code here:
        if(jRadioButton3.isSelected()){
            tipoInforme=3;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        try{
            String sql = "";
            sql = "update comentarios set tipo_informe = (?) where id_pedido8="+dato_pedido;
            ps = con.prepareStatement(sql);
            //ps.setInt(1,(dato_pedido));
            ps.setInt(1, Integer.valueOf(tipoInforme));
            int res = ps.executeUpdate();
            System.out.println(dato_pedido);
            con.close();
        }catch(SQLException ex)
        {
            System.err.println(ex.toString());
        }
    }
    }//GEN-LAST:event_jRadioButton3ItemStateChanged

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
            java.util.logging.Logger.getLogger(pedido_gral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pedido_gral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pedido_gral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pedido_gral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pedido_gral().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton arch_no;
    private javax.swing.JRadioButton arch_si;
    private javax.swing.JButton b_Guardar;
    private javax.swing.JButton b_cerrar;
    private javax.swing.JButton b_repuesto;
    private javax.swing.JButton b_revision;
    private javax.swing.JButton b_traslado;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JTextArea comentarios;
    private javax.swing.JLabel confirmacion_cierre;
    private javax.swing.JTextArea diag;
    private com.toedter.calendar.JDateChooser fecha_pc;
    private com.toedter.calendar.JDateChooser fecha_t;
    private javax.swing.JRadioButton hoja_no;
    private javax.swing.JRadioButton hoja_si;
    private javax.swing.JCheckBox infTemp;
    private javax.swing.JRadioButton inf_f_no;
    private javax.swing.JRadioButton inf_f_si;
    private javax.swing.JRadioButton informe_no;
    private javax.swing.JRadioButton informe_si;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JComboBox<String> medio_p;
    private javax.swing.JTextArea motivo_p;
    private javax.swing.JTextField n_planilla;
    private javax.swing.JTextField nombre_p;
    private javax.swing.JTextArea obs_f;
    private javax.swing.JRadioButton p_cerrada_na;
    private javax.swing.JRadioButton p_cerrada_no;
    private javax.swing.JRadioButton p_cerrada_si;
    private javax.swing.JRadioButton planilla_no;
    private javax.swing.JRadioButton planilla_si;
    private javax.swing.JRadioButton reparado_na;
    private javax.swing.JRadioButton reparado_no;
    private javax.swing.JRadioButton reparado_si;
    private javax.swing.JRadioButton repuesto_no;
    private javax.swing.JRadioButton repuesto_si;
    private javax.swing.JTable t_datos;
    public static javax.swing.JTable t_repuesto;
    private javax.swing.JRadioButton traslado_no;
    private javax.swing.JRadioButton traslado_si;
    // End of variables declaration//GEN-END:variables
}
