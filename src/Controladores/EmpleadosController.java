package Controladores;

import Modelos.*;
import Vistas.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmpleadosController implements ActionListener, MouseListener{
 frmEmpleados VistaEmpleados;
 frmPrincipal VistaPrincipal;
 frmConsulta VistaConsulta;
 EmpleadosModel ModeloEmpleado;
 
 
    

    public EmpleadosController(frmEmpleados VistaEmpleados, frmPrincipal VistaPrincipal,frmConsulta VistaConsulta, EmpleadosModel ModeloEmpleado) {
        this.VistaEmpleados = VistaEmpleados;
        this.VistaPrincipal = VistaPrincipal;
        this.VistaConsulta = VistaConsulta;
        this.ModeloEmpleado = ModeloEmpleado;
        
        /*LEVANTAR LAS VISTAS*/
      this.VistaPrincipal.setExtendedState(frmPrincipal.MAXIMIZED_BOTH);
      this.VistaPrincipal.setVisible(true);
      
      /*PONER A LA ESCUCHA LOS BOTONES*/
      this.VistaEmpleados.btn_Agregar.addActionListener(this);
      this.VistaEmpleados.btn_Editar.addActionListener(this);
      this.VistaEmpleados.btnEliminar.addActionListener(this);
      this.VistaPrincipal.jmAdEm.addMouseListener(this);
      this.VistaPrincipal.jmBuscEmp.addMouseListener(this);
      this.VistaConsulta.btnEjecutar.addActionListener(this);
        
      /*REALIZAR LA CONEXION*/
            
            //Limpiar la tabla Vista Empleados
                /*DefaultTableModel TablaModelo = new DefaultTableModel();
                TablaModelo.setRowCount(0);
                TablaModelo.setColumnCount(0);
                //this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
      
            //prepara el modelo de la tabla
                    TablaModelo.addColumn("CODIGO");
                    TablaModelo.addColumn("APELLIDOS");
                    TablaModelo.addColumn("NOMBRE");
                    TablaModelo.addColumn("TELEFONO");
                    
      /* LEVANTAR EL MODELO Y LOGRAR RECORRER EL RESULTSET*/
        //Captar el resultado que viene del Modelo desde el método LISTARDATOS
               // ResultSet rstEmpleados =  this.ModeloEmpleado.ListarDatos();
               
                    /*try
                    {
                       
                    while(rstEmpleados.next())
                    {
                     TablaModelo.addRow(new Object[]{rstEmpleados.getInt("codigo"),rstEmpleados.getString("apellido"),rstEmpleados.getString("nombre"),rstEmpleados.getString("telefono")});  
                    }  
                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null, "Algo hizo falta..."+e);
                    }*/
                    
                    DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
                    this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
                    
                   
            //PASAR EL MODELO CREADO A LA TABLA DE LA VISTA EMPLEADOS        
                    this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        
          //    poner a la escucha tabla empleados
          this.VistaEmpleados.jtbEmpleados.addMouseListener(this);
          
        /*LEVANTAR LA VISTA EMPLEADOR*/
      //this.VistaEmpleados.setLocationRelativeTo(null);
      //this.VistaEmpleados.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.VistaEmpleados.btn_Editar)
        {
            this.ModeloEmpleado.Actualizar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()), 
                    this.VistaEmpleados.txtApellidos.getText(), this.VistaEmpleados.txtNombre.getText(),
                    this.VistaEmpleados.txtTelefono.getText());
            this.ModeloEmpleado.ListarDatos();
            DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
             this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        }
        if(e.getSource() == this.VistaEmpleados.btn_Agregar)
        {
            this.ModeloEmpleado.Guardar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()), 
                    this.VistaEmpleados.txtApellidos.getText(), this.VistaEmpleados.txtNombre.getText(),
                    this.VistaEmpleados.txtTelefono.getText());
            this.ModeloEmpleado.ListarDatos();
            DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
            this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        }
        if(e.getSource() == this.VistaEmpleados.btnEliminar)
        {
            try {
                this.ModeloEmpleado.Eliminar(Integer.parseInt(this.VistaEmpleados.txtCodigo.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.ModeloEmpleado.ListarDatos();
            DefaultTableModel TablaModelo = this.ModeloEmpleado.ListarDatos();
            this.VistaEmpleados.jtbEmpleados.setModel(TablaModelo);
        }
        if(e.getSource() == this.VistaConsulta.btnEjecutar){
            DefaultTableModel TablaModelo1 = this.ModeloEmpleado.Consultar(this.VistaConsulta.txtConsulta.getText());
            this.VistaConsulta.jtbBuscar.setModel(TablaModelo1);
        }

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        int fila;
        if(arg0.getSource()==this.VistaEmpleados.jtbEmpleados)
        {
            //JOptionPane.showMessageDialog(null, "clicando la tabla");
            fila = this.VistaEmpleados.jtbEmpleados.getSelectedRow();
            //codigo
            this.VistaEmpleados.txtCodigo.setText(
            this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 0).toString());
            //apellidos
            this.VistaEmpleados.txtApellidos.setText(
            this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 1).toString());
            //nombre
            this.VistaEmpleados.txtNombre.setText(
            this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 2).toString());
            //telefono
            this.VistaEmpleados.txtTelefono.setText(
            this.VistaEmpleados.jtbEmpleados.getValueAt(fila, 3).toString());
        }
        if(arg0.getSource() == this.VistaPrincipal.jmAdEm){
            this.VistaEmpleados.setVisible(true);
        }
        if(arg0.getSource() == this.VistaPrincipal.jmBuscEmp){
            this.VistaConsulta.setVisible(true); 
        }
                           
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
       
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
       
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        
    }

    
    
 
 
}
