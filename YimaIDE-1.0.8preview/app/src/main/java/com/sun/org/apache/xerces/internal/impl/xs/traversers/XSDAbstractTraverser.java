package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xerces.internal.impl.dv.xs.XSSimpleTypeDecl;
import com.sun.org.apache.xerces.internal.impl.validation.ValidationState;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeUseImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSComplexTypeDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSWildcardDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.NamespaceSupport;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.XSAttributeUse;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xml.internal.serializer.SerializerConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class XSDAbstractTraverser {
    protected static final int CHILD_OF_GROUP = 4;
    protected static final int GROUP_REF_WITH_ALL = 2;
    protected static final int NOT_ALL_CONTEXT = 0;
    protected static final String NO_NAME = "(no name)";
    protected static final int PROCESSING_ALL_EL = 1;
    protected static final int PROCESSING_ALL_GP = 8;
    private static final XSSimpleType fQNameDV = (XSSimpleType) SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME);
    protected XSAttributeChecker fAttrChecker;
    protected XSDHandler fSchemaHandler;
    protected SymbolTable fSymbolTable = null;
    protected boolean fValidateAnnotations = false;
    ValidationState fValidationState = new ValidationState();
    private StringBuilder fPattern = new StringBuilder();
    private final XSFacets xsFacets = new XSFacets();

    public static final class FacetInfo {
        final short fFixedFacets;
        final short fPresentFacets;
        final XSFacets facetdata;
        final Element nodeAfterFacets;

        public FacetInfo(XSFacets xSFacets, Element element, short s, short s2) {
            this.facetdata = xSFacets;
            this.nodeAfterFacets = element;
            this.fPresentFacets = s;
            this.fFixedFacets = s2;
        }
    }

    public XSDAbstractTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        this.fSchemaHandler = null;
        this.fAttrChecker = null;
        this.fSchemaHandler = xSDHandler;
        this.fAttrChecker = xSAttributeChecker;
    }

    private void checkEnumerationAndLengthInconsistency(XSSimpleType xSSimpleType, List<String> list, Element element, String str) {
        String str2 = SchemaSymbols.URI_SCHEMAFORSCHEMA;
        int i = 0;
        if (str2.equals(xSSimpleType.getNamespace()) && SchemaSymbols.ATTVAL_HEXBINARY.equals(xSSimpleType.getName())) {
            while (i < list.size()) {
                String str3 = list.get(i);
                if (str3.length() / 2 != this.xsFacets.length) {
                    reportSchemaWarning("FacetsContradict", new Object[]{str3, SchemaSymbols.ELT_LENGTH, str}, element);
                }
                i++;
            }
            return;
        }
        if (!str2.equals(xSSimpleType.getNamespace()) || !SchemaSymbols.ATTVAL_BASE64BINARY.equals(xSSimpleType.getName())) {
            while (i < list.size()) {
                String str4 = list.get(i);
                if (str4.length() != this.xsFacets.length) {
                    reportSchemaWarning("FacetsContradict", new Object[]{str4, SchemaSymbols.ELT_LENGTH, str}, element);
                }
                i++;
            }
            return;
        }
        while (i < list.size()) {
            String str5 = list.get(i);
            byte[] bArrDecode = Base64.decode(str5);
            if (bArrDecode != null && new String(bArrDecode).length() != this.xsFacets.length) {
                reportSchemaWarning("FacetsContradict", new Object[]{str5, SchemaSymbols.ELT_LENGTH, str}, element);
            }
            i++;
        }
    }

    private void checkEnumerationAndMaxLengthInconsistency(XSSimpleType xSSimpleType, List<String> list, Element element, String str) {
        String str2 = SchemaSymbols.URI_SCHEMAFORSCHEMA;
        int i = 0;
        if (str2.equals(xSSimpleType.getNamespace()) && SchemaSymbols.ATTVAL_HEXBINARY.equals(xSSimpleType.getName())) {
            while (i < list.size()) {
                String str3 = list.get(i);
                if (str3.length() / 2 > this.xsFacets.maxLength) {
                    reportSchemaWarning("FacetsContradict", new Object[]{str3, SchemaSymbols.ELT_MAXLENGTH, str}, element);
                }
                i++;
            }
            return;
        }
        if (!str2.equals(xSSimpleType.getNamespace()) || !SchemaSymbols.ATTVAL_BASE64BINARY.equals(xSSimpleType.getName())) {
            while (i < list.size()) {
                String str4 = list.get(i);
                if (str4.length() > this.xsFacets.maxLength) {
                    reportSchemaWarning("FacetsContradict", new Object[]{str4, SchemaSymbols.ELT_MAXLENGTH, str}, element);
                }
                i++;
            }
            return;
        }
        while (i < list.size()) {
            String str5 = list.get(i);
            byte[] bArrDecode = Base64.decode(str5);
            if (bArrDecode != null && new String(bArrDecode).length() > this.xsFacets.maxLength) {
                reportSchemaWarning("FacetsContradict", new Object[]{str5, SchemaSymbols.ELT_MAXLENGTH, str}, element);
            }
            i++;
        }
    }

    private void checkEnumerationAndMinLengthInconsistency(XSSimpleType xSSimpleType, List<String> list, Element element, String str) {
        String str2 = SchemaSymbols.URI_SCHEMAFORSCHEMA;
        int i = 0;
        if (str2.equals(xSSimpleType.getNamespace()) && SchemaSymbols.ATTVAL_HEXBINARY.equals(xSSimpleType.getName())) {
            while (i < list.size()) {
                String str3 = list.get(i);
                if (str3.length() / 2 < this.xsFacets.minLength) {
                    reportSchemaWarning("FacetsContradict", new Object[]{str3, SchemaSymbols.ELT_MINLENGTH, str}, element);
                }
                i++;
            }
            return;
        }
        if (!str2.equals(xSSimpleType.getNamespace()) || !SchemaSymbols.ATTVAL_BASE64BINARY.equals(xSSimpleType.getName())) {
            while (i < list.size()) {
                String str4 = list.get(i);
                if (str4.length() < this.xsFacets.minLength) {
                    reportSchemaWarning("FacetsContradict", new Object[]{str4, SchemaSymbols.ELT_MINLENGTH, str}, element);
                }
                i++;
            }
            return;
        }
        while (i < list.size()) {
            String str5 = list.get(i);
            byte[] bArrDecode = Base64.decode(str5);
            if (bArrDecode != null && new String(bArrDecode).length() < this.xsFacets.minLength) {
                reportSchemaWarning("FacetsContradict", new Object[]{str5, SchemaSymbols.ELT_MINLENGTH, str}, element);
            }
            i++;
        }
    }

    private boolean containsQName(XSSimpleType xSSimpleType) {
        if (xSSimpleType.getVariety() == 1) {
            short primitiveKind = xSSimpleType.getPrimitiveKind();
            return primitiveKind == 18 || primitiveKind == 20;
        }
        if (xSSimpleType.getVariety() == 2) {
            return containsQName((XSSimpleType) xSSimpleType.getItemType());
        }
        if (xSSimpleType.getVariety() == 3) {
            XSObjectList memberTypes = xSSimpleType.getMemberTypes();
            for (int i = 0; i < memberTypes.getLength(); i++) {
                if (containsQName((XSSimpleType) memberTypes.item(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String escapeAttValue(String str, int i) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        sb.append(str.substring(0, i));
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                sb.append(SerializerConstants.ENTITY_QUOT);
            } else if (cCharAt == '<') {
                sb.append(SerializerConstants.ENTITY_LT);
            } else if (cCharAt == '&') {
                sb.append(SerializerConstants.ENTITY_AMP);
            } else if (cCharAt == '\t') {
                sb.append("&#x9;");
            } else if (cCharAt == '\n') {
                sb.append(SerializerConstants.ENTITY_CRLF);
            } else if (cCharAt == '\r') {
                sb.append("&#xD;");
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    public static String getSchemaTypeName(XSTypeDefinition xSTypeDefinition) {
        return xSTypeDefinition instanceof XSSimpleTypeDefinition ? ((XSSimpleTypeDecl) xSTypeDefinition).getTypeName() : ((XSComplexTypeDecl) xSTypeDefinition).getTypeName();
    }

    private static String processAttValue(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == '<' || cCharAt == '&' || cCharAt == '\t' || cCharAt == '\n' || cCharAt == '\r') {
                return escapeAttValue(str, i);
            }
        }
        return str;
    }

    public void checkNotationType(String str, XSTypeDefinition xSTypeDefinition, Element element) {
        if (xSTypeDefinition.getTypeCategory() == 16) {
            XSSimpleType xSSimpleType = (XSSimpleType) xSTypeDefinition;
            if (xSSimpleType.getVariety() == 1 && xSSimpleType.getPrimitiveKind() == 20 && (xSSimpleType.getDefinedFacets() & 2048) == 0) {
                reportSchemaError("enumeration-required-notation", new Object[]{xSTypeDefinition.getName(), str, DOMUtil.getLocalName(element)}, element);
            }
        }
    }

    public XSParticleDecl checkOccurrences(XSParticleDecl xSParticleDecl, String str, Element element, int i, long j) {
        int i2 = xSParticleDecl.fMinOccurs;
        int i3 = xSParticleDecl.fMaxOccurs;
        int i4 = 1;
        boolean z = (((long) (1 << XSAttributeChecker.ATTIDX_MINOCCURS)) & j) != 0;
        boolean z2 = (((long) (1 << XSAttributeChecker.ATTIDX_MAXOCCURS)) & j) != 0;
        boolean z3 = (i & 1) != 0;
        boolean z4 = (i & 8) != 0;
        boolean z5 = (i & 2) != 0;
        if ((i & 4) != 0) {
            if (!z) {
                reportSchemaError("s4s-att-not-allowed", new Object[]{str, "minOccurs"}, element);
                i2 = 1;
            }
            if (!z2) {
                reportSchemaError("s4s-att-not-allowed", new Object[]{str, "maxOccurs"}, element);
                i3 = 1;
            }
        }
        if (i2 == 0 && i3 == 0) {
            xSParticleDecl.fType = (short) 0;
            return null;
        }
        if (!z3) {
            if ((z4 || z5) && i3 != 1) {
                reportSchemaError("cos-all-limited.1.2", null, element);
                if (i2 > 1) {
                    i2 = 1;
                }
                i3 = 1;
            }
            i4 = i2;
        } else if (i3 == 1) {
            i4 = i2;
        } else {
            reportSchemaError("cos-all-limited.2", new Object[]{i3 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(i3), ((XSElementDecl) xSParticleDecl.fValue).getName()}, element);
            if (i2 > 1) {
                i3 = 1;
            } else {
                i3 = 1;
                i4 = i2;
            }
        }
        xSParticleDecl.fMinOccurs = i4;
        xSParticleDecl.fMaxOccurs = i3;
        return xSParticleDecl;
    }

    public void reportSchemaError(String str, Object[] objArr, Element element) {
        this.fSchemaHandler.reportSchemaError(str, objArr, element);
    }

    public void reportSchemaWarning(String str, Object[] objArr, Element element) {
        this.fSchemaHandler.reportSchemaWarning(str, objArr, element);
    }

    public void reset(SymbolTable symbolTable, boolean z, Locale locale) {
        this.fSymbolTable = symbolTable;
        this.fValidateAnnotations = z;
        this.fValidationState.setExtraChecking(false);
        this.fValidationState.setSymbolTable(symbolTable);
        this.fValidationState.setLocale(locale);
    }

    public XSAnnotationImpl traverseAnnotationDecl(Element element, Object[] objArr, boolean z, XSDocumentInfo xSDocumentInfo) {
        String strSubstring;
        String strSubstring2;
        this.fAttrChecker.returnAttrArray(this.fAttrChecker.checkAttributes(element, z, xSDocumentInfo), xSDocumentInfo);
        String annotation = DOMUtil.getAnnotation(element);
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement != null) {
            do {
                String localName = DOMUtil.getLocalName(firstChildElement);
                if (localName.equals(SchemaSymbols.ELT_APPINFO) || localName.equals(SchemaSymbols.ELT_DOCUMENTATION)) {
                    this.fAttrChecker.returnAttrArray(this.fAttrChecker.checkAttributes(firstChildElement, true, xSDocumentInfo), xSDocumentInfo);
                } else {
                    reportSchemaError("src-annotation", new Object[]{localName}, firstChildElement);
                }
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            } while (firstChildElement != null);
        }
        if (annotation == null) {
            return null;
        }
        SchemaGrammar grammar = this.fSchemaHandler.getGrammar(xSDocumentInfo.fTargetNamespace);
        ArrayList arrayList = (ArrayList) objArr[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (arrayList == null || arrayList.isEmpty()) {
            if (this.fValidateAnnotations) {
                xSDocumentInfo.addAnnotation(new XSAnnotationInfo(annotation, element));
            }
            return new XSAnnotationImpl(annotation, grammar);
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append(" ");
        int i = 0;
        while (i < arrayList.size()) {
            int i2 = i + 1;
            String str = (String) arrayList.get(i);
            int iIndexOf = str.indexOf(58);
            if (iIndexOf == -1) {
                strSubstring = "";
                strSubstring2 = str;
            } else {
                strSubstring = str.substring(0, iIndexOf);
                strSubstring2 = str.substring(iIndexOf + 1);
            }
            if (element.getAttributeNS(xSDocumentInfo.fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(strSubstring)), strSubstring2).length() != 0) {
                i += 2;
            } else {
                sb.append(str);
                sb.append("=\"");
                i += 2;
                sb.append(processAttValue((String) arrayList.get(i2)));
                sb.append("\" ");
            }
        }
        StringBuilder sb2 = new StringBuilder(annotation.length() + sb.length());
        String str2 = SchemaSymbols.ELT_ANNOTATION;
        int iIndexOf2 = annotation.indexOf(str2);
        if (iIndexOf2 == -1) {
            return null;
        }
        int length = iIndexOf2 + str2.length();
        sb2.append(annotation.substring(0, length));
        sb2.append(sb.toString());
        sb2.append(annotation.substring(length, annotation.length()));
        String string = sb2.toString();
        if (this.fValidateAnnotations) {
            xSDocumentInfo.addAnnotation(new XSAnnotationInfo(string, element));
        }
        return new XSAnnotationImpl(string, grammar);
    }

    public Element traverseAttrsAndAttrGrps(Element element, XSAttributeGroupDecl xSAttributeGroupDecl, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, XSComplexTypeDecl xSComplexTypeDecl) {
        XSObjectList xSObjectList;
        Element nextSiblingElement = element;
        while (true) {
            String str = "src-ct.4";
            String str2 = "src-attribute_group.2";
            if (nextSiblingElement == null) {
                break;
            }
            String localName = DOMUtil.getLocalName(nextSiblingElement);
            if (!localName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                if (!localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    break;
                }
                XSAttributeGroupDecl xSAttributeGroupDeclTraverseLocal = this.fSchemaHandler.fAttributeGroupTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                if (xSAttributeGroupDeclTraverseLocal != null) {
                    XSObjectList attributeUses = xSAttributeGroupDeclTraverseLocal.getAttributeUses();
                    int length = attributeUses.getLength();
                    int i = 0;
                    while (i < length) {
                        XSAttributeUseImpl xSAttributeUseImpl = (XSAttributeUseImpl) attributeUses.item(i);
                        String str3 = str;
                        String str4 = str2;
                        if (xSAttributeUseImpl.fUse == 2) {
                            xSAttributeGroupDecl.addAttributeUse(xSAttributeUseImpl);
                        } else {
                            XSAttributeUse attributeUseNoProhibited = xSAttributeGroupDecl.getAttributeUseNoProhibited(xSAttributeUseImpl.fAttrDecl.getNamespace(), xSAttributeUseImpl.fAttrDecl.getName());
                            if (attributeUseNoProhibited == null) {
                                String strAddAttributeUse = xSAttributeGroupDecl.addAttributeUse(xSAttributeUseImpl);
                                if (strAddAttributeUse != null) {
                                    xSObjectList = attributeUses;
                                    reportSchemaError(xSComplexTypeDecl == null ? "ag-props-correct.3" : "ct-props-correct.5", new Object[]{xSComplexTypeDecl == null ? xSAttributeGroupDecl.fName : xSComplexTypeDecl.getName(), xSAttributeUseImpl.fAttrDecl.getName(), strAddAttributeUse}, nextSiblingElement);
                                }
                            } else {
                                xSObjectList = attributeUses;
                                if (xSAttributeUseImpl != attributeUseNoProhibited) {
                                    reportSchemaError(xSComplexTypeDecl == null ? "ag-props-correct.2" : "ct-props-correct.4", new Object[]{xSComplexTypeDecl == null ? xSAttributeGroupDecl.fName : xSComplexTypeDecl.getName(), xSAttributeUseImpl.fAttrDecl.getName()}, nextSiblingElement);
                                }
                            }
                            i++;
                            str = str3;
                            str2 = str4;
                            attributeUses = xSObjectList;
                        }
                        xSObjectList = attributeUses;
                        i++;
                        str = str3;
                        str2 = str4;
                        attributeUses = xSObjectList;
                    }
                    String str5 = str;
                    String str6 = str2;
                    XSWildcardDecl xSWildcardDecl = xSAttributeGroupDeclTraverseLocal.fAttributeWC;
                    if (xSWildcardDecl != null) {
                        XSWildcardDecl xSWildcardDecl2 = xSAttributeGroupDecl.fAttributeWC;
                        if (xSWildcardDecl2 == null) {
                            xSAttributeGroupDecl.fAttributeWC = xSWildcardDecl;
                        } else {
                            XSWildcardDecl xSWildcardDeclPerformIntersectionWith = xSWildcardDecl2.performIntersectionWith(xSWildcardDecl, xSWildcardDecl2.fProcessContents);
                            xSAttributeGroupDecl.fAttributeWC = xSWildcardDeclPerformIntersectionWith;
                            if (xSWildcardDeclPerformIntersectionWith == null) {
                                reportSchemaError(xSComplexTypeDecl == null ? str6 : str5, new Object[]{xSComplexTypeDecl == null ? xSAttributeGroupDecl.fName : xSComplexTypeDecl.getName()}, nextSiblingElement);
                            }
                        }
                    }
                }
            } else {
                XSAttributeUseImpl xSAttributeUseImplTraverseLocal = this.fSchemaHandler.fAttributeTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar, xSComplexTypeDecl);
                if (xSAttributeUseImplTraverseLocal != null) {
                    if (xSAttributeUseImplTraverseLocal.fUse == 2) {
                        xSAttributeGroupDecl.addAttributeUse(xSAttributeUseImplTraverseLocal);
                    } else {
                        XSAttributeUse attributeUseNoProhibited2 = xSAttributeGroupDecl.getAttributeUseNoProhibited(xSAttributeUseImplTraverseLocal.fAttrDecl.getNamespace(), xSAttributeUseImplTraverseLocal.fAttrDecl.getName());
                        if (attributeUseNoProhibited2 == null) {
                            String strAddAttributeUse2 = xSAttributeGroupDecl.addAttributeUse(xSAttributeUseImplTraverseLocal);
                            if (strAddAttributeUse2 != null) {
                                reportSchemaError(xSComplexTypeDecl == null ? "ag-props-correct.3" : "ct-props-correct.5", new Object[]{xSComplexTypeDecl == null ? xSAttributeGroupDecl.fName : xSComplexTypeDecl.getName(), xSAttributeUseImplTraverseLocal.fAttrDecl.getName(), strAddAttributeUse2}, nextSiblingElement);
                            }
                        } else if (attributeUseNoProhibited2 != xSAttributeUseImplTraverseLocal) {
                            reportSchemaError(xSComplexTypeDecl == null ? "ag-props-correct.2" : "ct-props-correct.4", new Object[]{xSComplexTypeDecl == null ? xSAttributeGroupDecl.fName : xSComplexTypeDecl.getName(), xSAttributeUseImplTraverseLocal.fAttrDecl.getName()}, nextSiblingElement);
                        }
                    }
                }
            }
            nextSiblingElement = DOMUtil.getNextSiblingElement(nextSiblingElement);
        }
        if (nextSiblingElement == null || !DOMUtil.getLocalName(nextSiblingElement).equals(SchemaSymbols.ELT_ANYATTRIBUTE)) {
            return nextSiblingElement;
        }
        XSWildcardDecl xSWildcardDeclTraverseAnyAttribute = this.fSchemaHandler.fWildCardTraverser.traverseAnyAttribute(nextSiblingElement, xSDocumentInfo, schemaGrammar);
        XSWildcardDecl xSWildcardDecl3 = xSAttributeGroupDecl.fAttributeWC;
        if (xSWildcardDecl3 == null) {
            xSAttributeGroupDecl.fAttributeWC = xSWildcardDeclTraverseAnyAttribute;
        } else {
            XSWildcardDecl xSWildcardDeclPerformIntersectionWith2 = xSWildcardDeclTraverseAnyAttribute.performIntersectionWith(xSWildcardDecl3, xSWildcardDeclTraverseAnyAttribute.fProcessContents);
            xSAttributeGroupDecl.fAttributeWC = xSWildcardDeclPerformIntersectionWith2;
            if (xSWildcardDeclPerformIntersectionWith2 == null) {
                reportSchemaError(xSComplexTypeDecl == null ? "src-attribute_group.2" : "src-ct.4", new Object[]{xSComplexTypeDecl == null ? xSAttributeGroupDecl.fName : xSComplexTypeDecl.getName()}, nextSiblingElement);
            }
        }
        return DOMUtil.getNextSiblingElement(nextSiblingElement);
    }

    /* JADX WARN: Code duplicated, block: B:162:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:164:0x03a5  */
    /* JADX WARN: Code duplicated, block: B:166:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:168:0x03ac  */
    /* JADX WARN: Code duplicated, block: B:170:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:172:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:174:0x03b8  */
    /* JADX WARN: Code duplicated, block: B:176:0x03bc  */
    /* JADX WARN: Code duplicated, block: B:178:0x03c0  */
    /* JADX WARN: Code duplicated, block: B:181:0x03c5  */
    /* JADX WARN: Code duplicated, block: B:182:0x03ca  */
    /* JADX WARN: Code duplicated, block: B:183:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:184:0x03d4  */
    /* JADX WARN: Code duplicated, block: B:185:0x03d9  */
    /* JADX WARN: Code duplicated, block: B:186:0x03de  */
    /* JADX WARN: Code duplicated, block: B:187:0x03e3  */
    /* JADX WARN: Code duplicated, block: B:188:0x03e8  */
    /* JADX WARN: Code duplicated, block: B:189:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:190:0x03f2  */
    /* JADX WARN: Code duplicated, block: B:192:0x03f8  */
    /* JADX WARN: Code duplicated, block: B:47:0x0137  */
    /* JADX WARN: Code duplicated, block: B:75:0x01db  */
    public FacetInfo traverseFacets(Element element, XSTypeDefinition xSTypeDefinition, XSSimpleType xSSimpleType, XSDocumentInfo xSDocumentInfo) {
        short s;
        short s2;
        short s3;
        XSSimpleType xSSimpleType2;
        Element element2;
        boolean z;
        short s4;
        Element element3;
        short s5;
        Object[] objArrCheckAttributes;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        Element nextSiblingElement;
        short s6;
        Object globalDecl;
        boolean zContainsQName = containsQName(xSSimpleType);
        ArrayList arrayList = zContainsQName ? new ArrayList() : null;
        this.xsFacets.reset();
        Element element4 = (Element) element.getParentNode();
        Element nextSiblingElement2 = element;
        short s7 = 0;
        short s8 = 0;
        XSObjectListImpl xSObjectListImpl = null;
        ArrayList arrayList2 = null;
        XSObjectListImpl xSObjectListImpl2 = null;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (true) {
            if (nextSiblingElement2 == null) {
                s = s7;
                element4 = element4;
                s2 = s8;
                break;
            }
            String localName = DOMUtil.getLocalName(nextSiblingElement2);
            String str = SchemaSymbols.ELT_ENUMERATION;
            short s9 = s7;
            if (localName.equals(str)) {
                short s10 = s8;
                objArrCheckAttributes = this.fAttrChecker.checkAttributes(nextSiblingElement2, false, xSDocumentInfo, zContainsQName);
                String str2 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_VALUE];
                if (str2 == null) {
                    reportSchemaError("s4s-att-must-appear", new Object[]{str, SchemaSymbols.ATT_VALUE}, nextSiblingElement2);
                    this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                    nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                    s7 = s9;
                    s8 = s10;
                } else {
                    NamespaceSupport namespaceSupport = (NamespaceSupport) objArrCheckAttributes[XSAttributeChecker.ATTIDX_ENUMNSDECLS];
                    if (xSSimpleType.getVariety() == 1 && xSSimpleType.getPrimitiveKind() == 20) {
                        xSDocumentInfo.fValidationContext.setNamespaceSupport(namespaceSupport);
                        try {
                            z = zContainsQName;
                            try {
                                globalDecl = this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 6, (QName) fQNameDV.validate(str2, (ValidationContext) xSDocumentInfo.fValidationContext, (ValidatedInfo) null), nextSiblingElement2);
                            } catch (InvalidDatatypeValueException e) {
                                e = e;
                                reportSchemaError(e.getKey(), e.getArgs(), nextSiblingElement2);
                                globalDecl = null;
                            }
                        } catch (InvalidDatatypeValueException e2) {
                            e = e2;
                            z = zContainsQName;
                        }
                        if (globalDecl == null) {
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                            nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                            s7 = s9;
                            s8 = s10;
                        } else {
                            xSDocumentInfo.fValidationContext.setNamespaceSupport(xSDocumentInfo.fNamespaceSupport);
                        }
                        zContainsQName = z;
                    } else {
                        z = zContainsQName;
                    }
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        xSObjectListImpl2 = new XSObjectListImpl();
                    }
                    arrayList2.add(str2);
                    xSObjectListImpl2.addXSObject(null);
                    if (z) {
                        arrayList.add(namespaceSupport);
                    }
                    Element firstChildElement = DOMUtil.getFirstChildElement(nextSiblingElement2);
                    if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(nextSiblingElement2);
                        if (syntheticAnnotation != null) {
                            s2 = s10;
                            xSObjectListImpl2.addXSObject(xSObjectListImpl2.getLength() - 1, traverseSyntheticAnnotation(nextSiblingElement2, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo));
                        }
                        if (firstChildElement != null) {
                            reportSchemaError("s4s-elt-must-match.1", new Object[]{"enumeration", "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
                        }
                        s7 = s9;
                    } else {
                        xSObjectListImpl2.addXSObject(xSObjectListImpl2.getLength() - 1, traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo));
                        firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                    }
                    s2 = s10;
                    if (firstChildElement != null) {
                        reportSchemaError("s4s-elt-must-match.1", new Object[]{"enumeration", "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
                    }
                    s7 = s9;
                }
            } else {
                z = zContainsQName;
                element4 = element4;
                s = s9;
                s2 = s8;
                String str3 = SchemaSymbols.ELT_PATTERN;
                if (localName.equals(str3)) {
                    Object[] objArrCheckAttributes2 = this.fAttrChecker.checkAttributes(nextSiblingElement2, false, xSDocumentInfo);
                    String str4 = (String) objArrCheckAttributes2[XSAttributeChecker.ATTIDX_VALUE];
                    if (str4 == null) {
                        reportSchemaError("s4s-att-must-appear", new Object[]{str3, SchemaSymbols.ATT_VALUE}, nextSiblingElement2);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                        nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                    } else {
                        int length = this.fPattern.length();
                        StringBuilder sb = this.fPattern;
                        if (length == 0) {
                            sb.append(str4);
                        } else {
                            sb.append("|");
                            this.fPattern.append(str4);
                        }
                        Element firstChildElement2 = DOMUtil.getFirstChildElement(nextSiblingElement2);
                        if (firstChildElement2 == null || !DOMUtil.getLocalName(firstChildElement2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(nextSiblingElement2);
                            if (syntheticAnnotation2 != null) {
                                if (xSObjectListImpl == null) {
                                    xSObjectListImpl = new XSObjectListImpl();
                                }
                                objArrCheckAttributes = objArrCheckAttributes2;
                                xSObjectListImpl.addXSObject(traverseSyntheticAnnotation(nextSiblingElement2, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo));
                            }
                            if (firstChildElement2 != null) {
                                reportSchemaError("s4s-elt-must-match.1", new Object[]{"pattern", "(annotation?)", DOMUtil.getLocalName(firstChildElement2)}, firstChildElement2);
                            }
                            s7 = s;
                            z2 = true;
                        } else {
                            if (xSObjectListImpl == null) {
                                xSObjectListImpl = new XSObjectListImpl();
                            }
                            xSObjectListImpl.addXSObject(traverseAnnotationDecl(firstChildElement2, objArrCheckAttributes2, false, xSDocumentInfo));
                            firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
                        }
                        objArrCheckAttributes = objArrCheckAttributes2;
                        if (firstChildElement2 != null) {
                            reportSchemaError("s4s-elt-must-match.1", new Object[]{"pattern", "(annotation?)", DOMUtil.getLocalName(firstChildElement2)}, firstChildElement2);
                        }
                        s7 = s;
                        z2 = true;
                    }
                } else {
                    if (!localName.equals(SchemaSymbols.ELT_MINLENGTH)) {
                        if (!localName.equals(SchemaSymbols.ELT_MAXLENGTH)) {
                            if (!localName.equals(SchemaSymbols.ELT_MAXEXCLUSIVE)) {
                                if (!localName.equals(SchemaSymbols.ELT_MAXINCLUSIVE)) {
                                    if (!localName.equals(SchemaSymbols.ELT_MINEXCLUSIVE)) {
                                        if (!localName.equals(SchemaSymbols.ELT_MININCLUSIVE)) {
                                            if (!localName.equals(SchemaSymbols.ELT_TOTALDIGITS)) {
                                                if (!localName.equals(SchemaSymbols.ELT_FRACTIONDIGITS)) {
                                                    if (!localName.equals(SchemaSymbols.ELT_WHITESPACE)) {
                                                        if (!localName.equals(SchemaSymbols.ELT_LENGTH)) {
                                                            break;
                                                        }
                                                        s4 = 1;
                                                    } else {
                                                        s4 = 16;
                                                    }
                                                } else {
                                                    s4 = 1024;
                                                }
                                            } else {
                                                s4 = 512;
                                            }
                                        } else {
                                            s4 = 256;
                                        }
                                    } else {
                                        s4 = 128;
                                    }
                                } else {
                                    s4 = 32;
                                }
                            } else {
                                s4 = 64;
                            }
                        } else {
                            s4 = 4;
                        }
                    } else {
                        s4 = 2;
                    }
                    Object[] objArrCheckAttributes3 = this.fAttrChecker.checkAttributes(nextSiblingElement2, false, xSDocumentInfo);
                    if ((s & s4) != 0) {
                        reportSchemaError("src-single-facet-value", new Object[]{localName}, nextSiblingElement2);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes3, xSDocumentInfo);
                        nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                    } else {
                        int i = XSAttributeChecker.ATTIDX_VALUE;
                        if (objArrCheckAttributes3[i] == null) {
                            if (nextSiblingElement2.getAttributeNodeNS(null, "value") == null) {
                                reportSchemaError("s4s-att-must-appear", new Object[]{nextSiblingElement2.getLocalName(), SchemaSymbols.ATT_VALUE}, nextSiblingElement2);
                            }
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes3, xSDocumentInfo);
                            nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                        } else {
                            short s11 = (short) (s | s4);
                            if (((Boolean) objArrCheckAttributes3[XSAttributeChecker.ATTIDX_FIXED]).booleanValue()) {
                                s2 = (short) (s2 | s4);
                            }
                            if (s4 == 1) {
                                this.xsFacets.length = ((XInt) objArrCheckAttributes3[i]).intValue();
                                z3 = true;
                            } else if (s4 == 2) {
                                this.xsFacets.minLength = ((XInt) objArrCheckAttributes3[i]).intValue();
                                z4 = true;
                            } else if (s4 == 4) {
                                this.xsFacets.maxLength = ((XInt) objArrCheckAttributes3[i]).intValue();
                                z5 = true;
                            } else if (s4 == 16) {
                                this.xsFacets.whiteSpace = ((XInt) objArrCheckAttributes3[i]).shortValue();
                            } else if (s4 == 32) {
                                this.xsFacets.maxInclusive = (String) objArrCheckAttributes3[i];
                            } else if (s4 == 64) {
                                this.xsFacets.maxExclusive = (String) objArrCheckAttributes3[i];
                            } else if (s4 == 128) {
                                this.xsFacets.minExclusive = (String) objArrCheckAttributes3[i];
                            } else if (s4 == 256) {
                                this.xsFacets.minInclusive = (String) objArrCheckAttributes3[i];
                            } else if (s4 == 512) {
                                this.xsFacets.totalDigits = ((XInt) objArrCheckAttributes3[i]).intValue();
                            } else if (s4 == 1024) {
                                this.xsFacets.fractionDigits = ((XInt) objArrCheckAttributes3[i]).intValue();
                            }
                            Element firstChildElement3 = DOMUtil.getFirstChildElement(nextSiblingElement2);
                            if (firstChildElement3 != null) {
                                element3 = nextSiblingElement2;
                                if (DOMUtil.getLocalName(firstChildElement3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                                    XSAnnotationImpl xSAnnotationImplTraverseAnnotationDecl = traverseAnnotationDecl(firstChildElement3, objArrCheckAttributes3, false, xSDocumentInfo);
                                    nextSiblingElement = DOMUtil.getNextSiblingElement(firstChildElement3);
                                    s11 = s11;
                                    s6 = 1;
                                    s5 = s4;
                                    objArrCheckAttributes = objArrCheckAttributes3;
                                    xSAnnotationImplTraverseSyntheticAnnotation = xSAnnotationImplTraverseAnnotationDecl;
                                    nextSiblingElement2 = element3;
                                }
                                if (s5 != s6) {
                                    this.xsFacets.lengthAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 2) {
                                    this.xsFacets.minLengthAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 4) {
                                    this.xsFacets.maxLengthAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 16) {
                                    this.xsFacets.whiteSpaceAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 32) {
                                    this.xsFacets.maxInclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 64) {
                                    this.xsFacets.maxExclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 128) {
                                    this.xsFacets.minExclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 256) {
                                    this.xsFacets.minInclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 != 512) {
                                    this.xsFacets.totalDigitsAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                } else if (s5 == 1024) {
                                    this.xsFacets.fractionDigitsAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                                }
                                if (nextSiblingElement != null) {
                                    reportSchemaError("s4s-elt-must-match.1", new Object[]{localName, "(annotation?)", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                                }
                                s7 = s11;
                                this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                                nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                                s8 = s2;
                                element4 = element4;
                                zContainsQName = z;
                            } else {
                                element3 = nextSiblingElement2;
                            }
                            String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element3);
                            if (syntheticAnnotation3 != null) {
                                short s12 = s4;
                                objArrCheckAttributes = objArrCheckAttributes3;
                                s5 = s12;
                                nextSiblingElement2 = element3;
                                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(nextSiblingElement2, syntheticAnnotation3, objArrCheckAttributes, false, xSDocumentInfo);
                            } else {
                                nextSiblingElement2 = element3;
                                s5 = s4;
                                objArrCheckAttributes = objArrCheckAttributes3;
                                xSAnnotationImplTraverseSyntheticAnnotation = null;
                            }
                            nextSiblingElement = firstChildElement3;
                            s6 = 1;
                            if (s5 != s6) {
                                this.xsFacets.lengthAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 2) {
                                this.xsFacets.minLengthAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 4) {
                                this.xsFacets.maxLengthAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 16) {
                                this.xsFacets.whiteSpaceAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 32) {
                                this.xsFacets.maxInclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 64) {
                                this.xsFacets.maxExclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 128) {
                                this.xsFacets.minExclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 256) {
                                this.xsFacets.minInclusiveAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 != 512) {
                                this.xsFacets.totalDigitsAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            } else if (s5 == 1024) {
                                this.xsFacets.fractionDigitsAnnotation = xSAnnotationImplTraverseSyntheticAnnotation;
                            }
                            if (nextSiblingElement != null) {
                                reportSchemaError("s4s-elt-must-match.1", new Object[]{localName, "(annotation?)", DOMUtil.getLocalName(nextSiblingElement)}, nextSiblingElement);
                            }
                            s7 = s11;
                            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
                            nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                            s8 = s2;
                            element4 = element4;
                            zContainsQName = z;
                        }
                    }
                }
                s7 = s;
                s8 = s2;
                element4 = element4;
                zContainsQName = z;
            }
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
            s8 = s2;
            element4 = element4;
            zContainsQName = z;
        }
        if (arrayList2 != null) {
            s3 = (short) (s | 2048);
            XSFacets xSFacets = this.xsFacets;
            xSFacets.enumeration = arrayList2;
            xSFacets.enumNSDecls = arrayList;
            xSFacets.enumAnnotations = xSObjectListImpl2;
        } else {
            s3 = s;
        }
        if (z2) {
            s3 = (short) (s3 | 8);
            this.xsFacets.pattern = this.fPattern.toString();
            this.xsFacets.patternAnnotations = xSObjectListImpl;
        }
        this.fPattern.setLength(0);
        if (arrayList2 != null) {
            if (z3) {
                xSSimpleType2 = xSSimpleType;
                element2 = element4;
                checkEnumerationAndLengthInconsistency(xSSimpleType2, arrayList2, element2, getSchemaTypeName(xSTypeDefinition));
            } else {
                xSSimpleType2 = xSSimpleType;
                element2 = element4;
            }
            if (z4) {
                checkEnumerationAndMinLengthInconsistency(xSSimpleType2, arrayList2, element2, getSchemaTypeName(xSTypeDefinition));
            }
            if (z5) {
                checkEnumerationAndMaxLengthInconsistency(xSSimpleType2, arrayList2, element2, getSchemaTypeName(xSTypeDefinition));
            }
        }
        return new FacetInfo(this.xsFacets, nextSiblingElement2, s3, s2);
    }

    public XSAnnotationImpl traverseSyntheticAnnotation(Element element, String str, Object[] objArr, boolean z, XSDocumentInfo xSDocumentInfo) {
        String strSubstring;
        SchemaGrammar grammar = this.fSchemaHandler.getGrammar(xSDocumentInfo.fTargetNamespace);
        ArrayList arrayList = (ArrayList) objArr[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (arrayList == null || arrayList.isEmpty()) {
            if (this.fValidateAnnotations) {
                xSDocumentInfo.addAnnotation(new XSAnnotationInfo(str, element));
            }
            return new XSAnnotationImpl(str, grammar);
        }
        StringBuilder sb = new StringBuilder(64);
        sb.append(" ");
        int i = 0;
        while (i < arrayList.size()) {
            int i2 = i + 1;
            String str2 = (String) arrayList.get(i);
            int iIndexOf = str2.indexOf(58);
            if (iIndexOf == -1) {
                strSubstring = "";
            } else {
                strSubstring = str2.substring(0, iIndexOf);
                str2.substring(iIndexOf + 1);
            }
            xSDocumentInfo.fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(strSubstring));
            sb.append(str2);
            sb.append("=\"");
            i += 2;
            sb.append(processAttValue((String) arrayList.get(i2)));
            sb.append("\" ");
        }
        StringBuilder sb2 = new StringBuilder(str.length() + sb.length());
        String str3 = SchemaSymbols.ELT_ANNOTATION;
        int iIndexOf2 = str.indexOf(str3);
        if (iIndexOf2 == -1) {
            return null;
        }
        int length = iIndexOf2 + str3.length();
        sb2.append(str.substring(0, length));
        sb2.append(sb.toString());
        sb2.append(str.substring(length, str.length()));
        String string = sb2.toString();
        if (this.fValidateAnnotations) {
            xSDocumentInfo.addAnnotation(new XSAnnotationInfo(string, element));
        }
        return new XSAnnotationImpl(string, grammar);
    }
}
