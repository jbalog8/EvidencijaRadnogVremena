/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author jbalog8
 */
@Entity
public class Zaposlenik extends Osoba{
    private String brKartice;
    @ManyToOne
    private Odjel odjel;

    public String getBrKartice() {
        return brKartice;
    }

    public void setBrKartice(String brKartice) {
        this.brKartice = brKartice;
    }

    public Odjel getOdjel() {
        return odjel;
    }

    public void setOdjel(Odjel odjel) {
        this.odjel = odjel;
    }
    
    
    
        
        
    
}