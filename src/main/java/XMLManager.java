import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

class XMLManager {

    static void load(String fileName, FopProcessor processor) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {

            SAXParser parser = factory.newSAXParser();
            File file = new File(fileName);
            FopHandler fopHandler = new FopHandler(processor);

            parser.parse(file, fopHandler);

        } catch (FopHandler.MySAXTerminatorException ignored) {
            System.out.println("Restriction reached.");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
