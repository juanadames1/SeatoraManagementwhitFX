package managment;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import model.Patient;

public class SeatoraManagment {

    private final String ruta;

// GESTION PACIENTE
    public SeatoraManagment() {
            this.ruta = "./src/files/myPatients.txt";
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

    public void nuevoPaciente() {
        String nom, fechanac,fechait, fechaft = null, tipot, nombreodon, x;
        String gen0, files=null;
        int cod0;

        
        cod0 = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente"));

        nom = JOptionPane.showInputDialog("Digite el nombre completo del paciente");

        gen0 = JOptionPane.showInputDialog("Digite el genero del paciente");
        
        
        fechanac= JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente de la forma DD/MM/AAAA");
        
        fechait= JOptionPane.showInputDialog("Ingrese la fecha de inicio del tratamiento del paciente de la forma DD/MM/AAAA");
        
        tipot= JOptionPane.showInputDialog("Ingrese el tipo de tratamiento");  
      
        fechaft= JOptionPane.showInputDialog("Ingrese la fecha de la finalización del tratamiento del paciente de la forma DD/MM/AAAA");
        
        nombreodon= JOptionPane.showInputDialog("Ingrese el nombre del odontólogo que está tratando al paciente");
        
        Patient pacient = new Patient(cod0, nom, gen0, fechanac, fechait, tipot, fechaft, nombreodon);

        this.guardarPacientes(files);

    }


    public void guardarPacientes(String files){
        try{
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fr);
                pw.println(files);
            pw.close();
            JOptionPane.showMessageDialog(null, "El paciente ha sido guardado en el archivo");
        }catch (IOException cosito){
            JOptionPane.showMessageDialog(null, "No se pudo guardar el paciente");
        }
    }

    public void verPaciente(){
        String cedula;
        cedula = JOptionPane.showInputDialog("Digite la cédula a buscar:");

        Patient pat = this.buscarPaciente(cedula);

        if(pat != null){
            System.out.println(pat);
        }else{
            JOptionPane.showMessageDialog(null, "Ese cédula no existe...");
        }
    }

    private Patient buscarPaciente(String cedula){
        FileReader file;
        BufferedReader br;
        String registro;
        Patient pat = null;

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                String[] campos = registro.split(", ");
                if(campos[0].equals(cedula)){
                    pat = new Patient(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5], campos [6], campos[7]);
                    break;
                }
            }
        }catch (IOException ex){
            System.out.println("FallO buscando pacientee");
        }
        return pat;
    }

    public boolean hayPacientes(){
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
            System.out.println("FallO buscando paciente");
        }
        return band;
    }

    public ArrayList<Patient> getPacientes(){
        FileReader file;
        BufferedReader br;
        String registro;
        Patient pat = null;
        ArrayList<Patient> pacients = new ArrayList();

        try{
            file = new FileReader(this.ruta);
            br = new BufferedReader(file);
            while((registro = br.readLine()) != null){
                String[] campos = registro.split(",");

                pat = new Patient(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5], campos [6], campos[7]);

                pacients.add(pat);

            }
        }catch (IOException ex){
            System.out.println("FallO buscando paciente");
        }
        return pacients;
    }

    public void verTodos() {

        ArrayList<Patient> pacients = this.getPacientes();


        for (Patient pat : pacients) {
            System.out.println(pat.toString() + "\n");
        }
    }



    public void eliminarPatients() {
        int cedula;

        ArrayList<Patient> pacient = this.getPacientes();
        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a eliminar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()== cedula){
                pacient.remove(pat);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Patient eliminado");
                break;
            }
        }

    }




    public void modificarCodigo() {
        int cedula, newDato0;
        boolean existe = false;
        ArrayList<Patient> pacient = this.getPacientes();

        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()== cedula) {
//
                newDato0 = Integer.parseInt(JOptionPane.showInputDialog("Digite la nueva cédula del paciente"));
//
                pat.setCedula(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Cédula modificada");
                existe = false;
                break;
            }
        }

    }

    private void reemplazarArchivo(ArrayList<Patient> pacients){
        try{
            File file = new File(this.ruta);
            FileWriter fr = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fr);
            for(Patient pacient:pacients)
                pw.println(pacient);
            pw.close();

        }catch (IOException ex){
            JOptionPane.showMessageDialog(null, "No se pudo modificar el paciente");
        }
    }



    public void modificarNombre() {
        int cedula;
        String newDato0;
        ArrayList<Patient> pacient = this.getPacientes();

        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()== cedula) {
//
                newDato0 = JOptionPane.showInputDialog("Digite el nuevo nombre del paciente");
//
                pat.setNombre(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Nombre modificado");
                break;
            }
        }
    }



    public void modificarGenero() {
        int cedula;
        String newDato;
        ArrayList<Patient> pacient = this.getPacientes();
        cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula() == cedula) {
                newDato = JOptionPane.showInputDialog("Digite el nuevo génereo del paciente");
                pat.setGenero(newDato);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Género modificado");
            }
        }
    }
    
    public void modificarFechadeNacimiento(){
        String newDato0;
        int cedula;
        ArrayList<Patient> pacient = this.getPacientes();
        cedula= Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()== cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite la nueva fecha de nacimiento de la forma DD/MM/AAAA");
                
                pat.setFechanacimiento(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Fecha de nacimiento modificada");
                break;
            }
        }
        
    }
    
    public void modificarFechaInicio(){
        String newDato0;
        int cedula;
        ArrayList<Patient> pacient = this.getPacientes();
        cedula=Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()==cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite la nueva fecha de inicio del tratamiento de la forma DD/MM/AAAA");
                
                pat.setFechaitratamiento(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Fecha de inicio del tratamiento modificada");
                break;
            }
        }
        
    }
    public void modificarFechaFinal(){
        String newDato0;
        int cedula;
        ArrayList<Patient> pacient = this.getPacientes();
        cedula=Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()==cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite la nueva fecha de fin del tratamiento de la forma DD/MM/AAAA");
                
                pat.setFechaftratamiento(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Fecha de fin del tratamiento modificada");
                break;
            }
        }
        
        
    }
    public void modificarTipoTratamiento(){
        String newDato0;
        int cedula;
        ArrayList<Patient> pacient = this.getPacientes();
        cedula=Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()==cedula) {
                newDato0 = JOptionPane.showInputDialog("Ingrese el nuevo tratamiento del paciente");
                
                pat.setTipotratamiento(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Nuevo tipo de tratamiento asignado");
                break;
            }
        }
        
    }
    public void modificarOdontologo(){
        String newDato0;
        int cedula;
        ArrayList<Patient> pacient = this.getPacientes();
        cedula=Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del paciente a modificar"));
        for (Patient pat : pacient) {
            if (pat.getCedula()==cedula) {
                newDato0 = JOptionPane.showInputDialog("Digite el nombre del nuevo Odontólogo");
                
                pat.setOdontologo(newDato0);
                this.reemplazarArchivo(pacient);
                JOptionPane.showMessageDialog(null, "Nuevo Odontólogo Asignado");
                break;
            }
        }
    }
}

