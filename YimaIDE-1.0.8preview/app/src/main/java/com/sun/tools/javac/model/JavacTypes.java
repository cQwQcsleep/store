package com.sun.tools.javac.model;

import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.ListBuffer;
import defpackage.aca;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.ReferenceType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.Types;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacTypes implements Types {
    private static final Set<TypeKind> EXEC_OR_PKG_OR_MOD = EnumSet.of(TypeKind.EXECUTABLE, TypeKind.PACKAGE, TypeKind.MODULE);
    private final Symtab syms;
    private final com.sun.tools.javac.code.Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.model.JavacTypes$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind;

        static {
            int[] iArr = new int[TypeKind.values().length];
            $SwitchMap$javax$lang$model$type$TypeKind = iArr;
            try {
                iArr[TypeKind.DECLARED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INTERSECTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.TYPEVAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.PACKAGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.MODULE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BOOLEAN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.BYTE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.SHORT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.INT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.LONG.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.CHAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.FLOAT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.DOUBLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.VOID.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.NONE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.NULL.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.EXECUTABLE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.WILDCARD.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.UNION.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.ARRAY.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }

    public JavacTypes(Context context) {
        context.put((Class<JavacTypes>) JavacTypes.class, this);
        this.syms = Symtab.instance(context);
        this.types = com.sun.tools.javac.code.Types.instance(context);
    }

    private static <T> T cast(Class<T> cls, Object obj) {
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        b6c.a(obj);
        return null;
    }

    private DeclaredType getDeclaredType0(Type type, Symbol.ClassSymbol classSymbol, TypeMirror... typeMirrorArr) {
        if (typeMirrorArr.length != classSymbol.type.getTypeArguments().length()) {
            w01.a("Incorrect number of type arguments");
            return null;
        }
        ListBuffer listBuffer = new ListBuffer();
        for (TypeMirror typeMirror : typeMirrorArr) {
            if (!(typeMirror instanceof ReferenceType) && !(typeMirror instanceof WildcardType)) {
                w01.a(typeMirror.toString());
                return null;
            }
            listBuffer.append((Type) typeMirror);
        }
        return new Type.ClassType(type, listBuffer.toList(), classSymbol);
    }

    public static JavacTypes instance(Context context) {
        JavacTypes javacTypes = (JavacTypes) context.get(JavacTypes.class);
        return javacTypes == null ? new JavacTypes(context) : javacTypes;
    }

    private void validateTypeNotIn(TypeMirror typeMirror, Set<TypeKind> set) {
        if (set.contains(typeMirror.getKind())) {
            w01.a(typeMirror.toString());
        }
    }

    @Override // javax.lang.model.util.Types
    public Element asElement(TypeMirror typeMirror) {
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[typeMirror.getKind().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return ((Type) cast(Type.class, typeMirror)).asElement();
            default:
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.util.Types
    public TypeMirror asMemberOf(DeclaredType declaredType, Element element) {
        Type type = (Type) declaredType;
        Symbol symbol = (Symbol) element;
        if (this.types.asSuper(type, symbol.getEnclosingElement()) != null) {
            return this.types.memberType(type, symbol);
        }
        q06.a(symbol, "@", type);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.util.Types
    public TypeElement boxedClass(PrimitiveType primitiveType) {
        return this.types.boxedClass((Type) primitiveType);
    }

    @Override // javax.lang.model.util.Types
    public TypeMirror capture(TypeMirror typeMirror) {
        validateTypeNotIn(typeMirror, EXEC_OR_PKG_OR_MOD);
        return this.types.capture((Type) typeMirror).stripMetadata();
    }

    @Override // javax.lang.model.util.Types
    public boolean contains(TypeMirror typeMirror, TypeMirror typeMirror2) {
        Set<TypeKind> set = EXEC_OR_PKG_OR_MOD;
        validateTypeNotIn(typeMirror, set);
        validateTypeNotIn(typeMirror2, set);
        return this.types.containsType((Type) typeMirror, (Type) typeMirror2);
    }

    @Override // javax.lang.model.util.Types
    public List<Type> directSupertypes(TypeMirror typeMirror) {
        validateTypeNotIn(typeMirror, EXEC_OR_PKG_OR_MOD);
        return (List) this.types.directSupertypes((Type) typeMirror).stream().map(new Function() { // from class: sn7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Type) obj).stripMetadataIfNeeded();
            }
        }).collect(Collectors.toList());
    }

    @Override // javax.lang.model.util.Types
    public TypeMirror erasure(TypeMirror typeMirror) {
        TypeKind kind = typeMirror.getKind();
        if (kind != TypeKind.PACKAGE && kind != TypeKind.MODULE) {
            return this.types.erasure((Type) typeMirror).stripMetadata();
        }
        w01.a(typeMirror.toString());
        return null;
    }

    @Override // javax.lang.model.util.Types
    public ArrayType getArrayType(TypeMirror typeMirror) {
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[typeMirror.getKind().ordinal()];
        if (i != 2 && i != 5 && i != 6) {
            switch (i) {
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    break;
                default:
                    return new Type.ArrayType((Type) typeMirror, this.syms.arrayClass);
            }
        }
        w01.a(typeMirror.toString());
        return null;
    }

    @Override // javax.lang.model.util.Types
    public DeclaredType getDeclaredType(TypeElement typeElement, TypeMirror... typeMirrorArr) {
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) typeElement;
        if (typeMirrorArr.length == 0) {
            return (DeclaredType) classSymbol.erasure(this.types);
        }
        if (classSymbol.type.getEnclosingType().isParameterized()) {
            throw new IllegalArgumentException(classSymbol.toString());
        }
        return getDeclaredType0(classSymbol.type.getEnclosingType(), classSymbol, typeMirrorArr);
    }

    @Override // javax.lang.model.util.Types
    public NoType getNoType(TypeKind typeKind) {
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[typeKind.ordinal()];
        if (i == 15) {
            return this.syms.voidType;
        }
        if (i == 16) {
            return Type.noType;
        }
        b6c.a(typeKind);
        return null;
    }

    @Override // javax.lang.model.util.Types
    public NullType getNullType() {
        return (NullType) this.syms.botType;
    }

    public Set<Symbol.MethodSymbol> getOverriddenMethods(Element element) {
        if (element.getKind() != ElementKind.METHOD || element.getModifiers().contains(Modifier.STATIC) || element.getModifiers().contains(Modifier.PRIVATE)) {
            return Collections.EMPTY_SET;
        }
        if (!(element instanceof Symbol.MethodSymbol)) {
            j2d.a();
            return null;
        }
        Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) element;
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) methodSymbol.owner;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Type type : this.types.closure(classSymbol.type)) {
            if (type != classSymbol.type) {
                for (Symbol symbol : ((Symbol.ClassSymbol) type.tsym).members().getSymbolsByName(methodSymbol.name)) {
                    if (symbol.kind == Kinds.Kind.MTH && methodSymbol.overrides(symbol, classSymbol, this.types, true)) {
                        linkedHashSet.add((Symbol.MethodSymbol) symbol);
                    }
                }
            }
        }
        return linkedHashSet;
    }

    @Override // javax.lang.model.util.Types
    public PrimitiveType getPrimitiveType(TypeKind typeKind) {
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[typeKind.ordinal()]) {
            case 7:
                return this.syms.booleanType;
            case 8:
                return this.syms.byteType;
            case 9:
                return this.syms.shortType;
            case 10:
                return this.syms.intType;
            case 11:
                return this.syms.longType;
            case 12:
                return this.syms.charType;
            case 13:
                return this.syms.floatType;
            case 14:
                return this.syms.doubleType;
            default:
                aca.a("Not a primitive type: ", typeKind);
                return null;
        }
    }

    @Override // javax.lang.model.util.Types
    public WildcardType getWildcardType(TypeMirror typeMirror, TypeMirror typeMirror2) {
        BoundKind boundKind;
        Type type;
        if (typeMirror == null && typeMirror2 == null) {
            boundKind = BoundKind.UNBOUND;
            type = this.syms.objectType;
        } else if (typeMirror2 == null) {
            type = (Type) typeMirror;
            boundKind = BoundKind.EXTENDS;
        } else {
            if (typeMirror != null) {
                w01.a("Extends and super bounds cannot both be provided");
                return null;
            }
            boundKind = BoundKind.SUPER;
            type = (Type) typeMirror2;
        }
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[type.getKind().ordinal()];
        if (i == 1 || i == 21 || i == 3 || i == 4) {
            return new Type.WildcardType(type, boundKind, this.syms.boundClass);
        }
        throw new IllegalArgumentException(type.toString());
    }

    @Override // javax.lang.model.util.Types
    public boolean isAssignable(TypeMirror typeMirror, TypeMirror typeMirror2) {
        Set<TypeKind> set = EXEC_OR_PKG_OR_MOD;
        validateTypeNotIn(typeMirror, set);
        validateTypeNotIn(typeMirror2, set);
        return this.types.isAssignable((Type) typeMirror, (Type) typeMirror2);
    }

    @Override // javax.lang.model.util.Types
    public boolean isSameType(TypeMirror typeMirror, TypeMirror typeMirror2) {
        TypeKind kind = typeMirror.getKind();
        TypeKind typeKind = TypeKind.WILDCARD;
        if (kind == typeKind || typeMirror2.getKind() == typeKind) {
            return false;
        }
        return this.types.isSameType((Type) typeMirror, (Type) typeMirror2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.util.Types
    public boolean isSubsignature(ExecutableType executableType, ExecutableType executableType2) {
        return this.types.isSubSignature((Type) executableType, (Type) executableType2);
    }

    @Override // javax.lang.model.util.Types
    public boolean isSubtype(TypeMirror typeMirror, TypeMirror typeMirror2) {
        Set<TypeKind> set = EXEC_OR_PKG_OR_MOD;
        validateTypeNotIn(typeMirror, set);
        validateTypeNotIn(typeMirror2, set);
        return this.types.isSubtype((Type) typeMirror, (Type) typeMirror2);
    }

    @Override // javax.lang.model.util.Types
    public <T extends TypeMirror> T stripAnnotations(T t) {
        return ((Type) t).stripMetadata();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.util.Types
    public PrimitiveType unboxedType(TypeMirror typeMirror) {
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            w01.a(typeMirror.toString());
            return null;
        }
        Type typeUnboxedType = this.types.unboxedType((Type) typeMirror);
        if (typeUnboxedType.isPrimitive()) {
            return (PrimitiveType) typeUnboxedType;
        }
        w01.a(typeMirror.toString());
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.util.Types
    public DeclaredType getDeclaredType(DeclaredType declaredType, TypeElement typeElement, TypeMirror... typeMirrorArr) {
        if (declaredType == 0) {
            return getDeclaredType(typeElement, typeMirrorArr);
        }
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) typeElement;
        Type type = (Type) declaredType;
        if (type.tsym == classSymbol.owner.enclClass()) {
            if (!type.isParameterized()) {
                return getDeclaredType(typeElement, typeMirrorArr);
            }
            return getDeclaredType0(type, classSymbol, typeMirrorArr);
        }
        b6c.a(declaredType);
        return null;
    }
}
