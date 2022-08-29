package services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import modelo.personajes;
import org.json.JSONObject;

public class apiDBS {

    public static JSONObject consumiendoAPI(personajes per) throws Exception {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://dragon-ball-super-api.herokuapp.com/api/characters/"+ per.getName())
                .header("id", "int")
                .header("imageUrl", "string(url)")
                .header("name", "string")
                .header("originplanet", "string")
                .header("role", "string")
                .header("specie", "string")
                .header("status", "string")
                .header("universe", "string")
                .asString();

        //lineas añadidas al unirest
        JSONObject cadenaJson = new JSONObject(response.getBody());
//        JSONObject cadena = cadenaJson.getJSONObject("data");
        per.setName(cadenaJson.getString("name")); 
        per.setStatus(cadenaJson.getString("status"));
         per.setRole(cadenaJson.getString("role"));
         per.setSpecie(cadenaJson.getString("specie"));
         per.setImagen(cadenaJson.getString("imageUrl"));
         per.setUniverse(cadenaJson.getString("universe"));
         System.out.println("entre en la api "+ per.getName());
        return cadenaJson;
        
    }

//    public static void main(String[] args) throws IOException, Exception {
//        personajes per = new personajes();
//        per.setName("Zeno");
//        JSONObject json = apiDBS.consumiendoAPI(per);
//        
//        System.out.println("nombre completo " + per.getRole());
//
//    }
}
