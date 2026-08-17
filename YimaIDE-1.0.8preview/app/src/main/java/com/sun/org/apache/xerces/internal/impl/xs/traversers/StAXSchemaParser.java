package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOMParser;
import com.sun.org.apache.xerces.internal.util.JAXPNamespaceContextWrapper;
import com.sun.org.apache.xerces.internal.util.StAXLocationWrapper;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLStringBuffer;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.w3c.dom.Document;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class StAXSchemaParser {
    private static final int CHUNK_MASK = 1023;
    private static final int CHUNK_SIZE = 1024;
    private final QName fAttributeQName;
    private final XMLAttributesImpl fAttributes;
    private final List<String> fDeclaredPrefixes;
    private int fDepth;
    private final QName fElementQName;
    private final JAXPNamespaceContextWrapper fNamespaceContext;
    private SchemaDOMParser fSchemaDOMParser;
    private final XMLStringBuffer fStringBuffer;
    private SymbolTable fSymbolTable;
    private final XMLString fTempString;
    private final char[] fCharBuffer = new char[1024];
    private final StAXLocationWrapper fLocationWrapper = new StAXLocationWrapper();

    public StAXSchemaParser() {
        JAXPNamespaceContextWrapper jAXPNamespaceContextWrapper = new JAXPNamespaceContextWrapper(this.fSymbolTable);
        this.fNamespaceContext = jAXPNamespaceContextWrapper;
        this.fElementQName = new QName();
        this.fAttributeQName = new QName();
        this.fAttributes = new XMLAttributesImpl();
        this.fTempString = new XMLString();
        ArrayList arrayList = new ArrayList();
        this.fDeclaredPrefixes = arrayList;
        this.fStringBuffer = new XMLStringBuffer();
        jAXPNamespaceContextWrapper.setDeclaredPrefixes(arrayList);
    }

    private void addNamespaceDeclarations() {
        String str;
        String strAddSymbol;
        for (String str2 : this.fDeclaredPrefixes) {
            String uri = this.fNamespaceContext.getURI(str2);
            if (str2.length() > 0) {
                str = XMLSymbols.PREFIX_XMLNS;
                this.fStringBuffer.clear();
                this.fStringBuffer.append(str);
                this.fStringBuffer.append(':');
                this.fStringBuffer.append(str2);
                SymbolTable symbolTable = this.fSymbolTable;
                XMLStringBuffer xMLStringBuffer = this.fStringBuffer;
                strAddSymbol = symbolTable.addSymbol(xMLStringBuffer.ch, xMLStringBuffer.offset, xMLStringBuffer.length);
            } else {
                str = XMLSymbols.EMPTY_STRING;
                str2 = XMLSymbols.PREFIX_XMLNS;
                strAddSymbol = str2;
            }
            this.fAttributeQName.setValues(str, str2, strAddSymbol, NamespaceContext.XMLNS_URI);
            XMLAttributesImpl xMLAttributesImpl = this.fAttributes;
            QName qName = this.fAttributeQName;
            String str3 = XMLSymbols.fCDATASymbol;
            if (uri == null) {
                uri = XMLSymbols.EMPTY_STRING;
            }
            xMLAttributesImpl.addAttribute(qName, str3, uri);
        }
    }

    private void fillDeclaredPrefixes(Iterator<Namespace> it) {
        this.fDeclaredPrefixes.clear();
        while (it.hasNext()) {
            String prefix = it.next().getPrefix();
            List<String> list = this.fDeclaredPrefixes;
            if (prefix == null) {
                prefix = "";
            }
            list.add(prefix);
        }
    }

    private void fillProcessingInstruction(String str) {
        int length = str.length();
        char[] charArray = this.fCharBuffer;
        if (charArray.length < length) {
            charArray = str.toCharArray();
        } else {
            str.getChars(0, length, charArray, 0);
        }
        this.fTempString.setValues(charArray, 0, length);
    }

    private void fillXMLAttributes(StartElement startElement) {
        this.fAttributes.removeAllAttributes();
        Iterator attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = (Attribute) attributes.next();
            fillQName(this.fAttributeQName, attribute.getName());
            String dTDType = attribute.getDTDType();
            int length = this.fAttributes.getLength();
            XMLAttributesImpl xMLAttributesImpl = this.fAttributes;
            QName qName = this.fAttributeQName;
            if (dTDType == null) {
                dTDType = XMLSymbols.fCDATASymbol;
            }
            xMLAttributesImpl.addAttributeNS(qName, dTDType, attribute.getValue());
            this.fAttributes.setSpecified(length, attribute.isSpecified());
        }
    }

    private void sendCharactersToSchemaParser(String str, boolean z) {
        if (str != null) {
            int length = str.length();
            int i = length & 1023;
            if (i > 0) {
                str.getChars(0, i, this.fCharBuffer, 0);
                this.fTempString.setValues(this.fCharBuffer, 0, i);
                SchemaDOMParser schemaDOMParser = this.fSchemaDOMParser;
                if (z) {
                    schemaDOMParser.ignorableWhitespace(this.fTempString, null);
                } else {
                    schemaDOMParser.characters(this.fTempString, null);
                }
            }
            while (i < length) {
                int i2 = i + 1024;
                str.getChars(i, i2, this.fCharBuffer, 0);
                this.fTempString.setValues(this.fCharBuffer, 0, 1024);
                SchemaDOMParser schemaDOMParser2 = this.fSchemaDOMParser;
                if (z) {
                    schemaDOMParser2.ignorableWhitespace(this.fTempString, null);
                } else {
                    schemaDOMParser2.characters(this.fTempString, null);
                }
                i = i2;
            }
        }
    }

    public final void fillQName(QName qName, String str, String str2, String str3) {
        String strAddSymbol;
        String strAddSymbol2 = (str == null || str.length() <= 0) ? null : this.fSymbolTable.addSymbol(str);
        String strAddSymbol3 = str2 != null ? this.fSymbolTable.addSymbol(str2) : XMLSymbols.EMPTY_STRING;
        String strAddSymbol4 = (str3 == null || str3.length() <= 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(str3);
        if (strAddSymbol4 != XMLSymbols.EMPTY_STRING) {
            this.fStringBuffer.clear();
            this.fStringBuffer.append(strAddSymbol4);
            this.fStringBuffer.append(':');
            this.fStringBuffer.append(strAddSymbol3);
            SymbolTable symbolTable = this.fSymbolTable;
            XMLStringBuffer xMLStringBuffer = this.fStringBuffer;
            strAddSymbol = symbolTable.addSymbol(xMLStringBuffer.ch, xMLStringBuffer.offset, xMLStringBuffer.length);
        } else {
            strAddSymbol = strAddSymbol3;
        }
        qName.setValues(strAddSymbol4, strAddSymbol3, strAddSymbol, strAddSymbol2);
    }

    public Document getDocument() {
        return this.fSchemaDOMParser.getDocument();
    }

    public void parse(XMLStreamReader xMLStreamReader) throws XMLStreamException, XNIException {
        if (xMLStreamReader.hasNext()) {
            int eventType = xMLStreamReader.getEventType();
            if (eventType != 7 && eventType != 1) {
                throw new XMLStreamException();
            }
            this.fLocationWrapper.setLocation(xMLStreamReader.getLocation());
            this.fSchemaDOMParser.startDocument(this.fLocationWrapper, null, this.fNamespaceContext, null);
            boolean z = true;
            while (xMLStreamReader.hasNext()) {
                if (z) {
                    z = false;
                } else {
                    eventType = xMLStreamReader.next();
                }
                if (eventType == 1) {
                    this.fDepth++;
                    this.fLocationWrapper.setLocation(xMLStreamReader.getLocation());
                    this.fNamespaceContext.setNamespaceContext(xMLStreamReader.getNamespaceContext());
                    fillQName(this.fElementQName, xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName(), xMLStreamReader.getPrefix());
                    fillXMLAttributes(xMLStreamReader);
                    fillDeclaredPrefixes(xMLStreamReader);
                    addNamespaceDeclarations();
                    this.fNamespaceContext.pushContext();
                    this.fSchemaDOMParser.startElement(this.fElementQName, this.fAttributes, null);
                } else if (eventType == 2) {
                    this.fLocationWrapper.setLocation(xMLStreamReader.getLocation());
                    this.fNamespaceContext.setNamespaceContext(xMLStreamReader.getNamespaceContext());
                    fillQName(this.fElementQName, xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName(), xMLStreamReader.getPrefix());
                    fillDeclaredPrefixes(xMLStreamReader);
                    this.fSchemaDOMParser.endElement(this.fElementQName, null);
                    this.fNamespaceContext.popContext();
                    int i = this.fDepth - 1;
                    this.fDepth = i;
                    if (i <= 0) {
                        break;
                    }
                } else if (eventType == 3) {
                    fillProcessingInstruction(xMLStreamReader.getPIData());
                    this.fSchemaDOMParser.processingInstruction(xMLStreamReader.getPITarget(), this.fTempString, null);
                } else if (eventType == 4) {
                    this.fTempString.setValues(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
                    this.fSchemaDOMParser.characters(this.fTempString, null);
                } else if (eventType == 6) {
                    this.fTempString.setValues(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
                    this.fSchemaDOMParser.ignorableWhitespace(this.fTempString, null);
                } else if (eventType == 7) {
                    this.fDepth++;
                } else if (eventType == 12) {
                    this.fSchemaDOMParser.startCDATA(null);
                    this.fTempString.setValues(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
                    this.fSchemaDOMParser.characters(this.fTempString, null);
                    this.fSchemaDOMParser.endCDATA(null);
                }
            }
            this.fLocationWrapper.setLocation(null);
            this.fNamespaceContext.setNamespaceContext(null);
            this.fSchemaDOMParser.endDocument(null);
        }
    }

    public void reset(SchemaDOMParser schemaDOMParser, SymbolTable symbolTable) {
        this.fSchemaDOMParser = schemaDOMParser;
        this.fSymbolTable = symbolTable;
        this.fNamespaceContext.setSymbolTable(symbolTable);
        this.fNamespaceContext.reset();
    }

    private void fillDeclaredPrefixes(EndElement endElement) {
        fillDeclaredPrefixes(endElement.getNamespaces());
    }

    private void fillDeclaredPrefixes(StartElement startElement) {
        fillDeclaredPrefixes(startElement.getNamespaces());
    }

    private void fillDeclaredPrefixes(XMLStreamReader xMLStreamReader) {
        this.fDeclaredPrefixes.clear();
        int namespaceCount = xMLStreamReader.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            String namespacePrefix = xMLStreamReader.getNamespacePrefix(i);
            List<String> list = this.fDeclaredPrefixes;
            if (namespacePrefix == null) {
                namespacePrefix = "";
            }
            list.add(namespacePrefix);
        }
    }

    private void fillXMLAttributes(XMLStreamReader xMLStreamReader) {
        this.fAttributes.removeAllAttributes();
        int attributeCount = xMLStreamReader.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            fillQName(this.fAttributeQName, xMLStreamReader.getAttributeNamespace(i), xMLStreamReader.getAttributeLocalName(i), xMLStreamReader.getAttributePrefix(i));
            String attributeType = xMLStreamReader.getAttributeType(i);
            XMLAttributesImpl xMLAttributesImpl = this.fAttributes;
            QName qName = this.fAttributeQName;
            if (attributeType == null) {
                attributeType = XMLSymbols.fCDATASymbol;
            }
            xMLAttributesImpl.addAttributeNS(qName, attributeType, xMLStreamReader.getAttributeValue(i));
            this.fAttributes.setSpecified(i, xMLStreamReader.isAttributeSpecified(i));
        }
    }

    private void fillQName(QName qName, javax.xml.namespace.QName qName2) {
        fillQName(qName, qName2.getNamespaceURI(), qName2.getLocalPart(), qName2.getPrefix());
    }

    public void parse(XMLEventReader xMLEventReader) throws XMLStreamException, XNIException {
        XMLEvent xMLEventPeek = xMLEventReader.peek();
        if (xMLEventPeek != null) {
            int eventType = xMLEventPeek.getEventType();
            if (eventType != 7 && eventType != 1) {
                throw new XMLStreamException();
            }
            this.fLocationWrapper.setLocation(xMLEventPeek.getLocation());
            this.fSchemaDOMParser.startDocument(this.fLocationWrapper, null, this.fNamespaceContext, null);
            while (xMLEventReader.hasNext()) {
                XMLEvent xMLEventNextEvent = xMLEventReader.nextEvent();
                int eventType2 = xMLEventNextEvent.getEventType();
                if (eventType2 == 1) {
                    this.fDepth++;
                    StartElement startElementAsStartElement = xMLEventNextEvent.asStartElement();
                    fillQName(this.fElementQName, startElementAsStartElement.getName());
                    this.fLocationWrapper.setLocation(startElementAsStartElement.getLocation());
                    this.fNamespaceContext.setNamespaceContext(startElementAsStartElement.getNamespaceContext());
                    fillXMLAttributes(startElementAsStartElement);
                    fillDeclaredPrefixes(startElementAsStartElement);
                    addNamespaceDeclarations();
                    this.fNamespaceContext.pushContext();
                    this.fSchemaDOMParser.startElement(this.fElementQName, this.fAttributes, null);
                } else if (eventType2 == 2) {
                    EndElement endElementAsEndElement = xMLEventNextEvent.asEndElement();
                    fillQName(this.fElementQName, endElementAsEndElement.getName());
                    fillDeclaredPrefixes(endElementAsEndElement);
                    this.fLocationWrapper.setLocation(endElementAsEndElement.getLocation());
                    this.fSchemaDOMParser.endElement(this.fElementQName, null);
                    this.fNamespaceContext.popContext();
                    int i = this.fDepth - 1;
                    this.fDepth = i;
                    if (i <= 0) {
                        break;
                    }
                } else if (eventType2 == 3) {
                    ProcessingInstruction processingInstruction = (ProcessingInstruction) xMLEventNextEvent;
                    fillProcessingInstruction(processingInstruction.getData());
                    this.fSchemaDOMParser.processingInstruction(processingInstruction.getTarget(), this.fTempString, null);
                } else if (eventType2 == 4) {
                    sendCharactersToSchemaParser(xMLEventNextEvent.asCharacters().getData(), false);
                } else if (eventType2 == 6) {
                    sendCharactersToSchemaParser(xMLEventNextEvent.asCharacters().getData(), true);
                } else if (eventType2 == 7) {
                    this.fDepth++;
                } else if (eventType2 == 12) {
                    this.fSchemaDOMParser.startCDATA(null);
                    sendCharactersToSchemaParser(xMLEventNextEvent.asCharacters().getData(), false);
                    this.fSchemaDOMParser.endCDATA(null);
                }
            }
            this.fLocationWrapper.setLocation(null);
            this.fNamespaceContext.setNamespaceContext(null);
            this.fSchemaDOMParser.endDocument(null);
        }
    }
}
