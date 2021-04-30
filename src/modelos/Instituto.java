/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import static Frames.GenerarPedido.comboInstituto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class Instituto {
    
    private int id_inst;
    private String n_inst;

    public int getId_inst() {
        return id_inst;
    }

    public void setId_inst(int id_inst) {
        this.id_inst = id_inst;
    }

    public String getN_inst() {
        return n_inst;
    }

    public void setN_inst(String n_inst) {
        this.n_inst = n_inst;
    }
    
    public String toString()
    {
        return this.n_inst;
    }
    
    public Vector<Instituto> mostrarInstitutos()
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        
        Vector<Instituto> datos =new Vector<Instituto>();
        Instituto dat =null;
        
        try{
            String sql  = "select * from aa1instituto";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //agrego la primer opción del jcombobox
            dat = new Instituto();
            dat.setId_inst(0);
            dat.setN_inst("Seleccione Instituto...");
            datos.add(dat);
            
            while(rs.next())
            {
                dat = new Instituto();
                dat.setId_inst(rs.getInt("id_inst"));
                dat.setN_inst(rs.getString("n_inst"));
                datos.add(dat);
            }
            
            rs.close();
            
        }catch(SQLException ex)
            
        {
            System.err.println(ex.toString());
        }
        return datos;
    }
}
