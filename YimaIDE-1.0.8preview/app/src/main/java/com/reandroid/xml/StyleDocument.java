package com.reandroid.xml;

import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.io.IOUtil;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleDocument extends XMLDocument implements SpanSet<StyleElement>, Comparable<StyleDocument> {
    private static final XmlPullParser PARSER = XMLFactory.newPullParser();

    public static StyleDocument copyInner(XMLElement xMLElement) {
        StyleDocument styleDocument = new StyleDocument();
        for (XMLNode xMLNode : xMLElement) {
            if (xMLNode instanceof XMLElement) {
                styleDocument.newElement().copyFrom((XMLElement) xMLNode);
            } else if (xMLNode instanceof XMLText) {
                styleDocument.newText(((XMLText) xMLNode).getText());
            }
        }
        return styleDocument;
    }

    public static StyleDocument create(String str) {
        try {
            return parseStyledString(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static StyleDocument parseNext(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        StyleDocument styleDocument = new StyleDocument();
        styleDocument.parseInner(xmlPullParser);
        return styleDocument;
    }

    public static StyleDocument parseStyledString(String str) throws XmlPullParserException, IOException {
        StyleDocument styleDocument = new StyleDocument();
        styleDocument.parseString(str);
        return styleDocument;
    }

    @Override // java.lang.Comparable
    public int compareTo(StyleDocument styleDocument) {
        if (styleDocument == null) {
            return 0;
        }
        return getStyledString().compareTo(styleDocument.getStyledString());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StyleDocument) {
            return getXml().equals(((StyleDocument) obj).getXml());
        }
        return false;
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public Iterator<StyleElement> getElements() {
        return iterator(StyleElement.class);
    }

    public String getHtml() {
        return getText(false, false);
    }

    @Override // com.reandroid.xml.SpanSet
    public Iterator<StyleElement> getSpans() {
        return InstanceIterator.of(recursiveNodes(), StyleElement.class);
    }

    public Iterator<StyleText> getStyleTexts() {
        return InstanceIterator.of(recursiveNodes(), StyleText.class);
    }

    public String getStyledString() {
        StringWriter stringWriter = new StringWriter();
        try {
            writeStyledText(stringWriter);
            stringWriter.flush();
            stringWriter.close();
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    public String getText(boolean z, boolean z2) {
        return toText(z, z2);
    }

    public String getXml() {
        return toText(true, false);
    }

    public boolean hasElements() {
        return getElements().hasNext();
    }

    public int hashCode() {
        return getXml().hashCode();
    }

    @Override // com.reandroid.xml.XMLDocument, com.reandroid.xml.XMLNodeTree
    public XMLComment newComment() {
        throw new IllegalArgumentException("Can not create comment at style document: " + getClass());
    }

    @Override // com.reandroid.xml.XMLDocument, com.reandroid.xml.base.NodeFactory
    public StyleElement newElement() {
        StyleElement styleElement = new StyleElement();
        add((XMLNode) styleElement);
        return styleElement;
    }

    @Override // com.reandroid.xml.XMLDocument, com.reandroid.xml.base.NodeFactory
    public StyleText newText() {
        StyleText styleText = new StyleText();
        add((XMLNode) styleText);
        return styleText;
    }

    public void parseString(String str) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser = PARSER;
        synchronized (xmlPullParser) {
            xmlPullParser.setInput(new StringReader("<parser>" + str + "</parser>"));
            XMLUtil.setFeatureRelaxed(xmlPullParser, true);
            XMLUtil.ensureStartTag(xmlPullParser);
            xmlPullParser.nextToken();
            parseInner(xmlPullParser);
            IOUtil.close(xmlPullParser);
        }
    }

    public void writeStyledText(Appendable appendable) throws IOException {
        for (XMLNode xMLNode : this) {
            if (xMLNode instanceof StyleText) {
                ((StyleText) xMLNode).writeStyledText(appendable);
            } else if (xMLNode instanceof StyleElement) {
                ((StyleElement) xMLNode).writeStyledText(appendable);
            }
        }
    }

    public String getXml(boolean z) {
        return toText(true, z);
    }

    @Override // com.reandroid.xml.XMLDocument, com.reandroid.xml.XMLNodeTree
    public StyleText newText(String str) {
        return (StyleText) super.newText(str);
    }
}
