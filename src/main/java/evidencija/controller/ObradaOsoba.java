/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.Osoba;
import evidencija.util.EvidencijaException;
import evidencija.util.OibValidation;
import java.util.List;

/**
 *
 * @author jbalog8
 */
public abstract class ObradaOsoba<T extends Osoba> extends Obrada<T>{

   

    @Override
    protected void kontrolaCreate() throws EvidencijaException {
        kontrolaOib();
        kontrolaEmail();
     
    }

    @Override
    protected void kontrolaUpdate() throws EvidencijaException {
    }

    @Override
    protected void kontrolaDelete() throws EvidencijaException {
        
    }

    private void kontrolaOib() throws EvidencijaException{
       
        if(!OibValidation.checkOIB(entitet.getOib())){
            throw new EvidencijaException("OIB nije formalno valjan");
        }
    }

    private void kontrolaEmail() throws EvidencijaException{
        
       
    }
    
    
}
