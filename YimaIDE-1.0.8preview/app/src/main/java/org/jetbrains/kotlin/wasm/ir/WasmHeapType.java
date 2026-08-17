package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType;", "", "<init>", "()V", "Type", "Simple", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class WasmHeapType {
    public /* synthetic */ WasmHeapType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType;", "<init>", "()V", "GcType", "VTableType", "FunctionType", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$FunctionType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$GcType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$VTableType;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static abstract class Type extends WasmHeapType {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$FunctionType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static abstract class FunctionType extends Type {
            public FunctionType() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$GcType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static abstract class GcType extends Type {
            public GcType() {
                super(null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type$VTableType;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static abstract class VTableType extends Type {
            public VTableType() {
                super(null);
            }
        }

        private Type() {
            super(null);
        }

        public /* synthetic */ Type(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private WasmHeapType() {
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0001\b\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType;", "name", "", "code", "", "<init>", "(Ljava/lang/String;B)V", "getName", "()Ljava/lang/String;", "getCode", "()B", "toString", "Func", "Extern", "Any", "Eq", "Struct", "None", "NoFunc", "NoExtern", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Any;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Eq;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Extern;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Func;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$NoExtern;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$NoFunc;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$None;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Struct;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static abstract class Simple extends WasmHeapType {
        private final byte code;
        private final String name;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Any;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Any extends Simple {
            public static final Any INSTANCE = new Any();

            private Any() {
                super("any", (byte) -18, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Eq;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Eq extends Simple {
            public static final Eq INSTANCE = new Eq();

            private Eq() {
                super("eq", (byte) -19, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Extern;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Extern extends Simple {
            public static final Extern INSTANCE = new Extern();

            private Extern() {
                super("extern", (byte) -17, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Func;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Func extends Simple {
            public static final Func INSTANCE = new Func();

            private Func() {
                super("func", (byte) -16, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$NoExtern;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class NoExtern extends Simple {
            public static final NoExtern INSTANCE = new NoExtern();

            private NoExtern() {
                super("noextern", (byte) -14, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$NoFunc;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class NoFunc extends Simple {
            public static final NoFunc INSTANCE = new NoFunc();

            private NoFunc() {
                super("nofunc", (byte) -13, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$None;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class None extends Simple {
            public static final None INSTANCE = new None();

            private None() {
                super("none", (byte) -15, null);
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple$Struct;", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Simple;", "<init>", "()V", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Struct extends Simple {
            public static final Struct INSTANCE = new Struct();

            private Struct() {
                super("struct", (byte) -21, null);
            }
        }

        private Simple(String str, byte b) {
            super(null);
            this.name = str;
            this.code = b;
        }

        public final byte getCode() {
            return this.code;
        }

        public final String getName() {
            return this.name;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Simple:");
            sb.append(this.name);
            sb.append('(');
            String string = Integer.toString(this.code, CharsKt.checkRadix(16));
            string.getClass();
            sb.append(string);
            sb.append(')');
            return sb.toString();
        }

        public /* synthetic */ Simple(String str, byte b, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, b);
        }
    }
}
