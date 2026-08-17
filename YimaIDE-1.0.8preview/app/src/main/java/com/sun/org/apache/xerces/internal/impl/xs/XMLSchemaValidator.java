package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.validation.ConfigurableValidationState;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationManager;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationState;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Field;
import com.sun.org.apache.xerces.internal.impl.xs.identity.FieldActivator;
import com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import com.sun.org.apache.xerces.internal.impl.xs.identity.KeyRef;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;
import com.sun.org.apache.xerces.internal.impl.xs.identity.UniqueOrKey;
import com.sun.org.apache.xerces.internal.impl.xs.identity.ValueStore;
import com.sun.org.apache.xerces.internal.impl.xs.identity.XPathMatcher;
import com.sun.org.apache.xerces.internal.impl.xs.models.CMBuilder;
import com.sun.org.apache.xerces.internal.impl.xs.models.CMNodeFactory;
import com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator;
import com.sun.org.apache.xerces.internal.impl.xs.util.XS10TypeHelper;
import com.sun.org.apache.xerces.internal.parsers.XMLParser;
import com.sun.org.apache.xerces.internal.util.AugmentationsImpl;
import com.sun.org.apache.xerces.internal.util.IntStack;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.URI;
import com.sun.org.apache.xerces.internal.util.XMLAttributesImpl;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLEntityResolver;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;
import javax.xml.namespace.QName;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLSchemaValidator implements XMLComponent, XMLDocumentFilter, FieldActivator, RevalidationHandler, XSElementDeclHelper {
    private static final int BUFFER_SIZE = 20;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_NORMALIZATION = false;
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    public static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    public static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String GENERATE_SYNTHETIC_ANNOTATIONS = "http://apache.org/xml/features/generate-synthetic-annotations";
    protected static final int ID_CONSTRAINT_NUM = 1;
    static final int INC_STACK_SIZE = 8;
    static final int INITIAL_STACK_SIZE = 8;
    protected static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    protected static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    protected static final String NORMALIZE_DATA = "http://apache.org/xml/features/validation/schema/normalized-value";
    protected static final String OVERRIDE_PARSER = "jdk.xml.overrideDefaultParser";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    protected static final String REPORT_WHITESPACE = "http://java.sun.com/xml/schema/features/report-ignored-element-content-whitespace";
    protected static final String SCHEMA_AUGMENT_PSVI = "http://apache.org/xml/features/validation/schema/augment-psvi";
    protected static final String SCHEMA_ELEMENT_DEFAULT = "http://apache.org/xml/features/validation/schema/element-default";
    public static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String USE_CATALOG = "http://javax.xml.XMLConstants/feature/useCatalog";
    public static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    private static final String XML_SECURITY_PROPERTY_MANAGER = "jdk.xml.xmlSecurityPropertyManager";
    static final XSAttributeDecl XSI_NIL;
    static final XSAttributeDecl XSI_NONAMESPACESCHEMALOCATION;
    static final XSAttributeDecl XSI_SCHEMALOCATION;
    static final XSAttributeDecl XSI_TYPE;
    private boolean fAppendBuffer;
    private final StringBuilder fBuffer;
    private final CMBuilder fCMBuilder;
    private XSCMValidator[] fCMStack;
    private int[][] fCMStateStack;
    private int[] fCurrCMState;
    private XSCMValidator fCurrentCM;
    private XSElementDecl fCurrentElemDecl;
    private XSTypeDefinition fCurrentType;
    protected XMLString fDefaultValue;
    protected XMLDocumentHandler fDocumentHandler;
    protected XMLDocumentSource fDocumentSource;
    private XSElementDecl[] fElemDeclStack;
    private int fElementDepth;
    private final XMLString fEmptyXMLStr;
    protected XMLEntityResolver fEntityResolver;
    protected String fExternalNoNamespaceSchema;
    protected String fExternalSchemas;
    private boolean fFirstChunk;
    private final XSGrammarBucket fGrammarBucket;
    protected XMLGrammarPool fGrammarPool;
    private boolean fIDCChecking;
    private int fIgnoreXSITypeDepth;
    protected Object fJaxpSchemaSource;
    protected final Map<String, XMLSchemaLoader.LocationArray> fLocationPairs;
    private XMLLocator fLocator;
    protected XPathMatcherStack fMatcherStack;
    private int fNFullValidationDepth;
    private int fNNoneValidationDepth;
    private boolean fNil;
    private boolean[] fNilStack;
    private final XMLString fNormalizedStr;
    private XSNotationDecl fNotation;
    private XSNotationDecl[] fNotationStack;
    private final XSSimpleType fQNameDV;
    private QName fRootElementDeclQName;
    private XSElementDecl fRootElementDeclaration;
    private XSTypeDefinition fRootTypeDefinition;
    private QName fRootTypeQName;
    private boolean fSawCharacters;
    private boolean fSawText;
    private boolean[] fSawTextStack;
    private final XMLSchemaLoader fSchemaLoader;
    private int fSkipValidationDepth;
    private ValidationState fState4ApplyDefault;
    private ValidationState fState4XsiType;
    private boolean fStrictAssess;
    private boolean[] fStrictAssessStack;
    private boolean[] fStringContent;
    private boolean fSubElement;
    private boolean[] fSubElementStack;
    private final SubstitutionGroupHandler fSubGroupHandler;
    protected SymbolTable fSymbolTable;
    private final com.sun.org.apache.xerces.internal.xni.QName fTempQName;
    private boolean fTrailing;
    private XSTypeDefinition[] fTypeStack;
    private boolean fUnionType;
    private ValidatedInfo fValidatedInfo;
    protected ValidationManager fValidationManager;
    private String fValidationRoot;
    protected ConfigurableValidationState fValidationState;
    protected ValueStoreCache fValueStoreCache;
    private short fWhiteSpace;
    protected final XSDDescription fXSDDescription;
    protected final XSIErrorReporter fXSIErrorReporter;
    private final CMNodeFactory nodeFactory;
    boolean reportWhitespace;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String SCHEMA_VALIDATION = "http://apache.org/xml/features/validation/schema";
    protected static final String DYNAMIC_VALIDATION = "http://apache.org/xml/features/validation/dynamic";
    protected static final String SCHEMA_FULL_CHECKING = "http://apache.org/xml/features/validation/schema-full-checking";
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String CONTINUE_AFTER_FATAL_ERROR = "http://apache.org/xml/features/continue-after-fatal-error";
    protected static final String STANDARD_URI_CONFORMANT_FEATURE = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String VALIDATE_ANNOTATIONS = "http://apache.org/xml/features/validate-annotations";
    protected static final String HONOUR_ALL_SCHEMALOCATIONS = "http://apache.org/xml/features/honour-all-schemaLocations";
    protected static final String USE_GRAMMAR_POOL_ONLY = "http://apache.org/xml/features/internal/validation/schema/use-grammar-pool-only";
    protected static final String IGNORE_XSI_TYPE = "http://apache.org/xml/features/validation/schema/ignore-xsi-type-until-elemdecl";
    protected static final String ID_IDREF_CHECKING = "http://apache.org/xml/features/validation/id-idref-checking";
    protected static final String IDENTITY_CONSTRAINT_CHECKING = "http://apache.org/xml/features/validation/identity-constraint-checking";
    protected static final String UNPARSED_ENTITY_CHECKING = "http://apache.org/xml/features/validation/unparsed-entity-checking";
    protected static final String NAMESPACE_GROWTH = "http://apache.org/xml/features/namespace-growth";
    protected static final String TOLERATE_DUPLICATES = "http://apache.org/xml/features/internal/tolerate-duplicates";
    private static final String[] RECOGNIZED_FEATURES = {VALIDATION, SCHEMA_VALIDATION, DYNAMIC_VALIDATION, SCHEMA_FULL_CHECKING, ALLOW_JAVA_ENCODINGS, CONTINUE_AFTER_FATAL_ERROR, STANDARD_URI_CONFORMANT_FEATURE, "http://apache.org/xml/features/generate-synthetic-annotations", VALIDATE_ANNOTATIONS, HONOUR_ALL_SCHEMALOCATIONS, USE_GRAMMAR_POOL_ONLY, IGNORE_XSI_TYPE, ID_IDREF_CHECKING, IDENTITY_CONSTRAINT_CHECKING, UNPARSED_ENTITY_CHECKING, NAMESPACE_GROWTH, TOLERATE_DUPLICATES, "jdk.xml.overrideDefaultParser", "http://javax.xml.XMLConstants/feature/useCatalog"};
    private static final Boolean[] FEATURE_DEFAULTS = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, Boolean.valueOf(JdkConstants.OVERRIDE_PARSER_DEFAULT), Boolean.valueOf(JdkXmlUtils.USE_CATALOG_DEFAULT)};
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String SCHEMA_LOCATION = "http://apache.org/xml/properties/schema/external-schemaLocation";
    protected static final String SCHEMA_NONS_LOCATION = "http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation";
    protected static final String ROOT_TYPE_DEF = "http://apache.org/xml/properties/validation/schema/root-type-definition";
    protected static final String ROOT_ELEMENT_DECL = "http://apache.org/xml/properties/validation/schema/root-element-declaration";
    protected static final String SCHEMA_DV_FACTORY = "http://apache.org/xml/properties/internal/validation/schema/dv-factory";
    private static final String[] RECOGNIZED_PROPERTIES = {"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", VALIDATION_MANAGER, SCHEMA_LOCATION, SCHEMA_NONS_LOCATION, "http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/jaxp/properties/schemaLanguage", ROOT_TYPE_DEF, ROOT_ELEMENT_DECL, SCHEMA_DV_FACTORY, "jdk.xml.xmlSecurityPropertyManager", JdkXmlUtils.CATALOG_DEFER, JdkXmlUtils.CATALOG_FILES, JdkXmlUtils.CATALOG_PREFER, JdkXmlUtils.CATALOG_RESOLVE, JdkConstants.CDATA_CHUNK_SIZE};
    private static final Object[] PROPERTY_DEFAULTS = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, Integer.valueOf(JdkConstants.CDATA_CHUNK_SIZE_DEFAULT)};
    protected ElementPSVImpl fCurrentPSVI = new ElementPSVImpl();
    protected final AugmentationsImpl fAugmentations = new AugmentationsImpl();
    protected boolean fDynamicValidation = false;
    protected boolean fSchemaDynamicValidation = false;
    protected boolean fDoValidation = false;
    protected boolean fFullChecking = false;
    protected boolean fNormalizeData = true;
    protected boolean fSchemaElementDefault = true;
    protected boolean fAugPSVI = true;
    protected boolean fIdConstraint = false;
    protected boolean fUseGrammarPoolOnly = false;
    protected boolean fNamespaceGrowth = false;
    private String fSchemaType = null;
    protected boolean fEntityRef = false;
    protected boolean fInCDATA = false;
    protected boolean fSawOnlyWhitespaceInElementContent = false;

    public class KeyRefValueStore extends ValueStoreBase {
        protected ValueStoreBase fKeyValueStore;

        public KeyRefValueStore(KeyRef keyRef, KeyValueStore keyValueStore) {
            super(keyRef);
            this.fKeyValueStore = keyValueStore;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.ValueStoreBase
        public void endDocument() {
            super.endDocument();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.ValueStoreBase
        public void endDocumentFragment() {
            super.endDocumentFragment();
            ValueStoreBase valueStoreBase = XMLSchemaValidator.this.fValueStoreCache.fGlobalIDConstraintMap.get(((KeyRef) this.fIdentityConstraint).getKey());
            this.fKeyValueStore = valueStoreBase;
            if (valueStoreBase == null) {
                XMLSchemaValidator.this.reportSchemaError("KeyRefOutOfScope", new Object[]{this.fIdentityConstraint.toString()});
                return;
            }
            int iContains = valueStoreBase.contains(this);
            if (iContains != -1) {
                String string = toString(this.fValues, iContains, this.fFieldCount);
                String elementName = this.fIdentityConstraint.getElementName();
                XMLSchemaValidator.this.reportSchemaError("KeyNotFound", new Object[]{this.fIdentityConstraint.getName(), string, elementName});
            }
        }
    }

    public class KeyValueStore extends ValueStoreBase {
        public KeyValueStore(UniqueOrKey uniqueOrKey) {
            super(uniqueOrKey);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.ValueStoreBase
        public void checkDuplicateValues() {
            if (contains()) {
                XMLSchemaValidator.this.reportSchemaError("DuplicateKey", new Object[]{toString(this.fLocalValues), this.fIdentityConstraint.getElementName(), this.fIdentityConstraint.getIdentityConstraintName()});
            }
        }
    }

    public class UniqueValueStore extends ValueStoreBase {
        public UniqueValueStore(UniqueOrKey uniqueOrKey) {
            super(uniqueOrKey);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.ValueStoreBase
        public void checkDuplicateValues() {
            if (contains()) {
                XMLSchemaValidator.this.reportSchemaError("DuplicateUnique", new Object[]{toString(this.fLocalValues), this.fIdentityConstraint.getElementName(), this.fIdentityConstraint.getIdentityConstraintName()});
            }
        }
    }

    public class ValueStoreCache {
        final LocalIDKey fLocalId = new LocalIDKey();
        protected final List<ValueStoreBase> fValueStores = new ArrayList();
        protected final Map<LocalIDKey, ValueStoreBase> fIdentityConstraint2ValueStoreMap = new HashMap();
        protected final Stack<Map<IdentityConstraint, ValueStoreBase>> fGlobalMapStack = new Stack<>();
        protected final Map<IdentityConstraint, ValueStoreBase> fGlobalIDConstraintMap = new HashMap();

        public ValueStoreCache() {
        }

        public void endDocument() {
            Iterator<ValueStoreBase> it = this.fValueStores.iterator();
            while (it.hasNext()) {
                it.next().endDocument();
            }
        }

        public void endElement() {
            Map<IdentityConstraint, ValueStoreBase> mapPop;
            if (this.fGlobalMapStack.isEmpty() || (mapPop = this.fGlobalMapStack.pop()) == null) {
                return;
            }
            for (Map.Entry<IdentityConstraint, ValueStoreBase> entry : mapPop.entrySet()) {
                IdentityConstraint key = entry.getKey();
                ValueStoreBase value = entry.getValue();
                if (value != null) {
                    ValueStoreBase valueStoreBase = this.fGlobalIDConstraintMap.get(key);
                    if (valueStoreBase == null) {
                        this.fGlobalIDConstraintMap.put(key, value);
                    } else if (valueStoreBase != value) {
                        valueStoreBase.append(value);
                    }
                }
            }
        }

        public ValueStoreBase getGlobalValueStoreFor(IdentityConstraint identityConstraint) {
            return this.fGlobalIDConstraintMap.get(identityConstraint);
        }

        public ValueStoreBase getValueStoreFor(IdentityConstraint identityConstraint, int i) {
            LocalIDKey localIDKey = this.fLocalId;
            localIDKey.fDepth = i;
            localIDKey.fId = identityConstraint;
            return this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
        }

        public void initValueStoresFor(XSElementDecl xSElementDecl, FieldActivator fieldActivator) {
            IdentityConstraint[] identityConstraintArr = xSElementDecl.fIDConstraints;
            int i = xSElementDecl.fIDCPos;
            for (int i2 = 0; i2 < i; i2++) {
                short category = identityConstraintArr[i2].getCategory();
                if (category == 1) {
                    UniqueOrKey uniqueOrKey = (UniqueOrKey) identityConstraintArr[i2];
                    LocalIDKey localIDKey = new LocalIDKey(uniqueOrKey, XMLSchemaValidator.this.fElementDepth);
                    KeyValueStore keyValueStore = (KeyValueStore) this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
                    if (keyValueStore == null) {
                        keyValueStore = XMLSchemaValidator.this.new KeyValueStore(uniqueOrKey);
                        this.fIdentityConstraint2ValueStoreMap.put(localIDKey, keyValueStore);
                    } else {
                        keyValueStore.clear();
                    }
                    this.fValueStores.add(keyValueStore);
                    XMLSchemaValidator.this.activateSelectorFor(identityConstraintArr[i2]);
                } else if (category == 2) {
                    KeyRef keyRef = (KeyRef) identityConstraintArr[i2];
                    LocalIDKey localIDKey2 = new LocalIDKey(keyRef, XMLSchemaValidator.this.fElementDepth);
                    KeyRefValueStore keyRefValueStore = (KeyRefValueStore) this.fIdentityConstraint2ValueStoreMap.get(localIDKey2);
                    if (keyRefValueStore == null) {
                        keyRefValueStore = XMLSchemaValidator.this.new KeyRefValueStore(keyRef, null);
                        this.fIdentityConstraint2ValueStoreMap.put(localIDKey2, keyRefValueStore);
                    } else {
                        keyRefValueStore.clear();
                    }
                    this.fValueStores.add(keyRefValueStore);
                    XMLSchemaValidator.this.activateSelectorFor(identityConstraintArr[i2]);
                } else if (category == 3) {
                    UniqueOrKey uniqueOrKey2 = (UniqueOrKey) identityConstraintArr[i2];
                    LocalIDKey localIDKey3 = new LocalIDKey(uniqueOrKey2, XMLSchemaValidator.this.fElementDepth);
                    UniqueValueStore uniqueValueStore = (UniqueValueStore) this.fIdentityConstraint2ValueStoreMap.get(localIDKey3);
                    if (uniqueValueStore == null) {
                        uniqueValueStore = XMLSchemaValidator.this.new UniqueValueStore(uniqueOrKey2);
                        this.fIdentityConstraint2ValueStoreMap.put(localIDKey3, uniqueValueStore);
                    } else {
                        uniqueValueStore.clear();
                    }
                    this.fValueStores.add(uniqueValueStore);
                    XMLSchemaValidator.this.activateSelectorFor(identityConstraintArr[i2]);
                }
            }
        }

        public void startDocument() {
            this.fValueStores.clear();
            this.fIdentityConstraint2ValueStoreMap.clear();
            this.fGlobalIDConstraintMap.clear();
            this.fGlobalMapStack.removeAllElements();
        }

        public void startElement() {
            int size = this.fGlobalIDConstraintMap.size();
            Stack<Map<IdentityConstraint, ValueStoreBase>> stack = this.fGlobalMapStack;
            if (size > 0) {
                stack.push((Map) ((HashMap) this.fGlobalIDConstraintMap).clone());
            } else {
                stack.push(null);
            }
            this.fGlobalIDConstraintMap.clear();
        }

        public String toString() {
            String string = super.toString();
            int iLastIndexOf = string.lastIndexOf(36);
            if (iLastIndexOf != -1) {
                return string.substring(iLastIndexOf + 1);
            }
            int iLastIndexOf2 = string.lastIndexOf(46);
            return iLastIndexOf2 != -1 ? string.substring(iLastIndexOf2 + 1) : string;
        }

        public void transplant(IdentityConstraint identityConstraint, int i) {
            LocalIDKey localIDKey = this.fLocalId;
            localIDKey.fDepth = i;
            localIDKey.fId = identityConstraint;
            ValueStoreBase valueStoreBase = this.fIdentityConstraint2ValueStoreMap.get(localIDKey);
            if (identityConstraint.getCategory() == 2) {
                return;
            }
            ValueStoreBase valueStoreBase2 = this.fGlobalIDConstraintMap.get(identityConstraint);
            if (valueStoreBase2 == null) {
                this.fGlobalIDConstraintMap.put(identityConstraint, valueStoreBase);
            } else {
                valueStoreBase2.append(valueStoreBase);
                this.fGlobalIDConstraintMap.put(identityConstraint, valueStoreBase2);
            }
        }
    }

    public static class XPathMatcherStack {
        protected int fMatchersCount;
        protected XPathMatcher[] fMatchers = new XPathMatcher[4];
        protected IntStack fContextStack = new IntStack();

        private void ensureMatcherCapacity() {
            int i = this.fMatchersCount;
            XPathMatcher[] xPathMatcherArr = this.fMatchers;
            if (i == xPathMatcherArr.length) {
                XPathMatcher[] xPathMatcherArr2 = new XPathMatcher[xPathMatcherArr.length * 2];
                System.arraycopy(xPathMatcherArr, 0, xPathMatcherArr2, 0, xPathMatcherArr.length);
                this.fMatchers = xPathMatcherArr2;
            }
        }

        public void addMatcher(XPathMatcher xPathMatcher) {
            ensureMatcherCapacity();
            XPathMatcher[] xPathMatcherArr = this.fMatchers;
            int i = this.fMatchersCount;
            this.fMatchersCount = i + 1;
            xPathMatcherArr[i] = xPathMatcher;
        }

        public void clear() {
            for (int i = 0; i < this.fMatchersCount; i++) {
                this.fMatchers[i] = null;
            }
            this.fMatchersCount = 0;
            this.fContextStack.clear();
        }

        public XPathMatcher getMatcherAt(int i) {
            return this.fMatchers[i];
        }

        public int getMatcherCount() {
            return this.fMatchersCount;
        }

        public void popContext() {
            this.fMatchersCount = this.fContextStack.pop();
        }

        public void pushContext() {
            this.fContextStack.push(this.fMatchersCount);
        }

        public int size() {
            return this.fContextStack.size();
        }
    }

    static {
        SchemaGrammar.BuiltinSchemaGrammar builtinSchemaGrammar = SchemaGrammar.SG_XSI;
        XSI_TYPE = builtinSchemaGrammar.getGlobalAttributeDecl(SchemaSymbols.XSI_TYPE);
        XSI_NIL = builtinSchemaGrammar.getGlobalAttributeDecl(SchemaSymbols.XSI_NIL);
        XSI_SCHEMALOCATION = builtinSchemaGrammar.getGlobalAttributeDecl(SchemaSymbols.XSI_SCHEMALOCATION);
        XSI_NONAMESPACESCHEMALOCATION = builtinSchemaGrammar.getGlobalAttributeDecl(SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION);
    }

    public XMLSchemaValidator() {
        XSIErrorReporter xSIErrorReporter = new XSIErrorReporter();
        this.fXSIErrorReporter = xSIErrorReporter;
        this.fValidationManager = null;
        this.fValidationState = new ConfigurableValidationState();
        this.fExternalSchemas = null;
        this.fExternalNoNamespaceSchema = null;
        this.fJaxpSchemaSource = null;
        this.fXSDDescription = new XSDDescription();
        this.fLocationPairs = new HashMap();
        this.reportWhitespace = false;
        this.fEmptyXMLStr = new XMLString(null, 0, -1);
        this.fNormalizedStr = new XMLString();
        this.fFirstChunk = true;
        this.fTrailing = false;
        this.fWhiteSpace = (short) -1;
        this.fUnionType = false;
        XSGrammarBucket xSGrammarBucket = new XSGrammarBucket();
        this.fGrammarBucket = xSGrammarBucket;
        SubstitutionGroupHandler substitutionGroupHandler = new SubstitutionGroupHandler(this);
        this.fSubGroupHandler = substitutionGroupHandler;
        this.fQNameDV = (XSSimpleType) SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME);
        CMNodeFactory cMNodeFactory = new CMNodeFactory();
        this.nodeFactory = cMNodeFactory;
        CMBuilder cMBuilder = new CMBuilder(cMNodeFactory);
        this.fCMBuilder = cMBuilder;
        this.fSchemaLoader = new XMLSchemaLoader(xSIErrorReporter.fErrorReporter, xSGrammarBucket, substitutionGroupHandler, cMBuilder);
        this.fSubElementStack = new boolean[8];
        this.fElemDeclStack = new XSElementDecl[8];
        this.fNilStack = new boolean[8];
        this.fNotationStack = new XSNotationDecl[8];
        this.fTypeStack = new XSTypeDefinition[8];
        this.fCMStack = new XSCMValidator[8];
        this.fCMStateStack = new int[8][];
        this.fStrictAssess = true;
        this.fStrictAssessStack = new boolean[8];
        this.fBuffer = new StringBuilder();
        this.fAppendBuffer = true;
        this.fSawText = false;
        this.fSawTextStack = new boolean[8];
        this.fSawCharacters = false;
        this.fStringContent = new boolean[8];
        this.fTempQName = new com.sun.org.apache.xerces.internal.xni.QName();
        this.fRootTypeQName = null;
        this.fRootTypeDefinition = null;
        this.fRootElementDeclQName = null;
        this.fRootElementDeclaration = null;
        this.fValidatedInfo = new ValidatedInfo();
        this.fState4XsiType = new ValidationState();
        this.fState4ApplyDefault = new ValidationState();
        this.fMatcherStack = new XPathMatcherStack();
        this.fValueStoreCache = new ValueStoreCache();
        this.fState4XsiType.setExtraChecking(false);
        this.fState4ApplyDefault.setFacetChecking(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void activateSelectorFor(IdentityConstraint identityConstraint) {
        Selector selector = identityConstraint.getSelector();
        if (selector == null) {
            return;
        }
        XPathMatcher xPathMatcherCreateMatcher = selector.createMatcher(this, this.fElementDepth);
        this.fMatcherStack.addMatcher(xPathMatcherCreateMatcher);
        xPathMatcherCreateMatcher.startDocumentFragment();
    }

    private String expectedStr(List<Object> list) {
        StringBuilder sb = new StringBuilder("{");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(list.get(i).toString());
        }
        sb.append('}');
        return sb.toString();
    }

    private boolean hasSchemaComponent(SchemaGrammar schemaGrammar, short s, com.sun.org.apache.xerces.internal.xni.QName qName) {
        String str;
        if (schemaGrammar != null && qName != null && (str = qName.localpart) != null && str.length() > 0) {
            if (s != 5) {
                if (s != 6) {
                    return s == 7 && schemaGrammar.getTypeDefinition(str) != null;
                }
                return schemaGrammar.getAttributeDeclaration(str) != null;
            }
            if (schemaGrammar.getElementDeclaration(str) != null) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0066  */
    private void normalizeWhitespace(XMLString xMLString, boolean z) {
        boolean z2;
        int i = xMLString.offset;
        int i2 = xMLString.length;
        int i3 = i + i2;
        XMLString xMLString2 = this.fNormalizedStr;
        char[] cArr = xMLString2.ch;
        if (cArr == null || cArr.length < i2 + 1) {
            xMLString2.ch = new char[i2 + 1];
        }
        xMLString2.offset = 1;
        xMLString2.length = 1;
        boolean z3 = z;
        boolean z4 = false;
        boolean z5 = false;
        for (int i4 = xMLString.offset; i4 < i3; i4++) {
            char c = xMLString.ch[i4];
            if (XMLChar.isSpace(c)) {
                if (!z3) {
                    XMLString xMLString3 = this.fNormalizedStr;
                    char[] cArr2 = xMLString3.ch;
                    int i5 = xMLString3.length;
                    xMLString3.length = i5 + 1;
                    cArr2[i5] = ' ';
                    z3 = z;
                }
                if (!z5) {
                    z4 = true;
                }
            } else {
                XMLString xMLString4 = this.fNormalizedStr;
                char[] cArr3 = xMLString4.ch;
                int i6 = xMLString4.length;
                xMLString4.length = i6 + 1;
                cArr3[i6] = c;
                z3 = false;
                z5 = true;
            }
        }
        if (z3) {
            XMLString xMLString5 = this.fNormalizedStr;
            int i7 = xMLString5.length;
            if (i7 > 1) {
                xMLString5.length = i7 - 1;
            } else if (!z4 || this.fFirstChunk) {
                z2 = false;
            }
            z2 = true;
        } else {
            z2 = false;
        }
        XMLString xMLString6 = this.fNormalizedStr;
        int i8 = xMLString6.length;
        if (i8 > 1 && !this.fFirstChunk && this.fWhiteSpace == 2 && (this.fTrailing || z4)) {
            xMLString6.offset = 0;
            xMLString6.ch[0] = ' ';
        }
        xMLString6.length = i8 - xMLString6.offset;
        this.fTrailing = z2;
        if (z2 || z5) {
            this.fFirstChunk = false;
        }
    }

    private void setLocationHints(XSDDescription xSDDescription, String[] strArr, StringList stringList) {
        int length = strArr.length;
        String[] strArr2 = new String[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!stringList.contains(strArr[i2])) {
                strArr2[i] = strArr[i2];
                i++;
            }
        }
        if (i > 0) {
            XSDDescription xSDDescription2 = this.fXSDDescription;
            if (i == length) {
                xSDDescription2.fLocationHints = strArr2;
                return;
            }
            String[] strArr3 = new String[i];
            xSDDescription2.fLocationHints = strArr3;
            System.arraycopy(strArr2, 0, strArr3, 0, i);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.FieldActivator
    public XPathMatcher activateField(Field field, int i) {
        XPathMatcher xPathMatcherCreateMatcher = field.createMatcher(this.fValueStoreCache.getValueStoreFor(field.getIdentityConstraint(), i));
        this.fMatcherStack.addMatcher(xPathMatcherCreateMatcher);
        xPathMatcherCreateMatcher.startDocumentFragment();
        return xPathMatcherCreateMatcher;
    }

    public void addDefaultAttributes(com.sun.org.apache.xerces.internal.xni.QName qName, XMLAttributes xMLAttributes, XSAttributeGroupDecl xSAttributeGroupDecl) {
        int iAddAttribute;
        XSObjectList attributeUses = xSAttributeGroupDecl.getAttributeUses();
        int length = attributeUses.getLength();
        for (int i = 0; i < length; i++) {
            XSAttributeUseImpl xSAttributeUseImpl = (XSAttributeUseImpl) attributeUses.item(i);
            XSAttributeDecl xSAttributeDecl = xSAttributeUseImpl.fAttrDecl;
            short constraintType = xSAttributeUseImpl.fConstraintType;
            ValidatedInfo validatedInfo = xSAttributeUseImpl.fDefault;
            if (constraintType == 0) {
                constraintType = xSAttributeDecl.getConstraintType();
                validatedInfo = xSAttributeDecl.fDefault;
            }
            boolean z = xMLAttributes.getValue(xSAttributeDecl.fTargetNamespace, xSAttributeDecl.fName) != null;
            if (xSAttributeUseImpl.fUse == 1) {
                if (!z) {
                    reportSchemaError("cvc-complex-type.4", new Object[]{qName.rawname, xSAttributeDecl.fName});
                }
                if (!z) {
                    String str = xSAttributeDecl.fTargetNamespace;
                    if (str != null) {
                        reportSchemaError("cvc-complex-type.4_ns", new Object[]{qName.rawname, xSAttributeDecl.fName, str});
                    } else {
                        reportSchemaError("cvc-complex-type.4", new Object[]{qName.rawname, xSAttributeDecl.fName});
                    }
                }
            }
            if (!z && constraintType != 0) {
                String str2 = xSAttributeDecl.fName;
                com.sun.org.apache.xerces.internal.xni.QName qName2 = new com.sun.org.apache.xerces.internal.xni.QName(null, str2, str2, xSAttributeDecl.fTargetNamespace);
                String strStringValue = validatedInfo != null ? validatedInfo.stringValue() : "";
                if (xMLAttributes instanceof XMLAttributesImpl) {
                    XMLAttributesImpl xMLAttributesImpl = (XMLAttributesImpl) xMLAttributes;
                    iAddAttribute = xMLAttributesImpl.getLength();
                    xMLAttributesImpl.addAttributeNS(qName2, "CDATA", strStringValue);
                } else {
                    iAddAttribute = xMLAttributes.addAttribute(qName2, "CDATA", strStringValue);
                }
                if (this.fAugPSVI) {
                    Augmentations augmentations = xMLAttributes.getAugmentations(iAddAttribute);
                    AttributePSVImpl attributePSVImpl = new AttributePSVImpl();
                    augmentations.putItem(Constants.ATTRIBUTE_PSVI, attributePSVImpl);
                    attributePSVImpl.fDeclaration = xSAttributeDecl;
                    attributePSVImpl.fTypeDecl = xSAttributeDecl.fType;
                    attributePSVImpl.fValue.copyFrom(validatedInfo);
                    attributePSVImpl.fValidationContext = this.fValidationRoot;
                    attributePSVImpl.fValidity = (short) 2;
                    attributePSVImpl.fValidationAttempted = (short) 2;
                    attributePSVImpl.fSpecified = true;
                }
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.RevalidationHandler
    public boolean characterData(String str, Augmentations augmentations) {
        short s;
        this.fSawText = this.fSawText || str.length() > 0;
        if (this.fNormalizeData && (s = this.fWhiteSpace) != -1 && s != 0) {
            normalizeWhitespace(str, s == 2);
            StringBuilder sb = this.fBuffer;
            XMLString xMLString = this.fNormalizedStr;
            sb.append(xMLString.ch, xMLString.offset, xMLString.length);
        } else if (this.fAppendBuffer) {
            this.fBuffer.append(str);
        }
        XSTypeDefinition xSTypeDefinition = this.fCurrentType;
        if (xSTypeDefinition != null && xSTypeDefinition.getTypeCategory() == 15 && ((XSComplexTypeDecl) this.fCurrentType).fContentType == 2) {
            for (int i = 0; i < str.length(); i++) {
                if (!XMLChar.isSpace(str.charAt(i))) {
                    this.fSawCharacters = true;
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void characters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLString xMLStringHandleCharacters = handleCharacters(xMLString);
        if (this.fSawOnlyWhitespaceInElementContent) {
            this.fSawOnlyWhitespaceInElementContent = false;
            if (!this.reportWhitespace) {
                ignorableWhitespace(xMLStringHandleCharacters, augmentations);
                return;
            }
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            if (!this.fNormalizeData || !this.fUnionType) {
                xMLDocumentHandler.characters(xMLStringHandleCharacters, augmentations);
            } else if (augmentations != null) {
                xMLDocumentHandler.characters(this.fEmptyXMLStr, augmentations);
            }
        }
    }

    public void checkElementMatchesRootElementDecl(XSElementDecl xSElementDecl, com.sun.org.apache.xerces.internal.xni.QName qName) {
        String str = qName.localpart;
        String str2 = xSElementDecl.fName;
        if (str == str2 && qName.uri == xSElementDecl.fTargetNamespace) {
            return;
        }
        reportSchemaError("cvc-elt.1.b", new Object[]{qName.rawname, str2});
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.comment(xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void doctypeDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.doctypeDecl(str, str2, str3, augmentations);
        }
    }

    public void elementDefault(String str) {
    }

    public Object elementLocallyValidComplexType(com.sun.org.apache.xerces.internal.xni.QName qName, Object obj) {
        int i;
        int i2;
        XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl) this.fCurrentType;
        Object objValidate = null;
        if (!this.fNil) {
            short s = xSComplexTypeDecl.fContentType;
            if (s == 0 && (this.fSubElement || this.fSawText)) {
                reportSchemaError("cvc-complex-type.2.1", new Object[]{qName.rawname});
            } else if (s == 1) {
                if (this.fSubElement) {
                    reportSchemaError("cvc-complex-type.2.2", new Object[]{qName.rawname});
                }
                XSSimpleType xSSimpleType = xSComplexTypeDecl.fXSSimpleType;
                try {
                    if (!this.fNormalizeData || this.fUnionType) {
                        this.fValidationState.setNormalizationRequired(true);
                    }
                    objValidate = xSSimpleType.validate(obj, this.fValidationState, this.fValidatedInfo);
                } catch (InvalidDatatypeValueException e) {
                    reportSchemaError(e.getKey(), e.getArgs());
                    reportSchemaError("cvc-complex-type.2.2", new Object[]{qName.rawname});
                }
            } else if (s == 2 && this.fSawCharacters) {
                reportSchemaError("cvc-complex-type.2.3", new Object[]{qName.rawname});
            }
            short s2 = xSComplexTypeDecl.fContentType;
            if (s2 == 2 || s2 == 3) {
                int[] iArr = this.fCurrCMState;
                if (iArr[0] < 0 || this.fCurrentCM.endContentModel(iArr)) {
                    List<String> listCheckMinMaxBounds = this.fCurrentCM.checkMinMaxBounds();
                    if (listCheckMinMaxBounds != null) {
                        for (int i3 = 0; i3 < listCheckMinMaxBounds.size(); i3 += 2) {
                            reportSchemaError(listCheckMinMaxBounds.get(i3), new Object[]{qName.rawname, listCheckMinMaxBounds.get(i3 + 1)});
                        }
                    }
                } else {
                    String strExpectedStr = expectedStr(this.fCurrentCM.whatCanGoHere(this.fCurrCMState));
                    int[] iArrOccurenceInfo = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                    if (iArrOccurenceInfo == null || (i2 = iArrOccurenceInfo[2]) >= (i = iArrOccurenceInfo[0])) {
                        reportSchemaError("cvc-complex-type.2.4.b", new Object[]{qName.rawname, strExpectedStr});
                    } else {
                        int i4 = i - i2;
                        if (i4 > 1) {
                            reportSchemaError("cvc-complex-type.2.4.j", new Object[]{qName.rawname, this.fCurrentCM.getTermName(iArrOccurenceInfo[3]), Integer.toString(i), Integer.toString(i4)});
                        } else {
                            reportSchemaError("cvc-complex-type.2.4.i", new Object[]{qName.rawname, this.fCurrentCM.getTermName(iArrOccurenceInfo[3]), Integer.toString(i)});
                        }
                    }
                }
            }
        }
        return objValidate;
    }

    public Object elementLocallyValidType(com.sun.org.apache.xerces.internal.xni.QName qName, Object obj) {
        XSTypeDefinition xSTypeDefinition = this.fCurrentType;
        if (xSTypeDefinition == null) {
            return null;
        }
        if (xSTypeDefinition.getTypeCategory() != 16) {
            return elementLocallyValidComplexType(qName, obj);
        }
        if (this.fSubElement) {
            reportSchemaError("cvc-type.3.1.2", new Object[]{qName.rawname});
        }
        if (this.fNil) {
            return null;
        }
        XSSimpleType xSSimpleType = (XSSimpleType) this.fCurrentType;
        try {
            if (!this.fNormalizeData || this.fUnionType) {
                this.fValidationState.setNormalizationRequired(true);
            }
            return xSSimpleType.validate(obj, this.fValidationState, this.fValidatedInfo);
        } catch (InvalidDatatypeValueException e) {
            reportSchemaError(e.getKey(), e.getArgs());
            reportSchemaError("cvc-type.3.1.3", new Object[]{qName.rawname, obj});
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void emptyElement(com.sun.org.apache.xerces.internal.xni.QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        Augmentations augmentationsHandleStartElement = handleStartElement(qName, xMLAttributes, augmentations);
        this.fDefaultValue = null;
        if (this.fElementDepth != -2) {
            augmentationsHandleStartElement = handleEndElement(qName, augmentationsHandleStartElement);
        }
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            if (!this.fSchemaElementDefault || this.fDefaultValue == null) {
                xMLDocumentHandler.emptyElement(qName, xMLAttributes, augmentationsHandleStartElement);
                return;
            }
            xMLDocumentHandler.startElement(qName, xMLAttributes, augmentationsHandleStartElement);
            this.fDocumentHandler.characters(this.fDefaultValue, null);
            this.fDocumentHandler.endElement(qName, augmentationsHandleStartElement);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endCDATA(Augmentations augmentations) throws XNIException {
        this.fInCDATA = false;
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.endCDATA(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endDocument(Augmentations augmentations) throws XNIException {
        handleEndDocument();
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.endDocument(augmentations);
        }
        this.fLocator = null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endElement(com.sun.org.apache.xerces.internal.xni.QName qName, Augmentations augmentations) throws XNIException {
        XMLString xMLString;
        this.fDefaultValue = null;
        Augmentations augmentationsHandleEndElement = handleEndElement(qName, augmentations);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            if (!this.fSchemaElementDefault || (xMLString = this.fDefaultValue) == null) {
                xMLDocumentHandler.endElement(qName, augmentationsHandleEndElement);
            } else {
                xMLDocumentHandler.characters(xMLString, null);
                this.fDocumentHandler.endElement(qName, augmentationsHandleEndElement);
            }
        }
    }

    public final Augmentations endElementPSVI(boolean z, SchemaGrammar[] schemaGrammarArr, Augmentations augmentations) {
        if (this.fAugPSVI) {
            augmentations = getEmptyAugs(augmentations);
            ElementPSVImpl elementPSVImpl = this.fCurrentPSVI;
            elementPSVImpl.fDeclaration = this.fCurrentElemDecl;
            elementPSVImpl.fTypeDecl = this.fCurrentType;
            elementPSVImpl.fNotation = this.fNotation;
            elementPSVImpl.fValidationContext = this.fValidationRoot;
            elementPSVImpl.fNil = this.fNil;
            int i = this.fElementDepth;
            int i2 = this.fNFullValidationDepth;
            if (i > i2) {
                elementPSVImpl.fValidationAttempted = (short) 2;
            } else if (i > this.fNNoneValidationDepth) {
                elementPSVImpl.fValidationAttempted = (short) 0;
            } else {
                elementPSVImpl.fValidationAttempted = (short) 1;
            }
            if (i2 == i) {
                this.fNFullValidationDepth = i - 1;
            }
            if (this.fNNoneValidationDepth == i) {
                this.fNNoneValidationDepth = i - 1;
            }
            if (this.fDefaultValue != null) {
                elementPSVImpl.fSpecified = true;
            }
            elementPSVImpl.fValue.copyFrom(this.fValidatedInfo);
            if (this.fStrictAssess) {
                String[] strArrMergeContext = this.fXSIErrorReporter.mergeContext();
                ElementPSVImpl elementPSVImpl2 = this.fCurrentPSVI;
                elementPSVImpl2.fErrors = strArrMergeContext;
                elementPSVImpl2.fValidity = strArrMergeContext != null ? (short) 1 : (short) 2;
            } else {
                this.fCurrentPSVI.fValidity = (short) 0;
                this.fXSIErrorReporter.popContext();
            }
            if (z) {
                ElementPSVImpl elementPSVImpl3 = this.fCurrentPSVI;
                elementPSVImpl3.fGrammars = schemaGrammarArr;
                elementPSVImpl3.fSchemaInformation = null;
            }
        }
        return augmentations;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void endGeneralEntity(String str, Augmentations augmentations) throws XNIException {
        this.fEntityRef = false;
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.endGeneralEntity(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.FieldActivator
    public void endValueScopeFor(IdentityConstraint identityConstraint, int i) {
        this.fValueStoreCache.getValueStoreFor(identityConstraint, i).endValueScope();
    }

    public void ensureStackCapacity() {
        int i = this.fElementDepth;
        if (i == this.fElemDeclStack.length) {
            int i2 = i + 8;
            boolean[] zArr = new boolean[i2];
            System.arraycopy(this.fSubElementStack, 0, zArr, 0, i);
            this.fSubElementStack = zArr;
            XSElementDecl[] xSElementDeclArr = new XSElementDecl[i2];
            System.arraycopy(this.fElemDeclStack, 0, xSElementDeclArr, 0, this.fElementDepth);
            this.fElemDeclStack = xSElementDeclArr;
            boolean[] zArr2 = new boolean[i2];
            System.arraycopy(this.fNilStack, 0, zArr2, 0, this.fElementDepth);
            this.fNilStack = zArr2;
            XSNotationDecl[] xSNotationDeclArr = new XSNotationDecl[i2];
            System.arraycopy(this.fNotationStack, 0, xSNotationDeclArr, 0, this.fElementDepth);
            this.fNotationStack = xSNotationDeclArr;
            XSTypeDefinition[] xSTypeDefinitionArr = new XSTypeDefinition[i2];
            System.arraycopy(this.fTypeStack, 0, xSTypeDefinitionArr, 0, this.fElementDepth);
            this.fTypeStack = xSTypeDefinitionArr;
            XSCMValidator[] xSCMValidatorArr = new XSCMValidator[i2];
            System.arraycopy(this.fCMStack, 0, xSCMValidatorArr, 0, this.fElementDepth);
            this.fCMStack = xSCMValidatorArr;
            boolean[] zArr3 = new boolean[i2];
            System.arraycopy(this.fSawTextStack, 0, zArr3, 0, this.fElementDepth);
            this.fSawTextStack = zArr3;
            boolean[] zArr4 = new boolean[i2];
            System.arraycopy(this.fStringContent, 0, zArr4, 0, this.fElementDepth);
            this.fStringContent = zArr4;
            boolean[] zArr5 = new boolean[i2];
            System.arraycopy(this.fStrictAssessStack, 0, zArr5, 0, this.fElementDepth);
            this.fStrictAssessStack = zArr5;
            int[][] iArr = new int[i2][];
            System.arraycopy(this.fCMStateStack, 0, iArr, 0, this.fElementDepth);
            this.fCMStateStack = iArr;
        }
    }

    public SchemaGrammar findSchemaGrammar(short s, String str, com.sun.org.apache.xerces.internal.xni.QName qName, com.sun.org.apache.xerces.internal.xni.QName qName2, XMLAttributes xMLAttributes) {
        SchemaGrammar grammar = this.fGrammarBucket.getGrammar(str);
        boolean zContains = true;
        if (grammar == null) {
            this.fXSDDescription.setNamespace(str);
            XMLGrammarPool xMLGrammarPool = this.fGrammarPool;
            if (xMLGrammarPool != null && (grammar = (SchemaGrammar) xMLGrammarPool.retrieveGrammar(this.fXSDDescription)) != null && !this.fGrammarBucket.putGrammar(grammar, true, this.fNamespaceGrowth)) {
                this.fXSIErrorReporter.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "GrammarConflict", null, (short) 0);
                grammar = null;
            }
        }
        if (this.fUseGrammarPoolOnly) {
            return grammar;
        }
        if (grammar != null && (!this.fNamespaceGrowth || hasSchemaComponent(grammar, s, qName2))) {
            return grammar;
        }
        this.fXSDDescription.reset();
        XSDDescription xSDDescription = this.fXSDDescription;
        xSDDescription.fContextType = s;
        xSDDescription.setNamespace(str);
        XSDDescription xSDDescription2 = this.fXSDDescription;
        xSDDescription2.fEnclosedElementName = qName;
        xSDDescription2.fTriggeringComponent = qName2;
        xSDDescription2.fAttributes = xMLAttributes;
        XMLLocator xMLLocator = this.fLocator;
        if (xMLLocator != null) {
            xSDDescription2.setBaseSystemId(xMLLocator.getExpandedSystemId());
        }
        Map<String, XMLSchemaLoader.LocationArray> map = this.fLocationPairs;
        XMLSchemaLoader.LocationArray locationArray = map.get(str == null ? XMLSymbols.EMPTY_STRING : str);
        if (locationArray != null) {
            String[] locationArray2 = locationArray.getLocationArray();
            if (locationArray2.length != 0) {
                setLocationHints(this.fXSDDescription, locationArray2, grammar);
            }
        }
        if (grammar != null && this.fXSDDescription.fLocationHints == null) {
            return grammar;
        }
        if (grammar != null) {
            map = Collections.EMPTY_MAP;
        }
        try {
            XMLInputSource xMLInputSourceResolveDocument = XMLSchemaLoader.resolveDocument(this.fXSDDescription, map, this.fEntityResolver);
            if (grammar != null && this.fNamespaceGrowth) {
                try {
                    zContains = true ^ grammar.getDocumentLocations().contains(XMLEntityManager.expandSystemId(xMLInputSourceResolveDocument.getSystemId(), xMLInputSourceResolveDocument.getBaseSystemId(), false));
                } catch (URI.MalformedURIException unused) {
                }
            }
            return zContains ? this.fSchemaLoader.loadSchema(this.fXSDDescription, xMLInputSourceResolveDocument, this.fLocationPairs) : grammar;
        } catch (IOException e) {
            String[] locationHints = this.fXSDDescription.getLocationHints();
            this.fXSIErrorReporter.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "schema_reference.4", new Object[]{locationHints != null ? locationHints[0] : XMLSymbols.EMPTY_STRING}, (short) 0, (Exception) e);
            return grammar;
        }
    }

    public XSTypeDefinition getAndCheckXsiType(com.sun.org.apache.xerces.internal.xni.QName qName, String str, XMLAttributes xMLAttributes) {
        XMLSchemaValidator xMLSchemaValidator;
        com.sun.org.apache.xerces.internal.xni.QName qName2;
        try {
            com.sun.org.apache.xerces.internal.xni.QName qName3 = (com.sun.org.apache.xerces.internal.xni.QName) this.fQNameDV.validate(str, (ValidationContext) this.fValidationState, (ValidatedInfo) null);
            XSTypeDefinition globalTypeDecl = qName3.uri == SchemaSymbols.URI_SCHEMAFORSCHEMA ? SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(qName3.localpart) : null;
            if (globalTypeDecl == null) {
                xMLSchemaValidator = this;
                qName2 = qName;
                SchemaGrammar schemaGrammarFindSchemaGrammar = xMLSchemaValidator.findSchemaGrammar((short) 7, qName3.uri, qName2, qName3, xMLAttributes);
                if (schemaGrammarFindSchemaGrammar != null) {
                    globalTypeDecl = schemaGrammarFindSchemaGrammar.getGlobalTypeDecl(qName3.localpart);
                }
            } else {
                xMLSchemaValidator = this;
                qName2 = qName;
            }
            if (globalTypeDecl == null) {
                xMLSchemaValidator.reportSchemaError("cvc-elt.4.2", new Object[]{qName2.rawname, str});
                return null;
            }
            XSTypeDefinition xSTypeDefinition = xMLSchemaValidator.fCurrentType;
            if (xSTypeDefinition != null) {
                XSElementDecl xSElementDecl = xMLSchemaValidator.fCurrentElemDecl;
                short s = xSElementDecl != null ? xSElementDecl.fBlock : (short) 0;
                if (xSTypeDefinition.getTypeCategory() == 15) {
                    s = (short) (((XSComplexTypeDecl) xMLSchemaValidator.fCurrentType).fBlock | s);
                }
                if (!XSConstraints.checkTypeDerivationOk(globalTypeDecl, xMLSchemaValidator.fCurrentType, s)) {
                    xMLSchemaValidator.reportSchemaError("cvc-elt.4.3", new Object[]{qName2.rawname, str, XS10TypeHelper.getSchemaTypeName(xMLSchemaValidator.fCurrentType)});
                }
            }
            return globalTypeDecl;
        } catch (InvalidDatatypeValueException e) {
            reportSchemaError(e.getKey(), e.getArgs());
            reportSchemaError("cvc-elt.4.1", new Object[]{qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_TYPE, str});
            return null;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public XMLDocumentHandler getDocumentHandler() {
        return this.fDocumentHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public XMLDocumentSource getDocumentSource() {
        return this.fDocumentSource;
    }

    public Augmentations getEmptyAugs(Augmentations augmentations) {
        if (augmentations == null) {
            augmentations = this.fAugmentations;
            augmentations.removeAllItems();
        }
        augmentations.putItem(Constants.ELEMENT_PSVI, this.fCurrentPSVI);
        this.fCurrentPSVI.reset();
        return augmentations;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Boolean getFeatureDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_FEATURES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return FEATURE_DEFAULTS[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.XSElementDeclHelper
    public XSElementDecl getGlobalElementDecl(com.sun.org.apache.xerces.internal.xni.QName qName) {
        SchemaGrammar schemaGrammarFindSchemaGrammar = findSchemaGrammar((short) 5, qName.uri, null, qName, null);
        if (schemaGrammarFindSchemaGrammar != null) {
            return schemaGrammarFindSchemaGrammar.getGlobalElementDecl(qName.localpart);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public Object getPropertyDefault(String str) {
        int i = 0;
        while (true) {
            String[] strArr = RECOGNIZED_PROPERTIES;
            if (i >= strArr.length) {
                return null;
            }
            if (strArr[i].equals(str)) {
                return PROPERTY_DEFAULTS[i];
            }
            i++;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public boolean getXsiNil(com.sun.org.apache.xerces.internal.xni.QName qName, String str) {
        XSElementDecl xSElementDecl = this.fCurrentElemDecl;
        if (xSElementDecl != null && !xSElementDecl.getNillable()) {
            reportSchemaError("cvc-elt.3.1", new Object[]{qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL});
            return false;
        }
        String strTrim = XMLChar.trim(str);
        if (!strTrim.equals("true") && !strTrim.equals("1")) {
            return false;
        }
        XSElementDecl xSElementDecl2 = this.fCurrentElemDecl;
        if (xSElementDecl2 == null || xSElementDecl2.getConstraintType() != 2) {
            return true;
        }
        reportSchemaError("cvc-elt.3.2.2", new Object[]{qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL});
        return true;
    }

    public XMLString handleCharacters(XMLString xMLString) {
        short s;
        if (this.fSkipValidationDepth >= 0) {
            return xMLString;
        }
        this.fSawText = this.fSawText || xMLString.length > 0;
        if (this.fNormalizeData && (s = this.fWhiteSpace) != -1 && s != 0) {
            normalizeWhitespace(xMLString, s == 2);
            xMLString = this.fNormalizedStr;
        }
        if (this.fAppendBuffer) {
            this.fBuffer.append(xMLString.ch, xMLString.offset, xMLString.length);
        }
        this.fSawOnlyWhitespaceInElementContent = false;
        XSTypeDefinition xSTypeDefinition = this.fCurrentType;
        if (xSTypeDefinition != null && xSTypeDefinition.getTypeCategory() == 15 && ((XSComplexTypeDecl) this.fCurrentType).fContentType == 2) {
            for (int i = xMLString.offset; i < xMLString.offset + xMLString.length; i++) {
                if (!XMLChar.isSpace(xMLString.ch[i])) {
                    this.fSawCharacters = true;
                    return xMLString;
                }
                this.fSawOnlyWhitespaceInElementContent = !this.fSawCharacters;
            }
        }
        return xMLString;
    }

    public void handleEndDocument() {
        if (this.fIDCChecking) {
            this.fValueStoreCache.endDocument();
        }
    }

    public Augmentations handleEndElement(com.sun.org.apache.xerces.internal.xni.QName qName, Augmentations augmentations) {
        XPathMatcherStack xPathMatcherStack;
        Selector.Matcher matcher;
        IdentityConstraint identityConstraint;
        ValueStoreBase valueStoreFor;
        Selector.Matcher matcher2;
        IdentityConstraint identityConstraint2;
        com.sun.org.apache.xerces.internal.xni.QName qName2;
        int i = this.fSkipValidationDepth;
        if (i >= 0) {
            int i2 = this.fElementDepth;
            if (i != i2 || i <= 0) {
                this.fElementDepth = i2 - 1;
            } else {
                this.fNFullValidationDepth = i - 1;
                this.fSkipValidationDepth = -1;
                int i3 = i2 - 1;
                this.fElementDepth = i3;
                this.fSubElement = this.fSubElementStack[i3];
                this.fCurrentElemDecl = this.fElemDeclStack[i3];
                this.fNil = this.fNilStack[i3];
                this.fNotation = this.fNotationStack[i3];
                this.fCurrentType = this.fTypeStack[i3];
                this.fCurrentCM = this.fCMStack[i3];
                this.fStrictAssess = this.fStrictAssessStack[i3];
                this.fCurrCMState = this.fCMStateStack[i3];
                this.fSawText = this.fSawTextStack[i3];
                this.fSawCharacters = this.fStringContent[i3];
            }
            if (this.fElementDepth == -1 && this.fFullChecking && !this.fUseGrammarPoolOnly) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            return this.fAugPSVI ? getEmptyAugs(augmentations) : augmentations;
        }
        processElementContent(qName);
        if (this.fIDCChecking) {
            int matcherCount = this.fMatcherStack.getMatcherCount() - 1;
            int i4 = matcherCount;
            while (true) {
                xPathMatcherStack = this.fMatcherStack;
                if (i4 < 0) {
                    break;
                }
                XPathMatcher matcherAt = xPathMatcherStack.getMatcherAt(i4);
                XSElementDecl xSElementDecl = this.fCurrentElemDecl;
                XSTypeDefinition xSTypeDefinition = this.fCurrentType;
                if (xSElementDecl == null) {
                    ValidatedInfo validatedInfo = this.fValidatedInfo;
                    qName2 = qName;
                    matcherAt.endElement(qName2, xSTypeDefinition, false, validatedInfo.actualValue, validatedInfo.actualValueType, validatedInfo.itemValueTypes);
                } else {
                    qName2 = qName;
                    boolean nillable = xSElementDecl.getNillable();
                    XMLString xMLString = this.fDefaultValue;
                    matcherAt.endElement(qName2, xSTypeDefinition, nillable, xMLString == null ? this.fValidatedInfo.actualValue : this.fCurrentElemDecl.fDefault.actualValue, xMLString == null ? this.fValidatedInfo.actualValueType : this.fCurrentElemDecl.fDefault.actualValueType, xMLString == null ? this.fValidatedInfo.itemValueTypes : this.fCurrentElemDecl.fDefault.itemValueTypes);
                }
                i4--;
                qName = qName2;
            }
            if (xPathMatcherStack.size() > 0) {
                this.fMatcherStack.popContext();
            }
            int matcherCount2 = this.fMatcherStack.getMatcherCount();
            for (int i5 = matcherCount; i5 >= matcherCount2; i5--) {
                XPathMatcher matcherAt2 = this.fMatcherStack.getMatcherAt(i5);
                if ((matcherAt2 instanceof Selector.Matcher) && (identityConstraint2 = (matcher2 = (Selector.Matcher) matcherAt2).getIdentityConstraint()) != null && identityConstraint2.getCategory() != 2) {
                    this.fValueStoreCache.transplant(identityConstraint2, matcher2.getInitialDepth());
                }
            }
            while (matcherCount >= matcherCount2) {
                XPathMatcher matcherAt3 = this.fMatcherStack.getMatcherAt(matcherCount);
                if ((matcherAt3 instanceof Selector.Matcher) && (identityConstraint = (matcher = (Selector.Matcher) matcherAt3).getIdentityConstraint()) != null && identityConstraint.getCategory() == 2 && (valueStoreFor = this.fValueStoreCache.getValueStoreFor(identityConstraint, matcher.getInitialDepth())) != null && valueStoreFor.fHasValue) {
                    valueStoreFor.endDocumentFragment();
                }
                matcherCount--;
            }
            this.fValueStoreCache.endElement();
        }
        int i6 = this.fElementDepth;
        int i7 = this.fIgnoreXSITypeDepth;
        if (i6 < i7) {
            this.fIgnoreXSITypeDepth = i7 - 1;
        }
        if (i6 == 0) {
            Iterator<String> itCheckIDRefID = this.fValidationState.checkIDRefID();
            this.fValidationState.resetIDTables();
            if (itCheckIDRefID != null) {
                while (itCheckIDRefID.hasNext()) {
                    reportSchemaError("cvc-id.1", new Object[]{itCheckIDRefID.next()});
                }
            }
            if (this.fFullChecking && !this.fUseGrammarPoolOnly) {
                XSConstraints.fullSchemaChecking(this.fGrammarBucket, this.fSubGroupHandler, this.fCMBuilder, this.fXSIErrorReporter.fErrorReporter);
            }
            SchemaGrammar[] grammars = this.fGrammarBucket.getGrammars();
            if (this.fGrammarPool != null) {
                for (SchemaGrammar schemaGrammar : grammars) {
                    schemaGrammar.setImmutable(true);
                }
                this.fGrammarPool.cacheGrammars("http://www.w3.org/2001/XMLSchema", grammars);
            }
            return endElementPSVI(true, grammars, augmentations);
        }
        Augmentations augmentationsEndElementPSVI = endElementPSVI(false, null, augmentations);
        int i8 = this.fElementDepth - 1;
        this.fElementDepth = i8;
        this.fSubElement = this.fSubElementStack[i8];
        this.fCurrentElemDecl = this.fElemDeclStack[i8];
        this.fNil = this.fNilStack[i8];
        this.fNotation = this.fNotationStack[i8];
        this.fCurrentType = this.fTypeStack[i8];
        this.fCurrentCM = this.fCMStack[i8];
        this.fStrictAssess = this.fStrictAssessStack[i8];
        this.fCurrCMState = this.fCMStateStack[i8];
        this.fSawText = this.fSawTextStack[i8];
        this.fSawCharacters = this.fStringContent[i8];
        this.fWhiteSpace = (short) -1;
        this.fAppendBuffer = false;
        this.fUnionType = false;
        return augmentationsEndElementPSVI;
    }

    public void handleIgnorableWhitespace(XMLString xMLString) {
    }

    public void handleStartDocument(XMLLocator xMLLocator, String str) {
        if (this.fIDCChecking) {
            this.fValueStoreCache.startDocument();
        }
        if (this.fAugPSVI) {
            ElementPSVImpl elementPSVImpl = this.fCurrentPSVI;
            elementPSVImpl.fGrammars = null;
            elementPSVImpl.fSchemaInformation = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0113  */
    public Augmentations handleStartElement(com.sun.org.apache.xerces.internal.xni.QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) {
        short s;
        short s2;
        Object objOneTransition;
        XSWildcardDecl xSWildcardDecl;
        SchemaGrammar schemaGrammarFindSchemaGrammar;
        int[] iArrOccurenceInfo;
        int i;
        if (this.fElementDepth == -1 && this.fValidationManager.isGrammarFound() && this.fSchemaType == null && !this.fUseGrammarPoolOnly) {
            this.fSchemaDynamicValidation = true;
        }
        if (!this.fUseGrammarPoolOnly) {
            String str = SchemaSymbols.URI_XSI;
            storeLocations(xMLAttributes.getValue(str, SchemaSymbols.XSI_SCHEMALOCATION), xMLAttributes.getValue(str, SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION));
        }
        if (this.fSkipValidationDepth >= 0) {
            this.fElementDepth++;
            if (this.fAugPSVI) {
                return getEmptyAugs(augmentations);
            }
        } else {
            XSCMValidator xSCMValidator = this.fCurrentCM;
            if (xSCMValidator != null) {
                objOneTransition = xSCMValidator.oneTransition(qName, this.fCurrCMState, this.fSubGroupHandler);
                int[] iArr = this.fCurrCMState;
                if (iArr[0] != -1) {
                    s = 3;
                    s2 = 2;
                } else if (((XSComplexTypeDecl) this.fCurrentType).fParticle != null) {
                    List<Object> listWhatCanGoHere = this.fCurrentCM.whatCanGoHere(iArr);
                    if (listWhatCanGoHere.size() > 0) {
                        String strExpectedStr = expectedStr(listWhatCanGoHere);
                        int[] iArrOccurenceInfo2 = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                        String str2 = qName.uri != null ? "{\"" + qName.uri + "\":" + qName.localpart + "}" : qName.localpart;
                        if (iArrOccurenceInfo2 != null) {
                            int i2 = iArrOccurenceInfo2[0];
                            s = 3;
                            int i3 = iArrOccurenceInfo2[1];
                            s2 = 2;
                            int i4 = iArrOccurenceInfo2[2];
                            if (i4 < i2) {
                                int i5 = i2 - i4;
                                String str3 = qName.rawname;
                                if (i5 > 1) {
                                    reportSchemaError("cvc-complex-type.2.4.h", new Object[]{str3, this.fCurrentCM.getTermName(iArrOccurenceInfo2[3]), Integer.toString(i2), Integer.toString(i5)});
                                } else {
                                    reportSchemaError("cvc-complex-type.2.4.g", new Object[]{str3, this.fCurrentCM.getTermName(iArrOccurenceInfo2[3]), Integer.toString(i2)});
                                }
                            } else if (i4 < i3 || i3 == -1) {
                                reportSchemaError("cvc-complex-type.2.4.a", new Object[]{str2, strExpectedStr});
                            } else {
                                reportSchemaError("cvc-complex-type.2.4.e", new Object[]{qName.rawname, strExpectedStr, Integer.toString(i3)});
                            }
                        } else {
                            s = 3;
                            s2 = 2;
                            reportSchemaError("cvc-complex-type.2.4.a", new Object[]{str2, strExpectedStr});
                        }
                    } else {
                        s = 3;
                        s2 = 2;
                        iArrOccurenceInfo = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                        if (iArrOccurenceInfo != null || iArrOccurenceInfo[2] < (i = iArrOccurenceInfo[1]) || i == -1) {
                            reportSchemaError("cvc-complex-type.2.4.d", new Object[]{qName.rawname});
                        } else {
                            reportSchemaError("cvc-complex-type.2.4.f", new Object[]{this.fCurrentCM.getTermName(iArrOccurenceInfo[3]), Integer.toString(i)});
                        }
                    }
                } else {
                    s = 3;
                    s2 = 2;
                    iArrOccurenceInfo = this.fCurrentCM.occurenceInfo(this.fCurrCMState);
                    if (iArrOccurenceInfo != null) {
                        reportSchemaError("cvc-complex-type.2.4.d", new Object[]{qName.rawname});
                    } else {
                        reportSchemaError("cvc-complex-type.2.4.d", new Object[]{qName.rawname});
                    }
                }
            } else {
                s = 3;
                s2 = 2;
                objOneTransition = null;
            }
            if (this.fElementDepth != -1) {
                ensureStackCapacity();
                boolean[] zArr = this.fSubElementStack;
                int i6 = this.fElementDepth;
                zArr[i6] = true;
                this.fSubElement = false;
                this.fElemDeclStack[i6] = this.fCurrentElemDecl;
                this.fNilStack[i6] = this.fNil;
                this.fNotationStack[i6] = this.fNotation;
                this.fTypeStack[i6] = this.fCurrentType;
                this.fStrictAssessStack[i6] = this.fStrictAssess;
                this.fCMStack[i6] = this.fCurrentCM;
                this.fCMStateStack[i6] = this.fCurrCMState;
                this.fSawTextStack[i6] = this.fSawText;
                this.fStringContent[i6] = this.fSawCharacters;
            }
            this.fElementDepth++;
            this.fCurrentElemDecl = null;
            this.fCurrentType = null;
            this.fStrictAssess = true;
            this.fNil = false;
            this.fNotation = null;
            this.fBuffer.setLength(0);
            this.fSawText = false;
            this.fSawCharacters = false;
            if (objOneTransition == null) {
                xSWildcardDecl = null;
            } else if (objOneTransition instanceof XSElementDecl) {
                this.fCurrentElemDecl = (XSElementDecl) objOneTransition;
                xSWildcardDecl = null;
            } else {
                xSWildcardDecl = (XSWildcardDecl) objOneTransition;
            }
            if (xSWildcardDecl == null || xSWildcardDecl.fProcessContents != s2) {
                if (this.fElementDepth == 0) {
                    XSElementDecl xSElementDecl = this.fRootElementDeclaration;
                    if (xSElementDecl != null) {
                        this.fCurrentElemDecl = xSElementDecl;
                        checkElementMatchesRootElementDecl(xSElementDecl, qName);
                    } else {
                        QName qName2 = this.fRootElementDeclQName;
                        if (qName2 != null) {
                            processRootElementDeclQName(qName2, qName);
                        } else {
                            XSTypeDefinition xSTypeDefinition = this.fRootTypeDefinition;
                            if (xSTypeDefinition != null) {
                                this.fCurrentType = xSTypeDefinition;
                            } else {
                                QName qName3 = this.fRootTypeQName;
                                if (qName3 != null) {
                                    processRootTypeQName(qName3);
                                }
                            }
                        }
                    }
                }
                if (this.fCurrentType == null) {
                    if (this.fCurrentElemDecl == null && (schemaGrammarFindSchemaGrammar = findSchemaGrammar((short) 5, qName.uri, null, qName, xMLAttributes)) != null) {
                        this.fCurrentElemDecl = schemaGrammarFindSchemaGrammar.getGlobalElementDecl(qName.localpart);
                    }
                    XSElementDecl xSElementDecl2 = this.fCurrentElemDecl;
                    if (xSElementDecl2 != null) {
                        this.fCurrentType = xSElementDecl2.fType;
                    }
                }
                int i7 = this.fElementDepth;
                int i8 = this.fIgnoreXSITypeDepth;
                if (i7 == i8 && this.fCurrentElemDecl == null) {
                    this.fIgnoreXSITypeDepth = i8 + 1;
                }
                String value = i7 >= this.fIgnoreXSITypeDepth ? xMLAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_TYPE) : null;
                if (this.fCurrentType == null && value == null) {
                    int i9 = this.fElementDepth;
                    if (i9 == 0) {
                        if (this.fDynamicValidation || this.fSchemaDynamicValidation) {
                            XMLDocumentSource xMLDocumentSource = this.fDocumentSource;
                            if (xMLDocumentSource != null) {
                                xMLDocumentSource.setDocumentHandler(this.fDocumentHandler);
                                XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
                                if (xMLDocumentHandler != null) {
                                    xMLDocumentHandler.setDocumentSource(this.fDocumentSource);
                                }
                                this.fElementDepth = -2;
                                return augmentations;
                            }
                            this.fSkipValidationDepth = i9;
                            if (this.fAugPSVI) {
                                return getEmptyAugs(augmentations);
                            }
                        } else {
                            this.fXSIErrorReporter.fErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "cvc-elt.1.a", new Object[]{qName.rawname}, (short) 1);
                        }
                    } else if (xSWildcardDecl != null && xSWildcardDecl.fProcessContents == 1) {
                        reportSchemaError("cvc-complex-type.2.4.c", new Object[]{qName.rawname});
                    }
                    this.fCurrentType = SchemaGrammar.fAnyType;
                    this.fStrictAssess = false;
                    this.fNFullValidationDepth = this.fElementDepth;
                    this.fAppendBuffer = false;
                    this.fXSIErrorReporter.pushContext();
                } else {
                    this.fXSIErrorReporter.pushContext();
                    if (value != null) {
                        XSTypeDefinition xSTypeDefinition2 = this.fCurrentType;
                        XSTypeDefinition andCheckXsiType = getAndCheckXsiType(qName, value, xMLAttributes);
                        this.fCurrentType = andCheckXsiType;
                        if (andCheckXsiType == null) {
                            if (xSTypeDefinition2 == null) {
                                this.fCurrentType = SchemaGrammar.fAnyType;
                            } else {
                                this.fCurrentType = xSTypeDefinition2;
                            }
                        }
                    }
                    this.fNNoneValidationDepth = this.fElementDepth;
                    XSElementDecl xSElementDecl3 = this.fCurrentElemDecl;
                    if ((xSElementDecl3 == null || xSElementDecl3.getConstraintType() != 2) && this.fCurrentType.getTypeCategory() != 16) {
                        this.fAppendBuffer = ((XSComplexTypeDecl) this.fCurrentType).fContentType == 1;
                    } else {
                        this.fAppendBuffer = true;
                    }
                }
                XSElementDecl xSElementDecl4 = this.fCurrentElemDecl;
                if (xSElementDecl4 != null && xSElementDecl4.getAbstract()) {
                    reportSchemaError("cvc-elt.2", new Object[]{qName.rawname});
                }
                if (this.fElementDepth == 0) {
                    this.fValidationRoot = qName.rawname;
                }
                if (this.fNormalizeData) {
                    this.fFirstChunk = true;
                    this.fTrailing = false;
                    this.fUnionType = false;
                    this.fWhiteSpace = (short) -1;
                }
                try {
                    if (this.fCurrentType.getTypeCategory() == 15) {
                        XSComplexTypeDecl xSComplexTypeDecl = (XSComplexTypeDecl) this.fCurrentType;
                        if (xSComplexTypeDecl.getAbstract()) {
                            reportSchemaError("cvc-type.2", new Object[]{qName.rawname});
                        }
                        if (this.fNormalizeData && xSComplexTypeDecl.fContentType == 1) {
                            if (xSComplexTypeDecl.fXSSimpleType.getVariety() == s) {
                                this.fUnionType = true;
                            } else {
                                this.fWhiteSpace = xSComplexTypeDecl.fXSSimpleType.getWhitespace();
                            }
                        }
                    } else if (this.fNormalizeData) {
                        XSSimpleType xSSimpleType = (XSSimpleType) this.fCurrentType;
                        if (xSSimpleType.getVariety() == 3) {
                            this.fUnionType = true;
                        } else {
                            this.fWhiteSpace = xSSimpleType.getWhitespace();
                        }
                    }
                } catch (DatatypeException unused) {
                }
                this.fCurrentCM = null;
                if (this.fCurrentType.getTypeCategory() == 15) {
                    this.fCurrentCM = ((XSComplexTypeDecl) this.fCurrentType).getContentModel(this.fCMBuilder);
                }
                this.fCurrCMState = null;
                XSCMValidator xSCMValidator2 = this.fCurrentCM;
                if (xSCMValidator2 != null) {
                    this.fCurrCMState = xSCMValidator2.startContentModel();
                }
                String value2 = xMLAttributes.getValue(SchemaSymbols.URI_XSI, SchemaSymbols.XSI_NIL);
                if (value2 != null && this.fCurrentElemDecl != null) {
                    this.fNil = getXsiNil(qName, value2);
                }
                XSAttributeGroupDecl attrGrp = this.fCurrentType.getTypeCategory() == 15 ? ((XSComplexTypeDecl) this.fCurrentType).getAttrGrp() : null;
                if (this.fIDCChecking) {
                    this.fValueStoreCache.startElement();
                    this.fMatcherStack.pushContext();
                    XSElementDecl xSElementDecl5 = this.fCurrentElemDecl;
                    if (xSElementDecl5 != null && xSElementDecl5.fIDCPos > 0) {
                        this.fIdConstraint = true;
                        this.fValueStoreCache.initValueStoresFor(xSElementDecl5, this);
                    }
                }
                processAttributes(qName, xMLAttributes, attrGrp);
                if (attrGrp != null) {
                    addDefaultAttributes(qName, xMLAttributes, attrGrp);
                }
                int matcherCount = this.fMatcherStack.getMatcherCount();
                for (int i10 = 0; i10 < matcherCount; i10++) {
                    this.fMatcherStack.getMatcherAt(i10).startElement(qName, xMLAttributes);
                }
                if (!this.fAugPSVI) {
                    return augmentations;
                }
                Augmentations emptyAugs = getEmptyAugs(augmentations);
                ElementPSVImpl elementPSVImpl = this.fCurrentPSVI;
                elementPSVImpl.fValidationContext = this.fValidationRoot;
                elementPSVImpl.fDeclaration = this.fCurrentElemDecl;
                elementPSVImpl.fTypeDecl = this.fCurrentType;
                elementPSVImpl.fNotation = this.fNotation;
                elementPSVImpl.fNil = this.fNil;
                return emptyAugs;
            }
            this.fSkipValidationDepth = this.fElementDepth;
            if (this.fAugPSVI) {
                return getEmptyAugs(augmentations);
            }
        }
        return augmentations;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void ignorableWhitespace(XMLString xMLString, Augmentations augmentations) throws XNIException {
        handleIgnorableWhitespace(xMLString);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.ignorableWhitespace(xMLString, augmentations);
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x009b  */
    /* JADX WARN: Code duplicated, block: B:47:0x00b1  */
    public void processAttributes(com.sun.org.apache.xerces.internal.xni.QName qName, XMLAttributes xMLAttributes, XSAttributeGroupDecl xSAttributeGroupDecl) {
        XSWildcardDecl xSWildcardDecl;
        XSObjectList xSObjectList;
        int i;
        String str;
        String str2;
        XSWildcardDecl xSWildcardDecl2;
        String str3;
        char c;
        XSAttributeUseImpl xSAttributeUseImpl;
        XSAttributeDecl xSAttributeDecl;
        XSAttributeDecl xSAttributeDecl2;
        XMLAttributes xMLAttributes2 = xMLAttributes;
        int length = xMLAttributes2.getLength();
        XSTypeDefinition xSTypeDefinition = this.fCurrentType;
        char c2 = 16;
        boolean z = xSTypeDefinition == null || xSTypeDefinition.getTypeCategory() == 16;
        if (z) {
            xSWildcardDecl = null;
            xSObjectList = null;
            i = 0;
        } else {
            XSObjectList attributeUses = xSAttributeGroupDecl.getAttributeUses();
            int length2 = attributeUses.getLength();
            xSWildcardDecl = xSAttributeGroupDecl.fAttributeWC;
            xSObjectList = attributeUses;
            i = length2;
        }
        String str4 = null;
        int i2 = 0;
        AttributePSVImpl attributePSVImpl = null;
        while (i2 < length) {
            xMLAttributes2.getName(i2, this.fTempQName);
            if (this.fAugPSVI || this.fIdConstraint) {
                Augmentations augmentations = xMLAttributes2.getAugmentations(i2);
                AttributePSVImpl attributePSVImpl2 = (AttributePSVImpl) augmentations.getItem(Constants.ATTRIBUTE_PSVI);
                if (attributePSVImpl2 != null) {
                    attributePSVImpl2.reset();
                    attributePSVImpl = attributePSVImpl2;
                } else {
                    AttributePSVImpl attributePSVImpl3 = new AttributePSVImpl();
                    augmentations.putItem(Constants.ATTRIBUTE_PSVI, attributePSVImpl3);
                    attributePSVImpl = attributePSVImpl3;
                }
                attributePSVImpl.fValidationContext = this.fValidationRoot;
            }
            AttributePSVImpl attributePSVImpl4 = attributePSVImpl;
            com.sun.org.apache.xerces.internal.xni.QName qName2 = this.fTempQName;
            if (qName2.uri != SchemaSymbols.URI_XSI) {
                str2 = str4;
                xSWildcardDecl2 = xSWildcardDecl;
                attributePSVImpl4 = attributePSVImpl4;
                com.sun.org.apache.xerces.internal.xni.QName qName3 = qName;
                i2 = i2;
                str3 = qName2.rawname;
                if (str3 != XMLSymbols.PREFIX_XMLNS || str3.startsWith("xmlns:")) {
                    i2 = i2;
                    c = c2;
                    attributePSVImpl4 = attributePSVImpl4;
                } else if (z) {
                    reportSchemaError("cvc-type.3.1.1", new Object[]{qName3.rawname, this.fTempQName.rawname});
                    i2 = i2;
                    c = c2;
                    attributePSVImpl4 = attributePSVImpl4;
                } else {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= i) {
                            xSAttributeUseImpl = null;
                            break;
                        }
                        XSAttributeUseImpl xSAttributeUseImpl2 = (XSAttributeUseImpl) xSObjectList.item(i3);
                        XSAttributeDecl xSAttributeDecl3 = xSAttributeUseImpl2.fAttrDecl;
                        String str5 = xSAttributeDecl3.fName;
                        com.sun.org.apache.xerces.internal.xni.QName qName4 = this.fTempQName;
                        if (str5 == qName4.localpart && xSAttributeDecl3.fTargetNamespace == qName4.uri) {
                            xSAttributeUseImpl = xSAttributeUseImpl2;
                            break;
                        }
                        i3++;
                    }
                    if (xSAttributeUseImpl != null || (xSWildcardDecl2 != null && xSWildcardDecl2.allowNamespace(this.fTempQName.uri))) {
                        if (xSAttributeUseImpl != null) {
                            xSAttributeDecl = xSAttributeUseImpl.fAttrDecl;
                            c = 16;
                        } else if (xSWildcardDecl2.fProcessContents != 2) {
                            com.sun.org.apache.xerces.internal.xni.QName qName5 = this.fTempQName;
                            SchemaGrammar schemaGrammarFindSchemaGrammar = findSchemaGrammar((short) 6, qName5.uri, qName, qName5, xMLAttributes);
                            XSAttributeDecl globalAttributeDecl = schemaGrammarFindSchemaGrammar != null ? schemaGrammarFindSchemaGrammar.getGlobalAttributeDecl(this.fTempQName.localpart) : null;
                            if (globalAttributeDecl == null) {
                                if (xSWildcardDecl2.fProcessContents == 1) {
                                    reportSchemaError("cvc-complex-type.3.2.2", new Object[]{qName.rawname, this.fTempQName.rawname});
                                }
                                i2 = i2;
                                attributePSVImpl4 = attributePSVImpl4;
                                c = 16;
                            } else {
                                c = 16;
                                if (globalAttributeDecl.fType.getTypeCategory() == 16 && globalAttributeDecl.fType.isIDType()) {
                                    if (str2 != null) {
                                        reportSchemaError("cvc-complex-type.5.1", new Object[]{qName.rawname, globalAttributeDecl.fName, str2});
                                    } else {
                                        str2 = globalAttributeDecl.fName;
                                    }
                                }
                                qName3 = qName;
                                xSAttributeDecl = globalAttributeDecl;
                            }
                        }
                        processOneAttribute(qName3, xMLAttributes, i2, xSAttributeDecl, xSAttributeUseImpl, attributePSVImpl4);
                    } else {
                        reportSchemaError("cvc-complex-type.3.2.2", new Object[]{qName3.rawname, this.fTempQName.rawname});
                        this.fNFullValidationDepth = this.fElementDepth;
                    }
                    i2 = i2;
                    attributePSVImpl4 = attributePSVImpl4;
                    c = 16;
                }
            } else {
                String str6 = qName2.localpart;
                if (str6 == SchemaSymbols.XSI_TYPE) {
                    xSAttributeDecl2 = XSI_TYPE;
                } else if (str6 == SchemaSymbols.XSI_NIL) {
                    xSAttributeDecl2 = XSI_NIL;
                } else if (str6 == SchemaSymbols.XSI_SCHEMALOCATION) {
                    xSAttributeDecl2 = XSI_SCHEMALOCATION;
                } else {
                    xSAttributeDecl2 = str6 == SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION ? XSI_NONAMESPACESCHEMALOCATION : null;
                }
                if (xSAttributeDecl2 != null) {
                    XSAttributeDecl xSAttributeDecl4 = xSAttributeDecl2;
                    xSWildcardDecl2 = xSWildcardDecl;
                    str2 = str4;
                    processOneAttribute(qName, xMLAttributes2, i2, xSAttributeDecl4, null, attributePSVImpl4);
                    c = c2;
                } else {
                    str2 = str4;
                    xSWildcardDecl2 = xSWildcardDecl;
                    attributePSVImpl4 = attributePSVImpl4;
                    com.sun.org.apache.xerces.internal.xni.QName qName6 = qName;
                    i2 = i2;
                    str3 = qName2.rawname;
                    if (str3 != XMLSymbols.PREFIX_XMLNS) {
                        i2 = i2;
                        c = c2;
                        attributePSVImpl4 = attributePSVImpl4;
                    } else {
                        i2 = i2;
                        c = c2;
                        attributePSVImpl4 = attributePSVImpl4;
                    }
                }
            }
            i2++;
            xMLAttributes2 = xMLAttributes;
            attributePSVImpl = attributePSVImpl4;
            c2 = c;
            xSWildcardDecl = xSWildcardDecl2;
            str4 = str2;
        }
        String str7 = str4;
        if (z || (str = xSAttributeGroupDecl.fIDAttrName) == null || str7 == null) {
            return;
        }
        reportSchemaError("cvc-complex-type.5.2", new Object[]{qName.rawname, str7, str});
    }

    public void processElementContent(com.sun.org.apache.xerces.internal.xni.QName qName) {
        ValidatedInfo validatedInfo;
        XSElementDecl xSElementDecl = this.fCurrentElemDecl;
        if (xSElementDecl != null && (validatedInfo = xSElementDecl.fDefault) != null && !this.fSawText && !this.fSubElement && !this.fNil) {
            String strStringValue = validatedInfo.stringValue();
            int length = strStringValue.length();
            XMLString xMLString = this.fNormalizedStr;
            char[] cArr = xMLString.ch;
            if (cArr == null || cArr.length < length) {
                xMLString.ch = new char[length];
            }
            strStringValue.getChars(0, length, xMLString.ch, 0);
            XMLString xMLString2 = this.fNormalizedStr;
            xMLString2.offset = 0;
            xMLString2.length = length;
            this.fDefaultValue = xMLString2;
        }
        this.fValidatedInfo.normalizedValue = null;
        if (this.fNil && (this.fSubElement || this.fSawText)) {
            reportSchemaError("cvc-elt.3.2.1", new Object[]{qName.rawname, SchemaSymbols.URI_XSI + "," + SchemaSymbols.XSI_NIL});
        }
        this.fValidatedInfo.reset();
        XSElementDecl xSElementDecl2 = this.fCurrentElemDecl;
        if (xSElementDecl2 == null || xSElementDecl2.getConstraintType() == 0 || this.fSubElement || this.fSawText || this.fNil) {
            Object objElementLocallyValidType = elementLocallyValidType(qName, this.fBuffer);
            XSElementDecl xSElementDecl3 = this.fCurrentElemDecl;
            if (xSElementDecl3 != null && xSElementDecl3.getConstraintType() == 2 && !this.fNil) {
                String string = this.fBuffer.toString();
                if (this.fSubElement) {
                    reportSchemaError("cvc-elt.5.2.2.1", new Object[]{qName.rawname});
                }
                short typeCategory = this.fCurrentType.getTypeCategory();
                XSTypeDefinition xSTypeDefinition = this.fCurrentType;
                if (typeCategory == 15) {
                    short s = ((XSComplexTypeDecl) xSTypeDefinition).fContentType;
                    if (s == 3) {
                        if (!this.fCurrentElemDecl.fDefault.normalizedValue.equals(string)) {
                            reportSchemaError("cvc-elt.5.2.2.2.1", new Object[]{qName.rawname, string, this.fCurrentElemDecl.fDefault.normalizedValue});
                        }
                    } else if (s == 1 && objElementLocallyValidType != null && (!ValidatedInfo.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) || !objElementLocallyValidType.equals(this.fCurrentElemDecl.fDefault.actualValue))) {
                        reportSchemaError("cvc-elt.5.2.2.2.2", new Object[]{qName.rawname, string, this.fCurrentElemDecl.fDefault.stringValue()});
                    }
                } else if (xSTypeDefinition.getTypeCategory() == 16 && objElementLocallyValidType != null && (!ValidatedInfo.isComparable(this.fValidatedInfo, this.fCurrentElemDecl.fDefault) || !objElementLocallyValidType.equals(this.fCurrentElemDecl.fDefault.actualValue))) {
                    reportSchemaError("cvc-elt.5.2.2.2.2", new Object[]{qName.rawname, string, this.fCurrentElemDecl.fDefault.stringValue()});
                }
            }
        } else {
            XSTypeDefinition xSTypeDefinition2 = this.fCurrentType;
            XSElementDecl xSElementDecl4 = this.fCurrentElemDecl;
            if (xSTypeDefinition2 != xSElementDecl4.fType && XSConstraints.ElementDefaultValidImmediate(xSTypeDefinition2, xSElementDecl4.fDefault.stringValue(), this.fState4XsiType, null) == null) {
                reportSchemaError("cvc-elt.5.1.1", new Object[]{qName.rawname, this.fCurrentType.getName(), this.fCurrentElemDecl.fDefault.stringValue()});
            }
            elementLocallyValidType(qName, this.fCurrentElemDecl.fDefault.stringValue());
        }
        if (this.fDefaultValue == null && this.fNormalizeData && this.fDocumentHandler != null && this.fUnionType) {
            String string2 = this.fValidatedInfo.normalizedValue;
            if (string2 == null) {
                string2 = this.fBuffer.toString();
            }
            int length2 = string2.length();
            XMLString xMLString3 = this.fNormalizedStr;
            char[] cArr2 = xMLString3.ch;
            if (cArr2 == null || cArr2.length < length2) {
                xMLString3.ch = new char[length2];
            }
            string2.getChars(0, length2, xMLString3.ch, 0);
            XMLString xMLString4 = this.fNormalizedStr;
            xMLString4.offset = 0;
            xMLString4.length = length2;
            this.fDocumentHandler.characters(xMLString4, null);
        }
    }

    public void processOneAttribute(com.sun.org.apache.xerces.internal.xni.QName qName, XMLAttributes xMLAttributes, int i, XSAttributeDecl xSAttributeDecl, XSAttributeUseImpl xSAttributeUseImpl, AttributePSVImpl attributePSVImpl) {
        int i2;
        String value = xMLAttributes.getValue(i);
        this.fXSIErrorReporter.pushContext();
        XSSimpleType xSSimpleType = xSAttributeDecl.fType;
        Object objValidate = null;
        try {
            objValidate = xSSimpleType.validate(value, (ValidationContext) this.fValidationState, this.fValidatedInfo);
            if (this.fNormalizeData) {
                xMLAttributes.setValue(i, this.fValidatedInfo.normalizedValue);
            }
            if (xSSimpleType.getVariety() == 1 && xSSimpleType.getPrimitiveKind() == 20) {
                com.sun.org.apache.xerces.internal.xni.QName qName2 = (com.sun.org.apache.xerces.internal.xni.QName) objValidate;
                SchemaGrammar grammar = this.fGrammarBucket.getGrammar(qName2.uri);
                if (grammar != null) {
                    this.fNotation = grammar.getGlobalNotationDecl(qName2.localpart);
                }
            }
        } catch (InvalidDatatypeValueException e) {
            reportSchemaError(e.getKey(), e.getArgs());
            reportSchemaError("cvc-attribute.3", new Object[]{qName.rawname, this.fTempQName.rawname, value, xSSimpleType instanceof XSSimpleTypeDecl ? ((XSSimpleTypeDecl) xSSimpleType).getTypeName() : xSSimpleType.getName()});
        }
        if (objValidate != null && xSAttributeDecl.getConstraintType() == 2 && (!ValidatedInfo.isComparable(this.fValidatedInfo, xSAttributeDecl.fDefault) || !objValidate.equals(xSAttributeDecl.fDefault.actualValue))) {
            reportSchemaError("cvc-attribute.4", new Object[]{qName.rawname, this.fTempQName.rawname, value, xSAttributeDecl.fDefault.stringValue()});
        }
        if (objValidate != null && xSAttributeUseImpl != null && xSAttributeUseImpl.fConstraintType == 2 && (!ValidatedInfo.isComparable(this.fValidatedInfo, xSAttributeUseImpl.fDefault) || !objValidate.equals(xSAttributeUseImpl.fDefault.actualValue))) {
            reportSchemaError("cvc-complex-type.3.1", new Object[]{qName.rawname, this.fTempQName.rawname, value, xSAttributeUseImpl.fDefault.stringValue()});
        }
        if (this.fIdConstraint) {
            attributePSVImpl.fValue.copyFrom(this.fValidatedInfo);
        }
        if (this.fAugPSVI) {
            attributePSVImpl.fDeclaration = xSAttributeDecl;
            attributePSVImpl.fTypeDecl = xSSimpleType;
            attributePSVImpl.fValue.copyFrom(this.fValidatedInfo);
            attributePSVImpl.fValidationAttempted = (short) 2;
            if (!this.fUseGrammarPoolOnly && ((i2 = this.fElementDepth) >= this.fIgnoreXSITypeDepth || this.fCurrentElemDecl != null)) {
                this.fNNoneValidationDepth = i2;
            }
            String[] strArrMergeContext = this.fXSIErrorReporter.mergeContext();
            attributePSVImpl.fErrors = strArrMergeContext;
            attributePSVImpl.fValidity = strArrMergeContext == null ? (short) 2 : (short) 1;
        }
    }

    public void processRootElementDeclQName(QName qName, com.sun.org.apache.xerces.internal.xni.QName qName2) {
        String localPart;
        String strAddSymbol = this.fSymbolTable.addSymbol(qName.getNamespaceURI());
        if (strAddSymbol != null && strAddSymbol.equals("")) {
            strAddSymbol = null;
        }
        SchemaGrammar schemaGrammarFindSchemaGrammar = findSchemaGrammar((short) 5, strAddSymbol, null, null, null);
        if (schemaGrammarFindSchemaGrammar != null) {
            this.fCurrentElemDecl = schemaGrammarFindSchemaGrammar.getGlobalElementDecl(qName.getLocalPart());
        }
        XSElementDecl xSElementDecl = this.fCurrentElemDecl;
        if (xSElementDecl != null) {
            checkElementMatchesRootElementDecl(xSElementDecl, qName2);
            return;
        }
        if (qName.getPrefix().equals("")) {
            localPart = qName.getLocalPart();
        } else {
            localPart = qName.getPrefix() + ":" + qName.getLocalPart();
        }
        reportSchemaError("cvc-elt.1.a", new Object[]{localPart});
    }

    public void processRootTypeQName(QName qName) {
        XMLSchemaValidator xMLSchemaValidator;
        String localPart;
        String strAddSymbol = this.fSymbolTable.addSymbol(qName.getNamespaceURI());
        if (strAddSymbol != null && strAddSymbol.equals("")) {
            strAddSymbol = null;
        }
        String str = strAddSymbol;
        if (SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(str)) {
            this.fCurrentType = SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(qName.getLocalPart());
            xMLSchemaValidator = this;
        } else {
            xMLSchemaValidator = this;
            SchemaGrammar schemaGrammarFindSchemaGrammar = xMLSchemaValidator.findSchemaGrammar((short) 5, str, null, null, null);
            if (schemaGrammarFindSchemaGrammar != null) {
                xMLSchemaValidator.fCurrentType = schemaGrammarFindSchemaGrammar.getGlobalTypeDecl(qName.getLocalPart());
            }
        }
        if (xMLSchemaValidator.fCurrentType == null) {
            if (qName.getPrefix().equals("")) {
                localPart = qName.getLocalPart();
            } else {
                localPart = qName.getPrefix() + ":" + qName.getLocalPart();
            }
            xMLSchemaValidator.reportSchemaError("cvc-type.1", new Object[]{localPart});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.processingInstruction(str, xMLString, augmentations);
        }
    }

    public void reportSchemaError(String str, Object[] objArr) {
        if (this.fDoValidation) {
            this.fXSIErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, str, objArr, (short) 1);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        this.fIdConstraint = false;
        this.fLocationPairs.clear();
        this.fValidationState.resetIDTables();
        this.fSchemaLoader.reset(xMLComponentManager);
        this.fCurrentElemDecl = null;
        this.fCurrentCM = null;
        this.fCurrCMState = null;
        this.fSkipValidationDepth = -1;
        this.fNFullValidationDepth = -1;
        this.fNNoneValidationDepth = -1;
        this.fElementDepth = -1;
        this.fSubElement = false;
        this.fSchemaDynamicValidation = false;
        this.fEntityRef = false;
        this.fInCDATA = false;
        this.fMatcherStack.clear();
        this.fXSIErrorReporter.reset((XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter"));
        if (!xMLComponentManager.getFeature(PARSER_SETTINGS, true)) {
            this.fValidationManager.addValidationState(this.fValidationState);
            this.nodeFactory.reset();
            XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
            return;
        }
        this.nodeFactory.reset(xMLComponentManager);
        SymbolTable symbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        if (symbolTable != this.fSymbolTable) {
            this.fSymbolTable = symbolTable;
        }
        this.fNamespaceGrowth = xMLComponentManager.getFeature(NAMESPACE_GROWTH, false);
        boolean feature = xMLComponentManager.getFeature(DYNAMIC_VALIDATION, false);
        this.fDynamicValidation = feature;
        if (feature) {
            this.fDoValidation = true;
        } else {
            this.fDoValidation = xMLComponentManager.getFeature(VALIDATION, false);
        }
        boolean z = this.fDoValidation;
        if (z) {
            this.fDoValidation = z | xMLComponentManager.getFeature(SCHEMA_VALIDATION, false);
        }
        this.fFullChecking = xMLComponentManager.getFeature(SCHEMA_FULL_CHECKING, false);
        this.fNormalizeData = xMLComponentManager.getFeature(NORMALIZE_DATA, false);
        this.fSchemaElementDefault = xMLComponentManager.getFeature(SCHEMA_ELEMENT_DEFAULT, false);
        this.fAugPSVI = xMLComponentManager.getFeature(SCHEMA_AUGMENT_PSVI, true);
        this.fSchemaType = (String) xMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", null);
        this.fUseGrammarPoolOnly = xMLComponentManager.getFeature(USE_GRAMMAR_POOL_ONLY, false);
        this.fEntityResolver = (XMLEntityResolver) xMLComponentManager.getProperty(ENTITY_MANAGER);
        ValidationManager validationManager = (ValidationManager) xMLComponentManager.getProperty(VALIDATION_MANAGER);
        this.fValidationManager = validationManager;
        validationManager.addValidationState(this.fValidationState);
        this.fValidationState.setSymbolTable(this.fSymbolTable);
        try {
            Object property = xMLComponentManager.getProperty(ROOT_TYPE_DEF);
            if (property == null) {
                this.fRootTypeQName = null;
                this.fRootTypeDefinition = null;
            } else if (property instanceof QName) {
                this.fRootTypeQName = (QName) property;
                this.fRootTypeDefinition = null;
            } else {
                this.fRootTypeDefinition = (XSTypeDefinition) property;
                this.fRootTypeQName = null;
            }
        } catch (XMLConfigurationException unused) {
            this.fRootTypeQName = null;
            this.fRootTypeDefinition = null;
        }
        try {
            Object property2 = xMLComponentManager.getProperty(ROOT_ELEMENT_DECL);
            if (property2 == null) {
                this.fRootElementDeclQName = null;
                this.fRootElementDeclaration = null;
            } else if (property2 instanceof QName) {
                this.fRootElementDeclQName = (QName) property2;
                this.fRootElementDeclaration = null;
            } else {
                this.fRootElementDeclaration = (XSElementDecl) property2;
                this.fRootElementDeclQName = null;
            }
        } catch (XMLConfigurationException unused2) {
            this.fRootElementDeclQName = null;
            this.fRootElementDeclaration = null;
        }
        this.fIgnoreXSITypeDepth = xMLComponentManager.getFeature(IGNORE_XSI_TYPE, false) ? 0 : -1;
        try {
            this.fIDCChecking = xMLComponentManager.getFeature(IDENTITY_CONSTRAINT_CHECKING);
        } catch (XMLConfigurationException unused3) {
            this.fIDCChecking = true;
        }
        try {
            this.fValidationState.setIdIdrefChecking(xMLComponentManager.getFeature(ID_IDREF_CHECKING));
        } catch (XMLConfigurationException unused4) {
            this.fValidationState.setIdIdrefChecking(true);
        }
        try {
            this.fValidationState.setUnparsedEntityChecking(xMLComponentManager.getFeature(UNPARSED_ENTITY_CHECKING));
        } catch (XMLConfigurationException unused5) {
            this.fValidationState.setUnparsedEntityChecking(true);
        }
        try {
            this.fExternalSchemas = (String) xMLComponentManager.getProperty(SCHEMA_LOCATION);
            this.fExternalNoNamespaceSchema = (String) xMLComponentManager.getProperty(SCHEMA_NONS_LOCATION);
        } catch (XMLConfigurationException unused6) {
            this.fExternalSchemas = null;
            this.fExternalNoNamespaceSchema = null;
        }
        XMLSchemaLoader.processExternalHints(this.fExternalSchemas, this.fExternalNoNamespaceSchema, this.fLocationPairs, this.fXSIErrorReporter.fErrorReporter);
        this.fJaxpSchemaSource = xMLComponentManager.getProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", null);
        this.fGrammarPool = (XMLGrammarPool) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool", null);
        this.fState4XsiType.setSymbolTable(symbolTable);
        this.fState4ApplyDefault.setSymbolTable(symbolTable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentSource
    public void setDocumentHandler(XMLDocumentHandler xMLDocumentHandler) {
        this.fDocumentHandler = xMLDocumentHandler;
        if (xMLDocumentHandler instanceof XMLParser) {
            try {
                this.reportWhitespace = ((XMLParser) xMLDocumentHandler).getFeature(REPORT_WHITESPACE);
            } catch (Exception unused) {
                this.reportWhitespace = false;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void setDocumentSource(XMLDocumentSource xMLDocumentSource) {
        this.fDocumentSource = xMLDocumentSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
        if (str.equals(ROOT_TYPE_DEF)) {
            if (obj == null) {
                this.fRootTypeQName = null;
                this.fRootTypeDefinition = null;
                return;
            } else if (obj instanceof QName) {
                this.fRootTypeQName = (QName) obj;
                this.fRootTypeDefinition = null;
                return;
            } else {
                this.fRootTypeDefinition = (XSTypeDefinition) obj;
                this.fRootTypeQName = null;
                return;
            }
        }
        if (str.equals(ROOT_ELEMENT_DECL)) {
            if (obj == null) {
                this.fRootElementDeclQName = null;
                this.fRootElementDeclaration = null;
            } else if (obj instanceof QName) {
                this.fRootElementDeclQName = (QName) obj;
                this.fRootElementDeclaration = null;
            } else {
                this.fRootElementDeclaration = (XSElementDecl) obj;
                this.fRootElementDeclQName = null;
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startCDATA(Augmentations augmentations) throws XNIException {
        this.fInCDATA = true;
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.startCDATA(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startDocument(XMLLocator xMLLocator, String str, NamespaceContext namespaceContext, Augmentations augmentations) throws XNIException {
        this.fValidationState.setNamespaceSupport(namespaceContext);
        this.fState4XsiType.setNamespaceSupport(namespaceContext);
        this.fState4ApplyDefault.setNamespaceSupport(namespaceContext);
        this.fLocator = xMLLocator;
        handleStartDocument(xMLLocator, str);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.startDocument(xMLLocator, str, namespaceContext, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startElement(com.sun.org.apache.xerces.internal.xni.QName qName, XMLAttributes xMLAttributes, Augmentations augmentations) throws XNIException {
        Augmentations augmentationsHandleStartElement = handleStartElement(qName, xMLAttributes, augmentations);
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.startElement(qName, xMLAttributes, augmentationsHandleStartElement);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void startGeneralEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        this.fEntityRef = true;
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.startGeneralEntity(str, xMLResourceIdentifier, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.FieldActivator
    public void startValueScopeFor(IdentityConstraint identityConstraint, int i) {
        this.fValueStoreCache.getValueStoreFor(identityConstraint, i).startValueScope();
    }

    public void storeLocations(String str, String str2) {
        if (str != null) {
            Map<String, XMLSchemaLoader.LocationArray> map = this.fLocationPairs;
            XMLLocator xMLLocator = this.fLocator;
            if (!XMLSchemaLoader.tokenizeSchemaLocationStr(str, map, xMLLocator == null ? null : xMLLocator.getExpandedSystemId())) {
                this.fXSIErrorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, "SchemaLocation", new Object[]{str}, (short) 0);
            }
        }
        if (str2 != null) {
            Map<String, XMLSchemaLoader.LocationArray> map2 = this.fLocationPairs;
            String str3 = XMLSymbols.EMPTY_STRING;
            XMLSchemaLoader.LocationArray locationArray = map2.get(str3);
            if (locationArray == null) {
                locationArray = new XMLSchemaLoader.LocationArray();
                this.fLocationPairs.put(str3, locationArray);
            }
            XMLLocator xMLLocator2 = this.fLocator;
            if (xMLLocator2 != null) {
                try {
                    str2 = XMLEntityManager.expandSystemId(str2, xMLLocator2.getExpandedSystemId(), false);
                } catch (URI.MalformedURIException unused) {
                }
            }
            locationArray.addLocation(str2);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.textDecl(str, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler
    public void xmlDecl(String str, String str2, String str3, Augmentations augmentations) throws XNIException {
        XMLDocumentHandler xMLDocumentHandler = this.fDocumentHandler;
        if (xMLDocumentHandler != null) {
            xMLDocumentHandler.xmlDecl(str, str2, str3, augmentations);
        }
    }

    public static final class LocalIDKey {
        public int fDepth;
        public IdentityConstraint fId;

        public LocalIDKey(IdentityConstraint identityConstraint, int i) {
            this.fId = identityConstraint;
            this.fDepth = i;
        }

        public boolean equals(Object obj) {
            if (obj instanceof LocalIDKey) {
                LocalIDKey localIDKey = (LocalIDKey) obj;
                if (localIDKey.fId == this.fId && localIDKey.fDepth == this.fDepth) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.fId.hashCode() + this.fDepth;
        }

        public LocalIDKey() {
        }
    }

    public static final class ShortVector {
        private short[] fData;
        private int fLength;

        public ShortVector(int i) {
            this.fData = new short[i];
        }

        private void ensureCapacity(int i) {
            short[] sArr = this.fData;
            if (sArr == null) {
                this.fData = new short[8];
            } else if (sArr.length <= i) {
                short[] sArr2 = new short[sArr.length * 2];
                System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
                this.fData = sArr2;
            }
        }

        public void add(short s) {
            ensureCapacity(this.fLength + 1);
            short[] sArr = this.fData;
            int i = this.fLength;
            this.fLength = i + 1;
            sArr[i] = s;
        }

        public void clear() {
            this.fLength = 0;
        }

        public boolean contains(short s) {
            for (int i = 0; i < this.fLength; i++) {
                if (this.fData[i] == s) {
                    return true;
                }
            }
            return false;
        }

        public int length() {
            return this.fLength;
        }

        public short valueAt(int i) {
            return this.fData[i];
        }

        public ShortVector() {
        }
    }

    public final class XSIErrorReporter {
        int fContextCount;
        XMLErrorReporter fErrorReporter;
        Vector<String> fErrors = new Vector<>();
        int[] fContext = new int[8];

        public XSIErrorReporter() {
        }

        public String[] mergeContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return null;
            }
            int[] iArr = this.fContext;
            int i = this.fContextCount - 1;
            this.fContextCount = i;
            int i2 = iArr[i];
            int size = this.fErrors.size() - i2;
            if (size == 0) {
                return null;
            }
            String[] strArr = new String[size];
            for (int i3 = 0; i3 < size; i3++) {
                strArr[i3] = this.fErrors.get(i2 + i3);
            }
            return strArr;
        }

        public String[] popContext() {
            if (!XMLSchemaValidator.this.fAugPSVI) {
                return null;
            }
            int[] iArr = this.fContext;
            int i = this.fContextCount - 1;
            this.fContextCount = i;
            int i2 = iArr[i];
            int size = this.fErrors.size() - i2;
            if (size == 0) {
                return null;
            }
            String[] strArr = new String[size];
            int i3 = 0;
            while (true) {
                Vector<String> vector = this.fErrors;
                if (i3 >= size) {
                    vector.setSize(i2);
                    return strArr;
                }
                strArr[i3] = vector.get(i2 + i3);
                i3++;
            }
        }

        public void pushContext() {
            if (XMLSchemaValidator.this.fAugPSVI) {
                int i = this.fContextCount;
                int[] iArr = this.fContext;
                if (i == iArr.length) {
                    int[] iArr2 = new int[i + 8];
                    System.arraycopy(iArr, 0, iArr2, 0, i);
                    this.fContext = iArr2;
                }
                int[] iArr3 = this.fContext;
                int i2 = this.fContextCount;
                this.fContextCount = i2 + 1;
                iArr3[i2] = this.fErrors.size();
            }
        }

        public void reportError(XMLLocator xMLLocator, String str, String str2, Object[] objArr, short s) throws XNIException {
            String strReportError = this.fErrorReporter.reportError(xMLLocator, str, str2, objArr, s);
            if (XMLSchemaValidator.this.fAugPSVI) {
                this.fErrors.add(str2);
                this.fErrors.add(strReportError);
            }
        }

        public void reset(XMLErrorReporter xMLErrorReporter) {
            this.fErrorReporter = xMLErrorReporter;
            this.fErrors.removeAllElements();
            this.fContextCount = 0;
        }

        public void reportError(String str, String str2, Object[] objArr, short s) throws XNIException {
            String strReportError = this.fErrorReporter.reportError(str, str2, objArr, s);
            if (XMLSchemaValidator.this.fAugPSVI) {
                this.fErrors.add(str2);
                this.fErrors.add(strReportError);
            }
        }
    }

    private void setLocationHints(XSDDescription xSDDescription, String[] strArr, SchemaGrammar schemaGrammar) {
        int length = strArr.length;
        if (schemaGrammar == null) {
            String[] strArr2 = new String[length];
            this.fXSDDescription.fLocationHints = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, length);
            return;
        }
        setLocationHints(xSDDescription, strArr, schemaGrammar.getDocumentLocations());
    }

    public abstract class ValueStoreBase implements ValueStore {
        protected int fFieldCount;
        protected Field[] fFields;
        protected IdentityConstraint fIdentityConstraint;
        protected ShortList[] fLocalItemValueTypes;
        protected short[] fLocalValueTypes;
        protected Object[] fLocalValues;
        protected int fValuesCount;
        protected boolean fHasValue = false;
        public final Vector<Object> fValues = new Vector<>();
        public ShortVector fValueTypes = null;
        public Vector<ShortList> fItemValueTypes = null;
        private boolean fUseValueTypeVector = false;
        private int fValueTypesLength = 0;
        private short fValueType = 0;
        private boolean fUseItemValueTypeVector = false;
        private int fItemValueTypesLength = 0;
        private ShortList fItemValueType = null;
        final StringBuilder fTempBuffer = new StringBuilder();

        public ValueStoreBase(IdentityConstraint identityConstraint) {
            this.fFieldCount = 0;
            this.fFields = null;
            this.fLocalValues = null;
            this.fLocalValueTypes = null;
            this.fLocalItemValueTypes = null;
            this.fIdentityConstraint = identityConstraint;
            int fieldCount = identityConstraint.getFieldCount();
            this.fFieldCount = fieldCount;
            this.fFields = new Field[fieldCount];
            this.fLocalValues = new Object[fieldCount];
            this.fLocalValueTypes = new short[fieldCount];
            this.fLocalItemValueTypes = new ShortList[fieldCount];
            for (int i = 0; i < this.fFieldCount; i++) {
                this.fFields[i] = this.fIdentityConstraint.getFieldAt(i);
            }
        }

        private void addItemValueType(ShortList shortList) {
            if (this.fUseItemValueTypeVector) {
                this.fItemValueTypes.add(shortList);
                return;
            }
            int i = this.fItemValueTypesLength;
            this.fItemValueTypesLength = i + 1;
            if (i == 0) {
                this.fItemValueType = shortList;
                return;
            }
            ShortList shortList2 = this.fItemValueType;
            if (shortList2 == shortList) {
                return;
            }
            if (shortList2 != null && shortList2.equals(shortList)) {
                return;
            }
            int i2 = 1;
            this.fUseItemValueTypeVector = true;
            if (this.fItemValueTypes == null) {
                this.fItemValueTypes = new Vector<>(this.fItemValueTypesLength * 2);
            }
            while (true) {
                int i3 = this.fItemValueTypesLength;
                Vector<ShortList> vector = this.fItemValueTypes;
                if (i2 >= i3) {
                    vector.add(shortList);
                    return;
                } else {
                    vector.add(this.fItemValueType);
                    i2++;
                }
            }
        }

        private void addValueType(short s) {
            if (this.fUseValueTypeVector) {
                this.fValueTypes.add(s);
                return;
            }
            int i = this.fValueTypesLength;
            int i2 = i + 1;
            this.fValueTypesLength = i2;
            if (i == 0) {
                this.fValueType = s;
                return;
            }
            if (this.fValueType == s) {
                return;
            }
            int i3 = 1;
            this.fUseValueTypeVector = true;
            if (this.fValueTypes == null) {
                this.fValueTypes = new ShortVector(i2 * 2);
            }
            while (true) {
                int i4 = this.fValueTypesLength;
                ShortVector shortVector = this.fValueTypes;
                if (i3 >= i4) {
                    shortVector.add(s);
                    return;
                } else {
                    shortVector.add(this.fValueType);
                    i3++;
                }
            }
        }

        private ShortList getItemValueTypeAt(int i) {
            return this.fUseItemValueTypeVector ? this.fItemValueTypes.get(i) : this.fItemValueType;
        }

        private short getValueTypeAt(int i) {
            return this.fUseValueTypeVector ? this.fValueTypes.valueAt(i) : this.fValueType;
        }

        private boolean itemValueTypeContains(ShortList shortList) {
            if (this.fUseItemValueTypeVector) {
                return this.fItemValueTypes.contains(shortList);
            }
            ShortList shortList2 = this.fItemValueType;
            if (shortList2 != shortList) {
                return shortList2 != null && shortList2.equals(shortList);
            }
            return true;
        }

        private boolean valueTypeContains(short s) {
            if (this.fUseValueTypeVector) {
                return this.fValueTypes.contains(s);
            }
            return this.fValueType == s;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.ValueStore
        public void addValue(Field field, boolean z, Object obj, short s, ShortList shortList) {
            int i = this.fFieldCount - 1;
            while (i > -1 && this.fFields[i] != field) {
                i--;
            }
            if (i == -1) {
                XMLSchemaValidator.this.reportSchemaError("UnknownField", new Object[]{field.toString(), this.fIdentityConstraint.getElementName(), this.fIdentityConstraint.getIdentityConstraintName()});
                return;
            }
            if (z) {
                this.fValuesCount++;
                this.fHasValue = true;
            } else {
                XMLSchemaValidator.this.reportSchemaError("FieldMultipleMatch", new Object[]{field.toString(), this.fIdentityConstraint.getIdentityConstraintName()});
            }
            this.fLocalValues[i] = obj;
            this.fLocalValueTypes[i] = s;
            this.fLocalItemValueTypes[i] = shortList;
            if (this.fValuesCount == this.fFieldCount) {
                checkDuplicateValues();
                for (int i2 = 0; i2 < this.fFieldCount; i2++) {
                    this.fValues.add(this.fLocalValues[i2]);
                    addValueType(this.fLocalValueTypes[i2]);
                    addItemValueType(this.fLocalItemValueTypes[i2]);
                }
            }
        }

        public void append(ValueStoreBase valueStoreBase) {
            for (int i = 0; i < valueStoreBase.fValues.size(); i++) {
                this.fValues.add(valueStoreBase.fValues.get(i));
            }
        }

        public void checkDuplicateValues() {
        }

        public void clear() {
            this.fValuesCount = 0;
            this.fUseValueTypeVector = false;
            this.fValueTypesLength = 0;
            this.fValueType = (short) 0;
            this.fUseItemValueTypeVector = false;
            this.fItemValueTypesLength = 0;
            this.fItemValueType = null;
            this.fValues.setSize(0);
            ShortVector shortVector = this.fValueTypes;
            if (shortVector != null) {
                shortVector.clear();
            }
            Vector<ShortList> vector = this.fItemValueTypes;
            if (vector != null) {
                vector.setSize(0);
            }
        }

        public int contains(ValueStoreBase valueStoreBase) {
            Vector<Object> vector = valueStoreBase.fValues;
            int size = vector.size();
            if (this.fFieldCount <= 1) {
                for (int i = 0; i < size; i++) {
                    short valueTypeAt = valueStoreBase.getValueTypeAt(i);
                    if (!valueTypeContains(valueTypeAt) || !this.fValues.contains(vector.get(i)) || ((valueTypeAt == 44 || valueTypeAt == 43) && !itemValueTypeContains(valueStoreBase.getItemValueTypeAt(i)))) {
                        return i;
                    }
                }
                return -1;
            }
            int size2 = this.fValues.size();
            int i2 = 0;
            while (i2 < size) {
                int i3 = 0;
                while (i3 < size2) {
                    int i4 = 0;
                    while (true) {
                        int i5 = this.fFieldCount;
                        if (i4 < i5) {
                            int i6 = i2 + i4;
                            Object obj = vector.get(i6);
                            int i7 = i3 + i4;
                            Object obj2 = this.fValues.get(i7);
                            short valueTypeAt2 = valueStoreBase.getValueTypeAt(i6);
                            short valueTypeAt3 = getValueTypeAt(i7);
                            if (obj == obj2 || (valueTypeAt2 == valueTypeAt3 && obj != null && obj.equals(obj2))) {
                                if (valueTypeAt2 == 44 || valueTypeAt2 == 43) {
                                    ShortList itemValueTypeAt = valueStoreBase.getItemValueTypeAt(i6);
                                    ShortList itemValueTypeAt2 = getItemValueTypeAt(i7);
                                    if (itemValueTypeAt == null || itemValueTypeAt2 == null || !itemValueTypeAt.equals(itemValueTypeAt2)) {
                                    }
                                }
                                i4++;
                            }
                            i3 += this.fFieldCount;
                        } else {
                            i2 += i5;
                        }
                    }
                }
                return i2;
            }
            return -1;
        }

        public void endDocument() {
        }

        public void endDocumentFragment() {
        }

        public void endValueScope() {
            int i = this.fValuesCount;
            if (i == 0) {
                if (this.fIdentityConstraint.getCategory() == 1) {
                    XMLSchemaValidator.this.reportSchemaError("AbsentKeyValue", new Object[]{this.fIdentityConstraint.getElementName(), this.fIdentityConstraint.getIdentityConstraintName()});
                    return;
                }
                return;
            }
            if (i == this.fFieldCount || this.fIdentityConstraint.getCategory() != 1) {
                return;
            }
            IdentityConstraint identityConstraint = this.fIdentityConstraint;
            UniqueOrKey uniqueOrKey = (UniqueOrKey) identityConstraint;
            XMLSchemaValidator.this.reportSchemaError("KeyNotEnoughValues", new Object[]{identityConstraint.getElementName(), uniqueOrKey.getIdentityConstraintName()});
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.identity.ValueStore
        public void reportError(String str, Object[] objArr) {
            XMLSchemaValidator.this.reportSchemaError(str, objArr);
        }

        public void startValueScope() {
            this.fValuesCount = 0;
            for (int i = 0; i < this.fFieldCount; i++) {
                this.fLocalValues[i] = null;
                this.fLocalValueTypes[i] = 0;
                this.fLocalItemValueTypes[i] = null;
            }
        }

        public String toString() {
            String string = super.toString();
            int iLastIndexOf = string.lastIndexOf(36);
            if (iLastIndexOf != -1) {
                string = string.substring(iLastIndexOf + 1);
            }
            int iLastIndexOf2 = string.lastIndexOf(46);
            if (iLastIndexOf2 != -1) {
                string = string.substring(iLastIndexOf2 + 1);
            }
            return string + '[' + this.fIdentityConstraint + ']';
        }

        public String toString(Vector<Object> vector, int i, int i2) {
            if (i2 == 0) {
                return "";
            }
            if (i2 == 1) {
                return String.valueOf(vector.get(i));
            }
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < i2; i3++) {
                if (i3 > 0) {
                    sb.append(',');
                }
                sb.append(vector.get(i + i3));
            }
            return sb.toString();
        }

        public String toString(Object[] objArr) {
            int length = objArr.length;
            if (length == 0) {
                return "";
            }
            this.fTempBuffer.setLength(0);
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    this.fTempBuffer.append(',');
                }
                this.fTempBuffer.append(objArr[i]);
            }
            return this.fTempBuffer.toString();
        }

        public boolean contains() {
            int size = this.fValues.size();
            int i = 0;
            while (i < size) {
                int i2 = this.fFieldCount + i;
                for (int i3 = 0; i3 < this.fFieldCount; i3++) {
                    Object obj = this.fLocalValues[i3];
                    Object obj2 = this.fValues.get(i);
                    short s = this.fLocalValueTypes[i3];
                    short valueTypeAt = getValueTypeAt(i);
                    if (obj != null && obj2 != null && s == valueTypeAt && obj.equals(obj2)) {
                        if (s == 44 || s == 43) {
                            ShortList shortList = this.fLocalItemValueTypes[i3];
                            ShortList itemValueTypeAt = getItemValueTypeAt(i);
                            if (shortList == null || itemValueTypeAt == null || !shortList.equals(itemValueTypeAt)) {
                            }
                        }
                        i++;
                    }
                    i = i2;
                }
                return true;
            }
            return false;
        }
    }

    private void normalizeWhitespace(String str, boolean z) {
        XMLString xMLString;
        int i;
        int length = str.length();
        XMLString xMLString2 = this.fNormalizedStr;
        char[] cArr = xMLString2.ch;
        if (cArr == null || cArr.length < length) {
            xMLString2.ch = new char[length];
        }
        xMLString2.offset = 0;
        xMLString2.length = 0;
        boolean z2 = z;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (!XMLChar.isSpace(cCharAt)) {
                XMLString xMLString3 = this.fNormalizedStr;
                char[] cArr2 = xMLString3.ch;
                int i3 = xMLString3.length;
                xMLString3.length = i3 + 1;
                cArr2[i3] = cCharAt;
                z2 = false;
            } else if (!z2) {
                XMLString xMLString4 = this.fNormalizedStr;
                char[] cArr3 = xMLString4.ch;
                int i4 = xMLString4.length;
                xMLString4.length = i4 + 1;
                cArr3[i4] = ' ';
                z2 = z;
            }
        }
        if (!z2 || (i = (xMLString = this.fNormalizedStr).length) == 0) {
            return;
        }
        xMLString.length = i - 1;
    }
}
