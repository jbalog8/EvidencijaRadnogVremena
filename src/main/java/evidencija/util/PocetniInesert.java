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
        List<Odjel> odjeli = new ArrayList<>(); //pitanje ne radi
        List<VrstaRada> vrstaRada = new ArrayList<>(); //pitanje ne radi
        List<Zaposlenik> zaposlenici = new ArrayList<>();

        unosZaposlenika(odjeli, faker, session);
        //unosOdjela(faker, session);
        //unesiEvidenciju(zaposlenici, vrstaRada, faker, session);
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

        for (int i = 0; i <= 20; i++) {
            z = new Zaposlenik();
            z.setIme(faker.name().firstName());
            z.setPrezime(faker.name().lastName());
            z.setEmail(new StringBuilder().append(z.getIme().substring(0, 1).
                    toLowerCase()).append(z.getPrezime().toLowerCase()).append("@").
                    append("fakecompay.com").toString());
            z.setOib(EvidencijaUtil.generirajOib());
            z.setBrKartice(EvidencijaUtil.generirajKarticu());
            z.setOdjel(odjeli.get((int)(Math.random() * 10)));
            session.save(z);

        }

    }

    private static String[] unosOdjela(Faker faker, Session session) {
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
        return naziv;
    }

    private static void unesiEvidenciju(List<Zaposlenik> zaposlenici, List<VrstaRada> vrsteRada, Faker faker, Session session) {
        Evidencija e = new Evidencija();
        for (int i = 0; i < 100; i++) {
            e = new Evidencija();

            e.setZaposlenik(zaposlenici.get((int) (Math.random() * 10)));
            e.setDatumPocetak(EvidencijaUtil.generirajDatum("01.01.2022", "04.04.2022"));
            e.setDatumKraj(EvidencijaUtil.generirajDatum("01.01.2022", "04.04.2022"));
            e.setVrstaRada(vrsteRada.get((int) (Math.random() * 10)));
            session.save(e);

        }
    }

    private static void unesiVrstuRada(Faker faker, Session session) {
        List<VrstaRada> vrsteRada = new ArrayList<>();
        VrstaRada vR;
        String[] naziv = {"Redovan rad", "Prekovremeni rad", "Terensi rad"};
        for (int i = 0; i <= naziv.length; i++) {
            vR = new VrstaRada();
            vR.setNaziv(naziv[i]);

            session.save(vR);
            vrsteRada.add(vR);
        }
    }

}
