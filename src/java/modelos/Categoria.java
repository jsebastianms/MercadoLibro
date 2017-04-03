/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author camilo
 */
public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private String influencia;

    public Categoria(int id, String nombre, String descripcion, String influencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.influencia = influencia;

    }

    public Categoria(String nombre, String descripcion, String influencia) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.influencia = influencia;
    }

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInfluencia() {
        return influencia;
    }

    public void setRecomendado(String influencia) {
        this.influencia = influencia;
    }

}
