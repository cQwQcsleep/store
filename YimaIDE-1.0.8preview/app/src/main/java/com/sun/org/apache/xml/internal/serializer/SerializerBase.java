package com.sun.org.apache.xml.internal.serializer;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.serializer.dom3.DOM3SerializerImpl;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Set;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.Transformer;
import jdk.xml.internal.JdkConstants;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.Locator2;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SerializerBase implements SerializationHandler, SerializerConstants {
    private HashMap<String, String> m_OutputProps;
    private HashMap<String, String> m_OutputPropsDefault;
    protected String m_doctypePublic;
    protected String m_doctypeSystem;
    protected ErrorListener m_errListener;
    protected String m_mediatype;
    protected NamespaceMappings m_prefixMap;
    protected SourceLocator m_sourceLocator;
    private String m_standalone;
    protected SerializerTrace m_tracer;
    private Transformer m_transformer;
    protected boolean m_needToCallStartDocument = true;
    protected boolean m_cdataTagOpen = false;
    protected AttributesImplSerializer m_attributes = new AttributesImplSerializer();
    protected int m_inEntityRef = 0;
    protected boolean m_inExternalDTD = false;
    boolean m_needToOutputDocTypeDecl = true;
    protected boolean m_shouldNotWriteXMLHeader = false;
    protected boolean m_standaloneWasSpecified = false;
    protected boolean m_isStandalone = false;
    protected boolean m_doIndent = false;
    protected int m_indentAmount = 4;
    protected String m_version = null;
    protected Writer m_writer = null;
    protected ElemContext m_elemContext = new ElemContext();
    protected char[] m_charsBuff = new char[60];
    protected char[] m_attrBuff = new char[30];
    private Locator m_locator = null;
    protected boolean m_needToCallSetDocumentInfo = true;
    protected String m_StringOfCDATASections = null;
    boolean m_docIsEmpty = true;
    protected HashMap<String, HashMap<String, String>> m_CdataElems = null;

    private void addCDATAElement(String str, String str2) {
        if (this.m_CdataElems == null) {
            this.m_CdataElems = new HashMap<>();
        }
        HashMap<String, String> map = this.m_CdataElems.get(str2);
        if (map == null) {
            map = new HashMap<>();
            this.m_CdataElems.put(str2, map);
        }
        map.put(str, str);
    }

    private void flushMyWriter() {
        Writer writer = this.m_writer;
        if (writer != null) {
            try {
                writer.flush();
            } catch (IOException unused) {
            }
        }
    }

    private String getElementURI() {
        String prefixPart = getPrefixPart(this.m_elemContext.m_elementName);
        NamespaceMappings namespaceMappings = this.m_prefixMap;
        String strLookupNamespace = prefixPart == null ? namespaceMappings.lookupNamespace("") : namespaceMappings.lookupNamespace(prefixPart);
        return strLookupNamespace == null ? "" : strLookupNamespace;
    }

    public static char getFirstCharLocName(String str) {
        int iIndexOf = str.indexOf(125);
        return iIndexOf < 0 ? str.charAt(0) : str.charAt(iIndexOf + 1);
    }

    public static String getLocalName(String str) {
        int iLastIndexOf = str.lastIndexOf(58);
        return iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    public static final String getPrefixPart(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    private String getProp(String str, boolean z) {
        if (this.m_OutputProps == null) {
            this.m_OutputProps = new HashMap<>();
            this.m_OutputPropsDefault = new HashMap<>();
        }
        return z ? this.m_OutputPropsDefault.get(str) : this.m_OutputProps.get(str);
    }

    private void resetSerializerBase() {
        this.m_attributes.clear();
        this.m_StringOfCDATASections = null;
        this.m_elemContext = new ElemContext();
        this.m_doctypePublic = null;
        this.m_doctypeSystem = null;
        this.m_doIndent = false;
        this.m_indentAmount = 4;
        this.m_inEntityRef = 0;
        this.m_inExternalDTD = false;
        this.m_mediatype = null;
        this.m_needToCallStartDocument = true;
        this.m_needToOutputDocTypeDecl = false;
        NamespaceMappings namespaceMappings = this.m_prefixMap;
        if (namespaceMappings != null) {
            namespaceMappings.reset();
        }
        this.m_shouldNotWriteXMLHeader = false;
        this.m_sourceLocator = null;
        this.m_standalone = null;
        this.m_standaloneWasSpecified = false;
        this.m_tracer = null;
        this.m_transformer = null;
        this.m_version = null;
    }

    private static final boolean subPartMatch(String str, String str2) {
        if (str != str2) {
            return str != null && str.equals(str2);
        }
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2) {
        if (this.m_elemContext.m_startTagOpen) {
            String strPatchName = patchName(str);
            addAttributeAlways(getNamespaceURI(strPatchName, false), getLocalName(strPatchName), strPatchName, "CDATA", str2, false);
        }
    }

    public boolean addAttributeAlways(String str, String str2, String str3, String str4, String str5, boolean z) {
        int index = (str2 == null || str == null || str.length() == 0) ? this.m_attributes.getIndex(str3) : this.m_attributes.getIndex(str, str2);
        AttributesImplSerializer attributesImplSerializer = this.m_attributes;
        if (index >= 0) {
            attributesImplSerializer.setValue(index, str5);
            return false;
        }
        attributesImplSerializer.addAttribute(str, str2, str3, str4, str5);
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttributes(Attributes attributes) throws SAXException {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String uri = attributes.getURI(i);
            if (uri == null) {
                uri = "";
            }
            addAttributeAlways(uri, attributes.getLocalName(i), attributes.getQName(i), attributes.getType(i), attributes.getValue(i), false);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addXSLAttribute(String str, String str2, String str3) {
        if (this.m_elemContext.m_startTagOpen) {
            String strPatchName = patchName(str);
            addAttributeAlways(str3, getLocalName(strPatchName), strPatchName, "CDATA", str2, true);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public ContentHandler asContentHandler() throws IOException {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public Object asDOM3Serializer() throws IOException {
        return new DOM3SerializerImpl(this);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public DOMSerializer asDOMSerializer() throws IOException {
        return this;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void characters(Node node) throws SAXException {
        flushPending();
        String nodeValue = node.getNodeValue();
        if (nodeValue != null) {
            int length = nodeValue.length();
            if (length > this.m_charsBuff.length) {
                this.m_charsBuff = new char[(length * 2) + 1];
            }
            nodeValue.getChars(0, length, this.m_charsBuff, 0);
            characters(this.m_charsBuff, 0, length);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void close() {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedLexicalHandler
    public void comment(String str) throws SAXException {
        int length = str.length();
        if (length > this.m_charsBuff.length) {
            this.m_charsBuff = new char[(length * 2) + 1];
        }
        str.getChars(0, length, this.m_charsBuff, 0);
        comment(this.m_charsBuff, 0, length);
    }

    public boolean documentIsEmpty() {
        return this.m_docIsEmpty && this.m_elemContext.m_currentElemDepth == 0;
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String str) throws SAXException {
        if (str.equals("[dtd]")) {
            this.m_inExternalDTD = false;
        }
        if (!this.m_inExternalDTD) {
            this.m_inEntityRef--;
        }
        if (this.m_tracer != null) {
            fireEndEntity(str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void entityReference(String str) throws SAXException {
        flushPending();
        startEntity(str);
        endEntity(str);
        if (this.m_tracer != null) {
            fireEntityReference(str);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        this.m_elemContext.m_startTagOpen = false;
    }

    public void fireCDATAEvent(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(10, cArr, i, i2);
        }
    }

    public void fireCharEvent(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(5, cArr, i, i2);
        }
    }

    public void fireCommentEvent(char[] cArr, int i, int i2) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(8, new String(cArr, i, i2));
        }
    }

    public void fireEndDoc() throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(2);
        }
    }

    public void fireEndElem(String str) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(4, str, (Attributes) null);
        }
    }

    public void fireEndEntity(String str) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
        }
    }

    public void fireEntityReference(String str) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(9, str, (Attributes) null);
        }
    }

    public void fireEscapingEvent(String str, String str2) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(7, str, str2);
        }
    }

    public void fireStartDoc() throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(1);
        }
    }

    public void fireStartElem(String str) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(3, str, this.m_attributes);
        }
    }

    public void fireStartEntity(String str) throws SAXException {
        if (this.m_tracer != null) {
            flushMyWriter();
            this.m_tracer.fireGenerateEvent(9, str);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getDoctypePublic() {
        return this.m_doctypePublic;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getDoctypeSystem() {
        return this.m_doctypeSystem;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getEncoding() {
        return getOutputProperty("encoding");
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getIndent() {
        return this.m_doIndent;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public int getIndentAmount() {
        return this.m_indentAmount;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getMediaType() {
        return this.m_mediatype;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public NamespaceMappings getNamespaceMappings() {
        return this.m_prefixMap;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getNamespaceURI(String str, boolean z) {
        NamespaceMappings namespaceMappings;
        int iLastIndexOf = str.lastIndexOf(58);
        String strSubstring = iLastIndexOf > 0 ? str.substring(0, iLastIndexOf) : "";
        if (("".equals(strSubstring) && !z) || (namespaceMappings = this.m_prefixMap) == null) {
            return "";
        }
        String strLookupNamespace = namespaceMappings.lookupNamespace(strSubstring);
        if (strLookupNamespace != null || strSubstring.equals("xmlns")) {
            return strLookupNamespace;
        }
        f63.a(Utils.messages.createMessage("ER_NAMESPACE_PREFIX", new Object[]{str.substring(0, iLastIndexOf)}));
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getNamespaceURIFromPrefix(String str) {
        NamespaceMappings namespaceMappings = this.m_prefixMap;
        if (namespaceMappings != null) {
            return namespaceMappings.lookupNamespace(str);
        }
        return null;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public boolean getOmitXMLDeclaration() {
        return this.m_shouldNotWriteXMLHeader;
    }

    public Set<String> getOutputPropDefaultKeys() {
        return this.m_OutputPropsDefault.keySet();
    }

    public Set<String> getOutputPropKeys() {
        return this.m_OutputProps.keySet();
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getOutputProperty(String str) {
        String outputPropertyNonDefault = getOutputPropertyNonDefault(str);
        return outputPropertyNonDefault == null ? getOutputPropertyDefault(str) : outputPropertyNonDefault;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getOutputPropertyDefault(String str) {
        return getProp(str, true);
    }

    public String getOutputPropertyNonDefault(String str) {
        return getProp(str, false);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public String getPrefix(String str) {
        return this.m_prefixMap.lookupPrefix(str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getStandalone() {
        return this.m_standalone;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public Transformer getTransformer() {
        return this.m_transformer;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public String getVersion() {
        return this.m_version;
    }

    public final boolean inTemporaryOutputState() {
        return getEncoding() == null;
    }

    public void initCDATA() {
    }

    public void initCdataElems(String str) {
        if (str != null) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            String string = null;
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if (Character.isWhitespace(cCharAt)) {
                    if (z2) {
                        sb.append(cCharAt);
                    } else if (sb.length() > 0) {
                        String string2 = sb.toString();
                        if (!z) {
                            string = "";
                        }
                        addCDATAElement(string, string2);
                        sb.setLength(0);
                        z = false;
                    }
                } else if ('{' == cCharAt) {
                    z2 = true;
                } else if ('}' == cCharAt) {
                    string = sb.toString();
                    sb.setLength(0);
                    z2 = false;
                    z = true;
                } else {
                    sb.append(cCharAt);
                }
            }
            if (sb.length() > 0) {
                String string3 = sb.toString();
                if (!z) {
                    string = "";
                }
                addCDATAElement(string, string3);
            }
        }
    }

    public boolean isCdataSection() {
        if (this.m_StringOfCDATASections == null) {
            return false;
        }
        ElemContext elemContext = this.m_elemContext;
        if (elemContext.m_elementLocalName == null) {
            this.m_elemContext.m_elementLocalName = getLocalName(elemContext.m_elementName);
        }
        ElemContext elemContext2 = this.m_elemContext;
        String str = elemContext2.m_elementURI;
        if (str == null) {
            elemContext2.m_elementURI = getElementURI();
        } else if (str.length() == 0) {
            ElemContext elemContext3 = this.m_elemContext;
            if (elemContext3.m_elementName == null) {
                elemContext3.m_elementName = elemContext3.m_elementLocalName;
            } else if (elemContext3.m_elementLocalName.length() < this.m_elemContext.m_elementName.length()) {
                this.m_elemContext.m_elementURI = getElementURI();
            }
        }
        HashMap<String, HashMap<String, String>> map = this.m_CdataElems;
        HashMap<String, String> map2 = map != null ? map.get(this.m_elemContext.m_elementLocalName) : null;
        return (map2 == null || map2.get(this.m_elemContext.m_elementURI) == null) ? false : true;
    }

    public boolean isInEntityRef() {
        return this.m_inEntityRef > 0;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void namespaceAfterStartElement(String str, String str2) throws SAXException {
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String str, String str2, String str3) throws SAXException {
    }

    public String patchName(String str) {
        int iLastIndexOf = str.lastIndexOf(58);
        if (iLastIndexOf > 0) {
            int iIndexOf = str.indexOf(58);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iLastIndexOf + 1);
            String strLookupNamespace = this.m_prefixMap.lookupNamespace(strSubstring);
            if (strLookupNamespace != null && strLookupNamespace.length() == 0) {
                return strSubstring2;
            }
            if (iIndexOf != iLastIndexOf) {
                return strSubstring + ':' + strSubstring2;
            }
        }
        return str;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.Serializer
    public boolean reset() {
        resetSerializerBase();
        return true;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setDTDEntityExpansion(boolean z) {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctype(String str, String str2) {
        setOutputProperty(Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM, str);
        setOutputProperty(Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC, str2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctypePublic(String str) {
        setOutputProperty(Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC, str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setDoctypeSystem(String str) {
        setOutputProperty(Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM, str);
    }

    public void setDocumentInfo() {
        Locator locator = this.m_locator;
        if (locator == null) {
            return;
        }
        try {
            String xMLVersion = ((Locator2) locator).getXMLVersion();
            if (xMLVersion != null) {
                setVersion(xMLVersion);
            }
        } catch (ClassCastException unused) {
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.m_locator = locator;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setEncoding(String str) {
        setOutputProperty("encoding", str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setIndent(boolean z) {
        setOutputProperty("indent", z ? JdkConstants.JDK_YES : "no");
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIndentAmount(int i) {
        this.m_indentAmount = i;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setIsStandalone(boolean z) {
        this.m_isStandalone = z;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setMediaType(String str) {
        setOutputProperty(Constants.ATTRNAME_OUTPUT_MEDIATYPE, str);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setNamespaceMappings(NamespaceMappings namespaceMappings) {
        this.m_prefixMap = namespaceMappings;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOmitXMLDeclaration(boolean z) {
        setOutputProperty("omit-xml-declaration", z ? JdkConstants.JDK_YES : "no");
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOutputProperty(String str, String str2) {
        setProp(str, str2, false);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setOutputPropertyDefault(String str, String str2) {
        setProp(str, str2, true);
    }

    public void setProp(String str, String str2, boolean z) {
        if (this.m_OutputProps == null) {
            this.m_OutputProps = new HashMap<>();
            this.m_OutputPropsDefault = new HashMap<>();
        }
        if (z) {
            this.m_OutputPropsDefault.put(str, str2);
            return;
        }
        if (!Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS.equals(str) || str2 == null) {
            this.m_OutputProps.put(str, str2);
            return;
        }
        initCdataElems(str2);
        String str3 = this.m_OutputProps.get(str);
        if (str3 == null) {
            str2 = str3 + ' ' + str2;
        }
        this.m_OutputProps.put(str, str2);
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void setSourceLocator(SourceLocator sourceLocator) {
        this.m_sourceLocator = sourceLocator;
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setStandalone(String str) {
        setOutputProperty(Constants.ATTRNAME_OUTPUT_STANDALONE, str);
    }

    public void setStandaloneInternal(String str) {
        if (JdkConstants.JDK_YES.equals(str)) {
            this.m_standalone = JdkConstants.JDK_YES;
        } else {
            this.m_standalone = "no";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler
    public void setTransformer(Transformer transformer) {
        this.m_transformer = transformer;
        if ((transformer instanceof SerializerTrace) && ((SerializerTrace) transformer).hasTraceListeners()) {
            this.m_tracer = (SerializerTrace) this.m_transformer;
        } else {
            this.m_tracer = null;
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.XSLOutputAttributes
    public void setVersion(String str) {
        setOutputProperty("version", str);
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        startDocumentInternal();
        this.m_needToCallStartDocument = false;
    }

    public void startDocumentInternal() throws SAXException {
        if (this.m_tracer != null) {
            fireStartDoc();
        }
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String str, String str2, String str3, String str4) throws SAXException {
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
    }

    @Override // com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) throws SAXException {
        if (this.m_elemContext.m_startTagOpen) {
            addAttributeAlways(str, str2, str3, str4, str5, z);
        }
    }

    @Override // com.sun.org.apache.xml.internal.serializer.SerializationHandler, com.sun.org.apache.xml.internal.serializer.ExtendedContentHandler
    public void addAttribute(String str, String str2, String str3, String str4, String str5) throws SAXException {
        if (this.m_elemContext.m_startTagOpen) {
            addAttributeAlways(str, str2, str3, str4, str5, false);
        }
    }
}
