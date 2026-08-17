package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeUseImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDAttributeTraverser extends XSDAbstractTraverser {
    public XSDAttributeTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    public void checkDefaultValid(XSAttributeDecl xSAttributeDecl) throws InvalidDatatypeValueException {
        ((XSSimpleType) xSAttributeDecl.getTypeDefinition()).validate(xSAttributeDecl.getValInfo().normalizedValue, (ValidationContext) this.fValidationState, xSAttributeDecl.getValInfo());
        ((XSSimpleType) xSAttributeDecl.getTypeDefinition()).validate(xSAttributeDecl.getValInfo().stringValue(), (ValidationContext) this.fValidationState, xSAttributeDecl.getValInfo());
    }

    public XSAttributeDecl traverseGlobal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, true, xSDocumentInfo);
        XSAttributeDecl xSAttributeDeclTraverseNamedAttr = traverseNamedAttr(element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar, true, null);
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return xSAttributeDeclTraverseNamedAttr;
    }

    public XSAttributeUseImpl traverseLocal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, XSComplexTypeDecl xSComplexTypeDecl) {
        Element element2;
        XSDocumentInfo xSDocumentInfo2;
        String str;
        XSAttributeDecl xSAttributeDeclTraverseNamedAttr;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        String str2;
        short s;
        XSAttributeUseImpl attributeUse;
        ValidatedInfo validatedInfo;
        short s2;
        XSObjectListImpl xSObjectListImpl;
        Object[] objArr;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        String str3 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_DEFAULT];
        String str4 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_FIXED];
        String str5 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_NAME];
        QName qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_REF];
        XInt xInt = (XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_USE];
        String str6 = SchemaSymbols.ATT_REF;
        if (element.getAttributeNode(str6) == null) {
            element2 = element;
            xSDocumentInfo2 = xSDocumentInfo;
            str = str5;
            xSAttributeDeclTraverseNamedAttr = traverseNamedAttr(element2, objArrCheckAttributes, xSDocumentInfo2, schemaGrammar, false, xSComplexTypeDecl);
            xSAnnotationImplTraverseSyntheticAnnotation = null;
        } else if (qName != null) {
            xSAttributeDeclTraverseNamedAttr = (XSAttributeDecl) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 1, qName, element);
            Element firstChildElement = DOMUtil.getFirstChildElement(element);
            if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                objArr = objArrCheckAttributes;
                String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                xSAnnotationImplTraverseSyntheticAnnotation = syntheticAnnotation != null ? traverseSyntheticAnnotation(element, syntheticAnnotation, objArr, false, xSDocumentInfo) : null;
            } else {
                xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                objArr = objArrCheckAttributes;
            }
            if (firstChildElement != null) {
                reportSchemaError("src-attribute.3.2", new Object[]{qName.rawname}, firstChildElement);
            }
            str = qName.localpart;
            objArrCheckAttributes = objArr;
            element2 = element;
            xSDocumentInfo2 = xSDocumentInfo;
        } else {
            element2 = element;
            xSDocumentInfo2 = xSDocumentInfo;
            str = str5;
            xSAnnotationImplTraverseSyntheticAnnotation = null;
            xSAttributeDeclTraverseNamedAttr = null;
        }
        if (str3 != null) {
            str4 = str3;
            str2 = str4;
            s = 1;
        } else if (str4 != null) {
            str2 = null;
            s = 2;
        } else {
            str4 = str3;
            str2 = str4;
            s = 0;
        }
        if (xSAttributeDeclTraverseNamedAttr != null) {
            XSDeclarationPool xSDeclarationPool = this.fSchemaHandler.fDeclPool;
            attributeUse = xSDeclarationPool != null ? xSDeclarationPool.getAttributeUse() : new XSAttributeUseImpl();
            attributeUse.fAttrDecl = xSAttributeDeclTraverseNamedAttr;
            attributeUse.fUse = xInt.shortValue();
            attributeUse.fConstraintType = s;
            if (str4 != null) {
                ValidatedInfo validatedInfo2 = new ValidatedInfo();
                attributeUse.fDefault = validatedInfo2;
                validatedInfo2.normalizedValue = str4;
            }
            if (element2.getAttributeNode(str6) == null) {
                attributeUse.fAnnotations = xSAttributeDeclTraverseNamedAttr.getAnnotations();
            } else {
                if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
                    xSObjectListImpl = new XSObjectListImpl();
                    xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
                } else {
                    xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
                }
                attributeUse.fAnnotations = xSObjectListImpl;
            }
        } else {
            attributeUse = null;
        }
        if (str4 != null && str2 != null) {
            reportSchemaError("src-attribute.1", new Object[]{str}, element2);
        }
        if (s == 1 && xInt != null && xInt.intValue() != 0) {
            reportSchemaError("src-attribute.2", new Object[]{str}, element2);
            attributeUse.fUse = (short) 0;
        }
        if (str4 != null && attributeUse != null) {
            this.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
            try {
                checkDefaultValid(attributeUse);
                validatedInfo = null;
            } catch (InvalidDatatypeValueException e) {
                reportSchemaError(e.getKey(), e.getArgs(), element2);
                reportSchemaError("a-props-correct.2", new Object[]{str, str4}, element2);
                validatedInfo = null;
                attributeUse.fDefault = null;
                attributeUse.fConstraintType = (short) 0;
            }
            if (((XSSimpleType) xSAttributeDeclTraverseNamedAttr.getTypeDefinition()).isIDType()) {
                reportSchemaError("a-props-correct.3", new Object[]{str}, element2);
                attributeUse.fDefault = validatedInfo;
                attributeUse.fConstraintType = (short) 0;
            }
            if (attributeUse.fAttrDecl.getConstraintType() == 2 && (s2 = attributeUse.fConstraintType) != 0 && (s2 != 2 || !attributeUse.fAttrDecl.getValInfo().actualValue.equals(attributeUse.fDefault.actualValue))) {
                reportSchemaError("au-props-correct.2", new Object[]{str, attributeUse.fAttrDecl.getValInfo().stringValue()}, element2);
                attributeUse.fDefault = attributeUse.fAttrDecl.getValInfo();
                attributeUse.fConstraintType = (short) 2;
            }
        }
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo2);
        return attributeUse;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0070  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a0  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDHandler] */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl] */
    /* JADX WARN: Type inference failed for: r10v1, types: [com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl] */
    /* JADX WARN: Type inference failed for: r10v2, types: [com.sun.org.apache.xerces.internal.impl.xs.XSAttributeDecl] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType, com.sun.org.apache.xerces.internal.xs.XSTypeDefinition] */
    /* JADX WARN: Type inference failed for: r22v0, types: [com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDAbstractTraverser, com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDAttributeTraverser] */
    /* JADX WARN: Type inference failed for: r26v0, types: [com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public XSAttributeDecl traverseNamedAttr(Element element, Object[] objArr, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, boolean z, XSComplexTypeDecl xSComplexTypeDecl) {
        char c;
        XSComplexTypeDecl xSComplexTypeDecl2;
        String str;
        XSComplexTypeDecl xSComplexTypeDecl3;
        char c2;
        String str2;
        boolean z2;
        XSAnnotationImpl xSAnnotationImpl;
        short s;
        ValidatedInfo validatedInfo;
        Element element2;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        Element nextSiblingElement;
        XSObject xSObjectTraverseLocal;
        boolean z3;
        String str3;
        XSObjectListImpl xSObjectListImpl;
        ValidatedInfo validatedInfo2;
        XSTypeDefinition xSTypeDefinition;
        String str4 = (String) objArr[XSAttributeChecker.ATTIDX_DEFAULT];
        String str5 = (String) objArr[XSAttributeChecker.ATTIDX_FIXED];
        XInt xInt = (XInt) objArr[XSAttributeChecker.ATTIDX_FORM];
        String strAddSymbol = (String) objArr[XSAttributeChecker.ATTIDX_NAME];
        QName qName = (QName) objArr[XSAttributeChecker.ATTIDX_TYPE];
        XSDeclarationPool xSDeclarationPool = this.fSchemaHandler.fDeclPool;
        ?? attributeDecl = xSDeclarationPool != null ? xSDeclarationPool.getAttributeDecl() : new XSAttributeDecl();
        if (strAddSymbol != null) {
            strAddSymbol = this.fSymbolTable.addSymbol(strAddSymbol);
        }
        String str6 = strAddSymbol;
        if (z) {
            str2 = xSDocumentInfo.fTargetNamespace;
            c2 = 1;
            xSComplexTypeDecl3 = null;
        } else {
            if (xSComplexTypeDecl != null) {
                xSComplexTypeDecl2 = xSComplexTypeDecl;
                c = 2;
            } else {
                c = 0;
                xSComplexTypeDecl2 = null;
            }
            if (xInt != null) {
                if (xInt.intValue() == 1) {
                    str = xSDocumentInfo.fTargetNamespace;
                    xSComplexTypeDecl3 = xSComplexTypeDecl2;
                    c2 = c;
                    str2 = str;
                } else {
                    xSComplexTypeDecl3 = xSComplexTypeDecl2;
                    c2 = c;
                    str2 = null;
                }
            } else if (xSDocumentInfo.fAreLocalAttributesQualified) {
                str = xSDocumentInfo.fTargetNamespace;
                xSComplexTypeDecl3 = xSComplexTypeDecl2;
                c2 = c;
                str2 = str;
            } else {
                xSComplexTypeDecl3 = xSComplexTypeDecl2;
                c2 = c;
                str2 = null;
            }
        }
        if (!z) {
            z2 = true;
            xSAnnotationImpl = null;
            s = 0;
            validatedInfo = null;
        } else if (str5 != null) {
            ValidatedInfo validatedInfo3 = new ValidatedInfo();
            validatedInfo3.normalizedValue = str5;
            z2 = true;
            xSAnnotationImpl = null;
            validatedInfo = validatedInfo3;
            s = 2;
        } else if (str4 != null) {
            ValidatedInfo validatedInfo4 = new ValidatedInfo();
            validatedInfo4.normalizedValue = str4;
            z2 = true;
            xSAnnotationImpl = null;
            s = 1;
            validatedInfo = validatedInfo4;
        } else {
            z2 = true;
            xSAnnotationImpl = null;
            s = 0;
            validatedInfo = null;
        }
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                element2 = element;
                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element2, syntheticAnnotation, objArr, false, xSDocumentInfo);
            } else {
                element2 = element;
                xSAnnotationImplTraverseSyntheticAnnotation = xSAnnotationImpl;
            }
            nextSiblingElement = firstChildElement;
        } else {
            xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArr, false, xSDocumentInfo);
            nextSiblingElement = DOMUtil.getNextSiblingElement(firstChildElement);
            element2 = element;
        }
        if (nextSiblingElement == null || !DOMUtil.getLocalName(nextSiblingElement).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            xSObjectTraverseLocal = xSAnnotationImpl;
            z3 = false;
        } else {
            xSObjectTraverseLocal = this.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar);
            nextSiblingElement = DOMUtil.getNextSiblingElement(nextSiblingElement);
            z3 = z2;
        }
        if (xSObjectTraverseLocal != null || qName == null) {
            str3 = str4;
        } else {
            str3 = str4;
            XSTypeDefinition xSTypeDefinition2 = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 7, qName, element2);
            if (xSTypeDefinition2 != null) {
                xSTypeDefinition = xSTypeDefinition2;
                if (xSTypeDefinition2.getTypeCategory() == 16) {
                    xSObjectTraverseLocal = (XSSimpleType) xSTypeDefinition;
                }
            } else {
                xSTypeDefinition = xSTypeDefinition2;
            }
            reportSchemaError("src-resolve", new Object[]{qName.rawname, "simpleType definition"}, element2);
            if (xSTypeDefinition == null) {
                attributeDecl.fUnresolvedTypeName = qName;
            }
        }
        if (xSObjectTraverseLocal == null) {
            xSObjectTraverseLocal = SchemaGrammar.fAnySimpleType;
        }
        ?? r13 = xSObjectTraverseLocal;
        if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        XSObjectListImpl xSObjectListImpl2 = xSObjectListImpl;
        ?? r4 = xSAnnotationImpl;
        attributeDecl.setValues(str6, str2, r13, s, c2, validatedInfo, xSComplexTypeDecl3, xSObjectListImpl2);
        ValidatedInfo validatedInfo5 = validatedInfo;
        if (str6 == null) {
            if (z) {
                reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_ATTRIBUTE, SchemaSymbols.ATT_NAME}, element2);
            } else {
                reportSchemaError("src-attribute.3.1", r4, element2);
            }
            str6 = "(no name)";
        }
        if (nextSiblingElement != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{str6, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
        }
        if (str3 != null && str5 != null) {
            reportSchemaError("src-attribute.1", new Object[]{str6}, element2);
        }
        if (z3 && qName != null) {
            reportSchemaError("src-attribute.4", new Object[]{str6}, element2);
        }
        checkNotationType(str6, r13, element2);
        if (validatedInfo5 != null) {
            this.fValidationState.setNamespaceSupport(xSDocumentInfo.fNamespaceSupport);
            try {
                checkDefaultValid(attributeDecl);
                validatedInfo2 = validatedInfo5;
            } catch (InvalidDatatypeValueException e) {
                reportSchemaError(e.getKey(), e.getArgs(), element2);
                reportSchemaError("a-props-correct.2", new Object[]{str6, validatedInfo5.normalizedValue}, element2);
                validatedInfo2 = null;
                attributeDecl.setValues(str6, str2, r13, (short) 0, c2, null, xSComplexTypeDecl3, xSObjectListImpl2);
            }
        } else {
            validatedInfo2 = validatedInfo5;
        }
        if (validatedInfo2 != null && r13.isIDType()) {
            reportSchemaError("a-props-correct.3", new Object[]{str6}, element2);
            attributeDecl.setValues(str6, str2, r13, (short) 0, c2, null, xSComplexTypeDecl3, xSObjectListImpl2);
        }
        if (str6.equals(XMLSymbols.PREFIX_XMLNS)) {
            reportSchemaError("no-xmlns", null, element2);
            return null;
        }
        if (str2 != 0) {
            String str7 = SchemaSymbols.URI_XSI;
            if (str2.equals(str7)) {
                reportSchemaError("no-xsi", new Object[]{str7}, element2);
                return null;
            }
        }
        ?? r10 = attributeDecl;
        if (str6.equals("(no name)")) {
            return null;
        }
        if (z) {
            if (schemaGrammar.getGlobalAttributeDecl(str6) == null) {
                schemaGrammar.addGlobalAttributeDecl(attributeDecl);
            }
            String strSchemaDocument2SystemId = this.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
            XSAttributeDecl globalAttributeDecl = schemaGrammar.getGlobalAttributeDecl(str6, strSchemaDocument2SystemId);
            if (globalAttributeDecl == null) {
                schemaGrammar.addGlobalAttributeDecl(attributeDecl, strSchemaDocument2SystemId);
            }
            ?? r0 = this.fSchemaHandler;
            ?? r11 = attributeDecl;
            r10 = attributeDecl;
            if (r0.fTolerateDuplicates) {
                if (globalAttributeDecl != null) {
                    r11 = globalAttributeDecl;
                }
                r0.addGlobalAttributeDecl(r11);
                r10 = r11;
            }
        }
        return r10;
    }

    public void checkDefaultValid(XSAttributeUseImpl xSAttributeUseImpl) throws InvalidDatatypeValueException {
        XSSimpleType xSSimpleType = (XSSimpleType) xSAttributeUseImpl.fAttrDecl.getTypeDefinition();
        ValidatedInfo validatedInfo = xSAttributeUseImpl.fDefault;
        xSSimpleType.validate(validatedInfo.normalizedValue, (ValidationContext) this.fValidationState, validatedInfo);
        ((XSSimpleType) xSAttributeUseImpl.fAttrDecl.getTypeDefinition()).validate(xSAttributeUseImpl.fDefault.stringValue(), (ValidationContext) this.fValidationState, xSAttributeUseImpl.fDefault);
    }
}
