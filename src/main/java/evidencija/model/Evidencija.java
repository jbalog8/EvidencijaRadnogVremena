/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Column;

/**
 *
 * @author jbalog8
 */
@Entity
public class Evidencija extends Entitet {

    @Column(nullable = false)
    private Date datumPocetak;
    private Date datumKraj;

    @ManyToOne
    private Zaposlenik zaposlenik;

    @ManyToOne
    private VrstaRada vrstaRada;

    public Date getDatumPocetak() {
        return datumPocetak;
    }

    public void setDatumPocetak(Date datumPocetak) {
        this.datumPocetak = datumPocetak;
    }

    public Date getDatumKraj() {
        return datumKraj;
    }

    public void setDatumKraj(Date datumKraj) {
        this.datumKraj = datumKraj;
    }

   

    public VrstaRada getVrstaRada() {
        return vrstaRada;
    }

    public void setVrstaRada(VrstaRada vrstaRada) {
        this.vrstaRada = vrstaRada;
    }

    public Zaposlenik getZaposlenik() {
        return zaposlenik;
    }

    public void setZaposlenik(Zaposlenik zaposlenik) {
        this.zaposlenik = zaposlenik;
    }

   
    

}
