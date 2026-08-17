package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import java.util.Locale;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDElementTraverser extends XSDAbstractTraverser {
    boolean fDeferTraversingLocalElements;
    protected final XSElementDecl fTempElementDecl;

    public XSDElementTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
        this.fTempElementDecl = new XSElementDecl();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDAbstractTraverser
    public void reset(SymbolTable symbolTable, boolean z, Locale locale) {
        super.reset(symbolTable, z, locale);
        this.fDeferTraversingLocalElements = true;
    }

    public XSElementDecl traverseGlobal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, true, xSDocumentInfo);
        XSElementDecl xSElementDeclTraverseNamedElement = traverseNamedElement(element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar, true, null);
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return xSElementDeclTraverseNamedElement;
    }

    public void traverseLocal(XSParticleDecl xSParticleDecl, Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, int i, XSObject xSObject, String[] strArr) {
        Object[] objArr;
        XSDocumentInfo xSDocumentInfo2;
        XSAnnotationImpl xSAnnotationImpl;
        XSObjectListImpl xSObjectListImpl;
        Object[] objArr2;
        if (strArr != null) {
            xSDocumentInfo.fNamespaceSupport.setEffectiveContext(strArr);
        }
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        QName qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_REF];
        XInt xInt = (XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt xInt2 = (XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSElementDecl xSElementDeclTraverseNamedElement = null;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation = null;
        if (element.getAttributeNode(SchemaSymbols.ATT_REF) == null) {
            objArr = objArrCheckAttributes;
            xSDocumentInfo2 = xSDocumentInfo;
            xSAnnotationImpl = null;
            xSElementDeclTraverseNamedElement = traverseNamedElement(element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar, false, xSObject);
        } else if (qName != null) {
            XSElementDecl xSElementDecl = (XSElementDecl) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 3, qName, element);
            Element firstChildElement = DOMUtil.getFirstChildElement(element);
            if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                objArr2 = objArrCheckAttributes;
                String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation != null) {
                    xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element, syntheticAnnotation, objArr2, false, xSDocumentInfo);
                }
            } else {
                xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                objArr2 = objArrCheckAttributes;
            }
            if (firstChildElement != null) {
                reportSchemaError("src-element.2.2", new Object[]{qName.rawname, DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
            }
            xSDocumentInfo2 = xSDocumentInfo;
            xSAnnotationImpl = xSAnnotationImplTraverseSyntheticAnnotation;
            xSElementDeclTraverseNamedElement = xSElementDecl;
            objArr = objArr2;
        } else {
            xSDocumentInfo2 = xSDocumentInfo;
            objArr = objArrCheckAttributes;
            xSAnnotationImpl = null;
        }
        xSParticleDecl.fMinOccurs = xInt.intValue();
        xSParticleDecl.fMaxOccurs = xInt2.intValue();
        if (xSElementDeclTraverseNamedElement != null) {
            xSParticleDecl.fType = (short) 1;
            xSParticleDecl.fValue = xSElementDeclTraverseNamedElement;
        } else {
            xSParticleDecl.fType = (short) 0;
        }
        if (qName != null) {
            if (xSAnnotationImpl != null) {
                xSObjectListImpl = new XSObjectListImpl();
                xSObjectListImpl.addXSObject(xSAnnotationImpl);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            xSParticleDecl.fAnnotations = xSObjectListImpl;
        } else {
            xSParticleDecl.fAnnotations = xSElementDeclTraverseNamedElement != null ? xSElementDeclTraverseNamedElement.fAnnotations : XSObjectListImpl.EMPTY_LIST;
        }
        checkOccurrences(xSParticleDecl, SchemaSymbols.ELT_ELEMENT, (Element) element.getParentNode(), i, ((Long) objArr[XSAttributeChecker.ATTIDX_FROMDEFAULT]).longValue());
        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo2);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:105:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:107:0x0218  */
    /* JADX WARN: Code duplicated, block: B:109:0x0222  */
    /* JADX WARN: Code duplicated, block: B:110:0x0237  */
    /* JADX WARN: Code duplicated, block: B:114:0x027f  */
    /* JADX WARN: Code duplicated, block: B:116:0x0290  */
    /* JADX WARN: Code duplicated, block: B:118:0x029d  */
    /* JADX WARN: Code duplicated, block: B:120:0x02aa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:121:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:122:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:125:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:134:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:136:0x030d  */
    /* JADX WARN: Code duplicated, block: B:155:0x038e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:156:0x038f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:157:0x0391  */
    /* JADX WARN: Code duplicated, block: B:159:0x039c  */
    /* JADX WARN: Code duplicated, block: B:162:0x03ad  */
    /* JADX WARN: Code duplicated, block: B:165:0x03b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:167:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:26:0x009d  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:35:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:39:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:46:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:48:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:49:0x0109  */
    /* JADX WARN: Code duplicated, block: B:51:0x010f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0135  */
    /* JADX WARN: Code duplicated, block: B:59:0x013c  */
    /* JADX WARN: Code duplicated, block: B:60:0x0142  */
    /* JADX WARN: Code duplicated, block: B:62:0x0145  */
    /* JADX WARN: Code duplicated, block: B:63:0x014e  */
    /* JADX WARN: Code duplicated, block: B:66:0x0154  */
    /* JADX WARN: Code duplicated, block: B:68:0x0160  */
    /* JADX WARN: Code duplicated, block: B:69:0x016d  */
    /* JADX WARN: Code duplicated, block: B:71:0x0175  */
    /* JADX WARN: Code duplicated, block: B:72:0x0182  */
    /* JADX WARN: Code duplicated, block: B:83:0x019f  */
    /* JADX WARN: Code duplicated, block: B:86:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:88:0x01ab  */
    /* JADX WARN: Instruction removed from duplicated block: B:109:0x0222, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:110:0x0237, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11, types: [com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo, com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v13 */
    public XSElementDecl traverseNamedElement(Element element, Object[] objArr, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, boolean z, XSObject xSObject) {
        short sShortValue;
        short sShortValue2;
        boolean z2;
        Element firstChildElement;
        String syntheticAnnotation;
        XSElementDecl xSElementDecl;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        XSObjectListImpl xSObjectListImpl;
        XSTypeDefinition xSTypeDefinitionTraverseLocal;
        XSDElementTraverser xSDElementTraverser;
        XSDocumentInfo xSDocumentInfo2;
        String str;
        String str2;
        boolean z3;
        ?? r11;
        Element element2;
        XSTypeDefinition xSTypeDefinition;
        XSElementDecl xSElementDecl2;
        String strSchemaDocument2SystemId;
        XSElementDecl globalElementDecl;
        XSDHandler xSDHandler;
        XSTypeDefinition xSTypeDefinition2;
        ValidatedInfo validatedInfo;
        String localName;
        String str3;
        String str4;
        String str5;
        Element element3;
        XSElementDecl xSElementDecl3;
        String localName2;
        short s;
        XSDElementTraverser xSDElementTraverser2 = this;
        Element element4 = element;
        XSDocumentInfo xSDocumentInfo3 = xSDocumentInfo;
        Boolean bool = (Boolean) objArr[XSAttributeChecker.ATTIDX_ABSTRACT];
        XInt xInt = (XInt) objArr[XSAttributeChecker.ATTIDX_BLOCK];
        String str6 = (String) objArr[XSAttributeChecker.ATTIDX_DEFAULT];
        XInt xInt2 = (XInt) objArr[XSAttributeChecker.ATTIDX_FINAL];
        String str7 = (String) objArr[XSAttributeChecker.ATTIDX_FIXED];
        XInt xInt3 = (XInt) objArr[XSAttributeChecker.ATTIDX_FORM];
        String str8 = (String) objArr[XSAttributeChecker.ATTIDX_NAME];
        Boolean bool2 = (Boolean) objArr[XSAttributeChecker.ATTIDX_NILLABLE];
        QName qName = (QName) objArr[XSAttributeChecker.ATTIDX_SUBSGROUP];
        QName qName2 = (QName) objArr[XSAttributeChecker.ATTIDX_TYPE];
        XSDeclarationPool xSDeclarationPool = xSDElementTraverser2.fSchemaHandler.fDeclPool;
        XSElementDecl elementDecl = xSDeclarationPool != null ? xSDeclarationPool.getElementDecl() : new XSElementDecl();
        if (str8 != null) {
            elementDecl.fName = xSDElementTraverser2.fSymbolTable.addSymbol(str8);
        }
        if (!z) {
            if (xSObject instanceof XSComplexTypeDecl) {
                elementDecl.setIsLocal((XSComplexTypeDecl) xSObject);
            }
            if (xInt3 != null) {
                if (xInt3.intValue() == 1) {
                    elementDecl.fTargetNamespace = xSDocumentInfo3.fTargetNamespace;
                } else {
                    elementDecl.fTargetNamespace = null;
                }
            } else if (xSDocumentInfo3.fAreLocalElementsQualified) {
                elementDecl.fTargetNamespace = xSDocumentInfo3.fTargetNamespace;
            } else {
                elementDecl.fTargetNamespace = null;
            }
            if (xInt == null) {
                s = xSDocumentInfo3.fBlockDefault;
                elementDecl.fBlock = s;
                if (s != 31) {
                    elementDecl.fBlock = (short) (s & 7);
                }
            } else {
                sShortValue = xInt.shortValue();
                elementDecl.fBlock = sShortValue;
                if (sShortValue != 31 && (sShortValue | 7) != 7) {
                    xSDElementTraverser2.reportSchemaError("s4s-att-invalid-value", new Object[]{elementDecl.fName, "block", "must be (#all | List of (extension | restriction | substitution))"}, element4);
                }
            }
            if (xInt2 == null) {
                sShortValue2 = xSDocumentInfo3.fFinalDefault;
            } else {
                sShortValue2 = xInt2.shortValue();
            }
            elementDecl.fFinal = (short) (sShortValue2 & 3);
            if (bool2.booleanValue()) {
                elementDecl.setIsNillable();
            }
            if (bool != null && bool.booleanValue()) {
                elementDecl.setIsAbstract();
            }
            if (str7 != null) {
                ValidatedInfo validatedInfo2 = new ValidatedInfo();
                elementDecl.fDefault = validatedInfo2;
                validatedInfo2.normalizedValue = str7;
                elementDecl.setConstraintType((short) 2);
                z2 = true;
            } else if (str6 != null) {
                ValidatedInfo validatedInfo3 = new ValidatedInfo();
                elementDecl.fDefault = validatedInfo3;
                validatedInfo3.normalizedValue = str6;
                z2 = true;
                elementDecl.setConstraintType((short) 1);
            } else {
                z2 = true;
                elementDecl.setConstraintType((short) 0);
            }
            if (qName != null) {
                elementDecl.fSubGroup = (XSElementDecl) xSDElementTraverser2.fSchemaHandler.getGlobalDecl(xSDocumentInfo3, 3, qName, element4);
            }
            firstChildElement = DOMUtil.getFirstChildElement(element4);
            if (firstChildElement == null && DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                xSAnnotationImplTraverseSyntheticAnnotation = xSDElementTraverser2.traverseAnnotationDecl(firstChildElement, objArr, false, xSDocumentInfo3);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                xSElementDecl = elementDecl;
            } else {
                syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element4);
                xSElementDecl = elementDecl;
                if (syntheticAnnotation != null) {
                    xSAnnotationImplTraverseSyntheticAnnotation = xSDElementTraverser2.traverseSyntheticAnnotation(element4, syntheticAnnotation, objArr, false, xSDocumentInfo3);
                } else {
                    xSAnnotationImplTraverseSyntheticAnnotation = null;
                }
            }
            if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
                xSObjectListImpl = new XSObjectListImpl();
                xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            xSElementDecl.fAnnotations = xSObjectListImpl;
            if (firstChildElement != null) {
                localName2 = DOMUtil.getLocalName(firstChildElement);
                if (localName2.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                    xSTypeDefinitionTraverseLocal = xSDElementTraverser2.fSchemaHandler.fComplexTypeTraverser.traverseLocal(firstChildElement, xSDocumentInfo3, schemaGrammar);
                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                } else if (localName2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                    xSTypeDefinitionTraverseLocal = xSDElementTraverser2.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(firstChildElement, xSDocumentInfo3, schemaGrammar);
                    firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                } else {
                    z2 = false;
                    xSTypeDefinitionTraverseLocal = null;
                }
            } else {
                z2 = false;
                xSTypeDefinitionTraverseLocal = null;
            }
            if (xSTypeDefinitionTraverseLocal == null && qName2 != null && (xSTypeDefinitionTraverseLocal = (XSTypeDefinition) xSDElementTraverser2.fSchemaHandler.getGlobalDecl(xSDocumentInfo3, 7, qName2, element4)) == null) {
                xSElementDecl.fUnresolvedTypeName = qName2;
            }
            if (xSTypeDefinitionTraverseLocal == null && (xSElementDecl3 = xSElementDecl.fSubGroup) != null) {
                xSTypeDefinitionTraverseLocal = xSElementDecl3.fType;
            }
            if (xSTypeDefinitionTraverseLocal == null) {
                xSTypeDefinitionTraverseLocal = SchemaGrammar.fAnyType;
            }
            xSElementDecl.fType = xSTypeDefinitionTraverseLocal;
            if (firstChildElement != null) {
                localName = DOMUtil.getLocalName(firstChildElement);
                while (firstChildElement != null) {
                    str3 = SchemaSymbols.ELT_KEY;
                    if (localName.equals(str3) && !localName.equals(SchemaSymbols.ELT_KEYREF) && !localName.equals(SchemaSymbols.ELT_UNIQUE)) {
                        break;
                    }
                    if (!localName.equals(str3) || localName.equals(SchemaSymbols.ELT_UNIQUE)) {
                        DOMUtil.setHidden(firstChildElement, xSDElementTraverser2.fSchemaHandler.fHiddenNodes);
                        xSDElementTraverser2.fSchemaHandler.fUniqueOrKeyTraverser.traverse(firstChildElement, xSElementDecl, xSDocumentInfo3, schemaGrammar);
                        str4 = SchemaSymbols.ATT_NAME;
                        if (DOMUtil.getAttrValue(firstChildElement, str4).length() != 0) {
                            XSDHandler xSDHandler2 = xSDElementTraverser2.fSchemaHandler;
                            if (xSDocumentInfo3.fTargetNamespace == null) {
                                str5 = "," + DOMUtil.getAttrValue(firstChildElement, str4);
                            } else {
                                str5 = xSDocumentInfo3.fTargetNamespace + "," + DOMUtil.getAttrValue(firstChildElement, str4);
                            }
                            Element element5 = firstChildElement;
                            XSDocumentInfo xSDocumentInfo4 = xSDocumentInfo3;
                            element3 = element5;
                            element4 = element;
                            xSDHandler2.checkForDuplicateNames(str5, 1, xSDElementTraverser2.fSchemaHandler.getIDRegistry(), xSDElementTraverser2.fSchemaHandler.getIDRegistry_sub(), element3, xSDocumentInfo4);
                            xSDocumentInfo3 = xSDocumentInfo4;
                        }
                        firstChildElement = DOMUtil.getNextSiblingElement(element3);
                        if (firstChildElement != null) {
                            localName = DOMUtil.getLocalName(firstChildElement);
                        } else {
                            localName = localName;
                        }
                        str7 = str7;
                    } else if (localName.equals(SchemaSymbols.ELT_KEYREF)) {
                        xSDElementTraverser2.fSchemaHandler.storeKeyRef(firstChildElement, xSDocumentInfo3, xSElementDecl);
                    }
                    localName = localName;
                    xSDocumentInfo3 = xSDocumentInfo3;
                    element3 = firstChildElement;
                    element4 = element4;
                    firstChildElement = DOMUtil.getNextSiblingElement(element3);
                    if (firstChildElement != null) {
                        localName = DOMUtil.getLocalName(firstChildElement);
                    } else {
                        localName = localName;
                    }
                    str7 = str7;
                }
                xSDElementTraverser = xSDElementTraverser2;
                xSDocumentInfo2 = xSDocumentInfo3;
                str = str6;
                str2 = str7;
                z3 = z2;
                r11 = 0;
                element2 = element4;
                xSTypeDefinition = xSTypeDefinitionTraverseLocal;
                firstChildElement = firstChildElement;
            } else {
                xSDElementTraverser = xSDElementTraverser2;
                xSDocumentInfo2 = xSDocumentInfo3;
                str = str6;
                str2 = str7;
                z3 = z2;
                r11 = 0;
                element2 = element4;
                xSTypeDefinition = xSTypeDefinitionTraverseLocal;
            }
            if (str8 == null) {
                if (z) {
                    xSDElementTraverser.reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_ELEMENT, SchemaSymbols.ATT_NAME}, element2);
                } else {
                    xSDElementTraverser.reportSchemaError("src-element.2.1", r11, element2);
                }
                str8 = "(no name)";
            }
            if (firstChildElement != null) {
                xSDElementTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{str8, "(annotation?, (simpleType | complexType)?, (unique | key | keyref)*))", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
            }
            if (str != null && str2 != null) {
                xSDElementTraverser.reportSchemaError("src-element.1", new Object[]{str8}, element2);
            }
            if (z3 && qName2 != null) {
                xSDElementTraverser.reportSchemaError("src-element.3", new Object[]{str8}, element2);
            }
            xSDElementTraverser.checkNotationType(str8, xSTypeDefinition, element2);
            if (xSElementDecl.fDefault != null) {
                xSDElementTraverser.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
                xSTypeDefinition2 = xSElementDecl.fType;
                validatedInfo = xSElementDecl.fDefault;
                if (XSConstraints.ElementDefaultValidImmediate(xSTypeDefinition2, validatedInfo.normalizedValue, xSDElementTraverser.fValidationState, validatedInfo) == null) {
                    xSDElementTraverser.reportSchemaError("e-props-correct.2", new Object[]{str8, xSElementDecl.fDefault.normalizedValue}, element2);
                    xSElementDecl.fDefault = r11;
                    xSElementDecl.setConstraintType((short) 0);
                }
            }
            xSElementDecl2 = xSElementDecl.fSubGroup;
            if (xSElementDecl2 != null && !XSConstraints.checkTypeDerivationOk(xSElementDecl.fType, xSElementDecl2.fType, xSElementDecl2.fFinal)) {
                xSDElementTraverser.reportSchemaError("e-props-correct.4", new Object[]{str8, qName.prefix + ":" + qName.localpart}, element2);
                xSElementDecl.fSubGroup = r11;
            }
            if (xSElementDecl.fDefault != null && ((xSTypeDefinition.getTypeCategory() == 16 && ((XSSimpleType) xSTypeDefinition).isIDType()) || (xSTypeDefinition.getTypeCategory() == 15 && ((XSComplexTypeDecl) xSTypeDefinition).containsTypeID()))) {
                xSDElementTraverser.reportSchemaError("e-props-correct.5", new Object[]{xSElementDecl.fName}, element2);
                xSElementDecl.fDefault = r11;
                xSElementDecl.setConstraintType((short) 0);
            }
            if (xSElementDecl.fName == null) {
                return r11;
            }
            if (z) {
                schemaGrammar.addGlobalElementDeclAll(xSElementDecl);
                if (schemaGrammar.getGlobalElementDecl(xSElementDecl.fName) == null) {
                    schemaGrammar.addGlobalElementDecl(xSElementDecl);
                }
                strSchemaDocument2SystemId = xSDElementTraverser.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo2);
                globalElementDecl = schemaGrammar.getGlobalElementDecl(xSElementDecl.fName, strSchemaDocument2SystemId);
                if (globalElementDecl == null) {
                    schemaGrammar.addGlobalElementDecl(xSElementDecl, strSchemaDocument2SystemId);
                }
                xSDHandler = xSDElementTraverser.fSchemaHandler;
                if (xSDHandler.fTolerateDuplicates) {
                    if (globalElementDecl == null) {
                        globalElementDecl = xSElementDecl;
                    }
                    xSDHandler.addGlobalElementDecl(globalElementDecl);
                    return globalElementDecl;
                }
            }
            return xSElementDecl;
        }
        elementDecl.fTargetNamespace = xSDocumentInfo3.fTargetNamespace;
        elementDecl.setIsGlobal();
        if (xInt == null) {
            s = xSDocumentInfo3.fBlockDefault;
            elementDecl.fBlock = s;
            if (s != 31) {
                elementDecl.fBlock = (short) (s & 7);
            }
        } else {
            sShortValue = xInt.shortValue();
            elementDecl.fBlock = sShortValue;
            if (sShortValue != 31) {
                xSDElementTraverser2.reportSchemaError("s4s-att-invalid-value", new Object[]{elementDecl.fName, "block", "must be (#all | List of (extension | restriction | substitution))"}, element4);
            }
        }
        if (xInt2 == null) {
            sShortValue2 = xSDocumentInfo3.fFinalDefault;
        } else {
            sShortValue2 = xInt2.shortValue();
        }
        elementDecl.fFinal = (short) (sShortValue2 & 3);
        if (bool2.booleanValue()) {
            elementDecl.setIsNillable();
        }
        if (bool != null) {
            elementDecl.setIsAbstract();
        }
        if (str7 != null) {
            ValidatedInfo validatedInfo4 = new ValidatedInfo();
            elementDecl.fDefault = validatedInfo4;
            validatedInfo4.normalizedValue = str7;
            elementDecl.setConstraintType((short) 2);
            z2 = true;
        } else if (str6 != null) {
            ValidatedInfo validatedInfo5 = new ValidatedInfo();
            elementDecl.fDefault = validatedInfo5;
            validatedInfo5.normalizedValue = str6;
            z2 = true;
            elementDecl.setConstraintType((short) 1);
        } else {
            z2 = true;
            elementDecl.setConstraintType((short) 0);
        }
        if (qName != null) {
            elementDecl.fSubGroup = (XSElementDecl) xSDElementTraverser2.fSchemaHandler.getGlobalDecl(xSDocumentInfo3, 3, qName, element4);
        }
        firstChildElement = DOMUtil.getFirstChildElement(element4);
        if (firstChildElement == null) {
            syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element4);
            xSElementDecl = elementDecl;
            if (syntheticAnnotation != null) {
                xSAnnotationImplTraverseSyntheticAnnotation = xSDElementTraverser2.traverseSyntheticAnnotation(element4, syntheticAnnotation, objArr, false, xSDocumentInfo3);
            } else {
                xSAnnotationImplTraverseSyntheticAnnotation = null;
            }
        } else {
            syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element4);
            xSElementDecl = elementDecl;
            if (syntheticAnnotation != null) {
                xSAnnotationImplTraverseSyntheticAnnotation = xSDElementTraverser2.traverseSyntheticAnnotation(element4, syntheticAnnotation, objArr, false, xSDocumentInfo3);
            } else {
                xSAnnotationImplTraverseSyntheticAnnotation = null;
            }
        }
        if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        xSElementDecl.fAnnotations = xSObjectListImpl;
        if (firstChildElement != null) {
            localName2 = DOMUtil.getLocalName(firstChildElement);
            if (localName2.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                xSTypeDefinitionTraverseLocal = xSDElementTraverser2.fSchemaHandler.fComplexTypeTraverser.traverseLocal(firstChildElement, xSDocumentInfo3, schemaGrammar);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            } else if (localName2.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                xSTypeDefinitionTraverseLocal = xSDElementTraverser2.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(firstChildElement, xSDocumentInfo3, schemaGrammar);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            } else {
                z2 = false;
                xSTypeDefinitionTraverseLocal = null;
            }
        } else {
            z2 = false;
            xSTypeDefinitionTraverseLocal = null;
        }
        if (xSTypeDefinitionTraverseLocal == null) {
            xSElementDecl.fUnresolvedTypeName = qName2;
        }
        if (xSTypeDefinitionTraverseLocal == null) {
            xSTypeDefinitionTraverseLocal = xSElementDecl3.fType;
        }
        if (xSTypeDefinitionTraverseLocal == null) {
            xSTypeDefinitionTraverseLocal = SchemaGrammar.fAnyType;
        }
        xSElementDecl.fType = xSTypeDefinitionTraverseLocal;
        if (firstChildElement != null) {
            localName = DOMUtil.getLocalName(firstChildElement);
            while (firstChildElement != null) {
                str3 = SchemaSymbols.ELT_KEY;
                if (localName.equals(str3)) {
                }
                if (localName.equals(str3)) {
                    DOMUtil.setHidden(firstChildElement, xSDElementTraverser2.fSchemaHandler.fHiddenNodes);
                    xSDElementTraverser2.fSchemaHandler.fUniqueOrKeyTraverser.traverse(firstChildElement, xSElementDecl, xSDocumentInfo3, schemaGrammar);
                    str4 = SchemaSymbols.ATT_NAME;
                    if (DOMUtil.getAttrValue(firstChildElement, str4).length() != 0) {
                        XSDHandler xSDHandler3 = xSDElementTraverser2.fSchemaHandler;
                        if (xSDocumentInfo3.fTargetNamespace == null) {
                            str5 = "," + DOMUtil.getAttrValue(firstChildElement, str4);
                        } else {
                            str5 = xSDocumentInfo3.fTargetNamespace + "," + DOMUtil.getAttrValue(firstChildElement, str4);
                        }
                        Element element6 = firstChildElement;
                        XSDocumentInfo xSDocumentInfo5 = xSDocumentInfo3;
                        element3 = element6;
                        element4 = element;
                        xSDHandler3.checkForDuplicateNames(str5, 1, xSDElementTraverser2.fSchemaHandler.getIDRegistry(), xSDElementTraverser2.fSchemaHandler.getIDRegistry_sub(), element3, xSDocumentInfo5);
                        xSDocumentInfo3 = xSDocumentInfo5;
                    } else {
                        localName = localName;
                        xSDocumentInfo3 = xSDocumentInfo3;
                        element3 = firstChildElement;
                        element4 = element4;
                    }
                } else {
                    DOMUtil.setHidden(firstChildElement, xSDElementTraverser2.fSchemaHandler.fHiddenNodes);
                    xSDElementTraverser2.fSchemaHandler.fUniqueOrKeyTraverser.traverse(firstChildElement, xSElementDecl, xSDocumentInfo3, schemaGrammar);
                    str4 = SchemaSymbols.ATT_NAME;
                    if (DOMUtil.getAttrValue(firstChildElement, str4).length() != 0) {
                        XSDHandler xSDHandler4 = xSDElementTraverser2.fSchemaHandler;
                        if (xSDocumentInfo3.fTargetNamespace == null) {
                            str5 = "," + DOMUtil.getAttrValue(firstChildElement, str4);
                        } else {
                            str5 = xSDocumentInfo3.fTargetNamespace + "," + DOMUtil.getAttrValue(firstChildElement, str4);
                        }
                        Element element7 = firstChildElement;
                        XSDocumentInfo xSDocumentInfo6 = xSDocumentInfo3;
                        element3 = element7;
                        element4 = element;
                        xSDHandler4.checkForDuplicateNames(str5, 1, xSDElementTraverser2.fSchemaHandler.getIDRegistry(), xSDElementTraverser2.fSchemaHandler.getIDRegistry_sub(), element3, xSDocumentInfo6);
                        xSDocumentInfo3 = xSDocumentInfo6;
                    } else {
                        localName = localName;
                        xSDocumentInfo3 = xSDocumentInfo3;
                        element3 = firstChildElement;
                        element4 = element4;
                    }
                }
                firstChildElement = DOMUtil.getNextSiblingElement(element3);
                if (firstChildElement != null) {
                    localName = DOMUtil.getLocalName(firstChildElement);
                } else {
                    localName = localName;
                }
                str7 = str7;
            }
            xSDElementTraverser = xSDElementTraverser2;
            xSDocumentInfo2 = xSDocumentInfo3;
            str = str6;
            str2 = str7;
            z3 = z2;
            r11 = 0;
            element2 = element4;
            xSTypeDefinition = xSTypeDefinitionTraverseLocal;
            firstChildElement = firstChildElement;
        } else {
            xSDElementTraverser = xSDElementTraverser2;
            xSDocumentInfo2 = xSDocumentInfo3;
            str = str6;
            str2 = str7;
            z3 = z2;
            r11 = 0;
            element2 = element4;
            xSTypeDefinition = xSTypeDefinitionTraverseLocal;
        }
        if (str8 == null) {
            if (z) {
                xSDElementTraverser.reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_ELEMENT, SchemaSymbols.ATT_NAME}, element2);
            } else {
                xSDElementTraverser.reportSchemaError("src-element.2.1", r11, element2);
            }
            str8 = "(no name)";
        }
        if (firstChildElement != null) {
            xSDElementTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{str8, "(annotation?, (simpleType | complexType)?, (unique | key | keyref)*))", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
        }
        if (str != null) {
            xSDElementTraverser.reportSchemaError("src-element.1", new Object[]{str8}, element2);
        }
        if (z3) {
            xSDElementTraverser.reportSchemaError("src-element.3", new Object[]{str8}, element2);
        }
        xSDElementTraverser.checkNotationType(str8, xSTypeDefinition, element2);
        if (xSElementDecl.fDefault != null) {
            xSDElementTraverser.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
            xSTypeDefinition2 = xSElementDecl.fType;
            validatedInfo = xSElementDecl.fDefault;
            if (XSConstraints.ElementDefaultValidImmediate(xSTypeDefinition2, validatedInfo.normalizedValue, xSDElementTraverser.fValidationState, validatedInfo) == null) {
                xSDElementTraverser.reportSchemaError("e-props-correct.2", new Object[]{str8, xSElementDecl.fDefault.normalizedValue}, element2);
                xSElementDecl.fDefault = r11;
                xSElementDecl.setConstraintType((short) 0);
            }
        }
        xSElementDecl2 = xSElementDecl.fSubGroup;
        if (xSElementDecl2 != null) {
            xSDElementTraverser.reportSchemaError("e-props-correct.4", new Object[]{str8, qName.prefix + ":" + qName.localpart}, element2);
            xSElementDecl.fSubGroup = r11;
        }
        if (xSElementDecl.fDefault != null) {
            xSDElementTraverser.reportSchemaError("e-props-correct.5", new Object[]{xSElementDecl.fName}, element2);
            xSElementDecl.fDefault = r11;
            xSElementDecl.setConstraintType((short) 0);
        }
        if (xSElementDecl.fName == null) {
            return r11;
        }
        if (z) {
            schemaGrammar.addGlobalElementDeclAll(xSElementDecl);
            if (schemaGrammar.getGlobalElementDecl(xSElementDecl.fName) == null) {
                schemaGrammar.addGlobalElementDecl(xSElementDecl);
            }
            strSchemaDocument2SystemId = xSDElementTraverser.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo2);
            globalElementDecl = schemaGrammar.getGlobalElementDecl(xSElementDecl.fName, strSchemaDocument2SystemId);
            if (globalElementDecl == null) {
                schemaGrammar.addGlobalElementDecl(xSElementDecl, strSchemaDocument2SystemId);
            }
            xSDHandler = xSDElementTraverser.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (globalElementDecl == null) {
                    globalElementDecl = xSElementDecl;
                }
                xSDHandler.addGlobalElementDecl(globalElementDecl);
                return globalElementDecl;
            }
        }
        return xSElementDecl;
    }

    public XSParticleDecl traverseLocal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, int i, XSObject xSObject) {
        XSParticleDecl xSParticleDecl;
        XSDeclarationPool xSDeclarationPool = this.fSchemaHandler.fDeclPool;
        if (xSDeclarationPool != null) {
            xSParticleDecl = xSDeclarationPool.getParticleDecl();
        } else {
            xSParticleDecl = new XSParticleDecl();
        }
        XSParticleDecl xSParticleDecl2 = xSParticleDecl;
        if (this.fDeferTraversingLocalElements) {
            xSParticleDecl2.fType = (short) 1;
            Attr attributeNode = element.getAttributeNode(SchemaSymbols.ATT_MINOCCURS);
            if (attributeNode != null) {
                try {
                    int i2 = Integer.parseInt(XMLChar.trim(attributeNode.getValue()));
                    if (i2 >= 0) {
                        xSParticleDecl2.fMinOccurs = i2;
                    }
                } catch (NumberFormatException unused) {
                }
            }
            this.fSchemaHandler.fillInLocalElemInfo(element, xSDocumentInfo, i, xSObject, xSParticleDecl2);
            return xSParticleDecl2;
        }
        traverseLocal(xSParticleDecl2, element, xSDocumentInfo, schemaGrammar, i, xSObject, null);
        if (xSParticleDecl2.fType == 0) {
            return null;
        }
        return xSParticleDecl2;
    }
}
