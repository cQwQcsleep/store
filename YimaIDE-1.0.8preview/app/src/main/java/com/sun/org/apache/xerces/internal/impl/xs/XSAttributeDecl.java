package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSComplexTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSAttributeDecl implements XSAttributeDeclaration {
    public static final short SCOPE_ABSENT = 0;
    public static final short SCOPE_GLOBAL = 1;
    public static final short SCOPE_LOCAL = 2;
    String fName = null;
    String fTargetNamespace = null;
    XSSimpleType fType = null;
    public QName fUnresolvedTypeName = null;
    short fConstraintType = 0;
    short fScope = 0;
    XSComplexTypeDecl fEnclosingCT = null;
    XSObjectList fAnnotations = null;
    ValidatedInfo fDefault = null;
    private XSNamespaceItem fNamespaceItem = null;

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    @Deprecated
    public Object getActualVC() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.actualValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    @Deprecated
    public short getActualVCType() {
        if (getConstraintType() == 0) {
            return (short) 45;
        }
        return this.fDefault.actualValueType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public XSAnnotation getAnnotation() {
        XSObjectList xSObjectList = this.fAnnotations;
        if (xSObjectList != null) {
            return (XSAnnotation) xSObjectList.item(0);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public short getConstraintType() {
        return this.fConstraintType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    @Deprecated
    public String getConstraintValue() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.stringValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return this.fEnclosingCT;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    @Deprecated
    public ShortList getItemValueTypes() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.itemValueTypes;
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

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public short getScope() {
        return this.fScope;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 1;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public XSSimpleTypeDefinition getTypeDefinition() {
        return this.fType;
    }

    public ValidatedInfo getValInfo() {
        return this.fDefault;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration
    public XSValue getValueConstraintValue() {
        return this.fDefault;
    }

    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fUnresolvedTypeName = null;
        this.fConstraintType = (short) 0;
        this.fScope = (short) 0;
        this.fDefault = null;
        this.fAnnotations = null;
    }

    public void setNamespaceItem(XSNamespaceItem xSNamespaceItem) {
        this.fNamespaceItem = xSNamespaceItem;
    }

    public void setValues(String str, String str2, XSSimpleType xSSimpleType, short s, short s2, ValidatedInfo validatedInfo, XSComplexTypeDecl xSComplexTypeDecl, XSObjectList xSObjectList) {
        this.fName = str;
        this.fTargetNamespace = str2;
        this.fType = xSSimpleType;
        this.fConstraintType = s;
        this.fScope = s2;
        this.fDefault = validatedInfo;
        this.fEnclosingCT = xSComplexTypeDecl;
        this.fAnnotations = xSObjectList;
    }
}
