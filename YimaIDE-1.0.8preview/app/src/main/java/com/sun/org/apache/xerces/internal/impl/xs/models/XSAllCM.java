package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.xs.SubstitutionGroupHandler;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import com.sun.org.apache.xerces.internal.xni.QName;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSAllCM implements XSCMValidator {
    private static final short STATE_CHILD = 1;
    private static final short STATE_START = 0;
    private static final short STATE_VALID = 1;
    private XSElementDecl[] fAllElements;
    private boolean fHasOptionalContent;
    private boolean[] fIsOptionalElement;
    private int fNumElements = 0;

    public XSAllCM(boolean z, int i) {
        this.fHasOptionalContent = z;
        this.fAllElements = new XSElementDecl[i];
        this.fIsOptionalElement = new boolean[i];
    }

    public void addElement(XSElementDecl xSElementDecl, boolean z) {
        XSElementDecl[] xSElementDeclArr = this.fAllElements;
        int i = this.fNumElements;
        xSElementDeclArr[i] = xSElementDecl;
        this.fIsOptionalElement[i] = z;
        this.fNumElements = i + 1;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public List<String> checkMinMaxBounds() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException {
        int i = 0;
        while (i < this.fNumElements) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.fNumElements; i3++) {
                XSElementDecl[] xSElementDeclArr = this.fAllElements;
                if (XSConstraints.overlapUPA(xSElementDeclArr[i], xSElementDeclArr[i3], substitutionGroupHandler)) {
                    throw new XMLSchemaException("cos-nonambig", new Object[]{this.fAllElements[i].toString(), this.fAllElements[i3].toString()});
                }
            }
            i = i2;
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean endContentModel(int[] iArr) {
        int i = iArr[0];
        if (i == -1 || i == -2) {
            return false;
        }
        if (this.fHasOptionalContent && i == 0) {
            return true;
        }
        for (int i2 = 0; i2 < this.fNumElements; i2++) {
            if (!this.fIsOptionalElement[i2] && iArr[i2 + 1] == 0) {
                return false;
            }
        }
        return true;
    }

    public Object findMatchingDecl(QName qName, SubstitutionGroupHandler substitutionGroupHandler) {
        XSElementDecl matchingElemDecl = null;
        for (int i = 0; i < this.fNumElements; i++) {
            matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, this.fAllElements[i]);
            if (matchingElemDecl != null) {
                return matchingElemDecl;
            }
        }
        return matchingElemDecl;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public String getTermName(int i) {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean isCompactedForUPA() {
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public int[] occurenceInfo(int[] iArr) {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public Object oneTransition(QName qName, int[] iArr, SubstitutionGroupHandler substitutionGroupHandler) {
        XSElementDecl matchingElemDecl;
        if (iArr[0] < 0) {
            iArr[0] = -2;
            return findMatchingDecl(qName, substitutionGroupHandler);
        }
        iArr[0] = 1;
        int i = 0;
        while (i < this.fNumElements) {
            int i2 = i + 1;
            if (iArr[i2] == 0 && (matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, this.fAllElements[i])) != null) {
                iArr[i2] = 1;
                return matchingElemDecl;
            }
            i = i2;
        }
        iArr[0] = -1;
        return findMatchingDecl(qName, substitutionGroupHandler);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public int[] startContentModel() {
        int[] iArr = new int[this.fNumElements + 1];
        for (int i = 0; i <= this.fNumElements; i++) {
            iArr[i] = 0;
        }
        return iArr;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public List<Object> whatCanGoHere(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < this.fNumElements) {
            int i2 = i + 1;
            if (iArr[i2] == 0) {
                arrayList.add(this.fAllElements[i]);
            }
            i = i2;
        }
        return arrayList;
    }
}
