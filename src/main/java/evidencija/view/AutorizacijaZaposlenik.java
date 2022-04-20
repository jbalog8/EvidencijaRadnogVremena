/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package evidencija.view;

import evidencija.controller.ObradaEvidencija;
import evidencija.controller.ObradaVrstaRada;
import evidencija.controller.ObradaZaposlenik;
import evidencija.model.Evidencija;
import evidencija.model.VrstaRada;
import evidencija.model.Zaposlenik;
import evidencija.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.hibernate.Session;

/**
 *
 * @author jbalog8
 */
public class AutorizacijaZaposlenik extends javax.swing.JFrame {

    private ObradaZaposlenik obrada;
    private ObradaEvidencija obradaEvidencija;
   

    /**
     * Creates new form AutorizacijaZaposlenik
     */
    public AutorizacijaZaposlenik() {
        initComponents();

        obrada = new ObradaZaposlenik();
        obradaEvidencija = new ObradaEvidencija();
        ucitajVrstuRada();
        
        
         
        txtBrojKartice.getText();

        txtBrojKartice.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                promjenaBarKod();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                promjenaBarKod();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                promjenaBarKod();
            }
        });
    }

    private void promjenaBarKod() {
        if (txtBrojKartice.getText().length() != 13) {
            return;
        }
         Zaposlenik zaposlenik = obrada.getZaposlenik(txtBrojKartice.getText());
        if(zaposlenik==null){
            JOptionPane.showMessageDialog(null, "Ne postoji zaposlenik");
            return;
        }
        if(zaposlenik.getAktivan() == false){
            JOptionPane.showMessageDialog(null, "Niste više aktivni zaposlenik/ca poduzeća");
            return;
        }
       
        List<Evidencija> evidencije = obrada.getEvidencije(zaposlenik);
       
       
        if(evidencije==null || evidencije.size()==0){
            Evidencija e = new Evidencija();
            e.setZaposlenik(zaposlenik);
            e.setDatumPocetak(new Date());
            obradaEvidencija.setEntitet(e);
            try {
               
                obradaEvidencija.create();
                preuzmiVrijednost();
                JOptionPane.showMessageDialog(null, "Uspješno ste se prijavili " + e.getZaposlenik());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "greška dodavanje novi");
                return;
            }
        }else{
            var evidencija = evidencije.get(0);
            // da li je prošlo više od 8 sati. Ako je prošlo više od 8 sati tada
            // staru evidenciju zatvore nakon 8 sati od početka stare
            //i napravi novu
            evidencija.setDatumKraj(new Date());
           
            obradaEvidencija.setEntitet(evidencija);
            try {
               
                obradaEvidencija.update();
                JOptionPane.showMessageDialog(null, "Uspješno ste se odjavili " + evidencija.getZaposlenik());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Nije dobro");
                return;
            }
        }
        
        /*
        new ZaposlenikovProzor().setVisible(true);
        Zaposlenik z = obrada.getZaposlenik(txtBrojKartice.getText());

        Runnable doHighlight = new Runnable() {
            @Override
            public void run() {
                txtBrojKartice.setText("");
            }
        };
        SwingUtilities.invokeLater(doHighlight);
        System.out.println(z.getIme() + z.getPrezime());
        */
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBrojKartice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnPRIJAVA = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbVrstaRada = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Unesi broj kartice");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRIJAVA");

        btnPRIJAVA.setText("PRIJAVA");
        btnPRIJAVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPRIJAVAActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBrojKartice)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 88, Short.MAX_VALUE)
                        .addComponent(btnPRIJAVA)
                        .addGap(73, 73, 73))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(cmbVrstaRada, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addGap(97, 97, 97))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVrstaRada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBrojKartice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPRIJAVA)
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPRIJAVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPRIJAVAActionPerformed


    }//GEN-LAST:event_btnPRIJAVAActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        if (evt.getClickCount() == 2) {
            dispose();
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPRIJAVA;
    private javax.swing.JComboBox<VrstaRada> cmbVrstaRada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtBrojKartice;
    // End of variables declaration//GEN-END:variables

    private void ucitajVrstuRada() {
        DefaultComboBoxModel<VrstaRada> ms = new DefaultComboBoxModel<>();
        VrstaRada vr = new VrstaRada();
        vr.setSifra(Long.valueOf(0));
        vr.setNaziv("-");
        ms.addElement(vr);
        new ObradaVrstaRada().read().forEach(s -> {
            ms.addElement(s);
        });
        cmbVrstaRada.setModel(ms);
    }
    
    private void preuzmiVrijednost(){
        var e = obradaEvidencija.getEntitet();
        e.setVrstaRada((VrstaRada)cmbVrstaRada.getSelectedItem());
    }
    
   
}
