/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.view;

import evidencija.model.Zaposlenik;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author jbalog8
 */
public class ZaposlenikComparator implements Comparator<Zaposlenik>{

     private Collator hr;
     
     public ZaposlenikComparator(){
         hr = Collator.getInstance(new Locale("hr", "HR")); 
        hr.setStrength(Collator.PRIMARY);
         
     }
     
     
    @Override
    public int compare(Zaposlenik o1, Zaposlenik o2) {
       return hr.compare(o1.getPrezime(), o2.getPrezime());
    }
    
    
}
