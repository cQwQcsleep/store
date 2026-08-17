package com.sun.tools.javac.code;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.jvm.ClassFile;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.CompilerInternalException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.JavacMessages;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Warner;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import javax.tools.JavaFileObject;
import nbjavac.ExactConversionsSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Types {
    private Type arraySuperType;
    final Name capturedName;
    final Check chk;
    JCDiagnostic.Factory diags;
    public final boolean dumpStacktraceOnError;
    final Enter enter;
    final JavacMessages messages;
    final Names names;
    public final Warner noWarnings;
    final Symtab syms;
    protected static final Context.Key<Types> typesKey = new Context.Key<>();
    private static final TypeMapping<Void> newInstanceFun = new TypeMapping<Void>() { // from class: com.sun.tools.javac.code.Types.20
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type.TypeVar visitTypeVar(Type.TypeVar typeVar, Void r4) {
            return new Type.TypeVar(typeVar.tsym, typeVar.getUpperBound(), typeVar.getLowerBound(), typeVar.getMetadata());
        }
    };
    private static final HashCodeVisitor hashCodeVisitor = new HashCodeVisitor();
    private static final HashCodeVisitor hashCodeStrictVisitor = new HashCodeVisitor() { // from class: com.sun.tools.javac.code.Types.25
        @Override // com.sun.tools.javac.code.Types.HashCodeVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitTypeVar(Type.TypeVar typeVar, Void r2) {
            return Integer.valueOf(System.identityHashCode(typeVar));
        }
    };
    List<Warner> warnStack = List.nil();
    private final UnaryVisitor<Boolean> isUnbounded = new UnaryVisitor<Boolean>() { // from class: com.sun.tools.javac.code.Types.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitClassType(Type.ClassType classType, Void r7) {
            List listAllparams = classType.tsym.type.allparams();
            List listAllparams2 = classType.allparams();
            while (listAllparams.nonEmpty()) {
                Symtab symtab = Types.this.syms;
                if (!Types.this.containsType((Type) listAllparams2.head, new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass, (Type.TypeVar) listAllparams.head))) {
                    return Boolean.FALSE;
                }
                listAllparams = listAllparams.tail;
                listAllparams2 = listAllparams2.tail;
            }
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Void r2) {
            return Boolean.TRUE;
        }
    };
    private final SimpleVisitor<Type, Symbol> asSub = new SimpleVisitor<Type, Symbol>() { // from class: com.sun.tools.javac.code.Types.2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Symbol symbol) {
            Symbol.TypeSymbol typeSymbol = classType.tsym;
            if (typeSymbol == symbol) {
                return classType;
            }
            Type typeAsSuper = Types.this.asSuper(symbol.type, typeSymbol);
            if (typeAsSuper == null) {
                return null;
            }
            ListBuffer<Type> listBuffer = new ListBuffer<>();
            ListBuffer<Type> listBuffer2 = new ListBuffer<>();
            try {
                Types.this.adapt(typeAsSuper, classType, listBuffer, listBuffer2);
                Type typeSubst = Types.this.subst(symbol.type, listBuffer.toList(), listBuffer2.toList());
                if (!Types.this.isSubtype(typeSubst, classType)) {
                    return null;
                }
                ListBuffer listBuffer3 = new ListBuffer();
                for (List listAllparams = symbol.type.allparams(); listAllparams.nonEmpty(); listAllparams = listAllparams.tail) {
                    if (typeSubst.contains((Type) listAllparams.head) && !classType.contains((Type) listAllparams.head)) {
                        listBuffer3.append((Type) listAllparams.head);
                    }
                }
                if (!listBuffer3.nonEmpty()) {
                    return typeSubst;
                }
                if (classType.isRaw()) {
                    return Types.this.erasure(typeSubst);
                }
                List<Type> list = listBuffer3.toList();
                ListBuffer listBuffer4 = new ListBuffer();
                for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                    Symtab symtab = Types.this.syms;
                    listBuffer4.append(new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass, (Type.TypeVar) list2.head));
                }
                return Types.this.subst(typeSubst, list, listBuffer4.toList());
            } catch (AdaptFailure unused) {
                return null;
            }
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitErrorType(Type.ErrorType errorType, Symbol symbol) {
            return errorType;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Symbol symbol) {
            return null;
        }
    };
    private DescriptorCache descCache = new DescriptorCache();
    private Predicate<Symbol> bridgeFilter = new Predicate<Symbol>() { // from class: com.sun.tools.javac.code.Types.3
        @Override // java.util.function.Predicate
        public boolean test(Symbol symbol) {
            if (symbol.kind != Kinds.Kind.MTH) {
                return false;
            }
            Name name = symbol.name;
            Names names = Types.this.names;
            return (name == names.init || name == names.clinit || (symbol.flags() & 4096) != 0) ? false : true;
        }
    };
    private TypeRelation isSubtype = new TypeRelation() { // from class: com.sun.tools.javac.code.Types.4
        private Set<TypePair> cache = new HashSet();

        private boolean containsTypeRecursive(Type type, Type type2) {
            TypePair typePair = Types.this.new TypePair(type, type2);
            boolean zAdd = this.cache.add(typePair);
            Types types = Types.this;
            if (!zAdd) {
                return types.containsType(type.getTypeArguments(), rewriteSupers(type2).getTypeArguments());
            }
            try {
                return types.containsType(type.getTypeArguments(), type2.getTypeArguments());
            } finally {
                this.cache.remove(typePair);
            }
        }

        private Type rewriteSupers(Type type) {
            Type.WildcardType wildcardType;
            if (type.isParameterized()) {
                ListBuffer listBuffer = new ListBuffer();
                ListBuffer listBuffer2 = new ListBuffer();
                Types.this.adaptSelf(type, listBuffer, listBuffer2);
                if (!listBuffer.isEmpty()) {
                    ListBuffer listBuffer3 = new ListBuffer();
                    boolean z = false;
                    for (Type type2 : listBuffer2.toList()) {
                        Type typeRewriteSupers = rewriteSupers(type2);
                        if (!typeRewriteSupers.isSuperBound() || typeRewriteSupers.isExtendsBound()) {
                            if (typeRewriteSupers != type2) {
                                wildcardType = new Type.WildcardType(Types.this.wildUpperBound(typeRewriteSupers), BoundKind.EXTENDS, Types.this.syms.boundClass, typeRewriteSupers.getMetadata());
                            }
                            listBuffer3.append(typeRewriteSupers);
                        } else {
                            Symtab symtab = Types.this.syms;
                            wildcardType = new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass, typeRewriteSupers.getMetadata());
                        }
                        typeRewriteSupers = wildcardType;
                        z = true;
                        listBuffer3.append(typeRewriteSupers);
                    }
                    if (z) {
                        return Types.this.subst(type.tsym.type, listBuffer.toList(), listBuffer3.toList());
                    }
                }
            }
            return type;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitArrayType(Type.ArrayType arrayType, Type type) {
            if (type.hasTag(TypeTag.ARRAY)) {
                boolean zIsPrimitive = arrayType.elemtype.isPrimitive();
                Types types = Types.this;
                return zIsPrimitive ? Boolean.valueOf(types.isSameType(arrayType.elemtype, types.elemtype(type))) : Boolean.valueOf(types.isSubtypeNoCapture(arrayType.elemtype, types.elemtype(type)));
            }
            if (!type.hasTag(TypeTag.CLASS)) {
                return Boolean.FALSE;
            }
            Name qualifiedName = type.tsym.getQualifiedName();
            Names names = Types.this.names;
            return Boolean.valueOf(qualifiedName == names.java_lang_Object || qualifiedName == names.java_lang_Cloneable || qualifiedName == names.java_io_Serializable);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitClassType(Type.ClassType classType, Type type) {
            Type typeAsSuper = Types.this.asSuper(classType, type.tsym);
            if (typeAsSuper == null) {
                return Boolean.FALSE;
            }
            if (typeAsSuper.hasTag(TypeTag.CLASS)) {
                return Boolean.valueOf(typeAsSuper.tsym == type.tsym && (!type.isParameterized() || containsTypeRecursive(type, typeAsSuper)) && Types.this.isSubtypeNoCapture(typeAsSuper.getEnclosingType(), type.getEnclosingType()));
            }
            return Boolean.valueOf(Types.this.isSubtypeNoCapture(typeAsSuper, type));
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Type type2) {
            boolean z = true;
            switch (AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
                case 3:
                    return Boolean.valueOf(!type2.hasTag(TypeTag.CHAR) && type.getTag().isSubRangeOf(type2.getTag()));
                case 4:
                    return Boolean.valueOf(!type2.hasTag(TypeTag.SHORT) && type.getTag().isSubRangeOf(type2.getTag()));
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    return Boolean.valueOf(type.getTag().isSubRangeOf(type2.getTag()));
                case 10:
                case 11:
                    return Boolean.valueOf(type.hasTag(type2.getTag()));
                case 12:
                    return Boolean.valueOf(Types.this.isSubtypeNoCapture(type.getUpperBound(), type2));
                case 13:
                    if (!type2.hasTag(TypeTag.BOT) && !type2.hasTag(TypeTag.CLASS) && !type2.hasTag(TypeTag.ARRAY) && !type2.hasTag(TypeTag.TYPEVAR)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                case 14:
                case 15:
                    return Boolean.FALSE;
                default:
                    pe1.a("isSubtype ", type.getTag());
                    return null;
            }
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitUndetVar(Type.UndetVar undetVar, Type type) {
            if (undetVar == type || undetVar.qtype == type || type.hasTag(TypeTag.ERROR)) {
                return Boolean.TRUE;
            }
            if (type.hasTag(TypeTag.BOT)) {
                return Boolean.FALSE;
            }
            undetVar.addBound(Type.UndetVar.InferenceBound.UPPER, type, Types.this);
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitErrorType(Type.ErrorType errorType, Type type) {
            return Boolean.TRUE;
        }
    };
    TypeEqualityVisitor isSameTypeVisitor = new TypeEqualityVisitor() { // from class: com.sun.tools.javac.code.Types.5
        @Override // com.sun.tools.javac.code.Types.TypeEqualityVisitor
        public boolean sameTypeArguments(List<Type> list, List<Type> list2) {
            return Types.this.containsTypeEquivalent(list, list2);
        }

        @Override // com.sun.tools.javac.code.Types.TypeEqualityVisitor
        public boolean sameTypeComparator(Type type, Type type2) {
            return Types.this.isSameType(type, type2);
        }
    };
    private TypeRelation containsType = new TypeRelation() { // from class: com.sun.tools.javac.code.Types.6
        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Type type2) {
            boolean zIsPartial = type2.isPartial();
            Types types = Types.this;
            return zIsPartial ? Boolean.valueOf(types.containedBy(type2, type)) : Boolean.valueOf(types.isSameType(type, type2));
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitUndetVar(Type.UndetVar undetVar, Type type) {
            return !type.hasTag(TypeTag.WILDCARD) ? Boolean.valueOf(Types.this.isSameType(undetVar, type)) : Boolean.FALSE;
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0037  */
        /* JADX WARN: Code duplicated, block: B:16:0x003d  */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitWildcardType(Type.WildcardType wildcardType, Type type) {
            boolean z;
            Types types;
            boolean zIsPartial = type.isPartial();
            Types types2 = Types.this;
            if (zIsPartial) {
                return Boolean.valueOf(types2.containedBy(type, wildcardType));
            }
            if (types2.isSameWildcard(wildcardType, type) || Types.this.isCaptureOf(type, wildcardType)) {
                z = true;
            } else if (wildcardType.isExtendsBound()) {
                if (!wildcardType.isSuperBound()) {
                    types = Types.this;
                    if (!types.isSubtypeNoCapture(types.wildUpperBound(type), Types.this.wildUpperBound(wildcardType))) {
                        z = false;
                    }
                }
                z = true;
            } else {
                Types types3 = Types.this;
                if (types3.isSubtypeNoCapture(types3.wildLowerBound(wildcardType), Types.this.wildLowerBound(type))) {
                    if (!wildcardType.isSuperBound()) {
                        types = Types.this;
                        if (!types.isSubtypeNoCapture(types.wildUpperBound(type), Types.this.wildUpperBound(wildcardType))) {
                        }
                    }
                    z = true;
                }
                z = false;
            }
            return Boolean.valueOf(z);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitErrorType(Type.ErrorType errorType, Type type) {
            return Boolean.TRUE;
        }
    };
    private TypeRelation isCastable = new TypeRelation() { // from class: com.sun.tools.javac.code.Types.7
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitArrayType(Type.ArrayType arrayType, Type type) {
            int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
            if (i == 1) {
                if (Types.this.elemtype(arrayType).isPrimitive() || Types.this.elemtype(type).isPrimitive()) {
                    return Boolean.valueOf(Types.this.elemtype(arrayType).hasTag(Types.this.elemtype(type).getTag()));
                }
                Types types = Types.this;
                return Boolean.valueOf(types.isCastable(types.elemtype(arrayType), Types.this.elemtype(type), Types.this.warnStack.head));
            }
            if (i == 2) {
                return Boolean.valueOf(Types.this.isSubtype(arrayType, type));
            }
            if (i != 12) {
                return (i == 13 || i == 16) ? Boolean.TRUE : Boolean.FALSE;
            }
            Types types2 = Types.this;
            if (!types2.isCastable(type, arrayType, types2.noWarnings)) {
                return Boolean.FALSE;
            }
            Types.this.warnStack.head.warn(Lint.LintCategory.UNCHECKED);
            return Boolean.TRUE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v13, types: [com.sun.tools.javac.code.Type] */
        /* JADX WARN: Type inference failed for: r0v14 */
        /* JADX WARN: Type inference failed for: r11v6, types: [com.sun.tools.javac.code.Types] */
        /* JADX WARN: Type inference failed for: r12v0, types: [com.sun.tools.javac.code.Type, com.sun.tools.javac.code.Type$ClassType] */
        /* JADX WARN: Type inference failed for: r12v1, types: [com.sun.tools.javac.code.Type] */
        /* JADX WARN: Type inference failed for: r12v5 */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitClassType(Type.ClassType classType, Type type) {
            if (type.hasTag(TypeTag.ERROR) || type.hasTag(TypeTag.BOT)) {
                return Boolean.TRUE;
            }
            if (type.hasTag(TypeTag.TYPEVAR)) {
                if (!Types.this.isCastable(classType, type.getUpperBound(), Types.this.noWarnings)) {
                    return Boolean.FALSE;
                }
                Types.this.warnStack.head.warn(Lint.LintCategory.UNCHECKED);
                return Boolean.TRUE;
            }
            if (classType.isCompound() || type.isCompound()) {
                return Boolean.valueOf(!classType.isCompound() ? visitCompoundType((Type.ClassType) type, classType, true) : visitCompoundType(classType, type, false));
            }
            TypeTag typeTag = TypeTag.CLASS;
            if (type.hasTag(typeTag) || type.hasTag(TypeTag.ARRAY)) {
                Types types = Types.this;
                boolean zIsSubtype = types.isSubtype(types.erasure((Type) classType), Types.this.erasure(type));
                if (!zIsSubtype) {
                    Types types2 = Types.this;
                    if (!types2.isSubtype(types2.erasure(type), Types.this.erasure((Type) classType))) {
                        if (type.hasTag(typeTag)) {
                            long jFlags = type.tsym.flags() & 512;
                            Symbol.TypeSymbol typeSymbol = classType.tsym;
                            if (jFlags != 0) {
                                long jFlags2 = typeSymbol.flags() & 16;
                                Types types3 = Types.this;
                                return Boolean.valueOf(jFlags2 == 0 ? types3.sideCast(classType, type, types3.warnStack.head) : types3.sideCastFinal(classType, type, types3.warnStack.head));
                            }
                            if ((typeSymbol.flags() & 512) == 0) {
                                return Boolean.FALSE;
                            }
                            long jFlags3 = type.tsym.flags() & 16;
                            Types types4 = Types.this;
                            return Boolean.valueOf(jFlags3 == 0 ? types4.sideCast(classType, type, types4.warnStack.head) : types4.sideCastFinal(classType, type, types4.warnStack.head));
                        }
                    }
                }
                if (!zIsSubtype && type.hasTag(TypeTag.ARRAY)) {
                    if (!Types.this.isReifiable(type)) {
                        Types.this.warnStack.head.warn(Lint.LintCategory.UNCHECKED);
                    }
                    return Boolean.TRUE;
                }
                if (type.isRaw()) {
                    return Boolean.TRUE;
                }
                if (classType.isRaw()) {
                    if (!Types.this.isUnbounded(type)) {
                        Types.this.warnStack.head.warn(Lint.LintCategory.UNCHECKED);
                    }
                    return Boolean.TRUE;
                }
                ?? r0 = zIsSubtype ? classType : type;
                if (zIsSubtype) {
                    classType = type;
                }
                Type typeRewriteQuantifiers = Types.this.rewriteQuantifiers(r0, true, false);
                Type typeRewriteQuantifiers2 = Types.this.rewriteQuantifiers(r0, false, false);
                Type typeRewriteQuantifiers3 = Types.this.rewriteQuantifiers(classType, true, false);
                Type typeAsSub = Types.this.asSub(Types.this.rewriteQuantifiers(classType, false, false), typeRewriteQuantifiers2.tsym);
                Type typeAsSub2 = typeAsSub == null ? null : Types.this.asSub(typeRewriteQuantifiers3, typeRewriteQuantifiers.tsym);
                if (typeAsSub2 == null) {
                    typeRewriteQuantifiers = Types.this.rewriteQuantifiers(r0, true, true);
                    typeRewriteQuantifiers2 = Types.this.rewriteQuantifiers(r0, false, true);
                    Type typeRewriteQuantifiers4 = Types.this.rewriteQuantifiers(classType, true, true);
                    typeAsSub = Types.this.asSub(Types.this.rewriteQuantifiers(classType, false, true), typeRewriteQuantifiers2.tsym);
                    typeAsSub2 = typeAsSub != null ? Types.this.asSub(typeRewriteQuantifiers4, typeRewriteQuantifiers.tsym) : null;
                }
                if (typeAsSub2 != null) {
                    Symbol.TypeSymbol typeSymbol2 = r0.tsym;
                    if (typeSymbol2 != typeAsSub2.tsym || typeSymbol2 != typeAsSub.tsym) {
                        Assert.error(r0.tsym + " != " + typeAsSub2.tsym + " != " + typeAsSub.tsym);
                    }
                    if (!Types.this.disjointTypes(typeRewriteQuantifiers.allparams(), typeAsSub2.allparams()) && !Types.this.disjointTypes(typeRewriteQuantifiers.allparams(), typeAsSub.allparams()) && !Types.this.disjointTypes(typeRewriteQuantifiers2.allparams(), typeAsSub2.allparams()) && !Types.this.disjointTypes(typeRewriteQuantifiers2.allparams(), typeAsSub.allparams())) {
                        Types types5 = Types.this;
                        if (!zIsSubtype ? types5.giveWarning(classType, r0) : types5.giveWarning(r0, classType)) {
                            Types.this.warnStack.head.warn(Lint.LintCategory.UNCHECKED);
                        }
                        return Boolean.TRUE;
                    }
                }
                boolean zIsReifiable = Types.this.isReifiable(type);
                ?? r11 = Types.this;
                return zIsReifiable ? Boolean.valueOf(r11.isSubtypeUnchecked(r0, classType)) : Boolean.valueOf(r11.isSubtypeUnchecked(r0, classType, r11.warnStack.head));
            }
            return Boolean.FALSE;
        }

        public boolean visitCompoundType(Type.ClassType classType, Type type, boolean z) {
            Types types = Types.this;
            Warner warner = types.noWarnings;
            for (Type type2 : types.directSupertypes(classType)) {
                warner.clear();
                Types types2 = Types.this;
                if (z) {
                    if (!types2.isCastable(type, type2, warner)) {
                        return false;
                    }
                } else if (!types2.isCastable(type2, type, warner)) {
                    return false;
                }
            }
            Lint.LintCategory lintCategory = Lint.LintCategory.UNCHECKED;
            if (!warner.hasLint(lintCategory)) {
                return true;
            }
            Types.this.warnStack.head.warn(lintCategory);
            return true;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Type type2) {
            if (type2.hasTag(TypeTag.ERROR) || type.hasTag(TypeTag.NONE)) {
                return Boolean.TRUE;
            }
            switch (AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    return Boolean.valueOf(type2.isNumeric());
                case 10:
                    return Boolean.valueOf(type2.hasTag(TypeTag.BOOLEAN));
                case 11:
                    return Boolean.FALSE;
                case 12:
                default:
                    x1f.a();
                    return null;
                case 13:
                    return Boolean.valueOf(Types.this.isSubtype(type, type2));
            }
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitTypeVar(Type.TypeVar typeVar, Type type) {
            int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
            if (i != 12) {
                return (i == 13 || i == 16) ? Boolean.TRUE : Boolean.valueOf(Types.this.isCastable(typeVar.getUpperBound(), type, Types.this.warnStack.head));
            }
            if (Types.this.isSubtype(typeVar, type)) {
                return Boolean.TRUE;
            }
            if (!Types.this.isCastable(typeVar.getUpperBound(), type, Types.this.noWarnings)) {
                return Boolean.FALSE;
            }
            Types.this.warnStack.head.warn(Lint.LintCategory.UNCHECKED);
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitWildcardType(Type.WildcardType wildcardType, Type type) {
            Types types = Types.this;
            return Boolean.valueOf(types.isCastable(types.wildUpperBound(wildcardType), type, Types.this.warnStack.head));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitErrorType(Type.ErrorType errorType, Type type) {
            return Boolean.TRUE;
        }
    };
    private TypeRelation disjointType = new TypeRelation() { // from class: com.sun.tools.javac.code.Types.8
        private Set<TypePair> cache = new HashSet();

        private boolean isCastableRecursive(Type type, Type type2) {
            TypePair typePair = Types.this.new TypePair(type, type2);
            if (!this.cache.add(typePair)) {
                return true;
            }
            try {
                return Types.this.isCastable(type, type2);
            } finally {
                this.cache.remove(typePair);
            }
        }

        private boolean notSoftSubtypeRecursive(Type type, Type type2) {
            TypePair typePair = Types.this.new TypePair(type, type2);
            if (!this.cache.add(typePair)) {
                return false;
            }
            try {
                return Types.this.notSoftSubtype(type, type2);
            } finally {
                this.cache.remove(typePair);
            }
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Type type2) {
            if (type2.hasTag(TypeTag.WILDCARD)) {
                return visit(type2, type);
            }
            return Boolean.valueOf(notSoftSubtypeRecursive(type, type2) || notSoftSubtypeRecursive(type2, type));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitWildcardType(Type.WildcardType wildcardType, Type type) {
            if (wildcardType.isUnbound()) {
                return Boolean.FALSE;
            }
            if (!type.hasTag(TypeTag.WILDCARD)) {
                boolean zIsExtendsBound = wildcardType.isExtendsBound();
                Type type2 = wildcardType.type;
                return zIsExtendsBound ? Boolean.valueOf(notSoftSubtypeRecursive(type, type2)) : Boolean.valueOf(notSoftSubtypeRecursive(type2, type));
            }
            if (type.isUnbound()) {
                return Boolean.FALSE;
            }
            if (wildcardType.isExtendsBound()) {
                if (type.isExtendsBound()) {
                    return Boolean.valueOf(!isCastableRecursive(wildcardType.type, Types.this.wildUpperBound(type)));
                }
                if (type.isSuperBound()) {
                    return Boolean.valueOf(notSoftSubtypeRecursive(Types.this.wildLowerBound(type), wildcardType.type));
                }
            } else if (wildcardType.isSuperBound() && type.isExtendsBound()) {
                return Boolean.valueOf(notSoftSubtypeRecursive(wildcardType.type, Types.this.wildUpperBound(type)));
            }
            return Boolean.FALSE;
        }
    };
    private final TypeMapping<Void> cvarLowerBoundMapping = new TypeMapping<Void>() { // from class: com.sun.tools.javac.code.Types.9
        @Override // com.sun.tools.javac.code.Types.TypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitCapturedType(Type.CapturedType capturedType, Void r2) {
            return Types.this.cvarLowerBound(capturedType);
        }
    };
    private UnaryVisitor<Boolean> isReifiable = new UnaryVisitor<Boolean>() { // from class: com.sun.tools.javac.code.Types.10
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitArrayType(Type.ArrayType arrayType, Void r2) {
            return visit(arrayType.elemtype);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitClassType(Type.ClassType classType, Void r2) {
            if (classType.isCompound()) {
                return Boolean.FALSE;
            }
            if (!classType.isParameterized()) {
                return Boolean.TRUE;
            }
            Iterator<Type> it = classType.allparams().iterator();
            while (it.hasNext()) {
                if (!it.next().isUnbound()) {
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Void r2) {
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitTypeVar(Type.TypeVar typeVar, Void r2) {
            return Boolean.FALSE;
        }
    };
    private TypeMapping<Void> elemTypeFun = new TypeMapping<Void>() { // from class: com.sun.tools.javac.code.Types.11
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Void r3) {
            return visit(Types.this.skipTypeVars(typeVar, false));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(Type.ArrayType arrayType, Void r2) {
            return arrayType.elemtype;
        }
    };
    private SimpleVisitor<Type, Symbol> asSuper = new SimpleVisitor<Type, Symbol>() { // from class: com.sun.tools.javac.code.Types.12
        private Set<Symbol> seenTypes = new HashSet();

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(Type.ArrayType arrayType, Symbol symbol) {
            if (Types.this.isSubtype(arrayType, symbol.type)) {
                return symbol.type;
            }
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:19:0x0036 A[Catch: all -> 0x0026, TRY_ENTER, TryCatch #0 {all -> 0x0026, blocks: (B:8:0x000f, B:10:0x001d, B:19:0x0036, B:21:0x0043, B:22:0x0049, B:24:0x004f, B:26:0x005b, B:29:0x0068, B:15:0x0028), top: B:35:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:21:0x0043 A[Catch: all -> 0x0026, TryCatch #0 {all -> 0x0026, blocks: (B:8:0x000f, B:10:0x001d, B:19:0x0036, B:21:0x0043, B:22:0x0049, B:24:0x004f, B:26:0x005b, B:29:0x0068, B:15:0x0028), top: B:35:0x000f }] */
        /* JADX WARN: Code duplicated, block: B:24:0x004f A[Catch: all -> 0x0026, TryCatch #0 {all -> 0x0026, blocks: (B:8:0x000f, B:10:0x001d, B:19:0x0036, B:21:0x0043, B:22:0x0049, B:24:0x004f, B:26:0x005b, B:29:0x0068, B:15:0x0028), top: B:35:0x000f }] */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Symbol symbol) {
            Type typeAsSuper;
            List listInterfaces;
            Symbol.TypeSymbol typeSymbol = classType.tsym;
            if (typeSymbol == symbol) {
                return classType;
            }
            if (!this.seenTypes.add(typeSymbol)) {
                return null;
            }
            try {
                Type typeSupertype = Types.this.supertype(classType);
                if (!typeSupertype.hasTag(TypeTag.CLASS) && !typeSupertype.hasTag(TypeTag.TYPEVAR)) {
                    if ((symbol.flags() & 512) != 0) {
                        while (listInterfaces.nonEmpty()) {
                            if (!((Type) listInterfaces.head).hasTag(TypeTag.ERROR)) {
                            }
                        }
                    }
                    return null;
                }
                typeAsSuper = Types.this.asSuper(typeSupertype, symbol);
                if (typeAsSuper == null) {
                    if ((symbol.flags() & 512) != 0) {
                        for (listInterfaces = Types.this.interfaces(classType); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
                            if (!((Type) listInterfaces.head).hasTag(TypeTag.ERROR) || (typeAsSuper = Types.this.asSuper((Type) listInterfaces.head, symbol)) == null) {
                            }
                        }
                    }
                    return null;
                }
                return typeAsSuper;
            } finally {
                this.seenTypes.remove(typeSymbol);
            }
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Symbol symbol) {
            return typeVar.tsym == symbol ? typeVar : Types.this.asSuper(typeVar.getUpperBound(), symbol);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitErrorType(Type.ErrorType errorType, Symbol symbol) {
            return errorType;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Symbol symbol) {
            return null;
        }
    };
    private SimpleVisitor<Type, Symbol> memberType = new SimpleVisitor<Type, Symbol>() { // from class: com.sun.tools.javac.code.Types.13
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Symbol symbol) {
            Symbol symbol2 = symbol.owner;
            if ((symbol.flags() & 8) == 0 && symbol2.type.isParameterized()) {
                Type typeAsOuterSuper = Types.this.asOuterSuper(classType, symbol2);
                if (classType.isCompound()) {
                    typeAsOuterSuper = Types.this.capture(typeAsOuterSuper);
                }
                if (typeAsOuterSuper != null) {
                    List<Type> listAllparams = symbol2.type.allparams();
                    List<Type> listAllparams2 = typeAsOuterSuper.allparams();
                    if (listAllparams.nonEmpty()) {
                        boolean zIsEmpty = listAllparams2.isEmpty();
                        Types types = Types.this;
                        return zIsEmpty ? types.erasure(symbol.type) : types.subst(symbol.type, listAllparams, listAllparams2);
                    }
                }
            }
            return symbol.type;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Symbol symbol) {
            return Types.this.memberType(typeVar.getUpperBound(), symbol);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(Type.WildcardType wildcardType, Symbol symbol) {
            Types types = Types.this;
            return types.memberType(types.wildUpperBound(wildcardType), symbol);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitErrorType(Type.ErrorType errorType, Symbol symbol) {
            return errorType;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Symbol symbol) {
            return symbol.type;
        }
    };
    private TypeMapping<Boolean> erasure = new Type.StructuralTypeMapping<Boolean>() { // from class: com.sun.tools.javac.code.Types.14
        private Type combineMetadata(Type type, Type type2) {
            if (!type2.getMetadata().nonEmpty()) {
                return type;
            }
            switch (AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 12:
                case 13:
                case 14:
                case 19:
                    break;
                case 2:
                    if ((type instanceof Type.UnionClassType) || (type instanceof Type.IntersectionClassType)) {
                        return type;
                    }
                    break;
                case 11:
                case 15:
                case 16:
                case 17:
                case 18:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    return type;
                default:
                    x01.a(type.getTag().name());
                    return null;
            }
            return type.dropMetadata(TypeMetadata.Annotations.class);
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Boolean bool) {
            Type typeErasure = classType.tsym.erasure(Types.this);
            return bool.booleanValue() ? new Type.ErasedClassType(typeErasure.getEnclosingType(), typeErasure.tsym, classType.dropMetadata(TypeMetadata.Annotations.class).getMetadata()) : combineMetadata(typeErasure, classType);
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Boolean bool) {
            return type.isPrimitive() ? type : combineMetadata(type, type);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Boolean bool) {
            return combineMetadata(Types.this.erasure(typeVar.getUpperBound(), bool.booleanValue()), typeVar);
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(Type.WildcardType wildcardType, Boolean bool) {
            Types types = Types.this;
            return combineMetadata(types.erasure(types.wildUpperBound(wildcardType), bool.booleanValue()), wildcardType);
        }
    };
    private UnaryVisitor<Type> supertype = new UnaryVisitor<Type>() { // from class: com.sun.tools.javac.code.Types.15
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(Type.ArrayType arrayType, Void r4) {
            if (!arrayType.elemtype.isPrimitive()) {
                Types types = Types.this;
                if (!types.isSameType(arrayType.elemtype, types.syms.objectType)) {
                    return new Type.ArrayType(Types.this.supertype(arrayType.elemtype), arrayType.tsym);
                }
            }
            return Types.this.arraySuperType();
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Void r5) {
            if (classType.supertype_field == null) {
                Type superclass = ((Symbol.ClassSymbol) classType.tsym).getSuperclass();
                if (classType.isInterface()) {
                    superclass = ((Type.ClassType) classType.tsym.type).supertype_field;
                }
                if (classType.supertype_field == null) {
                    List<Type> listAllparams = Types.this.classBound(classType).allparams();
                    List<Type> listAllparams2 = classType.tsym.type.allparams();
                    if (classType.hasErasedSupertypes()) {
                        classType.supertype_field = Types.this.erasureRecursive(superclass);
                    } else if (listAllparams2.nonEmpty()) {
                        classType.supertype_field = Types.this.subst(superclass, listAllparams2, listAllparams);
                    } else {
                        classType.supertype_field = superclass;
                    }
                }
            }
            return classType.supertype_field;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Void r3) {
            return (typeVar.getUpperBound().hasTag(TypeTag.TYPEVAR) || !(typeVar.getUpperBound().isCompound() || typeVar.getUpperBound().isInterface())) ? typeVar.getUpperBound() : Types.this.supertype(typeVar.getUpperBound());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitErrorType(Type.ErrorType errorType, Void r2) {
            return Type.noType;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Void r2) {
            return Type.noType;
        }
    };
    private UnaryVisitor<List<Type>> interfaces = new UnaryVisitor<List<Type>>() { // from class: com.sun.tools.javac.code.Types.16
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public List<Type> visitClassType(Type.ClassType classType, Void r5) {
            if (classType.interfaces_field == null) {
                List<Type> interfaces = ((Symbol.ClassSymbol) classType.tsym).getInterfaces();
                if (classType.interfaces_field == null) {
                    Assert.check(classType != classType.tsym.type, classType);
                    List<Type> listAllparams = classType.allparams();
                    List<Type> listAllparams2 = classType.tsym.type.allparams();
                    if (classType.hasErasedSupertypes()) {
                        classType.interfaces_field = Types.this.erasureRecursive(interfaces);
                    } else if (listAllparams2.nonEmpty()) {
                        classType.interfaces_field = Types.this.subst(interfaces, listAllparams2, listAllparams);
                    } else {
                        classType.interfaces_field = interfaces;
                    }
                }
            }
            return classType.interfaces_field;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public List<Type> visitTypeVar(Type.TypeVar typeVar, Void r2) {
            if (typeVar.getUpperBound().isCompound()) {
                return Types.this.interfaces(typeVar.getUpperBound());
            }
            return typeVar.getUpperBound().isInterface() ? List.of(typeVar.getUpperBound()) : List.nil();
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public List<Type> visitType(Type type, Void r2) {
            return List.nil();
        }
    };
    private final UnaryVisitor<List<Type>> directSupertypes = new UnaryVisitor<List<Type>>() { // from class: com.sun.tools.javac.code.Types.17
        @Override // com.sun.tools.javac.code.Type.Visitor
        public List<Type> visitType(Type type, Void r3) {
            if (type.isIntersection()) {
                return ((Type.IntersectionClassType) type).getExplicitComponents();
            }
            Type typeSupertype = Types.this.supertype(type);
            return (typeSupertype == Type.noType || typeSupertype == type || typeSupertype == null) ? Types.this.interfaces(type) : Types.this.interfaces(type).prepend(typeSupertype);
        }
    };
    Map<Type, Boolean> isDerivedRawCache = new HashMap();
    private UnaryVisitor<Type> classBound = new UnaryVisitor<Type>() { // from class: com.sun.tools.javac.code.Types.18
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Void r4) {
            Type typeClassBound = Types.this.classBound(classType.getEnclosingType());
            return typeClassBound != classType.getEnclosingType() ? new Type.ClassType(typeClassBound, classType.getTypeArguments(), classType.tsym, classType.getMetadata()) : classType;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Void r2) {
            Types types = Types.this;
            return types.classBound(types.supertype(typeVar));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitErrorType(Type.ErrorType errorType, Void r2) {
            return errorType;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Void r2) {
            return type;
        }
    };
    private ImplementationCache implCache = new ImplementationCache();
    private MembersClosureCache membersCache = new MembersClosureCache();
    public CandidatesCache candidatesCache = new CandidatesCache();
    TypeRelation hasSameArgs_strict = new HasSameArgs(true);
    TypeRelation hasSameArgs_nonstrict = new HasSameArgs(false);
    private final MapVisitor<List<Type>> methodWithParameters = new MapVisitor<List<Type>>() { // from class: com.sun.tools.javac.code.Types.21
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitForAll(Type.ForAll forAll, List<Type> list) {
            return new Type.ForAll(forAll.tvars, (Type) forAll.qtype.accept(this, list));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitMethodType(Type.MethodType methodType, List<Type> list) {
            return new Type.MethodType(list, methodType.restype, methodType.thrown, methodType.tsym);
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, List<Type> list) {
            throw new IllegalArgumentException("Not a method type: " + type);
        }
    };
    private final MapVisitor<List<Type>> methodWithThrown = new MapVisitor<List<Type>>() { // from class: com.sun.tools.javac.code.Types.22
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitForAll(Type.ForAll forAll, List<Type> list) {
            return new Type.ForAll(forAll.tvars, (Type) forAll.qtype.accept(this, list));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitMethodType(Type.MethodType methodType, List<Type> list) {
            return new Type.MethodType(methodType.argtypes, methodType.restype, list, methodType.tsym);
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, List<Type> list) {
            throw new IllegalArgumentException("Not a method type: " + type);
        }
    };
    private final MapVisitor<Type> methodWithReturn = new MapVisitor<Type>() { // from class: com.sun.tools.javac.code.Types.23
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitForAll(final Type.ForAll forAll, Type type) {
            return new Type.ForAll(this, forAll.tvars, (Type) forAll.qtype.accept(this, type)) { // from class: com.sun.tools.javac.code.Types.23.2
                final /* synthetic */ AnonymousClass23 this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return forAll;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitMethodType(final Type.MethodType methodType, Type type) {
            return new Type.MethodType(this, methodType.argtypes, type, methodType.thrown, methodType.tsym) { // from class: com.sun.tools.javac.code.Types.23.1
                final /* synthetic */ AnonymousClass23 this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.sun.tools.javac.code.Type
                public Type baseType() {
                    return methodType;
                }
            };
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Type type2) {
            throw new IllegalArgumentException("Not a method type: " + type);
        }
    };
    private Map<Type, List<Type>> closureCache = new HashMap();
    BiPredicate<Type, Type> basicClosureSkip = new BiPredicate() { // from class: uwe
        @Override // java.util.function.BiPredicate
        public final boolean test(Object obj, Object obj2) {
            return Types.c((Type) obj, (Type) obj2);
        }
    };
    TypeEqualityVisitor exactTypeVisitor = new TypeEqualityVisitor() { // from class: com.sun.tools.javac.code.Types.24
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Types.TypeEqualityVisitor
        public boolean sameTypeArguments(List<Type> list, List<Type> list2) {
            List list3 = list;
            List list4 = list2;
            while (list3.nonEmpty() && list4.nonEmpty() && sameTypeComparator((Type) list3.head, (Type) list4.head)) {
                list3 = list3.tail;
                list4 = list4.tail;
            }
            return list3.isEmpty() && list4.isEmpty();
        }

        @Override // com.sun.tools.javac.code.Types.TypeEqualityVisitor
        public boolean sameTypeComparator(Type type, Type type2) {
            return Types.this.exactTypeVisitor.visit(type, type2).booleanValue();
        }
    };
    Set<TypePair> mergeCache = new HashSet();

    /* JADX INFO: renamed from: com.sun.tools.javac.code.Types$26, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass26 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$BoundKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.VOID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.WILDCARD.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.NONE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ERROR.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNDETVAR.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FORALL.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.MODULE.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.METHOD.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.PACKAGE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DEFERRED.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_THIS.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_OBJECT.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            int[] iArr2 = new int[BoundKind.values().length];
            $SwitchMap$com$sun$tools$javac$code$BoundKind = iArr2;
            try {
                iArr2[BoundKind.EXTENDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$BoundKind[BoundKind.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$BoundKind[BoundKind.UNBOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    public static class AdaptFailure extends RuntimeException {
        static final long serialVersionUID = -7490231548272701566L;
    }

    public class CandidatesCache {
        public Map<Entry, List<Symbol.MethodSymbol>> cache = new WeakHashMap();

        public class Entry {
            Symbol.MethodSymbol msym;
            Type site;

            public Entry(Type type, Symbol.MethodSymbol methodSymbol) {
                this.site = type;
                this.msym = methodSymbol;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Entry)) {
                    return false;
                }
                Entry entry = (Entry) obj;
                return entry.msym == this.msym && Types.this.isSameType(this.site, entry.site);
            }

            public int hashCode() {
                return (~this.msym.hashCode()) & Types.this.hashCode(this.site);
            }
        }

        public CandidatesCache() {
        }

        public List<Symbol.MethodSymbol> get(Entry entry) {
            return this.cache.get(entry);
        }

        public void put(Entry entry, List<Symbol.MethodSymbol> list) {
            this.cache.put(entry, list);
        }
    }

    public class ClosureHolder {
        List<Type> closure = List.nil();
        final boolean minClosure;
        final BiPredicate<Type, Type> shouldSkip;

        public ClosureHolder(boolean z, BiPredicate<Type, Type> biPredicate) {
            this.minClosure = z;
            this.shouldSkip = biPredicate;
        }

        public void add(Type type) {
            this.closure = Types.this.insert(this.closure, type, this.shouldSkip);
        }

        public List<Type> closure() {
            return this.minClosure ? Types.this.closureMin(this.closure) : this.closure;
        }

        public ClosureHolder merge(ClosureHolder closureHolder) {
            this.closure = Types.this.union(this.closure, closureHolder.closure, this.shouldSkip);
            return this;
        }
    }

    public static abstract class DefaultSymbolVisitor<R, S> implements Symbol.Visitor<R, S> {
        public final R visit(Symbol symbol, S s) {
            return (R) symbol.accept(this, s);
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public R visitClassSymbol(Symbol.ClassSymbol classSymbol, S s) {
            return visitSymbol(classSymbol, s);
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public R visitMethodSymbol(Symbol.MethodSymbol methodSymbol, S s) {
            return visitSymbol(methodSymbol, s);
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public R visitOperatorSymbol(Symbol.OperatorSymbol operatorSymbol, S s) {
            return visitSymbol(operatorSymbol, s);
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public R visitPackageSymbol(Symbol.PackageSymbol packageSymbol, S s) {
            return visitSymbol(packageSymbol, s);
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public R visitTypeSymbol(Symbol.TypeSymbol typeSymbol, S s) {
            return visitSymbol(typeSymbol, s);
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public R visitVarSymbol(Symbol.VarSymbol varSymbol, S s) {
            return visitSymbol(varSymbol, s);
        }
    }

    public static abstract class DefaultTypeVisitor<R, S> implements Type.Visitor<R, S> {
        public final R visit(Type type, S s) {
            return (R) type.accept(this, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitArrayType(Type.ArrayType arrayType, S s) {
            return visitType(arrayType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitCapturedType(Type.CapturedType capturedType, S s) {
            return visitType(capturedType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitClassType(Type.ClassType classType, S s) {
            return visitType(classType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitErrorType(Type.ErrorType errorType, S s) {
            return visitType(errorType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitForAll(Type.ForAll forAll, S s) {
            return visitType(forAll, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitMethodType(Type.MethodType methodType, S s) {
            return visitType(methodType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitModuleType(Type.ModuleType moduleType, S s) {
            return visitType(moduleType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitPackageType(Type.PackageType packageType, S s) {
            return visitType(packageType, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitTypeVar(Type.TypeVar typeVar, S s) {
            return visitType(typeVar, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitUndetVar(Type.UndetVar undetVar, S s) {
            return visitType(undetVar, s);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public R visitWildcardType(Type.WildcardType wildcardType, S s) {
            return visitType(wildcardType, s);
        }
    }

    public class DescriptorFilter implements Predicate<Symbol> {
        Symbol.TypeSymbol origin;

        public DescriptorFilter(Symbol.TypeSymbol typeSymbol) {
            this.origin = typeSymbol;
        }

        @Override // java.util.function.Predicate
        public boolean test(Symbol symbol) {
            return symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 8796093023232L) == 1024 && !Types.this.overridesObjectMethod(this.origin, symbol) && (Types.this.interfaceCandidates(this.origin.type, (Symbol.MethodSymbol) symbol).head.flags() & Flags.DEFAULT) == 0;
        }
    }

    public static class FunctionDescriptorLookupError extends CompilerInternalException {
        private static final long serialVersionUID = 0;
        transient JCDiagnostic diagnostic;

        public FunctionDescriptorLookupError(boolean z) {
            super(z);
            this.diagnostic = null;
        }

        public JCDiagnostic getDiagnostic() {
            return this.diagnostic;
        }

        public FunctionDescriptorLookupError setMessage(JCDiagnostic jCDiagnostic) {
            this.diagnostic = jCDiagnostic;
            return this;
        }
    }

    public class ImplementationCache {
        private WeakHashMap<Symbol.MethodSymbol, SoftReference<Map<Symbol.TypeSymbol, Entry>>> _map = new WeakHashMap<>();

        public class Entry {
            final Symbol.MethodSymbol cachedImpl;
            final boolean checkResult;
            final Predicate<Symbol> implFilter;
            final int prevMark;

            public Entry(Symbol.MethodSymbol methodSymbol, Predicate<Symbol> predicate, boolean z, int i) {
                this.cachedImpl = methodSymbol;
                this.implFilter = predicate;
                this.checkResult = z;
                this.prevMark = i;
            }

            public boolean matches(Predicate<Symbol> predicate, boolean z, int i) {
                return this.implFilter == predicate && this.checkResult == z && this.prevMark == i;
            }
        }

        public ImplementationCache() {
        }

        private Symbol.MethodSymbol implementationInternal(Symbol.MethodSymbol methodSymbol, Symbol.TypeSymbol typeSymbol, boolean z, Predicate<Symbol> predicate) {
            Type typeSupertype = typeSymbol.type;
            while (true) {
                Symbol symbol = null;
                if (!typeSupertype.hasTag(TypeTag.CLASS) && !typeSupertype.hasTag(TypeTag.TYPEVAR)) {
                    return null;
                }
                Type typeSkipTypeVars = Types.this.skipTypeVars(typeSupertype, false);
                for (Symbol symbol2 : typeSkipTypeVars.tsym.members().getSymbolsByName(methodSymbol.name, predicate)) {
                    if (symbol2 != null && symbol2.overrides(methodSymbol, typeSymbol, Types.this, z)) {
                        if ((symbol2.flags() & 1024) == 0) {
                            symbol = symbol2;
                            break;
                        }
                        symbol = symbol2;
                    }
                }
                if (symbol != null) {
                    return (Symbol.MethodSymbol) symbol;
                }
                typeSupertype = Types.this.supertype(typeSkipTypeVars);
            }
        }

        public Symbol.MethodSymbol get(Symbol.MethodSymbol methodSymbol, Symbol.TypeSymbol typeSymbol, boolean z, Predicate<Symbol> predicate) {
            SoftReference<Map<Symbol.TypeSymbol, Entry>> softReference = this._map.get(methodSymbol);
            Map<Symbol.TypeSymbol, Entry> map = softReference != null ? softReference.get() : null;
            if (map == null) {
                map = new HashMap<>();
                this._map.put(methodSymbol, new SoftReference<>(map));
            }
            Entry entry = map.get(typeSymbol);
            Scope.CompoundScope compoundScopeMembersClosure = Types.this.membersClosure(typeSymbol.type, true);
            if (entry != null && entry.matches(predicate, z, compoundScopeMembersClosure.getMark())) {
                return entry.cachedImpl;
            }
            Symbol.MethodSymbol methodSymbolImplementationInternal = implementationInternal(methodSymbol, typeSymbol, z, predicate);
            map.put(typeSymbol, new Entry(methodSymbolImplementationInternal, predicate, z, compoundScopeMembersClosure.getMark()));
            return methodSymbolImplementationInternal;
        }
    }

    public class MembersClosureCache extends SimpleVisitor<Scope.CompoundScope, Void> {
        Scope.CompoundScope nilScope;
        private Map<Symbol.TypeSymbol, Scope.CompoundScope> _map = new HashMap();
        Set<Symbol.TypeSymbol> seenTypes = new HashSet();

        public class MembersScope extends Scope.CompoundScope {
            Scope.CompoundScope scope;

            public MembersScope(Scope.CompoundScope compoundScope) {
                super(compoundScope.owner);
                this.scope = compoundScope;
            }

            public static /* synthetic */ boolean f(Predicate predicate, Symbol symbol) {
                if (symbol.owner.isInterface()) {
                    return false;
                }
                return predicate == null || predicate.test(symbol);
            }

            public Predicate<Symbol> combine(final Predicate<Symbol> predicate) {
                return new Predicate() { // from class: com.sun.tools.javac.code.c0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Types.MembersClosureCache.MembersScope.f(predicate, (Symbol) obj);
                    }
                };
            }

            @Override // com.sun.tools.javac.code.Scope.CompoundScope
            public int getMark() {
                return this.scope.getMark();
            }

            @Override // com.sun.tools.javac.code.Scope.CompoundScope, com.sun.tools.javac.code.Scope
            public Iterable<Symbol> getSymbols(Predicate<Symbol> predicate, Scope.LookupKind lookupKind) {
                return this.scope.getSymbols(combine(predicate), lookupKind);
            }

            @Override // com.sun.tools.javac.code.Scope.CompoundScope, com.sun.tools.javac.code.Scope
            public Iterable<Symbol> getSymbolsByName(Name name, Predicate<Symbol> predicate, Scope.LookupKind lookupKind) {
                return this.scope.getSymbolsByName(name, combine(predicate), lookupKind);
            }
        }

        public MembersClosureCache() {
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Scope.CompoundScope visitClassType(Type.ClassType classType, Void r6) {
            if (!this.seenTypes.add(classType.tsym)) {
                return new Scope.CompoundScope(classType.tsym);
            }
            try {
                this.seenTypes.add(classType.tsym);
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) classType.tsym;
                Scope.CompoundScope compoundScope = this._map.get(classSymbol);
                if (compoundScope == null) {
                    compoundScope = new Scope.CompoundScope(classSymbol);
                    Iterator<Type> it = Types.this.interfaces(classType).iterator();
                    while (it.hasNext()) {
                        compoundScope.prependSubScope(visit(it.next(), null));
                    }
                    compoundScope.prependSubScope(visit(Types.this.supertype(classType), null));
                    compoundScope.prependSubScope(classSymbol.members());
                    this._map.put(classSymbol, compoundScope);
                }
                return compoundScope;
            } finally {
                this.seenTypes.remove(classType.tsym);
            }
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Scope.CompoundScope visitType(Type type, Void r2) {
            if (this.nilScope == null) {
                this.nilScope = new Scope.CompoundScope(Types.this.syms.noSymbol);
            }
            return this.nilScope;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Scope.CompoundScope visitTypeVar(Type.TypeVar typeVar, Void r2) {
            return visit(typeVar.getUpperBound(), null);
        }
    }

    public class MethodFilter implements Predicate<Symbol> {
        Symbol msym;
        Type site;

        public MethodFilter(Symbol symbol, Type type) {
            this.msym = symbol;
            this.site = type;
        }

        @Override // java.util.function.Predicate
        public boolean test(Symbol symbol) {
            if (symbol.kind != Kinds.Kind.MTH || symbol.name != this.msym.name || (symbol.flags() & 4096) != 0 || !symbol.isInheritedIn(this.site.tsym, Types.this)) {
                return false;
            }
            Types types = Types.this;
            return types.overrideEquivalent(types.memberType(this.site, symbol), Types.this.memberType(this.site, this.msym));
        }
    }

    public enum MostSpecificReturnCheck {
        BASIC { // from class: com.sun.tools.javac.code.Types.MostSpecificReturnCheck.1
            @Override // com.sun.tools.javac.code.Types.MostSpecificReturnCheck
            public boolean test(Type type, Type type2, Types types) {
                List<Type> typeArguments = type.getTypeArguments();
                List<Type> typeArguments2 = type2.getTypeArguments();
                Type typeMo73getReturnType = type.mo73getReturnType();
                Type typeSubst = types.subst(type2.mo73getReturnType(), typeArguments2, typeArguments);
                if (types.isSameType(typeMo73getReturnType, typeSubst)) {
                    return true;
                }
                return (typeMo73getReturnType.isPrimitive() || typeSubst.isPrimitive() || !types.isSubtype(typeMo73getReturnType, typeSubst)) ? false : true;
            }
        },
        RTS { // from class: com.sun.tools.javac.code.Types.MostSpecificReturnCheck.2
            @Override // com.sun.tools.javac.code.Types.MostSpecificReturnCheck
            public boolean test(Type type, Type type2, Types types) {
                return types.returnTypeSubstitutable(type, type2);
            }
        };

        public abstract boolean test(Type type, Type type2, Types types);
    }

    public enum ProjectionKind {
        UPWARDS { // from class: com.sun.tools.javac.code.Types.ProjectionKind.1
            @Override // com.sun.tools.javac.code.Types.ProjectionKind
            public ProjectionKind complement() {
                return ProjectionKind.DOWNWARDS;
            }
        },
        DOWNWARDS { // from class: com.sun.tools.javac.code.Types.ProjectionKind.2
            @Override // com.sun.tools.javac.code.Types.ProjectionKind
            public ProjectionKind complement() {
                return ProjectionKind.UPWARDS;
            }
        };

        public abstract ProjectionKind complement();
    }

    public static abstract class SimpleVisitor<R, S> extends DefaultTypeVisitor<R, S> {
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public R visitCapturedType(Type.CapturedType capturedType, S s) {
            return visitTypeVar(capturedType, s);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public R visitForAll(Type.ForAll forAll, S s) {
            return visit(forAll.qtype, s);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public R visitUndetVar(Type.UndetVar undetVar, S s) {
            return visit(undetVar.qtype, s);
        }
    }

    public class Subst extends Type.StructuralTypeMapping<Void> {
        List<Type> from;
        List<Type> to;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [com.sun.tools.javac.util.List, com.sun.tools.javac.util.List<com.sun.tools.javac.code.Type>] */
        /* JADX WARN: Type inference failed for: r4v1, types: [com.sun.tools.javac.util.List, com.sun.tools.javac.util.List<com.sun.tools.javac.code.Type>] */
        /* JADX WARN: Type inference failed for: r4v2, types: [com.sun.tools.javac.util.List<A>] */
        public Subst(List<Type> list, List<Type> list2) {
            int length = list.length();
            int length2 = list2.length();
            List list3 = list;
            while (length > length2) {
                length--;
                list3 = list3.tail;
            }
            while (length < length2) {
                length2--;
                list2 = list2.tail;
            }
            this.from = list3;
            this.to = list2;
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Void r4) {
            if (!classType.isCompound()) {
                return super.visitClassType(classType, r4);
            }
            Type typeVisit = visit(Types.this.supertype(classType));
            List<Type> listVisit = visit(Types.this.interfaces(classType), r4);
            return (typeVisit == Types.this.supertype(classType) && listVisit == Types.this.interfaces(classType)) ? classType : Types.this.makeIntersectionType(listVisit.prepend(typeVisit));
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitForAll(Type.ForAll forAll, Void r5) {
            if (Type.containsAny(this.to, forAll.tvars)) {
                List<Type> listNewInstances = Types.this.newInstances(forAll.tvars);
                forAll = new Type.ForAll(listNewInstances, Types.this.subst(forAll.qtype, forAll.tvars, listNewInstances));
            }
            List<Type> listSubstBounds = Types.this.substBounds(forAll.tvars, this.from, this.to);
            Type typeVisit = visit(forAll.qtype);
            List<Type> list = forAll.tvars;
            if (listSubstBounds == list && typeVisit == forAll.qtype) {
                return forAll;
            }
            return listSubstBounds == list ? new Type.ForAll(listSubstBounds, typeVisit) { // from class: com.sun.tools.javac.code.Types.Subst.1
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            } : new Type.ForAll(listSubstBounds, Types.this.subst(typeVisit, forAll.tvars, listSubstBounds)) { // from class: com.sun.tools.javac.code.Types.Subst.2
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            };
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Void r3) {
            List list = this.from;
            List list2 = this.to;
            while (list.nonEmpty()) {
                if (typeVar.equalsIgnoreMetadata((Type) list.head)) {
                    return ((Type) list2.head).withTypeVar(typeVar);
                }
                list = list.tail;
                list2 = list2.tail;
            }
            return typeVar;
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(Type.WildcardType wildcardType, Void r2) {
            Type.WildcardType wildcardType2 = (Type.WildcardType) super.visitWildcardType(wildcardType, r2);
            if (wildcardType2 != wildcardType && wildcardType.isExtendsBound() && wildcardType2.type.isExtendsBound()) {
                wildcardType2.type = Types.this.wildUpperBound(wildcardType2.type);
            }
            return wildcardType2;
        }
    }

    public class TypePair {
        final Type t1;
        final Type t2;

        public TypePair(Type type, Type type2) {
            this.t1 = type;
            this.t2 = type2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TypePair)) {
                return false;
            }
            TypePair typePair = (TypePair) obj;
            return Types.this.exactTypeVisitor.visit(this.t1, typePair.t1).booleanValue() && Types.this.exactTypeVisitor.visit(this.t2, typePair.t2).booleanValue();
        }

        public int hashCode() {
            return (Types.this.hashCode(this.t1) * 127) + Types.this.hashCode(this.t2);
        }
    }

    public class TypeProjection extends TypeMapping<ProjectionKind> {
        Set<Type> seen = new HashSet();
        List<Type> vars;

        public class TypeArgumentProjection extends TypeMapping<ProjectionKind> {
            Type declaredBound;
            Type site;

            public TypeArgumentProjection(Type type, Type type2) {
                this.site = type;
                this.declaredBound = type2;
            }

            private Type makeWildcard(Type type, BoundKind boundKind) {
                return new Type.WildcardType(type, boundKind, Types.this.syms.boundClass) { // from class: com.sun.tools.javac.code.Types.TypeProjection.TypeArgumentProjection.1
                    @Override // com.sun.tools.javac.code.Type
                    public boolean needsStripping() {
                        return true;
                    }
                };
            }

            @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitType(Type type, ProjectionKind projectionKind) {
                BoundKind boundKind;
                ProjectionKind projectionKind2 = ProjectionKind.DOWNWARDS;
                TypeProjection typeProjection = TypeProjection.this;
                if (projectionKind == projectionKind2) {
                    return Types.this.syms.botType;
                }
                Type map = type.map(typeProjection, ProjectionKind.UPWARDS);
                Type map2 = type.map(TypeProjection.this, projectionKind2);
                List<Type> typeArguments = this.site.tsym.type.getTypeArguments();
                Types types = Types.this;
                if (!types.isSameType(map, types.syms.objectType) && (this.declaredBound.containsAny(typeArguments) || !Types.this.isSubtype(this.declaredBound, map))) {
                    boundKind = BoundKind.EXTENDS;
                } else if (map2.hasTag(TypeTag.BOT)) {
                    map = Types.this.syms.objectType;
                    boundKind = BoundKind.UNBOUND;
                } else {
                    map = map2;
                    boundKind = BoundKind.SUPER;
                }
                return makeWildcard(map, boundKind);
            }

            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitWildcardType(Type.WildcardType wildcardType, ProjectionKind projectionKind) {
                Type map = Types.this.syms.botType;
                BoundKind boundKind = wildcardType.kind;
                int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$BoundKind[boundKind.ordinal()];
                if (i == 1) {
                    map = wildcardType.type.map(TypeProjection.this, projectionKind);
                    if (map.hasTag(TypeTag.BOT)) {
                        return Types.this.syms.botType;
                    }
                } else if (i == 2) {
                    map = wildcardType.type.map(TypeProjection.this, projectionKind.complement());
                    if (map.hasTag(TypeTag.BOT)) {
                        map = Types.this.syms.objectType;
                        boundKind = BoundKind.UNBOUND;
                    }
                }
                return makeWildcard(map, boundKind);
            }
        }

        public TypeProjection(List<Type> list) {
            this.vars = list;
        }

        public static /* synthetic */ Type b(TypeProjection typeProjection, ProjectionKind projectionKind, Type type) {
            typeProjection.getClass();
            return type.map(typeProjection, projectionKind);
        }

        private Type mapTypeArgument(Type type, Type type2, Type type3, ProjectionKind projectionKind) {
            return type3.containsAny(this.vars) ? type3.map(new TypeArgumentProjection(type, type2), projectionKind) : type3;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(Type.ArrayType arrayType, ProjectionKind projectionKind) {
            Type type = arrayType.elemtype;
            Type typeVisit = visit(type, projectionKind);
            if (typeVisit == type) {
                return arrayType;
            }
            return typeVisit.hasTag(TypeTag.BOT) ? Types.this.syms.botType : new Type.ArrayType(typeVisit, arrayType.tsym, arrayType.metadata) { // from class: com.sun.tools.javac.code.Types.TypeProjection.2
                @Override // com.sun.tools.javac.code.Type
                public boolean needsStripping() {
                    return true;
                }
            };
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, final ProjectionKind projectionKind) {
            if (classType.isCompound()) {
                List<Type> listDirectSupertypes = Types.this.directSupertypes(classType);
                List<Z> map = listDirectSupertypes.map(new Function() { // from class: com.sun.tools.javac.code.d0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Types.TypeProjection.b(this.b, projectionKind, (Type) obj);
                    }
                });
                if (listDirectSupertypes != map) {
                    return Types.this.makeIntersectionType(map);
                }
            } else {
                Type enclosingType = classType.getEnclosingType();
                Type typeVisit = visit(enclosingType, projectionKind);
                List<Type> typeArguments = classType.getTypeArguments();
                List typeArguments2 = classType.tsym.type.getTypeArguments();
                ListBuffer listBuffer = new ListBuffer();
                Iterator<Type> it = typeArguments.iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Type next = it.next();
                    Type typeMapTypeArgument = mapTypeArgument(classType, ((Type) typeArguments2.head).getUpperBound(), next, projectionKind);
                    if (typeMapTypeArgument.hasTag(TypeTag.BOT)) {
                        return Types.this.syms.botType;
                    }
                    listBuffer.add(typeMapTypeArgument);
                    z |= next != typeMapTypeArgument;
                    typeArguments2 = typeArguments2.tail;
                }
                if (typeVisit != enclosingType || z) {
                    return new Type.ClassType(typeVisit, listBuffer.toList(), classType.tsym, classType.getMetadata()) { // from class: com.sun.tools.javac.code.Types.TypeProjection.1
                        @Override // com.sun.tools.javac.code.Type
                        public boolean needsStripping() {
                            return true;
                        }
                    };
                }
            }
            return classType;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, ProjectionKind projectionKind) {
            Type upperBound;
            if (!this.vars.contains(typeVar)) {
                return typeVar;
            }
            if (!this.seen.add(typeVar)) {
                ProjectionKind projectionKind2 = ProjectionKind.UPWARDS;
                Types types = Types.this;
                return projectionKind == projectionKind2 ? types.syms.objectType : types.syms.botType;
            }
            try {
                int iOrdinal = projectionKind.ordinal();
                if (iOrdinal == 0) {
                    upperBound = typeVar.getUpperBound();
                } else {
                    if (iOrdinal != 1) {
                        Assert.error();
                        return null;
                    }
                    upperBound = typeVar.getLowerBound() == null ? Types.this.syms.botType : typeVar.getLowerBound();
                }
                return upperBound.map(this, projectionKind);
            } finally {
                this.seen.remove(typeVar);
            }
        }
    }

    public static abstract class TypeRelation extends SimpleVisitor<Boolean, Type> {
    }

    public static abstract class UnaryVisitor<R> extends SimpleVisitor<R, Void> {
        public final R visit(Type type) {
            return (R) type.accept(this, (Object) null);
        }
    }

    public static class UniqueType {
        public final Type type;
        final Types types;

        public UniqueType(Type type, Types types) {
            this.type = type;
            this.types = types;
        }

        public boolean equals(Object obj) {
            return (obj instanceof UniqueType) && this.types.isSameType(this.type, ((UniqueType) obj).type);
        }

        public int hashCode() {
            return this.types.hashCode(this.type);
        }

        public String toString() {
            return this.type.toString();
        }
    }

    public Types(Context context) {
        boolean z = true;
        context.put(typesKey, this);
        this.syms = Symtab.instance(context);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        Source.instance(context);
        this.chk = Check.instance(context);
        this.enter = Enter.instance(context);
        this.capturedName = namesInstance.fromString("<captured wildcard>");
        this.messages = JavacMessages.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.noWarnings = new Warner(null);
        Options optionsInstance = Options.instance(context);
        if (!optionsInstance.isSet("dev") && !optionsInstance.isSet(Option.DOE)) {
            z = false;
        }
        this.dumpStacktraceOnError = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adaptSelf(Type type, ListBuffer<Type> listBuffer, ListBuffer<Type> listBuffer2) {
        try {
            adapt(type.tsym.type, type, listBuffer, listBuffer2);
        } catch (AdaptFailure e) {
            x01.a(e);
        }
    }

    private void appendTyparamString(Type.TypeVar typeVar, StringBuilder sb) {
        sb.append(typeVar);
        if (typeVar.getUpperBound() == null || typeVar.getUpperBound().tsym.getQualifiedName() == this.names.java_lang_Object) {
            return;
        }
        sb.append(" extends ");
        Type upperBound = typeVar.getUpperBound();
        if (!upperBound.isCompound()) {
            sb.append(upperBound);
            return;
        }
        if ((erasure(typeVar).tsym.flags() & 512) == 0) {
            sb.append(supertype(typeVar));
            for (Type type : interfaces(typeVar)) {
                sb.append('&');
                sb.append(type);
            }
            return;
        }
        boolean z = true;
        for (Type type2 : interfaces(typeVar)) {
            if (!z) {
                sb.append('&');
            }
            sb.append(type2);
            z = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type arraySuperType() {
        if (this.arraySuperType == null) {
            Symtab symtab = this.syms;
            this.arraySuperType = makeIntersectionType(List.of(symtab.serializableType, symtab.cloneableType), true);
        }
        return this.arraySuperType;
    }

    public static /* synthetic */ ClosureHolder b(Types types, boolean z, BiPredicate biPredicate) {
        types.getClass();
        return types.new ClosureHolder(z, biPredicate);
    }

    public static /* synthetic */ boolean c(Type type, Type type2) {
        return type.tsym == type2.tsym;
    }

    private void checkUnsafeVarargsConversion(Type type, Type type2, Warner warner) {
        if (!type.hasTag(TypeTag.ARRAY) || isReifiable(type)) {
            return;
        }
        Type.ArrayType arrayType = (Type.ArrayType) type;
        int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type2.getTag().ordinal()];
        boolean zIsVarargs = false;
        if (i == 1) {
            zIsVarargs = (!arrayType.isVarargs() || ((Type.ArrayType) type2).isVarargs() || isReifiable(arrayType)) ? false : true;
        } else if (i == 2) {
            zIsVarargs = arrayType.isVarargs();
        }
        if (zIsVarargs) {
            warner.warn(Lint.LintCategory.VARARGS);
        }
    }

    private Type compoundMin(List<Type> list) {
        if (list.isEmpty()) {
            return this.syms.objectType;
        }
        List<Type> listClosureMin = closureMin(list);
        if (listClosureMin.isEmpty()) {
            return null;
        }
        return listClosureMin.tail.isEmpty() ? listClosureMin.head : makeIntersectionType(listClosureMin);
    }

    public static /* synthetic */ String d(Type type) {
        return "type " + type;
    }

    private boolean eraseNotNeeded(Type type) {
        return type.isPrimitive() || this.syms.stringType.tsym == type.tsym;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type erasure(Type type, boolean z) {
        return type.isPrimitive() ? type : this.erasure.visit(type, Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Symbol.MethodSymbol firstUnimplementedAbstractImpl(Symbol.ClassSymbol classSymbol, Symbol.ClassSymbol classSymbol2) {
        Symbol.MethodSymbol methodSymbol;
        Symbol.MethodSymbol methodSymbolFirstUnimplementedAbstractImpl = null;
        if (classSymbol2 != classSymbol && (classSymbol2.flags() & 1536) == 0) {
            return null;
        }
        for (Symbol symbol : classSymbol2.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 8796093023234L) == 1024) {
                Symbol.MethodSymbol methodSymbol2 = (Symbol.MethodSymbol) symbol;
                Symbol.MethodSymbol methodSymbolImplementation = methodSymbol2.implementation(classSymbol, this, true);
                if ((methodSymbolImplementation == null || methodSymbolImplementation == methodSymbol2) && (methodSymbol = interfaceCandidates(classSymbol.type, methodSymbol2).head) != null && methodSymbol.overrides(methodSymbol2, classSymbol, this, true)) {
                    methodSymbolImplementation = methodSymbol;
                }
                if (methodSymbolImplementation == null || methodSymbolImplementation == methodSymbol2) {
                    methodSymbolFirstUnimplementedAbstractImpl = methodSymbol2;
                    break;
                }
            }
        }
        if (methodSymbolFirstUnimplementedAbstractImpl == null) {
            Type typeSupertype = supertype(classSymbol2.type);
            if (typeSupertype.hasTag(TypeTag.CLASS)) {
                methodSymbolFirstUnimplementedAbstractImpl = firstUnimplementedAbstractImpl(classSymbol, (Symbol.ClassSymbol) typeSupertype.tsym);
            }
        }
        for (List listInterfaces = interfaces(classSymbol2.type); methodSymbolFirstUnimplementedAbstractImpl == null && listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            methodSymbolFirstUnimplementedAbstractImpl = firstUnimplementedAbstractImpl(classSymbol, (Symbol.ClassSymbol) ((Type) listInterfaces.head).tsym);
        }
        return methodSymbolFirstUnimplementedAbstractImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean giveWarning(Type type, Type type2) {
        for (Type type3 : type2.isCompound() ? directSupertypes(type2) : List.of(type2)) {
            Type typeAsSub = asSub(type, type3.tsym);
            if (type3.isParameterized() && !isUnbounded(type3) && !isSubtype(type, type3) && (typeAsSub == null || !containsType(type3.allparams(), typeAsSub.allparams()))) {
                return true;
            }
        }
        return false;
    }

    private Type glbFlattened(List<Type> list, Type type) {
        List<Type> listClosureMin = closureMin(list);
        if (listClosureMin.isEmpty()) {
            return this.syms.objectType;
        }
        if (listClosureMin.tail.isEmpty()) {
            return listClosureMin.head;
        }
        List<Type> listNil = List.nil();
        List<Type> listNil2 = List.nil();
        int i = 0;
        for (Type type2 : listClosureMin) {
            if (!type2.isInterface()) {
                i++;
                Type typeCvarLowerBound = cvarLowerBound(type2);
                if (type2 != typeCvarLowerBound && !typeCvarLowerBound.hasTag(TypeTag.BOT)) {
                    listNil = listNil.append(type2);
                    listNil2 = listNil2.append(typeCvarLowerBound);
                }
            }
        }
        if (i > 1) {
            return listNil2.isEmpty() ? createErrorType(type) : glb(listClosureMin.diff(listNil).appendList(listNil2));
        }
        return makeIntersectionType(listClosureMin);
    }

    public static Types instance(Context context) {
        Types types = (Types) context.get(typesKey);
        return types == null ? new Types(context) : types;
    }

    private boolean isSubtypeUncheckedInternal(Type type, Type type2, boolean z, Warner warner) {
        Type typeAsSuper;
        TypeTag typeTag = TypeTag.ARRAY;
        if (type.hasTag(typeTag) && type2.hasTag(typeTag)) {
            return ((Type.ArrayType) type).elemtype.isPrimitive() ? isSameType(elemtype(type), elemtype(type2)) : isSubtypeUncheckedInternal(elemtype(type), elemtype(type2), false, warner);
        }
        if (isSubtype(type, type2, z)) {
            return true;
        }
        if (type.hasTag(TypeTag.TYPEVAR)) {
            return isSubtypeUncheckedInternal(type.getUpperBound(), type2, false, warner);
        }
        if (type2.isRaw() || (typeAsSuper = asSuper(type, type2.tsym)) == null || !typeAsSuper.isRaw()) {
            return false;
        }
        if (isReifiable(type2)) {
            warner.silentWarn(Lint.LintCategory.UNCHECKED);
        } else {
            warner.warn(Lint.LintCategory.UNCHECKED);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type.WildcardType makeExtendsWildcard(Type type, Type.TypeVar typeVar) {
        if (type != this.syms.objectType) {
            return new Type.WildcardType(type, BoundKind.EXTENDS, this.syms.boundClass, typeVar);
        }
        Symtab symtab = this.syms;
        return new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass, typeVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type.WildcardType makeSuperWildcard(Type type, Type.TypeVar typeVar) {
        if (!type.hasTag(TypeTag.BOT)) {
            return new Type.WildcardType(type, BoundKind.SUPER, this.syms.boundClass, typeVar);
        }
        Symtab symtab = this.syms;
        return new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass, typeVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Type merge(Type type, Type type2) {
        Type.WildcardType wildcardType;
        Type.ClassType classType = (Type.ClassType) type;
        List typeArguments = classType.getTypeArguments();
        List typeArguments2 = ((Type.ClassType) type2).getTypeArguments();
        ListBuffer listBuffer = new ListBuffer();
        List typeArguments3 = classType.tsym.type.getTypeArguments();
        while (typeArguments.nonEmpty() && typeArguments2.nonEmpty() && typeArguments3.nonEmpty()) {
            if (containsType((Type) typeArguments.head, (Type) typeArguments2.head)) {
                listBuffer.append((Type) typeArguments.head);
            } else if (containsType((Type) typeArguments2.head, (Type) typeArguments.head)) {
                listBuffer.append((Type) typeArguments2.head);
            } else {
                TypePair typePair = new TypePair(type, type2);
                if (this.mergeCache.add(typePair)) {
                    wildcardType = new Type.WildcardType(lub(wildUpperBound((Type) typeArguments.head), wildUpperBound((Type) typeArguments2.head)), BoundKind.EXTENDS, this.syms.boundClass);
                    this.mergeCache.remove(typePair);
                } else {
                    Symtab symtab = this.syms;
                    wildcardType = new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass);
                }
                listBuffer.append(wildcardType.withTypeVar((Type) typeArguments3.head));
            }
            typeArguments = typeArguments.tail;
            typeArguments2 = typeArguments2.tail;
            typeArguments3 = typeArguments3.tail;
        }
        Assert.check(typeArguments.isEmpty() && typeArguments2.isEmpty() && typeArguments3.isEmpty());
        return new Type.ClassType(classType.getEnclosingType(), listBuffer.toList(), classType.tsym);
    }

    private boolean pendingBridges(Symbol.ClassSymbol classSymbol, Symbol.TypeSymbol typeSymbol) {
        JavaFileObject javaFileObject = classSymbol.classfile;
        if (javaFileObject != null && javaFileObject.getKind() == JavaFileObject.Kind.CLASS && this.enter.getEnv(classSymbol) == null) {
            return false;
        }
        if (classSymbol == typeSymbol) {
            return true;
        }
        Iterator<Type> it = interfaces(classSymbol.type).iterator();
        while (it.hasNext()) {
            if (pendingBridges((Symbol.ClassSymbol) it.next().tsym, typeSymbol)) {
                return true;
            }
        }
        return false;
    }

    private Type relaxBound(Type type) {
        return type.hasTag(TypeTag.TYPEVAR) ? rewriteQuantifiers(skipTypeVars(type, false), true, true) : type;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Type rewriteQuantifiers(Type type, boolean z, boolean z2) {
        return new Rewriter(z, z2).visit(type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public boolean sideCast(Type type, Type type2, Warner warner) {
        boolean z;
        if ((type2.tsym.flags() & 512) == 0) {
            Assert.check((512 & type.tsym.flags()) != 0);
            z = true;
        } else {
            type2 = type;
            type = type2;
            z = false;
        }
        List listSupertypeClosure = supertypeClosure(type, erasure(type2));
        boolean zIsEmpty = listSupertypeClosure.isEmpty();
        while (listSupertypeClosure.nonEmpty()) {
            Type typeAsSuper = asSuper(type2, ((Type) listSupertypeClosure.head).tsym);
            Type type3 = (Type) listSupertypeClosure.head;
            if (disjointTypes(typeAsSuper.getTypeArguments(), type3.getTypeArguments())) {
                return false;
            }
            zIsEmpty = zIsEmpty || (!z ? !giveWarning(typeAsSuper, type3) : !giveWarning(type3, typeAsSuper));
            listSupertypeClosure = listSupertypeClosure.tail;
        }
        if (zIsEmpty) {
            if (z) {
                type = type2;
            }
            if (!isReifiable(type)) {
                warner.warn(Lint.LintCategory.UNCHECKED);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sideCastFinal(Type type, Type type2, Warner warner) {
        Type type3;
        boolean z;
        if ((type2.tsym.flags() & 512) == 0) {
            Assert.check((512 & type.tsym.flags()) != 0);
            type3 = type;
            type = type2;
            z = true;
        } else {
            type3 = type2;
            z = false;
        }
        Assert.check((type.tsym.flags() & 16) != 0);
        Type typeAsSuper = asSuper(type, type3.tsym);
        if (typeAsSuper == null || disjointTypes(typeAsSuper.getTypeArguments(), type3.getTypeArguments())) {
            return false;
        }
        if (!isReifiable(type2) && (!z ? giveWarning(typeAsSuper, type3) : giveWarning(type3, typeAsSuper))) {
            warner.warn(Lint.LintCategory.UNCHECKED);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<Type> supertypeClosure(Type type, Type type2) {
        List<Type> listNil = List.nil();
        for (List listInterfaces = interfaces(type); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            boolean zIsSubtype = isSubtype(type2, erasure((Type) listInterfaces.head));
            A a = listInterfaces.head;
            listNil = zIsSubtype ? insert(listNil, (Type) a) : union(listNil, supertypeClosure((Type) a, type2));
        }
        return listNil;
    }

    private String typaramsString(List<Type> list) {
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        boolean z = true;
        for (Type type : list) {
            if (!z) {
                sb.append(", ");
            }
            appendTyparamString((Type.TypeVar) type, sb);
            z = false;
        }
        sb.append('>');
        return sb.toString();
    }

    public void adapt(Type type, Type type2, ListBuffer<Type> listBuffer, ListBuffer<Type> listBuffer2) throws AdaptFailure {
        new Adapter(listBuffer, listBuffer2).adapt(type, type2);
    }

    public Type asEnclosingSuper(Type type, Symbol symbol) {
        while (!type.hasTag(TypeTag.NONE)) {
            Type typeAsSuper = asSuper(type, symbol);
            if (typeAsSuper != null) {
                return typeAsSuper;
            }
            type = type.tsym.owner.enclClass() != null ? type.tsym.owner.enclClass().type : Type.noType;
        }
        return null;
    }

    public Type asOuterSuper(Type type, Symbol symbol) {
        while (!type.hasTag(TypeTag.NONE)) {
            Type typeAsSuper = asSuper(type, symbol);
            if (typeAsSuper != null) {
                return typeAsSuper;
            }
            type = type.getEnclosingType();
        }
        return null;
    }

    public Type asSub(Type type, Symbol symbol) {
        return this.asSub.visit(type, symbol);
    }

    public Type asSuper(Type type, Symbol symbol) {
        Type type2 = symbol.type;
        Type type3 = this.syms.objectType;
        return type2 == type3 ? type3 : this.asSuper.visit(type, symbol);
    }

    public Symbol.ClassSymbol boxedClass(Type type) {
        Symtab symtab = this.syms;
        return symtab.enterClass(symtab.java_base, symtab.boxedName[type.getTag().ordinal()]);
    }

    public Type boxedTypeOrType(Type type) {
        return type.isPrimitive() ? boxedClass(type).type : type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v17, types: [A, com.sun.tools.javac.code.Type] */
    public Type capture(Type type) {
        Type typeCapture;
        if (!type.hasTag(TypeTag.CLASS)) {
            return type;
        }
        if (type.getEnclosingType() != Type.noType && (typeCapture = capture(type.getEnclosingType())) != type.getEnclosingType()) {
            type = subst(memberType(typeCapture, type.tsym), type.tsym.type.getTypeArguments(), type.getTypeArguments());
        }
        Type.ClassType classType = (Type.ClassType) type;
        if (classType.isRaw() || !classType.isParameterized()) {
            return classType;
        }
        List<Type> typeArguments = ((Type.ClassType) classType.asElement().asType()).getTypeArguments();
        List typeArguments2 = classType.getTypeArguments();
        List<Type> listFreshTypeVariables = freshTypeVariables(typeArguments2);
        boolean z = false;
        List list = typeArguments;
        List list2 = listFreshTypeVariables;
        while (!list.isEmpty() && !typeArguments2.isEmpty() && !list2.isEmpty()) {
            A a = list2.head;
            A a2 = typeArguments2.head;
            if (a != a2) {
                Type.WildcardType wildcardType = (Type.WildcardType) a2;
                Type upperBound = ((Type) list.head).getUpperBound();
                Type.CapturedType capturedType = (Type.CapturedType) list2.head;
                if (upperBound == null) {
                    upperBound = this.syms.objectType;
                }
                int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$BoundKind[wildcardType.kind.ordinal()];
                if (i == 1) {
                    capturedType.setUpperBound(glb(wildcardType.mo75getExtendsBound(), subst(upperBound, typeArguments, listFreshTypeVariables)));
                    capturedType.lower = this.syms.botType;
                } else if (i == 2) {
                    capturedType.setUpperBound(subst(upperBound, typeArguments, listFreshTypeVariables));
                    capturedType.lower = wildcardType.mo76getSuperBound();
                } else if (i == 3) {
                    capturedType.setUpperBound(subst(upperBound, typeArguments, listFreshTypeVariables));
                    capturedType.lower = this.syms.botType;
                }
                Type upperBound2 = capturedType.getUpperBound();
                TypeTag typeTag = TypeTag.UNDETVAR;
                Type upperBound3 = upperBound2.hasTag(typeTag) ? ((Type.UndetVar) capturedType.getUpperBound()).qtype : capturedType.getUpperBound();
                boolean zHasTag = capturedType.lower.hasTag(typeTag);
                Type type2 = capturedType.lower;
                if (zHasTag) {
                    type2 = ((Type.UndetVar) type2).qtype;
                }
                Type upperBound4 = capturedType.getUpperBound();
                TypeTag typeTag2 = TypeTag.ERROR;
                if (!upperBound4.hasTag(typeTag2) && !capturedType.lower.hasTag(typeTag2) && isSameType(upperBound3, type2)) {
                    list2.head = capturedType.getUpperBound();
                }
                z = true;
            }
            list = list.tail;
            typeArguments2 = typeArguments2.tail;
            list2 = list2.tail;
        }
        if (list.isEmpty() && typeArguments2.isEmpty() && list2.isEmpty()) {
            return z ? new Type.ClassType(classType.getEnclosingType(), listFreshTypeVariables, classType.tsym, classType.getMetadata()) : type;
        }
        return erasure(type);
    }

    public List<Type> captures(Type type) {
        CaptureScanner captureScanner = new CaptureScanner();
        HashSet hashSet = new HashSet();
        captureScanner.visit(type, hashSet);
        return List.from(hashSet);
    }

    public Type classBound(Type type) {
        return this.classBound.visit(type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> closure(Type type) {
        List<Type> listClosure = this.closureCache.get(type);
        if (listClosure == null) {
            Type typeSupertype = supertype(type);
            if (type.isCompound()) {
                listClosure = closure(supertype(type));
            } else if (typeSupertype.hasTag(TypeTag.CLASS)) {
                listClosure = insert(closure(typeSupertype), type);
            } else {
                listClosure = typeSupertype.hasTag(TypeTag.TYPEVAR) ? closure(typeSupertype).prepend(type) : List.of(type);
            }
            for (List listInterfaces = interfaces(type); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
                listClosure = union(listClosure, closure((Type) listInterfaces.head));
            }
            this.closureCache.put(type, listClosure);
        }
        return listClosure;
    }

    public Collector<Type, ClosureHolder, List<Type>> closureCollector(final boolean z, final BiPredicate<Type, Type> biPredicate) {
        return Collector.of(new Supplier() { // from class: wwe
            @Override // java.util.function.Supplier
            public final Object get() {
                return Types.b(this.b, z, biPredicate);
            }
        }, new BiConsumer() { // from class: com.sun.tools.javac.code.s
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((Types.ClosureHolder) obj).add((Type) obj2);
            }
        }, new BinaryOperator() { // from class: com.sun.tools.javac.code.t
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((Types.ClosureHolder) obj).merge((Types.ClosureHolder) obj2);
            }
        }, new Function() { // from class: com.sun.tools.javac.code.u
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Types.ClosureHolder) obj).closure();
            }
        }, new Collector.Characteristics[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> closureMin(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        HashSet hashSet = new HashSet();
        for (List list2 = list; !list2.isEmpty(); list2 = list2.tail) {
            Type type = (Type) list2.head;
            boolean zContains = hashSet.contains(type);
            boolean z = !zContains;
            if (!zContains && type.hasTag(TypeTag.TYPEVAR)) {
                Iterator it = list2.tail.iterator();
                while (it.hasNext()) {
                    if (isSubtypeNoCapture((Type) it.next(), type)) {
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                if (type.isInterface()) {
                    listBuffer2.append(type);
                } else {
                    listBuffer.append(type);
                }
                for (A a : list2.tail) {
                    if (isSubtypeNoCapture(type, a)) {
                        hashSet.add(a);
                    }
                }
            }
        }
        return listBuffer.appendList(listBuffer2).toList();
    }

    public Type constantType(PoolConstant.LoadableConstant loadableConstant) {
        int iPoolTag = loadableConstant.poolTag();
        switch (iPoolTag) {
            case 3:
                return this.syms.intType;
            case 4:
                return this.syms.floatType;
            case 5:
                return this.syms.longType;
            case 6:
                return this.syms.doubleType;
            case 7:
                return this.syms.classType;
            case 8:
                return this.syms.stringType;
            default:
                switch (iPoolTag) {
                    case 15:
                        return this.syms.methodHandleType;
                    case 16:
                        return this.syms.methodTypeType;
                    case 17:
                        return ((Symbol.DynamicVarSymbol) loadableConstant).type;
                    default:
                        throw new AssertionError("Not a loadable constant: " + loadableConstant.poolTag());
                }
        }
    }

    public boolean containedBy(Type type, Type type2) {
        int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i == 16) {
            return true;
        }
        if (i != 17) {
            return containsType(type2, type);
        }
        if (!type2.hasTag(TypeTag.WILDCARD)) {
            return isSameType(type, type2);
        }
        Type.UndetVar undetVar = (Type.UndetVar) type;
        int i2 = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$BoundKind[((Type.WildcardType) type2).kind.ordinal()];
        if (i2 == 1) {
            undetVar.addBound(Type.UndetVar.InferenceBound.UPPER, wildUpperBound(type2), this);
        } else if (i2 == 2) {
            undetVar.addBound(Type.UndetVar.InferenceBound.LOWER, wildLowerBound(type2), this);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsType(List<Type> list, List<Type> list2) {
        List list3 = list;
        List list4 = list2;
        while (list3.nonEmpty() && list4.nonEmpty() && containsType((Type) list3.head, (Type) list4.head)) {
            list3 = list3.tail;
            list4 = list4.tail;
        }
        return list3.isEmpty() && list4.isEmpty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsTypeEquivalent(List<Type> list, List<Type> list2) {
        List list3 = list;
        List list4 = list2;
        while (list3.nonEmpty() && list4.nonEmpty() && containsTypeEquivalent((Type) list3.head, (Type) list4.head)) {
            list3 = list3.tail;
            list4 = list4.tail;
        }
        return list3.isEmpty() && list4.isEmpty();
    }

    public boolean covariantReturnType(Type type, Type type2, Warner warner) {
        if (isSameType(type, type2)) {
            return true;
        }
        return (type.isPrimitive() || type2.isPrimitive() || !isAssignable(type, type2, warner)) ? false : true;
    }

    public Type createErrorType(Type type) {
        return new Type.ErrorType(type, this.syms.errSymbol);
    }

    public Type createMethodTypeWithParameters(Type type, List<Type> list) {
        return (Type) type.accept(this.methodWithParameters, list);
    }

    public Type createMethodTypeWithReturn(Type type, Type type2) {
        return (Type) type.accept(this.methodWithReturn, type2);
    }

    public Type createMethodTypeWithThrown(Type type, List<Type> list) {
        return (Type) type.accept(this.methodWithThrown, list);
    }

    public Type cvarLowerBound(Type type) {
        return (type.hasTag(TypeTag.TYPEVAR) && ((Type.TypeVar) type).isCaptured()) ? cvarLowerBound(type.getLowerBound()) : type;
    }

    public List<Type> cvarLowerBounds(List<Type> list) {
        return list.map(this.cvarLowerBoundMapping);
    }

    public Type cvarUpperBound(Type type) {
        Type.TypeVar typeVar;
        boolean zHasTag = type.hasTag(TypeTag.TYPEVAR);
        Type type2 = type;
        if (zHasTag) {
            typeVar = (Type.TypeVar) type;
            if (typeVar.isCaptured()) {
                type2 = typeVar;
                return cvarUpperBound(typeVar.getUpperBound());
            }
        }
        type2 = typeVar;
        return type2;
    }

    public int dimensions(Type type) {
        int i = 0;
        while (type.hasTag(TypeTag.ARRAY)) {
            i++;
            type = elemtype(type);
        }
        return i;
    }

    public List<Type> directSupertypes(Type type) {
        return this.directSupertypes.visit(type);
    }

    public boolean disjointType(Type type, Type type2) {
        return this.disjointType.visit(type, type2).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean disjointTypes(List<Type> list, List<Type> list2) {
        List list3 = list;
        for (List list4 = list2; list3.tail != null && list4.tail != null; list4 = list4.tail) {
            if (disjointType((Type) list3.head, (Type) list4.head)) {
                return true;
            }
            list3 = list3.tail;
        }
        return false;
    }

    public Type elemtype(Type type) {
        int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i == 1) {
            return ((Type.ArrayType) type).elemtype;
        }
        if (i == 14) {
            return elemtype(wildUpperBound(type));
        }
        if (i == 16) {
            return type;
        }
        if (i != 18) {
            return null;
        }
        return elemtype(((Type.ForAll) type).qtype);
    }

    public Type elemtypeOrType(Type type) {
        Type typeElemtype = elemtype(type);
        return typeElemtype != null ? typeElemtype : type;
    }

    public List<Type> erasedSupertypes(Type type) {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type2 : closure(type)) {
            if (type2.hasTag(TypeTag.TYPEVAR)) {
                listBuffer.append(type2);
            } else {
                listBuffer.append(erasure(type2));
            }
        }
        return listBuffer.toList();
    }

    public List<Type> erasureRecursive(List<Type> list) {
        return this.erasure.visit(list, Boolean.TRUE);
    }

    public Symbol findDescriptorSymbol(Symbol.TypeSymbol typeSymbol) throws FunctionDescriptorLookupError {
        return this.descCache.get(typeSymbol).getSymbol();
    }

    public Type findDescriptorType(Type type) throws FunctionDescriptorLookupError {
        return this.descCache.get(type.tsym).getType(type);
    }

    public Symbol.MethodSymbol firstUnimplementedAbstract(Symbol.ClassSymbol classSymbol) {
        try {
            return firstUnimplementedAbstractImpl(classSymbol, classSymbol);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(this.enter.getEnv(classSymbol).tree.pos(), e);
            return null;
        }
    }

    public List<Type> freshTypeVariables(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type : list) {
            if (type.hasTag(TypeTag.WILDCARD)) {
                Type.WildcardType wildcardType = (Type.WildcardType) type;
                Type typeMo75getExtendsBound = wildcardType.mo75getExtendsBound();
                if (typeMo75getExtendsBound == null) {
                    typeMo75getExtendsBound = this.syms.objectType;
                }
                Type type2 = typeMo75getExtendsBound;
                Name name = this.capturedName;
                Symtab symtab = this.syms;
                listBuffer.append(new Type.CapturedType(name, symtab.noSymbol, type2, symtab.botType, wildcardType));
            } else {
                listBuffer.append(type);
            }
        }
        return listBuffer.toList();
    }

    public List<Symbol> functionalInterfaceBridges(Symbol.TypeSymbol typeSymbol) {
        Assert.check(isFunctionalInterface(typeSymbol));
        Symbol symbolFindDescriptorSymbol = findDescriptorSymbol(typeSymbol);
        Scope.CompoundScope compoundScopeMembersClosure = membersClosure(typeSymbol.type, false);
        ListBuffer listBuffer = new ListBuffer();
        for (Symbol symbol : compoundScopeMembersClosure.getSymbolsByName(symbolFindDescriptorSymbol.name, this.bridgeFilter)) {
            if (symbol != symbolFindDescriptorSymbol && symbolFindDescriptorSymbol.overrides(symbol, typeSymbol, this, false)) {
                Iterator it = listBuffer.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        listBuffer.add(symbol);
                        break;
                    }
                    Symbol symbol2 = (Symbol) it.next();
                    if (isSameType(symbol2.erasure(this), symbol.erasure(this)) || (symbol2.overrides(symbol, typeSymbol, this, false) && (pendingBridges((Symbol.ClassSymbol) typeSymbol, symbol2.enclClass()) || ((Symbol.MethodSymbol) symbol).binaryImplementation((Symbol.ClassSymbol) symbol2.owner, this) != null))) {
                        break;
                    }
                }
            }
        }
        return listBuffer.toList();
    }

    public List<Type> getBounds(Type.TypeVar typeVar) {
        if (typeVar.getUpperBound().hasTag(TypeTag.NONE)) {
            return List.nil();
        }
        if (typeVar.getUpperBound().isErroneous() || !typeVar.getUpperBound().isCompound()) {
            return List.of(typeVar.getUpperBound());
        }
        return (erasure(typeVar).tsym.flags() & 512) == 0 ? interfaces(typeVar).prepend(supertype(typeVar)) : interfaces(typeVar);
    }

    public Attribute.RetentionPolicy getRetention(Symbol.TypeSymbol typeSymbol) {
        Attribute attributeMember;
        Attribute.RetentionPolicy retentionPolicy = Attribute.RetentionPolicy.CLASS;
        Attribute.Compound compoundAttribute = typeSymbol.attribute(this.syms.retentionType.tsym);
        if (compoundAttribute != null && (attributeMember = compoundAttribute.member(this.names.value)) != null && (attributeMember instanceof Attribute.Enum)) {
            Name name = ((Attribute.Enum) attributeMember).value.name;
            Names names = this.names;
            if (name == names.SOURCE) {
                return Attribute.RetentionPolicy.SOURCE;
            }
            if (name != names.CLASS && name == names.RUNTIME) {
                return Attribute.RetentionPolicy.RUNTIME;
            }
        }
        return retentionPolicy;
    }

    public Type glb(Type type, Type type2) {
        if (type2 == null) {
            return type;
        }
        if (type.isPrimitive() || type2.isPrimitive()) {
            return this.syms.errType;
        }
        if (isSubtypeNoCapture(type, type2)) {
            return type;
        }
        return isSubtypeNoCapture(type2, type) ? type2 : glbFlattened(union(closure(type), closure(type2)), type);
    }

    public boolean hasSameArgs(Type type, Type type2, boolean z) {
        return hasSameArgs(type, type2, z ? this.hasSameArgs_strict : this.hasSameArgs_nonstrict);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean hasSameBounds(Type.ForAll forAll, Type.ForAll forAll2) {
        List list = forAll.tvars;
        List list2 = forAll2.tvars;
        while (list.nonEmpty() && list2.nonEmpty() && isSameType(((Type) list.head).getUpperBound(), subst(((Type) list2.head).getUpperBound(), forAll2.tvars, forAll.tvars))) {
            list = list.tail;
            list2 = list2.tail;
        }
        return list.isEmpty() && list2.isEmpty();
    }

    public int hashCode(Type type, boolean z) {
        return (z ? hashCodeStrictVisitor.visit(type) : hashCodeVisitor.visit(type)).intValue();
    }

    public Symbol.MethodSymbol implementation(Symbol.MethodSymbol methodSymbol, Symbol.TypeSymbol typeSymbol, boolean z, Predicate<Symbol> predicate) {
        return this.implCache.get(methodSymbol, typeSymbol, z, predicate);
    }

    public List<Type> insert(List<Type> list, Type type, BiPredicate<Type, Type> biPredicate) {
        if (list.isEmpty()) {
            return list.prepend(type);
        }
        if (biPredicate.test(type, list.head)) {
            return list;
        }
        return type.tsym.precedes(list.head.tsym, this) ? list.prepend(type) : insert(list.tail, type, biPredicate).prepend(list.head);
    }

    public List<Symbol.MethodSymbol> interfaceCandidates(Type type, Symbol.MethodSymbol methodSymbol) {
        CandidatesCache candidatesCache = this.candidatesCache;
        Objects.requireNonNull(candidatesCache);
        CandidatesCache.Entry entry = candidatesCache.new Entry(type, methodSymbol);
        List<Symbol.MethodSymbol> list = this.candidatesCache.get(entry);
        if (list != null) {
            return list;
        }
        MethodFilter methodFilter = new MethodFilter(methodSymbol, type);
        List<Symbol.MethodSymbol> listNil = List.nil();
        for (Symbol symbol : membersClosure(type, false).getSymbols(methodFilter)) {
            if (!type.tsym.isInterface() && !symbol.owner.isInterface()) {
                return List.of((Symbol.MethodSymbol) symbol);
            }
            if (!listNil.contains(symbol)) {
                listNil = listNil.prepend((Symbol.MethodSymbol) symbol);
            }
        }
        List<Symbol.MethodSymbol> listPrune = prune(listNil);
        this.candidatesCache.put(entry, listPrune);
        return listPrune;
    }

    public List<Type> interfaces(Type type) {
        return this.interfaces.visit(type);
    }

    public List<Type> intersect(List<Type> list, List<Type> list2) {
        if (list == list2) {
            return list;
        }
        if (list.isEmpty() || list2.isEmpty()) {
            return List.nil();
        }
        if (list.head.tsym.precedes(list2.head.tsym, this)) {
            return intersect(list.tail, list2);
        }
        if (list2.head.tsym.precedes(list.head.tsym, this)) {
            return intersect(list, list2.tail);
        }
        if (isSameType(list.head, list2.head)) {
            return intersect(list.tail, list2.tail).prepend(list.head);
        }
        Type type = list.head;
        if (type.tsym == list2.head.tsym) {
            TypeTag typeTag = TypeTag.CLASS;
            if (type.hasTag(typeTag) && list2.head.hasTag(typeTag)) {
                if (list.head.isParameterized() && list2.head.isParameterized()) {
                    return intersect(list.tail, list2.tail).prepend(merge(list.head, list2.head));
                }
                if (list.head.isRaw() || list2.head.isRaw()) {
                    return intersect(list.tail, list2.tail).prepend(erasure(list.head));
                }
            }
        }
        return intersect(list.tail, list2.tail);
    }

    public boolean isArray(Type type) {
        while (type.hasTag(TypeTag.WILDCARD)) {
            type = wildUpperBound(type);
        }
        return type.hasTag(TypeTag.ARRAY);
    }

    public boolean isAssignable(Type type, Type type2, Warner warner) {
        if (type.hasTag(TypeTag.ERROR)) {
            return true;
        }
        if (type.getTag().isSubRangeOf(TypeTag.INT) && type.constValue() != null) {
            int iIntValue = ((Number) type.constValue()).intValue();
            int[] iArr = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag;
            int i = iArr[type2.getTag().ordinal()];
            if (i == 2) {
                int i2 = iArr[unboxedType(type2).getTag().ordinal()];
                if (i2 == 3 || i2 == 4 || i2 == 5) {
                    return isAssignable(type, unboxedType(type2), warner);
                }
            } else if ((i == 3 || i == 4 || i == 5 || i == 6) && type2.getTag().checkRange(iIntValue)) {
                return true;
            }
        }
        return isConvertible(type, type2, warner);
    }

    public boolean isCaptureOf(Type type, Type.WildcardType wildcardType) {
        if (type.hasTag(TypeTag.TYPEVAR) && ((Type.TypeVar) type).isCaptured()) {
            return isSameWildcard(wildcardType, ((Type.CapturedType) type).wildcard);
        }
        return false;
    }

    public boolean isCastable(Type type, Type type2, Warner warner) {
        boolean zBooleanValue;
        if (type == type2) {
            return true;
        }
        if (type.isPrimitive() != type2.isPrimitive()) {
            Type typeSkipTypeVars = skipTypeVars(type, false);
            return isConvertible(typeSkipTypeVars, type2, warner) || (type2.isPrimitive() && isSubtype(boxedClass(type2).type, typeSkipTypeVars));
        }
        List<Warner> list = this.warnStack;
        if (warner != list.head) {
            try {
                this.warnStack = list.prepend(warner);
                checkUnsafeVarargsConversion(type, type2, warner);
                zBooleanValue = this.isCastable.visit(type, type2).booleanValue();
                this.warnStack = this.warnStack.tail;
            } catch (Throwable th) {
                this.warnStack = this.warnStack.tail;
                throw th;
            }
        } else {
            zBooleanValue = this.isCastable.visit(type, type2).booleanValue();
        }
        if (zBooleanValue) {
            TypeTag typeTag = TypeTag.CLASS;
            if (type.hasTag(typeTag)) {
                Kinds.Kind kind = type.tsym.kind;
                Kinds.KindSelector kindSelector = Kinds.KindSelector.TYP;
                if (kind.matches(kindSelector) && type2.hasTag(typeTag) && type2.tsym.kind.matches(kindSelector) && (type.tsym.isSealed() || type2.tsym.isSealed())) {
                    return type.isCompound() || type2.isCompound() || !new DisjointChecker().areDisjoint((Symbol.ClassSymbol) type.tsym, (Symbol.ClassSymbol) type2.tsym);
                }
            }
        }
        return zBooleanValue;
    }

    public boolean isConvertible(Type type, Type type2, Warner warner) {
        if (type.hasTag(TypeTag.ERROR)) {
            return true;
        }
        boolean zIsPrimitive = type.isPrimitive();
        if (zIsPrimitive == type2.isPrimitive()) {
            return isSubtypeUnchecked(type, type2, warner);
        }
        TypeTag typeTag = TypeTag.UNDETVAR;
        boolean zHasTag = type.hasTag(typeTag);
        boolean zHasTag2 = type2.hasTag(typeTag);
        if (zHasTag || zHasTag2) {
            return zHasTag ? isSubtype(type, boxedTypeOrType(type2)) : isSubtype(boxedTypeOrType(type), type2);
        }
        return zIsPrimitive ? isSubtype(boxedClass(type).type, type2) : isSubtype(unboxedType(type), type2);
    }

    public boolean isDerivedRaw(Type type) {
        Boolean boolValueOf = this.isDerivedRawCache.get(type);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(isDerivedRawInternal(type));
            this.isDerivedRawCache.put(type, boolValueOf);
        }
        return boolValueOf.booleanValue();
    }

    public boolean isDerivedRawInternal(Type type) {
        if (type.isErroneous()) {
            return false;
        }
        if (type.isRaw()) {
            return true;
        }
        return (supertype(type) != Type.noType && isDerivedRaw(supertype(type))) || isDerivedRaw(interfaces(type));
    }

    public boolean isDirectSuperInterface(Symbol.TypeSymbol typeSymbol, Symbol.TypeSymbol typeSymbol2) {
        Iterator<Type> it = interfaces(typeSymbol2.type).iterator();
        while (it.hasNext()) {
            if (typeSymbol == it.next().tsym) {
                return true;
            }
        }
        return false;
    }

    public boolean isFunctionalInterface(Symbol.TypeSymbol typeSymbol) {
        try {
            findDescriptorSymbol(typeSymbol);
            return true;
        } catch (FunctionDescriptorLookupError unused) {
            return false;
        }
    }

    public boolean isReifiable(Type type) {
        return this.isReifiable.visit(type).booleanValue();
    }

    public boolean isSameType(Type type, Type type2) {
        return this.isSameTypeVisitor.visit(type, type2).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isSameTypes(List<Type> list, List<Type> list2) {
        List list3 = list;
        List list4 = list2;
        while (list3.tail != null && list4.tail != null && isSameType((Type) list3.head, (Type) list4.head)) {
            list3 = list3.tail;
            list4 = list4.tail;
        }
        return list3.tail == null && list4.tail == null;
    }

    public boolean isSameWildcard(Type.WildcardType wildcardType, Type type) {
        if (!type.hasTag(TypeTag.WILDCARD)) {
            return false;
        }
        Type.WildcardType wildcardType2 = (Type.WildcardType) type;
        return wildcardType2.kind == wildcardType.kind && wildcardType2.type == wildcardType.type;
    }

    public boolean isSignaturePolymorphic(Symbol.MethodSymbol methodSymbol) {
        List<Type> listMo71getParameterTypes = methodSymbol.type.mo71getParameterTypes();
        if ((methodSymbol.flags_field & 256) == 0) {
            return false;
        }
        Symbol symbol = methodSymbol.owner;
        Symtab symtab = this.syms;
        return (symbol == symtab.methodHandleType.tsym || symbol == symtab.varHandleType.tsym) && listMo71getParameterTypes.length() == 1 && listMo71getParameterTypes.head.hasTag(TypeTag.ARRAY) && ((Type.ArrayType) listMo71getParameterTypes.head).elemtype.tsym == this.syms.objectType.tsym;
    }

    public boolean isSubSignature(Type type, Type type2) {
        return hasSameArgs(type, type2, true) || hasSameArgs(type, erasure(type2), true);
    }

    public boolean isSubtype(Type type, Type type2, boolean z) {
        Type typeCvarLowerBound;
        if (type.equalsIgnoreMetadata(type2)) {
            return true;
        }
        if (type2.isPartial()) {
            return isSuperType(type2, type);
        }
        if (type2.isCompound()) {
            Iterator<Type> it = interfaces(type2).prepend(supertype(type2)).iterator();
            while (it.hasNext()) {
                if (!isSubtype(type, it.next(), z)) {
                    return false;
                }
            }
            return true;
        }
        if (!type.hasTag(TypeTag.UNDETVAR) && !type.isCompound() && type2 != (typeCvarLowerBound = cvarLowerBound(wildLowerBound(type2))) && !typeCvarLowerBound.hasTag(TypeTag.BOT)) {
            if (z) {
                type = capture(type);
            }
            return isSubtype(type, typeCvarLowerBound, false);
        }
        TypeRelation typeRelation = this.isSubtype;
        if (z) {
            type = capture(type);
        }
        return typeRelation.visit(type, type2).booleanValue();
    }

    public final boolean isSubtypeNoCapture(Type type, Type type2) {
        return isSubtype(type, type2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isSubtypeUnchecked(Type type, List<Type> list, Warner warner) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (!isSubtypeUnchecked(type, (Type) list2.head, warner)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isSubtypes(List<Type> list, List<Type> list2) {
        List list3 = list;
        List list4 = list2;
        while (list3.tail != null && list4.tail != null && isSubtype((Type) list3.head, (Type) list4.head)) {
            list3 = list3.tail;
            list4 = list4.tail;
        }
        return list3.tail == null && list4.tail == null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isSubtypesUnchecked(List<Type> list, List<Type> list2, Warner warner) {
        List list3 = list;
        List list4 = list2;
        while (list3.tail != null && list4.tail != null && isSubtypeUnchecked((Type) list3.head, (Type) list4.head, warner)) {
            list3 = list3.tail;
            list4 = list4.tail;
        }
        return list3.tail == null && list4.tail == null;
    }

    public boolean isSuperType(Type type, Type type2) {
        int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i != 16) {
            if (i != 17) {
                return isSubtype(type2, type);
            }
            Type.UndetVar undetVar = (Type.UndetVar) type;
            if (type != type2 && undetVar.qtype != type2 && !type2.hasTag(TypeTag.ERROR) && !type2.hasTag(TypeTag.BOT)) {
                undetVar.addBound(Type.UndetVar.InferenceBound.LOWER, type2, this);
            }
        }
        return true;
    }

    public boolean isUnbounded(Type type) {
        return this.isUnbounded.visit(type).booleanValue();
    }

    public boolean isUnconditionallyExactCombined(Type type, Type type2) {
        if (isUnconditionallyExactTypeBased(type, type2)) {
            return true;
        }
        return (type.constValue() instanceof Number) && isUnconditionallyExactValueBased(type, type2);
    }

    public boolean isUnconditionallyExactTypeBased(Type type, Type type2) {
        if (isSameType(type, type2)) {
            return true;
        }
        if (!type2.isPrimitive()) {
            return isSubtype(boxedTypeOrType(erasure(type)), type2);
        }
        if (!type.isPrimitive() || !type.getTag().isStrictSubRangeOf(type2.getTag())) {
            return false;
        }
        if (type.hasTag(TypeTag.BYTE) && type2.hasTag(TypeTag.CHAR)) {
            return false;
        }
        if (type.hasTag(TypeTag.INT) && type2.hasTag(TypeTag.FLOAT)) {
            return false;
        }
        return (type.hasTag(TypeTag.LONG) && (type2.hasTag(TypeTag.DOUBLE) || type2.hasTag(TypeTag.FLOAT))) ? false : true;
    }

    public boolean isUnconditionallyExactValueBased(Type type, Type type2) {
        Object objConstValue = type.constValue();
        if (!(objConstValue instanceof Number)) {
            return false;
        }
        Number number = (Number) objConstValue;
        if (!type2.getTag().isNumeric()) {
            return false;
        }
        int[] iArr = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag;
        switch (iArr[type.getTag().ordinal()]) {
            case 3:
                if (iArr[type2.getTag().ordinal()] != 4) {
                    return true;
                }
                return ExactConversionsSupport.isIntToCharExact(number.intValue());
            case 4:
                int i = iArr[type2.getTag().ordinal()];
                if (i == 3) {
                    return ExactConversionsSupport.isIntToByteExact(number.intValue());
                }
                if (i != 5) {
                    return true;
                }
                return ExactConversionsSupport.isIntToShortExact(number.intValue());
            case 5:
                int i2 = iArr[type2.getTag().ordinal()];
                if (i2 == 3) {
                    return ExactConversionsSupport.isIntToByteExact(number.intValue());
                }
                if (i2 != 4) {
                    return true;
                }
                return ExactConversionsSupport.isIntToCharExact(number.intValue());
            case 6:
                int i3 = iArr[type2.getTag().ordinal()];
                if (i3 == 3) {
                    return ExactConversionsSupport.isIntToByteExact(number.intValue());
                }
                if (i3 == 4) {
                    return ExactConversionsSupport.isIntToCharExact(number.intValue());
                }
                if (i3 == 5) {
                    return ExactConversionsSupport.isIntToShortExact(number.intValue());
                }
                if (i3 != 8) {
                    return true;
                }
                return ExactConversionsSupport.isIntToFloatExact(number.intValue());
            case 7:
                switch (iArr[type2.getTag().ordinal()]) {
                    case 3:
                        return ExactConversionsSupport.isLongToByteExact(number.longValue());
                    case 4:
                        return ExactConversionsSupport.isLongToCharExact(number.longValue());
                    case 5:
                        return ExactConversionsSupport.isLongToShortExact(number.longValue());
                    case 6:
                        return ExactConversionsSupport.isLongToIntExact(number.longValue());
                    case 7:
                    default:
                        return true;
                    case 8:
                        return ExactConversionsSupport.isLongToFloatExact(number.longValue());
                    case 9:
                        return ExactConversionsSupport.isLongToDoubleExact(number.longValue());
                }
            case 8:
                int i4 = iArr[type2.getTag().ordinal()];
                if (i4 == 3) {
                    return ExactConversionsSupport.isFloatToByteExact(number.floatValue());
                }
                if (i4 == 4) {
                    return ExactConversionsSupport.isFloatToCharExact(number.floatValue());
                }
                if (i4 == 5) {
                    return ExactConversionsSupport.isFloatToShortExact(number.floatValue());
                }
                if (i4 == 6) {
                    return ExactConversionsSupport.isFloatToIntExact(number.floatValue());
                }
                if (i4 != 7) {
                    return true;
                }
                return ExactConversionsSupport.isFloatToLongExact(number.floatValue());
            case 9:
                switch (iArr[type2.getTag().ordinal()]) {
                    case 3:
                        return ExactConversionsSupport.isDoubleToByteExact(number.doubleValue());
                    case 4:
                        return ExactConversionsSupport.isDoubleToCharExact(number.doubleValue());
                    case 5:
                        return ExactConversionsSupport.isDoubleToShortExact(number.doubleValue());
                    case 6:
                        return ExactConversionsSupport.isDoubleToIntExact(number.doubleValue());
                    case 7:
                        return ExactConversionsSupport.isDoubleToLongExact(number.doubleValue());
                    case 8:
                        return ExactConversionsSupport.isDoubleToFloatExact(number.doubleValue());
                    default:
                        return true;
                }
            default:
                return true;
        }
    }

    public Type lub(Type... typeArr) {
        int[] iArr = new int[typeArr.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= typeArr.length) {
                if (i2 == 0) {
                    return this.syms.botType;
                }
                if (i2 == 1) {
                    Type[] typeArr2 = new Type[typeArr.length];
                    for (int i3 = 0; i3 < typeArr.length; i3++) {
                        Type typeApply = this.elemTypeFun.apply(typeArr[i3]);
                        typeArr2[i3] = typeApply;
                        if (typeApply.isPrimitive()) {
                            Type type = typeArr[0];
                            for (int i4 = 1; i4 < typeArr.length; i4++) {
                                if (!isSameType(type, typeArr[i4])) {
                                    return arraySuperType();
                                }
                            }
                            return type;
                        }
                    }
                    return new Type.ArrayType(lub(typeArr2), this.syms.arrayClass);
                }
                if (i2 != 2) {
                    List<Type> listOf = List.of(arraySuperType());
                    for (int i5 = 0; i5 < typeArr.length; i5++) {
                        if (iArr[i5] != 1) {
                            listOf = listOf.prepend(typeArr[i5]);
                        }
                    }
                    return lub(listOf);
                }
                int i6 = 0;
                for (Type type2 : typeArr) {
                    if (type2.hasTag(TypeTag.CLASS) || type2.hasTag(TypeTag.TYPEVAR)) {
                        break;
                    }
                    i6++;
                }
                Assert.check(i6 < typeArr.length);
                List<Type> listErasedSupertypes = erasedSupertypes(typeArr[i6]);
                int i7 = i6 + 1;
                for (int i8 = i7; i8 < typeArr.length; i8++) {
                    Type type3 = typeArr[i8];
                    if (type3.hasTag(TypeTag.CLASS) || type3.hasTag(TypeTag.TYPEVAR)) {
                        listErasedSupertypes = intersect(listErasedSupertypes, erasedSupertypes(type3));
                    }
                }
                List<Type> listClosureMin = closureMin(listErasedSupertypes);
                List<Type> listNil = List.nil();
                for (Type type4 : listClosureMin) {
                    List<Type> listOf2 = List.of(asSuper(typeArr[i6], type4.tsym));
                    for (int i9 = i7; i9 < typeArr.length; i9++) {
                        Type typeAsSuper = asSuper(typeArr[i9], type4.tsym);
                        listOf2 = intersect(listOf2, typeAsSuper != null ? List.of(typeAsSuper) : List.nil());
                    }
                    listNil = listNil.appendList(listOf2);
                }
                return compoundMin(listNil);
            }
            Type upperBound = typeArr[i];
            int i10 = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[upperBound.getTag().ordinal()];
            if (i10 != 1) {
                if (i10 != 2) {
                    if (i10 != 12) {
                        iArr[i] = 0;
                        if (upperBound.isPrimitive()) {
                            return this.syms.errType;
                        }
                    } else {
                        do {
                            upperBound = upperBound.getUpperBound();
                        } while (upperBound.hasTag(TypeTag.TYPEVAR));
                        if (upperBound.hasTag(TypeTag.ARRAY)) {
                            iArr[i] = 1;
                        } else {
                            iArr[i] = 2;
                        }
                    }
                    i++;
                    i2 = i2;
                } else {
                    iArr[i] = 2;
                }
                i2 = (i2 == true ? 1 : 0) | 2;
                i++;
                i2 = i2;
            } else {
                iArr[i] = 1;
            }
            i2 = (i2 == true ? 1 : 0) | 1;
            i++;
            i2 = i2;
        }
    }

    public Type.ArrayType makeArrayType(Type type) {
        if (type.hasTag(TypeTag.VOID) || type.hasTag(TypeTag.PACKAGE)) {
            Assert.error("Type t must not be a VOID or PACKAGE type, " + type.toString());
        }
        return new Type.ArrayType(type, this.syms.arrayClass);
    }

    public Symbol.ClassSymbol makeFunctionalInterfaceClass(Env<AttrContext> env, Name name, Type type, long j) {
        if (type == null || type == this.syms.unknownType) {
            return null;
        }
        Symbol symbolFindDescriptorSymbol = findDescriptorSymbol(type.tsym);
        Type typeFindDescriptorType = findDescriptorType(type);
        Symbol.ClassSymbol classSymbol = new Symbol.ClassSymbol(j, name, env.enclClass.sym.outermostClass());
        classSymbol.completer = Symbol.Completer.NULL_COMPLETER;
        classSymbol.members_field = Scope.WriteableScope.create(classSymbol);
        classSymbol.members_field.enter(new Symbol.MethodSymbol(symbolFindDescriptorSymbol.flags(), symbolFindDescriptorSymbol.name, typeFindDescriptorType, classSymbol));
        Type.ClassType classType = new Type.ClassType(Type.noType, List.nil(), classSymbol);
        classType.supertype_field = this.syms.objectType;
        classType.interfaces_field = type.isIntersection() ? directSupertypes(type) : List.of(type);
        classSymbol.type = classType;
        classSymbol.sourcefile = ((Symbol.ClassSymbol) classSymbol.owner).sourcefile;
        return classSymbol;
    }

    public Type.IntersectionClassType makeIntersectionType(List<Type> list, boolean z) {
        Assert.check(list.nonEmpty());
        Type type = list.head;
        if (z) {
            list = list.prepend(this.syms.objectType);
        }
        boolean z2 = Type.moreInfo;
        Names names = this.names;
        Symbol.ClassSymbol classSymbol = new Symbol.ClassSymbol(1090524161L, z2 ? names.fromString(list.toString()) : names.empty, null, this.syms.noSymbol);
        Type.IntersectionClassType intersectionClassType = new Type.IntersectionClassType(list, classSymbol, z);
        classSymbol.type = intersectionClassType;
        classSymbol.erasure_field = list.head.hasTag(TypeTag.TYPEVAR) ? this.syms.objectType : erasure(type);
        classSymbol.members_field = Scope.WriteableScope.create(classSymbol);
        return intersectionClassType;
    }

    public Type memberType(Type type, Symbol symbol) {
        return (symbol.flags() & 8) != 0 ? symbol.type : this.memberType.visit(type, symbol);
    }

    public Scope.CompoundScope membersClosure(final Type type, boolean z) {
        Scope.CompoundScope compoundScopeVisit = this.membersCache.visit(type, null);
        Assert.checkNonNull(compoundScopeVisit, (Supplier<String>) new Supplier() { // from class: vwe
            @Override // java.util.function.Supplier
            public final Object get() {
                return Types.d(type);
            }
        });
        if (!z) {
            return compoundScopeVisit;
        }
        MembersClosureCache membersClosureCache = this.membersCache;
        Objects.requireNonNull(membersClosureCache);
        return membersClosureCache.new MembersScope(compoundScopeVisit);
    }

    public Optional<Symbol> mergeAbstracts(List<Symbol> list, Type type, boolean z) {
        Types types = this;
        List<Type> listMo71getParameterTypes = list.head.erasure(types).mo71getParameterTypes();
        int i = 0;
        boolean z2 = false;
        for (Symbol symbol : list) {
            if ((symbol.flags() & 1024) == 0 || (z && !types.isSameTypes(listMo71getParameterTypes, symbol.erasure(types).mo71getParameterTypes()))) {
                return Optional.empty();
            }
            if (symbol.type.hasTag(TypeTag.FORALL)) {
                z2 = true;
            }
        }
        MostSpecificReturnCheck[] mostSpecificReturnCheckArrValues = MostSpecificReturnCheck.values();
        int length = mostSpecificReturnCheckArrValues.length;
        while (i < length) {
            MostSpecificReturnCheck mostSpecificReturnCheck = mostSpecificReturnCheckArrValues[i];
            for (final Symbol symbol2 : list) {
                Type typeMemberType = types.memberType(type, symbol2);
                List<Type> listMo74getThrownTypes = typeMemberType.mo74getThrownTypes();
                for (Symbol symbol3 : list) {
                    if (symbol2 != symbol3) {
                        Type typeMemberType2 = types.memberType(type, symbol3);
                        if (types.isSubSignature(typeMemberType, typeMemberType2) && mostSpecificReturnCheck.test(typeMemberType, typeMemberType2, types)) {
                            List<Type> listMo74getThrownTypes2 = typeMemberType2.mo74getThrownTypes();
                            TypeTag typeTag = TypeTag.FORALL;
                            if (!typeMemberType.hasTag(typeTag) && z2) {
                                listMo74getThrownTypes2 = types.erasure(listMo74getThrownTypes2);
                            } else if (typeMemberType.hasTag(typeTag)) {
                                Assert.check(typeMemberType2.hasTag(typeTag));
                                listMo74getThrownTypes2 = types.subst(listMo74getThrownTypes2, typeMemberType2.getTypeArguments(), typeMemberType.getTypeArguments());
                            }
                            listMo74getThrownTypes = types.chk.intersect(listMo74getThrownTypes, listMo74getThrownTypes2);
                        }
                    }
                }
                return listMo74getThrownTypes == typeMemberType.mo74getThrownTypes() ? Optional.of(symbol2) : Optional.of(new Symbol.MethodSymbol(types, symbol2.flags(), symbol2.name, types.createMethodTypeWithThrown(symbol2.type, listMo74getThrownTypes), symbol2.owner) { // from class: com.sun.tools.javac.code.Types.19
                    final /* synthetic */ Types this$0;

                    {
                        this.this$0 = types;
                    }

                    @Override // com.sun.tools.javac.code.Symbol
                    public Symbol baseSymbol() {
                        return symbol2;
                    }
                });
            }
            i++;
            types = this;
        }
        return Optional.empty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> newInstances(List<Type> list) {
        List<Type> map = list.map(newInstanceFun);
        for (List list2 = map; list2.nonEmpty(); list2 = list2.tail) {
            Type.TypeVar typeVar = (Type.TypeVar) list2.head;
            typeVar.setUpperBound(subst(typeVar.getUpperBound(), list, map));
        }
        return map;
    }

    public void newRound() {
        this.descCache._map.clear();
        this.isDerivedRawCache.clear();
        this.implCache._map.clear();
        this.membersCache._map.clear();
        this.closureCache.clear();
    }

    public boolean notSoftSubtype(Type type, Type type2) {
        boolean zIsSubtype;
        if (type == type2) {
            return false;
        }
        if (type.hasTag(TypeTag.TYPEVAR)) {
            zIsSubtype = isCastable(((Type.TypeVar) type).getUpperBound(), relaxBound(type2), this.noWarnings);
        } else {
            if (!type2.hasTag(TypeTag.WILDCARD)) {
                type2 = cvarUpperBound(type2);
            }
            zIsSubtype = isSubtype(type, relaxBound(type2));
        }
        return !zIsSubtype;
    }

    public boolean overrideEquivalent(Type type, Type type2) {
        return hasSameArgs(type, type2) || hasSameArgs(type, erasure(type2)) || hasSameArgs(erasure(type), type2);
    }

    public boolean overridesObjectMethod(Symbol.TypeSymbol typeSymbol, Symbol symbol) {
        Iterator<Symbol> it = this.syms.objectType.tsym.members().getSymbolsByName(symbol.name).iterator();
        while (it.hasNext()) {
            if (symbol.overrides(it.next(), typeSymbol, this, true)) {
                return true;
            }
        }
        return false;
    }

    public List<Symbol.MethodSymbol> prune(List<Symbol.MethodSymbol> list) {
        Symbol symbol;
        Symbol symbol2;
        ListBuffer listBuffer = new ListBuffer();
        for (Symbol.MethodSymbol methodSymbol : list) {
            Iterator<Symbol.MethodSymbol> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    listBuffer.append(methodSymbol);
                    break;
                }
                Symbol.MethodSymbol next = it.next();
                if (methodSymbol != next && (symbol = next.owner) != (symbol2 = methodSymbol.owner) && asSuper(symbol.type, symbol2) != null) {
                    break;
                }
            }
        }
        return listBuffer.toList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int rank(Type type) {
        int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i == 2) {
            Type.ClassType classType = (Type.ClassType) type;
            if (classType.rank_field < 0) {
                if (classType.tsym.getQualifiedName() == this.names.java_lang_Object) {
                    classType.rank_field = 0;
                } else {
                    int iRank = rank(supertype(classType));
                    for (List listInterfaces = interfaces(classType); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
                        if (rank((Type) listInterfaces.head) > iRank) {
                            iRank = rank((Type) listInterfaces.head);
                        }
                    }
                    classType.rank_field = iRank + 1;
                }
            }
            return classType.rank_field;
        }
        if (i != 12) {
            if (i != 15 && i != 16) {
                x1f.a();
            }
            return 0;
        }
        Type.TypeVar typeVar = (Type.TypeVar) type;
        if (typeVar.rank_field < 0) {
            int iRank2 = rank(supertype(typeVar));
            for (List listInterfaces2 = interfaces(typeVar); listInterfaces2.nonEmpty(); listInterfaces2 = listInterfaces2.tail) {
                if (rank((Type) listInterfaces2.head) > iRank2) {
                    iRank2 = rank((Type) listInterfaces2.head);
                }
            }
            typeVar.rank_field = iRank2 + 1;
        }
        return typeVar.rank_field;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type removeWildcards(Type type) {
        if (!type.getTypeArguments().stream().anyMatch(new Predicate() { // from class: twe
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Type) obj).hasTag(TypeTag.WILDCARD);
            }
        })) {
            return type;
        }
        List typeArguments = type.getTypeArguments();
        List<Type> typeArguments2 = type.tsym.type.getTypeArguments();
        ListBuffer listBuffer = new ListBuffer();
        for (Type type2 : typeArguments2) {
            Type type3 = (Type) typeArguments.head;
            Type upperBound = type2.getUpperBound();
            if (((Type) typeArguments.head).hasTag(TypeTag.WILDCARD)) {
                Type.WildcardType wildcardType = (Type.WildcardType) type3;
                if (upperBound.containsAny(typeArguments2)) {
                    listBuffer.add(wildcardType.type);
                } else {
                    int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$BoundKind[wildcardType.kind.ordinal()];
                    if (i == 1) {
                        listBuffer.add(glb(upperBound, wildcardType.type));
                    } else if (i == 2) {
                        listBuffer.add(wildcardType.type);
                    } else if (i != 3) {
                        Assert.error("Cannot get here!");
                    } else {
                        listBuffer.add(upperBound);
                    }
                }
            } else {
                listBuffer.add(type3);
            }
            typeArguments = typeArguments.tail;
        }
        return subst(type.tsym.type, typeArguments2, listBuffer.toList());
    }

    public boolean resultSubtype(Type type, Type type2, Warner warner) {
        List<Type> typeArguments = type.getTypeArguments();
        return covariantReturnType(type.mo73getReturnType(), subst(type2.mo73getReturnType(), type2.getTypeArguments(), typeArguments), warner);
    }

    public boolean returnTypeSubstitutable(Type type, Type type2, Type type3, Warner warner) {
        if (isSameType(type.mo73getReturnType(), type3)) {
            return true;
        }
        if (type.mo73getReturnType().isPrimitive() || type3.isPrimitive()) {
            return false;
        }
        if (hasSameArgs(type, type2)) {
            return covariantReturnType(type.mo73getReturnType(), type3, warner);
        }
        if (isSubtypeUnchecked(type.mo73getReturnType(), type3, warner)) {
            return true;
        }
        if (!isSubtype(type.mo73getReturnType(), erasure(type3))) {
            return false;
        }
        warner.warn(Lint.LintCategory.UNCHECKED);
        return true;
    }

    public void setBounds(Type.TypeVar typeVar, List<Type> list, boolean z) {
        typeVar.setUpperBound(list.tail.isEmpty() ? list.head : makeIntersectionType(list, z));
        typeVar.rank_field = -1;
    }

    public Type skipTypeVars(Type type, boolean z) {
        while (type.hasTag(TypeTag.TYPEVAR)) {
            type = type.getUpperBound();
        }
        return z ? capture(type) : type;
    }

    public List<Type> subst(List<Type> list, List<Type> list2, List<Type> list3) {
        return list.map(new Subst(list2, list3));
    }

    public Type.TypeVar substBound(Type.TypeVar typeVar, List<Type> list, List<Type> list2) {
        Type typeSubst = subst(typeVar.getUpperBound(), list, list2);
        if (typeSubst == typeVar.getUpperBound()) {
            return typeVar;
        }
        Type.TypeVar typeVar2 = new Type.TypeVar(typeVar.tsym, null, this.syms.botType, typeVar.getMetadata());
        typeVar2.setUpperBound(subst(typeSubst, List.of(typeVar), List.of(typeVar2)));
        return typeVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [A, com.sun.tools.javac.code.Type] */
    public List<Type> substBounds(List<Type> list, List<Type> list2, List<Type> list3) {
        if (!list.isEmpty()) {
            ListBuffer listBuffer = new ListBuffer();
            Iterator<Type> it = list.iterator();
            boolean z = false;
            while (it.hasNext()) {
                Type.TypeVar typeVar = (Type.TypeVar) it.next();
                Type typeSubst = subst(typeVar.getUpperBound(), list2, list3);
                if (typeSubst != typeVar.getUpperBound()) {
                    z = true;
                }
                listBuffer.append(typeSubst);
            }
            if (z) {
                ListBuffer listBuffer2 = new ListBuffer();
                for (Type type : list) {
                    listBuffer2.append(new Type.TypeVar(type.tsym, null, this.syms.botType, type.getMetadata()));
                }
                List<Type> list4 = listBuffer2.toList();
                for (List list5 = listBuffer.toList(); !list5.isEmpty(); list5 = list5.tail) {
                    list5.head = subst((Type) list5.head, list, list4);
                }
                List list6 = listBuffer.toList();
                Iterator it2 = listBuffer2.toList().iterator();
                while (it2.hasNext()) {
                    ((Type.TypeVar) ((Type) it2.next())).setUpperBound((Type) list6.head);
                    list6 = list6.tail;
                }
                return listBuffer2.toList();
            }
        }
        return list;
    }

    public Type supertype(Type type) {
        return this.supertype.visit(type);
    }

    @Deprecated
    public String toString(Type type) {
        if (!type.hasTag(TypeTag.FORALL)) {
            return "" + type;
        }
        Type.ForAll forAll = (Type.ForAll) type;
        return typaramsString(forAll.tvars) + forAll.qtype;
    }

    public Type unboxedType(Type type) {
        if (type.hasTag(TypeTag.ERROR)) {
            return Type.noType;
        }
        int i = 0;
        while (true) {
            Symtab symtab = this.syms;
            Name[] nameArr = symtab.boxedName;
            if (i >= nameArr.length) {
                return Type.noType;
            }
            Name name = nameArr[i];
            if (name != null && asSuper(type, symtab.enterClass(symtab.java_base, name)) != null) {
                return this.syms.typeOfTag[i];
            }
            i++;
        }
    }

    public Type unboxedTypeOrType(Type type) {
        Type typeUnboxedType = unboxedType(type);
        return typeUnboxedType.hasTag(TypeTag.NONE) ? type : typeUnboxedType;
    }

    public List<Type> union(List<Type> list, List<Type> list2, BiPredicate<Type, Type> biPredicate) {
        if (list.isEmpty()) {
            return list2;
        }
        if (list2.isEmpty()) {
            return list;
        }
        if (biPredicate.test(list.head, list2.head)) {
            return union(list.tail, list2.tail, biPredicate).prepend(list.head);
        }
        return list2.head.tsym.precedes(list.head.tsym, this) ? union(list, list2.tail, biPredicate).prepend(list2.head) : union(list.tail, list2, biPredicate).prepend(list.head);
    }

    public Type upward(Type type, List<Type> list) {
        return type.map(new TypeProjection(list), ProjectionKind.UPWARDS);
    }

    public Type wildLowerBound(Type type) {
        if (!type.hasTag(TypeTag.WILDCARD)) {
            return type;
        }
        Type.WildcardType wildcardType = (Type.WildcardType) type;
        return wildcardType.isExtendsBound() ? this.syms.botType : wildLowerBound(wildcardType.type);
    }

    public Type wildUpperBound(Type type) {
        if (!type.hasTag(TypeTag.WILDCARD)) {
            return type;
        }
        Type.WildcardType wildcardType = (Type.WildcardType) type;
        if (!wildcardType.isSuperBound()) {
            return wildUpperBound(wildcardType.type);
        }
        Type.TypeVar typeVar = wildcardType.bound;
        return typeVar == null ? this.syms.objectType : typeVar.getUpperBound();
    }

    public static class MapVisitor<S> extends DefaultTypeVisitor<Type, S> {
        public final Type visit(Type type) {
            return (Type) type.accept(this, (Object) null);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, S s) {
            return type;
        }
    }

    public class Adapter extends SimpleVisitor<Void, Type> {
        ListBuffer<Type> from;
        ListBuffer<Type> to;
        private Set<TypePair> cache = new HashSet();
        Map<Symbol, Type> mapping = new HashMap();

        public Adapter(ListBuffer<Type> listBuffer, ListBuffer<Type> listBuffer2) {
            this.from = listBuffer;
            this.to = listBuffer2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void adaptRecursive(List<Type> list, List<Type> list2) {
            int length = list.length();
            int length2 = list2.length();
            List list3 = list;
            List list4 = list2;
            if (length == length2) {
                while (list3.nonEmpty()) {
                    adaptRecursive((Type) list3.head, (Type) list4.head);
                    list3 = list3.tail;
                    list4 = list4.tail;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [A, com.sun.tools.javac.code.Type] */
        public void adapt(Type type, Type type2) throws AdaptFailure {
            visit(type, type2);
            List list = this.from.toList();
            List list2 = this.to.toList();
            while (!list.isEmpty()) {
                Type type3 = this.mapping.get(((Type) list.head).tsym);
                if (list2.head != type3) {
                    list2.head = type3;
                }
                list = list.tail;
                list2 = list2.tail;
            }
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitArrayType(Type.ArrayType arrayType, Type type) throws AdaptFailure {
            if (!type.hasTag(TypeTag.ARRAY)) {
                return null;
            }
            adaptRecursive(Types.this.elemtype(arrayType), Types.this.elemtype(type));
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitClassType(Type.ClassType classType, Type type) throws AdaptFailure {
            if (!type.hasTag(TypeTag.CLASS)) {
                return null;
            }
            adaptRecursive(classType.allparams(), type.allparams());
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitTypeVar(Type.TypeVar typeVar, Type type) throws AdaptFailure {
            Type type2 = this.mapping.get(typeVar.tsym);
            if (type2 == null) {
                this.from.append(typeVar);
                this.to.append(type);
            } else if (type2.isSuperBound() && type.isSuperBound()) {
                Types types = Types.this;
                if (!types.isSubtype(types.wildLowerBound(type2), Types.this.wildLowerBound(type))) {
                    type = type2;
                }
            } else {
                if (type2.isExtendsBound() && type.isExtendsBound()) {
                    Types types2 = Types.this;
                    if (types2.isSubtype(types2.wildUpperBound(type2), Types.this.wildUpperBound(type))) {
                    }
                } else if (!Types.this.isSameType(type2, type)) {
                    throw new AdaptFailure();
                }
                type = type2;
            }
            this.mapping.put(typeVar.tsym, type);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitWildcardType(Type.WildcardType wildcardType, Type type) throws AdaptFailure {
            if (wildcardType.isExtendsBound()) {
                adaptRecursive(Types.this.wildUpperBound(wildcardType), Types.this.wildUpperBound(type));
                return null;
            }
            if (!wildcardType.isSuperBound()) {
                return null;
            }
            adaptRecursive(Types.this.wildLowerBound(wildcardType), Types.this.wildLowerBound(type));
            return null;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Void visitType(Type type, Type type2) {
            return null;
        }

        private void adaptRecursive(Type type, Type type2) {
            TypePair typePair = Types.this.new TypePair(type, type2);
            if (this.cache.add(typePair)) {
                try {
                    visit(type, type2);
                } finally {
                    this.cache.remove(typePair);
                }
            }
        }
    }

    public class CaptureScanner extends SimpleVisitor<Void, Set<Type>> {
        public CaptureScanner() {
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitArrayType(Type.ArrayType arrayType, Set<Type> set) {
            return visit(arrayType.elemtype, set);
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitCapturedType(Type.CapturedType capturedType, Set<Type> set) {
            if (!set.add(capturedType)) {
                return null;
            }
            visit(capturedType.getUpperBound(), set);
            visit(capturedType.getLowerBound(), set);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitClassType(Type.ClassType classType, final Set<Type> set) {
            if (classType.isCompound()) {
                Types.this.directSupertypes(classType).forEach(new Consumer() { // from class: com.sun.tools.javac.code.v
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.b.visit((Type) obj, set);
                    }
                });
                return null;
            }
            classType.allparams().forEach(new Consumer() { // from class: com.sun.tools.javac.code.w
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.visit((Type) obj, set);
                }
            });
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitTypeVar(Type.TypeVar typeVar, Set<Type> set) {
            if ((typeVar.tsym.flags() & 4096) == 0 || !set.add(typeVar)) {
                return null;
            }
            visit(typeVar.getUpperBound(), set);
            return null;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Void visitType(Type type, Set<Type> set) {
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitWildcardType(Type.WildcardType wildcardType, Set<Type> set) {
            visit(wildcardType.type, set);
            return null;
        }
    }

    public class HasSameArgs extends TypeRelation {
        boolean strict;

        public HasSameArgs(boolean z) {
            this.strict = z;
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitForAll(Type.ForAll forAll, Type type) {
            boolean z = false;
            if (!type.hasTag(TypeTag.FORALL)) {
                return Boolean.valueOf(this.strict ? false : visitMethodType(forAll.asMethodType(), type).booleanValue());
            }
            Type.ForAll forAll2 = (Type.ForAll) type;
            if (Types.this.hasSameBounds(forAll, forAll2) && visit(forAll.qtype, Types.this.subst(forAll2.qtype, forAll2.tvars, forAll.tvars)).booleanValue()) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitMethodType(Type.MethodType methodType, Type type) {
            return Boolean.valueOf(type.hasTag(TypeTag.METHOD) && Types.this.containsTypeEquivalent(methodType.argtypes, type.mo71getParameterTypes()));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitErrorType(Type.ErrorType errorType, Type type) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Type type2) {
            throw new AssertionError();
        }
    }

    public static class HashCodeVisitor extends UnaryVisitor<Integer> {
        private HashCodeVisitor() {
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitArrayType(Type.ArrayType arrayType, Void r2) {
            return Integer.valueOf(visit(arrayType.elemtype).intValue() + 12);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitClassType(Type.ClassType classType, Void r3) {
            int iIntValue = (visit(classType.getEnclosingType()).intValue() * 127) + classType.tsym.flatName().hashCode();
            Iterator<Type> it = classType.getTypeArguments().iterator();
            while (it.hasNext()) {
                iIntValue = (iIntValue * 127) + visit(it.next()).intValue();
            }
            return Integer.valueOf(iIntValue);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitMethodType(Type.MethodType methodType, Void r4) {
            int iOrdinal = TypeTag.METHOD.ordinal();
            for (List list = methodType.argtypes; list.tail != null; list = list.tail) {
                iOrdinal = (iOrdinal << 5) + visit((Type) list.head).intValue();
            }
            return Integer.valueOf((iOrdinal << 5) + visit(methodType.restype).intValue());
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Integer visitType(Type type, Void r2) {
            return Integer.valueOf(type.getTag().ordinal());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitTypeVar(Type.TypeVar typeVar, Void r2) {
            return Integer.valueOf(System.identityHashCode(typeVar));
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitUndetVar(Type.UndetVar undetVar, Void r2) {
            return Integer.valueOf(System.identityHashCode(undetVar));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitWildcardType(Type.WildcardType wildcardType, Void r2) {
            int iHashCode = wildcardType.kind.hashCode();
            Type type = wildcardType.type;
            if (type != null) {
                iHashCode = (iHashCode * 127) + visit(type).intValue();
            }
            return Integer.valueOf(iHashCode);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Integer visitErrorType(Type.ErrorType errorType, Void r2) {
            return 0;
        }
    }

    public class Rewriter extends UnaryVisitor<Type> {
        boolean high;
        boolean rewriteTypeVars;
        Map<Type, Type> argMap = new HashMap();
        Set<Type> seen = new HashSet();

        public Rewriter(boolean z, boolean z2) {
            this.high = z;
            this.rewriteTypeVars = z2;
        }

        private Type rewriteAsWildcardType(Type type, Type.TypeVar typeVar, BoundKind boundKind) {
            int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$BoundKind[boundKind.ordinal()];
            if (i == 1) {
                boolean z = this.high;
                Types types = Types.this;
                return z ? types.makeExtendsWildcard(B(type), typeVar) : types.makeExtendsWildcard(types.syms.objectType, typeVar);
            }
            if (i == 2) {
                boolean z2 = this.high;
                Types types2 = Types.this;
                return z2 ? types2.makeSuperWildcard(types2.syms.botType, typeVar) : types2.makeSuperWildcard(B(type), typeVar);
            }
            if (i == 3) {
                Types types3 = Types.this;
                return types3.makeExtendsWildcard(types3.syms.objectType, typeVar);
            }
            Assert.error("Invalid bound kind " + boundKind);
            return null;
        }

        public Type B(Type type) {
            while (type.hasTag(TypeTag.WILDCARD)) {
                Type.WildcardType wildcardType = (Type.WildcardType) type;
                type = this.high ? wildcardType.mo75getExtendsBound() : wildcardType.mo76getSuperBound();
                if (type == null) {
                    boolean z = this.high;
                    Types types = Types.this;
                    type = z ? types.syms.objectType : types.syms.botType;
                }
            }
            return type;
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitCapturedType(Type.CapturedType capturedType, Void r3) {
            Type type = capturedType.wildcard.type;
            Type typeVisit = visit(type.contains(capturedType) ? Types.this.erasure(type) : visit(type));
            Type.WildcardType wildcardType = capturedType.wildcard;
            return rewriteAsWildcardType(typeVisit, wildcardType.bound, wildcardType.kind);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, Void r7) {
            ListBuffer listBuffer = new ListBuffer();
            boolean z = false;
            for (Type type : classType.allparams()) {
                Type type2 = this.argMap.get(type);
                if (type2 == null) {
                    Map<Type, Type> map = this.argMap;
                    Type typeVisit = visit(type);
                    map.put(type, typeVisit);
                    type2 = typeVisit;
                }
                if (type != type2) {
                    z = true;
                }
                listBuffer.append(type2);
            }
            if (!z) {
                return classType;
            }
            Types types = Types.this;
            Type type3 = classType.tsym.type;
            return types.subst(type3, type3.allparams(), listBuffer.toList());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitTypeVar(Type.TypeVar typeVar, Void r3) {
            boolean zAdd = this.seen.add(typeVar);
            boolean z = this.rewriteTypeVars;
            if (zAdd) {
                if (z) {
                    return rewriteAsWildcardType(typeVar.getUpperBound().contains(typeVar) ? Types.this.erasure(typeVar.getUpperBound()) : visit(typeVar.getUpperBound()), typeVar, BoundKind.EXTENDS);
                }
            } else if (z) {
                Types types = Types.this;
                return types.makeExtendsWildcard(types.syms.objectType, typeVar);
            }
            return typeVar;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(Type.WildcardType wildcardType, Void r3) {
            Type typeVisit = visit(wildcardType.type);
            return wildcardType.type == typeVisit ? wildcardType : rewriteAsWildcardType(typeVisit, wildcardType.bound, wildcardType.kind);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, Void r2) {
            return type;
        }
    }

    public abstract class TypeEqualityVisitor extends TypeRelation {
        public TypeEqualityVisitor() {
        }

        public abstract boolean sameTypeArguments(List<Type> list, List<Type> list2);

        public abstract boolean sameTypeComparator(Type type, Type type2);

        /* JADX WARN: Code duplicated, block: B:14:0x002a  */
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitArrayType(Type.ArrayType arrayType, Type type) {
            boolean z;
            if (arrayType == type) {
                return Boolean.TRUE;
            }
            if (type.isPartial()) {
                return visit(type, arrayType);
            }
            if (type.hasTag(TypeTag.ARRAY)) {
                Types types = Types.this;
                if (types.containsTypeEquivalent(arrayType.elemtype, types.elemtype(type))) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitClassType(Type.ClassType classType, Type type) {
            if (classType == type) {
                return Boolean.TRUE;
            }
            if (type.isPartial()) {
                return visit(type, classType);
            }
            boolean z = false;
            if (type.isSuperBound() && !type.isExtendsBound()) {
                if (visit(classType, Types.this.wildUpperBound(type)).booleanValue() && visit(classType, Types.this.wildLowerBound(type)).booleanValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            if (!classType.isCompound() || !type.isCompound()) {
                if (classType.tsym == type.tsym && visit(classType.getEnclosingType(), type.getEnclosingType()).booleanValue() && sameTypeArguments(classType.getTypeArguments(), type.getTypeArguments())) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
            if (!visit(Types.this.supertype(classType), Types.this.supertype(type)).booleanValue()) {
                return Boolean.FALSE;
            }
            HashMap map = new HashMap();
            for (Type type2 : Types.this.interfaces(classType)) {
                map.put(type2.tsym, type2);
            }
            for (Type type3 : Types.this.interfaces(type)) {
                if (map.containsKey(type3.tsym) && visit((Type) map.remove(type3.tsym), type3).booleanValue()) {
                }
                return Boolean.FALSE;
            }
            return Boolean.valueOf(map.isEmpty());
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitForAll(Type.ForAll forAll, Type type) {
            if (!type.hasTag(TypeTag.FORALL)) {
                return Boolean.FALSE;
            }
            Type.ForAll forAll2 = (Type.ForAll) type;
            return Boolean.valueOf(Types.this.hasSameBounds(forAll, forAll2) && visit(forAll.qtype, Types.this.subst(forAll2.qtype, forAll2.tvars, forAll.tvars)).booleanValue());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitMethodType(Type.MethodType methodType, Type type) {
            return Boolean.valueOf(Types.this.hasSameArgs(methodType, type) && visit(methodType.mo73getReturnType(), type.mo73getReturnType()).booleanValue());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitPackageType(Type.PackageType packageType, Type type) {
            return Boolean.valueOf(packageType == type);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Type type2) {
            if (type.equalsIgnoreMetadata(type2)) {
                return Boolean.TRUE;
            }
            if (type2.isPartial()) {
                return visit(type2, type);
            }
            switch (AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 13:
                case 15:
                    return Boolean.valueOf(type.hasTag(type2.getTag()));
                case 12:
                    boolean z = false;
                    if (type2.hasTag(TypeTag.TYPEVAR)) {
                        return Boolean.valueOf(type == type2);
                    }
                    if (type2.isSuperBound() && !type2.isExtendsBound() && visit(type, Types.this.wildUpperBound(type2)).booleanValue()) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                case 14:
                default:
                    pe1.a("isSameType ", type.getTag());
                    return null;
            }
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitUndetVar(Type.UndetVar undetVar, Type type) {
            if (type.hasTag(TypeTag.WILDCARD)) {
                return Boolean.FALSE;
            }
            if (undetVar == type || undetVar.qtype == type || type.hasTag(TypeTag.ERROR)) {
                return Boolean.TRUE;
            }
            undetVar.addBound(Type.UndetVar.InferenceBound.EQ, type, Types.this);
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitWildcardType(Type.WildcardType wildcardType, Type type) {
            if (!type.hasTag(TypeTag.WILDCARD)) {
                return Boolean.FALSE;
            }
            Type.WildcardType wildcardType2 = (Type.WildcardType) type;
            return Boolean.valueOf((wildcardType.kind == wildcardType2.kind || (wildcardType.isExtendsBound() && type.isExtendsBound())) && sameTypeComparator(wildcardType.type, wildcardType2.type));
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitErrorType(Type.ErrorType errorType, Type type) {
            return Boolean.TRUE;
        }
    }

    public static class TypeMapping<S> extends MapVisitor<S> implements Function<Type, Type> {
        public List<Type> visit(List<Type> list, final S s) {
            return list.map(new Function() { // from class: xwe
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.visit((Type) obj, s);
                }
            });
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitCapturedType(Type.CapturedType capturedType, S s) {
            return visitTypeVar(capturedType, s);
        }

        @Override // java.util.function.Function
        public Type apply(Type type) {
            return visit(type);
        }
    }

    public boolean isFunctionalInterface(Type type) {
        try {
            findDescriptorType(type);
            return true;
        } catch (FunctionDescriptorLookupError unused) {
            return false;
        }
    }

    public Type erasureRecursive(Type type) {
        return erasure(type, true);
    }

    public Type createErrorType(Symbol.ClassSymbol classSymbol, Type type) {
        return new Type.ErrorType(classSymbol, type);
    }

    public Type subst(Type type, List<Type> list, List<Type> list2) {
        return type.map(new Subst(list, list2));
    }

    public Type createErrorType(Name name, Symbol.TypeSymbol typeSymbol, Type type) {
        return new Type.ErrorType(name, typeSymbol, type);
    }

    public boolean hasSameArgs(Type type, Type type2) {
        return hasSameArgs(type, type2, true);
    }

    public class DescriptorCache {
        private WeakHashMap<Symbol.TypeSymbol, Entry> _map = new WeakHashMap<>();

        public class Entry {
            final FunctionDescriptor cachedDescRes;
            final int prevMark;

            public Entry(FunctionDescriptor functionDescriptor, int i) {
                this.cachedDescRes = functionDescriptor;
                this.prevMark = i;
            }

            public boolean matches(int i) {
                return this.prevMark == i;
            }
        }

        public class FunctionDescriptor {
            Symbol descSym;

            public FunctionDescriptor(Symbol symbol) {
                this.descSym = symbol;
            }

            public Symbol getSymbol() {
                return this.descSym;
            }

            public Type getType(Type type) {
                Type typeRemoveWildcards = Types.this.removeWildcards(type);
                if (typeRemoveWildcards.isIntersection()) {
                    Iterator<Type> it = ((Type.IntersectionClassType) typeRemoveWildcards).getExplicitComponents().iterator();
                    while (it.hasNext()) {
                        if (!Types.this.chk.checkValidGenericType(it.next())) {
                            DescriptorCache descriptorCache = DescriptorCache.this;
                            throw descriptorCache.failure(Types.this.diags.fragment(CompilerProperties.Fragments.NoSuitableFunctionalIntfInst(typeRemoveWildcards)));
                        }
                    }
                } else if (!Types.this.chk.checkValidGenericType(typeRemoveWildcards)) {
                    DescriptorCache descriptorCache2 = DescriptorCache.this;
                    throw descriptorCache2.failure(Types.this.diags.fragment(CompilerProperties.Fragments.NoSuitableFunctionalIntfInst(typeRemoveWildcards)));
                }
                return Types.this.memberType(typeRemoveWildcards, this.descSym);
            }
        }

        public DescriptorCache() {
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [com.sun.tools.javac.code.Types$DescriptorCache$1] */
        public static /* synthetic */ AnonymousClass1 a(DescriptorCache descriptorCache, final Symbol symbol) {
            descriptorCache.getClass();
            return new FunctionDescriptor(descriptorCache, symbol.baseSymbol()) { // from class: com.sun.tools.javac.code.Types.DescriptorCache.1
                final /* synthetic */ DescriptorCache this$1;

                {
                    this.this$1 = descriptorCache;
                }

                @Override // com.sun.tools.javac.code.Types.DescriptorCache.FunctionDescriptor
                public Type getType(Type type) {
                    return Types.this.createMethodTypeWithThrown(Types.this.memberType(type, getSymbol()), symbol.type.mo74getThrownTypes());
                }
            };
        }

        public static /* synthetic */ boolean d(DescriptorCache descriptorCache, Symbol symbol, Symbol symbol2) {
            descriptorCache.getClass();
            return symbol2.owner.isSubClass(symbol.enclClass(), Types.this);
        }

        private FunctionDescriptor mergeDescriptors(Symbol.TypeSymbol typeSymbol, List<Symbol> list) {
            return (FunctionDescriptor) Types.this.mergeAbstracts(list, typeSymbol.type, false).map(new Function() { // from class: com.sun.tools.javac.code.x
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Types.DescriptorCache.a(this.b, (Symbol) obj);
                }
            }).orElse(null);
        }

        public FunctionDescriptorLookupError failure(JCDiagnostic jCDiagnostic) {
            return new FunctionDescriptorLookupError(Types.this.dumpStacktraceOnError).setMessage(jCDiagnostic);
        }

        public FunctionDescriptor findDescriptorInternal(final Symbol.TypeSymbol typeSymbol, Scope.CompoundScope compoundScope) throws FunctionDescriptorLookupError {
            if (!typeSymbol.isInterface() || (typeSymbol.flags() & 8192) != 0 || typeSymbol.isSealed()) {
                throw failure("not.a.functional.intf", typeSymbol);
            }
            ListBuffer<Symbol> listBuffer = new ListBuffer();
            for (final Symbol symbol : compoundScope.getSymbols(Types.this.new DescriptorFilter(typeSymbol))) {
                final Type typeMemberType = Types.this.memberType(typeSymbol.type, symbol);
                if (!listBuffer.isEmpty()) {
                    if (symbol.name == ((Symbol) listBuffer.first()).name) {
                        Types types = Types.this;
                        if (types.overrideEquivalent(typeMemberType, types.memberType(typeSymbol.type, (Symbol) listBuffer.first()))) {
                            if (!listBuffer.stream().filter(new Predicate() { // from class: com.sun.tools.javac.code.y
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return Types.DescriptorCache.d(this.b, symbol, (Symbol) obj);
                                }
                            }).map(new Function() { // from class: com.sun.tools.javac.code.z
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return Types.this.memberType(typeSymbol.type, (Symbol) obj);
                                }
                            }).anyMatch(new Predicate() { // from class: com.sun.tools.javac.code.a0
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return Types.this.isSubSignature((Type) obj, typeMemberType);
                                }
                            })) {
                                listBuffer.append(symbol);
                            }
                        }
                    }
                    throw failure("not.a.functional.intf.1", typeSymbol, Types.this.diags.fragment(CompilerProperties.Fragments.IncompatibleAbstracts(Kinds.kindName(typeSymbol), typeSymbol)));
                }
                listBuffer.append(symbol);
            }
            if (listBuffer.isEmpty()) {
                throw failure("not.a.functional.intf.1", typeSymbol, Types.this.diags.fragment(CompilerProperties.Fragments.NoAbstracts(Kinds.kindName(typeSymbol), typeSymbol)));
            }
            if (listBuffer.size() == 1) {
                return new FunctionDescriptor((Symbol) listBuffer.first());
            }
            FunctionDescriptor functionDescriptorMergeDescriptors = mergeDescriptors(typeSymbol, listBuffer.toList());
            if (functionDescriptorMergeDescriptors != null) {
                return functionDescriptorMergeDescriptors;
            }
            ListBuffer listBuffer2 = new ListBuffer();
            for (Symbol symbol2 : listBuffer) {
                listBuffer2.append(Types.this.diags.fragment(symbol2.type.mo74getThrownTypes().nonEmpty() ? "descriptor.throws" : "descriptor", symbol2.name, symbol2.type.mo71getParameterTypes(), symbol2.type.mo73getReturnType(), symbol2.type.mo74getThrownTypes()));
            }
            throw failure(new JCDiagnostic.MultilineDiagnostic(Types.this.diags.fragment(CompilerProperties.Fragments.IncompatibleDescsInFunctionalIntf(Kinds.kindName(typeSymbol), typeSymbol)), listBuffer2.toList()));
        }

        public FunctionDescriptor get(Symbol.TypeSymbol typeSymbol) throws FunctionDescriptorLookupError {
            Entry entry = this._map.get(typeSymbol);
            Scope.CompoundScope compoundScopeMembersClosure = Types.this.membersClosure(typeSymbol.type, false);
            if (entry != null && entry.matches(compoundScopeMembersClosure.getMark())) {
                return entry.cachedDescRes;
            }
            FunctionDescriptor functionDescriptorFindDescriptorInternal = findDescriptorInternal(typeSymbol, compoundScopeMembersClosure);
            this._map.put(typeSymbol, new Entry(functionDescriptorFindDescriptorInternal, compoundScopeMembersClosure.getMark()));
            return functionDescriptorFindDescriptorInternal;
        }

        public FunctionDescriptorLookupError failure(String str, Object... objArr) {
            return failure(Types.this.diags.fragment(str, objArr));
        }
    }

    private boolean hasSameArgs(Type type, Type type2, TypeRelation typeRelation) {
        return typeRelation.visit(type, type2).booleanValue();
    }

    public Type erasure(Type type) {
        return eraseNotNeeded(type) ? type : erasure(type, false);
    }

    public List<Type> erasure(List<Type> list) {
        return this.erasure.visit(list, Boolean.FALSE);
    }

    public boolean isSubtypeUnchecked(Type type, Type type2, Warner warner) {
        boolean zIsSubtypeUncheckedInternal = isSubtypeUncheckedInternal(type, type2, true, warner);
        if (zIsSubtypeUncheckedInternal) {
            checkUnsafeVarargsConversion(type, type2, warner);
        }
        return zIsSubtypeUncheckedInternal;
    }

    public int hashCode(Type type) {
        return hashCode(type, false);
    }

    public void setBounds(Type.TypeVar typeVar, List<Type> list) {
        setBounds(typeVar, list, list.head.tsym.isInterface());
    }

    public boolean isSubtypeUnchecked(Type type, Type type2) {
        return isSubtypeUnchecked(type, type2, this.noWarnings);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isDerivedRaw(List<Type> list) {
        List list2 = list;
        while (list2.nonEmpty() && !isDerivedRaw((Type) list2.head)) {
            list2 = list2.tail;
        }
        return list2.nonEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean containsTypeEquivalent(Type type, Type type2) {
        if (isSameType(type, type2)) {
            return true;
        }
        return containsType(type, type2) && containsType(type2, type);
    }

    public boolean containsType(Type type, Type type2) {
        return this.containsType.visit(type, type2).booleanValue();
    }

    public String toString(Symbol symbol, Locale locale) {
        return Printer.createStandardPrinter(this.messages).visit(symbol, locale);
    }

    public String toString(Type type, Locale locale) {
        return Printer.createStandardPrinter(this.messages).visit(type, locale);
    }

    public Type glb(List<Type> list) {
        Type typeGlb = list.head;
        for (Type type : list.tail) {
            if (typeGlb.isErroneous()) {
                return typeGlb;
            }
            typeGlb = glb(typeGlb, type);
        }
        return typeGlb;
    }

    public Attribute.RetentionPolicy getRetention(Attribute.Compound compound) {
        return getRetention(compound.type.tsym);
    }

    public List<Type> insert(List<Type> list, Type type) {
        return insert(list, type, this.basicClosureSkip);
    }

    public boolean returnTypeSubstitutable(Type type, Type type2) {
        if (hasSameArgs(type, type2)) {
            return resultSubtype(type, type2, this.noWarnings);
        }
        return covariantReturnType(type.mo73getReturnType(), erasure(type2.mo73getReturnType()), this.noWarnings);
    }

    public boolean isConvertible(Type type, Type type2) {
        return isConvertible(type, type2, this.noWarnings);
    }

    public Type.IntersectionClassType makeIntersectionType(List<Type> list) {
        return makeIntersectionType(list, list.head.tsym.isInterface());
    }

    public List<Type> union(List<Type> list, List<Type> list2) {
        return union(list, list2, this.basicClosureSkip);
    }

    public boolean isAssignable(Type type, Type type2) {
        return isAssignable(type, type2, this.noWarnings);
    }

    public final boolean isSubtype(Type type, Type type2) {
        return isSubtype(type, type2, true);
    }

    public boolean isCastable(Type type, Type type2) {
        return isCastable(type, type2, this.noWarnings);
    }

    public class DisjointChecker {
        Set<Pair<Symbol.ClassSymbol, Symbol.ClassSymbol>> pairsSeen = new HashSet();

        public DisjointChecker() {
        }

        public static /* synthetic */ boolean a(DisjointChecker disjointChecker, Symbol.ClassSymbol classSymbol, Type type) {
            disjointChecker.getClass();
            return disjointChecker.areDisjoint(classSymbol, (Symbol.ClassSymbol) type.tsym);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean areDisjoint(Symbol.ClassSymbol classSymbol, Symbol.ClassSymbol classSymbol2) {
            if (!this.pairsSeen.add(new Pair<>(classSymbol, classSymbol2))) {
                return false;
            }
            if (classSymbol.isInterface() != classSymbol2.isInterface()) {
                Symbol.ClassSymbol classSymbol3 = classSymbol.isInterface() ? classSymbol : classSymbol2;
                if (classSymbol3 == classSymbol) {
                    classSymbol = classSymbol2;
                }
                Types types = Types.this;
                if (!types.isSubtype(types.erasure(classSymbol.type), Types.this.erasure(classSymbol3.type))) {
                    if (classSymbol.isFinal()) {
                        return true;
                    }
                    if (classSymbol.isSealed()) {
                        return areDisjoint(classSymbol3, classSymbol.getPermittedSubclasses());
                    }
                    if (classSymbol3.isSealed()) {
                        return areDisjoint(classSymbol, classSymbol3.getPermittedSubclasses());
                    }
                }
            } else {
                boolean zIsInterface = classSymbol.isInterface();
                Types types2 = Types.this;
                if (!zIsInterface) {
                    if (!types2.isSubtype(types2.erasure(classSymbol2.type), Types.this.erasure(classSymbol.type))) {
                        Types types3 = Types.this;
                        if (!types3.isSubtype(types3.erasure(classSymbol.type), Types.this.erasure(classSymbol2.type))) {
                            return true;
                        }
                    }
                    return false;
                }
                if (!types2.isSubtype(types2.erasure(classSymbol.type), Types.this.erasure(classSymbol2.type))) {
                    Types types4 = Types.this;
                    if (!types4.isSubtype(types4.erasure(classSymbol2.type), Types.this.erasure(classSymbol.type))) {
                        if (classSymbol.isSealed()) {
                            return areDisjoint(classSymbol2, classSymbol.getPermittedSubclasses());
                        }
                        if (classSymbol2.isSealed()) {
                            return areDisjoint(classSymbol, classSymbol2.getPermittedSubclasses());
                        }
                    }
                }
            }
            return false;
        }

        public boolean areDisjoint(final Symbol.ClassSymbol classSymbol, List<Type> list) {
            return list.stream().allMatch(new Predicate() { // from class: com.sun.tools.javac.code.b0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Types.DisjointChecker.a(this.b, classSymbol, (Type) obj);
                }
            });
        }
    }

    public abstract class SignatureGenerator {

        public class InvalidSignatureException extends CompilerInternalException {
            private static final long serialVersionUID = 0;
            private final transient Type type;

            public InvalidSignatureException(Type type, boolean z) {
                super(z);
                this.type = type;
            }

            public Type type() {
                return this.type;
            }
        }

        public SignatureGenerator() {
        }

        public abstract void append(char c);

        public abstract void append(Name name);

        public abstract void append(byte[] bArr);

        public void assembleClassSig(Type type) {
            Type.ClassType classType = (Type.ClassType) type;
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) classType.tsym;
            classReference(classSymbol);
            Type enclosingType = classType.getEnclosingType();
            if (enclosingType.allparams().nonEmpty()) {
                boolean z = classSymbol.owner.kind == Kinds.Kind.MTH || classSymbol.name == Types.this.names.empty;
                if (z) {
                    enclosingType = Types.this.erasure(enclosingType);
                }
                assembleClassSig(enclosingType);
                append(z ? '$' : '.');
                Assert.check(classSymbol.flatname.startsWith(classSymbol.owner.enclClass().flatname));
                append(z ? classSymbol.flatname.subName(classSymbol.owner.enclClass().flatname.length() + 1) : classSymbol.name);
            } else {
                append(ClassFile.externalize(classSymbol.flatname));
            }
            if (classType.getTypeArguments().nonEmpty()) {
                append('<');
                assembleSig(classType.getTypeArguments());
                append('>');
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void assembleParamsSig(List<Type> list) {
            append('<');
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                Type.TypeVar typeVar = (Type.TypeVar) list2.head;
                append(typeVar.tsym.name);
                List bounds = Types.this.getBounds(typeVar);
                if ((((Type) bounds.head).tsym.flags() & 512) != 0) {
                    append(':');
                }
                while (bounds.nonEmpty()) {
                    append(':');
                    assembleSig((Type) bounds.head);
                    bounds = bounds.tail;
                }
            }
            append('>');
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void assembleSig(Type type) {
            int i = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
            if (i == 14) {
                Type.WildcardType wildcardType = (Type.WildcardType) type;
                int i2 = AnonymousClass26.$SwitchMap$com$sun$tools$javac$code$BoundKind[wildcardType.kind.ordinal()];
                if (i2 == 1) {
                    append('+');
                    assembleSig(wildcardType.type);
                    return;
                } else if (i2 == 2) {
                    append(LocaleUtility.IETF_SEPARATOR);
                    assembleSig(wildcardType.type);
                    return;
                } else if (i2 == 3) {
                    append('*');
                    return;
                } else {
                    x01.a(wildcardType.kind);
                    return;
                }
            }
            if (i == 18) {
                Type.ForAll forAll = (Type.ForAll) type;
                assembleParamsSig(forAll.tvars);
                assembleSig(forAll.qtype);
                return;
            }
            if (i == 20) {
                Type.MethodType methodType = (Type.MethodType) type;
                append('(');
                assembleSig(methodType.argtypes);
                append(')');
                assembleSig(methodType.restype);
                if (hasTypeVar(methodType.thrown)) {
                    for (List list = methodType.thrown; list.nonEmpty(); list = list.tail) {
                        append('^');
                        assembleSig((Type) list.head);
                    }
                    return;
                }
                return;
            }
            switch (i) {
                case 1:
                    append('[');
                    assembleSig(((Type.ArrayType) type).elemtype);
                    break;
                case 2:
                    if (type.isCompound()) {
                        reportIllegalSignature(type);
                    }
                    append('L');
                    assembleClassSig(type);
                    append(';');
                    break;
                case 3:
                    append('B');
                    break;
                case 4:
                    append('C');
                    break;
                case 5:
                    append('S');
                    break;
                case 6:
                    append('I');
                    break;
                case 7:
                    append('J');
                    break;
                case 8:
                    append('F');
                    break;
                case 9:
                    append('D');
                    break;
                case 10:
                    append('Z');
                    break;
                case 11:
                    append('V');
                    break;
                case 12:
                    if (((Type.TypeVar) type).isCaptured()) {
                        reportIllegalSignature(type);
                    }
                    append('T');
                    append(type.tsym.name);
                    append(';');
                    break;
                default:
                    pe1.a("typeSig ", type.getTag());
                    break;
            }
        }

        public void classReference(Symbol.ClassSymbol classSymbol) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean hasTypeVar(List<Type> list) {
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                if (((Type) list2.head).hasTag(TypeTag.TYPEVAR)) {
                    return true;
                }
            }
            return false;
        }

        public void reportIllegalSignature(Type type) {
            throw new InvalidSignatureException(type, Types.this.dumpStacktraceOnError);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void assembleSig(List<Type> list) {
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                assembleSig((Type) list2.head);
            }
        }
    }

    public List<Type> capture(List<Type> list) {
        List listNil = List.nil();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listNil = listNil.prepend(capture(it.next()));
        }
        return listNil.reverse();
    }

    public Type lub(List<Type> list) {
        return lub((Type[]) list.toArray(new Type[list.length()]));
    }
}
