package javax.xml.validation;

import com.sun.org.apache.xerces.internal.jaxp.validation.XMLSchemaFactory;
import defpackage.h0f;
import defpackage.i0f;
import java.io.File;
import java.net.URL;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import jdk.xml.internal.SecuritySupport;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SchemaFactory {
    public static SchemaFactory newDefaultInstance() {
        return new XMLSchemaFactory();
    }

    public static SchemaFactory newInstance(String str, String str2, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = SecuritySupport.getContextClassLoader();
        }
        SchemaFactory schemaFactoryCreateInstance = new SchemaFactoryFinder(classLoader).createInstance(str2);
        if (schemaFactoryCreateInstance == null) {
            h0f.a("Factory ", str2, " could not be loaded to implement the schema language specified by: ", str);
            return null;
        }
        if (schemaFactoryCreateInstance.isSchemaLanguageSupported(str)) {
            return schemaFactoryCreateInstance;
        }
        i0f.a("Factory ", schemaFactoryCreateInstance.getClass().getName(), " does not implement the schema language specified by: ", str);
        return null;
    }

    public abstract ErrorHandler getErrorHandler();

    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(str);
    }

    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str == null) {
            throw new NullPointerException("the name parameter is null");
        }
        throw new SAXNotRecognizedException(str);
    }

    public abstract LSResourceResolver getResourceResolver();

    public abstract boolean isSchemaLanguageSupported(String str);

    public abstract Schema newSchema() throws SAXException;

    public Schema newSchema(URL url) throws SAXException {
        return newSchema(new StreamSource(url.toExternalForm()));
    }

    public abstract Schema newSchema(Source[] sourceArr) throws SAXException;

    public abstract void setErrorHandler(ErrorHandler errorHandler);

    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str != null) {
            throw new SAXNotRecognizedException(str);
        }
        throw new NullPointerException("the name parameter is null");
    }

    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (str != null) {
            throw new SAXNotRecognizedException(str);
        }
        throw new NullPointerException("the name parameter is null");
    }

    public abstract void setResourceResolver(LSResourceResolver lSResourceResolver);

    public Schema newSchema(Source source) throws SAXException {
        return newSchema(new Source[]{source});
    }

    public Schema newSchema(File file) throws SAXException {
        return newSchema(new StreamSource(file));
    }

    public static SchemaFactory newInstance(String str) {
        ClassLoader contextClassLoader = SecuritySupport.getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = SchemaFactory.class.getClassLoader();
        }
        SchemaFactory schemaFactoryNewFactory = new SchemaFactoryFinder(contextClassLoader).newFactory(str);
        if (schemaFactoryNewFactory != null) {
            return schemaFactoryNewFactory;
        }
        kg9.a("No SchemaFactory that implements the schema language specified by: ", str, " could be loaded");
        return null;
    }
}
