
package managment;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Odontologo;

public class GestionOdontologo {

    private final String ruta;

    public GestionOdontologo() {
        this.ruta = "./src/files/misOdontologos.txt";
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

    public void nuevoOdontologo() {
        String nom, fechanac, espc ,salari;
        String gen0, files=null;
        int tarj, cod0;

        
        cod0 = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del Odontólogo"));

        nom = JOptionPane.showInputDialog("Digite el nombre completo del Odontólogo");

        gen0 = JOptionPane.showInputDialog("Digite el genero del Odontólogo");
        
        fechanac= JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del Odontólogo de la forma DD/MM/AAAA");
        
        tarj = Integer.parseInt(JOptionPane.showInputDialog("Digite el número de tarjeta profesional del Odontólogo"));
        
        espc= JOptionPane.showInputDialog("Ingrese la especialidad del Odóntologo");
      
        salari = JOptionPane.showInputDialog("Ingrese el salario mensual del empleado");
        
        Odontologo odonto = new Odontologo(cod0, nom, gen0, fechanac, tarj, espc, salari);

        this.guardarOdontologo(files);

    }


    public void guardarOdontologo(String files){
        try{
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
                pw.println(files);
            pw.close();
            JOptionPane.showMessageDialog(null, "El Odontólogo ha sido guardado en el archivo");
        }catch (IOException cosito){
            JOptionPane.showMessageDialog(null, "No se pudo guardar el Odontólogo");
        }
    }

    public void verOdontologos(){
        String cedula;
        cedula = JOptionPane.showInputDialog("Digite la cédula a buscar:");

        Odontologo odo = this.buscarOdontologos(cedula);

        if(odo != null){
            System.out.println(odo);
        }else{
            JOptionPane.showMessageDialog(null, "Ese cédula no existe");
        }
    }

    private Odontologo buscarOdontologos(String cedula){
        FileReader file;
        BufferedReader br;
        String registro;
        Odontologo odo = null;

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                String[] campos = registro.split(", ");
                if(campos[0].equals(cedula)){
                    odo = new Odontologo(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], Integer.parseInt(campos[4]), campos[5], campos[6]);
                    break;
                }
            }
        }catch (IOException ex){
            System.out.println("FallO buscando Odontólogo");
        }
        return odo;
    }

    public boolean hayOdontologos(){
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
            System.out.println("FallO buscando Odontólogo");
        }
        return band;
    }

    public ArrayList<Odontologo> getOdontologos(){
        FileReader file;
        BufferedReader br;
        String registro;
        Odontologo odo;
        ArrayList<Odontologo> odontos = new ArrayList();

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                String[] campos = registro.split(",");

                odo = new Odontologo(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], Integer.parseInt(campos[4]), campos[5], campos[6]);

                odontos.add(odo);
            }
        }catch (IOException ex){
            System.out.println("FallO buscando Odontólogo");
        }
        return odontos;
    }

    public void verTodos() {

        ArrayList<Odontologo> odontos = this.getOdontologos();


        for (Odontologo odo : odontos) {
            System.out.println(odo.toString() + "\n");
        }
    }
 
    public void eliminarOdontologos() {
        int cedula;

        ArrayList<Odontologo> odonto = this.getOdontologos();
        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del Odontólogo a eliminar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                odonto.remove(odo);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Odontólogo eliminado");
                break;
            }
        }

    }


    public void modificarCodigo() {
        int newDato0;
        int cedula;
        boolean existe = false;
        ArrayList<Odontologo> odonto = this.getOdontologos();

        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
//
                newDato0 = Integer.parseInt(JOptionPane.showInputDialog("Digite la nueva cédula del Odontólogo"));
//
                odo.setCedula(newDato0);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Cédula modificada");
                existe = false;
                break;
            }
        }

    }

    private void reemplazarArchivo(ArrayList<Odontologo> odontos){
        try{
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fr);
            for(Odontologo odonto:odontos)
                pw.println(odonto);
            pw.close();

        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar el Odontólogo");
        }
    }



    public void modificarNombre() {
        String newDato0;
        int cedula;
        ArrayList<Odontologo> odonto = this.getOdontologos();

        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula del empleado a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo nombre del empleado");
                odo.setNombre(newDato0);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Nombre modificado");
                break;
            }
        }
    }



    public void modificarGenero() {
        int cedula;
        String newDato;
        ArrayList<Odontologo> odonto = this.getOdontologos();
        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del empleado a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                newDato = JOptionPane.showInputDialog("Digite el nuevo génereo del Odontólogo");
                odo.setGenero(newDato);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Género modificado");
            }
        }
    }
    
    public void modificarFechadeNacimiento(){
        String  newDato0;
        int cedula;
        ArrayList<Odontologo> odonto = this.getOdontologos();
        cedula= Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del Odontólogo a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite la nueva fecha de nacimiento de la forma DD/MM/AAAA");
                
                odo.setFechanacimiento(newDato0);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Fecha de nacimiento modificada");
                break;
            }
        }
        
    }
    
    public void modificarSalario(){
         String newDato0;
         int cedula;
       ArrayList<Odontologo> odonto = this.getOdontologos();
        cedula= Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del Odontólogo a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo salario mensual del Odontólogo");
                
                odo.setSalario(newDato0);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Nuevo salario asignado");
                break;
            }
        }
        
    }
    
    public void modificarEspecialidad(){
        String newDato0;
        int cedula;
       ArrayList<Odontologo> odonto = this.getOdontologos();
        cedula= Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del Odontólogo a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite la nueva especialidad del Odontólogo");
                
                odo.setEspecialidad(newDato0);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Nueva especialidad asignada");
                break;
            }
        }
    }
    
    public void modificarTarjeta(){
        String newDato0;
        int cedula;
       ArrayList<Odontologo> odonto = this.getOdontologos();
        cedula= Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del Odontólogo a modificar"));
        for (Odontologo odo : odonto) {
            if (odo.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo número de tarjeta profesional del Odontólogo");
                
                odo.setEspecialidad(newDato0);
                this.reemplazarArchivo(odonto);
                JOptionPane.showMessageDialog(null, "Tarjeta modificada");
                break;
            }
        }
    }
}

