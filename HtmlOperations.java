import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlOperations{
    private String html;
    private LinkedHashMap<Integer, String> textos = new LinkedHashMap<>();
    private Stack<String> tags = new Stack<>();
    public HtmlOperations(String html) {
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    public void process() throws MalformedHtmlException {
        Scanner cod = new Scanner(html);
        int nivel = 0;
        int aux = 0;
        Pattern openTag = Pattern.compile("^\\s*<([^/].*?)>");
        Pattern closeTag = Pattern.compile("^\\s*</\\w+>\\s*$");
        Pattern text = Pattern.compile("^\\s*[^<].*?\\s*$");

        while (cod.hasNextLine()) {
            String linha = cod.nextLine();
            if(linha.isBlank()){
                continue;
            }
            String linhaSemEspaco = linha.trim();
            Matcher trueOpenTag = openTag.matcher(linhaSemEspaco);
            Matcher trueCloseTag = closeTag.matcher(linhaSemEspaco);
            Matcher trueText = text.matcher(linhaSemEspaco);
            if (trueOpenTag.matches()) {
                String divOpen = linhaSemEspaco.substring(1,linhaSemEspaco.length()-1);
                tags.push(divOpen);
                nivel++;
//                System.out.println("Tag aberta: " + divOpen + "Nivel:" + nivel);

            } else if (trueCloseTag.matches()) {
                String divClose = linhaSemEspaco.substring(2,linhaSemEspaco.length()-1);
                if((tags.peek().equals(divClose)) && (!tags.isEmpty())){ // compara se o topo da pilha é a mesma div que está sendo fechada, também verifica se não está tentando dar pop em uma pilha vazia
                    tags.pop();
//                    System.out.println("Tag fechada: " + divClose + "Nivel:" + nivel);
                    nivel--;
                }else{
                    throw  new MalformedHtmlException();
                }

            } else if ((trueText.matches()) && (!tags.isEmpty())) { //se a pilha estiver vazia, significa que o texto está depois da ultima tag html, logo está malformed
                if (nivel > aux) {
                    textos.put(nivel, linhaSemEspaco); //insere no máximo 1 trecho de texto para cada nivel, sendo a ultima inserção na pilha o primeiro trecho do maior nivel
                    aux = nivel;
                }
            }else{
                throw new MalformedHtmlException();
            }

        }
        cod.close();
    }

    public String getText() throws MalformedHtmlException {
        Map.Entry<Integer, String> ultimo = null;
        for (Map.Entry<Integer, String> lastInsert : textos.entrySet()) {
            ultimo =  lastInsert;
        }
            return ultimo.getValue();

    }
}




