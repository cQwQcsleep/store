package com.sun.org.apache.xerces.internal.xpointer;

import com.sun.org.apache.xerces.internal.impl.Constants;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.util.SymbolTable;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XNIException;
import com.sun.org.apache.xerces.internal.xs.AttributePSVI;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ShortHandPointer implements XPointerPart {
    private boolean fIsFragmentResolved = false;
    int fMatchingChildCount = 0;
    private String fShortHandPointer;
    private SymbolTable fSymbolTable;

    public ShortHandPointer(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    private boolean hasMatchingIdentifier(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, int i) throws XNIException {
        String schemaDeterminedID = null;
        if (xMLAttributes != null) {
            for (int i2 = 0; i2 < xMLAttributes.getLength() && (schemaDeterminedID = getSchemaDeterminedID(xMLAttributes, i2)) == null && (schemaDeterminedID = getChildrenSchemaDeterminedID(xMLAttributes, i2)) == null && (schemaDeterminedID = getDTDDeterminedID(xMLAttributes, i2)) == null; i2++) {
            }
        }
        return schemaDeterminedID != null && schemaDeterminedID.equals(this.fShortHandPointer);
    }

    public String getChildrenSchemaDeterminedID(XMLAttributes xMLAttributes, int i) throws XNIException {
        return null;
    }

    public String getDTDDeterminedID(XMLAttributes xMLAttributes, int i) throws XNIException {
        if (xMLAttributes.getType(i).equals(SchemaSymbols.ATTVAL_ID)) {
            return xMLAttributes.getValue(i);
        }
        return null;
    }

    public String getSchemaDeterminedID(XMLAttributes xMLAttributes, int i) throws XNIException {
        AttributePSVI attributePSVI = (AttributePSVI) xMLAttributes.getAugmentations(i).getItem(Constants.ATTRIBUTE_PSVI);
        if (attributePSVI == null) {
            return null;
        }
        XSTypeDefinition memberTypeDefinition = attributePSVI.getMemberTypeDefinition();
        if (memberTypeDefinition != null) {
            memberTypeDefinition = attributePSVI.getTypeDefinition();
        }
        if (memberTypeDefinition == null || !((XSSimpleType) memberTypeDefinition).isIDType()) {
            return null;
        }
        return attributePSVI.getSchemaValue().getNormalizedValue();
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public String getSchemeData() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public String getSchemeName() {
        return this.fShortHandPointer;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public boolean isChildFragmentResolved() {
        return this.fIsFragmentResolved && this.fMatchingChildCount > 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public boolean isFragmentResolved() {
        return this.fIsFragmentResolved;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public void parseXPointer(String str) throws XNIException {
        this.fShortHandPointer = str;
        this.fIsFragmentResolved = false;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public boolean resolveXPointer(QName qName, XMLAttributes xMLAttributes, Augmentations augmentations, int i) throws XNIException {
        int i2 = this.fMatchingChildCount;
        if (i2 == 0) {
            this.fIsFragmentResolved = false;
        }
        if (i == 0) {
            if (i2 == 0) {
                this.fIsFragmentResolved = hasMatchingIdentifier(qName, xMLAttributes, augmentations, i);
            }
            if (this.fIsFragmentResolved) {
                this.fMatchingChildCount++;
            }
        } else if (i == 2) {
            if (i2 == 0) {
                this.fIsFragmentResolved = hasMatchingIdentifier(qName, xMLAttributes, augmentations, i);
            }
        } else if (this.fIsFragmentResolved) {
            this.fMatchingChildCount = i2 - 1;
        }
        return this.fIsFragmentResolved;
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public void setSchemeData(String str) {
    }

    @Override // com.sun.org.apache.xerces.internal.xpointer.XPointerPart
    public void setSchemeName(String str) {
        this.fShortHandPointer = str;
    }

    public ShortHandPointer() {
    }
}
