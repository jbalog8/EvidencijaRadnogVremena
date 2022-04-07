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

        unosZaposlenika(odjeli, faker, session);

        List<Zaposlenik> zaposlenici = new ArrayList<>();
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

    private static void unosZaposlenika(List<Odjel> odjeli, Faker faker, Session session) {
        Zaposlenik z;
        z = new Zaposlenik();
        z.setIme("Josip");
        z.setPrezime("Balog");
        z.setEmail("jbalog8@fakecompay.com");
        // z.setOdjel(z.getOdjel());
        z.setOib(EvidencijaUtil.generirajOib());
        z.setBrKartice(EvidencijaUtil.generirajKarticu());
        session.save(z);

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

        }

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

            for (int j = 0; j < 50; i++) {
                e = new Evidencija();
                e.setZaposlenik(z);
                e.setDatumPocetak(null);
                vrsteRada = new ArrayList<>();
                e.setVrstaRada(vrsteRada.get((int) ((Math.random() * (3 - 1)) + 1)));
                session.save(e);

            }
        }
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
