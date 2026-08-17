package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSNotationDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDNotationTraverser extends XSDAbstractTraverser {
    public XSDNotationTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0078  */
    /* JADX WARN: Code duplicated, block: B:20:0x0081  */
    /* JADX WARN: Code duplicated, block: B:23:0x0087  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:29:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:32:0x00bb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x00bd  */
    public XSNotationDecl traverse(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        XSDNotationTraverser xSDNotationTraverser;
        XSDocumentInfo xSDocumentInfo2;
        XSObjectListImpl xSObjectListImpl;
        String strSchemaDocument2SystemId;
        XSNotationDecl globalNotationDecl;
        XSDHandler xSDHandler;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, true, xSDocumentInfo);
        String str = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_NAME];
        String str2 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_PUBLIC];
        String str3 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_SYSTEM];
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation = null;
        if (str == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_NOTATION, SchemaSymbols.ATT_NAME}, element);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            return null;
        }
        if (str3 == null && str2 == null) {
            reportSchemaError("PublicSystemOnNotation", null, element);
            str2 = "missing";
        }
        XSNotationDecl xSNotationDecl = new XSNotationDecl();
        xSNotationDecl.fName = str;
        xSNotationDecl.fTargetNamespace = xSDocumentInfo.fTargetNamespace;
        xSNotationDecl.fPublicId = str2;
        xSNotationDecl.fSystemId = str3;
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                xSDNotationTraverser = this;
                xSDocumentInfo2 = xSDocumentInfo;
                xSAnnotationImplTraverseSyntheticAnnotation = xSDNotationTraverser.traverseSyntheticAnnotation(element, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo2);
            }
            if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
                xSObjectListImpl = new XSObjectListImpl();
                xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            xSNotationDecl.fAnnotations = xSObjectListImpl;
            if (firstChildElement != null) {
                xSDNotationTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_NOTATION, "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
            }
            if (schemaGrammar.getGlobalNotationDecl(xSNotationDecl.fName) == null) {
                schemaGrammar.addGlobalNotationDecl(xSNotationDecl);
            }
            strSchemaDocument2SystemId = xSDNotationTraverser.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo2);
            globalNotationDecl = schemaGrammar.getGlobalNotationDecl(xSNotationDecl.fName, strSchemaDocument2SystemId);
            if (globalNotationDecl == null) {
                schemaGrammar.addGlobalNotationDecl(xSNotationDecl, strSchemaDocument2SystemId);
            }
            xSDHandler = xSDNotationTraverser.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (globalNotationDecl != null) {
                    xSNotationDecl = globalNotationDecl;
                }
                xSDHandler.addGlobalNotationDecl(xSNotationDecl);
            }
            xSDNotationTraverser.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo2);
            return xSNotationDecl;
        }
        xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
        firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
        xSDNotationTraverser = this;
        xSDocumentInfo2 = xSDocumentInfo;
        if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        xSNotationDecl.fAnnotations = xSObjectListImpl;
        if (firstChildElement != null) {
            xSDNotationTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_NOTATION, "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
        }
        if (schemaGrammar.getGlobalNotationDecl(xSNotationDecl.fName) == null) {
            schemaGrammar.addGlobalNotationDecl(xSNotationDecl);
        }
        strSchemaDocument2SystemId = xSDNotationTraverser.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo2);
        globalNotationDecl = schemaGrammar.getGlobalNotationDecl(xSNotationDecl.fName, strSchemaDocument2SystemId);
        if (globalNotationDecl == null) {
            schemaGrammar.addGlobalNotationDecl(xSNotationDecl, strSchemaDocument2SystemId);
        }
        xSDHandler = xSDNotationTraverser.fSchemaHandler;
        if (xSDHandler.fTolerateDuplicates) {
            if (globalNotationDecl != null) {
                xSNotationDecl = globalNotationDecl;
            }
            xSDHandler.addGlobalNotationDecl(xSNotationDecl);
        }
        xSDNotationTraverser.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo2);
        return xSNotationDecl;
    }
}
