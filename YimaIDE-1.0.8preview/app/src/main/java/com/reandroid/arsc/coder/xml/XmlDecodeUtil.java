package com.reandroid.arsc.coder.xml;

import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlDecodeUtil {
    static final int INDENT_BAG = 4;
    static final int INDENT_ENTRY = 2;
    static final int INDENT_ROOT = 0;

    public static void bagIndent(XmlSerializer xmlSerializer) throws IOException {
        writeTagIndent(xmlSerializer, 4);
    }

    public static void entryIndent(XmlSerializer xmlSerializer) throws IOException {
        writeTagIndent(xmlSerializer, 2);
    }

    public static void rootIndent(XmlSerializer xmlSerializer) throws IOException {
        writeTagIndent(xmlSerializer, 0);
    }

    public static void writeTagIndent(XmlSerializer xmlSerializer, int i) throws IOException {
        if (i < 0) {
            return;
        }
        StringBuilder sb = new StringBuilder("\n");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        xmlSerializer.text(sb.toString());
    }
}
