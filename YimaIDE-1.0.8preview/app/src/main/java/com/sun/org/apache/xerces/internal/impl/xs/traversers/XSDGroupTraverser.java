package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import com.sun.org.apache.xerces.internal.impl.xs.XSDeclarationPool;
import com.sun.org.apache.xerces.internal.impl.xs.XSGroupDecl;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelGroupImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.util.XMLSymbols;
import com.sun.org.apache.xerces.internal.xni.QName;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class XSDGroupTraverser extends XSDAbstractParticleTraverser {
    public XSDGroupTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x007b  */
    /* JADX WARN: Code duplicated, block: B:21:0x0089  */
    /* JADX WARN: Code duplicated, block: B:23:0x0091  */
    /* JADX WARN: Code duplicated, block: B:24:0x009f  */
    /* JADX WARN: Code duplicated, block: B:26:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:27:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:29:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:30:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:33:0x00dd  */
    public XSGroupDecl traverseGlobal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        Element element2;
        XSAnnotationImpl xSAnnotationImpl;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        XSGroupDecl xSGroupDecl;
        String str;
        XSParticleDecl xSParticleDeclTraverseSequence;
        Object grpOrAttrGrpRedefinedByRestriction;
        XSObjectListImpl xSObjectListImpl;
        XSDGroupTraverser xSDGroupTraverser = this;
        Element element3 = element;
        xSDocumentInfo = xSDocumentInfo;
        Object[] objArrCheckAttributes = xSDGroupTraverser.fAttrChecker.checkAttributes(element3, true, xSDocumentInfo);
        String str2 = (String) objArrCheckAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (str2 == null) {
            xSDGroupTraverser.reportSchemaError("s4s-att-must-appear", new Object[]{"group (global)", "name"}, element3);
        }
        XSGroupDecl xSGroupDecl2 = new XSGroupDecl();
        Element firstChildElement = DOMUtil.getFirstChildElement(element3);
        XSGroupDecl xSGroupDecl3 = null;
        if (firstChildElement == null) {
            xSDGroupTraverser.reportSchemaError("s4s-elt-must-match.2", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"}, element3);
            element3 = element3;
            objArrCheckAttributes = objArrCheckAttributes;
            xSGroupDecl = xSGroupDecl2;
            xSParticleDeclTraverseSequence = null;
            xSAnnotationImpl = null;
            schemaGrammar = schemaGrammar;
        } else {
            String localName = firstChildElement.getLocalName();
            if (localName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                xSAnnotationImplTraverseSyntheticAnnotation = xSDGroupTraverser.traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, true, xSDocumentInfo);
                firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
                if (firstChildElement != null) {
                    localName = firstChildElement.getLocalName();
                }
            } else {
                String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element3);
                if (syntheticAnnotation != null) {
                    xSAnnotationImplTraverseSyntheticAnnotation = xSDGroupTraverser.traverseSyntheticAnnotation(element3, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo);
                } else {
                    element2 = firstChildElement;
                    xSAnnotationImpl = null;
                }
                if (element2 == null) {
                    xSDGroupTraverser.reportSchemaError("s4s-elt-must-match.2", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"}, element3);
                    xSGroupDecl = xSGroupDecl2;
                    str = "s4s-elt-must-match.1";
                } else {
                    if (localName.equals(SchemaSymbols.ELT_ALL)) {
                        schemaGrammar = schemaGrammar;
                        xSGroupDecl = xSGroupDecl2;
                        str = "s4s-elt-must-match.1";
                        xSDocumentInfo = xSDocumentInfo;
                        xSParticleDeclTraverseSequence = xSDGroupTraverser.traverseAll(element2, xSDocumentInfo, schemaGrammar, 4, xSGroupDecl);
                        xSDGroupTraverser = this;
                    } else {
                        xSGroupDecl = xSGroupDecl2;
                        str = "s4s-elt-must-match.1";
                        if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                            xSDGroupTraverser = this;
                            xSDocumentInfo = xSDocumentInfo;
                            schemaGrammar = schemaGrammar;
                            xSParticleDeclTraverseSequence = xSDGroupTraverser.traverseChoice(element2, xSDocumentInfo, schemaGrammar, 4, xSGroupDecl);
                        } else if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                            xSDGroupTraverser = this;
                            xSDocumentInfo = xSDocumentInfo;
                            schemaGrammar = schemaGrammar;
                            xSParticleDeclTraverseSequence = xSDGroupTraverser.traverseSequence(element2, xSDocumentInfo, schemaGrammar, 4, xSGroupDecl);
                        } else {
                            xSDGroupTraverser = this;
                            xSDGroupTraverser.reportSchemaError(str, new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(element2)}, element2);
                        }
                    }
                    if (element2 != null && DOMUtil.getNextSiblingElement(element2) != null) {
                        xSDGroupTraverser.reportSchemaError(str, new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element2))}, DOMUtil.getNextSiblingElement(element2));
                    }
                }
                xSParticleDeclTraverseSequence = null;
                if (element2 != null) {
                    xSDGroupTraverser.reportSchemaError(str, new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element2))}, DOMUtil.getNextSiblingElement(element2));
                }
            }
            element2 = firstChildElement;
            xSAnnotationImpl = xSAnnotationImplTraverseSyntheticAnnotation;
            if (element2 == null) {
                xSDGroupTraverser.reportSchemaError("s4s-elt-must-match.2", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"}, element3);
                xSGroupDecl = xSGroupDecl2;
                str = "s4s-elt-must-match.1";
            } else {
                if (localName.equals(SchemaSymbols.ELT_ALL)) {
                    schemaGrammar = schemaGrammar;
                    xSGroupDecl = xSGroupDecl2;
                    str = "s4s-elt-must-match.1";
                    xSDocumentInfo = xSDocumentInfo;
                    xSParticleDeclTraverseSequence = xSDGroupTraverser.traverseAll(element2, xSDocumentInfo, schemaGrammar, 4, xSGroupDecl);
                    xSDGroupTraverser = this;
                } else {
                    xSGroupDecl = xSGroupDecl2;
                    str = "s4s-elt-must-match.1";
                    if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                        xSDGroupTraverser = this;
                        xSDocumentInfo = xSDocumentInfo;
                        schemaGrammar = schemaGrammar;
                        xSParticleDeclTraverseSequence = xSDGroupTraverser.traverseChoice(element2, xSDocumentInfo, schemaGrammar, 4, xSGroupDecl);
                    } else if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                        xSDGroupTraverser = this;
                        xSDocumentInfo = xSDocumentInfo;
                        schemaGrammar = schemaGrammar;
                        xSParticleDeclTraverseSequence = xSDGroupTraverser.traverseSequence(element2, xSDocumentInfo, schemaGrammar, 4, xSGroupDecl);
                    } else {
                        xSDGroupTraverser = this;
                        xSDGroupTraverser.reportSchemaError(str, new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(element2)}, element2);
                    }
                }
                if (element2 != null) {
                    xSDGroupTraverser.reportSchemaError(str, new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element2))}, DOMUtil.getNextSiblingElement(element2));
                }
            }
            xSParticleDeclTraverseSequence = null;
            if (element2 != null) {
                xSDGroupTraverser.reportSchemaError(str, new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element2))}, DOMUtil.getNextSiblingElement(element2));
            }
        }
        if (str2 != null) {
            xSGroupDecl.fName = str2;
            xSGroupDecl.fTargetNamespace = xSDocumentInfo.fTargetNamespace;
            if (xSParticleDeclTraverseSequence == null) {
                xSParticleDeclTraverseSequence = XSConstraints.getEmptySequence();
            }
            xSGroupDecl.fModelGroup = (XSModelGroupImpl) xSParticleDeclTraverseSequence.fValue;
            if (xSAnnotationImpl != null) {
                xSObjectListImpl = new XSObjectListImpl();
                xSObjectListImpl.addXSObject(xSAnnotationImpl);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            xSGroupDecl.fAnnotations = xSObjectListImpl;
            if (schemaGrammar.getGlobalGroupDecl(xSGroupDecl.fName) == null) {
                schemaGrammar.addGlobalGroupDecl(xSGroupDecl);
            }
            String strSchemaDocument2SystemId = xSDGroupTraverser.fSchemaHandler.schemaDocument2SystemId(xSDocumentInfo);
            XSGroupDecl globalGroupDecl = schemaGrammar.getGlobalGroupDecl(xSGroupDecl.fName, strSchemaDocument2SystemId);
            if (globalGroupDecl == null) {
                schemaGrammar.addGlobalGroupDecl(xSGroupDecl, strSchemaDocument2SystemId);
            }
            XSDHandler xSDHandler = xSDGroupTraverser.fSchemaHandler;
            if (xSDHandler.fTolerateDuplicates) {
                if (globalGroupDecl == null) {
                    globalGroupDecl = xSGroupDecl;
                }
                xSDHandler.addGlobalGroupDecl(globalGroupDecl);
            } else {
                globalGroupDecl = xSGroupDecl;
            }
            xSGroupDecl3 = globalGroupDecl;
        }
        if (xSGroupDecl3 != null && (grpOrAttrGrpRedefinedByRestriction = xSDGroupTraverser.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(4, new QName(XMLSymbols.EMPTY_STRING, str2, str2, xSDocumentInfo.fTargetNamespace), xSDocumentInfo, element3)) != null) {
            schemaGrammar.addRedefinedGroupDecl(xSGroupDecl3, (XSGroupDecl) grpOrAttrGrpRedefinedByRestriction, xSDGroupTraverser.fSchemaHandler.element2Locator(element3));
        }
        xSDGroupTraverser.fAttrChecker.returnAttrArray(objArrCheckAttributes, xSDocumentInfo);
        return xSGroupDecl3;
    }

    public XSParticleDecl traverseLocal(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar) {
        XSGroupDecl xSGroupDecl;
        Object[] objArr;
        Element nextSiblingElement;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        XSParticleDecl xSParticleDeclCheckOccurrences;
        XSObjectListImpl xSObjectListImpl;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        QName qName = (QName) objArrCheckAttributes[XSAttributeChecker.ATTIDX_REF];
        XInt xInt = (XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt xInt2 = (XInt) objArrCheckAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSParticleDecl xSParticleDecl = null;
        if (qName == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"group (local)", "ref"}, element);
            xSGroupDecl = null;
        } else {
            xSGroupDecl = (XSGroupDecl) this.fSchemaHandler.getGlobalDecl(xSDocumentInfo, 4, qName, element);
        }
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                objArr = objArrCheckAttributes;
                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo);
                nextSiblingElement = firstChildElement;
            } else {
                objArr = objArrCheckAttributes;
                nextSiblingElement = firstChildElement;
                xSAnnotationImplTraverseSyntheticAnnotation = null;
            }
        } else {
            XSAnnotationImpl xSAnnotationImplTraverseAnnotationDecl = traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
            objArr = objArrCheckAttributes;
            nextSiblingElement = DOMUtil.getNextSiblingElement(firstChildElement);
            xSAnnotationImplTraverseSyntheticAnnotation = xSAnnotationImplTraverseAnnotationDecl;
        }
        if (nextSiblingElement != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{"group (local)", "(annotation?)", DOMUtil.getLocalName(element)}, element);
        }
        int iIntValue = xInt.intValue();
        int iIntValue2 = xInt2.intValue();
        if (xSGroupDecl != null && xSGroupDecl.fModelGroup != null && (iIntValue != 0 || iIntValue2 != 0)) {
            XSDeclarationPool xSDeclarationPool = this.fSchemaHandler.fDeclPool;
            XSParticleDecl particleDecl = xSDeclarationPool != null ? xSDeclarationPool.getParticleDecl() : new XSParticleDecl();
            particleDecl.fType = (short) 3;
            XSModelGroupImpl xSModelGroupImpl = xSGroupDecl.fModelGroup;
            particleDecl.fValue = xSModelGroupImpl;
            particleDecl.fMinOccurs = iIntValue;
            particleDecl.fMaxOccurs = iIntValue2;
            if (xSModelGroupImpl.fCompositor == 103) {
                xSParticleDeclCheckOccurrences = checkOccurrences(particleDecl, SchemaSymbols.ELT_GROUP, (Element) element.getParentNode(), 2, ((Long) objArr[XSAttributeChecker.ATTIDX_FROMDEFAULT]).longValue());
            } else {
                xSParticleDeclCheckOccurrences = particleDecl;
            }
            xSParticleDecl = xSParticleDeclCheckOccurrences;
            if (qName != null) {
                if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
                    xSObjectListImpl = new XSObjectListImpl();
                    xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
                } else {
                    xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
                }
                xSParticleDecl.fAnnotations = xSObjectListImpl;
            } else {
                xSParticleDecl.fAnnotations = xSGroupDecl.fAnnotations;
            }
        }
        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
        return xSParticleDecl;
    }
}
