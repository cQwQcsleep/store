package org.jetbrains.kotlin.wasm.ir;

import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate$Catch;", "Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate;", "type", "Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate$Catch$CatchType;", "immediates", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate$Catch$CatchType;Ljava/util/List;)V", "getType", "()Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate$Catch$CatchType;", "getImmediates", "()Ljava/util/List;", "CatchType", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmImmediate$Catch extends WasmImmediate {
    private final List<WasmImmediate> immediates;
    private final CatchType type;

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CATCH' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmImmediate$Catch$CatchType;", "", "mnemonic", "", "opcode", "", "immediates", "", "Lorg/jetbrains/kotlin/wasm/ir/WasmImmediateKind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;ILjava/lang/String;I[Lorg/jetbrains/kotlin/wasm/ir/WasmImmediateKind;)V", "getMnemonic", "()Ljava/lang/String;", "getOpcode", "()I", "getImmediates", "()[Lorg/jetbrains/kotlin/wasm/ir/WasmImmediateKind;", "[Lorg/jetbrains/kotlin/wasm/ir/WasmImmediateKind;", "CATCH", "CATCH_REF", "CATCH_ALL", "CATCH_ALL_REF", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CatchType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ CatchType[] $VALUES;
        public static final CatchType CATCH;
        public static final CatchType CATCH_ALL;
        public static final CatchType CATCH_ALL_REF;
        public static final CatchType CATCH_REF;
        private final WasmImmediateKind[] immediates;
        private final String mnemonic;
        private final int opcode;

        private static final /* synthetic */ CatchType[] $values() {
            return new CatchType[]{CATCH, CATCH_REF, CATCH_ALL, CATCH_ALL_REF};
        }

        static {
            WasmImmediateKind wasmImmediateKind = WasmImmediateKind.TAG_IDX;
            WasmImmediateKind wasmImmediateKind2 = WasmImmediateKind.LABEL_IDX;
            CATCH = new CatchType("CATCH", 0, "catch", 0, wasmImmediateKind, wasmImmediateKind2);
            CATCH_REF = new CatchType("CATCH_REF", 1, "catch_ref", 1, wasmImmediateKind, wasmImmediateKind2);
            CATCH_ALL = new CatchType("CATCH_ALL", 2, "catch_all", 2, wasmImmediateKind2);
            CATCH_ALL_REF = new CatchType("CATCH_ALL_REF", 3, "catch_all_ref", 3, wasmImmediateKind2);
            CatchType[] catchTypeArr$values = $values();
            $VALUES = catchTypeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(catchTypeArr$values);
        }

        private CatchType(String str, int i, String str2, int i2, WasmImmediateKind... wasmImmediateKindArr) {
            super(str, i);
            this.mnemonic = str2;
            this.opcode = i2;
            this.immediates = wasmImmediateKindArr;
        }

        public static EnumEntries<CatchType> getEntries() {
            return $ENTRIES;
        }

        public static CatchType valueOf(String str) {
            return (CatchType) Enum.valueOf(CatchType.class, str);
        }

        public static CatchType[] values() {
            return (CatchType[]) $VALUES.clone();
        }

        public final WasmImmediateKind[] getImmediates() {
            return this.immediates;
        }

        public final String getMnemonic() {
            return this.mnemonic;
        }

        public final int getOpcode() {
            return this.opcode;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmImmediate$Catch(CatchType catchType, List<? extends WasmImmediate> list) {
        super((DefaultConstructorMarker) null);
        catchType.getClass();
        list.getClass();
        this.type = catchType;
        this.immediates = list;
        if (list.size() == catchType.getImmediates().length) {
            return;
        }
        throw new IllegalArgumentException(("Immediates sizes are not equals: " + catchType.name() + " required " + catchType.getImmediates().length + ", but " + list.size() + " were provided").toString());
    }

    public final List<WasmImmediate> getImmediates() {
        return this.immediates;
    }

    public final CatchType getType() {
        return this.type;
    }
}
