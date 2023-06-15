
package Modelos;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmpleadosModel {
Connection MyConexion;
ResultSet result;


public DefaultTableModel ListarDatos()
{
    //realizar la conexion
    
    //limpiar la tabla vista empleados
    DefaultTableModel TablaModelo = new DefaultTableModel();
    TablaModelo.setRowCount(0);
    TablaModelo.setColumnCount(0);
    
    //prepara ek modelo de la tabla
    
        TablaModelo.addColumn("CODIGO");
    TablaModelo.addColumn("APELLIDOS");
    TablaModelo.addColumn("NOMBRES");
    TablaModelo.addColumn("TELEFONO");
    
    try
    {
        Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        result = sentencia.executeQuery("select * from empleados");  
       //return result;
       
       while(result.next())
       {
           TablaModelo.addRow(new Object[]{result.getInt("codigo"), result.getString("apellidos"), result.getString("nombre"), result.getString("telefono")});
       }
       return TablaModelo;
    }
    
    
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, "No se Pudo Listar Empleados...."+e.getMessage());
        return TablaModelo;
    }
}


public void Actualizar(int codigo, String apellido, String nombre, String telefono)
{
        try
        {
          Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        sentencia.executeQuery("Update empleados set apellidos ="+"'"+apellido+"',nombre="+"'"+nombre+"',telefono="+"'"+telefono+"' where codigo="+"'"+codigo+"'");
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "No se pudo Actualizar..."+ex.getMessage());
        }
        
          
}

public void Guardar(int codigo, String apellido, String nombre, String telefono)
{
     try
        {
          Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        sentencia.executeQuery("Insert into empleados values ("+"'"+codigo+"',"+"'"+apellido+"',"+"'"+nombre+"',"+"'"+telefono+"')");
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "No se pudo Guardar..."+ex.getMessage());
        }
         
     
    }

    public void Eliminar(int codEliminar) throws SQLException
     {
         try
        {
          Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        sentencia.executeQuery("Delete from empleados where codigo="+"'"+codEliminar+"'");
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "No se pudo Eliminar..."+ex.getMessage());
        }
     }
public DefaultTableModel Consultar(String consulta)
{
   
    //limpiar la tabla vista empleados
    DefaultTableModel TablaModelo1 = new DefaultTableModel();
    TablaModelo1.setRowCount(0);
    TablaModelo1.setColumnCount(0);
    
    //prepara ek modelo de la tabla
    
    TablaModelo1.addColumn("CODIGO");
    TablaModelo1.addColumn("APELLIDOS");
    TablaModelo1.addColumn("NOMBRES");
    TablaModelo1.addColumn("TELEFONO");
    
    try
    {
        Conexion nuevaConexion = new Conexion();
        MyConexion = nuevaConexion.Conectar();
        Statement sentencia = MyConexion.createStatement();
        result = sentencia.executeQuery(consulta);  
       //return result;
       
       while(result.next())
       {
           TablaModelo1.addRow(new Object[]{result.getInt("codigo"), result.getString("apellidos"), result.getString("nombre"), result.getString("telefono")});
       }
       return TablaModelo1;
    }
    
    
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null, "No se Pudo Consultar Empleado...."+e.getMessage());
        return TablaModelo1;
    }
   }
}
