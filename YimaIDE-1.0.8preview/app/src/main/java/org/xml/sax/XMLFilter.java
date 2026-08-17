package org.xml.sax;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface XMLFilter extends XMLReader {
    XMLReader getParent();

    void setParent(XMLReader xMLReader);
}
