package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xpath.XPathException;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Field;
import com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.XMLChar;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDAbstractIDConstraintTraverser extends XSDAbstractTraverser {
    public XSDAbstractIDConstraintTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    public boolean traverseIdentityConstraint(IdentityConstraint identityConstraint, Element element, XSDocumentInfo xSDocumentInfo, Object[] objArr) {
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null) {
            reportSchemaError("s4s-elt-must-match.2", new Object[]{"identity constraint", "(annotation?, selector, field+)"}, element);
            return false;
        }
        String localName = DOMUtil.getLocalName(firstChildElement);
        String str = SchemaSymbols.ELT_ANNOTATION;
        if (localName.equals(str)) {
            identityConstraint.addAnnotation(traverseAnnotationDecl(firstChildElement, objArr, false, xSDocumentInfo));
            firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            if (firstChildElement == null) {
                reportSchemaError("s4s-elt-must-match.2", new Object[]{"identity constraint", "(annotation?, selector, field+)"}, element);
                return false;
            }
        } else {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                identityConstraint.addAnnotation(traverseSyntheticAnnotation(element, syntheticAnnotation, objArr, false, xSDocumentInfo));
            }
        }
        String localName2 = DOMUtil.getLocalName(firstChildElement);
        String str2 = SchemaSymbols.ELT_SELECTOR;
        if (!localName2.equals(str2)) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{"identity constraint", "(annotation?, selector, field+)", str2}, firstChildElement);
            return false;
        }
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(firstChildElement, false, xSDocumentInfo);
        Element firstChildElement2 = DOMUtil.getFirstChildElement(firstChildElement);
        if (firstChildElement2 != null) {
            if (DOMUtil.getLocalName(firstChildElement2).equals(str)) {
                identityConstraint.addAnnotation(traverseAnnotationDecl(firstChildElement2, objArrCheckAttributes, false, xSDocumentInfo));
                firstChildElement2 = DOMUtil.getNextSiblingElement(firstChildElement2);
            } else {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{str2, "(annotation?)", DOMUtil.getLocalName(firstChildElement2)}, firstChildElement2);
            }
            if (firstChildElement2 != null) {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{str2, "(annotation?)", DOMUtil.getLocalName(firstChildElement2)}, firstChildElement2);
            }
        } else {
            String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(firstChildElement);
            if (syntheticAnnotation2 != null) {
                identityConstraint.addAnnotation(traverseSyntheticAnnotation(element, syntheticAnnotation2, objArrCheckAttributes, false, xSDocumentInfo));
            }
        }
        String str3 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_XPATH];
        if (str3 == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{str2, SchemaSymbols.ATT_XPATH}, firstChildElement);
            return false;
        }
        String strTrim = XMLChar.trim(str3);
        try {
            identityConstraint.setSelector(new Selector(new Selector.XPath(strTrim, this.fSymbolTable, xSDocumentInfo.fNamespaceSupport), identityConstraint));
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            Element nextSiblingElement = DOMUtil.getNextSiblingElement(firstChildElement);
            if (nextSiblingElement == null) {
                reportSchemaError("s4s-elt-must-match.2", new Object[]{"identity constraint", "(annotation?, selector, field+)"}, firstChildElement);
                return false;
            }
            Element nextSiblingElement2 = nextSiblingElement;
            while (nextSiblingElement2 != null) {
                String localName3 = DOMUtil.getLocalName(nextSiblingElement2);
                String str4 = SchemaSymbols.ELT_FIELD;
                if (localName3.equals(str4)) {
                    Object[] objArrCheckAttributes2 = this.fAttrChecker.checkAttributes(nextSiblingElement2, false, xSDocumentInfo);
                    Element firstChildElement3 = DOMUtil.getFirstChildElement(nextSiblingElement2);
                    if (firstChildElement3 != null && DOMUtil.getLocalName(firstChildElement3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        identityConstraint.addAnnotation(traverseAnnotationDecl(firstChildElement3, objArrCheckAttributes2, false, xSDocumentInfo));
                        firstChildElement3 = DOMUtil.getNextSiblingElement(firstChildElement3);
                    }
                    if (firstChildElement3 != null) {
                        reportSchemaError("s4s-elt-must-match.1", new Object[]{str4, "(annotation?)", DOMUtil.getLocalName(firstChildElement3)}, firstChildElement3);
                    } else {
                        String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(nextSiblingElement2);
                        if (syntheticAnnotation3 != null) {
                            identityConstraint.addAnnotation(traverseSyntheticAnnotation(element, syntheticAnnotation3, objArrCheckAttributes2, false, xSDocumentInfo));
                        }
                    }
                    String str5 = (String) objArrCheckAttributes2[XSAttributeChecker.ATTIDX_XPATH];
                    if (str5 == null) {
                        reportSchemaError("s4s-att-must-appear", new Object[]{str4, SchemaSymbols.ATT_XPATH}, nextSiblingElement2);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                        return false;
                    }
                    String strTrim2 = XMLChar.trim(str5);
                    try {
                        identityConstraint.addField(new Field(new Field.XPath(strTrim2, this.fSymbolTable, xSDocumentInfo.fNamespaceSupport), identityConstraint));
                        nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                    } catch (XPathException e) {
                        reportSchemaError(e.getKey(), new Object[]{strTrim2}, nextSiblingElement2);
                        this.fAttrChecker.returnAttrArray(objArrCheckAttributes2, xSDocumentInfo);
                        return false;
                    }
                } else {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{"identity constraint", "(annotation?, selector, field+)", str4}, nextSiblingElement2);
                    nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2);
                }
            }
            return identityConstraint.getFieldCount() > 0;
        } catch (XPathException e2) {
            reportSchemaError(e2.getKey(), new Object[]{strTrim}, firstChildElement);
            this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
            return false;
        }
    }
}
