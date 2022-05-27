
package model;

public class Employee extends Person {
    //Atributos propios de la clase
    protected String Salario;
    protected String Cargo;
  
 //Constructor 
 public Employee (int cedula, String nombre,String genero, String fechanacimiento, String Cargo, String Salario){
     super(cedula,nombre,genero,fechanacimiento);
     this.Cargo= Cargo;
     this.Salario= Salario; 
 }
 
 //Métodos Analizadores y Modificadores

 // Salario
    public String getSalario() {
        return Salario;
    }

    public void setSalario (String Salario) {
        this.Salario = Salario;
    }

    //Cargo
    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }
    
    //Sobreescritura
    @Override
    public String toString(){
        return this.cedula + "," + this.nombre + "," + this.genero + "," + this.fechanacimiento + ", "+ this.Cargo+ ", " + this.Salario;
    }
 
}