/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija;

import evidencija.util.HibernateUtil;
import evidencija.view.SplashScreen;
import org.hibernate.Session;

/**
 *
 * @author jbalog8
 */
public class Start {

    private Session session;

    public Start() {
        this.session = HibernateUtil.getSession();
    }

    public static void main(String[] args) {
        //new Start();
        new SplashScreen().setVisible(true);

    }

}
