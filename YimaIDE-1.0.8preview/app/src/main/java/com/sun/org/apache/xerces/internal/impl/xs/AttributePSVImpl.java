package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttributePSVImpl implements AttributePSVI {
    protected XSAttributeDeclaration fDeclaration;
    protected String[] fErrors;
    protected boolean fIsConstant;
    protected boolean fSpecified;
    protected XSTypeDefinition fTypeDecl;
    protected short fValidationAttempted;
    protected String fValidationContext;
    protected short fValidity;
    protected ValidatedInfo fValue;

    public AttributePSVImpl(boolean z, AttributePSVI attributePSVI) {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = false;
        this.fValue = new ValidatedInfo();
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
        this.fDeclaration = attributePSVI.getAttributeDeclaration();
        this.fTypeDecl = attributePSVI.getTypeDefinition();
        this.fSpecified = attributePSVI.getIsSchemaSpecified();
        this.fValue.copyFrom(attributePSVI.getSchemaValue());
        this.fValidationAttempted = attributePSVI.getValidationAttempted();
        this.fValidity = attributePSVI.getValidity();
        if (attributePSVI instanceof AttributePSVImpl) {
            String[] strArr = ((AttributePSVImpl) attributePSVI).fErrors;
            this.fErrors = strArr != null ? (String[]) strArr.clone() : null;
        } else {
            StringList errorCodes = attributePSVI.getErrorCodes();
            int length = errorCodes.getLength();
            if (length > 0) {
                StringList errorMessages = attributePSVI.getErrorMessages();
                String[] strArr2 = new String[length << 1];
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = i + 1;
                    strArr2[i] = errorCodes.item(i2);
                    i += 2;
                    strArr2[i3] = errorMessages.item(i2);
                }
                this.fErrors = strArr2;
            }
        }
        this.fValidationContext = attributePSVI.getValidationContext();
        this.fIsConstant = z;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public ItemPSVI constant() {
        return isConstant() ? this : new AttributePSVImpl(true, this);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    @Deprecated
    public Object getActualNormalizedValue() {
        return this.fValue.getActualValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    @Deprecated
    public short getActualNormalizedValueType() {
        return this.fValue.getActualValueType();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.AttributePSVI
    public XSAttributeDeclaration getAttributeDeclaration() {
        return this.fDeclaration;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public StringList getErrorCodes() {
        String[] strArr = this.fErrors;
        return (strArr == null || strArr.length == 0) ? StringListImpl.EMPTY_LIST : new PSVIErrorList(this.fErrors, true);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public StringList getErrorMessages() {
        String[] strArr = this.fErrors;
        return (strArr == null || strArr.length == 0) ? StringListImpl.EMPTY_LIST : new PSVIErrorList(this.fErrors, false);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public boolean getIsSchemaSpecified() {
        return this.fSpecified;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    @Deprecated
    public ShortList getItemValueTypes() {
        return this.fValue.getListValueTypes();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.fValue.getMemberTypeDefinition();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public String getSchemaDefault() {
        XSAttributeDeclaration xSAttributeDeclaration = this.fDeclaration;
        if (xSAttributeDeclaration == null) {
            return null;
        }
        return xSAttributeDeclaration.getConstraintValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    @Deprecated
    public String getSchemaNormalizedValue() {
        return this.fValue.getNormalizedValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public XSValue getSchemaValue() {
        return this.fValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public XSTypeDefinition getTypeDefinition() {
        return this.fTypeDecl;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public short getValidationAttempted() {
        return this.fValidationAttempted;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public String getValidationContext() {
        return this.fValidationContext;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public short getValidity() {
        return this.fValidity;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public boolean isConstant() {
        return this.fIsConstant;
    }

    public void reset() {
        this.fValue.reset();
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = false;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
    }

    public AttributePSVImpl() {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = false;
        this.fValue = new ValidatedInfo();
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
    }
}
