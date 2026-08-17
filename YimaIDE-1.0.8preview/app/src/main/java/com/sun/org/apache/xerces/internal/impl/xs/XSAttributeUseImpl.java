package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSAttributeUse;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSAttributeUseImpl implements XSAttributeUse {
    public XSAttributeDecl fAttrDecl = null;
    public short fUse = 0;
    public short fConstraintType = 0;
    public ValidatedInfo fDefault = null;
    public XSObjectList fAnnotations = null;

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    @Deprecated
    public Object getActualVC() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.actualValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    @Deprecated
    public short getActualVCType() {
        if (getConstraintType() == 0) {
            return (short) 45;
        }
        return this.fDefault.actualValueType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    public XSAttributeDeclaration getAttrDeclaration() {
        return this.fAttrDecl;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    public short getConstraintType() {
        return this.fConstraintType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    @Deprecated
    public String getConstraintValue() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.stringValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    @Deprecated
    public ShortList getItemValueTypes() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.itemValueTypes;
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

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    public boolean getRequired() {
        return this.fUse == 1;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 4;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeUse
    public XSValue getValueConstraintValue() {
        return this.fDefault;
    }

    public void reset() {
        this.fDefault = null;
        this.fAttrDecl = null;
        this.fUse = (short) 0;
        this.fConstraintType = (short) 0;
        this.fAnnotations = null;
    }
}
