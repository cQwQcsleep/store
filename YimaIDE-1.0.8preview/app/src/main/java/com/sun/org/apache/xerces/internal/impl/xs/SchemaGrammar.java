package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import com.sun.org.apache.xerces.internal.impl.xs.util.ObjectListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.SimpleLocator;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMap4Types;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import com.sun.org.apache.xerces.internal.parsers.XML11Configuration;
import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XSGrammar;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSElementDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSIDCDefinition;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import com.sun.org.apache.xerces.internal.xs.XSModelGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSNamedMap;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSNotationDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SchemaGrammar implements XSGrammar, XSNamespaceItem {
    private static final int BASICSET_COUNT = 29;
    private static final int FULLSET_COUNT = 46;
    private static final boolean[] GLOBAL_COMP;
    private static final int GRAMMAR_XS = 1;
    private static final int GRAMMAR_XSI = 2;
    private static final int INC_SIZE = 16;
    private static final int INITIAL_SIZE = 16;
    private static final short MAX_COMP_IDX = 16;
    private static final int REDEFINED_GROUP_INIT_SIZE = 2;
    public static final BuiltinSchemaGrammar SG_SchemaNS;
    private static final BuiltinSchemaGrammar SG_SchemaNSExtended;
    public static final BuiltinSchemaGrammar SG_XSI;
    public static final XSSimpleType fAnySimpleType;
    public static final XSComplexTypeDecl fAnyType = new XSAnyType();
    SymbolHash fAllGlobalElemDecls;
    XSAnnotationImpl[] fAnnotations;
    private int fCTCount;
    private SimpleLocator[] fCTLocators;
    private XSComplexTypeDecl[] fComplexTypeDecls;
    private XSNamedMap[] fComponents;
    private ObjectList[] fComponentsExt;
    private SoftReference<DOMParser> fDOMParser;
    private List<Object> fDocuments;
    boolean fFullChecked;
    SymbolHash fGlobalAttrDecls;
    SymbolHash fGlobalAttrDeclsExt;
    SymbolHash fGlobalAttrGrpDecls;
    SymbolHash fGlobalAttrGrpDeclsExt;
    SymbolHash fGlobalElemDecls;
    SymbolHash fGlobalElemDeclsExt;
    SymbolHash fGlobalGroupDecls;
    SymbolHash fGlobalGroupDeclsExt;
    SymbolHash fGlobalIDConstraintDecls;
    SymbolHash fGlobalIDConstraintDeclsExt;
    SymbolHash fGlobalNotationDecls;
    SymbolHash fGlobalNotationDeclsExt;
    SymbolHash fGlobalTypeDecls;
    SymbolHash fGlobalTypeDeclsExt;
    XSDDescription fGrammarDescription;
    List<SchemaGrammar> fImported;
    private boolean fIsImmutable;
    private List<String> fLocations;
    int fNumAnnotations;
    private int fRGCount;
    private SimpleLocator[] fRGLocators;
    private XSGroupDecl[] fRedefinedGroupDecls;
    private SoftReference<SAXParser> fSAXParser;
    private int fSubGroupCount;
    private XSElementDecl[] fSubGroups;
    private SymbolTable fSymbolTable;
    String fTargetNamespace;

    public static class BuiltinAttrDecl extends XSAttributeDecl {
        public BuiltinAttrDecl(String str, String str2, XSSimpleType xSSimpleType, short s) {
            this.fName = str;
            this.fTargetNamespace = str2;
            this.fType = xSSimpleType;
            this.fScope = s;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl, com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
        public XSAnnotation getAnnotation() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl, com.sun.org.apache.xerces.internal.xs.XSObject
        public XSNamespaceItem getNamespaceItem() {
            return SchemaGrammar.SG_XSI;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl
        public void reset() {
        }

        public void setValues(String str, String str2, XSSimpleType xSSimpleType, short s, short s2, ValidatedInfo validatedInfo, XSComplexTypeDecl xSComplexTypeDecl) {
        }
    }

    public static class BuiltinSchemaGrammar extends SchemaGrammar {
        private static final String EXTENDED_SCHEMA_FACTORY_CLASS = "com.sun.org.apache.xerces.internal.impl.dv.xs.ExtendedSchemaDVFactoryImpl";

        public BuiltinSchemaGrammar(int i, short s) {
            SchemaDVFactory schemaDVFactory = s == 1 ? SchemaDVFactory.getInstance() : SchemaDVFactory.getInstance(EXTENDED_SCHEMA_FACTORY_CLASS);
            if (i == 1) {
                String str = SchemaSymbols.URI_SCHEMAFORSCHEMA;
                this.fTargetNamespace = str;
                XSDDescription xSDDescription = new XSDDescription();
                this.fGrammarDescription = xSDDescription;
                xSDDescription.fContextType = (short) 3;
                xSDDescription.setNamespace(str);
                this.fGlobalAttrDecls = new SymbolHash(1);
                this.fGlobalAttrGrpDecls = new SymbolHash(1);
                this.fGlobalElemDecls = new SymbolHash(1);
                this.fGlobalGroupDecls = new SymbolHash(1);
                this.fGlobalNotationDecls = new SymbolHash(1);
                this.fGlobalIDConstraintDecls = new SymbolHash(1);
                this.fGlobalAttrDeclsExt = new SymbolHash(1);
                this.fGlobalAttrGrpDeclsExt = new SymbolHash(1);
                this.fGlobalElemDeclsExt = new SymbolHash(1);
                this.fGlobalGroupDeclsExt = new SymbolHash(1);
                this.fGlobalNotationDeclsExt = new SymbolHash(1);
                this.fGlobalIDConstraintDeclsExt = new SymbolHash(1);
                this.fGlobalTypeDeclsExt = new SymbolHash(1);
                this.fAllGlobalElemDecls = new SymbolHash(1);
                SymbolHash builtInTypes = schemaDVFactory.getBuiltInTypes();
                this.fGlobalTypeDecls = builtInTypes;
                int length = builtInTypes.getLength();
                XSTypeDefinition[] xSTypeDefinitionArr = new XSTypeDefinition[length];
                this.fGlobalTypeDecls.getValues(xSTypeDefinitionArr, 0);
                for (int i2 = 0; i2 < length; i2++) {
                    XSTypeDefinition xSTypeDefinition = xSTypeDefinitionArr[i2];
                    if (xSTypeDefinition instanceof XSSimpleTypeDecl) {
                        ((XSSimpleTypeDecl) xSTypeDefinition).setNamespaceItem(this);
                    }
                }
                SymbolHash symbolHash = this.fGlobalTypeDecls;
                XSComplexTypeDecl xSComplexTypeDecl = SchemaGrammar.fAnyType;
                symbolHash.put(xSComplexTypeDecl.getName(), xSComplexTypeDecl);
                return;
            }
            if (i == 2) {
                String str2 = SchemaSymbols.URI_XSI;
                this.fTargetNamespace = str2;
                XSDDescription xSDDescription2 = new XSDDescription();
                this.fGrammarDescription = xSDDescription2;
                xSDDescription2.fContextType = (short) 3;
                xSDDescription2.setNamespace(str2);
                this.fGlobalAttrGrpDecls = new SymbolHash(1);
                this.fGlobalElemDecls = new SymbolHash(1);
                this.fGlobalGroupDecls = new SymbolHash(1);
                this.fGlobalNotationDecls = new SymbolHash(1);
                this.fGlobalIDConstraintDecls = new SymbolHash(1);
                this.fGlobalTypeDecls = new SymbolHash(1);
                this.fGlobalAttrDeclsExt = new SymbolHash(1);
                this.fGlobalAttrGrpDeclsExt = new SymbolHash(1);
                this.fGlobalElemDeclsExt = new SymbolHash(1);
                this.fGlobalGroupDeclsExt = new SymbolHash(1);
                this.fGlobalNotationDeclsExt = new SymbolHash(1);
                this.fGlobalIDConstraintDeclsExt = new SymbolHash(1);
                this.fGlobalTypeDeclsExt = new SymbolHash(1);
                this.fAllGlobalElemDecls = new SymbolHash(1);
                this.fGlobalAttrDecls = new SymbolHash(8);
                String str3 = SchemaSymbols.XSI_TYPE;
                this.fGlobalAttrDecls.put(str3, new BuiltinAttrDecl(str3, str2, schemaDVFactory.getBuiltInType(SchemaSymbols.ATTVAL_QNAME), (short) 1));
                String str4 = SchemaSymbols.XSI_NIL;
                this.fGlobalAttrDecls.put(str4, new BuiltinAttrDecl(str4, str2, schemaDVFactory.getBuiltInType("boolean"), (short) 1));
                XSSimpleType builtInType = schemaDVFactory.getBuiltInType(SchemaSymbols.ATTVAL_ANYURI);
                String str5 = SchemaSymbols.XSI_SCHEMALOCATION;
                XSSimpleType xSSimpleTypeCreateTypeList = schemaDVFactory.createTypeList("#AnonType_schemaLocation", str2, (short) 0, builtInType, null);
                if (xSSimpleTypeCreateTypeList instanceof XSSimpleTypeDecl) {
                    ((XSSimpleTypeDecl) xSSimpleTypeCreateTypeList).setAnonymous(true);
                }
                this.fGlobalAttrDecls.put(str5, new BuiltinAttrDecl(str5, str2, xSSimpleTypeCreateTypeList, (short) 1));
                String str6 = SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION;
                this.fGlobalAttrDecls.put(str6, new BuiltinAttrDecl(str6, str2, builtInType, (short) 1));
            }
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl, SimpleLocator simpleLocator) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public synchronized void addDocument(Object obj, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeDecl(XSAttributeDecl xSAttributeDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeDecl(XSAttributeDecl xSAttributeDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalElementDecl(XSElementDecl xSElementDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalElementDecl(XSElementDecl xSElementDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalElementDeclAll(XSElementDecl xSElementDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalSimpleTypeDecl(XSSimpleType xSSimpleType) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalSimpleTypeDecl(XSSimpleType xSSimpleType, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addRedefinedGroupDecl(XSGroupDecl xSGroupDecl, XSGroupDecl xSGroupDecl2, SimpleLocator simpleLocator) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public synchronized DOMParser getDOMParser() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar, com.sun.org.apache.xerces.internal.xni.grammars.Grammar
        public XMLGrammarDescription getGrammarDescription() {
            return this.fGrammarDescription.makeClone();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public synchronized SAXParser getSAXParser() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void setImportedGrammars(List<SchemaGrammar> list) {
        }
    }

    public static final class Schema4Annotations extends SchemaGrammar {
        public static final Schema4Annotations INSTANCE = new Schema4Annotations();

        private Schema4Annotations() {
            String str = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            this.fTargetNamespace = str;
            XSDDescription xSDDescription = new XSDDescription();
            this.fGrammarDescription = xSDDescription;
            xSDDescription.fContextType = (short) 3;
            xSDDescription.setNamespace(str);
            this.fGlobalAttrDecls = new SymbolHash(1);
            this.fGlobalAttrGrpDecls = new SymbolHash(1);
            this.fGlobalElemDecls = new SymbolHash(6);
            this.fGlobalGroupDecls = new SymbolHash(1);
            this.fGlobalNotationDecls = new SymbolHash(1);
            this.fGlobalIDConstraintDecls = new SymbolHash(1);
            this.fGlobalAttrDeclsExt = new SymbolHash(1);
            this.fGlobalAttrGrpDeclsExt = new SymbolHash(1);
            this.fGlobalElemDeclsExt = new SymbolHash(6);
            this.fGlobalGroupDeclsExt = new SymbolHash(1);
            this.fGlobalNotationDeclsExt = new SymbolHash(1);
            this.fGlobalIDConstraintDeclsExt = new SymbolHash(1);
            this.fGlobalTypeDeclsExt = new SymbolHash(1);
            this.fAllGlobalElemDecls = new SymbolHash(6);
            this.fGlobalTypeDecls = SchemaGrammar.SG_SchemaNS.fGlobalTypeDecls;
            String str2 = SchemaSymbols.ELT_ANNOTATION;
            XSElementDecl xSElementDeclCreateAnnotationElementDecl = createAnnotationElementDecl(str2);
            String str3 = SchemaSymbols.ELT_DOCUMENTATION;
            XSElementDecl xSElementDeclCreateAnnotationElementDecl2 = createAnnotationElementDecl(str3);
            String str4 = SchemaSymbols.ELT_APPINFO;
            XSElementDecl xSElementDeclCreateAnnotationElementDecl3 = createAnnotationElementDecl(str4);
            this.fGlobalElemDecls.put(xSElementDeclCreateAnnotationElementDecl.fName, xSElementDeclCreateAnnotationElementDecl);
            this.fGlobalElemDecls.put(xSElementDeclCreateAnnotationElementDecl2.fName, xSElementDeclCreateAnnotationElementDecl2);
            this.fGlobalElemDecls.put(xSElementDeclCreateAnnotationElementDecl3.fName, xSElementDeclCreateAnnotationElementDecl3);
            this.fGlobalElemDeclsExt.put("," + xSElementDeclCreateAnnotationElementDecl.fName, xSElementDeclCreateAnnotationElementDecl);
            this.fGlobalElemDeclsExt.put("," + xSElementDeclCreateAnnotationElementDecl2.fName, xSElementDeclCreateAnnotationElementDecl2);
            this.fGlobalElemDeclsExt.put("," + xSElementDeclCreateAnnotationElementDecl3.fName, xSElementDeclCreateAnnotationElementDecl3);
            this.fAllGlobalElemDecls.put(xSElementDeclCreateAnnotationElementDecl, xSElementDeclCreateAnnotationElementDecl);
            this.fAllGlobalElemDecls.put(xSElementDeclCreateAnnotationElementDecl2, xSElementDeclCreateAnnotationElementDecl2);
            this.fAllGlobalElemDecls.put(xSElementDeclCreateAnnotationElementDecl3, xSElementDeclCreateAnnotationElementDecl3);
            XSComplexTypeDecl xSComplexTypeDecl = new XSComplexTypeDecl();
            XSComplexTypeDecl xSComplexTypeDecl2 = new XSComplexTypeDecl();
            XSComplexTypeDecl xSComplexTypeDecl3 = new XSComplexTypeDecl();
            xSElementDeclCreateAnnotationElementDecl.fType = xSComplexTypeDecl;
            xSElementDeclCreateAnnotationElementDecl2.fType = xSComplexTypeDecl2;
            xSElementDeclCreateAnnotationElementDecl3.fType = xSComplexTypeDecl3;
            XSAttributeGroupDecl xSAttributeGroupDecl = new XSAttributeGroupDecl();
            XSAttributeGroupDecl xSAttributeGroupDecl2 = new XSAttributeGroupDecl();
            XSAttributeGroupDecl xSAttributeGroupDecl3 = new XSAttributeGroupDecl();
            XSAttributeUseImpl xSAttributeUseImpl = new XSAttributeUseImpl();
            XSAttributeDecl xSAttributeDecl = new XSAttributeDecl();
            xSAttributeUseImpl.fAttrDecl = xSAttributeDecl;
            xSAttributeDecl.setValues(SchemaSymbols.ATT_ID, null, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_ID), (short) 0, (short) 2, null, xSComplexTypeDecl, null);
            xSAttributeUseImpl.fUse = (short) 0;
            xSAttributeUseImpl.fConstraintType = (short) 0;
            XSAttributeUseImpl xSAttributeUseImpl2 = new XSAttributeUseImpl();
            XSAttributeDecl xSAttributeDecl2 = new XSAttributeDecl();
            xSAttributeUseImpl2.fAttrDecl = xSAttributeDecl2;
            String str5 = SchemaSymbols.ATT_SOURCE;
            xSAttributeDecl2.setValues(str5, null, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_ANYURI), (short) 0, (short) 2, null, xSComplexTypeDecl2, null);
            xSAttributeUseImpl2.fUse = (short) 0;
            xSAttributeUseImpl2.fConstraintType = (short) 0;
            XSAttributeUseImpl xSAttributeUseImpl3 = new XSAttributeUseImpl();
            XSAttributeDecl xSAttributeDecl3 = new XSAttributeDecl();
            xSAttributeUseImpl3.fAttrDecl = xSAttributeDecl3;
            xSAttributeDecl3.setValues("lang".intern(), NamespaceContext.XML_URI, (XSSimpleType) this.fGlobalTypeDecls.get("language"), (short) 0, (short) 2, null, xSComplexTypeDecl2, null);
            xSAttributeUseImpl3.fUse = (short) 0;
            xSAttributeUseImpl3.fConstraintType = (short) 0;
            XSAttributeUseImpl xSAttributeUseImpl4 = new XSAttributeUseImpl();
            XSAttributeDecl xSAttributeDecl4 = new XSAttributeDecl();
            xSAttributeUseImpl4.fAttrDecl = xSAttributeDecl4;
            xSAttributeDecl4.setValues(str5, null, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_ANYURI), (short) 0, (short) 2, null, xSComplexTypeDecl3, null);
            xSAttributeUseImpl4.fUse = (short) 0;
            xSAttributeUseImpl4.fConstraintType = (short) 0;
            XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
            xSWildcardDecl.fNamespaceList = new String[]{this.fTargetNamespace, null};
            xSWildcardDecl.fType = (short) 2;
            xSWildcardDecl.fProcessContents = (short) 3;
            xSAttributeGroupDecl.addAttributeUse(xSAttributeUseImpl);
            xSAttributeGroupDecl.fAttributeWC = xSWildcardDecl;
            xSAttributeGroupDecl2.addAttributeUse(xSAttributeUseImpl2);
            xSAttributeGroupDecl2.addAttributeUse(xSAttributeUseImpl3);
            xSAttributeGroupDecl2.fAttributeWC = xSWildcardDecl;
            xSAttributeGroupDecl3.addAttributeUse(xSAttributeUseImpl4);
            xSAttributeGroupDecl3.fAttributeWC = xSWildcardDecl;
            XSParticleDecl xSParticleDeclCreateUnboundedModelGroupParticle = createUnboundedModelGroupParticle();
            XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
            xSModelGroupImpl.fCompositor = (short) 101;
            xSModelGroupImpl.fParticleCount = 2;
            XSParticleDecl[] xSParticleDeclArr = new XSParticleDecl[2];
            xSModelGroupImpl.fParticles = xSParticleDeclArr;
            xSParticleDeclArr[0] = createChoiceElementParticle(xSElementDeclCreateAnnotationElementDecl3);
            xSModelGroupImpl.fParticles[1] = createChoiceElementParticle(xSElementDeclCreateAnnotationElementDecl2);
            xSParticleDeclCreateUnboundedModelGroupParticle.fValue = xSModelGroupImpl;
            XSParticleDecl xSParticleDeclCreateUnboundedAnyWildcardSequenceParticle = createUnboundedAnyWildcardSequenceParticle();
            String str6 = this.fTargetNamespace;
            XSComplexTypeDecl xSComplexTypeDecl4 = SchemaGrammar.fAnyType;
            XSObjectListImpl xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            xSComplexTypeDecl.setValues("#AnonType_" + str2, str6, xSComplexTypeDecl4, (short) 2, (short) 0, (short) 3, (short) 2, false, xSAttributeGroupDecl, null, xSParticleDeclCreateUnboundedModelGroupParticle, xSObjectListImpl);
            xSComplexTypeDecl.setName("#AnonType_" + str2);
            xSComplexTypeDecl.setIsAnonymous();
            xSComplexTypeDecl2.setValues("#AnonType_" + str3, this.fTargetNamespace, xSComplexTypeDecl4, (short) 2, (short) 0, (short) 3, (short) 3, false, xSAttributeGroupDecl2, null, xSParticleDeclCreateUnboundedAnyWildcardSequenceParticle, xSObjectListImpl);
            xSComplexTypeDecl2.setName("#AnonType_" + str3);
            xSComplexTypeDecl2.setIsAnonymous();
            xSComplexTypeDecl3.setValues("#AnonType_" + str4, this.fTargetNamespace, xSComplexTypeDecl4, (short) 2, (short) 0, (short) 3, (short) 3, false, xSAttributeGroupDecl3, null, xSParticleDeclCreateUnboundedAnyWildcardSequenceParticle, xSObjectListImpl);
            xSComplexTypeDecl3.setName("#AnonType_" + str4);
            xSComplexTypeDecl3.setIsAnonymous();
        }

        private XSElementDecl createAnnotationElementDecl(String str) {
            XSElementDecl xSElementDecl = new XSElementDecl();
            xSElementDecl.fName = str;
            xSElementDecl.fTargetNamespace = this.fTargetNamespace;
            xSElementDecl.setIsGlobal();
            xSElementDecl.fBlock = (short) 7;
            xSElementDecl.setConstraintType((short) 0);
            return xSElementDecl;
        }

        private XSParticleDecl createAnyLaxWildcardParticle() {
            XSParticleDecl xSParticleDecl = new XSParticleDecl();
            xSParticleDecl.fMinOccurs = 1;
            xSParticleDecl.fMaxOccurs = 1;
            xSParticleDecl.fType = (short) 2;
            XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
            xSWildcardDecl.fNamespaceList = null;
            xSWildcardDecl.fType = (short) 1;
            xSWildcardDecl.fProcessContents = (short) 3;
            xSParticleDecl.fValue = xSWildcardDecl;
            return xSParticleDecl;
        }

        private XSParticleDecl createChoiceElementParticle(XSElementDecl xSElementDecl) {
            XSParticleDecl xSParticleDecl = new XSParticleDecl();
            xSParticleDecl.fMinOccurs = 1;
            xSParticleDecl.fMaxOccurs = 1;
            xSParticleDecl.fType = (short) 1;
            xSParticleDecl.fValue = xSElementDecl;
            return xSParticleDecl;
        }

        private XSParticleDecl createUnboundedAnyWildcardSequenceParticle() {
            XSParticleDecl xSParticleDeclCreateUnboundedModelGroupParticle = createUnboundedModelGroupParticle();
            XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
            xSModelGroupImpl.fCompositor = (short) 102;
            xSModelGroupImpl.fParticleCount = 1;
            xSModelGroupImpl.fParticles = new XSParticleDecl[]{createAnyLaxWildcardParticle()};
            xSParticleDeclCreateUnboundedModelGroupParticle.fValue = xSModelGroupImpl;
            return xSParticleDeclCreateUnboundedModelGroupParticle;
        }

        private XSParticleDecl createUnboundedModelGroupParticle() {
            XSParticleDecl xSParticleDecl = new XSParticleDecl();
            xSParticleDecl.fMinOccurs = 0;
            xSParticleDecl.fMaxOccurs = -1;
            xSParticleDecl.fType = (short) 3;
            return xSParticleDecl;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl, SimpleLocator simpleLocator) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public synchronized void addDocument(Object obj, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeDecl(XSAttributeDecl xSAttributeDecl) {
        }

        public void addGlobalAttributeDecl(XSAttributeGroupDecl xSAttributeGroupDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalElementDecl(XSElementDecl xSElementDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalElementDecl(XSElementDecl xSElementDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalElementDeclAll(XSElementDecl xSElementDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalSimpleTypeDecl(XSSimpleType xSSimpleType) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalSimpleTypeDecl(XSSimpleType xSSimpleType, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition, String str) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void addRedefinedGroupDecl(XSGroupDecl xSGroupDecl, XSGroupDecl xSGroupDecl2, SimpleLocator simpleLocator) {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public synchronized DOMParser getDOMParser() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar, com.sun.org.apache.xerces.internal.xni.grammars.Grammar
        public XMLGrammarDescription getGrammarDescription() {
            return this.fGrammarDescription.makeClone();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public synchronized SAXParser getSAXParser() {
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar
        public void setImportedGrammars(List<SchemaGrammar> list) {
        }
    }

    public static class XSAnyType extends XSComplexTypeDecl {
        public XSAnyType() {
            this.fName = SchemaSymbols.ATTVAL_ANYTYPE;
            this.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            this.fBaseType = this;
            this.fDerivedBy = (short) 2;
            this.fContentType = (short) 3;
            this.fParticle = createParticle();
            this.fAttrGrp = createAttrGrp();
        }

        private XSAttributeGroupDecl createAttrGrp() {
            XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
            xSWildcardDecl.fProcessContents = (short) 3;
            XSAttributeGroupDecl xSAttributeGroupDecl = new XSAttributeGroupDecl();
            xSAttributeGroupDecl.fAttributeWC = xSWildcardDecl;
            return xSAttributeGroupDecl;
        }

        private XSParticleDecl createParticle() {
            XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
            xSWildcardDecl.fProcessContents = (short) 3;
            XSParticleDecl xSParticleDecl = new XSParticleDecl();
            xSParticleDecl.fMinOccurs = 0;
            xSParticleDecl.fMaxOccurs = -1;
            xSParticleDecl.fType = (short) 2;
            xSParticleDecl.fValue = xSWildcardDecl;
            XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
            xSModelGroupImpl.fCompositor = (short) 102;
            xSModelGroupImpl.fParticleCount = 1;
            xSModelGroupImpl.fParticles = new XSParticleDecl[]{xSParticleDecl};
            XSParticleDecl xSParticleDecl2 = new XSParticleDecl();
            xSParticleDecl2.fType = (short) 3;
            xSParticleDecl2.fValue = xSModelGroupImpl;
            return xSParticleDecl2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl, com.sun.org.apache.xerces.internal.xs.XSComplexTypeDefinition
        public XSObjectList getAnnotations() {
            return XSObjectListImpl.EMPTY_LIST;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl, com.sun.org.apache.xerces.internal.xs.XSObject
        public XSNamespaceItem getNamespaceItem() {
            return SchemaGrammar.SG_SchemaNS;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl
        public void reset() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl
        public void setContainsTypeID() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl
        public void setIsAbstractType() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl
        public void setIsAnonymous() {
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl
        public void setName(String str) {
        }

        public void setValues(String str, String str2, XSTypeDefinition xSTypeDefinition, short s, short s2, short s3, short s4, boolean z, XSAttributeGroupDecl xSAttributeGroupDecl, XSSimpleType xSSimpleType, XSParticleDecl xSParticleDecl) {
        }
    }

    static {
        BuiltinSchemaGrammar builtinSchemaGrammar = new BuiltinSchemaGrammar(1, (short) 1);
        SG_SchemaNS = builtinSchemaGrammar;
        SG_SchemaNSExtended = new BuiltinSchemaGrammar(1, (short) 2);
        fAnySimpleType = (XSSimpleType) builtinSchemaGrammar.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ANYSIMPLETYPE);
        SG_XSI = new BuiltinSchemaGrammar(2, (short) 1);
        GLOBAL_COMP = new boolean[]{false, true, true, true, false, true, true, false, false, false, true, true, false, false, false, true, true};
    }

    public SchemaGrammar(SchemaGrammar schemaGrammar) {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
        this.fIsImmutable = false;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fComponentsExt = null;
        this.fDocuments = null;
        this.fLocations = null;
        this.fTargetNamespace = schemaGrammar.fTargetNamespace;
        this.fGrammarDescription = schemaGrammar.fGrammarDescription.makeClone();
        this.fSymbolTable = schemaGrammar.fSymbolTable;
        this.fGlobalAttrDecls = schemaGrammar.fGlobalAttrDecls.makeClone();
        this.fGlobalAttrGrpDecls = schemaGrammar.fGlobalAttrGrpDecls.makeClone();
        this.fGlobalElemDecls = schemaGrammar.fGlobalElemDecls.makeClone();
        this.fGlobalGroupDecls = schemaGrammar.fGlobalGroupDecls.makeClone();
        this.fGlobalNotationDecls = schemaGrammar.fGlobalNotationDecls.makeClone();
        this.fGlobalIDConstraintDecls = schemaGrammar.fGlobalIDConstraintDecls.makeClone();
        this.fGlobalTypeDecls = schemaGrammar.fGlobalTypeDecls.makeClone();
        this.fGlobalAttrDeclsExt = schemaGrammar.fGlobalAttrDeclsExt.makeClone();
        this.fGlobalAttrGrpDeclsExt = schemaGrammar.fGlobalAttrGrpDeclsExt.makeClone();
        this.fGlobalElemDeclsExt = schemaGrammar.fGlobalElemDeclsExt.makeClone();
        this.fGlobalGroupDeclsExt = schemaGrammar.fGlobalGroupDeclsExt.makeClone();
        this.fGlobalNotationDeclsExt = schemaGrammar.fGlobalNotationDeclsExt.makeClone();
        this.fGlobalIDConstraintDeclsExt = schemaGrammar.fGlobalIDConstraintDeclsExt.makeClone();
        this.fGlobalTypeDeclsExt = schemaGrammar.fGlobalTypeDeclsExt.makeClone();
        this.fAllGlobalElemDecls = schemaGrammar.fAllGlobalElemDecls.makeClone();
        int i = schemaGrammar.fNumAnnotations;
        this.fNumAnnotations = i;
        if (i > 0) {
            XSAnnotationImpl[] xSAnnotationImplArr = new XSAnnotationImpl[schemaGrammar.fAnnotations.length];
            this.fAnnotations = xSAnnotationImplArr;
            System.arraycopy(schemaGrammar.fAnnotations, 0, xSAnnotationImplArr, 0, i);
        }
        int i2 = schemaGrammar.fSubGroupCount;
        this.fSubGroupCount = i2;
        if (i2 > 0) {
            XSElementDecl[] xSElementDeclArr = new XSElementDecl[schemaGrammar.fSubGroups.length];
            this.fSubGroups = xSElementDeclArr;
            System.arraycopy(schemaGrammar.fSubGroups, 0, xSElementDeclArr, 0, i2);
        }
        int i3 = schemaGrammar.fCTCount;
        this.fCTCount = i3;
        if (i3 > 0) {
            XSComplexTypeDecl[] xSComplexTypeDeclArr = new XSComplexTypeDecl[schemaGrammar.fComplexTypeDecls.length];
            this.fComplexTypeDecls = xSComplexTypeDeclArr;
            this.fCTLocators = new SimpleLocator[schemaGrammar.fCTLocators.length];
            System.arraycopy(schemaGrammar.fComplexTypeDecls, 0, xSComplexTypeDeclArr, 0, i3);
            System.arraycopy(schemaGrammar.fCTLocators, 0, this.fCTLocators, 0, this.fCTCount);
        }
        int i4 = schemaGrammar.fRGCount;
        this.fRGCount = i4;
        if (i4 > 0) {
            XSGroupDecl[] xSGroupDeclArr = new XSGroupDecl[schemaGrammar.fRedefinedGroupDecls.length];
            this.fRedefinedGroupDecls = xSGroupDeclArr;
            this.fRGLocators = new SimpleLocator[schemaGrammar.fRGLocators.length];
            System.arraycopy(schemaGrammar.fRedefinedGroupDecls, 0, xSGroupDeclArr, 0, i4);
            System.arraycopy(schemaGrammar.fRGLocators, 0, this.fRGLocators, 0, this.fRGCount / 2);
        }
        if (schemaGrammar.fImported != null) {
            this.fImported = new ArrayList();
            for (int i5 = 0; i5 < schemaGrammar.fImported.size(); i5++) {
                this.fImported.add(schemaGrammar.fImported.get(i5));
            }
        }
        if (schemaGrammar.fLocations != null) {
            for (int i6 = 0; i6 < schemaGrammar.fLocations.size(); i6++) {
                addDocument(null, schemaGrammar.fLocations.get(i6));
            }
        }
    }

    public static SchemaGrammar getS4SGrammar(short s) {
        return s == 1 ? SG_SchemaNS : SG_SchemaNSExtended;
    }

    public static final XSComplexTypeDecl[] resize(XSComplexTypeDecl[] xSComplexTypeDeclArr, int i) {
        XSComplexTypeDecl[] xSComplexTypeDeclArr2 = new XSComplexTypeDecl[i];
        System.arraycopy(xSComplexTypeDeclArr, 0, xSComplexTypeDeclArr2, 0, Math.min(xSComplexTypeDeclArr.length, i));
        return xSComplexTypeDeclArr2;
    }

    public void addAnnotation(XSAnnotationImpl xSAnnotationImpl) {
        if (xSAnnotationImpl == null) {
            return;
        }
        XSAnnotationImpl[] xSAnnotationImplArr = this.fAnnotations;
        if (xSAnnotationImplArr == null) {
            this.fAnnotations = new XSAnnotationImpl[2];
        } else {
            int i = this.fNumAnnotations;
            if (i == xSAnnotationImplArr.length) {
                XSAnnotationImpl[] xSAnnotationImplArr2 = new XSAnnotationImpl[i << 1];
                System.arraycopy(xSAnnotationImplArr, 0, xSAnnotationImplArr2, 0, i);
                this.fAnnotations = xSAnnotationImplArr2;
            }
        }
        XSAnnotationImpl[] xSAnnotationImplArr3 = this.fAnnotations;
        int i2 = this.fNumAnnotations;
        this.fNumAnnotations = i2 + 1;
        xSAnnotationImplArr3[i2] = xSAnnotationImpl;
    }

    public void addComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl, SimpleLocator simpleLocator) {
        int i = this.fCTCount;
        XSComplexTypeDecl[] xSComplexTypeDeclArr = this.fComplexTypeDecls;
        if (i == xSComplexTypeDeclArr.length) {
            this.fComplexTypeDecls = resize(xSComplexTypeDeclArr, i + 16);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount + 16);
        }
        SimpleLocator[] simpleLocatorArr = this.fCTLocators;
        int i2 = this.fCTCount;
        simpleLocatorArr[i2] = simpleLocator;
        XSComplexTypeDecl[] xSComplexTypeDeclArr2 = this.fComplexTypeDecls;
        this.fCTCount = i2 + 1;
        xSComplexTypeDeclArr2[i2] = xSComplexTypeDecl;
    }

    public synchronized void addDocument(Object obj, String str) {
        try {
            if (this.fDocuments == null) {
                this.fDocuments = new CopyOnWriteArrayList();
                this.fLocations = new CopyOnWriteArrayList();
            }
            this.fDocuments.add(obj);
            this.fLocations.add(str);
        } catch (Throwable th) {
            throw th;
        }
    }

    public void addGlobalAttributeDecl(XSAttributeDecl xSAttributeDecl, String str) {
        SymbolHash symbolHash = this.fGlobalAttrDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSAttributeDecl.fName);
        symbolHash.put(sb.toString(), xSAttributeDecl);
        if (xSAttributeDecl.getNamespaceItem() == null) {
            xSAttributeDecl.setNamespaceItem(this);
        }
    }

    public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl, String str) {
        SymbolHash symbolHash = this.fGlobalAttrGrpDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSAttributeGroupDecl.fName);
        symbolHash.put(sb.toString(), xSAttributeGroupDecl);
        if (xSAttributeGroupDecl.getNamespaceItem() == null) {
            xSAttributeGroupDecl.setNamespaceItem(this);
        }
    }

    public void addGlobalComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl, String str) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSComplexTypeDecl.getName());
        symbolHash.put(sb.toString(), xSComplexTypeDecl);
        if (xSComplexTypeDecl.getNamespaceItem() == null) {
            xSComplexTypeDecl.setNamespaceItem(this);
        }
    }

    public void addGlobalElementDecl(XSElementDecl xSElementDecl, String str) {
        SymbolHash symbolHash = this.fGlobalElemDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSElementDecl.fName);
        symbolHash.put(sb.toString(), xSElementDecl);
        if (xSElementDecl.getNamespaceItem() == null) {
            xSElementDecl.setNamespaceItem(this);
        }
    }

    public void addGlobalElementDeclAll(XSElementDecl xSElementDecl) {
        if (this.fAllGlobalElemDecls.get(xSElementDecl) == null) {
            this.fAllGlobalElemDecls.put(xSElementDecl, xSElementDecl);
            if (xSElementDecl.fSubGroup != null) {
                int i = this.fSubGroupCount;
                XSElementDecl[] xSElementDeclArr = this.fSubGroups;
                if (i == xSElementDeclArr.length) {
                    this.fSubGroups = resize(xSElementDeclArr, i + 16);
                }
                XSElementDecl[] xSElementDeclArr2 = this.fSubGroups;
                int i2 = this.fSubGroupCount;
                this.fSubGroupCount = i2 + 1;
                xSElementDeclArr2[i2] = xSElementDecl;
            }
        }
    }

    public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl, String str) {
        SymbolHash symbolHash = this.fGlobalGroupDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSGroupDecl.fName);
        symbolHash.put(sb.toString(), xSGroupDecl);
        if (xSGroupDecl.getNamespaceItem() == null) {
            xSGroupDecl.setNamespaceItem(this);
        }
    }

    public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl, String str) {
        SymbolHash symbolHash = this.fGlobalNotationDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSNotationDecl.fName);
        symbolHash.put(sb.toString(), xSNotationDecl);
        if (xSNotationDecl.getNamespaceItem() == null) {
            xSNotationDecl.setNamespaceItem(this);
        }
    }

    public void addGlobalSimpleTypeDecl(XSSimpleType xSSimpleType, String str) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSSimpleType.getName());
        symbolHash.put(sb.toString(), xSSimpleType);
        if (xSSimpleType.getNamespaceItem() == null && (xSSimpleType instanceof XSSimpleTypeDecl)) {
            ((XSSimpleTypeDecl) xSSimpleType).setNamespaceItem(this);
        }
    }

    public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition, String str) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(xSTypeDefinition.getName());
        symbolHash.put(sb.toString(), xSTypeDefinition);
        if (xSTypeDefinition.getNamespaceItem() == null) {
            if (xSTypeDefinition instanceof XSComplexTypeDecl) {
                ((XSComplexTypeDecl) xSTypeDefinition).setNamespaceItem(this);
            } else if (xSTypeDefinition instanceof XSSimpleTypeDecl) {
                ((XSSimpleTypeDecl) xSTypeDefinition).setNamespaceItem(this);
            }
        }
    }

    public final void addIDConstraintDecl(XSElementDecl xSElementDecl, IdentityConstraint identityConstraint, String str) {
        SymbolHash symbolHash = this.fGlobalIDConstraintDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(",");
        sb.append(identityConstraint.getIdentityConstraintName());
        symbolHash.put(sb.toString(), identityConstraint);
    }

    public void addRedefinedGroupDecl(XSGroupDecl xSGroupDecl, XSGroupDecl xSGroupDecl2, SimpleLocator simpleLocator) {
        int i = this.fRGCount;
        XSGroupDecl[] xSGroupDeclArr = this.fRedefinedGroupDecls;
        if (i == xSGroupDeclArr.length) {
            this.fRedefinedGroupDecls = resize(xSGroupDeclArr, i << 1);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount);
        }
        SimpleLocator[] simpleLocatorArr = this.fRGLocators;
        int i2 = this.fRGCount;
        simpleLocatorArr[i2 / 2] = simpleLocator;
        XSGroupDecl[] xSGroupDeclArr2 = this.fRedefinedGroupDecls;
        int i3 = i2 + 1;
        this.fRGCount = i3;
        xSGroupDeclArr2[i2] = xSGroupDecl;
        this.fRGCount = i2 + 2;
        xSGroupDeclArr2[i3] = xSGroupDecl2;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSObjectList getAnnotations() {
        return this.fNumAnnotations == 0 ? XSObjectListImpl.EMPTY_LIST : new XSObjectListImpl(this.fAnnotations, this.fNumAnnotations);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSAttributeDeclaration getAttributeDeclaration(String str) {
        return getGlobalAttributeDecl(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSAttributeGroupDefinition getAttributeGroup(String str) {
        return getGlobalAttributeGroupDecl(str);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x004d A[Catch: all -> 0x001a, TryCatch #0 {all -> 0x001a, blocks: (B:6:0x0007, B:9:0x000f, B:11:0x0013, B:14:0x001c, B:43:0x005a, B:44:0x0064, B:33:0x0041, B:34:0x0044, B:35:0x0047, B:36:0x004a, B:37:0x004d, B:38:0x0050, B:39:0x0053, B:45:0x006d, B:48:0x0073), top: B:53:0x0007 }] */
    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public synchronized XSNamedMap getComponents(short s) {
        SymbolHash symbolHash;
        if (s > 0 && s <= 16) {
            try {
                if (GLOBAL_COMP[s]) {
                    if (this.fComponents == null) {
                        this.fComponents = new XSNamedMap[17];
                    }
                    XSNamedMap[] xSNamedMapArr = this.fComponents;
                    if (xSNamedMapArr[s] == null) {
                        if (s == 1) {
                            symbolHash = this.fGlobalAttrDecls;
                        } else if (s == 2) {
                            symbolHash = this.fGlobalElemDecls;
                        } else if (s == 3) {
                            symbolHash = this.fGlobalTypeDecls;
                        } else if (s == 5) {
                            symbolHash = this.fGlobalAttrGrpDecls;
                        } else if (s == 6) {
                            symbolHash = this.fGlobalGroupDecls;
                        } else if (s == 10) {
                            symbolHash = this.fGlobalIDConstraintDecls;
                        } else if (s == 11) {
                            symbolHash = this.fGlobalNotationDecls;
                        } else if (s == 15 || s == 16) {
                            symbolHash = this.fGlobalTypeDecls;
                        } else {
                            symbolHash = null;
                        }
                        if (s == 15 || s == 16) {
                            xSNamedMapArr[s] = new XSNamedMap4Types(this.fTargetNamespace, symbolHash, s);
                        } else {
                            xSNamedMapArr[s] = new XSNamedMapImpl(this.fTargetNamespace, symbolHash);
                        }
                    }
                    return this.fComponents[s];
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return XSNamedMapImpl.EMPTY_MAP;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x004c A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:6:0x0007, B:9:0x000e, B:11:0x0012, B:14:0x001b, B:41:0x0054, B:34:0x0040, B:35:0x0043, B:36:0x0046, B:37:0x0049, B:38:0x004c, B:39:0x004f, B:40:0x0052, B:42:0x0062, B:45:0x0068), top: B:50:0x0007 }] */
    public synchronized ObjectList getComponentsExt(short s) {
        SymbolHash symbolHash;
        if (s > 0 && s <= 16) {
            try {
                if (GLOBAL_COMP[s]) {
                    if (this.fComponentsExt == null) {
                        this.fComponentsExt = new ObjectList[17];
                    }
                    if (this.fComponentsExt[s] == null) {
                        if (s == 1) {
                            symbolHash = this.fGlobalAttrDeclsExt;
                        } else if (s == 2) {
                            symbolHash = this.fGlobalElemDeclsExt;
                        } else if (s == 3) {
                            symbolHash = this.fGlobalTypeDeclsExt;
                        } else if (s == 5) {
                            symbolHash = this.fGlobalAttrGrpDeclsExt;
                        } else if (s == 6) {
                            symbolHash = this.fGlobalGroupDeclsExt;
                        } else if (s == 10) {
                            symbolHash = this.fGlobalIDConstraintDeclsExt;
                        } else if (s == 11) {
                            symbolHash = this.fGlobalNotationDeclsExt;
                        } else if (s == 15 || s == 16) {
                            symbolHash = this.fGlobalTypeDeclsExt;
                        } else {
                            symbolHash = null;
                        }
                        Object[] entries = symbolHash.getEntries();
                        this.fComponentsExt[s] = new ObjectListImpl(entries, entries.length);
                    }
                    return this.fComponentsExt[s];
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return ObjectListImpl.EMPTY_LIST;
    }

    public synchronized DOMParser getDOMParser() {
        DOMParser dOMParser;
        SoftReference<DOMParser> softReference = this.fDOMParser;
        if (softReference != null && (dOMParser = softReference.get()) != null) {
            return dOMParser;
        }
        XML11Configuration xML11Configuration = new XML11Configuration(this.fSymbolTable);
        xML11Configuration.setFeature("http://xml.org/sax/features/namespaces", true);
        xML11Configuration.setFeature("http://xml.org/sax/features/validation", false);
        DOMParser dOMParser2 = new DOMParser(xML11Configuration);
        try {
            dOMParser2.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false);
        } catch (SAXException unused) {
        }
        this.fDOMParser = new SoftReference<>(dOMParser2);
        return dOMParser2;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public StringList getDocumentLocations() {
        return new StringListImpl(this.fLocations);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSElementDeclaration getElementDeclaration(String str) {
        return getGlobalElementDecl(str);
    }

    public final XSAttributeDecl getGlobalAttributeDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalAttrDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (XSAttributeDecl) symbolHash.get(sb.toString());
    }

    public final XSAttributeGroupDecl getGlobalAttributeGroupDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalAttrGrpDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (XSAttributeGroupDecl) symbolHash.get(sb.toString());
    }

    public final XSElementDecl getGlobalElementDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalElemDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (XSElementDecl) symbolHash.get(sb.toString());
    }

    public final XSGroupDecl getGlobalGroupDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalGroupDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (XSGroupDecl) symbolHash.get(sb.toString());
    }

    public final XSNotationDecl getGlobalNotationDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalNotationDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (XSNotationDecl) symbolHash.get(sb.toString());
    }

    public final XSTypeDefinition getGlobalTypeDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (XSTypeDefinition) symbolHash.get(sb.toString());
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.Grammar
    public XMLGrammarDescription getGrammarDescription() {
        return this.fGrammarDescription;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSIDCDefinition getIDCDefinition(String str) {
        return getIDConstraintDecl(str);
    }

    public final IdentityConstraint getIDConstraintDecl(String str, String str2) {
        SymbolHash symbolHash = this.fGlobalIDConstraintDeclsExt;
        StringBuilder sb = new StringBuilder();
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append(",");
        sb.append(str);
        return (IdentityConstraint) symbolHash.get(sb.toString());
    }

    public List<SchemaGrammar> getImportedGrammars() {
        return this.fImported;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSModelGroupDefinition getModelGroupDefinition(String str) {
        return getGlobalGroupDecl(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSNotationDeclaration getNotationDeclaration(String str) {
        return getGlobalNotationDecl(str);
    }

    public final SimpleLocator[] getRGLocators() {
        int i = this.fRGCount;
        XSGroupDecl[] xSGroupDeclArr = this.fRedefinedGroupDecls;
        if (i < xSGroupDeclArr.length) {
            this.fRedefinedGroupDecls = resize(xSGroupDeclArr, i);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount / 2);
        }
        return this.fRGLocators;
    }

    public final XSGroupDecl[] getRedefinedGroupDecls() {
        int i = this.fRGCount;
        XSGroupDecl[] xSGroupDeclArr = this.fRedefinedGroupDecls;
        if (i < xSGroupDeclArr.length) {
            this.fRedefinedGroupDecls = resize(xSGroupDeclArr, i);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount / 2);
        }
        return this.fRedefinedGroupDecls;
    }

    public synchronized SAXParser getSAXParser() {
        SAXParser sAXParser;
        SoftReference<SAXParser> softReference = this.fSAXParser;
        if (softReference != null && (sAXParser = softReference.get()) != null) {
            return sAXParser;
        }
        XML11Configuration xML11Configuration = new XML11Configuration(this.fSymbolTable);
        xML11Configuration.setFeature("http://xml.org/sax/features/namespaces", true);
        xML11Configuration.setFeature("http://xml.org/sax/features/validation", false);
        SAXParser sAXParser2 = new SAXParser(xML11Configuration);
        this.fSAXParser = new SoftReference<>(sAXParser2);
        return sAXParser2;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public String getSchemaNamespace() {
        return this.fTargetNamespace;
    }

    public final XSElementDecl[] getSubstitutionGroups() {
        int i = this.fSubGroupCount;
        XSElementDecl[] xSElementDeclArr = this.fSubGroups;
        if (i < xSElementDeclArr.length) {
            this.fSubGroups = resize(xSElementDeclArr, i);
        }
        return this.fSubGroups;
    }

    public final String getTargetNamespace() {
        return this.fTargetNamespace;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSNamespaceItem
    public XSTypeDefinition getTypeDefinition(String str) {
        return getGlobalTypeDecl(str);
    }

    public final SimpleLocator[] getUncheckedCTLocators() {
        int i = this.fCTCount;
        if (i < this.fCTLocators.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, i);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
        }
        return this.fCTLocators;
    }

    public final XSComplexTypeDecl[] getUncheckedComplexTypeDecls() {
        int i = this.fCTCount;
        XSComplexTypeDecl[] xSComplexTypeDeclArr = this.fComplexTypeDecls;
        if (i < xSComplexTypeDeclArr.length) {
            this.fComplexTypeDecls = resize(xSComplexTypeDeclArr, i);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
        }
        return this.fComplexTypeDecls;
    }

    public final boolean hasIDConstraints() {
        return this.fGlobalIDConstraintDecls.getLength() > 0;
    }

    public boolean isImmutable() {
        return this.fIsImmutable;
    }

    public boolean isNamespaceAware() {
        return true;
    }

    public synchronized void removeDocument(int i) {
        List<Object> list = this.fDocuments;
        if (list != null && i >= 0 && i < list.size()) {
            this.fDocuments.remove(i);
            this.fLocations.remove(i);
        }
    }

    public synchronized void resetComponents() {
        this.fComponents = null;
        this.fComponentsExt = null;
    }

    public void setImmutable(boolean z) {
        this.fIsImmutable = z;
    }

    public void setImportedGrammars(List<SchemaGrammar> list) {
        this.fImported = list;
    }

    public final void setUncheckedTypeNum(int i) {
        this.fCTCount = i;
        this.fComplexTypeDecls = resize(this.fComplexTypeDecls, i);
        this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XSGrammar
    public XSModel toXSModel(XSGrammar[] xSGrammarArr) {
        boolean z;
        if (xSGrammarArr == null || xSGrammarArr.length == 0) {
            return toXSModel();
        }
        int length = xSGrammarArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (xSGrammarArr[i] == this) {
                z = true;
                break;
            }
            i++;
        }
        SchemaGrammar[] schemaGrammarArr = new SchemaGrammar[z ? length : length + 1];
        for (int i2 = 0; i2 < length; i2++) {
            schemaGrammarArr[i2] = (SchemaGrammar) xSGrammarArr[i2];
        }
        if (!z) {
            schemaGrammarArr[length] = this;
        }
        return new XSModelImpl(schemaGrammarArr);
    }

    public static final XSGroupDecl[] resize(XSGroupDecl[] xSGroupDeclArr, int i) {
        XSGroupDecl[] xSGroupDeclArr2 = new XSGroupDecl[i];
        System.arraycopy(xSGroupDeclArr, 0, xSGroupDeclArr2, 0, Math.min(xSGroupDeclArr.length, i));
        return xSGroupDeclArr2;
    }

    public static final XSElementDecl[] resize(XSElementDecl[] xSElementDeclArr, int i) {
        XSElementDecl[] xSElementDeclArr2 = new XSElementDecl[i];
        System.arraycopy(xSElementDeclArr, 0, xSElementDeclArr2, 0, Math.min(xSElementDeclArr.length, i));
        return xSElementDeclArr2;
    }

    public static final SimpleLocator[] resize(SimpleLocator[] simpleLocatorArr, int i) {
        SimpleLocator[] simpleLocatorArr2 = new SimpleLocator[i];
        System.arraycopy(simpleLocatorArr, 0, simpleLocatorArr2, 0, Math.min(simpleLocatorArr.length, i));
        return simpleLocatorArr2;
    }

    public final XSAttributeDecl getGlobalAttributeDecl(String str) {
        return (XSAttributeDecl) this.fGlobalAttrDecls.get(str);
    }

    public final XSAttributeGroupDecl getGlobalAttributeGroupDecl(String str) {
        return (XSAttributeGroupDecl) this.fGlobalAttrGrpDecls.get(str);
    }

    public final XSElementDecl getGlobalElementDecl(String str) {
        return (XSElementDecl) this.fGlobalElemDecls.get(str);
    }

    public final XSGroupDecl getGlobalGroupDecl(String str) {
        return (XSGroupDecl) this.fGlobalGroupDecls.get(str);
    }

    public final XSNotationDecl getGlobalNotationDecl(String str) {
        return (XSNotationDecl) this.fGlobalNotationDecls.get(str);
    }

    public final XSTypeDefinition getGlobalTypeDecl(String str) {
        return (XSTypeDefinition) this.fGlobalTypeDecls.get(str);
    }

    public final IdentityConstraint getIDConstraintDecl(String str) {
        return (IdentityConstraint) this.fGlobalIDConstraintDecls.get(str);
    }

    public final void addIDConstraintDecl(XSElementDecl xSElementDecl, IdentityConstraint identityConstraint) {
        xSElementDecl.addIDConstraint(identityConstraint);
        this.fGlobalIDConstraintDecls.put(identityConstraint.getIdentityConstraintName(), identityConstraint);
    }

    public void addGlobalAttributeDecl(XSAttributeDecl xSAttributeDecl) {
        this.fGlobalAttrDecls.put(xSAttributeDecl.fName, xSAttributeDecl);
        xSAttributeDecl.setNamespaceItem(this);
    }

    public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl xSAttributeGroupDecl) {
        this.fGlobalAttrGrpDecls.put(xSAttributeGroupDecl.fName, xSAttributeGroupDecl);
        xSAttributeGroupDecl.setNamespaceItem(this);
    }

    public void addGlobalElementDecl(XSElementDecl xSElementDecl) {
        this.fGlobalElemDecls.put(xSElementDecl.fName, xSElementDecl);
        xSElementDecl.setNamespaceItem(this);
    }

    public void addGlobalGroupDecl(XSGroupDecl xSGroupDecl) {
        this.fGlobalGroupDecls.put(xSGroupDecl.fName, xSGroupDecl);
        xSGroupDecl.setNamespaceItem(this);
    }

    public void addGlobalNotationDecl(XSNotationDecl xSNotationDecl) {
        this.fGlobalNotationDecls.put(xSNotationDecl.fName, xSNotationDecl);
        xSNotationDecl.setNamespaceItem(this);
    }

    public void addGlobalComplexTypeDecl(XSComplexTypeDecl xSComplexTypeDecl) {
        this.fGlobalTypeDecls.put(xSComplexTypeDecl.getName(), xSComplexTypeDecl);
        xSComplexTypeDecl.setNamespaceItem(this);
    }

    public void addGlobalSimpleTypeDecl(XSSimpleType xSSimpleType) {
        this.fGlobalTypeDecls.put(xSSimpleType.getName(), xSSimpleType);
        if (xSSimpleType instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) xSSimpleType).setNamespaceItem(this);
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.grammars.XSGrammar
    public XSModel toXSModel() {
        return new XSModelImpl(new SchemaGrammar[]{this});
    }

    public void addGlobalTypeDecl(XSTypeDefinition xSTypeDefinition) {
        this.fGlobalTypeDecls.put(xSTypeDefinition.getName(), xSTypeDefinition);
        if (xSTypeDefinition instanceof XSComplexTypeDecl) {
            ((XSComplexTypeDecl) xSTypeDefinition).setNamespaceItem(this);
        } else if (xSTypeDefinition instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) xSTypeDefinition).setNamespaceItem(this);
        }
    }

    public SchemaGrammar(String str, XSDDescription xSDDescription, SymbolTable symbolTable) {
        this.fAnnotations = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
        this.fIsImmutable = false;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fComponentsExt = null;
        this.fDocuments = null;
        this.fLocations = null;
        this.fTargetNamespace = str;
        this.fGrammarDescription = xSDDescription;
        this.fSymbolTable = symbolTable;
        this.fGlobalAttrDecls = new SymbolHash(12);
        this.fGlobalAttrGrpDecls = new SymbolHash(5);
        this.fGlobalElemDecls = new SymbolHash(25);
        this.fGlobalGroupDecls = new SymbolHash(5);
        this.fGlobalNotationDecls = new SymbolHash(1);
        this.fGlobalIDConstraintDecls = new SymbolHash(3);
        this.fGlobalAttrDeclsExt = new SymbolHash(12);
        this.fGlobalAttrGrpDeclsExt = new SymbolHash(5);
        this.fGlobalElemDeclsExt = new SymbolHash(25);
        this.fGlobalGroupDeclsExt = new SymbolHash(5);
        this.fGlobalNotationDeclsExt = new SymbolHash(1);
        this.fGlobalIDConstraintDeclsExt = new SymbolHash(3);
        this.fGlobalTypeDeclsExt = new SymbolHash(25);
        this.fAllGlobalElemDecls = new SymbolHash(25);
        if (this.fTargetNamespace == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            this.fGlobalTypeDecls = SG_SchemaNS.fGlobalTypeDecls.makeClone();
        } else {
            this.fGlobalTypeDecls = new SymbolHash(25);
        }
    }

    public SchemaGrammar() {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
        this.fIsImmutable = false;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fComponentsExt = null;
        this.fDocuments = null;
        this.fLocations = null;
    }
}
