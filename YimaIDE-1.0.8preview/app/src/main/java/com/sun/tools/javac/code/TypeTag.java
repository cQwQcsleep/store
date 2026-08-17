package com.sun.tools.javac.code;

import com.sun.source.tree.Tree;
import defpackage.s22;
import javax.lang.model.type.TypeKind;
import nbjavac.ExactConversionsSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum TypeTag {
    BYTE(1, 125, true),
    CHAR(2, 122, true),
    SHORT(4, 124, true),
    LONG(16, 112, true),
    FLOAT(32, 96, true),
    INT(8, 120, true),
    DOUBLE(64, 64, true),
    BOOLEAN(0, 0, true),
    VOID,
    CLASS,
    ARRAY,
    METHOD,
    PACKAGE,
    MODULE,
    TYPEVAR,
    WILDCARD,
    FORALL,
    DEFERRED,
    BOT,
    NONE,
    ERROR,
    UNDETVAR,
    UNINITIALIZED_THIS,
    UNINITIALIZED_OBJECT;

    final boolean isPrimitive;
    final int numericClass;
    final int superClasses;

    public static class NumericClasses {
        public static final int BYTE_CLASS = 1;
        static final int BYTE_SUPERCLASSES = 125;
        public static final int CHAR_CLASS = 2;
        static final int CHAR_SUPERCLASSES = 122;
        public static final int DOUBLE_CLASS = 64;
        public static final int FLOAT_CLASS = 32;
        static final int FLOAT_SUPERCLASSES = 96;
        public static final int INT_CLASS = 8;
        static final int INT_SUPERCLASSES = 120;
        public static final int LONG_CLASS = 16;
        static final int LONG_SUPERCLASSES = 112;
        public static final int SHORT_CLASS = 4;
        static final int SHORT_SUPERCLASSES = 124;
    }

    TypeTag() {
        this(0, 0, false);
    }

    public static int getTypeTagCount() {
        return UNDETVAR.ordinal() + 1;
    }

    public boolean checkRange(int i) {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return ExactConversionsSupport.isIntToByteExact(i);
        }
        if (iOrdinal == 1) {
            return ExactConversionsSupport.isIntToCharExact(i);
        }
        if (iOrdinal == 2) {
            return ExactConversionsSupport.isIntToShortExact(i);
        }
        if (iOrdinal == 5) {
            return true;
        }
        if (iOrdinal == 7) {
            return i >= 0 && i <= 1;
        }
        x1f.a();
        return false;
    }

    public Tree.Kind getKindLiteral() {
        int iOrdinal = ordinal();
        if (iOrdinal == 1) {
            return Tree.Kind.CHAR_LITERAL;
        }
        if (iOrdinal == 9) {
            return Tree.Kind.STRING_LITERAL;
        }
        if (iOrdinal == 18) {
            return Tree.Kind.NULL_LITERAL;
        }
        if (iOrdinal == 3) {
            return Tree.Kind.LONG_LITERAL;
        }
        if (iOrdinal == 4) {
            return Tree.Kind.FLOAT_LITERAL;
        }
        if (iOrdinal == 5) {
            return Tree.Kind.INT_LITERAL;
        }
        if (iOrdinal == 6) {
            return Tree.Kind.DOUBLE_LITERAL;
        }
        if (iOrdinal == 7) {
            return Tree.Kind.BOOLEAN_LITERAL;
        }
        s22.a("unknown literal kind ", this);
        return null;
    }

    public TypeKind getPrimitiveTypeKind() {
        switch (this) {
            case BYTE:
                return TypeKind.BYTE;
            case CHAR:
                return TypeKind.CHAR;
            case SHORT:
                return TypeKind.SHORT;
            case LONG:
                return TypeKind.LONG;
            case FLOAT:
                return TypeKind.FLOAT;
            case INT:
                return TypeKind.INT;
            case DOUBLE:
                return TypeKind.DOUBLE;
            case BOOLEAN:
                return TypeKind.BOOLEAN;
            case VOID:
                return TypeKind.VOID;
            default:
                s22.a("unknown primitive type ", this);
                return null;
        }
    }

    public boolean isInSuperClassesOf(TypeTag typeTag) {
        return (this.numericClass & typeTag.superClasses) != 0;
    }

    public boolean isNumeric() {
        return this.numericClass != 0;
    }

    public boolean isStrictSubRangeOf(TypeTag typeTag) {
        return ((this.superClasses & typeTag.numericClass) == 0 || this == typeTag) ? false : true;
    }

    public boolean isSubRangeOf(TypeTag typeTag) {
        return (this.superClasses & typeTag.numericClass) != 0;
    }

    TypeTag(int i, int i2, boolean z) {
        this.superClasses = i2;
        this.numericClass = i;
        this.isPrimitive = z;
    }
}
