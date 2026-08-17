package com.sun.org.apache.xerces.internal.dom;

import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.xs.AttributePSVImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.ItemPSVI;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSAttributeDeclaration;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PSVIAttrNSImpl extends AttrNSImpl implements AttributePSVI {
    static final long serialVersionUID = -3241738699421018889L;
    protected XSAttributeDeclaration fDeclaration;
    protected StringList fErrorCodes;
    protected StringList fErrorMessages;
    protected boolean fSpecified;
    protected XSTypeDefinition fTypeDecl;
    protected short fValidationAttempted;
    protected String fValidationContext;
    protected short fValidity;
    protected ValidatedInfo fValue;

    public PSVIAttrNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2, String str3) {
        super(coreDocumentImpl, str, str2, str3);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = true;
        this.fValue = new ValidatedInfo();
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrorCodes = null;
        this.fErrorMessages = null;
        this.fValidationContext = null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw new NotSerializableException(getClass().getName());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        throw new NotSerializableException(getClass().getName());
    }

    @Override // com.sun.org.apache.xerces.internal.xs.ItemPSVI
    public ItemPSVI constant() {
        return new AttributePSVImpl(true, this);
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
        return false;
    }

    public void setPSVI(AttributePSVI attributePSVI) {
        this.fDeclaration = attributePSVI.getAttributeDeclaration();
        this.fValidationContext = attributePSVI.getValidationContext();
        this.fValidity = attributePSVI.getValidity();
        this.fValidationAttempted = attributePSVI.getValidationAttempted();
        this.fErrorCodes = attributePSVI.getErrorCodes();
        this.fErrorMessages = attributePSVI.getErrorMessages();
        this.fValue.copyFrom(attributePSVI.getSchemaValue());
        this.fTypeDecl = attributePSVI.getTypeDefinition();
        this.fSpecified = attributePSVI.getIsSchemaSpecified();
    }

    public PSVIAttrNSImpl(CoreDocumentImpl coreDocumentImpl, String str, String str2) {
        super(coreDocumentImpl, str, str2);
        this.fDeclaration = null;
        this.fTypeDecl = null;
        this.fSpecified = true;
        this.fValue = new ValidatedInfo();
        this.fValidationAttempted = (short) 0;
        this.fValidity = (short) 0;
        this.fErrorCodes = null;
        this.fErrorMessages = null;
        this.fValidationContext = null;
    }
}
