package com.sun.tools.javac.code;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeAnnotationPosition {
    public static final List<TypePathEntry> emptyPath;
    public static final TypeAnnotationPosition unknown;
    public final int bound_index;
    public List<TypePathEntry> location;
    public final JCTree.JCLambda onLambda;
    public int parameter_index;
    public final int pos;
    public final TargetType type;
    public final int type_index;
    public boolean isValidOffset = false;
    public int offset = -1;
    public int[] lvarOffset = null;
    public int[] lvarLength = null;
    public int[] lvarIndex = null;
    private int exception_index = Integer.MIN_VALUE;
    private int exceptionStartPos = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: com.sun.tools.javac.code.TypeAnnotationPosition$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TargetType;

        static {
            int[] iArr = new int[TargetType.values().length];
            $SwitchMap$com$sun$tools$javac$code$TargetType = iArr;
            try {
                iArr[TargetType.INSTANCEOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.LOCAL_VARIABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_RECEIVER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_TYPE_PARAMETER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_TYPE_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_TYPE_PARAMETER_BOUND.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_TYPE_PARAMETER_BOUND.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_EXTENDS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.THROWS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.EXCEPTION_PARAMETER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_FORMAL_PARAMETER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CAST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_INVOCATION_TYPE_ARGUMENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_REFERENCE_TYPE_ARGUMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_RETURN.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.FIELD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.UNKNOWN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public enum TypePathEntryKind {
        ARRAY(0),
        INNER_TYPE(1),
        WILDCARD(2),
        TYPE_ARGUMENT(3);

        public final int tag;

        TypePathEntryKind(int i) {
            this.tag = i;
        }
    }

    static {
        List<TypePathEntry> listNil = List.nil();
        emptyPath = listNil;
        unknown = new TypeAnnotationPosition(TargetType.UNKNOWN, -1, Integer.MIN_VALUE, null, Integer.MIN_VALUE, Integer.MIN_VALUE, listNil);
    }

    private TypeAnnotationPosition(TargetType targetType, int i, int i2, JCTree.JCLambda jCLambda, int i3, int i4, List<TypePathEntry> list) {
        Assert.checkNonNull(list);
        this.type = targetType;
        this.pos = i;
        this.parameter_index = i2;
        this.onLambda = jCLambda;
        this.type_index = i3;
        this.bound_index = i4;
        this.location = list;
    }

    public static TypeAnnotationPosition classExtends(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.CLASS_EXTENDS, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition constructorInvocationTypeArg(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition constructorRef(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.CONSTRUCTOR_REFERENCE, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition constructorRefTypeArg(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition exceptionParameter(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.EXCEPTION_PARAMETER, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition field(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.FIELD, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static List<Integer> getBinaryFromTypePath(java.util.List<TypePathEntry> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (TypePathEntry typePathEntry : list) {
            listBuffer = listBuffer.append(Integer.valueOf(typePathEntry.tag.tag)).append(Integer.valueOf(typePathEntry.arg));
        }
        return listBuffer.toList();
    }

    public static List<TypePathEntry> getTypePathFromBinary(java.util.List<Integer> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            Assert.check(it.hasNext());
            listBuffer = listBuffer.append(TypePathEntry.fromBinary(next.intValue(), it.next().intValue()));
        }
        return listBuffer.toList();
    }

    public static TypeAnnotationPosition instanceOf(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.INSTANCEOF, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition localVariable(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.LOCAL_VARIABLE, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodInvocationTypeArg(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.METHOD_INVOCATION_TYPE_ARGUMENT, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodParameter(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.METHOD_FORMAL_PARAMETER, i2, i, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodReceiver(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.METHOD_RECEIVER, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodRef(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.METHOD_REFERENCE, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodRefTypeArg(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.METHOD_REFERENCE_TYPE_ARGUMENT, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodReturn(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.METHOD_RETURN, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodThrows(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.THROWS, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodTypeParameter(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.METHOD_TYPE_PARAMETER, i2, i, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition methodTypeParameterBound(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2, int i3) {
        return new TypeAnnotationPosition(TargetType.METHOD_TYPE_PARAMETER_BOUND, i3, i, jCLambda, Integer.MIN_VALUE, i2, list);
    }

    public static TypeAnnotationPosition newObj(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.NEW, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition resourceVariable(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return new TypeAnnotationPosition(TargetType.RESOURCE_VARIABLE, i, Integer.MIN_VALUE, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition typeCast(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.CAST, i2, Integer.MIN_VALUE, jCLambda, i, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition typeParameter(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2) {
        return new TypeAnnotationPosition(TargetType.CLASS_TYPE_PARAMETER, i2, i, jCLambda, Integer.MIN_VALUE, Integer.MIN_VALUE, list);
    }

    public static TypeAnnotationPosition typeParameterBound(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i, int i2, int i3) {
        return new TypeAnnotationPosition(TargetType.CLASS_TYPE_PARAMETER_BOUND, i3, i, jCLambda, Integer.MIN_VALUE, i2, list);
    }

    public boolean emitToClassfile() {
        return !this.type.isLocal() || this.isValidOffset;
    }

    public int getCatchType() {
        Assert.check(hasCatchType(), "exception_index does not contain valid catch info");
        return (-this.exception_index) - 1;
    }

    public int getExceptionIndex() {
        Assert.check(this.exception_index >= 0, "exception_index is not set");
        return this.exception_index;
    }

    public int getStartPos() {
        Assert.check(this.exceptionStartPos >= 0, "exceptionStartPos does not contain valid start position");
        return this.exceptionStartPos;
    }

    public boolean hasCatchType() {
        int i = this.exception_index;
        return i < 0 && i != Integer.MIN_VALUE;
    }

    public boolean hasExceptionIndex() {
        return this.exception_index >= 0;
    }

    public boolean matchesPos(int i) {
        return this.pos == i;
    }

    public void setCatchInfo(int i, int i2) {
        Assert.check(!hasExceptionIndex(), "exception_index is already set");
        Assert.check(i >= 0, "Expected a valid catch type");
        Assert.check(i2 >= 0, "Expected a valid start position");
        this.exception_index = -(i + 1);
        this.exceptionStartPos = i2;
    }

    public void setExceptionIndex(int i) {
        Assert.check(!hasExceptionIndex(), "exception_index already set");
        Assert.check(i >= 0, "Expected a valid index into exception table");
        this.exception_index = i;
        this.isValidOffset = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.type);
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TargetType[this.type.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                sb.append(", offset = ");
                sb.append(this.offset);
                break;
            case 5:
            case 6:
                if (this.lvarOffset == null) {
                    sb.append(", lvarOffset is null!");
                } else {
                    sb.append(", {");
                    for (int i = 0; i < this.lvarOffset.length; i++) {
                        if (i != 0) {
                            sb.append("; ");
                        }
                        sb.append("start_pc = ");
                        sb.append(this.lvarOffset[i]);
                        sb.append(", length = ");
                        sb.append(this.lvarLength[i]);
                        sb.append(", index = ");
                        sb.append(this.lvarIndex[i]);
                    }
                    sb.append("}");
                }
                break;
            case 7:
            case 21:
            case 22:
                break;
            case 8:
            case 9:
                sb.append(", param_index = ");
                sb.append(this.parameter_index);
                break;
            case 10:
            case 11:
                sb.append(", param_index = ");
                sb.append(this.parameter_index);
                sb.append(", bound_index = ");
                sb.append(this.bound_index);
                break;
            case 12:
                sb.append(", type_index = ");
                sb.append(this.type_index);
                break;
            case 13:
                sb.append(", type_index = ");
                sb.append(this.type_index);
                break;
            case 14:
                sb.append(", exception_index = ");
                sb.append(this.exception_index);
                break;
            case 15:
                sb.append(", param_index = ");
                sb.append(this.parameter_index);
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                sb.append(", offset = ");
                sb.append(this.offset);
                sb.append(", type_index = ");
                sb.append(this.type_index);
                break;
            case 23:
                sb.append(", position UNKNOWN!");
                break;
            default:
                Assert.error("Unknown target type: " + this.type);
                break;
        }
        if (!this.location.isEmpty()) {
            sb.append(", location = (");
            sb.append(this.location);
            sb.append(")");
        }
        sb.append(", pos = ");
        sb.append(this.pos);
        if (this.onLambda != null) {
            sb.append(", onLambda hash = ");
            sb.append(this.onLambda.hashCode());
        }
        sb.append(']');
        return sb.toString();
    }

    public void updatePosOffset(int i) {
        this.offset = i;
        this.isValidOffset = true;
    }

    public static TypeAnnotationPosition methodTypeParameterBound(List<TypePathEntry> list, int i, int i2) {
        return methodTypeParameterBound(list, null, i, i2, -1);
    }

    public static TypeAnnotationPosition typeParameterBound(List<TypePathEntry> list, int i, int i2) {
        return typeParameterBound(list, null, i, i2, -1);
    }

    public static TypeAnnotationPosition classExtends(List<TypePathEntry> list, JCTree.JCLambda jCLambda, int i) {
        return classExtends(list, jCLambda, 65535, i);
    }

    public static TypeAnnotationPosition constructorInvocationTypeArg(List<TypePathEntry> list, int i) {
        return constructorInvocationTypeArg(list, null, i, -1);
    }

    public static TypeAnnotationPosition constructorRefTypeArg(List<TypePathEntry> list, int i) {
        return constructorRefTypeArg(list, null, i, -1);
    }

    public static TypeAnnotationPosition methodInvocationTypeArg(List<TypePathEntry> list, int i) {
        return methodInvocationTypeArg(list, null, i, -1);
    }

    public static TypeAnnotationPosition methodParameter(JCTree.JCLambda jCLambda, int i, int i2) {
        return methodParameter(emptyPath, jCLambda, i, i2);
    }

    public static TypeAnnotationPosition methodRefTypeArg(List<TypePathEntry> list, int i) {
        return methodRefTypeArg(list, null, i, -1);
    }

    public static TypeAnnotationPosition methodThrows(List<TypePathEntry> list, int i) {
        return methodThrows(list, null, i, -1);
    }

    public static TypeAnnotationPosition methodTypeParameter(List<TypePathEntry> list, int i) {
        return methodTypeParameter(list, null, i, -1);
    }

    public static TypeAnnotationPosition typeCast(List<TypePathEntry> list, int i) {
        return typeCast(list, null, i, -1);
    }

    public static TypeAnnotationPosition typeParameter(List<TypePathEntry> list, int i) {
        return typeParameter(list, null, i, -1);
    }

    public static TypeAnnotationPosition classExtends(List<TypePathEntry> list, int i) {
        return classExtends(list, null, i, -1);
    }

    public static TypeAnnotationPosition constructorRef(List<TypePathEntry> list) {
        return constructorRef(list, null, -1);
    }

    public static TypeAnnotationPosition exceptionParameter(JCTree.JCLambda jCLambda, int i) {
        return exceptionParameter(emptyPath, jCLambda, i);
    }

    public static TypeAnnotationPosition field(List<TypePathEntry> list) {
        return field(list, null, -1);
    }

    public static TypeAnnotationPosition instanceOf(List<TypePathEntry> list) {
        return instanceOf(list, null, -1);
    }

    public static TypeAnnotationPosition localVariable(JCTree.JCLambda jCLambda, int i) {
        return localVariable(emptyPath, jCLambda, i);
    }

    public static TypeAnnotationPosition methodParameter(int i, int i2) {
        return methodParameter(null, i, i2);
    }

    public static TypeAnnotationPosition methodReceiver(List<TypePathEntry> list) {
        return methodReceiver(list, null, -1);
    }

    public static TypeAnnotationPosition methodRef(List<TypePathEntry> list) {
        return methodRef(list, null, -1);
    }

    public static TypeAnnotationPosition methodReturn(List<TypePathEntry> list) {
        return methodReturn(list, null, -1);
    }

    public static TypeAnnotationPosition newObj(int i) {
        return newObj(emptyPath, null, i);
    }

    public static TypeAnnotationPosition resourceVariable(JCTree.JCLambda jCLambda, int i) {
        return resourceVariable(emptyPath, jCLambda, i);
    }

    public static TypeAnnotationPosition classExtends(int i, int i2) {
        return classExtends(emptyPath, null, i, i2);
    }

    public static TypeAnnotationPosition exceptionParameter(List<TypePathEntry> list) {
        return exceptionParameter(list, null, -1);
    }

    public static TypeAnnotationPosition field(int i) {
        return field(emptyPath, null, i);
    }

    public static TypeAnnotationPosition localVariable(List<TypePathEntry> list) {
        return localVariable(list, null, -1);
    }

    public static TypeAnnotationPosition methodParameter(List<TypePathEntry> list, int i) {
        return methodParameter(list, null, i, -1);
    }

    public static TypeAnnotationPosition methodReceiver(int i) {
        return methodReceiver(emptyPath, null, i);
    }

    public static TypeAnnotationPosition methodReturn(int i) {
        return methodReturn(emptyPath, null, i);
    }

    public static TypeAnnotationPosition newObj(List<TypePathEntry> list) {
        return newObj(list, null, -1);
    }

    public static TypeAnnotationPosition resourceVariable(List<TypePathEntry> list) {
        return resourceVariable(list, null, -1);
    }

    public static TypeAnnotationPosition classExtends(int i) {
        return classExtends(65535, i);
    }

    public static class TypePathEntry {
        public static final TypePathEntry ARRAY = new TypePathEntry(TypePathEntryKind.ARRAY);
        public static final TypePathEntry INNER_TYPE = new TypePathEntry(TypePathEntryKind.INNER_TYPE);
        public static final TypePathEntry WILDCARD = new TypePathEntry(TypePathEntryKind.WILDCARD);
        public static final int bytesPerEntry = 2;
        public final int arg;
        public final TypePathEntryKind tag;

        private TypePathEntry(TypePathEntryKind typePathEntryKind) {
            Assert.check(typePathEntryKind == TypePathEntryKind.ARRAY || typePathEntryKind == TypePathEntryKind.INNER_TYPE || typePathEntryKind == TypePathEntryKind.WILDCARD);
            this.tag = typePathEntryKind;
            this.arg = 0;
        }

        public static TypePathEntry fromBinary(int i, int i2) {
            Assert.check(i2 == 0 || i == TypePathEntryKind.TYPE_ARGUMENT.tag);
            if (i == 0) {
                return ARRAY;
            }
            if (i == 1) {
                return INNER_TYPE;
            }
            if (i == 2) {
                return WILDCARD;
            }
            if (i == 3) {
                return new TypePathEntry(TypePathEntryKind.TYPE_ARGUMENT, i2);
            }
            Assert.error("Invalid TypePathEntryKind tag: " + i);
            return null;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TypePathEntry)) {
                return false;
            }
            TypePathEntry typePathEntry = (TypePathEntry) obj;
            return this.tag == typePathEntry.tag && this.arg == typePathEntry.arg;
        }

        public int hashCode() {
            return (this.tag.hashCode() * 17) + this.arg;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(this.tag.toString());
            if (this.tag == TypePathEntryKind.TYPE_ARGUMENT) {
                str = "(" + this.arg + ")";
            } else {
                str = "";
            }
            sb.append(str);
            return sb.toString();
        }

        public TypePathEntry(TypePathEntryKind typePathEntryKind, int i) {
            Assert.check(typePathEntryKind == TypePathEntryKind.TYPE_ARGUMENT);
            this.tag = typePathEntryKind;
            this.arg = i;
        }
    }
}
