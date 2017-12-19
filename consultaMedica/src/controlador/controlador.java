
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import modelo.modelo;
import vista.vistaPrincipal;
import vista.vistaAgregar;
import vista.vistaListar;

public class controlador {
    
    //vistas
    private vistaPrincipal vista1;
    private vistaAgregar vista2 = new vistaAgregar();
    private vistaListar vista3 = new vistaListar();
    
    //modelo
    private modelo modelo = new modelo();
    
    //enum
    
    //constructor de clase
    
    //inicia todas las acciones y listeners de las vistas
    public void iniciar(){
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {       
        switch (accion.valueOf(e.getActionCommand())) {
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
