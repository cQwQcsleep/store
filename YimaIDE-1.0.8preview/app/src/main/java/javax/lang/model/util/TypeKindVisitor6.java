package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.NoType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class TypeKindVisitor6<R, P> extends SimpleTypeVisitor6<R, P> {

    /* JADX INFO: renamed from: javax.lang.model.util.TypeKindVisitor6$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind;

        static {
            int[] iArr = new int[TypeKind.values().length];
            $SwitchMap$javax$lang$model$type$TypeKind = iArr;
            try {
                iArr[TypeKind.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.CHAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.VOID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.PACKAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.MODULE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.NONE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @Deprecated
    public TypeKindVisitor6() {
        super(null);
    }

    @Override // javax.lang.model.util.SimpleTypeVisitor6, javax.lang.model.type.TypeVisitor
    public R visitNoType(NoType noType, P p) {
        TypeKind kind = noType.getKind();
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[kind.ordinal()]) {
            case 9:
                return visitNoTypeAsVoid(noType, p);
            case 10:
                return visitNoTypeAsPackage(noType, p);
            case 11:
                return visitNoTypeAsModule(noType, p);
            case 12:
                return visitNoTypeAsNone(noType, p);
            default:
                md6.a("Bad kind ", kind, " for NoType", noType);
                return null;
        }
    }

    public R visitNoTypeAsModule(NoType noType, P p) {
        return visitUnknown(noType, p);
    }

    public R visitNoTypeAsNone(NoType noType, P p) {
        return defaultAction(noType, p);
    }

    public R visitNoTypeAsPackage(NoType noType, P p) {
        return defaultAction(noType, p);
    }

    public R visitNoTypeAsVoid(NoType noType, P p) {
        return defaultAction(noType, p);
    }

    @Override // javax.lang.model.util.SimpleTypeVisitor6, javax.lang.model.type.TypeVisitor
    public R visitPrimitive(PrimitiveType primitiveType, P p) {
        TypeKind kind = primitiveType.getKind();
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[kind.ordinal()]) {
            case 1:
                return visitPrimitiveAsBoolean(primitiveType, p);
            case 2:
                return visitPrimitiveAsByte(primitiveType, p);
            case 3:
                return visitPrimitiveAsShort(primitiveType, p);
            case 4:
                return visitPrimitiveAsInt(primitiveType, p);
            case 5:
                return visitPrimitiveAsLong(primitiveType, p);
            case 6:
                return visitPrimitiveAsChar(primitiveType, p);
            case 7:
                return visitPrimitiveAsFloat(primitiveType, p);
            case 8:
                return visitPrimitiveAsDouble(primitiveType, p);
            default:
                md6.a("Bad kind ", kind, " for PrimitiveType", primitiveType);
                return null;
        }
    }

    public R visitPrimitiveAsBoolean(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsByte(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsChar(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsDouble(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsFloat(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsInt(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsLong(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    public R visitPrimitiveAsShort(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    @Deprecated
    public TypeKindVisitor6(R r) {
        super(r);
    }
}
