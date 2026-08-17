package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.xs.SubstitutionGroupHandler;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import com.sun.org.apache.xerces.internal.xni.QName;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSEmptyCM implements XSCMValidator {
    private static final List<Object> EMPTY = new ArrayList(0);
    private static final short STATE_START = 0;

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public List<String> checkMinMaxBounds() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException {
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public boolean endContentModel(int[] iArr) {
        return iArr[0] >= 0;
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
        if (iArr[0] < 0) {
            iArr[0] = -2;
            return null;
        }
        iArr[0] = -1;
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public int[] startContentModel() {
        return new int[]{0};
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xs.models.XSCMValidator
    public List<Object> whatCanGoHere(int[] iArr) {
        return EMPTY;
    }
}
