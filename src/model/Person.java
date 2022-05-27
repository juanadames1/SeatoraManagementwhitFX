/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author juanc
 */
public class Person {
    //atributos
    protected int cedula;
    protected String nombre;
    protected String genero;
    protected String fechanacimiento;
    
    //Constructores
    public Person() {
        this.cedula = 0;
        this.nombre = "";
        this.genero = "";
        this.fechanacimiento="";
    }
    
    public Person(int cedula, String nombre, String genero, String fechanacimiento){
        this.cedula = cedula;
        this.nombre = nombre;
        this.genero = genero;
        this.fechanacimiento = fechanacimiento;
    }

    //Métodos analizadores y modificadores

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    
    @Override
    public String toString() {
        return this.cedula + "," + this.nombre + "," + this.genero + "," + this.fechanacimiento;  
    }
}
