import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAnalyzer {

    public static void main(String[] args)
    {
        try {
            String url = args[0];
            HtmlReader reader = new HtmlReader(url);
            String resposta = reader.getHTML();

            HtmlOperations operations = new HtmlOperations(resposta);
            operations.process();
            String lastText = operations.getText();
            System.out.println(lastText);
        } catch(HtmlReaderException | IOException | InterruptedException e) {
            System.out.println("URL connection error");
        } catch(MalformedHtmlException e) {
            System.out.println(e.getMessage());
        }
    }
}