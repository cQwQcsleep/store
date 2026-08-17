package com.sun.tools.javac.code;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Constants;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Pair;
import defpackage.s22;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.type.DeclaredType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Attribute implements AnnotationValue {
    public Type type;

    /* JADX INFO: renamed from: com.sun.tools.javac.code.Attribute$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public enum RetentionPolicy {
        SOURCE,
        CLASS,
        RUNTIME
    }

    public static class UnresolvedClass extends Error {
        public Type classType;

        public UnresolvedClass(Type type, Type type2) {
            super(type);
            this.classType = type2;
        }
    }

    public interface Visitor {
        void visitArray(Array array);

        void visitClass(Class r1);

        void visitCompound(Compound compound);

        void visitConstant(Constant constant);

        void visitEnum(Enum r1);

        void visitError(Error error);
    }

    public Attribute(Type type) {
        this.type = type;
    }

    @Override // javax.lang.model.element.AnnotationValue
    public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
        throw new UnsupportedOperationException();
    }

    public abstract void accept(Visitor visitor);

    public TypeAnnotationPosition getPosition() {
        return null;
    }

    @Override // javax.lang.model.element.AnnotationValue
    public Object getValue() {
        throw new UnsupportedOperationException();
    }

    public boolean isSynthesized() {
        return false;
    }

    public static class Class extends Attribute {
        public final Type classType;

        public Class(Types types, Type type) {
            super(makeClassType(types, type));
            this.classType = type;
        }

        public static Type makeClassType(Types types, Type type) {
            return new Type.ClassType(types.syms.classType.getEnclosingType(), List.of(type.isPrimitive() ? types.boxedClass(type).type : types.erasure(type)), types.syms.classType.tsym);
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
            return annotationValueVisitor.visitType(this.classType, p);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return this.classType + JavaClass.EXTENSION;
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public Type getValue() {
            return this.classType;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Visitor visitor) {
            visitor.visitClass(this);
        }
    }

    public static class Compound extends Attribute implements AnnotationMirror {
        public TypeAnnotationPosition position;
        private boolean synthesized;
        public final List<Pair<Symbol.MethodSymbol, Attribute>> values;

        public Compound(Type type, List<Pair<Symbol.MethodSymbol, Attribute>> list, TypeAnnotationPosition typeAnnotationPosition) {
            super(type);
            this.synthesized = false;
            this.values = list;
            this.position = typeAnnotationPosition;
        }

        private Pair<Symbol.MethodSymbol, Attribute> getElemPair(Name name) {
            for (Pair<Symbol.MethodSymbol, Attribute> pair : this.values) {
                if (pair.fst.name == name) {
                    return pair;
                }
            }
            return null;
        }

        private Compound getFirstEmbeddedTC() {
            if (this.values.size() != 1) {
                return null;
            }
            Pair<Symbol.MethodSymbol, Attribute> pair = this.values.get(0);
            if (!pair.fst.getSimpleName().contentEquals("value")) {
                return null;
            }
            Attribute attribute = pair.snd;
            if (!(attribute instanceof Array)) {
                return null;
            }
            Attribute[] attributeArr = ((Array) attribute).values;
            if (attributeArr.length == 0) {
                return null;
            }
            Attribute attribute2 = attributeArr[0];
            if (attribute2 instanceof TypeCompound) {
                return (TypeCompound) attribute2;
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
            return annotationValueVisitor.visitAnnotation(this, p);
        }

        @Override // javax.lang.model.element.AnnotationMirror
        public DeclaredType getAnnotationType() {
            return (DeclaredType) this.type;
        }

        @Override // javax.lang.model.element.AnnotationMirror
        public Map<Symbol.MethodSymbol, Attribute> getElementValues() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Pair<Symbol.MethodSymbol, Attribute> pair : this.values) {
                linkedHashMap.put(pair.fst, pair.snd);
            }
            return linkedHashMap;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public TypeAnnotationPosition getPosition() {
            if (hasUnknownPosition() && this.values.size() != 0) {
                Pair<Symbol.MethodSymbol, Attribute> elemPair = getElemPair(this.values.head.fst.name.table.names.value);
                this.position = elemPair == null ? null : elemPair.snd.getPosition();
            }
            return this.position;
        }

        public boolean hasUnknownPosition() {
            return this.position.type == TargetType.UNKNOWN;
        }

        public boolean isContainerTypeCompound() {
            return isSynthesized() && this.values.size() == 1 && getFirstEmbeddedTC() != null;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public boolean isSynthesized() {
            return this.synthesized;
        }

        public Attribute member(Name name) {
            Pair<Symbol.MethodSymbol, Attribute> elemPair = getElemPair(name);
            if (elemPair == null) {
                return null;
            }
            return elemPair.snd;
        }

        public void setSynthesized(boolean z) {
            this.synthesized = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v4, types: [com.sun.tools.javac.util.Name, java.lang.CharSequence] */
        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            StringBuilder sb = new StringBuilder("@");
            sb.append(this.type);
            int length = this.values.length();
            if (length > 0) {
                sb.append('(');
                boolean z = true;
                for (Pair<Symbol.MethodSymbol, Attribute> pair : this.values) {
                    if (!z) {
                        sb.append(", ");
                    }
                    ?? r3 = pair.fst.name;
                    if (length > 1 || r3 != r3.table.names.value) {
                        sb.append((CharSequence) r3);
                        sb.append('=');
                    }
                    sb.append(pair.snd);
                    z = false;
                }
                sb.append(')');
            }
            return sb.toString();
        }

        public boolean tryFixPosition() {
            Compound firstEmbeddedTC;
            TypeAnnotationPosition typeAnnotationPosition;
            if (!isContainerTypeCompound() || (firstEmbeddedTC = getFirstEmbeddedTC()) == null || (typeAnnotationPosition = firstEmbeddedTC.position) == null || typeAnnotationPosition.type == TargetType.UNKNOWN) {
                return false;
            }
            this.position = typeAnnotationPosition;
            return true;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Visitor visitor) {
            visitor.visitCompound(this);
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public Compound getValue() {
            return this;
        }

        public Compound(Type type, List<Pair<Symbol.MethodSymbol, Attribute>> list) {
            this(type, list, null);
        }
    }

    public static class Enum extends Attribute {
        public Symbol.VarSymbol value;

        public Enum(Type type, Symbol.VarSymbol varSymbol) {
            super(type);
            this.value = (Symbol.VarSymbol) Assert.checkNonNull(varSymbol);
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
            return annotationValueVisitor.visitEnumConstant(this.value, p);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return this.value.toString();
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public Symbol.VarSymbol getValue() {
            return this.value;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Visitor visitor) {
            visitor.visitEnum(this);
        }
    }

    public static class Error extends Attribute {
        public Error(Type type) {
            super(type);
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
            return annotationValueVisitor.visitString(toString(), p);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return "<error>";
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public String getValue() {
            return toString();
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Visitor visitor) {
            visitor.visitError(this);
        }
    }

    public static class TypeCompound extends Compound {
        public TypeCompound(Compound compound, TypeAnnotationPosition typeAnnotationPosition) {
            super(compound.type, compound.values, typeAnnotationPosition);
        }

        public TypeCompound(Type type, List<Pair<Symbol.MethodSymbol, Attribute>> list, TypeAnnotationPosition typeAnnotationPosition) {
            super(type, list, typeAnnotationPosition);
        }
    }

    public static class Array extends Attribute {
        public final Attribute[] values;

        public Array(Type type, List<Attribute> list) {
            super(type);
            this.values = (Attribute[]) list.toArray(new Attribute[list.size()]);
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
            return annotationValueVisitor.visitArray(getValue(), p);
        }

        @Override // com.sun.tools.javac.code.Attribute
        public TypeAnnotationPosition getPosition() {
            Attribute[] attributeArr = this.values;
            if (attributeArr.length != 0) {
                return attributeArr[0].getPosition();
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public List<Attribute> getValue() {
            return List.from(this.values);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            Attribute[] attributeArr = this.values;
            int length = attributeArr.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                Attribute attribute = attributeArr[i];
                if (!z) {
                    sb.append(", ");
                }
                sb.append(attribute);
                i++;
                z = false;
            }
            sb.append('}');
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Visitor visitor) {
            visitor.visitArray(this);
        }

        public Array(Type type, Attribute[] attributeArr) {
            super(type);
            this.values = attributeArr;
        }
    }

    public static class Constant extends Attribute {
        public final Object value;

        public Constant(Type type, Object obj) {
            super(type);
            this.value = obj;
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public <R, P> R accept(AnnotationValueVisitor<R, P> annotationValueVisitor, P p) {
            Object obj = this.value;
            if (obj instanceof String) {
                return annotationValueVisitor.visitString((String) obj, p);
            }
            if (obj instanceof Integer) {
                int iIntValue = ((Integer) obj).intValue();
                int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[this.type.getTag().ordinal()];
                if (i == 1) {
                    return annotationValueVisitor.visitBoolean(iIntValue != 0, p);
                }
                if (i == 2) {
                    return annotationValueVisitor.visitChar((char) iIntValue, p);
                }
                if (i == 3) {
                    return annotationValueVisitor.visitByte((byte) iIntValue, p);
                }
                if (i == 4) {
                    return annotationValueVisitor.visitShort((short) iIntValue, p);
                }
                if (i == 5) {
                    return annotationValueVisitor.visitInt(iIntValue, p);
                }
            }
            int i2 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[this.type.getTag().ordinal()];
            if (i2 == 6) {
                return annotationValueVisitor.visitLong(((Long) this.value).longValue(), p);
            }
            if (i2 == 7) {
                return annotationValueVisitor.visitFloat(((Float) this.value).floatValue(), p);
            }
            Object obj2 = this.value;
            if (i2 == 8) {
                return annotationValueVisitor.visitDouble(((Double) obj2).doubleValue(), p);
            }
            s22.a("Bad annotation element value: ", obj2);
            return null;
        }

        @Override // com.sun.tools.javac.code.Attribute, javax.lang.model.element.AnnotationValue
        public Object getValue() {
            return Constants.decode(this.value, this.type);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return Constants.format(this.value, this.type);
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Visitor visitor) {
            visitor.visitConstant(this);
        }
    }
}
