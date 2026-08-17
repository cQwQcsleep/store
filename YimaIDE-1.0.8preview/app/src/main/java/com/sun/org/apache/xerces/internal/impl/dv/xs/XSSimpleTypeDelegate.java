package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.DatatypeException;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeFacetException;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.dv.XSFacets;
import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSTypeDefinition;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSSimpleTypeDelegate implements XSSimpleType {
    protected final XSSimpleType type;

    public XSSimpleTypeDelegate(XSSimpleType xSSimpleType) {
        xSSimpleType.getClass();
        this.type = xSSimpleType;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public void applyFacets(XSFacets xSFacets, short s, short s2, ValidationContext validationContext) throws InvalidDatatypeFacetException {
        this.type.applyFacets(xSFacets, s, s2, validationContext);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean derivedFrom(String str, String str2, short s) {
        return this.type.derivedFrom(str, str2, s);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean derivedFromType(XSTypeDefinition xSTypeDefinition, short s) {
        return this.type.derivedFromType(xSTypeDefinition, s);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getAnnotations() {
        return this.type.getAnnotations();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean getAnonymous() {
        return this.type.getAnonymous();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public XSTypeDefinition getBaseType() {
        return this.type.getBaseType();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean getBounded() {
        return this.type.getBounded();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getBuiltInKind() {
        return this.type.getBuiltInKind();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getDefinedFacets() {
        return this.type.getDefinedFacets();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObject getFacet(int i) {
        return this.type.getFacet(i);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getFacets() {
        return this.type.getFacets();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public short getFinal() {
        return this.type.getFinal();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean getFinite() {
        return this.type.getFinite();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getFixedFacets() {
        return this.type.getFixedFacets();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSSimpleTypeDefinition getItemType() {
        return this.type.getItemType();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public StringList getLexicalEnumeration() {
        return this.type.getLexicalEnumeration();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public String getLexicalFacetValue(short s) {
        return this.type.getLexicalFacetValue(s);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public StringList getLexicalPattern() {
        return this.type.getLexicalPattern();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getMemberTypes() {
        return this.type.getMemberTypes();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSObjectList getMultiValueFacets() {
        return this.type.getMultiValueFacets();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getName() {
        return this.type.getName();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public String getNamespace() {
        return this.type.getNamespace();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public XSNamespaceItem getNamespaceItem() {
        return this.type.getNamespaceItem();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean getNumeric() {
        return this.type.getNumeric();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getOrdered() {
        return this.type.getOrdered();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public short getPrimitiveKind() {
        return this.type.getPrimitiveKind();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public XSSimpleTypeDefinition getPrimitiveType() {
        return this.type.getPrimitiveType();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSObject
    public short getType() {
        return this.type.getType();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public short getTypeCategory() {
        return this.type.getTypeCategory();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public short getVariety() {
        return this.type.getVariety();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public short getWhitespace() throws DatatypeException {
        return this.type.getWhitespace();
    }

    public XSSimpleType getWrappedXSSimpleType() {
        return this.type;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean isDefinedFacet(short s) {
        return this.type.isDefinedFacet(s);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public boolean isEqual(Object obj, Object obj2) {
        return this.type.isEqual(obj, obj2);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSTypeDefinition
    public boolean isFinal(short s) {
        return this.type.isFinal(s);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition
    public boolean isFixedFacet(short s) {
        return this.type.isFixedFacet(s);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public boolean isIDType() {
        return this.type.isIDType();
    }

    public String toString() {
        return this.type.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public Object validate(String str, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        return this.type.validate(str, validationContext, validatedInfo);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public void validate(ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        this.type.validate(validationContext, validatedInfo);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType
    public Object validate(Object obj, ValidationContext validationContext, ValidatedInfo validatedInfo) throws InvalidDatatypeValueException {
        return this.type.validate(obj, validationContext, validatedInfo);
    }
}
