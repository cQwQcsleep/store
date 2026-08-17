package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;
import defpackage.x73;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class IncrementalSAXSource_Filter implements IncrementalSAXSource, ContentHandler, DTDHandler, LexicalHandler, ErrorHandler, Runnable {
    private int eventcounter;
    boolean DEBUG = false;
    private CoroutineManager fCoroutineManager = null;
    private int fControllerCoroutineID = -1;
    private int fSourceCoroutineID = -1;
    private ContentHandler clientContentHandler = null;
    private LexicalHandler clientLexicalHandler = null;
    private DTDHandler clientDTDHandler = null;
    private ErrorHandler clientErrorHandler = null;
    private int frequency = 5;
    private boolean fNoMoreEvents = false;
    private XMLReader fXMLReader = null;
    private InputSource fXMLReaderInputSource = null;

    public class StopException extends RuntimeException {
        static final long serialVersionUID = -1129245796185754956L;

        public StopException() {
        }
    }

    public IncrementalSAXSource_Filter() {
        init(new CoroutineManager(), -1, -1);
    }

    private void co_entry_pause() throws SAXException {
        if (this.fCoroutineManager == null) {
            init(null, -1, -1);
        }
        try {
            if (this.fCoroutineManager.co_entry_pause(this.fSourceCoroutineID) == Boolean.FALSE) {
                co_yield(false);
            }
        } catch (NoSuchMethodException e) {
            if (this.DEBUG) {
                e.printStackTrace();
            }
            x73.a(e);
        }
    }

    private void co_yield(boolean z) throws SAXException {
        if (this.fNoMoreEvents) {
            return;
        }
        try {
            Boolean bool = Boolean.FALSE;
            if ((z ? this.fCoroutineManager.co_resume(Boolean.TRUE, this.fSourceCoroutineID, this.fControllerCoroutineID) : bool) == bool) {
                this.fNoMoreEvents = true;
                if (this.fXMLReader != null) {
                    throw new StopException();
                }
                this.fCoroutineManager.co_exit_to(bool, this.fSourceCoroutineID, this.fControllerCoroutineID);
            }
        } catch (NoSuchMethodException e) {
            this.fNoMoreEvents = true;
            this.fCoroutineManager.co_exit(this.fSourceCoroutineID);
            x73.a(e);
        }
    }

    public static IncrementalSAXSource createIncrementalSAXSource(CoroutineManager coroutineManager, int i) {
        return new IncrementalSAXSource_Filter(coroutineManager, i);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        int i3 = this.eventcounter - 1;
        this.eventcounter = i3;
        if (i3 <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.characters(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.comment(cArr, i, i2);
        }
    }

    public void count_and_yield(boolean z) throws SAXException {
        if (!z) {
            this.eventcounter = 0;
        }
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public Object deliverMoreNodes(boolean z) {
        if (this.fNoMoreEvents) {
            return Boolean.FALSE;
        }
        try {
            Object objCo_resume = this.fCoroutineManager.co_resume(z ? Boolean.TRUE : Boolean.FALSE, this.fControllerCoroutineID, this.fSourceCoroutineID);
            if (objCo_resume == Boolean.FALSE) {
                this.fCoroutineManager.co_exit(this.fControllerCoroutineID);
            }
            return objCo_resume;
        } catch (NoSuchMethodException e) {
            return e;
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endCDATA();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endDTD();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.endDocument();
        }
        this.eventcounter = 0;
        co_yield(false);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.endElement(str, str2, str3);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.endEntity(str);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.endPrefixMapping(str);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        ErrorHandler errorHandler = this.clientErrorHandler;
        if (errorHandler != null) {
            errorHandler.error(sAXParseException);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        ErrorHandler errorHandler = this.clientErrorHandler;
        if (errorHandler != null) {
            errorHandler.error(sAXParseException);
        }
        this.eventcounter = 0;
        co_yield(false);
    }

    public int getControllerCoroutineID() {
        return this.fControllerCoroutineID;
    }

    public CoroutineManager getCoroutineManager() {
        return this.fCoroutineManager;
    }

    public int getSourceCoroutineID() {
        return this.fSourceCoroutineID;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        int i3 = this.eventcounter - 1;
        this.eventcounter = i3;
        if (i3 <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.ignorableWhitespace(cArr, i, i2);
        }
    }

    public void init(CoroutineManager coroutineManager, int i, int i2) {
        if (coroutineManager == null) {
            coroutineManager = new CoroutineManager();
        }
        this.fCoroutineManager = coroutineManager;
        this.fControllerCoroutineID = coroutineManager.co_joinCoroutineSet(i);
        int iCo_joinCoroutineSet = coroutineManager.co_joinCoroutineSet(i2);
        this.fSourceCoroutineID = iCo_joinCoroutineSet;
        if (this.fControllerCoroutineID == -1 || iCo_joinCoroutineSet == -1) {
            f63.a(XMLMessages.createXMLMessage("ER_COJOINROUTINESET_FAILED", null));
        } else {
            this.fNoMoreEvents = false;
            this.eventcounter = this.frequency;
        }
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
        DTDHandler dTDHandler = this.clientDTDHandler;
        if (dTDHandler != null) {
            dTDHandler.notationDecl(str, str2, str3);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.processingInstruction(str, str2);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.fXMLReader == null) {
            return;
        }
        if (this.DEBUG) {
            System.out.println("IncrementalSAXSource_Filter parse thread launched");
        }
        Object obj = Boolean.FALSE;
        try {
            this.fXMLReader.parse(this.fXMLReaderInputSource);
        } catch (StopException unused) {
            if (this.DEBUG) {
                System.out.println("Active IncrementalSAXSource_Filter normal stop exception");
            }
        } catch (IOException e) {
            obj = e;
        } catch (SAXException e2) {
            Exception exception = e2.getException();
            boolean z = exception instanceof StopException;
            boolean z2 = this.DEBUG;
            if (!z) {
                if (z2) {
                    System.out.println("Active IncrementalSAXSource_Filter UNEXPECTED SAX exception: " + exception);
                    exception.printStackTrace();
                }
                obj = e2;
            } else if (z2) {
                System.out.println("Active IncrementalSAXSource_Filter normal stop exception");
            }
        }
        this.fXMLReader = null;
        try {
            this.fNoMoreEvents = true;
            this.fCoroutineManager.co_exit_to(obj, this.fSourceCoroutineID, this.fControllerCoroutineID);
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace(System.err);
            this.fCoroutineManager.co_exit(this.fSourceCoroutineID);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void setContentHandler(ContentHandler contentHandler) {
        this.clientContentHandler = contentHandler;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void setDTDHandler(DTDHandler dTDHandler) {
        this.clientDTDHandler = dTDHandler;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.setDocumentLocator(locator);
        }
    }

    public void setErrHandler(ErrorHandler errorHandler) {
        this.clientErrorHandler = errorHandler;
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        this.clientLexicalHandler = lexicalHandler;
    }

    public void setReturnFrequency(int i) {
        if (i < 1) {
            i = 1;
        }
        this.eventcounter = i;
        this.frequency = i;
    }

    public void setXMLReader(XMLReader xMLReader) {
        this.fXMLReader = xMLReader;
        xMLReader.setContentHandler(this);
        xMLReader.setDTDHandler(this);
        xMLReader.setErrorHandler(this);
        try {
            xMLReader.setProperty("http://xml.org/sax/properties/lexical-handler", this);
        } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) throws SAXException {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.skippedEntity(str);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startCDATA();
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String str, String str2, String str3) throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startDTD(str, str2, str3);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        co_entry_pause();
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.startDocument();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.startElement(str, str2, str3, attributes);
        }
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String str) throws SAXException {
        LexicalHandler lexicalHandler = this.clientLexicalHandler;
        if (lexicalHandler != null) {
            lexicalHandler.startEntity(str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.IncrementalSAXSource
    public void startParse(InputSource inputSource) throws SAXException {
        if (this.fNoMoreEvents) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_INCRSAXSRCFILTER_NOT_RESTARTABLE", null));
        }
        if (this.fXMLReader == null) {
            throw new SAXException(XMLMessages.createXMLMessage("ER_XMLRDR_NOT_BEFORE_STARTPARSE", null));
        }
        this.fXMLReaderInputSource = inputSource;
        ThreadControllerWrapper.runThread(this, -1);
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        int i = this.eventcounter - 1;
        this.eventcounter = i;
        if (i <= 0) {
            co_yield(true);
            this.eventcounter = this.frequency;
        }
        ContentHandler contentHandler = this.clientContentHandler;
        if (contentHandler != null) {
            contentHandler.startPrefixMapping(str, str2);
        }
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
        DTDHandler dTDHandler = this.clientDTDHandler;
        if (dTDHandler != null) {
            dTDHandler.unparsedEntityDecl(str, str2, str3, str4);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        ErrorHandler errorHandler = this.clientErrorHandler;
        if (errorHandler != null) {
            errorHandler.error(sAXParseException);
        }
    }

    public IncrementalSAXSource_Filter(CoroutineManager coroutineManager, int i) {
        init(coroutineManager, i, -1);
    }
}
