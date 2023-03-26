public class MalformedHtmlException extends Exception {

    public MalformedHtmlException() {
        super();
    }

    @Override
    public String getMessage() {
        return "malformed HTML";
    }
}
