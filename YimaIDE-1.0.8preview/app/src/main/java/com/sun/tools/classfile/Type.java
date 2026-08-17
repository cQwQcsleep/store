package com.sun.tools.classfile;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Type {

    /* JADX INFO: renamed from: com.sun.tools.classfile.Type$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind;

        static {
            int[] iArr = new int[WildcardType.Kind.values().length];
            $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind = iArr;
            try {
                iArr[WildcardType.Kind.UNBOUNDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind[WildcardType.Kind.EXTENDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind[WildcardType.Kind.SUPER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class ArrayType extends Type {
        public final Type elemType;

        public ArrayType(Type type) {
            this.elemType = type;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitArrayType(this, d);
        }

        public String toString() {
            return this.elemType + "[]";
        }
    }

    public static class ClassSigType extends Type {
        public final Type superclassType;
        public final List<Type> superinterfaceTypes;
        public final List<TypeParamType> typeParamTypes;

        public ClassSigType(List<TypeParamType> list, Type type, List<Type> list2) {
            this.typeParamTypes = list;
            this.superclassType = type;
            this.superinterfaceTypes = list2;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitClassSigType(this, d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Type.appendIfNotEmpty(sb, "<", this.typeParamTypes, ">");
            if (this.superclassType != null) {
                sb.append(" extends ");
                sb.append(this.superclassType);
            }
            Type.appendIfNotEmpty(sb, " implements ", this.superinterfaceTypes, "");
            return sb.toString();
        }
    }

    public static class ClassType extends Type {
        public final String name;
        public final ClassType outerType;
        public final List<Type> typeArgs;

        public ClassType(ClassType classType, String str, List<Type> list) {
            this.outerType = classType;
            this.name = str;
            this.typeArgs = list;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitClassType(this, d);
        }

        public String getBinaryName() {
            if (this.outerType == null) {
                return this.name;
            }
            return this.outerType.getBinaryName() + "$" + this.name;
        }

        @Override // com.sun.tools.classfile.Type
        public boolean isObject() {
            if (this.outerType != null || !this.name.equals("java/lang/Object")) {
                return false;
            }
            List<Type> list = this.typeArgs;
            return list == null || list.isEmpty();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            ClassType classType = this.outerType;
            if (classType != null) {
                sb.append(classType);
                sb.append(Constants.ATTRVAL_THIS);
            }
            sb.append(this.name);
            Type.appendIfNotEmpty(sb, "<", this.typeArgs, ">");
            return sb.toString();
        }
    }

    public static class SimpleType extends Type {
        private static final Set<String> primitiveTypes = new HashSet(Arrays.asList("boolean", "byte", PsiKeyword.CHAR, "double", "float", "int", "long", "short", PsiKeyword.VOID));
        public final String name;

        public SimpleType(String str) {
            this.name = str;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitSimpleType(this, d);
        }

        public boolean isPrimitiveType() {
            return primitiveTypes.contains(this.name);
        }

        public String toString() {
            return this.name;
        }
    }

    public static class TypeParamType extends Type {
        public final Type classBound;
        public final List<Type> interfaceBounds;
        public final String name;

        public TypeParamType(String str, Type type, List<Type> list) {
            this.name = str;
            this.classBound = type;
            this.interfaceBounds = list;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitTypeParamType(this, d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            String str = " extends ";
            if (this.classBound != null) {
                sb.append(" extends ");
                sb.append(this.classBound);
                str = " & ";
            }
            List<Type> list = this.interfaceBounds;
            if (list != null) {
                for (Type type : list) {
                    sb.append(str);
                    sb.append(type);
                    str = " & ";
                }
            }
            return sb.toString();
        }
    }

    public interface Visitor<R, P> {
        R visitArrayType(ArrayType arrayType, P p);

        R visitClassSigType(ClassSigType classSigType, P p);

        R visitClassType(ClassType classType, P p);

        R visitMethodType(MethodType methodType, P p);

        R visitSimpleType(SimpleType simpleType, P p);

        R visitTypeParamType(TypeParamType typeParamType, P p);

        R visitWildcardType(WildcardType wildcardType, P p);
    }

    public static void append(StringBuilder sb, String str, List<? extends Type> list, String str2) {
        sb.append(str);
        String str3 = "";
        for (Type type : list) {
            sb.append(str3);
            sb.append(type);
            str3 = ", ";
        }
        sb.append(str2);
    }

    public static void appendIfNotEmpty(StringBuilder sb, String str, List<? extends Type> list, String str2) {
        if (list == null || list.size() <= 0) {
            return;
        }
        append(sb, str, list, str2);
    }

    public abstract <R, D> R accept(Visitor<R, D> visitor, D d);

    public boolean isObject() {
        return false;
    }

    public static class WildcardType extends Type {
        public final Type boundType;
        public final Kind kind;

        public enum Kind {
            UNBOUNDED,
            EXTENDS,
            SUPER
        }

        public WildcardType(Kind kind, Type type) {
            this.kind = kind;
            this.boundType = type;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitWildcardType(this, d);
        }

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind[this.kind.ordinal()];
            if (i == 1) {
                return "?";
            }
            if (i == 2) {
                return "? extends " + this.boundType;
            }
            if (i != 3) {
                x1f.a();
                return null;
            }
            return "? super " + this.boundType;
        }

        public WildcardType() {
            this(Kind.UNBOUNDED, null);
        }
    }

    public static class MethodType extends Type {
        public final List<? extends Type> paramTypes;
        public final Type returnType;
        public final List<? extends Type> throwsTypes;
        public final List<? extends TypeParamType> typeParamTypes;

        public MethodType(List<? extends TypeParamType> list, List<? extends Type> list2, Type type, List<? extends Type> list3) {
            this.typeParamTypes = list;
            this.paramTypes = list2;
            this.returnType = type;
            this.throwsTypes = list3;
        }

        @Override // com.sun.tools.classfile.Type
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodType(this, d);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Type.appendIfNotEmpty(sb, "<", this.typeParamTypes, "> ");
            sb.append(this.returnType);
            Type.append(sb, " (", this.paramTypes, ")");
            Type.appendIfNotEmpty(sb, " throws ", this.throwsTypes, "");
            return sb.toString();
        }

        public MethodType(List<? extends Type> list, Type type) {
            this(null, list, type, null);
        }
    }
}
