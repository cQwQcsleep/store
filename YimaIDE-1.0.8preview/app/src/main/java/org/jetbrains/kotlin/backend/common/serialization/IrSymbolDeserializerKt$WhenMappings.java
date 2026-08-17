package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.serialization.encodings.BinarySymbolData;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class IrSymbolDeserializerKt$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[BinarySymbolData.SymbolKind.values().length];
        try {
            iArr[BinarySymbolData.SymbolKind.ANONYMOUS_INIT_SYMBOL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.CLASS_SYMBOL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.CONSTRUCTOR_SYMBOL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.TYPE_PARAMETER_SYMBOL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.ENUM_ENTRY_SYMBOL.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.STANDALONE_FIELD_SYMBOL.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.FIELD_SYMBOL.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.FUNCTION_SYMBOL.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.TYPEALIAS_SYMBOL.ordinal()] = 9;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.PROPERTY_SYMBOL.ordinal()] = 10;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.VARIABLE_SYMBOL.ordinal()] = 11;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.VALUE_PARAMETER_SYMBOL.ordinal()] = 12;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.RECEIVER_PARAMETER_SYMBOL.ordinal()] = 13;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.LOCAL_DELEGATED_PROPERTY_SYMBOL.ordinal()] = 14;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.RETURNABLE_BLOCK_SYMBOL.ordinal()] = 15;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            iArr[BinarySymbolData.SymbolKind.FILE_SYMBOL.ordinal()] = 16;
        } catch (NoSuchFieldError unused16) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}
