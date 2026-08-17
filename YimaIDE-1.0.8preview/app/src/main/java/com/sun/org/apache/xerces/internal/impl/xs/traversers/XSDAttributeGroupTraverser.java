package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSAttributeGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.QName;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDAttributeGroupTraverser extends XSDAbstractTraverser {
    public XSDAttributeGroupTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0074  */
    /* JADX WARN: Code duplicated, block: B:21:0x008a  */
    /* JADX WARN: Code duplicated, block: B:23:0x0090  */
    /* JADX WARN: Code duplicated, block: B:29:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:30:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:33:0x00db  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:39:0x00f5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:43:0x00fd  */
    public XSAttributeGroupDecl traverseGlobal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        String str;
        Object[] objArr;
        Element element2;
        XSAnnotationImpl xSAnnotationImpl;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        Element elementTraverseAttrsAndAttrGrps;
        XSAttributeGroupDecl xSAttributeGroupDecl;
        XSObjectListImpl xSObjectListImpl;
        String strSchemaDocument2SystemId;
        XSAttributeGroupDecl globalAttributeGroupDecl;
        XSDHandler xSDHandler;
        Object[] objArrValidRestrictionOf;
        Element element3 = element;
        XSAttributeGroupDecl xSAttributeGroupDecl2 = new XSAttributeGroupDecl();
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element3, true, xSDocumentInfo);
        String str2 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (str2 == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"attributeGroup (global)", "name"}, element3);
            str = "(no name)";
        } else {
            str = str2;
        }
        xSAttributeGroupDecl2.fName = str;
        xSAttributeGroupDecl2.fTargetNamespace = xSDocumentInfo.fTargetNamespace;
        Element firstChildElement = DOMUtil.getFirstChildElement(element3);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element3);
            if (syntheticAnnotation != null) {
                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element3, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo);
                objArr = objArrCheckAttributes;
            } else {
                objArr = objArrCheckAttributes;
                element3 = element3;
                element2 = firstChildElement;
                xSAnnotationImpl = null;
            }
            elementTraverseAttrsAndAttrGrps = traverseAttrsAndAttrGrps(element2, xSAttributeGroupDecl2, xSDocumentInfo, schemaGrammar, null);
            if (elementTraverseAttrsAndAttrGrps != null) {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{str, "(annotation?, ((attribute | attributeGroup)*, anyAttribute?))", DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps)}, elementTraverseAttrsAndAttrGrps);
            }
            if (str.equals("(no name)")) {
                this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
                return null;
            }
            xSAttributeGroupDecl2.removeProhibitedAttrs();
            xSAttributeGroupDecl = (XSAttributeGroupDecl) this.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(2, new QName(XMLSymbols.EMPTY_STRING, str, str, xSDocumentInfo.fTargetNamespace), xSDocumentInfo, element3);
            if (xSAttributeGroupDecl != null && (objArrValidRestrictionOf = xSAttributeGroupDecl2.validRestrictionOf(str, xSAttributeGroupDecl)) != null) {
                reportSchemaError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element2);
                reportSchemaError("src-redefine.7.2.2", new Object[]{str, objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1]}, element2);
            }
            if (xSAnnotationImpl != null) {
                xSObjectListImpl = new XSObjectListImpl();
                xSObjectListImpl.addXSObject(xSAnnotationImpl);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            xSAttributeGroupDecl2.fAnnotations = xSObjectListImpl;
            if (schemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl2.fName) == null) {
                schemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl2);
            }
            strSchemaDocument2SystemId = this.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
            globalAttributeGroupDecl = schemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl2.fName, strSchemaDocument2SystemId);
            if (globalAttributeGroupDecl == null) {
                schemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl2, strSchemaDocument2SystemId);
            }
            xSDHandler = this.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (globalAttributeGroupDecl == null) {
                    globalAttributeGroupDecl = xSAttributeGroupDecl2;
                }
                xSDHandler.addGlobalAttributeGroupDecl(globalAttributeGroupDecl);
            } else {
                globalAttributeGroupDecl = xSAttributeGroupDecl2;
            }
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
            return globalAttributeGroupDecl;
        }
        xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
        firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
        objArr = objArrCheckAttributes;
        element2 = firstChildElement;
        xSAnnotationImpl = xSAnnotationImplTraverseSyntheticAnnotation;
        elementTraverseAttrsAndAttrGrps = traverseAttrsAndAttrGrps(element2, xSAttributeGroupDecl2, xSDocumentInfo, schemaGrammar, null);
        if (elementTraverseAttrsAndAttrGrps != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{str, "(annotation?, ((attribute | attributeGroup)*, anyAttribute?))", DOMUtil.getLocalName(elementTraverseAttrsAndAttrGrps)}, elementTraverseAttrsAndAttrGrps);
        }
        if (str.equals("(no name)")) {
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
            return null;
        }
        xSAttributeGroupDecl2.removeProhibitedAttrs();
        xSAttributeGroupDecl = (XSAttributeGroupDecl) this.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(2, new QName(XMLSymbols.EMPTY_STRING, str, str, xSDocumentInfo.fTargetNamespace), xSDocumentInfo, element3);
        if (xSAttributeGroupDecl != null) {
            reportSchemaError((String) objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1], objArrValidRestrictionOf, element2);
            reportSchemaError("src-redefine.7.2.2", new Object[]{str, objArrValidRestrictionOf[objArrValidRestrictionOf.length - 1]}, element2);
        }
        if (xSAnnotationImpl != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImpl);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        xSAttributeGroupDecl2.fAnnotations = xSObjectListImpl;
        if (schemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl2.fName) == null) {
            schemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl2);
        }
        strSchemaDocument2SystemId = this.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
        globalAttributeGroupDecl = schemaGrammar.getGlobalAttributeGroupDecl(xSAttributeGroupDecl2.fName, strSchemaDocument2SystemId);
        if (globalAttributeGroupDecl == null) {
            schemaGrammar.addGlobalAttributeGroupDecl(xSAttributeGroupDecl2, strSchemaDocument2SystemId);
        }
        xSDHandler = this.fSchemaHandler;
        if (xSDHandler.fTolerateDuplicates) {
            if (globalAttributeGroupDecl == null) {
                globalAttributeGroupDecl = xSAttributeGroupDecl2;
            }
            xSDHandler.addGlobalAttributeGroupDecl(globalAttributeGroupDecl);
        } else {
            globalAttributeGroupDecl = xSAttributeGroupDecl2;
        }
        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
        return globalAttributeGroupDecl;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0059  */
    public XSAttributeGroupDecl traverseLocal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        XSDAttributeGroupTraverser xSDAttributeGroupTraverser;
        XSDocumentInfo xSDocumentInfo2;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        QName qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_REF];
        if (qName == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"attributeGroup (local)", "ref"}, element);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            return null;
        }
        XSAttributeGroupDecl xSAttributeGroupDecl = (XSAttributeGroupDecl) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 2, qName, element);
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement != null) {
            if (DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            } else {
                String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(firstChildElement);
                if (syntheticAnnotation != null) {
                    xSDAttributeGroupTraverser = this;
                    xSDocumentInfo2 = xSDocumentInfo;
                    xSDAttributeGroupTraverser.traverseSyntheticAnnotation(firstChildElement, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo2);
                }
                if (firstChildElement != null) {
                    xSDAttributeGroupTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{qName.rawname, "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
                }
            }
            xSDAttributeGroupTraverser = this;
            xSDocumentInfo2 = xSDocumentInfo;
            if (firstChildElement != null) {
                xSDAttributeGroupTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{qName.rawname, "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, firstChildElement);
            }
        } else {
            xSDAttributeGroupTraverser = this;
            xSDocumentInfo2 = xSDocumentInfo;
        }
        xSDAttributeGroupTraverser.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo2);
        return xSAttributeGroupDecl;
    }
}
