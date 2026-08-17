package org.jdom2.input.sax;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class AbstractReaderXSDFactory extends AbstractReaderSchemaFactory {
    private static final ThreadLocal<SchemaFactory> schemafactl = new ThreadLocal<>();

    public interface SchemaFactoryProvider {
        SchemaFactory getSchemaFactory();
    }

    public AbstractReaderXSDFactory(SAXParserFactory sAXParserFactory, SchemaFactoryProvider schemaFactoryProvider, String... strArr) throws JDOMException {
        super(sAXParserFactory, getSchemaFromString(schemaFactoryProvider, strArr));
    }

    private static final Schema getSchemaFromFile(SchemaFactoryProvider schemaFactoryProvider, File... fileArr) throws JDOMException {
        if (fileArr == null) {
            x0e.a("Cannot specify a null input array");
            return null;
        }
        if (fileArr.length == 0) {
            w01.a("You need at least one XSD source for an XML Schema validator");
            return null;
        }
        Source[] sourceArr = new Source[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            File file = fileArr[i];
            if (file == null) {
                x0e.a("Cannot specify a null SystemID");
                return null;
            }
            sourceArr[i] = new StreamSource(file);
        }
        return getSchemaFromSource(schemaFactoryProvider, sourceArr);
    }

    private static final Schema getSchemaFromSource(SchemaFactoryProvider schemaFactoryProvider, Source... sourceArr) throws JDOMException {
        if (sourceArr == null) {
            x0e.a("Cannot specify a null input array");
            return null;
        }
        if (sourceArr.length == 0) {
            w01.a("You need at least one XSD Source for an XML Schema validator");
            return null;
        }
        try {
            ThreadLocal<SchemaFactory> threadLocal = schemafactl;
            SchemaFactory schemaFactory = threadLocal.get();
            if (schemaFactory == null) {
                schemaFactory = schemaFactoryProvider.getSchemaFactory();
                threadLocal.set(schemaFactory);
            }
            if (schemaFactory != null) {
                return schemaFactory.newSchema(sourceArr);
            }
            throw new JDOMException("Unable to create XSDSchema validator.");
        } catch (SAXException e) {
            throw new JDOMException("Unable to create a Schema for Sources " + Arrays.toString(sourceArr), e);
        }
    }

    private static final Schema getSchemaFromString(SchemaFactoryProvider schemaFactoryProvider, String... strArr) throws JDOMException {
        if (strArr == null) {
            x0e.a("Cannot specify a null input array");
            return null;
        }
        if (strArr.length == 0) {
            w01.a("You need at least one XSD source for an XML Schema validator");
            return null;
        }
        Source[] sourceArr = new Source[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str == null) {
                x0e.a("Cannot specify a null SystemID");
                return null;
            }
            sourceArr[i] = new StreamSource(str);
        }
        return getSchemaFromSource(schemaFactoryProvider, sourceArr);
    }

    private static final Schema getSchemaFromURL(SchemaFactoryProvider schemaFactoryProvider, URL... urlArr) throws JDOMException {
        if (urlArr == null) {
            x0e.a("Cannot specify a null input array");
            return null;
        }
        if (urlArr.length == 0) {
            w01.a("You need at least one XSD source for an XML Schema validator");
            return null;
        }
        int length = urlArr.length;
        InputStream[] inputStreamArr = new InputStream[length];
        int i = 0;
        try {
            Source[] sourceArr = new Source[urlArr.length];
            for (int i2 = 0; i2 < urlArr.length; i2++) {
                URL url = urlArr[i2];
                if (url == null) {
                    throw new NullPointerException("Cannot specify a null SystemID");
                }
                try {
                    InputStream inputStreamOpenStream = url.openStream();
                    inputStreamArr[i2] = inputStreamOpenStream;
                    sourceArr[i2] = new StreamSource(inputStreamOpenStream, urlArr[i2].toString());
                } catch (IOException e) {
                    throw new JDOMException("Unable to read Schema URL " + urlArr[i2].toString(), e);
                }
            }
            Schema schemaFromSource = getSchemaFromSource(schemaFactoryProvider, sourceArr);
            while (i < length) {
                InputStream inputStream = inputStreamArr[i];
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                i++;
            }
            return schemaFromSource;
        } catch (Throwable th) {
            while (i < length) {
                InputStream inputStream2 = inputStreamArr[i];
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException unused2) {
                    }
                }
                i++;
            }
            throw th;
        }
    }

    public AbstractReaderXSDFactory(SAXParserFactory sAXParserFactory, SchemaFactoryProvider schemaFactoryProvider, URL... urlArr) throws JDOMException {
        super(sAXParserFactory, getSchemaFromURL(schemaFactoryProvider, urlArr));
    }

    public AbstractReaderXSDFactory(SAXParserFactory sAXParserFactory, SchemaFactoryProvider schemaFactoryProvider, File... fileArr) throws JDOMException {
        super(sAXParserFactory, getSchemaFromFile(schemaFactoryProvider, fileArr));
    }

    public AbstractReaderXSDFactory(SAXParserFactory sAXParserFactory, SchemaFactoryProvider schemaFactoryProvider, Source... sourceArr) throws JDOMException {
        super(sAXParserFactory, getSchemaFromSource(schemaFactoryProvider, sourceArr));
    }
}
