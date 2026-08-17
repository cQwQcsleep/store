package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.ElementPSVImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.xs.ElementPSVI;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSComplexTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSElementDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSModel;
import com.sun.org.apache.xerces.internal.xs.XSNotationDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PSVIElementNSImpl extends ElementNSImpl implements ElementPSVI {
    static final long serialVersionUID = 6815489624636016068L;
    protected XSElementDeclaration fDeclaration;
    protected StringList fErrorCodes;
    protected StringList fErrorMessages;
    protected boolean fNil;
    protected XSNotationDeclaration fNotation;
    protected XSModel fSchemaInformation;
    protected boolean fSpecified;
    protected XSTypeDefinition fTypeDecl;
    protected short fValidationAttempted;
    protected String fValidationContext;
    protected short fValidity;
    protected ValidatedInfo fValue;

    public PSVIElementNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2, String str3) {
        super(coreDocumentImpl, str, str2, str3);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = true;
        this.fValue = new ValidatedInfo();
        this.fNotation = null;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrorCodes = null;
        this.fErrorMessages = null;
        this.fValidationContext = null;
        this.fSchemaInformation = null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(getClass().getName());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(getClass().getName());
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public ItemPSVI constant() {
        return new ElementPSVImpl(true, this);
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
        StringList stringList = this.fErrorCodes;
        return stringList != null ? stringList : StringListImpl.EMPTY_LIST;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public StringList getErrorMessages() {
        StringList stringList = this.fErrorMessages;
        return stringList != null ? stringList : StringListImpl.EMPTY_LIST;
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
    public XSModel getSchemaInformation() {
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
        return false;
    }

    public void setPSVI(ElementPSVI elementPSVI) {
        this.fDeclaration = elementPSVI.getElementDeclaration();
        this.fNotation = elementPSVI.getNotation();
        this.fValidationContext = elementPSVI.getValidationContext();
        this.fTypeDecl = elementPSVI.getTypeDefinition();
        this.fSchemaInformation = elementPSVI.getSchemaInformation();
        this.fValidity = elementPSVI.getValidity();
        this.fValidationAttempted = elementPSVI.getValidationAttempted();
        this.fErrorCodes = elementPSVI.getErrorCodes();
        this.fErrorMessages = elementPSVI.getErrorMessages();
        XSTypeDefinition xSTypeDefinition = this.fTypeDecl;
        if ((xSTypeDefinition instanceof XSSimpleTypeDefinition) || ((xSTypeDefinition instanceof XSComplexTypeDefinition) && ((XSComplexTypeDefinition) xSTypeDefinition).getContentType() == 1)) {
            this.fValue.copyFrom(elementPSVI.getSchemaValue());
        } else {
            this.fValue.reset();
        }
        this.fSpecified = elementPSVI.getIsSchemaSpecified();
        this.fNil = elementPSVI.getNil();
    }

    public PSVIElementNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2) {
        super(coreDocumentImpl, str, str2);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fNil = false;
        this.fSpecified = true;
        this.fValue = new ValidatedInfo();
        this.fNotation = null;
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrorCodes = null;
        this.fErrorMessages = null;
        this.fValidationContext = null;
        this.fSchemaInformation = null;
    }
}
