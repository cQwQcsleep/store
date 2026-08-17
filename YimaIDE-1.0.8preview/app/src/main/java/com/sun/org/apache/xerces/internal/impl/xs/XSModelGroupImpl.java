package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSModelGroup;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSModelGroupImpl implements XSModelGroup {
    public static final short MODELGROUP_ALL = 103;
    public static final short MODELGROUP_CHOICE = 101;
    public static final short MODELGROUP_SEQUENCE = 102;
    public short fCompositor;
    public XSParticleDecl[] fParticles = null;
    public int fParticleCount = 0;
    public XSObjectList fAnnotations = null;
    private String fDescription = null;

    private int maxEffectiveTotalRangeAllSeq() {
        int i = 0;
        for (int i2 = 0; i2 < this.fParticleCount; i2++) {
            int iMaxEffectiveTotalRange = this.fParticles[i2].maxEffectiveTotalRange();
            if (iMaxEffectiveTotalRange == -1) {
                return -1;
            }
            i += iMaxEffectiveTotalRange;
        }
        return i;
    }

    private int maxEffectiveTotalRangeChoice() {
        int iMaxEffectiveTotalRange = 0;
        if (this.fParticleCount > 0 && (iMaxEffectiveTotalRange = this.fParticles[0].maxEffectiveTotalRange()) == -1) {
            return -1;
        }
        for (int i = 1; i < this.fParticleCount; i++) {
            int iMaxEffectiveTotalRange2 = this.fParticles[i].maxEffectiveTotalRange();
            if (iMaxEffectiveTotalRange2 == -1) {
                return -1;
            }
            if (iMaxEffectiveTotalRange2 > iMaxEffectiveTotalRange) {
                iMaxEffectiveTotalRange = iMaxEffectiveTotalRange2;
            }
        }
        return iMaxEffectiveTotalRange;
    }

    private int minEffectiveTotalRangeAllSeq() {
        int iMinEffectiveTotalRange = 0;
        for (int i = 0; i < this.fParticleCount; i++) {
            iMinEffectiveTotalRange += this.fParticles[i].minEffectiveTotalRange();
        }
        return iMinEffectiveTotalRange;
    }

    private int minEffectiveTotalRangeChoice() {
        int iMinEffectiveTotalRange = this.fParticleCount > 0 ? this.fParticles[0].minEffectiveTotalRange() : 0;
        for (int i = 1; i < this.fParticleCount; i++) {
            int iMinEffectiveTotalRange2 = this.fParticles[i].minEffectiveTotalRange();
            if (iMinEffectiveTotalRange2 < iMinEffectiveTotalRange) {
                iMinEffectiveTotalRange = iMinEffectiveTotalRange2;
            }
        }
        return iMinEffectiveTotalRange;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModelGroup
    public XSAnnotation getAnnotation() {
        XSObjectList xSObjectList = this.fAnnotations;
        if (xSObjectList != null) {
            return (XSAnnotation) xSObjectList.item(0);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModelGroup
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModelGroup
    public short getCompositor() {
        short s = this.fCompositor;
        if (s == 101) {
            return (short) 2;
        }
        return s == 102 ? (short) 1 : (short) 3;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getName() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getNamespace() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSModelGroup
    public XSObjectList getParticles() {
        return new XSObjectListImpl(this.fParticles, this.fParticleCount);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 7;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.fParticleCount; i++) {
            if (!this.fParticles[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int maxEffectiveTotalRange() {
        return this.fCompositor == 101 ? maxEffectiveTotalRangeChoice() : maxEffectiveTotalRangeAllSeq();
    }

    public int minEffectiveTotalRange() {
        return this.fCompositor == 101 ? minEffectiveTotalRangeChoice() : minEffectiveTotalRangeAllSeq();
    }

    public void reset() {
        this.fCompositor = (short) 102;
        this.fParticles = null;
        this.fParticleCount = 0;
        this.fDescription = null;
        this.fAnnotations = null;
    }

    public String toString() {
        if (this.fDescription == null) {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.fCompositor == 103) {
                stringBuffer.append("all(");
            } else {
                stringBuffer.append('(');
            }
            if (this.fParticleCount > 0) {
                stringBuffer.append(this.fParticles[0].toString());
            }
            for (int i = 1; i < this.fParticleCount; i++) {
                if (this.fCompositor == 101) {
                    stringBuffer.append('|');
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(this.fParticles[i].toString());
            }
            stringBuffer.append(')');
            this.fDescription = stringBuffer.toString();
        }
        return this.fDescription;
    }
}
