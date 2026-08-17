package com.reandroid.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XmlSerializerWrapper implements XmlSerializer {
    private final XmlSerializer baseSerializer;

    public XmlSerializerWrapper(XmlSerializer xmlSerializer) {
        this.baseSerializer = xmlSerializer;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer attribute(String str, String str2, String str3) throws IllegalStateException, IOException, IllegalArgumentException {
        return getBaseSerializer().attribute(str, str2, str3);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void cdsect(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().cdsect(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void comment(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().comment(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void docdecl(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().docdecl(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void endDocument() throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().endDocument();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer endTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        return getBaseSerializer().endTag(str, str2);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void entityRef(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().entityRef(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void flush() throws IOException {
        getBaseSerializer().flush();
    }

    public XmlSerializer getBaseSerializer() {
        return this.baseSerializer;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public int getDepth() {
        return getBaseSerializer().getDepth();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public boolean getFeature(String str) {
        return getBaseSerializer().getFeature(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getName() {
        return getBaseSerializer().getName();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getNamespace() {
        return getBaseSerializer().getNamespace();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getPrefix(String str, boolean z) throws IllegalArgumentException {
        return getBaseSerializer().getPrefix(str, z);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public Object getProperty(String str) {
        return getBaseSerializer().getProperty(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void ignorableWhitespace(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().ignorableWhitespace(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void processingInstruction(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().processingInstruction(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setFeature(String str, boolean z) throws IllegalStateException, IllegalArgumentException {
        getBaseSerializer().setFeature(str, z);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(OutputStream outputStream, String str) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().setOutput(outputStream, str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setPrefix(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().setPrefix(str, str2);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setProperty(String str, Object obj) throws IllegalStateException, IllegalArgumentException {
        getBaseSerializer().setProperty(str, obj);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void startDocument(String str, Boolean bool) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().startDocument(str, bool);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer startTag(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        return getBaseSerializer().startTag(str, str2);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        return getBaseSerializer().text(str);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(Writer writer) throws IllegalStateException, IOException, IllegalArgumentException {
        getBaseSerializer().setOutput(writer);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(char[] cArr, int i, int i2) throws IllegalStateException, IOException, IllegalArgumentException {
        return getBaseSerializer().text(cArr, i, i2);
    }
}
