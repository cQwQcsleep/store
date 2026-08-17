package org.eclipse.jdt.internal.compiler.util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class GenericXMLWriter extends PrintWriter {
    private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private final String lineSeparator;
    private int tab;

    public GenericXMLWriter(Writer writer, String str, boolean z) {
        super(writer);
        this.tab = 0;
        this.lineSeparator = str;
        if (z) {
            print(XML_VERSION);
            print(str);
        }
    }

    private static void appendEscapedChar(StringBuilder sb, char c) {
        String replacement = getReplacement(c);
        if (replacement == null) {
            sb.append(c);
            return;
        }
        sb.append('&');
        sb.append(replacement);
        sb.append(';');
    }

    private static String getEscaped(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 10);
        for (int i = 0; i < str.length(); i++) {
            appendEscapedChar(sb, str.charAt(i));
        }
        return sb.toString();
    }

    private static String getReplacement(char c) {
        if (c == '\"') {
            return "quot";
        }
        if (c == '<') {
            return "lt";
        }
        if (c == '>') {
            return "gt";
        }
        if (c == '&') {
            return "amp";
        }
        if (c != '\'') {
            return null;
        }
        return "apos";
    }

    private void printTabulation() {
        for (int i = 0; i < this.tab; i++) {
            print('\t');
        }
    }

    public void endTag(String str, boolean z, boolean z2) {
        this.tab--;
        printTag("/" + str, null, z, z2, false);
    }

    public void printString(String str, boolean z, boolean z2) {
        if (z) {
            printTabulation();
        }
        print(str);
        if (z2) {
            print(this.lineSeparator);
        }
    }

    public void printTag(String str, HashMap map, boolean z, boolean z2, boolean z3) {
        if (z) {
            printTabulation();
        }
        print(Util.C_GENERIC_START);
        print(str);
        if (map != null) {
            int size = map.size();
            Map.Entry[] entryArr = new Map.Entry[size];
            map.entrySet().toArray(entryArr);
            Arrays.sort(entryArr, new Comparator() { // from class: org.eclipse.jdt.internal.compiler.util.GenericXMLWriter.1
                @Override // java.util.Comparator
                public int compare(Object obj, Object obj2) {
                    return ((String) ((Map.Entry) obj).getKey()).compareTo((String) ((Map.Entry) obj2).getKey());
                }
            });
            for (int i = 0; i < size; i++) {
                print(' ');
                print(entryArr[i].getKey());
                print("=\"");
                print(getEscaped(String.valueOf(entryArr[i].getValue())));
                print('\"');
            }
        }
        if (z3) {
            print("/>");
        } else {
            print(">");
        }
        if (z2) {
            print(this.lineSeparator);
        }
        if (map == null || z3) {
            return;
        }
        this.tab++;
    }

    public void startTag(String str, boolean z) {
        printTag(str, null, z, true, false);
        this.tab++;
    }

    public GenericXMLWriter(OutputStream outputStream, String str, boolean z) {
        this(new PrintWriter(outputStream), str, z);
    }
}
