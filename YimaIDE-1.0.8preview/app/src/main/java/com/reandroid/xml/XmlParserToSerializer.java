package com.reandroid.xml;

import java.io.Closeable;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XmlParserToSerializer {
    private int depth = 0;
    private boolean enableIndent = true;
    private final XmlPullParser parser;
    boolean processNamespace;
    boolean reportNamespaceAttrs;
    private XmlSerializer serializer;

    public XmlParserToSerializer(XmlPullParser xmlPullParser, XmlSerializer xmlSerializer) {
        this.parser = xmlPullParser;
        this.serializer = XmlIndentingSerializer.create(xmlSerializer);
        XMLUtil.setFeatureSafe(xmlPullParser, "http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        XMLUtil.setFeatureSafe(xmlPullParser, "http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes", true);
    }

    private void close() throws IOException {
        XmlPullParser xmlPullParser = this.parser;
        if (xmlPullParser instanceof Closeable) {
            ((Closeable) xmlPullParser).close();
        }
        XMLUtil.close(this.serializer);
    }

    private boolean nextEvent(int i) throws XmlPullParserException, IOException {
        boolean z = i >= 0;
        switch (i) {
            case 0:
                onStartDocument();
                this.depth++;
                return z;
            case 1:
                onEndDocument();
                int i2 = this.depth - 1;
                this.depth = i2;
                return i2 != 0;
            case 2:
                onStartTag();
                this.depth++;
                return z;
            case 3:
                onEndTag();
                int i3 = this.depth - 1;
                this.depth = i3;
                return i3 != 0;
            case 4:
                onText();
                return z;
            case 5:
                onCdsect();
                return z;
            case 6:
                onEntityRef();
                return z;
            case 7:
                onIgnorableWhitespace();
                return z;
            case 8:
                onProcessingInstruction();
                return z;
            case 9:
                onComment();
                return z;
            case 10:
                onDocDecl();
                return z;
            default:
                return z;
        }
    }

    private void onCdsect() throws IOException {
        this.serializer.cdsect(this.parser.getText());
    }

    private void onComment() throws IOException {
        this.serializer.comment(this.parser.getText());
    }

    private void onDocDecl() throws IOException {
        this.serializer.docdecl(this.parser.getText());
    }

    private void onEndDocument() throws IOException {
        this.serializer.endDocument();
    }

    private void onEndTag() throws IOException {
        this.serializer.endTag(this.parser.getNamespace(), this.parser.getName());
    }

    private void onEntityRef() throws IOException {
        this.serializer.entityRef(this.parser.getName());
    }

    private void onIgnorableWhitespace() throws IOException {
        this.serializer.ignorableWhitespace(this.parser.getText());
    }

    private void onProcessingInstruction() throws IOException {
        this.serializer.processingInstruction(this.parser.getText());
    }

    private void onStartDocument() throws IOException {
        this.serializer.startDocument(this.parser.getInputEncoding(), (Boolean) XMLUtil.getPropertySafe(this.parser, XMLUtil.PROPERTY_XMLDECL_STANDALONE));
    }

    private void onStartTag() throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser = this.parser;
        XmlSerializer xmlSerializer = this.serializer;
        boolean z = this.processNamespace;
        if (!z || !this.reportNamespaceAttrs) {
            int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth());
            for (int i = 0; i < namespaceCount; i++) {
                xmlSerializer.setPrefix(xmlPullParser.getNamespacePrefix(i), xmlPullParser.getNamespaceUri(i));
            }
        }
        xmlSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            xmlSerializer.attribute(z ? xmlPullParser.getAttributeNamespace(i2) : null, xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2));
        }
    }

    private void onText() throws IOException {
        this.serializer.text(this.parser.getText());
    }

    public void setEnableIndent(boolean z) {
        if (z == this.enableIndent) {
            return;
        }
        this.enableIndent = z;
        XmlSerializer xmlSerializer = this.serializer;
        if (z) {
            this.serializer = XmlIndentingSerializer.create(xmlSerializer);
        } else if (xmlSerializer instanceof XmlIndentingSerializer) {
            this.serializer = ((XmlIndentingSerializer) xmlSerializer).getBaseSerializer();
        }
    }

    public void write() throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser = this.parser;
        this.processNamespace = XMLUtil.getFeatureSafe(xmlPullParser, "http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        this.reportNamespaceAttrs = XMLUtil.getFeatureSafe(xmlPullParser, "http://xmlpull.org/v1/doc/features.html#report-namespace-prefixes", false);
        for (int iNextToken = xmlPullParser.nextToken(); nextEvent(iNextToken); iNextToken = xmlPullParser.nextToken()) {
        }
        this.serializer.flush();
        close();
    }
}
