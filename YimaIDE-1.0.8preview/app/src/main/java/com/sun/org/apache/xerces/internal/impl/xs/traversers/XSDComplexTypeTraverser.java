package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeFacetException;
import com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeUseImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelGroupImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSWildcardDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSAttributeUse;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDComplexTypeTraverser extends XSDAbstractParticleTraverser {
    private static final boolean DEBUG = false;
    private static final int GLOBAL_NUM = 11;
    private static XSParticleDecl fErrorContent;
    private static XSWildcardDecl fErrorWildcard;
    private XSAnnotationImpl[] fAnnotations;
    private XSAttributeGroupDecl fAttrGrp;
    private XSTypeDefinition fBaseType;
    private short fBlock;
    private XSComplexTypeDecl fComplexTypeDecl;
    private short fContentType;
    private short fDerivedBy;
    private short fFinal;
    private Object[] fGlobalStore;
    private int fGlobalStorePos;
    private boolean fIsAbstract;
    private String fName;
    private XSParticleDecl fParticle;
    private String fTargetNamespace;
    private XSSimpleType fXSSimpleType;

    public XSDComplexTypeTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
        this.fName = null;
        this.fTargetNamespace = null;
        this.fDerivedBy = (short) 2;
        this.fFinal = (short) 0;
        this.fBlock = (short) 0;
        this.fContentType = (short) 0;
        this.fBaseType = null;
        this.fAttrGrp = null;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fIsAbstract = false;
        this.fComplexTypeDecl = null;
        this.fAnnotations = null;
        this.fGlobalStore = null;
        this.fGlobalStorePos = 0;
    }

    private void addAnnotation(XSAnnotationImpl xSAnnotationImpl) {
        if (xSAnnotationImpl == null) {
            return;
        }
        XSAnnotationImpl[] xSAnnotationImplArr = this.fAnnotations;
        if (xSAnnotationImplArr == null) {
            this.fAnnotations = new XSAnnotationImpl[1];
        } else {
            XSAnnotationImpl[] xSAnnotationImplArr2 = new XSAnnotationImpl[xSAnnotationImplArr.length + 1];
            System.arraycopy(xSAnnotationImplArr, 0, xSAnnotationImplArr2, 0, xSAnnotationImplArr.length);
            this.fAnnotations = xSAnnotationImplArr2;
        }
        XSAnnotationImpl[] xSAnnotationImplArr3 = this.fAnnotations;
        xSAnnotationImplArr3[xSAnnotationImplArr3.length - 1] = xSAnnotationImpl;
    }

    private void contentBackup() {
        if (this.fGlobalStore == null) {
            this.fGlobalStore = new Object[11];
            this.fGlobalStorePos = 0;
        }
        int i = this.fGlobalStorePos;
        Object[] objArr = this.fGlobalStore;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[i + 11];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            this.fGlobalStore = objArr2;
        }
        Object[] objArr3 = this.fGlobalStore;
        int i2 = this.fGlobalStorePos;
        int i3 = i2 + 1;
        this.fGlobalStorePos = i3;
        objArr3[i2] = this.fComplexTypeDecl;
        int i4 = i2 + 2;
        this.fGlobalStorePos = i4;
        objArr3[i3] = this.fIsAbstract ? Boolean.TRUE : Boolean.FALSE;
        int i5 = i2 + 3;
        this.fGlobalStorePos = i5;
        objArr3[i4] = this.fName;
        int i6 = i2 + 4;
        this.fGlobalStorePos = i6;
        objArr3[i5] = this.fTargetNamespace;
        this.fGlobalStorePos = i2 + 5;
        objArr3[i6] = Integer.valueOf((this.fDerivedBy << 16) + this.fFinal);
        Object[] objArr4 = this.fGlobalStore;
        int i7 = this.fGlobalStorePos;
        this.fGlobalStorePos = i7 + 1;
        objArr4[i7] = Integer.valueOf((this.fBlock << 16) + this.fContentType);
        Object[] objArr5 = this.fGlobalStore;
        int i8 = this.fGlobalStorePos;
        int i9 = i8 + 1;
        this.fGlobalStorePos = i9;
        objArr5[i8] = this.fBaseType;
        int i10 = i8 + 2;
        this.fGlobalStorePos = i10;
        objArr5[i9] = this.fAttrGrp;
        int i11 = i8 + 3;
        this.fGlobalStorePos = i11;
        objArr5[i10] = this.fParticle;
        int i12 = i8 + 4;
        this.fGlobalStorePos = i12;
        objArr5[i11] = this.fXSSimpleType;
        this.fGlobalStorePos = i8 + 5;
        objArr5[i12] = this.fAnnotations;
    }

    private void contentRestore() {
        Object[] objArr = this.fGlobalStore;
        int i = this.fGlobalStorePos;
        int i2 = i - 1;
        this.fGlobalStorePos = i2;
        this.fAnnotations = (XSAnnotationImpl[]) objArr[i2];
        int i3 = i - 2;
        this.fGlobalStorePos = i3;
        this.fXSSimpleType = (XSSimpleType) objArr[i3];
        int i4 = i - 3;
        this.fGlobalStorePos = i4;
        this.fParticle = (XSParticleDecl) objArr[i4];
        int i5 = i - 4;
        this.fGlobalStorePos = i5;
        this.fAttrGrp = (XSAttributeGroupDecl) objArr[i5];
        int i6 = i - 5;
        this.fGlobalStorePos = i6;
        this.fBaseType = (XSTypeDefinition) objArr[i6];
        int i7 = i - 6;
        this.fGlobalStorePos = i7;
        int iIntValue = ((Integer) objArr[i7]).intValue();
        this.fBlock = (short) (iIntValue >> 16);
        this.fContentType = (short) iIntValue;
        Object[] objArr2 = this.fGlobalStore;
        int i8 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i8;
        int iIntValue2 = ((Integer) objArr2[i8]).intValue();
        this.fDerivedBy = (short) (iIntValue2 >> 16);
        this.fFinal = (short) iIntValue2;
        Object[] objArr3 = this.fGlobalStore;
        int i9 = this.fGlobalStorePos;
        int i10 = i9 - 1;
        this.fGlobalStorePos = i10;
        this.fTargetNamespace = (String) objArr3[i10];
        int i11 = i9 - 2;
        this.fGlobalStorePos = i11;
        this.fName = (String) objArr3[i11];
        int i12 = i9 - 3;
        this.fGlobalStorePos = i12;
        this.fIsAbstract = ((Boolean) objArr3[i12]).booleanValue();
        Object[] objArr4 = this.fGlobalStore;
        int i13 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i13;
        this.fComplexTypeDecl = (XSComplexTypeDecl) objArr4[i13];
    }

    private String genAnonTypeName(Element element) {
        StringBuffer stringBuffer = new StringBuffer("#AnonType_");
        for (Element parent = DOMUtil.getParent(element); parent != null && parent != DOMUtil.getRoot(DOMUtil.getDocument(parent)); parent = DOMUtil.getParent(parent)) {
            stringBuffer.append(parent.getAttribute(SchemaSymbols.ATT_NAME));
        }
        return stringBuffer.toString();
    }

    private static XSParticleDecl getErrorContent() {
        if (fErrorContent == null) {
            XSParticleDecl xSParticleDecl = new XSParticleDecl();
            xSParticleDecl.fType = (short) 2;
            xSParticleDecl.fValue = getErrorWildcard();
            xSParticleDecl.fMinOccurs = 0;
            xSParticleDecl.fMaxOccurs = -1;
            XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
            xSModelGroupImpl.fCompositor = (short) 102;
            xSModelGroupImpl.fParticleCount = 1;
            xSModelGroupImpl.fParticles = new XSParticleDecl[]{xSParticleDecl};
            XSParticleDecl xSParticleDecl2 = new XSParticleDecl();
            xSParticleDecl2.fType = (short) 3;
            xSParticleDecl2.fValue = xSModelGroupImpl;
            fErrorContent = xSParticleDecl2;
        }
        return fErrorContent;
    }

    private static XSWildcardDecl getErrorWildcard() {
        if (fErrorWildcard == null) {
            XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
            xSWildcardDecl.fProcessContents = (short) 2;
            fErrorWildcard = xSWildcardDecl;
        }
        return fErrorWildcard;
    }

    private void handleComplexTypeError(String str, Object[] objArr, Element element) {
        if (str != null) {
            reportSchemaError(str, objArr, element);
        }
        this.fBaseType = SchemaGrammar.fAnyType;
        this.fContentType = (short) 3;
        this.fXSSimpleType = null;
        this.fParticle = getErrorContent();
        this.fAttrGrp.fAttributeWC = getErrorWildcard();
    }

    private boolean isAttrOrAttrGroup(Element element) {
        String localName = DOMUtil.getLocalName(element);
        return localName.equals(SchemaSymbols.ELT_ATTRIBUTE) || localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP) || localName.equals(SchemaSymbols.ELT_ANYATTRIBUTE);
    }

    private void mergeAttributes(XSAttributeGroupDecl xSAttributeGroupDecl, XSAttributeGroupDecl xSAttributeGroupDecl2, String str, boolean z, Element element) throws ComplexTypeRecoverableError {
        XSObjectList attributeUses = xSAttributeGroupDecl.getAttributeUses();
        int length = attributeUses.getLength();
        for (int i = 0; i < length; i++) {
            XSAttributeUseImpl xSAttributeUseImpl = (XSAttributeUseImpl) attributeUses.item(i);
            XSAttributeUse attributeUse = xSAttributeGroupDecl2.getAttributeUse(xSAttributeUseImpl.fAttrDecl.getNamespace(), xSAttributeUseImpl.fAttrDecl.getName());
            if (attributeUse == null) {
                String strAddAttributeUse = xSAttributeGroupDecl2.addAttributeUse(xSAttributeUseImpl);
                if (strAddAttributeUse != null) {
                    throw new ComplexTypeRecoverableError("ct-props-correct.5", new Object[]{str, strAddAttributeUse, xSAttributeUseImpl.fAttrDecl.getName()}, element);
                }
            } else if (attributeUse != xSAttributeUseImpl && z) {
                reportSchemaError("ct-props-correct.4", new Object[]{str, xSAttributeUseImpl.fAttrDecl.getName()}, element);
                xSAttributeGroupDecl2.replaceAttributeUse(attributeUse, xSAttributeUseImpl);
            }
        }
        if (z) {
            XSWildcardDecl xSWildcardDecl = xSAttributeGroupDecl2.fAttributeWC;
            XSWildcardDecl xSWildcardDecl2 = xSAttributeGroupDecl.fAttributeWC;
            if (xSWildcardDecl == null) {
                xSAttributeGroupDecl2.fAttributeWC = xSWildcardDecl2;
            } else if (xSWildcardDecl2 != null) {
                XSWildcardDecl xSWildcardDeclPerformUnionWith = xSWildcardDecl.performUnionWith(xSWildcardDecl2, xSWildcardDecl.fProcessContents);
                xSAttributeGroupDecl2.fAttributeWC = xSWildcardDeclPerformUnionWith;
                if (xSWildcardDeclPerformUnionWith == null) {
                    throw new ComplexTypeRecoverableError("src-ct.5", new Object[]{str}, element);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:41:0x009c  */
    /* JADX WARN: Code duplicated, block: B:48:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:54:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:55:0x00c5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:56:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:57:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:59:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:61:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:63:0x00e6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:68:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:70:0x010e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:71:? A[RETURN, SYNTHETIC] */
    private void processComplexContent(Element element, boolean z, boolean z2, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) throws ComplexTypeRecoverableError {
        Element nextSiblingElement;
        XSParticleDecl xSParticleDeclTraverseLocal;
        boolean z3;
        XSParticleDecl emptySequence;
        Element elementTraverseAttrsAndAttrGrps;
        Element firstChildElement;
        XSParticleDecl xSParticleDeclTraverseAll;
        if (element != null) {
            String localName = DOMUtil.getLocalName(element);
            if (!localName.equals(SchemaSymbols.ELT_GROUP)) {
                if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                    xSParticleDeclTraverseAll = traverseSequence(element, xSDocumentInfo, schemaGrammar, 0, this.fComplexTypeDecl);
                    z3 = xSParticleDeclTraverseAll != null && ((XSModelGroupImpl) xSParticleDeclTraverseAll.fValue).fParticleCount == 0;
                    nextSiblingElement = DOMUtil.getNextSiblingElement(element);
                } else if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                    xSParticleDeclTraverseAll = traverseChoice(element, xSDocumentInfo, schemaGrammar, 0, this.fComplexTypeDecl);
                    z3 = xSParticleDeclTraverseAll != null && xSParticleDeclTraverseAll.fMinOccurs == 0 && ((XSModelGroupImpl) xSParticleDeclTraverseAll.fValue).fParticleCount == 0;
                    nextSiblingElement = DOMUtil.getNextSiblingElement(element);
                } else if (localName.equals(SchemaSymbols.ELT_ALL)) {
                    xSParticleDeclTraverseAll = traverseAll(element, xSDocumentInfo, schemaGrammar, 8, this.fComplexTypeDecl);
                    z3 = xSParticleDeclTraverseAll != null && ((XSModelGroupImpl) xSParticleDeclTraverseAll.fValue).fParticleCount == 0;
                    nextSiblingElement = DOMUtil.getNextSiblingElement(element);
                } else {
                    nextSiblingElement = element;
                    xSParticleDeclTraverseLocal = null;
                }
                xSParticleDeclTraverseLocal = xSParticleDeclTraverseAll;
                if (!z3) {
                    firstChildElement = DOMUtil.getFirstChildElement(element);
                    if (firstChildElement != null && DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                    }
                    emptySequence = firstChildElement != null ? xSParticleDeclTraverseLocal : null;
                }
                if (emptySequence == null && z) {
                    emptySequence = XSConstraints.getEmptySequence();
                }
                this.fParticle = emptySequence;
                if (emptySequence == null) {
                    this.fContentType = (short) 0;
                } else if (z) {
                    this.fContentType = (short) 3;
                } else {
                    this.fContentType = (short) 2;
                }
                if (nextSiblingElement != null) {
                    if (isAttrOrAttrGroup(nextSiblingElement)) {
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    }
                    elementTraverseAttrsAndAttrGrps = traverseAttrsAndAttrGrps(nextSiblingElement, this.fAttrGrp, xSDocumentInfo, schemaGrammar, this.fComplexTypeDecl);
                    if (elementTraverseAttrsAndAttrGrps == null) {
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps)}, elementTraverseAttrsAndAttrGrps);
                    }
                    if (z2) {
                    }
                    this.fAttrGrp.removeProhibitedAttrs();
                }
            }
            xSParticleDeclTraverseLocal = this.fSchemaHandler.fGroupTraverser.traverseLocal(element, xSDocumentInfo, schemaGrammar);
            nextSiblingElement = DOMUtil.getNextSiblingElement(element);
        } else {
            nextSiblingElement = null;
            xSParticleDeclTraverseLocal = null;
        }
        z3 = false;
        if (!z3) {
            firstChildElement = DOMUtil.getFirstChildElement(element);
            if (firstChildElement != null) {
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            }
            if (firstChildElement != null) {
            }
        }
        if (emptySequence == null) {
            emptySequence = XSConstraints.getEmptySequence();
        }
        this.fParticle = emptySequence;
        if (emptySequence == null) {
            this.fContentType = (short) 0;
        } else if (z) {
            this.fContentType = (short) 3;
        } else {
            this.fContentType = (short) 2;
        }
        if (nextSiblingElement != null) {
            if (isAttrOrAttrGroup(nextSiblingElement)) {
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
            }
            elementTraverseAttrsAndAttrGrps = traverseAttrsAndAttrGrps(nextSiblingElement, this.fAttrGrp, xSDocumentInfo, schemaGrammar, this.fComplexTypeDecl);
            if (elementTraverseAttrsAndAttrGrps == null) {
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps)}, elementTraverseAttrsAndAttrGrps);
            }
            if (z2) {
                this.fAttrGrp.removeProhibitedAttrs();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0219  */
    /* JADX WARN: Code duplicated, block: B:103:0x0221  */
    /* JADX WARN: Code duplicated, block: B:121:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:123:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:125:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:127:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:129:0x0312  */
    /* JADX WARN: Code duplicated, block: B:131:0x0324  */
    /* JADX WARN: Code duplicated, block: B:16:0x004e  */
    /* JADX WARN: Code duplicated, block: B:18:0x005f  */
    /* JADX WARN: Code duplicated, block: B:19:0x0062  */
    /* JADX WARN: Code duplicated, block: B:21:0x006a  */
    /* JADX WARN: Code duplicated, block: B:24:0x0074  */
    /* JADX WARN: Code duplicated, block: B:26:0x0080  */
    /* JADX WARN: Code duplicated, block: B:28:0x008b  */
    /* JADX WARN: Code duplicated, block: B:30:0x008f  */
    /* JADX WARN: Code duplicated, block: B:32:0x009d  */
    /* JADX WARN: Code duplicated, block: B:34:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:35:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:43:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:54:0x0115  */
    /* JADX WARN: Code duplicated, block: B:56:0x011b  */
    /* JADX WARN: Code duplicated, block: B:61:0x013e  */
    /* JADX WARN: Code duplicated, block: B:63:0x0142  */
    /* JADX WARN: Code duplicated, block: B:71:0x017b  */
    /* JADX WARN: Code duplicated, block: B:79:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:81:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:82:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:85:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:87:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:94:0x01f2  */
    private void traverseComplexContent(Element element, boolean z, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) throws ComplexTypeRecoverableError {
        String localName;
        Element nextSiblingElement;
        XSAttributeChecker xSAttributeChecker;
        Object[] objArrCheckAttributes;
        QName qName;
        XSTypeDefinition xSTypeDefinition;
        XSComplexTypeDecl xSComplexTypeDecl;
        Element firstChildElement;
        String syntheticAnnotation;
        Element element2;
        XSParticleDecl xSParticleDecl;
        XSParticleDecl xSParticleDecl2;
        Object[] objArrValidRestrictionOf;
        String localName2;
        String str;
        String syntheticAnnotation2;
        String str2;
        Object[] objArrCheckAttributes2 = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        Boolean bool = (Boolean) objArrCheckAttributes2[XSAttributeChecker.ATTIDX_MIXED];
        boolean zBooleanValue = bool != null ? bool.booleanValue() : z;
        this.fXSSimpleType = null;
        Element firstChildElement2 = DOMUtil.getFirstChildElement(element);
        if (firstChildElement2 == null || !DOMUtil.getLocalName(firstChildElement2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation3 != null) {
                addAnnotation(traverseSyntheticAnnotation(element, syntheticAnnotation3, objArrCheckAttributes2, false, xSDocumentInfo));
            }
            if (firstChildElement2 != null) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[]{this.fName, SchemaSymbols.ELT_COMPLEXCONTENT}, element);
            }
            localName = DOMUtil.getLocalName(firstChildElement2);
            if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
                this.fDerivedBy = (short) 2;
            } else {
                if (localName.equals(SchemaSymbols.ELT_EXTENSION)) {
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, localName}, firstChildElement2);
                }
                this.fDerivedBy = (short) 1;
            }
            nextSiblingElement = DOMUtil.getNextSiblingElement(firstChildElement2);
            xSAttributeChecker = this.fAttrChecker;
            if (nextSiblingElement == null) {
                xSAttributeChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
            }
            objArrCheckAttributes = xSAttributeChecker.checkAttributes(firstChildElement2, false, xSDocumentInfo);
            qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_BASE];
            if (qName != null) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[]{localName, "base"}, firstChildElement2);
            }
            xSTypeDefinition = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 7, qName, firstChildElement2);
            if (xSTypeDefinition != null) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                throw new ComplexTypeRecoverableError();
            }
            if (xSTypeDefinition instanceof XSComplexTypeDecl) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                throw new ComplexTypeRecoverableError("src-ct.1", new Object[]{this.fName, xSTypeDefinition.getName()}, firstChildElement2);
            }
            xSComplexTypeDecl = (XSComplexTypeDecl) xSTypeDefinition;
            this.fBaseType = xSComplexTypeDecl;
            if ((xSComplexTypeDecl.getFinal() & this.fDerivedBy) != 0) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                if (this.fDerivedBy == 1) {
                    str2 = "cos-ct-extends.1.1";
                } else {
                    str2 = "derivation-ok-restriction.1";
                }
                throw new ComplexTypeRecoverableError(str2, new Object[]{this.fName, this.fBaseType.getName()}, firstChildElement2);
            }
            firstChildElement = DOMUtil.getFirstChildElement(firstChildElement2);
            try {
                if (firstChildElement != null) {
                    syntheticAnnotation = DOMUtil.getSyntheticAnnotation(firstChildElement);
                    if (syntheticAnnotation != null) {
                        addAnnotation(traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo));
                    }
                    element2 = firstChildElement;
                    processComplexContent(element2, zBooleanValue, true, xSDocumentInfo, schemaGrammar);
                    xSParticleDecl = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                    if (this.fDerivedBy != 2) {
                        if (this.fParticle == null) {
                            this.fContentType = xSComplexTypeDecl.getContentType();
                            this.fXSSimpleType = (XSSimpleType) xSComplexTypeDecl.getSimpleType();
                            this.fParticle = xSParticleDecl;
                        } else if (xSComplexTypeDecl.getContentType() != 0) {
                            if (this.fContentType != 2 && xSComplexTypeDecl.getContentType() != 2) {
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                                throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.3.2.2.1.a", new Object[]{this.fName}, element2);
                            }
                            if (this.fContentType != 3 && xSComplexTypeDecl.getContentType() != 3) {
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                                throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.3.2.2.1.b", new Object[]{this.fName}, element2);
                            }
                            xSParticleDecl2 = this.fParticle;
                            if ((xSParticleDecl2.fType != 3 && ((XSModelGroupImpl) xSParticleDecl2.fValue).fCompositor == 103) || (((XSParticleDecl) xSComplexTypeDecl.getParticle()).fType == 3 && ((XSModelGroupImpl) ((XSParticleDecl) xSComplexTypeDecl.getParticle()).fValue).fCompositor == 103)) {
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                                throw new ComplexTypeRecoverableError("cos-all-limited.1.2", new Object[0], element2);
                            }
                            XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
                            xSModelGroupImpl.fCompositor = (short) 102;
                            xSModelGroupImpl.fParticleCount = 2;
                            XSParticleDecl[] xSParticleDeclArr = new XSParticleDecl[2];
                            xSModelGroupImpl.fParticles = xSParticleDeclArr;
                            xSParticleDeclArr[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                            xSModelGroupImpl.fParticles[1] = this.fParticle;
                            XSObjectListImpl xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
                            xSModelGroupImpl.fAnnotations = xSObjectListImpl;
                            XSParticleDecl xSParticleDecl3 = new XSParticleDecl();
                            xSParticleDecl3.fType = (short) 3;
                            xSParticleDecl3.fValue = xSModelGroupImpl;
                            xSParticleDecl3.fAnnotations = xSObjectListImpl;
                            this.fParticle = xSParticleDecl3;
                        }
                        this.fAttrGrp.removeProhibitedAttrs();
                        try {
                            mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element2);
                        } catch (ComplexTypeRecoverableError e) {
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                            throw e;
                        }
                    } else {
                        if (this.fContentType != 3 && xSComplexTypeDecl.getContentType() != 3) {
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                            throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.4.1.2", new Object[]{this.fName, xSComplexTypeDecl.getName()}, element2);
                        }
                        try {
                            mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element2);
                            this.fAttrGrp.removeProhibitedAttrs();
                            if (xSComplexTypeDecl != SchemaGrammar.fAnyType && (objArrValidRestrictionOf = this.fAttrGrp.validRestrictionOf(this.fName, xSComplexTypeDecl.getAttrGrp())) != null) {
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                                throw new ComplexTypeRecoverableError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element2);
                            }
                        } catch (ComplexTypeRecoverableError e2) {
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                            throw e2;
                        }
                    }
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                }
                localName2 = DOMUtil.getLocalName(firstChildElement);
                str = SchemaSymbols.ELT_ANNOTATION;
                if (localName2.equals(str)) {
                    addAnnotation(traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo));
                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                } else {
                    syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(firstChildElement);
                    if (syntheticAnnotation2 != null) {
                        addAnnotation(traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo));
                    }
                }
                if (firstChildElement != null && DOMUtil.getLocalName(firstChildElement).equals(str)) {
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, str}, firstChildElement);
                }
                processComplexContent(element2, zBooleanValue, true, xSDocumentInfo, schemaGrammar);
                xSParticleDecl = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                if (this.fDerivedBy != 2) {
                    if (this.fContentType != 3) {
                    }
                    mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element2);
                    this.fAttrGrp.removeProhibitedAttrs();
                    if (xSComplexTypeDecl != SchemaGrammar.fAnyType) {
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                        throw new ComplexTypeRecoverableError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element2);
                    }
                } else {
                    if (this.fParticle == null) {
                        this.fContentType = xSComplexTypeDecl.getContentType();
                        this.fXSSimpleType = (XSSimpleType) xSComplexTypeDecl.getSimpleType();
                        this.fParticle = xSParticleDecl;
                    } else if (xSComplexTypeDecl.getContentType() != 0) {
                        if (this.fContentType != 2) {
                        }
                        if (this.fContentType != 3) {
                        }
                        xSParticleDecl2 = this.fParticle;
                        if (xSParticleDecl2.fType != 3) {
                            XSModelGroupImpl xSModelGroupImpl2 = new XSModelGroupImpl();
                            xSModelGroupImpl2.fCompositor = (short) 102;
                            xSModelGroupImpl2.fParticleCount = 2;
                            XSParticleDecl[] xSParticleDeclArr2 = new XSParticleDecl[2];
                            xSModelGroupImpl2.fParticles = xSParticleDeclArr2;
                            xSParticleDeclArr2[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                            xSModelGroupImpl2.fParticles[1] = this.fParticle;
                            XSObjectListImpl xSObjectListImpl2 = XSObjectListImpl.EMPTY_LIST;
                            xSModelGroupImpl2.fAnnotations = xSObjectListImpl2;
                            XSParticleDecl xSParticleDecl4 = new XSParticleDecl();
                            xSParticleDecl4.fType = (short) 3;
                            xSParticleDecl4.fValue = xSModelGroupImpl2;
                            xSParticleDecl4.fAnnotations = xSObjectListImpl2;
                            this.fParticle = xSParticleDecl4;
                        } else {
                            XSModelGroupImpl xSModelGroupImpl3 = new XSModelGroupImpl();
                            xSModelGroupImpl3.fCompositor = (short) 102;
                            xSModelGroupImpl3.fParticleCount = 2;
                            XSParticleDecl[] xSParticleDeclArr3 = new XSParticleDecl[2];
                            xSModelGroupImpl3.fParticles = xSParticleDeclArr3;
                            xSParticleDeclArr3[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                            xSModelGroupImpl3.fParticles[1] = this.fParticle;
                            XSObjectListImpl xSObjectListImpl3 = XSObjectListImpl.EMPTY_LIST;
                            xSModelGroupImpl3.fAnnotations = xSObjectListImpl3;
                            XSParticleDecl xSParticleDecl5 = new XSParticleDecl();
                            xSParticleDecl5.fType = (short) 3;
                            xSParticleDecl5.fValue = xSModelGroupImpl3;
                            xSParticleDecl5.fAnnotations = xSObjectListImpl3;
                            this.fParticle = xSParticleDecl5;
                        }
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                        throw new ComplexTypeRecoverableError("cos-all-limited.1.2", new Object[0], element2);
                    }
                    this.fAttrGrp.removeProhibitedAttrs();
                    mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element2);
                }
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            } catch (ComplexTypeRecoverableError e3) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                throw e3;
            }
            element2 = firstChildElement;
        } else {
            addAnnotation(traverseAnnotationDecl(firstChildElement2, objArrCheckAttributes2, false, xSDocumentInfo));
            firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
        }
        if (firstChildElement2 != null) {
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[]{this.fName, SchemaSymbols.ELT_COMPLEXCONTENT}, element);
        }
        localName = DOMUtil.getLocalName(firstChildElement2);
        if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = (short) 2;
        } else {
            if (localName.equals(SchemaSymbols.ELT_EXTENSION)) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, localName}, firstChildElement2);
            }
            this.fDerivedBy = (short) 1;
        }
        nextSiblingElement = DOMUtil.getNextSiblingElement(firstChildElement2);
        xSAttributeChecker = this.fAttrChecker;
        if (nextSiblingElement == null) {
            xSAttributeChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
        }
        objArrCheckAttributes = xSAttributeChecker.checkAttributes(firstChildElement2, false, xSDocumentInfo);
        qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_BASE];
        if (qName != null) {
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[]{localName, "base"}, firstChildElement2);
        }
        xSTypeDefinition = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 7, qName, firstChildElement2);
        if (xSTypeDefinition != null) {
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            throw new ComplexTypeRecoverableError();
        }
        if (xSTypeDefinition instanceof XSComplexTypeDecl) {
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            throw new ComplexTypeRecoverableError("src-ct.1", new Object[]{this.fName, xSTypeDefinition.getName()}, firstChildElement2);
        }
        xSComplexTypeDecl = (XSComplexTypeDecl) xSTypeDefinition;
        this.fBaseType = xSComplexTypeDecl;
        if ((xSComplexTypeDecl.getFinal() & this.fDerivedBy) != 0) {
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            if (this.fDerivedBy == 1) {
                str2 = "cos-ct-extends.1.1";
            } else {
                str2 = "derivation-ok-restriction.1";
            }
            throw new ComplexTypeRecoverableError(str2, new Object[]{this.fName, this.fBaseType.getName()}, firstChildElement2);
        }
        firstChildElement = DOMUtil.getFirstChildElement(firstChildElement2);
        if (firstChildElement != null) {
            syntheticAnnotation = DOMUtil.getSyntheticAnnotation(firstChildElement);
            if (syntheticAnnotation != null) {
                addAnnotation(traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo));
            }
            element2 = firstChildElement;
            processComplexContent(element2, zBooleanValue, true, xSDocumentInfo, schemaGrammar);
            xSParticleDecl = (XSParticleDecl) xSComplexTypeDecl.getParticle();
            if (this.fDerivedBy != 2) {
                if (this.fContentType != 3) {
                }
                mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element2);
                this.fAttrGrp.removeProhibitedAttrs();
                if (xSComplexTypeDecl != SchemaGrammar.fAnyType) {
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                    throw new ComplexTypeRecoverableError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element2);
                }
            } else {
                if (this.fParticle == null) {
                    this.fContentType = xSComplexTypeDecl.getContentType();
                    this.fXSSimpleType = (XSSimpleType) xSComplexTypeDecl.getSimpleType();
                    this.fParticle = xSParticleDecl;
                } else if (xSComplexTypeDecl.getContentType() != 0) {
                    if (this.fContentType != 2) {
                    }
                    if (this.fContentType != 3) {
                    }
                    xSParticleDecl2 = this.fParticle;
                    if (xSParticleDecl2.fType != 3) {
                        XSModelGroupImpl xSModelGroupImpl4 = new XSModelGroupImpl();
                        xSModelGroupImpl4.fCompositor = (short) 102;
                        xSModelGroupImpl4.fParticleCount = 2;
                        XSParticleDecl[] xSParticleDeclArr4 = new XSParticleDecl[2];
                        xSModelGroupImpl4.fParticles = xSParticleDeclArr4;
                        xSParticleDeclArr4[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                        xSModelGroupImpl4.fParticles[1] = this.fParticle;
                        XSObjectListImpl xSObjectListImpl4 = XSObjectListImpl.EMPTY_LIST;
                        xSModelGroupImpl4.fAnnotations = xSObjectListImpl4;
                        XSParticleDecl xSParticleDecl6 = new XSParticleDecl();
                        xSParticleDecl6.fType = (short) 3;
                        xSParticleDecl6.fValue = xSModelGroupImpl4;
                        xSParticleDecl6.fAnnotations = xSObjectListImpl4;
                        this.fParticle = xSParticleDecl6;
                    } else {
                        XSModelGroupImpl xSModelGroupImpl5 = new XSModelGroupImpl();
                        xSModelGroupImpl5.fCompositor = (short) 102;
                        xSModelGroupImpl5.fParticleCount = 2;
                        XSParticleDecl[] xSParticleDeclArr5 = new XSParticleDecl[2];
                        xSModelGroupImpl5.fParticles = xSParticleDeclArr5;
                        xSParticleDeclArr5[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                        xSModelGroupImpl5.fParticles[1] = this.fParticle;
                        XSObjectListImpl xSObjectListImpl5 = XSObjectListImpl.EMPTY_LIST;
                        xSModelGroupImpl5.fAnnotations = xSObjectListImpl5;
                        XSParticleDecl xSParticleDecl7 = new XSParticleDecl();
                        xSParticleDecl7.fType = (short) 3;
                        xSParticleDecl7.fValue = xSModelGroupImpl5;
                        xSParticleDecl7.fAnnotations = xSObjectListImpl5;
                        this.fParticle = xSParticleDecl7;
                    }
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                    throw new ComplexTypeRecoverableError("cos-all-limited.1.2", new Object[0], element2);
                }
                this.fAttrGrp.removeProhibitedAttrs();
                mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element2);
            }
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        }
        localName2 = DOMUtil.getLocalName(firstChildElement);
        str = SchemaSymbols.ELT_ANNOTATION;
        if (localName2.equals(str)) {
            addAnnotation(traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo));
            firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
        } else {
            syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(firstChildElement);
            if (syntheticAnnotation2 != null) {
                addAnnotation(traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo));
            }
        }
        if (firstChildElement != null) {
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, str}, firstChildElement);
        }
        element2 = firstChildElement;
        processComplexContent(element2, zBooleanValue, true, xSDocumentInfo, schemaGrammar);
        xSParticleDecl = (XSParticleDecl) xSComplexTypeDecl.getParticle();
        if (this.fDerivedBy != 2) {
            if (this.fContentType != 3) {
            }
            mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element2);
            this.fAttrGrp.removeProhibitedAttrs();
            if (xSComplexTypeDecl != SchemaGrammar.fAnyType) {
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                throw new ComplexTypeRecoverableError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element2);
            }
        } else {
            if (this.fParticle == null) {
                this.fContentType = xSComplexTypeDecl.getContentType();
                this.fXSSimpleType = (XSSimpleType) xSComplexTypeDecl.getSimpleType();
                this.fParticle = xSParticleDecl;
            } else if (xSComplexTypeDecl.getContentType() != 0) {
                if (this.fContentType != 2) {
                }
                if (this.fContentType != 3) {
                }
                xSParticleDecl2 = this.fParticle;
                if (xSParticleDecl2.fType != 3) {
                    XSModelGroupImpl xSModelGroupImpl6 = new XSModelGroupImpl();
                    xSModelGroupImpl6.fCompositor = (short) 102;
                    xSModelGroupImpl6.fParticleCount = 2;
                    XSParticleDecl[] xSParticleDeclArr6 = new XSParticleDecl[2];
                    xSModelGroupImpl6.fParticles = xSParticleDeclArr6;
                    xSParticleDeclArr6[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                    xSModelGroupImpl6.fParticles[1] = this.fParticle;
                    XSObjectListImpl xSObjectListImpl6 = XSObjectListImpl.EMPTY_LIST;
                    xSModelGroupImpl6.fAnnotations = xSObjectListImpl6;
                    XSParticleDecl xSParticleDecl8 = new XSParticleDecl();
                    xSParticleDecl8.fType = (short) 3;
                    xSParticleDecl8.fValue = xSModelGroupImpl6;
                    xSParticleDecl8.fAnnotations = xSObjectListImpl6;
                    this.fParticle = xSParticleDecl8;
                } else {
                    XSModelGroupImpl xSModelGroupImpl7 = new XSModelGroupImpl();
                    xSModelGroupImpl7.fCompositor = (short) 102;
                    xSModelGroupImpl7.fParticleCount = 2;
                    XSParticleDecl[] xSParticleDeclArr7 = new XSParticleDecl[2];
                    xSModelGroupImpl7.fParticles = xSParticleDeclArr7;
                    xSParticleDeclArr7[0] = (XSParticleDecl) xSComplexTypeDecl.getParticle();
                    xSModelGroupImpl7.fParticles[1] = this.fParticle;
                    XSObjectListImpl xSObjectListImpl7 = XSObjectListImpl.EMPTY_LIST;
                    xSModelGroupImpl7.fAnnotations = xSObjectListImpl7;
                    XSParticleDecl xSParticleDecl9 = new XSParticleDecl();
                    xSParticleDecl9.fType = (short) 3;
                    xSParticleDecl9.fValue = xSModelGroupImpl7;
                    xSParticleDecl9.fAnnotations = xSObjectListImpl7;
                    this.fParticle = xSParticleDecl9;
                }
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                throw new ComplexTypeRecoverableError("cos-all-limited.1.2", new Object[0], element2);
            }
            this.fAttrGrp.removeProhibitedAttrs();
            mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element2);
        }
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
    }

    private void traverseComplexContentDecl(Element element, boolean z) {
    }

    private XSComplexTypeDecl traverseComplexTypeDecl(Element element, String str, Object[] objArr, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        this.fComplexTypeDecl = new XSComplexTypeDecl();
        this.fAttrGrp = new XSAttributeGroupDecl();
        Boolean bool = (Boolean) objArr[XSAttributeChecker.ATTIDX_ABSTRACT];
        XInt xInt = (XInt) objArr[XSAttributeChecker.ATTIDX_BLOCK];
        Boolean bool2 = (Boolean) objArr[XSAttributeChecker.ATTIDX_MIXED];
        XInt xInt2 = (XInt) objArr[XSAttributeChecker.ATTIDX_FINAL];
        this.fName = str;
        this.fComplexTypeDecl.setName(str);
        this.fTargetNamespace = xSDocumentInfo.fTargetNamespace;
        this.fBlock = xInt == null ? xSDocumentInfo.fBlockDefault : xInt.shortValue();
        short sShortValue = xInt2 == null ? xSDocumentInfo.fFinalDefault : xInt2.shortValue();
        this.fBlock = (short) (this.fBlock & 3);
        this.fFinal = (short) (sShortValue & 3);
        this.fIsAbstract = bool != null && bool.booleanValue();
        this.fAnnotations = null;
        try {
            Element firstChildElement = DOMUtil.getFirstChildElement(element);
            if (firstChildElement != null) {
                String localName = DOMUtil.getLocalName(firstChildElement);
                String str2 = SchemaSymbols.ELT_ANNOTATION;
                if (localName.equals(str2)) {
                    addAnnotation(traverseAnnotationDecl(firstChildElement, objArr, false, xSDocumentInfo));
                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                } else {
                    String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                    if (syntheticAnnotation != null) {
                        addAnnotation(traverseSyntheticAnnotation(element, syntheticAnnotation, objArr, false, xSDocumentInfo));
                    }
                }
                if (firstChildElement != null && DOMUtil.getLocalName(firstChildElement).equals(str2)) {
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, str2}, firstChildElement);
                }
            } else {
                String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation2 != null) {
                    addAnnotation(traverseSyntheticAnnotation(element, syntheticAnnotation2, objArr, false, xSDocumentInfo));
                }
            }
            Element element2 = firstChildElement;
            if (element2 == null) {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.fDerivedBy = (short) 2;
                processComplexContent(element2, bool2.booleanValue(), false, xSDocumentInfo, schemaGrammar);
            } else if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_SIMPLECONTENT)) {
                traverseSimpleContent(element2, xSDocumentInfo, schemaGrammar);
                Element nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
                if (nextSiblingElement != null) {
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                }
            } else if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_COMPLEXCONTENT)) {
                traverseComplexContent(element2, bool2.booleanValue(), xSDocumentInfo, schemaGrammar);
                Element nextSiblingElement2 = DOMUtil.getNextSiblingElement(element2);
                if (nextSiblingElement2 != null) {
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement2)}, nextSiblingElement2);
                }
            } else {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.fDerivedBy = (short) 2;
                processComplexContent(element2, bool2.booleanValue(), false, xSDocumentInfo, schemaGrammar);
            }
        } catch (ComplexTypeRecoverableError e) {
            handleComplexTypeError(e.getMessage(), e.errorSubstText, e.errorElem);
        }
        XSComplexTypeDecl xSComplexTypeDecl = this.fComplexTypeDecl;
        String str3 = this.fName;
        String str4 = this.fTargetNamespace;
        XSTypeDefinition xSTypeDefinition = this.fBaseType;
        short s = this.fDerivedBy;
        short s2 = this.fFinal;
        short s3 = this.fBlock;
        short s4 = this.fContentType;
        boolean z = this.fIsAbstract;
        XSAttributeGroupDecl xSAttributeGroupDecl = this.fAttrGrp;
        XSSimpleType xSSimpleType = this.fXSSimpleType;
        XSParticleDecl xSParticleDecl = this.fParticle;
        XSAnnotationImpl[] xSAnnotationImplArr = this.fAnnotations;
        xSComplexTypeDecl.setValues(str3, str4, xSTypeDefinition, s, s2, s3, s4, z, xSAttributeGroupDecl, xSSimpleType, xSParticleDecl, new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr != null ? xSAnnotationImplArr.length : 0));
        return this.fComplexTypeDecl;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0251  */
    /* JADX WARN: Code duplicated, block: B:102:0x0258  */
    /* JADX WARN: Code duplicated, block: B:104:0x025e  */
    /* JADX WARN: Code duplicated, block: B:107:0x026c  */
    /* JADX WARN: Code duplicated, block: B:109:0x0286  */
    /* JADX WARN: Code duplicated, block: B:111:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:116:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:121:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:123:0x0303  */
    /* JADX WARN: Code duplicated, block: B:125:0x0308  */
    /* JADX WARN: Code duplicated, block: B:127:0x030e  */
    /* JADX WARN: Code duplicated, block: B:129:0x031b  */
    /* JADX WARN: Code duplicated, block: B:130:0x0321  */
    /* JADX WARN: Code duplicated, block: B:132:0x033b  */
    /* JADX WARN: Code duplicated, block: B:13:0x004c  */
    /* JADX WARN: Code duplicated, block: B:142:0x037d  */
    /* JADX WARN: Code duplicated, block: B:144:0x0399  */
    /* JADX WARN: Code duplicated, block: B:146:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:148:0x03c6  */
    /* JADX WARN: Code duplicated, block: B:150:0x03da  */
    /* JADX WARN: Code duplicated, block: B:152:0x03ec  */
    /* JADX WARN: Code duplicated, block: B:156:0x0357 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:15:0x005c  */
    /* JADX WARN: Code duplicated, block: B:16:0x005f  */
    /* JADX WARN: Code duplicated, block: B:18:0x0067  */
    /* JADX WARN: Code duplicated, block: B:21:0x0071  */
    /* JADX WARN: Code duplicated, block: B:23:0x007d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0088  */
    /* JADX WARN: Code duplicated, block: B:27:0x0095  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:31:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:40:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:42:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:47:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:48:0x0100  */
    /* JADX WARN: Code duplicated, block: B:51:0x0114  */
    /* JADX WARN: Code duplicated, block: B:53:0x011a  */
    /* JADX WARN: Code duplicated, block: B:55:0x0126  */
    /* JADX WARN: Code duplicated, block: B:57:0x0134  */
    /* JADX WARN: Code duplicated, block: B:59:0x013a  */
    /* JADX WARN: Code duplicated, block: B:61:0x0149  */
    /* JADX WARN: Code duplicated, block: B:68:0x016d  */
    /* JADX WARN: Code duplicated, block: B:70:0x0174  */
    /* JADX WARN: Code duplicated, block: B:71:0x0181  */
    /* JADX WARN: Code duplicated, block: B:74:0x0189  */
    /* JADX WARN: Code duplicated, block: B:88:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:90:0x01ea A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:91:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:92:0x0200  */
    private void traverseSimpleContent(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) throws ComplexTypeRecoverableError {
        Object[] objArr;
        Element element2;
        String localName;
        Element nextSiblingElement;
        XSAttributeChecker xSAttributeChecker;
        Object[] objArrCheckAttributes;
        QName qName;
        XSTypeDefinition xSTypeDefinition;
        XSSimpleType xSSimpleType;
        short s;
        XSSimpleType xSSimpleType2;
        XSComplexTypeDecl xSComplexTypeDecl;
        Element firstChildElement;
        String syntheticAnnotation;
        Object[] objArr2;
        Element nextSiblingElement2;
        XSDocumentInfo xSDocumentInfo2;
        Element elementTraverseAttrsAndAttrGrps;
        SchemaGrammar schemaGrammar2;
        Element element3;
        short s2;
        XSSimpleType xSSimpleType3;
        Element element4;
        Object[] objArrValidRestrictionOf;
        Element elementTraverseAttrsAndAttrGrps2;
        String localName2;
        String str;
        String syntheticAnnotation2;
        Object[] objArr3;
        String str2;
        XSComplexTypeDecl xSComplexTypeDecl2;
        XSDocumentInfo xSDocumentInfo3 = xSDocumentInfo;
        short s3 = 0;
        Object[] objArrCheckAttributes2 = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo3);
        this.fContentType = (short) 1;
        XSFacets xSFacets = null;
        this.fParticle = null;
        Element firstChildElement2 = DOMUtil.getFirstChildElement(element);
        if (firstChildElement2 == null || !DOMUtil.getLocalName(firstChildElement2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation3 != null) {
                XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element, syntheticAnnotation3, objArrCheckAttributes2, false, xSDocumentInfo3);
                objArr = objArrCheckAttributes2;
                xSDocumentInfo3 = xSDocumentInfo3;
                addAnnotation(xSAnnotationImplTraverseSyntheticAnnotation);
            }
            element2 = firstChildElement2;
            if (element2 != null) {
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[]{this.fName, SchemaSymbols.ELT_SIMPLECONTENT}, element);
            }
            localName = DOMUtil.getLocalName(element2);
            if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
                this.fDerivedBy = (short) 2;
            } else {
                if (localName.equals(SchemaSymbols.ELT_EXTENSION)) {
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, localName}, element2);
                }
                this.fDerivedBy = (short) 1;
            }
            nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
            xSAttributeChecker = this.fAttrChecker;
            if (nextSiblingElement == null) {
                xSAttributeChecker.returnAttrArray(objArr, xSDocumentInfo3);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
            }
            objArrCheckAttributes = xSAttributeChecker.checkAttributes(element2, false, xSDocumentInfo3);
            qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_BASE];
            if (qName != null) {
                XSDocumentInfo xSDocumentInfo4 = xSDocumentInfo3;
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo4);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo4);
                throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[]{localName, "base"}, element2);
            }
            xSTypeDefinition = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo3, 7, qName, element2);
            if (xSTypeDefinition != null) {
                XSDocumentInfo xSDocumentInfo5 = xSDocumentInfo3;
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo5);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo5);
                throw new ComplexTypeRecoverableError();
            }
            this.fBaseType = xSTypeDefinition;
            if (xSTypeDefinition.getTypeCategory() == 15) {
                xSComplexTypeDecl2 = (XSComplexTypeDecl) xSTypeDefinition;
                s = xSComplexTypeDecl2.getFinal();
                if (xSComplexTypeDecl2.getContentType() != 1) {
                    xSSimpleType2 = (XSSimpleType) xSComplexTypeDecl2.getSimpleType();
                } else {
                    if (this.fDerivedBy == 2 || xSComplexTypeDecl2.getContentType() != 3 || !((XSParticleDecl) xSComplexTypeDecl2.getParticle()).emptiable()) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo3);
                        throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[]{this.fName, xSComplexTypeDecl2.getName()}, element2);
                    }
                    xSSimpleType2 = null;
                }
                xSComplexTypeDecl = xSComplexTypeDecl2;
            } else {
                xSSimpleType = (XSSimpleType) xSTypeDefinition;
                if (this.fDerivedBy != 2) {
                    XSDocumentInfo xSDocumentInfo6 = xSDocumentInfo3;
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo6);
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo6);
                    throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[]{this.fName, xSSimpleType.getName()}, element2);
                }
                s = xSSimpleType.getFinal();
                xSSimpleType2 = xSSimpleType;
                xSComplexTypeDecl = null;
            }
            if ((this.fDerivedBy & s) != 0) {
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo3);
                if (this.fDerivedBy == 1) {
                    str2 = "cos-ct-extends.1.1";
                } else {
                    str2 = "derivation-ok-restriction.1";
                }
                throw new ComplexTypeRecoverableError(str2, new Object[]{this.fName, this.fBaseType.getName()}, element2);
            }
            firstChildElement = DOMUtil.getFirstChildElement(element2);
            if (firstChildElement != null) {
                localName2 = DOMUtil.getLocalName(firstChildElement);
                str = SchemaSymbols.ELT_ANNOTATION;
                if (localName2.equals(str)) {
                    addAnnotation(traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo3));
                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                } else {
                    syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element2);
                    if (syntheticAnnotation2 != null) {
                        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation2 = traverseSyntheticAnnotation(element2, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo);
                        objArr3 = objArrCheckAttributes;
                        xSDocumentInfo3 = xSDocumentInfo;
                        addAnnotation(xSAnnotationImplTraverseSyntheticAnnotation2);
                    }
                    if (firstChildElement == null && DOMUtil.getLocalName(firstChildElement).equals(str)) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                        this.fAttrChecker.returnAttrArray(objArr3, xSDocumentInfo3);
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, str}, firstChildElement);
                    }
                    objArr2 = objArr3;
                }
                objArr3 = objArrCheckAttributes;
                if (firstChildElement == null) {
                }
                objArr2 = objArr3;
            } else {
                syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element2);
                if (syntheticAnnotation != null) {
                    objArr2 = objArrCheckAttributes;
                    xSDocumentInfo3 = xSDocumentInfo;
                    addAnnotation(traverseSyntheticAnnotation(element2, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo));
                } else {
                    objArr2 = objArrCheckAttributes;
                    xSDocumentInfo3 = xSDocumentInfo;
                }
            }
            nextSiblingElement2 = firstChildElement;
            if (this.fDerivedBy == 2) {
                if (nextSiblingElement2 == null && DOMUtil.getLocalName(nextSiblingElement2).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                    schemaGrammar2 = schemaGrammar;
                    XSSimpleType xSSimpleTypeTraverseLocal = this.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(nextSiblingElement2, xSDocumentInfo3, schemaGrammar2);
                    if (xSSimpleTypeTraverseLocal == null) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo3);
                        throw new ComplexTypeRecoverableError();
                    }
                    if (xSSimpleType2 != null && !XSConstraints.checkSimpleDerivationOk(xSSimpleTypeTraverseLocal, xSSimpleType2, xSSimpleType2.getFinal())) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo3);
                        throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.2.2.1", new Object[]{this.fName, xSSimpleTypeTraverseLocal.getName(), xSSimpleType2.getName()}, nextSiblingElement2);
                    }
                    nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                    xSSimpleType2 = xSSimpleTypeTraverseLocal;
                } else {
                    schemaGrammar2 = schemaGrammar;
                }
                if (xSSimpleType2 != null) {
                    XSDocumentInfo xSDocumentInfo7 = xSDocumentInfo3;
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo7);
                    this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo7);
                    throw new ComplexTypeRecoverableError("src-ct.2.2", new Object[]{this.fName}, nextSiblingElement2);
                }
                if (nextSiblingElement2 != null) {
                    XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets = traverseFacets(nextSiblingElement2, this.fComplexTypeDecl, xSSimpleType2, xSDocumentInfo3);
                    element3 = facetInfoTraverseFacets.nodeAfterFacets;
                    XSFacets xSFacets2 = facetInfoTraverseFacets.facetdata;
                    s3 = facetInfoTraverseFacets.fPresentFacets;
                    s2 = facetInfoTraverseFacets.fFixedFacets;
                    xSFacets = xSFacets2;
                } else {
                    element3 = null;
                    s2 = 0;
                }
                String strGenAnonTypeName = genAnonTypeName(element);
                XSSimpleType xSSimpleType4 = xSSimpleType2;
                this.fXSSimpleType = this.fSchemaHandler.fDVFactory.createTypeRestriction(strGenAnonTypeName, xSDocumentInfo3.fTargetNamespace, (short) 0, xSSimpleType4, null);
                try {
                    this.fValidationState.setNamespaceSupport(xSDocumentInfo3.fNamespaceSupport);
                    this.fXSSimpleType.applyFacets(xSFacets, s3, s2, this.fValidationState);
                } catch (InvalidDatatypeFacetException e) {
                    reportSchemaError(e.getKey(), e.getArgs(), nextSiblingElement2);
                    this.fXSSimpleType = this.fSchemaHandler.fDVFactory.createTypeRestriction(strGenAnonTypeName, xSDocumentInfo3.fTargetNamespace, (short) 0, xSSimpleType4, null);
                }
                xSSimpleType3 = this.fXSSimpleType;
                if (xSSimpleType3 instanceof XSSimpleTypeDecl) {
                    ((XSSimpleTypeDecl) xSSimpleType3).setAnonymous(true);
                }
                if (element3 != null) {
                    element4 = element3;
                    xSDocumentInfo2 = xSDocumentInfo3;
                } else {
                    if (isAttrOrAttrGroup(element3)) {
                        Element element5 = element3;
                        XSDocumentInfo xSDocumentInfo8 = xSDocumentInfo3;
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo8);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo8);
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(element5)}, element5);
                    }
                    Element element6 = element3;
                    elementTraverseAttrsAndAttrGrps2 = traverseAttrsAndAttrGrps(element6, this.fAttrGrp, xSDocumentInfo3, schemaGrammar2, this.fComplexTypeDecl);
                    element4 = element6;
                    xSDocumentInfo2 = xSDocumentInfo3;
                    if (elementTraverseAttrsAndAttrGrps2 != null) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps2)}, elementTraverseAttrsAndAttrGrps2);
                    }
                }
                try {
                    mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element);
                    this.fAttrGrp.removeProhibitedAttrs();
                    objArrValidRestrictionOf = this.fAttrGrp.validRestrictionOf(this.fName, xSComplexTypeDecl.getAttrGrp());
                    if (objArrValidRestrictionOf != null) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                        throw new ComplexTypeRecoverableError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element4);
                    }
                } catch (ComplexTypeRecoverableError e2) {
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                    this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                    throw e2;
                }
            } else {
                xSDocumentInfo2 = xSDocumentInfo3;
                this.fXSSimpleType = xSSimpleType2;
                if (nextSiblingElement2 != null) {
                    if (isAttrOrAttrGroup(nextSiblingElement2)) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement2)}, nextSiblingElement2);
                    }
                    elementTraverseAttrsAndAttrGrps = traverseAttrsAndAttrGrps(nextSiblingElement2, this.fAttrGrp, xSDocumentInfo2, schemaGrammar, this.fComplexTypeDecl);
                    if (elementTraverseAttrsAndAttrGrps == null) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps)}, elementTraverseAttrsAndAttrGrps);
                    }
                    this.fAttrGrp.removeProhibitedAttrs();
                }
                if (xSComplexTypeDecl != null) {
                    try {
                        mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element);
                    } catch (ComplexTypeRecoverableError e3) {
                        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                        throw e3;
                    }
                }
            }
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
            this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
        }
        addAnnotation(traverseAnnotationDecl(firstChildElement2, objArrCheckAttributes2, false, xSDocumentInfo3));
        firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
        objArr = objArrCheckAttributes2;
        element2 = firstChildElement2;
        if (element2 != null) {
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[]{this.fName, SchemaSymbols.ELT_SIMPLECONTENT}, element);
        }
        localName = DOMUtil.getLocalName(element2);
        if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = (short) 2;
        } else {
            if (localName.equals(SchemaSymbols.ELT_EXTENSION)) {
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, localName}, element2);
            }
            this.fDerivedBy = (short) 1;
        }
        nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
        xSAttributeChecker = this.fAttrChecker;
        if (nextSiblingElement == null) {
            xSAttributeChecker.returnAttrArray(objArr, xSDocumentInfo3);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
        }
        objArrCheckAttributes = xSAttributeChecker.checkAttributes(element2, false, xSDocumentInfo3);
        qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_BASE];
        if (qName != null) {
            XSDocumentInfo xSDocumentInfo9 = xSDocumentInfo3;
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo9);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo9);
            throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[]{localName, "base"}, element2);
        }
        xSTypeDefinition = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo3, 7, qName, element2);
        if (xSTypeDefinition != null) {
            XSDocumentInfo xSDocumentInfo10 = xSDocumentInfo3;
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo10);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo10);
            throw new ComplexTypeRecoverableError();
        }
        this.fBaseType = xSTypeDefinition;
        if (xSTypeDefinition.getTypeCategory() == 15) {
            xSComplexTypeDecl2 = (XSComplexTypeDecl) xSTypeDefinition;
            s = xSComplexTypeDecl2.getFinal();
            if (xSComplexTypeDecl2.getContentType() != 1) {
                if (this.fDerivedBy == 2) {
                }
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo3);
                throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[]{this.fName, xSComplexTypeDecl2.getName()}, element2);
            }
            xSSimpleType2 = (XSSimpleType) xSComplexTypeDecl2.getSimpleType();
            xSComplexTypeDecl = xSComplexTypeDecl2;
        } else {
            xSSimpleType = (XSSimpleType) xSTypeDefinition;
            if (this.fDerivedBy != 2) {
                XSDocumentInfo xSDocumentInfo11 = xSDocumentInfo3;
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo11);
                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo11);
                throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[]{this.fName, xSSimpleType.getName()}, element2);
            }
            s = xSSimpleType.getFinal();
            xSSimpleType2 = xSSimpleType;
            xSComplexTypeDecl = null;
        }
        if ((this.fDerivedBy & s) != 0) {
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo3);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo3);
            if (this.fDerivedBy == 1) {
                str2 = "cos-ct-extends.1.1";
            } else {
                str2 = "derivation-ok-restriction.1";
            }
            throw new ComplexTypeRecoverableError(str2, new Object[]{this.fName, this.fBaseType.getName()}, element2);
        }
        firstChildElement = DOMUtil.getFirstChildElement(element2);
        if (firstChildElement != null) {
            localName2 = DOMUtil.getLocalName(firstChildElement);
            str = SchemaSymbols.ELT_ANNOTATION;
            if (localName2.equals(str)) {
                addAnnotation(traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo3));
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            } else {
                syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element2);
                if (syntheticAnnotation2 != null) {
                    XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation3 = traverseSyntheticAnnotation(element2, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo);
                    objArr3 = objArrCheckAttributes;
                    xSDocumentInfo3 = xSDocumentInfo;
                    addAnnotation(xSAnnotationImplTraverseSyntheticAnnotation3);
                }
                if (firstChildElement == null) {
                }
                objArr2 = objArr3;
            }
            objArr3 = objArrCheckAttributes;
            if (firstChildElement == null) {
            }
            objArr2 = objArr3;
        } else {
            syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element2);
            if (syntheticAnnotation != null) {
                objArr2 = objArrCheckAttributes;
                xSDocumentInfo3 = xSDocumentInfo;
                addAnnotation(traverseSyntheticAnnotation(element2, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo));
            } else {
                objArr2 = objArrCheckAttributes;
                xSDocumentInfo3 = xSDocumentInfo;
            }
        }
        nextSiblingElement2 = firstChildElement;
        if (this.fDerivedBy == 2) {
            if (nextSiblingElement2 == null) {
                schemaGrammar2 = schemaGrammar;
            } else {
                schemaGrammar2 = schemaGrammar;
            }
            if (xSSimpleType2 != null) {
                XSDocumentInfo xSDocumentInfo12 = xSDocumentInfo3;
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo12);
                this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo12);
                throw new ComplexTypeRecoverableError("src-ct.2.2", new Object[]{this.fName}, nextSiblingElement2);
            }
            if (nextSiblingElement2 != null) {
                XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets2 = traverseFacets(nextSiblingElement2, this.fComplexTypeDecl, xSSimpleType2, xSDocumentInfo3);
                element3 = facetInfoTraverseFacets2.nodeAfterFacets;
                XSFacets xSFacets3 = facetInfoTraverseFacets2.facetdata;
                s3 = facetInfoTraverseFacets2.fPresentFacets;
                s2 = facetInfoTraverseFacets2.fFixedFacets;
                xSFacets = xSFacets3;
            } else {
                element3 = null;
                s2 = 0;
            }
            String strGenAnonTypeName2 = genAnonTypeName(element);
            XSSimpleType xSSimpleType5 = xSSimpleType2;
            this.fXSSimpleType = this.fSchemaHandler.fDVFactory.createTypeRestriction(strGenAnonTypeName2, xSDocumentInfo3.fTargetNamespace, (short) 0, xSSimpleType5, null);
            this.fValidationState.setNamespaceSupport(xSDocumentInfo3.fNamespaceSupport);
            this.fXSSimpleType.applyFacets(xSFacets, s3, s2, this.fValidationState);
            xSSimpleType3 = this.fXSSimpleType;
            if (xSSimpleType3 instanceof XSSimpleTypeDecl) {
                ((XSSimpleTypeDecl) xSSimpleType3).setAnonymous(true);
            }
            if (element3 != null) {
                element4 = element3;
                xSDocumentInfo2 = xSDocumentInfo3;
            } else {
                if (isAttrOrAttrGroup(element3)) {
                    Element element7 = element3;
                    XSDocumentInfo xSDocumentInfo13 = xSDocumentInfo3;
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo13);
                    this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo13);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(element7)}, element7);
                }
                Element element8 = element3;
                elementTraverseAttrsAndAttrGrps2 = traverseAttrsAndAttrGrps(element8, this.fAttrGrp, xSDocumentInfo3, schemaGrammar2, this.fComplexTypeDecl);
                element4 = element8;
                xSDocumentInfo2 = xSDocumentInfo3;
                if (elementTraverseAttrsAndAttrGrps2 != null) {
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                    this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps2)}, elementTraverseAttrsAndAttrGrps2);
                }
            }
            mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element);
            this.fAttrGrp.removeProhibitedAttrs();
            objArrValidRestrictionOf = this.fAttrGrp.validRestrictionOf(this.fName, xSComplexTypeDecl.getAttrGrp());
            if (objArrValidRestrictionOf != null) {
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                throw new ComplexTypeRecoverableError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element4);
            }
        } else {
            xSDocumentInfo2 = xSDocumentInfo3;
            this.fXSSimpleType = xSSimpleType2;
            if (nextSiblingElement2 != null) {
                if (isAttrOrAttrGroup(nextSiblingElement2)) {
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                    this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(nextSiblingElement2)}, nextSiblingElement2);
                }
                elementTraverseAttrsAndAttrGrps = traverseAttrsAndAttrGrps(nextSiblingElement2, this.fAttrGrp, xSDocumentInfo2, schemaGrammar, this.fComplexTypeDecl);
                if (elementTraverseAttrsAndAttrGrps == null) {
                    this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
                    this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps)}, elementTraverseAttrsAndAttrGrps);
                }
                this.fAttrGrp.removeProhibitedAttrs();
            }
            if (xSComplexTypeDecl != null) {
                mergeAttributes(xSComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element);
            }
        }
        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
        this.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
    }

    private void traverseSimpleContentDecl(Element element) {
    }

    public XSComplexTypeDecl traverseGlobal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, true, xSDocumentInfo);
        String str = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_NAME];
        contentBackup();
        XSComplexTypeDecl xSComplexTypeDeclTraverseComplexTypeDecl = traverseComplexTypeDecl(element, str, objArrCheckAttributes, xSDocumentInfo, schemaGrammar);
        contentRestore();
        schemaGrammar.addComplexTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl, this.fSchemaHandler.element2Locator(element));
        if (str == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_COMPLEXTYPE, SchemaSymbols.ATT_NAME}, element);
            xSComplexTypeDeclTraverseComplexTypeDecl = null;
        } else {
            if (schemaGrammar.getGlobalTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl.getName()) == null) {
                schemaGrammar.addGlobalComplexTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl);
            }
            String strSchemaDocument2SystemId = this.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
            XSTypeDefinition globalTypeDecl = schemaGrammar.getGlobalTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl.getName(), strSchemaDocument2SystemId);
            if (globalTypeDecl == null) {
                schemaGrammar.addGlobalComplexTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl, strSchemaDocument2SystemId);
            }
            XSDHandler xSDHandler = this.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (globalTypeDecl != null && (globalTypeDecl instanceof XSComplexTypeDecl)) {
                    xSComplexTypeDeclTraverseComplexTypeDecl = (XSComplexTypeDecl) globalTypeDecl;
                }
                xSDHandler.addGlobalTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl);
            }
        }
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return xSComplexTypeDeclTraverseComplexTypeDecl;
    }

    public XSComplexTypeDecl traverseLocal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        String strGenAnonTypeName = genAnonTypeName(element);
        contentBackup();
        XSComplexTypeDecl xSComplexTypeDeclTraverseComplexTypeDecl = traverseComplexTypeDecl(element, strGenAnonTypeName, objArrCheckAttributes, xSDocumentInfo, schemaGrammar);
        contentRestore();
        schemaGrammar.addComplexTypeDecl(xSComplexTypeDeclTraverseComplexTypeDecl, this.fSchemaHandler.element2Locator(element));
        xSComplexTypeDeclTraverseComplexTypeDecl.setIsAnonymous();
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return xSComplexTypeDeclTraverseComplexTypeDecl;
    }

    public static final class ComplexTypeRecoverableError extends Exception {
        private static final long serialVersionUID = 6802729912091130335L;
        Element errorElem;
        Object[] errorSubstText;

        public ComplexTypeRecoverableError() {
            this.errorSubstText = null;
            this.errorElem = null;
        }

        public ComplexTypeRecoverableError(String str, Object[] objArr, Element element) {
            super(str);
            this.errorSubstText = objArr;
            this.errorElem = element;
        }
    }
}
