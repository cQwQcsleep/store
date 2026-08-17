package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeFacetException;
import com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import java.util.ArrayList;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDSimpleTypeTraverser extends XSDAbstractTraverser {
    private boolean fIsBuiltIn;

    public XSDSimpleTypeTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
        this.fIsBuiltIn = false;
    }

    private final boolean checkBuiltIn(String str, String str2) {
        if (str2 != SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            return false;
        }
        if (SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(str) != null) {
            this.fIsBuiltIn = true;
        }
        return this.fIsBuiltIn;
    }

    private XSSimpleType errorType(String str, String str2, short s) {
        XSSimpleType xSSimpleType = (XSSimpleType) SchemaGrammar.SG_SchemaNS.getTypeDefinition("string");
        if (s == 2) {
            return this.fSchemaHandler.fDVFactory.createTypeRestriction(str, str2, (short) 0, xSSimpleType, null);
        }
        if (s == 8) {
            return this.fSchemaHandler.fDVFactory.createTypeUnion(str, str2, (short) 0, new XSSimpleType[]{xSSimpleType}, null);
        }
        if (s != 16) {
            return null;
        }
        return this.fSchemaHandler.fDVFactory.createTypeList(str, str2, (short) 0, xSSimpleType, null);
    }

    private XSSimpleType findDTValidator(Element element, String str, QName qName, short s, XSDocumentInfo xSDocumentInfo) {
        XSTypeDefinition xSTypeDefinition;
        if (qName == null || (xSTypeDefinition = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 7, qName, element)) == null) {
            return null;
        }
        if (xSTypeDefinition.getTypeCategory() != 16) {
            reportSchemaError("cos-st-restricts.1.1", new Object[]{qName.rawname, str}, element);
            return null;
        }
        if (xSTypeDefinition == SchemaGrammar.fAnySimpleType && s == 2) {
            if (checkBuiltIn(str, xSDocumentInfo.fTargetNamespace)) {
                return null;
            }
            reportSchemaError("cos-st-restricts.1.1", new Object[]{qName.rawname, str}, element);
            return null;
        }
        if ((xSTypeDefinition.getFinal() & s) == 0) {
            return (XSSimpleType) xSTypeDefinition;
        }
        if (s == 2) {
            reportSchemaError("st-props-correct.3", new Object[]{str, qName.rawname}, element);
        } else if (s == 16) {
            reportSchemaError("cos-st-restricts.2.3.1.1", new Object[]{qName.rawname, str}, element);
        } else if (s == 8) {
            reportSchemaError("cos-st-restricts.3.3.1.1", new Object[]{qName.rawname, str}, element);
        }
        return null;
    }

    private String genAnonTypeName(Element element) {
        StringBuffer stringBuffer = new StringBuffer("#AnonType_");
        for (Element parent = DOMUtil.getParent(element); parent != null && parent != DOMUtil.getRoot(DOMUtil.getDocument(parent)); parent = DOMUtil.getParent(parent)) {
            stringBuffer.append(parent.getAttribute(SchemaSymbols.ATT_NAME));
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Code duplicated, block: B:125:0x0261 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:126:0x0263 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:129:0x0268 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:131:0x026c  */
    /* JADX WARN: Code duplicated, block: B:133:0x0271  */
    /* JADX WARN: Code duplicated, block: B:142:0x028d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:143:0x028f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:146:0x029a  */
    /* JADX WARN: Code duplicated, block: B:147:0x029c  */
    /* JADX WARN: Code duplicated, block: B:151:0x02a5 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:157:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:162:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:164:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:165:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:168:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:170:0x0303  */
    /* JADX WARN: Code duplicated, block: B:172:0x030c  */
    /* JADX WARN: Code duplicated, block: B:174:0x0310  */
    /* JADX WARN: Code duplicated, block: B:176:0x031d  */
    /* JADX WARN: Code duplicated, block: B:178:0x0320  */
    /* JADX WARN: Code duplicated, block: B:180:0x0336  */
    /* JADX WARN: Code duplicated, block: B:182:0x033a  */
    /* JADX WARN: Code duplicated, block: B:184:0x0347  */
    /* JADX WARN: Code duplicated, block: B:186:0x034a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:197:0x038f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:198:0x0391  */
    /* JADX WARN: Code duplicated, block: B:199:0x03a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:200:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:201:0x03b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:202:0x03b5  */
    /* JADX WARN: Code duplicated, block: B:66:0x0154  */
    /* JADX WARN: Code duplicated, block: B:90:0x01d7  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v32, types: [com.sun.org.apache.xerces.internal.impl.dv.SchemaDVFactory] */
    /* JADX WARN: Type inference failed for: r11v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v23, types: [com.sun.org.apache.xerces.internal.xs.XSObjectList] */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v41 */
    /* JADX WARN: Type inference failed for: r5v42 */
    /* JADX WARN: Type inference failed for: r5v43 */
    /* JADX WARN: Type inference failed for: r5v44 */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDAbstractTraverser, com.sun.org.apache.xerces.internal.impl.xs.traversers.XSDSimpleTypeTraverser] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r7v17, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private XSSimpleType getSimpleType(String str, Element element, Object[] objArr, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Element element2;
        Element element3;
        XSAnnotationImpl[] xSAnnotationImplArr;
        boolean z;
        boolean z2;
        boolean z3;
        short s;
        XSSimpleType xSSimpleType;
        int i;
        ArrayList arrayList;
        Element element4;
        Element nextSiblingElement;
        Object[] objArr2;
        XSDSimpleTypeTraverser xSDSimpleTypeTraverser;
        XSSimpleType xSSimpleTypeFindDTValidator;
        QName qName;
        XSSimpleType xSSimpleTypeTraverseLocal;
        ?? r6;
        Element element5;
        QName qName2;
        Object obj;
        String str2;
        XSSimpleType xSSimpleType2;
        XSSimpleType xSSimpleType3;
        ?? r5;
        short s2;
        XSDocumentInfo xSDocumentInfo2;
        Element element6;
        XSSimpleType xSSimpleType4;
        XSSimpleType xSSimpleTypeCreateTypeUnion;
        XSObjectListImpl xSObjectListImpl;
        XSObjectListImpl xSObjectListImpl2;
        ?? xSObjectListImpl3;
        ?? arrayList2;
        ArrayList arrayList3;
        XInt xInt = (XInt) objArr[XSAttributeChecker.ATTIDX_FINAL];
        int iIntValue = xInt == null ? xSDocumentInfo.fFinalDefault : xInt.intValue();
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                element2 = element;
                XSAnnotationImpl[] xSAnnotationImplArr2 = {traverseSyntheticAnnotation(element2, syntheticAnnotation, objArr, false, xSDocumentInfo)};
                element3 = firstChildElement;
                xSAnnotationImplArr = xSAnnotationImplArr2;
            } else {
                element2 = element;
                element3 = firstChildElement;
                xSAnnotationImplArr = null;
            }
        } else {
            XSAnnotationImpl xSAnnotationImplTraverseAnnotationDecl = traverseAnnotationDecl(firstChildElement, objArr, false, xSDocumentInfo);
            XSAnnotationImpl[] xSAnnotationImplArr3 = xSAnnotationImplTraverseAnnotationDecl != null ? new XSAnnotationImpl[]{xSAnnotationImplTraverseAnnotationDecl} : null;
            Element nextSiblingElement2 = DOMUtil.getNextSiblingElement(firstChildElement);
            xSAnnotationImplArr = xSAnnotationImplArr3;
            element3 = nextSiblingElement2;
            element2 = element;
        }
        if (element3 == null) {
            reportSchemaError("s4s-elt-must-match.2", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))"}, element2);
            return errorType(str, xSDocumentInfo.fTargetNamespace, (short) 2);
        }
        String localName = DOMUtil.getLocalName(element3);
        if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            z2 = true;
            z3 = false;
            z = false;
            s = 2;
        } else if (localName.equals(SchemaSymbols.ELT_LIST)) {
            z3 = true;
            z2 = false;
            z = false;
            s = 16;
        } else {
            if (!localName.equals(SchemaSymbols.ELT_UNION)) {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", localName}, element2);
                return errorType(str, xSDocumentInfo.fTargetNamespace, (short) 2);
            }
            z = true;
            z2 = false;
            z3 = false;
            s = 8;
        }
        Element nextSiblingElement3 = DOMUtil.getNextSiblingElement(element3);
        if (nextSiblingElement3 != null) {
            xSSimpleType = null;
            reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", DOMUtil.getLocalName(nextSiblingElement3)}, nextSiblingElement3);
        } else {
            xSSimpleType = null;
        }
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element3, false, xSDocumentInfo);
        QName qName3 = (QName) objArrCheckAttributes[z2 ? XSAttributeChecker.ATTIDX_BASE : XSAttributeChecker.ATTIDX_ITEMTYPE];
        ArrayList arrayList4 = (ArrayList) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MEMBERTYPES];
        Element firstChildElement2 = DOMUtil.getFirstChildElement(element3);
        if (firstChildElement2 == null || !DOMUtil.getLocalName(firstChildElement2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            i = 0;
            arrayList = arrayList4;
            element4 = element3;
            String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element4);
            if (syntheticAnnotation2 != null) {
                nextSiblingElement = firstChildElement2;
                XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element4, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo);
                objArr2 = objArrCheckAttributes;
                xSAnnotationImplArr = xSAnnotationImplArr == null ? new XSAnnotationImpl[]{xSAnnotationImplTraverseSyntheticAnnotation} : new XSAnnotationImpl[]{xSAnnotationImplArr[0], xSAnnotationImplTraverseSyntheticAnnotation};
            }
            if ((!z2 || z3) && qName3 != null) {
                xSDSimpleTypeTraverser = this;
                xSSimpleTypeFindDTValidator = xSDSimpleTypeTraverser.findDTValidator(element4, str, qName3, s, xSDocumentInfo);
                qName = qName3;
                if (xSSimpleTypeFindDTValidator != null && xSDSimpleTypeTraverser.fIsBuiltIn) {
                    xSDSimpleTypeTraverser.fIsBuiltIn = i;
                    return xSSimpleType;
                }
                xSSimpleTypeTraverseLocal = xSSimpleTypeFindDTValidator;
            } else {
                xSDSimpleTypeTraverser = this;
                qName = qName3;
                xSSimpleTypeTraverseLocal = xSSimpleType;
            }
            short s3 = 3;
            if (z || arrayList == null || arrayList.size() <= 0) {
                r6 = xSDSimpleTypeTraverser;
                element5 = element4;
                qName2 = qName;
                obj = xSSimpleType;
            } else {
                int size = arrayList.size();
                ArrayList arrayList5 = new ArrayList(size);
                int i2 = i;
                while (i2 < size) {
                    ArrayList arrayList6 = arrayList3;
                    int i3 = size;
                    QName qName4 = qName;
                    short s4 = s3;
                    int i4 = i2;
                    XSSimpleType xSSimpleTypeFindDTValidator2 = xSDSimpleTypeTraverser.findDTValidator(element4, str, (QName) arrayList.get(i2), (short) 8, xSDocumentInfo);
                    XSDSimpleTypeTraverser xSDSimpleTypeTraverser2 = xSDSimpleTypeTraverser;
                    Element element7 = element4;
                    if (xSSimpleTypeFindDTValidator2 == null) {
                        arrayList3 = arrayList5;
                    } else if (xSSimpleTypeFindDTValidator2.getVariety() == s4) {
                        arrayList3 = arrayList5;
                        XSObjectList memberTypes = xSSimpleTypeFindDTValidator2.getMemberTypes();
                        for (int i5 = 0; i5 < memberTypes.getLength(); i5++) {
                            arrayList6.add(memberTypes.item(i5));
                        }
                    } else {
                        arrayList3 = arrayList5;
                        arrayList6.add(xSSimpleTypeFindDTValidator2);
                    }
                    element4 = element7;
                    xSDSimpleTypeTraverser = xSDSimpleTypeTraverser2;
                    arrayList3 = arrayList6;
                    qName = qName4;
                    s3 = 3;
                    i2 = i4 + 1;
                    size = i3;
                }
                arrayList3 = arrayList5;
                r6 = xSDSimpleTypeTraverser;
                element5 = element4;
                qName2 = qName;
                obj = arrayList3;
            }
            if (nextSiblingElement == null && DOMUtil.getLocalName(nextSiblingElement).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                if (z2 || z3) {
                    if (qName2 != null) {
                        r6.reportSchemaError(z3 ? "src-simple-type.3.a" : "src-simple-type.2.a", xSSimpleType, nextSiblingElement);
                    }
                    if (xSSimpleTypeTraverseLocal == null) {
                        xSSimpleTypeTraverseLocal = r6.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                    }
                    nextSiblingElement = DOMUtil.getNextSiblingElement(nextSiblingElement);
                    xSSimpleType3 = xSSimpleTypeTraverseLocal;
                    xSSimpleType2 = null;
                    r5 = obj;
                } else if (z) {
                    if (obj == null) {
                        arrayList2 = new ArrayList(2);
                    }
                    do {
                        XSSimpleType xSSimpleTypeTraverseLocal2 = r6.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                        if (xSSimpleTypeTraverseLocal2 != null) {
                            if (xSSimpleTypeTraverseLocal2.getVariety() == 3) {
                                XSObjectList memberTypes2 = xSSimpleTypeTraverseLocal2.getMemberTypes();
                                for (int i6 = 0; i6 < memberTypes2.getLength(); i6++) {
                                    arrayList2.add(memberTypes2.item(i6));
                                }
                            } else {
                                arrayList2.add(xSSimpleTypeTraverseLocal2);
                            }
                        }
                        nextSiblingElement = DOMUtil.getNextSiblingElement(nextSiblingElement);
                        if (nextSiblingElement == null) {
                            break;
                        }
                    } while (DOMUtil.getLocalName(nextSiblingElement).equals(SchemaSymbols.ELT_SIMPLETYPE));
                    xSSimpleType3 = xSSimpleTypeTraverseLocal;
                    xSSimpleType2 = xSSimpleType;
                    r5 = arrayList2;
                } else {
                    xSSimpleType2 = xSSimpleType;
                }
                if ((!z2 || z3) && xSSimpleType3 == null) {
                    r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
                    String str3 = xSDocumentInfo.fTargetNamespace;
                    if (z2) {
                        s2 = 2;
                    } else {
                        s2 = 16;
                    }
                    return r6.errorType(str, str3, s2);
                }
                if (!z && (r5 == 0 || r5.size() == 0)) {
                    r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
                    return r6.errorType(str, xSDocumentInfo.fTargetNamespace, (short) 8);
                }
                if (!z3 && r6.isListDatatype(xSSimpleType3)) {
                    r6.reportSchemaError("cos-st-restricts.2.1", new Object[]{str, xSSimpleType3.getName()}, element5);
                    r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
                    return r6.errorType(str, xSDocumentInfo.fTargetNamespace, (short) 16);
                }
                if (!z2) {
                    xSDocumentInfo2 = xSDocumentInfo;
                    element6 = element5;
                    if (z3) {
                        SchemaDVFactory schemaDVFactory = r6.fSchemaHandler.fDVFactory;
                        String str4 = xSDocumentInfo2.fTargetNamespace;
                        short s5 = (short) iIntValue;
                        if (xSAnnotationImplArr == null) {
                            xSObjectListImpl2 = null;
                        } else {
                            xSObjectListImpl2 = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                        }
                        xSSimpleTypeCreateTypeUnion = schemaDVFactory.createTypeList(str, str4, s5, xSSimpleType3, xSObjectListImpl2);
                    } else {
                        xSSimpleType4 = xSSimpleType3;
                        if (z) {
                            XSSimpleType[] xSSimpleTypeArr = (XSSimpleType[]) r5.toArray(new XSSimpleType[r5.size()]);
                            SchemaDVFactory schemaDVFactory2 = r6.fSchemaHandler.fDVFactory;
                            String str5 = xSDocumentInfo2.fTargetNamespace;
                            short s6 = (short) iIntValue;
                            if (xSAnnotationImplArr == null) {
                                xSObjectListImpl = null;
                            } else {
                                xSObjectListImpl = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                            }
                            xSSimpleTypeCreateTypeUnion = schemaDVFactory2.createTypeUnion(str, str5, s6, xSSimpleTypeArr, xSObjectListImpl);
                        } else {
                            xSSimpleTypeCreateTypeUnion = null;
                        }
                    }
                    if (z2 && nextSiblingElement != null) {
                        XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets = r6.traverseFacets(nextSiblingElement, xSSimpleTypeCreateTypeUnion, xSSimpleType4, xSDocumentInfo2);
                        nextSiblingElement = facetInfoTraverseFacets.nodeAfterFacets;
                        try {
                            r6.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
                            xSSimpleTypeCreateTypeUnion.applyFacets(facetInfoTraverseFacets.facetdata, facetInfoTraverseFacets.fPresentFacets, facetInfoTraverseFacets.fFixedFacets, r6.fValidationState);
                        } catch (InvalidDatatypeFacetException e) {
                            r6.reportSchemaError(e.getKey(), e.getArgs(), element6);
                            xSSimpleTypeCreateTypeUnion = r6.fSchemaHandler.fDVFactory.createTypeRestriction(str, xSDocumentInfo2.fTargetNamespace, (short) iIntValue, xSSimpleType4, xSAnnotationImplArr == null ? null : new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length));
                        }
                    }
                    if (nextSiblingElement != null) {
                        if (z2) {
                            r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                        } else if (z3) {
                            r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                        } else if (z) {
                            r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                        }
                    }
                    r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                    return xSSimpleTypeCreateTypeUnion;
                }
                ?? r0 = r6.fSchemaHandler.fDVFactory;
                xSDocumentInfo2 = xSDocumentInfo;
                String str6 = xSDocumentInfo2.fTargetNamespace;
                element6 = element5;
                short s7 = (short) iIntValue;
                if (xSAnnotationImplArr == null) {
                    xSObjectListImpl3 = xSSimpleType2;
                } else {
                    xSObjectListImpl3 = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                }
                xSSimpleTypeCreateTypeUnion = r0.createTypeRestriction(str, str6, s7, xSSimpleType3, xSObjectListImpl3);
                xSSimpleType4 = xSSimpleType3;
                if (z2) {
                    XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets2 = r6.traverseFacets(nextSiblingElement, xSSimpleTypeCreateTypeUnion, xSSimpleType4, xSDocumentInfo2);
                    nextSiblingElement = facetInfoTraverseFacets2.nodeAfterFacets;
                    r6.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
                    xSSimpleTypeCreateTypeUnion.applyFacets(facetInfoTraverseFacets2.facetdata, facetInfoTraverseFacets2.fPresentFacets, facetInfoTraverseFacets2.fFixedFacets, r6.fValidationState);
                }
                if (nextSiblingElement != null) {
                    if (z2) {
                        r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    } else if (z3) {
                        r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    } else if (z) {
                        r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    }
                }
                r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                return xSSimpleTypeCreateTypeUnion;
            }
            if ((!z2 || z3) && qName2 == null) {
                if (z3) {
                    str2 = "src-simple-type.3.b";
                } else {
                    str2 = "src-simple-type.2.b";
                }
                xSSimpleType2 = null;
                r6.reportSchemaError(str2, null, element5);
            } else {
                xSSimpleType2 = null;
                if (z && (arrayList == null || arrayList.size() == 0)) {
                    r6.reportSchemaError("src-union-memberTypes-or-simpleTypes", null, element5);
                }
            }
            xSSimpleType3 = xSSimpleTypeTraverseLocal;
            r5 = obj;
            if (z2) {
                r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
                String str7 = xSDocumentInfo.fTargetNamespace;
                if (z2) {
                    s2 = 2;
                } else {
                    s2 = 16;
                }
                return r6.errorType(str, str7, s2);
            }
            r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
            String str8 = xSDocumentInfo.fTargetNamespace;
            if (z2) {
                s2 = 2;
            } else {
                s2 = 16;
            }
            return r6.errorType(str, str8, s2);
            if (!z) {
            }
            if (!z3) {
            }
            if (!z2) {
                xSDocumentInfo2 = xSDocumentInfo;
                element6 = element5;
                if (z3) {
                    SchemaDVFactory schemaDVFactory3 = r6.fSchemaHandler.fDVFactory;
                    String str9 = xSDocumentInfo2.fTargetNamespace;
                    short s8 = (short) iIntValue;
                    if (xSAnnotationImplArr == null) {
                        xSObjectListImpl2 = null;
                    } else {
                        xSObjectListImpl2 = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                    }
                    xSSimpleTypeCreateTypeUnion = schemaDVFactory3.createTypeList(str, str9, s8, xSSimpleType3, xSObjectListImpl2);
                } else {
                    xSSimpleType4 = xSSimpleType3;
                    if (z) {
                        XSSimpleType[] xSSimpleTypeArr2 = (XSSimpleType[]) r5.toArray(new XSSimpleType[r5.size()]);
                        SchemaDVFactory schemaDVFactory4 = r6.fSchemaHandler.fDVFactory;
                        String str10 = xSDocumentInfo2.fTargetNamespace;
                        short s9 = (short) iIntValue;
                        if (xSAnnotationImplArr == null) {
                            xSObjectListImpl = null;
                        } else {
                            xSObjectListImpl = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                        }
                        xSSimpleTypeCreateTypeUnion = schemaDVFactory4.createTypeUnion(str, str10, s9, xSSimpleTypeArr2, xSObjectListImpl);
                    } else {
                        xSSimpleTypeCreateTypeUnion = null;
                    }
                }
                if (z2) {
                    XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets3 = r6.traverseFacets(nextSiblingElement, xSSimpleTypeCreateTypeUnion, xSSimpleType4, xSDocumentInfo2);
                    nextSiblingElement = facetInfoTraverseFacets3.nodeAfterFacets;
                    r6.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
                    xSSimpleTypeCreateTypeUnion.applyFacets(facetInfoTraverseFacets3.facetdata, facetInfoTraverseFacets3.fPresentFacets, facetInfoTraverseFacets3.fFixedFacets, r6.fValidationState);
                }
                if (nextSiblingElement != null) {
                    if (z2) {
                        r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    } else if (z3) {
                        r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    } else if (z) {
                        r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                    }
                }
                r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
                return xSSimpleTypeCreateTypeUnion;
            }
            ?? r1 = r6.fSchemaHandler.fDVFactory;
            xSDocumentInfo2 = xSDocumentInfo;
            String str11 = xSDocumentInfo2.fTargetNamespace;
            element6 = element5;
            short s10 = (short) iIntValue;
            if (xSAnnotationImplArr == null) {
                xSObjectListImpl3 = xSSimpleType2;
            } else {
                xSObjectListImpl3 = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
            }
            xSSimpleTypeCreateTypeUnion = r1.createTypeRestriction(str, str11, s10, xSSimpleType3, xSObjectListImpl3);
            xSSimpleType4 = xSSimpleType3;
            if (z2) {
                XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets4 = r6.traverseFacets(nextSiblingElement, xSSimpleTypeCreateTypeUnion, xSSimpleType4, xSDocumentInfo2);
                nextSiblingElement = facetInfoTraverseFacets4.nodeAfterFacets;
                r6.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
                xSSimpleTypeCreateTypeUnion.applyFacets(facetInfoTraverseFacets4.facetdata, facetInfoTraverseFacets4.fPresentFacets, facetInfoTraverseFacets4.fFixedFacets, r6.fValidationState);
            }
            if (nextSiblingElement != null) {
                if (z2) {
                    r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                } else if (z3) {
                    r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                } else if (z) {
                    r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                }
            }
            r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
            return xSSimpleTypeCreateTypeUnion;
        }
        XSAnnotationImpl xSAnnotationImplTraverseAnnotationDecl2 = traverseAnnotationDecl(firstChildElement2, objArrCheckAttributes, false, xSDocumentInfo);
        if (xSAnnotationImplTraverseAnnotationDecl2 == null) {
            i = 0;
        } else if (xSAnnotationImplArr == null) {
            xSAnnotationImplArr = new XSAnnotationImpl[]{xSAnnotationImplTraverseAnnotationDecl2};
            i = 0;
        } else {
            i = 0;
            xSAnnotationImplArr = new XSAnnotationImpl[]{xSAnnotationImplArr[0], xSAnnotationImplTraverseAnnotationDecl2};
        }
        firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
        arrayList = arrayList4;
        element4 = element3;
        objArr2 = objArrCheckAttributes;
        nextSiblingElement = firstChildElement2;
        if (z2) {
            xSDSimpleTypeTraverser = this;
            xSSimpleTypeFindDTValidator = xSDSimpleTypeTraverser.findDTValidator(element4, str, qName3, s, xSDocumentInfo);
            qName = qName3;
            if (xSSimpleTypeFindDTValidator != null) {
            }
            xSSimpleTypeTraverseLocal = xSSimpleTypeFindDTValidator;
        } else {
            xSDSimpleTypeTraverser = this;
            xSSimpleTypeFindDTValidator = xSDSimpleTypeTraverser.findDTValidator(element4, str, qName3, s, xSDocumentInfo);
            qName = qName3;
            if (xSSimpleTypeFindDTValidator != null) {
            }
            xSSimpleTypeTraverseLocal = xSSimpleTypeFindDTValidator;
        }
        short s11 = 3;
        if (z) {
            r6 = xSDSimpleTypeTraverser;
            element5 = element4;
            qName2 = qName;
            obj = xSSimpleType;
        } else {
            r6 = xSDSimpleTypeTraverser;
            element5 = element4;
            qName2 = qName;
            obj = xSSimpleType;
        }
        if (nextSiblingElement == null) {
            if (z2) {
                if (z3) {
                    str2 = "src-simple-type.3.b";
                } else {
                    str2 = "src-simple-type.2.b";
                }
                xSSimpleType2 = null;
                r6.reportSchemaError(str2, null, element5);
            } else {
                if (z3) {
                    str2 = "src-simple-type.3.b";
                } else {
                    str2 = "src-simple-type.2.b";
                }
                xSSimpleType2 = null;
                r6.reportSchemaError(str2, null, element5);
            }
            xSSimpleType3 = xSSimpleTypeTraverseLocal;
            r5 = obj;
        } else {
            if (z2) {
                if (z3) {
                    str2 = "src-simple-type.3.b";
                } else {
                    str2 = "src-simple-type.2.b";
                }
                xSSimpleType2 = null;
                r6.reportSchemaError(str2, null, element5);
            } else {
                if (z3) {
                    str2 = "src-simple-type.3.b";
                } else {
                    str2 = "src-simple-type.2.b";
                }
                xSSimpleType2 = null;
                r6.reportSchemaError(str2, null, element5);
            }
            xSSimpleType3 = xSSimpleTypeTraverseLocal;
            r5 = obj;
        }
        if (z2) {
            r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
            String str12 = xSDocumentInfo.fTargetNamespace;
            if (z2) {
                s2 = 2;
            } else {
                s2 = 16;
            }
            return r6.errorType(str, str12, s2);
        }
        r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo);
        String str13 = xSDocumentInfo.fTargetNamespace;
        if (z2) {
            s2 = 2;
        } else {
            s2 = 16;
        }
        return r6.errorType(str, str13, s2);
        if (!z) {
        }
        if (!z3) {
        }
        if (!z2) {
            xSDocumentInfo2 = xSDocumentInfo;
            element6 = element5;
            if (z3) {
                SchemaDVFactory schemaDVFactory5 = r6.fSchemaHandler.fDVFactory;
                String str14 = xSDocumentInfo2.fTargetNamespace;
                short s12 = (short) iIntValue;
                if (xSAnnotationImplArr == null) {
                    xSObjectListImpl2 = null;
                } else {
                    xSObjectListImpl2 = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                }
                xSSimpleTypeCreateTypeUnion = schemaDVFactory5.createTypeList(str, str14, s12, xSSimpleType3, xSObjectListImpl2);
            } else {
                xSSimpleType4 = xSSimpleType3;
                if (z) {
                    XSSimpleType[] xSSimpleTypeArr3 = (XSSimpleType[]) r5.toArray(new XSSimpleType[r5.size()]);
                    SchemaDVFactory schemaDVFactory6 = r6.fSchemaHandler.fDVFactory;
                    String str15 = xSDocumentInfo2.fTargetNamespace;
                    short s13 = (short) iIntValue;
                    if (xSAnnotationImplArr == null) {
                        xSObjectListImpl = null;
                    } else {
                        xSObjectListImpl = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
                    }
                    xSSimpleTypeCreateTypeUnion = schemaDVFactory6.createTypeUnion(str, str15, s13, xSSimpleTypeArr3, xSObjectListImpl);
                } else {
                    xSSimpleTypeCreateTypeUnion = null;
                }
            }
            if (z2) {
                XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets5 = r6.traverseFacets(nextSiblingElement, xSSimpleTypeCreateTypeUnion, xSSimpleType4, xSDocumentInfo2);
                nextSiblingElement = facetInfoTraverseFacets5.nodeAfterFacets;
                r6.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
                xSSimpleTypeCreateTypeUnion.applyFacets(facetInfoTraverseFacets5.facetdata, facetInfoTraverseFacets5.fPresentFacets, facetInfoTraverseFacets5.fFixedFacets, r6.fValidationState);
            }
            if (nextSiblingElement != null) {
                if (z2) {
                    r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                } else if (z3) {
                    r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                } else if (z) {
                    r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                }
            }
            r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
            return xSSimpleTypeCreateTypeUnion;
        }
        ?? r2 = r6.fSchemaHandler.fDVFactory;
        xSDocumentInfo2 = xSDocumentInfo;
        String str16 = xSDocumentInfo2.fTargetNamespace;
        element6 = element5;
        short s14 = (short) iIntValue;
        if (xSAnnotationImplArr == null) {
            xSObjectListImpl3 = xSSimpleType2;
        } else {
            xSObjectListImpl3 = new XSObjectListImpl(xSAnnotationImplArr, xSAnnotationImplArr.length);
        }
        xSSimpleTypeCreateTypeUnion = r2.createTypeRestriction(str, str16, s14, xSSimpleType3, xSObjectListImpl3);
        xSSimpleType4 = xSSimpleType3;
        if (z2) {
            XSDAbstractTraverser.FacetInfo facetInfoTraverseFacets6 = r6.traverseFacets(nextSiblingElement, xSSimpleTypeCreateTypeUnion, xSSimpleType4, xSDocumentInfo2);
            nextSiblingElement = facetInfoTraverseFacets6.nodeAfterFacets;
            r6.fValidationState.setNamespaceSupport(xSDocumentInfo2.fNamespaceSupport);
            xSSimpleTypeCreateTypeUnion.applyFacets(facetInfoTraverseFacets6.facetdata, facetInfoTraverseFacets6.fPresentFacets, facetInfoTraverseFacets6.fFixedFacets, r6.fValidationState);
        }
        if (nextSiblingElement != null) {
            if (z2) {
                r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
            } else if (z3) {
                r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
            } else if (z) {
                r6.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
            }
        }
        r6.fAttrChecker.returnAttrArray(objArr2, xSDocumentInfo2);
        return xSSimpleTypeCreateTypeUnion;
    }

    private boolean isListDatatype(XSSimpleType xSSimpleType) {
        if (xSSimpleType.getVariety() == 2) {
            return true;
        }
        if (xSSimpleType.getVariety() == 3) {
            XSObjectList memberTypes = xSSimpleType.getMemberTypes();
            for (int i = 0; i < memberTypes.getLength(); i++) {
                if (((XSSimpleType) memberTypes.item(i)).getVariety() == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private XSSimpleType traverseSimpleTypeDecl(Element element, Object[] objArr, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        return getSimpleType((String) objArr[XSAttributeChecker.ATTIDX_NAME], element, objArr, xSDocumentInfo, schemaGrammar);
    }

    public XSSimpleType traverseGlobal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, true, xSDocumentInfo);
        int i = XSAttributeChecker.ATTIDX_NAME;
        String str = (String) objArrCheckAttributes[i];
        if (str == null) {
            objArrCheckAttributes[i] = "(no name)";
        }
        XSSimpleType xSSimpleTypeTraverseSimpleTypeDecl = traverseSimpleTypeDecl(element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar);
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        if (str == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, SchemaSymbols.ATT_NAME}, element);
            xSSimpleTypeTraverseSimpleTypeDecl = null;
        }
        if (xSSimpleTypeTraverseSimpleTypeDecl != null) {
            if (schemaGrammar.getGlobalTypeDecl(xSSimpleTypeTraverseSimpleTypeDecl.getName()) == null) {
                schemaGrammar.addGlobalSimpleTypeDecl(xSSimpleTypeTraverseSimpleTypeDecl);
            }
            String strSchemaDocument2SystemId = this.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
            XSTypeDefinition globalTypeDecl = schemaGrammar.getGlobalTypeDecl(xSSimpleTypeTraverseSimpleTypeDecl.getName(), strSchemaDocument2SystemId);
            if (globalTypeDecl == null) {
                schemaGrammar.addGlobalSimpleTypeDecl(xSSimpleTypeTraverseSimpleTypeDecl, strSchemaDocument2SystemId);
            }
            XSDHandler xSDHandler = this.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (globalTypeDecl != null && (globalTypeDecl instanceof XSSimpleType)) {
                    xSSimpleTypeTraverseSimpleTypeDecl = (XSSimpleType) globalTypeDecl;
                }
                xSDHandler.addGlobalTypeDecl(xSSimpleTypeTraverseSimpleTypeDecl);
            }
        }
        return xSSimpleTypeTraverseSimpleTypeDecl;
    }

    public XSSimpleType traverseLocal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        XSSimpleType simpleType = getSimpleType(genAnonTypeName(element), element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar);
        if (simpleType instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) simpleType).setAnonymous(true);
        }
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return simpleType;
    }
}
