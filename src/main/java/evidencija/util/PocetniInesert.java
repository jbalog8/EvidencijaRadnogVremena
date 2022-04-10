/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.util;

import com.github.javafaker.Faker;
import evidencija.model.Evidencija;
import evidencija.model.Odjel;
import evidencija.model.Operater;
import evidencija.model.VrstaRada;
import evidencija.model.Zaposlenik;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author jbalog8
 */
public class PocetniInesert {

    public static void pocetniUnos() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Faker faker = new Faker();
        unosOperatera();

        List<Odjel> odjeli = unosOdjela(faker, session); //pitanje ne radi
        List<VrstaRada> vrstaRada = unesiVrstuRada(faker, session); //pitanje ne radi

        //unosZaposlenika(odjeli, faker, session);

        List<Zaposlenik> zaposlenici = unosZaposlenika(odjeli, faker, session);//new ArrayList<>();
        unesiEvidenciju(zaposlenici, vrstaRada, faker, session);

        //unosOdjela(faker, session);
        //unesiVrstuRada(faker, session);
        session.getTransaction().commit();

    }

    public static void unosOperatera() {

        Operater o = new Operater();
        o.setIme("Josip");
        o.setPrezime("Balog");
        o.setEmail("jbalog8@gmail.com");
        o.setLozinka(BCrypt.hashpw("jbalog8", BCrypt.gensalt()));

    }

    private static List<Zaposlenik> unosZaposlenika(List<Odjel> odjeli, Faker faker, Session session) {
        
        List<Zaposlenik> zaposlenici = new ArrayList<>();
        Zaposlenik z;
        z = new Zaposlenik();
        z.setIme("Josip");
        z.setPrezime("Balog");
        z.setEmail("jbalog8@fakecompay.com");
        z.setOdjel((odjeli.get(0)));
        z.setOib(EvidencijaUtil.generirajOib());
        z.setBrKartice(EvidencijaUtil.generirajKarticu());
        session.save(z);
        zaposlenici.add((z));

        for (int i = 0; i < 20; i++) {
            z = new Zaposlenik();
            z.setIme(faker.name().firstName());
            z.setPrezime(faker.name().lastName());
            z.setEmail(new StringBuilder().append(z.getIme().substring(0, 1).
                    toLowerCase()).append(z.getPrezime().toLowerCase()).append("@").
                    append("fakecompay.com").toString());
            z.setOib(EvidencijaUtil.generirajOib());
            z.setBrKartice(EvidencijaUtil.generirajKarticu());

            z.setOdjel(odjeli.get((int) ((Math.random() * (4 - 1)) + 1)));
            session.save(z);
            zaposlenici.add((z)); 
        }
        

        return zaposlenici;
    }

    private static List<Odjel> unosOdjela(Faker faker, Session session) {
        List<Odjel> odjeli = new ArrayList<>();
        Odjel o;
        String[] naziv = {"Uprava", "Financije", "Nabava", "Marketing"};
        o = new Odjel();
        for (int i = 0; i < naziv.length; i++) {
            o = new Odjel();
            o.setNaziv(naziv[i]);
            session.save(o);
            odjeli.add(o);

        }
        return odjeli;
    }

    private static void unesiEvidenciju(List<Zaposlenik> zaposlenici, List<VrstaRada> vrsteRada, Faker faker, Session session) {
        Evidencija e = new Evidencija();
        Zaposlenik z;
        for (int i = 0; i < zaposlenici.size(); i++) {
            z = zaposlenici.get(i);

            for (int j = 0; j < 10; j++) {
                e = new Evidencija();
                e.setZaposlenik(z);
                Calendar c = Calendar.getInstance();
                c.set(Calendar.MONTH, 0); // mjeseci kreću od nule
                c.set(Calendar.MONTH, 1);
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DAY_OF_MONTH, slucajniBroj(27, 1));
                c.set(Calendar.YEAR, 2022);
                c.set(Calendar.HOUR_OF_DAY, slucajniBroj(9, 7));
                c.set(Calendar.MINUTE, slucajniBroj(59, 1));
                c.set(Calendar.SECOND, slucajniBroj(59, 1));

                e.setDatumPocetak(c.getTime());
                c.add(Calendar.MINUTE, slucajniBroj((8 * 60) + 10, (7 * 60) + 50));
                e.setDatumKraj(c.getTime());
                e.setVrstaRada(vrsteRada.get((int) ((Math.random() * (3 - 1)) + 1)));
                session.save(e);

            }
        }
    }

    /*
    private static void radSDatumima(){
         Calendar c = Calendar.getInstance();
        for(int i=0;i<10;i++){
            c.set(Calendar.MONTH, 5); // mjeseci kreću od nule
        c.set(Calendar.DAY_OF_MONTH, slucajniBroj(27, 1));
        c.set(Calendar.YEAR, 2022);
        c.set(Calendar.HOUR_OF_DAY,  slucajniBroj(9, 7));
        c.set(Calendar.MINUTE,  slucajniBroj(59, 1));
        c.set(Calendar.SECOND, slucajniBroj(59, 1) );
        System.out.println("Početak: " + c.getTime());
        if(slucajniBroj(19, 1)>5){
            c.add(Calendar.MINUTE, slucajniBroj((8*60) + 10, (7 * 60) + 50));
            System.out.println("Kraj: " + c.getTime());
        }else{
            //postaviš završni datum na null (ne moraš ništa raditi)
        }
            System.out.println("-------------");
        
        }
    }
     */
    private static int slucajniBroj(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static List<VrstaRada> unesiVrstuRada(Faker faker, Session session) {
        List<VrstaRada> vrsteRada = new ArrayList<>();
        VrstaRada vR;
        String[] naziv = {"Redovan rad", "Prekovremeni rad", "Terenski rad"};
        for (int i = 0; i < naziv.length; i++) {
            vR = new VrstaRada();
            vR.setNaziv(naziv[i]);

            session.save(vR);
            vrsteRada.add(vR);
        }
        return vrsteRada;
    }

}
