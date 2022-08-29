package Controller;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import modelo.personajes;
import services.apiDBS;

@Named(value = "ApiC")
@Data
@SessionScoped
public class ApiC implements Serializable {

    personajes per;

    public ApiC() {
        per = new personajes();
    }

    public void consumiendo() {
        try {
            per.setName(caseMayuscula(per.getName()));
            apiDBS.consumiendoAPI(per);
        } catch (Exception e) {
            System.out.println("problemas en conumiendoC " + e.getMessage());
        }

    }

    public String caseMayuscula(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {  // Si se encuentra el primer carácter de una palabra
                if (ch[i] >= 'a' && ch[i] <= 'z') {      // Si está en minúsculas
                    ch[i] = (char) (ch[i] - 'a' + 'A');  // Convertir en mayúsculas
                }
            } // Si aparte del primer carácter cualquiera está en mayúsculas
            else if (ch[i] >= 'A' && ch[i] <= 'Z') {     // Convertir en minúsculas
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        camelcase = st;
        return camelcase;
    }
    
     public void limpiar() throws Exception {
        try {
            per = new personajes();
        } catch (Exception e) {
            LogManager lgmngr = LogManager.getLogManager();
            Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
            log.log(Level.INFO, "Error en ClienteC/Limpiar: {0}", e.getMessage());
        }
    }

}
