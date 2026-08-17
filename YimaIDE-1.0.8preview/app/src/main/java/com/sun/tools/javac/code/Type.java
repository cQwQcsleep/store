package com.sun.tools.javac.code;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeMetadata;
import com.sun.tools.javac.comp.Infer;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Pair;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.lang.model.element.Element;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Type extends AnnoConstruct implements TypeMirror, PoolConstant {
    protected final List<TypeMetadata> metadata;
    public Symbol.TypeSymbol tsym;
    public static final JCNoType noType = new JCNoType() { // from class: com.sun.tools.javac.code.Type.1
        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return Option.LINT_CUSTOM_NONE;
        }
    };
    public static final JCNoType recoveryType = new JCNoType() { // from class: com.sun.tools.javac.code.Type.2
        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return "recovery";
        }
    };
    public static final JCNoType stuckType = new JCNoType() { // from class: com.sun.tools.javac.code.Type.3
        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return "stuck";
        }
    };
    public static boolean moreInfo = false;
    private static final Types.TypeMapping<Void> stripMetadata = new StructuralTypeMapping<Void>() { // from class: com.sun.tools.javac.code.Type.4
        private static Type dropMetadata(Type type) {
            if (type.getMetadata().isEmpty()) {
                return type;
            }
            Type typeBaseType = type.baseType();
            return typeBaseType.getMetadata().isEmpty() ? typeBaseType : typeBaseType.cloneWithMetadata(List.nil());
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(ArrayType arrayType, Void r2) {
            return super.visitArrayType((ArrayType) dropMetadata(arrayType), r2);
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(ClassType classType, Void r2) {
            return super.visitClassType((ClassType) dropMetadata(classType), r2);
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(WildcardType wildcardType, Void r2) {
            return super.visitWildcardType((WildcardType) dropMetadata(wildcardType), r2);
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Void r2) {
            return dropMetadata(type);
        }
    };

    /* JADX INFO: renamed from: com.sun.tools.javac.code.Type$5, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.CHAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static class BottomType extends Type implements NullType {
        public BottomType() {
            super(null, List.nil());
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitNull(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.NULL;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.BOT;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public String stringValue() {
            return PsiKeyword.NULL;
        }
    }

    public static class ErasedClassType extends ClassType {
        public ErasedClassType(Type type, Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list) {
            super(type, List.nil(), typeSymbol, list);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType
        public boolean hasErasedSupertypes() {
            return true;
        }
    }

    public static class IntersectionClassType extends ClassType implements IntersectionType {
        public boolean allInterfaces;

        public IntersectionClassType(List<Type> list, Symbol.ClassSymbol classSymbol, boolean z) {
            super(Type.noType, List.nil(), classSymbol);
            this.allInterfaces = z;
            Assert.check((classSymbol.flags() & 16777216) != 0);
            Type type = list.head;
            this.supertype_field = type;
            this.interfaces_field = list.tail;
            Assert.check((type.tsym.isCompleted() && this.supertype_field.isInterface()) ? false : true, this.supertype_field);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitIntersection(this, p);
        }

        public java.util.List<? extends TypeMirror> getBounds() {
            return Collections.unmodifiableList(getExplicitComponents());
        }

        public List<Type> getComponents() {
            return this.interfaces_field.prepend(this.supertype_field);
        }

        public List<Type> getExplicitComponents() {
            return this.allInterfaces ? this.interfaces_field : getComponents();
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.INTERSECTION;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isIntersection() {
            return true;
        }
    }

    public static class JCNoType extends Type implements NoType {
        public JCNoType() {
            super(null, List.nil());
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitNoType(this, p);
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.NONE;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.NONE;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }
    }

    public static class JCVoidType extends Type implements NoType {
        public JCVoidType() {
            super(null, List.nil());
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitNoType(this, p);
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.VOID;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.VOID;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitiveOrVoid() {
            return true;
        }
    }

    public static abstract class StructuralTypeMapping<S> extends Types.TypeMapping<S> {
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(ArrayType arrayType, S s) {
            Type type = arrayType.elemtype;
            Type typeVisit = visit(type, s);
            return typeVisit == type ? arrayType : new ArrayType(typeVisit, arrayType.tsym, arrayType.metadata) { // from class: com.sun.tools.javac.code.Type.StructuralTypeMapping.3
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(ClassType classType, S s) {
            Type enclosingType = classType.getEnclosingType();
            Type typeVisit = visit(enclosingType, s);
            List<Type> typeArguments = classType.getTypeArguments();
            List<Type> listVisit = visit(typeArguments, s);
            return (typeVisit == enclosingType && listVisit == typeArguments) ? classType : new ClassType(typeVisit, listVisit, classType.tsym, classType.metadata) { // from class: com.sun.tools.javac.code.Type.StructuralTypeMapping.1
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitForAll(ForAll forAll, S s) {
            return visit(forAll.qtype, s);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitMethodType(MethodType methodType, S s) {
            List<Type> list = methodType.argtypes;
            Type type = methodType.restype;
            List<Type> list2 = methodType.thrown;
            List<Type> listVisit = visit(list, s);
            Type typeVisit = visit(type, s);
            List<Type> listVisit2 = visit(list2, s);
            return (listVisit == list && typeVisit == type && listVisit2 == list2) ? methodType : new MethodType(listVisit, typeVisit, listVisit2, methodType.tsym) { // from class: com.sun.tools.javac.code.Type.StructuralTypeMapping.4
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(WildcardType wildcardType, S s) {
            Type typeVisit = wildcardType.type;
            if (typeVisit != null) {
                typeVisit = visit(typeVisit, s);
            }
            Type type = typeVisit;
            return type == wildcardType.type ? wildcardType : new WildcardType(type, wildcardType.kind, wildcardType.tsym, wildcardType.bound, wildcardType.metadata) { // from class: com.sun.tools.javac.code.Type.StructuralTypeMapping.2
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            };
        }
    }

    public static class UnionClassType extends ClassType implements UnionType {
        final List<? extends Type> alternatives_field;

        public UnionClassType(ClassType classType, List<? extends Type> list) {
            super(classType.outer_field, classType.typarams_field, classType.tsym);
            this.allparams_field = classType.allparams_field;
            this.supertype_field = classType.supertype_field;
            this.interfaces_field = classType.interfaces_field;
            this.all_interfaces_field = classType.interfaces_field;
            this.alternatives_field = list;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitUnion(this, p);
        }

        public Iterable<? extends Type> getAlternativeTypes() {
            return this.alternatives_field;
        }

        @Override // javax.lang.model.type.UnionType
        public java.util.List<? extends TypeMirror> getAlternatives() {
            return Collections.unmodifiableList(this.alternatives_field);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.UNION;
        }

        public Type getLub() {
            return this.tsym.type;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return getLub().isCompound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isUnion() {
            return true;
        }
    }

    public interface Visitor<R, S> {
        R visitArrayType(ArrayType arrayType, S s);

        R visitCapturedType(CapturedType capturedType, S s);

        R visitClassType(ClassType classType, S s);

        R visitErrorType(ErrorType errorType, S s);

        R visitForAll(ForAll forAll, S s);

        R visitMethodType(MethodType methodType, S s);

        R visitModuleType(ModuleType moduleType, S s);

        R visitPackageType(PackageType packageType, S s);

        R visitType(Type type, S s);

        R visitTypeVar(TypeVar typeVar, S s);

        R visitUndetVar(UndetVar undetVar, S s);

        R visitWildcardType(WildcardType wildcardType, S s);
    }

    public Type(Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list) {
        Assert.checkNonNull(list);
        this.tsym = typeSymbol;
        this.metadata = list;
    }

    public static List<Type> baseTypes(List<Type> list) {
        if (!list.nonEmpty()) {
            return list;
        }
        Type typeBaseType = list.head.baseType();
        List<Type> listBaseTypes = baseTypes(list.tail);
        return (typeBaseType == list.head && listBaseTypes == list.tail) ? list : listBaseTypes.prepend(typeBaseType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean contains(List<Type> list, Type type) {
        for (List list2 = list; list2.tail != null; list2 = list2.tail) {
            if (((Type) list2.head).contains(type)) {
                return true;
            }
        }
        return false;
    }

    public static List<Type> filter(List<Type> list, Predicate<Type> predicate) {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type : list) {
            if (predicate.test(type)) {
                listBuffer.append(type);
            }
        }
        return listBuffer.toList();
    }

    public static List<Type> getModelTypes(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(it.next().getModelType());
        }
        return listBuffer.toList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isErroneous(List<Type> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (((Type) list2.head).isErroneous()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String toString(List<Type> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.head.toString());
        List list2 = list;
        while (true) {
            List list3 = list2.tail;
            if (!list3.nonEmpty()) {
                return sb.toString();
            }
            sb.append(",");
            sb.append(((Type) list3.head).toString());
            list2 = list3;
        }
    }

    @Override // javax.lang.model.type.TypeMirror
    public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
        throw new AssertionError();
    }

    public Type addMetadata(TypeMetadata typeMetadata) {
        Assert.check(getMetadata(typeMetadata.getClass()) == null);
        return cloneWithMetadata(this.metadata.prepend(typeMetadata));
    }

    public List<Type> allparams() {
        return List.nil();
    }

    public Type annotatedType(List<Attribute.TypeCompound> list) {
        return addMetadata(new TypeMetadata.Annotations(list));
    }

    public void appendAnnotationsString(StringBuilder sb, boolean z) {
        if (isAnnotated()) {
            if (z) {
                sb.append(" ");
            }
            sb.append(getAnnotationMirrors().toString(" "));
            sb.append(" ");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String argtypes(boolean z) {
        Object obj;
        List listMo71getParameterTypes = mo71getParameterTypes();
        if (!z) {
            return listMo71getParameterTypes.toString();
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            boolean zNonEmpty = listMo71getParameterTypes.tail.nonEmpty();
            obj = listMo71getParameterTypes.head;
            if (!zNonEmpty) {
                break;
            }
            sb.append(obj);
            listMo71getParameterTypes = listMo71getParameterTypes.tail;
            sb.append(',');
        }
        boolean zHasTag = ((Type) obj).hasTag(TypeTag.ARRAY);
        Object obj2 = listMo71getParameterTypes.head;
        if (zHasTag) {
            sb.append(((ArrayType) obj2).elemtype);
            if (((Type) listMo71getParameterTypes.head).getAnnotationMirrors().nonEmpty()) {
                sb.append(((Type) listMo71getParameterTypes.head).getAnnotationMirrors());
            }
            sb.append("...");
        } else {
            sb.append(obj2);
        }
        return sb.toString();
    }

    public Symbol.TypeSymbol asElement() {
        return this.tsym;
    }

    public MethodType asMethodType() {
        throw new AssertionError();
    }

    public Type baseType() {
        return this;
    }

    public Type cloneWithMetadata(List<TypeMetadata> list) {
        throw new AssertionError("Cannot add metadata to this type: " + getTag());
    }

    public void complete() {
    }

    public Type constType(Object obj) {
        throw new AssertionError();
    }

    public Object constValue() {
        return getMetadata(TypeMetadata.ConstantValue.class, new Function() { // from class: jse
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((TypeMetadata.ConstantValue) obj).value();
            }
        }, null);
    }

    public boolean containsAny(List<Type> list) {
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            if (contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public Type dropMetadata(Class<? extends TypeMetadata> cls) {
        List<TypeMetadata> listNil = List.nil();
        for (TypeMetadata typeMetadata : this.metadata) {
            if (typeMetadata.getClass() != cls) {
                listNil = listNil.prepend(typeMetadata);
            }
        }
        return cloneWithMetadata(listNil);
    }

    @Override // javax.lang.model.type.TypeMirror
    public boolean equals(Object obj) {
        return this == obj;
    }

    public boolean equalsIgnoreMetadata(Type type) {
        return typeNoMetadata().equals(type.typeNoMetadata());
    }

    @Override // com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
    public List<Attribute.TypeCompound> getAnnotationMirrors() {
        return (List) getMetadata(TypeMetadata.Annotations.class, new Function() { // from class: kse
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((TypeMetadata.Annotations) obj).annotations();
            }
        }, List.nil());
    }

    public Type getEnclosingType() {
        return null;
    }

    public TypeKind getKind() {
        return TypeKind.OTHER;
    }

    public Type getLowerBound() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <M extends TypeMetadata, Z> Z getMetadata(Class<M> cls, Function<M, Z> function, Z z) {
        for (TypeMetadata typeMetadata : this.metadata) {
            if (typeMetadata.getClass() == cls) {
                return (Z) function.apply(typeMetadata);
            }
        }
        return z;
    }

    public Type getModelType() {
        return this;
    }

    public Type getOriginalType() {
        return this;
    }

    /* JADX INFO: renamed from: getParameterTypes */
    public List<Type> mo71getParameterTypes() {
        return List.nil();
    }

    /* JADX INFO: renamed from: getReceiverType */
    public Type mo72getReceiverType() {
        return null;
    }

    /* JADX INFO: renamed from: getReturnType */
    public Type mo73getReturnType() {
        return null;
    }

    public abstract TypeTag getTag();

    /* JADX INFO: renamed from: getThrownTypes */
    public List<Type> mo74getThrownTypes() {
        return List.nil();
    }

    public List<Type> getTypeArguments() {
        return List.nil();
    }

    public Type getUpperBound() {
        return null;
    }

    public boolean hasTag(TypeTag typeTag) {
        return typeTag == getTag();
    }

    @Override // javax.lang.model.type.TypeMirror
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isAnnotated() {
        return getMetadata(TypeMetadata.Annotations.class) != null;
    }

    public boolean isCompound() {
        return false;
    }

    public boolean isExtendsBound() {
        return false;
    }

    public boolean isFalse() {
        return false;
    }

    public boolean isFinal() {
        return (this.tsym.flags() & 16) != 0;
    }

    public boolean isIntegral() {
        return false;
    }

    public boolean isInterface() {
        return (this.tsym.flags() & 512) != 0;
    }

    public boolean isIntersection() {
        return false;
    }

    public boolean isNullOrReference() {
        return false;
    }

    public boolean isNumeric() {
        return false;
    }

    public boolean isParameterized() {
        return false;
    }

    public boolean isPartial() {
        return false;
    }

    public boolean isPrimitive() {
        return false;
    }

    public boolean isPrimitiveOrVoid() {
        return false;
    }

    public boolean isRaw() {
        return false;
    }

    public boolean isReference() {
        return false;
    }

    public boolean isSuperBound() {
        return false;
    }

    public boolean isTrue() {
        return false;
    }

    public boolean isUnbound() {
        return false;
    }

    public boolean isUnion() {
        return false;
    }

    public boolean isValueBased() {
        Symbol.TypeSymbol typeSymbol = this.tsym;
        return (typeSymbol == null || (typeSymbol.flags_field & 9007199254740992L) == 0) ? false : true;
    }

    public <Z> Type map(Types.TypeMapping<Z> typeMapping) {
        return typeMapping.visit(this, (Z) null);
    }

    public boolean needsStripping() {
        return false;
    }

    @Override // com.sun.tools.javac.jvm.PoolConstant
    public Object poolKey(Types types) {
        return new Types.UniqueType(this, types);
    }

    @Override // com.sun.tools.javac.jvm.PoolConstant
    public int poolTag() {
        throw new AssertionError("Invalid pool entry");
    }

    public Type preannotatedType() {
        return addMetadata(new TypeMetadata.Annotations());
    }

    public String stringValue() {
        return Assert.checkNonNull(constValue()).toString();
    }

    public Type stripMetadata() {
        return (Type) accept(stripMetadata, (Object) null);
    }

    public Type stripMetadataIfNeeded() {
        return needsStripping() ? (Type) accept(stripMetadata, (Object) null) : this;
    }

    public Type typeNoMetadata() {
        return this.metadata.isEmpty() ? this : stripMetadata();
    }

    public Type withTypeVar(Type type) {
        return this;
    }

    public static class ArrayType extends Type implements PoolConstant.LoadableConstant, javax.lang.model.type.ArrayType {
        public Type elemtype;

        public ArrayType(ArrayType arrayType) {
            this(arrayType.elemtype, arrayType.tsym, arrayType.getMetadata());
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitArrayType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            return this.elemtype.allparams();
        }

        @Override // com.sun.tools.javac.code.Type
        public ArrayType cloneWithMetadata(List<TypeMetadata> list) {
            return new ArrayType(this.elemtype, this.tsym, list) { // from class: com.sun.tools.javac.code.Type.ArrayType.1
                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return ArrayType.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType, javax.lang.model.type.ArrayType
                public /* bridge */ /* synthetic */ TypeMirror getComponentType() {
                    return super.getComponentType();
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            this.elemtype.complete();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return type.equalsIgnoreMetadata(this) || this.elemtype.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public boolean equals(Object obj) {
            if (!(obj instanceof ArrayType)) {
                return false;
            }
            ArrayType arrayType = (ArrayType) obj;
            return this == arrayType || this.elemtype.equals(arrayType.elemtype);
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.ARRAY;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.ARRAY;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public int hashCode() {
            return (TypeTag.ARRAY.ordinal() << 5) + this.elemtype.hashCode();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.elemtype.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isParameterized() {
            return this.elemtype.isParameterized();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isRaw() {
            return this.elemtype.isRaw();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        public boolean isVarargs() {
            return false;
        }

        public ArrayType makeVarargs() {
            return new ArrayType(this.elemtype, this.tsym, this.metadata) { // from class: com.sun.tools.javac.code.Type.ArrayType.2
                @Override // com.sun.tools.javac.code.Type.ArrayType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list);
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType, javax.lang.model.type.ArrayType
                public /* bridge */ /* synthetic */ TypeMirror getComponentType() {
                    return super.getComponentType();
                }

                @Override // com.sun.tools.javac.code.Type.ArrayType
                public boolean isVarargs() {
                    return true;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 7;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [com.sun.tools.javac.code.Type$ArrayType] */
        /* JADX WARN: Type inference failed for: r4v1, types: [com.sun.tools.javac.code.Type] */
        /* JADX WARN: Type inference failed for: r4v3, types: [com.sun.tools.javac.code.Type] */
        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Type componentType = this.elemtype;
            while (componentType.getKind() == TypeKind.ARRAY) {
                componentType = ((ArrayType) componentType).getComponentType();
            }
            sb.append(componentType);
            do {
                this.appendAnnotationsString(sb, true);
                sb.append("[]");
                this = ((ArrayType) this).getComponentType();
            } while (this.getKind() == TypeKind.ARRAY);
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitArray(this, p);
        }

        @Override // javax.lang.model.type.ArrayType
        public Type getComponentType() {
            return this.elemtype;
        }

        @Override // com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        public ArrayType(Type type, Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list) {
            super(typeSymbol, list);
            this.elemtype = type;
        }

        public ArrayType(Type type, Symbol.TypeSymbol typeSymbol) {
            this(type, typeSymbol, List.nil());
        }
    }

    public static class ClassType extends Type implements DeclaredType, PoolConstant.LoadableConstant, javax.lang.model.type.ErrorType {
        public List<Type> all_interfaces_field;
        public List<Type> allparams_field;
        public List<Type> interfaces_field;
        private Type outer_field;
        int rank_field;
        public Type supertype_field;
        public List<Type> typarams_field;

        public ClassType(Type type, List<Type> list, Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list2) {
            super(typeSymbol, list2);
            this.rank_field = -1;
            this.outer_field = type;
            this.typarams_field = list;
            this.allparams_field = null;
            this.supertype_field = null;
            this.interfaces_field = null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private String className(Symbol symbol, boolean z) {
            String localizedString;
            if (symbol.name.length() == 0 && (symbol.flags() & 16777216) != 0) {
                StringBuilder sb = new StringBuilder(this.supertype_field.toString());
                for (List list = this.interfaces_field; list.nonEmpty(); list = list.tail) {
                    sb.append("&");
                    sb.append(((Type) list.head).toString());
                }
                return sb.toString();
            }
            if (symbol.name.length() != 0) {
                if (!z) {
                    return symbol.name.toString();
                }
                symbol.apiComplete();
                return symbol.getQualifiedName().toString();
            }
            ClassType classType = (ClassType) this.tsym.type;
            if (classType == null) {
                localizedString = Log.getLocalizedString("anonymous.class", null);
            } else {
                List<Type> list2 = classType.interfaces_field;
                localizedString = (list2 == null || !list2.nonEmpty()) ? Log.getLocalizedString("anonymous.class", classType.supertype_field) : Log.getLocalizedString("anonymous.class", classType.interfaces_field.head);
            }
            if (!Type.moreInfo) {
                return localizedString;
            }
            return localizedString + String.valueOf(symbol.hashCode());
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitClassType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            if (this.allparams_field == null) {
                this.allparams_field = getTypeArguments().prependList(getEnclosingType().allparams());
            }
            return this.allparams_field;
        }

        @Override // javax.lang.model.type.DeclaredType
        public /* bridge */ /* synthetic */ Element asElement() {
            return super.asElement();
        }

        @Override // com.sun.tools.javac.code.Type
        public ClassType cloneWithMetadata(List<TypeMetadata> list) {
            return new ClassType(this.outer_field, this.typarams_field, this.tsym, list) { // from class: com.sun.tools.javac.code.Type.ClassType.1
                @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                public /* bridge */ /* synthetic */ Element asElement() {
                    return super.asElement();
                }

                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return ClassType.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                public /* bridge */ /* synthetic */ TypeMirror getEnclosingType() {
                    return super.getEnclosingType();
                }

                @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                public /* bridge */ /* synthetic */ java.util.List getTypeArguments() {
                    return super.getTypeArguments();
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            this.tsym.complete();
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return addMetadata(new TypeMetadata.ConstantValue(obj));
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            if (type.equalsIgnoreMetadata(this)) {
                return true;
            }
            if (isParameterized() && (getEnclosingType().contains(type) || Type.contains(getTypeArguments(), type))) {
                return true;
            }
            if (isCompound()) {
                return this.supertype_field.contains(type) || Type.contains(this.interfaces_field, type);
            }
            return false;
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            this.tsym.apiComplete();
            return this.tsym.kind == Kinds.Kind.TYP ? TypeKind.DECLARED : TypeKind.ERROR;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.CLASS;
        }

        @Override // javax.lang.model.type.DeclaredType
        public List<Type> getTypeArguments() {
            if (this.typarams_field == null) {
                complete();
                if (this.typarams_field == null) {
                    this.typarams_field = List.nil();
                }
            }
            return this.typarams_field;
        }

        public boolean hasErasedSupertypes() {
            return isRaw();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            if (getEnclosingType().isErroneous() || Type.isErroneous(getTypeArguments())) {
                return true;
            }
            Type type = this.tsym.type;
            return this != type && type.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isParameterized() {
            return allparams().tail != null;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isRaw() {
            Type type = this.tsym.type;
            return this != type && type.allparams().nonEmpty() && allparams().isEmpty();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 7;
        }

        public void setEnclosingType(Type type) {
            this.outer_field = type;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (getEnclosingType().hasTag(TypeTag.CLASS) && this.tsym.owner.kind == Kinds.Kind.TYP) {
                sb.append(getEnclosingType().toString());
                sb.append(Constants.ATTRVAL_THIS);
                appendAnnotationsString(sb);
                sb.append(className(this.tsym, false));
            } else {
                boolean zIsAnnotated = isAnnotated();
                Symbol symbol = this.tsym;
                if (zIsAnnotated) {
                    if (!symbol.packge().isUnnamed()) {
                        sb.append(this.tsym.packge());
                        sb.append(Constants.ATTRVAL_THIS);
                    }
                    ListBuffer listBuffer = new ListBuffer();
                    Symbol symbol2 = this.tsym;
                    while (true) {
                        symbol2 = symbol2.owner;
                        if (symbol2 == null || symbol2.kind != Kinds.Kind.TYP) {
                            break;
                        }
                        listBuffer.prepend(symbol2.name);
                    }
                    Iterator it = listBuffer.iterator();
                    while (it.hasNext()) {
                        sb.append((CharSequence) it.next());
                        sb.append(Constants.ATTRVAL_THIS);
                    }
                    appendAnnotationsString(sb);
                    sb.append((CharSequence) this.tsym.name);
                } else {
                    sb.append(className(symbol, true));
                }
            }
            if (getTypeArguments().nonEmpty()) {
                sb.append('<');
                sb.append(getTypeArguments().toString());
                sb.append(">");
            }
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitDeclared(this, p);
        }

        @Override // javax.lang.model.type.DeclaredType
        public Type getEnclosingType() {
            return this.outer_field;
        }

        @Override // com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        public ClassType(Type type, List<Type> list, Symbol.TypeSymbol typeSymbol) {
            this(type, list, typeSymbol, List.nil());
        }
    }

    public static class ErrorType extends ClassType implements javax.lang.model.type.ErrorType {
        private Type originalType;

        /* JADX WARN: Illegal instructions before constructor call */
        public ErrorType(Type type, Symbol.TypeSymbol typeSymbol) {
            JCNoType jCNoType = Type.noType;
            super(jCNoType, List.nil(), null);
            this.originalType = null;
            this.tsym = typeSymbol;
            this.originalType = type == null ? jCNoType : type;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitErrorType(this, s);
        }

        public Type asSub(Symbol symbol) {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public ErrorType cloneWithMetadata(List<TypeMetadata> list) {
            return new ErrorType(this.originalType, this.tsym, list) { // from class: com.sun.tools.javac.code.Type.ErrorType.1
                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return ErrorType.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.ErrorType, com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ ClassType cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.ErrorType, com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                public /* bridge */ /* synthetic */ TypeMirror getEnclosingType() {
                    return super.getEnclosingType();
                }

                @Override // com.sun.tools.javac.code.Type.ErrorType, com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.ERROR;
        }

        @Override // com.sun.tools.javac.code.Type
        public Type getOriginalType() {
            return this.originalType;
        }

        @Override // com.sun.tools.javac.code.Type
        /* JADX INFO: renamed from: getReturnType */
        public Type mo73getReturnType() {
            return this;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.ERROR;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isCompound() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return true;
        }

        public boolean isGenType(Type type) {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isInterface() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitError(this, p);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
        public Type getEnclosingType() {
            return Type.noType;
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ ClassType cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        public ErrorType(Symbol.ClassSymbol classSymbol, Type type) {
            this(type, classSymbol);
            classSymbol.type = this;
            classSymbol.kind = Kinds.Kind.ERR;
            classSymbol.members_field = new Scope.ErrorScope(classSymbol);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public ErrorType(Type type, Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list) {
            JCNoType jCNoType = Type.noType;
            super(jCNoType, List.nil(), null, list);
            this.originalType = null;
            this.tsym = typeSymbol;
            this.originalType = type == null ? jCNoType : type;
        }

        public ErrorType(Name name, Symbol.TypeSymbol typeSymbol, Type type) {
            this(new Symbol.ClassSymbol(1073741833L, name, null, typeSymbol), type);
        }
    }

    public static class ForAll extends DelegatedType implements ExecutableType {
        public List<Type> tvars;

        public ForAll(List<Type> list, Type type) {
            super(TypeTag.FORALL, (MethodType) type);
            this.tvars = list;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitForAll(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public MethodType asMethodType() {
            return (MethodType) this.qtype;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            for (List list = this.tvars; list.nonEmpty(); list = list.tail) {
                ((TypeVar) list.head).getUpperBound().complete();
            }
            this.qtype.complete();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return this.qtype.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.EXECUTABLE;
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getParameterTypes */
        public /* bridge */ /* synthetic */ java.util.List mo71getParameterTypes() {
            return super.mo71getParameterTypes();
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getReceiverType */
        public /* bridge */ /* synthetic */ TypeMirror mo72getReceiverType() {
            return super.mo72getReceiverType();
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getReturnType */
        public /* bridge */ /* synthetic */ TypeMirror mo73getReturnType() {
            return super.mo73getReturnType();
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getThrownTypes */
        public /* bridge */ /* synthetic */ java.util.List mo74getThrownTypes() {
            return super.mo74getThrownTypes();
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type, javax.lang.model.type.DeclaredType
        public List<Type> getTypeArguments() {
            return this.tvars;
        }

        @Override // javax.lang.model.type.ExecutableType
        public List<TypeVar> getTypeVariables() {
            return List.convert(TypeVar.class, getTypeArguments());
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.qtype.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAnnotationsString(sb);
            sb.append('<');
            sb.append(this.tvars);
            sb.append('>');
            sb.append(this.qtype);
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitExecutable(this, p);
        }
    }

    public static class MethodType extends Type implements ExecutableType, PoolConstant.LoadableConstant {
        public List<Type> argtypes;
        public Type recvtype;
        public Type restype;
        public List<Type> thrown;

        public MethodType(List<Type> list, Type type, List<Type> list2, Symbol.TypeSymbol typeSymbol) {
            super(typeSymbol, List.nil());
            this.argtypes = list;
            this.restype = type;
            this.thrown = list2;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitMethodType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public Symbol.TypeSymbol asElement() {
            return null;
        }

        @Override // com.sun.tools.javac.code.Type
        public MethodType asMethodType() {
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Type
        public void complete() {
            for (List list = this.argtypes; list.nonEmpty(); list = list.tail) {
                ((Type) list.head).complete();
            }
            this.restype.complete();
            this.recvtype.complete();
            for (List list2 = this.thrown; list2.nonEmpty(); list2 = list2.tail) {
                ((Type) list2.head).complete();
            }
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return type.equalsIgnoreMetadata(this) || Type.contains(this.argtypes, type) || this.restype.contains(type) || Type.contains(this.thrown, type);
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.EXECUTABLE;
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getReceiverType, reason: merged with bridge method [inline-methods] */
        public Type mo72getReceiverType() {
            Type type = this.recvtype;
            return type == null ? Type.noType : type;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.METHOD;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            if (Type.isErroneous(this.argtypes)) {
                return true;
            }
            Type type = this.restype;
            return type != null && type.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 16;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAnnotationsString(sb);
            sb.append('(');
            sb.append(this.argtypes);
            sb.append(')');
            sb.append(this.restype);
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitExecutable(this, p);
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getParameterTypes, reason: merged with bridge method [inline-methods] */
        public List<Type> mo71getParameterTypes() {
            return this.argtypes;
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getReturnType, reason: merged with bridge method [inline-methods] */
        public Type mo73getReturnType() {
            return this.restype;
        }

        @Override // javax.lang.model.type.ExecutableType
        /* JADX INFO: renamed from: getThrownTypes, reason: merged with bridge method [inline-methods] */
        public List<Type> mo74getThrownTypes() {
            return this.thrown;
        }

        @Override // javax.lang.model.type.ExecutableType
        public List<TypeVar> getTypeVariables() {
            return List.nil();
        }
    }

    public static class ModuleType extends Type implements NoType {
        public ModuleType(Symbol.ModuleSymbol moduleSymbol) {
            super(moduleSymbol, List.nil());
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitModuleType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public ModuleType annotatedType(List<Attribute.TypeCompound> list) {
            throw new AssertionError("Cannot annotate a module type");
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.MODULE;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.MODULE;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return this.tsym.getQualifiedName().toString();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitNoType(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type annotatedType(List list) {
            return annotatedType((List<Attribute.TypeCompound>) list);
        }
    }

    public static class PackageType extends Type implements NoType {
        public PackageType(Symbol.PackageSymbol packageSymbol) {
            super(packageSymbol, List.nil());
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitPackageType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.PACKAGE;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.PACKAGE;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return this.tsym.getQualifiedName().toString();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitNoType(this, p);
        }
    }

    public static class TypeVar extends Type implements TypeVariable {
        private Type _bound;
        public Type lower;
        int rank_field;

        public TypeVar(Name name, Symbol symbol, Type type) {
            super(null, List.nil());
            this._bound = null;
            this.rank_field = -1;
            Assert.checkNonNull(type);
            this.tsym = new Symbol.TypeVariableSymbol(0L, name, this, symbol);
            setUpperBound(null);
            this.lower = type;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitTypeVar(this, s);
        }

        @Override // javax.lang.model.type.TypeVariable
        public /* bridge */ /* synthetic */ Element asElement() {
            return super.asElement();
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeVar cloneWithMetadata(List<TypeMetadata> list) {
            return new TypeVar(this.tsym, getUpperBound(), this.lower, list) { // from class: com.sun.tools.javac.code.Type.TypeVar.1
                @Override // com.sun.tools.javac.code.Type.TypeVar, javax.lang.model.type.TypeVariable
                public /* bridge */ /* synthetic */ Element asElement() {
                    return super.asElement();
                }

                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return TypeVar.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar, javax.lang.model.type.TypeVariable
                public /* bridge */ /* synthetic */ TypeMirror getLowerBound() {
                    return super.getLowerBound();
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar, javax.lang.model.type.TypeVariable
                public Type getUpperBound() {
                    return TypeVar.this.getUpperBound();
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar
                public void setUpperBound(Type type) {
                    TypeVar.this.setUpperBound(type);
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.TYPEVAR;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.TYPEVAR;
        }

        public boolean isCaptured() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        public void setUpperBound(Type type) {
            this._bound = type;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitTypeVariable(this, p);
        }

        @Override // javax.lang.model.type.TypeVariable
        public Type getLowerBound() {
            return this.lower;
        }

        @Override // javax.lang.model.type.TypeVariable
        public Type getUpperBound() {
            return this._bound;
        }

        @Override // com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        public TypeVar(Symbol.TypeSymbol typeSymbol, Type type, Type type2) {
            this(typeSymbol, type, type2, List.nil());
        }

        public TypeVar(Symbol.TypeSymbol typeSymbol, Type type, Type type2, List<TypeMetadata> list) {
            super(typeSymbol, list);
            this._bound = null;
            this.rank_field = -1;
            Assert.checkNonNull(type2);
            setUpperBound(type);
            this.lower = type2;
        }
    }

    public static class WildcardType extends Type implements javax.lang.model.type.WildcardType {
        public TypeVar bound;
        boolean isPrintingBound;
        public BoundKind kind;
        public Type type;

        public WildcardType(Type type, BoundKind boundKind, Symbol.TypeSymbol typeSymbol, TypeVar typeVar, List<TypeMetadata> list) {
            super(typeSymbol, list);
            this.isPrintingBound = false;
            this.type = (Type) Assert.checkNonNull(type);
            this.kind = boundKind;
            this.bound = typeVar;
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitWildcardType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public WildcardType cloneWithMetadata(List<TypeMetadata> list) {
            return new WildcardType(this.type, this.kind, this.tsym, this.bound, list) { // from class: com.sun.tools.javac.code.Type.WildcardType.1
                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return WildcardType.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.WildcardType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.WildcardType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Type.WildcardType
                /* JADX INFO: renamed from: getExtendsBound */
                public /* bridge */ /* synthetic */ TypeMirror mo75getExtendsBound() {
                    return super.mo75getExtendsBound();
                }

                @Override // com.sun.tools.javac.code.Type.WildcardType
                /* JADX INFO: renamed from: getSuperBound */
                public /* bridge */ /* synthetic */ TypeMirror mo76getSuperBound() {
                    return super.mo76getSuperBound();
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean contains(Type type) {
            return this.kind != BoundKind.UNBOUND && this.type.contains(type);
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // 
        /* JADX INFO: renamed from: getExtendsBound, reason: merged with bridge method [inline-methods] */
        public Type mo75getExtendsBound() {
            if (this.kind == BoundKind.EXTENDS) {
                return this.type;
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            return TypeKind.WILDCARD;
        }

        @Override // 
        /* JADX INFO: renamed from: getSuperBound, reason: merged with bridge method [inline-methods] */
        public Type mo76getSuperBound() {
            if (this.kind == BoundKind.SUPER) {
                return this.type;
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.WILDCARD;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isExtendsBound() {
            BoundKind boundKind = this.kind;
            return boundKind == BoundKind.EXTENDS || boundKind == BoundKind.UNBOUND;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNullOrReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isReference() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isSuperBound() {
            BoundKind boundKind = this.kind;
            return boundKind == BoundKind.SUPER || boundKind == BoundKind.UNBOUND;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isUnbound() {
            BoundKind boundKind = this.kind;
            if (boundKind != BoundKind.UNBOUND) {
                return boundKind == BoundKind.EXTENDS && this.type.tsym.flatName() == this.type.tsym.name.table.names.java_lang_Object;
            }
            return true;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAnnotationsString(sb);
            sb.append(this.kind.toString());
            if (this.kind != BoundKind.UNBOUND) {
                sb.append(this.type);
            }
            if (Type.moreInfo && this.bound != null && !this.isPrintingBound) {
                try {
                    this.isPrintingBound = true;
                    sb.append("{:");
                    sb.append(this.bound.getUpperBound());
                    sb.append(":}");
                } finally {
                    this.isPrintingBound = false;
                }
            }
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Type
        public Type withTypeVar(Type type) {
            if (this.bound == type) {
                return this;
            }
            this.bound = (TypeVar) type;
            return this;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitWildcard(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        public WildcardType(Type type, BoundKind boundKind, Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list) {
            this(type, boundKind, typeSymbol, null, list);
        }

        public WildcardType(Type type, BoundKind boundKind, Symbol.TypeSymbol typeSymbol, TypeVar typeVar) {
            this(type, boundKind, typeSymbol, typeVar, List.nil());
        }

        public WildcardType(Type type, BoundKind boundKind, Symbol.TypeSymbol typeSymbol) {
            this(type, boundKind, typeSymbol, null, List.nil());
        }
    }

    public <R, S> R accept(Visitor<R, S> visitor, S s) {
        return visitor.visitType(this, s);
    }

    public <Z> Type map(Types.TypeMapping<Z> typeMapping, Z z) {
        return typeMapping.visit(this, z);
    }

    public static abstract class DelegatedType extends Type {
        public Type qtype;
        public TypeTag tag;

        public DelegatedType(TypeTag typeTag, Type type, List<TypeMetadata> list) {
            super(type.tsym, list);
            this.tag = typeTag;
            this.qtype = type;
        }

        @Override // com.sun.tools.javac.code.Type
        public List<Type> allparams() {
            return this.qtype.allparams();
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.DeclaredType
        public Type getEnclosingType() {
            return this.qtype.getEnclosingType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* JADX INFO: renamed from: getParameterTypes */
        public List<Type> mo71getParameterTypes() {
            return this.qtype.mo71getParameterTypes();
        }

        @Override // com.sun.tools.javac.code.Type
        /* JADX INFO: renamed from: getReceiverType */
        public Type mo72getReceiverType() {
            return this.qtype.mo72getReceiverType();
        }

        @Override // com.sun.tools.javac.code.Type
        /* JADX INFO: renamed from: getReturnType */
        public Type mo73getReturnType() {
            return this.qtype.mo73getReturnType();
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return this.tag;
        }

        @Override // com.sun.tools.javac.code.Type
        /* JADX INFO: renamed from: getThrownTypes */
        public List<Type> mo74getThrownTypes() {
            return this.qtype.mo74getThrownTypes();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.DeclaredType
        public List<Type> getTypeArguments() {
            return this.qtype.getTypeArguments();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeVariable
        public Type getUpperBound() {
            return this.qtype.getUpperBound();
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isErroneous() {
            return this.qtype.isErroneous();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return this.qtype.toString();
        }

        public DelegatedType(TypeTag typeTag, Type type) {
            this(typeTag, type, List.nil());
        }
    }

    public static class JCPrimitiveType extends Type implements PrimitiveType {
        TypeTag tag;

        private JCPrimitiveType(TypeTag typeTag, Symbol.TypeSymbol typeSymbol, List<TypeMetadata> list) {
            super(typeSymbol, list);
            this.tag = typeTag;
            Assert.check(typeTag.isPrimitive);
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public <R, P> R accept(TypeVisitor<R, P> typeVisitor, P p) {
            return typeVisitor.visitPrimitive(this, p);
        }

        @Override // com.sun.tools.javac.code.Type
        public JCPrimitiveType cloneWithMetadata(List<TypeMetadata> list) {
            return new JCPrimitiveType(this.tag, this.tsym, list) { // from class: com.sun.tools.javac.code.Type.JCPrimitiveType.1
                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return JCPrimitiveType.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.JCPrimitiveType, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.JCPrimitiveType, com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type
        public Type constType(Object obj) {
            return addMetadata(new TypeMetadata.ConstantValue(obj));
        }

        @Override // com.sun.tools.javac.code.Type, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public TypeKind getKind() {
            switch (AnonymousClass5.$SwitchMap$com$sun$tools$javac$code$TypeTag[this.tag.ordinal()]) {
                case 1:
                    return TypeKind.CHAR;
                case 2:
                    return TypeKind.BYTE;
                case 3:
                    return TypeKind.SHORT;
                case 4:
                    return TypeKind.INT;
                case 5:
                    return TypeKind.LONG;
                case 6:
                    return TypeKind.FLOAT;
                case 7:
                    return TypeKind.DOUBLE;
                case 8:
                    return TypeKind.BOOLEAN;
                default:
                    x1f.a();
                    return null;
            }
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return this.tag;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isFalse() {
            return this.tag == TypeTag.BOOLEAN && constValue() != null && ((Integer) constValue()).intValue() == 0;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isIntegral() {
            int i = AnonymousClass5.$SwitchMap$com$sun$tools$javac$code$TypeTag[this.tag.ordinal()];
            return i == 1 || i == 2 || i == 3 || i == 4 || i == 5;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isNumeric() {
            return this.tag != TypeTag.BOOLEAN;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitive() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPrimitiveOrVoid() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isTrue() {
            return (this.tag != TypeTag.BOOLEAN || constValue() == null || ((Integer) constValue()).intValue() == 0) ? false : true;
        }

        @Override // com.sun.tools.javac.code.Type
        public String stringValue() {
            Object objCheckNonNull = Assert.checkNonNull(constValue());
            TypeTag typeTag = this.tag;
            if (typeTag == TypeTag.BOOLEAN) {
                return ((Integer) objCheckNonNull).intValue() == 0 ? "false" : "true";
            }
            return typeTag == TypeTag.CHAR ? String.valueOf((char) ((Integer) objCheckNonNull).intValue()) : objCheckNonNull.toString();
        }

        @Override // com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        public JCPrimitiveType(TypeTag typeTag, Symbol.TypeSymbol typeSymbol) {
            this(typeTag, typeSymbol, List.nil());
        }
    }

    public static class CapturedType extends TypeVar {
        public WildcardType wildcard;

        public CapturedType(Name name, Symbol symbol, Type type, Type type2, WildcardType wildcardType) {
            super(name, symbol, type2);
            this.lower = (Type) Assert.checkNonNull(type2);
            setUpperBound(type);
            this.wildcard = wildcardType;
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitCapturedType(this, s);
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
        public CapturedType cloneWithMetadata(List<TypeMetadata> list) {
            return new CapturedType(this.tsym, getUpperBound(), getUpperBound(), this.lower, this.wildcard, list) { // from class: com.sun.tools.javac.code.Type.CapturedType.1
                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return CapturedType.this.baseType();
                }

                @Override // com.sun.tools.javac.code.Type.CapturedType, com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ TypeVar cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar, javax.lang.model.type.TypeVariable
                public Type getUpperBound() {
                    return CapturedType.this.getUpperBound();
                }

                @Override // com.sun.tools.javac.code.Type.TypeVar
                public void setUpperBound(Type type) {
                    CapturedType.this.setUpperBound(type);
                }

                @Override // com.sun.tools.javac.code.Type.CapturedType, com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
                public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list2) {
                    return super.cloneWithMetadata((List<TypeMetadata>) list2);
                }
            };
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar
        public boolean isCaptured() {
            return true;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAnnotationsString(sb);
            sb.append("capture#");
            sb.append((((long) hashCode()) & 4294967295L) % 997);
            sb.append(" of ");
            sb.append(this.wildcard);
            return sb.toString();
        }

        public CapturedType(Symbol.TypeSymbol typeSymbol, Type type, Type type2, Type type3, WildcardType wildcardType, List<TypeMetadata> list) {
            super(typeSymbol, type, type3, list);
            this.wildcard = wildcardType;
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }

        @Override // com.sun.tools.javac.code.Type.TypeVar, com.sun.tools.javac.code.Type
        public /* bridge */ /* synthetic */ TypeVar cloneWithMetadata(List list) {
            return cloneWithMetadata((List<TypeMetadata>) list);
        }
    }

    public boolean contains(Type type) {
        return type.equalsIgnoreMetadata(this);
    }

    public boolean isErroneous() {
        return false;
    }

    public static boolean containsAny(List<Type> list, List<Type> list2) {
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().containsAny(list2)) {
                return true;
            }
        }
        return false;
    }

    public void appendAnnotationsString(StringBuilder sb) {
        appendAnnotationsString(sb, false);
    }

    public <M extends TypeMetadata> M getMetadata(Class<M> cls) {
        return (M) getMetadata(cls, Function.identity(), null);
    }

    public List<TypeMetadata> getMetadata() {
        return this.metadata;
    }

    @Override // javax.lang.model.type.TypeMirror
    public String toString() {
        Name name;
        StringBuilder sb = new StringBuilder();
        appendAnnotationsString(sb);
        Symbol.TypeSymbol typeSymbol = this.tsym;
        if (typeSymbol != null && (name = typeSymbol.name) != null) {
            sb.append(name.toString());
        } else {
            sb.append("<none>");
        }
        if (moreInfo && hasTag(TypeTag.TYPEVAR)) {
            sb.append(hashCode());
        }
        return sb.toString();
    }

    public static class UndetVar extends DelegatedType {
        protected Map<InferenceBound, List<Type>> bounds;
        public int declaredCount;
        public ArrayDeque<Infer.IncorporationAction> incorporationActions;
        private Type inst;
        Kind kind;
        public UndetVarListener listener;
        Types.TypeMapping<Void> toTypeVarMap;

        public enum InferenceBound {
            LOWER { // from class: com.sun.tools.javac.code.Type.UndetVar.InferenceBound.1
                @Override // com.sun.tools.javac.code.Type.UndetVar.InferenceBound
                public InferenceBound complement() {
                    return InferenceBound.UPPER;
                }
            },
            EQ { // from class: com.sun.tools.javac.code.Type.UndetVar.InferenceBound.2
                @Override // com.sun.tools.javac.code.Type.UndetVar.InferenceBound
                public InferenceBound complement() {
                    return InferenceBound.EQ;
                }
            },
            UPPER { // from class: com.sun.tools.javac.code.Type.UndetVar.InferenceBound.3
                @Override // com.sun.tools.javac.code.Type.UndetVar.InferenceBound
                public InferenceBound complement() {
                    return InferenceBound.LOWER;
                }
            };

            public abstract InferenceBound complement();

            public boolean lessThan(InferenceBound inferenceBound) {
                int iOrdinal;
                if (inferenceBound != this && (iOrdinal = inferenceBound.ordinal()) != 0) {
                    if (iOrdinal != 1) {
                        if (iOrdinal == 2) {
                            return true;
                        }
                        Assert.error("Cannot get here!");
                        return false;
                    }
                    if (this != UPPER) {
                        return true;
                    }
                }
                return false;
            }
        }

        public enum Kind {
            NORMAL,
            CAPTURED,
            THROWS
        }

        public interface UndetVarListener {
            void varBoundChanged(UndetVar undetVar, InferenceBound inferenceBound, Type type, boolean z);

            default void varInstantiated(UndetVar undetVar) {
                Assert.error();
            }
        }

        public UndetVar(TypeVar typeVar, UndetVarListener undetVarListener, Types types) {
            super(TypeTag.UNDETVAR, typeVar);
            this.incorporationActions = new ArrayDeque<>();
            this.inst = null;
            this.listener = null;
            this.toTypeVarMap = new StructuralTypeMapping<Void>() { // from class: com.sun.tools.javac.code.Type.UndetVar.1
                @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
                public Type visitUndetVar(UndetVar undetVar, Void r2) {
                    return undetVar.inst != null ? undetVar.inst : undetVar.qtype;
                }
            };
            this.kind = typeVar.isCaptured() ? Kind.CAPTURED : Kind.NORMAL;
            this.listener = undetVarListener;
            this.bounds = new EnumMap(InferenceBound.class);
            List<Type> bounds = types.getBounds(typeVar);
            this.declaredCount = bounds.length();
            this.bounds.put(InferenceBound.UPPER, List.nil());
            this.bounds.put(InferenceBound.LOWER, List.nil());
            this.bounds.put(InferenceBound.EQ, List.nil());
            Iterator<Type> it = bounds.reverse().iterator();
            while (it.hasNext()) {
                addBound(InferenceBound.UPPER, it.next(), types, true);
            }
            if (!typeVar.isCaptured() || typeVar.lower.hasTag(TypeTag.BOT)) {
                return;
            }
            addBound(InferenceBound.LOWER, typeVar.lower, types, true);
        }

        public static /* synthetic */ void a(UndetVar undetVar, ListBuffer listBuffer, UndetVar undetVar2, InferenceBound inferenceBound, Type type, boolean z) {
            undetVar.getClass();
            Assert.check(undetVar2 == undetVar);
            listBuffer.add(new Pair(inferenceBound, type));
        }

        private void addBound(InferenceBound inferenceBound, Type type, Types types, boolean z) {
            if (this.kind == Kind.CAPTURED && !z) {
                if (type.hasTag(TypeTag.UNDETVAR)) {
                    UndetVar undetVar = (UndetVar) type;
                    if (undetVar.isCaptured()) {
                        return;
                    }
                    undetVar.addBound(inferenceBound.complement(), this, types, false);
                    return;
                }
                return;
            }
            Type typeBaseType = type.map(this.toTypeVarMap).baseType();
            List<Type> list = this.bounds.get(inferenceBound);
            if (type == this.qtype) {
                return;
            }
            Iterator<Type> it = list.iterator();
            while (it.hasNext()) {
                if (types.isSameType(it.next(), typeBaseType)) {
                    return;
                }
            }
            this.bounds.put(inferenceBound, list.prepend(typeBaseType));
            notifyBoundChange(inferenceBound, typeBaseType, false);
        }

        private void notifyBoundChange(InferenceBound inferenceBound, Type type, boolean z) {
            UndetVarListener undetVarListener = this.listener;
            if (undetVarListener != null) {
                undetVarListener.varBoundChanged(this, inferenceBound, type, z);
            }
        }

        @Override // com.sun.tools.javac.code.Type
        public <R, S> R accept(Visitor<R, S> visitor, S s) {
            return visitor.visitUndetVar(this, s);
        }

        @Override // com.sun.tools.javac.code.Type
        public Type baseType() {
            Type type = this.inst;
            return type == null ? this : type.baseType();
        }

        public String debugString() {
            String str = "inference var = " + this.qtype + "\n";
            if (this.inst != null) {
                str = str + "inst = " + this.inst + '\n';
            }
            for (InferenceBound inferenceBound : InferenceBound.values()) {
                List<Type> list = this.bounds.get(inferenceBound);
                if (list != null && list.size() > 0) {
                    str = str + inferenceBound + " = " + list + '\n';
                }
            }
            return str;
        }

        public UndetVar dup(Types types) {
            UndetVar undetVar = new UndetVar((TypeVar) this.qtype, this.listener, types);
            dupTo(undetVar, types);
            return undetVar;
        }

        public void dupTo(UndetVar undetVar, Types types) {
            undetVar.listener = null;
            undetVar.bounds.clear();
            for (InferenceBound inferenceBound : InferenceBound.values()) {
                undetVar.bounds.put(inferenceBound, List.nil());
                Iterator<Type> it = getBounds(inferenceBound).iterator();
                while (it.hasNext()) {
                    undetVar.addBound(inferenceBound, it.next(), types, true);
                }
            }
            undetVar.inst = this.inst;
            undetVar.listener = this.listener;
            undetVar.incorporationActions = new ArrayDeque<>();
            Iterator<Infer.IncorporationAction> it2 = this.incorporationActions.iterator();
            while (it2.hasNext()) {
                undetVar.incorporationActions.add(it2.next().dup(undetVar));
            }
            undetVar.kind = this.kind;
        }

        public List<Type> getBounds(InferenceBound... inferenceBoundArr) {
            ListBuffer listBuffer = new ListBuffer();
            for (InferenceBound inferenceBound : inferenceBoundArr) {
                listBuffer.appendList(this.bounds.get(inferenceBound));
            }
            return listBuffer.toList();
        }

        public List<Type> getDeclaredBounds() {
            ListBuffer listBuffer = new ListBuffer();
            int i = 0;
            for (Type type : getBounds(InferenceBound.UPPER)) {
                int i2 = i + 1;
                if (i == this.declaredCount) {
                    break;
                }
                listBuffer.append(type);
                i = i2;
            }
            return listBuffer.toList();
        }

        public Type getInst() {
            return this.inst;
        }

        public final boolean isCaptured() {
            return this.kind == Kind.CAPTURED;
        }

        @Override // com.sun.tools.javac.code.Type
        public boolean isPartial() {
            return true;
        }

        public final boolean isThrows() {
            return this.kind == Kind.THROWS;
        }

        public void setBounds(InferenceBound inferenceBound, List<Type> list) {
            this.bounds.put(inferenceBound, list);
        }

        public void setInst(Type type) {
            this.inst = type;
            UndetVarListener undetVarListener = this.listener;
            if (undetVarListener != null) {
                undetVarListener.varInstantiated(this);
            }
        }

        public void setNormal() {
            Assert.check(this.kind == Kind.CAPTURED);
            this.kind = Kind.NORMAL;
        }

        public void setThrow() {
            if (this.kind != Kind.CAPTURED) {
                this.kind = Kind.THROWS;
            } else {
                g33.a();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void substBounds(List<Type> list, List<Type> list2, Types types) {
            final ListBuffer<Pair> listBuffer = new ListBuffer();
            UndetVarListener undetVarListener = this.listener;
            try {
                this.listener = new UndetVarListener() { // from class: nse
                    @Override // com.sun.tools.javac.code.Type.UndetVar.UndetVarListener
                    public final void varBoundChanged(Type.UndetVar undetVar, Type.UndetVar.InferenceBound inferenceBound, Type type, boolean z) {
                        Type.UndetVar.a(this.a, listBuffer, undetVar, inferenceBound, type, z);
                    }
                };
                for (Map.Entry<InferenceBound, List<Type>> entry : this.bounds.entrySet()) {
                    InferenceBound key = entry.getKey();
                    List<Type> value = entry.getValue();
                    ListBuffer listBuffer2 = new ListBuffer();
                    ListBuffer listBuffer3 = new ListBuffer();
                    for (Type type : value) {
                        if (type.containsAny(list)) {
                            listBuffer3.append(type);
                        } else {
                            listBuffer2.append(type);
                        }
                    }
                    this.bounds.put(key, listBuffer2.toList());
                    Iterator it = listBuffer3.iterator();
                    while (it.hasNext()) {
                        addBound(key, types.subst((Type) it.next(), list, list2), types, true);
                    }
                }
            } finally {
                this.listener = undetVarListener;
                for (Pair pair : listBuffer) {
                    notifyBoundChange((InferenceBound) pair.fst, (Type) pair.snd, true);
                }
            }
        }

        @Override // com.sun.tools.javac.code.Type.DelegatedType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAnnotationsString(sb);
            Type type = this.inst;
            if (type == null) {
                sb.append(this.qtype);
                sb.append('?');
            } else {
                sb.append(type);
            }
            return sb.toString();
        }

        public final void addBound(InferenceBound inferenceBound, Type type, Types types) {
            addBound(inferenceBound, type, types, false);
        }
    }
}
