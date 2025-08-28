package org.prog1.entities;

import java.util.Objects;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private int dni;
    private String correo;
    private String localidad;


    public Cliente(){

    }

    public Cliente(int idCliente, String nombre, String apellido,int dni, String correo,String localidad){
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
        this.localidad = localidad;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", correo='" + correo + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }



}
