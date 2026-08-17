package com.sun.org.apache.xml.internal.serializer;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
interface XSLOutputAttributes {
    String getDoctypePublic();

    String getDoctypeSystem();

    String getEncoding();

    boolean getIndent();

    int getIndentAmount();

    String getMediaType();

    boolean getOmitXMLDeclaration();

    String getOutputProperty(String str);

    String getOutputPropertyDefault(String str);

    String getStandalone();

    String getVersion();

    void setCdataSectionElements(List<String> list);

    void setDoctype(String str, String str2);

    void setDoctypePublic(String str);

    void setDoctypeSystem(String str);

    void setEncoding(String str);

    void setIndent(boolean z);

    void setMediaType(String str);

    void setOmitXMLDeclaration(boolean z);

    void setOutputProperty(String str, String str2);

    void setOutputPropertyDefault(String str, String str2);

    void setStandalone(String str);

    void setVersion(String str);
}
