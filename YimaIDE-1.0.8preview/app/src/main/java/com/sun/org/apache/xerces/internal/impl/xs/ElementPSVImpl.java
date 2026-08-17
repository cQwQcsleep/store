package com.sun.org.apache.xerces.internal.impl.xs;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSElementDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import com.sun.org.apache.xerces.internal.xs.XSNotationDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementPSVImpl implements ElementPSVI {
    protected XSElementDeclaration fDeclaration;
    protected String[] fErrors;
    protected SchemaGrammar[] fGrammars;
    protected boolean fIsConstant;
    protected boolean fNil;
    protected XSNotationDeclaration fNotation;
    protected XSModel fSchemaInformation;
    protected boolean fSpecified;
    protected XSTypeDefinition fTypeDecl;
    protected short fValidationAttempted;
    protected String fValidationContext;
    protected short fValidity;
    protected ValidatedInfo fValue;

    public ElementPSVImpl(boolean z, ElementPSVI elementPSVI) {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = false;
        this.fValue = new ValidatedInfo();
        this.fNotation = null;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
        this.fGrammars = null;
        this.fSchemaInformation = null;
        this.fDeclaration = elementPSVI.getElementDeclaration();
        this.fTypeDecl = elementPSVI.getTypeDefinition();
        this.fNil = elementPSVI.getNil();
        this.fSpecified = elementPSVI.getIsSchemaSpecified();
        this.fValue.copyFrom(elementPSVI.getSchemaValue());
        this.fNotation = elementPSVI.getNotation();
        this.fValidationAttempted = elementPSVI.getValidationAttempted();
        this.fValidity = elementPSVI.getValidity();
        this.fValidationContext = elementPSVI.getValidationContext();
        if (elementPSVI instanceof ElementPSVImpl) {
            ElementPSVImpl elementPSVImpl = (ElementPSVImpl) elementPSVI;
            String[] strArr = elementPSVImpl.fErrors;
            this.fErrors = strArr != null ? (String[]) strArr.clone() : null;
            elementPSVImpl.copySchemaInformationTo(this);
        } else {
            StringList errorCodes = elementPSVI.getErrorCodes();
            int length = errorCodes.getLength();
            if (length > 0) {
                StringList errorMessages = elementPSVI.getErrorMessages();
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
            this.fSchemaInformation = elementPSVI.getSchemaInformation();
        }
        this.fIsConstant = z;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public ItemPSVI constant() {
        return isConstant() ? this : new ElementPSVImpl(true, this);
    }

    public void copySchemaInformationTo(ElementPSVImpl elementPSVImpl) {
        elementPSVImpl.fGrammars = this.fGrammars;
        elementPSVImpl.fSchemaInformation = this.fSchemaInformation;
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

    @Override // com.sun.org.apache.xerces.internal.xs.ElementPSVI
    public XSElementDeclaration getElementDeclaration() {
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

    @Override // com.sun.org.apache.xerces.internal.xs.ElementPSVI
    public boolean getNil() {
        return this.fNil;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ElementPSVI
    public XSNotationDeclaration getNotation() {
        return this.fNotation;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public String getSchemaDefault() {
        XSElementDeclaration xSElementDeclaration = this.fDeclaration;
        if (xSElementDeclaration == null) {
            return null;
        }
        return xSElementDeclaration.getConstraintValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ElementPSVI
    public synchronized XSModel getSchemaInformation() {
        try {
            if (this.fSchemaInformation == null && this.fGrammars != null) {
                this.fSchemaInformation = new XSModelImpl(this.fGrammars);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.fSchemaInformation;
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
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = false;
        this.fNotation = null;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
        this.fValue.reset();
    }

    public ElementPSVImpl() {
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = false;
        this.fValue = new ValidatedInfo();
        this.fNotation = null;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrors = null;
        this.fValidationContext = null;
        this.fGrammars = null;
        this.fSchemaInformation = null;
    }
}
