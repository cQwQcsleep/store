package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xpath.internal.NodeSet;
import java.util.StringTokenizer;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExsltStrings extends ExsltBase {
    public static String align(String str, String str2, String str3) {
        if (str.length() >= str2.length()) {
            return str.substring(0, str2.length());
        }
        if (str3.equals("right")) {
            return str2.substring(0, str2.length() - str.length()).concat(str);
        }
        if (!str3.equals("center")) {
            return str.concat(str2.substring(str.length()));
        }
        int length = (str2.length() - str.length()) / 2;
        return str2.substring(0, length) + str + str2.substring(length + str.length());
    }

    public static String concat(NodeList nodeList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < nodeList.getLength(); i++) {
            String string = ExsltBase.toString(nodeList.item(i));
            if (string != null && string.length() > 0) {
                stringBuffer.append(string);
            }
        }
        return stringBuffer.toString();
    }

    public static String padding(double d, String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = (int) d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (i2 == str.length()) {
                i2 = 0;
            }
            stringBuffer.append(str.charAt(i2));
            i2++;
        }
        return stringBuffer.toString();
    }

    public static NodeList split(String str, String str2) {
        int length;
        String strSubstring;
        NodeSet nodeSet = new NodeSet();
        nodeSet.setShouldCacheNodes(true);
        boolean z = false;
        int i = 0;
        while (!z && i < str.length()) {
            int iIndexOf = str.indexOf(str2, i);
            if (iIndexOf >= 0) {
                strSubstring = str.substring(i, iIndexOf);
                length = iIndexOf + str2.length();
            } else {
                length = i;
                strSubstring = str.substring(i);
                z = true;
            }
            Document dOMDocument = JdkXmlUtils.getDOMDocument();
            synchronized (dOMDocument) {
                Element elementCreateElement = dOMDocument.createElement(SchemaSymbols.ATTVAL_TOKEN);
                elementCreateElement.appendChild(dOMDocument.createTextNode(strSubstring));
                nodeSet.addNode(elementCreateElement);
            }
            i = length;
        }
        return nodeSet;
    }

    public static NodeList tokenize(String str, String str2) {
        NodeSet nodeSet = new NodeSet();
        if (str2 != null && str2.length() > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
            Document dOMDocument = JdkXmlUtils.getDOMDocument();
            synchronized (dOMDocument) {
                while (stringTokenizer.hasMoreTokens()) {
                    try {
                        Element elementCreateElement = dOMDocument.createElement(SchemaSymbols.ATTVAL_TOKEN);
                        elementCreateElement.appendChild(dOMDocument.createTextNode(stringTokenizer.nextToken()));
                        nodeSet.addNode(elementCreateElement);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return nodeSet;
        }
        Document dOMDocument2 = JdkXmlUtils.getDOMDocument();
        synchronized (dOMDocument2) {
            int i = 0;
            while (i < str.length()) {
                try {
                    Element elementCreateElement2 = dOMDocument2.createElement(SchemaSymbols.ATTVAL_TOKEN);
                    int i2 = i + 1;
                    elementCreateElement2.appendChild(dOMDocument2.createTextNode(str.substring(i, i2)));
                    nodeSet.addNode(elementCreateElement2);
                    i = i2;
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        return nodeSet;
    }

    public static String padding(double d) {
        return padding(d, " ");
    }

    public static NodeList split(String str) {
        return split(str, " ");
    }

    public static NodeList tokenize(String str) {
        return tokenize(str, " \t\n\r");
    }

    public static String align(String str, String str2) {
        return align(str, str2, "left");
    }
}
