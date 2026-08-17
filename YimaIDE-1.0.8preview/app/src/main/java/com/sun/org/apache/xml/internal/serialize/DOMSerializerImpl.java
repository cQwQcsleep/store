package com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.dom.AbortException;
import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xerces.internal.dom.DOMErrorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMLocatorImpl;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xerces.internal.dom.DOMNormalizer;
import com.sun.org.apache.xerces.internal.dom.DOMStringListImpl;
import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import defpackage.zi0;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.ls.LSSerializerFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Deprecated
public class DOMSerializerImpl implements LSSerializer, DOMConfiguration {
    protected static final short CDATA = 8;
    protected static final short COMMENTS = 32;
    protected static final short DISCARDDEFAULT = 64;
    protected static final short DOM_ELEMENT_CONTENT_WHITESPACE = 1024;
    protected static final short ENTITIES = 4;
    protected static final short INFOSET = 128;
    protected static final short NAMESPACES = 1;
    protected static final short NSDECL = 512;
    protected static final short PRETTY_PRINT = 2048;
    protected static final short SPLITCDATA = 16;
    protected static final short WELLFORMED = 2;
    protected static final short XMLDECL = 256;
    private DOMStringList fRecognizedParameters;
    protected short features;
    private XMLSerializer serializer;
    private XML11Serializer xml11Serializer;
    private DOMErrorHandler fErrorHandler = null;
    private final DOMErrorImpl fError = new DOMErrorImpl();
    private final DOMLocatorImpl fLocator = new DOMLocatorImpl();

    public DOMSerializerImpl() {
        this.features = (short) 0;
        this.features = (short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (((short) (this.features | 1)) | 4)) | 32)) | 8)) | 16)) | 2)) | 512)) | 1024)) | 64)) | 256);
        XMLSerializer xMLSerializer = new XMLSerializer();
        this.serializer = xMLSerializer;
        initSerializer(xMLSerializer);
    }

    private String _getInputEncoding(Node node) {
        Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null) {
            return null;
        }
        try {
            return ownerDocument.getInputEncoding();
        } catch (ThreadDeath | VirtualMachineError e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    private String _getXmlEncoding(Node node) {
        Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null) {
            return null;
        }
        try {
            return ownerDocument.getXmlEncoding();
        } catch (ThreadDeath | VirtualMachineError e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    private String _getXmlVersion(Node node) {
        Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null) {
            return null;
        }
        try {
            return ownerDocument.getXmlVersion();
        } catch (ThreadDeath | VirtualMachineError e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    private void copySettings(XMLSerializer xMLSerializer, XMLSerializer xMLSerializer2) {
        xMLSerializer2.fDOMErrorHandler = this.fErrorHandler;
        xMLSerializer2._format.setEncoding(xMLSerializer._format.getEncoding());
        xMLSerializer2._format.setLineSeparator(xMLSerializer._format.getLineSeparator());
        xMLSerializer2.fDOMFilter = xMLSerializer.fDOMFilter;
    }

    private void initSerializer(XMLSerializer xMLSerializer) {
        xMLSerializer.fNSBinder = new NamespaceSupport();
        xMLSerializer.fLocalNSBinder = new NamespaceSupport();
        xMLSerializer.fSymbolTable = new SymbolTable();
    }

    private void prepareForSerialization(XMLSerializer xMLSerializer, Node node) {
        xMLSerializer.reset();
        short s = this.features;
        xMLSerializer.features = s;
        xMLSerializer.fDOMErrorHandler = this.fErrorHandler;
        boolean zBooleanValue = true;
        xMLSerializer.fNamespaces = (s & 1) != 0;
        xMLSerializer.fNamespacePrefixes = (s & 512) != 0;
        xMLSerializer._format.setIndenting((s & 2048) != 0);
        xMLSerializer._format.setOmitComments((this.features & 32) == 0);
        xMLSerializer._format.setOmitXMLDeclaration((this.features & 256) == 0);
        if ((this.features & 2) != 0) {
            Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
            try {
                java.lang.reflect.Method method = ownerDocument.getClass().getMethod("isXMLVersionChanged()", null);
                if (method != null) {
                    zBooleanValue = ((Boolean) method.invoke(ownerDocument, null)).booleanValue();
                }
            } catch (Exception unused) {
            }
            if (node.getFirstChild() == null) {
                verify(node, zBooleanValue, false);
                return;
            }
            Node parentNode = node;
            while (parentNode != null) {
                verify(parentNode, zBooleanValue, false);
                Node firstChild = parentNode.getFirstChild();
                while (true) {
                    if (firstChild != null) {
                        parentNode = firstChild;
                        break;
                    }
                    firstChild = parentNode.getNextSibling();
                    if (firstChild == null) {
                        parentNode = parentNode.getParentNode();
                        if (node == parentNode) {
                            parentNode = null;
                            break;
                        }
                        firstChild = parentNode.getNextSibling();
                    }
                }
            }
        }
    }

    private void verify(Node node, boolean z, boolean z2) {
        short nodeType = node.getNodeType();
        DOMLocatorImpl dOMLocatorImpl = this.fLocator;
        dOMLocatorImpl.fRelatedNode = node;
        if (nodeType == 1) {
            if (z) {
                if (!((this.features & 1) != 0 ? CoreDocumentImpl.isValidQName(node.getPrefix(), node.getLocalName(), z2) : CoreDocumentImpl.isXMLName(node.getNodeName(), z2)) && this.fErrorHandler != null) {
                    DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Element", node.getNodeName()}), (short) 3, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                }
            }
            NamedNodeMap attributes = node.hasAttributes() ? node.getAttributes() : null;
            if (attributes != null) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Attr attr = (Attr) attributes.item(i);
                    DOMLocatorImpl dOMLocatorImpl2 = this.fLocator;
                    dOMLocatorImpl2.fRelatedNode = attr;
                    DOMNormalizer.isAttrValueWF(this.fErrorHandler, this.fError, dOMLocatorImpl2, attributes, attr, attr.getValue(), z2);
                    if (z && !CoreDocumentImpl.isXMLName(attr.getNodeName(), z2)) {
                        DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Attr", node.getNodeName()}), (short) 3, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                    }
                }
            }
        } else if (nodeType == 3 || nodeType == 4) {
            DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, dOMLocatorImpl, node.getNodeValue(), z2);
        } else if (nodeType != 5) {
            if (nodeType == 7) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) node;
                String target = processingInstruction.getTarget();
                if (z) {
                    if (!(z2 ? XML11Char.isXML11ValidName(target) : XMLChar.isValidName(target))) {
                        DOMNormalizer.reportDOMError(this.fErrorHandler, this.fError, this.fLocator, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME, new Object[]{"Element", node.getNodeName()}), (short) 3, MsgKey.ER_WF_INVALID_CHARACTER_IN_NODE_NAME);
                    }
                }
                DOMNormalizer.isXMLCharWF(this.fErrorHandler, this.fError, this.fLocator, processingInstruction.getData(), z2);
            } else if (nodeType == 8 && (this.features & 32) != 0) {
                DOMNormalizer.isCommentWF(this.fErrorHandler, this.fError, dOMLocatorImpl, ((Comment) node).getData(), z2);
            }
        } else if (z && (this.features & 4) != 0) {
            CoreDocumentImpl.isXMLName(node.getNodeName(), z2);
        }
        this.fLocator.fRelatedNode = null;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String str, Object obj) {
        if (obj == null) {
            return true;
        }
        if (!(obj instanceof Boolean)) {
            str.equalsIgnoreCase("error-handler");
            return obj instanceof DOMErrorHandler;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (str.equalsIgnoreCase("namespaces") || str.equalsIgnoreCase("split-cdata-sections") || str.equalsIgnoreCase("discard-default-content") || str.equalsIgnoreCase("xml-declaration") || str.equalsIgnoreCase("well-formed") || str.equalsIgnoreCase("infoset") || str.equalsIgnoreCase("entities") || str.equalsIgnoreCase("cdata-sections") || str.equalsIgnoreCase("comments") || str.equalsIgnoreCase("format-pretty-print") || str.equalsIgnoreCase("namespace-declarations")) {
            return true;
        }
        if (str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("validate") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("datatype-normalization")) {
            return true ^ zBooleanValue;
        }
        if (str.equalsIgnoreCase("element-content-whitespace") || str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            return zBooleanValue;
        }
        return false;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public DOMConfiguration getDomConfig() {
        return this;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public LSSerializerFilter getFilter() {
        return this.serializer.fDOMFilter;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public String getNewLine() {
        return this.serializer._format.getLineSeparator();
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String str) throws DOMException {
        if (str.equalsIgnoreCase("normalize-characters")) {
            return null;
        }
        if (str.equalsIgnoreCase("comments")) {
            return (this.features & 32) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespaces")) {
            return (this.features & 1) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("xml-declaration")) {
            return (this.features & 256) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("cdata-sections")) {
            return (this.features & 8) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("entities")) {
            return (this.features & 4) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("split-cdata-sections")) {
            return (this.features & 16) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("well-formed")) {
            return (this.features & 2) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespace-declarations")) {
            return (this.features & 512) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("element-content-whitespace") || str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("discard-default-content")) {
            return (this.features & 64) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("format-pretty-print")) {
            return (this.features & 2048) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("infoset")) {
            short s = this.features;
            return ((s & 4) != 0 || (s & 8) != 0 || (s & 1) == 0 || (s & 512) == 0 || (s & 2) == 0 || (s & 32) == 0) ? Boolean.FALSE : Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("validate") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("datatype-normalization")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("error-handler")) {
            return this.fErrorHandler;
        }
        if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER) || str.equalsIgnoreCase("schema-location") || str.equalsIgnoreCase("schema-type")) {
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
            return null;
        }
        zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{str}));
        return null;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        if (this.fRecognizedParameters == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("namespaces");
            arrayList.add("split-cdata-sections");
            arrayList.add("discard-default-content");
            arrayList.add("xml-declaration");
            arrayList.add("canonical-form");
            arrayList.add("validate-if-schema");
            arrayList.add("validate");
            arrayList.add("check-character-normalization");
            arrayList.add("datatype-normalization");
            arrayList.add("format-pretty-print");
            arrayList.add("well-formed");
            arrayList.add("infoset");
            arrayList.add("namespace-declarations");
            arrayList.add("element-content-whitespace");
            arrayList.add("entities");
            arrayList.add("cdata-sections");
            arrayList.add("comments");
            arrayList.add("ignore-unknown-character-denormalizations");
            arrayList.add("error-handler");
            this.fRecognizedParameters = new DOMStringListImpl(arrayList);
        }
        return this.fRecognizedParameters;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public void setFilter(LSSerializerFilter lSSerializerFilter) {
        this.serializer.fDOMFilter = lSSerializerFilter;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public void setNewLine(String str) {
        this.serializer._format.setLineSeparator(str);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String str, Object obj) throws DOMException {
        if (!(obj instanceof Boolean)) {
            if (str.equalsIgnoreCase("error-handler")) {
                if (obj == null || (obj instanceof DOMErrorHandler)) {
                    this.fErrorHandler = (DOMErrorHandler) obj;
                    return;
                } else {
                    zi0.a(17, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
                    return;
                }
            }
            if (str.equalsIgnoreCase(Constants.DOM_RESOURCE_RESOLVER) || str.equalsIgnoreCase("schema-location") || str.equalsIgnoreCase("schema-type") || (str.equalsIgnoreCase("normalize-characters") && obj != null)) {
                zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
                return;
            } else {
                zi0.a(8, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{str}));
                return;
            }
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (str.equalsIgnoreCase("infoset")) {
            if (zBooleanValue) {
                this.features = (short) (((short) (((short) (((short) (((short) (((short) (this.features & (-5))) & (-9))) | 1)) | 512)) | 2)) | 32);
                return;
            }
            return;
        }
        if (str.equalsIgnoreCase("xml-declaration")) {
            short s = this.features;
            this.features = (short) (zBooleanValue ? s | 256 : s & (-257));
            return;
        }
        if (str.equalsIgnoreCase("namespaces")) {
            short s2 = this.features;
            this.features = (short) (zBooleanValue ? s2 | 1 : s2 & (-2));
            this.serializer.fNamespaces = zBooleanValue;
            return;
        }
        if (str.equalsIgnoreCase("split-cdata-sections")) {
            short s3 = this.features;
            this.features = (short) (zBooleanValue ? s3 | 16 : s3 & (-17));
            return;
        }
        if (str.equalsIgnoreCase("discard-default-content")) {
            short s4 = this.features;
            this.features = (short) (zBooleanValue ? s4 | 64 : s4 & (-65));
            return;
        }
        if (str.equalsIgnoreCase("well-formed")) {
            short s5 = this.features;
            this.features = (short) (zBooleanValue ? s5 | 2 : s5 & (-3));
            return;
        }
        if (str.equalsIgnoreCase("entities")) {
            short s6 = this.features;
            this.features = (short) (zBooleanValue ? s6 | 4 : s6 & (-5));
            return;
        }
        if (str.equalsIgnoreCase("cdata-sections")) {
            short s7 = this.features;
            this.features = (short) (zBooleanValue ? s7 | 8 : s7 & (-9));
            return;
        }
        if (str.equalsIgnoreCase("comments")) {
            short s8 = this.features;
            this.features = (short) (zBooleanValue ? s8 | 32 : s8 & (-33));
            return;
        }
        if (str.equalsIgnoreCase("format-pretty-print")) {
            short s9 = this.features;
            this.features = (short) (zBooleanValue ? s9 | 2048 : s9 & (-2049));
            return;
        }
        if (str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("validate") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("datatype-normalization")) {
            if (zBooleanValue) {
                zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
            }
        } else if (str.equalsIgnoreCase("namespace-declarations")) {
            short s10 = this.features;
            this.features = (short) (zBooleanValue ? s10 | 512 : s10 & (-513));
            this.serializer.fNamespacePrefixes = zBooleanValue;
        } else if (!str.equalsIgnoreCase("element-content-whitespace") && !str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_FOUND", new Object[]{str}));
        } else {
            if (zBooleanValue) {
                return;
            }
            zi0.a(9, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "FEATURE_NOT_SUPPORTED", new Object[]{str}));
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public boolean write(Node node, LSOutput lSOutput) throws LSException {
        XMLSerializer xMLSerializer;
        if (node == null) {
            return false;
        }
        String str_getXmlVersion = _getXmlVersion(node);
        if (str_getXmlVersion == null || !str_getXmlVersion.equals(SerializerConstants.XMLVERSION11)) {
            xMLSerializer = this.serializer;
        } else {
            if (this.xml11Serializer == null) {
                XML11Serializer xML11Serializer = new XML11Serializer();
                this.xml11Serializer = xML11Serializer;
                initSerializer(xML11Serializer);
            }
            copySettings(this.serializer, this.xml11Serializer);
            xMLSerializer = this.xml11Serializer;
        }
        String encoding = lSOutput.getEncoding();
        if (encoding == null && (encoding = _getInputEncoding(node)) == null && (encoding = _getXmlEncoding(node)) == null) {
            encoding = "UTF-8";
        }
        try {
            try {
                try {
                    try {
                        prepareForSerialization(xMLSerializer, node);
                        xMLSerializer._format.setEncoding(encoding);
                        OutputStream byteStream = lSOutput.getByteStream();
                        Writer characterStream = lSOutput.getCharacterStream();
                        String systemId = lSOutput.getSystemId();
                        if (characterStream != null) {
                            xMLSerializer.setOutputCharStream(characterStream);
                        } else if (byteStream != null) {
                            xMLSerializer.setOutputByteStream(byteStream);
                        } else {
                            if (systemId == null) {
                                String message = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, MsgKey.ER_NO_OUTPUT_SPECIFIED, null);
                                if (xMLSerializer.fDOMErrorHandler != null) {
                                    DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
                                    dOMErrorImpl.fType = MsgKey.ER_NO_OUTPUT_SPECIFIED;
                                    dOMErrorImpl.fMessage = message;
                                    dOMErrorImpl.fSeverity = (short) 3;
                                    xMLSerializer.fDOMErrorHandler.handleError(dOMErrorImpl);
                                }
                                throw new LSException((short) 82, message);
                            }
                            xMLSerializer.setOutputByteStream(XMLEntityManager.createOutputStream(systemId));
                        }
                        if (node.getNodeType() == 9) {
                            xMLSerializer.serialize((Document) node);
                        } else if (node.getNodeType() == 11) {
                            xMLSerializer.serialize((DocumentFragment) node);
                        } else if (node.getNodeType() == 1) {
                            xMLSerializer.serialize((Element) node);
                        } else {
                            if (node.getNodeType() != 3 && node.getNodeType() != 8 && node.getNodeType() != 5 && node.getNodeType() != 4 && node.getNodeType() != 7) {
                                xMLSerializer.clearDocumentState();
                                return false;
                            }
                            xMLSerializer.serialize(node);
                        }
                        xMLSerializer.clearDocumentState();
                        return true;
                    } catch (LSException e) {
                        throw e;
                    }
                } catch (UnsupportedEncodingException e2) {
                    if (xMLSerializer.fDOMErrorHandler != null) {
                        DOMErrorImpl dOMErrorImpl2 = new DOMErrorImpl();
                        dOMErrorImpl2.fException = e2;
                        dOMErrorImpl2.fType = MsgKey.ER_UNSUPPORTED_ENCODING;
                        dOMErrorImpl2.fMessage = e2.getMessage();
                        dOMErrorImpl2.fSeverity = (short) 3;
                        xMLSerializer.fDOMErrorHandler.handleError(dOMErrorImpl2);
                    }
                    throw new LSException((short) 82, DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, MsgKey.ER_UNSUPPORTED_ENCODING, null));
                } catch (Exception e3) {
                    if (xMLSerializer.fDOMErrorHandler != null) {
                        DOMErrorImpl dOMErrorImpl3 = new DOMErrorImpl();
                        dOMErrorImpl3.fException = e3;
                        dOMErrorImpl3.fMessage = e3.getMessage();
                        dOMErrorImpl3.fSeverity = (short) 2;
                        xMLSerializer.fDOMErrorHandler.handleError(dOMErrorImpl3);
                    }
                    throw ((LSException) DOMUtil.createLSException((short) 82, e3).fillInStackTrace());
                }
            } catch (AbortException unused) {
                xMLSerializer.clearDocumentState();
                return false;
            } catch (RuntimeException e4) {
                throw ((LSException) DOMUtil.createLSException((short) 82, e4).fillInStackTrace());
            }
        } catch (Throwable th) {
            xMLSerializer.clearDocumentState();
            throw th;
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public String writeToString(Node node) throws DOMException, LSException {
        XMLSerializer xMLSerializer;
        String str_getXmlVersion = _getXmlVersion(node);
        if (str_getXmlVersion == null || !str_getXmlVersion.equals(SerializerConstants.XMLVERSION11)) {
            xMLSerializer = this.serializer;
        } else {
            if (this.xml11Serializer == null) {
                XML11Serializer xML11Serializer = new XML11Serializer();
                this.xml11Serializer = xML11Serializer;
                initSerializer(xML11Serializer);
            }
            copySettings(this.serializer, this.xml11Serializer);
            xMLSerializer = this.xml11Serializer;
        }
        StringWriter stringWriter = new StringWriter();
        try {
            try {
                try {
                    prepareForSerialization(xMLSerializer, node);
                    xMLSerializer._format.setEncoding(XMLEntityManager.EncodingInfo.STR_UTF16);
                    xMLSerializer.setOutputCharStream(stringWriter);
                    if (node.getNodeType() == 9) {
                        xMLSerializer.serialize((Document) node);
                    } else if (node.getNodeType() == 11) {
                        xMLSerializer.serialize((DocumentFragment) node);
                    } else if (node.getNodeType() == 1) {
                        xMLSerializer.serialize((Element) node);
                    } else {
                        if (node.getNodeType() != 3 && node.getNodeType() != 8 && node.getNodeType() != 5 && node.getNodeType() != 4 && node.getNodeType() != 7) {
                            String message = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "unable-to-serialize-node", null);
                            if (xMLSerializer.fDOMErrorHandler != null) {
                                DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
                                dOMErrorImpl.fType = "unable-to-serialize-node";
                                dOMErrorImpl.fMessage = message;
                                dOMErrorImpl.fSeverity = (short) 3;
                                xMLSerializer.fDOMErrorHandler.handleError(dOMErrorImpl);
                            }
                            throw new LSException((short) 82, message);
                        }
                        xMLSerializer.serialize(node);
                    }
                    xMLSerializer.clearDocumentState();
                    return stringWriter.toString();
                } catch (AbortException unused) {
                    xMLSerializer.clearDocumentState();
                    return null;
                } catch (RuntimeException e) {
                    throw ((LSException) DOMUtil.createLSException((short) 82, e).fillInStackTrace());
                }
            } catch (IOException e2) {
                throw new DOMException((short) 2, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "STRING_TOO_LONG", new Object[]{e2.getMessage()}));
            } catch (LSException e3) {
                throw e3;
            }
        } catch (Throwable th) {
            xMLSerializer.clearDocumentState();
            throw th;
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public boolean writeToURI(Node node, String str) throws LSException {
        XMLSerializer xMLSerializer;
        if (node == null) {
            return false;
        }
        String str_getXmlVersion = _getXmlVersion(node);
        if (str_getXmlVersion == null || !str_getXmlVersion.equals(SerializerConstants.XMLVERSION11)) {
            xMLSerializer = this.serializer;
        } else {
            if (this.xml11Serializer == null) {
                XML11Serializer xML11Serializer = new XML11Serializer();
                this.xml11Serializer = xML11Serializer;
                initSerializer(xML11Serializer);
            }
            copySettings(this.serializer, this.xml11Serializer);
            xMLSerializer = this.xml11Serializer;
        }
        String str_getInputEncoding = _getInputEncoding(node);
        if (str_getInputEncoding == null && (str_getInputEncoding = _getXmlEncoding(node)) == null) {
            str_getInputEncoding = "UTF-8";
        }
        try {
            try {
                try {
                    prepareForSerialization(xMLSerializer, node);
                    xMLSerializer._format.setEncoding(str_getInputEncoding);
                    xMLSerializer.setOutputByteStream(XMLEntityManager.createOutputStream(str));
                    if (node.getNodeType() == 9) {
                        xMLSerializer.serialize((Document) node);
                    } else if (node.getNodeType() == 11) {
                        xMLSerializer.serialize((DocumentFragment) node);
                    } else if (node.getNodeType() == 1) {
                        xMLSerializer.serialize((Element) node);
                    } else {
                        if (node.getNodeType() != 3 && node.getNodeType() != 8 && node.getNodeType() != 5 && node.getNodeType() != 4 && node.getNodeType() != 7) {
                            xMLSerializer.clearDocumentState();
                            return false;
                        }
                        xMLSerializer.serialize(node);
                    }
                    xMLSerializer.clearDocumentState();
                    return true;
                } catch (AbortException unused) {
                    xMLSerializer.clearDocumentState();
                    return false;
                } catch (LSException e) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw ((LSException) DOMUtil.createLSException((short) 82, e2).fillInStackTrace());
            } catch (Exception e3) {
                if (xMLSerializer.fDOMErrorHandler != null) {
                    DOMErrorImpl dOMErrorImpl = new DOMErrorImpl();
                    dOMErrorImpl.fException = e3;
                    dOMErrorImpl.fMessage = e3.getMessage();
                    dOMErrorImpl.fSeverity = (short) 2;
                    xMLSerializer.fDOMErrorHandler.handleError(dOMErrorImpl);
                }
                throw ((LSException) DOMUtil.createLSException((short) 82, e3).fillInStackTrace());
            }
        } catch (Throwable th) {
            xMLSerializer.clearDocumentState();
            throw th;
        }
    }
}
