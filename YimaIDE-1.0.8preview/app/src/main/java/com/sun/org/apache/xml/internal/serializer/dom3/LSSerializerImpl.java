package com.sun.org.apache.xml.internal.serializer.dom3;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xml.internal.serializer.DOM3Serializer;
import com.sun.org.apache.xml.internal.serializer.Encodings;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import com.sun.org.apache.xml.internal.serializer.Serializer;
import com.sun.org.apache.xml.internal.serializer.ToXMLStream;
import com.sun.org.apache.xml.internal.serializer.utils.MsgKey;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import defpackage.zi0;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkProperty;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMErrorHandler;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.ls.LSSerializerFilter;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class LSSerializerImpl implements DOMConfiguration, LSSerializer {
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
    private static final int IS_STANDALONE = 524288;
    private static final int NAMESPACEDECLS = 512;
    private static final int NAMESPACES = 256;
    private static final int NORMALIZECHARS = 1024;
    private static final int PRETTY_PRINT = 65536;
    private static final int SCHEMAVALIDATE = 8192;
    private static final int SPLITCDATA = 2048;
    private static final int VALIDATE = 4096;
    private static final int WELLFORMED = 16384;
    private static final int XMLDECL = 262144;
    private Properties fDOMConfigProperties;
    private String fEncoding;
    private JdkProperty<Boolean> fIsStandalone;
    private Serializer fXMLSerializer;
    private DOM3Serializer fDOMSerializer = null;
    private LSSerializerFilter fSerializerFilter = null;
    private Node fVisitedNode = null;
    private String fEndOfLine = "\n";
    private DOMErrorHandler fDOMErrorHandler = null;
    private String[] fRecognizedParameters = {"canonical-form", "cdata-sections", "check-character-normalization", "comments", "datatype-normalization", "element-content-whitespace", "entities", "infoset", "namespaces", "namespace-declarations", "split-cdata-sections", "validate", "validate-if-schema", "well-formed", "discard-default-content", "format-pretty-print", "ignore-unknown-character-denormalizations", "xml-declaration", JdkConstants.FQ_IS_STANDALONE, JdkConstants.SP_IS_STANDALONE, "error-handler"};
    protected int fFeatures = (2 | 8) | 314208;

    public LSSerializerImpl() {
        this.fXMLSerializer = null;
        this.fDOMConfigProperties = null;
        this.fDOMConfigProperties = new Properties();
        initializeSerializerProps();
        Properties defaultMethodProperties = OutputPropertiesFactory.getDefaultMethodProperties("xml");
        ToXMLStream toXMLStream = new ToXMLStream(null);
        this.fXMLSerializer = toXMLStream;
        toXMLStream.setOutputFormat(defaultMethodProperties);
        this.fXMLSerializer.setOutputFormat(this.fDOMConfigProperties);
    }

    @Override // org.w3c.dom.DOMConfiguration
    public boolean canSetParameter(String str, Object obj) {
        if (!(obj instanceof Boolean)) {
            return (str.equalsIgnoreCase("error-handler") && obj == null) || (obj instanceof DOMErrorHandler);
        }
        if (str.equalsIgnoreCase("cdata-sections") || str.equalsIgnoreCase("comments") || str.equalsIgnoreCase("entities") || str.equalsIgnoreCase("infoset") || str.equalsIgnoreCase("element-content-whitespace") || str.equalsIgnoreCase("namespaces") || str.equalsIgnoreCase("namespace-declarations") || str.equalsIgnoreCase("split-cdata-sections") || str.equalsIgnoreCase("well-formed") || str.equalsIgnoreCase("discard-default-content") || str.equalsIgnoreCase("format-pretty-print") || str.equalsIgnoreCase("xml-declaration") || str.equalsIgnoreCase(JdkConstants.FQ_IS_STANDALONE) || str.equalsIgnoreCase(JdkConstants.SP_IS_STANDALONE)) {
            return true;
        }
        if (str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("datatype-normalization") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("validate")) {
            return !((Boolean) obj).booleanValue();
        }
        if (str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public DOMConfiguration getDomConfig() {
        return this;
    }

    public DOMErrorHandler getErrorHandler() {
        return this.fDOMErrorHandler;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public LSSerializerFilter getFilter() {
        return this.fSerializerFilter;
    }

    public String getInputEncoding(Node node) {
        if (node == null) {
            return null;
        }
        Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null || !ownerDocument.getImplementation().hasFeature("Core", "3.0")) {
            return null;
        }
        return ownerDocument.getInputEncoding();
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public String getNewLine() {
        return this.fEndOfLine;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public Object getParameter(String str) throws DOMException {
        if (str.equalsIgnoreCase("normalize-characters")) {
            return null;
        }
        if (str.equalsIgnoreCase("comments")) {
            return (this.fFeatures & 8) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("cdata-sections")) {
            return (this.fFeatures & 2) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("entities")) {
            return (this.fFeatures & 64) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespaces")) {
            return (this.fFeatures & 256) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("namespace-declarations")) {
            return (this.fFeatures & 512) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("split-cdata-sections")) {
            return (this.fFeatures & 2048) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("well-formed")) {
            return (this.fFeatures & 16384) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("discard-default-content")) {
            return (this.fFeatures & 32768) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("format-pretty-print")) {
            return (this.fFeatures & 65536) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("xml-declaration")) {
            return (this.fFeatures & 262144) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (JdkProperty.ImplPropMap.ISSTANDALONE.is(str)) {
            return (this.fFeatures & 524288) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("element-content-whitespace")) {
            return (this.fFeatures & 32) != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("check-character-normalization") || str.equalsIgnoreCase("datatype-normalization") || str.equalsIgnoreCase("validate") || str.equalsIgnoreCase("validate-if-schema")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("infoset")) {
            int i = this.fFeatures;
            return ((i & 64) != 0 || (i & 2) != 0 || (i & 32) == 0 || (i & 256) == 0 || (i & 512) == 0 || (i & 16384) == 0 || (i & 8) == 0) ? Boolean.FALSE : Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("error-handler")) {
            return this.fDOMErrorHandler;
        }
        if (!str.equalsIgnoreCase("schema-location") && !str.equalsIgnoreCase("schema-type")) {
            zi0.a(8, Utils.messages.createMessage("FEATURE_NOT_FOUND", new Object[]{str}));
        }
        return null;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public DOMStringList getParameterNames() {
        return new DOMStringListImpl(this.fRecognizedParameters);
    }

    public String getXMLEncoding(Node node) {
        if (node == null) {
            return "UTF-8";
        }
        Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
        return (ownerDocument == null || !ownerDocument.getImplementation().hasFeature("Core", "3.0")) ? "UTF-8" : ownerDocument.getXmlEncoding();
    }

    public String getXMLVersion(Node node) {
        if (node == null) {
            return "1.0";
        }
        Document ownerDocument = node.getNodeType() == 9 ? (Document) node : node.getOwnerDocument();
        if (ownerDocument == null || !ownerDocument.getImplementation().hasFeature("Core", "3.0")) {
            return "1.0";
        }
        try {
            return ownerDocument.getXmlVersion();
        } catch (AbstractMethodError unused) {
            return "1.0";
        }
    }

    public void initializeSerializerProps() {
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}canonical-form", DOMConstants.DOM3_DEFAULT_FALSE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}check-character-normalization", DOMConstants.DOM3_DEFAULT_FALSE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", DOMConstants.DOM3_DEFAULT_FALSE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", DOMConstants.DOM3_DEFAULT_TRUE);
        if ((this.fFeatures & 128) != 0) {
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", DOMConstants.DOM3_DEFAULT_TRUE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", DOMConstants.DOM3_DEFAULT_TRUE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", DOMConstants.DOM3_DEFAULT_TRUE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", DOMConstants.DOM3_DEFAULT_TRUE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", DOMConstants.DOM3_DEFAULT_TRUE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", DOMConstants.DOM3_DEFAULT_FALSE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", DOMConstants.DOM3_DEFAULT_FALSE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", DOMConstants.DOM3_DEFAULT_FALSE);
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", DOMConstants.DOM3_DEFAULT_FALSE);
        }
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate", DOMConstants.DOM3_DEFAULT_FALSE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", DOMConstants.DOM3_DEFAULT_FALSE);
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("indent", DOMConstants.DOM3_DEFAULT_FALSE);
        this.fDOMConfigProperties.setProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, Integer.toString(4));
        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", DOMConstants.DOM3_DEFAULT_TRUE);
        this.fDOMConfigProperties.setProperty("omit-xml-declaration", "no");
        JdkProperty<Boolean> jdkProperty = new JdkProperty<>(JdkProperty.ImplPropMap.ISSTANDALONE, Boolean.class, Boolean.FALSE, JdkProperty.State.DEFAULT);
        this.fIsStandalone = jdkProperty;
        if (!jdkProperty.getValue().booleanValue()) {
            this.fDOMConfigProperties.setProperty(DOMConstants.NS_IS_STANDALONE, DOMConstants.DOM3_DEFAULT_FALSE);
        } else {
            this.fFeatures |= 524288;
            this.fDOMConfigProperties.setProperty(DOMConstants.NS_IS_STANDALONE, DOMConstants.DOM3_EXPLICIT_TRUE);
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public void setFilter(LSSerializerFilter lSSerializerFilter) {
        this.fSerializerFilter = lSSerializerFilter;
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public void setNewLine(String str) {
        if (str == null) {
            str = this.fEndOfLine;
        }
        this.fEndOfLine = str;
    }

    @Override // org.w3c.dom.DOMConfiguration
    public void setParameter(String str, Object obj) throws DOMException {
        String str2;
        if (!(obj instanceof Boolean)) {
            if (str.equalsIgnoreCase("error-handler")) {
                if (obj == null || (obj instanceof DOMErrorHandler)) {
                    this.fDOMErrorHandler = (DOMErrorHandler) obj;
                    return;
                } else {
                    zi0.a(17, Utils.messages.createMessage(MsgKey.ER_TYPE_MISMATCH_ERR, new Object[]{str}));
                    return;
                }
            }
            if (str.equalsIgnoreCase("schema-location") || str.equalsIgnoreCase("schema-type") || (str.equalsIgnoreCase("normalize-characters") && obj != null)) {
                zi0.a(9, Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[]{str}));
                return;
            } else {
                zi0.a(8, Utils.messages.createMessage("FEATURE_NOT_FOUND", new Object[]{str}));
                return;
            }
        }
        Boolean bool = (Boolean) obj;
        boolean zBooleanValue = bool.booleanValue();
        boolean zEqualsIgnoreCase = str.equalsIgnoreCase("comments");
        String str3 = DOMConstants.DOM3_EXPLICIT_TRUE;
        if (zEqualsIgnoreCase) {
            int i = this.fFeatures;
            this.fFeatures = zBooleanValue ? i | 8 : i & (-9);
            Properties properties = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("cdata-sections")) {
            int i2 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i2 | 2 : i2 & (-3);
            Properties properties2 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties2.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties2.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("entities")) {
            int i3 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i3 | 64 : i3 & (-65);
            Properties properties3 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties3.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties3.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("namespaces")) {
            int i4 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i4 | 256 : i4 & (-257);
            Properties properties4 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties4.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties4.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("namespace-declarations")) {
            int i5 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i5 | 512 : i5 & (-513);
            Properties properties5 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties5.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties5.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("split-cdata-sections")) {
            int i6 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i6 | 2048 : i6 & (-2049);
            Properties properties6 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties6.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties6.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}split-cdata-sections", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("well-formed")) {
            int i7 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i7 | 16384 : i7 & (-16385);
            Properties properties7 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties7.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties7.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("discard-default-content")) {
            int i8 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i8 | 32768 : i8 & (-32769);
            Properties properties8 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties8.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties8.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}discard-default-content", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("format-pretty-print")) {
            int i9 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i9 | 65536 : i9 & (-65537);
            Properties properties9 = this.fDOMConfigProperties;
            if (!zBooleanValue) {
                properties9.setProperty("indent", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            } else {
                properties9.setProperty("indent", DOMConstants.DOM3_EXPLICIT_TRUE);
                this.fDOMConfigProperties.setProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, Integer.toString(4));
                return;
            }
        }
        if (str.equalsIgnoreCase("xml-declaration")) {
            int i10 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i10 | 262144 : i10 & (-262145);
            Properties properties10 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties10.setProperty("omit-xml-declaration", "no");
                return;
            } else {
                properties10.setProperty("omit-xml-declaration", JdkConstants.JDK_YES);
                return;
            }
        }
        if (JdkProperty.ImplPropMap.ISSTANDALONE.is(str)) {
            this.fIsStandalone.setValue(str, bool, JdkProperty.State.APIPROPERTY);
            boolean zBooleanValue2 = this.fIsStandalone.getValue().booleanValue();
            int i11 = this.fFeatures;
            this.fFeatures = zBooleanValue2 ? 524288 | i11 : (-524289) & i11;
            Properties properties11 = this.fDOMConfigProperties;
            if (!this.fIsStandalone.getValue().booleanValue()) {
                str3 = DOMConstants.DOM3_EXPLICIT_FALSE;
            }
            properties11.setProperty(DOMConstants.NS_IS_STANDALONE, str3);
            return;
        }
        if (str.equalsIgnoreCase("element-content-whitespace")) {
            int i12 = this.fFeatures;
            this.fFeatures = zBooleanValue ? i12 | 32 : i12 & (-33);
            Properties properties12 = this.fDOMConfigProperties;
            if (zBooleanValue) {
                properties12.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                properties12.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", DOMConstants.DOM3_EXPLICIT_FALSE);
                return;
            }
        }
        if (str.equalsIgnoreCase("ignore-unknown-character-denormalizations")) {
            if (zBooleanValue) {
                this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}ignore-unknown-character-denormalizations", DOMConstants.DOM3_EXPLICIT_TRUE);
                return;
            } else {
                zi0.a(9, Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[]{str}));
                return;
            }
        }
        if (str.equalsIgnoreCase("canonical-form") || str.equalsIgnoreCase("validate-if-schema") || str.equalsIgnoreCase("validate")) {
            str2 = "validate";
        } else {
            str2 = "validate";
            if (!str.equalsIgnoreCase("check-character-normalization") && !str.equalsIgnoreCase("datatype-normalization")) {
                if (!str.equalsIgnoreCase("infoset")) {
                    if (str.equalsIgnoreCase("normalize-characters")) {
                        zi0.a(9, Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[]{str}));
                        return;
                    }
                    return;
                } else {
                    if (zBooleanValue) {
                        this.fFeatures = (this.fFeatures & (-8275)) | 17192;
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespaces", DOMConstants.DOM3_EXPLICIT_TRUE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}namespace-declarations", DOMConstants.DOM3_EXPLICIT_TRUE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}comments", DOMConstants.DOM3_EXPLICIT_TRUE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}element-content-whitespace", DOMConstants.DOM3_EXPLICIT_TRUE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}well-formed", DOMConstants.DOM3_EXPLICIT_TRUE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}entities", DOMConstants.DOM3_EXPLICIT_FALSE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}cdata-sections", DOMConstants.DOM3_EXPLICIT_FALSE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", DOMConstants.DOM3_EXPLICIT_FALSE);
                        this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", DOMConstants.DOM3_EXPLICIT_FALSE);
                        return;
                    }
                    return;
                }
            }
        }
        if (zBooleanValue) {
            zi0.a(9, Utils.messages.createMessage("FEATURE_NOT_SUPPORTED", new Object[]{str}));
            return;
        }
        if (str.equalsIgnoreCase("canonical-form")) {
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}canonical-form", DOMConstants.DOM3_EXPLICIT_FALSE);
            return;
        }
        if (str.equalsIgnoreCase("validate-if-schema")) {
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate-if-schema", DOMConstants.DOM3_EXPLICIT_FALSE);
            return;
        }
        if (str.equalsIgnoreCase(str2)) {
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}validate", DOMConstants.DOM3_EXPLICIT_FALSE);
        } else if (str.equalsIgnoreCase("validate-if-schema")) {
            this.fDOMConfigProperties.setProperty("check-character-normalizationcheck-character-normalization", DOMConstants.DOM3_EXPLICIT_FALSE);
        } else if (str.equalsIgnoreCase("datatype-normalization")) {
            this.fDOMConfigProperties.setProperty("{http://www.w3.org/TR/DOM-Level-3-LS}datatype-normalization", DOMConstants.DOM3_EXPLICIT_FALSE);
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public boolean write(Node node, LSOutput lSOutput) throws LSException {
        OutputStream outputStream;
        if (lSOutput == null) {
            String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_NO_OUTPUT_SPECIFIED, null);
            DOMErrorHandler dOMErrorHandler = this.fDOMErrorHandler;
            if (dOMErrorHandler != null) {
                dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_NO_OUTPUT_SPECIFIED));
            }
            throw new LSException((short) 82, strCreateMessage);
        }
        if (node == null) {
            return false;
        }
        Serializer serializer = this.fXMLSerializer;
        serializer.reset();
        if (node != this.fVisitedNode) {
            String xMLVersion = getXMLVersion(node);
            String encoding = lSOutput.getEncoding();
            this.fEncoding = encoding;
            if (encoding == null) {
                String inputEncoding = getInputEncoding(node);
                this.fEncoding = inputEncoding;
                if (inputEncoding == null) {
                    inputEncoding = getXMLEncoding(node) == null ? "UTF-8" : getXMLEncoding(node);
                }
                this.fEncoding = inputEncoding;
            }
            if (!Encodings.isRecognizedEncoding(this.fEncoding)) {
                String strCreateMessage2 = Utils.messages.createMessage(MsgKey.ER_UNSUPPORTED_ENCODING, null);
                DOMErrorHandler dOMErrorHandler2 = this.fDOMErrorHandler;
                if (dOMErrorHandler2 != null) {
                    dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, strCreateMessage2, MsgKey.ER_UNSUPPORTED_ENCODING));
                }
                throw new LSException((short) 82, strCreateMessage2);
            }
            serializer.getOutputFormat().setProperty("version", xMLVersion);
            this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xerces-2j}xml-version", xMLVersion);
            this.fDOMConfigProperties.setProperty("encoding", this.fEncoding);
            if ((node.getNodeType() != 9 || node.getNodeType() != 1 || node.getNodeType() != 6) && (this.fFeatures & 262144) != 0) {
                this.fDOMConfigProperties.setProperty("omit-xml-declaration", DOMConstants.DOM3_DEFAULT_FALSE);
            }
            this.fVisitedNode = node;
        }
        this.fXMLSerializer.setOutputFormat(this.fDOMConfigProperties);
        try {
            Writer characterStream = lSOutput.getCharacterStream();
            if (characterStream == null) {
                OutputStream byteStream = lSOutput.getByteStream();
                if (byteStream == null) {
                    String systemId = lSOutput.getSystemId();
                    if (systemId == null) {
                        String strCreateMessage3 = Utils.messages.createMessage(MsgKey.ER_NO_OUTPUT_SPECIFIED, null);
                        DOMErrorHandler dOMErrorHandler3 = this.fDOMErrorHandler;
                        if (dOMErrorHandler3 != null) {
                            dOMErrorHandler3.handleError(new DOMErrorImpl((short) 3, strCreateMessage3, MsgKey.ER_NO_OUTPUT_SPECIFIED));
                        }
                        throw new LSException((short) 82, strCreateMessage3);
                    }
                    URL url = new URL(SystemIDResolver.getAbsoluteURI(systemId));
                    String protocol = url.getProtocol();
                    String host = url.getHost();
                    if (protocol.equalsIgnoreCase("file") && (host == null || host.length() == 0 || host.equals("localhost"))) {
                        outputStream = new FileOutputStream(new File(url.getPath()));
                    } else {
                        URLConnection uRLConnectionOpenConnection = url.openConnection();
                        uRLConnectionOpenConnection.setDoInput(false);
                        uRLConnectionOpenConnection.setDoOutput(true);
                        uRLConnectionOpenConnection.setUseCaches(false);
                        uRLConnectionOpenConnection.setAllowUserInteraction(false);
                        if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
                            ((HttpURLConnection) uRLConnectionOpenConnection).setRequestMethod("PUT");
                        }
                        outputStream = uRLConnectionOpenConnection.getOutputStream();
                    }
                    serializer.setWriter(new OutputStreamWriter(outputStream));
                } else {
                    serializer.setWriter(new OutputStreamWriter(byteStream, this.fEncoding));
                }
            } else {
                serializer.setWriter(characterStream);
            }
            if (this.fDOMSerializer == null) {
                this.fDOMSerializer = (DOM3Serializer) serializer.asDOM3Serializer();
            }
            DOMErrorHandler dOMErrorHandler4 = this.fDOMErrorHandler;
            if (dOMErrorHandler4 != null) {
                this.fDOMSerializer.setErrorHandler(dOMErrorHandler4);
            }
            LSSerializerFilter lSSerializerFilter = this.fSerializerFilter;
            if (lSSerializerFilter != null) {
                this.fDOMSerializer.setNodeFilter(lSSerializerFilter);
            }
            this.fDOMSerializer.setNewLine(this.fEndOfLine);
            this.fDOMSerializer.serializeDOM3(node);
            return true;
        } catch (UnsupportedEncodingException e) {
            String strCreateMessage4 = Utils.messages.createMessage(MsgKey.ER_UNSUPPORTED_ENCODING, null);
            DOMErrorHandler dOMErrorHandler5 = this.fDOMErrorHandler;
            if (dOMErrorHandler5 != null) {
                dOMErrorHandler5.handleError(new DOMErrorImpl((short) 3, strCreateMessage4, MsgKey.ER_UNSUPPORTED_ENCODING, e));
            }
            throw new LSException((short) 82, e.getMessage());
        } catch (LSException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            e3.printStackTrace();
            throw new LSException((short) 82, e3.getMessage());
        } catch (Exception e4) {
            DOMErrorHandler dOMErrorHandler6 = this.fDOMErrorHandler;
            if (dOMErrorHandler6 != null) {
                dOMErrorHandler6.handleError(new DOMErrorImpl((short) 3, e4.getMessage(), null, e4));
            }
            e4.printStackTrace();
            throw new LSException((short) 82, e4.toString());
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public String writeToString(Node node) throws DOMException, LSException {
        if (node == null) {
            return null;
        }
        Serializer serializer = this.fXMLSerializer;
        serializer.reset();
        if (node != this.fVisitedNode) {
            String xMLVersion = getXMLVersion(node);
            serializer.getOutputFormat().setProperty("version", xMLVersion);
            this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xerces-2j}xml-version", xMLVersion);
            this.fDOMConfigProperties.setProperty("encoding", XMLEntityManager.EncodingInfo.STR_UTF16);
            if ((node.getNodeType() != 9 || node.getNodeType() != 1 || node.getNodeType() != 6) && (this.fFeatures & 262144) != 0) {
                this.fDOMConfigProperties.setProperty("omit-xml-declaration", DOMConstants.DOM3_DEFAULT_FALSE);
            }
            this.fVisitedNode = node;
        }
        this.fXMLSerializer.setOutputFormat(this.fDOMConfigProperties);
        StringWriter stringWriter = new StringWriter();
        try {
            serializer.setWriter(stringWriter);
            if (this.fDOMSerializer == null) {
                this.fDOMSerializer = (DOM3Serializer) serializer.asDOM3Serializer();
            }
            DOMErrorHandler dOMErrorHandler = this.fDOMErrorHandler;
            if (dOMErrorHandler != null) {
                this.fDOMSerializer.setErrorHandler(dOMErrorHandler);
            }
            LSSerializerFilter lSSerializerFilter = this.fSerializerFilter;
            if (lSSerializerFilter != null) {
                this.fDOMSerializer.setNodeFilter(lSSerializerFilter);
            }
            this.fDOMSerializer.setNewLine(this.fEndOfLine);
            this.fDOMSerializer.serializeDOM3(node);
            return stringWriter.toString();
        } catch (LSException e) {
            throw e;
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            throw new LSException((short) 82, e2.toString());
        } catch (Exception e3) {
            DOMErrorHandler dOMErrorHandler2 = this.fDOMErrorHandler;
            if (dOMErrorHandler2 != null) {
                dOMErrorHandler2.handleError(new DOMErrorImpl((short) 3, e3.getMessage(), null, e3));
            }
            e3.printStackTrace();
            throw new LSException((short) 82, e3.toString());
        }
    }

    @Override // org.w3c.dom.ls.LSSerializer
    public boolean writeToURI(Node node, String str) throws LSException {
        OutputStream outputStream;
        if (node == null) {
            return false;
        }
        Serializer serializer = this.fXMLSerializer;
        serializer.reset();
        if (node != this.fVisitedNode) {
            String xMLVersion = getXMLVersion(node);
            String inputEncoding = getInputEncoding(node);
            this.fEncoding = inputEncoding;
            if (inputEncoding == null) {
                if (inputEncoding == null) {
                    inputEncoding = getXMLEncoding(node) == null ? "UTF-8" : getXMLEncoding(node);
                }
                this.fEncoding = inputEncoding;
            }
            serializer.getOutputFormat().setProperty("version", xMLVersion);
            this.fDOMConfigProperties.setProperty("{http://xml.apache.org/xerces-2j}xml-version", xMLVersion);
            this.fDOMConfigProperties.setProperty("encoding", this.fEncoding);
            if ((node.getNodeType() != 9 || node.getNodeType() != 1 || node.getNodeType() != 6) && (this.fFeatures & 262144) != 0) {
                this.fDOMConfigProperties.setProperty("omit-xml-declaration", DOMConstants.DOM3_DEFAULT_FALSE);
            }
            this.fVisitedNode = node;
        }
        this.fXMLSerializer.setOutputFormat(this.fDOMConfigProperties);
        try {
            if (str == null) {
                String strCreateMessage = Utils.messages.createMessage(MsgKey.ER_NO_OUTPUT_SPECIFIED, null);
                DOMErrorHandler dOMErrorHandler = this.fDOMErrorHandler;
                if (dOMErrorHandler != null) {
                    dOMErrorHandler.handleError(new DOMErrorImpl((short) 3, strCreateMessage, MsgKey.ER_NO_OUTPUT_SPECIFIED));
                }
                throw new LSException((short) 82, strCreateMessage);
            }
            URL url = new URL(SystemIDResolver.getAbsoluteURI(str));
            String protocol = url.getProtocol();
            String host = url.getHost();
            if (protocol.equalsIgnoreCase("file") && (host == null || host.length() == 0 || host.equals("localhost"))) {
                outputStream = new FileOutputStream(new File(url.getPath()));
            } else {
                URLConnection uRLConnectionOpenConnection = url.openConnection();
                uRLConnectionOpenConnection.setDoInput(false);
                uRLConnectionOpenConnection.setDoOutput(true);
                uRLConnectionOpenConnection.setUseCaches(false);
                uRLConnectionOpenConnection.setAllowUserInteraction(false);
                if (uRLConnectionOpenConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnectionOpenConnection).setRequestMethod("PUT");
                }
                outputStream = uRLConnectionOpenConnection.getOutputStream();
            }
            serializer.setWriter(new OutputStreamWriter(outputStream, this.fEncoding));
            if (this.fDOMSerializer == null) {
                this.fDOMSerializer = (DOM3Serializer) serializer.asDOM3Serializer();
            }
            DOMErrorHandler dOMErrorHandler2 = this.fDOMErrorHandler;
            if (dOMErrorHandler2 != null) {
                this.fDOMSerializer.setErrorHandler(dOMErrorHandler2);
            }
            LSSerializerFilter lSSerializerFilter = this.fSerializerFilter;
            if (lSSerializerFilter != null) {
                this.fDOMSerializer.setNodeFilter(lSSerializerFilter);
            }
            this.fDOMSerializer.setNewLine(this.fEndOfLine);
            this.fDOMSerializer.serializeDOM3(node);
            return true;
        } catch (LSException e) {
            throw e;
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            throw new LSException((short) 82, e2.toString());
        } catch (Exception e3) {
            DOMErrorHandler dOMErrorHandler3 = this.fDOMErrorHandler;
            if (dOMErrorHandler3 != null) {
                dOMErrorHandler3.handleError(new DOMErrorImpl((short) 3, e3.getMessage(), null, e3));
            }
            e3.printStackTrace();
            throw new LSException((short) 82, e3.toString());
        }
    }
}
