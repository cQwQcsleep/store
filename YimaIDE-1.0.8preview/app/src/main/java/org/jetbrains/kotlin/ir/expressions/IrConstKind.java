package org.jetbrains.kotlin.ir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\n\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\n\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "", "asString", "", "<init>", "(Ljava/lang/String;)V", "getAsString", "()Ljava/lang/String;", "toString", "Null", "Boolean", "Char", "Byte", "Short", "Int", "Long", "String", "Float", "Double", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Boolean;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Byte;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Char;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Double;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Float;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Int;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Long;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Null;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Short;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$String;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrConstKind {
    private final java.lang.String asString;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Boolean;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Boolean extends IrConstKind {
        public static final Boolean INSTANCE = new Boolean();

        private Boolean() {
            super("Boolean", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Byte;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Byte extends IrConstKind {
        public static final Byte INSTANCE = new Byte();

        private Byte() {
            super("Byte", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Char;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Char extends IrConstKind {
        public static final Char INSTANCE = new Char();

        private Char() {
            super("Char", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Double;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Double extends IrConstKind {
        public static final Double INSTANCE = new Double();

        private Double() {
            super("Double", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Float;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Float extends IrConstKind {
        public static final Float INSTANCE = new Float();

        private Float() {
            super("Float", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Int;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Int extends IrConstKind {
        public static final Int INSTANCE = new Int();

        private Int() {
            super("Int", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Long;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Long extends IrConstKind {
        public static final Long INSTANCE = new Long();

        private Long() {
            super("Long", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Null;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Null extends IrConstKind {
        public static final Null INSTANCE = new Null();

        private Null() {
            super("Null", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$Short;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Short extends IrConstKind {
        public static final Short INSTANCE = new Short();

        private Short() {
            super("Short", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrConstKind$String;", "Lorg/jetbrains/kotlin/ir/expressions/IrConstKind;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class String extends IrConstKind {
        public static final String INSTANCE = new String();

        private String() {
            super("String", null);
        }
    }

    private IrConstKind(java.lang.String str) {
        this.asString = str;
    }

    public final java.lang.String getAsString() {
        return this.asString;
    }

    public java.lang.String toString() {
        return this.asString;
    }

    public /* synthetic */ IrConstKind(java.lang.String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
