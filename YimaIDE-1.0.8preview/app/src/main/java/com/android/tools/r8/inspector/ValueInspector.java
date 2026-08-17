package com.android.tools.r8.inspector;

import com.android.tools.r8.references.TypeReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ValueInspector {
    BooleanValueInspector asBooleanValue();

    ByteValueInspector asByteValue();

    CharValueInspector asCharValue();

    DoubleValueInspector asDoubleValue();

    FloatValueInspector asFloatValue();

    IntValueInspector asIntValue();

    LongValueInspector asLongValue();

    ShortValueInspector asShortValue();

    StringValueInspector asStringValue();

    TypeReference getTypeReference();

    boolean isBooleanValue();

    boolean isByteValue();

    boolean isCharValue();

    boolean isDoubleValue();

    boolean isFloatValue();

    boolean isIntValue();

    boolean isLongValue();

    boolean isPrimitive();

    boolean isShortValue();

    boolean isStringValue();
}
