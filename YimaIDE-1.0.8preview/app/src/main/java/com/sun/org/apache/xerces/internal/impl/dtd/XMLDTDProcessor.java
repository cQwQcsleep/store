package com.sun.org.apache.xerces.internal.impl.dtd;

import com.sun.org.apache.xerces.internal.impl.XMLErrorReporter;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler;
import com.sun.org.apache.xerces.internal.xni.XMLDTDHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponent;
import com.sun.org.apache.xerces.internal.xni.parser.XMLComponentManager;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelFilter;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDFilter;
import com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLDTDProcessor implements XMLComponent, XMLDTDFilter, XMLDTDContentModelFilter {
    protected static final String DTD_VALIDATOR = "http://apache.org/xml/properties/internal/validator/dtd";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    private static final Boolean[] FEATURE_DEFAULTS;
    protected static final String GRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_PROPERTIES;
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final int TOP_LEVEL_SCOPE = -1;
    protected XMLDTDContentModelHandler fDTDContentModelHandler;
    protected XMLDTDContentModelSource fDTDContentModelSource;
    protected DTDGrammar fDTDGrammar;
    protected XMLDTDHandler fDTDHandler;
    protected XMLDTDSource fDTDSource;
    protected boolean fDTDValidation;
    protected XMLErrorReporter fErrorReporter;
    protected DTDGrammarBucket fGrammarBucket;
    protected XMLGrammarPool fGrammarPool;
    protected boolean fInDTDIgnore;
    protected Locale fLocale;
    private boolean fMixed;
    private Map<String, String> fNotationEnumVals;
    private boolean fPerformValidation;
    protected SymbolTable fSymbolTable;
    private Map<String, String> fTableOfIDAttributeNames;
    private Map<String, String> fTableOfNOTATIONAttributeNames;
    protected boolean fValidation;
    protected XMLDTDValidator fValidator;
    protected boolean fWarnDuplicateAttdef;
    protected boolean fWarnOnUndeclaredElemdef;
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String WARN_ON_DUPLICATE_ATTDEF = "http://apache.org/xml/features/validation/warn-on-duplicate-attdef";
    protected static final String WARN_ON_UNDECLARED_ELEMDEF = "http://apache.org/xml/features/validation/warn-on-undeclared-elemdef";
    protected static final String NOTIFY_CHAR_REFS = "http://apache.org/xml/features/scanner/notify-char-refs";
    private static final String[] RECOGNIZED_FEATURES = {VALIDATION, WARN_ON_DUPLICATE_ATTDEF, WARN_ON_UNDECLARED_ELEMDEF, NOTIFY_CHAR_REFS};
    private final XMLEntityDecl fEntityDecl = new XMLEntityDecl();
    private final Map<String, String> fNDataDeclNotations = new HashMap();
    private String fDTDElementDeclName = null;
    private final List<String> fMixedElementTypes = new ArrayList();
    private final List<String> fDTDElementDecls = new ArrayList();

    static {
        Boolean bool = Boolean.FALSE;
        FEATURE_DEFAULTS = new Boolean[]{null, bool, bool, null};
        RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/grammar-pool", DTD_VALIDATOR};
        PROPERTY_DEFAULTS = new Object[]{null, null, null, null};
    }

    private void checkDeclaredElements(DTDGrammar dTDGrammar, int i, int i2, XMLContentSpec xMLContentSpec) {
        dTDGrammar.getContentSpec(i2, xMLContentSpec);
        short s = xMLContentSpec.type;
        if (s == 0) {
            String str = (String) xMLContentSpec.value;
            if (str == null || dTDGrammar.getElementDeclIndex(str) != -1) {
                return;
            }
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "UndeclaredElementInContentSpec", new Object[]{dTDGrammar.getElementDeclName(i).rawname, str}, (short) 0);
            return;
        }
        if (s == 4 || s == 5) {
            int i3 = ((int[]) xMLContentSpec.value)[0];
            int i4 = ((int[]) xMLContentSpec.otherValue)[0];
            checkDeclaredElements(dTDGrammar, i, i3, xMLContentSpec);
            checkDeclaredElements(dTDGrammar, i, i4, xMLContentSpec);
            return;
        }
        if (s == 2 || s == 1 || s == 3) {
            checkDeclaredElements(dTDGrammar, i, ((int[]) xMLContentSpec.value)[0], xMLContentSpec);
        }
    }

    public static void checkStandaloneEntityRef(String str, DTDGrammar dTDGrammar, XMLEntityDecl xMLEntityDecl, XMLErrorReporter xMLErrorReporter) throws XNIException {
        int entityDeclIndex = dTDGrammar.getEntityDeclIndex(str);
        if (entityDeclIndex > -1) {
            dTDGrammar.getEntityDecl(entityDeclIndex, xMLEntityDecl);
            if (xMLEntityDecl.inExternal) {
                xMLErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_REFERENCE_TO_EXTERNALLY_DECLARED_ENTITY_WHEN_STANDALONE", new Object[]{str}, (short) 1);
            }
        }
    }

    private boolean normalizeDefaultAttrValue(XMLString xMLString) {
        int i = xMLString.offset;
        int i2 = xMLString.length + i;
        int i3 = i;
        boolean z = true;
        while (i < i2) {
            char[] cArr = xMLString.ch;
            char c = cArr[i];
            if (c != ' ') {
                if (i3 != i) {
                    cArr[i3] = c;
                }
                i3++;
                z = false;
            } else if (!z) {
                cArr[i3] = ' ';
                i3++;
                z = true;
            }
            i++;
        }
        if (i3 == i2) {
            return false;
        }
        if (z) {
            i3--;
        }
        xMLString.length = i3 - xMLString.offset;
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void any(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.any(augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.any(augmentations);
        }
    }

    /* JADX WARN: Code duplicated, block: B:121:0x0175  */
    /* JADX WARN: Code duplicated, block: B:140:0x017b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:? A[LOOP:2: B:109:0x0157->B:141:?, LOOP_END, SYNTHETIC] */
    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void attributeDecl(String str, String str2, String str3, String[] strArr, String str4, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        boolean z;
        int i;
        if (str3 != XMLSymbols.fCDATASymbol && xMLString != null) {
            normalizeDefaultAttrValue(xMLString);
        }
        if (this.fValidation) {
            DTDGrammar activeGrammar = this.fDTDGrammar;
            if (activeGrammar == null) {
                activeGrammar = this.fGrammarBucket.getActiveGrammar();
            }
            int i2 = 0;
            if (activeGrammar.getAttributeDeclIndex(activeGrammar.getElementDeclIndex(str), str2) != -1) {
                if (this.fWarnDuplicateAttdef) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ATTRIBUTE_DEFINITION", new Object[]{str, str2}, (short) 0);
                }
                z = true;
            } else {
                z = false;
            }
            if (str3 == XMLSymbols.fIDSymbol) {
                if (xMLString != null && xMLString.length != 0 && (str4 == null || (str4 != XMLSymbols.fIMPLIEDSymbol && str4 != XMLSymbols.fREQUIREDSymbol))) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IDDefaultTypeInvalid", new Object[]{str2}, (short) 1);
                }
                if (!this.fTableOfIDAttributeNames.containsKey(str)) {
                    this.fTableOfIDAttributeNames.put(str, str2);
                } else if (!z) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MORE_THAN_ONE_ID_ATTRIBUTE", new Object[]{str, this.fTableOfIDAttributeNames.get(str), str2}, (short) 1);
                }
            }
            if (str3 == XMLSymbols.fNOTATIONSymbol) {
                for (String str5 : strArr) {
                    this.fNotationEnumVals.put(str5, str2);
                }
                if (!this.fTableOfNOTATIONAttributeNames.containsKey(str)) {
                    this.fTableOfNOTATIONAttributeNames.put(str, str2);
                } else if (!z) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_MORE_THAN_ONE_NOTATION_ATTRIBUTE", new Object[]{str, this.fTableOfNOTATIONAttributeNames.get(str), str2}, (short) 1);
                }
            }
            if (str3 == XMLSymbols.fENUMERATIONSymbol || str3 == XMLSymbols.fNOTATIONSymbol) {
                int i3 = 0;
                loop3: while (i3 < strArr.length) {
                    int i4 = i3 + 1;
                    for (int i5 = i4; i5 < strArr.length; i5++) {
                        if (strArr[i3].equals(strArr[i5])) {
                            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", str3 == XMLSymbols.fENUMERATIONSymbol ? "MSG_DISTINCT_TOKENS_IN_ENUMERATION" : "MSG_DISTINCT_NOTATION_IN_ENUMERATION", new Object[]{str, strArr[i3], str2}, (short) 1);
                            break loop3;
                        }
                    }
                    i3 = i4;
                }
            }
            if (xMLString != null && (str4 == null || str4 == XMLSymbols.fFIXEDSymbol)) {
                String string = xMLString.toString();
                if (str3 == XMLSymbols.fNMTOKENSSymbol || str3 == XMLSymbols.fENTITIESSymbol || str3 == XMLSymbols.fIDREFSSymbol) {
                    StringTokenizer stringTokenizer = new StringTokenizer(string, " ");
                    if (!stringTokenizer.hasMoreTokens()) {
                        i2 = 1;
                        break;
                    }
                    while (true) {
                        String strNextToken = stringTokenizer.nextToken();
                        if (str3 != XMLSymbols.fNMTOKENSSymbol) {
                            if ((str3 != XMLSymbols.fENTITIESSymbol && str3 != XMLSymbols.fIDREFSSymbol) || isValidName(strNextToken)) {
                                if (!stringTokenizer.hasMoreTokens()) {
                                    i2 = 1;
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else if (isValidNmtoken(strNextToken)) {
                            if (!stringTokenizer.hasMoreTokens()) {
                                i2 = 1;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    i = i2;
                } else {
                    i = (str3 == XMLSymbols.fENTITYSymbol || str3 == XMLSymbols.fIDSymbol || str3 == XMLSymbols.fIDREFSymbol || str3 == XMLSymbols.fNOTATIONSymbol ? isValidName(string) : !(str3 == XMLSymbols.fNMTOKENSymbol || str3 == XMLSymbols.fENUMERATIONSymbol) || isValidNmtoken(string)) ? 1 : 0;
                    if (str3 == XMLSymbols.fNOTATIONSymbol || str3 == XMLSymbols.fENUMERATIONSymbol) {
                        i = 0;
                        while (i2 < strArr.length) {
                            if (xMLString.equals(strArr[i2])) {
                                i = 1;
                            }
                            i2++;
                        }
                    }
                }
                if (i == 0) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ATT_DEFAULT_INVALID", new Object[]{str2, string}, (short) 1);
                }
            }
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.attributeDecl(str, str2, str3, strArr, str4, xMLString, xMLString2, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.attributeDecl(str, str2, str3, strArr, str4, xMLString, xMLString2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void comment(XMLString xMLString, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.comment(xMLString, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.comment(xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void element(String str, Augmentations augmentations) throws XNIException {
        if (this.fMixed && this.fValidation) {
            if (this.fMixedElementTypes.contains(str)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "DuplicateTypeInMixedContent", new Object[]{this.fDTDElementDeclName, str}, (short) 1);
            } else {
                this.fMixedElementTypes.add(str);
            }
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.element(str, augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.element(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void elementDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        if (this.fValidation) {
            if (this.fDTDElementDecls.contains(str)) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_ELEMENT_ALREADY_DECLARED", new Object[]{str}, (short) 1);
            } else {
                this.fDTDElementDecls.add(str);
            }
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.elementDecl(str, str2, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.elementDecl(str, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void empty(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.empty(augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.empty(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endAttlist(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endAttlist(augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endAttlist(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endConditional(Augmentations augmentations) throws XNIException {
        this.fInDTDIgnore = false;
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endConditional(augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endConditional(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void endContentModel(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endContentModel(augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.endContentModel(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endDTD(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endDTD(augmentations);
            XMLGrammarPool xMLGrammarPool = this.fGrammarPool;
            if (xMLGrammarPool != null) {
                xMLGrammarPool.cacheGrammars("http://www.w3.org/TR/REC-xml", new Grammar[]{this.fDTDGrammar});
            }
        }
        if (this.fValidation) {
            DTDGrammar activeGrammar = this.fDTDGrammar;
            if (activeGrammar == null) {
                activeGrammar = this.fGrammarBucket.getActiveGrammar();
            }
            for (Map.Entry<String, String> entry : this.fNDataDeclNotations.entrySet()) {
                String value = entry.getValue();
                if (activeGrammar.getNotationDeclIndex(value) == -1) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NOT_DECLARED_FOR_UNPARSED_ENTITYDECL", new Object[]{entry.getKey(), value}, (short) 1);
                }
            }
            for (Map.Entry<String, String> entry2 : this.fNotationEnumVals.entrySet()) {
                String key = entry2.getKey();
                if (activeGrammar.getNotationDeclIndex(key) == -1) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_NOTATION_NOT_DECLARED_FOR_NOTATIONTYPE_ATTRIBUTE", new Object[]{entry2.getValue(), key}, (short) 1);
                }
            }
            for (Map.Entry<String, String> entry3 : this.fTableOfNOTATIONAttributeNames.entrySet()) {
                String key2 = entry3.getKey();
                if (activeGrammar.getContentSpecType(activeGrammar.getElementDeclIndex(key2)) == 1) {
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "NoNotationOnEmptyElement", new Object[]{key2, entry3.getValue()}, (short) 1);
                }
            }
            this.fTableOfIDAttributeNames = null;
            this.fTableOfNOTATIONAttributeNames = null;
            if (this.fWarnOnUndeclaredElemdef) {
                checkDeclaredElements(activeGrammar);
            }
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endDTD(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endExternalSubset(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endExternalSubset(augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endExternalSubset(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void endGroup(Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endGroup(augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.endGroup(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void endParameterEntity(String str, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.endParameterEntity(str, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.endParameterEntity(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void externalEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        DTDGrammar activeGrammar = this.fDTDGrammar;
        if (activeGrammar == null) {
            activeGrammar = this.fGrammarBucket.getActiveGrammar();
        }
        if (activeGrammar.getEntityDeclIndex(str) == -1) {
            DTDGrammar dTDGrammar = this.fDTDGrammar;
            if (dTDGrammar != null) {
                dTDGrammar.externalEntityDecl(str, xMLResourceIdentifier, augmentations);
            }
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null) {
                xMLDTDHandler.externalEntityDecl(str, xMLResourceIdentifier, augmentations);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource
    public XMLDTDContentModelHandler getDTDContentModelHandler() {
        return this.fDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public XMLDTDContentModelSource getDTDContentModelSource() {
        return this.fDTDContentModelSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource
    public XMLDTDHandler getDTDHandler() {
        return this.fDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public XMLDTDSource getDTDSource() {
        return this.fDTDSource;
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

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void ignoredCharacters(XMLString xMLString, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.ignoredCharacters(xMLString, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.ignoredCharacters(xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void internalEntityDecl(String str, XMLString xMLString, XMLString xMLString2, Augmentations augmentations) throws XNIException {
        DTDGrammar activeGrammar = this.fDTDGrammar;
        if (activeGrammar == null) {
            activeGrammar = this.fGrammarBucket.getActiveGrammar();
        }
        if (activeGrammar.getEntityDeclIndex(str) == -1) {
            DTDGrammar dTDGrammar = this.fDTDGrammar;
            if (dTDGrammar != null) {
                dTDGrammar.internalEntityDecl(str, xMLString, xMLString2, augmentations);
            }
            XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
            if (xMLDTDHandler != null) {
                xMLDTDHandler.internalEntityDecl(str, xMLString, xMLString2, augmentations);
            }
        }
    }

    public boolean isValidName(String str) {
        return XMLChar.isValidName(str);
    }

    public boolean isValidNmtoken(String str) {
        return XMLChar.isValidNmtoken(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void notationDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        if (this.fValidation) {
            DTDGrammar activeGrammar = this.fDTDGrammar;
            if (activeGrammar == null) {
                activeGrammar = this.fGrammarBucket.getActiveGrammar();
            }
            if (activeGrammar.getNotationDeclIndex(str) != -1) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "UniqueNotationName", new Object[]{str}, (short) 1);
            }
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.notationDecl(str, xMLResourceIdentifier, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.notationDecl(str, xMLResourceIdentifier, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void occurrence(short s, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.occurrence(s, augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.occurrence(s, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void pcdata(Augmentations augmentations) {
        this.fMixed = true;
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.pcdata(augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.pcdata(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void processingInstruction(String str, XMLString xMLString, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.processingInstruction(str, xMLString, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.processingInstruction(str, xMLString, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void reset(XMLComponentManager xMLComponentManager) throws XMLConfigurationException {
        if (!xMLComponentManager.getFeature(PARSER_SETTINGS, true)) {
            reset();
            return;
        }
        this.fValidation = xMLComponentManager.getFeature(VALIDATION, false);
        this.fDTDValidation = !xMLComponentManager.getFeature("http://apache.org/xml/features/validation/schema", false);
        this.fWarnDuplicateAttdef = xMLComponentManager.getFeature(WARN_ON_DUPLICATE_ATTDEF, false);
        this.fWarnOnUndeclaredElemdef = xMLComponentManager.getFeature(WARN_ON_UNDECLARED_ELEMDEF, false);
        this.fErrorReporter = (XMLErrorReporter) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fSymbolTable = (SymbolTable) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fGrammarPool = (XMLGrammarPool) xMLComponentManager.getProperty("http://apache.org/xml/properties/internal/grammar-pool", null);
        try {
            this.fValidator = (XMLDTDValidator) xMLComponentManager.getProperty(DTD_VALIDATOR, null);
        } catch (ClassCastException unused) {
            this.fValidator = null;
        }
        XMLDTDValidator xMLDTDValidator = this.fValidator;
        if (xMLDTDValidator != null) {
            this.fGrammarBucket = xMLDTDValidator.getGrammarBucket();
        } else {
            this.fGrammarBucket = null;
        }
        reset();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void separator(short s, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.separator(s, augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.separator(s, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDContentModelSource
    public void setDTDContentModelHandler(XMLDTDContentModelHandler xMLDTDContentModelHandler) {
        this.fDTDContentModelHandler = xMLDTDContentModelHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void setDTDContentModelSource(XMLDTDContentModelSource xMLDTDContentModelSource) {
        this.fDTDContentModelSource = xMLDTDContentModelSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLDTDSource
    public void setDTDHandler(XMLDTDHandler xMLDTDHandler) {
        this.fDTDHandler = xMLDTDHandler;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void setDTDSource(XMLDTDSource xMLDTDSource) {
        this.fDTDSource = xMLDTDSource;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setFeature(String str, boolean z) throws XMLConfigurationException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.parser.XMLComponent
    public void setProperty(String str, Object obj) throws XMLConfigurationException {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startAttlist(String str, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startAttlist(str, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startAttlist(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startConditional(short s, Augmentations augmentations) throws XNIException {
        this.fInDTDIgnore = s == 1;
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startConditional(s, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startConditional(s, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void startContentModel(String str, Augmentations augmentations) throws XNIException {
        if (this.fValidation) {
            this.fDTDElementDeclName = str;
            this.fMixedElementTypes.clear();
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startContentModel(str, augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.startContentModel(str, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startDTD(XMLLocator xMLLocator, Augmentations augmentations) throws XNIException {
        this.fNDataDeclNotations.clear();
        this.fDTDElementDecls.clear();
        if (!this.fGrammarBucket.getActiveGrammar().isImmutable()) {
            this.fDTDGrammar = this.fGrammarBucket.getActiveGrammar();
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startDTD(xMLLocator, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startDTD(xMLLocator, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startExternalSubset(XMLResourceIdentifier xMLResourceIdentifier, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startExternalSubset(xMLResourceIdentifier, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startExternalSubset(xMLResourceIdentifier, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDContentModelHandler
    public void startGroup(Augmentations augmentations) throws XNIException {
        this.fMixed = false;
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startGroup(augmentations);
        }
        XMLDTDContentModelHandler xMLDTDContentModelHandler = this.fDTDContentModelHandler;
        if (xMLDTDContentModelHandler != null) {
            xMLDTDContentModelHandler.startGroup(augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void startParameterEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        if (this.fPerformValidation && this.fDTDGrammar != null && this.fGrammarBucket.getStandalone()) {
            checkStandaloneEntityRef(str, this.fDTDGrammar, this.fEntityDecl, this.fErrorReporter);
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.startParameterEntity(str, xMLResourceIdentifier, str2, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.startParameterEntity(str, xMLResourceIdentifier, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void textDecl(String str, String str2, Augmentations augmentations) throws XNIException {
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.textDecl(str, str2, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.textDecl(str, str2, augmentations);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLDTDHandler
    public void unparsedEntityDecl(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, Augmentations augmentations) throws XNIException {
        if (this.fValidation) {
            this.fNDataDeclNotations.put(str, str2);
        }
        DTDGrammar dTDGrammar = this.fDTDGrammar;
        if (dTDGrammar != null) {
            dTDGrammar.unparsedEntityDecl(str, xMLResourceIdentifier, str2, augmentations);
        }
        XMLDTDHandler xMLDTDHandler = this.fDTDHandler;
        if (xMLDTDHandler != null) {
            xMLDTDHandler.unparsedEntityDecl(str, xMLResourceIdentifier, str2, augmentations);
        }
    }

    private void checkDeclaredElements(DTDGrammar dTDGrammar) {
        int firstElementDeclIndex = dTDGrammar.getFirstElementDeclIndex();
        XMLContentSpec xMLContentSpec = new XMLContentSpec();
        while (firstElementDeclIndex >= 0) {
            short contentSpecType = dTDGrammar.getContentSpecType(firstElementDeclIndex);
            if (contentSpecType == 3 || contentSpecType == 2) {
                checkDeclaredElements(dTDGrammar, firstElementDeclIndex, dTDGrammar.getContentSpecIndex(firstElementDeclIndex), xMLContentSpec);
            }
            firstElementDeclIndex = dTDGrammar.getNextElementDeclIndex(firstElementDeclIndex);
        }
    }

    public void reset() {
        this.fDTDGrammar = null;
        this.fInDTDIgnore = false;
        this.fNDataDeclNotations.clear();
        if (this.fValidation) {
            if (this.fNotationEnumVals == null) {
                this.fNotationEnumVals = new HashMap();
            }
            this.fNotationEnumVals.clear();
            this.fTableOfIDAttributeNames = new HashMap();
            this.fTableOfNOTATIONAttributeNames = new HashMap();
        }
    }
}
