/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.Zaposlenik;
import evidencija.util.EvidencijaException;
import java.util.List;

/**
 *
 * @author jbalog8
 */
public class ObradaZaposlenik extends ObradaOsoba<Zaposlenik>{

    @Override
    public List<Zaposlenik> read() {
        return session.createQuery("from Zaposlenik").list();
    }

    @Override
    protected void kontrolaCreate() throws EvidencijaException {
        super.kontrolaCreate();
        kontrolaBrKartice();
       
    }

    private void kontrolaBrKartice() throws EvidencijaException{
        if(entitet.getBrKartice() == null && entitet.getBrKartice().length() < 13){
            throw new EvidencijaException("Broj kartice ne moze biti 0 i ne moze biti manje od 13 znamaneki");
        }
        List<Zaposlenik> lista = session.createQuery("from Zaposlenik z " + "where z.brKartice=:brKartice")
                .setParameter("brKartice", entitet.getBrKartice()).list();
        
        if(lista!= null && lista.size()>0){
            throw new EvidencijaException("Broj kartice je već u sustavu, dodjeljen " + lista.get(0).getPrezime());
        }
    }

    @Override
    protected void kontrolaDelete() throws EvidencijaException {
        super.kontrolaDelete();
        /*
        if(entitet.getOdjel()!= null){
            throw new EvidencijaException("Zaposlenik se nalazi na jednom odjelu");
        }
*/
        
    }
    
    
    
    
    
    
}
