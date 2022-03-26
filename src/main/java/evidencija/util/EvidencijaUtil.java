/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evidencija.util;

import evidencija.model.Operater;
import evidencija.model.Zaposlenik;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

/**
 *
 * @author jbalog8
 */
public class EvidencijaUtil {
    public static Operater operater;
    public static final String NAZIV_APP="EVIDENCIJA app";
    
    public static String getNaslov(String naslov){
        if(EvidencijaUtil.operater==null){
            return EvidencijaUtil.NAZIV_APP + " " + naslov;
        }
        return EvidencijaUtil.NAZIV_APP + " " + naslov +" " + EvidencijaUtil.operater.getIme() + " " + 
                EvidencijaUtil.operater.getPrezime() + " - " + EvidencijaUtil.operater.getUloga();
    }

    public static String generirajOib() {
        // DZ
        // Umjesto spajanja na web stranicu napraviti generiranje u java programskom jeziku
        // https://regos.hr/app/uploads/2018/07/KONTROLA-OIB-a.pdf
        try {
            URL url = new URL("http://oib.itcentrala.com/oib-generator/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            url.openStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            Document d = Jsoup.parse(sb.toString());
            return Xsoup.compile("/html/body/div[1]/div[1]/text()").evaluate(d).get();
        } catch (Exception e) {
        }
        return "";
    }
    
    public static String generirajKarticu(){
       int broj = ((int)(Math.random()*100)+1);
       String kartica = "";
       
        kartica = "0000000000000" + broj;
        kartica = kartica.substring(kartica.length()-13);
        
        return kartica;
    }
    
}
