package org.jetbrains.kotlin.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0011\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001eB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000b\u001a\u00020\u0000H\u0016J\b\u0010\f\u001a\u00020\u0000H\u0016J\n\u0010\r\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\b\u0010\n\u0082\u0001\u0011\u001f !\"#$%&'()*+,-./¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind;", "", "asString", "", "<init>", "(Ljava/lang/String;)V", "getAsString", "()Ljava/lang/String;", "isUnsigned", "", "()Z", "toSigned", "toUnsigned", "toString", "Null", "Boolean", "Char", "Byte", "UnsignedByte", "Short", "UnsignedShort", "Int", "UnsignedInt", "Long", "UnsignedLong", "String", "Float", "Double", "Error", "IntegerLiteral", "UnsignedIntegerLiteral", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Boolean;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Byte;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Char;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Double;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Error;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Float;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Int;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$IntegerLiteral;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Long;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Null;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$Short;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$String;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedByte;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedInt;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedIntegerLiteral;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedLong;", "Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedShort;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public abstract class ConstantValueKind {
    private final java.lang.String asString;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Boolean;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Boolean extends ConstantValueKind {
        public static final Boolean INSTANCE = new Boolean();

        private Boolean() {
            super("Boolean", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Byte;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toUnsigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Byte extends ConstantValueKind {
        public static final Byte INSTANCE = new Byte();

        private Byte() {
            super("Byte", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toUnsigned() {
            return UnsignedByte.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Char;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Char extends ConstantValueKind {
        public static final Char INSTANCE = new Char();

        private Char() {
            super("Char", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Double;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Double extends ConstantValueKind {
        public static final Double INSTANCE = new Double();

        private Double() {
            super("Double", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Error;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Error extends ConstantValueKind {
        public static final Error INSTANCE = new Error();

        private Error() {
            super("Error", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Float;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Float extends ConstantValueKind {
        public static final Float INSTANCE = new Float();

        private Float() {
            super("Float", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Int;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toUnsigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Int extends ConstantValueKind {
        public static final Int INSTANCE = new Int();

        private Int() {
            super("Int", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toUnsigned() {
            return UnsignedInt.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$IntegerLiteral;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toUnsigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class IntegerLiteral extends ConstantValueKind {
        public static final IntegerLiteral INSTANCE = new IntegerLiteral();

        private IntegerLiteral() {
            super("IntegerLiteral", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toUnsigned() {
            return UnsignedIntegerLiteral.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Long;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toUnsigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Long extends ConstantValueKind {
        public static final Long INSTANCE = new Long();

        private Long() {
            super("Long", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toUnsigned() {
            return UnsignedLong.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Null;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Null extends ConstantValueKind {
        public static final Null INSTANCE = new Null();

        private Null() {
            super("Null", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$Short;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toUnsigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Short extends ConstantValueKind {
        public static final Short INSTANCE = new Short();

        private Short() {
            super("Short", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toUnsigned() {
            return UnsignedShort.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$String;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class String extends ConstantValueKind {
        public static final String INSTANCE = new String();

        private String() {
            super("String", null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedByte;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toSigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class UnsignedByte extends ConstantValueKind {
        public static final UnsignedByte INSTANCE = new UnsignedByte();

        private UnsignedByte() {
            super("UByte", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toSigned() {
            return Byte.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedInt;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toSigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class UnsignedInt extends ConstantValueKind {
        public static final UnsignedInt INSTANCE = new UnsignedInt();

        private UnsignedInt() {
            super("UInt", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toSigned() {
            return Int.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedIntegerLiteral;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toSigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class UnsignedIntegerLiteral extends ConstantValueKind {
        public static final UnsignedIntegerLiteral INSTANCE = new UnsignedIntegerLiteral();

        private UnsignedIntegerLiteral() {
            super("UnsignedIntegerLiteral", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toSigned() {
            return IntegerLiteral.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedLong;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toSigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class UnsignedLong extends ConstantValueKind {
        public static final UnsignedLong INSTANCE = new UnsignedLong();

        private UnsignedLong() {
            super("ULong", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toSigned() {
            return Long.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0001H\u0016¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/types/ConstantValueKind$UnsignedShort;", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "<init>", "()V", "toSigned", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class UnsignedShort extends ConstantValueKind {
        public static final UnsignedShort INSTANCE = new UnsignedShort();

        private UnsignedShort() {
            super("UShort", null);
        }

        @Override // org.jetbrains.kotlin.types.ConstantValueKind
        public ConstantValueKind toSigned() {
            return Short.INSTANCE;
        }
    }

    private ConstantValueKind(java.lang.String str) {
        this.asString = str;
    }

    public final java.lang.String getAsString() {
        return this.asString;
    }

    public final boolean isUnsigned() {
        return this.asString.charAt(0) == 'U';
    }

    public ConstantValueKind toSigned() {
        return this;
    }

    public java.lang.String toString() {
        return this.asString;
    }

    public ConstantValueKind toUnsigned() {
        return this;
    }

    public /* synthetic */ ConstantValueKind(java.lang.String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }
}
