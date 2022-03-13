/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.VrstaRada;
import evidencija.util.EvidencijaException;
import java.util.List;

/**
 *
 * @author jbalog8
 */
public class ObradaVrstaRada extends Obrada<VrstaRada> {

    @Override
    public List<VrstaRada> read() {
        return session.createQuery("from VrstaRada a order by a.naziv").list();
    }

    @Override
    protected void kontrolaCreate() throws EvidencijaException {
        kontrolaNaziv();
    }

    @Override
    protected void kontrolaUpdate() throws EvidencijaException {
        kontrolaNaziv();
    }

    @Override
    protected void kontrolaDelete() throws EvidencijaException {
        
    }

    private void kontrolaNaziv() throws EvidencijaException{
         if(entitet.getNaziv()==null || entitet.getNaziv().trim().isEmpty()){
            throw new EvidencijaException("Naziv vrste rada obavezan");
         }
          if(entitet.getNaziv().trim().length()>50){
            throw new EvidencijaException("Naziv vrste rada ne smije biti duži od 50 znakova");
        }
        
        
    }
    
    
}
