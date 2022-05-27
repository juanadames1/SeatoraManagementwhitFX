
package model;

public class Odontologo extends Person {
    //Atributos
    private String Salario;
    private int Tarjeta;
    private String Especialidad;
    
    //Constructor
    public Odontologo(int cedula, String nombre, String genero, String fechanacimiento, int Tajeta, String Especialidad, String Salario){
        super(cedula, nombre, genero, fechanacimiento);
        this.Tarjeta=0;
        this.Especialidad=Especialidad;
        this.Salario=Salario;
    }
    
    //Metodos analizadores y modificadores

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String Salario) {
        this.Salario = Salario;
    }

    public int getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(int Tarjeta) {
        this.Tarjeta = Tarjeta;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }
    
    @Override
    public String toString(){
        return this.cedula + "," + this.nombre + "," + this.genero + "," + this.fechanacimiento + "," + this.Tarjeta + "," + this.Especialidad + "," + this.Salario;
    }
    
}
