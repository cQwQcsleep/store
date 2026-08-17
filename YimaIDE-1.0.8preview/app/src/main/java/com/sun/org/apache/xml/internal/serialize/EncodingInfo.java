package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.util.EncodingMap;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class EncodingInfo {
    CharsetEncoder fCharsetEncoder = null;
    boolean fHaveTriedCharsetEncoder = false;
    String ianaName;
    String javaName;
    int lastPrintable;

    public EncodingInfo(String str, String str2, int i) {
        this.ianaName = str;
        this.javaName = EncodingMap.getIANA2JavaMapping(str);
        this.lastPrintable = i;
    }

    private boolean isPrintable0(char c) {
        if (this.fCharsetEncoder == null && !this.fHaveTriedCharsetEncoder) {
            try {
                Charset charsetForName = Charset.forName(this.javaName);
                if (charsetForName.canEncode()) {
                    this.fCharsetEncoder = charsetForName.newEncoder();
                } else {
                    this.fHaveTriedCharsetEncoder = true;
                }
            } catch (Exception unused) {
                this.fHaveTriedCharsetEncoder = true;
            }
        }
        CharsetEncoder charsetEncoder = this.fCharsetEncoder;
        if (charsetEncoder != null) {
            try {
                return charsetEncoder.canEncode(c);
            } catch (Exception unused2) {
                this.fCharsetEncoder = null;
                this.fHaveTriedCharsetEncoder = false;
            }
        }
        return false;
    }

    public static void testJavaEncodingName(String str) throws UnsupportedEncodingException {
        new String("valid".getBytes(), str);
    }

    public String getIANAName() {
        return this.ianaName;
    }

    public Writer getWriter(OutputStream outputStream) throws UnsupportedEncodingException {
        if (this.javaName != null) {
            return new OutputStreamWriter(outputStream, this.javaName);
        }
        String iANA2JavaMapping = EncodingMap.getIANA2JavaMapping(this.ianaName);
        this.javaName = iANA2JavaMapping;
        return iANA2JavaMapping == null ? new OutputStreamWriter(outputStream, "UTF8") : new OutputStreamWriter(outputStream, this.javaName);
    }

    public boolean isPrintable(char c) {
        if (c <= this.lastPrintable) {
            return true;
        }
        return isPrintable0(c);
    }
}
