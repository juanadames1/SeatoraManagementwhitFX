package managment;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeManagment {

    private final String ruta;

// GESTION PACIENTE
    public EmployeeManagment() {
        this.ruta = "./src/files/myEmployee.txt";
        this.verificArchivo();
    }

    private void verificArchivo() {

        try{
            File filex = new File(this.ruta);
            if(!filex.exists())
                filex.createNewFile();

        }
        catch(IOException ex){
            System.out.println("Problemas con la ruta");
        }
    }

    public void nuevoEmpleado() {
        String nom, fechanac, car = null, salari;
        String gen0, files=null;
        int cod0;

        
        cod0 = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado"));

        nom = JOptionPane.showInputDialog("Digite el nombre completo del empleado");

        gen0 = JOptionPane.showInputDialog("Digite el genero del empleado");
        
        fechanac= JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del empleado de la forma DD/MM/AAAA");
        
        car=JOptionPane.showInputDialog("Ingrese el cargo del empleado");
      
        salari = JOptionPane.showInputDialog("Ingrese el salario mensual del empleado");
        
       Employee emplead = new Employee(cod0, nom, gen0, fechanac, car, salari);

        this.guardarEmepleados(files);

    }


    public void guardarEmepleados(String files){
        try{
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
                pw.println(files);
            pw.close();
            JOptionPane.showMessageDialog(null, "El empleado ha sido guardado en el archivo");
        }catch (IOException cosito){
            JOptionPane.showMessageDialog(null, "No se pudo guardar el empleado");
        }
    }

    public void verEmpleados(){
        String cedula;
        cedula = JOptionPane.showInputDialog("Digite la cédula a buscar:");

       Employee emp = this.buscarEmpleados(cedula);

        if(emp != null){
            System.out.println(emp);
        }else{
            JOptionPane.showMessageDialog(null, "Ese cédula no existe");
        }
    }

    private Employee buscarEmpleados(String cedula){
        FileReader file;
        BufferedReader br;
        String registro;
       Employee emp = null;

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                String[] campos = registro.split(", ");
                if(campos[0].equals(cedula)){
                    emp = new Employee(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5]);
                    break;
                }
            }
        }catch (IOException ex){
            System.out.println("FallO buscando empleado");
        }
        return emp;
    }

    public boolean hayEmpleados(){
        FileReader file;
        BufferedReader br;
        String registro;
        boolean band = false;

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                band = true;
                break;
            }

        }catch (IOException ex){
            System.out.println("FallO buscando empleado");
        }
        return band;
    }

    public ArrayList<Employee> getEmpleados(){
        FileReader file;
        BufferedReader br;
        String registro;
       Employee emp;
        ArrayList<Employee> empleads = new ArrayList();

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                String[] campos = registro.split(",");

                emp = new Employee(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5]);

                empleads.add(emp);
            }
        }catch (IOException ex){
            System.out.println("FallO buscando empleado");
        }
        return empleads;
    }

    public void verTodos() {

        ArrayList<Employee> empleads = this.getEmpleados();


        for (Employee emp : empleads) {
            System.out.println(emp.toString() + "\n");
        }
    }
 
    public void eliminarEmpleados() {
        int cedula;

        ArrayList<Employee> emplead = this.getEmpleados();
        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado a eliminar"));
        for (Employee emp : emplead) {
            if (emp.getCedula()== cedula) {
                emplead.remove(emp);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Empleado eliminado");
                break;
            }
        }

    }




    public void modificarCodigo() {
        int cedula, newDato0;
        boolean existe = false;
        ArrayList<Employee> emplead = this.getEmpleados();

        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula a modificar"));
        for (Employee emp : emplead) {
            if (emp.getCedula() == cedula) {
//
                newDato0 = Integer.parseInt(JOptionPane.showInputDialog("Digite la nueva cédula del empleado"));
//
                emp.setCedula(newDato0);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Cédula modificada");
                existe = false;
                break;
            }
        }

    }

    private void reemplazarArchivo(ArrayList<Employee> empleads){
        try{
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fr);
            for(Employee emplead:empleads)
                pw.println(emplead);
            pw.close();

        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar el empleado");
        }
    }



    public void modificarNombre() {
        String newDato0;
        int cedula;
        ArrayList<Employee> emplead = this.getEmpleados();

        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula del empleado a modificar"));
        for (Employee emp : emplead) {
            if (emp.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo nombre del empleado");
                emp.setNombre(newDato0);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Nombre modificado");
                break;
            }
        }
    }



    public void modificarGenero() {
        int cedula;
        String newDato;
        ArrayList<Employee> emplead = this.getEmpleados();
        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado a modificar"));
        for (Employee emp : emplead) {
            if (emp.getCedula()== cedula) {
                newDato = JOptionPane.showInputDialog("Digite el nuevo génereo del empleado");
                emp.setGenero(newDato);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Género modificado");
            }
        }
    }
    public void modificarFechadeNacimiento(){
        String newDato0;
        int cedula;
        ArrayList<Employee> emplead = this.getEmpleados();
        cedula= Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado a modificar"));
        for (Employee emp : emplead) {
            if (emp.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite la nueva fecha de nacimiento de la forma DD/MM/AAAA");
                
                emp.setFechanacimiento(newDato0);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Fecha de nacimiento modificada");
                break;
            }
        }
        
    }
    public void modificarSalario(){
         String newDato0;
         int cedula;
        ArrayList<Employee> emplead = this.getEmpleados();
        cedula=Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado a modificar"));
        for (Employee emp : emplead) {
            if (emp.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo salario mensual del empleado");
                
                emp.setSalario(newDato0);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Nuevo salario asignado");
                break;
            }
        }
        
    }
    public void modificarCargo(){
        String newDato0;
        int cedula;
        ArrayList<Employee> emplead = this.getEmpleados();
        cedula=Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado a modificar"));
        for (Employee emp : emplead) {
            if (emp.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo cargo del empleado");
                
                emp.setCargo(newDato0);
                this.reemplazarArchivo(emplead);
                JOptionPane.showMessageDialog(null, "Cargo del empleado modificado");
                break;
            }
        }
        
    }
}

