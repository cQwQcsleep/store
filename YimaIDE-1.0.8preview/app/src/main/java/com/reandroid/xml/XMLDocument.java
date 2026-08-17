package com.reandroid.xml;

import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.xml.base.Document;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDocument extends XMLNodeTree implements Document<XMLElement> {
    private final XMLDocDeclaration declaration;

    public XMLDocument(String str) {
        this();
        setDocumentElement(new XMLElement(str));
    }

    public static XMLDocument load(String str) throws XmlPullParserException, IOException {
        XMLDocument xMLDocument = new XMLDocument();
        xMLDocument.parse(XMLFactory.newPullParser(str));
        return xMLDocument;
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void endSerialize(XmlSerializer xmlSerializer) {
        if (getDeclaration().isValid()) {
            try {
                xmlSerializer.endDocument();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public XMLDocDeclaration getDeclaration() {
        return this.declaration;
    }

    public XMLDocType getDocType() {
        return (XMLDocType) CollectionUtil.getFirst(iterator(XMLDocType.class));
    }

    @Override // com.reandroid.xml.base.Document
    public XMLElement getDocumentElement() {
        return (XMLElement) CollectionUtil.getFirst(iterator(XMLElement.class));
    }

    public XMLDocType getOrCreateDocType() {
        XMLDocType docType = getDocType();
        if (docType != null) {
            return docType;
        }
        XMLDocType xMLDocTypeNewDocType = newDocType();
        move(xMLDocTypeNewDocType, 0);
        return xMLDocTypeNewDocType;
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public XMLComment newComment() {
        XMLComment xMLComment = new XMLComment();
        add((XMLNode) xMLComment);
        return xMLComment;
    }

    @Override // com.reandroid.xml.base.NodeFactory
    public XMLElement newElement() {
        XMLElement xMLElement = new XMLElement();
        add((XMLNode) xMLElement);
        return xMLElement;
    }

    @Override // com.reandroid.xml.base.NodeFactory
    public XMLText newText() {
        XMLText xMLText = new XMLText();
        add((XMLNode) xMLText);
        return xMLText;
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void onEndParse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void onStartParse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 0) {
            clear();
            getDeclaration().parse(xmlPullParser);
        } else {
            if (XMLUtil.hasFeatureRelaxed(xmlPullParser)) {
                return;
            }
            XMLUtil.expectEvent(xmlPullParser, 0);
        }
    }

    public void setDocumentElement(XMLElement xMLElement) {
        clear();
        add((XMLNode) xMLElement);
    }

    public void setEncoding(String str) {
        getDeclaration().encoding(str);
    }

    public void setStandalone(Boolean bool) {
        getDeclaration().standalone(bool);
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public void startSerialize(XmlSerializer xmlSerializer) throws IOException {
        getDeclaration().serialize(xmlSerializer);
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        XMLDocDeclaration declaration = getDeclaration();
        if (declaration.isValid()) {
            appendable.append(declaration.toString());
        }
        Iterator<XMLNode> it = iterator();
        while (it.hasNext()) {
            it.next().write(appendable, z, z2);
        }
    }

    @Override // com.reandroid.xml.XMLNodeTree
    public XMLText newText(String str) {
        return super.newText(str);
    }

    public XMLDocument() {
        this.declaration = new XMLDocDeclaration();
    }

    public static XMLDocument load(InputStream inputStream) throws XmlPullParserException, IOException {
        XMLDocument xMLDocument = new XMLDocument();
        xMLDocument.parse(XMLFactory.newPullParser(inputStream));
        return xMLDocument;
    }

    public static XMLDocument load(File file) throws XmlPullParserException, IOException {
        XMLDocument xMLDocument = new XMLDocument();
        xMLDocument.parse(XMLFactory.newPullParser(file));
        return xMLDocument;
    }
}
