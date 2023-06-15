import Controladores.EmpleadosController;
import Modelos.EmpleadosModel;
import Vistas.*;

public class main {

    public static void main(String[] args) {
        // TODO code application logic here
        frmPrincipal VistaPrincipal = new frmPrincipal();
        frmConsulta VistaConsulta = new frmConsulta(VistaPrincipal, true);
        EmpleadosModel ModeloEmpleados = new EmpleadosModel();
        frmEmpleados VistaEmpleados = new frmEmpleados(VistaPrincipal,true);
        
        EmpleadosController ControladorEmpleados = new EmpleadosController(VistaEmpleados, VistaPrincipal, VistaConsulta, ModeloEmpleados);
        
    }
    
}
