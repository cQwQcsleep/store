package com.sun.org.apache.xerces.internal.impl.dv;

import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
import com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectListImpl;
import com.sun.org.apache.xerces.internal.xs.ShortList;
import com.sun.org.apache.xerces.internal.xs.XSObjectList;
import com.sun.org.apache.xerces.internal.xs.XSSimpleTypeDefinition;
import com.sun.org.apache.xerces.internal.xs.XSValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ValidatedInfo implements XSValue {
    public XSSimpleType actualType;
    public Object actualValue;
    public short actualValueType;
    public ShortList itemValueTypes;
    public XSSimpleType memberType;
    public XSSimpleType[] memberTypes;
    public String normalizedValue;

    private static short convertToPrimitiveKind(short s) {
        if (s <= 20) {
            return s;
        }
        if (s <= 29) {
            return (short) 2;
        }
        if (s <= 42) {
            return (short) 4;
        }
        return s;
    }

    public static boolean isComparable(ValidatedInfo validatedInfo, ValidatedInfo validatedInfo2) {
        short sConvertToPrimitiveKind = convertToPrimitiveKind(validatedInfo.actualValueType);
        short sConvertToPrimitiveKind2 = convertToPrimitiveKind(validatedInfo2.actualValueType);
        if (sConvertToPrimitiveKind != sConvertToPrimitiveKind2) {
            return (sConvertToPrimitiveKind == 1 && sConvertToPrimitiveKind2 == 2) || (sConvertToPrimitiveKind == 2 && sConvertToPrimitiveKind2 == 1);
        }
        if (sConvertToPrimitiveKind == 44 || sConvertToPrimitiveKind == 43) {
            ShortList shortList = validatedInfo.itemValueTypes;
            ShortList shortList2 = validatedInfo2.itemValueTypes;
            int length = shortList != null ? shortList.getLength() : 0;
            if (length != (shortList2 != null ? shortList2.getLength() : 0)) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                short sConvertToPrimitiveKind3 = convertToPrimitiveKind(shortList.item(i));
                short sConvertToPrimitiveKind4 = convertToPrimitiveKind(shortList2.item(i));
                if (sConvertToPrimitiveKind3 != sConvertToPrimitiveKind4 && ((sConvertToPrimitiveKind3 != 1 || sConvertToPrimitiveKind4 != 2) && (sConvertToPrimitiveKind3 != 2 || sConvertToPrimitiveKind4 != 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void copyFrom(XSValue xSValue) {
        if (xSValue == null) {
            reset();
            return;
        }
        if (xSValue instanceof ValidatedInfo) {
            ValidatedInfo validatedInfo = (ValidatedInfo) xSValue;
            this.normalizedValue = validatedInfo.normalizedValue;
            this.actualValue = validatedInfo.actualValue;
            this.actualValueType = validatedInfo.actualValueType;
            this.actualType = validatedInfo.actualType;
            this.memberType = validatedInfo.memberType;
            this.memberTypes = validatedInfo.memberTypes;
            this.itemValueTypes = validatedInfo.itemValueTypes;
            return;
        }
        this.normalizedValue = xSValue.getNormalizedValue();
        this.actualValue = xSValue.getActualValue();
        this.actualValueType = xSValue.getActualValueType();
        this.actualType = (XSSimpleType) xSValue.getTypeDefinition();
        XSSimpleType xSSimpleType = (XSSimpleType) xSValue.getMemberTypeDefinition();
        this.memberType = xSSimpleType;
        if (xSSimpleType == null) {
            xSSimpleType = this.actualType;
        }
        if (xSSimpleType == null || xSSimpleType.getBuiltInKind() != 43) {
            this.memberTypes = null;
        } else {
            XSObjectList memberTypeDefinitions = xSValue.getMemberTypeDefinitions();
            this.memberTypes = new XSSimpleType[memberTypeDefinitions.getLength()];
            for (int i = 0; i < memberTypeDefinitions.getLength(); i++) {
                this.memberTypes[i] = (XSSimpleType) memberTypeDefinitions.get(i);
            }
        }
        this.itemValueTypes = xSValue.getListValueTypes();
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public Object getActualValue() {
        return this.actualValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public short getActualValueType() {
        return this.actualValueType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public ShortList getListValueTypes() {
        ShortList shortList = this.itemValueTypes;
        return shortList == null ? ShortListImpl.EMPTY_LIST : shortList;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public XSSimpleTypeDefinition getMemberTypeDefinition() {
        return this.memberType;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public XSObjectList getMemberTypeDefinitions() {
        if (this.memberTypes == null) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        XSSimpleType[] xSSimpleTypeArr = this.memberTypes;
        return new XSObjectListImpl(xSSimpleTypeArr, xSSimpleTypeArr.length);
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public String getNormalizedValue() {
        return this.normalizedValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xs.XSValue
    public XSSimpleTypeDefinition getTypeDefinition() {
        return this.actualType;
    }

    public void reset() {
        this.normalizedValue = null;
        this.actualValue = null;
        this.actualValueType = (short) 45;
        this.actualType = null;
        this.memberType = null;
        this.memberTypes = null;
        this.itemValueTypes = null;
    }

    public String stringValue() {
        Object obj = this.actualValue;
        return obj == null ? this.normalizedValue : obj.toString();
    }
}
