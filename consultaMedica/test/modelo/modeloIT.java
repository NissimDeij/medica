/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Duoc UC
 */
public class modeloIT {
    
    public modeloIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of agregarComuna method, of class modelo.
     */
    @Test
    public void testAgregarComuna() {
        System.out.println("agregarComuna");
        int idComuna = 7;
        String nombre = "Test2";
        modelo instance = new modelo();
        boolean expResult = true;
        boolean result = instance.agregarComuna(idComuna, nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of modificarComuna method, of class modelo.
     */
    @Test
    public void testModificarComuna() {
        /*
        System.out.println("modificarComuna");
        int idComuna = 0;
        String nombre = "";
        modelo instance = new modelo();
        boolean expResult = false;
        boolean result = instance.modificarComuna(idComuna, nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of eliminarComuna method, of class modelo.
     */
    @Test
    public void testEliminarComuna() {
        /*
        System.out.println("eliminarComuna");
        int idComuna = 0;
        modelo instance = new modelo();
        boolean expResult = false;
        boolean result = instance.eliminarComuna(idComuna);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of poblarComboComunas method, of class modelo.
     */
    @Test
    public void testPoblarComboComunas() {
        /*
        System.out.println("poblarComboComunas");
        modelo instance = new modelo();
        ArrayList expResult = null;
        ArrayList result = instance.poblarComboComunas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of listarComunas method, of class modelo.
     */
    @Test
    public void testListarComunas() {
        
//        System.out.println("listarComunas");
//        modelo instance = new modelo();
//        DefaultTableModel expResult = null;
//        DefaultTableModel result = instance.listarComunas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarPaciente method, of class modelo.
     */
    @Test
    public void testAgregarPaciente() {
//        System.out.println("agregarPaciente");
//        String rut = "";
//        String nombre = "";
//        String genero = "";
//        int edad = 0;
//        String direccion = "";
//        String ciudad = "";
//        String isapre = "";
//        boolean donante = false;
//        modelo instance = new modelo();
//        boolean expResult = false;
//        boolean result = instance.agregarPaciente(rut, nombre, genero, edad, direccion, ciudad, isapre, donante);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPaciente method, of class modelo.
     */
    @Test
    public void testBuscarPaciente() {
//        System.out.println("buscarPaciente");
//        String rut = "";
//        modelo instance = new modelo();
//        DefaultTableModel expResult = null;
//        DefaultTableModel result = instance.buscarPaciente(rut);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPaciente method, of class modelo.
     */
    @Test
    public void testModificarPaciente() {
//        System.out.println("modificarPaciente");
//        String rut = "";
//        String nombre = "";
//        String genero = "";
//        int edad = 0;
//        String direccion = "";
//        String ciudad = "";
//        String isapre = "";
//        boolean donante = false;
//        modelo instance = new modelo();
//        boolean expResult = false;
//        boolean result = instance.modificarPaciente(rut, nombre, genero, edad, direccion, ciudad, isapre, donante);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarPaciente method, of class modelo.
     */
    @Test
    public void testEliminarPaciente() {
//        System.out.println("eliminarPaciente");
//        int rut = 0;
//        modelo instance = new modelo();
//        boolean expResult = false;
//        boolean result = instance.eliminarPaciente(rut);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of rutExiste method, of class modelo.
     */
    @Test
    public void testRutExiste() {
//        System.out.println("rutExiste");
//        String rut = "";
//        modelo instance = new modelo();
//        boolean expResult = false;
//        boolean result = instance.rutExiste(rut);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of comunaExiste method, of class modelo.
     */
    @Test
    public void testComunaExiste() {
//        System.out.println("comunaExiste");
//        int idComuna = 0;
//        modelo instance = new modelo();
//        boolean expResult = false;
//        boolean result = instance.comunaExiste(idComuna);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
