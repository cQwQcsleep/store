package com.sun.tools.javac.code;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.jvm.Code;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Pair;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Symbol extends AnnoConstruct implements PoolConstant, Element {
    public Completer completer = Completer.NULL_COMPLETER;
    public Type erasure_field = null;
    public long flags_field;
    public Kinds.Kind kind;
    protected SymbolMetadata metadata;
    public Name name;
    public Symbol owner;
    public Type type;

    /* JADX INFO: renamed from: com.sun.tools.javac.code.Symbol$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.PREINC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREDEC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTINC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTDEC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr2;
            try {
                iArr2[ElementKind.LOCAL_VARIABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.PACKAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.PARAMETER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RESOURCE_VARIABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.EXCEPTION_PARAMETER.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public static class BindingSymbol extends VarSymbol {
        public BindingSymbol(long j, Name name, Type type, Symbol symbol) {
            super(j | 576460752303685632L, name, type, symbol);
        }

        public List<BindingSymbol> aliases() {
            return List.of(this);
        }

        public boolean isAliasFor(BindingSymbol bindingSymbol) {
            return aliases().containsAll(bindingSymbol.aliases());
        }

        public boolean isPreserved() {
            return (this.flags_field & Flags.MATCH_BINDING_TO_OUTER) != 0;
        }

        public void preserveBinding() {
            this.flags_field |= Flags.MATCH_BINDING_TO_OUTER;
        }
    }

    public interface Completer {
        public static final Completer NULL_COMPLETER = new Completer() { // from class: com.sun.tools.javac.code.Symbol.Completer.1
            @Override // com.sun.tools.javac.code.Symbol.Completer
            public void complete(Symbol symbol) {
            }

            @Override // com.sun.tools.javac.code.Symbol.Completer
            public boolean isTerminal() {
                return true;
            }
        };

        void complete(Symbol symbol) throws CompletionFailure;

        default boolean isTerminal() {
            return false;
        }
    }

    public static class DynamicVarSymbol extends VarSymbol implements PoolConstant.Dynamic, PoolConstant.LoadableConstant {
        public MethodHandleSymbol bsm;
        public PoolConstant.LoadableConstant[] staticArgs;

        public DynamicVarSymbol(Name name, Symbol symbol, MethodHandleSymbol methodHandleSymbol, Type type, PoolConstant.LoadableConstant[] loadableConstantArr) {
            super(0L, name, type, symbol);
            this.bsm = methodHandleSymbol;
            this.staticArgs = loadableConstantArr;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public PoolConstant.LoadableConstant bootstrapMethod() {
            return this.bsm;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public PoolConstant dynamicType() {
            return this.type;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isDynamic() {
            return true;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public Name name() {
            return this.name;
        }

        @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 17;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public PoolConstant.LoadableConstant[] staticArgs() {
            return this.staticArgs;
        }
    }

    public enum ModuleFlags {
        OPEN(32),
        SYNTHETIC(4096),
        MANDATED(32768);

        public final int value;

        ModuleFlags(int i) {
            this.value = i;
        }

        public static int value(Set<ModuleFlags> set) {
            Iterator<ModuleFlags> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                i |= it.next().value;
            }
            return i;
        }
    }

    public enum ModuleResolutionFlags {
        DO_NOT_RESOLVE_BY_DEFAULT(1),
        WARN_DEPRECATED(2),
        WARN_DEPRECATED_REMOVAL(4),
        WARN_INCUBATING(8);

        public final int value;

        ModuleResolutionFlags(int i) {
            this.value = i;
        }

        public static int value(Set<ModuleResolutionFlags> set) {
            Iterator<ModuleResolutionFlags> it = set.iterator();
            int i = 0;
            while (it.hasNext()) {
                i |= it.next().value;
            }
            return i;
        }
    }

    public static class ModuleSymbol extends TypeSymbol implements ModuleElement {
        public JavaFileManager.Location classLocation;
        public List<Directive> directives;
        public List<Symbol> enclosedPackages;
        public List<Directive.ExportsDirective> exports;
        public final Set<ModuleFlags> flags;
        public ClassSymbol module_info;
        public List<Directive.OpensDirective> opens;
        public JavaFileManager.Location patchLocation;
        public JavaFileManager.Location patchOutputLocation;
        public List<Directive.ProvidesDirective> provides;
        public Set<ModuleSymbol> readModules;
        public List<Directive.RequiresDirective> requires;
        public final Set<ModuleResolutionFlags> resolutionFlags;
        public JavaFileManager.Location sourceLocation;
        public PackageSymbol unnamedPackage;
        public List<Directive.UsesDirective> uses;
        public Completer usesProvidesCompleter;
        public Name version;
        public Map<Name, PackageSymbol> visiblePackages;

        public ModuleSymbol(Name name, Symbol symbol) {
            super(Kinds.Kind.MDL, 0L, name, null, symbol);
            this.enclosedPackages = List.nil();
            this.usesProvidesCompleter = Completer.NULL_COMPLETER;
            this.flags = EnumSet.noneOf(ModuleFlags.class);
            this.resolutionFlags = EnumSet.noneOf(ModuleResolutionFlags.class);
            Assert.checkNonNull(name);
            this.type = new Type.ModuleType(this);
        }

        public static /* synthetic */ boolean a(Symbol symbol) {
            return symbol.kind == Kinds.Kind.TYP;
        }

        public static ModuleSymbol create(Name name, Name name2) {
            ModuleSymbol moduleSymbol = new ModuleSymbol(name, null);
            ClassSymbol classSymbol = new ClassSymbol(2251799813685248L, name2, moduleSymbol);
            Name nameFormFullName = TypeSymbol.formFullName(name2, moduleSymbol);
            classSymbol.fullname = nameFormFullName;
            classSymbol.flatname = nameFormFullName;
            classSymbol.members_field = Scope.WriteableScope.create(classSymbol);
            moduleSymbol.module_info = classSymbol;
            return moduleSymbol;
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitModule(this, p);
        }

        public void completeUsesProvides() {
            Completer completer = this.usesProvidesCompleter;
            Completer completer2 = Completer.NULL_COMPLETER;
            if (completer != completer2) {
                this.usesProvidesCompleter = completer2;
                completer.complete(this);
            }
        }

        @Override // javax.lang.model.element.ModuleElement
        public java.util.List<ModuleElement.Directive> getDirectives() {
            apiComplete();
            completeUsesProvides();
            return Collections.unmodifiableList(this.directives);
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public List<Symbol> getEnclosedElements() {
            List<Symbol> listNil = List.nil();
            for (Symbol symbol : this.enclosedPackages) {
                if (symbol.members().anyMatch(new Predicate() { // from class: nwd
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Symbol.ModuleSymbol.a((Symbol) obj);
                    }
                })) {
                    listNil = listNil.prepend(symbol);
                }
            }
            return listNil;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            return ElementKind.MODULE;
        }

        @Override // javax.lang.model.element.ModuleElement, javax.lang.model.element.QualifiedNameable
        public /* bridge */ /* synthetic */ javax.lang.model.element.Name getQualifiedName() {
            return super.getQualifiedName();
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public Name getSimpleName() {
            return Convert.shortName(this.name);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isDeprecated() {
            return hasDeprecatedAnnotation();
        }

        public boolean isNoModule() {
            return false;
        }

        @Override // javax.lang.model.element.ModuleElement
        public boolean isOpen() {
            return this.flags.contains(ModuleFlags.OPEN);
        }

        @Override // javax.lang.model.element.ModuleElement
        public boolean isUnnamed() {
            return this.name.length() == 0 && this.owner == null;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public ClassSymbol outermostClass() {
            return null;
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 19;
        }

        public void reset() {
            this.directives = null;
            this.requires = null;
            this.exports = null;
            this.provides = null;
            this.uses = null;
            this.visiblePackages = null;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            Name name = this.name;
            if (name == null) {
                return "<unknown>";
            }
            return name.length() == 0 ? "<unnamed>" : String.valueOf(this.name);
        }
    }

    public static class OperatorSymbol extends MethodSymbol {
        private int accessCode;
        public int opcode;

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'UNKNOWN' uses external variables
        	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
        	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
         */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        public static final class AccessCode {
            private static final /* synthetic */ AccessCode[] $VALUES;
            public static final AccessCode ASSIGN;
            public static final AccessCode DEREF;
            public static final AccessCode FIRSTASGOP;
            public static final AccessCode POSTDEC;
            public static final AccessCode POSTINC;
            public static final AccessCode PREDEC;
            public static final AccessCode PREINC;
            public static final AccessCode UNKNOWN;
            public static final int numberOfAccessCodes;
            public final int code;
            public final JCTree.Tag tag;

            private static /* synthetic */ AccessCode[] $values() {
                return new AccessCode[]{UNKNOWN, DEREF, ASSIGN, PREINC, PREDEC, POSTINC, POSTDEC, FIRSTASGOP};
            }

            static {
                JCTree.Tag tag = JCTree.Tag.NO_TAG;
                UNKNOWN = new AccessCode("UNKNOWN", 0, -1, tag);
                DEREF = new AccessCode("DEREF", 1, 0, tag);
                ASSIGN = new AccessCode("ASSIGN", 2, 2, JCTree.Tag.ASSIGN);
                PREINC = new AccessCode("PREINC", 3, 4, JCTree.Tag.PREINC);
                PREDEC = new AccessCode("PREDEC", 4, 6, JCTree.Tag.PREDEC);
                POSTINC = new AccessCode("POSTINC", 5, 8, JCTree.Tag.POSTINC);
                POSTDEC = new AccessCode("POSTDEC", 6, 10, JCTree.Tag.POSTDEC);
                AccessCode accessCode = new AccessCode("FIRSTASGOP", 7, 12, tag);
                FIRSTASGOP = accessCode;
                $VALUES = $values();
                numberOfAccessCodes = accessCode.code + 86;
            }

            private AccessCode(String str, int i, int i2, JCTree.Tag tag) {
                super(str, i);
                this.code = i2;
                this.tag = tag;
            }

            public static int from(JCTree.Tag tag, int i) {
                int i2;
                int i3;
                int i4 = AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[tag.ordinal()];
                if (i4 == 1) {
                    return PREINC.code;
                }
                if (i4 == 2) {
                    return PREDEC.code;
                }
                if (i4 == 3) {
                    return POSTINC.code;
                }
                if (i4 == 4) {
                    return POSTDEC.code;
                }
                if (96 <= i && i <= 131) {
                    i2 = (i - 96) * 2;
                    i3 = FIRSTASGOP.code;
                } else {
                    if (i == 256) {
                        return FIRSTASGOP.code + 72;
                    }
                    if (270 > i || i > 275) {
                        return -1;
                    }
                    i2 = (i - 233) * 2;
                    i3 = FIRSTASGOP.code;
                }
                return i2 + i3;
            }

            public static AccessCode getFromCode(int i) {
                for (AccessCode accessCode : values()) {
                    if (accessCode.code == i) {
                        return accessCode;
                    }
                }
                return UNKNOWN;
            }

            public static AccessCode valueOf(String str) {
                return (AccessCode) Enum.valueOf(AccessCode.class, str);
            }

            public static AccessCode[] values() {
                return (AccessCode[]) $VALUES.clone();
            }
        }

        public OperatorSymbol(Name name, Type type, int i, Symbol symbol) {
            super(9L, name, type, symbol);
            this.accessCode = Integer.MIN_VALUE;
            this.opcode = i;
        }

        @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitOperatorSymbol(this, p);
        }

        public int getAccessCode(JCTree.Tag tag) {
            if (this.accessCode != Integer.MIN_VALUE && !tag.isIncOrDecUnaryOp()) {
                return this.accessCode;
            }
            int iFrom = AccessCode.from(tag, this.opcode);
            this.accessCode = iFrom;
            return iFrom;
        }
    }

    public static class ParamSymbol extends VarSymbol {
        public ParamSymbol(long j, Name name, Type type, Symbol symbol) {
            super(j, name, type, symbol);
        }

        @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public Name getSimpleName() {
            Name parameterName;
            long j = this.flags_field;
            if ((j & 4503599627370496L) == 0) {
                this.flags_field = j | 4503599627370496L;
                Symbol symbol = this;
                while (symbol != null && !(symbol instanceof RootPackageSymbol)) {
                    symbol = symbol.owner;
                }
                if (symbol != null && (parameterName = ((RootPackageSymbol) symbol).missingInfoHandler.getParameterName(this)) != null) {
                    this.name = parameterName;
                }
            }
            return super.getSimpleName();
        }
    }

    public static class RootPackageSymbol extends PackageSymbol {
        public final boolean allowPrivateInvokeVirtual;
        public final MissingInfoHandler missingInfoHandler;

        public RootPackageSymbol(Name name, Symbol symbol, MissingInfoHandler missingInfoHandler, boolean z) {
            super(name, symbol);
            this.missingInfoHandler = missingInfoHandler;
            this.allowPrivateInvokeVirtual = z;
        }
    }

    public static abstract class TypeSymbol extends Symbol {
        public TypeSymbol(Kinds.Kind kind, long j, Name name, Type type, Symbol symbol) {
            super(kind, j, name, type, symbol);
        }

        public static Name formFlatName(Name name, Symbol symbol) {
            if (symbol == null || symbol.kind.matches(Kinds.KindSelector.VAL_MTH)) {
                return name;
            }
            Kinds.Kind kind = symbol.kind;
            Kinds.Kind kind2 = Kinds.Kind.TYP;
            if (kind == kind2 && symbol.type.hasTag(TypeTag.TYPEVAR)) {
                return name;
            }
            char c = symbol.kind == kind2 ? '$' : '.';
            Name nameFlatName = symbol.flatName();
            return (nameFlatName == null || nameFlatName == nameFlatName.table.names.empty) ? name : nameFlatName.append(c, name);
        }

        public static Name formFullName(Name name, Symbol symbol) {
            if (symbol == null) {
                return name;
            }
            Kinds.Kind kind = symbol.kind;
            if (kind != Kinds.Kind.ERR) {
                if (kind.matches(Kinds.KindSelector.VAL_MTH)) {
                    return name;
                }
                if (symbol.kind == Kinds.Kind.TYP && symbol.type.hasTag(TypeTag.TYPEVAR)) {
                    return name;
                }
            }
            Name qualifiedName = symbol.getQualifiedName();
            return (qualifiedName == null || qualifiedName == qualifiedName.table.names.empty) ? name : qualifiedName.append('.', name);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitTypeSymbol(this, p);
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ TypeMirror asType() {
            return super.asType();
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        public Annotate.AnnotationTypeMetadata getAnnotationTypeMetadata() {
            Assert.error("Only on ClassSymbol");
            return null;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public List<Symbol> getEnclosedElements() {
            List<Symbol> listNil = List.nil();
            if (this.kind == Kinds.Kind.TYP && this.type.hasTag(TypeTag.TYPEVAR)) {
                return listNil;
            }
            apiComplete();
            for (Symbol symbol : members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                symbol.apiComplete();
                if ((symbol.flags() & 4096) == 0 && symbol.owner == this && symbol.kind != Kinds.Kind.ERR) {
                    listNil = listNil.prepend(symbol);
                }
            }
            return listNil;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ Element getEnclosingElement() {
            return super.getEnclosingElement();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ javax.lang.model.element.Name getSimpleName() {
            return super.getSimpleName();
        }

        public boolean isAnnotationType() {
            return false;
        }

        public final boolean precedes(TypeSymbol typeSymbol, Types types) {
            if (this == typeSymbol) {
                return false;
            }
            if (this.type.hasTag(typeSymbol.type.getTag())) {
                if (this.type.hasTag(TypeTag.CLASS)) {
                    if (types.rank(typeSymbol.type) >= types.rank(this.type)) {
                        return types.rank(typeSymbol.type) == types.rank(this.type) && getQualifiedName().compareTo(typeSymbol.getQualifiedName()) < 0;
                    }
                    return true;
                }
                if (this.type.hasTag(TypeTag.TYPEVAR)) {
                    return types.isSubtype(this.type, typeSymbol.type);
                }
            }
            return this.type.hasTag(TypeTag.TYPEVAR);
        }
    }

    public interface Visitor<R, P> {
        R visitClassSymbol(ClassSymbol classSymbol, P p);

        R visitMethodSymbol(MethodSymbol methodSymbol, P p);

        R visitOperatorSymbol(OperatorSymbol operatorSymbol, P p);

        R visitPackageSymbol(PackageSymbol packageSymbol, P p);

        R visitSymbol(Symbol symbol, P p);

        R visitTypeSymbol(TypeSymbol typeSymbol, P p);

        R visitVarSymbol(VarSymbol varSymbol, P p);
    }

    public Symbol(Kinds.Kind kind, long j, Name name, Type type, Symbol symbol) {
        this.kind = kind;
        this.flags_field = j;
        this.type = type;
        this.owner = symbol;
        this.name = name;
    }

    private boolean hiddenIn(ClassSymbol classSymbol, Types types) {
        Symbol symbolHiddenInInternal = hiddenInInternal(classSymbol, types);
        Assert.check(symbolHiddenInInternal != null, "the result of hiddenInInternal() can't be null");
        return symbolHiddenInInternal != this;
    }

    private Symbol hiddenInInternal(ClassSymbol classSymbol, Types types) {
        if (classSymbol != this.owner) {
            for (Symbol symbol : classSymbol.members().getSymbolsByName(this.name)) {
                Kinds.Kind kind = symbol.kind;
                Kinds.Kind kind2 = this.kind;
                if (kind == kind2 && (kind2 != Kinds.Kind.MTH || ((symbol.flags() & 8) != 0 && types.isSubSignature(symbol.type, this.type)))) {
                    return symbol;
                }
            }
            Symbol symbol2 = null;
            for (Type type : types.interfaces(classSymbol.type).prepend(types.supertype(classSymbol.type))) {
                if (type != null && type.hasTag(TypeTag.CLASS)) {
                    Symbol symbolHiddenInInternal = hiddenInInternal((ClassSymbol) type.tsym, types);
                    if (symbolHiddenInInternal != this) {
                        if (symbolHiddenInInternal != null) {
                            symbol2 = symbolHiddenInInternal;
                        }
                    }
                }
            }
            return symbol2;
        }
        return this;
    }

    private SymbolMetadata initedMetadata() {
        if (this.metadata == null) {
            this.metadata = new SymbolMetadata(this);
        }
        return this.metadata;
    }

    public <R, P> R accept(Visitor<R, P> visitor, P p) {
        return visitor.visitSymbol(this, p);
    }

    public boolean annotationsPendingCompletion() {
        SymbolMetadata symbolMetadata = this.metadata;
        if (symbolMetadata == null) {
            return false;
        }
        return symbolMetadata.pendingCompletion();
    }

    public void apiComplete() throws CompletionFailure {
        try {
            complete();
        } catch (CompletionFailure e) {
            e.dcfh.handleAPICompletionFailure(e);
        }
    }

    public void appendAttributes(List<Attribute.Compound> list) {
        if (list.nonEmpty()) {
            initedMetadata().append(list);
        }
    }

    public void appendClassInitTypeAttributes(List<Attribute.TypeCompound> list) {
        if (list.nonEmpty()) {
            initedMetadata().appendClassInitTypeAttributes(list);
        }
    }

    public void appendInitTypeAttributes(List<Attribute.TypeCompound> list) {
        if (list.nonEmpty()) {
            initedMetadata().appendInitTypeAttributes(list);
        }
    }

    public void appendUniqueTypeAttributes(List<Attribute.TypeCompound> list) {
        if (list.nonEmpty()) {
            initedMetadata().appendUniqueTypes(list);
        }
    }

    public Symbol asMemberOf(Type type, Types types) {
        throw new AssertionError();
    }

    public Attribute.Compound attribute(Symbol symbol) {
        for (Attribute.Compound compound : getRawAttributes()) {
            if (compound.type.tsym == symbol) {
                return compound;
            }
        }
        return null;
    }

    public Symbol baseSymbol() {
        return this;
    }

    public Symbol clone(Symbol symbol) {
        throw new AssertionError();
    }

    public void complete() throws CompletionFailure {
        Completer completer = this.completer;
        Completer completer2 = Completer.NULL_COMPLETER;
        if (completer != completer2) {
            this.completer = completer2;
            completer.complete(this);
        }
    }

    public ClassSymbol enclClass() {
        while (this != null && (!this.kind.matches(Kinds.KindSelector.TYP) || !this.type.hasTag(TypeTag.CLASS))) {
            this = this.owner;
        }
        return (ClassSymbol) this;
    }

    public Type erasure(Types types) {
        if (this.erasure_field == null) {
            this.erasure_field = types.erasure(this.type);
        }
        return this.erasure_field;
    }

    public boolean exists() {
        return true;
    }

    public Type externalType(Types types) {
        Type typeErasure = erasure(types);
        Name name = this.name;
        if (name != name.table.names.init || !this.owner.hasOuterInstance()) {
            return typeErasure;
        }
        return new Type.MethodType(typeErasure.mo71getParameterTypes().prepend(this.owner.innermostAccessibleEnclosingClass().erasure(types)), typeErasure.mo73getReturnType(), typeErasure.mo74getThrownTypes(), typeErasure.tsym);
    }

    public long flags() {
        return this.flags_field;
    }

    public Name flatName() {
        return getQualifiedName();
    }

    @Override // com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
    public List<Attribute.Compound> getAnnotationMirrors() {
        apiComplete();
        return getRawAttributes();
    }

    public List<Attribute.TypeCompound> getClassInitTypeAttributes() {
        SymbolMetadata symbolMetadata = this.metadata;
        return symbolMetadata == null ? List.nil() : symbolMetadata.getClassInitTypeAttributes();
    }

    public List<Attribute.Compound> getDeclarationAttributes() {
        SymbolMetadata symbolMetadata = this.metadata;
        return symbolMetadata == null ? List.nil() : symbolMetadata.getDeclarationAttributes();
    }

    @Override // javax.lang.model.element.Element
    public java.util.List<Symbol> getEnclosedElements() {
        return List.nil();
    }

    public List<Attribute.TypeCompound> getInitTypeAttributes() {
        SymbolMetadata symbolMetadata = this.metadata;
        return symbolMetadata == null ? List.nil() : symbolMetadata.getInitTypeAttributes();
    }

    @Override // javax.lang.model.element.Element
    public ElementKind getKind() {
        return ElementKind.OTHER;
    }

    public SymbolMetadata getMetadata() {
        return this.metadata;
    }

    @Override // javax.lang.model.element.Element
    public Set<Modifier> getModifiers() {
        apiComplete();
        return Flags.asModifierSet(flags());
    }

    public Name getQualifiedName() {
        return this.name;
    }

    public List<Attribute.Compound> getRawAttributes() {
        SymbolMetadata symbolMetadata = this.metadata;
        return symbolMetadata == null ? List.nil() : symbolMetadata.getDeclarationAttributes();
    }

    public List<Attribute.TypeCompound> getRawTypeAttributes() {
        SymbolMetadata symbolMetadata = this.metadata;
        return symbolMetadata == null ? List.nil() : symbolMetadata.getTypeAttributes();
    }

    public List<TypeVariableSymbol> getTypeParameters() {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type : this.type.getTypeArguments()) {
            Assert.check(type.tsym.getKind() == ElementKind.TYPE_PARAMETER);
            listBuffer.append((TypeVariableSymbol) type.tsym);
        }
        return listBuffer.toList();
    }

    public boolean hasAnnotations() {
        SymbolMetadata symbolMetadata = this.metadata;
        return (symbolMetadata == null || symbolMetadata.isEmpty()) ? false : true;
    }

    public boolean hasDeprecatedAnnotation() {
        return (this.flags_field & Flags.DEPRECATED_ANNOTATION) != 0;
    }

    public boolean hasOuterInstance() {
        if (this.type.getEnclosingType().hasTag(TypeTag.CLASS) && (flags() & 2305843009213710848L) == 0) {
            return (flags() & ClassFileConstants.JDK20) == 0 || this.type.getEnclosingType().tsym.hasOuterInstance();
        }
        return false;
    }

    public boolean hasTypeAnnotations() {
        SymbolMetadata symbolMetadata = this.metadata;
        return (symbolMetadata == null || symbolMetadata.isTypesEmpty()) ? false : true;
    }

    public ClassSymbol innermostAccessibleEnclosingClass() {
        Assert.check(enclClass().hasOuterInstance());
        Type enclosingType = enclClass().type;
        while ((enclosingType.tsym.flags() & ClassFileConstants.JDK20) != 0) {
            enclosingType = enclosingType.getEnclosingType();
        }
        return (ClassSymbol) enclosingType.getEnclosingType().tsym;
    }

    public boolean isAbstract() {
        return (this.flags_field & 1024) != 0;
    }

    public final boolean isAccessibleIn(Symbol symbol, Types types) {
        Type type;
        int i = (int) (this.flags_field & 7);
        if (i != 0) {
            if (i != 2) {
                return i != 4 || (symbol.flags() & 512) == 0;
            }
            return this.owner == symbol;
        }
        PackageSymbol packageSymbolPackge = packge();
        Symbol symbol2 = symbol;
        while (symbol2 != null && symbol2 != this.owner) {
            while (true) {
                boolean zHasTag = symbol2.type.hasTag(TypeTag.TYPEVAR);
                type = symbol2.type;
                if (!zHasTag) {
                    break;
                }
                symbol2 = type.getUpperBound().tsym;
            }
            if (type.isErroneous()) {
                return true;
            }
            if ((symbol2.flags() & 16777216) == 0 && symbol2.packge() != packageSymbolPackge) {
                return false;
            }
            symbol2 = types.supertype(symbol2.type).tsym;
        }
        return (symbol.flags() & 512) == 0;
    }

    public boolean isAnonymous() {
        return this.name.length() == 0;
    }

    public boolean isCompleted() {
        return this.completer.isTerminal();
    }

    public boolean isConstructor() {
        Name name = this.name;
        return name == name.table.names.init;
    }

    public boolean isDeprecatableViaAnnotation() {
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[getKind().ordinal()];
        return (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) ? false : true;
    }

    public boolean isDeprecated() {
        return (this.flags_field & Flags.BODY_ONLY_FINALIZE) != 0;
    }

    public boolean isDeprecatedForRemoval() {
        return (this.flags_field & Flags.DEPRECATED_REMOVAL) != 0;
    }

    public boolean isDirectlyOrIndirectlyLocal() {
        if (this.owner.kind.matches(Kinds.KindSelector.VAL_MTH)) {
            return true;
        }
        Symbol symbol = this.owner;
        return symbol.kind == Kinds.Kind.TYP && symbol.isDirectlyOrIndirectlyLocal();
    }

    public boolean isDynamic() {
        return false;
    }

    public boolean isEnclosedBy(ClassSymbol classSymbol) {
        while (this.kind != Kinds.Kind.PCK) {
            if (this == classSymbol) {
                return true;
            }
            this = this.owner;
        }
        return false;
    }

    public boolean isEnum() {
        return (flags() & 16384) != 0;
    }

    public boolean isFinal() {
        return (this.flags_field & 16) != 0;
    }

    public boolean isImplicit() {
        return (this.flags_field & 524288) != 0;
    }

    public boolean isInheritedIn(Symbol symbol, Types types) {
        return isAccessibleIn(symbol, types);
    }

    public boolean isInner() {
        return this.kind == Kinds.Kind.TYP && this.type.getEnclosingType().hasTag(TypeTag.CLASS);
    }

    public boolean isInterface() {
        return (flags() & 512) != 0;
    }

    public boolean isMemberOf(TypeSymbol typeSymbol, Types types) {
        Symbol symbol = this.owner;
        if (symbol != typeSymbol) {
            return typeSymbol.isSubClass(symbol, types) && isInheritedIn(typeSymbol, types) && !hiddenIn((ClassSymbol) typeSymbol, types);
        }
        return true;
    }

    public boolean isNonSealed() {
        return (this.flags_field & Long.MIN_VALUE) != 0;
    }

    public boolean isPreviewApi() {
        return (this.flags_field & Flags.PREVIEW_API) != 0;
    }

    public boolean isPrivate() {
        return (this.flags_field & 7) == 2;
    }

    public boolean isPublic() {
        return (this.flags_field & 7) == 1;
    }

    public boolean isSealed() {
        return (this.flags_field & Flags.SEALED) != 0;
    }

    public boolean isStatic() {
        if ((flags() & 8) != 0) {
            return true;
        }
        if ((this.owner.flags() & 512) == 0 || this.kind == Kinds.Kind.MTH) {
            return false;
        }
        Name name = this.name;
        return name != name.table.names._this;
    }

    public boolean isSubClass(Symbol symbol, Types types) {
        throw new AssertionError("isSubClass " + this);
    }

    public Symbol location() {
        Kinds.Kind kind;
        Name name = this.owner.name;
        if (name == null) {
            return null;
        }
        if (name.length() != 0 || (this.owner.flags() & 1048576) != 0 || (kind = this.owner.kind) == Kinds.Kind.PCK || kind == Kinds.Kind.TYP) {
            return this.owner;
        }
        return null;
    }

    public Scope.WriteableScope members() {
        return null;
    }

    public ClassSymbol outermostClass() {
        Symbol symbol = null;
        while (this.kind != Kinds.Kind.PCK) {
            symbol = this;
            this = this.owner;
        }
        return (ClassSymbol) symbol;
    }

    public boolean overrides(Symbol symbol, TypeSymbol typeSymbol, Types types, boolean z) {
        return false;
    }

    public PackageSymbol packge() {
        while (this.kind != Kinds.Kind.PCK) {
            this = this.owner;
        }
        return (PackageSymbol) this;
    }

    @Override // com.sun.tools.javac.jvm.PoolConstant
    public int poolTag() {
        throw new AssertionError("Invalid pool entry");
    }

    public void prependAttributes(List<Attribute.Compound> list) {
        if (list.nonEmpty()) {
            initedMetadata().prepend(list);
        }
    }

    public void resetAnnotations() {
        initedMetadata().reset();
    }

    public void setAttributes(Symbol symbol) {
        if (this.metadata == null && symbol.metadata == null) {
            return;
        }
        initedMetadata().setAttributes(symbol.metadata);
    }

    public void setClassInitTypeAttributes(List<Attribute.TypeCompound> list) {
        initedMetadata().setClassInitTypeAttributes(list);
    }

    public void setDeclarationAttributes(List<Attribute.Compound> list) {
        if (this.metadata != null || list.nonEmpty()) {
            initedMetadata().setDeclarationAttributes(list);
        }
    }

    public void setInitTypeAttributes(List<Attribute.TypeCompound> list) {
        initedMetadata().setInitTypeAttributes(list);
    }

    public void setTypeAttributes(List<Attribute.TypeCompound> list) {
        if (this.metadata != null || list.nonEmpty()) {
            if (this.metadata == null) {
                this.metadata = new SymbolMetadata(this);
            }
            this.metadata.setTypeAttributes(list);
        }
    }

    public String toString() {
        return this.name.toString();
    }

    public static class ClassSymbol extends TypeSymbol implements TypeElement {
        private Annotate.AnnotationTypeMetadata annotationTypeMetadata;
        public JavaFileObject classfile;
        public Name flatname;
        public Name fullname;
        public boolean isPermittedExplicit;
        public Scope.WriteableScope members_field;
        private java.util.List<PermittedClassWithPos> permitted;
        private List<RecordComponent> recordComponents;
        public JavaFileObject sourcefile;
        public List<ClassSymbol> trans_local;

        public ClassSymbol(long j, Name name, Type type, Symbol symbol) {
            super(Kinds.Kind.TYP, j, name, type, symbol);
            this.recordComponents = List.nil();
            this.isPermittedExplicit = false;
            this.members_field = null;
            this.fullname = TypeSymbol.formFullName(name, symbol);
            this.flatname = TypeSymbol.formFlatName(name, symbol);
            this.sourcefile = null;
            this.classfile = null;
            this.annotationTypeMetadata = Annotate.AnnotationTypeMetadata.notAnAnnotationType();
            this.permitted = new ArrayList();
        }

        private ClassSymbol getSuperClassToSearchForAnnotations() {
            Type superclass = getSuperclass();
            if (!superclass.hasTag(TypeTag.CLASS) || superclass.isErroneous()) {
                return null;
            }
            return (ClassSymbol) superclass.tsym;
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitType(this, p);
        }

        public void addPermittedSubclass(ClassSymbol classSymbol, int i) {
            Assert.check(!this.isPermittedExplicit);
            PermittedClassWithPos permittedClassWithPos = new PermittedClassWithPos(classSymbol, i, null);
            int iBinarySearch = Collections.binarySearch(this.permitted, permittedClassWithPos, Comparator.comparing(new Function() { // from class: com.sun.tools.javac.code.p
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((Symbol.ClassSymbol.PermittedClassWithPos) obj).pos());
                }
            }));
            if (iBinarySearch < 0) {
                this.permitted.add((-iBinarySearch) - 1, permittedClassWithPos);
            }
        }

        public String className() {
            return this.name.length() == 0 ? Log.getLocalizedString("anonymous.class", this.flatname) : this.fullname.toString();
        }

        public void clearAnnotationMetadata() {
            this.metadata = null;
            this.annotationTypeMetadata = Annotate.AnnotationTypeMetadata.notAnAnnotationType();
        }

        public void clearPermittedSubclasses() {
            this.permitted.clear();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public void complete() throws CompletionFailure {
            Completer completer = this.completer;
            try {
                super.complete();
            } catch (CompletionFailure e) {
                e.dcfh.classSymbolCompleteFailed(this, completer);
                this.flags_field |= 9;
                this.type = new Type.ErrorType(this, Type.noType);
                throw e;
            }
        }

        public RecordComponent createRecordComponent(RecordComponent recordComponent, JCTree.JCVariableDecl jCVariableDecl, VarSymbol varSymbol) {
            if (recordComponent == null || this.recordComponents.isEmpty()) {
                List<RecordComponent> list = this.recordComponents;
                RecordComponent recordComponent2 = new RecordComponent(varSymbol, jCVariableDecl);
                this.recordComponents = list.append(recordComponent2);
                return recordComponent2;
            }
            ListBuffer listBuffer = new ListBuffer();
            RecordComponent recordComponent3 = null;
            for (RecordComponent recordComponent4 : this.recordComponents) {
                if (recordComponent == recordComponent4) {
                    recordComponent3 = new RecordComponent(varSymbol, recordComponent.ast, recordComponent.isVarargs);
                    listBuffer.add(recordComponent3);
                } else {
                    listBuffer.add(recordComponent4);
                }
            }
            this.recordComponents = listBuffer.toList();
            return recordComponent3;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Type erasure(Types types) {
            if (this.erasure_field == null) {
                this.erasure_field = new Type.ClassType(types.erasure(this.type.getEnclosingType()), List.nil(), this, this.type.getMetadata());
            }
            return this.erasure_field;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public long flags() {
            complete();
            return this.flags_field;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Name flatName() {
            return this.flatname;
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol
        public Annotate.AnnotationTypeMetadata getAnnotationTypeMetadata() {
            return this.annotationTypeMetadata;
        }

        @Override // com.sun.tools.javac.code.AnnoConstruct
        public <A extends Annotation> Attribute.Compound getAttribute(Class<A> cls) {
            Attribute.Compound attribute = super.getAttribute(cls);
            boolean zIsAnnotationPresent = cls.isAnnotationPresent(Inherited.class);
            if (attribute != null || !zIsAnnotationPresent) {
                return attribute;
            }
            ClassSymbol superClassToSearchForAnnotations = getSuperClassToSearchForAnnotations();
            if (superClassToSearchForAnnotations == null) {
                return null;
            }
            return superClassToSearchForAnnotations.getAttribute(cls);
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public List<Symbol> getEnclosedElements() {
            List<Symbol> enclosedElements = super.getEnclosedElements();
            if (!this.recordComponents.isEmpty()) {
                Iterator<RecordComponent> it = this.recordComponents.reverse().iterator();
                while (it.hasNext()) {
                    enclosedElements = enclosedElements.prepend(it.next());
                }
            }
            return enclosedElements;
        }

        @Override // com.sun.tools.javac.code.AnnoConstruct
        public <A extends Annotation> A[] getInheritedAnnotations(Class<A> cls) {
            ClassSymbol superClassToSearchForAnnotations = getSuperClassToSearchForAnnotations();
            return superClassToSearchForAnnotations == null ? (A[]) super.getInheritedAnnotations(cls) : (A[]) superClassToSearchForAnnotations.getAnnotationsByType(cls);
        }

        @Override // javax.lang.model.element.TypeElement
        public List<Type> getInterfaces() {
            apiComplete();
            Type type = this.type;
            if (!(type instanceof Type.ClassType)) {
                return List.nil();
            }
            Type.ClassType classType = (Type.ClassType) type;
            if (classType.interfaces_field == null) {
                classType.interfaces_field = List.nil();
            }
            List<Type> list = classType.all_interfaces_field;
            return list != null ? Type.getModelTypes(list) : classType.interfaces_field;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            apiComplete();
            long jFlags = flags();
            if ((8192 & jFlags) != 0) {
                return ElementKind.ANNOTATION_TYPE;
            }
            if ((512 & jFlags) != 0) {
                return ElementKind.INTERFACE;
            }
            if ((16384 & jFlags) != 0) {
                return ElementKind.ENUM;
            }
            return (jFlags & Flags.RECORD) != 0 ? ElementKind.RECORD : ElementKind.CLASS;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public Set<Modifier> getModifiers() {
            apiComplete();
            return Flags.asModifierSet(flags() & (-8796093022209L));
        }

        @Override // javax.lang.model.element.TypeElement
        public NestingKind getNestingKind() {
            apiComplete();
            if (this.owner.kind == Kinds.Kind.PCK) {
                return NestingKind.TOP_LEVEL;
            }
            if (this.name.length() == 0) {
                return NestingKind.ANONYMOUS;
            }
            return this.owner.kind == Kinds.Kind.MTH ? NestingKind.LOCAL : NestingKind.MEMBER;
        }

        @Override // javax.lang.model.element.TypeElement
        public List<Type> getPermittedSubclasses() {
            return (List) this.permitted.stream().map(new Function() { // from class: com.sun.tools.javac.code.q
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Symbol.ClassSymbol.PermittedClassWithPos) obj).permittedClass().type;
                }
            }).collect(List.collector());
        }

        @Override // com.sun.tools.javac.code.Symbol
        public List<Attribute.Compound> getRawAttributes() {
            complete();
            return super.getRawAttributes();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public List<Attribute.TypeCompound> getRawTypeAttributes() {
            complete();
            return super.getRawTypeAttributes();
        }

        public RecordComponent getRecordComponent(VarSymbol varSymbol) {
            for (RecordComponent recordComponent : this.recordComponents) {
                if (recordComponent.name == varSymbol.name) {
                    return recordComponent;
                }
            }
            return null;
        }

        @Override // javax.lang.model.element.TypeElement
        public Type getSuperclass() {
            apiComplete();
            Type type = this.type;
            if (!(type instanceof Type.ClassType)) {
                return Type.noType;
            }
            Type.ClassType classType = (Type.ClassType) type;
            if (classType.supertype_field == null) {
                classType.supertype_field = Type.noType;
            }
            return classType.isInterface() ? Type.noType : classType.supertype_field.getModelType();
        }

        @Override // javax.lang.model.element.TypeElement, javax.lang.model.element.Parameterizable
        public /* bridge */ /* synthetic */ java.util.List getTypeParameters() {
            return super.getTypeParameters();
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol
        public boolean isAnnotationType() {
            return (this.flags_field & 8192) != 0;
        }

        public boolean isPermittedSubclass(Symbol symbol) {
            Iterator<PermittedClassWithPos> it = this.permitted.iterator();
            while (it.hasNext()) {
                if (it.next().permittedClass.equals(symbol)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isRecord() {
            return (this.flags_field & Flags.RECORD) != 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Symbol
        public boolean isSubClass(Symbol symbol, Types types) {
            if (this == symbol) {
                return true;
            }
            long jFlags = symbol.flags() & 512;
            Type typeSupertype = this.type;
            if (jFlags == 0) {
                while (typeSupertype.hasTag(TypeTag.CLASS)) {
                    if (typeSupertype.tsym == symbol) {
                        return true;
                    }
                    typeSupertype = types.supertype(typeSupertype);
                }
                return false;
            }
            while (typeSupertype.hasTag(TypeTag.CLASS)) {
                for (List listInterfaces = types.interfaces(typeSupertype); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
                    if (((Type) listInterfaces.head).tsym.isSubClass(symbol, types)) {
                        return true;
                    }
                }
                typeSupertype = types.supertype(typeSupertype);
            }
            return false;
        }

        public void markAbstractIfNeeded(Types types) {
            if (types.enter.getEnv(this) == null || (flags() & 16384) == 0 || types.supertype(this.type).tsym != types.syms.enumSym || (flags() & 1040) != 0 || types.firstUnimplementedAbstract(this) == null) {
                return;
            }
            this.flags_field |= 1024;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Scope.WriteableScope members() {
            complete();
            return this.members_field;
        }

        public void reset() {
            this.kind = Kinds.Kind.TYP;
            this.erasure_field = null;
            this.members_field = null;
            this.flags_field = 0L;
            Type type = this.type;
            if (type instanceof Type.ClassType) {
                Type.ClassType classType = (Type.ClassType) type;
                classType.setEnclosingType(Type.noType);
                classType.rank_field = -1;
                classType.typarams_field = null;
                classType.allparams_field = null;
                classType.supertype_field = null;
                classType.interfaces_field = null;
                classType.all_interfaces_field = null;
            }
            clearAnnotationMetadata();
        }

        public void setAnnotationTypeMetadata(Annotate.AnnotationTypeMetadata annotationTypeMetadata) {
            Assert.checkNonNull(annotationTypeMetadata);
            Assert.check(!this.annotationTypeMetadata.isMetadataForAnnotationType());
            this.annotationTypeMetadata = annotationTypeMetadata;
        }

        public void setPermittedSubclasses(List<Symbol> list) {
            this.permitted.clear();
            Iterator<Symbol> it = list.iterator();
            while (it.hasNext()) {
                this.permitted.add(new PermittedClassWithPos(it.next(), 0, null));
            }
        }

        public void setRecordComponents(List<RecordComponent> list) {
            this.recordComponents = list;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            return className();
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitClassSymbol(this, p);
        }

        @Override // javax.lang.model.element.TypeElement, javax.lang.model.element.QualifiedNameable
        public Name getQualifiedName() {
            return this.fullname;
        }

        @Override // javax.lang.model.element.TypeElement
        public List<? extends RecordComponent> getRecordComponents() {
            return this.recordComponents;
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public Name getSimpleName() {
            return this.name;
        }

        public static final class PermittedClassWithPos {
            private final Symbol permittedClass;
            private final int pos;

            private PermittedClassWithPos(Symbol symbol, int i) {
                this.permittedClass = symbol;
                this.pos = i;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof PermittedClassWithPos)) {
                    return false;
                }
                PermittedClassWithPos permittedClassWithPos = (PermittedClassWithPos) obj;
                return this.pos == permittedClassWithPos.pos && Objects.equals(this.permittedClass, permittedClassWithPos.permittedClass);
            }

            public final int hashCode() {
                return (Objects.hashCode(this.permittedClass) * 31) + Integer.hashCode(this.pos);
            }

            public Symbol permittedClass() {
                return this.permittedClass;
            }

            public int pos() {
                return this.pos;
            }

            public final String toString() {
                return "PermittedClassWithPos[permittedClass=" + Objects.toString(this.permittedClass) + ", pos=" + Integer.toString(this.pos) + "]";
            }

            public /* synthetic */ PermittedClassWithPos(Symbol symbol, int i, AnonymousClass1 anonymousClass1) {
                this(symbol, i);
            }
        }

        public ClassSymbol(long j, Name name, Symbol symbol) {
            this(j, name, new Type.ClassType(Type.noType, null, null), symbol);
            this.type.tsym = this;
        }
    }

    public static class CompletionFailure extends RuntimeException {
        private static final long serialVersionUID = 0;
        public final transient DeferredCompletionFailureHandler dcfh;
        private transient JCDiagnostic diag;
        private transient Supplier<JCDiagnostic> diagSupplier;
        public transient Symbol sym;

        public CompletionFailure(Symbol symbol, Supplier<JCDiagnostic> supplier, DeferredCompletionFailureHandler deferredCompletionFailureHandler) {
            this.dcfh = deferredCompletionFailureHandler;
            this.sym = symbol;
            this.diagSupplier = supplier;
        }

        public JCDiagnostic getDetailValue() {
            return getDiagnostic();
        }

        public JCDiagnostic getDiagnostic() {
            Supplier<JCDiagnostic> supplier;
            if (this.diag == null && (supplier = this.diagSupplier) != null) {
                this.diag = supplier.get();
            }
            return this.diag;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return getDiagnostic().getMessage(null);
        }

        public void resetDiagnostic(Supplier<JCDiagnostic> supplier) {
            this.diagSupplier = supplier;
            this.diag = null;
        }

        @Override // java.lang.Throwable
        public CompletionFailure initCause(Throwable th) {
            super.initCause(th);
            return this;
        }
    }

    public static class DynamicMethodSymbol extends MethodSymbol implements PoolConstant.Dynamic {
        public MethodHandleSymbol bsm;
        public PoolConstant.LoadableConstant[] staticArgs;

        public DynamicMethodSymbol(Name name, Symbol symbol, MethodHandleSymbol methodHandleSymbol, Type type, PoolConstant.LoadableConstant[] loadableConstantArr) {
            super(0L, name, type, symbol);
            this.bsm = methodHandleSymbol;
            this.staticArgs = loadableConstantArr;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isDynamic() {
            return true;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public Name name() {
            return this.name;
        }

        @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 18;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public PoolConstant.LoadableConstant[] staticArgs() {
            return this.staticArgs;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public MethodHandleSymbol bootstrapMethod() {
            return this.bsm;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant.Dynamic
        public Type dynamicType() {
            return this.type;
        }
    }

    public static class MethodSymbol extends Symbol implements ExecutableElement {
        public static final Predicate<Symbol> implementation_filter = new Predicate() { // from class: mwd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Symbol.MethodSymbol.a((Symbol) obj);
            }
        };
        public List<VarSymbol> capturedLocals;
        public Code code;
        public Attribute defaultValue;
        public List<VarSymbol> extraParams;
        public List<VarSymbol> params;

        public MethodSymbol(long j, Name name, Type type, Symbol symbol) {
            super(Kinds.Kind.MTH, j, name, type, symbol);
            this.code = null;
            this.extraParams = List.nil();
            this.capturedLocals = List.nil();
            this.params = null;
            this.defaultValue = null;
            if (symbol.type.hasTag(TypeTag.TYPEVAR)) {
                Assert.error(symbol + Constants.ATTRVAL_THIS + name);
            }
        }

        public static /* synthetic */ boolean a(Symbol symbol) {
            return symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 4096) == 0;
        }

        private boolean isOverridableIn(TypeSymbol typeSymbol) {
            int i = (int) (this.flags_field & 7);
            if (i == 0) {
                return packge() == typeSymbol.packge() && (typeSymbol.flags() & 512) == 0;
            }
            if (i != 1) {
                return i == 4 && (typeSymbol.flags() & 512) == 0;
            }
            return !this.owner.isInterface() || (this.flags_field & 8) == 0;
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitExecutable(this, p);
        }

        public MethodHandleSymbol asHandle() {
            return new MethodHandleSymbol(this);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol asMemberOf(Type type, Types types) {
            return new MethodSymbol(this.flags_field, this.name, types.memberType(type, this), this.owner);
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ TypeMirror asType() {
            return super.asType();
        }

        public MethodSymbol binaryImplementation(ClassSymbol classSymbol, Types types) {
            TypeSymbol typeSymbol = classSymbol;
            while (typeSymbol != null) {
                for (Symbol symbol : typeSymbol.members().getSymbolsByName(this.name)) {
                    if (symbol.kind == Kinds.Kind.MTH) {
                        MethodSymbol methodSymbol = (MethodSymbol) symbol;
                        if (methodSymbol.binaryOverrides(this, classSymbol, types)) {
                            return methodSymbol;
                        }
                    }
                }
                typeSymbol = types.supertype(typeSymbol.type).tsym;
            }
            return null;
        }

        public boolean binaryOverrides(Symbol symbol, TypeSymbol typeSymbol, Types types) {
            if (!isConstructor() && symbol.kind == Kinds.Kind.MTH) {
                if (this == symbol) {
                    return true;
                }
                MethodSymbol methodSymbol = (MethodSymbol) symbol;
                if (methodSymbol.isOverridableIn((TypeSymbol) this.owner) && types.asSuper(this.owner.type, methodSymbol.owner) != null && types.isSameType(erasure(types), methodSymbol.erasure(types))) {
                    return true;
                }
                if ((flags() & 1024) == 0 && methodSymbol.isOverridableIn(typeSymbol) && isMemberOf(typeSymbol, types) && types.isSameType(erasure(types), methodSymbol.erasure(types))) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public MethodSymbol clone(final Symbol symbol) {
            MethodSymbol methodSymbol = new MethodSymbol(this, this.flags_field, this.name, this.type, symbol) { // from class: com.sun.tools.javac.code.Symbol.MethodSymbol.1
                final /* synthetic */ MethodSymbol this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
                public /* bridge */ /* synthetic */ TypeMirror asType() {
                    return super.asType();
                }

                @Override // com.sun.tools.javac.code.Symbol
                public Symbol baseSymbol() {
                    return this.this$0;
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol
                public /* bridge */ /* synthetic */ Symbol clone(Symbol symbol2) {
                    return super.clone(symbol2);
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, javax.lang.model.element.ExecutableElement
                public /* bridge */ /* synthetic */ AnnotationValue getDefaultValue() {
                    return super.getDefaultValue();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
                public /* bridge */ /* synthetic */ Element getEnclosingElement() {
                    return super.getEnclosingElement();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, javax.lang.model.element.ExecutableElement
                public /* bridge */ /* synthetic */ java.util.List getParameters() {
                    return super.getParameters();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, javax.lang.model.element.ExecutableElement
                public /* bridge */ /* synthetic */ TypeMirror getReceiverType() {
                    return super.getReceiverType();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, javax.lang.model.element.ExecutableElement
                public /* bridge */ /* synthetic */ TypeMirror getReturnType() {
                    return super.getReturnType();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
                public /* bridge */ /* synthetic */ javax.lang.model.element.Name getSimpleName() {
                    return super.getSimpleName();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, javax.lang.model.element.ExecutableElement
                public /* bridge */ /* synthetic */ java.util.List getThrownTypes() {
                    return super.getThrownTypes();
                }

                @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, javax.lang.model.element.ExecutableElement, javax.lang.model.element.Parameterizable
                public /* bridge */ /* synthetic */ java.util.List getTypeParameters() {
                    return super.getTypeParameters();
                }

                @Override // com.sun.tools.javac.jvm.PoolConstant
                public Object poolKey(Types types) {
                    return new Pair(symbol, baseSymbol());
                }
            };
            methodSymbol.code = this.code;
            return methodSymbol;
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ Element getEnclosingElement() {
            return super.getEnclosingElement();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            Name name = this.name;
            Names names = name.table.names;
            if (name == names.init) {
                return ElementKind.CONSTRUCTOR;
            }
            if (name == names.clinit) {
                return ElementKind.STATIC_INIT;
            }
            if ((flags() & 1048576) != 0) {
                return isStatic() ? ElementKind.STATIC_INIT : ElementKind.INSTANCE_INIT;
            }
            return ElementKind.METHOD;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public Set<Modifier> getModifiers() {
            long jFlags = flags();
            long j = (-4611686018427387905L) & jFlags;
            if ((Flags.DEFAULT & jFlags) != 0) {
                j = (-4611686018427388929L) & jFlags;
            }
            return Flags.asModifierSet(j);
        }

        @Override // javax.lang.model.element.ExecutableElement
        public Type getReceiverType() {
            return asType().mo72getReceiverType();
        }

        @Override // javax.lang.model.element.ExecutableElement
        public Type getReturnType() {
            return asType().mo73getReturnType();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ javax.lang.model.element.Name getSimpleName() {
            return super.getSimpleName();
        }

        @Override // javax.lang.model.element.ExecutableElement
        public List<Type> getThrownTypes() {
            return asType().mo74getThrownTypes();
        }

        @Override // javax.lang.model.element.ExecutableElement, javax.lang.model.element.Parameterizable
        public /* bridge */ /* synthetic */ java.util.List getTypeParameters() {
            return super.getTypeParameters();
        }

        public MethodSymbol implementation(TypeSymbol typeSymbol, Types types, boolean z, Predicate<Symbol> predicate) {
            MethodSymbol methodSymbolImplementation = types.implementation(this, typeSymbol, z, predicate);
            if (methodSymbolImplementation != null) {
                return methodSymbolImplementation;
            }
            if (!types.isDerivedRaw(typeSymbol.type) || typeSymbol.isInterface()) {
                return null;
            }
            return implementation(types.supertype(typeSymbol.type).tsym, types, z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Symbol implemented(TypeSymbol typeSymbol, Types types) {
            Symbol symbolImplemented = null;
            for (List listInterfaces = types.interfaces(typeSymbol.type); symbolImplemented == null && listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
                TypeSymbol typeSymbol2 = ((Type) listInterfaces.head).tsym;
                Symbol symbolImplementedIn = implementedIn(typeSymbol2, types);
                symbolImplemented = symbolImplementedIn == null ? implemented(typeSymbol2, types) : symbolImplementedIn;
            }
            return symbolImplemented;
        }

        public Symbol implementedIn(TypeSymbol typeSymbol, Types types) {
            Symbol symbol = null;
            for (Symbol symbol2 : typeSymbol.members().getSymbolsByName(this.name)) {
                if (overrides(symbol2, (TypeSymbol) this.owner, types, true) && types.isSameType(this.type.mo73getReturnType(), types.memberType(this.owner.type, symbol2).mo73getReturnType())) {
                    symbol = symbol2;
                }
            }
            return symbol;
        }

        public Type implicitReceiverType() {
            ClassSymbol classSymbolEnclClass = enclClass();
            if (classSymbolEnclClass == null) {
                return null;
            }
            Type type = classSymbolEnclClass.type;
            if (isConstructor()) {
                return type.getEnclosingType();
            }
            if (isStatic()) {
                return null;
            }
            return type;
        }

        @Override // javax.lang.model.element.ExecutableElement
        public boolean isDefault() {
            return (flags() & Flags.DEFAULT) != 0;
        }

        public boolean isHandle() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isInheritedIn(Symbol symbol, Types types) {
            if (((int) (this.flags_field & 7)) != 1) {
                return super.isInheritedIn(symbol, types);
            }
            return !this.owner.isInterface() || symbol == this.owner || (this.flags_field & 8) == 0;
        }

        public boolean isLambdaMethod() {
            return (flags() & 562949953421312L) == 562949953421312L;
        }

        public boolean isStaticOrInstanceInit() {
            return getKind() == ElementKind.STATIC_INIT || getKind() == ElementKind.INSTANCE_INIT;
        }

        @Override // javax.lang.model.element.ExecutableElement
        public boolean isVarArgs() {
            return (flags() & Flags.VARARGS) != 0;
        }

        public MethodSymbol originalEnclosingMethod() {
            return this;
        }

        public boolean overrides(Symbol symbol, TypeSymbol typeSymbol, Types types, boolean z, boolean z2) {
            if (!isConstructor() && symbol.kind == Kinds.Kind.MTH) {
                if (this == symbol) {
                    return true;
                }
                MethodSymbol methodSymbol = (MethodSymbol) symbol;
                if (methodSymbol.isOverridableIn((TypeSymbol) this.owner) && types.asSuper(this.owner.type, methodSymbol.owner) != null) {
                    Type typeMemberType = types.memberType(this.owner.type, this);
                    Type typeMemberType2 = types.memberType(this.owner.type, methodSymbol);
                    if (types.isSubSignature(typeMemberType, typeMemberType2) && (!z || types.returnTypeSubstitutable(typeMemberType, typeMemberType2))) {
                        return true;
                    }
                }
                if (((flags() & 1024) == 0 || !z2) && (((methodSymbol.flags() & 1024) != 0 || (methodSymbol.flags() & Flags.DEFAULT) != 0) && methodSymbol.isOverridableIn(typeSymbol) && isMemberOf(typeSymbol, types))) {
                    Type typeMemberType3 = types.memberType(typeSymbol.type, this);
                    Type typeMemberType4 = types.memberType(typeSymbol.type, methodSymbol);
                    if (types.isSubSignature(typeMemberType3, typeMemberType4) && (!z || types.resultSubtype(typeMemberType3, typeMemberType4, types.noWarnings))) {
                        return true;
                    }
                }
            }
            return false;
        }

        public List<VarSymbol> params() {
            MethodSymbol methodSymbol;
            this.owner.complete();
            if (this.params == null) {
                ListBuffer listBuffer = new ListBuffer();
                Iterator<Type> it = this.type.mo71getParameterTypes().iterator();
                int i = 0;
                while (it.hasNext()) {
                    listBuffer.append(new VarSymbol(8589934592L, this.name.table.fromString(Constants.ELEMNAME_ARG_STRING + i), it.next(), this));
                    i++;
                }
                methodSymbol = this;
                methodSymbol.params = listBuffer.toList();
            } else {
                methodSymbol = this;
            }
            Assert.checkNonNull(methodSymbol.params);
            return methodSymbol.params;
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return this.owner.isInterface() ? 11 : 10;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            if ((flags() & 1048576) != 0) {
                return this.owner.name.toString();
            }
            Name name = this.name;
            String string = name == name.table.names.init ? this.owner.name.toString() : name.toString();
            Type type = this.type;
            if (type == null) {
                return string;
            }
            if (type.hasTag(TypeTag.FORALL)) {
                string = "<" + ((Type.ForAll) this.type).getTypeArguments() + ">" + string;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append("(");
            sb.append(this.type.argtypes((flags() & Flags.VARARGS) != 0));
            sb.append(")");
            return sb.toString();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitMethodSymbol(this, p);
        }

        @Override // javax.lang.model.element.ExecutableElement
        public Attribute getDefaultValue() {
            return this.defaultValue;
        }

        @Override // javax.lang.model.element.ExecutableElement
        public List<VarSymbol> getParameters() {
            return params();
        }

        public MethodSymbol implementation(TypeSymbol typeSymbol, Types types, boolean z) {
            return implementation(typeSymbol, types, z, implementation_filter);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean overrides(Symbol symbol, TypeSymbol typeSymbol, Types types, boolean z) {
            return overrides(symbol, typeSymbol, types, z, true);
        }
    }

    public static class PackageSymbol extends TypeSymbol implements PackageElement {
        public Name fullname;
        public Scope.WriteableScope members_field;
        public ModuleSymbol modle;
        public ClassSymbol package_info;
        public JavaFileObject sourcefile;

        public PackageSymbol(Name name, Type type, Symbol symbol) {
            super(Kinds.Kind.PCK, 0L, name, type, symbol);
            this.members_field = null;
            this.fullname = TypeSymbol.formFullName(name, symbol);
        }

        private void mergeAttributes() {
            if (this.metadata != null || this.package_info.metadata == null) {
                return;
            }
            SymbolMetadata symbolMetadata = new SymbolMetadata(this);
            this.metadata = symbolMetadata;
            symbolMetadata.setAttributes(this.package_info.metadata);
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitPackage(this, p);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return (this.flags_field & 8388608) != 0;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public long flags() {
            complete();
            return this.flags_field;
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public Symbol getEnclosingElement() {
            ModuleSymbol moduleSymbol = this.modle;
            if (moduleSymbol == null || moduleSymbol.isNoModule()) {
                return null;
            }
            return this.modle;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            return ElementKind.PACKAGE;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public List<Attribute.Compound> getRawAttributes() {
            complete();
            ClassSymbol classSymbol = this.package_info;
            if (classSymbol != null) {
                classSymbol.complete();
                mergeAttributes();
            }
            return super.getRawAttributes();
        }

        @Override // javax.lang.model.element.PackageElement
        public boolean isUnnamed() {
            return this.name.length() == 0 && this.owner != null;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Scope.WriteableScope members() {
            complete();
            return this.members_field;
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 20;
        }

        public void reset() {
            this.metadata = null;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            return this.fullname.toString();
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitPackageSymbol(this, p);
        }

        @Override // javax.lang.model.element.PackageElement, javax.lang.model.element.QualifiedNameable
        public Name getQualifiedName() {
            return this.fullname;
        }

        public PackageSymbol(Name name, Symbol symbol) {
            this(name, null, symbol);
            this.type = new Type.PackageType(this);
        }
    }

    public static class TypeVariableSymbol extends TypeSymbol implements TypeParameterElement {
        public TypeVariableSymbol(long j, Name name, Type type, Symbol symbol) {
            super(Kinds.Kind.TYP, j, name, type, symbol);
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitTypeParameter(this, p);
        }

        @Override // com.sun.tools.javac.code.Symbol.TypeSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public List<Attribute.Compound> getAnnotationMirrors() {
            List<Attribute.TypeCompound> rawTypeAttributes = this.owner.getRawTypeAttributes();
            int iIndexOf = this.owner.getTypeParameters().indexOf(this);
            List listNil = List.nil();
            for (Attribute.TypeCompound typeCompound : rawTypeAttributes) {
                if (isCurrentSymbolsAnnotation(typeCompound, iIndexOf)) {
                    listNil = listNil.prepend(typeCompound);
                }
            }
            return listNil.reverse();
        }

        @Override // com.sun.tools.javac.code.AnnoConstruct
        public <A extends Annotation> Attribute.Compound getAttribute(Class<A> cls) {
            String name = cls.getName();
            List<Attribute.TypeCompound> rawTypeAttributes = this.owner.getRawTypeAttributes();
            int iIndexOf = this.owner.getTypeParameters().indexOf(this);
            for (Attribute.TypeCompound typeCompound : rawTypeAttributes) {
                if (isCurrentSymbolsAnnotation(typeCompound, iIndexOf) && name.contentEquals((CharSequence) typeCompound.type.tsym.flatName())) {
                    return typeCompound;
                }
            }
            return null;
        }

        @Override // javax.lang.model.element.TypeParameterElement
        public List<Type> getBounds() {
            Type upperBound = ((Type.TypeVar) this.type).getUpperBound();
            if (!upperBound.isCompound()) {
                return List.of(upperBound);
            }
            Type.ClassType classType = (Type.ClassType) upperBound;
            boolean zIsInterface = classType.tsym.erasure_field.isInterface();
            List<Type> list = classType.interfaces_field;
            return !zIsInterface ? list.prepend(classType.supertype_field) : list;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            return ElementKind.TYPE_PARAMETER;
        }

        public boolean isCurrentSymbolsAnnotation(Attribute.TypeCompound typeCompound, int i) {
            TypeAnnotationPosition typeAnnotationPosition = typeCompound.position;
            TargetType targetType = typeAnnotationPosition.type;
            return (targetType == TargetType.CLASS_TYPE_PARAMETER || targetType == TargetType.METHOD_TYPE_PARAMETER) && typeAnnotationPosition.parameter_index == i && typeCompound.type.tsym.flatName() != this.name.table.names.requiresIdentityInternal;
        }

        @Override // javax.lang.model.element.TypeParameterElement
        public Symbol getGenericElement() {
            return this.owner;
        }
    }

    public static class VarSymbol extends Symbol implements VariableElement {
        public int adr;
        private Object data;
        public int pos;

        public VarSymbol(long j, Name name, Type type, Symbol symbol) {
            super(Kinds.Kind.VAR, j, name, type, symbol);
            this.pos = -1;
            this.adr = -1;
        }

        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitVariable(this, p);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol asMemberOf(Type type, Types types) {
            return new VarSymbol(this.flags_field, this.name, types.memberType(type, this), this.owner);
        }

        public MethodHandleSymbol asMethodHandle(boolean z) {
            return new MethodHandleSymbol(this, z);
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ TypeMirror asType() {
            return super.asType();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public VarSymbol clone(final Symbol symbol) {
            VarSymbol varSymbol = new VarSymbol(this, this.flags_field, this.name, this.type, symbol) { // from class: com.sun.tools.javac.code.Symbol.VarSymbol.1
                final /* synthetic */ VarSymbol this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
                public /* bridge */ /* synthetic */ TypeMirror asType() {
                    return super.asType();
                }

                @Override // com.sun.tools.javac.code.Symbol
                public Symbol baseSymbol() {
                    return this.this$0;
                }

                @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol
                public /* bridge */ /* synthetic */ Symbol clone(Symbol symbol2) {
                    return super.clone(symbol2);
                }

                @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
                public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
                    return super.getAnnotationMirrors();
                }

                @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
                public /* bridge */ /* synthetic */ Element getEnclosingElement() {
                    return super.getEnclosingElement();
                }

                @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
                public /* bridge */ /* synthetic */ javax.lang.model.element.Name getSimpleName() {
                    return super.getSimpleName();
                }

                @Override // com.sun.tools.javac.jvm.PoolConstant
                public Object poolKey(Types types) {
                    return new Pair(symbol, baseSymbol());
                }
            };
            varSymbol.pos = this.pos;
            varSymbol.adr = this.adr;
            varSymbol.data = this.data;
            return varSymbol;
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        public Object getConstValue() {
            Object obj = this.data;
            if (obj == ElementKind.EXCEPTION_PARAMETER || obj == ElementKind.RESOURCE_VARIABLE) {
                return null;
            }
            if (obj instanceof Callable) {
                Callable callable = (Callable) obj;
                this.data = null;
                try {
                    this.data = callable.call();
                } catch (Exception e) {
                    x01.a(e);
                    return null;
                }
            }
            return this.data;
        }

        @Override // javax.lang.model.element.VariableElement
        public Object getConstantValue() {
            return com.sun.tools.javac.util.Constants.decode(getConstValue(), this.type);
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ Element getEnclosingElement() {
            return super.getEnclosingElement();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            long jFlags = flags();
            if ((8589934592L & jFlags) != 0) {
                return isExceptionParameter() ? ElementKind.EXCEPTION_PARAMETER : ElementKind.PARAMETER;
            }
            if ((16384 & jFlags) != 0) {
                return ElementKind.ENUM_CONSTANT;
            }
            Kinds.Kind kind = this.owner.kind;
            if (kind == Kinds.Kind.TYP || kind == Kinds.Kind.ERR) {
                return ElementKind.FIELD;
            }
            if (isResourceVariable()) {
                return ElementKind.RESOURCE_VARIABLE;
            }
            return (jFlags & Flags.MATCH_BINDING) != 0 ? ElementKind.BINDING_VARIABLE : ElementKind.LOCAL_VARIABLE;
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ javax.lang.model.element.Name getSimpleName() {
            return super.getSimpleName();
        }

        public boolean isExceptionParameter() {
            return this.data == ElementKind.EXCEPTION_PARAMETER;
        }

        public boolean isResourceVariable() {
            return this.data == ElementKind.RESOURCE_VARIABLE;
        }

        public boolean isUnnamedVariable() {
            return this.name.length() == 0;
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 9;
        }

        public void setData(Object obj) {
            Assert.check(!(obj instanceof Env), this);
            this.data = obj;
        }

        public void setLazyConstValue(final Env<AttrContext> env, final Env<AttrContext> env2, final Attr attr, final JCTree.JCVariableDecl jCVariableDecl) {
            setData(new Callable() { // from class: owd
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return attr.attribLazyConstantValue(env, env2, jCVariableDecl, this.a.type);
                }
            });
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            return this.name.toString();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitVarSymbol(this, p);
        }
    }

    @Override // javax.lang.model.element.Element
    public Type asType() {
        return this.type;
    }

    @Override // javax.lang.model.element.Element
    public Symbol getEnclosingElement() {
        return this.owner;
    }

    @Override // javax.lang.model.element.Element
    public Name getSimpleName() {
        return this.name;
    }

    public static class DelegatedSymbol<T extends Symbol> extends Symbol {
        protected T other;

        public DelegatedSymbol(T t) {
            super(t.kind, t.flags_field, t.name, t.type, t.owner);
            this.other = t;
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return (R) this.other.accept(elementVisitor, p);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol asMemberOf(Type type, Types types) {
            return this.other.asMemberOf(type, types);
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ TypeMirror asType() {
            return super.asType();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol baseSymbol() {
            return this.other;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public void complete() throws CompletionFailure {
            this.other.complete();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public ClassSymbol enclClass() {
            return this.other.enclClass();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Type erasure(Types types) {
            return this.other.erasure(types);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Type externalType(Types types) {
            return this.other.externalType(types);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Name flatName() {
            return this.other.flatName();
        }

        @Override // com.sun.tools.javac.code.Symbol, com.sun.tools.javac.code.AnnoConstruct, javax.lang.model.AnnotatedConstruct
        public /* bridge */ /* synthetic */ java.util.List getAnnotationMirrors() {
            return super.getAnnotationMirrors();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ Element getEnclosingElement() {
            return super.getEnclosingElement();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.TypeElement, javax.lang.model.element.QualifiedNameable
        public Name getQualifiedName() {
            return this.other.getQualifiedName();
        }

        @Override // com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public /* bridge */ /* synthetic */ javax.lang.model.element.Name getSimpleName() {
            return super.getSimpleName();
        }

        public T getUnderlyingSymbol() {
            return this.other;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean hasOuterInstance() {
            return this.other.hasOuterInstance();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isConstructor() {
            return this.other.isConstructor();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isDirectlyOrIndirectlyLocal() {
            return this.other.isDirectlyOrIndirectlyLocal();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isEnclosedBy(ClassSymbol classSymbol) {
            return this.other.isEnclosedBy(classSymbol);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isInheritedIn(Symbol symbol, Types types) {
            return this.other.isInheritedIn(symbol, types);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isInner() {
            return this.other.isInner();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isMemberOf(TypeSymbol typeSymbol, Types types) {
            return this.other.isMemberOf(typeSymbol, types);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isSubClass(Symbol symbol, Types types) {
            return this.other.isSubClass(symbol, types);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol location() {
            return this.other.location();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Scope.WriteableScope members() {
            return this.other.members();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public ClassSymbol outermostClass() {
            return this.other.outermostClass();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public PackageSymbol packge() {
            return this.other.packge();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            return this.other.toString();
        }

        @Override // com.sun.tools.javac.code.Symbol
        public <R, P> R accept(Visitor<R, P> visitor, P p) {
            return visitor.visitSymbol(this.other, p);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol location(Type type, Types types) {
            return this.other.location(type, types);
        }
    }

    public static class MethodHandleSymbol extends MethodSymbol implements PoolConstant.LoadableConstant {
        private boolean getter;
        private Symbol refSym;

        public MethodHandleSymbol(Symbol symbol, boolean z) {
            super(symbol.flags_field, symbol.name, symbol.type, symbol.owner);
            this.refSym = symbol;
            this.getter = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.sun.tools.javac.code.Symbol] */
        /* JADX WARN: Type inference failed for: r1v8 */
        /* JADX WARN: Type inference failed for: r1v9 */
        private boolean allowPrivateInvokeVirtual() {
            ?? r1 = this;
            while (r1 != 0 && !(r1 instanceof RootPackageSymbol)) {
                r1 = r1.owner;
            }
            return r1 != 0 && ((RootPackageSymbol) r1).allowPrivateInvokeVirtual;
        }

        @Override // com.sun.tools.javac.code.Symbol.MethodSymbol
        public MethodHandleSymbol asHandle() {
            return this;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol baseSymbol() {
            return this.refSym;
        }

        @Override // com.sun.tools.javac.code.Symbol.MethodSymbol
        public boolean isHandle() {
            return true;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant
        public Object poolKey(Types types) {
            return new Pair(baseSymbol(), Integer.valueOf(referenceKind()));
        }

        @Override // com.sun.tools.javac.code.Symbol.MethodSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 15;
        }

        public int referenceKind() {
            Symbol symbol = this.refSym;
            if (symbol.kind == Kinds.Kind.VAR) {
                if (this.getter) {
                    return symbol.isStatic() ? 2 : 1;
                }
                return symbol.isStatic() ? 4 : 3;
            }
            if (symbol.isConstructor()) {
                return 8;
            }
            if (this.refSym.isStatic()) {
                return 6;
            }
            if ((this.refSym.flags() & 2) == 0 || allowPrivateInvokeVirtual()) {
                return this.refSym.enclClass().isInterface() ? 9 : 5;
            }
            return 7;
        }

        public MethodHandleSymbol(Symbol symbol) {
            this(symbol, false);
        }
    }

    public static class RecordComponent extends VarSymbol implements RecordComponentElement {
        public MethodSymbol accessor;
        public JCTree.JCMethodDecl accessorMeth;
        private JCTree.JCVariableDecl ast;
        private final boolean isVarargs;
        private final int pos;

        public RecordComponent(VarSymbol varSymbol, JCTree.JCVariableDecl jCVariableDecl) {
            this(varSymbol, jCVariableDecl, varSymbol.type.hasTag(TypeTag.ARRAY) && ((Type.ArrayType) varSymbol.type).isVarargs());
        }

        @Override // com.sun.tools.javac.code.Symbol.VarSymbol, javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            return elementVisitor.visitRecordComponent(this, p);
        }

        public JCTree.JCVariableDecl declarationFor() {
            return this.ast;
        }

        @Override // javax.lang.model.element.RecordComponentElement
        public ExecutableElement getAccessor() {
            return this.accessor;
        }

        @Override // com.sun.tools.javac.code.Symbol.VarSymbol, com.sun.tools.javac.code.Symbol, javax.lang.model.element.Element
        public ElementKind getKind() {
            return ElementKind.RECORD_COMPONENT;
        }

        public List<JCTree.JCAnnotation> getOriginalAnnos() {
            JCTree.JCVariableDecl jCVariableDecl = this.ast;
            return jCVariableDecl == null ? List.nil() : jCVariableDecl.mods.annotations;
        }

        public boolean isVarargs() {
            return this.isVarargs;
        }

        public RecordComponent(Name name, Type type, Symbol symbol) {
            super(1L, name, type, symbol);
            this.pos = -1;
            this.ast = null;
            this.isVarargs = false;
        }

        public RecordComponent(VarSymbol varSymbol, JCTree.JCVariableDecl jCVariableDecl, boolean z) {
            super(1L, varSymbol.name, varSymbol.type, varSymbol.owner);
            this.ast = jCVariableDecl;
            this.pos = varSymbol.pos;
            this.isVarargs = z;
        }
    }

    public Symbol location(Type type, Types types) {
        Type typeAsOuterSuper;
        Name name = this.owner.name;
        if (name != null && name.length() != 0) {
            if (this.owner.type.hasTag(TypeTag.CLASS) && (typeAsOuterSuper = types.asOuterSuper(type, this.owner)) != null) {
                return typeAsOuterSuper.tsym;
            }
            return this.owner;
        }
        return location();
    }
}
