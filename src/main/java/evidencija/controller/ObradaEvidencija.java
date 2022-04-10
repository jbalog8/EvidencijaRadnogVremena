/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.Evidencija;
import evidencija.model.Zaposlenik;
import evidencija.util.EvidencijaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jbalog8
 */
public class ObradaEvidencija extends Obrada<Evidencija> {

    @Override
    public List<Evidencija> read() {
        return session.createQuery("from Evidencija").list();
    }

    public List<Evidencija> read(String uvjet) {

        return session.createQuery("from Evidencija  "
                + " where (zaposlenik_sifra = :zaposlenik_sifra)"
                + " order by  datumPocetak, datumKraj")
                .setParameter("zaposlenik_sifra", uvjet)
                .setMaxResults(50)
                .list();

}

    public Evidencija getZaposlenik(Zaposlenik zaposlenik) {
        try {
            return (Evidencija) session.createQuery("from Evidencija where zaposlenik=:zaposlenik")
                    .setParameter("zaposlenik", zaposlenik).setMaxResults(50);
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    protected void kontrolaCreate() throws EvidencijaException {

    }

    @Override
    protected void kontrolaUpdate() throws EvidencijaException {

    }

    @Override
    protected void kontrolaDelete() throws EvidencijaException {
//        if (entitet.getZaposlenik() != null) {
//            throw new EvidencijaException("Evidenciju ne mozeš obrisati zato što pripada zaposleniku");
//        }
    }

    public Zaposlenik getZaposlenik(String ime) {
        if (entitet.getZaposlenik() == null) {
            try {
               return (Zaposlenik) session.createQuery("from Zaposlenik where ime=:ime")
                        .setParameter("ime", ime).getSingleResult();

            } catch (Exception e) {

            }

        }

        return null;
    }

}
