package br.gov.pi.ati.util;

import br.gov.pi.ati.modelo.cadastro.enums.Formato;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.text.MaskFormatter;

public class Utils {

    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.equals(""));
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

    public static Long getTempoAtendimentoEmAnos(Date dataInicial, Date dataFinal) {

        if (dataInicial == null) {
            return 0L;
        }

        Calendar dataInicialTemp = Calendar.getInstance();
        dataInicialTemp.setTime(dataInicial);

        Calendar dataFinalTemp = Calendar.getInstance();

        if (dataFinal != null) {
            dataFinalTemp.setTime(dataFinal);
        } else {
            dataFinalTemp.setTime(new Date());
        }

        Long milisegundos = (dataFinalTemp.getTimeInMillis() - dataInicialTemp.getTimeInMillis());

        return (((milisegundos / (1000 * 60 * 60 * 24)) / 30) / 12);
    }

    public static Date convertStringToDate(String dataString, String formato) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        return formatter.parse(dataString);
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

    public static String getAno(Date data) {
        SimpleDateFormat formataData = null;

        if (data == null) {
            return "";
        }

        formataData = new SimpleDateFormat("yyyy");

        return formataData.format(data);
    }

    public static boolean ehInteiro(String s) {
        // cria um array de char
        char[] c = s.replace(".", "").replace("-", "").replace("/", "").toCharArray();
        boolean d = true;
        for (int i = 0; i < c.length; i++) // verifica se o char não é um dígito
        {
            if (!Character.isDigit(c[i])) {
                d = false;
                break;
            }
        }
        return d;
    }

    public String inserirCaractere(String input, int width, Formato formato) {

        char ch = ' ';

        if (formato == Formato.NUM) {
            ch = '0';
        }

        String strPad = "";

        if (input == null) {
            input = "";
        }

        StringBuilder sb = new StringBuilder(input.trim());

        if (formato == Formato.NUM) {
            while (sb.length() < width) {
                sb.insert(0, ch);
            }
            strPad = sb.toString();

            if (strPad.length() > width) {

                strPad = strPad.substring(0, width);
            }
        } else {
            int total = sb.length() - 1;
            while (sb.length() < width) {
                sb.append(ch);
            }
            strPad = sb.toString();

            if (strPad.length() > width) {

                strPad = strPad.substring(0, width);
            }
        }

        return strPad;
    }

    public static String moeda(BigDecimal num) {
        
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);

        if (num == null) {
            return nf.format(BigDecimal.ZERO);
        }
        
        String formatado = nf.format(num);

        return formatado;
    }
}
