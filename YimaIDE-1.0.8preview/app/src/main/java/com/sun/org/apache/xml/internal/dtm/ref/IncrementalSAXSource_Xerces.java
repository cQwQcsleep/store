package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xml.internal.utils.WrappedRuntimeException;
import defpackage.x73;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IncrementalSAXSource_Xerces implements IncrementalSAXSource {
    private static final Object[] noparms = new Object[0];
    private static final Object[] parmsfalse = {Boolean.FALSE};
    Constructor<?> fConfigInputSourceCtor;
    Method fConfigParse;
    Method fConfigSetByteStream;
    Method fConfigSetCharStream;
    Method fConfigSetEncoding;
    Method fConfigSetInput;
    SAXParser fIncrementalParser;
    private boolean fParseInProgress;
    Method fParseSome;
    Method fParseSomeSetup;
    Object fPullParserConfig;
    Method fReset;
    Method fSetInputSource;

    public IncrementalSAXSource_Xerces() throws NoSuchMethodException {
        this.fParseSomeSetup = null;
        this.fParseSome = null;
        this.fPullParserConfig = null;
        this.fConfigSetInput = null;
        this.fConfigParse = null;
        this.fSetInputSource = null;
        this.fConfigInputSourceCtor = null;
        this.fConfigSetByteStream = null;
        this.fConfigSetCharStream = null;
        this.fConfigSetEncoding = null;
        this.fReset = null;
        this.fParseInProgress = false;
        try {
            Constructor constructor = SAXParser.class.getConstructor(ObjectFactory.findProviderClass("com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration", true));
            Class<?> clsFindProviderClass = ObjectFactory.findProviderClass("com.sun.org.apache.xerces.internal.parsers.StandardParserConfiguration", true);
            Object objNewInstance = clsFindProviderClass.getConstructor(null).newInstance(null);
            this.fPullParserConfig = objNewInstance;
            this.fIncrementalParser = (SAXParser) constructor.newInstance(objNewInstance);
            Class<?> clsFindProviderClass2 = ObjectFactory.findProviderClass("com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource", true);
            this.fConfigSetInput = clsFindProviderClass.getMethod("setInputSource", clsFindProviderClass2);
            this.fConfigInputSourceCtor = clsFindProviderClass2.getConstructor(String.class, String.class, String.class);
            this.fConfigSetByteStream = clsFindProviderClass2.getMethod("setByteStream", InputStream.class);
            this.fConfigSetCharStream = clsFindProviderClass2.getMethod("setCharacterStream", Reader.class);
            this.fConfigSetEncoding = clsFindProviderClass2.getMethod("setEncoding", String.class);
            this.fConfigParse = clsFindProviderClass.getMethod("parse", Boolean.TYPE);
            this.fReset = this.fIncrementalParser.getClass().getMethod(Constants.RESET, null);
        } catch (Exception unused) {
            IncrementalSAXSource_Xerces incrementalSAXSource_Xerces = new IncrementalSAXSource_Xerces(new SAXParser());
            this.fParseSomeSetup = incrementalSAXSource_Xerces.fParseSomeSetup;
            this.fParseSome = incrementalSAXSource_Xerces.fParseSome;
            this.fIncrementalParser = incrementalSAXSource_Xerces.fIncrementalParser;
        }
    }

    @Deprecated
    public static void _main(String[] strArr) {
        System.out.println("Starting...");
        if (new CoroutineManager().co_joinCoroutineSet(-1) == -1) {
            System.out.println("ERROR: Couldn't allocate coroutine number.\n");
            return;
        }
        IncrementalSAXSource incrementalSAXSourceCreateIncrementalSAXSource = createIncrementalSAXSource();
        XMLSerializer xMLSerializer = new XMLSerializer(System.out, (OutputFormat) null);
        incrementalSAXSourceCreateIncrementalSAXSource.setContentHandler(xMLSerializer);
        incrementalSAXSourceCreateIncrementalSAXSource.setLexicalHandler(xMLSerializer);
        int i = 0;
        while (i < strArr.length) {
            try {
                incrementalSAXSourceCreateIncrementalSAXSource.startParse(new InputSource(strArr[i]));
                Object objDeliverMoreNodes = incrementalSAXSourceCreateIncrementalSAXSource.deliverMoreNodes(true);
                boolean z = true;
                while (objDeliverMoreNodes == Boolean.TRUE) {
                    System.out.println("\nSome parsing successful, trying more.\n");
                    int i2 = i + 1;
                    if (i2 < strArr.length && "!".equals(strArr[i2])) {
                        z = false;
                        i = i2;
                    }
                    objDeliverMoreNodes = incrementalSAXSourceCreateIncrementalSAXSource.deliverMoreNodes(z);
                }
                if ((objDeliverMoreNodes instanceof Boolean) && ((Boolean) objDeliverMoreNodes) == Boolean.FALSE) {
                    System.out.println("\nParser ended (EOF or on request).\n");
                } else if (objDeliverMoreNodes == null) {
                    System.out.println("\nUNEXPECTED: Parser says shut down prematurely.\n");
                } else if (objDeliverMoreNodes instanceof Exception) {
                    throw new WrappedRuntimeException((Exception) objDeliverMoreNodes);
                }
            } catch (SAXException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public static IncrementalSAXSource createIncrementalSAXSource() {
        try {
            return new IncrementalSAXSource_Xerces();
        } catch (NoSuchMethodException unused) {
            IncrementalSAXSource_Filter incrementalSAXSource_Filter = new IncrementalSAXSource_Filter();
            incrementalSAXSource_Filter.setXMLReader(new SAXParser());
            return incrementalSAXSource_Filter;
        }
    }

    private boolean parseSome() throws IllegalAccessException, SAXException, IOException, InvocationTargetException {
        return this.fConfigSetInput != null ? ((Boolean) this.fConfigParse.invoke(this.fPullParserConfig, parmsfalse)).booleanValue() : ((Boolean) this.fParseSome.invoke(this.fIncrementalParser, noparms)).booleanValue();
    }

    private boolean parseSomeSetup(InputSource inputSource) throws IllegalAccessException, SAXException, InstantiationException, IOException, InvocationTargetException {
        if (this.fConfigSetInput == null) {
            return ((Boolean) this.fParseSomeSetup.invoke(this.fIncrementalParser, inputSource)).booleanValue();
        }
        Object objNewInstance = this.fConfigInputSourceCtor.newInstance(inputSource.getPublicId(), inputSource.getSystemId(), null);
        Object[] objArr = {inputSource.getByteStream()};
        this.fConfigSetByteStream.invoke(objNewInstance, objArr);
        objArr[0] = inputSource.getCharacterStream();
        this.fConfigSetCharStream.invoke(objNewInstance, objArr);
        objArr[0] = inputSource.getEncoding();
        this.fConfigSetEncoding.invoke(objNewInstance, objArr);
        this.fReset.invoke(this.fIncrementalParser, null);
        objArr[0] = objNewInstance;
        this.fConfigSetInput.invoke(this.fPullParserConfig, objArr);
        return parseSome();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public Object deliverMoreNodes(boolean z) {
        if (!z) {
            this.fParseInProgress = false;
            return Boolean.FALSE;
        }
        try {
            return parseSome() ? Boolean.TRUE : Boolean.FALSE;
        } catch (IOException | SAXException e) {
            return e;
        } catch (Exception e2) {
            return new SAXException(e2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void setContentHandler(ContentHandler contentHandler) {
        this.fIncrementalParser.setContentHandler(contentHandler);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void setDTDHandler(DTDHandler dTDHandler) {
        this.fIncrementalParser.setDTDHandler(dTDHandler);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        try {
            this.fIncrementalParser.setProperty("http://xml.org/sax/properties/lexical-handler", lexicalHandler);
        } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void startParse(InputSource inputSource) throws SAXException {
        if (this.fIncrementalParser == null) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_STARTPARSE_NEEDS_SAXPARSER", null));
        }
        if (this.fParseInProgress) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_STARTPARSE_WHILE_PARSING", null));
        }
        try {
            if (!parseSomeSetup(inputSource)) {
                throw new SAXException(XMLMessages.createXMLMessage("ER_COULD_NOT_INIT_PARSER", null));
            }
        } catch (Exception e) {
            x73.a(e);
        }
    }

    public static IncrementalSAXSource createIncrementalSAXSource(SAXParser sAXParser) {
        try {
            return new IncrementalSAXSource_Xerces(sAXParser);
        } catch (NoSuchMethodException unused) {
            IncrementalSAXSource_Filter incrementalSAXSource_Filter = new IncrementalSAXSource_Filter();
            incrementalSAXSource_Filter.setXMLReader(sAXParser);
            return incrementalSAXSource_Filter;
        }
    }

    public IncrementalSAXSource_Xerces(SAXParser sAXParser) throws NoSuchMethodException {
        this.fParseSomeSetup = null;
        this.fParseSome = null;
        this.fPullParserConfig = null;
        this.fConfigSetInput = null;
        this.fConfigParse = null;
        this.fSetInputSource = null;
        this.fConfigInputSourceCtor = null;
        this.fConfigSetByteStream = null;
        this.fConfigSetCharStream = null;
        this.fConfigSetEncoding = null;
        this.fReset = null;
        this.fParseInProgress = false;
        this.fIncrementalParser = sAXParser;
        Class<?> cls = sAXParser.getClass();
        this.fParseSomeSetup = cls.getMethod("parseSomeSetup", InputSource.class);
        this.fParseSome = cls.getMethod("parseSome", null);
    }
}
