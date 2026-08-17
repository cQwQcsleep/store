package com.sun.org.apache.xerces.internal.impl;

import com.sun.org.apache.xerces.internal.util.NamespaceContextWrapper;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLAttributesIteratorImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDScanner;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.xml.internal.stream.Entity;
import com.sun.xml.internal.stream.StaxErrorReporter;
import com.sun.xml.internal.stream.dtd.nonvalidating.DTDGrammar;
import com.sun.xml.internal.stream.dtd.nonvalidating.XMLNotationDecl;
import com.sun.xml.internal.stream.events.EntityDeclarationImpl;
import com.sun.xml.internal.stream.events.NotationDeclarationImpl;
import defpackage.ayf;
import defpackage.txf;
import defpackage.zxf;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.NotationDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLStreamReaderImpl implements XMLStreamReader {
    static final boolean DEBUG = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String READER_IN_DEFINED_STATE = "http://java.sun.com/xml/stream/properties/reader-in-defined-state";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private String fDTDDecl;
    protected XMLEntityManager fEntityManager;
    protected XMLEntityScanner fEntityScanner;
    protected StaxErrorReporter fErrorReporter;
    private int fEventType;
    protected XMLInputSource fInputSource;
    protected NamespaceContextWrapper fNamespaceContextWrapper;
    protected PropertyManager fPropertyManager;
    private boolean fReaderInDefinedState;
    private boolean fReuse;
    protected XMLDocumentScannerImpl fScanner;
    private SymbolTable fSymbolTable = new SymbolTable();
    private String versionStr;

    public XMLStreamReaderImpl(InputStream inputStream, String str, PropertyManager propertyManager) throws XMLStreamException {
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fScanner = xMLNSDocumentScannerImpl;
        this.fNamespaceContextWrapper = new NamespaceContextWrapper((NamespaceSupport) xMLNSDocumentScannerImpl.getNamespaceContext());
        this.fEntityManager = new XMLEntityManager();
        this.fErrorReporter = new StaxErrorReporter();
        this.fEntityScanner = null;
        this.fInputSource = null;
        this.fPropertyManager = null;
        this.fReuse = true;
        this.fReaderInDefinedState = true;
        this.fDTDDecl = null;
        this.versionStr = null;
        init(propertyManager);
        setInputSource(new XMLInputSource((String) null, (String) null, (String) null, new BufferedInputStream(inputStream), str));
    }

    public static final String getEventTypeString(int i) {
        switch (i) {
            case 1:
                return "START_ELEMENT";
            case 2:
                return "END_ELEMENT";
            case 3:
                return "PROCESSING_INSTRUCTION";
            case 4:
                return "CHARACTERS";
            case 5:
                return "COMMENT";
            case 6:
                return "SPACE";
            case 7:
                return "START_DOCUMENT";
            case 8:
                return "END_DOCUMENT";
            case 9:
                return "ENTITY_REFERENCE";
            case 10:
                return "ATTRIBUTE";
            case 11:
                return "DTD";
            case 12:
                return "CDATA";
            default:
                return "UNKNOWN_EVENT_TYPE, " + String.valueOf(i);
        }
    }

    public static void pr(String str) {
        System.out.println(str);
    }

    private void switchToXML11Scanner() throws IOException {
        XMLDocumentScannerImpl xMLDocumentScannerImpl = this.fScanner;
        int i = xMLDocumentScannerImpl.fEntityDepth;
        NamespaceContext namespaceContext = xMLDocumentScannerImpl.fNamespaceContext;
        XML11NSDocumentScannerImpl xML11NSDocumentScannerImpl = new XML11NSDocumentScannerImpl();
        this.fScanner = xML11NSDocumentScannerImpl;
        xML11NSDocumentScannerImpl.reset(this.fPropertyManager);
        this.fScanner.setPropertyManager(this.fPropertyManager);
        XMLEntityScanner entityScanner = this.fEntityManager.getEntityScanner();
        this.fEntityScanner = entityScanner;
        entityScanner.registerListener(this.fScanner);
        this.fEntityManager.fCurrentEntity.mayReadChunks = true;
        this.fScanner.setScannerState(7);
        XMLDocumentScannerImpl xMLDocumentScannerImpl2 = this.fScanner;
        xMLDocumentScannerImpl2.fEntityDepth = i;
        xMLDocumentScannerImpl2.fNamespaceContext = namespaceContext;
        this.fEventType = xMLDocumentScannerImpl2.next();
    }

    public boolean canReuse() {
        return this.fReuse;
    }

    public void close() throws XMLStreamException {
        this.fReuse = true;
    }

    public QName convertXNIQNametoJavaxQName(com.sun.org.apache.xerces.internal.xni.QName qName) {
        if (qName == null) {
            return null;
        }
        String str = qName.prefix;
        String str2 = qName.uri;
        return str == null ? new QName(str2, qName.localpart) : new QName(str2, qName.localpart, str);
    }

    public int getAttributeCount() {
        int i = this.fEventType;
        if (i == 1 || i == 10) {
            return this.fScanner.getAttributeIterator().getLength();
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeCount()");
        return 0;
    }

    public String getAttributeLocalName(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return this.fScanner.getAttributeIterator().getLocalName(i);
        }
        g33.a();
        return null;
    }

    public QName getAttributeName(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return convertXNIQNametoJavaxQName(this.fScanner.getAttributeIterator().getQualifiedName(i));
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeName()");
        return null;
    }

    public String getAttributeNamespace(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return this.fScanner.getAttributeIterator().getURI(i);
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeNamespace()");
        return null;
    }

    public String getAttributePrefix(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return this.fScanner.getAttributeIterator().getPrefix(i);
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributePrefix()");
        return null;
    }

    public QName getAttributeQName(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return new QName(this.fScanner.getAttributeIterator().getURI(i), this.fScanner.getAttributeIterator().getLocalName(i));
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeQName()");
        return null;
    }

    public String getAttributeType(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return this.fScanner.getAttributeIterator().getType(i);
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeType()");
        return null;
    }

    public String getAttributeValue(String str, String str2) {
        int i = this.fEventType;
        if (i != 1 && i != 10) {
            vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeValue()");
            return null;
        }
        XMLAttributesIteratorImpl attributeIterator = this.fScanner.getAttributeIterator();
        if (str == null) {
            return attributeIterator.getValue(attributeIterator.getIndexByLocalName(str2));
        }
        XMLAttributesIteratorImpl attributeIterator2 = this.fScanner.getAttributeIterator();
        if (str.length() == 0) {
            str = null;
        }
        return attributeIterator2.getValue(str, str2);
    }

    public String getCharacterEncodingScheme() {
        return this.fScanner.getCharacterEncodingScheme();
    }

    public int getColumnNumber() {
        return this.fEntityScanner.getColumnNumber();
    }

    public String getElementText() throws XMLStreamException {
        if (getEventType() != 1) {
            throw new XMLStreamException("parser must be on START_ELEMENT to read next text", getLocation());
        }
        int next = next();
        StringBuilder sb = new StringBuilder();
        while (next != 2) {
            if (next == 4 || next == 12 || next == 6 || next == 9) {
                sb.append(getText());
            } else if (next != 3 && next != 5) {
                if (next == 8) {
                    jnd.a("unexpected end of document when reading element text content");
                    return null;
                }
                if (next == 1) {
                    throw new XMLStreamException("elementGetText() function expects text only elment but START_ELEMENT was encountered.", getLocation());
                }
                throw new XMLStreamException("Unexpected event type " + next, getLocation());
            }
            next = next();
        }
        return sb.toString();
    }

    public String getEncoding() {
        return this.fEntityScanner.getEncoding();
    }

    public List<EntityDeclaration> getEntityDecls() {
        if (this.fEventType == 11) {
            Map<String, Entity> entities = this.fEntityManager.getEntityStore().getEntities();
            if (entities.size() > 0) {
                ArrayList arrayList = new ArrayList(entities.size());
                for (Map.Entry<String, Entity> entry : entities.entrySet()) {
                    String key = entry.getKey();
                    Entity value = entry.getValue();
                    EntityDeclarationImpl entityDeclarationImpl = new EntityDeclarationImpl();
                    entityDeclarationImpl.setEntityName(key);
                    if (value.isExternal()) {
                        Entity.ExternalEntity externalEntity = (Entity.ExternalEntity) value;
                        entityDeclarationImpl.setXMLResourceIdentifier(externalEntity.entityLocation);
                        entityDeclarationImpl.setNotationName(externalEntity.notation);
                    } else {
                        entityDeclarationImpl.setEntityReplacementText(((Entity.InternalEntity) value).text);
                    }
                    arrayList.add(entityDeclarationImpl);
                }
                return arrayList;
            }
        }
        return null;
    }

    public int getEventType() {
        return this.fEventType;
    }

    public int getLineNumber() {
        return this.fEntityScanner.getLineNumber();
    }

    public String getLocalName() {
        int i = this.fEventType;
        if (i == 1 || i == 2) {
            return this.fScanner.getElementQName().localpart;
        }
        if (i == 9) {
            return this.fScanner.getEntityName();
        }
        zia.a("Method getLocalName() cannot be called for ", getEventTypeString(this.fEventType), " event.");
        return null;
    }

    public Location getLocation() {
        return new Location() { // from class: com.sun.org.apache.xerces.internal.impl.XMLStreamReaderImpl.1
            int _columnNumber;
            int _lineNumber;
            int _offset;
            String _publicId;
            String _systemId;

            {
                this._systemId = XMLStreamReaderImpl.this.fEntityScanner.getExpandedSystemId();
                this._publicId = XMLStreamReaderImpl.this.fEntityScanner.getPublicId();
                this._offset = XMLStreamReaderImpl.this.fEntityScanner.getCharacterOffset();
                this._columnNumber = XMLStreamReaderImpl.this.fEntityScanner.getColumnNumber();
                this._lineNumber = XMLStreamReaderImpl.this.fEntityScanner.getLineNumber();
            }

            @Override // javax.xml.stream.Location
            public int getCharacterOffset() {
                return this._offset;
            }

            @Override // javax.xml.stream.Location
            public int getColumnNumber() {
                return this._columnNumber;
            }

            @Override // javax.xml.stream.Location
            public int getLineNumber() {
                return this._lineNumber;
            }

            public String getLocationURI() {
                return this._systemId;
            }

            @Override // javax.xml.stream.Location
            public String getPublicId() {
                return this._publicId;
            }

            @Override // javax.xml.stream.Location
            public String getSystemId() {
                return this._systemId;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("Line number = " + getLineNumber());
                sb.append("\n");
                sb.append("Column number = " + getColumnNumber());
                sb.append("\n");
                sb.append("System Id = " + getSystemId());
                sb.append("\n");
                sb.append("Public Id = " + getPublicId());
                sb.append("\n");
                sb.append("Location Uri= " + getLocationURI());
                sb.append("\n");
                sb.append("CharacterOffset = " + getCharacterOffset());
                sb.append("\n");
                return sb.toString();
            }
        };
    }

    public QName getName() {
        int i = this.fEventType;
        if (i == 1 || i == 2) {
            return convertXNIQNametoJavaxQName(this.fScanner.getElementQName());
        }
        throw new IllegalStateException("Illegal to call getName() when event type is " + getEventTypeString(this.fEventType) + ". Valid states are " + getEventTypeString(1) + ", " + getEventTypeString(2));
    }

    public javax.xml.namespace.NamespaceContext getNamespaceContext() {
        return this.fNamespaceContextWrapper;
    }

    public int getNamespaceCount() {
        int i = this.fEventType;
        if (i == 1 || i == 2 || i == 13) {
            return this.fScanner.getNamespaceContext().getDeclaredPrefixCount();
        }
        ayf.a("Current event state is ", getEventTypeString(this.fEventType), getEventTypeString(1), getEventTypeString(2), getEventTypeString(13), " valid for getNamespaceCount().");
        return 0;
    }

    public String getNamespacePrefix(int i) {
        int i2 = this.fEventType;
        if (i2 != 1 && i2 != 2 && i2 != 13) {
            ayf.a("Current state ", getEventTypeString(this.fEventType), getEventTypeString(1), getEventTypeString(2), getEventTypeString(13), " valid for getNamespacePrefix().");
            return null;
        }
        String declaredPrefixAt = this.fScanner.getNamespaceContext().getDeclaredPrefixAt(i);
        if (declaredPrefixAt.equals("")) {
            return null;
        }
        return declaredPrefixAt;
    }

    public String getNamespaceURI(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 2 || i2 == 13) {
            return this.fScanner.getNamespaceContext().getURI(this.fScanner.getNamespaceContext().getDeclaredPrefixAt(i));
        }
        ayf.a("Current state ", getEventTypeString(this.fEventType), getEventTypeString(1), getEventTypeString(2), getEventTypeString(13), " valid for getNamespaceURI().");
        return null;
    }

    public List<NotationDeclaration> getNotationDecls() {
        XMLDTDScanner xMLDTDScanner;
        DTDGrammar grammar;
        if (this.fEventType != 11 || (xMLDTDScanner = this.fScanner.fDTDScanner) == null || (grammar = ((XMLDTDScannerImpl) xMLDTDScanner).getGrammar()) == null) {
            return null;
        }
        List<XMLNotationDecl> notationDecls = grammar.getNotationDecls();
        ArrayList arrayList = new ArrayList();
        for (XMLNotationDecl xMLNotationDecl : notationDecls) {
            if (xMLNotationDecl != null) {
                arrayList.add(new NotationDeclarationImpl(xMLNotationDecl));
            }
        }
        return arrayList;
    }

    public String getPIData() {
        if (this.fEventType == 3) {
            return this.fScanner.getPIData().toString();
        }
        zia.a("Current state of the parser is ", getEventTypeString(this.fEventType), " But Expected state is 3");
        return null;
    }

    public String getPITarget() {
        if (this.fEventType == 3) {
            return this.fScanner.getPITarget();
        }
        zia.a("Current state of the parser is ", getEventTypeString(this.fEventType), " But Expected state is 3");
        return null;
    }

    public String getPrefix() {
        int i = this.fEventType;
        if (i != 1 && i != 2) {
            return null;
        }
        String str = this.fScanner.getElementQName().prefix;
        return str == null ? "" : str;
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        if (str == null) {
            j2d.a();
            return null;
        }
        if (this.fPropertyManager == null) {
            return null;
        }
        if (str.equals(PropertyManager.STAX_NOTATIONS)) {
            return getNotationDecls();
        }
        return str.equals(PropertyManager.STAX_ENTITIES) ? getEntityDecls() : this.fPropertyManager.getProperty(str);
    }

    public PropertyManager getPropertyManager() {
        return this.fPropertyManager;
    }

    public XMLDocumentScannerImpl getScanner() {
        System.out.println("returning scanner");
        return this.fScanner;
    }

    public String getText() {
        int i = this.fEventType;
        if (i == 4 || i == 5 || i == 12 || i == 6) {
            return this.fScanner.getCharacterData().toString();
        }
        if (i == 9) {
            String entityName = this.fScanner.getEntityName();
            if (entityName == null) {
                return null;
            }
            XMLDocumentScannerImpl xMLDocumentScannerImpl = this.fScanner;
            if (xMLDocumentScannerImpl.foundBuiltInRefs) {
                return xMLDocumentScannerImpl.getCharacterData().toString();
            }
            Entity entity = this.fEntityManager.getEntityStore().getEntity(entityName);
            if (entity == null) {
                return null;
            }
            return entity.isExternal() ? ((Entity.ExternalEntity) entity).entityLocation.getExpandedSystemId() : ((Entity.InternalEntity) entity).text;
        }
        if (i == 11) {
            String str = this.fDTDDecl;
            if (str != null) {
                return str;
            }
            String string = this.fScanner.getDTDDecl().toString();
            this.fDTDDecl = string;
            return string;
        }
        throw new IllegalStateException("Current state " + getEventTypeString(this.fEventType) + " is not among the states" + getEventTypeString(4) + ", " + getEventTypeString(5) + ", " + getEventTypeString(12) + ", " + getEventTypeString(6) + ", " + getEventTypeString(9) + ", " + getEventTypeString(11) + " valid for getText() ");
    }

    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        if (cArr == null) {
            x0e.a("target char array can't be null");
            return 0;
        }
        if (i2 < 0 || i3 < 0 || i < 0 || i2 >= cArr.length || i2 + i3 > cArr.length) {
            qc6.a();
            return 0;
        }
        int textLength = getTextLength() - i;
        if (textLength < 0) {
            jb9.a("sourceStart is greater thannumber of characters associated with this event");
            return 0;
        }
        if (textLength < i3) {
            i3 = textLength;
        }
        System.arraycopy(getTextCharacters(), getTextStart() + i, cArr, i2, i3);
        return i3;
    }

    public int getTextLength() {
        int i = this.fEventType;
        if (i == 4 || i == 5 || i == 12 || i == 6) {
            return this.fScanner.getCharacterData().length;
        }
        zxf.a(getEventTypeString(this.fEventType), getEventTypeString(4), getEventTypeString(5), getEventTypeString(12), getEventTypeString(6), " valid for getTextLength() ");
        return 0;
    }

    public int getTextStart() {
        int i = this.fEventType;
        if (i == 4 || i == 5 || i == 12 || i == 6) {
            return this.fScanner.getCharacterData().offset;
        }
        zxf.a(getEventTypeString(this.fEventType), getEventTypeString(4), getEventTypeString(5), getEventTypeString(12), getEventTypeString(6), " valid for getTextStart() ");
        return 0;
    }

    public String getValue() {
        int i = this.fEventType;
        if (i == 3) {
            return this.fScanner.getPIData().toString();
        }
        if (i == 5) {
            return this.fScanner.getComment();
        }
        if (i == 1 || i == 2) {
            return this.fScanner.getElementQName().localpart;
        }
        if (i == 4) {
            return this.fScanner.getCharacterData().toString();
        }
        return null;
    }

    public String getVersion() {
        String xMLVersion = this.fEntityScanner.getXMLVersion();
        if (!"1.0".equals(xMLVersion) || this.fEntityScanner.xmlVersionSetExplicitly) {
            return xMLVersion;
        }
        return null;
    }

    public boolean hasAttributes() {
        return this.fScanner.getAttributeIterator().getLength() > 0;
    }

    public boolean hasName() {
        int i = this.fEventType;
        return i == 1 || i == 2;
    }

    public boolean hasNext() throws XMLStreamException {
        int i = this.fEventType;
        return (i == -1 || i == 8) ? false : true;
    }

    public boolean hasText() {
        int i = this.fEventType;
        if (i == 4 || i == 5 || i == 12) {
            return this.fScanner.getCharacterData().length > 0;
        }
        if (i != 9) {
            if (i == 11) {
                return this.fScanner.fSeenDoctypeDecl;
            }
            return false;
        }
        String entityName = this.fScanner.getEntityName();
        if (entityName != null) {
            if (this.fScanner.foundBuiltInRefs) {
                return true;
            }
            Entity entity = this.fEntityManager.getEntityStore().getEntity(entityName);
            if (entity == null) {
                return false;
            }
            if (entity.isExternal()) {
                return ((Entity.ExternalEntity) entity).entityLocation.getExpandedSystemId() != null;
            }
            if (((Entity.InternalEntity) entity).text != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasValue() {
        int i = this.fEventType;
        return i == 1 || i == 2 || i == 9 || i == 3 || i == 5 || i == 4;
    }

    public final void init(PropertyManager propertyManager) throws XMLStreamException {
        this.fPropertyManager = propertyManager;
        propertyManager.setProperty("http://apache.org/xml/properties/internal/symbol-table", this.fSymbolTable);
        propertyManager.setProperty("http://apache.org/xml/properties/internal/error-reporter", this.fErrorReporter);
        propertyManager.setProperty(ENTITY_MANAGER, this.fEntityManager);
        reset();
    }

    public boolean isAttributeSpecified(int i) {
        int i2 = this.fEventType;
        if (i2 == 1 || i2 == 10) {
            return this.fScanner.getAttributeIterator().isSpecified(i);
        }
        vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for isAttributeSpecified()");
        return false;
    }

    public boolean isCharacters() {
        return this.fEventType == 4;
    }

    public boolean isEndElement() {
        return this.fEventType == 2;
    }

    public boolean isStandalone() {
        return this.fScanner.isStandAlone();
    }

    public boolean isStartElement() {
        return this.fEventType == 1;
    }

    public boolean isWhiteSpace() {
        if (!isCharacters() && this.fEventType != 12) {
            return false;
        }
        char[] textCharacters = getTextCharacters();
        int textStart = getTextStart();
        int textLength = getTextLength() + textStart;
        while (textStart < textLength) {
            if (!XMLChar.isSpace(textCharacters[textStart])) {
                return false;
            }
            textStart++;
        }
        return true;
    }

    public int next() throws XMLStreamException {
        Boolean bool;
        String str;
        if (!hasNext()) {
            if (this.fEventType != -1) {
                hb9.a("END_DOCUMENT reached: no more elements on the stream.");
                return 0;
            }
            jnd.a("Error processing input source. The input stream is not complete.");
            return 0;
        }
        try {
            this.fEventType = this.fScanner.next();
            if (this.versionStr == null) {
                this.versionStr = getVersion();
            }
            if (this.fEventType == 7 && (str = this.versionStr) != null && str.equals(SerializerConstants.XMLVERSION11)) {
                switchToXML11Scanner();
            }
            int i = this.fEventType;
            if (i == 4 || i == 9 || i == 3 || i == 5 || i == 12) {
                XMLEntityScanner xMLEntityScanner = this.fEntityScanner;
                xMLEntityScanner.checkNodeCount(xMLEntityScanner.fCurrentEntity);
            }
            return this.fEventType;
        } catch (XNIException e) {
            throw new XMLStreamException(e.getMessage(), getLocation(), e.getException());
        } catch (IOException e2) {
            if (this.fScanner.fScannerState != 46 || (bool = (Boolean) this.fPropertyManager.getProperty(XMLInputFactory.IS_VALIDATING)) == null || bool.booleanValue()) {
                throw new XMLStreamException(e2.getMessage(), getLocation(), e2);
            }
            this.fEventType = 11;
            this.fScanner.setScannerState(43);
            XMLDocumentScannerImpl xMLDocumentScannerImpl = this.fScanner;
            xMLDocumentScannerImpl.setDriver(xMLDocumentScannerImpl.fPrologDriver);
            String str2 = this.fDTDDecl;
            if (str2 == null || str2.length() == 0) {
                this.fDTDDecl = "<!-- Exception scanning External DTD Subset.  True contents of DTD cannot be determined.  Processing will continue as XMLInputFactory.IS_VALIDATING == false. -->";
            }
            return 11;
        }
    }

    public int nextTag() throws XMLStreamException {
        int next = next();
        while (true) {
            if ((next != 4 || !isWhiteSpace()) && ((next != 12 || !isWhiteSpace()) && next != 6 && next != 3 && next != 5)) {
                break;
            }
            next = next();
        }
        if (next == 1 || next == 2) {
            return next;
        }
        throw new XMLStreamException("found: " + getEventTypeString(next) + ", expected " + getEventTypeString(1) + " or " + getEventTypeString(2), getLocation());
    }

    public void require(int i, String str, String str2) throws XMLStreamException {
        if (i != this.fEventType) {
            throw new XMLStreamException("Event type " + getEventTypeString(i) + " specified did not match with current parser event " + getEventTypeString(this.fEventType));
        }
        if (str != null && !str.equals(getNamespaceURI())) {
            txf.a("Namespace URI ", str, " specified did not match with current namespace URI");
        } else {
            if (str2 == null || str2.equals(getLocalName())) {
                return;
            }
            txf.a("LocalName ", str2, " specified did not match with current local name");
        }
    }

    public void reset() {
        this.fReuse = true;
        this.fEventType = 0;
        this.fEntityManager.reset(this.fPropertyManager);
        this.fScanner.reset(this.fPropertyManager);
        this.fDTDDecl = null;
        this.fEntityScanner = this.fEntityManager.getEntityScanner();
        this.fReaderInDefinedState = ((Boolean) this.fPropertyManager.getProperty("http://java.sun.com/xml/stream/properties/reader-in-defined-state")).booleanValue();
        this.versionStr = null;
    }

    public final void setInputSource(XMLInputSource xMLInputSource) throws XMLStreamException {
        String str;
        this.fReuse = false;
        try {
            this.fScanner.setInputSource(xMLInputSource);
            if (this.fReaderInDefinedState) {
                this.fEventType = this.fScanner.next();
                if (this.versionStr == null) {
                    this.versionStr = getVersion();
                }
                if (this.fEventType == 7 && (str = this.versionStr) != null && str.equals(SerializerConstants.XMLVERSION11)) {
                    switchToXML11Scanner();
                }
            }
        } catch (XNIException e) {
            throw new XMLStreamException(e.getMessage(), getLocation(), e.getException());
        } catch (IOException e2) {
            az3.a(e2);
        }
    }

    public void setPropertyManager(PropertyManager propertyManager) {
        this.fPropertyManager = propertyManager;
        this.fScanner.setProperty(Constants.STAX_PROPERTIES, propertyManager);
        this.fScanner.setPropertyManager(propertyManager);
    }

    public boolean standaloneSet() {
        return this.fScanner.standaloneSet();
    }

    public char[] getTextCharacters() {
        int i = this.fEventType;
        if (i != 4 && i != 5 && i != 12 && i != 6) {
            zxf.a(getEventTypeString(this.fEventType), getEventTypeString(4), getEventTypeString(5), getEventTypeString(12), getEventTypeString(6), " valid for getTextCharacters() ");
            return null;
        }
        return this.fScanner.getCharacterData().ch;
    }

    public String getNamespaceURI() {
        int i = this.fEventType;
        if (i == 1 || i == 2) {
            return this.fScanner.getElementQName().uri;
        }
        return null;
    }

    public String getNamespaceURI(String str) {
        if (str != null) {
            return this.fScanner.getNamespaceContext().getURI(this.fSymbolTable.addSymbol(str));
        }
        w01.a("prefix cannot be null.");
        return null;
    }

    public String getAttributeValue(int i) {
        int i2 = this.fEventType;
        if (i2 != 1 && i2 != 10) {
            vu.a("Current state is not among the states ", getEventTypeString(1), " , ", getEventTypeString(10), "valid for getAttributeValue()");
            return null;
        }
        return this.fScanner.getAttributeIterator().getValue(i);
    }

    public XMLStreamReaderImpl(String str, PropertyManager propertyManager) throws XMLStreamException {
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fScanner = xMLNSDocumentScannerImpl;
        this.fNamespaceContextWrapper = new NamespaceContextWrapper((NamespaceSupport) xMLNSDocumentScannerImpl.getNamespaceContext());
        this.fEntityManager = new XMLEntityManager();
        this.fErrorReporter = new StaxErrorReporter();
        this.fEntityScanner = null;
        this.fInputSource = null;
        this.fPropertyManager = null;
        this.fReuse = true;
        this.fReaderInDefinedState = true;
        this.fDTDDecl = null;
        this.versionStr = null;
        init(propertyManager);
        setInputSource(new XMLInputSource(null, str, null, false));
    }

    public XMLStreamReaderImpl(InputStream inputStream, PropertyManager propertyManager) throws XMLStreamException {
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fScanner = xMLNSDocumentScannerImpl;
        this.fNamespaceContextWrapper = new NamespaceContextWrapper((NamespaceSupport) xMLNSDocumentScannerImpl.getNamespaceContext());
        this.fEntityManager = new XMLEntityManager();
        this.fErrorReporter = new StaxErrorReporter();
        this.fEntityScanner = null;
        this.fInputSource = null;
        this.fPropertyManager = null;
        this.fReuse = true;
        this.fReaderInDefinedState = true;
        this.fDTDDecl = null;
        this.versionStr = null;
        init(propertyManager);
        setInputSource(new XMLInputSource((String) null, (String) null, (String) null, inputStream, (String) null));
    }

    public XMLStreamReaderImpl(Reader reader, PropertyManager propertyManager) throws XMLStreamException {
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fScanner = xMLNSDocumentScannerImpl;
        this.fNamespaceContextWrapper = new NamespaceContextWrapper((NamespaceSupport) xMLNSDocumentScannerImpl.getNamespaceContext());
        this.fEntityManager = new XMLEntityManager();
        this.fErrorReporter = new StaxErrorReporter();
        this.fEntityScanner = null;
        this.fInputSource = null;
        this.fPropertyManager = null;
        this.fReuse = true;
        this.fReaderInDefinedState = true;
        this.fDTDDecl = null;
        this.versionStr = null;
        init(propertyManager);
        setInputSource(new XMLInputSource((String) null, (String) null, (String) null, new BufferedReader(reader), (String) null));
    }

    public XMLStreamReaderImpl(XMLInputSource xMLInputSource, PropertyManager propertyManager) throws XMLStreamException {
        XMLNSDocumentScannerImpl xMLNSDocumentScannerImpl = new XMLNSDocumentScannerImpl();
        this.fScanner = xMLNSDocumentScannerImpl;
        this.fNamespaceContextWrapper = new NamespaceContextWrapper((NamespaceSupport) xMLNSDocumentScannerImpl.getNamespaceContext());
        this.fEntityManager = new XMLEntityManager();
        this.fErrorReporter = new StaxErrorReporter();
        this.fEntityScanner = null;
        this.fInputSource = null;
        this.fPropertyManager = null;
        this.fReuse = true;
        this.fReaderInDefinedState = true;
        this.fDTDDecl = null;
        this.versionStr = null;
        init(propertyManager);
        setInputSource(xMLInputSource);
    }
}
