package org.jetbrains.kotlin.javac.resolve;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.javac.JavacWrapper;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaField;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u0011H\u0002J&\u0010\u0012\u001a\u0004\u0018\u00010\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/javac/resolve/ConstantEvaluator;", "", "containingClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "javac", "Lorg/jetbrains/kotlin/javac/JavacWrapper;", "compilationUnit", "Lcom/sun/source/tree/CompilationUnitTree;", Const.CONSTRUCTOR_NAME, "(Lorg/jetbrains/kotlin/load/java/structure/JavaClass;Lorg/jetbrains/kotlin/javac/JavacWrapper;Lcom/sun/source/tree/CompilationUnitTree;)V", "getValue", Constants.ATTRNAME_EXPR, "Lcom/sun/tools/javac/tree/JCTree$JCExpression;", "evaluateUnaryExpression", "value", "Lcom/sun/tools/javac/tree/JCTree$JCUnary;", "evaluateBinaryExpression", "Lcom/sun/tools/javac/tree/JCTree$JCBinary;", "evaluateValue", "lhsValue", "rhsValue", "opcode", "Lcom/sun/tools/javac/tree/JCTree$Tag;", "org.jetbrains.kotlin:javac-wrapper"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ConstantEvaluator {
    private final CompilationUnitTree compilationUnit;
    private final JavaClass containingClass;
    private final JavacWrapper javac;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            try {
                iArr[JCTree.Tag.COMPL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[JCTree.Tag.NOT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[JCTree.Tag.AND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[JCTree.Tag.OR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[JCTree.Tag.EQ.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[JCTree.Tag.NE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[JCTree.Tag.BITXOR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[JCTree.Tag.BITAND.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[JCTree.Tag.BITOR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[JCTree.Tag.PLUS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[JCTree.Tag.MINUS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[JCTree.Tag.MUL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[JCTree.Tag.DIV.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[JCTree.Tag.MOD.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[JCTree.Tag.SR.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[JCTree.Tag.SL.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[JCTree.Tag.USR.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[JCTree.Tag.LT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[JCTree.Tag.LE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[JCTree.Tag.GT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[JCTree.Tag.GE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ConstantEvaluator(JavaClass javaClass, JavacWrapper javacWrapper, CompilationUnitTree compilationUnitTree) {
        javaClass.getClass();
        javacWrapper.getClass();
        compilationUnitTree.getClass();
        this.containingClass = javaClass;
        this.javac = javacWrapper;
        this.compilationUnit = compilationUnitTree;
    }

    private final Object evaluateBinaryExpression(JCTree.JCBinary value) {
        JCTree.JCExpression jCExpression = value.lhs;
        jCExpression.getClass();
        Object value2 = getValue(jCExpression);
        if (value2 == null) {
            return null;
        }
        JCTree.JCExpression jCExpression2 = value.rhs;
        jCExpression2.getClass();
        Object value3 = getValue(jCExpression2);
        if (value3 == null) {
            return null;
        }
        JCTree.Tag tag = value.getTag();
        tag.getClass();
        return evaluateValue(value2, value3, tag);
    }

    private final Object evaluateUnaryExpression(JCTree.JCUnary value) {
        JCTree.JCExpression jCExpression = value.arg;
        jCExpression.getClass();
        Object value2 = getValue(jCExpression);
        JCTree.Tag tag = value.getTag();
        int i = tag == null ? -1 : WhenMappings.$EnumSwitchMapping$0[tag.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            Boolean bool = value2 instanceof Boolean ? (Boolean) value2 : null;
            if (bool != null) {
                return Boolean.valueOf(!bool.booleanValue());
            }
            return null;
        }
        if (value2 instanceof Integer) {
            return Integer.valueOf(~((Number) value2).intValue());
        }
        if (value2 instanceof Long) {
            return Long.valueOf(~((Number) value2).longValue());
        }
        if (value2 instanceof Short) {
            return Short.valueOf((short) (~((Number) value2).shortValue()));
        }
        if (value2 instanceof Byte) {
            return Byte.valueOf((byte) (~((Number) value2).byteValue()));
        }
        return null;
    }

    private final Object evaluateValue(Object lhsValue, Object rhsValue, JCTree.Tag opcode) {
        if ((lhsValue instanceof String) && opcode == JCTree.Tag.PLUS) {
            return ((String) lhsValue) + rhsValue;
        }
        boolean z = false;
        if ((lhsValue instanceof Boolean) && (rhsValue instanceof Boolean)) {
            switch (WhenMappings.$EnumSwitchMapping$0[opcode.ordinal()]) {
                case 3:
                    if (((Boolean) lhsValue).booleanValue() && ((Boolean) rhsValue).booleanValue()) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                case 4:
                    return Boolean.valueOf(((Boolean) lhsValue).booleanValue() || ((Boolean) rhsValue).booleanValue());
                case 5:
                    return Boolean.valueOf(Intrinsics.areEqual(lhsValue, rhsValue));
                case 6:
                    return Boolean.valueOf(!Intrinsics.areEqual(lhsValue, rhsValue));
                case 7:
                    return Boolean.valueOf(((Boolean) lhsValue).booleanValue() ^ ((Boolean) rhsValue).booleanValue());
                case 8:
                    return Boolean.valueOf(((Boolean) lhsValue).booleanValue() & ((Boolean) rhsValue).booleanValue());
                case 9:
                    return Boolean.valueOf(((Boolean) lhsValue).booleanValue() | ((Boolean) rhsValue).booleanValue());
                default:
                    return null;
            }
        }
        if (!(lhsValue instanceof Number) || !(rhsValue instanceof Number)) {
            return null;
        }
        boolean z2 = ((lhsValue instanceof Float) || (lhsValue instanceof Double) || (rhsValue instanceof Float) || (rhsValue instanceof Double)) ? false : true;
        boolean z3 = !z2 ? !((lhsValue instanceof Double) || (rhsValue instanceof Double)) : !((lhsValue instanceof Long) || (rhsValue instanceof Long));
        switch (WhenMappings.$EnumSwitchMapping$0[opcode.ordinal()]) {
            case 5:
                if (!z2 ? ((Number) lhsValue).doubleValue() == ((Number) rhsValue).doubleValue() : ((Number) lhsValue).longValue() == ((Number) rhsValue).longValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 6:
                if (!z2 ? ((Number) lhsValue).doubleValue() != ((Number) rhsValue).doubleValue() : ((Number) lhsValue).longValue() != ((Number) rhsValue).longValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 7:
                return z3 ? Long.valueOf(((Number) lhsValue).longValue() ^ ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() ^ ((Number) rhsValue).intValue());
            case 8:
                return z3 ? Long.valueOf(((Number) lhsValue).longValue() & ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() & ((Number) rhsValue).intValue());
            case 9:
                return z3 ? Long.valueOf(((Number) lhsValue).longValue() | ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() | ((Number) rhsValue).intValue());
            case 10:
                if (z2) {
                    return z3 ? Long.valueOf(((Number) lhsValue).longValue() + ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() + ((Number) rhsValue).intValue());
                }
                return z3 ? Double.valueOf(((Number) lhsValue).doubleValue() + ((Number) rhsValue).doubleValue()) : Float.valueOf(((Number) lhsValue).floatValue() + ((Number) rhsValue).floatValue());
            case 11:
                if (z2) {
                    return z3 ? Long.valueOf(((Number) lhsValue).longValue() - ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() - ((Number) rhsValue).intValue());
                }
                return z3 ? Double.valueOf(((Number) lhsValue).doubleValue() - ((Number) rhsValue).doubleValue()) : Float.valueOf(((Number) lhsValue).floatValue() - ((Number) rhsValue).floatValue());
            case 12:
                if (z2) {
                    return z3 ? Long.valueOf(((Number) lhsValue).longValue() * ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() * ((Number) rhsValue).intValue());
                }
                return z3 ? Double.valueOf(((Number) lhsValue).doubleValue() * ((Number) rhsValue).doubleValue()) : Float.valueOf(((Number) lhsValue).floatValue() * ((Number) rhsValue).floatValue());
            case 13:
                if (z2) {
                    return z3 ? Long.valueOf(((Number) lhsValue).longValue() / ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() / ((Number) rhsValue).intValue());
                }
                return z3 ? Double.valueOf(((Number) lhsValue).doubleValue() / ((Number) rhsValue).doubleValue()) : Float.valueOf(((Number) lhsValue).floatValue() / ((Number) rhsValue).floatValue());
            case 14:
                if (z2) {
                    return z3 ? Long.valueOf(((Number) lhsValue).longValue() % ((Number) rhsValue).longValue()) : Integer.valueOf(((Number) lhsValue).intValue() % ((Number) rhsValue).intValue());
                }
                return z3 ? Double.valueOf(((Number) lhsValue).doubleValue() % ((Number) rhsValue).doubleValue()) : Float.valueOf(((Number) lhsValue).floatValue() % ((Number) rhsValue).floatValue());
            case 15:
                return z3 ? Long.valueOf(((Number) lhsValue).longValue() >> ((Number) rhsValue).intValue()) : Integer.valueOf(((Number) lhsValue).intValue() >> ((Number) rhsValue).intValue());
            case 16:
                return z3 ? Long.valueOf(((Number) lhsValue).longValue() << ((Number) rhsValue).intValue()) : Integer.valueOf(((Number) lhsValue).intValue() << ((Number) rhsValue).intValue());
            case 17:
                return z3 ? Long.valueOf(((Number) lhsValue).longValue() >>> ((Number) rhsValue).intValue()) : Integer.valueOf(((Number) lhsValue).intValue() >>> ((Number) rhsValue).intValue());
            case 18:
                if (!z2 ? ((Number) lhsValue).doubleValue() < ((Number) rhsValue).doubleValue() : ((Number) lhsValue).longValue() < ((Number) rhsValue).longValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 19:
                if (!z2 ? ((Number) lhsValue).doubleValue() <= ((Number) rhsValue).doubleValue() : ((Number) lhsValue).longValue() <= ((Number) rhsValue).longValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 20:
                if (!z2 ? ((Number) lhsValue).doubleValue() > ((Number) rhsValue).doubleValue() : ((Number) lhsValue).longValue() > ((Number) rhsValue).longValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 21:
                if (!z2 ? ((Number) lhsValue).doubleValue() >= ((Number) rhsValue).doubleValue() : ((Number) lhsValue).longValue() >= ((Number) rhsValue).longValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    public final Object getValue(JCTree.JCExpression expr) {
        expr.getClass();
        if (expr instanceof JCTree.JCLiteral) {
            JCTree.JCLiteral jCLiteral = (JCTree.JCLiteral) expr;
            TypeTag typeTag = jCLiteral.typetag;
            TypeTag typeTag2 = TypeTag.BOOLEAN;
            Object obj = jCLiteral.value;
            return typeTag == typeTag2 ? Boolean.valueOf(!Intrinsics.areEqual(obj, 0)) : obj;
        }
        if ((expr instanceof JCTree.JCIdent) || (expr instanceof JCTree.JCFieldAccess)) {
            JavaField javaFieldResolveField = this.javac.resolveField(expr, this.compilationUnit, this.containingClass);
            if (javaFieldResolveField != null) {
                return javaFieldResolveField.getInitializerValue();
            }
            return null;
        }
        if (expr instanceof JCTree.JCBinary) {
            return evaluateBinaryExpression((JCTree.JCBinary) expr);
        }
        if (expr instanceof JCTree.JCParens) {
            JCTree.JCExpression jCExpression = ((JCTree.JCParens) expr).expr;
            jCExpression.getClass();
            return getValue(jCExpression);
        }
        if (expr instanceof JCTree.JCUnary) {
            return evaluateUnaryExpression((JCTree.JCUnary) expr);
        }
        return null;
    }
}
