package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.identity.IdentityConstraint;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import com.sun.org.apache.xerces.internal.xs.XSComplexTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSElementDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSNamedMap;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSElementDecl implements XSElementDeclaration {
    private static final short ABSTRACT = 8;
    private static final short CONSTRAINT_MASK = 3;
    static final int INITIAL_SIZE = 2;
    private static final short NILLABLE = 4;
    public static final short SCOPE_ABSENT = 0;
    public static final short SCOPE_GLOBAL = 1;
    public static final short SCOPE_LOCAL = 2;
    public String fName = null;
    public String fTargetNamespace = null;
    public XSTypeDefinition fType = null;
    public QName fUnresolvedTypeName = null;
    short fMiscFlags = 0;
    public short fScope = 0;
    XSComplexTypeDecl fEnclosingCT = null;
    public short fBlock = 0;
    public short fFinal = 0;
    public XSObjectList fAnnotations = null;
    public ValidatedInfo fDefault = null;
    public XSElementDecl fSubGroup = null;
    int fIDCPos = 0;
    IdentityConstraint[] fIDConstraints = new IdentityConstraint[2];
    private XSNamespaceItem fNamespaceItem = null;
    private String fDescription = null;

    public static final IdentityConstraint[] resize(IdentityConstraint[] identityConstraintArr, int i) {
        IdentityConstraint[] identityConstraintArr2 = new IdentityConstraint[i];
        System.arraycopy(identityConstraintArr, 0, identityConstraintArr2, 0, Math.min(identityConstraintArr.length, i));
        return identityConstraintArr2;
    }

    public void addIDConstraint(IdentityConstraint identityConstraint) {
        int i = this.fIDCPos;
        IdentityConstraint[] identityConstraintArr = this.fIDConstraints;
        if (i == identityConstraintArr.length) {
            this.fIDConstraints = resize(identityConstraintArr, i * 2);
        }
        IdentityConstraint[] identityConstraintArr2 = this.fIDConstraints;
        int i2 = this.fIDCPos;
        this.fIDCPos = i2 + 1;
        identityConstraintArr2[i2] = identityConstraint;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public boolean getAbstract() {
        return (this.fMiscFlags & 8) != 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    @Deprecated
    public Object getActualVC() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.actualValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    @Deprecated
    public short getActualVCType() {
        if (getConstraintType() == 0) {
            return (short) 45;
        }
        return this.fDefault.actualValueType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSAnnotation getAnnotation() {
        XSObjectList xSObjectList = this.fAnnotations;
        if (xSObjectList != null) {
            return (XSAnnotation) xSObjectList.item(0);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSObjectList getAnnotations() {
        XSObjectList xSObjectList = this.fAnnotations;
        return xSObjectList != null ? xSObjectList : XSObjectListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public short getConstraintType() {
        return (short) (this.fMiscFlags & 3);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    @Deprecated
    public String getConstraintValue() {
        if (getConstraintType() == 0) {
            return null;
        }
        return this.fDefault.stringValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public short getDisallowedSubstitutions() {
        return this.fBlock;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return this.fEnclosingCT;
    }

    public IdentityConstraint[] getIDConstraints() {
        int i = this.fIDCPos;
        if (i == 0) {
            return null;
        }
        IdentityConstraint[] identityConstraintArr = this.fIDConstraints;
        if (i < identityConstraintArr.length) {
            this.fIDConstraints = resize(identityConstraintArr, i);
        }
        return this.fIDConstraints;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSNamedMap getIdentityConstraints() {
        return new XSNamedMapImpl(this.fIDConstraints, this.fIDCPos);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
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

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public boolean getNillable() {
        return (this.fMiscFlags & 4) != 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public short getScope() {
        return this.fScope;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSElementDeclaration getSubstitutionGroupAffiliation() {
        return this.fSubGroup;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public short getSubstitutionGroupExclusions() {
        return this.fFinal;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return (short) 2;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSTypeDefinition getTypeDefinition() {
        return this.fType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public XSValue getValueConstraintValue() {
        return this.fDefault;
    }

    public int hashCode() {
        int iHashCode = this.fName.hashCode();
        String str = this.fTargetNamespace;
        return str != null ? (iHashCode << 16) + str.hashCode() : iHashCode;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public boolean isDisallowedSubstitution(short s) {
        return (this.fBlock & s) != 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSElementDeclaration
    public boolean isSubstitutionGroupExclusion(short s) {
        return (this.fFinal & s) != 0;
    }

    public void reset() {
        this.fScope = (short) 0;
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fUnresolvedTypeName = null;
        this.fMiscFlags = (short) 0;
        this.fBlock = (short) 0;
        this.fFinal = (short) 0;
        this.fDefault = null;
        this.fAnnotations = null;
        this.fSubGroup = null;
        for (int i = 0; i < this.fIDCPos; i++) {
            this.fIDConstraints[i] = null;
        }
        this.fIDCPos = 0;
    }

    public void setConstraintType(short s) {
        short s2 = this.fMiscFlags;
        this.fMiscFlags = (short) ((s & 3) | ((short) (s2 ^ (s2 & 3))));
    }

    public void setIsAbstract() {
        this.fMiscFlags = (short) (this.fMiscFlags | 8);
    }

    public void setIsGlobal() {
        this.fScope = (short) 1;
    }

    public void setIsLocal(XSComplexTypeDecl xSComplexTypeDecl) {
        this.fScope = (short) 2;
        this.fEnclosingCT = xSComplexTypeDecl;
    }

    public void setIsNillable() {
        this.fMiscFlags = (short) (this.fMiscFlags | 4);
    }

    public void setNamespaceItem(XSNamespaceItem xSNamespaceItem) {
        this.fNamespaceItem = xSNamespaceItem;
    }

    public String toString() {
        if (this.fDescription == null) {
            String str = this.fTargetNamespace;
            if (str != null) {
                int length = str.length();
                String str2 = this.fName;
                StringBuffer stringBuffer = new StringBuffer(length + (str2 != null ? str2.length() : 4) + 3);
                stringBuffer.append('\"');
                stringBuffer.append(this.fTargetNamespace);
                stringBuffer.append("\":");
                stringBuffer.append(this.fName);
                this.fDescription = stringBuffer.toString();
            } else {
                this.fDescription = this.fName;
            }
        }
        return this.fDescription;
    }
}
