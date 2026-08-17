package com.sun.org.apache.xml.internal.utils;

import java.util.Locale;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLStringDefault implements XMLString {
    private String m_str;

    public XMLStringDefault(String str) {
        this.m_str = str;
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public char charAt(int i) {
        return this.m_str.charAt(i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int compareTo(XMLString xMLString) {
        return this.m_str.compareTo(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int compareToIgnoreCase(XMLString xMLString) {
        return this.m_str.compareToIgnoreCase(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString concat(String str) {
        return new XMLStringDefault(this.m_str.concat(str));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public void dispatchAsComment(LexicalHandler lexicalHandler) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public void dispatchCharactersEvents(ContentHandler contentHandler) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean endsWith(String str) {
        return this.m_str.endsWith(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(XMLString xMLString) {
        return this.m_str.equals(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equalsIgnoreCase(String str) {
        return this.m_str.equalsIgnoreCase(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString fixWhiteSpace(boolean z, boolean z2, boolean z3) {
        return new XMLStringDefault(this.m_str.trim());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public void getChars(int i, int i2, char[] cArr, int i3) {
        while (i < i2) {
            cArr[i3] = this.m_str.charAt(i);
            i++;
            i3++;
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean hasString() {
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int hashCode() {
        return this.m_str.hashCode();
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(XMLString xMLString) {
        return this.m_str.indexOf(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(int i) {
        return this.m_str.lastIndexOf(i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int length() {
        return this.m_str.length();
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(XMLString xMLString, int i) {
        return this.m_str.startsWith(xMLString.toString(), i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString substring(int i) {
        return new XMLStringDefault(this.m_str.substring(i));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public double toDouble() {
        try {
            return Double.valueOf(this.m_str).doubleValue();
        } catch (NumberFormatException unused) {
            return Double.NaN;
        }
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toLowerCase(Locale locale) {
        return new XMLStringDefault(this.m_str.toLowerCase(locale));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public String toString() {
        return this.m_str;
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toUpperCase(Locale locale) {
        return new XMLStringDefault(this.m_str.toUpperCase(locale));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString trim() {
        return new XMLStringDefault(this.m_str.trim());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(int i, int i2) {
        return this.m_str.lastIndexOf(i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(String str) {
        return this.m_str.lastIndexOf(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int lastIndexOf(String str, int i) {
        return this.m_str.lastIndexOf(str, i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(String str) {
        return this.m_str.equals(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(int i, int i2) {
        return this.m_str.indexOf(i, i2);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(String str, int i) {
        return this.m_str.startsWith(str, i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean equals(Object obj) {
        return this.m_str.equals(obj);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(String str) {
        return this.m_str.indexOf(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(String str) {
        return this.m_str.startsWith(str);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString substring(int i, int i2) {
        return new XMLStringDefault(this.m_str.substring(i, i2));
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toLowerCase() {
        return new XMLStringDefault(this.m_str.toLowerCase());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public XMLString toUpperCase() {
        return new XMLStringDefault(this.m_str.toUpperCase());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(int i) {
        return this.m_str.indexOf(i);
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public boolean startsWith(XMLString xMLString) {
        return this.m_str.startsWith(xMLString.toString());
    }

    @Override // com.sun.org.apache.xml.internal.utils.XMLString
    public int indexOf(String str, int i) {
        return this.m_str.indexOf(str, i);
    }
}
