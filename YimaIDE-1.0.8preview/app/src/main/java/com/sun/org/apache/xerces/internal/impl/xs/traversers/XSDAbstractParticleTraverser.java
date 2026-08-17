package com.sun.org.apache.xerces.internal.impl.xs.traversers;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.impl.xs.XSAnnotationImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSModelGroupImpl;
import com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XInt;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.util.DOMUtil;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class XSDAbstractParticleTraverser extends XSDAbstractTraverser {
    ParticleArray fPArray;

    public static class ParticleArray {
        XSParticleDecl[] fParticles = new XSParticleDecl[10];
        int[] fPos = new int[5];
        int fContextCount = 0;

        public void addParticle(XSParticleDecl xSParticleDecl) {
            int i = this.fPos[this.fContextCount];
            XSParticleDecl[] xSParticleDeclArr = this.fParticles;
            if (i == xSParticleDeclArr.length) {
                XSParticleDecl[] xSParticleDeclArr2 = new XSParticleDecl[i * 2];
                System.arraycopy(xSParticleDeclArr, 0, xSParticleDeclArr2, 0, i);
                this.fParticles = xSParticleDeclArr2;
            }
            XSParticleDecl[] xSParticleDeclArr3 = this.fParticles;
            int[] iArr = this.fPos;
            int i2 = this.fContextCount;
            int i3 = iArr[i2];
            iArr[i2] = i3 + 1;
            xSParticleDeclArr3[i3] = xSParticleDecl;
        }

        public int getParticleCount() {
            int[] iArr = this.fPos;
            int i = this.fContextCount;
            return iArr[i] - iArr[i - 1];
        }

        public XSParticleDecl[] popContext() {
            int[] iArr = this.fPos;
            int i = this.fContextCount;
            int i2 = iArr[i] - iArr[i - 1];
            XSParticleDecl[] xSParticleDeclArr = null;
            if (i2 != 0) {
                XSParticleDecl[] xSParticleDeclArr2 = new XSParticleDecl[i2];
                System.arraycopy(this.fParticles, iArr[i - 1], xSParticleDeclArr2, 0, i2);
                for (int i3 = this.fPos[this.fContextCount - 1]; i3 < this.fPos[this.fContextCount]; i3++) {
                    this.fParticles[i3] = null;
                }
                xSParticleDeclArr = xSParticleDeclArr2;
            }
            this.fContextCount--;
            return xSParticleDeclArr;
        }

        public void pushContext() {
            int i = this.fContextCount + 1;
            this.fContextCount = i;
            int[] iArr = this.fPos;
            if (i == iArr.length) {
                int[] iArr2 = new int[i * 2];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                this.fPos = iArr2;
            }
            int[] iArr3 = this.fPos;
            int i2 = this.fContextCount;
            iArr3[i2] = iArr3[i2 - 1];
        }
    }

    public XSDAbstractParticleTraverser(XSDHandler xSDHandler, XSAttributeChecker xSAttributeChecker) {
        super(xSDHandler, xSAttributeChecker);
        this.fPArray = new ParticleArray();
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0043  */
    /* JADX WARN: Code duplicated, block: B:17:0x004f  */
    /* JADX WARN: Code duplicated, block: B:18:0x005e  */
    /* JADX WARN: Code duplicated, block: B:20:0x0067  */
    /* JADX WARN: Code duplicated, block: B:22:0x0075  */
    /* JADX WARN: Code duplicated, block: B:24:0x007d  */
    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    /* JADX WARN: Code duplicated, block: B:28:0x0091  */
    /* JADX WARN: Code duplicated, block: B:30:0x0099  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:33:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:36:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:37:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:40:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:44:0x0107  */
    /* JADX WARN: Code duplicated, block: B:45:0x010a  */
    /* JADX WARN: Code duplicated, block: B:48:0x0120  */
    /* JADX WARN: Code duplicated, block: B:49:0x0129  */
    /* JADX WARN: Code duplicated, block: B:52:0x0147  */
    /* JADX WARN: Code duplicated, block: B:54:0x014b  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e8 A[SYNTHETIC] */
    private XSParticleDecl traverseSeqChoice(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, int i, boolean z, XSObject xSObject) {
        Object[] objArr;
        Element element2;
        XSAnnotationImpl xSAnnotationImpl;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        Element nextSiblingElement;
        short s;
        XSObjectListImpl xSObjectListImpl;
        String str;
        String localName;
        Object[] objArr2;
        XSParticleDecl xSParticleDeclTraverseAny;
        XSParticleDecl xSParticleDeclTraverseSequence;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo);
                objArr = objArrCheckAttributes;
            } else {
                objArr = objArrCheckAttributes;
                element2 = firstChildElement;
                xSAnnotationImpl = null;
            }
            this.fPArray.pushContext();
            for (nextSiblingElement = element2; nextSiblingElement != null; nextSiblingElement = DOMUtil.getNextSiblingElement(nextSiblingElement)) {
                localName = DOMUtil.getLocalName(nextSiblingElement);
                if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                    xSParticleDeclTraverseAny = this.fSchemaHandler.fElementTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar, 0, xSObject);
                } else if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                    xSParticleDeclTraverseAny = this.fSchemaHandler.fGroupTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                    if (hasAllContent(xSParticleDeclTraverseAny)) {
                        reportSchemaError("cos-all-limited.1.2", null, nextSiblingElement);
                        xSParticleDeclTraverseAny = null;
                    }
                } else {
                    if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                        xSParticleDeclTraverseSequence = traverseChoice(nextSiblingElement, xSDocumentInfo, schemaGrammar, 0, xSObject);
                    } else if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                        xSParticleDeclTraverseSequence = traverseSequence(nextSiblingElement, xSDocumentInfo, schemaGrammar, 0, xSObject);
                    } else if (localName.equals(SchemaSymbols.ELT_ANY)) {
                        xSParticleDeclTraverseAny = this.fSchemaHandler.fWildCardTraverser.traverseAny(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                    } else {
                        if (z) {
                            objArr2 = new Object[]{"choice", "(annotation?, (element | group | choice | sequence | any)*)", DOMUtil.getLocalName(nextSiblingElement)};
                        } else {
                            objArr2 = new Object[]{"sequence", "(annotation?, (element | group | choice | sequence | any)*)", DOMUtil.getLocalName(nextSiblingElement)};
                        }
                        reportSchemaError("s4s-elt-must-match.1", objArr2, nextSiblingElement);
                        xSParticleDeclTraverseAny = null;
                    }
                    xSParticleDeclTraverseAny = xSParticleDeclTraverseSequence;
                }
                if (xSParticleDeclTraverseAny != null) {
                    this.fPArray.addParticle(xSParticleDeclTraverseAny);
                }
            }
            XInt xInt = (XInt) objArr[XSAttributeChecker.ATTIDX_MINOCCURS];
            XInt xInt2 = (XInt) objArr[XSAttributeChecker.ATTIDX_MAXOCCURS];
            Long l = (Long) objArr[XSAttributeChecker.ATTIDX_FROMDEFAULT];
            XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
            if (z) {
                s = 101;
            } else {
                s = 102;
            }
            xSModelGroupImpl.fCompositor = s;
            xSModelGroupImpl.fParticleCount = this.fPArray.getParticleCount();
            xSModelGroupImpl.fParticles = this.fPArray.popContext();
            if (xSAnnotationImpl != null) {
                xSObjectListImpl = new XSObjectListImpl();
                xSObjectListImpl.addXSObject(xSAnnotationImpl);
            } else {
                xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
            }
            xSModelGroupImpl.fAnnotations = xSObjectListImpl;
            XSParticleDecl xSParticleDecl = new XSParticleDecl();
            xSParticleDecl.fType = (short) 3;
            xSParticleDecl.fMinOccurs = xInt.intValue();
            xSParticleDecl.fMaxOccurs = xInt2.intValue();
            xSParticleDecl.fValue = xSModelGroupImpl;
            xSParticleDecl.fAnnotations = xSObjectListImpl;
            if (z) {
                str = SchemaSymbols.ELT_CHOICE;
            } else {
                str = SchemaSymbols.ELT_SEQUENCE;
            }
            XSParticleDecl xSParticleDeclCheckOccurrences = checkOccurrences(xSParticleDecl, str, (Element) element.getParentNode(), i, l.longValue());
            this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
            return xSParticleDeclCheckOccurrences;
        }
        xSAnnotationImplTraverseSyntheticAnnotation = traverseAnnotationDecl(firstChildElement, objArrCheckAttributes, false, xSDocumentInfo);
        firstChildElement = DOMUtil.getNextSiblingElement(firstChildElement);
        objArr = objArrCheckAttributes;
        element2 = firstChildElement;
        xSAnnotationImpl = xSAnnotationImplTraverseSyntheticAnnotation;
        this.fPArray.pushContext();
        while (nextSiblingElement != null) {
            localName = DOMUtil.getLocalName(nextSiblingElement);
            if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                xSParticleDeclTraverseAny = this.fSchemaHandler.fElementTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar, 0, xSObject);
            } else if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                xSParticleDeclTraverseAny = this.fSchemaHandler.fGroupTraverser.traverseLocal(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                if (hasAllContent(xSParticleDeclTraverseAny)) {
                    reportSchemaError("cos-all-limited.1.2", null, nextSiblingElement);
                    xSParticleDeclTraverseAny = null;
                }
            } else {
                if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                    xSParticleDeclTraverseSequence = traverseChoice(nextSiblingElement, xSDocumentInfo, schemaGrammar, 0, xSObject);
                } else if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                    xSParticleDeclTraverseSequence = traverseSequence(nextSiblingElement, xSDocumentInfo, schemaGrammar, 0, xSObject);
                } else if (localName.equals(SchemaSymbols.ELT_ANY)) {
                    xSParticleDeclTraverseAny = this.fSchemaHandler.fWildCardTraverser.traverseAny(nextSiblingElement, xSDocumentInfo, schemaGrammar);
                } else {
                    if (z) {
                        objArr2 = new Object[]{"choice", "(annotation?, (element | group | choice | sequence | any)*)", DOMUtil.getLocalName(nextSiblingElement)};
                    } else {
                        objArr2 = new Object[]{"sequence", "(annotation?, (element | group | choice | sequence | any)*)", DOMUtil.getLocalName(nextSiblingElement)};
                    }
                    reportSchemaError("s4s-elt-must-match.1", objArr2, nextSiblingElement);
                    xSParticleDeclTraverseAny = null;
                }
                xSParticleDeclTraverseAny = xSParticleDeclTraverseSequence;
            }
            if (xSParticleDeclTraverseAny != null) {
                this.fPArray.addParticle(xSParticleDeclTraverseAny);
            }
        }
        XInt xInt3 = (XInt) objArr[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt xInt4 = (XInt) objArr[XSAttributeChecker.ATTIDX_MAXOCCURS];
        Long l2 = (Long) objArr[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        XSModelGroupImpl xSModelGroupImpl2 = new XSModelGroupImpl();
        if (z) {
            s = 101;
        } else {
            s = 102;
        }
        xSModelGroupImpl2.fCompositor = s;
        xSModelGroupImpl2.fParticleCount = this.fPArray.getParticleCount();
        xSModelGroupImpl2.fParticles = this.fPArray.popContext();
        if (xSAnnotationImpl != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImpl);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        xSModelGroupImpl2.fAnnotations = xSObjectListImpl;
        XSParticleDecl xSParticleDecl2 = new XSParticleDecl();
        xSParticleDecl2.fType = (short) 3;
        xSParticleDecl2.fMinOccurs = xInt3.intValue();
        xSParticleDecl2.fMaxOccurs = xInt4.intValue();
        xSParticleDecl2.fValue = xSModelGroupImpl2;
        xSParticleDecl2.fAnnotations = xSObjectListImpl;
        if (z) {
            str = SchemaSymbols.ELT_CHOICE;
        } else {
            str = SchemaSymbols.ELT_SEQUENCE;
        }
        XSParticleDecl xSParticleDeclCheckOccurrences2 = checkOccurrences(xSParticleDecl2, str, (Element) element.getParentNode(), i, l2.longValue());
        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
        return xSParticleDeclCheckOccurrences2;
    }

    public boolean hasAllContent(XSParticleDecl xSParticleDecl) {
        return xSParticleDecl != null && xSParticleDecl.fType == 3 && ((XSModelGroupImpl) xSParticleDecl.fValue).fCompositor == 103;
    }

    public XSParticleDecl traverseAll(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, int i, XSObject xSObject) {
        Object[] objArr;
        Element nextSiblingElement;
        XSAnnotationImpl xSAnnotationImplTraverseSyntheticAnnotation;
        XSObjectListImpl xSObjectListImpl;
        XSParticleDecl xSParticleDeclTraverseLocal;
        Object[] objArrCheckAttributes = this.fAttrChecker.checkAttributes(element, false, xSDocumentInfo);
        Element firstChildElement = DOMUtil.getFirstChildElement(element);
        if (firstChildElement == null || !DOMUtil.getLocalName(firstChildElement).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                objArr = objArrCheckAttributes;
                nextSiblingElement = firstChildElement;
                xSAnnotationImplTraverseSyntheticAnnotation = traverseSyntheticAnnotation(element, syntheticAnnotation, objArrCheckAttributes, false, xSDocumentInfo);
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
        this.fPArray.pushContext();
        for (Element nextSiblingElement2 = nextSiblingElement; nextSiblingElement2 != null; nextSiblingElement2 = DOMUtil.getNextSiblingElement(nextSiblingElement2)) {
            if (DOMUtil.getLocalName(nextSiblingElement2).equals(SchemaSymbols.ELT_ELEMENT)) {
                xSParticleDeclTraverseLocal = this.fSchemaHandler.fElementTraverser.traverseLocal(nextSiblingElement2, xSDocumentInfo, schemaGrammar, 1, xSObject);
            } else {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{"all", "(annotation?, element*)", DOMUtil.getLocalName(nextSiblingElement2)}, nextSiblingElement2);
                xSParticleDeclTraverseLocal = null;
            }
            if (xSParticleDeclTraverseLocal != null) {
                this.fPArray.addParticle(xSParticleDeclTraverseLocal);
            }
        }
        XInt xInt = (XInt) objArr[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt xInt2 = (XInt) objArr[XSAttributeChecker.ATTIDX_MAXOCCURS];
        Long l = (Long) objArr[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        XSModelGroupImpl xSModelGroupImpl = new XSModelGroupImpl();
        xSModelGroupImpl.fCompositor = (short) 103;
        xSModelGroupImpl.fParticleCount = this.fPArray.getParticleCount();
        xSModelGroupImpl.fParticles = this.fPArray.popContext();
        if (xSAnnotationImplTraverseSyntheticAnnotation != null) {
            xSObjectListImpl = new XSObjectListImpl();
            xSObjectListImpl.addXSObject(xSAnnotationImplTraverseSyntheticAnnotation);
        } else {
            xSObjectListImpl = XSObjectListImpl.EMPTY_LIST;
        }
        xSModelGroupImpl.fAnnotations = xSObjectListImpl;
        XSParticleDecl xSParticleDecl = new XSParticleDecl();
        xSParticleDecl.fType = (short) 3;
        xSParticleDecl.fMinOccurs = xInt.intValue();
        xSParticleDecl.fMaxOccurs = xInt2.intValue();
        xSParticleDecl.fValue = xSModelGroupImpl;
        xSParticleDecl.fAnnotations = xSObjectListImpl;
        XSParticleDecl xSParticleDeclCheckOccurrences = checkOccurrences(xSParticleDecl, SchemaSymbols.ELT_ALL, (Element) element.getParentNode(), i, l.longValue());
        this.fAttrChecker.returnAttrArray(objArr, xSDocumentInfo);
        return xSParticleDeclCheckOccurrences;
    }

    public XSParticleDecl traverseChoice(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, int i, XSObject xSObject) {
        return traverseSeqChoice(element, xSDocumentInfo, schemaGrammar, i, true, xSObject);
    }

    public XSParticleDecl traverseSequence(Element element, XSDocumentInfo xSDocumentInfo, SchemaGrammar schemaGrammar, int i, XSObject xSObject) {
        return traverseSeqChoice(element, xSDocumentInfo, schemaGrammar, i, false, xSObject);
    }
}
