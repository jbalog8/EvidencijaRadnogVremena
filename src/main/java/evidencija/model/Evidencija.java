/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author jbalog8
 */
@Entity
public class Evidencija extends Entitet{
        private Date datum;
	private boolean prijava;
        @ManyToOne
	private Zaposlenik zaposlenik;
        @ManyToOne
	private VrstaRada vrstaRada;

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isPrijava() {
        return prijava;
    }

    public void setPrijava(boolean prijava) {
        this.prijava = prijava;
    }

    public Zaposlenik getZaposlenik() {
        return zaposlenik;
    }

    public void setZaposlenik(Zaposlenik zaposlenik) {
        this.zaposlenik = zaposlenik;
    }

    public VrstaRada getVrstaRada() {
        return vrstaRada;
    }

    public void setVrstaRada(VrstaRada vrstaRada) {
        this.vrstaRada = vrstaRada;
    }
        
    
}
