/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.Evidencija;
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
    public List<Zaposlenik> read(String uvjet) {
        return session.createQuery("from Zaposlenik z " 
                + "where concat(z.ime,' ', z.prezime)"
                + "like : uvjet order by z.prezime, z.ime")
                .setParameter("uvjet", "%" + uvjet + "%")
                .setMaxResults(50)
                .list();
    }
    
    
    
    public List<Zaposlenik> readPocetakPrezime(String uvjet) {
        return session.createQuery("from Zaposlenik z " 
                + "where concat(z.prezime)"
                + "like : uvjet order by z.prezime, z.ime")
                .setParameter("uvjet", "%" + uvjet + "%")
                .setMaxResults(50)
                .list();

    }
    
    public List<Evidencija> getEvidencije(Zaposlenik zaposlenik){
        try {
            return session.createQuery("from Evidencija where zaposlenik=:zaposlenik "
                    + "and datumKraj is null order by datumKraj desc")
                    .setParameter("zaposlenik", zaposlenik).list();
           
        } catch (Exception e) {
            return null;
        }
    }
    
    public Zaposlenik getZaposlenik(String brKartice){
        try {
            return (Zaposlenik) session.createQuery("from Zaposlenik where brKartice=:brKartice")
                    .setParameter("brKartice", brKartice).getSingleResult();
            
        } catch (Exception e) {
            return null;
        }
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
    
    protected void kontrolaUpdate() throws EvidencijaException{
        super.kontrolaUpdate();
        List<Zaposlenik> lista = session.createQuery("from Zaposlenik z " + "where z.aktivan=:aktivan").
                setParameter("aktivan", entitet.getAktivan()).list();
        
    }
    
     public Zaposlenik getZaposlenik(Boolean aktivan){
        try {
            return (Zaposlenik) session.createQuery("from Zaposlenik where aktivan=:aktivan")
                    .setParameter("aktivan", aktivan).getSingleResult();
            
        } catch (Exception e) {
            return null;
        }
    }

    

  

    
        
    

   
    
    
    
    
    
    
    
}
