
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import modelo.modelo;
import vista.vistaPrincipal;
import vista.vistaAgregar;
import vista.vistaListar;
import vista.vistaComunas;

public class controlador implements ActionListener, MouseListener, FocusListener {
    
    //vistas
    private vistaPrincipal vista1;
    private vistaAgregar vista2 = new vistaAgregar();
    private vistaListar vista3 = new vistaListar();
    private vistaComunas vista4 = new vistaComunas();
    
    //modelo
    private modelo modelo = new modelo();

    //enum
    public enum accion {
        //menu-items
        mitAgregar, //agregar paciente
        mitListar, //listar nomina
        mitSalir, //salir
        mitListarComuna, // Lista comunas
        //botones
        btnBuscar, //buscar un paciente por codigo
        btnGuardar, //guardar cambios (al editar paciente)
        btnEliminar, //eliminar paciente
        btnModificar, //modifica campos 
        btnLimpiar, //limpiar campos (para agregar o editar paciente)
        btnSalir, //vuelve a la ventana principal
        btnAgregarComuna,
        btnEditarComuna,
        btnEliminarComuna,
        //JTextFields
        txtRut, // campo de ingreso para el rut del paciente
        txtNombre, // campo de ingreso para el nombre del paciente
        txtEdad, // campo de ingreso para la edad del paciente
        txtDireccion, // campo de ingreso para el sueldo del paciente
        txtBuscarRut, // campo de ingreso para buscar pacientes por rut       
        txtIdComuna
    };
    //constructor de clase
    public controlador (JFrame padre){
        this.vista1 = (vistaPrincipal) padre;
    }
    //inicia todas las acciones y listeners de las vistas
    public void iniciar(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this.vista1);
            SwingUtilities.updateComponentTreeUI(this.vista2);
            SwingUtilities.updateComponentTreeUI(this.vista3);
            SwingUtilities.updateComponentTreeUI(this.vista4);
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
        this.vista1.mitSalir.setActionCommand("mitSalir");
        this.vista1.mitSalir.addActionListener(this);
        //item agregar
        this.vista1.mitAgregar.setActionCommand("mitAgregar");
        this.vista1.mitAgregar.addActionListener(this);
        //item listar
        this.vista1.mitListar.setActionCommand("mitListar");
        this.vista1.mitListar.addActionListener(this);
        //item ListarComuna
        this.vista1.mitListarComuna.setActionCommand("mitListarComuna");
        this.vista1.mitListarComuna.addActionListener(this);

        //boton Guardar
        this.vista2.btnGuardar.setActionCommand("btnGuardar");
        this.vista2.btnGuardar.addActionListener(this);
        //boton Limpiar
        this.vista2.btnLimpiar.setActionCommand("btnLimpiar");
        this.vista2.btnLimpiar.addActionListener(this);
        //boton Salir
        this.vista2.btnSalir.setActionCommand("btnSalir");
        this.vista2.btnSalir.addActionListener(this);
        //boton Buscar
        this.vista3.btnBuscar.setActionCommand("btnBuscar");
        this.vista3.btnBuscar.addActionListener(this);
        //boton Modificar
        this.vista3.btnModificar.setActionCommand("btnModificar");
        this.vista3.btnModificar.addActionListener(this);
        //boton Eliminar
        this.vista3.btnEliminar.setActionCommand("btnEliminar");
        this.vista3.btnEliminar.addActionListener(this);
        //boton AgregarComuna
        this.vista4.btnAgregarComuna.setActionCommand("btnAgregarComuna");
        this.vista4.btnAgregarComuna.addActionListener(this);
        //boton EditarComuna
        this.vista4.btnEditarComuna.setActionCommand("btnEditarComuna");
        this.vista4.btnEditarComuna.addActionListener(this);
        //boton EliminarComuna
        this.vista4.btnEliminarComuna.setActionCommand("btnEliminarComuna");
        this.vista4.btnEliminarComuna.addActionListener(this);

        //agregamos focus listeners para los campos del formulario ingresar
        this.vista2.txtRut.addFocusListener(this);
        this.vista2.txtNombre.addFocusListener(this);
        this.vista2.txtEdad.addFocusListener(this);
        this.vista2.txtDireccion.addFocusListener(this);
        this.vista4.txtIdComuna.addFocusListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {       
        switch (accion.valueOf(e.getActionCommand())) {
            case mitSalir:
                System.exit(0);
                break;
                
            case mitAgregar:
                this.vista2.setLocationRelativeTo(null);
                this.vista2.setTitle("Agregar Paciente");
                this.vista2.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                this.vista2.cmbCiudad.setModel(new DefaultComboBoxModel(this.modelo.poblarComboComunas().toArray()));
                this.vista2.setVisible(true);
                break;
                
            case mitListar:
                this.vista3.setLocationRelativeTo(null);
                this.vista3.setTitle("Listar Pacientes");
                this.vista3.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                this.vista3.tblLista.setModel(this.modelo.buscarPaciente("")); //actualiza JTable
                this.vista3.setVisible(true);
                break;
                
            case mitListarComuna:
                this.vista4.setLocationRelativeTo(null);
                this.vista4.setTitle("Comunas");
                this.vista4.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                this.vista4.tblListarComuna.setModel(this.modelo.listarComunas()); //actualiza JTable
                this.vista4.setVisible(true);
                break;
                
            case btnGuardar:
                //Enviamos datos del formulario Agregar Paciente a metodo agregarPaciente
                String gen="";
                String isap="";
                if(this.vista2.rdbFemenino.isSelected()==true){
                    gen = "F";
                }else if(this.vista2.rdbMasculino.isSelected()==true){
                    gen = "M";
                }
                if(this.vista2.rdbSi.isSelected()==true){
                    isap = "S";
                }else if(this.vista2.rdbNo.isSelected()==true){
                    isap = "N";
                }
                
                if(ValidacionFinal(vista2) == true){
                    if (this.modelo.agregarPaciente(
                            this.vista2.txtRut.getText(),
                            this.vista2.txtNombre.getText(),
                            gen,
                            Integer.parseInt(this.vista2.txtEdad.getText()),
                            this.vista2.txtDireccion.getText(),
                            this.vista2.cmbCiudad.getSelectedIndex(),
                            isap,
                            this.vista2.chkDonante.isSelected()
                    )) {
                        JOptionPane.showMessageDialog(null, "Paciente agregado correctamente");
                        //Limpiamos textField
                        LimpiarForm(vista2);
                        vista2.txtRut.requestFocus();
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
                
            case btnBuscar:
                this.vista3.tblLista.setModel(this.modelo.buscarPaciente(this.vista3.txtBuscarRut.getText())); //actualiza JTable
                break;  
            
            case btnEliminar:
                if (this.modelo.eliminarPaciente(
                       this.vista3.tblLista.getValueAt(this.vista3.tblLista.getSelectedRow(), 0).toString()
                        )){
                    JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar trabajador");
                }
                break;
            case btnModificar:
                break;
            
            case btnAgregarComuna:
                if(!this.vista4.txtIdComuna.getText().equals("") && !this.vista4.txtNombreComuna.getText().equals("")){
                    if(this.modelo.comunaExiste(Integer.parseInt(this.vista4.txtIdComuna.getText()))==false){
                        if(this.modelo.agregarComuna(
                                Integer.parseInt(this.vista4.txtIdComuna.getText()),
                                this.vista4.txtNombreComuna.getText())){
                            this.LimpiarFormComuna(vista4);
                            this.vista4.tblListarComuna.setModel(this.modelo.listarComunas()); //actualiza JTable
                            JOptionPane.showMessageDialog(null, "Comuna agregada exitosamente");
                        }else{
                            JOptionPane.showMessageDialog(null, "No fue posible agregar la comuna");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "La comuna ya existe");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Por favor complete todos los campos");
                }    
                break;
            
            case btnEditarComuna:
                if(!this.vista4.txtIdComuna.getText().equals("") && 
                        this.modelo.comunaExiste(Integer.parseInt(this.vista4.txtIdComuna.getText())) && 
                        !this.vista4.txtNombreComuna.getText().equals("")){
                    if(this.modelo.modificarComuna(
                            Integer.parseInt(this.vista4.txtIdComuna.getText()),
                            this.vista4.txtNombreComuna.getText())){
                        this.LimpiarFormComuna(vista4);
                        this.vista4.tblListarComuna.setModel(this.modelo.listarComunas()); //actualiza JTable
                        JOptionPane.showMessageDialog(null, "Comuna modificada exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "No fue posible modificar la comuna");
                    }
                }
                break;
                
            case btnEliminarComuna:
                if(!this.vista4.txtIdComuna.getText().equals("") && this.modelo.comunaExiste(Integer.parseInt(this.vista4.txtIdComuna.getText()))){
                    if(this.modelo.eliminarComuna(
                            Integer.parseInt(this.vista4.txtIdComuna.getText()))){
                        this.LimpiarFormComuna(vista4);
                        this.vista4.tblListarComuna.setModel(this.modelo.listarComunas()); //actualiza JTable
                        JOptionPane.showMessageDialog(null, "Comuna eliminada exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(null, "No fue posible eliminar la comuna");
                    }
                }
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
    public void mouseClicked(MouseEvent e) {
        
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
    public void focusLost(FocusEvent fe) {
        // Validación instantanea al salir del campo rut
        if(fe.getSource()==this.vista2.txtRut){
            if(!this.vista2.txtRut.getText().equals("")){
                if(!validacion.validarRut(this.vista2.txtRut.getText())){
                    JOptionPane.showMessageDialog(null, "El rut ingresado no es válido.\nPor favor ingrese el rut sin puntos ni dígito verificador.");
                    this.vista2.txtRut.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo rut en buscar rut
        else if(fe.getSource()==this.vista3.txtBuscarRut){
            if(!this.vista3.txtBuscarRut.getText().equals("")){
                if(!validacion.validarRut(this.vista3.txtBuscarRut.getText())){
                    JOptionPane.showMessageDialog(null, "El rut ingresado no es válido.\nPor favor ingrese el rut sin puntos ni dígito verificador.");
                    this.vista3.txtBuscarRut.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo nombre
        else if(fe.getSource()==this.vista2.txtNombre){
            if(!this.vista2.txtNombre.getText().equals("")){
                if(!validacion.validarLargoString(this.vista2.txtNombre.getText())){
                    JOptionPane.showMessageDialog(null, "El nombre ingresado excede el número máximo de caracteres permitido.\nPor favor ingrese un nombre de no más de 60 caracteres.");
                    this.vista2.txtNombre.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo edad
        else if(fe.getSource()==this.vista2.txtEdad){
            if(!this.vista2.txtEdad.getText().equals("")){
                if(!validacion.esNum(this.vista2.txtEdad.getText())){
                    JOptionPane.showMessageDialog(null, "La edad ingresada no es válida.\nPor favor ingrese solo valores numéricos.");
                    this.vista2.txtEdad.requestFocus();
                }
            }    
        }
        // Validación instantanea al salir del campo dirección
        else if(fe.getSource()==this.vista2.txtDireccion){
            if(!this.vista2.txtDireccion.getText().equals("")){
                if(!validacion.validarLargoString(this.vista2.txtDireccion.getText())){
                    JOptionPane.showMessageDialog(null, "La dirección ingresada excede el número máximo de caracteres permitido.\nPor favor ingrese una dirección de no más de 60 caracteres.");
                    this.vista2.txtDireccion.requestFocus();
                }
            }    
        }
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
    
    private void LimpiarFormComuna(vistaComunas vista){
        vista.txtIdComuna.setText("");
        vista.txtNombreComuna.setText("");
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
