package com.reandroid.dex.ins;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class InsConstCommentHelper {
    private static TypeKey findDataTypeFromFieldInsLazy(SizeXIns sizeXIns) {
        Key key = sizeXIns.getKey();
        if ((key instanceof FieldKey) && sizeXIns.getOpcode().isFieldPut()) {
            return ((FieldKey) key).getType();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static TypeKey findDataTypeFromInvokeInsLazy(int i, SizeXIns sizeXIns) {
        Key key = sizeXIns.getKey();
        if (!(key instanceof MethodKey)) {
            return null;
        }
        MethodKey methodKey = (MethodKey) key;
        return methodKey.getParameter(methodKey.getParameterIndex(indexOfRegister(i, (RegistersSet) sizeXIns)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static TypeKey findDataTypeFromNextInsLazy(SizeXIns sizeXIns) {
        InstructionList instructionList = sizeXIns.getInstructionList();
        TypeKey typeKeyFindDataTypeFromFieldInsLazy = null;
        if (instructionList == null) {
            return null;
        }
        Ins ins = instructionList.get(sizeXIns.getIndex() + 1);
        if ((ins instanceof RegistersSet) && (ins instanceof SizeXIns)) {
            int register = ((RegistersSet) sizeXIns).getRegister();
            if (indexOfRegister(register, (RegistersSet) ins) < 0) {
                return null;
            }
            String name = ins.getOpcode().getName();
            if (name.contains("float")) {
                return TypeKey.TYPE_F;
            }
            if (name.contains("double")) {
                return TypeKey.TYPE_D;
            }
            SizeXIns sizeXIns2 = (SizeXIns) ins;
            typeKeyFindDataTypeFromFieldInsLazy = findDataTypeFromFieldInsLazy(sizeXIns2);
            if (typeKeyFindDataTypeFromFieldInsLazy == null) {
                typeKeyFindDataTypeFromFieldInsLazy = findDataTypeFromInvokeInsLazy(register, sizeXIns2);
            }
            if (typeKeyFindDataTypeFromFieldInsLazy == null) {
                return findDataTypeFromReturnInsLazy(sizeXIns2);
            }
        }
        return typeKeyFindDataTypeFromFieldInsLazy;
    }

    private static TypeKey findDataTypeFromReturnInsLazy(SizeXIns sizeXIns) {
        MethodDef methodDef;
        MethodKey key;
        if (!sizeXIns.getOpcode().isReturning() || (methodDef = sizeXIns.getMethodDef()) == null || (key = methodDef.getKey()) == null) {
            return null;
        }
        return key.getReturnType();
    }

    public static String getCommentForConstNumber(SizeXIns sizeXIns) {
        TypeKey typeKeyFindDataTypeFromNextInsLazy;
        if (!(sizeXIns instanceof ConstNumber)) {
            return null;
        }
        long dataAsLong = sizeXIns.getDataAsLong();
        if (dataAsLong == 0 || (typeKeyFindDataTypeFromNextInsLazy = findDataTypeFromNextInsLazy(sizeXIns)) == null) {
            return null;
        }
        if (TypeKey.TYPE_D.equals(typeKeyFindDataTypeFromNextInsLazy)) {
            return Double.toString(Double.longBitsToDouble(dataAsLong));
        }
        if (!TypeKey.TYPE_F.equals(typeKeyFindDataTypeFromNextInsLazy)) {
            if (TypeKey.TYPE_C.equals(typeKeyFindDataTypeFromNextInsLazy)) {
                return safeQuotedChar((char) dataAsLong);
            }
            return null;
        }
        return Float.intBitsToFloat((int) dataAsLong) + "f";
    }

    private static int indexOfRegister(int i, RegistersSet registersSet) {
        int registersCount = registersSet.getRegistersCount();
        for (int i2 = 0; i2 < registersCount; i2++) {
            if (i == registersSet.getRegister(i2)) {
                return i2;
            }
        }
        return -1;
    }

    private static String safeQuotedChar(char c) {
        if (c == '\n') {
            return "'\\n'";
        }
        if (c == '\r') {
            return "'\\r'";
        }
        if (c == '\t') {
            return "'\\t'";
        }
        if (c == '\b') {
            return "'\\b'";
        }
        return c == '\f' ? "'\\f'" : DexUtils.quoteChar(c);
    }
}
