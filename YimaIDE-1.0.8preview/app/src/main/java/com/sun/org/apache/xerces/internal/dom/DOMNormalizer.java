package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDLoader;
import com.sun.org.apache.xerces.internal.impl.dtd.XMLDTDValidator;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.util.SimpleLocator;
import com.sun.org.apache.xerces.internal.jaxp.JAXPConstants;
import com.sun.org.apache.xerces.internal.util.AugmentationsImpl;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import jdk.xml.internal.JdkConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DOMNormalizer implements XMLDocumentHandler {
    protected static final boolean DEBUG = false;
    protected static final boolean DEBUG_EVENTS = false;
    protected static final boolean DEBUG_ND = false;
    public static final XMLString EMPTY_STRING = new XMLString();
    protected static final String PREFIX = "NS";
    private XMLDTDValidator fDTDValidator;
    protected DOMErrorHandler fErrorHandler;
    protected SymbolTable fSymbolTable;
    protected RevalidationHandler fValidationHandler;
    protected DOMConfigurationImpl fConfiguration = null;
    protected CoreDocumentImpl fDocument = null;
    protected final XMLAttributesProxy fAttrProxy = new XMLAttributesProxy();
    protected final QName fQName = new QName();
    private final DOMErrorImpl fError = new DOMErrorImpl();
    protected boolean fNamespaceValidation = false;
    protected boolean fPSVI = false;
    protected final NamespaceContext fNamespaceContext = new NamespaceSupport();
    protected final NamespaceContext fLocalNSBinder = new NamespaceSupport();
    protected final DOMLocatorImpl fLocator = new DOMLocatorImpl();
    protected Node fCurrentNode = null;
    private final QName fAttrQName = new QName();
    final XMLString fNormalizedValue = new XMLString(new char[16], 0, 0);
    private boolean fAllWhitespace = false;

    public final class XMLAttributesProxy implements XMLAttributes {
        protected AttributeMap fAttributes;
        protected CoreDocumentImpl fDocument;
        protected ElementImpl fElement;
        protected Vector<String> fDTDTypes = new Vector<>(5);
        protected Vector<Augmentations> fAugmentations = new Vector<>(5);

        public XMLAttributesProxy() {
        }

        private String getReportableType(String str) {
            return str.charAt(0) == '(' ? SchemaSymbols.ATTVAL_NMTOKEN : str;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public int addAttribute(QName qName, String str, String str2) {
            int xercesAttribute = this.fElement.getXercesAttribute(qName.uri, qName.localpart);
            if (xercesAttribute >= 0) {
                return xercesAttribute;
            }
            AttrImpl attrImpl = (AttrImpl) ((CoreDocumentImpl) this.fElement.getOwnerDocument()).createAttributeNS(qName.uri, qName.rawname, qName.localpart);
            attrImpl.setNodeValue(str2);
            int xercesAttributeNode = this.fElement.setXercesAttributeNode(attrImpl);
            this.fDTDTypes.insertElementAt(str, xercesAttributeNode);
            this.fAugmentations.insertElementAt(new AugmentationsImpl(), xercesAttributeNode);
            attrImpl.setSpecified(false);
            return xercesAttributeNode;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public Augmentations getAugmentations(int i) {
            return this.fAugmentations.elementAt(i);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public int getIndex(String str) {
            return -1;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public int getLength() {
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap != null) {
                return attributeMap.getLength();
            }
            return 0;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getLocalName(int i) {
            String localName;
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap == null || (localName = ((Node) attributeMap.getItem(i)).getLocalName()) == null) {
                return null;
            }
            return DOMNormalizer.this.fSymbolTable.addSymbol(localName);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void getName(int i, QName qName) {
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap != null) {
                DOMNormalizer.this.updateQName((Node) attributeMap.getItem(i), qName);
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getNonNormalizedValue(int i) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getPrefix(int i) {
            String prefix;
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap == null || (prefix = ((Node) attributeMap.getItem(i)).getPrefix()) == null || prefix.length() == 0) {
                return null;
            }
            return DOMNormalizer.this.fSymbolTable.addSymbol(prefix);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getQName(int i) {
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap == null) {
                return null;
            }
            return DOMNormalizer.this.fSymbolTable.addSymbol(((Node) attributeMap.getItem(i)).getNodeName());
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public QName getQualifiedName(int i) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getType(int i) {
            String strElementAt = this.fDTDTypes.elementAt(i);
            return strElementAt != null ? getReportableType(strElementAt) : "CDATA";
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getURI(int i) {
            String namespaceURI;
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap == null || (namespaceURI = ((Node) attributeMap.getItem(i)).getNamespaceURI()) == null) {
                return null;
            }
            return DOMNormalizer.this.fSymbolTable.addSymbol(namespaceURI);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getValue(String str, String str2) {
            Node namedItemNS;
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap == null || (namedItemNS = attributeMap.getNamedItemNS(str, str2)) == null) {
                return null;
            }
            return namedItemNS.getNodeValue();
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public boolean isSpecified(int i) {
            return ((Attr) this.fAttributes.getItem(i)).getSpecified();
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void removeAllAttributes() {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void removeAttributeAt(int i) {
        }

        public void setAttributes(AttributeMap attributeMap, CoreDocumentImpl coreDocumentImpl, ElementImpl elementImpl) {
            this.fDocument = coreDocumentImpl;
            this.fAttributes = attributeMap;
            this.fElement = elementImpl;
            if (attributeMap == null) {
                this.fDTDTypes.setSize(0);
                this.fAugmentations.setSize(0);
                return;
            }
            int length = attributeMap.getLength();
            this.fDTDTypes.setSize(length);
            this.fAugmentations.setSize(length);
            for (int i = 0; i < length; i++) {
                this.fAugmentations.setElementAt(new AugmentationsImpl(), i);
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setAugmentations(int i, Augmentations augmentations) {
            this.fAugmentations.setElementAt(augmentations, i);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setName(int i, QName qName) {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setNonNormalizedValue(int i, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setSpecified(int i, boolean z) {
            ((AttrImpl) this.fAttributes.getItem(i)).setSpecified(z);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setType(int i, String str) {
            this.fDTDTypes.setElementAt(str, i);
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setValue(int i, String str) {
            AttributeMap attributeMap = this.fAttributes;
            if (attributeMap != null) {
                AttrImpl attrImpl = (AttrImpl) attributeMap.getItem(i);
                boolean specified = attrImpl.getSpecified();
                attrImpl.setValue(str);
                attrImpl.setSpecified(specified);
            }
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public int getIndex(String str, String str2) {
            return -1;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public Augmentations getAugmentations(String str, String str2) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public Augmentations getAugmentations(String str) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getValue(int i) {
            AttributeMap attributeMap = this.fAttributes;
            return attributeMap != null ? attributeMap.item(i).getNodeValue() : "";
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getType(String str) {
            return "CDATA";
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getValue(String str) {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public String getType(String str, String str2) {
            return "CDATA";
        }

        @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
        public void setValue(int i, String str, XMLString xMLString) {
            setValue(i, xMLString.toString());
        }
    }

    public static final void isAttrValueWF(DOMErrorHandler dOMErrorHandler, DOMErrorImpl dOMErrorImpl, DOMLocatorImpl dOMLocatorImpl, NamedNodeMap namedNodeMap, Attr attr, String str, boolean z) {
        DOMErrorHandler dOMErrorHandler2;
        DOMErrorImpl dOMErrorImpl2;
        DOMLocatorImpl dOMLocatorImpl2;
        DocumentType doctype;
        if ((attr instanceof AttrImpl) && ((AttrImpl) attr).hasStringValue()) {
            isXMLCharWF(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, str, z);
            return;
        }
        NodeList childNodes = attr.getChildNodes();
        int i = 0;
        while (i < childNodes.getLength()) {
            Node nodeItem = childNodes.item(i);
            if (nodeItem.getNodeType() == 5) {
                Document ownerDocument = attr.getOwnerDocument();
                if (((ownerDocument == null || (doctype = ownerDocument.getDoctype()) == null) ? null : (Entity) doctype.getEntities().getNamedItemNS("*", nodeItem.getNodeName())) == null) {
                    dOMErrorHandler2 = dOMErrorHandler;
                    dOMErrorImpl2 = dOMErrorImpl;
                    dOMLocatorImpl2 = dOMLocatorImpl;
                    reportDOMError(dOMErrorHandler2, dOMErrorImpl2, dOMLocatorImpl2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "UndeclaredEntRefInAttrValue", new Object[]{attr.getNodeName()}), (short) 2, "UndeclaredEntRefInAttrValue");
                } else {
                    dOMErrorHandler2 = dOMErrorHandler;
                    dOMErrorImpl2 = dOMErrorImpl;
                    dOMLocatorImpl2 = dOMLocatorImpl;
                }
            } else {
                dOMErrorHandler2 = dOMErrorHandler;
                dOMErrorImpl2 = dOMErrorImpl;
                dOMLocatorImpl2 = dOMLocatorImpl;
                isXMLCharWF(dOMErrorHandler2, dOMErrorImpl2, dOMLocatorImpl2, nodeItem.getNodeValue(), z);
            }
            i++;
            dOMErrorHandler = dOMErrorHandler2;
            dOMErrorImpl = dOMErrorImpl2;
            dOMLocatorImpl = dOMLocatorImpl2;
        }
    }

    public static final void isCDataWF(DOMErrorHandler dOMErrorHandler, DOMErrorImpl dOMErrorImpl, DOMLocatorImpl dOMLocatorImpl, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        if (z) {
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
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInCDSect", new Object[]{Integer.toString(c, 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                } else if (c == ']' && i2 < length && charArray[i2] == ']') {
                    int i3 = i2;
                    do {
                        i3++;
                        if (i3 >= length) {
                            break;
                        }
                    } while (charArray[i3] == ']');
                    if (i3 < length && charArray[i3] == '>') {
                        reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "CDEndInContent", null), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                    }
                }
                i = i2;
            }
            return;
        }
        while (i < length) {
            int i4 = i + 1;
            char c3 = charArray[i];
            if (XMLChar.isInvalid(c3)) {
                if (XMLChar.isHighSurrogate(c3) && i4 < length) {
                    i += 2;
                    char c4 = charArray[i4];
                    if (!XMLChar.isLowSurrogate(c4) || !XMLChar.isSupplemental(XMLChar.supplemental(c3, c4))) {
                        i4 = i;
                    }
                }
                reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInCDSect", new Object[]{Integer.toString(c3, 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
            } else if (c3 == ']' && i4 < length && charArray[i4] == ']') {
                int i5 = i4;
                do {
                    i5++;
                    if (i5 >= length) {
                        break;
                    }
                } while (charArray[i5] == ']');
                if (i5 < length && charArray[i5] == '>') {
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "CDEndInContent", null), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                }
            }
            i = i4;
        }
    }

    public static final void isCommentWF(DOMErrorHandler dOMErrorHandler, DOMErrorImpl dOMErrorImpl, DOMLocatorImpl dOMLocatorImpl, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        if (z) {
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
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInComment", new Object[]{Integer.toString(charArray[i2 - 1], 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                } else if (c == '-' && i2 < length && charArray[i2] == '-') {
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "DashDashInComment", null), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
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
                reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInComment", new Object[]{Integer.toString(charArray[i3 - 1], 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
            } else if (c3 == '-' && i3 < length && charArray[i3] == '-') {
                reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "DashDashInComment", null), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
            }
            i = i3;
        }
    }

    public static final void isXMLCharWF(DOMErrorHandler dOMErrorHandler, DOMErrorImpl dOMErrorImpl, DOMLocatorImpl dOMLocatorImpl, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        if (z) {
            while (i < length) {
                int i2 = i + 1;
                if (XML11Char.isXML11Invalid(charArray[i])) {
                    char c = charArray[i];
                    if (!XMLChar.isHighSurrogate(c) || i2 >= length) {
                        i = i2;
                        reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[]{Integer.toString(charArray[i - 1], 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                    } else {
                        i += 2;
                        char c2 = charArray[i2];
                        if (!XMLChar.isLowSurrogate(c2) || !XMLChar.isSupplemental(XMLChar.supplemental(c, c2))) {
                            reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[]{Integer.toString(charArray[i - 1], 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                        }
                    }
                } else {
                    i = i2;
                }
            }
            return;
        }
        while (i < length) {
            int i3 = i + 1;
            if (XMLChar.isInvalid(charArray[i])) {
                char c3 = charArray[i];
                if (!XMLChar.isHighSurrogate(c3) || i3 >= length) {
                    i = i3;
                    reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[]{Integer.toString(charArray[i - 1], 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                } else {
                    i += 2;
                    char c4 = charArray[i3];
                    if (!XMLChar.isLowSurrogate(c4) || !XMLChar.isSupplemental(XMLChar.supplemental(c3, c4))) {
                        reportDOMError(dOMErrorHandler, dOMErrorImpl, dOMLocatorImpl, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "InvalidXMLCharInDOM", new Object[]{Integer.toString(charArray[i - 1], 16)}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER);
                    }
                }
            } else {
                i = i3;
            }
        }
    }

    private void processDTD(String str, String str2) {
        String nodeName;
        String str3;
        String internalSubset;
        String documentURI = this.fDocument.getDocumentURI();
        DocumentType doctype = this.fDocument.getDoctype();
        XMLDTDLoader dTDLoader = null;
        if (doctype != null) {
            nodeName = doctype.getName();
            String publicId = doctype.getPublicId();
            if (str2 == null || str2.length() == 0) {
                str2 = doctype.getSystemId();
            }
            internalSubset = doctype.getInternalSubset();
            str3 = publicId;
        } else {
            Element documentElement = this.fDocument.getDocumentElement();
            if (documentElement == null) {
                return;
            }
            nodeName = documentElement.getNodeName();
            if (str2 == null || str2.length() == 0) {
                return;
            }
            str3 = null;
            internalSubset = null;
        }
        String str4 = str2;
        String str5 = nodeName;
        try {
            this.fValidationHandler.doctypeDecl(str5, str3, str4, null);
            CoreDOMImplementationImpl coreDOMImplementationImpl = CoreDOMImplementationImpl.singleton;
            dTDLoader = coreDOMImplementationImpl.getDTDLoader(str);
            dTDLoader.setFeature("http://xml.org/sax/features/validation", true);
            dTDLoader.setEntityResolver(this.fConfiguration.getEntityResolver());
            dTDLoader.setErrorHandler(this.fConfiguration.getErrorHandler());
            dTDLoader.loadGrammarWithContext((XMLDTDValidator) this.fValidationHandler, str5, str3, str4, documentURI, internalSubset);
            coreDOMImplementationImpl.releaseDTDLoader(str, dTDLoader);
        } catch (IOException unused) {
            if (dTDLoader != null) {
                CoreDOMImplementationImpl.singleton.releaseDTDLoader(str, dTDLoader);
            }
        } catch (Throwable th) {
            if (dTDLoader == null) {
                throw th;
            }
            CoreDOMImplementationImpl.singleton.releaseDTDLoader(str, dTDLoader);
            throw th;
        }
    }

    public static final void reportDOMError(DOMErrorHandler dOMErrorHandler, DOMErrorImpl dOMErrorImpl, DOMLocatorImpl dOMLocatorImpl, String str, short s, String str2) {
        if (dOMErrorHandler != null) {
            dOMErrorImpl.reset();
            dOMErrorImpl.fMessage = str;
            dOMErrorImpl.fSeverity = s;
            dOMErrorImpl.fLocator = dOMLocatorImpl;
            dOMErrorImpl.fType = str2;
            dOMErrorImpl.fRelatedData = dOMLocatorImpl.fRelatedNode;
            if (!dOMErrorHandler.handleError(dOMErrorImpl)) {
                throw new AbortException();
            }
        }
        if (s == 3) {
            throw new AbortException();
        }
    }

    public final void addNamespaceDecl(String str, String str2, ElementImpl elementImpl) {
        if (str == XMLSymbols.EMPTY_STRING) {
            elementImpl.setAttributeNS(NamespaceContext.XMLNS_URI, XMLSymbols.PREFIX_XMLNS, str2);
            return;
        }
        elementImpl.setAttributeNS(NamespaceContext.XMLNS_URI, "xmlns:" + str, str2);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        startElement(qName, xMLAttributes, augmentations);
        endElement(qName, augmentations);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(QName qName, Augmentations augmentations) throws XNIException {
        ElementPSVI elementPSVI;
        if (augmentations == null || (elementPSVI = (ElementPSVI) augmentations.getItem(Constants.ELEMENT_PSVI)) == null) {
            Node node = this.fCurrentNode;
            if (node instanceof ElementNSImpl) {
                ((ElementNSImpl) node).setType(null);
                return;
            }
            return;
        }
        Node node2 = this.fCurrentNode;
        ElementImpl elementImpl = (ElementImpl) node2;
        if (this.fPSVI) {
            ((PSVIElementNSImpl) node2).setPSVI(elementPSVI);
        }
        if (elementImpl instanceof ElementNSImpl) {
            XSTypeDefinition memberTypeDefinition = elementPSVI.getMemberTypeDefinition();
            if (memberTypeDefinition == null) {
                memberTypeDefinition = elementPSVI.getTypeDefinition();
            }
            ((ElementNSImpl) elementImpl).setType(memberTypeDefinition);
        }
        String normalizedValue = elementPSVI.getSchemaValue().getNormalizedValue();
        if ((this.fConfiguration.features & 2) != 0) {
            if (normalizedValue != null) {
                elementImpl.setTextContent(normalizedValue);
            }
        } else {
            if (elementImpl.getTextContent().length() != 0 || normalizedValue == null) {
                return;
            }
            elementImpl.setTextContent(normalizedValue);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
    }

    public final void expandEntityRef(Node node, Node node2) {
        Node firstChild = node2.getFirstChild();
        while (firstChild != null) {
            Node nextSibling = firstChild.getNextSibling();
            node.insertBefore(firstChild, node2);
            firstChild = nextSibling;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        this.fAllWhitespace = true;
    }

    public final void namespaceFixUp(ElementImpl elementImpl, AttributeMap attributeMap) {
        AttributeMap attributeMap2 = attributeMap;
        if (attributeMap2 != null) {
            for (int i = 0; i < attributeMap2.getLength(); i++) {
                Attr attr = (Attr) attributeMap2.getItem(i);
                String namespaceURI = attr.getNamespaceURI();
                if (namespaceURI != null) {
                    String str = NamespaceContext.XMLNS_URI;
                    if (namespaceURI.equals(str)) {
                        String nodeValue = attr.getNodeValue();
                        if (nodeValue == null) {
                            nodeValue = XMLSymbols.EMPTY_STRING;
                        }
                        if (this.fDocument.errorChecking && nodeValue.equals(str)) {
                            this.fLocator.fRelatedNode = attr;
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "CantBindXMLNS", null), (short) 2, "CantBindXMLNS");
                        } else {
                            String prefix = attr.getPrefix();
                            String strAddSymbol = (prefix == null || prefix.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix);
                            String strAddSymbol2 = this.fSymbolTable.addSymbol(attr.getLocalName());
                            String str2 = XMLSymbols.PREFIX_XMLNS;
                            SymbolTable symbolTable = this.fSymbolTable;
                            if (strAddSymbol == str2) {
                                String strAddSymbol3 = symbolTable.addSymbol(nodeValue);
                                if (strAddSymbol3.length() != 0) {
                                    this.fNamespaceContext.declarePrefix(strAddSymbol2, strAddSymbol3);
                                }
                            } else {
                                String strAddSymbol4 = symbolTable.addSymbol(nodeValue);
                                NamespaceContext namespaceContext = this.fNamespaceContext;
                                String str3 = XMLSymbols.EMPTY_STRING;
                                if (strAddSymbol4.length() == 0) {
                                    strAddSymbol4 = null;
                                }
                                namespaceContext.declarePrefix(str3, strAddSymbol4);
                            }
                        }
                    }
                }
            }
        }
        String namespaceURI2 = elementImpl.getNamespaceURI();
        String prefix2 = elementImpl.getPrefix();
        if (namespaceURI2 != null) {
            String strAddSymbol5 = this.fSymbolTable.addSymbol(namespaceURI2);
            String strAddSymbol6 = (prefix2 == null || prefix2.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix2);
            if (this.fNamespaceContext.getURI(strAddSymbol6) != strAddSymbol5) {
                addNamespaceDecl(strAddSymbol6, strAddSymbol5, elementImpl);
                this.fLocalNSBinder.declarePrefix(strAddSymbol6, strAddSymbol5);
                this.fNamespaceContext.declarePrefix(strAddSymbol6, strAddSymbol5);
            }
        } else if (elementImpl.getLocalName() != null) {
            NamespaceContext namespaceContext2 = this.fNamespaceContext;
            String str4 = XMLSymbols.EMPTY_STRING;
            String uri = namespaceContext2.getURI(str4);
            if (uri != null && uri.length() > 0) {
                addNamespaceDecl(str4, str4, elementImpl);
                this.fLocalNSBinder.declarePrefix(str4, null);
                this.fNamespaceContext.declarePrefix(str4, null);
            }
        } else if (this.fNamespaceValidation) {
            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalElementName", new Object[]{elementImpl.getNodeName()}), (short) 3, "NullLocalElementName");
        } else {
            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalElementName", new Object[]{elementImpl.getNodeName()}), (short) 2, "NullLocalElementName");
        }
        if (attributeMap2 != null) {
            List<Node> listCloneMap = attributeMap2.cloneMap(new ArrayList());
            int i2 = 0;
            while (i2 < listCloneMap.size()) {
                Attr attr2 = (Attr) listCloneMap.get(i2);
                this.fLocator.fRelatedNode = attr2;
                attr2.normalize();
                String value = attr2.getValue();
                String namespaceURI3 = attr2.getNamespaceURI();
                if (value == null) {
                    value = XMLSymbols.EMPTY_STRING;
                }
                String str5 = value;
                CoreDocumentImpl coreDocumentImpl = this.fDocument;
                if (coreDocumentImpl.errorChecking && (this.fConfiguration.features & 256) != 0) {
                    isAttrValueWF(this.fErrorHandler, this.fError, this.fLocator, attributeMap2, attr2, str5, coreDocumentImpl.isXML11Version());
                    if (this.fDocument.isXMLVersionChanged()) {
                        if (!(this.fNamespaceValidation ? CoreDocumentImpl.isValidQName(attr2.getPrefix(), attr2.getLocalName(), this.fDocument.isXML11Version()) : CoreDocumentImpl.isXMLName(attr2.getNodeName(), this.fDocument.isXML11Version()))) {
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Attr", attr2.getNodeName()}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                        }
                    }
                }
                if (namespaceURI3 != null) {
                    String prefix3 = attr2.getPrefix();
                    String strAddSymbol7 = (prefix3 == null || prefix3.length() == 0) ? XMLSymbols.EMPTY_STRING : this.fSymbolTable.addSymbol(prefix3);
                    this.fSymbolTable.addSymbol(attr2.getLocalName());
                    if (!namespaceURI3.equals(NamespaceContext.XMLNS_URI)) {
                        ((AttrImpl) attr2).setIdAttribute(false);
                        String strAddSymbol8 = this.fSymbolTable.addSymbol(namespaceURI3);
                        String uri2 = this.fNamespaceContext.getURI(strAddSymbol7);
                        String str6 = XMLSymbols.EMPTY_STRING;
                        if (strAddSymbol7 == str6 || uri2 != strAddSymbol8) {
                            String prefix4 = this.fNamespaceContext.getPrefix(strAddSymbol8);
                            if (prefix4 == null || prefix4 == str6) {
                                if (strAddSymbol7 == str6 || this.fLocalNSBinder.getURI(strAddSymbol7) != null) {
                                    strAddSymbol7 = this.fSymbolTable.addSymbol("NS1");
                                    int i3 = 2;
                                    while (this.fLocalNSBinder.getURI(strAddSymbol7) != null) {
                                        strAddSymbol7 = this.fSymbolTable.addSymbol(PREFIX + i3);
                                        i3++;
                                    }
                                }
                                addNamespaceDecl(strAddSymbol7, strAddSymbol8, elementImpl);
                                this.fLocalNSBinder.declarePrefix(strAddSymbol7, this.fSymbolTable.addSymbol(str5));
                                this.fNamespaceContext.declarePrefix(strAddSymbol7, strAddSymbol8);
                                prefix4 = strAddSymbol7;
                            }
                            attr2.setPrefix(prefix4);
                        }
                    }
                } else {
                    ((AttrImpl) attr2).setIdAttribute(false);
                    if (attr2.getLocalName() == null) {
                        if (this.fNamespaceValidation) {
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr2.getNodeName()}), (short) 3, "NullLocalAttrName");
                        } else {
                            reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NullLocalAttrName", new Object[]{attr2.getNodeName()}), (short) 2, "NullLocalAttrName");
                        }
                    }
                }
                i2++;
                attributeMap2 = attributeMap;
            }
        }
    }

    public final String normalizeAttributeValue(String str, Attr attr) {
        if (!attr.getSpecified()) {
            return str;
        }
        int length = str.length();
        XMLString xMLString = this.fNormalizedValue;
        if (xMLString.ch.length < length) {
            xMLString.ch = new char[length];
        }
        int i = 0;
        xMLString.length = 0;
        boolean z = false;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\t' || cCharAt == '\n') {
                XMLString xMLString2 = this.fNormalizedValue;
                char[] cArr = xMLString2.ch;
                int i2 = xMLString2.length;
                xMLString2.length = i2 + 1;
                cArr[i2] = ' ';
            } else {
                XMLString xMLString3 = this.fNormalizedValue;
                if (cCharAt == '\r') {
                    char[] cArr2 = xMLString3.ch;
                    int i3 = xMLString3.length;
                    xMLString3.length = i3 + 1;
                    cArr2[i3] = ' ';
                    int i4 = i + 1;
                    if (i4 < length && str.charAt(i4) == '\n') {
                        i = i4;
                    }
                } else {
                    char[] cArr3 = xMLString3.ch;
                    int i5 = xMLString3.length;
                    xMLString3.length = i5 + 1;
                    cArr3[i5] = cCharAt;
                }
                i++;
            }
            z = true;
            i++;
        }
        if (!z) {
            return str;
        }
        String string = this.fNormalizedValue.toString();
        attr.setValue(string);
        return string;
    }

    public void normalizeDocument(CoreDocumentImpl coreDocumentImpl, DOMConfigurationImpl dOMConfigurationImpl) {
        String[] strArr;
        String str;
        String str2;
        this.fDocument = coreDocumentImpl;
        this.fConfiguration = dOMConfigurationImpl;
        this.fAllWhitespace = false;
        this.fNamespaceValidation = false;
        String xmlVersion = coreDocumentImpl.getXmlVersion();
        this.fSymbolTable = (SymbolTable) this.fConfiguration.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fNamespaceContext.reset();
        this.fNamespaceContext.declarePrefix(XMLSymbols.EMPTY_STRING, null);
        DOMConfigurationImpl dOMConfigurationImpl2 = this.fConfiguration;
        if ((dOMConfigurationImpl2.features & 64) != 0) {
            String str3 = (String) dOMConfigurationImpl2.getProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE);
            if (str3 == null || !str3.equals(Constants.NS_XMLSCHEMA)) {
                strArr = str3 != null ? (String[]) this.fConfiguration.getProperty(JAXPConstants.JAXP_SCHEMA_SOURCE) : null;
                this.fConfiguration.setDTDValidatorFactory(xmlVersion);
                this.fValidationHandler = CoreDOMImplementationImpl.singleton.getValidator("http://www.w3.org/TR/REC-xml", xmlVersion);
                this.fPSVI = false;
                str = "http://www.w3.org/TR/REC-xml";
            } else {
                str = "http://www.w3.org/2001/XMLSchema";
                this.fValidationHandler = CoreDOMImplementationImpl.singleton.getValidator("http://www.w3.org/2001/XMLSchema", xmlVersion);
                this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema", true);
                this.fConfiguration.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
                this.fNamespaceValidation = true;
                this.fPSVI = (this.fConfiguration.features & 128) != 0;
                strArr = null;
            }
            this.fConfiguration.setFeature("http://xml.org/sax/features/validation", true);
            this.fDocument.clearIdentifiers();
            RevalidationHandler revalidationHandler = this.fValidationHandler;
            if (revalidationHandler != null) {
                ((XMLComponent) revalidationHandler).reset(this.fConfiguration);
            }
        } else {
            this.fValidationHandler = null;
            strArr = null;
            str = null;
        }
        this.fErrorHandler = (DOMErrorHandler) this.fConfiguration.getParameter("error-handler");
        RevalidationHandler revalidationHandler2 = this.fValidationHandler;
        if (revalidationHandler2 != null) {
            revalidationHandler2.setDocumentHandler(this);
            RevalidationHandler revalidationHandler3 = this.fValidationHandler;
            String str4 = this.fDocument.fDocumentURI;
            revalidationHandler3.startDocument(new SimpleLocator(str4, str4, -1, -1), this.fDocument.encoding, this.fNamespaceContext, null);
            this.fValidationHandler.xmlDecl(this.fDocument.getXmlVersion(), this.fDocument.getXmlEncoding(), this.fDocument.getXmlStandalone() ? JdkConstants.JDK_YES : "no", null);
        }
        if (str == "http://www.w3.org/TR/REC-xml") {
            if (strArr != null) {
                try {
                    str2 = strArr[0];
                } catch (RuntimeException e) {
                    RevalidationHandler revalidationHandler4 = this.fValidationHandler;
                    if (revalidationHandler4 != null) {
                        revalidationHandler4.setDocumentHandler(null);
                        CoreDOMImplementationImpl.singleton.releaseValidator(str, xmlVersion, this.fValidationHandler);
                        this.fValidationHandler = null;
                    }
                    if (!(e instanceof AbortException)) {
                        throw e;
                    }
                    return;
                }
            } else {
                str2 = null;
            }
            processDTD(xmlVersion, str2);
        }
        Node firstChild = this.fDocument.getFirstChild();
        while (firstChild != null) {
            Node nextSibling = firstChild.getNextSibling();
            firstChild = normalizeNode(firstChild);
            if (firstChild == null) {
                firstChild = nextSibling;
            }
        }
        RevalidationHandler revalidationHandler5 = this.fValidationHandler;
        if (revalidationHandler5 != null) {
            revalidationHandler5.endDocument(null);
            this.fValidationHandler.setDocumentHandler(null);
            CoreDOMImplementationImpl.singleton.releaseValidator(str, xmlVersion, this.fValidationHandler);
            this.fValidationHandler = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:116:0x0262  */
    /* JADX WARN: Code duplicated, block: B:123:0x0287  */
    /* JADX WARN: Code duplicated, block: B:125:0x029a  */
    /* JADX WARN: Code duplicated, block: B:127:0x029e  */
    /* JADX WARN: Code duplicated, block: B:128:0x02a8  */
    public Node normalizeNode(Node node) {
        RevalidationHandler revalidationHandler;
        Node nextSibling;
        Node node2 = node;
        short nodeType = node2.getNodeType();
        this.fLocator.fRelatedNode = node2;
        int i = 0;
        if (nodeType == 1) {
            CoreDocumentImpl coreDocumentImpl = this.fDocument;
            if (coreDocumentImpl.errorChecking && (this.fConfiguration.features & 256) != 0 && coreDocumentImpl.isXMLVersionChanged()) {
                if (!(this.fNamespaceValidation ? CoreDocumentImpl.isValidQName(node2.getPrefix(), node2.getLocalName(), this.fDocument.isXML11Version()) : CoreDocumentImpl.isXMLName(node2.getNodeName(), this.fDocument.isXML11Version()))) {
                    reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Element", node2.getNodeName()}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                }
            }
            this.fNamespaceContext.pushContext();
            this.fLocalNSBinder.reset();
            ElementImpl elementImpl = (ElementImpl) node2;
            if (elementImpl.needsSyncChildren()) {
                elementImpl.synchronizeChildren();
            }
            AttributeMap attributeMap = elementImpl.hasAttributes() ? (AttributeMap) elementImpl.getAttributes() : null;
            if ((this.fConfiguration.features & 1) != 0) {
                namespaceFixUp(elementImpl, attributeMap);
                if ((this.fConfiguration.features & 512) == 0) {
                    if (attributeMap == null) {
                        attributeMap = elementImpl.hasAttributes() ? (AttributeMap) elementImpl.getAttributes() : null;
                    }
                    if (attributeMap != null) {
                        while (i < attributeMap.getLength()) {
                            Attr attr = (Attr) attributeMap.getItem(i);
                            String str = XMLSymbols.PREFIX_XMLNS;
                            if (str.equals(attr.getPrefix()) || str.equals(attr.getName())) {
                                elementImpl.removeAttributeNode(attr);
                                i--;
                            }
                            i++;
                        }
                    }
                }
            } else if (attributeMap != null) {
                while (i < attributeMap.getLength()) {
                    Attr attr2 = (Attr) attributeMap.item(i);
                    attr2.normalize();
                    if (this.fDocument.errorChecking && (this.fConfiguration.features & 256) != 0) {
                        isAttrValueWF(this.fErrorHandler, this.fError, this.fLocator, attributeMap, attr2, attr2.getValue(), this.fDocument.isXML11Version());
                        if (this.fDocument.isXMLVersionChanged()) {
                            if (!(this.fNamespaceValidation ? CoreDocumentImpl.isValidQName(node2.getPrefix(), node2.getLocalName(), this.fDocument.isXML11Version()) : CoreDocumentImpl.isXMLName(node2.getNodeName(), this.fDocument.isXML11Version()))) {
                                reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Attr", node2.getNodeName()}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                            }
                        }
                    }
                    i++;
                }
            }
            if (this.fValidationHandler != null) {
                this.fAttrProxy.setAttributes(attributeMap, this.fDocument, elementImpl);
                updateQName(elementImpl, this.fQName);
                this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node2;
                this.fCurrentNode = node2;
                this.fValidationHandler.startElement(this.fQName, this.fAttrProxy, null);
            }
            Node firstChild = elementImpl.getFirstChild();
            while (firstChild != null) {
                Node nextSibling2 = firstChild.getNextSibling();
                firstChild = normalizeNode(firstChild);
                if (firstChild == null) {
                    firstChild = nextSibling2;
                }
            }
            if (this.fValidationHandler != null) {
                updateQName(elementImpl, this.fQName);
                this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node2;
                this.fCurrentNode = node2;
                this.fValidationHandler.endElement(this.fQName, null);
            }
            this.fNamespaceContext.popContext();
        } else if (nodeType == 3) {
            Node nextSibling3 = node2.getNextSibling();
            if (nextSibling3 != null && nextSibling3.getNodeType() == 3) {
                ((Text) node2).appendData(nextSibling3.getNodeValue());
                node2.getParentNode().removeChild(nextSibling3);
                return node2;
            }
            if (node2.getNodeValue().length() == 0) {
                node2.getParentNode().removeChild(node2);
            } else {
                short nodeType2 = nextSibling3 != null ? nextSibling3.getNodeType() : (short) -1;
                if (nodeType2 != -1) {
                    short s = this.fConfiguration.features;
                    if (((s & 4) != 0 || nodeType2 != 6) && (((s & 32) != 0 || nodeType2 != 8) && ((s & 8) != 0 || nodeType2 != 4))) {
                        if (this.fDocument.errorChecking && (this.fConfiguration.features & 256) != 0) {
                            isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node2.getNodeValue(), this.fDocument.isXML11Version());
                        }
                        revalidationHandler = this.fValidationHandler;
                        if (revalidationHandler != null) {
                            this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node2;
                            this.fCurrentNode = node2;
                            revalidationHandler.characterData(node2.getNodeValue(), null);
                            if (!this.fNamespaceValidation) {
                                if (this.fAllWhitespace) {
                                    this.fAllWhitespace = false;
                                    ((TextImpl) node2).setIgnorableWhitespace(true);
                                } else {
                                    ((TextImpl) node2).setIgnorableWhitespace(false);
                                }
                            }
                        }
                    }
                } else {
                    if (this.fDocument.errorChecking) {
                        isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node2.getNodeValue(), this.fDocument.isXML11Version());
                    }
                    revalidationHandler = this.fValidationHandler;
                    if (revalidationHandler != null) {
                        this.fConfiguration.fErrorHandlerWrapper.fCurrentNode = node2;
                        this.fCurrentNode = node2;
                        revalidationHandler.characterData(node2.getNodeValue(), null);
                        if (!this.fNamespaceValidation) {
                            if (this.fAllWhitespace) {
                                this.fAllWhitespace = false;
                                ((TextImpl) node2).setIgnorableWhitespace(true);
                            } else {
                                ((TextImpl) node2).setIgnorableWhitespace(false);
                            }
                        }
                    }
                }
            }
        } else if (nodeType == 4) {
            DOMConfigurationImpl dOMConfigurationImpl = this.fConfiguration;
            if ((dOMConfigurationImpl.features & 8) == 0) {
                Node previousSibling = node2.getPreviousSibling();
                if (previousSibling == null || previousSibling.getNodeType() != 3) {
                    Node nodeCreateTextNode = this.fDocument.createTextNode(node2.getNodeValue());
                    node2.getParentNode().replaceChild(nodeCreateTextNode, node2);
                    return nodeCreateTextNode;
                }
                ((Text) previousSibling).appendData(node2.getNodeValue());
                node2.getParentNode().removeChild(node2);
                return previousSibling;
            }
            RevalidationHandler revalidationHandler2 = this.fValidationHandler;
            if (revalidationHandler2 != null) {
                dOMConfigurationImpl.fErrorHandlerWrapper.fCurrentNode = node2;
                this.fCurrentNode = node2;
                revalidationHandler2.startCDATA(null);
                this.fValidationHandler.characterData(node2.getNodeValue(), null);
                this.fValidationHandler.endCDATA(null);
            }
            String nodeValue = node2.getNodeValue();
            if ((this.fConfiguration.features & 16) != 0) {
                Node parentNode = node2.getParentNode();
                if (this.fDocument.errorChecking) {
                    isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, node2.getNodeValue(), this.fDocument.isXML11Version());
                }
                while (true) {
                    int iIndexOf = nodeValue.indexOf("]]>");
                    if (iIndexOf < 0) {
                        break;
                    }
                    int i2 = iIndexOf + 2;
                    node2.setNodeValue(nodeValue.substring(0, i2));
                    nodeValue = nodeValue.substring(i2);
                    Node nodeCreateCDATASection = this.fDocument.createCDATASection(nodeValue);
                    parentNode.insertBefore(nodeCreateCDATASection, node2.getNextSibling());
                    this.fLocator.fRelatedNode = node2;
                    reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_CDATA_SECTIONS_SPLIT, null), (short) 1, MsgKey.ER_CDATA_SECTIONS_SPLIT);
                    node2 = nodeCreateCDATASection;
                }
            } else {
                CoreDocumentImpl coreDocumentImpl2 = this.fDocument;
                if (coreDocumentImpl2.errorChecking) {
                    isCDataWF(this.fErrorHandler, this.fError, this.fLocator, nodeValue, coreDocumentImpl2.isXML11Version());
                }
            }
        } else if (nodeType == 5) {
            short s2 = this.fConfiguration.features;
            if ((s2 & 4) == 0) {
                Node previousSibling2 = node2.getPreviousSibling();
                Node parentNode2 = node2.getParentNode();
                ((EntityReferenceImpl) node2).setReadOnly(false, true);
                expandEntityRef(parentNode2, node2);
                parentNode2.removeChild(node2);
                Node nextSibling4 = previousSibling2 != null ? previousSibling2.getNextSibling() : parentNode2.getFirstChild();
                return (previousSibling2 == null || nextSibling4 == null || previousSibling2.getNodeType() != 3 || nextSibling4.getNodeType() != 3) ? nextSibling4 : previousSibling2;
            }
            CoreDocumentImpl coreDocumentImpl3 = this.fDocument;
            if (coreDocumentImpl3.errorChecking && (s2 & 256) != 0 && coreDocumentImpl3.isXMLVersionChanged()) {
                CoreDocumentImpl.isXMLName(node2.getNodeName(), this.fDocument.isXML11Version());
            }
        } else if (nodeType == 7) {
            if (this.fDocument.errorChecking && (this.fConfiguration.features & 256) != 0) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) node2;
                String target = processingInstruction.getTarget();
                if (!(this.fDocument.isXML11Version() ? XML11Char.isXML11ValidName(target) : XMLChar.isValidName(target))) {
                    reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Element", node2.getNodeName()}), (short) 2, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                }
                isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, processingInstruction.getData(), this.fDocument.isXML11Version());
            }
            RevalidationHandler revalidationHandler3 = this.fValidationHandler;
            if (revalidationHandler3 != null) {
                revalidationHandler3.processingInstruction(((ProcessingInstruction) node2).getTarget(), EMPTY_STRING, null);
            }
        } else if (nodeType == 8) {
            short s3 = this.fConfiguration.features;
            if ((s3 & 32) == 0) {
                Node previousSibling3 = node2.getPreviousSibling();
                Node parentNode3 = node2.getParentNode();
                parentNode3.removeChild(node2);
                if (previousSibling3 != null && previousSibling3.getNodeType() == 3 && (nextSibling = previousSibling3.getNextSibling()) != null && nextSibling.getNodeType() == 3) {
                    ((TextImpl) nextSibling).insertData(0, previousSibling3.getNodeValue());
                    parentNode3.removeChild(previousSibling3);
                    return nextSibling;
                }
            } else {
                if (this.fDocument.errorChecking && (s3 & 256) != 0) {
                    isCommentWF(this.fErrorHandler, this.fError, this.fLocator, ((Comment) node2).getData(), this.fDocument.isXML11Version());
                }
                RevalidationHandler revalidationHandler4 = this.fValidationHandler;
                if (revalidationHandler4 != null) {
                    revalidationHandler4.comment(EMPTY_STRING, null);
                }
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        String type;
        boolean zIsIDType;
        String normalizedValue;
        Element element = (Element) this.fCurrentNode;
        int length = xMLAttributes.getLength();
        for (int i = 0; i < length; i++) {
            xMLAttributes.getName(i, this.fAttrQName);
            QName qName2 = this.fAttrQName;
            Attr attributeNodeNS = element.getAttributeNodeNS(qName2.uri, qName2.localpart);
            if (attributeNodeNS == null) {
                attributeNodeNS = element.getAttributeNode(this.fAttrQName.rawname);
            }
            AttributePSVI attributePSVI = (AttributePSVI) xMLAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
            if (attributePSVI != null) {
                XSTypeDefinition memberTypeDefinition = attributePSVI.getMemberTypeDefinition();
                if (memberTypeDefinition != null) {
                    zIsIDType = ((XSSimpleType) memberTypeDefinition).isIDType();
                } else {
                    memberTypeDefinition = attributePSVI.getTypeDefinition();
                    zIsIDType = memberTypeDefinition != null ? ((XSSimpleType) memberTypeDefinition).isIDType() : false;
                }
                if (zIsIDType) {
                    ((ElementImpl) element).setIdAttributeNode(attributeNodeNS, true);
                }
                if (this.fPSVI) {
                    ((PSVIAttrNSImpl) attributeNodeNS).setPSVI(attributePSVI);
                }
                ((AttrImpl) attributeNodeNS).setType(memberTypeDefinition);
                if ((this.fConfiguration.features & 2) != 0 && (normalizedValue = attributePSVI.getSchemaValue().getNormalizedValue()) != null) {
                    boolean specified = attributeNodeNS.getSpecified();
                    attributeNodeNS.setValue(normalizedValue);
                    if (!specified) {
                        ((AttrImpl) attributeNodeNS).setSpecified(specified);
                    }
                }
            } else {
                if (Boolean.TRUE.equals(xMLAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_DECLARED))) {
                    type = xMLAttributes.getType(i);
                    if (SchemaSymbols.ATTVAL_ID.equals(type)) {
                        ((ElementImpl) element).setIdAttributeNode(attributeNodeNS, true);
                    }
                } else {
                    type = null;
                }
                ((AttrImpl) attributeNodeNS).setType(type);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
    }

    public final void updateQName(Node node, QName qName) {
        String prefix = node.getPrefix();
        String namespaceURI = node.getNamespaceURI();
        String localName = node.getLocalName();
        qName.prefix = (prefix == null || prefix.length() == 0) ? null : this.fSymbolTable.addSymbol(prefix);
        qName.localpart = localName != null ? this.fSymbolTable.addSymbol(localName) : null;
        qName.rawname = this.fSymbolTable.addSymbol(node.getNodeName());
        qName.uri = namespaceURI != null ? this.fSymbolTable.addSymbol(namespaceURI) : null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
    }
}
