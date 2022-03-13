/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.util;

import evidencija.model.Operater;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author jbalog8
 */
public class PocetniInesert {
    
    public static void unosOperatera(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        
        Operater o = new Operater();
        o.setIme("Josip");
        o.setPrezime("Balog");
        o.setEmail("jbalog8@gmail.com");
        o.setLozinka(BCrypt.hashpw("jbalog8", BCrypt.gensalt()));
        session.save(o);
        session.getTransaction().commit();
        
    }
    
}
