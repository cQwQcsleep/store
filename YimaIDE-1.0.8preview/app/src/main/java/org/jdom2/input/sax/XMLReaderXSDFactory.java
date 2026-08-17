package org.jdom2.input.sax;

import java.io.File;
import java.net.URL;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.validation.SchemaFactory;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.jdom2.JDOMException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class XMLReaderXSDFactory extends AbstractReaderXSDFactory {
    private static final AbstractReaderXSDFactory.SchemaFactoryProvider xsdschemas = new AbstractReaderXSDFactory.SchemaFactoryProvider() { // from class: org.jdom2.input.sax.XMLReaderXSDFactory.1
        @Override // org.jdom2.input.sax.AbstractReaderXSDFactory.SchemaFactoryProvider
        public SchemaFactory getSchemaFactory() {
            return SchemaFactory.newInstance(XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA);
        }
    };

    public XMLReaderXSDFactory(String... strArr) throws JDOMException {
        super(SAXParserFactory.newInstance(), xsdschemas, strArr);
    }

    public XMLReaderXSDFactory(URL... urlArr) throws JDOMException {
        super(SAXParserFactory.newInstance(), xsdschemas, urlArr);
    }

    public XMLReaderXSDFactory(File... fileArr) throws JDOMException {
        super(SAXParserFactory.newInstance(), xsdschemas, fileArr);
    }

    public XMLReaderXSDFactory(Source... sourceArr) throws JDOMException {
        super(SAXParserFactory.newInstance(), xsdschemas, sourceArr);
    }
}
