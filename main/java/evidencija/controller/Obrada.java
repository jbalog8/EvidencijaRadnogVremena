/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.util.EvidencijaException;
import evidencija.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author jbalog8
 */
public abstract class Obrada<T> {
    
    protected Session session;
    protected T entitet;
    
    public abstract List<T> read();
    protected abstract void kontrolaCreate() throws EvidencijaException;
    protected abstract void kontrolaUpdate() throws EvidencijaException;
    protected abstract void kontrolaDelete() throws EvidencijaException;
    
    public Obrada(){
        session=HibernateUtil.getSession();
    }
    
    public T create() throws EvidencijaException{
        kontrolaCreate();
        save();
        return entitet;
    }
    
    public T update() throws EvidencijaException{
        kontrolaUpdate();
        save();
        return entitet;
    }
    
    public void delete() throws EvidencijaException{
        kontrolaDelete();
        session.beginTransaction();
        session.delete(entitet);
        session.getTransaction().commit();
    }
    
    private void save(){
        session.beginTransaction();
        session.save(entitet);
        session.getTransaction().commit();
    }

    public T getEntitet() {
        return entitet;
    }

    public void setEntitet(T entitet) {
        this.entitet = entitet;
    }
}
