package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaLoader;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSDDescription;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSGrammarBucket;
import com.sun.org.apache.xerces.internal.impl.xs.XSGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSMessageFormatter;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelGroupImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSNotationDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import com.sun.org.apache.xerces.internal.impl.xs.opti.ElementImpl;
import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOM;
import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOMParser;
import com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaParsingConfig;
import com.sun.org.apache.xerces.internal.impl.xs.util.SimpleLocator;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSInputSource;
import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import com.sun.org.apache.xerces.internal.parsers.XML11Configuration;
import com.sun.org.apache.xerces.internal.util.DOMInputSource;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.DefaultErrorHandler;
import com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper;
import com.sun.org.apache.xerces.internal.util.SAXInputSource;
import com.sun.org.apache.xerces.internal.util.StAXInputSource;
import com.sun.org.apache.xerces.internal.util.StAXLocationWrapper;
import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityManager;
import com.sun.org.apache.xerces.internal.utils.XMLSecurityPropertyManager;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLSchemaDescription;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLErrorHandler;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParseException;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSAttributeUse;
import com.sun.org.apache.xerces.internal.xs.XSElementDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSModelGroup;
import com.sun.org.apache.xerces.internal.xs.XSModelGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSNamedMap;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSParticle;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTerm;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import javax.xml.catalog.CatalogFeatures;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSDHandler {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    static final int ATTRIBUTEGROUP_TYPE = 2;
    static final int ATTRIBUTE_TYPE = 1;
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final boolean DEBUG_NODE_POOL = false;
    protected static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
    static final int ELEMENT_TYPE = 3;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_HANDLER = "http://apache.org/xml/properties/internal/error-handler";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    static final int GROUP_TYPE = 4;
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    static final int IDENTITYCONSTRAINT_TYPE = 5;
    private static final int INC_KEYREF_STACK_AMOUNT = 2;
    private static final int INC_STACK_SIZE = 10;
    private static final int INIT_KEYREF_STACK = 2;
    private static final int INIT_STACK_SIZE = 30;
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String LOCALE = "http://apache.org/xml/properties/locale";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    static final int NOTATION_TYPE = 6;
    public static final String REDEF_IDENTIFIER = "_fn3dktizrknc9pi";
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String STRING_INTERNING = "http://xml.org/sax/features/string-interning";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    static final int TYPEDECL_TYPE = 7;
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String XMLSCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    private String fAccessExternalDTD;
    private String fAccessExternalSchema;
    private int[] fAllContext;
    private List<String> fAllTNSs;
    XML11Configuration fAnnotationValidator;
    private XSAttributeChecker fAttributeChecker;
    XSDAttributeGroupTraverser fAttributeGroupTraverser;
    XSDAttributeTraverser fAttributeTraverser;
    private String fCatalogFile;
    XSDComplexTypeTraverser fComplexTypeTraverser;
    SchemaDVFactory fDVFactory;
    protected XSDeclarationPool fDeclPool;
    private String fDefer;
    private Map<XSDocumentInfo, List<XSDocumentInfo>> fDependencyMap;
    private Map<Element, String> fDoc2SystemId;
    private Map<Element, XSDocumentInfo> fDoc2XSDocumentMap;
    XSDElementTraverser fElementTraverser;
    private XMLEntityManager fEntityManager;
    private XMLErrorHandler fErrorHandler;
    private XMLErrorReporter fErrorReporter;
    SymbolHash fGlobalAttrDecls;
    SymbolHash fGlobalAttrGrpDecls;
    SymbolHash fGlobalElemDecls;
    SymbolHash fGlobalGroupDecls;
    SymbolHash fGlobalIDConstraintDecls;
    SymbolHash fGlobalNotationDecls;
    SymbolHash fGlobalTypeDecls;
    private XSGrammarBucket fGrammarBucket;
    XSAnnotationGrammarPool fGrammarBucketAdapter;
    private XMLGrammarPool fGrammarPool;
    XSDGroupTraverser fGroupTraverser;
    Map<Node, String> fHiddenNodes;
    private boolean fHonourAllSchemaLocations;
    private Map<String, List<String>> fImportMap;
    private XSElementDecl[] fKeyrefElems;
    private String[][] fKeyrefNamespaceContext;
    private int fKeyrefStackPos;
    XSDKeyrefTraverser fKeyrefTraverser;
    private Element[] fKeyrefs;
    private XSDocumentInfo[] fKeyrefsMapXSDocumentInfo;
    private boolean fLastSchemaWasDuplicate;
    private String[][] fLocalElemNamespaceContext;
    private int fLocalElemStackPos;
    private Element[] fLocalElementDecl;
    private XSDocumentInfo[] fLocalElementDecl_schema;
    private Locale fLocale;
    private Map<String, XMLSchemaLoader.LocationArray> fLocationPairs;
    boolean fNamespaceGrowth;
    XSDNotationTraverser fNotationTraverser;
    private boolean fOverrideDefaultParser;
    private XSObject[] fParent;
    private XSParticleDecl[] fParticle;
    private String fPrefer;
    private Map<Element, SchemaNamespaceSupport> fRedefine2NSSupport;
    private Map<Element, XSDocumentInfo> fRedefine2XSDMap;
    private final Map<String, String> fRedefinedRestrictedAttributeGroupRegistry;
    private final Map<String, String> fRedefinedRestrictedGroupRegistry;
    private List<String> fReportedTNS;
    private String fResolve;
    private XSDocumentInfo fRoot;
    private XSDDescription fSchemaGrammarDescription;
    SchemaDOMParser fSchemaParser;
    protected XMLSecurityManager fSecurityManager;
    private XMLSecurityPropertyManager fSecurityPropertyMgr;
    XSDSimpleTypeTraverser fSimpleTypeTraverser;
    StAXSchemaParser fStAXSchemaParser;
    private SymbolTable fSymbolTable;
    boolean fTolerateDuplicates;
    private Map<XSDKey, Element> fTraversed;
    XSDUniqueOrKeyTraverser fUniqueOrKeyTraverser;
    private Map<String, Element> fUnparsedAttributeGroupRegistry;
    private Map<String, XSDocumentInfo> fUnparsedAttributeGroupRegistrySub;
    private Map<String, Element> fUnparsedAttributeRegistry;
    private Map<String, XSDocumentInfo> fUnparsedAttributeRegistrySub;
    private Map<String, Element> fUnparsedElementRegistry;
    private Map<String, XSDocumentInfo> fUnparsedElementRegistrySub;
    private Map<String, Element> fUnparsedGroupRegistry;
    private Map<String, XSDocumentInfo> fUnparsedGroupRegistrySub;
    private Map<String, Element> fUnparsedIdentityConstraintRegistry;
    private Map<String, XSDocumentInfo> fUnparsedIdentityConstraintRegistrySub;
    private Map<String, Element> fUnparsedNotationRegistry;
    private Map<String, XSDocumentInfo> fUnparsedNotationRegistrySub;
    private Map<String, XSDocumentInfo>[] fUnparsedRegistriesExt;
    private Map<String, Element> fUnparsedTypeRegistry;
    private Map<String, XSDocumentInfo> fUnparsedTypeRegistrySub;
    private boolean fUseCatalog;
    private boolean fValidateAnnotations;
    XSDWildcardTraverser fWildCardTraverser;
    SchemaContentHandler fXSContentHandler;
    private boolean registryEmpty;
    private SimpleLocator xl;
    private static final String[][] NS_ERROR_CODES = {new String[]{"src-include.2.1", "src-include.2.1"}, new String[]{"src-redefine.3.1", "src-redefine.3.1"}, new String[]{"src-import.3.1", "src-import.3.2"}, null, new String[]{"TargetNamespace.1", "TargetNamespace.2"}, new String[]{"TargetNamespace.1", "TargetNamespace.2"}, new String[]{"TargetNamespace.1", "TargetNamespace.2"}, new String[]{"TargetNamespace.1", "TargetNamespace.2"}};
    private static final String[] ELE_ERROR_CODES = {"src-include.1", "src-redefine.2", "src-import.2", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4", "schema_reference.4"};
    private static final String[] COMP_TYPE = {null, "attribute declaration", "attribute group", "element declaration", "group", "identity constraint", "notation", "type definition"};
    private static final String[] CIRCULAR_CODES = {"Internal-Error", "Internal-Error", "src-attribute_group.3", "e-props-correct.6", "mg-props-correct.2", "Internal-Error", "Internal-Error", "st-props-correct.2"};

    public static final class SAX2XNIUtil extends ErrorHandlerWrapper {
        private SAX2XNIUtil() {
        }

        public static XMLParseException createXMLParseException0(SAXParseException sAXParseException) {
            return ErrorHandlerWrapper.createXMLParseException(sAXParseException);
        }

        public static XNIException createXNIException0(SAXException sAXException) {
            return ErrorHandlerWrapper.createXNIException(sAXException);
        }
    }

    public static class XSAnnotationGrammarPool implements XMLGrammarPool {
        private XSGrammarBucket fGrammarBucket;
        private Grammar[] fInitialGrammarSet;

        private XSAnnotationGrammarPool() {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void cacheGrammars(String str, Grammar[] grammarArr) {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void clear() {
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void lockPool() {
        }

        public void refreshGrammars(XSGrammarBucket xSGrammarBucket) {
            this.fGrammarBucket = xSGrammarBucket;
            this.fInitialGrammarSet = null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription) {
            SchemaGrammar grammar;
            if (xMLGrammarDescription.getGrammarType() != "http://www.w3.org/2001/XMLSchema") {
                return null;
            }
            String targetNamespace = ((XMLSchemaDescription) xMLGrammarDescription).getTargetNamespace();
            XSGrammarBucket xSGrammarBucket = this.fGrammarBucket;
            if (xSGrammarBucket != null && (grammar = xSGrammarBucket.getGrammar(targetNamespace)) != null) {
                return grammar;
            }
            if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(targetNamespace)) {
                return SchemaGrammar.Schema4Annotations.INSTANCE;
            }
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public Grammar[] retrieveInitialGrammarSet(String str) {
            if (str != "http://www.w3.org/2001/XMLSchema") {
                return new Grammar[0];
            }
            if (this.fInitialGrammarSet == null) {
                XSGrammarBucket xSGrammarBucket = this.fGrammarBucket;
                if (xSGrammarBucket == null) {
                    this.fInitialGrammarSet = new Grammar[]{SchemaGrammar.Schema4Annotations.INSTANCE};
                } else {
                    SchemaGrammar[] grammars = xSGrammarBucket.getGrammars();
                    for (SchemaGrammar schemaGrammar : grammars) {
                        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(schemaGrammar.getTargetNamespace())) {
                            this.fInitialGrammarSet = grammars;
                            return grammars;
                        }
                    }
                    int length = grammars.length;
                    Grammar[] grammarArr = new Grammar[length + 1];
                    System.arraycopy(grammars, 0, grammarArr, 0, grammars.length);
                    grammarArr[length] = SchemaGrammar.Schema4Annotations.INSTANCE;
                    this.fInitialGrammarSet = grammarArr;
                }
            }
            return this.fInitialGrammarSet;
        }

        @Override // com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool
        public void unlockPool() {
        }
    }

    public static class XSDKey {
        String referNS;
        short referType;
        String systemId;

        public XSDKey(String str, short s, String str2) {
            this.systemId = str;
            this.referType = s;
            this.referNS = str2;
        }

        public boolean equals(Object obj) {
            String str;
            if (!(obj instanceof XSDKey)) {
                return false;
            }
            XSDKey xSDKey = (XSDKey) obj;
            return this.referNS == xSDKey.referNS && (str = this.systemId) != null && str.equals(xSDKey.systemId);
        }

        public int hashCode() {
            String str = this.referNS;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }
    }

    public XSDHandler() {
        this.fDeclPool = null;
        this.fSecurityManager = null;
        this.registryEmpty = true;
        this.fUnparsedAttributeRegistry = new HashMap();
        this.fUnparsedAttributeGroupRegistry = new HashMap();
        this.fUnparsedElementRegistry = new HashMap();
        this.fUnparsedGroupRegistry = new HashMap();
        this.fUnparsedIdentityConstraintRegistry = new HashMap();
        this.fUnparsedNotationRegistry = new HashMap();
        this.fUnparsedTypeRegistry = new HashMap();
        this.fUnparsedAttributeRegistrySub = new HashMap();
        this.fUnparsedAttributeGroupRegistrySub = new HashMap();
        this.fUnparsedElementRegistrySub = new HashMap();
        this.fUnparsedGroupRegistrySub = new HashMap();
        this.fUnparsedIdentityConstraintRegistrySub = new HashMap();
        this.fUnparsedNotationRegistrySub = new HashMap();
        this.fUnparsedTypeRegistrySub = new HashMap();
        this.fUnparsedRegistriesExt = new HashMap[]{null, null, null, null, null, null, null, null};
        this.fDependencyMap = new HashMap();
        this.fImportMap = new HashMap();
        this.fAllTNSs = new ArrayList();
        this.fLocationPairs = null;
        this.fHiddenNodes = null;
        this.fTraversed = new HashMap();
        this.fDoc2SystemId = new HashMap();
        this.fRoot = null;
        this.fDoc2XSDocumentMap = new HashMap();
        this.fRedefine2XSDMap = null;
        this.fRedefine2NSSupport = null;
        this.fRedefinedRestrictedAttributeGroupRegistry = new HashMap();
        this.fRedefinedRestrictedGroupRegistry = new HashMap();
        this.fValidateAnnotations = false;
        this.fHonourAllSchemaLocations = false;
        this.fNamespaceGrowth = false;
        this.fTolerateDuplicates = false;
        this.fSecurityPropertyMgr = null;
        this.fUseCatalog = true;
        this.fLocalElemStackPos = 0;
        this.fParticle = new XSParticleDecl[30];
        this.fLocalElementDecl = new Element[30];
        this.fLocalElementDecl_schema = new XSDocumentInfo[30];
        this.fAllContext = new int[30];
        this.fParent = new XSObject[30];
        this.fLocalElemNamespaceContext = (String[][]) Array.newInstance((Class<?>) String.class, 30, 1);
        this.fKeyrefStackPos = 0;
        this.fKeyrefs = new Element[2];
        this.fKeyrefsMapXSDocumentInfo = new XSDocumentInfo[2];
        this.fKeyrefElems = new XSElementDecl[2];
        this.fKeyrefNamespaceContext = (String[][]) Array.newInstance((Class<?>) String.class, 2, 1);
        this.fGlobalAttrDecls = new SymbolHash(12);
        this.fGlobalAttrGrpDecls = new SymbolHash(5);
        this.fGlobalElemDecls = new SymbolHash(25);
        this.fGlobalGroupDecls = new SymbolHash(5);
        this.fGlobalNotationDecls = new SymbolHash(1);
        this.fGlobalIDConstraintDecls = new SymbolHash(3);
        this.fGlobalTypeDecls = new SymbolHash(25);
        this.fReportedTNS = null;
        this.xl = new SimpleLocator();
        this.fHiddenNodes = new HashMap();
        this.fSchemaParser = new SchemaDOMParser(new SchemaParsingConfig());
    }

    private void addGlobalAttributeDecls(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        XSNamedMap components = schemaGrammar.getComponents((short) 1);
        int length = components.getLength();
        for (int i = 0; i < length; i++) {
            XSAttributeDecl xSAttributeDecl = (XSAttributeDecl) components.item(i);
            XSAttributeDecl globalAttributeDecl = schemaGrammar2.getGlobalAttributeDecl(xSAttributeDecl.getName());
            if (globalAttributeDecl == null) {
                schemaGrammar2.addGlobalAttributeDecl(xSAttributeDecl);
            } else if (globalAttributeDecl != xSAttributeDecl && !this.fTolerateDuplicates) {
                reportSharingError(xSAttributeDecl.getNamespace(), xSAttributeDecl.getName());
            }
        }
        ObjectList componentsExt = schemaGrammar.getComponentsExt((short) 1);
        int length2 = componentsExt.getLength();
        for (int i2 = 0; i2 < length2; i2 += 2) {
            String str = (String) componentsExt.item(i2);
            int iIndexOf = str.indexOf(44);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            XSAttributeDecl xSAttributeDecl2 = (XSAttributeDecl) componentsExt.item(i2 + 1);
            if (schemaGrammar2.getGlobalAttributeDecl(strSubstring2, strSubstring) == null) {
                schemaGrammar2.addGlobalAttributeDecl(xSAttributeDecl2, strSubstring);
            }
        }
    }

    private void addGlobalAttributeGroupDecls(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        XSNamedMap components = schemaGrammar.getComponents((short) 5);
        int length = components.getLength();
        for (int i = 0; i < length; i++) {
            XSAttributeGroupDecl xSAttributeGroupDecl = (XSAttributeGroupDecl) components.item(i);
            XSAttributeGroupDecl globalAttributeGroupDecl = schemaGrammar2.getGlobalAttributeGroupDecl(xSAttributeGroupDecl.getName());
            if (globalAttributeGroupDecl == null) {
                schemaGrammar2.addGlobalAttributeGroupDecl(xSAttributeGroupDecl);
            } else if (globalAttributeGroupDecl != xSAttributeGroupDecl && !this.fTolerateDuplicates) {
                reportSharingError(xSAttributeGroupDecl.getNamespace(), xSAttributeGroupDecl.getName());
            }
        }
        ObjectList componentsExt = schemaGrammar.getComponentsExt((short) 5);
        int length2 = componentsExt.getLength();
        for (int i2 = 0; i2 < length2; i2 += 2) {
            String str = (String) componentsExt.item(i2);
            int iIndexOf = str.indexOf(44);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            XSAttributeGroupDecl xSAttributeGroupDecl2 = (XSAttributeGroupDecl) componentsExt.item(i2 + 1);
            if (schemaGrammar2.getGlobalAttributeGroupDecl(strSubstring2, strSubstring) == null) {
                schemaGrammar2.addGlobalAttributeGroupDecl(xSAttributeGroupDecl2, strSubstring);
            }
        }
    }

    private void addGlobalComponent(XSObject xSObject, XSDDescription xSDDescription) {
        xSDDescription.setNamespace(xSObject.getNamespace());
        SchemaGrammar schemaGrammar = getSchemaGrammar(xSDDescription);
        short type = xSObject.getType();
        String name = xSObject.getName();
        if (type == 1) {
            XSAttributeDecl xSAttributeDecl = (XSAttributeDecl) xSObject;
            if (xSAttributeDecl.getScope() == 1) {
                if (schemaGrammar.getGlobalAttributeDecl(name) == null) {
                    schemaGrammar.addGlobalAttributeDecl(xSAttributeDecl);
                }
                if (schemaGrammar.getGlobalAttributeDecl(name, "") == null) {
                    schemaGrammar.addGlobalAttributeDecl(xSAttributeDecl, "");
                    return;
                }
                return;
            }
            return;
        }
        if (type == 2) {
            XSElementDecl xSElementDecl = (XSElementDecl) xSObject;
            if (xSElementDecl.getScope() == 1) {
                schemaGrammar.addGlobalElementDeclAll(xSElementDecl);
                if (schemaGrammar.getGlobalElementDecl(name) == null) {
                    schemaGrammar.addGlobalElementDecl(xSElementDecl);
                }
                if (schemaGrammar.getGlobalElementDecl(name, "") == null) {
                    schemaGrammar.addGlobalElementDecl(xSElementDecl, "");
                    return;
                }
                return;
            }
            return;
        }
        if (type == 3) {
            XSTypeDefinition xSTypeDefinition = (XSTypeDefinition) xSObject;
            if (xSTypeDefinition.getAnonymous()) {
                return;
            }
            if (schemaGrammar.getGlobalTypeDecl(name) == null) {
                schemaGrammar.addGlobalTypeDecl(xSTypeDefinition);
            }
            if (schemaGrammar.getGlobalTypeDecl(name, "") == null) {
                schemaGrammar.addGlobalTypeDecl(xSTypeDefinition, "");
                return;
            }
            return;
        }
        if (type == 5) {
            if (schemaGrammar.getGlobalAttributeDecl(name) == null) {
                schemaGrammar.addGlobalAttributeGroupDecl((XSAttributeGroupDecl) xSObject);
            }
            if (schemaGrammar.getGlobalAttributeDecl(name, "") == null) {
                schemaGrammar.addGlobalAttributeGroupDecl((XSAttributeGroupDecl) xSObject, "");
                return;
            }
            return;
        }
        if (type == 6) {
            if (schemaGrammar.getGlobalGroupDecl(name) == null) {
                schemaGrammar.addGlobalGroupDecl((XSGroupDecl) xSObject);
            }
            if (schemaGrammar.getGlobalGroupDecl(name, "") == null) {
                schemaGrammar.addGlobalGroupDecl((XSGroupDecl) xSObject, "");
                return;
            }
            return;
        }
        if (type != 11) {
            return;
        }
        if (schemaGrammar.getGlobalNotationDecl(name) == null) {
            schemaGrammar.addGlobalNotationDecl((XSNotationDecl) xSObject);
        }
        if (schemaGrammar.getGlobalNotationDecl(name, "") == null) {
            schemaGrammar.addGlobalNotationDecl((XSNotationDecl) xSObject, "");
        }
    }

    private void addGlobalComponents(List<XSObject> list, Map<String, List<String>> map) {
        XSDDescription xSDDescription = new XSDDescription();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addGlobalComponent(list.get(i), xSDDescription);
        }
        updateImportDependencies(map);
    }

    private void addGlobalElementDecls(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        XSNamedMap components = schemaGrammar.getComponents((short) 2);
        int length = components.getLength();
        for (int i = 0; i < length; i++) {
            XSElementDecl xSElementDecl = (XSElementDecl) components.item(i);
            if (schemaGrammar2.getGlobalElementDecl(xSElementDecl.getName()) == null) {
                schemaGrammar2.addGlobalElementDecl(xSElementDecl);
            }
        }
        ObjectList componentsExt = schemaGrammar.getComponentsExt((short) 2);
        int length2 = componentsExt.getLength();
        for (int i2 = 0; i2 < length2; i2 += 2) {
            String str = (String) componentsExt.item(i2);
            int iIndexOf = str.indexOf(44);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            XSElementDecl xSElementDecl2 = (XSElementDecl) componentsExt.item(i2 + 1);
            if (schemaGrammar2.getGlobalElementDecl(strSubstring2, strSubstring) == null) {
                schemaGrammar2.addGlobalElementDecl(xSElementDecl2, strSubstring);
            }
        }
    }

    private void addGlobalGroupDecls(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        XSNamedMap components = schemaGrammar.getComponents((short) 6);
        int length = components.getLength();
        for (int i = 0; i < length; i++) {
            XSGroupDecl xSGroupDecl = (XSGroupDecl) components.item(i);
            XSGroupDecl globalGroupDecl = schemaGrammar2.getGlobalGroupDecl(xSGroupDecl.getName());
            if (globalGroupDecl == null) {
                schemaGrammar2.addGlobalGroupDecl(xSGroupDecl);
            } else if (xSGroupDecl != globalGroupDecl && !this.fTolerateDuplicates) {
                reportSharingError(xSGroupDecl.getNamespace(), xSGroupDecl.getName());
            }
        }
        ObjectList componentsExt = schemaGrammar.getComponentsExt((short) 6);
        int length2 = componentsExt.getLength();
        for (int i2 = 0; i2 < length2; i2 += 2) {
            String str = (String) componentsExt.item(i2);
            int iIndexOf = str.indexOf(44);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            XSGroupDecl xSGroupDecl2 = (XSGroupDecl) componentsExt.item(i2 + 1);
            if (schemaGrammar2.getGlobalGroupDecl(strSubstring2, strSubstring) == null) {
                schemaGrammar2.addGlobalGroupDecl(xSGroupDecl2, strSubstring);
            }
        }
    }

    private void addGlobalNotationDecls(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        XSNamedMap components = schemaGrammar.getComponents((short) 11);
        int length = components.getLength();
        for (int i = 0; i < length; i++) {
            XSNotationDecl xSNotationDecl = (XSNotationDecl) components.item(i);
            XSNotationDecl globalNotationDecl = schemaGrammar2.getGlobalNotationDecl(xSNotationDecl.getName());
            if (globalNotationDecl == null) {
                schemaGrammar2.addGlobalNotationDecl(xSNotationDecl);
            } else if (globalNotationDecl != xSNotationDecl && !this.fTolerateDuplicates) {
                reportSharingError(xSNotationDecl.getNamespace(), xSNotationDecl.getName());
            }
        }
        ObjectList componentsExt = schemaGrammar.getComponentsExt((short) 11);
        int length2 = componentsExt.getLength();
        for (int i2 = 0; i2 < length2; i2 += 2) {
            String str = (String) componentsExt.item(i2);
            int iIndexOf = str.indexOf(44);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            XSNotationDecl xSNotationDecl2 = (XSNotationDecl) componentsExt.item(i2 + 1);
            if (schemaGrammar2.getGlobalNotationDecl(strSubstring2, strSubstring) == null) {
                schemaGrammar2.addGlobalNotationDecl(xSNotationDecl2, strSubstring);
            }
        }
    }

    private void addGlobalTypeDecls(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        XSNamedMap components = schemaGrammar.getComponents((short) 3);
        int length = components.getLength();
        for (int i = 0; i < length; i++) {
            XSTypeDefinition xSTypeDefinition = (XSTypeDefinition) components.item(i);
            XSTypeDefinition globalTypeDecl = schemaGrammar2.getGlobalTypeDecl(xSTypeDefinition.getName());
            if (globalTypeDecl == null) {
                schemaGrammar2.addGlobalTypeDecl(xSTypeDefinition);
            } else if (globalTypeDecl != xSTypeDefinition && !this.fTolerateDuplicates) {
                reportSharingError(xSTypeDefinition.getNamespace(), xSTypeDefinition.getName());
            }
        }
        ObjectList componentsExt = schemaGrammar.getComponentsExt((short) 3);
        int length2 = componentsExt.getLength();
        for (int i2 = 0; i2 < length2; i2 += 2) {
            String str = (String) componentsExt.item(i2);
            int iIndexOf = str.indexOf(44);
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1, str.length());
            XSTypeDefinition xSTypeDefinition2 = (XSTypeDefinition) componentsExt.item(i2 + 1);
            if (schemaGrammar2.getGlobalTypeDecl(strSubstring2, strSubstring) == null) {
                schemaGrammar2.addGlobalTypeDecl(xSTypeDefinition2, strSubstring);
            }
        }
    }

    private void addGrammarComponents(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        if (schemaGrammar2 == null) {
            createGrammarFrom(schemaGrammar);
            return;
        }
        if (schemaGrammar2.isImmutable()) {
            schemaGrammar2 = createGrammarFrom(schemaGrammar2);
        }
        addNewGrammarLocations(schemaGrammar, schemaGrammar2);
        addNewImportedGrammars(schemaGrammar, schemaGrammar2);
        addNewGrammarComponents(schemaGrammar, schemaGrammar2);
    }

    private void addGrammars(List<SchemaGrammar> list) {
        int size = list.size();
        XSDDescription xSDDescription = new XSDDescription();
        for (int i = 0; i < size; i++) {
            SchemaGrammar schemaGrammar = list.get(i);
            xSDDescription.setNamespace(schemaGrammar.getTargetNamespace());
            SchemaGrammar schemaGrammarFindGrammar = findGrammar(xSDDescription, this.fNamespaceGrowth);
            if (schemaGrammar != schemaGrammarFindGrammar) {
                addGrammarComponents(schemaGrammar, schemaGrammarFindGrammar);
            }
        }
    }

    private void addImportList(SchemaGrammar schemaGrammar, List<SchemaGrammar> list, List<String> list2) {
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            SchemaGrammar grammar = this.fGrammarBucket.getGrammar(list2.get(i));
            if (grammar != null) {
                list.add(grammar);
            }
        }
    }

    private void addNamespaceDependency(String str, String str2, List<String> list) {
        String strNull2EmptyString = null2EmptyString(str);
        String strNull2EmptyString2 = null2EmptyString(str2);
        if (strNull2EmptyString.equals(strNull2EmptyString2) || list.contains(strNull2EmptyString2)) {
            return;
        }
        list.add(strNull2EmptyString2);
    }

    private void addNewGrammarComponents(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        schemaGrammar2.resetComponents();
        addGlobalElementDecls(schemaGrammar, schemaGrammar2);
        addGlobalAttributeDecls(schemaGrammar, schemaGrammar2);
        addGlobalAttributeGroupDecls(schemaGrammar, schemaGrammar2);
        addGlobalGroupDecls(schemaGrammar, schemaGrammar2);
        addGlobalTypeDecls(schemaGrammar, schemaGrammar2);
        addGlobalNotationDecls(schemaGrammar, schemaGrammar2);
    }

    private void addNewGrammarLocations(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        StringList documentLocations = schemaGrammar.getDocumentLocations();
        int size = documentLocations.size();
        StringList documentLocations2 = schemaGrammar2.getDocumentLocations();
        for (int i = 0; i < size; i++) {
            String strItem = documentLocations.item(i);
            if (!documentLocations2.contains(strItem)) {
                schemaGrammar2.addDocument(null, strItem);
            }
        }
    }

    private void addNewImportedGrammars(SchemaGrammar schemaGrammar, SchemaGrammar schemaGrammar2) {
        ArrayList<SchemaGrammar> arrayList = (ArrayList) schemaGrammar.getImportedGrammars();
        if (arrayList != null) {
            ArrayList arrayList2 = (ArrayList) schemaGrammar2.getImportedGrammars();
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                schemaGrammar2.setImportedGrammars(arrayList2);
            }
            for (SchemaGrammar schemaGrammar3 : arrayList) {
                SchemaGrammar grammar = this.fGrammarBucket.getGrammar(schemaGrammar3.getTargetNamespace());
                if (grammar != null) {
                    schemaGrammar3 = grammar;
                }
                if (!containedImportedGrammar(arrayList2, schemaGrammar3)) {
                    arrayList2.add(schemaGrammar3);
                }
            }
        }
    }

    private void addRelatedAttribute(XSAttributeDeclaration xSAttributeDeclaration, List<XSObject> list, String str, Map<String, List<String>> map) {
        if (xSAttributeDeclaration.getScope() != 1) {
            expandRelatedAttributeComponents(xSAttributeDeclaration, list, str, map);
        } else {
            if (list.contains(xSAttributeDeclaration)) {
                return;
            }
            addNamespaceDependency(str, xSAttributeDeclaration.getNamespace(), findDependentNamespaces(str, map));
            list.add(xSAttributeDeclaration);
        }
    }

    private void addRelatedElement(XSElementDeclaration xSElementDeclaration, List<XSObject> list, String str, Map<String, List<String>> map) {
        if (xSElementDeclaration.getScope() != 1) {
            expandRelatedElementComponents(xSElementDeclaration, list, str, map);
        } else {
            if (list.contains(xSElementDeclaration)) {
                return;
            }
            addNamespaceDependency(str, xSElementDeclaration.getNamespace(), findDependentNamespaces(str, map));
            list.add(xSElementDeclaration);
        }
    }

    private void addRelatedType(XSTypeDefinition xSTypeDefinition, List<XSObject> list, String str, Map<String, List<String>> map) {
        if (xSTypeDefinition.getAnonymous()) {
            expandRelatedTypeComponents(xSTypeDefinition, list, str, map);
        } else {
            if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(xSTypeDefinition.getNamespace()) || list.contains(xSTypeDefinition)) {
                return;
            }
            addNamespaceDependency(str, xSTypeDefinition.getNamespace(), findDependentNamespaces(str, map));
            list.add(xSTypeDefinition);
        }
    }

    private boolean canAddComponent(XSObject xSObject, XSDDescription xSDDescription) {
        xSDDescription.setNamespace(xSObject.getNamespace());
        SchemaGrammar schemaGrammarFindGrammar = findGrammar(xSDDescription, false);
        if (schemaGrammarFindGrammar == null) {
            return true;
        }
        if (schemaGrammarFindGrammar.isImmutable()) {
            return false;
        }
        short type = xSObject.getType();
        String name = xSObject.getName();
        if (type != 1) {
            if (type != 2) {
                if (type != 3) {
                    if (type != 5) {
                        if (type != 6) {
                            if (type != 11 || schemaGrammarFindGrammar.getGlobalNotationDecl(name) == xSObject) {
                                return true;
                            }
                        } else if (schemaGrammarFindGrammar.getGlobalGroupDecl(name) == xSObject) {
                            return true;
                        }
                    } else if (schemaGrammarFindGrammar.getGlobalAttributeDecl(name) == xSObject) {
                        return true;
                    }
                } else if (schemaGrammarFindGrammar.getGlobalTypeDecl(name) == xSObject) {
                    return true;
                }
            } else if (schemaGrammarFindGrammar.getGlobalElementDecl(name) == xSObject) {
                return true;
            }
        } else if (schemaGrammarFindGrammar.getGlobalAttributeDecl(name) == xSObject) {
            return true;
        }
        return false;
    }

    private boolean canAddComponents(List<XSObject> list) {
        int size = list.size();
        XSDDescription xSDDescription = new XSDDescription();
        for (int i = 0; i < size; i++) {
            if (!canAddComponent(list.get(i), xSDDescription)) {
                return false;
            }
        }
        return true;
    }

    private int changeRedefineGroup(String str, String str2, String str3, Element element, XSDocumentInfo xSDocumentInfo) {
        XSDHandler xSDHandler;
        String str4;
        String str5;
        String str6;
        XSDocumentInfo xSDocumentInfo2;
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        int iChangeRedefineGroup = 0;
        while (firstChildElement != null) {
            if (DOMUtil.getLocalName(firstChildElement).equals(str2)) {
                xSDHandler = this;
                str4 = str;
                str5 = str2;
                str6 = str3;
                xSDocumentInfo2 = xSDocumentInfo;
                String str7 = SchemaSymbols.ATT_REF;
                String attribute = firstChildElement.getAttribute(str7);
                if (attribute.length() != 0 && str4.equals(xSDHandler.findQName(attribute, xSDocumentInfo2))) {
                    String str8 = XMLSymbols.EMPTY_STRING;
                    int iIndexOf = attribute.indexOf(":");
                    if (iIndexOf > 0) {
                        firstChildElement.setAttribute(str7, attribute.substring(0, iIndexOf) + ":" + str6);
                    } else {
                        firstChildElement.setAttribute(str7, str6);
                    }
                    iChangeRedefineGroup++;
                    if (str5.equals(SchemaSymbols.ELT_GROUP)) {
                        String attribute2 = firstChildElement.getAttribute(SchemaSymbols.ATT_MINOCCURS);
                        String attribute3 = firstChildElement.getAttribute(SchemaSymbols.ATT_MAXOCCURS);
                        if ((attribute3.length() != 0 && !attribute3.equals("1")) || (attribute2.length() != 0 && !attribute2.equals("1"))) {
                            xSDHandler.reportSchemaError("src-redefine.6.1.2", new Object[]{attribute}, firstChildElement);
                        }
                    }
                }
            } else {
                xSDHandler = this;
                str4 = str;
                str5 = str2;
                str6 = str3;
                xSDocumentInfo2 = xSDocumentInfo;
                iChangeRedefineGroup += xSDHandler.changeRedefineGroup(str4, str5, str6, firstChildElement, xSDocumentInfo2);
            }
            firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            this = xSDHandler;
            str = str4;
            str2 = str5;
            str3 = str6;
            xSDocumentInfo = xSDocumentInfo2;
        }
        return iChangeRedefineGroup;
    }

    private boolean containedImportedGrammar(List<SchemaGrammar> list, SchemaGrammar schemaGrammar) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (null2EmptyString(list.get(i).getTargetNamespace()).equals(null2EmptyString(schemaGrammar.getTargetNamespace()))) {
                return true;
            }
        }
        return false;
    }

    private void createAnnotationValidator() {
        this.fAnnotationValidator = new XML11Configuration();
        this.fGrammarBucketAdapter = new XSAnnotationGrammarPool();
        this.fAnnotationValidator.setFeature(VALIDATION, true);
        this.fAnnotationValidator.setFeature(XMLSCHEMA_VALIDATION, true);
        this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/internal/grammar-pool", this.fGrammarBucketAdapter);
        XML11Configuration xML11Configuration = this.fAnnotationValidator;
        XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
        if (xMLSecurityManager == null) {
            xMLSecurityManager = new XMLSecurityManager(true);
        }
        xML11Configuration.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
        this.fAnnotationValidator.setProperty("jdk.xml.xmlSecurityPropertyManager", this.fSecurityPropertyMgr);
        XML11Configuration xML11Configuration2 = this.fAnnotationValidator;
        Object defaultErrorHandler = this.fErrorHandler;
        if (defaultErrorHandler == null) {
            defaultErrorHandler = new DefaultErrorHandler();
        }
        xML11Configuration2.setProperty("http://apache.org/xml/properties/internal/error-handler", defaultErrorHandler);
        this.fAnnotationValidator.setProperty("http://apache.org/xml/properties/locale", this.fLocale);
        this.fAnnotationValidator.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", this.fUseCatalog);
        this.fAnnotationValidator.setProperty(JdkXmlUtils.CATALOG_FILES, this.fCatalogFile);
        this.fAnnotationValidator.setProperty(JdkXmlUtils.CATALOG_DEFER, this.fDefer);
        this.fAnnotationValidator.setProperty(JdkXmlUtils.CATALOG_PREFER, this.fPrefer);
        this.fAnnotationValidator.setProperty(JdkXmlUtils.CATALOG_RESOLVE, this.fResolve);
    }

    private SchemaGrammar createGrammarFrom(SchemaGrammar schemaGrammar) {
        SchemaGrammar schemaGrammar2 = new SchemaGrammar(schemaGrammar);
        this.fGrammarBucket.putGrammar(schemaGrammar2);
        updateImportListWith(schemaGrammar2);
        updateImportListFor(schemaGrammar2);
        return schemaGrammar2;
    }

    private void createTraversers() {
        this.fAttributeChecker = new XSAttributeChecker(this);
        this.fAttributeGroupTraverser = new XSDAttributeGroupTraverser(this, this.fAttributeChecker);
        this.fAttributeTraverser = new XSDAttributeTraverser(this, this.fAttributeChecker);
        this.fComplexTypeTraverser = new XSDComplexTypeTraverser(this, this.fAttributeChecker);
        this.fElementTraverser = new XSDElementTraverser(this, this.fAttributeChecker);
        this.fGroupTraverser = new XSDGroupTraverser(this, this.fAttributeChecker);
        this.fKeyrefTraverser = new XSDKeyrefTraverser(this, this.fAttributeChecker);
        this.fNotationTraverser = new XSDNotationTraverser(this, this.fAttributeChecker);
        this.fSimpleTypeTraverser = new XSDSimpleTypeTraverser(this, this.fAttributeChecker);
        this.fUniqueOrKeyTraverser = new XSDUniqueOrKeyTraverser(this, this.fAttributeChecker);
        this.fWildCardTraverser = new XSDWildcardTraverser(this, this.fAttributeChecker);
    }

    private String doc2SystemId(Element element) {
        String documentURI = element.getOwnerDocument() instanceof SchemaDOM ? ((SchemaDOM) element.getOwnerDocument()).getDocumentURI() : null;
        return documentURI != null ? documentURI : this.fDoc2SystemId.get(element);
    }

    private String emptyString2Null(String str) {
        if (str == XMLSymbols.EMPTY_STRING) {
            return null;
        }
        return str;
    }

    private boolean existingGrammars(List<SchemaGrammar> list) {
        int size = list.size();
        XSDDescription xSDDescription = new XSDDescription();
        for (int i = 0; i < size; i++) {
            xSDDescription.setNamespace(list.get(i).getTargetNamespace());
            if (findGrammar(xSDDescription, false) != null) {
                return true;
            }
        }
        return false;
    }

    private List<XSObject> expandComponents(XSObject[] xSObjectArr, Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < xSObjectArr.length; i++) {
            if (!arrayList.contains(xSObjectArr[i])) {
                arrayList.add(xSObjectArr[i]);
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            expandRelatedComponents(arrayList.get(i2), arrayList, map);
        }
        return arrayList;
    }

    private List<SchemaGrammar> expandGrammars(SchemaGrammar[] schemaGrammarArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < schemaGrammarArr.length; i++) {
            if (!arrayList.contains(schemaGrammarArr[i])) {
                arrayList.add(schemaGrammarArr[i]);
            }
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            List<SchemaGrammar> importedGrammars = ((SchemaGrammar) arrayList.get(i2)).getImportedGrammars();
            if (importedGrammars != null) {
                for (int size = importedGrammars.size() - 1; size >= 0; size--) {
                    SchemaGrammar schemaGrammar = importedGrammars.get(size);
                    if (!arrayList.contains(schemaGrammar)) {
                        arrayList.add(schemaGrammar);
                    }
                }
            }
        }
        return arrayList;
    }

    private void expandImportList(String str, List<String> list) {
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(str);
        if (grammar != null) {
            List<SchemaGrammar> importedGrammars = grammar.getImportedGrammars();
            if (importedGrammars != null) {
                updateImportList(grammar, importedGrammars, list);
                return;
            }
            ArrayList arrayList = new ArrayList();
            addImportList(grammar, arrayList, list);
            grammar.setImportedGrammars(arrayList);
        }
    }

    private void expandRelatedAttributeComponents(XSAttributeDeclaration xSAttributeDeclaration, List<XSObject> list, String str, Map<String, List<String>> map) {
        addRelatedType(xSAttributeDeclaration.getTypeDefinition(), list, str, map);
    }

    private void expandRelatedAttributeGroupComponents(XSAttributeGroupDefinition xSAttributeGroupDefinition, List<XSObject> list, String str, Map<String, List<String>> map) {
        expandRelatedAttributeUsesComponents(xSAttributeGroupDefinition.getAttributeUses(), list, str, map);
    }

    private void expandRelatedAttributeUseComponents(XSAttributeUse xSAttributeUse, List<XSObject> list, String str, Map<String, List<String>> map) {
        addRelatedAttribute(xSAttributeUse.getAttrDeclaration(), list, str, map);
    }

    private void expandRelatedAttributeUsesComponents(XSObjectList xSObjectList, List<XSObject> list, String str, Map<String, List<String>> map) {
        int size = xSObjectList == null ? 0 : xSObjectList.size();
        for (int i = 0; i < size; i++) {
            expandRelatedAttributeUseComponents((XSAttributeUse) xSObjectList.item(i), list, str, map);
        }
    }

    private void expandRelatedComplexTypeComponents(XSComplexTypeDecl xSComplexTypeDecl, List<XSObject> list, String str, Map<String, List<String>> map) {
        addRelatedType(xSComplexTypeDecl.getBaseType(), list, str, map);
        expandRelatedAttributeUsesComponents(xSComplexTypeDecl.getAttributeUses(), list, str, map);
        XSParticle particle = xSComplexTypeDecl.getParticle();
        if (particle != null) {
            expandRelatedParticleComponents(particle, list, str, map);
        }
    }

    private void expandRelatedComponents(XSObject xSObject, List<XSObject> list, Map<String, List<String>> map) {
        short type = xSObject.getType();
        if (type == 1) {
            expandRelatedAttributeComponents((XSAttributeDeclaration) xSObject, list, xSObject.getNamespace(), map);
            return;
        }
        if (type != 2) {
            if (type == 3) {
                expandRelatedTypeComponents((XSTypeDefinition) xSObject, list, xSObject.getNamespace(), map);
                return;
            } else {
                if (type != 5) {
                    if (type != 6) {
                        return;
                    }
                    expandRelatedModelGroupDefinitionComponents((XSModelGroupDefinition) xSObject, list, xSObject.getNamespace(), map);
                    return;
                }
                expandRelatedAttributeGroupComponents((XSAttributeGroupDefinition) xSObject, list, xSObject.getNamespace(), map);
            }
        }
        expandRelatedElementComponents((XSElementDeclaration) xSObject, list, xSObject.getNamespace(), map);
    }

    private void expandRelatedElementComponents(XSElementDeclaration xSElementDeclaration, List<XSObject> list, String str, Map<String, List<String>> map) {
        addRelatedType(xSElementDeclaration.getTypeDefinition(), list, str, map);
        XSElementDeclaration substitutionGroupAffiliation = xSElementDeclaration.getSubstitutionGroupAffiliation();
        if (substitutionGroupAffiliation != null) {
            addRelatedElement(substitutionGroupAffiliation, list, str, map);
        }
    }

    private void expandRelatedModelGroupComponents(XSModelGroup xSModelGroup, List<XSObject> list, String str, Map<String, List<String>> map) {
        XSObjectList particles = xSModelGroup.getParticles();
        int length = particles == null ? 0 : particles.getLength();
        for (int i = 0; i < length; i++) {
            expandRelatedParticleComponents((XSParticle) particles.item(i), list, str, map);
        }
    }

    private void expandRelatedModelGroupDefinitionComponents(XSModelGroupDefinition xSModelGroupDefinition, List<XSObject> list, String str, Map<String, List<String>> map) {
        expandRelatedModelGroupComponents(xSModelGroupDefinition.getModelGroup(), list, str, map);
    }

    private void expandRelatedParticleComponents(XSParticle xSParticle, List<XSObject> list, String str, Map<String, List<String>> map) {
        XSTerm term = xSParticle.getTerm();
        short type = term.getType();
        if (type == 2) {
            addRelatedElement((XSElementDeclaration) term, list, str, map);
        } else {
            if (type != 7) {
                return;
            }
            expandRelatedModelGroupComponents((XSModelGroup) term, list, str, map);
        }
    }

    private void expandRelatedSimpleTypeComponents(XSSimpleTypeDefinition xSSimpleTypeDefinition, List<XSObject> list, String str, Map<String, List<String>> map) {
        XSTypeDefinition baseType = xSSimpleTypeDefinition.getBaseType();
        if (baseType != null) {
            addRelatedType(baseType, list, str, map);
        }
        XSSimpleTypeDefinition itemType = xSSimpleTypeDefinition.getItemType();
        if (itemType != null) {
            addRelatedType(itemType, list, str, map);
        }
        XSSimpleTypeDefinition primitiveType = xSSimpleTypeDefinition.getPrimitiveType();
        if (primitiveType != null) {
            addRelatedType(primitiveType, list, str, map);
        }
        XSObjectList memberTypes = xSSimpleTypeDefinition.getMemberTypes();
        if (memberTypes.size() > 0) {
            for (int i = 0; i < memberTypes.size(); i++) {
                addRelatedType((XSTypeDefinition) memberTypes.item(i), list, str, map);
            }
        }
    }

    private void expandRelatedTypeComponents(XSTypeDefinition xSTypeDefinition, List<XSObject> list, String str, Map<String, List<String>> map) {
        if (xSTypeDefinition instanceof XSComplexTypeDecl) {
            expandRelatedComplexTypeComponents((XSComplexTypeDecl) xSTypeDefinition, list, str, map);
        } else if (xSTypeDefinition instanceof XSSimpleTypeDecl) {
            expandRelatedSimpleTypeComponents((XSSimpleTypeDefinition) xSTypeDefinition, list, str, map);
        }
    }

    private List<String> findDependentNamespaces(String str, Map<String, List<String>> map) {
        String strNull2EmptyString = null2EmptyString(str);
        List<String> fromMap = getFromMap(map, strNull2EmptyString);
        if (fromMap != null) {
            return fromMap;
        }
        ArrayList arrayList = new ArrayList();
        map.put(strNull2EmptyString, arrayList);
        return arrayList;
    }

    private String findQName(String str, XSDocumentInfo xSDocumentInfo) {
        SchemaNamespaceSupport schemaNamespaceSupport = xSDocumentInfo.fNamespaceSupport;
        int iIndexOf = str.indexOf(58);
        String str2 = XMLSymbols.EMPTY_STRING;
        String strSubstring = iIndexOf > 0 ? str.substring(0, iIndexOf) : str2;
        String uri = schemaNamespaceSupport.getURI(this.fSymbolTable.addSymbol(strSubstring));
        if (iIndexOf != 0) {
            str = str.substring(iIndexOf + 1);
        }
        if (strSubstring == str2 && uri == null && xSDocumentInfo.fIsChameleonSchema) {
            uri = xSDocumentInfo.fTargetNamespace;
        }
        if (uri == null) {
            return ",".concat(str);
        }
        return uri + "," + str;
    }

    private XSDocumentInfo findXSDocumentForDecl(XSDocumentInfo xSDocumentInfo, Element element, XSDocumentInfo xSDocumentInfo2) {
        if (xSDocumentInfo2 == null) {
            return null;
        }
        return xSDocumentInfo2;
    }

    private XSDocumentInfo getDocInfoFromMap(Map<String, XSDocumentInfo> map, String str) {
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    private Element getElementFromMap(Map<String, Element> map, String str) {
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    private List<String> getFromMap(Map<String, List<String>> map, String str) {
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    private XSAttributeDecl getGlobalAttributeDecl(String str) {
        return (XSAttributeDecl) this.fGlobalAttrDecls.get(str);
    }

    private XSAttributeGroupDecl getGlobalAttributeGroupDecl(String str) {
        return (XSAttributeGroupDecl) this.fGlobalAttrGrpDecls.get(str);
    }

    private XSElementDecl getGlobalElementDecl(String str) {
        return (XSElementDecl) this.fGlobalElemDecls.get(str);
    }

    private XSGroupDecl getGlobalGroupDecl(String str) {
        return (XSGroupDecl) this.fGlobalGroupDecls.get(str);
    }

    private XSNotationDecl getGlobalNotationDecl(String str) {
        return (XSNotationDecl) this.fGlobalNotationDecls.get(str);
    }

    private XSTypeDefinition getGlobalTypeDecl(String str) {
        return (XSTypeDefinition) this.fGlobalTypeDecls.get(str);
    }

    private IdentityConstraint getIDConstraintDecl(String str) {
        return (IdentityConstraint) this.fGlobalIDConstraintDecls.get(str);
    }

    private Element getSchemaDocument(String str, SAXInputSource sAXInputSource, boolean z, short s, Element element) {
        IOException iOException;
        String strExpandSystemId;
        XSDKey xSDKey;
        boolean feature;
        XMLSecurityManager xMLSecurityManager;
        XMLReader xMLReader = sAXInputSource.getXMLReader();
        InputSource inputSource = sAXInputSource.getInputSource();
        boolean feature2 = false;
        if (inputSource != null) {
            try {
                try {
                    if (inputSource.getSystemId() == null) {
                        if (inputSource.getByteStream() == null) {
                            if (inputSource.getCharacterStream() != null) {
                            }
                            iOException = null;
                        }
                    }
                    if (s != 3) {
                        strExpandSystemId = XMLEntityManager.expandSystemId(inputSource.getSystemId(), sAXInputSource.getBaseSystemId(), false);
                        xSDKey = new XSDKey(strExpandSystemId, s, str);
                        Element element2 = this.fTraversed.get(xSDKey);
                        if (element2 != null) {
                            this.fLastSchemaWasDuplicate = true;
                            return element2;
                        }
                    } else {
                        strExpandSystemId = null;
                        xSDKey = null;
                    }
                    if (xMLReader != null) {
                        try {
                            feature = xMLReader.getFeature("http://xml.org/sax/features/namespace-prefixes");
                        } catch (SAXException unused) {
                            feature = false;
                        }
                    } else {
                        XMLReader xMLReader2 = JdkXmlUtils.getXMLReader(this.fOverrideDefaultParser, this.fSecurityManager.isSecureProcessing());
                        try {
                            xMLReader2.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                            try {
                                if ((xMLReader2 instanceof SAXParser) && (xMLSecurityManager = this.fSecurityManager) != null) {
                                    xMLReader2.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
                                }
                            } catch (SAXException unused2) {
                            }
                            feature = true;
                        } catch (SAXException unused3) {
                            feature = false;
                        }
                        try {
                            xMLReader2.setProperty("http://javax.xml.XMLConstants/property/accessExternalDTD", this.fAccessExternalDTD);
                        } catch (SAXNotRecognizedException e) {
                            XMLSecurityManager.printWarning(xMLReader2.getClass().getName(), "http://javax.xml.XMLConstants/property/accessExternalDTD", e);
                        }
                        xMLReader = xMLReader2;
                    }
                    try {
                        feature2 = xMLReader.getFeature(STRING_INTERNING);
                    } catch (SAXException unused4) {
                    }
                    if (this.fXSContentHandler == null) {
                        this.fXSContentHandler = new SchemaContentHandler();
                    }
                    this.fXSContentHandler.reset(this.fSchemaParser, this.fSymbolTable, feature, feature2);
                    xMLReader.setContentHandler(this.fXSContentHandler);
                    xMLReader.setErrorHandler(this.fErrorReporter.getSAXErrorHandler());
                    xMLReader.parse(inputSource);
                    try {
                        xMLReader.setContentHandler(null);
                        xMLReader.setErrorHandler(null);
                    } catch (Exception unused5) {
                    }
                    Document document = this.fXSContentHandler.getDocument();
                    return getSchemaDocument0(xSDKey, strExpandSystemId, document != null ? DOMUtil.getRoot(document) : null);
                } catch (IOException e2) {
                    feature2 = true;
                    iOException = e2;
                }
            } catch (SAXParseException e3) {
                throw SAX2XNIUtil.createXMLParseException0(e3);
            } catch (SAXException e4) {
                throw SAX2XNIUtil.createXNIException0(e4);
            }
        } else {
            iOException = null;
        }
        return getSchemaDocument1(z, feature2, sAXInputSource, element, iOException);
    }

    private Element getSchemaDocument0(XSDKey xSDKey, String str, Element element) {
        if (xSDKey != null) {
            this.fTraversed.put(xSDKey, element);
        }
        if (str != null) {
            this.fDoc2SystemId.put(element, str);
        }
        this.fLastSchemaWasDuplicate = false;
        return element;
    }

    private Element getSchemaDocument1(boolean z, boolean z2, XMLInputSource xMLInputSource, Element element, IOException iOException) {
        if (z) {
            if (z2) {
                reportSchemaError("schema_reference.4", new Object[]{xMLInputSource.getSystemId()}, element, iOException);
            } else {
                reportSchemaError("schema_reference.4", new Object[]{xMLInputSource == null ? "" : xMLInputSource.getSystemId()}, element, iOException);
            }
        } else if (z2) {
            reportSchemaWarning("schema_reference.4", new Object[]{xMLInputSource.getSystemId()}, element, iOException);
        }
        this.fLastSchemaWasDuplicate = false;
        return null;
    }

    private SchemaGrammar getSchemaGrammar(XSDDescription xSDDescription) {
        SchemaGrammar schemaGrammarFindGrammar = findGrammar(xSDDescription, this.fNamespaceGrowth);
        if (schemaGrammarFindGrammar != null) {
            return schemaGrammarFindGrammar.isImmutable() ? createGrammarFrom(schemaGrammarFindGrammar) : schemaGrammarFindGrammar;
        }
        SchemaGrammar schemaGrammar = new SchemaGrammar(xSDDescription.getNamespace(), xSDDescription.makeClone(), this.fSymbolTable);
        this.fGrammarBucket.putGrammar(schemaGrammar);
        return schemaGrammar;
    }

    private boolean isExistingGrammar(XSDDescription xSDDescription, boolean z) {
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xSDDescription.getTargetNamespace());
        if (grammar == null) {
            return findGrammar(xSDDescription, z) != null;
        }
        if (grammar.isImmutable()) {
            return true;
        }
        try {
            return grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(xSDDescription.getLiteralSystemId(), xSDDescription.getBaseSystemId(), false));
        } catch (URI.MalformedURIException unused) {
            return false;
        }
    }

    private final boolean needReportTNSError(String str) {
        List<String> list = this.fReportedTNS;
        if (list == null) {
            this.fReportedTNS = new ArrayList();
        } else if (list.contains(str)) {
            return false;
        }
        this.fReportedTNS.add(str);
        return true;
    }

    private boolean nonAnnotationContent(Element element) {
        for (Element firstChildElement = DOMUtil.getFirstChildElement(element); firstChildElement != null; firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement)) {
            if (!DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                return true;
            }
        }
        return false;
    }

    private String null2EmptyString(String str) {
        return str == null ? XMLSymbols.EMPTY_STRING : str;
    }

    private boolean removeParticle(XSModelGroupImpl xSModelGroupImpl, XSParticleDecl xSParticleDecl) {
        int i = 0;
        while (i < xSModelGroupImpl.fParticleCount) {
            XSParticleDecl xSParticleDecl2 = xSModelGroupImpl.fParticles[i];
            if (xSParticleDecl2 == xSParticleDecl) {
                while (true) {
                    int i2 = xSModelGroupImpl.fParticleCount;
                    if (i >= i2 - 1) {
                        xSModelGroupImpl.fParticleCount = i2 - 1;
                        return true;
                    }
                    XSParticleDecl[] xSParticleDeclArr = xSModelGroupImpl.fParticles;
                    int i3 = i + 1;
                    xSParticleDeclArr[i] = xSParticleDeclArr[i3];
                    i = i3;
                }
            } else {
                if (xSParticleDecl2.fType == 3 && removeParticle((XSModelGroupImpl) xSParticleDecl2.fValue, xSParticleDecl)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    private void renameRedefiningComponents(XSDocumentInfo xSDocumentInfo, Element element, String str, String str2, String str3) {
        StringBuilder sb;
        StringBuilder sb2;
        if (str.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            Element firstChildElement = DOMUtil.getFirstChildElement(element);
            if (firstChildElement == null) {
                reportSchemaError("src-redefine.5.a.a", null, element);
                return;
            }
            if (DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            }
            if (firstChildElement == null) {
                reportSchemaError("src-redefine.5.a.a", null, element);
                return;
            }
            String localName = DOMUtil.getLocalName(firstChildElement);
            if (!localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
                reportSchemaError("src-redefine.5.a.b", new Object[]{localName}, element);
                return;
            }
            Object[] objArrCheckAttributes = this.fAttributeChecker.checkAttributes(firstChildElement, false, xSDocumentInfo);
            QName qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_BASE];
            if (qName != null && qName.uri == xSDocumentInfo.fTargetNamespace && qName.localpart.equals(str2)) {
                String str4 = qName.prefix;
                if (str4 == null || str4.length() <= 0) {
                    firstChildElement.setAttribute(SchemaSymbols.ATT_BASE, str3);
                } else {
                    firstChildElement.setAttribute(SchemaSymbols.ATT_BASE, qName.prefix + ":" + str3);
                }
            } else {
                StringBuilder sb3 = new StringBuilder();
                String str5 = xSDocumentInfo.fTargetNamespace;
                sb3.append(str5 != null ? str5 : "");
                sb3.append(",");
                sb3.append(str2);
                reportSchemaError("src-redefine.5.a.c", new Object[]{localName, sb3.toString()}, element);
            }
            this.fAttributeChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            return;
        }
        if (str.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
            Element firstChildElement2 = DOMUtil.getFirstChildElement(element);
            if (firstChildElement2 == null) {
                reportSchemaError("src-redefine.5.b.a", null, element);
                return;
            }
            String localName2 = DOMUtil.getLocalName(firstChildElement2);
            String str6 = SchemaSymbols.ELT_ANNOTATION;
            if (localName2.equals(str6)) {
                firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
            }
            if (firstChildElement2 == null) {
                reportSchemaError("src-redefine.5.b.a", null, element);
                return;
            }
            Element firstChildElement3 = DOMUtil.getFirstChildElement(firstChildElement2);
            if (firstChildElement3 == null) {
                reportSchemaError("src-redefine.5.b.b", null, firstChildElement2);
                return;
            }
            if (DOMUtil.getLocalName(firstChildElement3).equals(str6)) {
                firstChildElement3 = DOMUtil.getNextSiblingElement(firstChildElement3);
            }
            if (firstChildElement3 == null) {
                reportSchemaError("src-redefine.5.b.b", null, firstChildElement2);
                return;
            }
            String localName3 = DOMUtil.getLocalName(firstChildElement3);
            if (!localName3.equals(SchemaSymbols.ELT_RESTRICTION) && !localName3.equals(SchemaSymbols.ELT_EXTENSION)) {
                reportSchemaError("src-redefine.5.b.c", new Object[]{localName3}, firstChildElement3);
                return;
            }
            QName qName2 = (QName) this.fAttributeChecker.checkAttributes(firstChildElement3, false, xSDocumentInfo)[XSAttributeChecker.ATTIDX_BASE];
            if (qName2 == null || qName2.uri != xSDocumentInfo.fTargetNamespace || !qName2.localpart.equals(str2)) {
                StringBuilder sb4 = new StringBuilder();
                String str7 = xSDocumentInfo.fTargetNamespace;
                sb4.append(str7 != null ? str7 : "");
                sb4.append(",");
                sb4.append(str2);
                reportSchemaError("src-redefine.5.b.d", new Object[]{localName3, sb4.toString()}, firstChildElement3);
                return;
            }
            String str8 = qName2.prefix;
            if (str8 == null || str8.length() <= 0) {
                firstChildElement3.setAttribute(SchemaSymbols.ATT_BASE, str3);
                return;
            }
            firstChildElement3.setAttribute(SchemaSymbols.ATT_BASE, qName2.prefix + ":" + str3);
            return;
        }
        if (str.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
            if (xSDocumentInfo.fTargetNamespace == null) {
                sb2 = new StringBuilder(",");
            } else {
                sb2 = new StringBuilder();
                sb2.append(xSDocumentInfo.fTargetNamespace);
                sb2.append(",");
            }
            sb2.append(str2);
            String string = sb2.toString();
            int iChangeRedefineGroup = changeRedefineGroup(string, str, str3, element, xSDocumentInfo);
            if (iChangeRedefineGroup > 1) {
                reportSchemaError("src-redefine.7.1", new Object[]{Integer.valueOf(iChangeRedefineGroup)}, element);
                return;
            }
            if (iChangeRedefineGroup == 1) {
                return;
            }
            String str9 = xSDocumentInfo.fTargetNamespace;
            Map<String, String> map = this.fRedefinedRestrictedAttributeGroupRegistry;
            if (str9 == null) {
                map.put(string, "," + str3);
                return;
            } else {
                map.put(string, xSDocumentInfo.fTargetNamespace + "," + str3);
                return;
            }
        }
        if (!str.equals(SchemaSymbols.ELT_GROUP)) {
            reportSchemaError("Internal-Error", new Object[]{"could not handle this particular <redefine>; please submit your schemas and instance document in a bug report!"}, element);
            return;
        }
        if (xSDocumentInfo.fTargetNamespace == null) {
            sb = new StringBuilder(",");
        } else {
            sb = new StringBuilder();
            sb.append(xSDocumentInfo.fTargetNamespace);
            sb.append(",");
        }
        sb.append(str2);
        String string2 = sb.toString();
        int iChangeRedefineGroup2 = changeRedefineGroup(string2, str, str3, element, xSDocumentInfo);
        if (iChangeRedefineGroup2 > 1) {
            reportSchemaError("src-redefine.6.1.1", new Object[]{Integer.valueOf(iChangeRedefineGroup2)}, element);
            return;
        }
        if (iChangeRedefineGroup2 == 1) {
            return;
        }
        String str10 = xSDocumentInfo.fTargetNamespace;
        Map<String, String> map2 = this.fRedefinedRestrictedGroupRegistry;
        if (str10 == null) {
            map2.put(string2, "," + str3);
        } else {
            map2.put(string2, xSDocumentInfo.fTargetNamespace + "," + str3);
        }
    }

    private void reportSharingError(String str, String str2) {
        String str3;
        if (str == null) {
            str3 = "," + str2;
        } else {
            str3 = str + "," + str2;
        }
        reportSchemaError("sch-props-correct.2", new Object[]{str3}, null);
    }

    private Element resolveSchema(XSDDescription xSDDescription, boolean z, Element element, boolean z2) {
        XMLInputSource xMLInputSourceResolveDocument;
        try {
            xMLInputSourceResolveDocument = XMLSchemaLoader.resolveDocument(xSDDescription, z2 ? this.fLocationPairs : Collections.EMPTY_MAP, this.fEntityManager);
        } catch (IOException unused) {
            if (z) {
                reportSchemaError("schema_reference.4", new Object[]{xSDDescription.getLocationHints()[0]}, element);
            } else {
                reportSchemaWarning("schema_reference.4", new Object[]{xSDDescription.getLocationHints()[0]}, element);
            }
            xMLInputSourceResolveDocument = null;
        }
        XMLInputSource xMLInputSource = xMLInputSourceResolveDocument;
        if (xMLInputSource instanceof DOMInputSource) {
            return getSchemaDocument(xSDDescription.getTargetNamespace(), (DOMInputSource) xMLInputSource, z, xSDDescription.getContextType(), element);
        }
        if (xMLInputSource instanceof SAXInputSource) {
            return getSchemaDocument(xSDDescription.getTargetNamespace(), (SAXInputSource) xMLInputSource, z, xSDDescription.getContextType(), element);
        }
        if (xMLInputSource instanceof StAXInputSource) {
            return getSchemaDocument(xSDDescription.getTargetNamespace(), (StAXInputSource) xMLInputSource, z, xSDDescription.getContextType(), element);
        }
        return xMLInputSource instanceof XSInputSource ? getSchemaDocument((XSInputSource) xMLInputSource, xSDDescription) : getSchemaDocument(xSDDescription.getTargetNamespace(), xMLInputSource, z, xSDDescription.getContextType(), element);
    }

    private XMLInputSource resolveSchemaSource(XSDDescription xSDDescription, boolean z, Element element, boolean z2) {
        try {
            return XMLSchemaLoader.resolveDocument(xSDDescription, z2 ? this.fLocationPairs : Collections.EMPTY_MAP, this.fEntityManager);
        } catch (IOException unused) {
            if (z) {
                reportSchemaError("schema_reference.4", new Object[]{xSDDescription.getLocationHints()[0]}, element);
                return null;
            }
            reportSchemaWarning("schema_reference.4", new Object[]{xSDDescription.getLocationHints()[0]}, element);
            return null;
        }
    }

    private void setSchemasVisible(XSDocumentInfo xSDocumentInfo) {
        if (DOMUtil.isHidden(xSDocumentInfo.fSchemaElement, this.fHiddenNodes)) {
            DOMUtil.setVisible(xSDocumentInfo.fSchemaElement, this.fHiddenNodes);
            List<XSDocumentInfo> list = this.fDependencyMap.get(xSDocumentInfo);
            for (int i = 0; i < list.size(); i++) {
                setSchemasVisible(list.get(i));
            }
        }
    }

    private void updateImportDependencies(Map<String, List<String>> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value.size() > 0) {
                expandImportList(key, value);
            }
        }
    }

    private void updateImportList(SchemaGrammar schemaGrammar, List<SchemaGrammar> list, List<String> list2) {
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            SchemaGrammar grammar = this.fGrammarBucket.getGrammar(list2.get(i));
            if (grammar != null && !containedImportedGrammar(list, grammar)) {
                list.add(grammar);
            }
        }
    }

    private void updateImportListFor(SchemaGrammar schemaGrammar) {
        List<SchemaGrammar> importedGrammars = schemaGrammar.getImportedGrammars();
        if (importedGrammars != null) {
            for (int i = 0; i < importedGrammars.size(); i++) {
                SchemaGrammar schemaGrammar2 = importedGrammars.get(i);
                SchemaGrammar grammar = this.fGrammarBucket.getGrammar(schemaGrammar2.getTargetNamespace());
                if (grammar != null && schemaGrammar2 != grammar) {
                    importedGrammars.set(i, grammar);
                }
            }
        }
    }

    private void updateImportListWith(SchemaGrammar schemaGrammar) {
        List<SchemaGrammar> importedGrammars;
        for (SchemaGrammar schemaGrammar2 : this.fGrammarBucket.getGrammars()) {
            if (schemaGrammar2 != schemaGrammar && (importedGrammars = schemaGrammar2.getImportedGrammars()) != null) {
                for (int i = 0; i < importedGrammars.size(); i++) {
                    SchemaGrammar schemaGrammar3 = importedGrammars.get(i);
                    if (null2EmptyString(schemaGrammar3.getTargetNamespace()).equals(null2EmptyString(schemaGrammar.getTargetNamespace()))) {
                        if (schemaGrammar3 == schemaGrammar) {
                            break;
                        }
                        importedGrammars.set(i, schemaGrammar);
                        break;
                    }
                }
            }
        }
    }

    private void validateAnnotations(List<Object> list) {
        if (this.fAnnotationValidator == null) {
            createAnnotationValidator();
        }
        int size = list.size();
        XMLInputSource xMLInputSource = new XMLInputSource(null, null, null, false);
        this.fGrammarBucketAdapter.refreshGrammars(this.fGrammarBucket);
        for (int i = 0; i < size; i += 2) {
            xMLInputSource.setSystemId((String) list.get(i));
            for (XSAnnotationInfo xSAnnotationInfo = (XSAnnotationInfo) list.get(i + 1); xSAnnotationInfo != null; xSAnnotationInfo = xSAnnotationInfo.next) {
                xMLInputSource.setCharacterStream(new StringReader(xSAnnotationInfo.fAnnotation));
                try {
                    this.fAnnotationValidator.parse(xMLInputSource);
                } catch (IOException unused) {
                }
            }
        }
    }

    public void addGlobalAttributeDecl(XSAttributeDecl xSAttributeDecl) {
        String str;
        String namespace = xSAttributeDecl.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + xSAttributeDecl.getName();
        } else {
            str = namespace + "," + xSAttributeDecl.getName();
        }
        if (this.fGlobalAttrDecls.get(str) == null) {
            this.fGlobalAttrDecls.put(str, xSAttributeDecl);
        }
    }

    public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl) {
        String str;
        String namespace = xSAttributeGroupDecl.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + xSAttributeGroupDecl.getName();
        } else {
            str = namespace + "," + xSAttributeGroupDecl.getName();
        }
        if (this.fGlobalAttrGrpDecls.get(str) == null) {
            this.fGlobalAttrGrpDecls.put(str, xSAttributeGroupDecl);
        }
    }

    public void addGlobalElementDecl(XSElementDecl xSElementDecl) {
        String str;
        String namespace = xSElementDecl.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + xSElementDecl.getName();
        } else {
            str = namespace + "," + xSElementDecl.getName();
        }
        if (this.fGlobalElemDecls.get(str) == null) {
            this.fGlobalElemDecls.put(str, xSElementDecl);
        }
    }

    public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl) {
        String str;
        String namespace = xSGroupDecl.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + xSGroupDecl.getName();
        } else {
            str = namespace + "," + xSGroupDecl.getName();
        }
        if (this.fGlobalGroupDecls.get(str) == null) {
            this.fGlobalGroupDecls.put(str, xSGroupDecl);
        }
    }

    public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl) {
        String str;
        String namespace = xSNotationDecl.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + xSNotationDecl.getName();
        } else {
            str = namespace + "," + xSNotationDecl.getName();
        }
        if (this.fGlobalNotationDecls.get(str) == null) {
            this.fGlobalNotationDecls.put(str, xSNotationDecl);
        }
    }

    public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition) {
        String str;
        String namespace = xSTypeDefinition.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + xSTypeDefinition.getName();
        } else {
            str = namespace + "," + xSTypeDefinition.getName();
        }
        if (this.fGlobalTypeDecls.get(str) == null) {
            this.fGlobalTypeDecls.put(str, xSTypeDefinition);
        }
    }

    public void addIDConstraintDecl(IdentityConstraint identityConstraint) {
        String str;
        String namespace = identityConstraint.getNamespace();
        if (namespace == null || namespace.length() == 0) {
            str = "," + identityConstraint.getIdentityConstraintName();
        } else {
            str = namespace + "," + identityConstraint.getIdentityConstraintName();
        }
        if (this.fGlobalIDConstraintDecls.get(str) == null) {
            this.fGlobalIDConstraintDecls.put(str, identityConstraint);
        }
    }

    public void buildGlobalNameRegistries() {
        Element element;
        boolean z;
        String strConcat;
        String strConcat2;
        boolean z2;
        XSDHandler xSDHandler = this;
        xSDHandler.registryEmpty = false;
        Stack stack = new Stack();
        stack.push(xSDHandler.fRoot);
        while (!stack.empty()) {
            XSDocumentInfo xSDocumentInfo = (XSDocumentInfo) stack.pop();
            Element element2 = xSDocumentInfo.fSchemaElement;
            if (!DOMUtil.isHidden(element2, xSDHandler.fHiddenNodes)) {
                Element firstChildElement = DOMUtil.getFirstChildElement(element2);
                boolean z3 = true;
                while (firstChildElement != null) {
                    if (DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        element = firstChildElement;
                        z = z3;
                    } else if (DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_INCLUDE) || DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_IMPORT)) {
                        element = firstChildElement;
                        z = z3;
                        if (!z) {
                            xSDHandler.reportSchemaError("s4s-elt-invalid-content.3", new Object[]{DOMUtil.getLocalName(element)}, element);
                        }
                        DOMUtil.setHidden(element, xSDHandler.fHiddenNodes);
                    } else {
                        if (DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_REDEFINE)) {
                            if (!z3) {
                                xSDHandler.reportSchemaError("s4s-elt-invalid-content.3", new Object[]{DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
                            }
                            Element firstChildElement2 = DOMUtil.getFirstChildElement(firstChildElement);
                            while (firstChildElement2 != null) {
                                String str = SchemaSymbols.ATT_NAME;
                                String attrValue = DOMUtil.getAttrValue(firstChildElement2, str);
                                if (attrValue.length() != 0) {
                                    if (xSDocumentInfo.fTargetNamespace == null) {
                                        strConcat2 = ",".concat(attrValue);
                                    } else {
                                        strConcat2 = xSDocumentInfo.fTargetNamespace + "," + attrValue;
                                    }
                                    String strTrim = XMLChar.trim(strConcat2);
                                    String localName = DOMUtil.getLocalName(firstChildElement2);
                                    String str2 = SchemaSymbols.ELT_ATTRIBUTEGROUP;
                                    if (localName.equals(str2)) {
                                        Element element3 = firstChildElement2;
                                        xSDHandler.checkForDuplicateNames(strTrim, 2, xSDHandler.fUnparsedAttributeGroupRegistry, xSDHandler.fUnparsedAttributeGroupRegistrySub, element3, xSDocumentInfo);
                                        firstChildElement2 = element3;
                                        xSDHandler = this;
                                        xSDHandler.renameRedefiningComponents(xSDocumentInfo, firstChildElement2, str2, attrValue, DOMUtil.getAttrValue(firstChildElement2, str) + REDEF_IDENTIFIER);
                                    } else {
                                        String str3 = SchemaSymbols.ELT_COMPLEXTYPE;
                                        if (localName.equals(str3) || localName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                            Element element4 = firstChildElement2;
                                            z2 = z3;
                                            xSDHandler.checkForDuplicateNames(strTrim, 7, xSDHandler.fUnparsedTypeRegistry, xSDHandler.fUnparsedTypeRegistrySub, element4, xSDocumentInfo);
                                            firstChildElement2 = element4;
                                            String str4 = DOMUtil.getAttrValue(firstChildElement2, str) + REDEF_IDENTIFIER;
                                            if (localName.equals(str3)) {
                                                xSDHandler = this;
                                                xSDHandler.renameRedefiningComponents(xSDocumentInfo, firstChildElement2, str3, attrValue, str4);
                                            } else {
                                                xSDHandler = this;
                                                xSDHandler.renameRedefiningComponents(xSDocumentInfo, firstChildElement2, SchemaSymbols.ELT_SIMPLETYPE, attrValue, str4);
                                            }
                                        } else {
                                            String str5 = SchemaSymbols.ELT_GROUP;
                                            if (localName.equals(str5)) {
                                                Element element5 = firstChildElement2;
                                                xSDHandler.checkForDuplicateNames(strTrim, 4, xSDHandler.fUnparsedGroupRegistry, xSDHandler.fUnparsedGroupRegistrySub, element5, xSDocumentInfo);
                                                firstChildElement2 = element5;
                                                xSDHandler = this;
                                                xSDHandler.renameRedefiningComponents(xSDocumentInfo, firstChildElement2, str5, attrValue, DOMUtil.getAttrValue(firstChildElement2, str) + REDEF_IDENTIFIER);
                                            }
                                        }
                                    }
                                    z2 = z3;
                                } else {
                                    z2 = z3;
                                }
                                firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
                                z3 = z2;
                            }
                            z = z3;
                            element = firstChildElement;
                        } else {
                            String attrValue2 = DOMUtil.getAttrValue(firstChildElement, SchemaSymbols.ATT_NAME);
                            if (attrValue2.length() == 0) {
                                element = firstChildElement;
                            } else {
                                if (xSDocumentInfo.fTargetNamespace == null) {
                                    strConcat = ",".concat(attrValue2);
                                } else {
                                    strConcat = xSDocumentInfo.fTargetNamespace + "," + attrValue2;
                                }
                                String strTrim2 = XMLChar.trim(strConcat);
                                String localName2 = DOMUtil.getLocalName(firstChildElement);
                                if (localName2.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                                    element = firstChildElement;
                                    xSDHandler.checkForDuplicateNames(strTrim2, 1, xSDHandler.fUnparsedAttributeRegistry, xSDHandler.fUnparsedAttributeRegistrySub, element, xSDocumentInfo);
                                } else {
                                    element = firstChildElement;
                                    if (localName2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                        xSDHandler.checkForDuplicateNames(strTrim2, 2, xSDHandler.fUnparsedAttributeGroupRegistry, xSDHandler.fUnparsedAttributeGroupRegistrySub, element, xSDocumentInfo);
                                    } else if (localName2.equals(SchemaSymbols.ELT_COMPLEXTYPE) || localName2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                        xSDHandler.checkForDuplicateNames(strTrim2, 7, xSDHandler.fUnparsedTypeRegistry, xSDHandler.fUnparsedTypeRegistrySub, element, xSDocumentInfo);
                                    } else if (localName2.equals(SchemaSymbols.ELT_ELEMENT)) {
                                        xSDHandler.checkForDuplicateNames(strTrim2, 3, xSDHandler.fUnparsedElementRegistry, xSDHandler.fUnparsedElementRegistrySub, element, xSDocumentInfo);
                                    } else if (localName2.equals(SchemaSymbols.ELT_GROUP)) {
                                        xSDHandler.checkForDuplicateNames(strTrim2, 4, xSDHandler.fUnparsedGroupRegistry, xSDHandler.fUnparsedGroupRegistrySub, element, xSDocumentInfo);
                                    } else if (localName2.equals(SchemaSymbols.ELT_NOTATION)) {
                                        xSDHandler.checkForDuplicateNames(strTrim2, 6, xSDHandler.fUnparsedNotationRegistry, xSDHandler.fUnparsedNotationRegistrySub, element, xSDocumentInfo);
                                    }
                                }
                            }
                            z3 = false;
                        }
                        firstChildElement = DOMUtil.getNextSiblingElement(element);
                    }
                    z3 = z;
                    firstChildElement = DOMUtil.getNextSiblingElement(element);
                }
                DOMUtil.setHidden(element2, xSDHandler.fHiddenNodes);
                Iterator<XSDocumentInfo> it = xSDHandler.fDependencyMap.get(xSDocumentInfo).iterator();
                while (it.hasNext()) {
                    stack.push(it.next());
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0059 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:25:0x005b  */
    /* JADX WARN: Code duplicated, block: B:27:0x0063  */
    /* JADX WARN: Code duplicated, block: B:29:0x0078  */
    /* JADX WARN: Code duplicated, block: B:31:0x0081  */
    /* JADX WARN: Code duplicated, block: B:32:0x0090  */
    /* JADX WARN: Code duplicated, block: B:35:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:37:0x00f0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:38:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:40:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:41:0x0104  */
    /* JADX WARN: Code duplicated, block: B:42:0x0122  */
    /* JADX WARN: Code duplicated, block: B:43:0x012a  */
    /* JADX WARN: Code duplicated, block: B:45:0x012e  */
    /* JADX WARN: Code duplicated, block: B:46:0x0136  */
    /* JADX WARN: Instruction removed from duplicated block: B:32:0x0090, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:36:0x00d1, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:41:0x0104, please report this as an issue */
    public void checkForDuplicateNames(String str, int i, Map<String, Element> map, Map<String, XSDocumentInfo> map2, Element element, XSDocumentInfo xSDocumentInfo) {
        boolean z;
        Map<String, XSDocumentInfo> map3;
        String strConcat;
        Element element2 = map.get(str);
        if (element2 == null) {
            if (this.fNamespaceGrowth && !this.fTolerateDuplicates) {
                checkForDuplicateNames(str, i, element);
            }
            map.put(str, element);
            map2.put(str, xSDocumentInfo);
        } else {
            Element element3 = element2;
            XSDocumentInfo xSDocumentInfo2 = map2.get(str);
            if (element3 == element) {
                return;
            }
            Element parent = DOMUtil.getParent(element3);
            String localName = DOMUtil.getLocalName(parent);
            String str2 = SchemaSymbols.ELT_REDEFINE;
            XSDocumentInfo xSDocumentInfo3 = null;
            if (localName.equals(str2)) {
                Map<Element, XSDocumentInfo> map4 = this.fRedefine2XSDMap;
                if (map4 != null) {
                    xSDocumentInfo3 = map4.get(parent);
                }
            } else {
                if (DOMUtil.getLocalName(DOMUtil.getParent(element)).equals(str2)) {
                    z = false;
                    xSDocumentInfo3 = xSDocumentInfo2;
                }
                if (xSDocumentInfo3 != null) {
                    if (xSDocumentInfo2 == xSDocumentInfo) {
                        reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                        return;
                    }
                    strConcat = str.substring(str.lastIndexOf(44) + 1).concat(REDEF_IDENTIFIER);
                    if (xSDocumentInfo3 == xSDocumentInfo) {
                        element.setAttribute(SchemaSymbols.ATT_NAME, strConcat);
                        if (xSDocumentInfo.fTargetNamespace == null) {
                            map.put(",".concat(strConcat), element);
                            map2.put(",".concat(strConcat), xSDocumentInfo);
                        } else {
                            map.put(xSDocumentInfo.fTargetNamespace + "," + strConcat, element);
                            map2.put(xSDocumentInfo.fTargetNamespace + "," + strConcat, xSDocumentInfo);
                        }
                        if (xSDocumentInfo.fTargetNamespace == null) {
                            checkForDuplicateNames(",".concat(strConcat), i, map, map2, element, xSDocumentInfo);
                        } else {
                            checkForDuplicateNames(xSDocumentInfo.fTargetNamespace + "," + strConcat, i, map, map2, element, xSDocumentInfo);
                        }
                    } else if (z) {
                        reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                    } else if (xSDocumentInfo.fTargetNamespace == null) {
                        checkForDuplicateNames(",".concat(strConcat), i, map, map2, element, xSDocumentInfo);
                    } else {
                        checkForDuplicateNames(xSDocumentInfo.fTargetNamespace + "," + strConcat, i, map, map2, element, xSDocumentInfo);
                    }
                } else if (this.fTolerateDuplicates) {
                    map3 = this.fUnparsedRegistriesExt[i];
                    if (map3 != null && map3.get(str) == xSDocumentInfo) {
                        reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                    }
                } else {
                    reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                }
            }
            z = true;
            if (xSDocumentInfo3 != null) {
                if (xSDocumentInfo2 == xSDocumentInfo) {
                    reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                    return;
                }
                strConcat = str.substring(str.lastIndexOf(44) + 1).concat(REDEF_IDENTIFIER);
                if (xSDocumentInfo3 == xSDocumentInfo) {
                    element.setAttribute(SchemaSymbols.ATT_NAME, strConcat);
                    if (xSDocumentInfo.fTargetNamespace == null) {
                        map.put(",".concat(strConcat), element);
                        map2.put(",".concat(strConcat), xSDocumentInfo);
                    } else {
                        map.put(xSDocumentInfo.fTargetNamespace + "," + strConcat, element);
                        map2.put(xSDocumentInfo.fTargetNamespace + "," + strConcat, xSDocumentInfo);
                    }
                    if (xSDocumentInfo.fTargetNamespace == null) {
                        checkForDuplicateNames(",".concat(strConcat), i, map, map2, element, xSDocumentInfo);
                    } else {
                        checkForDuplicateNames(xSDocumentInfo.fTargetNamespace + "," + strConcat, i, map, map2, element, xSDocumentInfo);
                    }
                } else if (z) {
                    reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                } else if (xSDocumentInfo.fTargetNamespace == null) {
                    checkForDuplicateNames(",".concat(strConcat), i, map, map2, element, xSDocumentInfo);
                } else {
                    checkForDuplicateNames(xSDocumentInfo.fTargetNamespace + "," + strConcat, i, map, map2, element, xSDocumentInfo);
                }
            } else if (this.fTolerateDuplicates) {
                reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
            } else {
                map3 = this.fUnparsedRegistriesExt[i];
                if (map3 != null) {
                    reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
                }
            }
        }
        if (this.fTolerateDuplicates) {
            Map<String, XSDocumentInfo>[] mapArr = this.fUnparsedRegistriesExt;
            if (mapArr[i] == null) {
                mapArr[i] = new HashMap();
            }
            this.fUnparsedRegistriesExt[i].put(str, xSDocumentInfo);
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x023c  */
    /* JADX WARN: Code duplicated, block: B:104:0x023e  */
    /* JADX WARN: Code duplicated, block: B:109:0x0251  */
    /* JADX WARN: Code duplicated, block: B:112:0x0256  */
    /* JADX WARN: Code duplicated, block: B:114:0x0259  */
    /* JADX WARN: Code duplicated, block: B:200:0x0226 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x01a0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:78:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:79:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:82:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:84:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:89:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:92:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:93:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:95:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:98:0x0222  */
    public XSDocumentInfo constructTrees(Element element, String str, XSDDescription xSDDescription, boolean z) {
        SchemaGrammar grammar;
        SchemaGrammar schemaGrammar;
        Element element2;
        String str2;
        boolean zNonAnnotationContent;
        short s;
        boolean z2;
        boolean zContains;
        boolean z3;
        String str3;
        boolean z4;
        Element element3;
        String strNull2EmptyString;
        List<String> list;
        SchemaGrammar schemaGrammarFindGrammar;
        boolean z5;
        boolean z6;
        String str4;
        String str5;
        Element element4 = null;
        if (element == null) {
            return null;
        }
        String targetNamespace = xSDDescription.getTargetNamespace();
        short contextType = xSDDescription.getContextType();
        try {
            XSDocumentInfo xSDocumentInfo = new XSDocumentInfo(element, this.fAttributeChecker, this.fSymbolTable);
            String str6 = xSDocumentInfo.fTargetNamespace;
            if (str6 != null && str6.length() == 0) {
                reportSchemaWarning("EmptyTargetNamespace", new Object[]{str}, element);
                xSDocumentInfo.fTargetNamespace = null;
            }
            boolean z7 = true;
            if (targetNamespace == null) {
                String str7 = xSDocumentInfo.fTargetNamespace;
                if (str7 != null) {
                    if (contextType != 3) {
                        reportSchemaError(NS_ERROR_CODES[contextType][1], new Object[]{targetNamespace, str7}, element);
                        return null;
                    }
                    xSDDescription.setTargetNamespace(str7);
                    targetNamespace = xSDocumentInfo.fTargetNamespace;
                }
            } else if (contextType == 0 || contextType == 1) {
                String str8 = xSDocumentInfo.fTargetNamespace;
                if (str8 == null) {
                    xSDocumentInfo.fTargetNamespace = targetNamespace;
                    xSDocumentInfo.fIsChameleonSchema = true;
                } else if (targetNamespace != str8) {
                    reportSchemaError(NS_ERROR_CODES[contextType][0], new Object[]{targetNamespace, str8}, element);
                    return null;
                }
            } else if (contextType != 3 && targetNamespace != (str5 = xSDocumentInfo.fTargetNamespace)) {
                reportSchemaError(NS_ERROR_CODES[contextType][0], new Object[]{targetNamespace, str5}, element);
                return null;
            }
            xSDocumentInfo.addAllowedNS(xSDocumentInfo.fTargetNamespace);
            char c = 2;
            if (z) {
                grammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
                if (grammar.isImmutable()) {
                    SchemaGrammar schemaGrammar2 = new SchemaGrammar(grammar);
                    this.fGrammarBucket.putGrammar(schemaGrammar2);
                    updateImportListWith(schemaGrammar2);
                    grammar = schemaGrammar2;
                }
                updateImportListFor(grammar);
            } else if (contextType == 0 || contextType == 1) {
                grammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
            } else {
                if (this.fHonourAllSchemaLocations && contextType == 2) {
                    schemaGrammar = findGrammar(xSDDescription, false);
                    if (schemaGrammar == null) {
                        schemaGrammar = new SchemaGrammar(xSDocumentInfo.fTargetNamespace, xSDDescription.makeClone(), this.fSymbolTable);
                        this.fGrammarBucket.putGrammar(schemaGrammar);
                    }
                } else {
                    schemaGrammar = new SchemaGrammar(xSDocumentInfo.fTargetNamespace, xSDDescription.makeClone(), this.fSymbolTable);
                    this.fGrammarBucket.putGrammar(schemaGrammar);
                }
                grammar = schemaGrammar;
            }
            grammar.addDocument(null, this.fDoc2SystemId.get(xSDocumentInfo.fSchemaElement));
            this.fDoc2XSDocumentMap.put(element, xSDocumentInfo);
            ArrayList arrayList = new ArrayList();
            Element elementResolveSchema = null;
            Element firstChildElement = DOMUtil.getFirstChildElement(element);
            while (firstChildElement != null) {
                String localName = DOMUtil.getLocalName(firstChildElement);
                String str9 = SchemaSymbols.ELT_ANNOTATION;
                if (localName.equals(str9)) {
                    element2 = element4;
                    element3 = elementResolveSchema;
                } else {
                    element2 = element4;
                    if (!localName.equals(SchemaSymbols.ELT_IMPORT)) {
                        str2 = localName;
                        Element element5 = elementResolveSchema;
                        String str10 = SchemaSymbols.ELT_INCLUDE;
                        if (!str2.equals(str10) && !str2.equals(SchemaSymbols.ELT_REDEFINE)) {
                            break;
                        }
                        Object[] objArrCheckAttributes = this.fAttributeChecker.checkAttributes(firstChildElement, true, xSDocumentInfo);
                        String str11 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                        if (str2.equals(SchemaSymbols.ELT_REDEFINE)) {
                            if (this.fRedefine2NSSupport == null) {
                                this.fRedefine2NSSupport = new HashMap();
                            }
                            this.fRedefine2NSSupport.put(firstChildElement, new SchemaNamespaceSupport(xSDocumentInfo.fNamespaceSupport));
                        }
                        if (str2.equals(str10)) {
                            Element firstChildElement2 = DOMUtil.getFirstChildElement(firstChildElement);
                            if (firstChildElement2 != null) {
                                String localName2 = DOMUtil.getLocalName(firstChildElement2);
                                if (localName2.equals(str9)) {
                                    grammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(firstChildElement2, objArrCheckAttributes, true, xSDocumentInfo));
                                } else {
                                    reportSchemaError("s4s-elt-must-match.1", new Object[]{str2, "annotation?", localName2}, firstChildElement);
                                }
                                if (DOMUtil.getNextSiblingElement(firstChildElement2) != null) {
                                    reportSchemaError("s4s-elt-must-match.1", new Object[]{str2, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(firstChildElement2))}, firstChildElement);
                                }
                            } else {
                                String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(firstChildElement);
                                if (syntheticAnnotation != null) {
                                    grammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation, objArrCheckAttributes, true, xSDocumentInfo));
                                }
                            }
                        } else {
                            for (Element firstChildElement3 = DOMUtil.getFirstChildElement(firstChildElement); firstChildElement3 != null; firstChildElement3 = DOMUtil.getNextSiblingElement(firstChildElement3)) {
                                if (DOMUtil.getLocalName(firstChildElement3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                                    grammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(firstChildElement3, objArrCheckAttributes, true, xSDocumentInfo));
                                    DOMUtil.setHidden(firstChildElement3, this.fHiddenNodes);
                                } else {
                                    String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(firstChildElement);
                                    if (syntheticAnnotation2 != null) {
                                        grammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation2, objArrCheckAttributes, true, xSDocumentInfo));
                                    }
                                }
                            }
                        }
                        this.fAttributeChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                        if (str11 == 0) {
                            reportSchemaError("s4s-att-must-appear", new Object[]{"<include> or <redefine>", "schemaLocation"}, firstChildElement);
                        }
                        if (str2.equals(SchemaSymbols.ELT_REDEFINE)) {
                            zNonAnnotationContent = nonAnnotationContent(firstChildElement);
                            s = 1;
                        } else {
                            zNonAnnotationContent = false;
                            s = 0;
                        }
                        this.fSchemaGrammarDescription.reset();
                        this.fSchemaGrammarDescription.setContextType(s);
                        this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(element));
                        this.fSchemaGrammarDescription.setLocationHints(new String[]{str11});
                        this.fSchemaGrammarDescription.setTargetNamespace(targetNamespace);
                        XMLInputSource xMLInputSourceResolveSchemaSource = resolveSchemaSource(this.fSchemaGrammarDescription, zNonAnnotationContent, firstChildElement, true);
                        if (this.fNamespaceGrowth && s == 0) {
                            try {
                                z2 = false;
                                try {
                                    zContains = grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(xMLInputSourceResolveSchemaSource.getSystemId(), xMLInputSourceResolveSchemaSource.getBaseSystemId(), false));
                                } catch (URI.MalformedURIException unused) {
                                    zContains = z2;
                                }
                            } catch (URI.MalformedURIException unused2) {
                                z2 = false;
                            }
                        } else {
                            z2 = false;
                            zContains = z2;
                        }
                        if (zContains) {
                            z3 = true;
                            this.fLastSchemaWasDuplicate = true;
                            elementResolveSchema = element5;
                            str3 = str11;
                            z4 = z2;
                        } else {
                            elementResolveSchema = resolveSchema(xMLInputSourceResolveSchemaSource, this.fSchemaGrammarDescription, zNonAnnotationContent, firstChildElement);
                            str3 = str11;
                            z4 = z2;
                            z3 = true;
                        }
                    } else {
                        Object[] objArrCheckAttributes2 = this.fAttributeChecker.checkAttributes(firstChildElement, z7, xSDocumentInfo);
                        String str12 = (String) objArrCheckAttributes2[XSAttributeChecker.ATTIDX_SCHEMALOCATION];
                        String strAddSymbol = (String) objArrCheckAttributes2[XSAttributeChecker.ATTIDX_NAMESPACE];
                        if (strAddSymbol != null) {
                            strAddSymbol = this.fSymbolTable.addSymbol(strAddSymbol);
                        }
                        Element firstChildElement4 = DOMUtil.getFirstChildElement(firstChildElement);
                        if (firstChildElement4 != null) {
                            element3 = elementResolveSchema;
                            String localName3 = DOMUtil.getLocalName(firstChildElement4);
                            if (localName3.equals(str9)) {
                                grammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(firstChildElement4, objArrCheckAttributes2, true, xSDocumentInfo));
                            } else {
                                reportSchemaError("s4s-elt-must-match.1", new Object[]{localName, "annotation?", localName3}, firstChildElement);
                            }
                            if (DOMUtil.getNextSiblingElement(firstChildElement4) != null) {
                                reportSchemaError("s4s-elt-must-match.1", new Object[]{localName, "annotation?", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(firstChildElement4))}, firstChildElement);
                            }
                        } else {
                            element3 = elementResolveSchema;
                            String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(firstChildElement);
                            if (syntheticAnnotation3 != null) {
                                str2 = localName;
                                grammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation3, objArrCheckAttributes2, true, xSDocumentInfo));
                            }
                            this.fAttributeChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                            if (strAddSymbol == xSDocumentInfo.fTargetNamespace) {
                                if (strAddSymbol != null) {
                                    str4 = "src-import.1.1";
                                } else {
                                    str4 = "src-import.1.2";
                                }
                                reportSchemaError(str4, new Object[]{strAddSymbol}, firstChildElement);
                            } else {
                                if (xSDocumentInfo.isAllowedNS(strAddSymbol)) {
                                    xSDocumentInfo.addAllowedNS(strAddSymbol);
                                } else if (!this.fHonourAllSchemaLocations || this.fNamespaceGrowth) {
                                }
                                strNull2EmptyString = null2EmptyString(xSDocumentInfo.fTargetNamespace);
                                list = this.fImportMap.get(strNull2EmptyString);
                                if (list == null) {
                                    this.fAllTNSs.add(strNull2EmptyString);
                                    ArrayList arrayList2 = new ArrayList();
                                    this.fImportMap.put(strNull2EmptyString, arrayList2);
                                    arrayList2.add(strAddSymbol);
                                } else if (!list.contains(strAddSymbol)) {
                                    list.add(strAddSymbol);
                                }
                                this.fSchemaGrammarDescription.reset();
                                this.fSchemaGrammarDescription.setContextType((short) 2);
                                this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(element));
                                this.fSchemaGrammarDescription.setLiteralSystemId(str12);
                                this.fSchemaGrammarDescription.setLocationHints(new String[]{str12});
                                this.fSchemaGrammarDescription.setTargetNamespace(strAddSymbol);
                                schemaGrammarFindGrammar = findGrammar(this.fSchemaGrammarDescription, this.fNamespaceGrowth);
                                if (schemaGrammarFindGrammar == null) {
                                    z5 = false;
                                } else if (this.fNamespaceGrowth) {
                                    try {
                                        if (schemaGrammarFindGrammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(str12, this.fSchemaGrammarDescription.getBaseSystemId(), false))) {
                                            z5 = true;
                                        }
                                    } catch (URI.MalformedURIException unused3) {
                                        z5 = false;
                                    }
                                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                                    z7 = z3;
                                    element4 = element2;
                                    c = 2;
                                } else {
                                    if (!this.fHonourAllSchemaLocations && !isExistingGrammar(this.fSchemaGrammarDescription, false)) {
                                        z5 = false;
                                    }
                                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                                    z7 = z3;
                                    element4 = element2;
                                    c = 2;
                                }
                                XSDDescription xSDDescription2 = this.fSchemaGrammarDescription;
                                if (schemaGrammarFindGrammar == null) {
                                    z6 = true;
                                } else {
                                    z6 = false;
                                }
                                elementResolveSchema = resolveSchema(xSDDescription2, false, firstChildElement, z6);
                                z2 = false;
                                str3 = str12;
                                z3 = true;
                                z4 = z5;
                            }
                        }
                        str2 = localName;
                        this.fAttributeChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                        if (strAddSymbol == xSDocumentInfo.fTargetNamespace) {
                            if (strAddSymbol != null) {
                                str4 = "src-import.1.1";
                            } else {
                                str4 = "src-import.1.2";
                            }
                            reportSchemaError(str4, new Object[]{strAddSymbol}, firstChildElement);
                        } else {
                            if (xSDocumentInfo.isAllowedNS(strAddSymbol)) {
                                xSDocumentInfo.addAllowedNS(strAddSymbol);
                            } else if (!this.fHonourAllSchemaLocations) {
                            }
                            strNull2EmptyString = null2EmptyString(xSDocumentInfo.fTargetNamespace);
                            list = this.fImportMap.get(strNull2EmptyString);
                            if (list == null) {
                                this.fAllTNSs.add(strNull2EmptyString);
                                ArrayList arrayList3 = new ArrayList();
                                this.fImportMap.put(strNull2EmptyString, arrayList3);
                                arrayList3.add(strAddSymbol);
                            } else if (!list.contains(strAddSymbol)) {
                                list.add(strAddSymbol);
                            }
                            this.fSchemaGrammarDescription.reset();
                            this.fSchemaGrammarDescription.setContextType((short) 2);
                            this.fSchemaGrammarDescription.setBaseSystemId(doc2SystemId(element));
                            this.fSchemaGrammarDescription.setLiteralSystemId(str12);
                            this.fSchemaGrammarDescription.setLocationHints(new String[]{str12});
                            this.fSchemaGrammarDescription.setTargetNamespace(strAddSymbol);
                            schemaGrammarFindGrammar = findGrammar(this.fSchemaGrammarDescription, this.fNamespaceGrowth);
                            if (schemaGrammarFindGrammar == null) {
                                z5 = false;
                            } else if (this.fNamespaceGrowth) {
                                if (schemaGrammarFindGrammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(str12, this.fSchemaGrammarDescription.getBaseSystemId(), false))) {
                                    z5 = true;
                                }
                                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                                z7 = z3;
                                element4 = element2;
                                c = 2;
                            } else if (!this.fHonourAllSchemaLocations) {
                            }
                            XSDDescription xSDDescription3 = this.fSchemaGrammarDescription;
                            if (schemaGrammarFindGrammar == null) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            elementResolveSchema = resolveSchema(xSDDescription3, false, firstChildElement, z6);
                            z2 = false;
                            str3 = str12;
                            z3 = true;
                            z4 = z5;
                        }
                    }
                    XSDocumentInfo xSDocumentInfoConstructTrees = this.fLastSchemaWasDuplicate ? elementResolveSchema == null ? element2 : this.fDoc2XSDocumentMap.get(elementResolveSchema) : constructTrees(elementResolveSchema, str3, this.fSchemaGrammarDescription, z4);
                    if (str2.equals(SchemaSymbols.ELT_REDEFINE) && xSDocumentInfoConstructTrees != null) {
                        if (this.fRedefine2XSDMap == null) {
                            this.fRedefine2XSDMap = new HashMap();
                        }
                        this.fRedefine2XSDMap.put(firstChildElement, xSDocumentInfoConstructTrees);
                    }
                    if (elementResolveSchema != null) {
                        if (xSDocumentInfoConstructTrees != null) {
                            arrayList.add(xSDocumentInfoConstructTrees);
                        }
                        elementResolveSchema = element2;
                    }
                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                    z7 = z3;
                    element4 = element2;
                    c = 2;
                }
                elementResolveSchema = element3;
                z2 = false;
                z3 = true;
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                z7 = z3;
                element4 = element2;
                c = 2;
            }
            this.fDependencyMap.put(xSDocumentInfo, arrayList);
            return xSDocumentInfo;
        } catch (XMLSchemaException unused4) {
            reportSchemaError(ELE_ERROR_CODES[contextType], new Object[]{str}, element);
            return null;
        }
    }

    public boolean element2Locator(Element element, SimpleLocator simpleLocator) {
        if (simpleLocator == null || !(element instanceof ElementImpl)) {
            return false;
        }
        ElementImpl elementImpl = (ElementImpl) element;
        String str = this.fDoc2SystemId.get(DOMUtil.getRoot(elementImpl.getOwnerDocument()));
        simpleLocator.setValues(str, str, elementImpl.getLineNumber(), elementImpl.getColumnNumber(), elementImpl.getCharacterOffset());
        return true;
    }

    public void fillInLocalElemInfo(Element element, XSDocumentInfo xSDocumentInfo, int i, XSObject xSObject, XSParticleDecl xSParticleDecl) {
        XSParticleDecl[] xSParticleDeclArr = this.fParticle;
        int length = xSParticleDeclArr.length;
        int i2 = this.fLocalElemStackPos;
        if (length == i2) {
            XSParticleDecl[] xSParticleDeclArr2 = new XSParticleDecl[i2 + 10];
            System.arraycopy(xSParticleDeclArr, 0, xSParticleDeclArr2, 0, i2);
            this.fParticle = xSParticleDeclArr2;
            int i3 = this.fLocalElemStackPos;
            Element[] elementArr = new Element[i3 + 10];
            System.arraycopy(this.fLocalElementDecl, 0, elementArr, 0, i3);
            this.fLocalElementDecl = elementArr;
            int i4 = this.fLocalElemStackPos;
            XSDocumentInfo[] xSDocumentInfoArr = new XSDocumentInfo[i4 + 10];
            System.arraycopy(this.fLocalElementDecl_schema, 0, xSDocumentInfoArr, 0, i4);
            this.fLocalElementDecl_schema = xSDocumentInfoArr;
            int i5 = this.fLocalElemStackPos;
            int[] iArr = new int[i5 + 10];
            System.arraycopy(this.fAllContext, 0, iArr, 0, i5);
            this.fAllContext = iArr;
            int i6 = this.fLocalElemStackPos;
            XSObject[] xSObjectArr = new XSObject[i6 + 10];
            System.arraycopy(this.fParent, 0, xSObjectArr, 0, i6);
            this.fParent = xSObjectArr;
            int i7 = this.fLocalElemStackPos;
            String[][] strArr = new String[i7 + 10][];
            System.arraycopy(this.fLocalElemNamespaceContext, 0, strArr, 0, i7);
            this.fLocalElemNamespaceContext = strArr;
        }
        XSParticleDecl[] xSParticleDeclArr3 = this.fParticle;
        int i8 = this.fLocalElemStackPos;
        xSParticleDeclArr3[i8] = xSParticleDecl;
        this.fLocalElementDecl[i8] = element;
        this.fLocalElementDecl_schema[i8] = xSDocumentInfo;
        this.fAllContext[i8] = i;
        this.fParent[i8] = xSObject;
        String[][] strArr2 = this.fLocalElemNamespaceContext;
        this.fLocalElemStackPos = i8 + 1;
        strArr2[i8] = xSDocumentInfo.fNamespaceSupport.getEffectiveLocalContext();
    }

    public SchemaGrammar findGrammar(XSDDescription xSDDescription, boolean z) {
        XMLGrammarPool xMLGrammarPool;
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xSDDescription.getTargetNamespace());
        if (grammar != null || (xMLGrammarPool = this.fGrammarPool) == null) {
            return grammar;
        }
        SchemaGrammar schemaGrammar = (SchemaGrammar) xMLGrammarPool.retrieveGrammar(xSDDescription);
        if (schemaGrammar == null || this.fGrammarBucket.putGrammar(schemaGrammar, true, z)) {
            return schemaGrammar;
        }
        reportSchemaWarning("GrammarConflict", null, null);
        return null;
    }

    public SchemaDVFactory getDVFactory() {
        return this.fDVFactory;
    }

    public Object getGlobalDecl(XSDocumentInfo xSDocumentInfo, int i, QName qName, Element element) {
        String str;
        XSDocumentInfo docInfoFromMap;
        XSTypeDefinition globalTypeDecl;
        String str2 = qName.uri;
        if (str2 != null && str2 == SchemaSymbols.URI_SCHEMAFORSCHEMA && i == 7 && (globalTypeDecl = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(qName.localpart)) != null) {
            return globalTypeDecl;
        }
        if (!xSDocumentInfo.isAllowedNS(qName.uri) && xSDocumentInfo.needReportTNSError(qName.uri)) {
            reportSchemaError(qName.uri == null ? "src-resolve.4.1" : "src-resolve.4.2", new Object[]{this.fDoc2SystemId.get(xSDocumentInfo.fSchemaElement), qName.uri, qName.rawname}, element);
        }
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName.uri);
        Element elementFromMap = null;
        if (grammar == null) {
            if (needReportTNSError(qName.uri)) {
                reportSchemaError("src-resolve", new Object[]{qName.rawname, COMP_TYPE[i]}, element);
            }
            return null;
        }
        Object globalDeclFromGrammar = getGlobalDeclFromGrammar(grammar, i, qName.localpart);
        if (qName.uri == null) {
            str = "," + qName.localpart;
        } else {
            str = qName.uri + "," + qName.localpart;
        }
        if (this.fTolerateDuplicates) {
            Object globalDecl = getGlobalDecl(str, i);
            if (globalDecl != null) {
                return globalDecl;
            }
        } else if (globalDeclFromGrammar != null) {
            return globalDeclFromGrammar;
        }
        switch (i) {
            case 1:
                elementFromMap = getElementFromMap(this.fUnparsedAttributeRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedAttributeRegistrySub, str);
                break;
            case 2:
                elementFromMap = getElementFromMap(this.fUnparsedAttributeGroupRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedAttributeGroupRegistrySub, str);
                break;
            case 3:
                elementFromMap = getElementFromMap(this.fUnparsedElementRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedElementRegistrySub, str);
                break;
            case 4:
                elementFromMap = getElementFromMap(this.fUnparsedGroupRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedGroupRegistrySub, str);
                break;
            case 5:
                elementFromMap = getElementFromMap(this.fUnparsedIdentityConstraintRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedIdentityConstraintRegistrySub, str);
                break;
            case 6:
                elementFromMap = getElementFromMap(this.fUnparsedNotationRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedNotationRegistrySub, str);
                break;
            case 7:
                elementFromMap = getElementFromMap(this.fUnparsedTypeRegistry, str);
                docInfoFromMap = getDocInfoFromMap(this.fUnparsedTypeRegistrySub, str);
                break;
            default:
                reportSchemaError("Internal-Error", new Object[]{"XSDHandler asked to locate component of type " + i + "; it does not recognize this type!"}, element);
                docInfoFromMap = null;
                break;
        }
        if (elementFromMap != null) {
            XSDocumentInfo xSDocumentInfoFindXSDocumentForDecl = findXSDocumentForDecl(xSDocumentInfo, elementFromMap, docInfoFromMap);
            if (xSDocumentInfoFindXSDocumentForDecl == null) {
                if (globalDeclFromGrammar == null) {
                    reportSchemaError(qName.uri == null ? "src-resolve.4.1" : "src-resolve.4.2", new Object[]{this.fDoc2SystemId.get(xSDocumentInfo.fSchemaElement), qName.uri, qName.rawname}, element);
                    return globalDeclFromGrammar;
                }
            } else {
                if (!DOMUtil.isHidden(elementFromMap, this.fHiddenNodes)) {
                    return traverseGlobalDecl(i, elementFromMap, xSDocumentInfoFindXSDocumentForDecl, grammar);
                }
                if (globalDeclFromGrammar == null) {
                    String str3 = CIRCULAR_CODES[i];
                    if (i == 7 && SchemaSymbols.ELT_COMPLEXTYPE.equals(DOMUtil.getLocalName(elementFromMap))) {
                        str3 = "ct-props-correct.3";
                    }
                    reportSchemaError(str3, new Object[]{qName.prefix + ":" + qName.localpart}, element);
                }
            }
        } else if (globalDeclFromGrammar == null) {
            reportSchemaError("src-resolve", new Object[]{qName.rawname, COMP_TYPE[i]}, element);
            return globalDeclFromGrammar;
        }
        return globalDeclFromGrammar;
    }

    public Object getGlobalDeclFromGrammar(SchemaGrammar schemaGrammar, int i, String str) {
        switch (i) {
            case 1:
                return schemaGrammar.getGlobalAttributeDecl(str);
            case 2:
                return schemaGrammar.getGlobalAttributeGroupDecl(str);
            case 3:
                return schemaGrammar.getGlobalElementDecl(str);
            case 4:
                return schemaGrammar.getGlobalGroupDecl(str);
            case 5:
                return schemaGrammar.getIDConstraintDecl(str);
            case 6:
                return schemaGrammar.getGlobalNotationDecl(str);
            case 7:
                return schemaGrammar.getGlobalTypeDecl(str);
            default:
                return null;
        }
    }

    public SchemaGrammar getGrammar(String str) {
        return this.fGrammarBucket.getGrammar(str);
    }

    public Object getGrpOrAttrGrpRedefinedByRestriction(int i, QName qName, XSDocumentInfo xSDocumentInfo, Element element) {
        String str;
        String str2;
        if (qName.uri != null) {
            str = qName.uri + "," + qName.localpart;
        } else {
            str = "," + qName.localpart;
        }
        if (i == 2) {
            str2 = this.fRedefinedRestrictedAttributeGroupRegistry.get(str);
        } else {
            if (i != 4) {
                return null;
            }
            str2 = this.fRedefinedRestrictedGroupRegistry.get(str);
        }
        if (str2 == null) {
            return null;
        }
        int iIndexOf = str2.indexOf(",");
        Object globalDecl = getGlobalDecl(xSDocumentInfo, i, new QName(XMLSymbols.EMPTY_STRING, str2.substring(iIndexOf + 1), str2.substring(iIndexOf), iIndexOf == 0 ? null : str2.substring(0, iIndexOf)), element);
        if (globalDecl != null) {
            return globalDecl;
        }
        if (i == 2) {
            reportSchemaError("src-redefine.7.2.1", new Object[]{qName.localpart}, element);
        } else if (i == 4) {
            reportSchemaError("src-redefine.6.2.1", new Object[]{qName.localpart}, element);
        }
        return null;
    }

    public Map<String, Element> getIDRegistry() {
        return this.fUnparsedIdentityConstraintRegistry;
    }

    public Map<String, XSDocumentInfo> getIDRegistry_sub() {
        return this.fUnparsedIdentityConstraintRegistrySub;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x018d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0194  */
    /* JADX WARN: Code duplicated, block: B:105:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:121:0x01ab A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:124:0x01a5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:56:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:68:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:73:0x0101  */
    /* JADX WARN: Code duplicated, block: B:84:0x0133  */
    /* JADX WARN: Code duplicated, block: B:87:0x0141  */
    /* JADX WARN: Code duplicated, block: B:88:0x0143  */
    /* JADX WARN: Code duplicated, block: B:91:0x014c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:92:0x014d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0154  */
    /* JADX WARN: Code duplicated, block: B:97:0x016b  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004b, code lost:
    
        if (r0.getDocumentLocations().contains(com.sun.org.apache.xerces.internal.impl.XMLEntityManager.expandSystemId(r11.getSystemId(), r11.getBaseSystemId(), false)) != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SchemaGrammar parseSchema(XMLInputSource xMLInputSource, XSDDescription xSDDescription, Map<String, XMLSchemaLoader.LocationArray> map) throws IOException {
        String targetNamespace;
        SchemaGrammar schemaGrammarFindGrammar;
        XSDHandler xSDHandler;
        XMLInputSource xMLInputSource2;
        Element schemaDocument;
        boolean z;
        XSDocumentInfo xSDocumentInfoConstructTrees;
        ArrayList arrayList;
        int size;
        List<String> list;
        ArrayList arrayList2;
        SchemaGrammar grammar;
        int i;
        SchemaGrammar grammar2;
        String attrValue;
        String strAddSymbol;
        String strExpandSystemId;
        SchemaGrammar[] grammars;
        this.fLocationPairs = map;
        this.fSchemaParser.resetNodePool();
        short contextType = xSDDescription.getContextType();
        if (contextType != 3) {
            SchemaGrammar grammar3 = (this.fHonourAllSchemaLocations && contextType == 2 && isExistingGrammar(xSDDescription, this.fNamespaceGrowth)) ? this.fGrammarBucket.getGrammar(xSDDescription.getTargetNamespace()) : findGrammar(xSDDescription, this.fNamespaceGrowth);
            if (grammar3 != null) {
                if (this.fNamespaceGrowth) {
                }
                return grammar3;
            }
            targetNamespace = xSDDescription.getTargetNamespace();
            if (targetNamespace != null) {
                targetNamespace = this.fSymbolTable.addSymbol(targetNamespace);
            }
            schemaGrammarFindGrammar = grammar3;
        } else {
            targetNamespace = null;
            schemaGrammarFindGrammar = null;
        }
        prepareForParse();
        if (xMLInputSource instanceof DOMInputSource) {
            xSDHandler = this;
            schemaDocument = xSDHandler.getSchemaDocument(targetNamespace, (DOMInputSource) xMLInputSource, contextType == 3, contextType, (Element) null);
        } else {
            xSDHandler = this;
            if (xMLInputSource instanceof SAXInputSource) {
                schemaDocument = xSDHandler.getSchemaDocument(targetNamespace, (SAXInputSource) xMLInputSource, contextType == 3, contextType, (Element) null);
            } else {
                if (!(xMLInputSource instanceof StAXInputSource)) {
                    if (xMLInputSource instanceof XSInputSource) {
                        schemaDocument = xSDHandler.getSchemaDocument((XSInputSource) xMLInputSource, xSDDescription);
                    } else {
                        xMLInputSource2 = xMLInputSource;
                        schemaDocument = xSDHandler.getSchemaDocument(targetNamespace, xMLInputSource2, contextType == 3, contextType, (Element) null);
                    }
                    if (schemaDocument == null) {
                        if (xMLInputSource2 instanceof XSInputSource) {
                            return schemaGrammarFindGrammar;
                        }
                        XSInputSource xSInputSource = (XSInputSource) xMLInputSource2;
                        grammars = xSInputSource.getGrammars();
                        if (grammars == null && grammars.length > 0) {
                            return xSDHandler.fGrammarBucket.getGrammar(grammars[0].getTargetNamespace());
                        }
                        XSObject[] components = xSInputSource.getComponents();
                        return (components == null || components.length <= 0) ? schemaGrammarFindGrammar : xSDHandler.fGrammarBucket.getGrammar(components[0].getNamespace());
                    }
                    if (contextType == 3) {
                        attrValue = DOMUtil.getAttrValue(schemaDocument, SchemaSymbols.ATT_TARGETNAMESPACE);
                        if (attrValue != null || attrValue.length() <= 0) {
                            strAddSymbol = null;
                        } else {
                            strAddSymbol = xSDHandler.fSymbolTable.addSymbol(attrValue);
                            xSDDescription.setTargetNamespace(strAddSymbol);
                        }
                        schemaGrammarFindGrammar = xSDHandler.findGrammar(xSDDescription, xSDHandler.fNamespaceGrowth);
                        strExpandSystemId = XMLEntityManager.expandSystemId(xMLInputSource2.getSystemId(), xMLInputSource2.getBaseSystemId(), false);
                        if (schemaGrammarFindGrammar == null && (!xSDHandler.fNamespaceGrowth || (strExpandSystemId != null && schemaGrammarFindGrammar.getDocumentLocations().contains(strExpandSystemId)))) {
                            return schemaGrammarFindGrammar;
                        }
                        xSDHandler.fTraversed.put(new XSDKey(strExpandSystemId, contextType, strAddSymbol), schemaDocument);
                        if (strExpandSystemId != null) {
                            xSDHandler.fDoc2SystemId.put(schemaDocument, strExpandSystemId);
                        }
                    }
                    xSDHandler.prepareForTraverse();
                    String systemId = xMLInputSource2.getSystemId();
                    if (schemaGrammarFindGrammar != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    xSDocumentInfoConstructTrees = xSDHandler.constructTrees(schemaDocument, systemId, xSDDescription, z);
                    xSDHandler.fRoot = xSDocumentInfoConstructTrees;
                    if (xSDocumentInfoConstructTrees == null) {
                        return null;
                    }
                    xSDHandler.buildGlobalNameRegistries();
                    arrayList = xSDHandler.fValidateAnnotations ? new ArrayList() : null;
                    xSDHandler.traverseSchemas(arrayList);
                    xSDHandler.traverseLocalElements();
                    xSDHandler.resolveKeyRefs();
                    for (size = xSDHandler.fAllTNSs.size() - 1; size >= 0; size--) {
                        String str = xSDHandler.fAllTNSs.get(size);
                        list = xSDHandler.fImportMap.get(str);
                        arrayList2 = new ArrayList();
                        grammar = xSDHandler.fGrammarBucket.getGrammar(xSDHandler.emptyString2Null(str));
                        if (grammar == null) {
                            for (i = 0; i < list.size(); i++) {
                                grammar2 = xSDHandler.fGrammarBucket.getGrammar(list.get(i));
                                if (grammar2 != null) {
                                    arrayList2.add(grammar2);
                                }
                            }
                            grammar.setImportedGrammars(arrayList2);
                        }
                    }
                    if (xSDHandler.fValidateAnnotations && arrayList.size() > 0) {
                        xSDHandler.validateAnnotations(arrayList);
                    }
                    return xSDHandler.fGrammarBucket.getGrammar(xSDHandler.fRoot.fTargetNamespace);
                }
                schemaDocument = xSDHandler.getSchemaDocument(targetNamespace, (StAXInputSource) xMLInputSource, contextType == 3, contextType, (Element) null);
            }
        }
        xMLInputSource2 = xMLInputSource;
        if (schemaDocument == null) {
            if (xMLInputSource2 instanceof XSInputSource) {
                return schemaGrammarFindGrammar;
            }
            XSInputSource xSInputSource2 = (XSInputSource) xMLInputSource2;
            grammars = xSInputSource2.getGrammars();
            if (grammars == null) {
            }
            XSObject[] components2 = xSInputSource2.getComponents();
            if (components2 == null) {
                return schemaGrammarFindGrammar;
            }
        }
        if (contextType == 3) {
            attrValue = DOMUtil.getAttrValue(schemaDocument, SchemaSymbols.ATT_TARGETNAMESPACE);
            if (attrValue != null) {
                strAddSymbol = null;
            } else {
                strAddSymbol = null;
            }
            schemaGrammarFindGrammar = xSDHandler.findGrammar(xSDDescription, xSDHandler.fNamespaceGrowth);
            strExpandSystemId = XMLEntityManager.expandSystemId(xMLInputSource2.getSystemId(), xMLInputSource2.getBaseSystemId(), false);
            if (schemaGrammarFindGrammar == null) {
            }
            xSDHandler.fTraversed.put(new XSDKey(strExpandSystemId, contextType, strAddSymbol), schemaDocument);
            if (strExpandSystemId != null) {
                xSDHandler.fDoc2SystemId.put(schemaDocument, strExpandSystemId);
            }
        }
        xSDHandler.prepareForTraverse();
        String systemId2 = xMLInputSource2.getSystemId();
        if (schemaGrammarFindGrammar != null) {
            z = true;
        } else {
            z = false;
        }
        xSDocumentInfoConstructTrees = xSDHandler.constructTrees(schemaDocument, systemId2, xSDDescription, z);
        xSDHandler.fRoot = xSDocumentInfoConstructTrees;
        if (xSDocumentInfoConstructTrees == null) {
            return null;
        }
        xSDHandler.buildGlobalNameRegistries();
        if (xSDHandler.fValidateAnnotations) {
        }
        xSDHandler.traverseSchemas(arrayList);
        xSDHandler.traverseLocalElements();
        xSDHandler.resolveKeyRefs();
        while (size >= 0) {
            String str2 = xSDHandler.fAllTNSs.get(size);
            list = xSDHandler.fImportMap.get(str2);
            arrayList2 = new ArrayList();
            grammar = xSDHandler.fGrammarBucket.getGrammar(xSDHandler.emptyString2Null(str2));
            if (grammar == null) {
                while (i < list.size()) {
                    grammar2 = xSDHandler.fGrammarBucket.getGrammar(list.get(i));
                    if (grammar2 != null) {
                        arrayList2.add(grammar2);
                    }
                }
                grammar.setImportedGrammars(arrayList2);
            }
        }
        if (xSDHandler.fValidateAnnotations) {
            xSDHandler.validateAnnotations(arrayList);
        }
        return xSDHandler.fGrammarBucket.getGrammar(xSDHandler.fRoot.fTargetNamespace);
    }

    public void prepareForParse() {
        this.fTraversed.clear();
        this.fDoc2SystemId.clear();
        this.fHiddenNodes.clear();
        this.fLastSchemaWasDuplicate = false;
    }

    public void prepareForTraverse() {
        if (!this.registryEmpty) {
            this.fUnparsedAttributeRegistry.clear();
            this.fUnparsedAttributeGroupRegistry.clear();
            this.fUnparsedElementRegistry.clear();
            this.fUnparsedGroupRegistry.clear();
            this.fUnparsedIdentityConstraintRegistry.clear();
            this.fUnparsedNotationRegistry.clear();
            this.fUnparsedTypeRegistry.clear();
            this.fUnparsedAttributeRegistrySub.clear();
            this.fUnparsedAttributeGroupRegistrySub.clear();
            this.fUnparsedElementRegistrySub.clear();
            this.fUnparsedGroupRegistrySub.clear();
            this.fUnparsedIdentityConstraintRegistrySub.clear();
            this.fUnparsedNotationRegistrySub.clear();
            this.fUnparsedTypeRegistrySub.clear();
        }
        for (int i = 1; i <= 7; i++) {
            Map<String, XSDocumentInfo> map = this.fUnparsedRegistriesExt[i];
            if (map != null) {
                map.clear();
            }
        }
        this.fDependencyMap.clear();
        this.fDoc2XSDocumentMap.clear();
        Map<Element, XSDocumentInfo> map2 = this.fRedefine2XSDMap;
        if (map2 != null) {
            map2.clear();
        }
        Map<Element, SchemaNamespaceSupport> map3 = this.fRedefine2NSSupport;
        if (map3 != null) {
            map3.clear();
        }
        this.fAllTNSs.clear();
        this.fImportMap.clear();
        this.fRoot = null;
        for (int i2 = 0; i2 < this.fLocalElemStackPos; i2++) {
            this.fParticle[i2] = null;
            this.fLocalElementDecl[i2] = null;
            this.fLocalElementDecl_schema[i2] = null;
            this.fLocalElemNamespaceContext[i2] = null;
        }
        this.fLocalElemStackPos = 0;
        for (int i3 = 0; i3 < this.fKeyrefStackPos; i3++) {
            this.fKeyrefs[i3] = null;
            this.fKeyrefElems[i3] = null;
            this.fKeyrefNamespaceContext[i3] = null;
            this.fKeyrefsMapXSDocumentInfo[i3] = null;
        }
        this.fKeyrefStackPos = 0;
        if (this.fAttributeChecker == null) {
            createTraversers();
        }
        Locale locale = this.fErrorReporter.getLocale();
        this.fAttributeChecker.reset(this.fSymbolTable);
        this.fAttributeGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fAttributeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fComplexTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fElementTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fGroupTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fKeyrefTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fNotationTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fSimpleTypeTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fUniqueOrKeyTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fWildCardTraverser.reset(this.fSymbolTable, this.fValidateAnnotations, locale);
        this.fRedefinedRestrictedAttributeGroupRegistry.clear();
        this.fRedefinedRestrictedGroupRegistry.clear();
        this.fGlobalAttrDecls.clear();
        this.fGlobalAttrGrpDecls.clear();
        this.fGlobalElemDecls.clear();
        this.fGlobalGroupDecls.clear();
        this.fGlobalNotationDecls.clear();
        this.fGlobalIDConstraintDecls.clear();
        this.fGlobalTypeDecls.clear();
    }

    public void reportSchemaErr(String str, Object[] objArr, Element element, short s, Exception exc) {
        boolean zElement2Locator = element2Locator(element, this.xl);
        XMLErrorReporter xMLErrorReporter = this.fErrorReporter;
        if (zElement2Locator) {
            xMLErrorReporter.reportError(this.xl, XSMessageFormatter.SCHEMA_DOMAIN, str, objArr, s, exc);
        } else {
            xMLErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, str, objArr, s, exc);
        }
    }

    public void reportSchemaError(String str, Object[] objArr, Element element) {
        reportSchemaErr(str, objArr, element, (short) 1, null);
    }

    public void reportSchemaFatalError(String str, Object[] objArr, Element element) {
        reportSchemaErr(str, objArr, element, (short) 2, null);
    }

    public void reportSchemaWarning(String str, Object[] objArr, Element element) {
        reportSchemaErr(str, objArr, element, (short) 0, null);
    }

    public void reset(XMLComponentManager xMLComponentManager) {
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fSecurityManager = (XMLSecurityManager) xMLComponentManager.getProperty("http://apache.org/xml/properties/security-manager", null);
        this.fEntityManager = (XMLEntityManager) xMLComponentManager.getProperty(ENTITY_MANAGER);
        XMLEntityResolver xMLEntityResolver = (XMLEntityResolver) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
        if (xMLEntityResolver != null) {
            this.fSchemaParser.setEntityResolver(xMLEntityResolver);
        }
        XMLErrorReporter xMLErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fErrorReporter = xMLErrorReporter;
        this.fErrorHandler = xMLErrorReporter.getErrorHandler();
        this.fLocale = this.fErrorReporter.getLocale();
        this.fValidateAnnotations = xMLComponentManager.getFeature(VALIDATE_ANNOTATIONS, false);
        this.fHonourAllSchemaLocations = xMLComponentManager.getFeature(HONOUR_ALL_SCHEMALOCATIONS, false);
        this.fNamespaceGrowth = xMLComponentManager.getFeature(NAMESPACE_GROWTH, false);
        this.fTolerateDuplicates = xMLComponentManager.getFeature(TOLERATE_DUPLICATES, false);
        try {
            if (this.fErrorHandler != this.fSchemaParser.getProperty("http://apache.org/xml/properties/internal/error-handler")) {
                SchemaDOMParser schemaDOMParser = this.fSchemaParser;
                Object defaultErrorHandler = this.fErrorHandler;
                if (defaultErrorHandler == null) {
                    defaultErrorHandler = new DefaultErrorHandler();
                }
                schemaDOMParser.setProperty("http://apache.org/xml/properties/internal/error-handler", defaultErrorHandler);
                XML11Configuration xML11Configuration = this.fAnnotationValidator;
                if (xML11Configuration != null) {
                    Object defaultErrorHandler2 = this.fErrorHandler;
                    if (defaultErrorHandler2 == null) {
                        defaultErrorHandler2 = new DefaultErrorHandler();
                    }
                    xML11Configuration.setProperty("http://apache.org/xml/properties/internal/error-handler", defaultErrorHandler2);
                }
            }
            if (this.fLocale != this.fSchemaParser.getProperty("http://apache.org/xml/properties/locale")) {
                this.fSchemaParser.setProperty("http://apache.org/xml/properties/locale", this.fLocale);
                XML11Configuration xML11Configuration2 = this.fAnnotationValidator;
                if (xML11Configuration2 != null) {
                    xML11Configuration2.setProperty("http://apache.org/xml/properties/locale", this.fLocale);
                }
            }
        } catch (XMLConfigurationException unused) {
        }
        try {
            this.fSchemaParser.setFeature(CONTINUE_AFTER_FATAL_ERROR, this.fErrorReporter.getFeature(CONTINUE_AFTER_FATAL_ERROR));
        } catch (XMLConfigurationException unused2) {
        }
        try {
            if (xMLComponentManager.getFeature(ALLOW_JAVA_ENCODINGS, false)) {
                this.fSchemaParser.setFeature(ALLOW_JAVA_ENCODINGS, true);
            }
        } catch (XMLConfigurationException unused3) {
        }
        try {
            if (xMLComponentManager.getFeature(STANDARD_URI_CONFORMANT_FEATURE, false)) {
                this.fSchemaParser.setFeature(STANDARD_URI_CONFORMANT_FEATURE, true);
            }
        } catch (XMLConfigurationException unused4) {
        }
        try {
            this.fGrammarPool = (XMLGrammarPool) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool");
        } catch (XMLConfigurationException unused5) {
            this.fGrammarPool = null;
        }
        try {
            if (xMLComponentManager.getFeature(DISALLOW_DOCTYPE, false)) {
                this.fSchemaParser.setFeature(DISALLOW_DOCTYPE, true);
            }
        } catch (XMLConfigurationException unused6) {
        }
        try {
            XMLSecurityManager xMLSecurityManager = this.fSecurityManager;
            if (xMLSecurityManager != null) {
                this.fSchemaParser.setProperty("http://apache.org/xml/properties/security-manager", xMLSecurityManager);
            }
        } catch (XMLConfigurationException unused7) {
        }
        XMLSecurityPropertyManager xMLSecurityPropertyManager = (XMLSecurityPropertyManager) xMLComponentManager.getProperty("jdk.xml.xmlSecurityPropertyManager");
        this.fSecurityPropertyMgr = xMLSecurityPropertyManager;
        this.fSchemaParser.setProperty("jdk.xml.xmlSecurityPropertyManager", xMLSecurityPropertyManager);
        this.fAccessExternalDTD = this.fSecurityPropertyMgr.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_DTD);
        this.fAccessExternalSchema = this.fSecurityPropertyMgr.getValue(XMLSecurityPropertyManager.Property.ACCESS_EXTERNAL_SCHEMA);
        boolean feature = xMLComponentManager.getFeature(JdkConstants.OVERRIDE_PARSER);
        this.fOverrideDefaultParser = feature;
        this.fSchemaParser.setFeature(JdkConstants.OVERRIDE_PARSER, feature);
        this.fEntityManager.setFeature(JdkConstants.OVERRIDE_PARSER, this.fOverrideDefaultParser);
        boolean feature2 = xMLComponentManager.getFeature("http://javax.xml.XMLConstants/feature/useCatalog");
        this.fUseCatalog = feature2;
        this.fSchemaParser.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", feature2);
        this.fEntityManager.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", this.fUseCatalog);
        this.fCatalogFile = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_FILES);
        this.fDefer = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_DEFER);
        this.fPrefer = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_PREFER);
        this.fResolve = (String) xMLComponentManager.getProperty(JdkXmlUtils.CATALOG_RESOLVE);
        for (CatalogFeatures.Feature feature3 : CatalogFeatures.Feature.values()) {
            this.fSchemaParser.setProperty(feature3.getPropertyName(), xMLComponentManager.getProperty(feature3.getPropertyName()));
            this.fEntityManager.setProperty(feature3.getPropertyName(), xMLComponentManager.getProperty(feature3.getPropertyName()));
        }
        this.fSchemaParser.setProperty(JdkConstants.CDATA_CHUNK_SIZE, xMLComponentManager.getProperty(JdkConstants.CDATA_CHUNK_SIZE));
        this.fEntityManager.setProperty(JdkConstants.CDATA_CHUNK_SIZE, xMLComponentManager.getProperty(JdkConstants.CDATA_CHUNK_SIZE));
    }

    public void resolveKeyRefs() {
        for (int i = 0; i < this.fKeyrefStackPos; i++) {
            XSDocumentInfo xSDocumentInfo = this.fKeyrefsMapXSDocumentInfo[i];
            xSDocumentInfo.fNamespaceSupport.makeGlobal();
            xSDocumentInfo.fNamespaceSupport.setEffectiveContext(this.fKeyrefNamespaceContext[i]);
            SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
            DOMUtil.setHidden(this.fKeyrefs[i], this.fHiddenNodes);
            this.fKeyrefTraverser.traverse(this.fKeyrefs[i], this.fKeyrefElems[i], xSDocumentInfo, grammar);
        }
    }

    public String schemaDocument2SystemId(XSDocumentInfo xSDocumentInfo) {
        return this.fDoc2SystemId.get(xSDocumentInfo.fSchemaElement);
    }

    public void setDVFactory(SchemaDVFactory schemaDVFactory) {
        this.fDVFactory = schemaDVFactory;
    }

    public void setDeclPool(XSDeclarationPool xSDeclarationPool) {
        this.fDeclPool = xSDeclarationPool;
    }

    public void setGenerateSyntheticAnnotations(boolean z) {
        this.fSchemaParser.setFeature("http://apache.org/xml/features/generate-synthetic-annotations", z);
    }

    public void storeKeyRef(Element element, XSDocumentInfo xSDocumentInfo, XSElementDecl xSElementDecl) {
        XSDHandler xSDHandler;
        Element element2;
        XSDocumentInfo xSDocumentInfo2;
        String strConcat;
        String attrValue = DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME);
        if (attrValue.length() != 0) {
            if (xSDocumentInfo.fTargetNamespace == null) {
                strConcat = ",".concat(attrValue);
            } else {
                strConcat = xSDocumentInfo.fTargetNamespace + "," + attrValue;
            }
            xSDHandler = this;
            element2 = element;
            xSDocumentInfo2 = xSDocumentInfo;
            xSDHandler.checkForDuplicateNames(strConcat, 5, this.fUnparsedIdentityConstraintRegistry, this.fUnparsedIdentityConstraintRegistrySub, element2, xSDocumentInfo2);
        } else {
            xSDHandler = this;
            element2 = element;
            xSDocumentInfo2 = xSDocumentInfo;
        }
        int i = xSDHandler.fKeyrefStackPos;
        Element[] elementArr = xSDHandler.fKeyrefs;
        if (i == elementArr.length) {
            Element[] elementArr2 = new Element[i + 2];
            System.arraycopy(elementArr, 0, elementArr2, 0, i);
            xSDHandler.fKeyrefs = elementArr2;
            int i2 = xSDHandler.fKeyrefStackPos;
            XSElementDecl[] xSElementDeclArr = new XSElementDecl[i2 + 2];
            System.arraycopy(xSDHandler.fKeyrefElems, 0, xSElementDeclArr, 0, i2);
            xSDHandler.fKeyrefElems = xSElementDeclArr;
            int i3 = xSDHandler.fKeyrefStackPos;
            String[][] strArr = new String[i3 + 2][];
            System.arraycopy(xSDHandler.fKeyrefNamespaceContext, 0, strArr, 0, i3);
            xSDHandler.fKeyrefNamespaceContext = strArr;
            int i4 = xSDHandler.fKeyrefStackPos;
            XSDocumentInfo[] xSDocumentInfoArr = new XSDocumentInfo[i4 + 2];
            System.arraycopy(xSDHandler.fKeyrefsMapXSDocumentInfo, 0, xSDocumentInfoArr, 0, i4);
            xSDHandler.fKeyrefsMapXSDocumentInfo = xSDocumentInfoArr;
        }
        Element[] elementArr3 = xSDHandler.fKeyrefs;
        int i5 = xSDHandler.fKeyrefStackPos;
        elementArr3[i5] = element2;
        xSDHandler.fKeyrefElems[i5] = xSElementDecl;
        xSDHandler.fKeyrefNamespaceContext[i5] = xSDocumentInfo2.fNamespaceSupport.getEffectiveLocalContext();
        XSDocumentInfo[] xSDocumentInfoArr2 = xSDHandler.fKeyrefsMapXSDocumentInfo;
        int i6 = xSDHandler.fKeyrefStackPos;
        xSDHandler.fKeyrefStackPos = i6 + 1;
        xSDocumentInfoArr2[i6] = xSDocumentInfo2;
    }

    public Object traverseGlobalDecl(int i, Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Map<Element, SchemaNamespaceSupport> map;
        DOMUtil.setHidden(element, this.fHiddenNodes);
        Element parent = DOMUtil.getParent(element);
        Object objTraverseGlobal = null;
        xSDocumentInfo.backupNSSupport((!DOMUtil.getLocalName(parent).equals(SchemaSymbols.ELT_REDEFINE) || (map = this.fRedefine2NSSupport) == null) ? null : map.get(parent));
        if (i == 1) {
            objTraverseGlobal = this.fAttributeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
        } else if (i == 2) {
            objTraverseGlobal = this.fAttributeGroupTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
        } else if (i == 3) {
            objTraverseGlobal = this.fElementTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
        } else if (i == 4) {
            objTraverseGlobal = this.fGroupTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
        } else if (i == 6) {
            objTraverseGlobal = this.fNotationTraverser.traverse(element, xSDocumentInfo, schemaGrammar);
        } else if (i == 7) {
            objTraverseGlobal = DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_COMPLEXTYPE) ? this.fComplexTypeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar) : this.fSimpleTypeTraverser.traverseGlobal(element, xSDocumentInfo, schemaGrammar);
        }
        xSDocumentInfo.restoreNSSupport();
        return objTraverseGlobal;
    }

    public void traverseLocalElements() {
        XSModelGroupImpl xSModelGroupImpl;
        this.fElementTraverser.fDeferTraversingLocalElements = false;
        for (int i = 0; i < this.fLocalElemStackPos; i++) {
            Element element = this.fLocalElementDecl[i];
            XSDocumentInfo xSDocumentInfo = this.fLocalElementDecl_schema[i];
            this.fElementTraverser.traverseLocal(this.fParticle[i], element, xSDocumentInfo, this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace), this.fAllContext[i], this.fParent[i], this.fLocalElemNamespaceContext[i]);
            if (this.fParticle[i].fType == 0) {
                XSObject xSObject = this.fParent[i];
                if (xSObject instanceof XSComplexTypeDecl) {
                    XSParticle particle = ((XSComplexTypeDecl) xSObject).getParticle();
                    xSModelGroupImpl = particle != null ? (XSModelGroupImpl) particle.getTerm() : null;
                } else {
                    xSModelGroupImpl = ((XSGroupDecl) xSObject).fModelGroup;
                }
                if (xSModelGroupImpl != null) {
                    removeParticle(xSModelGroupImpl, this.fParticle[i]);
                }
            }
        }
    }

    public void traverseSchemas(List<Object> list) {
        XSAnnotationInfo annotations;
        String syntheticAnnotation;
        setSchemasVisible(this.fRoot);
        Stack stack = new Stack();
        stack.push(this.fRoot);
        while (!stack.empty()) {
            XSDocumentInfo xSDocumentInfo = (XSDocumentInfo) stack.pop();
            Element element = xSDocumentInfo.fSchemaElement;
            SchemaGrammar grammar = this.fGrammarBucket.getGrammar(xSDocumentInfo.fTargetNamespace);
            if (!DOMUtil.isHidden(element, this.fHiddenNodes)) {
                Element firstVisibleChildElement = DOMUtil.getFirstVisibleChildElement(element, this.fHiddenNodes);
                boolean z = false;
                while (firstVisibleChildElement != null) {
                    DOMUtil.setHidden(firstVisibleChildElement, this.fHiddenNodes);
                    String localName = DOMUtil.getLocalName(firstVisibleChildElement);
                    if (DOMUtil.getLocalName(firstVisibleChildElement).equals(SchemaSymbols.ELT_REDEFINE)) {
                        Map<Element, SchemaNamespaceSupport> map = this.fRedefine2NSSupport;
                        xSDocumentInfo.backupNSSupport(map != null ? map.get(firstVisibleChildElement) : null);
                        Element firstVisibleChildElement2 = DOMUtil.getFirstVisibleChildElement(firstVisibleChildElement, this.fHiddenNodes);
                        while (firstVisibleChildElement2 != null) {
                            String localName2 = DOMUtil.getLocalName(firstVisibleChildElement2);
                            DOMUtil.setHidden(firstVisibleChildElement2, this.fHiddenNodes);
                            if (localName2.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                                this.fAttributeGroupTraverser.traverseGlobal(firstVisibleChildElement2, xSDocumentInfo, grammar);
                            } else if (localName2.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                                this.fComplexTypeTraverser.traverseGlobal(firstVisibleChildElement2, xSDocumentInfo, grammar);
                            } else if (localName2.equals(SchemaSymbols.ELT_GROUP)) {
                                this.fGroupTraverser.traverseGlobal(firstVisibleChildElement2, xSDocumentInfo, grammar);
                            } else if (localName2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                                this.fSimpleTypeTraverser.traverseGlobal(firstVisibleChildElement2, xSDocumentInfo, grammar);
                            } else {
                                reportSchemaError("s4s-elt-must-match.1", new Object[]{DOMUtil.getLocalName(firstVisibleChildElement), "(annotation | (simpleType | complexType | group | attributeGroup))*", localName2}, firstVisibleChildElement2);
                            }
                            firstVisibleChildElement2 = DOMUtil.getNextVisibleSiblingElement(firstVisibleChildElement2, this.fHiddenNodes);
                        }
                        xSDocumentInfo.restoreNSSupport();
                    } else if (localName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                        this.fAttributeTraverser.traverseGlobal(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                        this.fAttributeGroupTraverser.traverseGlobal(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                        this.fComplexTypeTraverser.traverseGlobal(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                        this.fElementTraverser.traverseGlobal(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                        this.fGroupTraverser.traverseGlobal(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_NOTATION)) {
                        this.fNotationTraverser.traverse(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                        this.fSimpleTypeTraverser.traverseGlobal(firstVisibleChildElement, xSDocumentInfo, grammar);
                    } else if (localName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                        grammar.addAnnotation(this.fElementTraverser.traverseAnnotationDecl(firstVisibleChildElement, xSDocumentInfo.getSchemaAttrs(), true, xSDocumentInfo));
                        z = true;
                    } else {
                        reportSchemaError("s4s-elt-invalid-content.1", new Object[]{SchemaSymbols.ELT_SCHEMA, DOMUtil.getLocalName(firstVisibleChildElement)}, firstVisibleChildElement);
                    }
                    firstVisibleChildElement = DOMUtil.getNextVisibleSiblingElement(firstVisibleChildElement, this.fHiddenNodes);
                }
                if (!z && (syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element)) != null) {
                    grammar.addAnnotation(this.fElementTraverser.traverseSyntheticAnnotation(element, syntheticAnnotation, xSDocumentInfo.getSchemaAttrs(), true, xSDocumentInfo));
                }
                if (list != null && (annotations = xSDocumentInfo.getAnnotations()) != null) {
                    list.add(doc2SystemId(element));
                    list.add(annotations);
                }
                xSDocumentInfo.returnSchemaAttrs();
                DOMUtil.setHidden(element, this.fHiddenNodes);
                Iterator<XSDocumentInfo> it = this.fDependencyMap.get(xSDocumentInfo).iterator();
                while (it.hasNext()) {
                    stack.push(it.next());
                }
            }
        }
    }

    public void reportSchemaError(String str, Object[] objArr, Element element, Exception exc) {
        reportSchemaErr(str, objArr, element, (short) 1, exc);
    }

    public void reportSchemaWarning(String str, Object[] objArr, Element element, Exception exc) {
        reportSchemaErr(str, objArr, element, (short) 0, exc);
    }

    private void updateImportList(List<SchemaGrammar> list, List<SchemaGrammar> list2) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SchemaGrammar schemaGrammar = list.get(i);
            if (!containedImportedGrammar(list2, schemaGrammar)) {
                list2.add(schemaGrammar);
            }
        }
    }

    public Object getGlobalDeclFromGrammar(SchemaGrammar schemaGrammar, int i, String str, String str2) {
        switch (i) {
            case 1:
                return schemaGrammar.getGlobalAttributeDecl(str, str2);
            case 2:
                return schemaGrammar.getGlobalAttributeGroupDecl(str, str2);
            case 3:
                return schemaGrammar.getGlobalElementDecl(str, str2);
            case 4:
                return schemaGrammar.getGlobalGroupDecl(str, str2);
            case 5:
                return schemaGrammar.getIDConstraintDecl(str, str2);
            case 6:
                return schemaGrammar.getGlobalNotationDecl(str, str2);
            case 7:
                return schemaGrammar.getGlobalTypeDecl(str, str2);
            default:
                return null;
        }
    }

    public SimpleLocator element2Locator(Element element) {
        if (!(element instanceof ElementImpl)) {
            return null;
        }
        SimpleLocator simpleLocator = new SimpleLocator();
        if (element2Locator(element, simpleLocator)) {
            return simpleLocator;
        }
        return null;
    }

    private Element resolveSchema(XMLInputSource xMLInputSource, XSDDescription xSDDescription, boolean z, Element element) {
        if (xMLInputSource instanceof DOMInputSource) {
            return getSchemaDocument(xSDDescription.getTargetNamespace(), (DOMInputSource) xMLInputSource, z, xSDDescription.getContextType(), element);
        }
        if (xMLInputSource instanceof SAXInputSource) {
            return getSchemaDocument(xSDDescription.getTargetNamespace(), (SAXInputSource) xMLInputSource, z, xSDDescription.getContextType(), element);
        }
        if (xMLInputSource instanceof StAXInputSource) {
            return getSchemaDocument(xSDDescription.getTargetNamespace(), (StAXInputSource) xMLInputSource, z, xSDDescription.getContextType(), element);
        }
        if (xMLInputSource instanceof XSInputSource) {
            return getSchemaDocument((XSInputSource) xMLInputSource, xSDDescription);
        }
        return getSchemaDocument(xSDDescription.getTargetNamespace(), xMLInputSource, z, xSDDescription.getContextType(), element);
    }

    private Element getSchemaDocument(String str, XMLInputSource xMLInputSource, boolean z, short s, Element element) {
        boolean z2;
        String strExpandSystemId;
        XSDKey xSDKey;
        String strCheckAccess;
        IOException iOException = null;
        if (xMLInputSource != null) {
            try {
                if (xMLInputSource.getSystemId() == null) {
                    if (xMLInputSource.getByteStream() == null) {
                        if (xMLInputSource.getCharacterStream() != null) {
                        }
                        z2 = false;
                    }
                }
                if (s != 3) {
                    strExpandSystemId = XMLEntityManager.expandSystemId(xMLInputSource.getSystemId(), xMLInputSource.getBaseSystemId(), false);
                    xSDKey = new XSDKey(strExpandSystemId, s, str);
                    Element element2 = this.fTraversed.get(xSDKey);
                    if (element2 != null) {
                        this.fLastSchemaWasDuplicate = true;
                        return element2;
                    }
                    if (!xMLInputSource.isCreatedByResolver() && ((s == 2 || s == 0 || s == 1) && (strCheckAccess = SecuritySupport.checkAccess(strExpandSystemId, this.fAccessExternalSchema, "all")) != null)) {
                        reportSchemaFatalError("schema_reference.access", new Object[]{SecuritySupport.sanitizePath(strExpandSystemId), strCheckAccess}, element);
                    }
                } else {
                    strExpandSystemId = null;
                    xSDKey = null;
                }
                this.fSchemaParser.parse(xMLInputSource);
                Document document = this.fSchemaParser.getDocument();
                return getSchemaDocument0(xSDKey, strExpandSystemId, document != null ? DOMUtil.getRoot(document) : null);
            } catch (IOException e) {
                iOException = e;
                z2 = true;
            }
        } else {
            z2 = false;
        }
        return getSchemaDocument1(z, z2, xMLInputSource, element, iOException);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0023  */
    /* JADX WARN: Code duplicated, block: B:17:0x0034  */
    /* JADX WARN: Code duplicated, block: B:18:0x0036  */
    /* JADX WARN: Code duplicated, block: B:24:0x0045  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:38:0x006c  */
    /* JADX WARN: Code duplicated, block: B:41:0x0026 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private Element getSchemaDocument(String str, DOMInputSource dOMInputSource, boolean z, short s, Element element) {
        short nodeType;
        Element root;
        boolean z2;
        String strExpandSystemId;
        boolean z3;
        Element element2;
        Node parentNode;
        Node node = dOMInputSource.getNode();
        IOException iOException = null;
        xSDKey = null;
        XSDKey xSDKey = null;
        if (node != null) {
            nodeType = node.getNodeType();
            if (nodeType == 9) {
                root = DOMUtil.getRoot((Document) node);
            } else if (nodeType == 1) {
                root = (Element) node;
            }
            if (root != null) {
                if (s != 3) {
                    try {
                        strExpandSystemId = XMLEntityManager.expandSystemId(dOMInputSource.getSystemId(), dOMInputSource.getBaseSystemId(), false);
                        if (nodeType == 9) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (!z3 && (parentNode = root.getParentNode()) != null) {
                            z3 = parentNode.getNodeType() == 9;
                        }
                        if (z3 && (element2 = this.fTraversed.get((xSDKey = new XSDKey(strExpandSystemId, s, str)))) != null) {
                            this.fLastSchemaWasDuplicate = true;
                            return element2;
                        }
                    } catch (IOException e) {
                        iOException = e;
                        z2 = true;
                    }
                } else {
                    strExpandSystemId = null;
                }
                return getSchemaDocument0(xSDKey, strExpandSystemId, root);
            }
            z2 = false;
            return getSchemaDocument1(z, z2, dOMInputSource, element, iOException);
        }
        nodeType = -1;
        root = null;
        if (root != null) {
            if (s != 3) {
                strExpandSystemId = XMLEntityManager.expandSystemId(dOMInputSource.getSystemId(), dOMInputSource.getBaseSystemId(), false);
                if (nodeType == 9) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z3) {
                    z3 = parentNode.getNodeType() == 9;
                }
                if (z3) {
                    this.fLastSchemaWasDuplicate = true;
                    return element2;
                }
            } else {
                strExpandSystemId = null;
            }
            return getSchemaDocument0(xSDKey, strExpandSystemId, root);
        }
        z2 = false;
        return getSchemaDocument1(z, z2, dOMInputSource, element, iOException);
    }

    private Element getSchemaDocument(String str, StAXInputSource stAXInputSource, boolean z, short s, Element element) {
        IOException iOException;
        String strExpandSystemId;
        XSDKey xSDKey;
        try {
            boolean zShouldConsumeRemainingContent = stAXInputSource.shouldConsumeRemainingContent();
            XMLStreamReader xMLStreamReader = stAXInputSource.getXMLStreamReader();
            XMLEventReader xMLEventReader = stAXInputSource.getXMLEventReader();
            if (s != 3) {
                boolean zIsStartDocument = false;
                strExpandSystemId = XMLEntityManager.expandSystemId(stAXInputSource.getSystemId(), stAXInputSource.getBaseSystemId(), false);
                if (zShouldConsumeRemainingContent) {
                    zIsStartDocument = zShouldConsumeRemainingContent;
                } else if (xMLStreamReader != null) {
                    if (xMLStreamReader.getEventType() == 7) {
                        zIsStartDocument = true;
                    }
                } else {
                    zIsStartDocument = xMLEventReader.peek().isStartDocument();
                }
                if (zIsStartDocument) {
                    xSDKey = new XSDKey(strExpandSystemId, s, str);
                    Element element2 = this.fTraversed.get(xSDKey);
                    if (element2 != null) {
                        this.fLastSchemaWasDuplicate = true;
                        return element2;
                    }
                } else {
                    xSDKey = null;
                }
            } else {
                strExpandSystemId = null;
                xSDKey = null;
            }
            if (this.fStAXSchemaParser == null) {
                this.fStAXSchemaParser = new StAXSchemaParser();
            }
            this.fStAXSchemaParser.reset(this.fSchemaParser, this.fSymbolTable);
            StAXSchemaParser stAXSchemaParser = this.fStAXSchemaParser;
            if (xMLStreamReader != null) {
                stAXSchemaParser.parse(xMLStreamReader);
                if (zShouldConsumeRemainingContent) {
                    while (xMLStreamReader.hasNext()) {
                        xMLStreamReader.next();
                    }
                }
            } else {
                stAXSchemaParser.parse(xMLEventReader);
                if (zShouldConsumeRemainingContent) {
                    while (xMLEventReader.hasNext()) {
                        xMLEventReader.nextEvent();
                    }
                }
            }
            Document document = this.fStAXSchemaParser.getDocument();
            return getSchemaDocument0(xSDKey, strExpandSystemId, document != null ? DOMUtil.getRoot(document) : null);
        } catch (IOException e) {
            iOException = e;
            return getSchemaDocument1(z, true, stAXInputSource, element, iOException);
        } catch (XMLStreamException e2) {
            Throwable nestedException = e2.getNestedException();
            if (nestedException instanceof IOException) {
                iOException = (IOException) nestedException;
                return getSchemaDocument1(z, true, stAXInputSource, element, iOException);
            }
            StAXLocationWrapper stAXLocationWrapper = new StAXLocationWrapper();
            stAXLocationWrapper.setLocation(e2.getLocation());
            throw new XMLParseException(stAXLocationWrapper, e2.getMessage(), e2);
        }
    }

    private Element getSchemaDocument(XSInputSource xSInputSource, XSDDescription xSDDescription) {
        SchemaGrammar[] grammars = xSInputSource.getGrammars();
        short contextType = xSDDescription.getContextType();
        if (grammars != null && grammars.length > 0) {
            List<SchemaGrammar> listExpandGrammars = expandGrammars(grammars);
            if (!this.fNamespaceGrowth && existingGrammars(listExpandGrammars)) {
                return null;
            }
            addGrammars(listExpandGrammars);
            if (contextType != 3) {
                return null;
            }
            xSDDescription.setTargetNamespace(grammars[0].getTargetNamespace());
            return null;
        }
        XSObject[] components = xSInputSource.getComponents();
        if (components == null || components.length <= 0) {
            return null;
        }
        HashMap map = new HashMap();
        List<XSObject> listExpandComponents = expandComponents(components, map);
        if (!this.fNamespaceGrowth && !canAddComponents(listExpandComponents)) {
            return null;
        }
        addGlobalComponents(listExpandComponents, map);
        if (contextType != 3) {
            return null;
        }
        xSDDescription.setTargetNamespace(components[0].getNamespace());
        return null;
    }

    public void checkForDuplicateNames(String str, int i, Element element) {
        int iIndexOf = str.indexOf(44);
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(emptyString2Null(str.substring(0, iIndexOf)));
        if (grammar == null || getGlobalDeclFromGrammar(grammar, i, str.substring(iIndexOf + 1)) == null) {
            return;
        }
        reportSchemaError("sch-props-correct.2", new Object[]{str}, element);
    }

    public XSDHandler(XSGrammarBucket xSGrammarBucket) {
        this();
        this.fGrammarBucket = xSGrammarBucket;
        this.fSchemaGrammarDescription = new XSDDescription();
    }

    public Object getGlobalDecl(String str, int i) {
        switch (i) {
            case 1:
                return getGlobalAttributeDecl(str);
            case 2:
                return getGlobalAttributeGroupDecl(str);
            case 3:
                return getGlobalElementDecl(str);
            case 4:
                return getGlobalGroupDecl(str);
            case 5:
                return getIDConstraintDecl(str);
            case 6:
                return getGlobalNotationDecl(str);
            case 7:
                return getGlobalTypeDecl(str);
            default:
                return null;
        }
    }
}
