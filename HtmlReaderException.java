public class HtmlReaderException extends Exception {

    public HtmlReaderException() {
        super();
    }

    @Override
    public String getMessage() {
        return "URL connection error";
    }
}
