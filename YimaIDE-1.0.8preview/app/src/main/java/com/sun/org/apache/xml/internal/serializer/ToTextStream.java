package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import defpackage.x73;
import java.io.IOException;
import java.io.Writer;
import javax.xml.transform.ErrorListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ToTextStream extends ToStream {
    public ToTextStream() {
        this(null);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addUniqueAttribute(String str, String str2, int i) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public void cdata(char[] cArr, int i, int i2) throws SAXException {
        try {
            writeNormalizedChars(cArr, i, i2, this.m_lineSepUse);
            if (this.m_tracer != null) {
                super.fireCDATAEvent(cArr, i, i2);
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        flushPending();
        try {
            if (inTemporaryOutputState()) {
                this.m_writer.write(cArr, i, i2);
            } else {
                writeNormalizedChars(cArr, i, i2, this.m_lineSepUse);
            }
            if (this.m_tracer != null) {
                super.fireCharEvent(cArr, i, i2);
            }
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream
    public void charactersRaw(char[] cArr, int i, int i2) throws SAXException {
        try {
            writeNormalizedChars(cArr, i, i2, this.m_lineSepUse);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        int length = str.length();
        if (length > this.m_charsBuff.length) {
            this.m_charsBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_charsBuff, 0);
        comment(this.m_charsBuff, 0, length);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        flushPending();
        flushWriter();
        if (this.m_tracer != null) {
            super.fireEndDoc();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        if (this.m_tracer != null) {
            super.fireEndElem(str3);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void entityReference(String str) throws SAXException {
        if (this.m_tracer != null) {
            super.fireEntityReference(str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void flushPending() throws SAXException {
        if (this.m_needToCallStartDocument) {
            startDocumentInternal();
            this.m_needToCallStartDocument = false;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        try {
            writeNormalizedChars(cArr, i, i2, this.m_lineSepUse);
        } catch (IOException e) {
            x73.a(e);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) throws SAXException {
        flushPending();
        if (this.m_tracer != null) {
            super.fireEscapingEvent(str, str2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializerBase
    public void startDocumentInternal() throws SAXException {
        super.startDocumentInternal();
        this.m_needToCallStartDocument = false;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void startElement(String str, String str2, String str3) throws SAXException {
        if (this.m_needToCallStartDocument) {
            startDocumentInternal();
        }
        if (this.m_tracer != null) {
            super.fireStartElem(str3);
            firePseudoAttributes();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public boolean startPrefixMapping(String str, String str2, boolean z) throws SAXException {
        return false;
    }

    public void writeNormalizedChars(char[] cArr, int i, int i2, boolean z) throws SAXException, IOException {
        String encoding = getEncoding();
        Writer writer = this.m_writer;
        int i3 = i2 + i;
        while (i < i3) {
            char c = cArr[i];
            if ('\n' == c && z) {
                writer.write(this.m_lineSep, 0, this.m_lineSepLen);
            } else if (this.m_encodingInfo.isInEncoding(c)) {
                writer.write(c);
            } else if (Encodings.isHighUTF16Surrogate(c) || Encodings.isLowUTF16Surrogate(c)) {
                int iWriteUTF16Surrogate = writeUTF16Surrogate(c, cArr, i, i3);
                if (iWriteUTF16Surrogate >= 0) {
                    if (Encodings.isHighUTF16Surrogate(c)) {
                        i++;
                    }
                    if (iWriteUTF16Surrogate > 0) {
                        System.err.println(Utils.messages.createMessage("ER_ILLEGAL_CHARACTER", new Object[]{Integer.toString(iWriteUTF16Surrogate), encoding}));
                    }
                }
            } else if (encoding != null) {
                writer.write(38);
                writer.write(35);
                writer.write(Integer.toString(c));
                writer.write(59);
                System.err.println(Utils.messages.createMessage("ER_ILLEGAL_CHARACTER", new Object[]{Integer.toString(c), encoding}));
            } else {
                writer.write(c);
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
    }

    public ToTextStream(ErrorListener errorListener) {
        super(errorListener);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void endElement(String str) throws SAXException {
        if (this.m_tracer != null) {
            super.fireEndElem(str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (this.m_tracer != null) {
            super.fireStartElem(str3);
            firePseudoAttributes();
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, org.xml.sax.ext.LexicalHandler
    public void comment(char[] cArr, int i, int i2) throws SAXException {
        flushPending();
        if (this.m_tracer != null) {
            super.fireCommentEvent(cArr, i, i2);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ToStream, com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(String str) throws SAXException {
        int length = str.length();
        if (length > this.m_charsBuff.length) {
            this.m_charsBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_charsBuff, 0);
        characters(this.m_charsBuff, 0, length);
    }
}
