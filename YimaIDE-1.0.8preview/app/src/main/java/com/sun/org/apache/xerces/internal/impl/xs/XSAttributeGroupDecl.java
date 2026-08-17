package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition;
import com.sun.org.apache.xerces.internal.xs.XSAttributeUse;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSWildcard;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSAttributeGroupDecl implements XSAttributeGroupDefinition {
    private static final int INITIAL_SIZE = 5;
    public XSObjectList fAnnotations;
    public String fName = null;
    public String fTargetNamespace = null;
    int fAttrUseNum = 0;
    XSAttributeUseImpl[] fAttributeUses = new XSAttributeUseImpl[5];
    public XSWildcardDecl fAttributeWC = null;
    public String fIDAttrName = null;
    protected XSObjectListImpl fAttrUses = null;
    private XSNamespaceItem fNamespaceItem = null;

    public static final XSAttributeUseImpl[] resize(XSAttributeUseImpl[] xSAttributeUseImplArr, int i) {
        XSAttributeUseImpl[] xSAttributeUseImplArr2 = new XSAttributeUseImpl[i];
        System.arraycopy(xSAttributeUseImplArr, 0, xSAttributeUseImplArr2, 0, Math.min(xSAttributeUseImplArr.length, i));
        return xSAttributeUseImplArr2;
    }

    public String addAttributeUse(XSAttributeUseImpl xSAttributeUseImpl) {
        if (xSAttributeUseImpl.fUse != 2 && xSAttributeUseImpl.fAttrDecl.fType.isIDType()) {
            String str = this.fIDAttrName;
            if (str != null) {
                return str;
            }
            this.fIDAttrName = xSAttributeUseImpl.fAttrDecl.fName;
        }
        int i = this.fAttrUseNum;
        XSAttributeUseImpl[] xSAttributeUseImplArr = this.fAttributeUses;
        if (i == xSAttributeUseImplArr.length) {
            this.fAttributeUses = resize(xSAttributeUseImplArr, i * 2);
        }
        XSAttributeUseImpl[] xSAttributeUseImplArr2 = this.fAttributeUses;
        int i2 = this.fAttrUseNum;
        this.fAttrUseNum = i2 + 1;
        xSAttributeUseImplArr2[i2] = xSAttributeUseImpl;
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition
    public XSAnnotation getAnnotation() {
        XSObjectList xSObjectList = this.fAnnotations;
        if (xSObjectList != null) {
            return (XSAnnotation) xSObjectList.item(0);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    public XSAttributeUse getAttributeUse(String str, String str2) {
        for (int i = 0; i < this.fAttrUseNum; i++) {
            XSAttributeUseImpl xSAttributeUseImpl = this.fAttributeUses[i];
            XSAttributeDecl xSAttributeDecl = xSAttributeUseImpl.fAttrDecl;
            if (xSAttributeDecl.fTargetNamespace == str && xSAttributeDecl.fName == str2) {
                return xSAttributeUseImpl;
            }
        }
        return null;
    }

    public XSAttributeUse getAttributeUseNoProhibited(String str, String str2) {
        for (int i = 0; i < this.fAttrUseNum; i++) {
            XSAttributeUseImpl xSAttributeUseImpl = this.fAttributeUses[i];
            XSAttributeDecl xSAttributeDecl = xSAttributeUseImpl.fAttrDecl;
            if (xSAttributeDecl.fTargetNamespace == str && xSAttributeDecl.fName == str2 && xSAttributeUseImpl.fUse != 2) {
                return xSAttributeUseImpl;
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition
    public XSObjectList getAttributeUses() {
        if (this.fAttrUses == null) {
            this.fAttrUses = new XSObjectListImpl(this.fAttributeUses, this.fAttrUseNum);
        }
        return this.fAttrUses;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeGroupDefinition
    public XSWildcard getAttributeWildcard() {
        return this.fAttributeWC;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getName() {
        return this.fName;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getNamespace() {
        return this.fTargetNamespace;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 5;
    }

    public void removeProhibitedAttrs() {
        int i = this.fAttrUseNum;
        if (i == 0) {
            return;
        }
        XSAttributeUseImpl[] xSAttributeUseImplArr = new XSAttributeUseImpl[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.fAttrUseNum; i3++) {
            XSAttributeUseImpl xSAttributeUseImpl = this.fAttributeUses[i3];
            if (xSAttributeUseImpl.fUse != 2) {
                xSAttributeUseImplArr[i2] = xSAttributeUseImpl;
                i2++;
            }
        }
        this.fAttributeUses = xSAttributeUseImplArr;
        this.fAttrUseNum = i2;
    }

    public void replaceAttributeUse(XSAttributeUse xSAttributeUse, XSAttributeUseImpl xSAttributeUseImpl) {
        for (int i = 0; i < this.fAttrUseNum; i++) {
            XSAttributeUseImpl[] xSAttributeUseImplArr = this.fAttributeUses;
            if (xSAttributeUseImplArr[i] == xSAttributeUse) {
                xSAttributeUseImplArr[i] = xSAttributeUseImpl;
            }
        }
    }

    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        for (int i = 0; i < this.fAttrUseNum; i++) {
            this.fAttributeUses[i] = null;
        }
        this.fAttrUseNum = 0;
        this.fAttributeWC = null;
        this.fAnnotations = null;
        this.fIDAttrName = null;
    }

    public void setNamespaceItem(XSNamespaceItem xSNamespaceItem) {
        this.fNamespaceItem = xSNamespaceItem;
    }

    public Object[] validRestrictionOf(String str, XSAttributeGroupDecl xSAttributeGroupDecl) {
        for (int i = 0; i < this.fAttrUseNum; i++) {
            XSAttributeUseImpl xSAttributeUseImpl = this.fAttributeUses[i];
            XSAttributeDecl xSAttributeDecl = xSAttributeUseImpl.fAttrDecl;
            XSAttributeUseImpl xSAttributeUseImpl2 = (XSAttributeUseImpl) xSAttributeGroupDecl.getAttributeUse(xSAttributeDecl.fTargetNamespace, xSAttributeDecl.fName);
            if (xSAttributeUseImpl2 == null) {
                XSWildcardDecl xSWildcardDecl = xSAttributeGroupDecl.fAttributeWC;
                if (xSWildcardDecl == null) {
                    return new Object[]{str, xSAttributeDecl.fName, "derivation-ok-restriction.2.2.a"};
                }
                if (!xSWildcardDecl.allowNamespace(xSAttributeDecl.fTargetNamespace)) {
                    String str2 = xSAttributeDecl.fName;
                    String str3 = xSAttributeDecl.fTargetNamespace;
                    if (str3 == null) {
                        str3 = "";
                    }
                    return new Object[]{str, str2, str3, "derivation-ok-restriction.2.2.b"};
                }
            } else {
                if (xSAttributeUseImpl2.getRequired() && !xSAttributeUseImpl.getRequired()) {
                    return new Object[]{str, xSAttributeDecl.fName, xSAttributeUseImpl.fUse == 0 ? SchemaSymbols.ATTVAL_OPTIONAL : SchemaSymbols.ATTVAL_PROHIBITED, "derivation-ok-restriction.2.1.1"};
                }
                if (xSAttributeUseImpl.fUse == 2) {
                    continue;
                } else {
                    XSAttributeDecl xSAttributeDecl2 = xSAttributeUseImpl2.fAttrDecl;
                    XSSimpleType xSSimpleType = xSAttributeDecl.fType;
                    XSSimpleType xSSimpleType2 = xSAttributeDecl2.fType;
                    if (!XSConstraints.checkSimpleDerivationOk(xSSimpleType, xSSimpleType2, xSSimpleType2.getFinal())) {
                        return new Object[]{str, xSAttributeDecl.fName, xSAttributeDecl.fType.getName(), xSAttributeDecl2.fType.getName(), "derivation-ok-restriction.2.1.2"};
                    }
                    short constraintType = xSAttributeUseImpl2.fConstraintType;
                    if (constraintType == 0) {
                        constraintType = xSAttributeDecl2.getConstraintType();
                    }
                    short constraintType2 = xSAttributeUseImpl.fConstraintType;
                    if (constraintType2 == 0) {
                        constraintType2 = xSAttributeDecl.getConstraintType();
                    }
                    if (constraintType != 2) {
                        continue;
                    } else {
                        if (constraintType2 != 2) {
                            return new Object[]{str, xSAttributeDecl.fName, "derivation-ok-restriction.2.1.3.a"};
                        }
                        ValidatedInfo validatedInfo = xSAttributeUseImpl2.fDefault;
                        if (validatedInfo == null) {
                            validatedInfo = xSAttributeDecl2.fDefault;
                        }
                        ValidatedInfo validatedInfo2 = xSAttributeUseImpl.fDefault;
                        if (validatedInfo2 == null) {
                            validatedInfo2 = xSAttributeDecl.fDefault;
                        }
                        if (!validatedInfo.actualValue.equals(validatedInfo2.actualValue)) {
                            return new Object[]{str, xSAttributeDecl.fName, validatedInfo2.stringValue(), validatedInfo.stringValue(), "derivation-ok-restriction.2.1.3.b"};
                        }
                    }
                }
            }
        }
        for (int i2 = 0; i2 < xSAttributeGroupDecl.fAttrUseNum; i2++) {
            XSAttributeUseImpl xSAttributeUseImpl3 = xSAttributeGroupDecl.fAttributeUses[i2];
            if (xSAttributeUseImpl3.fUse == 1) {
                XSAttributeDecl xSAttributeDecl3 = xSAttributeUseImpl3.fAttrDecl;
                if (getAttributeUse(xSAttributeDecl3.fTargetNamespace, xSAttributeDecl3.fName) == null) {
                    return new Object[]{str, xSAttributeUseImpl3.fAttrDecl.fName, "derivation-ok-restriction.3"};
                }
            }
        }
        XSWildcardDecl xSWildcardDecl2 = this.fAttributeWC;
        if (xSWildcardDecl2 == null) {
            return null;
        }
        XSWildcardDecl xSWildcardDecl3 = xSAttributeGroupDecl.fAttributeWC;
        if (xSWildcardDecl3 == null) {
            return new Object[]{str, "derivation-ok-restriction.4.1"};
        }
        if (!xSWildcardDecl2.isSubsetOf(xSWildcardDecl3)) {
            return new Object[]{str, "derivation-ok-restriction.4.2"};
        }
        if (this.fAttributeWC.weakerProcessContents(xSAttributeGroupDecl.fAttributeWC)) {
            return new Object[]{str, this.fAttributeWC.getProcessContentsAsString(), xSAttributeGroupDecl.fAttributeWC.getProcessContentsAsString(), "derivation-ok-restriction.4.3"};
        }
        return null;
    }
}
