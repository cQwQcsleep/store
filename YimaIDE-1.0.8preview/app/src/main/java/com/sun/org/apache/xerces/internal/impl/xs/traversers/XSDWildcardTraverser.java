package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSWildcardDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDWildcardTraverser extends XSDAbstractTraverser {
    public XSDWildcardTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0041  */
    public XSParticleDecl traverseAny(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        XSParticleDecl particleDecl;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        XSWildcardDecl xSWildcardDeclTraverseWildcardDecl = traverseWildcardDecl(element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar);
        if (xSWildcardDeclTraverseWildcardDecl != null) {
            int iIntValue = ((XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
            int iIntValue2 = ((XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
            if (iIntValue2 != 0) {
                XSDeclarationPool xSDeclarationPool = this.fSchemaHandler.fDeclPool;
                particleDecl = xSDeclarationPool != null ? xSDeclarationPool.getParticleDecl() : new XSParticleDecl();
                particleDecl.fType = (short) 2;
                particleDecl.fValue = xSWildcardDeclTraverseWildcardDecl;
                particleDecl.fMinOccurs = iIntValue;
                particleDecl.fMaxOccurs = iIntValue2;
                particleDecl.fAnnotations = xSWildcardDeclTraverseWildcardDecl.fAnnotations;
            } else {
                particleDecl = null;
            }
        } else {
            particleDecl = null;
        }
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return particleDecl;
    }

    public XSWildcardDecl traverseAnyAttribute(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        XSWildcardDecl xSWildcardDeclTraverseWildcardDecl = traverseWildcardDecl(element, objArrCheckAttributes, xSDocumentInfo, schemaGrammar);
        this.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return xSWildcardDeclTraverseWildcardDecl;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0055  */
    public XSWildcardDecl traverseWildcardDecl(Element element, Object[] objArr, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        XSObjectListImpl xSObjectListImpl;
        XSDWildcardTraverser xSDWildcardTraverser;
        Element element2;
        XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
        xSWildcardDecl.fType = ((XInt) objArr[XSAttributeChecker.ATTIDX_NAMESPACE]).shortValue();
        xSWildcardDecl.fNamespaceList = (String[]) objArr[XSAttributeChecker.ATTIDX_NAMESPACE_LIST];
        xSWildcardDecl.fProcessContents = ((XInt) objArr[XSAttributeChecker.ATTIDX_PROCESSCONTENTS]).shortValue();
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation = null;
        if (firstChildElement != null) {
            if (DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
                xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArr, false, xSDocumentInfo);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
            } else {
                String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation != null) {
                    xSDWildcardTraverser = this;
                    element2 = element;
                    xSAnnotationImplTraverseSyntheticAnnotation = xSDWildcardTraverser.traverseSyntheticAnnotation(element2, syntheticAnnotation, objArr, false, xSDocumentInfo);
                }
                if (firstChildElement != null) {
                    xSDWildcardTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{"wildcard", "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, element2);
                }
            }
            xSDWildcardTraverser = this;
            element2 = element;
            if (firstChildElement != null) {
                xSDWildcardTraverser.reportSchemaError("s4s-elt-must-match.1", new Object[]{"wildcard", "(annotation?)", DOMUtil.getLocalName(firstChildElement)}, element2);
            }
        } else {
            String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation2 != null) {
                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element, syntheticAnnotation2, objArr, false, xSDocumentInfo);
            }
        }
        if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        xSWildcardDecl.fAnnotations = xSObjectListImpl;
        return xSWildcardDecl;
    }
}
