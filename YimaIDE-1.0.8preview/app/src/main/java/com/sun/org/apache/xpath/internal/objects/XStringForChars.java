package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.utils.FastStringBuffer;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XStringForChars extends XString {
    static final long serialVersionUID = -2235248887220850467L;
    int m_length;
    int m_start;
    protected String m_strCache;

    public XStringForChars(char[] cArr, int i, int i2) {
        super(cArr);
        this.m_strCache = null;
        this.m_start = i;
        this.m_length = i2;
        if (cArr != null) {
            return;
        }
        w01.a(XPATHMessages.createXPATHMessage("ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", null));
        throw null;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public void appendToFsb(FastStringBuffer fastStringBuffer) {
        fastStringBuffer.append((char[]) this.m_obj, this.m_start, this.m_length);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public char charAt(int i) {
        return ((char[]) this.m_obj)[i + this.m_start];
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public void dispatchAsComment(LexicalHandler lexicalHandler) throws SAXException {
        lexicalHandler.comment((char[]) this.m_obj, this.m_start, this.m_length);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xpath.internal.objects.XObject
    public void dispatchCharactersEvents(ContentHandler contentHandler) throws SAXException {
        contentHandler.characters((char[]) this.m_obj, this.m_start, this.m_length);
    }

    public FastStringBuffer fsb() {
        throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_FSB_NOT_SUPPORTED_XSTRINGFORCHARS", null));
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public void getChars(int i, int i2, char[] cArr, int i3) {
        System.arraycopy((char[]) this.m_obj, this.m_start + i, cArr, i3, i2);
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public boolean hasString() {
        return this.m_strCache != null;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xml.internal.utils.XMLString
    public int length() {
        return this.m_length;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XObject
    public Object object() {
        return str();
    }

    @Override // com.sun.org.apache.xpath.internal.objects.XString, com.sun.org.apache.xpath.internal.objects.XObject
    public String str() {
        if (this.m_strCache == null) {
            this.m_strCache = new String((char[]) this.m_obj, this.m_start, this.m_length);
        }
        return this.m_strCache;
    }

    private XStringForChars(String str) {
        super(str);
        this.m_strCache = null;
        throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_XSTRINGFORCHARS_CANNOT_TAKE_STRING", null));
    }
}
