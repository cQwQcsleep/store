package com.sun.org.apache.xalan.internal.xsltc.runtime.output;

import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2DOM;
import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXEventWriter;
import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXStreamWriter;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.serializer.ToHTMLSAXHandler;
import com.sun.org.apache.xml.internal.serializer.ToHTMLStream;
import com.sun.org.apache.xml.internal.serializer.ToTextSAXHandler;
import com.sun.org.apache.xml.internal.serializer.ToTextStream;
import com.sun.org.apache.xml.internal.serializer.ToUnknownStream;
import com.sun.org.apache.xml.internal.serializer.ToXMLSAXHandler;
import com.sun.org.apache.xml.internal.serializer.ToXMLStream;
import com.sun.xml.internal.stream.writers.WriterUtility;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.ErrorListener;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransletOutputHandlerFactory {
    public static final int DOM = 2;
    public static final int SAX = 1;
    public static final int STAX = 3;
    public static final int STREAM = 0;
    private ErrorListener _errListener;
    private boolean _overrideDefaultParser;
    private String _encoding = WriterUtility.UTF_8;
    private String _method = null;
    private int _outputType = 0;
    private OutputStream _ostream = System.out;
    private Writer _writer = null;
    private Node _node = null;
    private Node _nextSibling = null;
    private XMLEventWriter _xmlStAXEventWriter = null;
    private XMLStreamWriter _xmlStAXStreamWriter = null;
    private int _indentNumber = -1;
    private ContentHandler _handler = null;
    private LexicalHandler _lexHandler = null;

    public TransletOutputHandlerFactory(boolean z, ErrorListener errorListener) {
        this._overrideDefaultParser = z;
        this._errListener = errorListener;
    }

    public static TransletOutputHandlerFactory newInstance(boolean z, ErrorListener errorListener) {
        return new TransletOutputHandlerFactory(z, errorListener);
    }

    public Node getNode() {
        ContentHandler contentHandler = this._handler;
        if (contentHandler instanceof SAX2DOM) {
            return ((SAX2DOM) contentHandler).getDOM();
        }
        return null;
    }

    public SerializationHandler getSerializationHandler() throws ParserConfigurationException, IOException {
        int i;
        int i2 = this._outputType;
        SerializationHandler toTextStream = null;
        if (i2 == 0) {
            String str = this._method;
            if (str == null) {
                toTextStream = new ToUnknownStream(this._errListener);
            } else if (str.equalsIgnoreCase("xml")) {
                toTextStream = new ToXMLStream(this._errListener);
            } else if (this._method.equalsIgnoreCase("html")) {
                toTextStream = new ToHTMLStream(this._errListener);
            } else if (this._method.equalsIgnoreCase("text")) {
                toTextStream = new ToTextStream(this._errListener);
            }
            if (toTextStream != null && (i = this._indentNumber) >= 0) {
                toTextStream.setIndentAmount(i);
            }
            toTextStream.setEncoding(this._encoding);
            Writer writer = this._writer;
            if (writer != null) {
                toTextStream.setWriter(writer);
                return toTextStream;
            }
            toTextStream.setOutputStream(this._ostream);
            return toTextStream;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                Node node = this._node;
                SAX2DOM sax2dom = node != null ? new SAX2DOM(node, this._nextSibling, this._overrideDefaultParser) : new SAX2DOM(this._overrideDefaultParser);
                this._handler = sax2dom;
                this._lexHandler = sax2dom;
            } else if (i2 != 3) {
                return null;
            }
            XMLEventWriter xMLEventWriter = this._xmlStAXEventWriter;
            if (xMLEventWriter != null) {
                this._handler = new SAX2StAXEventWriter(xMLEventWriter);
            } else {
                XMLStreamWriter xMLStreamWriter = this._xmlStAXStreamWriter;
                if (xMLStreamWriter != null) {
                    this._handler = new SAX2StAXStreamWriter(xMLStreamWriter);
                }
            }
            this._lexHandler = (LexicalHandler) this._handler;
        }
        if (this._method == null) {
            this._method = "xml";
        }
        if (this._method.equalsIgnoreCase("xml")) {
            LexicalHandler lexicalHandler = this._lexHandler;
            ContentHandler contentHandler = this._handler;
            return lexicalHandler == null ? new ToXMLSAXHandler(contentHandler, this._encoding) : new ToXMLSAXHandler(contentHandler, lexicalHandler, this._encoding);
        }
        if (this._method.equalsIgnoreCase("html")) {
            LexicalHandler lexicalHandler2 = this._lexHandler;
            ContentHandler contentHandler2 = this._handler;
            return lexicalHandler2 == null ? new ToHTMLSAXHandler(contentHandler2, this._encoding) : new ToHTMLSAXHandler(contentHandler2, lexicalHandler2, this._encoding);
        }
        if (!this._method.equalsIgnoreCase("text")) {
            return null;
        }
        LexicalHandler lexicalHandler3 = this._lexHandler;
        ContentHandler contentHandler3 = this._handler;
        return lexicalHandler3 == null ? new ToTextSAXHandler(contentHandler3, this._encoding) : new ToTextSAXHandler(contentHandler3, lexicalHandler3, this._encoding);
    }

    public XMLEventWriter getXMLEventWriter() {
        ContentHandler contentHandler = this._handler;
        if (contentHandler instanceof SAX2StAXEventWriter) {
            return ((SAX2StAXEventWriter) contentHandler).getEventWriter();
        }
        return null;
    }

    public XMLStreamWriter getXMLStreamWriter() {
        ContentHandler contentHandler = this._handler;
        if (contentHandler instanceof SAX2StAXStreamWriter) {
            return ((SAX2StAXStreamWriter) contentHandler).getStreamWriter();
        }
        return null;
    }

    public void setEncoding(String str) {
        if (str != null) {
            this._encoding = str;
        }
    }

    public void setHandler(ContentHandler contentHandler) {
        this._handler = contentHandler;
    }

    public void setIndentNumber(int i) {
        this._indentNumber = i;
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        this._lexHandler = lexicalHandler;
    }

    public void setNextSibling(Node node) {
        this._nextSibling = node;
    }

    public void setNode(Node node) {
        this._node = node;
    }

    public void setOutputMethod(String str) {
        this._method = str;
    }

    public void setOutputStream(OutputStream outputStream) {
        this._ostream = outputStream;
    }

    public void setOutputType(int i) {
        this._outputType = i;
    }

    public void setWriter(Writer writer) {
        this._writer = writer;
    }

    public void setXMLEventWriter(XMLEventWriter xMLEventWriter) {
        this._xmlStAXEventWriter = xMLEventWriter;
    }

    public void setXMLStreamWriter(XMLStreamWriter xMLStreamWriter) {
        this._xmlStAXStreamWriter = xMLStreamWriter;
    }
}
