package br.gov.pi.ati.salvavidas.util;

import br.gov.pi.ati.salvavidas.modelo.denuncia.Denuncia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.MaskFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {

    public static Date converterStringParaDate(String dataString, String pattern) throws ParseException {
        if (dataString == null) {
            if (dataString.equals("")) {
                return null;
            }
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        return formato.parse(dataString);
    }

    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat formataData = null;

        if (date == null) {
            return "";
        }

        formataData = new SimpleDateFormat(pattern);

        String data = formataData.format(date);

        return data;
    }

    public static String format(String pattern, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String enderecoPorNomeAtributoJson(JSONArray jsonArray, String componente) {
        String chave = "";
        if (componente.equals("Numero")) {
            chave = "street_number";
        }
        if (componente.equals("Endereco")) {
            chave = "route";
        }
        if (componente.equals("Bairro")) {
            chave = "sublocality_level_1"; //["political","sublocality","sublocality_level_1"]
        }
        if (componente.equals("Cidade")) {
            chave = "administrative_area_level_2";
        }
        if (componente.equals("Estado")) {
            chave = "administrative_area_level_1";
        }
        if (componente.equals("Cep")) {
            chave = "postal_code";
        }
        if (componente.equals("Pais")) {
            chave = "country";
        }

        for (Object obj : jsonArray) {
            JSONArray tipos = ((JSONObject) obj).getJSONArray("types");
            if (tipos.toString().contains(chave)) {
                return ((JSONObject) obj).getString("long_name");
            }
        }

        return null;

    }

    public static String pegarRetornoUrlGoogle(URL url) throws IOException {
        if (url == null) {
            return null;
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("UTF-8")));
        } catch (IOException e) {
            return null;
        }
        String str;

        String json = "";
        while ((str = in.readLine()) != null) {
            json = json + str;
        }
        in.close();
//        return json;
        return json.replace("   ", "");
    }

    public static String pegarLatitude(String jsonString) {

        String location = jsonString.substring(jsonString.indexOf("\"location\" :"), jsonString.indexOf("\"location_type\"") - 13);
        String lat = location.substring((location.indexOf("\"lat\"") + 8), location.indexOf(","));
        return lat;
    }

    public static String pegarLongitude(String jsonString) {

        String location = jsonString.substring(jsonString.indexOf("\"location\" :"), jsonString.indexOf("\"location_type\""));
        String lng = location.substring((location.indexOf("\"lng\" :") + 8), (location.indexOf("}")));
        return lng;
    }

    public static Date primeiroDiaDoMes() {
        Calendar dataAtual = Calendar.getInstance();

        dataAtual.set(Calendar.DAY_OF_MONTH, 1);

        return dataAtual.getTime();
    }

    public static Date ultimoDiaDoMes() {
        Calendar dataAtual = Calendar.getInstance();

        dataAtual.set(Calendar.DAY_OF_MONTH, dataAtual.getActualMaximum(Calendar.DAY_OF_MONTH));

        return dataAtual.getTime();
    }

    public static Date primeiroDiaDaSemana() {
        Calendar dataAtual = Calendar.getInstance();

        dataAtual.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return dataAtual.getTime();
    }

    public static Date ultimoDiaDaSemana() {
        Calendar dataAtual = Calendar.getInstance();

        dataAtual.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return dataAtual.getTime();
    }

    public static Date primeiroDiaSemestre() {
        Calendar dataAtual = Calendar.getInstance();

        if (dataAtual.get(Calendar.MONTH) < 6) {
            dataAtual.set(Calendar.MONTH, 0);
        } else {
            dataAtual.set(Calendar.MONTH, 6);
        }

        dataAtual.set(Calendar.DAY_OF_MONTH, 1);

        return dataAtual.getTime();
    }

    public static Date ultimoDiaSemestre() {
        Calendar dataAtual = Calendar.getInstance();

        if (dataAtual.get(Calendar.MONTH) < 6) {
            dataAtual.set(Calendar.MONTH, 5);
        } else {
            dataAtual.set(Calendar.MONTH, 11);
        }

        dataAtual.set(Calendar.DAY_OF_MONTH, dataAtual.getActualMaximum(Calendar.DAY_OF_MONTH));

        return dataAtual.getTime();
    }

    public static Date primeiroDiaDoAno() {
        Calendar dataAtual = Calendar.getInstance();

        dataAtual.set(Calendar.DAY_OF_MONTH, 1);
        dataAtual.set(Calendar.MONTH, 0);

        return dataAtual.getTime();
    }

    public static Date ultiomoDiaDoAno() {
        Calendar dataAtual = Calendar.getInstance();

        dataAtual.set(Calendar.DAY_OF_MONTH, 31);
        dataAtual.set(Calendar.MONTH, 11);

        return dataAtual.getTime();
    }

    public static String gerarDomain(String login) {
        //String usuarioZimbra = "uid=juniel.silva,ou=people,dc=ati,dc=pi,dc=gov,dc=br";
        //"uid=juniel.silva,ou=ati,ou=usuarios,dc=pi,dc=gov,dc=br"
        String domainLDap = "uid=USUARIO_LDAP,ou=people,dc=ORGAO,dc=pi,dc=gov,dc=br";
        String[] loginPartes = new String[2];
        loginPartes = login.toLowerCase().split("@");
        String usuario = loginPartes[0];

        String domain = loginPartes[1].toString();

        String[] domainPedacos = new String[4];
        domainPedacos = domain.split("\\.");
        String orgao = domainPedacos[0];

        domainLDap = domainLDap.replace("USUARIO_LDAP", usuario).replace("ORGAO", orgao);

        return domainLDap;
    }

    public static Date setarData(Integer dia, Integer mes, Integer ano) {
        Calendar dataAtual = Calendar.getInstance();

        if (dia != null) {
            dataAtual.set(Calendar.DAY_OF_MONTH, dia);
        }

        if (mes != null) {
            dataAtual.set(Calendar.MONTH, mes);
        }

        if (ano != null) {
            dataAtual.set(Calendar.YEAR, ano);
        }

        return dataAtual.getTime();
    }

    public static BigDecimal calcularPorcPeloValor(Integer total, Integer valor) {

        BigDecimal retorno = BigDecimal.ZERO;

        if (valor == null || total == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal totalTemp = new BigDecimal(total);
        BigDecimal valorTemp = new BigDecimal(valor);

        if (totalTemp.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        retorno = valorTemp.multiply(new BigDecimal(100)).divide(totalTemp, 2, RoundingMode.UP);

        return retorno;
    }

    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.equals(""));
    }

    public static Integer getAnoAtual() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy");
        Integer ano = new Integer(formato.format(new Date()));
        return ano;
    }

    /* Calculate distance between two points in latitude 
     and longitude taking into account height difference. 
     If you are not interested in height difference pass 0.0.
     Uses Haversine method as its base. lat1, lon1 Start point 
     lat2, lon2 End point el1 Start altitude in meters el2 End altitude 
     in meters */
    public static double distance(double lat1, double lat2, double lon1, double lon2,
            double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = deg2rad(lat2 - lat1);
        Double lonDistance = deg2rad(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    public static String getAtributosMarcacaoDenuncia(Denuncia denuncia) {
        String retorno = "";

//        if (denuncia.getId() != null) {
//            retorno = retorno.concat("Id: ").concat(denuncia.getId().toString());
//            retorno = retorno.concat("\n");
//        }

        if (denuncia.getSituacao() != null) {
            retorno = retorno.concat("Situação: ").concat(denuncia.getSituacao().getDescricao());
            retorno = retorno.concat("\n");
        }

        if (denuncia.getDataDenuncia() != null) {
            if (denuncia.getDataDenuncia() != null) {
                retorno = retorno.concat("Data: ").concat(Utils.convertDateToString(denuncia.getDataDenuncia(), "dd/MM/yyyy"));
                retorno = retorno.concat(" ás ").concat(Utils.convertDateToString(denuncia.getDataDenuncia(), "HH:mm"));
                retorno = retorno.concat("\n");
            }
        }

        if (denuncia.getEndereco() != null) {
            if (denuncia.getEndereco().getEnderecoFormatado() != null) {
                retorno = retorno.concat("Endereço: ").concat(denuncia.getEndereco().getEnderecoFormatado());
                retorno = retorno.concat("\n");
            }
        }

        return retorno;
    }
}
