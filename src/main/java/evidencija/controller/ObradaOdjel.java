/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.Odjel;
import evidencija.util.EvidencijaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jbalog8
 */
public class ObradaOdjel extends Obrada<Odjel>{

    @Override
    public List<Odjel> read() {
        return session.createQuery("from Odjel").list();
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
            throw new EvidencijaException("Naziv odjela obavezan");
         }
          if(entitet.getNaziv().trim().length()>50){
            throw new EvidencijaException("Naziv odjela ne smije biti duži od 50 znakova");
        }
    }

   
        
    
    
    
}
