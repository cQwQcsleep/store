package com.sun.xml.internal.stream.writers;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.xml.internal.stream.util.ReadOnlyIterator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.stream.StreamResult;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class XMLStreamWriterImpl extends AbstractMap<Object, Object> implements XMLStreamWriterBase {
    public static final String CLOSE_EMPTY_ELEMENT = "/>";
    public static final char CLOSE_END_TAG = '>';
    public static final char CLOSE_START_TAG = '>';
    public static final String DEFAULT_ENCODING = " encoding=\"utf-8\"";
    public static final String DEFAULT_XMLDECL = "<?xml version=\"1.0\" ?>";
    public static final String DEFAULT_XML_VERSION = "1.0";
    public static final String END_CDATA = "]]>";
    public static final String END_COMMENT = "-->";
    public static final String OPEN_END_TAG = "</";
    public static final char OPEN_START_TAG = '<';
    public static final String OUTPUTSTREAM_PROPERTY = "sjsxp-outputstream";
    public static final String SPACE = " ";
    public static final String START_CDATA = "<![CDATA[";
    public static final String START_COMMENT = "<!--";
    public static final String UTF_8 = "UTF-8";
    private final String DEFAULT_PREFIX;
    Map<String, String> fAttrNamespace;
    private List<Attribute> fAttributeCache;
    private ElementStack fElementStack;
    private CharsetEncoder fEncoder;
    boolean fEscapeCharacters;
    private NamespaceSupport fInternalNamespaceContext;
    private boolean fIsRepairingNamespace;
    private NamespaceContextImpl fNamespaceContext;
    private List<QName> fNamespaceDecls;
    private OutputStream fOutputStream;
    private Random fPrefixGen;
    private PropertyManager fPropertyManager;
    private final ReadOnlyIterator<String> fReadOnlyIterator;
    private boolean fReuse;
    private boolean fStartTagOpened;
    private SymbolTable fSymbolTable;
    private Writer fWriter;

    public class Attribute extends QName {
        String value;

        public Attribute(String str) {
            this.value = str;
        }
    }

    public class NamespaceContextImpl implements NamespaceContext {
        NamespaceContext userContext = null;
        NamespaceSupport internalContext = null;

        public NamespaceContextImpl() {
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getNamespaceURI(String str) {
            String uri;
            if (str != null) {
                str = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(str);
            }
            NamespaceSupport namespaceSupport = this.internalContext;
            if (namespaceSupport != null && (uri = namespaceSupport.getURI(str)) != null) {
                return uri;
            }
            NamespaceContext namespaceContext = this.userContext;
            if (namespaceContext != null) {
                return namespaceContext.getNamespaceURI(str);
            }
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public String getPrefix(String str) {
            String prefix;
            if (str != null) {
                str = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(str);
            }
            NamespaceSupport namespaceSupport = this.internalContext;
            if (namespaceSupport != null && (prefix = namespaceSupport.getPrefix(str)) != null) {
                return prefix;
            }
            NamespaceContext namespaceContext = this.userContext;
            if (namespaceContext != null) {
                return namespaceContext.getPrefix(str);
            }
            return null;
        }

        @Override // javax.xml.namespace.NamespaceContext
        public Iterator<String> getPrefixes(String str) {
            if (str != null) {
                str = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(str);
            }
            NamespaceContext namespaceContext = this.userContext;
            Iterator<String> prefixes = namespaceContext != null ? namespaceContext.getPrefixes(str) : null;
            NamespaceSupport namespaceSupport = this.internalContext;
            List<String> prefixes2 = namespaceSupport != null ? namespaceSupport.getPrefixes(str) : null;
            if (prefixes2 == null && prefixes != null) {
                return prefixes;
            }
            if (prefixes2 != null && prefixes == null) {
                return new ReadOnlyIterator(prefixes2.iterator());
            }
            if (prefixes2 == null || prefixes == null) {
                return XMLStreamWriterImpl.this.fReadOnlyIterator;
            }
            while (prefixes.hasNext()) {
                String next = prefixes.next();
                if (next != null) {
                    next = XMLStreamWriterImpl.this.fSymbolTable.addSymbol(next);
                }
                if (!prefixes2.contains(next)) {
                    prefixes2.add(next);
                }
            }
            return new ReadOnlyIterator(prefixes2.iterator());
        }
    }

    public XMLStreamWriterImpl(StreamResult streamResult, String str, PropertyManager propertyManager) throws IOException {
        this.fEscapeCharacters = true;
        this.fIsRepairingNamespace = false;
        this.fOutputStream = null;
        this.fNamespaceContext = null;
        this.fInternalNamespaceContext = null;
        this.fPrefixGen = null;
        this.fPropertyManager = null;
        this.fStartTagOpened = false;
        this.fSymbolTable = new SymbolTable();
        this.fElementStack = new ElementStack();
        this.DEFAULT_PREFIX = this.fSymbolTable.addSymbol("");
        this.fReadOnlyIterator = new ReadOnlyIterator<>();
        this.fEncoder = null;
        this.fAttrNamespace = null;
        setOutput(streamResult, str);
        this.fPropertyManager = propertyManager;
        init();
    }

    private void addAttrNamespace(String str, String str2) {
        if (this.fAttrNamespace == null) {
            this.fAttrNamespace = new HashMap();
        }
        this.fAttrNamespace.put(str, str2);
    }

    private boolean checkUserNamespaceContext(String str, String str2) {
        String namespaceURI;
        NamespaceContext namespaceContext = this.fNamespaceContext.userContext;
        return (namespaceContext == null || (namespaceURI = namespaceContext.getNamespaceURI(str)) == null || !namespaceURI.equals(str2)) ? false : true;
    }

    private void closeStartTag() throws XMLStreamException {
        List<QName> list;
        String prefix;
        try {
            ElementState elementStatePeek = this.fElementStack.peek();
            if (this.fIsRepairingNamespace) {
                repair();
                correctPrefix(elementStatePeek, 1);
                String str = elementStatePeek.prefix;
                if (str != null && str != "") {
                    this.fWriter.write(str);
                    this.fWriter.write(":");
                }
                this.fWriter.write(elementStatePeek.localpart);
                int size = this.fNamespaceDecls.size();
                int i = 0;
                while (true) {
                    list = this.fNamespaceDecls;
                    if (i >= size) {
                        break;
                    }
                    QName qName = list.get(i);
                    if (qName != null && this.fInternalNamespaceContext.declarePrefix(qName.prefix, qName.uri)) {
                        writenamespace(qName.prefix, qName.uri);
                    }
                    i++;
                }
                list.clear();
                for (int i2 = 0; i2 < this.fAttributeCache.size(); i2++) {
                    Attribute attribute = this.fAttributeCache.get(i2);
                    String str2 = attribute.prefix;
                    if (str2 != null && attribute.uri != null && !str2.isEmpty() && !attribute.uri.isEmpty() && ((prefix = this.fInternalNamespaceContext.getPrefix(attribute.uri)) == null || !prefix.equals(attribute.prefix))) {
                        if (getAttrPrefix(attribute.uri) != null) {
                            writenamespace(attribute.prefix, attribute.uri);
                        } else if (this.fInternalNamespaceContext.declarePrefix(attribute.prefix, attribute.uri)) {
                            writenamespace(attribute.prefix, attribute.uri);
                        }
                    }
                    writeAttributeWithPrefix(attribute.prefix, attribute.localpart, attribute.value);
                }
                this.fAttrNamespace = null;
                this.fAttributeCache.clear();
            }
            if (elementStatePeek.isEmpty) {
                this.fElementStack.pop();
                this.fInternalNamespaceContext.popContext();
                this.fWriter.write("/>");
            } else {
                this.fWriter.write(62);
            }
            this.fStartTagOpened = false;
        } catch (IOException e) {
            this.fStartTagOpened = false;
            az3.a(e);
        }
    }

    private void correctPrefix(QName qName, int i) {
        String str = qName.prefix;
        String str2 = qName.uri;
        if (str == null || str.equals("")) {
            if (str2 == null) {
                return;
            }
            if ("".equals(str) && str2.equals("")) {
                return;
            }
            String strAddSymbol = this.fSymbolTable.addSymbol(str2);
            boolean z = false;
            for (int i2 = 0; i2 < this.fNamespaceDecls.size(); i2++) {
                QName qName2 = this.fNamespaceDecls.get(i2);
                if (qName2 != null && qName2.uri.equals(qName.uri)) {
                    qName.prefix = qName2.prefix;
                    return;
                }
            }
            String prefix = this.fNamespaceContext.getPrefix(strAddSymbol);
            if ("".equals(prefix)) {
                if (i == 1) {
                    return;
                }
                if (i == 10) {
                    prefix = getAttrPrefix(strAddSymbol);
                    z = true;
                }
            }
            String strAddSymbol2 = prefix == null ? this.fSymbolTable.addSymbol("zdef" + this.fPrefixGen.nextInt()) : this.fSymbolTable.addSymbol(prefix);
            if (prefix == null) {
                if (z) {
                    addAttrNamespace(strAddSymbol2, strAddSymbol);
                } else {
                    QName qName3 = new QName();
                    qName3.setValues(strAddSymbol2, "xmlns", null, strAddSymbol);
                    this.fNamespaceDecls.add(qName3);
                    this.fInternalNamespaceContext.declarePrefix(this.fSymbolTable.addSymbol(strAddSymbol2), strAddSymbol);
                }
            }
            str = strAddSymbol2;
        }
        qName.prefix = str;
    }

    private String getAttrPrefix(String str) {
        Map<String, String> map = this.fAttrNamespace;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private void init() {
        this.fReuse = false;
        this.fNamespaceDecls = new ArrayList();
        this.fPrefixGen = new Random();
        this.fAttributeCache = new ArrayList();
        NamespaceSupport namespaceSupport = new NamespaceSupport();
        this.fInternalNamespaceContext = namespaceSupport;
        namespaceSupport.reset();
        NamespaceContextImpl namespaceContextImpl = new NamespaceContextImpl();
        this.fNamespaceContext = namespaceContextImpl;
        namespaceContextImpl.internalContext = this.fInternalNamespaceContext;
        this.fIsRepairingNamespace = ((Boolean) this.fPropertyManager.getProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES)).booleanValue();
        setEscapeCharacters(((Boolean) this.fPropertyManager.getProperty(Constants.ESCAPE_CHARACTERS)).booleanValue());
    }

    private boolean isDefaultNamespace(String str) {
        return Objects.equals(str, this.fInternalNamespaceContext.getURI(this.DEFAULT_PREFIX));
    }

    private void openStartTag() throws IOException {
        this.fStartTagOpened = true;
        this.fWriter.write(60);
    }

    private void setOutputUsingStream(OutputStream outputStream, String str) throws IOException {
        this.fOutputStream = outputStream;
        if (str != null) {
            if (str.equalsIgnoreCase(WriterUtility.UTF_8)) {
                this.fWriter = new UTF8OutputStreamWriter(outputStream);
                return;
            } else {
                this.fWriter = new XMLWriter(new OutputStreamWriter(outputStream, str));
                this.fEncoder = Charset.forName(str).newEncoder();
                return;
            }
        }
        String systemProperty = SecuritySupport.getSystemProperty("file.encoding");
        if (systemProperty == null || !systemProperty.equalsIgnoreCase(WriterUtility.UTF_8)) {
            this.fWriter = new XMLWriter(new OutputStreamWriter(outputStream));
        } else {
            this.fWriter = new UTF8OutputStreamWriter(outputStream);
        }
    }

    private void setOutputUsingWriter(Writer writer) throws IOException {
        String encoding;
        this.fWriter = writer;
        if (!(writer instanceof OutputStreamWriter) || (encoding = ((OutputStreamWriter) writer).getEncoding()) == null || encoding.equalsIgnoreCase(WriterUtility.UTF_8)) {
            return;
        }
        this.fEncoder = Charset.forName(encoding).newEncoder();
    }

    private void verifyEncoding(String str) throws XMLStreamException {
        String encoding;
        Writer writer = this.fWriter;
        if (writer instanceof OutputStreamWriter) {
            encoding = ((OutputStreamWriter) writer).getEncoding();
        } else if (writer instanceof UTF8OutputStreamWriter) {
            encoding = ((UTF8OutputStreamWriter) writer).getEncoding();
        } else {
            encoding = writer instanceof XMLWriter ? ((OutputStreamWriter) ((XMLWriter) writer).getWriter()).getEncoding() : null;
        }
        if (encoding == null || encoding.equalsIgnoreCase(str)) {
            return;
        }
        Iterator<String> it = Charset.forName(str).aliases().iterator();
        boolean z = false;
        while (!z && it.hasNext()) {
            if (encoding.equalsIgnoreCase(it.next())) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        throw new XMLStreamException("Underlying stream encoding '" + encoding + "' and input paramter for writeStartDocument() method '" + str + "' do not match.");
    }

    private void writeAttributeWithPrefix(String str, String str2, String str3) throws IOException {
        this.fWriter.write(" ");
        if (str != null && !str.equals("")) {
            this.fWriter.write(str);
            this.fWriter.write(":");
        }
        this.fWriter.write(str2);
        this.fWriter.write("=\"");
        writeXMLContent(str3, true, true);
        this.fWriter.write("\"");
    }

    private void writeCharRef(int i) throws IOException {
        this.fWriter.write("&#x");
        this.fWriter.write(Integer.toHexString(i));
        this.fWriter.write(59);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0042  */
    private void writeXMLContent(String str, boolean z, boolean z2) throws IOException {
        if (!z) {
            this.fWriter.write(str);
            return;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char cCharAt = str.charAt(i);
            CharsetEncoder charsetEncoder = this.fEncoder;
            if (charsetEncoder != null && !charsetEncoder.canEncode(cCharAt)) {
                this.fWriter.write(str, i2, i - i2);
                if (i != length - 1) {
                    int i3 = i + 1;
                    if (Character.isSurrogatePair(cCharAt, str.charAt(i3))) {
                        writeCharRef(Character.toCodePoint(cCharAt, str.charAt(i3)));
                        i = i3;
                    } else {
                        writeCharRef(cCharAt);
                    }
                } else {
                    writeCharRef(cCharAt);
                }
            } else if (cCharAt == '\"') {
                this.fWriter.write(str, i2, i - i2);
                Writer writer = this.fWriter;
                if (z2) {
                    writer.write(SerializerConstants.ENTITY_QUOT);
                } else {
                    writer.write(34);
                }
            } else if (cCharAt == '&') {
                this.fWriter.write(str, i2, i - i2);
                this.fWriter.write(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt != '<') {
                if (cCharAt == '>') {
                    this.fWriter.write(str, i2, i - i2);
                    this.fWriter.write(SerializerConstants.ENTITY_GT);
                }
                i++;
            } else {
                this.fWriter.write(str, i2, i - i2);
                this.fWriter.write(SerializerConstants.ENTITY_LT);
            }
            i2 = i + 1;
            i++;
        }
        this.fWriter.write(str, i2, length - i2);
    }

    private void writenamespace(String str, String str2) throws IOException {
        this.fWriter.write(" xmlns");
        if (str != null && !str.equals("")) {
            this.fWriter.write(":");
            this.fWriter.write(str);
        }
        this.fWriter.write("=\"");
        writeXMLContent(str2, true, true);
        this.fWriter.write("\"");
    }

    public boolean canReuse() {
        return this.fReuse;
    }

    public void checkForNull(QName qName) {
        if (qName.prefix == null) {
            qName.prefix = "";
        }
        if (qName.uri == null) {
            qName.uri = "";
        }
    }

    public void close() throws XMLStreamException {
        Writer writer = this.fWriter;
        if (writer != null) {
            try {
                writer.flush();
            } catch (IOException e) {
                az3.a(e);
                return;
            }
        }
        this.fWriter = null;
        this.fOutputStream = null;
        this.fNamespaceDecls.clear();
        this.fAttributeCache.clear();
        this.fElementStack.clear();
        this.fInternalNamespaceContext.reset();
        this.fReuse = true;
        this.fStartTagOpened = false;
        this.fNamespaceContext.userContext = null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return obj.equals(OUTPUTSTREAM_PROPERTY);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<Object, Object>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        return this == obj;
    }

    public void flush() throws XMLStreamException {
        try {
            this.fWriter.flush();
        } catch (IOException e) {
            az3.a(e);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        if (obj.equals(OUTPUTSTREAM_PROPERTY)) {
            return this.fOutputStream;
        }
        return null;
    }

    public boolean getEscapeCharacters() {
        return this.fEscapeCharacters;
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    public String getPrefix(String str) throws XMLStreamException {
        return this.fNamespaceContext.getPrefix(str);
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        str.getClass();
        if (this.fPropertyManager.containsProperty(str)) {
            return this.fPropertyManager.getProperty(str);
        }
        kg9.a("Property '", str, "' is not supported");
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return this.fElementStack.hashCode();
    }

    public boolean isDeclared(QName qName) {
        for (int i = 0; i < this.fNamespaceDecls.size(); i++) {
            QName qName2 = this.fNamespaceDecls.get(i);
            String str = qName.prefix;
            if (str != null && str.equals(qName2.prefix) && qName2.uri.equals(qName.uri)) {
                return true;
            }
        }
        String str2 = qName.uri;
        return (str2 == null || this.fNamespaceContext.getPrefix(str2) == null) ? false : true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return false;
    }

    public void removeDuplicateDecls() {
        for (int i = 0; i < this.fNamespaceDecls.size(); i++) {
            QName qName = this.fNamespaceDecls.get(i);
            if (qName != null) {
                for (int i2 = i + 1; i2 < this.fNamespaceDecls.size(); i2++) {
                    QName qName2 = this.fNamespaceDecls.get(i2);
                    if (qName2 != null && qName.prefix.equals(qName2.prefix) && qName.uri.equals(qName2.uri)) {
                        this.fNamespaceDecls.remove(i2);
                    }
                }
            }
        }
    }

    public void repair() {
        String str;
        String str2;
        String str3;
        ElementState elementStatePeek = this.fElementStack.peek();
        removeDuplicateDecls();
        for (int i = 0; i < this.fAttributeCache.size(); i++) {
            Attribute attribute = this.fAttributeCache.get(i);
            String str4 = attribute.prefix;
            if ((str4 != null && !str4.isEmpty()) || ((str3 = attribute.uri) != null && !str3.isEmpty())) {
                correctPrefix(elementStatePeek, attribute);
            }
        }
        if (!isDeclared(elementStatePeek) && (str2 = elementStatePeek.prefix) != null && elementStatePeek.uri != null && !str2.isEmpty() && !elementStatePeek.uri.isEmpty()) {
            this.fNamespaceDecls.add(elementStatePeek);
        }
        int i2 = 0;
        while (i2 < this.fAttributeCache.size()) {
            Attribute attribute2 = this.fAttributeCache.get(i2);
            i2++;
            for (int i3 = i2; i3 < this.fAttributeCache.size(); i3++) {
                Attribute attribute3 = this.fAttributeCache.get(i3);
                if (!"".equals(attribute2.prefix) && !"".equals(attribute3.prefix)) {
                    correctPrefix(attribute2, attribute3);
                }
            }
        }
        repairNamespaceDecl(elementStatePeek);
        for (int i4 = 0; i4 < this.fAttributeCache.size(); i4++) {
            Attribute attribute4 = this.fAttributeCache.get(i4);
            String str5 = attribute4.prefix;
            if (str5 != null && str5.isEmpty() && (str = attribute4.uri) != null && str.isEmpty()) {
                repairNamespaceDecl(attribute4);
            }
        }
        for (int i5 = 0; i5 < this.fNamespaceDecls.size(); i5++) {
            QName qName = this.fNamespaceDecls.get(i5);
            if (qName != null) {
                this.fInternalNamespaceContext.declarePrefix(qName.prefix, qName.uri);
            }
        }
        for (int i6 = 0; i6 < this.fAttributeCache.size(); i6++) {
            correctPrefix(this.fAttributeCache.get(i6), 10);
        }
    }

    public void repairNamespaceDecl(QName qName) {
        String str;
        String namespaceURI;
        for (int i = 0; i < this.fNamespaceDecls.size(); i++) {
            QName qName2 = this.fNamespaceDecls.get(i);
            if (qName2 != null && (str = qName.prefix) != null && str.equals(qName2.prefix) && !qName.uri.equals(qName2.uri) && (namespaceURI = this.fNamespaceContext.getNamespaceURI(qName.prefix)) != null) {
                if (namespaceURI.equals(qName.uri)) {
                    this.fNamespaceDecls.set(i, null);
                } else {
                    qName2.uri = qName.uri;
                }
            }
        }
    }

    public void reset(boolean z) {
        if (!this.fReuse) {
            k2d.a("close() Must be called before calling reset()");
            return;
        }
        this.fReuse = false;
        this.fNamespaceDecls.clear();
        this.fAttributeCache.clear();
        this.fElementStack.clear();
        this.fInternalNamespaceContext.reset();
        this.fStartTagOpened = false;
        this.fNamespaceContext.userContext = null;
        if (z) {
            this.fIsRepairingNamespace = ((Boolean) this.fPropertyManager.getProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES)).booleanValue();
            setEscapeCharacters(((Boolean) this.fPropertyManager.getProperty(Constants.ESCAPE_CHARACTERS)).booleanValue());
        }
    }

    public void setDefaultNamespace(String str) throws XMLStreamException {
        if (str != null) {
            str = this.fSymbolTable.addSymbol(str);
        }
        if (!this.fIsRepairingNamespace) {
            this.fInternalNamespaceContext.declarePrefix(this.DEFAULT_PREFIX, str);
        } else {
            if (isDefaultNamespace(str)) {
                return;
            }
            QName qName = new QName();
            qName.setValues(this.DEFAULT_PREFIX, "xmlns", null, str);
            this.fNamespaceDecls.add(qName);
        }
    }

    public void setEscapeCharacters(boolean z) {
        this.fEscapeCharacters = z;
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        this.fNamespaceContext.userContext = namespaceContext;
    }

    public void setOutput(StreamResult streamResult, String str) throws IOException {
        if (streamResult.getOutputStream() != null) {
            setOutputUsingStream(streamResult.getOutputStream(), str);
        } else if (streamResult.getWriter() != null) {
            setOutputUsingWriter(streamResult.getWriter());
        } else if (streamResult.getSystemId() != null) {
            setOutputUsingStream(new FileOutputStream(streamResult.getSystemId()), str);
        }
    }

    public void setPrefix(String str, String str2) throws XMLStreamException {
        if (str == null) {
            jnd.a("Prefix cannot be null");
            return;
        }
        if (str2 == null) {
            jnd.a("URI cannot be null");
            return;
        }
        String strAddSymbol = this.fSymbolTable.addSymbol(str);
        String strAddSymbol2 = this.fSymbolTable.addSymbol(str2);
        boolean z = this.fIsRepairingNamespace;
        NamespaceSupport namespaceSupport = this.fInternalNamespaceContext;
        if (!z) {
            namespaceSupport.declarePrefix(strAddSymbol, strAddSymbol2);
            return;
        }
        String uri = namespaceSupport.getURI(strAddSymbol);
        if ((uri == null || uri != strAddSymbol2) && !checkUserNamespaceContext(strAddSymbol, strAddSymbol2)) {
            QName qName = new QName();
            qName.setValues(strAddSymbol, "xmlns", null, strAddSymbol2);
            this.fNamespaceDecls.add(qName);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return 1;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return XMLStreamWriterImpl.class.getName() + "@" + Integer.toHexString(hashCode());
    }

    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        String uri;
        try {
            if (!this.fStartTagOpened) {
                throw new XMLStreamException("Attribute not associated with any element");
            }
            if (str2 == null) {
                throw new XMLStreamException("NamespaceURI cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException("Local name cannot be null");
            }
            if (this.fIsRepairingNamespace) {
                if (str != null) {
                    str = this.fSymbolTable.addSymbol(str);
                }
                String strAddSymbol = this.fSymbolTable.addSymbol(str2);
                Attribute attribute = new Attribute(str4);
                attribute.setValues(str, str3, null, strAddSymbol);
                this.fAttributeCache.add(attribute);
                return;
            }
            if (str != null && !str.isEmpty()) {
                if (!str.equals("xml") || !str2.equals("http://www.w3.org/XML/1998/namespace")) {
                    str = this.fSymbolTable.addSymbol(str);
                    String strAddSymbol2 = this.fSymbolTable.addSymbol(str2);
                    if (this.fInternalNamespaceContext.containsPrefixInCurrentContext(str) && (uri = this.fInternalNamespaceContext.getURI(str)) != null && uri != strAddSymbol2) {
                        throw new XMLStreamException("Prefix " + str + " is already bound to " + uri + ". Trying to rebind it to " + strAddSymbol2 + " is an error.");
                    }
                    this.fInternalNamespaceContext.declarePrefix(str, strAddSymbol2);
                }
                writeAttributeWithPrefix(str, str3, str4);
                return;
            }
            if (!str2.isEmpty()) {
                throw new XMLStreamException("prefix cannot be null or empty");
            }
            writeAttributeWithPrefix(null, str3, str4);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeCData(String str) throws XMLStreamException {
        try {
            if (str == null) {
                throw new XMLStreamException("cdata cannot be null");
            }
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write("<![CDATA[");
            this.fWriter.write(str);
            this.fWriter.write("]]>");
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            writeXMLContent(cArr, i, i2, this.fEscapeCharacters);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeComment(String str) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write("<!--");
            if (str != null) {
                this.fWriter.write(str);
            }
            this.fWriter.write("-->");
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeDTD(String str) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write(str);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeDefaultNamespace(String str) throws XMLStreamException {
        String uri;
        if (str == null) {
            str = "";
        }
        try {
            if (!this.fStartTagOpened) {
                throw new IllegalStateException("Namespace Attribute not associated with any element");
            }
            if (this.fIsRepairingNamespace) {
                QName qName = new QName();
                qName.setValues("", "xmlns", null, str);
                this.fNamespaceDecls.add(qName);
                return;
            }
            String strAddSymbol = this.fSymbolTable.addSymbol(str);
            if (this.fInternalNamespaceContext.containsPrefixInCurrentContext("") && (uri = this.fInternalNamespaceContext.getURI("")) != null && !uri.equals(strAddSymbol)) {
                throw new XMLStreamException("xmlns has been already bound to " + uri + ". Rebinding it to " + strAddSymbol + " is an error");
            }
            this.fInternalNamespaceContext.declarePrefix("", strAddSymbol);
            writenamespace(null, strAddSymbol);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        try {
            if (str2 == null) {
                throw new XMLStreamException("Local Name cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException("NamespaceURI cannot be null");
            }
            if (str != null) {
                str = this.fSymbolTable.addSymbol(str);
            }
            String str4 = str;
            String strAddSymbol = this.fSymbolTable.addSymbol(str3);
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            this.fElementStack.push(str4, str2, null, strAddSymbol, true);
            this.fInternalNamespaceContext.pushContext();
            if (this.fIsRepairingNamespace) {
                return;
            }
            if (str4 == null) {
                throw new XMLStreamException("NamespaceURI " + strAddSymbol + " has not been bound to any prefix");
            }
            if (!str4.equals("")) {
                this.fWriter.write(str4);
                this.fWriter.write(":");
            }
            this.fWriter.write(str2);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeEndDocument() throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            while (!this.fElementStack.empty()) {
                ElementState elementStatePop = this.fElementStack.pop();
                this.fInternalNamespaceContext.popContext();
                if (!elementStatePop.isEmpty) {
                    this.fWriter.write("</");
                    String str = elementStatePop.prefix;
                    if (str != null && !str.isEmpty()) {
                        this.fWriter.write(elementStatePop.prefix);
                        this.fWriter.write(":");
                    }
                    this.fWriter.write(elementStatePop.localpart);
                    this.fWriter.write(62);
                }
            }
        } catch (IOException e) {
            az3.a(e);
        } catch (ArrayIndexOutOfBoundsException unused) {
            jnd.a("No more elements to write");
        }
    }

    public void writeEndElement() throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            ElementState elementStatePop = this.fElementStack.pop();
            if (elementStatePop == null) {
                throw new XMLStreamException("No element was found to write");
            }
            if (elementStatePop.isEmpty) {
                return;
            }
            this.fWriter.write("</");
            String str = elementStatePop.prefix;
            if (str != null && !str.isEmpty()) {
                this.fWriter.write(elementStatePop.prefix);
                this.fWriter.write(":");
            }
            this.fWriter.write(elementStatePop.localpart);
            this.fWriter.write(62);
            this.fInternalNamespaceContext.popContext();
        } catch (IOException e) {
            az3.a(e);
        } catch (ArrayIndexOutOfBoundsException e2) {
            throw new XMLStreamException("No element was found to write: " + e2.toString(), e2);
        }
    }

    public void writeEntityRef(String str) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            this.fWriter.write(38);
            this.fWriter.write(str);
            this.fWriter.write(59);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeNamespace(String str, String str2) throws XMLStreamException {
        String uri;
        if (str2 == null) {
            str2 = "";
        }
        try {
            if (!this.fStartTagOpened) {
                throw new IllegalStateException("Invalid state: start tag is not opened at writeNamespace(" + str + ", " + str2 + ")");
            }
            if (str != null && !str.equals("") && !str.equals("xmlns")) {
                if (str.equals("xml") && str2.equals("http://www.w3.org/XML/1998/namespace")) {
                    return;
                }
                String strAddSymbol = this.fSymbolTable.addSymbol(str);
                String strAddSymbol2 = this.fSymbolTable.addSymbol(str2);
                boolean z = this.fIsRepairingNamespace;
                NamespaceSupport namespaceSupport = this.fInternalNamespaceContext;
                if (z) {
                    String uri2 = namespaceSupport.getURI(strAddSymbol);
                    if (uri2 == null || !uri2.equals(strAddSymbol2)) {
                        QName qName = new QName();
                        qName.setValues(strAddSymbol, "xmlns", null, strAddSymbol2);
                        this.fNamespaceDecls.add(qName);
                        return;
                    }
                    return;
                }
                if (namespaceSupport.containsPrefixInCurrentContext(strAddSymbol) && (uri = this.fInternalNamespaceContext.getURI(strAddSymbol)) != null && !uri.equals(strAddSymbol2)) {
                    throw new XMLStreamException("prefix " + strAddSymbol + " has been already bound to " + uri + ". Rebinding it to " + strAddSymbol2 + " is an error");
                }
                this.fInternalNamespaceContext.declarePrefix(strAddSymbol, strAddSymbol2);
                writenamespace(strAddSymbol, strAddSymbol2);
                return;
            }
            writeDefaultNamespace(str2);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            if (str == null || str2 == null) {
                throw new XMLStreamException("PI target cannot be null");
            }
            this.fWriter.write("<?");
            this.fWriter.write(str);
            this.fWriter.write(" ");
            this.fWriter.write(str2);
            this.fWriter.write("?>");
        } catch (IOException e) {
            az3.a(e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:5:0x0008  */
    public void writeStartDocument(String str, String str2, boolean z, boolean z2) throws XMLStreamException {
        if (str == null) {
            if (str2 != null) {
                this.fWriter.write("<?xml version=\"1.0\" ?>");
                return;
            } else {
                this.fWriter.write("<?xml version=\"1.0\" ?>");
                return;
            }
        }
        try {
            if (str.length() == 0) {
                if ((str2 != null || str2.length() == 0) && !z2) {
                    this.fWriter.write("<?xml version=\"1.0\" ?>");
                    return;
                }
            }
        } catch (IOException e) {
            az3.a(e);
            return;
        }
        if (str != null && !str.isEmpty()) {
            verifyEncoding(str);
        }
        this.fWriter.write("<?xml version=\"");
        if (str2 == null || str2.isEmpty()) {
            this.fWriter.write("1.0");
        } else {
            this.fWriter.write(str2);
        }
        if (str != null && !str.isEmpty()) {
            this.fWriter.write("\" encoding=\"");
            this.fWriter.write(str);
        }
        if (z2) {
            this.fWriter.write("\" standalone=\"");
            Writer writer = this.fWriter;
            if (z) {
                writer.write(JdkConstants.JDK_YES);
            } else {
                writer.write("no");
            }
        }
        this.fWriter.write("\"?>");
    }

    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        try {
            if (str2 == null) {
                throw new XMLStreamException("Local Name cannot be null");
            }
            if (str3 == null) {
                throw new XMLStreamException("NamespaceURI cannot be null");
            }
            if (!this.fIsRepairingNamespace && str == null) {
                throw new XMLStreamException("Prefix cannot be null");
            }
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            String strAddSymbol = this.fSymbolTable.addSymbol(str3);
            if (str != null) {
                str = this.fSymbolTable.addSymbol(str);
            }
            String str4 = str;
            this.fElementStack.push(str4, str2, null, strAddSymbol, false);
            this.fInternalNamespaceContext.pushContext();
            String prefix = this.fNamespaceContext.getPrefix(strAddSymbol);
            if (str4 != null && (prefix == null || !str4.equals(prefix))) {
                this.fInternalNamespaceContext.declarePrefix(str4, strAddSymbol);
            }
            if (!this.fIsRepairingNamespace) {
                if (str4 != null && str4 != "") {
                    this.fWriter.write(str4);
                    this.fWriter.write(":");
                }
                this.fWriter.write(str2);
                return;
            }
            if (str4 != null) {
                if (prefix == null || !str4.equals(prefix)) {
                    QName qName = new QName();
                    qName.setValues(str4, "xmlns", null, strAddSymbol);
                    this.fNamespaceDecls.add(qName);
                }
            }
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public class ElementState extends QName {
        public boolean isEmpty;

        public ElementState() {
            this.isEmpty = false;
        }

        public void setValues(String str, String str2, String str3, String str4, boolean z) {
            super.setValues(str, str2, str3, str4);
            this.isEmpty = z;
        }

        public ElementState(String str, String str2, String str3, String str4) {
            super(str, str2, str3, str4);
            this.isEmpty = false;
        }
    }

    public void writeCharacters(String str) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            writeXMLContent(str);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeProcessingInstruction(String str) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            if (str != null) {
                this.fWriter.write("<?");
                this.fWriter.write(str);
                this.fWriter.write("?>");
                return;
            }
            jnd.a("PI target cannot be null");
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public class ElementStack {
        protected short fDepth;
        protected ElementState[] fElements = new ElementState[10];

        public ElementStack() {
            int i = 0;
            while (true) {
                ElementState[] elementStateArr = this.fElements;
                if (i >= elementStateArr.length) {
                    return;
                }
                elementStateArr[i] = XMLStreamWriterImpl.this.new ElementState();
                i++;
            }
        }

        public void clear() {
            this.fDepth = (short) 0;
        }

        public boolean empty() {
            return this.fDepth <= 0;
        }

        public ElementState peek() {
            return this.fElements[this.fDepth - 1];
        }

        public ElementState pop() {
            ElementState[] elementStateArr = this.fElements;
            short s = (short) (this.fDepth - 1);
            this.fDepth = s;
            return elementStateArr[s];
        }

        public ElementState push(String str, String str2, String str3, String str4, boolean z) {
            short s = this.fDepth;
            ElementState[] elementStateArr = this.fElements;
            if (s == elementStateArr.length) {
                ElementState[] elementStateArr2 = new ElementState[elementStateArr.length * 2];
                System.arraycopy(elementStateArr, 0, elementStateArr2, 0, s);
                this.fElements = elementStateArr2;
                int i = this.fDepth;
                while (true) {
                    ElementState[] elementStateArr3 = this.fElements;
                    if (i >= elementStateArr3.length) {
                        break;
                    }
                    elementStateArr3[i] = XMLStreamWriterImpl.this.new ElementState();
                    i++;
                }
            }
            this.fElements[this.fDepth].setValues(str, str2, str3, str4, z);
            ElementState[] elementStateArr4 = this.fElements;
            short s2 = this.fDepth;
            this.fDepth = (short) (s2 + 1);
            return elementStateArr4[s2];
        }

        public ElementState push(ElementState elementState) {
            short s = this.fDepth;
            ElementState[] elementStateArr = this.fElements;
            if (s == elementStateArr.length) {
                ElementState[] elementStateArr2 = new ElementState[elementStateArr.length * 2];
                System.arraycopy(elementStateArr, 0, elementStateArr2, 0, s);
                this.fElements = elementStateArr2;
                int i = this.fDepth;
                while (true) {
                    ElementState[] elementStateArr3 = this.fElements;
                    if (i >= elementStateArr3.length) {
                        break;
                    }
                    elementStateArr3[i] = XMLStreamWriterImpl.this.new ElementState();
                    i++;
                }
            }
            this.fElements[this.fDepth].setValues(elementState);
            ElementState[] elementStateArr4 = this.fElements;
            short s2 = this.fDepth;
            this.fDepth = (short) (s2 + 1);
            return elementStateArr4[s2];
        }
    }

    public XMLStreamWriterImpl(OutputStream outputStream, String str, PropertyManager propertyManager) throws IOException {
        this(new StreamResult(outputStream), str, propertyManager);
    }

    public XMLStreamWriterImpl(Writer writer, PropertyManager propertyManager) throws IOException {
        this(new StreamResult(writer), (String) null, propertyManager);
    }

    public XMLStreamWriterImpl(OutputStream outputStream, PropertyManager propertyManager) throws IOException {
        this(new OutputStreamWriter(outputStream), propertyManager);
    }

    public void reset() {
        reset(false);
    }

    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        if (str != null) {
            String strAddSymbol = this.fSymbolTable.addSymbol(str);
            writeEmptyElement(this.fNamespaceContext.getPrefix(strAddSymbol), str2, strAddSymbol);
        } else {
            jnd.a("NamespaceURI cannot be null");
        }
    }

    public void writeStartDocument(String str) throws XMLStreamException {
        writeStartDocument(null, str, false, false);
    }

    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        writeStartDocument(str, str2, false, false);
    }

    public void writeEmptyElement(String str) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                closeStartTag();
            }
            openStartTag();
            this.fElementStack.push(null, str, null, null, true);
            this.fInternalNamespaceContext.pushContext();
            if (this.fIsRepairingNamespace) {
                return;
            }
            this.fWriter.write(str);
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeStartDocument() throws XMLStreamException {
        writeStartDocument(null, null, false, false);
    }

    public void writeStartElement(String str, String str2) throws XMLStreamException {
        String prefix;
        if (str2 == null) {
            jnd.a("Local Name cannot be null");
            return;
        }
        if (str != null) {
            String strAddSymbol = this.fSymbolTable.addSymbol(str);
            if (this.fIsRepairingNamespace) {
                prefix = null;
            } else {
                prefix = this.fNamespaceContext.getPrefix(strAddSymbol);
                if (prefix != null) {
                    prefix = this.fSymbolTable.addSymbol(prefix);
                }
            }
            writeStartElement(prefix, str2, strAddSymbol);
            return;
        }
        jnd.a("NamespaceURI cannot be null");
    }

    public void writeStartElement(String str) throws XMLStreamException {
        try {
            if (str != null) {
                if (this.fStartTagOpened) {
                    closeStartTag();
                }
                openStartTag();
                this.fElementStack.push(null, str, null, null, false);
                this.fInternalNamespaceContext.pushContext();
                if (this.fIsRepairingNamespace) {
                    return;
                }
                this.fWriter.write(str);
                return;
            }
            throw new XMLStreamException("Local Name cannot be null");
        } catch (IOException e) {
            az3.a(e);
        }
    }

    private void writeXMLContent(String str) throws IOException {
        if (str == null || str.length() <= 0) {
            return;
        }
        writeXMLContent(str, this.fEscapeCharacters, false);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0038  */
    private void writeXMLContent(char[] cArr, int i, int i2, boolean z) throws IOException {
        if (!z) {
            this.fWriter.write(cArr, i, i2);
            return;
        }
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            CharsetEncoder charsetEncoder = this.fEncoder;
            if (charsetEncoder != null && !charsetEncoder.canEncode(c)) {
                this.fWriter.write(cArr, i4, i - i4);
                if (i != i3 - 1) {
                    int i5 = i + 1;
                    if (Character.isSurrogatePair(c, cArr[i5])) {
                        writeCharRef(Character.toCodePoint(c, cArr[i5]));
                        i = i5;
                    } else {
                        writeCharRef(c);
                    }
                } else {
                    writeCharRef(c);
                }
            } else if (c == '&') {
                this.fWriter.write(cArr, i4, i - i4);
                this.fWriter.write(SerializerConstants.ENTITY_AMP);
            } else if (c != '<') {
                if (c == '>') {
                    this.fWriter.write(cArr, i4, i - i4);
                    this.fWriter.write(SerializerConstants.ENTITY_GT);
                }
                i++;
            } else {
                this.fWriter.write(cArr, i4, i - i4);
                this.fWriter.write(SerializerConstants.ENTITY_LT);
            }
            i4 = i + 1;
            i++;
        }
        this.fWriter.write(cArr, i4, i3 - i4);
    }

    public void correctPrefix(QName qName, QName qName2) {
        checkForNull(qName);
        checkForNull(qName2);
        if (!qName.prefix.equals(qName2.prefix) || qName.uri.equals(qName2.uri)) {
            return;
        }
        String prefix = this.fNamespaceContext.getPrefix(qName2.uri);
        if (prefix != null) {
            qName2.prefix = this.fSymbolTable.addSymbol(prefix);
            return;
        }
        for (int i = 0; i < this.fNamespaceDecls.size(); i++) {
            QName qName3 = this.fNamespaceDecls.get(i);
            if (qName3 != null && qName3.uri.equals(qName2.uri)) {
                qName2.prefix = qName3.prefix;
                return;
            }
        }
        String strAddSymbol = this.fSymbolTable.addSymbol("zdef" + this.fPrefixGen.nextInt());
        qName2.prefix = strAddSymbol;
        QName qName4 = new QName();
        qName4.setValues(strAddSymbol, "xmlns", null, qName2.uri);
        this.fNamespaceDecls.add(qName4);
    }

    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        try {
            if (!this.fStartTagOpened) {
                throw new XMLStreamException("Attribute not associated with any element");
            }
            if (str != null) {
                String strAddSymbol = this.fSymbolTable.addSymbol(str);
                String prefix = this.fInternalNamespaceContext.getPrefix(strAddSymbol);
                if (this.fIsRepairingNamespace) {
                    Attribute attribute = new Attribute(str3);
                    attribute.setValues(null, str2, null, strAddSymbol);
                    this.fAttributeCache.add(attribute);
                    return;
                } else {
                    if (prefix != null) {
                        writeAttributeWithPrefix(prefix, str2, str3);
                        return;
                    }
                    throw new XMLStreamException("Prefix cannot be null");
                }
            }
            throw new XMLStreamException("NamespaceURI cannot be null");
        } catch (IOException e) {
            az3.a(e);
        }
    }

    public void writeAttribute(String str, String str2) throws XMLStreamException {
        try {
            if (this.fStartTagOpened) {
                if (this.fIsRepairingNamespace) {
                    Attribute attribute = new Attribute(str2);
                    attribute.setValues(null, str, null, null);
                    this.fAttributeCache.add(attribute);
                    return;
                } else {
                    this.fWriter.write(" ");
                    this.fWriter.write(str);
                    this.fWriter.write("=\"");
                    writeXMLContent(str2, true, true);
                    this.fWriter.write("\"");
                    return;
                }
            }
            throw new XMLStreamException("Attribute not associated with any element");
        } catch (IOException e) {
            az3.a(e);
        }
    }
}
