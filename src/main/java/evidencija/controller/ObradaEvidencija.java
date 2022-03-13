/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.controller;

import evidencija.model.Evidencija;
import evidencija.util.EvidencijaException;
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

    @Override
    protected void kontrolaCreate() throws EvidencijaException {
        kontrolaDatum();

    }

    @Override
    protected void kontrolaUpdate() throws EvidencijaException {
        kontrolaDatum();
    }

    @Override
    protected void kontrolaDelete() throws EvidencijaException {
        if (entitet.getZaposlenik() != null && entitet.getZaposlenik().size() > 0) {
            throw new EvidencijaException("Evidenciju ne mozes obrisati zato sto pripada zaposleniku");
        }
    }

    private void kontrolaDatum() throws EvidencijaException {

        if (entitet.getDatum() == null) {
            throw new EvidencijaException("Datum obavezan");
        }
    }

}
