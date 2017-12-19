
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import modelo.modelo;
import vista.vista1;
import vista.vistaAgregar;
import vista.vistaListar;

public class controlador implements ActionListener, MouseListener, FocusListener {
    
    //vistas
    private vista1 vista1;
    private vistaAgregar vista2 = new vistaAgregar();
    private vistaListar vista3 = new vistaListar();
    
    //modelo
    private modelo modelo = new modelo();

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //enum
    public enum accion {
        //menu-items
        itmAgregar, //agregar paciente
        itmListar, //listar nomina
        itmSalir, //salir
        //botones
        btnBuscar, //buscar un paciente por codigo
        btnGuardar, //guardar cambios (al editar paciente)
        btnEliminar, //eliminar paciente
        btnModificar, //modifica campos 
        btnLimpiar, //limpiar campos (para agregar o editar paciente)
        btnSalir, //vuelve a la ventana principal
        //JTextFields
        txtRut, // campo de ingreso para el rut del paciente
        txtNombre, // campo de ingreso para el nombre del paciente
        txtEdad, // campo de ingreso para la edad del paciente
        txtDireccion, // campo de ingreso para el sueldo del paciente
        txtBuscarRut // campo de ingreso para buscar pacientes por rut       
    };
    //constructor de clase
    
    //inicia todas las acciones y listeners de las vistas
    public void iniciar(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista1);
            SwingUtilities.updateComponentTreeUI(this.vista2);
            SwingUtilities.updateComponentTreeUI(this.vista3);
            this.vista1.setLocationRelativeTo(null);
            this.vista1.setTitle("Consulta Médica");
            //this.vista1.tblTrabajadores.setModel(this.modelo.ListarTrabajadores()); //actualiza JTable
            this.vista1.setVisible(true);
            
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        //escuchamos los botones
        
        //item salir
        this.vista1.itmSalir.setActionCommand("itmSalir");
        this.vista1.itmSalir.addActionListener(this);
        //item agregar
        this.vista1.itmAgregar.setActionCommand("itmAgregar");
        this.vista1.itmAgregar.addActionListener(this);
        //item listar
        this.vista1.itmListar.setActionCommand("itmListar");
        this.vista1.itmListar.addActionListener(this);


        //boton Guardar
        this.vista2.btnGuardar.setActionCommand("btnGuardar");
        this.vista2.btnGuardar.addActionListener(this);
        //boton Limpiar
        this.vista2.btnLimpiar.setActionCommand("btnLimpiar");
        this.vista2.btnLimpiar.addActionListener(this);
        //boton Salir
        this.vista2.btnSalir.setActionCommand("btnSalir");
        this.vista2.btnSalir.addActionListener(this);

        //agregamos focus listeners para los campos del formulario ingresar
        this.vista2.txtRut.addFocusListener(this);
        this.vista2.txtNombre.addFocusListener(this);
        this.vista2.txtEdad.addFocusListener(this);
        this.vista2.txtDireccion.addFocusListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {       
        switch (accion.valueOf(e.getActionCommand())) {
            case itmSalir:
                System.exit(0);
                break;
                
            case itmAgregar:
                this.vista2.setLocationRelativeTo(null);
                this.vista2.setTitle("Agregar Paciente");
                this.vista2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                this.vista2.setVisible(true);
                break;
                
            case itmListar:
                this.vista3.setLocationRelativeTo(null);
                this.vista3.setTitle("Listar Pacientes");
                this.vista3.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                this.vista3.setVisible(true);
                
            case btnGuardar:
                //Enviamos datos del formulario Agregar Paciente a metodo agregarPaciente
                if(ValidacionFinal(vista2) == true){
                    if (this.modelo.agregarPaciente(rut, nombre, genero, 0, direccion, ciudad, isapre, true)
                            Integer.parseInt(this.vista2.txtCodigo.getText()),
                            this.vista2.txtRut.getText(),
                            this.vista2.txtNombre.getText(),
                            this.vista2.txtApellido.getText(),
                            Integer.parseInt(this.vista2.txtCelular.getText()),
                            this.vista2.txtEmail.getText(),
                            Integer.parseInt(this.vista2.txtSueldo.getText()),
                            this.vista2.cmbEstadoCivil.getSelectedItem().toString().substring(0, 1),
                            this.vista2.cmbDepartamento.getSelectedItem().toString()
                    )) {
                        JOptionPane.showMessageDialog(null, "Paciente agregado correctamente");
                        //Limpiamos textField
                        LimpiarForm(vista2);

                    }else{
                        JOptionPane.showMessageDialog(null, "No se pudo agregar trabajador");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
                }
                break;
                
            case btnLimpiar:
                LimpiarForm(vista2);
                break;

            case btnSalir:
                LimpiarForm(vista2);
                this.vista2.setVisible(false);
                break;
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
    
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        
    }

    private void LimpiarForm(vistaAgregar vista){
        vista.txtRut.setText("");
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtDireccion.setText("");
        vista.cmbCiudad.setSelectedIndex(0);
        vista.btgGenero.clearSelection();
        vista.btgIsapre.clearSelection();
        vista.chkDonante.setSelected(false);
    }
    
    private boolean ValidacionFinal(vistaAgregar v){
        if(
                v.txtRut.getText().equals("") ||
                v.txtNombre.getText().equals("") ||
                v.txtEdad.getText().equals("") ||
                v.txtDireccion.getText().equals("") ||
                (v.rdbFemenino.isSelected()==false && v.rdbMasculino.isSelected()==false) ||
                (v.rdbSi.isSelected()==false && v.rdbNo.isSelected()==false) ||
                v.cmbCiudad.getSelectedIndex()==0){
            return false;
        }
        return true;
    }
}
