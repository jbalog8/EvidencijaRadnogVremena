/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.view;

import evidencija.model.Evidencija;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jbalog8
 */
public class TablicaModel extends AbstractTableModel {

    private List<Evidencija> evidencija;

    public TablicaModel(List<Evidencija> evidencija) {
        this.evidencija = evidencija;
    }

    @Override
    public int getRowCount() {
        return evidencija == null ? 0 : evidencija.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Evidencija e = evidencija.get(rowIndex);
        Object value = "??";

        switch (columnIndex) {
            case 0:
                value = e.getDatumPocetak();
                break;
            case 1:
                value = e.getDatumKraj();
                break;
            case 2:
                value = e.getVrstaRada();
                break;
            case 3:

                value = TimeUnit.MILLISECONDS.toHours(e.getDatumKraj().getTime()
                        - e.getDatumPocetak().getTime());
                break;
        }
        return value;
    }

    public Evidencija getEvidencija(int red) {
        return evidencija.get(red);

    }

}
