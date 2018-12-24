import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FopHandler extends DefaultHandler {

    private static Integer maxCounter = null; //to limit the number of items. null - no limit
    private final FopProcessor processor;
    private Fop fop;
    private StringBuilder stringBuilder;

    FopHandler(FopProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (maxCounter != null && maxCounter <= 0) {
            throw new MySAXTerminatorException();
        }

        stringBuilder = new StringBuilder();

        if (qName.equals("RECORD")) {
            fop = new Fop();
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (fop != null) {
            switch (qName) {
                case "FIO":
                    fop.setName(stringBuilder.toString());
                    break;
                case "ADDRESS":
                    fop.setAddress(stringBuilder.toString());
                    break;
                case "KVED":
                    if (stringBuilder.toString().indexOf(' ') > 0) {
                        fop.setKved(stringBuilder.toString().substring(0, stringBuilder.toString().indexOf(' ')));
                        fop.setKvedDescription(stringBuilder.toString().substring(stringBuilder.toString().indexOf(' ') + 1));
                    } else {
                        fop.setKved(stringBuilder.toString());
                    }
                    break;
                case "STAN":
                    fop.setStatus(stringBuilder.toString());
                    break;
                case "RECORD":
                    processor.process(fop);
                    fop = null;
                    maxCounter = maxCounter != null ? maxCounter - 1 : null;
                    break;

                default:
                    fop = null;
            }
        }
    }

    class MySAXTerminatorException extends SAXException {

    }

}