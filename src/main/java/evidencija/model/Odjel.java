/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author jbalog8
 */
@Entity
public class Odjel extends Entitet{
   
    private String naziv;
    
    @OneToMany(mappedBy = "odjel")
    private List<Zaposlenik> zaposlenici;

    public List<Zaposlenik> getZaposlenici() {
        return zaposlenici;
    }

    public void setZaposlenici(List<Zaposlenik> zaposlenici) {
        this.zaposlenici = zaposlenici;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    
    
    
    
}
