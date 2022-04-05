/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.model;

import javax.persistence.Entity;

/**
 *
 * @author jbalog8
 */
@Entity
public class VrstaRada extends Entitet{
    private String nazivRada;

    public String getNaziv() {
        return nazivRada;
    }

    public void setNaziv(String naziv) {
        this.nazivRada = naziv;
    }

    @Override
    public String toString() {
        return nazivRada;
    }
    
    
}
