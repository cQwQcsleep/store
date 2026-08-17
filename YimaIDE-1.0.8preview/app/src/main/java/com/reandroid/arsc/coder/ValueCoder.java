package com.reandroid.arsc.coder;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.AttributeDataFormat;
import com.reandroid.arsc.value.Value;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.arsc.value.attribute.AttributeBag;
import com.reandroid.utils.StringsUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ValueCoder {
    public static final Coder[] CODERS;
    private static final Coder[] CODERS_NULL;
    private static final Map<ValueType, Coder> CODER_MAP;

    static {
        CoderNull coderNull = CoderNull.INS;
        CoderBoolean coderBoolean = CoderBoolean.INS;
        CoderDimension coderDimension = CoderDimension.INS;
        CoderFraction coderFraction = CoderFraction.INS;
        CoderColorARGB4 coderColorARGB4 = CoderColorARGB4.INS;
        CoderColorRGB4 coderColorRGB4 = CoderColorRGB4.INS;
        CoderColorRGB8 coderColorRGB8 = CoderColorRGB8.INS;
        CoderColorARGB8 coderColorARGB8 = CoderColorARGB8.INS;
        CoderFloat coderFloat = CoderFloat.INS;
        CoderHex coderHex = CoderHex.INS;
        CoderInteger coderInteger = CoderInteger.INS;
        CODERS = new Coder[]{coderNull, coderBoolean, coderDimension, coderFraction, coderColorARGB4, coderColorRGB4, coderColorRGB8, coderColorARGB8, coderFloat, coderHex, coderInteger};
        HashMap map = new HashMap();
        map.put(coderNull.getValueType(), coderNull);
        map.put(coderBoolean.getValueType(), coderBoolean);
        map.put(coderDimension.getValueType(), coderDimension);
        map.put(coderFraction.getValueType(), coderFraction);
        map.put(coderColorRGB4.getValueType(), coderColorRGB4);
        map.put(coderColorARGB4.getValueType(), coderColorARGB4);
        map.put(coderColorRGB8.getValueType(), coderColorRGB8);
        map.put(coderColorARGB8.getValueType(), coderColorARGB8);
        map.put(coderFloat.getValueType(), coderFloat);
        map.put(coderHex.getValueType(), coderHex);
        map.put(coderInteger.getValueType(), coderInteger);
        CODER_MAP = map;
        CODERS_NULL = new Coder[]{CoderNullReference.INS, CoderNullAttribute.INS, coderNull};
    }

    private static String buildResourceNotFoundMessage(PackageBlock packageBlock, String str) {
        TableBlock tableBlock = packageBlock.getTableBlock();
        if (tableBlock != null) {
            return buildResourceNotFoundMessage(tableBlock, str);
        }
        return "Resource not found for: '" + str + "', package " + packageBlock + ", parent table = null";
    }

    public static String decode(ValueType valueType, int i) {
        String strDecodeNull = decodeNull(valueType, i);
        if (strDecodeNull != null) {
            return strDecodeNull;
        }
        Coder coder = CODER_MAP.get(valueType);
        if (coder == null) {
            return null;
        }
        return coder.decode(i);
    }

    private static String decodeNull(ValueType valueType, int i) {
        if (i != 0 && i != 1) {
            return null;
        }
        if (valueType == ValueType.NULL) {
            return CoderNull.INS.decode(i);
        }
        if (i != 0) {
            return null;
        }
        if (valueType == ValueType.ATTRIBUTE) {
            return CoderNullAttribute.INS.decode(i);
        }
        if (valueType == ValueType.REFERENCE) {
            return CoderNullReference.INS.decode(i);
        }
        return null;
    }

    public static String decodeReference(PackageBlock packageBlock, ValueType valueType, int i) {
        if (i == 0) {
            return valueType == ValueType.REFERENCE ? CoderNullReference.INS.decode(i) : CoderNullAttribute.INS.decode(i);
        }
        ResourceEntry resource = packageBlock.getTableBlock().getResource(packageBlock, i);
        if (resource == null || !resource.isDefined()) {
            return decodeUnknownResourceId(valueType == ValueType.REFERENCE, i);
        }
        return resource.buildReference(packageBlock, valueType);
    }

    public static String decodeUnknownNameId(int i) {
        return CoderUnknownNameId.INS.decode(i);
    }

    public static String decodeUnknownResourceId(boolean z, int i) {
        return z ? CoderUnknownReferenceId.INS.decode(i) : CoderUnknownAttributeId.INS.decode(i);
    }

    public static EncodeResult encode(String str, AttributeDataFormat... attributeDataFormatArr) {
        if (isEmpty(attributeDataFormatArr)) {
            return encodeAny(str);
        }
        if (str == null || str.length() == 0) {
            return null;
        }
        EncodeResult encodeResultEncodeUnknown = encodeUnknown(str);
        return encodeResultEncodeUnknown != null ? encodeResultEncodeUnknown : encodeWithin(str, attributeDataFormatArr);
    }

    private static EncodeResult encodeAny(String str) {
        EncodeResult encodeResultEncode;
        if (str != null && str.length() != 0) {
            EncodeResult encodeResultEncodeUnknown = encodeUnknown(str);
            if (encodeResultEncodeUnknown != null) {
                return encodeResultEncodeUnknown;
            }
            char cCharAt = str.charAt(0);
            for (Coder coder : CODERS) {
                if (coder.canStartWith(cCharAt) && (encodeResultEncode = coder.encode(str)) != null) {
                    return encodeResultEncode;
                }
            }
        }
        return null;
    }

    public static EncodeResult encodeAttributeValue(boolean z, Value value, ResourceEntry resourceEntry, String str) {
        AttributeBag attributeBagCreate;
        EncodeResult encodeResultEncodeReference = encodeReference(value.getPackageBlock(), str);
        if (encodeResultEncodeReference != null) {
            if (encodeResultEncodeReference.isError()) {
                return encodeResultEncodeReference;
            }
            value.setValue(encodeResultEncodeReference);
            return encodeResultEncodeReference;
        }
        if (resourceEntry != null && (attributeBagCreate = AttributeBag.create(resourceEntry.resolveReference().get())) != null && (encodeResultEncodeReference = attributeBagCreate.encode(str)) != null) {
            ValueType valueType = encodeResultEncodeReference.valueType;
            ValueType valueType2 = ValueType.STRING;
            if (valueType == valueType2) {
                value.setValueAsString(XmlSanitizer.unEscapeSpecialCharacter(str));
                return new EncodeResult(valueType2, value.getData());
            }
            if (encodeResultEncodeReference.isError()) {
                if (z) {
                    return encodeResultEncodeReference;
                }
                encodeResultEncodeReference = null;
            }
        }
        if (encodeResultEncodeReference == null) {
            encodeResultEncodeReference = encode(str);
        }
        if (encodeResultEncodeReference != null) {
            value.setValue(encodeResultEncodeReference);
            return encodeResultEncodeReference;
        }
        value.setValueAsString(XmlSanitizer.unEscapeSpecialCharacter(str));
        return new EncodeResult(ValueType.STRING, value.getData());
    }

    public static EncodeResult encodeHexOrInteger(String str) {
        if (str == null) {
            return null;
        }
        EncodeResult encodeResultEncode = CoderHex.INS.encode(str);
        return encodeResultEncode == null ? CoderInteger.INS.encode(str) : encodeResultEncode;
    }

    private static EncodeResult encodeNull(String str) {
        EncodeResult encodeResultEncode;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length != 5 && length != 6) {
            return null;
        }
        char cCharAt = str.charAt(0);
        for (Coder coder : CODERS_NULL) {
            if (coder.canStartWith(cCharAt) && (encodeResultEncode = coder.encode(str)) != null) {
                return encodeResultEncode;
            }
        }
        return null;
    }

    public static EncodeResult encodeReference(PackageBlock packageBlock, String str) {
        if (str == null || str.length() < 3) {
            return null;
        }
        EncodeResult encodeResultEncodeUnknownResourceId = encodeUnknownResourceId(str);
        if (encodeResultEncodeUnknownResourceId != null) {
            return encodeResultEncodeUnknownResourceId;
        }
        ReferenceString reference = ReferenceString.parseReference(str);
        if (reference == null) {
            return null;
        }
        EncodeResult encodeResultEncode = reference.encode(packageBlock, EncodeResult.RESOURCE_NOT_FOUND);
        return encodeResultEncode.isError() ? new EncodeResult(buildResourceNotFoundMessage(packageBlock, str)) : encodeResultEncode;
    }

    private static EncodeResult encodeUnknown(String str) {
        EncodeResult encodeResultEncode;
        EncodeResult encodeResultEncode2;
        char cCharAt = str.charAt(0);
        CoderUnknownReferenceId coderUnknownReferenceId = CoderUnknownReferenceId.INS;
        if (coderUnknownReferenceId.canStartWith(cCharAt) && (encodeResultEncode2 = coderUnknownReferenceId.encode(str)) != null) {
            return encodeResultEncode2;
        }
        CoderUnknownAttributeId coderUnknownAttributeId = CoderUnknownAttributeId.INS;
        if (coderUnknownAttributeId.canStartWith(cCharAt) && (encodeResultEncode = coderUnknownAttributeId.encode(str)) != null) {
            return encodeResultEncode;
        }
        CoderUnknownStringRef coderUnknownStringRef = CoderUnknownStringRef.INS;
        if (coderUnknownStringRef.canStartWith(cCharAt)) {
            return coderUnknownStringRef.encode(str);
        }
        return null;
    }

    public static EncodeResult encodeUnknownNameId(String str) {
        return CoderUnknownNameId.INS.encode(str);
    }

    public static EncodeResult encodeUnknownResourceId(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        EncodeResult encodeResultEncodeNull = encodeNull(str);
        return encodeResultEncodeNull != null ? encodeResultEncodeNull : encodeUnknown(str);
    }

    private static EncodeResult encodeWithin(String str, ValueType... valueTypeArr) {
        EncodeResult encodeResultEncode;
        if (str != null && str.length() != 0) {
            char cCharAt = str.charAt(0);
            for (ValueType valueType : valueTypeArr) {
                Coder coder = getCoder(valueType);
                if (coder != null && coder.canStartWith(cCharAt) && (encodeResultEncode = coder.encode(str)) != null) {
                    return encodeResultEncode;
                }
            }
        }
        return null;
    }

    public static Coder getCoder(ValueType valueType) {
        return CODER_MAP.get(valueType);
    }

    private static boolean isEmpty(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            for (Object obj : objArr) {
                if (obj != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static EncodeResult encode(String str, ValueType... valueTypeArr) {
        if (isEmpty(valueTypeArr)) {
            return encodeAny(str);
        }
        if (str == null || str.length() == 0) {
            return null;
        }
        EncodeResult encodeResultEncodeUnknown = encodeUnknown(str);
        return encodeResultEncodeUnknown != null ? encodeResultEncodeUnknown : encodeWithin(str, valueTypeArr);
    }

    private static String buildResourceNotFoundMessage(TableBlock tableBlock, String str) {
        return "Resource not found for: '" + str + "', frameworks " + StringsUtil.toString(tableBlock.getFrameWorks());
    }

    public static EncodeResult encode(String str) {
        return encodeAny(str);
    }

    private static EncodeResult encodeWithin(String str, AttributeDataFormat... attributeDataFormatArr) {
        if (str != null && str.length() != 0) {
            for (AttributeDataFormat attributeDataFormat : attributeDataFormatArr) {
                EncodeResult encodeResultEncodeWithin = encodeWithin(str, attributeDataFormat.valueTypes());
                if (encodeResultEncodeWithin != null) {
                    return encodeResultEncodeWithin;
                }
            }
        }
        return null;
    }

    public static EncodeResult encodeReference(TableBlock tableBlock, String str) {
        if (str == null || str.length() < 3) {
            return null;
        }
        EncodeResult encodeResultEncodeUnknownResourceId = encodeUnknownResourceId(str);
        if (encodeResultEncodeUnknownResourceId != null) {
            return encodeResultEncodeUnknownResourceId;
        }
        ReferenceString reference = ReferenceString.parseReference(str);
        if (reference == null) {
            return null;
        }
        EncodeResult encodeResultEncode = reference.encode(tableBlock, EncodeResult.RESOURCE_NOT_FOUND);
        return encodeResultEncode.isError() ? new EncodeResult(buildResourceNotFoundMessage(tableBlock, str)) : encodeResultEncode;
    }
}
