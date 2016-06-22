
import static java.lang.String.format;
import static java.lang.String.format;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

public class NovoMain {

    public static void main(String[] args) {
        try {
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatador = DateTimeFormatter
                    .ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(new Locale("pt", "br"));
            agora.format(formatador);

            System.out.println(agora.format(formatador));

            //Crio uma data local
            DateTime dataLocal = new DateTime();
            //Aqui eu crio  um formato do tippo ANO - MES - DIA
            SimpleDateFormat formatData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatData.format(dataLocal.toDate());

            System.out.println(dataLocal.toDate());

            String teste2 = formatData.format(dataLocal.toDate());
            //Date teste = formatData.format(dataLocal.toDate());

            System.out.println("Este: " + teste2);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) formatter.parse("2016-05-01 19:06:06");

            System.out.println("Resutado:" + date);
        } catch (ParseException ex) {
            Logger.getLogger(NovoMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
