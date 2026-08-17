package com.sun.org.apache.xml.internal.serializer.dom3;

import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.ls.LSSerializerFilter;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.LocatorImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class DOM3TreeWalker {
    private static final int CANONICAL = 1;
    private static final int CDATA = 2;
    private static final int CHARNORMALIZE = 4;
    private static final int COMMENTS = 8;
    private static final int DISCARDDEFAULT = 32768;
    private static final int DTNORMALIZE = 16;
    private static final int ELEM_CONTENT_WHITESPACE = 32;
    private static final int ENTITIES = 64;
    private static final int IGNORE_CHAR_DENORMALIZE = 131072;
    private static final int INFOSET = 128;
    private static final int NAMESPACEDECLS = 512;
    private static final int NAMESPACES = 256;
    private static final int NORMALIZECHARS = 1024;
    private static final int PRETTY_PRINT = 65536;
    private static final int SCHEMAVALIDATE = 8192;
    private static final int SPLITCDATA = 2048;
    private static final int VALIDATE = 4096;
    private static final int WELLFORMED = 16384;
    private static final int XMLDECL = 262144;
    private static final String XMLNS_PREFIX = "xmlns";
    private static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    private static final String XML_PREFIX = "xml";
    private static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    private static final Map<String, Integer> fFeatureMap;
    private Properties fDOMConfigProperties;
    private DOMErrorHandler fErrorHandler;
    private LSSerializerFilter fFilter;
    private String fNewLine;
    private SerializationHandler fSerializer;
    private int fWhatToShowFilter;
    private LocatorImpl fLocator = new LocatorImpl();
    private boolean fInEntityRef = false;
    private String fXMLVersion = null;
    private boolean fIsXMLVersion11 = false;
    private boolean fIsLevel3DOM = false;
    private int fFeatures = 0;
    boolean fNextIsRaw = false;
    private int fElementDepth = 0;
    private LexicalHandler fLexicalHandler = null;
    protected NamespaceSupport fNSBinder = new NamespaceSupport();
    protected NamespaceSupport fLocalNSBinder = new NamespaceSupport();

    static {
        HashMap map = new HashMap();
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", 2);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}comments", 8);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", 32);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}entities", 64);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", 256);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", 512);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", 2048);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", 16384);
        map.put("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", 32768);
        fFeatureMap = Collections.unmodifiableMap(map);
    }

    public DOM3TreeWalker(SerializationHandler serializationHandler, DOMErrorHandler dOMErrorHandler, LSSerializerFilter lSSerializerFilter, String str) {
        this.fSerializer = null;
        this.fDOMConfigProperties = null;
        this.fSerializer = serializationHandler;
        this.fErrorHandler = dOMErrorHandler;
        this.fFilter = lSSerializerFilter;
        this.fNewLine = str;
        this.fDOMConfigProperties = this.fSerializer.getOutputFormat();
        this.fSerializer.setDocumentLocator(this.fLocator);
        initProperties(this.fDOMConfigProperties);
    }

    private final void dispatachChars(Node node) throws SAXException {
        if (this.fSerializer != null) {
            String data = ((Text) node).getData();
            this.fSerializer.characters(data.toCharArray(), 0, data.length());
        }
    }

    public boolean applyFilter(Node node, int i) {
        LSSerializerFilter lSSerializerFilter = this.fFilter;
        if (lSSerializerFilter == null || (this.fWhatToShowFilter & i) == 0) {
            return true;
        }
        short sAcceptNode = lSSerializerFilter.acceptNode(node);
        return (sAcceptNode == 2 || sAcceptNode == 3) ? false : true;
    }

    public void checkUnboundPrefixInEntRef(Node node) {
        Node firstChild = node.getFirstChild();
        while (firstChild != null) {
            Node nextSibling = firstChild.getNextSibling();
            if (firstChild.getNodeType() == 1) {
                String prefix = firstChild.getPrefix();
                if (prefix != null && this.fNSBinder.getURI(prefix) == null) {
                    String strCreateMessage = Utils.messages.createMessage("unbound-prefix-in-entity-reference", new Object[]{node.getNodeName(), firstChild.getNodeName(), prefix});
                    DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
                    if (dOMErrorHandler != null) {
                        dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, "unbound-prefix-in-entity-reference", null, null, null));
                    }
                }
                NamedNodeMap attributes = firstChild.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    String prefix2 = attributes.item(i).getPrefix();
                    if (prefix2 != null && this.fNSBinder.getURI(prefix2) == null) {
                        String strCreateMessage2 = Utils.messages.createMessage("unbound-prefix-in-entity-reference", new Object[]{node.getNodeName(), firstChild.getNodeName(), attributes.item(i)});
                        DOMErrorHandler dOMErrorHandler2 = this.fErrorHandler;
                        if (dOMErrorHandler2 != null) {
                            dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, strCreateMessage2, "unbound-prefix-in-entity-reference", null, null, null));
                        }
                    }
                }
            }
            if (firstChild.hasChildNodes()) {
                checkUnboundPrefixInEntRef(firstChild);
            }
            firstChild = nextSibling;
        }
    }

    public void endNode(Node node) throws SAXException {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            serializeElement((Element) node, false);
        } else if (nodeType == 5) {
            serializeEntityReference((EntityReference) node, false);
        } else {
            if (nodeType != 10) {
                return;
            }
            serializeDocType((DocumentType) node, false);
        }
    }

    public void fixupElementNS(Node node) throws SAXException {
        Element element = (Element) node;
        String namespaceURI = element.getNamespaceURI();
        String prefix = element.getPrefix();
        String localName = element.getLocalName();
        if (namespaceURI != null) {
            if (prefix == null) {
                prefix = "";
            }
            String uri = this.fNSBinder.getURI(prefix);
            if (uri == null || !uri.equals(namespaceURI)) {
                if ((this.fFeatures & 512) != 0) {
                    if ("".equals(prefix) || "".equals(namespaceURI)) {
                        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", namespaceURI);
                    } else {
                        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:".concat(prefix), namespaceURI);
                    }
                }
                this.fLocalNSBinder.declarePrefix(prefix, namespaceURI);
                this.fNSBinder.declarePrefix(prefix, namespaceURI);
                return;
            }
            return;
        }
        if (localName == null || "".equals(localName)) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_NULL_LOCAL_ELEMENT_NAME, new Object[]{node.getNodeName()});
            DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 2, strCreateMessage, MsgKey.ER_NULL_LOCAL_ELEMENT_NAME, null, null, null));
                return;
            }
            return;
        }
        String uri2 = this.fNSBinder.getURI("");
        if (uri2 == null || uri2.length() <= 0) {
            return;
        }
        element.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns", "");
        this.fLocalNSBinder.declarePrefix("", "");
        this.fNSBinder.declarePrefix("", "");
    }

    public void initProperties(Properties properties) {
        for (String str : properties.stringPropertyNames()) {
            Integer num = fFeatureMap.get(str);
            if (num != null) {
                boolean zEndsWith = properties.getProperty(str).endsWith(JdkConstants.JDK_YES);
                int i = this.fFeatures;
                if (zEndsWith) {
                    this.fFeatures = num.intValue() | i;
                } else {
                    this.fFeatures = (~num.intValue()) & i;
                }
            } else if ("{http://www.w3.org/TR/DOM-Level-3-LS}format-pretty-print".equals(str)) {
                boolean zEndsWith2 = properties.getProperty(str).endsWith(JdkConstants.JDK_YES);
                SerializationHandler serializationHandler = this.fSerializer;
                if (zEndsWith2) {
                    serializationHandler.setIndent(true);
                    this.fSerializer.setIndentAmount(4);
                } else {
                    serializationHandler.setIndent(false);
                }
            } else if ("omit-xml-declaration".equals(str)) {
                boolean zEndsWith3 = properties.getProperty(str).endsWith(JdkConstants.JDK_YES);
                SerializationHandler serializationHandler2 = this.fSerializer;
                if (zEndsWith3) {
                    serializationHandler2.setOmitXMLDeclaration(true);
                } else {
                    serializationHandler2.setOmitXMLDeclaration(false);
                }
            } else if ("{http://xml.apache.org/xerces-2j}xml-version".equals(str)) {
                String property = properties.getProperty(str);
                if (SerializerConstants.XMLVERSION11.equals(property)) {
                    this.fIsXMLVersion11 = true;
                    this.fSerializer.setVersion(property);
                } else {
                    this.fSerializer.setVersion("1.0");
                }
            } else if ("encoding".equals(str)) {
                String property2 = properties.getProperty(str);
                if (property2 != null) {
                    this.fSerializer.setEncoding(property2);
                }
            } else if (OutputPropertiesFactory.S_KEY_ENTITIES.equals(str) && DOMConstants.S_XSL_VALUE_ENTITIES.equals(properties.getProperty(str))) {
                this.fSerializer.setDTDEntityExpansion(false);
            }
        }
        String str2 = this.fNewLine;
        if (str2 != null) {
            this.fSerializer.setOutputProperty(OutputPropertiesFactory.S_KEY_LINE_SEPARATOR, str2);
        }
    }

    public void isAttributeWellFormed(Node node) {
        if (!((this.fFeatures & 256) != 0 ? isValidQName(node.getPrefix(), node.getLocalName(), this.fIsXMLVersion11) : isXMLName(node.getNodeName(), this.fIsXMLVersion11))) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Attr", node.getNodeName()});
            DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, null, null, null));
            }
        }
        if (node.getNodeValue().indexOf(60) >= 0) {
            String strCreateMessage2 = Utils.messages.createMessage(MsgKey.ER_WF_LT_IN_ATTVAL, new Object[]{((Attr) node).getOwnerElement().getNodeName(), node.getNodeName()});
            DOMErrorHandler dOMErrorHandler2 = this.fErrorHandler;
            if (dOMErrorHandler2 != null) {
                dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, strCreateMessage2, MsgKey.ER_WF_LT_IN_ATTVAL, null, null, null));
            }
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem != null) {
                short nodeType = nodeItem.getNodeType();
                if (nodeType == 3) {
                    isTextWellFormed((Text) nodeItem);
                } else if (nodeType == 5) {
                    isEntityReferneceWellFormed((EntityReference) nodeItem);
                }
            }
        }
    }

    public void isCDATASectionWellFormed(CDATASection cDATASection) {
        Character chIsWFXMLChar = isWFXMLChar(cDATASection.getData());
        if (chIsWFXMLChar != null) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_CDATA, new Object[]{Integer.toHexString(Character.getNumericValue(chIsWFXMLChar.charValue()))});
            DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
            }
        }
    }

    public void isCommentWellFormed(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        if (this.fIsXMLVersion11) {
            while (i < length) {
                int i2 = i + 1;
                char c = charArray[i];
                if (XML11Char.isXML11Invalid(c)) {
                    if (XMLChar.isHighSurrogate(c) && i2 < length) {
                        i += 2;
                        char c2 = charArray[i2];
                        if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            i2 = i;
                        }
                    }
                    String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_COMMENT, new Object[]{Character.valueOf(c)});
                    DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
                    if (dOMErrorHandler != null) {
                        dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
                    }
                } else if (c == '-' && i2 < length && charArray[i2] == '-') {
                    String strCreateMessage2 = Utils.messages.createMessage(MsgKey.ER_WF_DASH_IN_COMMENT, null);
                    DOMErrorHandler dOMErrorHandler2 = this.fErrorHandler;
                    if (dOMErrorHandler2 != null) {
                        dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, strCreateMessage2, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
                    }
                }
                i = i2;
            }
            return;
        }
        while (i < length) {
            int i3 = i + 1;
            char c3 = charArray[i];
            if (XMLChar.isInvalid(c3)) {
                if (XMLChar.isHighSurrogate(c3) && i3 < length) {
                    i += 2;
                    char c4 = charArray[i3];
                    if (!XMLChar.isLowSurrogate(c4) || !XMLChar.isSupplemental(XMLChar.supplemental(c3, c4))) {
                        i3 = i;
                    }
                }
                String strCreateMessage3 = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_COMMENT, new Object[]{Character.valueOf(c3)});
                DOMErrorHandler dOMErrorHandler3 = this.fErrorHandler;
                if (dOMErrorHandler3 != null) {
                    dOMErrorHandler3.handleError(new DOMErrorImpl((short) 3, strCreateMessage3, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
                }
            } else if (c3 == '-' && i3 < length && charArray[i3] == '-') {
                String strCreateMessage4 = Utils.messages.createMessage(MsgKey.ER_WF_DASH_IN_COMMENT, null);
                DOMErrorHandler dOMErrorHandler4 = this.fErrorHandler;
                if (dOMErrorHandler4 != null) {
                    dOMErrorHandler4.handleError(new DOMErrorImpl((short) 3, strCreateMessage4, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
                }
            }
            i = i3;
        }
    }

    public void isElementWellFormed(Node node) {
        if ((this.fFeatures & 256) != 0 ? isValidQName(node.getPrefix(), node.getLocalName(), this.fIsXMLVersion11) : isXMLName(node.getNodeName(), this.fIsXMLVersion11)) {
            return;
        }
        String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Element", node.getNodeName()});
        DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
        if (dOMErrorHandler != null) {
            dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, null, null, null));
        }
    }

    public void isEntityReferneceWellFormed(EntityReference entityReference) {
        if (!isXMLName(entityReference.getNodeName(), this.fIsXMLVersion11)) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"EntityReference", entityReference.getNodeName()});
            DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, null, null, null));
            }
        }
        Node parentNode = entityReference.getParentNode();
        DocumentType doctype = entityReference.getOwnerDocument().getDoctype();
        if (doctype != null) {
            NamedNodeMap entities = doctype.getEntities();
            for (int i = 0; i < entities.getLength(); i++) {
                Entity entity = (Entity) entities.item(i);
                String nodeName = entityReference.getNodeName() == null ? "" : entityReference.getNodeName();
                String namespaceURI = entityReference.getNamespaceURI() == null ? "" : entityReference.getNamespaceURI();
                String nodeName2 = entity.getNodeName() == null ? "" : entity.getNodeName();
                String namespaceURI2 = entity.getNamespaceURI() != null ? entity.getNamespaceURI() : "";
                if (parentNode.getNodeType() == 1 && namespaceURI2.equals(namespaceURI) && nodeName2.equals(nodeName) && entity.getNotationName() != null) {
                    String strCreateMessage2 = Utils.messages.createMessage(MsgKey.ER_WF_REF_TO_UNPARSED_ENT, new Object[]{entityReference.getNodeName()});
                    DOMErrorHandler dOMErrorHandler2 = this.fErrorHandler;
                    if (dOMErrorHandler2 != null) {
                        dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, strCreateMessage2, MsgKey.ER_WF_REF_TO_UNPARSED_ENT, null, null, null));
                    }
                }
                if (parentNode.getNodeType() == 2 && namespaceURI2.equals(namespaceURI) && nodeName2.equals(nodeName) && (entity.getPublicId() != null || entity.getSystemId() != null || entity.getNotationName() != null)) {
                    String strCreateMessage3 = Utils.messages.createMessage(MsgKey.ER_WF_REF_TO_EXTERNAL_ENT, new Object[]{entityReference.getNodeName()});
                    DOMErrorHandler dOMErrorHandler3 = this.fErrorHandler;
                    if (dOMErrorHandler3 != null) {
                        dOMErrorHandler3.handleError(new DOMErrorImpl((short) 3, strCreateMessage3, MsgKey.ER_WF_REF_TO_EXTERNAL_ENT, null, null, null));
                    }
                }
            }
        }
    }

    public void isPIWellFormed(ProcessingInstruction processingInstruction) {
        if (!isXMLName(processingInstruction.getNodeName(), this.fIsXMLVersion11)) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"ProcessingInstruction", processingInstruction.getTarget()});
            DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, null, null, null));
            }
        }
        Character chIsWFXMLChar = isWFXMLChar(processingInstruction.getData());
        if (chIsWFXMLChar != null) {
            String strCreateMessage2 = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_PI, new Object[]{Integer.toHexString(Character.getNumericValue(chIsWFXMLChar.charValue()))});
            DOMErrorHandler dOMErrorHandler2 = this.fErrorHandler;
            if (dOMErrorHandler2 != null) {
                dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, strCreateMessage2, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
            }
        }
    }

    public void isTextWellFormed(Text text) {
        Character chIsWFXMLChar = isWFXMLChar(text.getData());
        if (chIsWFXMLChar != null) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_WF_INVALID_CHARACTER_IN_TEXT, new Object[]{Integer.toHexString(Character.getNumericValue(chIsWFXMLChar.charValue()))});
            DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_WF_INVALID_CHARACTER, null, null, null));
            }
        }
    }

    public boolean isValidQName(String str, String str2, boolean z) {
        if (str2 == null) {
            return false;
        }
        if (z) {
            return (str == null || XML11Char.isXML11ValidNCName(str)) && XML11Char.isXML11ValidNCName(str2);
        }
        return (str == null || XMLChar.isValidNCName(str)) && XMLChar.isValidNCName(str2);
    }

    public Character isWFXMLChar(String str) {
        if (str != null && str.length() != 0) {
            char[] charArray = str.toCharArray();
            int length = charArray.length;
            int i = 0;
            if (this.fIsXMLVersion11) {
                while (i < length) {
                    int i2 = i + 1;
                    if (XML11Char.isXML11Invalid(charArray[i])) {
                        char c = charArray[i];
                        if (XMLChar.isHighSurrogate(c) && i2 < length) {
                            i += 2;
                            char c2 = charArray[i2];
                            if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            }
                        }
                        return Character.valueOf(c);
                    }
                    i = i2;
                }
            } else {
                while (i < length) {
                    int i3 = i + 1;
                    if (XMLChar.isInvalid(charArray[i])) {
                        char c3 = charArray[i];
                        if (XMLChar.isHighSurrogate(c3) && i3 < length) {
                            i += 2;
                            char c4 = charArray[i3];
                            if (!XMLChar.isLowSurrogate(c4) || !XMLChar.isSupplemental(XMLChar.supplemental(c3, c4))) {
                            }
                        }
                        return Character.valueOf(c3);
                    }
                    i = i3;
                }
            }
        }
        return null;
    }

    public boolean isXMLName(String str, boolean z) {
        if (str == null) {
            return false;
        }
        return !z ? XMLChar.isValidName(str) : XML11Char.isXML11ValidName(str);
    }

    public void recordLocalNSDecl(Node node) {
        NamedNodeMap attributes = ((Element) node).getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = attributes.item(i);
            String localName = nodeItem.getLocalName();
            String prefix = nodeItem.getPrefix();
            String nodeValue = nodeItem.getNodeValue();
            String namespaceURI = nodeItem.getNamespaceURI();
            if (localName == null || "xmlns".equals(localName)) {
                localName = "";
            }
            if (prefix == null) {
                prefix = "";
            }
            if (nodeValue == null) {
                nodeValue = "";
            }
            if (namespaceURI == null) {
                namespaceURI = "";
            }
            if ("http://www.w3.org/2000/xmlns/".equals(namespaceURI)) {
                if ("http://www.w3.org/2000/xmlns/".equals(nodeValue)) {
                    String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_NS_PREFIX_CANNOT_BE_BOUND, new Object[]{prefix, "http://www.w3.org/2000/xmlns/"});
                    DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
                    if (dOMErrorHandler != null) {
                        dOMErrorHandler.handleError(new DOMErrorImpl((short) 2, strCreateMessage, MsgKey.ER_NS_PREFIX_CANNOT_BE_BOUND, null, null, null));
                    }
                } else if (!"xmlns".equals(prefix)) {
                    this.fNSBinder.declarePrefix("", nodeValue);
                } else if (nodeValue.length() != 0) {
                    this.fNSBinder.declarePrefix(localName, nodeValue);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00ab A[PHI: r6
      0x00ab: PHI (r6v2 java.lang.String) = 
      (r6v1 java.lang.String)
      (r6v1 java.lang.String)
      (r6v1 java.lang.String)
      (r6v15 java.lang.String)
      (r6v17 java.lang.String)
      (r6v1 java.lang.String)
     binds: [B:31:0x0083, B:32:0x0085, B:55:0x00dd, B:50:0x00cf, B:49:0x00bc, B:41:0x00a8] A[DONT_GENERATE, DONT_INLINE]] */
    public void serializeAttList(Element element) throws SAXException {
        short s;
        boolean z;
        String str;
        LSSerializerFilter lSSerializerFilter;
        short sAcceptNode;
        NamedNodeMap attributes = element.getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Node nodeItem = attributes.item(i);
            String localName = nodeItem.getLocalName();
            String nodeName = nodeItem.getNodeName();
            String prefix = nodeItem.getPrefix() == null ? "" : nodeItem.getPrefix();
            String nodeValue = nodeItem.getNodeValue();
            String str2 = null;
            String typeName = this.fIsLevel3DOM ? ((Attr) nodeItem).getSchemaTypeInfo().getTypeName() : null;
            if (typeName == null) {
                typeName = "CDATA";
            }
            String namespaceURI = nodeItem.getNamespaceURI();
            if (namespaceURI == null || namespaceURI.length() != 0) {
                str2 = namespaceURI;
            } else {
                nodeName = nodeItem.getLocalName();
            }
            boolean specified = ((Attr) nodeItem).getSpecified();
            boolean z2 = true;
            boolean z3 = nodeName.equals("xmlns") || nodeName.startsWith("xmlns:");
            if ((this.fFeatures & 16384) != 0) {
                isAttributeWellFormed(nodeItem);
            }
            if ((this.fFeatures & 256) == 0 || z3) {
                s = 2;
            } else if (str2 != null) {
                if (prefix == null) {
                    prefix = "";
                }
                String prefix2 = this.fNSBinder.getPrefix(str2);
                String uri = this.fNSBinder.getURI(prefix);
                if ("".equals(prefix) || "".equals(prefix2) || !prefix.equals(prefix2)) {
                    if (prefix2 == null || "".equals(prefix2)) {
                        if ("".equals(prefix) || uri != null) {
                            s = 2;
                            String str3 = "NS1";
                            int i2 = 2;
                            while (this.fLocalNSBinder.getURI(str3) != null) {
                                str3 = "NS" + i2;
                                i2++;
                            }
                            nodeName = str3 + ":" + localName;
                            if ((this.fFeatures & 512) != 0) {
                                this.fSerializer.addAttribute("http://www.w3.org/2000/xmlns/", str3, "xmlns:".concat(str3), "CDATA", str2);
                                this.fNSBinder.declarePrefix(str3, str2);
                                this.fLocalNSBinder.declarePrefix(str3, str2);
                            }
                        } else if ((this.fFeatures & 512) != 0) {
                            SerializationHandler serializationHandler = this.fSerializer;
                            String strConcat = "xmlns:".concat(prefix);
                            String str4 = prefix;
                            s = 2;
                            serializationHandler.addAttribute("http://www.w3.org/2000/xmlns/", str4, strConcat, "CDATA", str2);
                            this.fNSBinder.declarePrefix(str4, str2);
                            this.fLocalNSBinder.declarePrefix(str4, str2);
                        }
                    } else if (prefix2.length() > 0) {
                        nodeName = prefix2 + ":" + localName;
                    } else {
                        nodeName = localName;
                    }
                    s = 2;
                } else {
                    s = 2;
                }
            } else {
                s = 2;
                if (localName == null) {
                    String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_NULL_LOCAL_ELEMENT_NAME, new Object[]{nodeName});
                    DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
                    if (dOMErrorHandler != null) {
                        dOMErrorHandler.handleError(new DOMErrorImpl((short) 2, strCreateMessage, MsgKey.ER_NULL_LOCAL_ELEMENT_NAME, null, null, null));
                    }
                }
            }
            int i3 = this.fFeatures;
            if (((i3 & 32768) == 0 || !specified) && (i3 & 32768) != 0) {
                z = false;
                z2 = false;
            } else {
                z = true;
            }
            if (z2 && (lSSerializerFilter = this.fFilter) != null && (lSSerializerFilter.getWhatToShow() & s) != 0 && !z3 && ((sAcceptNode = this.fFilter.acceptNode(nodeItem)) == s || sAcceptNode == 3)) {
                z = false;
            }
            if (!z || !z3) {
                String str5 = str2;
                String str6 = typeName;
                str = nodeName;
                if (z && !z3) {
                    if ((this.fFeatures & 512) == 0 || str5 == null) {
                        this.fSerializer.addAttribute("", localName, str, str6, nodeValue);
                    } else {
                        this.fSerializer.addAttribute(str5, localName, str, str6, nodeValue);
                    }
                }
            } else if ((this.fFeatures & 512) == 0 || localName == null || "".equals(localName)) {
                str = nodeName;
            } else {
                String str7 = str2;
                String str8 = typeName;
                str = nodeName;
                this.fSerializer.addAttribute(str7, localName, str, str8, nodeValue);
            }
            if (z3 && (this.fFeatures & 512) != 0) {
                int iIndexOf = str.indexOf(":");
                String strSubstring = iIndexOf < 0 ? "" : str.substring(iIndexOf + 1);
                if (!"".equals(strSubstring)) {
                    this.fSerializer.namespaceAfterStartElement(strSubstring, nodeValue);
                }
            }
        }
    }

    public void serializeCDATASection(CDATASection cDATASection) throws SAXException {
        if ((this.fFeatures & 16384) != 0) {
            isCDATASectionWellFormed(cDATASection);
        }
        if ((this.fFeatures & 2) == 0) {
            dispatachChars(cDATASection);
            return;
        }
        String nodeValue = cDATASection.getNodeValue();
        int iIndexOf = nodeValue.indexOf("]]>");
        if ((this.fFeatures & 2048) != 0) {
            if (iIndexOf >= 0) {
                String strSubstring = nodeValue.substring(0, iIndexOf + 2);
                String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_CDATA_SECTIONS_SPLIT, null);
                DOMErrorHandler dOMErrorHandler = this.fErrorHandler;
                if (dOMErrorHandler != null) {
                    dOMErrorHandler.handleError(new DOMErrorImpl((short) 1, strCreateMessage, MsgKey.ER_CDATA_SECTIONS_SPLIT, null, strSubstring, null));
                }
            }
        } else if (iIndexOf >= 0) {
            nodeValue.substring(0, iIndexOf + 2);
            String strCreateMessage2 = Utils.messages.createMessage(MsgKey.ER_CDATA_SECTIONS_SPLIT, null);
            DOMErrorHandler dOMErrorHandler2 = this.fErrorHandler;
            if (dOMErrorHandler2 != null) {
                dOMErrorHandler2.handleError(new DOMErrorImpl((short) 2, strCreateMessage2, MsgKey.ER_CDATA_SECTIONS_SPLIT));
                return;
            }
            return;
        }
        if (applyFilter(cDATASection, 8)) {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.startCDATA();
            }
            dispatachChars(cDATASection);
            LexicalHandler lexicalHandler2 = this.fLexicalHandler;
            if (lexicalHandler2 != null) {
                lexicalHandler2.endCDATA();
            }
        }
    }

    public void serializeComment(Comment comment) throws SAXException {
        if ((this.fFeatures & 8) != 0) {
            String data = comment.getData();
            if ((this.fFeatures & 16384) != 0) {
                isCommentWellFormed(data);
            }
            if (this.fLexicalHandler == null || !applyFilter(comment, 128)) {
                return;
            }
            this.fLexicalHandler.comment(data.toCharArray(), 0, data.length());
        }
    }

    public void serializeDocType(DocumentType documentType, boolean z) throws SAXException {
        String nodeName = documentType.getNodeName();
        String publicId = documentType.getPublicId();
        String systemId = documentType.getSystemId();
        String internalSubset = documentType.getInternalSubset();
        if (internalSubset == null || "".equals(internalSubset)) {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (z) {
                if (lexicalHandler != null) {
                    lexicalHandler.startDTD(nodeName, publicId, systemId);
                    return;
                }
                return;
            } else {
                if (lexicalHandler != null) {
                    lexicalHandler.endDTD();
                    return;
                }
                return;
            }
        }
        if (z) {
            try {
                Writer writer = this.fSerializer.getWriter();
                writer.write("<!DOCTYPE " + nodeName + JdkXmlUtils.getDTDExternalDecl(publicId, systemId) + " [ " + this.fNewLine + internalSubset + "]>" + this.fNewLine);
                writer.flush();
            } catch (IOException e) {
                throw new SAXException(Utils.messages.createMessage(MsgKey.ER_WRITING_INTERNAL_SUBSET, null), e);
            }
        }
    }

    public void serializeElement(Element element, boolean z) throws SAXException {
        int i = this.fElementDepth;
        if (!z) {
            this.fElementDepth = i - 1;
            if (applyFilter(element, 1)) {
                this.fSerializer.endElement(element.getNamespaceURI(), element.getLocalName(), element.getNodeName());
                if ((this.fFeatures & 256) != 0) {
                    this.fNSBinder.popContext();
                    return;
                }
                return;
            }
            return;
        }
        this.fElementDepth = i + 1;
        if ((this.fFeatures & 16384) != 0) {
            isElementWellFormed(element);
        }
        if (applyFilter(element, 1)) {
            if ((this.fFeatures & 256) != 0) {
                this.fNSBinder.pushContext();
                this.fLocalNSBinder.reset();
                recordLocalNSDecl(element);
                fixupElementNS(element);
            }
            this.fSerializer.startElement(element.getNamespaceURI(), element.getLocalName(), element.getNodeName());
            serializeAttList(element);
        }
    }

    public void serializeEntityReference(EntityReference entityReference, boolean z) throws SAXException {
        if (!z) {
            LexicalHandler lexicalHandler = this.fLexicalHandler;
            if (lexicalHandler != null) {
                lexicalHandler.endEntity(entityReference.getNodeName());
                return;
            }
            return;
        }
        int i = this.fFeatures;
        if ((i & 64) != 0) {
            if ((i & 16384) != 0) {
                isEntityReferneceWellFormed(entityReference);
            }
            if ((this.fFeatures & 256) != 0) {
                checkUnboundPrefixInEntRef(entityReference);
            }
        }
        if (this.fLexicalHandler != null) {
            if ((this.fFeatures & 64) == 0 && entityReference.hasChildNodes()) {
                return;
            }
            this.fLexicalHandler.startEntity(entityReference.getNodeName());
        }
    }

    public void serializePI(ProcessingInstruction processingInstruction) throws SAXException {
        String nodeName = processingInstruction.getNodeName();
        if ((this.fFeatures & 16384) != 0) {
            isPIWellFormed(processingInstruction);
        }
        if (applyFilter(processingInstruction, 64)) {
            if (nodeName.equals("xslt-next-is-raw")) {
                this.fNextIsRaw = true;
            } else {
                this.fSerializer.processingInstruction(nodeName, processingInstruction.getData());
            }
        }
    }

    public void serializeText(Text text) throws SAXException {
        if (this.fNextIsRaw) {
            this.fNextIsRaw = false;
            this.fSerializer.processingInstruction("javax.xml.transform.disable-output-escaping", "");
            dispatachChars(text);
            this.fSerializer.processingInstruction("javax.xml.transform.enable-output-escaping", "");
            return;
        }
        if ((this.fFeatures & 16384) != 0) {
            isTextWellFormed(text);
        }
        boolean z = ((this.fIsLevel3DOM ? text.isElementContentWhitespace() : false) && (this.fFeatures & 32) == 0) ? false : true;
        if (applyFilter(text, 4) && z) {
            if (this.fSerializer.getIndent() && text.getData().replace('\n', ' ').trim().isEmpty()) {
                return;
            }
            dispatachChars(text);
        }
    }

    public void startNode(Node node) throws SAXException {
        if (node instanceof Locator) {
            Locator locator = (Locator) node;
            this.fLocator.setColumnNumber(locator.getColumnNumber());
            this.fLocator.setLineNumber(locator.getLineNumber());
            this.fLocator.setPublicId(locator.getPublicId());
            this.fLocator.setSystemId(locator.getSystemId());
        } else {
            this.fLocator.setColumnNumber(0);
            this.fLocator.setLineNumber(0);
        }
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            serializeElement((Element) node, true);
            return;
        }
        if (nodeType == 10) {
            serializeDocType((DocumentType) node, true);
            return;
        }
        if (nodeType == 3) {
            serializeText((Text) node);
            return;
        }
        if (nodeType == 4) {
            serializeCDATASection((CDATASection) node);
            return;
        }
        if (nodeType == 5) {
            serializeEntityReference((EntityReference) node, true);
        } else if (nodeType == 7) {
            serializePI((ProcessingInstruction) node);
        } else {
            if (nodeType != 8) {
                return;
            }
            serializeComment((Comment) node);
        }
    }

    public void traverse(Node node) throws SAXException {
        this.fSerializer.startDocument();
        if (node.getNodeType() != 9) {
            Document ownerDocument = node.getOwnerDocument();
            if (ownerDocument != null && ownerDocument.getImplementation().hasFeature("Core", "3.0")) {
                this.fIsLevel3DOM = true;
            }
        } else if (((Document) node).getImplementation().hasFeature("Core", "3.0")) {
            this.fIsLevel3DOM = true;
        }
        if (this.fSerializer != null) {
            this.fLexicalHandler = this.fSerializer;
        }
        LSSerializerFilter lSSerializerFilter = this.fFilter;
        if (lSSerializerFilter != null) {
            this.fWhatToShowFilter = lSSerializerFilter.getWhatToShow();
        }
        Node parentNode = node;
        while (parentNode != null) {
            startNode(parentNode);
            Node firstChild = parentNode.getFirstChild();
            while (true) {
                if (firstChild == null) {
                    endNode(parentNode);
                    if (!node.equals(parentNode)) {
                        firstChild = parentNode.getNextSibling();
                        if (firstChild == null && ((parentNode = parentNode.getParentNode()) == null || node.equals(parentNode))) {
                            if (parentNode != null) {
                                endNode(parentNode);
                            }
                            parentNode = null;
                        }
                    }
                }
                parentNode = firstChild;
            }
        }
        this.fSerializer.endDocument();
    }

    public boolean isWFXMLChar(String str, Character ch) {
        if (str != null && str.length() != 0) {
            char[] charArray = str.toCharArray();
            int length = charArray.length;
            if (this.fIsXMLVersion11) {
                int i = 0;
                while (i < length) {
                    int i2 = i + 1;
                    if (XML11Char.isXML11Invalid(charArray[i])) {
                        char c = charArray[i];
                        if (XMLChar.isHighSurrogate(c) && i2 < length) {
                            i += 2;
                            char c2 = charArray[i2];
                            if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            }
                        }
                        return false;
                    }
                    i = i2;
                }
            } else {
                int i3 = 0;
                while (i3 < length) {
                    int i4 = i3 + 1;
                    if (XMLChar.isInvalid(charArray[i3])) {
                        char c3 = charArray[i3];
                        if (XMLChar.isHighSurrogate(c3) && i4 < length) {
                            i3 += 2;
                            char c4 = charArray[i4];
                            if (!XMLChar.isLowSurrogate(c4) || !XMLChar.isSupplemental(XMLChar.supplemental(c3, c4))) {
                            }
                        }
                        return false;
                    }
                    i3 = i4;
                }
            }
        }
        return true;
    }

    public void traverse(Node node, Node node2) throws SAXException {
        this.fSerializer.startDocument();
        if (node.getNodeType() != 9) {
            Document ownerDocument = node.getOwnerDocument();
            if (ownerDocument != null && ownerDocument.getImplementation().hasFeature("Core", "3.0")) {
                this.fIsLevel3DOM = true;
            }
        } else if (((Document) node).getImplementation().hasFeature("Core", "3.0")) {
            this.fIsLevel3DOM = true;
        }
        if (this.fSerializer != null) {
            this.fLexicalHandler = this.fSerializer;
        }
        LSSerializerFilter lSSerializerFilter = this.fFilter;
        if (lSSerializerFilter != null) {
            this.fWhatToShowFilter = lSSerializerFilter.getWhatToShow();
        }
        while (node != null) {
            startNode(node);
            Node firstChild = node.getFirstChild();
            while (true) {
                if (firstChild == null) {
                    endNode(node);
                    if (node2 == null || !node2.equals(node)) {
                        firstChild = node.getNextSibling();
                        if (firstChild == null && ((node = node.getParentNode()) == null || (node2 != null && node2.equals(node)))) {
                            node = null;
                        }
                    }
                }
                node = firstChild;
            }
        }
        this.fSerializer.endDocument();
    }
}
