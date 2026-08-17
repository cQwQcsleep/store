package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotations;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.TypeEnter;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeCopier;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Abort;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Dependencies;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeEnter implements Symbol.Completer {
    static final boolean checkClash = true;
    protected static final Context.Key<TypeEnter> typeEnterKey = new Context.Key<>();
    boolean allowDeprecationOnImport;
    private final Annotate annotate;
    private final Attr attr;
    private final Check chk;
    private final Dependencies dependencies;
    private final Enter enter;
    private final Log log;
    private final TreeMaker make;
    private final MemberEnter memberEnter;
    private final Names names;
    private final Symtab syms;
    private final Todo todo;
    private Phase topLevelPhase;
    private final TypeAnnotations typeAnnotations;
    private final TypeEnvs typeEnvs;
    private final Types types;
    boolean completionEnabled = true;
    private final ImportsPhase completeClass = new ImportsPhase();

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.TypeEnter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.TYPEAPPLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public abstract class AbstractMembersPhase extends Phase {
        private boolean completing;
        private List<Env<AttrContext>> todo;

        public AbstractMembersPhase(Dependencies.CompletionCause completionCause, Phase phase) {
            super(completionCause, phase);
            this.todo = List.nil();
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void doCompleteEnvs(List<Env<AttrContext>> list) {
            this.todo = this.todo.prependList(list);
            boolean z = this.completing;
            if (z) {
                return;
            }
            this.completing = true;
            while (this.todo.nonEmpty()) {
                try {
                    List<Env<AttrContext>> list2 = this.todo;
                    Env<AttrContext> env = list2.head;
                    this.todo = list2.tail;
                    super.doCompleteEnvs(List.of(env));
                } catch (Throwable th) {
                    this.completing = z;
                    throw th;
                }
            }
            this.completing = z;
        }

        public void enterThisAndSuper(Symbol.ClassSymbol classSymbol, Env<AttrContext> env) {
            Type.ClassType classType = (Type.ClassType) classSymbol.type;
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(262160L, TypeEnter.this.names._this, classSymbol.type, classSymbol);
            varSymbol.pos = 0;
            env.info.scope.enter(varSymbol);
            if ((classSymbol.flags_field & 512) == 0 && classType.supertype_field.hasTag(TypeTag.CLASS)) {
                Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(262160L, TypeEnter.this.names._super, classType.supertype_field, classSymbol);
                varSymbol2.pos = 0;
                env.info.scope.enter(varSymbol2);
            }
        }
    }

    public class AnonClassConstructorHelper extends BasicConstructorHelper {
        boolean based;
        Symbol.MethodSymbol constr;
        Type encl;

        public AnonClassConstructorHelper(Symbol.TypeSymbol typeSymbol, Symbol.MethodSymbol methodSymbol, JCTree.JCExpression jCExpression) {
            super(typeSymbol);
            this.based = false;
            this.constr = methodSymbol;
            this.encl = jCExpression != null ? jCExpression.type : Type.noType;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.comp.TypeEnter.BasicConstructorHelper, com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Symbol.MethodSymbol constructorSymbol() {
            Symbol.MethodSymbol methodSymbolConstructorSymbol = super.constructorSymbol();
            long jFlags = methodSymbolConstructorSymbol.flags_field | (this.constr.flags() & Flags.VARARGS) | 536870912;
            methodSymbolConstructorSymbol.flags_field = jFlags;
            methodSymbolConstructorSymbol.flags_field = jFlags | (this.based ? Flags.ANONCONSTR_BASED : 0L);
            ListBuffer listBuffer = new ListBuffer();
            List listMo71getParameterTypes = constructorType().mo71getParameterTypes();
            if (!enclosingType().hasTag(TypeTag.NONE)) {
                List list = listMo71getParameterTypes.tail;
                listBuffer = listBuffer.prepend(new Symbol.VarSymbol(8589934592L, TypeEnter.this.make.paramName(0), enclosingType(), methodSymbolConstructorSymbol));
                listMo71getParameterTypes = list;
            }
            List<Symbol.VarSymbol> list2 = this.constr.params;
            if (list2 != null) {
                List list3 = listMo71getParameterTypes;
                for (Symbol.VarSymbol varSymbol : list2) {
                    listBuffer.add(new Symbol.VarSymbol(8589934592L | varSymbol.flags(), varSymbol.name, (Type) list3.head, methodSymbolConstructorSymbol));
                    list3 = list3.tail;
                }
            }
            methodSymbolConstructorSymbol.params = listBuffer.toList();
            return methodSymbolConstructorSymbol;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.BasicConstructorHelper, com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Type constructorType() {
            if (this.constructorType == null) {
                Type typeMemberType = TypeEnter.this.types.memberType(this.owner.type, this.constr);
                if (!enclosingType().hasTag(TypeTag.NONE)) {
                    typeMemberType = TypeEnter.this.types.createMethodTypeWithParameters(typeMemberType, typeMemberType.mo71getParameterTypes().prepend(enclosingType()));
                    this.based = true;
                }
                this.constructorType = typeMemberType;
            }
            return this.constructorType;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.BasicConstructorHelper, com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Type enclosingType() {
            return this.encl;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.BasicConstructorHelper, com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public List<Name> superArgs() {
            List listParams = TypeEnter.this.make.Params(constructorSymbol());
            if (!enclosingType().hasTag(TypeTag.NONE)) {
                listParams = listParams.tail;
            }
            return listParams.map(new Function() { // from class: com.sun.tools.javac.comp.a6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JCTree.JCVariableDecl) obj).name;
                }
            });
        }
    }

    public class BasicConstructorHelper implements DefaultConstructorHelper {
        Symbol.MethodSymbol constructorSymbol;
        Type constructorType;
        Symbol.TypeSymbol owner;

        public BasicConstructorHelper(Symbol.TypeSymbol typeSymbol) {
            this.owner = typeSymbol;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Symbol.MethodSymbol constructorSymbol() {
            if (this.constructorSymbol == null) {
                this.constructorSymbol = new Symbol.MethodSymbol(((owner().flags() & 16384) == 0 || TypeEnter.this.types.supertype(owner().type).tsym != TypeEnter.this.syms.enumSym) ? (owner().flags() & 7) | Flags.GENERATEDCONSTR : 68719476738L, TypeEnter.this.names.init, constructorType(), owner());
            }
            return this.constructorSymbol;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Type constructorType() {
            if (this.constructorType == null) {
                this.constructorType = new Type.MethodType(List.nil(), TypeEnter.this.syms.voidType, List.nil(), TypeEnter.this.syms.methodClass);
            }
            return this.constructorType;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Type enclosingType() {
            return Type.noType;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Symbol.TypeSymbol owner() {
            return this.owner;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public List<Name> superArgs() {
            return List.nil();
        }
    }

    public interface DefaultConstructorHelper {
        Symbol.MethodSymbol constructorSymbol();

        Type constructorType();

        Type enclosingType();

        default JCTree.JCMethodDecl finalAdjustment(JCTree.JCMethodDecl jCMethodDecl) {
            return jCMethodDecl;
        }

        Symbol.TypeSymbol owner();

        List<Name> superArgs();
    }

    public final class HeaderPhase extends AbstractHeaderPhase {
        public HeaderPhase() {
            super(Dependencies.CompletionCause.HEADER_PHASE, TypeEnter.this.new RecordPhase());
        }

        private void fillPermits(JCTree.JCClassDecl jCClassDecl, Env<AttrContext> env) throws Throwable {
            Env<AttrContext> env2;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            if (!classSymbol.isAnonymous() || classSymbol.isEnum()) {
                Iterator<Type> it = TypeEnter.this.types.directSupertypes(classSymbol.type).iterator();
                while (it.hasNext()) {
                    Symbol.TypeSymbol typeSymbol = it.next().tsym;
                    if (typeSymbol.kind == Kinds.Kind.TYP) {
                        Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) typeSymbol;
                        Env<AttrContext> env3 = TypeEnter.this.enter.getEnv(classSymbol2);
                        if (classSymbol2.isSealed() && !classSymbol2.isPermittedExplicit && env3 != null && env3.toplevel == env.toplevel) {
                            classSymbol2.addPermittedSubclass(classSymbol, jCClassDecl.pos);
                        }
                    }
                }
            }
            if (!classSymbol.isPermittedExplicit) {
                return;
            }
            ListBuffer listBuffer = new ListBuffer();
            List<JCTree.JCExpression> list = jCClassDecl.permitting;
            AttrContext attrContext = env.info;
            boolean z = attrContext.isPermitsClause;
            try {
                attrContext.isPermitsClause = true;
                Iterator<JCTree.JCExpression> it2 = list.iterator();
                while (it2.hasNext()) {
                    env2 = env;
                    try {
                        listBuffer.append(TypeEnter.this.attr.attribBase(it2.next(), env2, false, false, false).tsym);
                        env = env2;
                    } catch (Throwable th) {
                        th = th;
                        Throwable th2 = th;
                        env2.info.isPermitsClause = z;
                        throw th2;
                    }
                }
                env2 = env;
                classSymbol.setPermittedSubclasses(listBuffer.toList());
                env2.info.isPermitsClause = z;
            } catch (Throwable th3) {
                th = th3;
                env2 = env;
            }
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void runPhase(Env<AttrContext> env) throws Throwable {
            JCTree.JCClassDecl jCClassDecl = env.enclClass;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            Env<AttrContext> envBaseEnv = baseEnv(jCClassDecl, env);
            if (jCClassDecl.extending != null) {
                TypeEnter.this.annotate.queueScanTreeAndTypeAnnotate(jCClassDecl.extending, envBaseEnv, classSymbol);
            }
            Iterator<JCTree.JCExpression> it = jCClassDecl.implementing.iterator();
            while (it.hasNext()) {
                TypeEnter.this.annotate.queueScanTreeAndTypeAnnotate(it.next(), envBaseEnv, classSymbol);
            }
            TypeEnter.this.annotate.flush();
            attribSuperTypes(env, envBaseEnv);
            fillPermits(jCClassDecl, envBaseEnv);
            HashSet hashSet = new HashSet();
            for (JCTree.JCExpression jCExpression : jCClassDecl.implementing) {
                Type type = jCExpression.type;
                if (type.hasTag(TypeTag.CLASS)) {
                    TypeEnter.this.chk.checkNotRepeated(jCExpression.pos(), TypeEnter.this.types.erasure(type), hashSet);
                }
            }
            TypeEnter.this.annotate.annotateLater(jCClassDecl.mods.annotations, envBaseEnv, classSymbol);
            TypeEnter.this.attr.attribTypeVariables(jCClassDecl.typarams, envBaseEnv, false);
            Iterator<JCTree.JCTypeParameter> it2 = jCClassDecl.typarams.iterator();
            while (it2.hasNext()) {
                TypeEnter.this.annotate.queueScanTreeAndTypeAnnotate(it2.next(), envBaseEnv, classSymbol);
            }
            Symbol symbol = classSymbol.owner;
            Kinds.Kind kind = symbol.kind;
            Kinds.Kind kind2 = Kinds.Kind.PCK;
            if (kind == kind2 && symbol != env.toplevel.modle.unnamedPackage && TypeEnter.this.syms.packageExists(env.toplevel.modle, classSymbol.fullname)) {
                TypeEnter.this.log.error(jCClassDecl.pos, CompilerProperties.Errors.ClashWithPkgOfSameName(Kinds.kindName(classSymbol), classSymbol));
            }
            if (classSymbol.owner.kind == kind2 && (classSymbol.flags_field & 1) == 0 && !env.toplevel.sourcefile.isNameCompatible(classSymbol.name.toString(), JavaFileObject.Kind.SOURCE)) {
                classSymbol.flags_field |= Flags.AUXILIARY;
            }
        }
    }

    public final class HierarchyPhase extends AbstractHeaderPhase implements Symbol.Completer {
        public HierarchyPhase() {
            super(Dependencies.CompletionCause.HIERARCHY_PHASE, TypeEnter.this.new HeaderPhase());
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.AbstractHeaderPhase
        public JCTree.JCExpression clearTypeParams(JCTree.JCExpression jCExpression) {
            return AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()] != 1 ? jCExpression : ((JCTree.JCTypeApply) jCExpression).clazz;
        }

        @Override // com.sun.tools.javac.code.Symbol.Completer
        public void complete(Symbol symbol) throws Symbol.CompletionFailure {
            Assert.check((TypeEnter.this.topLevelPhase instanceof ImportsPhase) || TypeEnter.this.topLevelPhase == this);
            if (TypeEnter.this.topLevelPhase != this) {
                symbol.completer = this;
            } else {
                super.doCompleteEnvs(List.of(TypeEnter.this.typeEnvs.get((Symbol.ClassSymbol) symbol)));
            }
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void doCompleteEnvs(List<Env<AttrContext>> list) {
            Iterator<Env<AttrContext>> it = list.iterator();
            while (it.hasNext()) {
                it.next().enclClass.sym.completer = this;
            }
            Iterator<Env<AttrContext>> it2 = list.iterator();
            while (it2.hasNext()) {
                it2.next().enclClass.sym.complete();
            }
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void runPhase(Env<AttrContext> env) {
            JCTree.JCClassDecl jCClassDecl = env.enclClass;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            Type.ClassType classType = (Type.ClassType) classSymbol.type;
            Env<AttrContext> envBaseEnv = baseEnv(jCClassDecl, env);
            attribSuperTypes(env, envBaseEnv);
            if (classSymbol.fullname == TypeEnter.this.names.java_lang_Object) {
                if (jCClassDecl.extending != null) {
                    TypeEnter.this.chk.checkNonCyclic(jCClassDecl.extending.pos(), classType.supertype_field);
                    classType.supertype_field = Type.noType;
                } else if (jCClassDecl.implementing.nonEmpty()) {
                    TypeEnter.this.chk.checkNonCyclic(jCClassDecl.implementing.head.pos(), classType.interfaces_field.head);
                    classType.interfaces_field = List.nil();
                }
            }
            TypeEnter.this.markDeprecated(classSymbol, jCClassDecl.mods.annotations, envBaseEnv);
            TypeEnter.this.chk.checkNonCyclicDecl(jCClassDecl);
        }
    }

    public final class ImportsPhase extends Phase {
        BiConsumer<JCTree.JCImport, Symbol.CompletionFailure> cfHandler;
        Env<AttrContext> env;
        Scope.ImportFilter staticImportFilter;
        Scope.ImportFilter typeImportFilter;

        public ImportsPhase() {
            super(Dependencies.CompletionCause.IMPORTS_PHASE, TypeEnter.this.new HierarchyPhase());
            this.cfHandler = new BiConsumer() { // from class: com.sun.tools.javac.comp.d6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    TypeEnter.this.chk.completionError(((JCTree.JCImport) obj).pos(), (Symbol.CompletionFailure) obj2);
                }
            };
        }

        public static /* synthetic */ boolean b(ImportsPhase importsPhase, Symbol.PackageSymbol packageSymbol, Scope scope, Symbol symbol) {
            importsPhase.getClass();
            return symbol.kind == Kinds.Kind.TYP && TypeEnter.this.chk.importAccessible(symbol, packageSymbol);
        }

        public static /* synthetic */ boolean c(ImportsPhase importsPhase, Symbol.PackageSymbol packageSymbol, Scope scope, Symbol symbol) {
            importsPhase.getClass();
            return symbol.isStatic() && TypeEnter.this.chk.importAccessible(symbol, packageSymbol) && symbol.isMemberOf((Symbol.TypeSymbol) scope.owner, TypeEnter.this.types);
        }

        private void checkClassPackageClash(JCTree.JCPackageDecl jCPackageDecl) {
            if (jCPackageDecl.pid != null) {
                for (Symbol symbol = this.env.toplevel.packge; symbol.owner != TypeEnter.this.syms.rootPackage; symbol = symbol.owner) {
                    symbol.owner.complete();
                    if (TypeEnter.this.syms.getClass(TypeEnter.this.syms.lookupPackage(this.env.toplevel.modle, symbol.owner.getQualifiedName()).modle, symbol.getQualifiedName()) != null) {
                        TypeEnter.this.log.error(jCPackageDecl.pos, CompilerProperties.Errors.PkgClashesWithClassOfSameName(symbol));
                    }
                }
            }
            Annotate annotate = TypeEnter.this.annotate;
            List<JCTree.JCAnnotation> list = jCPackageDecl.annotations;
            Env<AttrContext> env = this.env;
            annotate.annotateLater(list, env, env.toplevel.packge);
        }

        private void doImport(JCTree.JCImport jCImport, boolean z) {
            JCTree.JCFieldAccess jCFieldAccess = jCImport.qualid;
            Name name = TreeInfo.name(jCFieldAccess);
            Env<AttrContext> envDup = this.env.dup(jCImport);
            Symbol.TypeSymbol typeSymbol = TypeEnter.this.attr.attribImportQualifier(jCImport, envDup).tsym;
            if (name == TypeEnter.this.names.asterisk) {
                TypeEnter.this.chk.checkCanonical(jCFieldAccess.selected);
                if (!jCImport.staticImport) {
                    importAll(jCImport, typeSymbol, this.env, z);
                    return;
                } else {
                    Assert.check(!z);
                    importStaticAll(jCImport, typeSymbol, this.env);
                    return;
                }
            }
            if (jCImport.staticImport) {
                Assert.check(!z);
                importNamedStatic(jCImport, typeSymbol, name, envDup);
                TypeEnter.this.chk.checkCanonical(jCFieldAccess.selected);
                return;
            }
            Assert.check(!z);
            Type typeAttribImportType = attribImportType(jCFieldAccess, envDup);
            Type originalType = typeAttribImportType.getOriginalType();
            if (originalType.hasTag(TypeTag.CLASS)) {
                typeAttribImportType = originalType;
            }
            Symbol symbol = typeAttribImportType.tsym;
            TypeEnter.this.chk.checkCanonical(jCFieldAccess);
            importNamed(jCImport.pos(), symbol, this.env, jCImport);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void doModuleImport(JCTree.JCModuleImport jCModuleImport) {
            Name nameFullName = TreeInfo.fullName(jCModuleImport.module);
            Symbol.ModuleSymbol module = TypeEnter.this.syms.getModule(nameFullName);
            if (module == null) {
                TypeEnter.this.log.error(jCModuleImport.pos, CompilerProperties.Errors.ImportModuleNotFound(nameFullName));
                return;
            }
            if (!this.env.toplevel.modle.readModules.contains(module)) {
                boolean zIsUnnamed = this.env.toplevel.modle.isUnnamed();
                TypeEnter typeEnter = TypeEnter.this;
                if (zIsUnnamed) {
                    typeEnter.log.error(jCModuleImport.pos, CompilerProperties.Errors.ImportModuleDoesNotReadUnnamed(module));
                } else {
                    typeEnter.log.error(jCModuleImport.pos, CompilerProperties.Errors.ImportModuleDoesNotRead(this.env.toplevel.modle, module));
                }
                module.getDirectives();
            }
            List listOf = List.of(module);
            HashSet hashSet = new HashSet();
            while (!listOf.isEmpty()) {
                Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) listOf.head;
                listOf = listOf.tail;
                if (hashSet.add(moduleSymbol)) {
                    for (Directive.ExportsDirective exportsDirective : moduleSymbol.exports) {
                        List<Symbol.ModuleSymbol> list = exportsDirective.modules;
                        if (list == null || list.contains(this.env.toplevel.modle)) {
                            doImport(TypeEnter.this.make.at(jCModuleImport.pos).Import(TypeEnter.this.make.Select(TypeEnter.this.make.QualIdent(exportsDirective.getPackage()), TypeEnter.this.names.asterisk), false), true);
                        }
                    }
                    for (Directive.RequiresDirective requiresDirective : moduleSymbol.requires) {
                        if (requiresDirective.isTransitive()) {
                            listOf = listOf.prepend(requiresDirective.module);
                        }
                    }
                }
            }
        }

        private void handleImports(List<JCTree.JCImportBase> list) {
            for (JCTree.JCImportBase jCImportBase : list) {
                if (jCImportBase instanceof JCTree.JCModuleImport) {
                    doModuleImport((JCTree.JCModuleImport) jCImportBase);
                } else {
                    doImport((JCTree.JCImport) jCImportBase, false);
                }
            }
        }

        private void implicitImports(JCTree.JCCompilationUnit jCCompilationUnit, Env<AttrContext> env) {
            Symbol.PackageSymbol packageSymbolEnterPackage = TypeEnter.this.syms.enterPackage(TypeEnter.this.syms.java_base, TypeEnter.this.names.java_lang);
            if (packageSymbolEnterPackage.members().isEmpty() && !packageSymbolEnterPackage.exists()) {
                TypeEnter.this.log.error(CompilerProperties.Errors.NoJavaLang);
                throw new Abort();
            }
            importAll(TypeEnter.this.make.at(jCCompilationUnit.pos()).Import(TypeEnter.this.make.Select(TypeEnter.this.make.QualIdent(packageSymbolEnterPackage.owner), packageSymbolEnterPackage), false), packageSymbolEnterPackage, env, false);
            List<JCTree> typeDecls = jCCompilationUnit.getTypeDecls();
            if (typeDecls.isEmpty()) {
                return;
            }
            JCTree jCTree = typeDecls.head;
            if (!(jCTree instanceof JCTree.JCClassDecl) || (((JCTree.JCClassDecl) jCTree).mods.flags & 524288) == 0) {
                return;
            }
            doModuleImport(TypeEnter.this.make.ModuleImport(TypeEnter.this.make.QualIdent(TypeEnter.this.syms.java_base)));
        }

        private void importAll(JCTree.JCImport jCImport, Symbol.TypeSymbol typeSymbol, Env<AttrContext> env, boolean z) {
            (z ? env.toplevel.moduleImportScope : env.toplevel.starImportScope).importAll(TypeEnter.this.types, typeSymbol.members(), this.typeImportFilter, jCImport, this.cfHandler);
        }

        private void importNamed(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Env<AttrContext> env, JCTree.JCImport jCImport) {
            if (symbol.kind == Kinds.Kind.TYP) {
                jCImport.importScope = env.toplevel.namedImportScope.importType(symbol.owner.members(), symbol.owner.members(), symbol);
            }
        }

        private void importNamedStatic(JCTree.JCImport jCImport, Symbol.TypeSymbol typeSymbol, Name name, Env<AttrContext> env) {
            if (typeSymbol.kind != Kinds.Kind.TYP) {
                TypeEnter.this.log.error(JCDiagnostic.DiagnosticFlag.RECOVERABLE, jCImport.pos(), CompilerProperties.Errors.StaticImpOnlyClassesAndInterfaces);
                return;
            }
            jCImport.importScope = env.toplevel.namedImportScope.importByName(TypeEnter.this.types, typeSymbol.members(), name, this.staticImportFilter, jCImport, this.cfHandler);
        }

        private void importStaticAll(JCTree.JCImport jCImport, Symbol.TypeSymbol typeSymbol, Env<AttrContext> env) {
            env.toplevel.starImportScope.importAll(TypeEnter.this.types, typeSymbol.members(), this.staticImportFilter, jCImport, this.cfHandler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void resolveImports(JCTree.JCCompilationUnit jCCompilationUnit, Env<AttrContext> env) {
            if (jCCompilationUnit.starImportScope.isFilled()) {
                return;
            }
            Scope.ImportFilter importFilter = this.staticImportFilter;
            Scope.ImportFilter importFilter2 = this.typeImportFilter;
            Env<AttrContext> env2 = this.env;
            try {
                this.env = env;
                final Symbol.PackageSymbol packageSymbol = env.toplevel.packge;
                this.staticImportFilter = new Scope.ImportFilter() { // from class: com.sun.tools.javac.comp.b6
                    @Override // com.sun.tools.javac.code.Scope.ImportFilter
                    public final boolean accepts(Scope scope, Symbol symbol) {
                        return TypeEnter.ImportsPhase.c(this.a, packageSymbol, scope, symbol);
                    }
                };
                this.typeImportFilter = new Scope.ImportFilter() { // from class: com.sun.tools.javac.comp.c6
                    @Override // com.sun.tools.javac.code.Scope.ImportFilter
                    public final boolean accepts(Scope scope, Symbol symbol) {
                        return TypeEnter.ImportsPhase.b(this.a, packageSymbol, scope, symbol);
                    }
                };
                implicitImports(jCCompilationUnit, env);
                JCTree.JCModuleDecl moduleDecl = jCCompilationUnit.getModuleDecl();
                if (jCCompilationUnit.getPackage() != null && moduleDecl == null) {
                    checkClassPackageClash(jCCompilationUnit.getPackage());
                }
                handleImports(jCCompilationUnit.getImports());
                if (moduleDecl != null) {
                    TypeEnter.this.markDeprecated(moduleDecl.sym, moduleDecl.mods.annotations, env);
                    TypeEnter.this.annotate.annotateLater(moduleDecl.mods.annotations, env, env.toplevel.modle);
                }
            } finally {
                this.env = env2;
                this.staticImportFilter = importFilter;
                this.typeImportFilter = importFilter2;
            }
        }

        public Type attribImportType(JCTree jCTree, Env<AttrContext> env) {
            Assert.check(TypeEnter.this.completionEnabled);
            boolean importSuppression = TypeEnter.this.chk.setImportSuppression(!TypeEnter.this.allowDeprecationOnImport);
            try {
                TypeEnter typeEnter = TypeEnter.this;
                typeEnter.completionEnabled = false;
                return typeEnter.attr.attribType(jCTree, env);
            } finally {
                TypeEnter typeEnter2 = TypeEnter.this;
                typeEnter2.completionEnabled = true;
                typeEnter2.chk.setImportSuppression(importSuppression);
            }
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void runPhase(Env<AttrContext> env) {
            Symbol.ClassSymbol classSymbol = env.enclClass.sym;
            if (classSymbol.owner.kind == Kinds.Kind.PCK) {
                resolveImports(env.toplevel, env.enclosing(JCTree.Tag.TOPLEVEL));
                TypeEnter.this.todo.append(env);
            }
            Symbol symbol = classSymbol.owner;
            if (symbol.kind == Kinds.Kind.TYP) {
                symbol.complete();
            }
        }
    }

    public final class MembersPhase extends AbstractMembersPhase {
        public MembersPhase() {
            super(Dependencies.CompletionCause.MEMBERS_PHASE, null);
        }

        public static /* synthetic */ boolean a(MembersPhase membersPhase, JCTree.JCVariableDecl jCVariableDecl) {
            TypeEnter typeEnter = TypeEnter.this;
            return typeEnter.lookupMethod(typeEnter.syms.objectType.tsym, jCVariableDecl.name, List.nil()) == null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAccessor(final JCTree.JCVariableDecl jCVariableDecl, Env<AttrContext> env) {
            Symbol.MethodSymbol methodSymbolLookupMethod = TypeEnter.this.lookupMethod(env.enclClass.sym, jCVariableDecl.sym.name, List.nil());
            Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
            Symbol.RecordComponent recordComponent = ((Symbol.ClassSymbol) varSymbol.owner).getRecordComponent(varSymbol);
            if (methodSymbolLookupMethod != null && (methodSymbolLookupMethod.flags_field & 16777216) == 0) {
                recordComponent.accessor = methodSymbolLookupMethod;
                return;
            }
            TreeCopier treeCopier = new TreeCopier(TypeEnter.this.make.at(jCVariableDecl.pos));
            JCTree.JCMethodDecl jCMethodDeclMethodDef = TypeEnter.this.make.at(jCVariableDecl.pos).MethodDef(TypeEnter.this.make.Modifiers(16777217L, recordComponent.getOriginalAnnos().isEmpty() ? recordComponent.getOriginalAnnos() : treeCopier.copy(recordComponent.getOriginalAnnos())), jCVariableDecl.sym.name, (JCTree.JCExpression) treeCopier.copy(TreeInfo.recordFields((JCTree.JCClassDecl) env.tree).stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.e6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TypeEnter.MembersPhase.c(jCVariableDecl, (JCTree.JCVariableDecl) obj);
                }
            }).findAny().get().vartype), List.nil(), List.nil(), List.nil(), null, null);
            TypeEnter.this.memberEnter.memberEnter(jCMethodDeclMethodDef, env);
            recordComponent.accessor = jCMethodDeclMethodDef.sym;
            recordComponent.accessorMeth = jCMethodDeclMethodDef;
        }

        private void addEnumMembers(JCTree.JCClassDecl jCClassDecl, Env<AttrContext> env) {
            TypeEnter.this.memberEnter.memberEnter(TypeEnter.this.make.MethodDef(TypeEnter.this.make.Modifiers(9L), TypeEnter.this.names.values, TypeEnter.this.make.Type(new Type.ArrayType(jCClassDecl.sym.type, TypeEnter.this.syms.arrayClass)), List.nil(), List.nil(), List.nil(), null, null), env);
            TypeEnter.this.memberEnter.memberEnter(TypeEnter.this.make.MethodDef(TypeEnter.this.make.Modifiers(9L), TypeEnter.this.names.valueOf, TypeEnter.this.make.Type(jCClassDecl.sym.type), List.nil(), List.of(TypeEnter.this.make.VarDef(TypeEnter.this.make.Modifiers(8589967360L), TypeEnter.this.names.fromString("name"), TypeEnter.this.make.Type(TypeEnter.this.syms.stringType), null)), List.nil(), null, null), env);
        }

        private void addRecordMembersIfNeeded(JCTree.JCClassDecl jCClassDecl, final Env<AttrContext> env) {
            TypeEnter typeEnter = TypeEnter.this;
            if (typeEnter.lookupMethod(jCClassDecl.sym, typeEnter.names.toString, List.nil()) == null) {
                TypeEnter.this.memberEnter.memberEnter(TypeEnter.this.make.MethodDef(TypeEnter.this.make.Modifiers(2305843009230471185L), TypeEnter.this.names.toString, TypeEnter.this.make.Type(TypeEnter.this.syms.stringType), List.nil(), List.nil(), List.nil(), null, null), env);
            }
            TypeEnter typeEnter2 = TypeEnter.this;
            if (typeEnter2.lookupMethod(jCClassDecl.sym, typeEnter2.names.hashCode, List.nil()) == null) {
                TypeEnter.this.memberEnter.memberEnter(TypeEnter.this.make.MethodDef(TypeEnter.this.make.Modifiers(2305843009230471185L), TypeEnter.this.names.hashCode, TypeEnter.this.make.Type(TypeEnter.this.syms.intType), List.nil(), List.nil(), List.nil(), null, null), env);
            }
            TypeEnter typeEnter3 = TypeEnter.this;
            if (typeEnter3.lookupMethod(jCClassDecl.sym, typeEnter3.names.equals, List.of(TypeEnter.this.syms.objectType)) == null) {
                TypeEnter.this.memberEnter.memberEnter(TypeEnter.this.make.MethodDef(TypeEnter.this.make.Modifiers(2305843009230471185L), TypeEnter.this.names.equals, TypeEnter.this.make.Type(TypeEnter.this.syms.booleanType), List.nil(), List.of(TypeEnter.this.make.VarDef(TypeEnter.this.make.Modifiers(8589934592L), TypeEnter.this.names.fromString("o"), TypeEnter.this.make.Type(TypeEnter.this.syms.objectType), null)), List.nil(), null, null), env);
            }
            List<JCTree.JCVariableDecl> listRecordFields = TreeInfo.recordFields(jCClassDecl);
            for (JCTree.JCVariableDecl jCVariableDecl : listRecordFields) {
                jCVariableDecl.mods.flags &= -17179869185L;
                jCVariableDecl.sym.flags_field &= -17179869185L;
            }
            listRecordFields.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.i6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TypeEnter.MembersPhase.a(this.b, (JCTree.JCVariableDecl) obj);
                }
            }).forEach(new Consumer() { // from class: com.sun.tools.javac.comp.j6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.addAccessor((JCTree.JCVariableDecl) obj, env);
                }
            });
        }

        public static /* synthetic */ boolean c(JCTree.JCVariableDecl jCVariableDecl, JCTree.JCVariableDecl jCVariableDecl2) {
            return jCVariableDecl2.name == jCVariableDecl.name;
        }

        public static /* synthetic */ boolean e(JCTree jCTree, JCTree jCTree2) {
            return TreeInfo.isConstructor(jCTree2) && jCTree2 != jCTree;
        }

        public void finishClass(JCTree.JCClassDecl jCClassDecl, final JCTree jCTree, Env<AttrContext> env) {
            if ((jCClassDecl.mods.flags & 16384) != 0 && !jCClassDecl.sym.type.hasTag(TypeTag.ERROR) && (TypeEnter.this.types.supertype(jCClassDecl.sym.type).tsym.flags() & 16384) == 0) {
                addEnumMembers(jCClassDecl, env);
            }
            boolean z = (jCClassDecl.sym.flags_field & Flags.RECORD) != 0;
            List<JCTree> listPrependList = z ? List.convert(JCTree.class, TreeInfo.recordFields(jCClassDecl)).prependList((List) jCClassDecl.defs.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.h6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TypeEnter.MembersPhase.e(jCTree, (JCTree) obj);
                }
            }).collect(List.collector())) : null;
            List<JCTree> listDiff = jCClassDecl.defs;
            if (z) {
                listDiff = listDiff.diff(listPrependList);
            }
            TypeEnter.this.memberEnter.memberEnter(listDiff, env);
            if (z) {
                addRecordMembersIfNeeded(jCClassDecl, env);
            }
            if (jCClassDecl.sym.isAnnotationType()) {
                Assert.check(jCClassDecl.sym.isCompleted());
                jCClassDecl.sym.setAnnotationTypeMetadata(new Annotate.AnnotationTypeMetadata(jCClassDecl.sym, TypeEnter.this.annotate.annotationTypeSourceCompleter()));
            }
        }

        public JCTree.JCMethodDecl getCanonicalConstructorDecl(JCTree.JCClassDecl jCClassDecl) {
            List<Type> listErasure = TypeEnter.this.types.erasure(TreeInfo.recordFields(jCClassDecl).map(new Function() { // from class: com.sun.tools.javac.comp.f6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JCTree.JCVariableDecl) obj).sym.type;
                }
            }));
            for (JCTree jCTree : jCClassDecl.defs) {
                if (TreeInfo.isConstructor(jCTree)) {
                    JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                    if (TypeEnter.this.types.isSameTypes(TypeEnter.this.types.erasure((List<Type>) jCMethodDecl.params.stream().map(new Function() { // from class: com.sun.tools.javac.comp.g6
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((JCTree.JCVariableDecl) obj).sym.type;
                        }
                    }).collect(List.collector())), listErasure)) {
                        return jCMethodDecl;
                    }
                }
            }
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0050  */
        public DefaultConstructorHelper getDefaultConstructorHelper(Env<AttrContext> env) {
            DefaultConstructorHelper recordConstructorHelper;
            JCTree.JCNewClass jCNewClass;
            Symbol symbol;
            JCTree.JCClassDecl jCClassDecl = env.enclClass;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            boolean z = (classSymbol.flags() & 512) == 0 && !TreeInfo.hasConstructors(jCClassDecl.defs);
            boolean zIsRecord = classSymbol.isRecord();
            JCTree.JCMethodDecl canonicalConstructorDecl = null;
            if (!z || zIsRecord) {
                recordConstructorHelper = null;
            } else {
                recordConstructorHelper = TypeEnter.this.new BasicConstructorHelper(classSymbol);
                if (classSymbol.name.length() == 0 && (symbol = (jCNewClass = (JCTree.JCNewClass) env.next.tree).constructor) != null) {
                    if (symbol.kind != Kinds.Kind.ERR) {
                        recordConstructorHelper = TypeEnter.this.new AnonClassConstructorHelper(classSymbol, (Symbol.MethodSymbol) symbol, jCNewClass.encl);
                    } else {
                        recordConstructorHelper = null;
                    }
                }
            }
            if (zIsRecord) {
                if (z || (canonicalConstructorDecl = getCanonicalConstructorDecl(env.enclClass)) == null) {
                    recordConstructorHelper = TypeEnter.this.new RecordConstructorHelper(classSymbol, TreeInfo.recordFields(jCClassDecl));
                }
                if (canonicalConstructorDecl != null) {
                    canonicalConstructorDecl.sym.flags_field |= Flags.RECORD;
                }
            }
            return recordConstructorHelper;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void runPhase(Env<AttrContext> env) {
            JCTree jCTreeDefaultConstructor;
            JCTree.JCClassDecl jCClassDecl = env.enclClass;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            DefaultConstructorHelper defaultConstructorHelper = getDefaultConstructorHelper(env);
            if (defaultConstructorHelper != null) {
                TypeEnter.this.chk.checkDefaultConstructor(classSymbol, jCClassDecl.pos());
                TypeEnter typeEnter = TypeEnter.this;
                jCTreeDefaultConstructor = typeEnter.defaultConstructor(typeEnter.make.at(jCClassDecl.pos), defaultConstructorHelper);
                jCClassDecl.defs = jCClassDecl.defs.prepend(jCTreeDefaultConstructor);
            } else {
                jCTreeDefaultConstructor = null;
            }
            if (!classSymbol.isRecord()) {
                enterThisAndSuper(classSymbol, env);
            }
            if (!jCClassDecl.typarams.isEmpty()) {
                for (JCTree.JCTypeParameter jCTypeParameter : jCClassDecl.typarams) {
                    TypeEnter.this.chk.checkNonCyclic((JCDiagnostic.DiagnosticPosition) jCTypeParameter, (Type.TypeVar) jCTypeParameter.type);
                }
            }
            finishClass(jCClassDecl, jCTreeDefaultConstructor, env);
            TypeEnter.this.typeAnnotations.organizeTypeAnnotationsSignatures(env, (JCTree.JCClassDecl) env.tree);
            TypeEnter.this.typeAnnotations.validateTypeAnnotationsSignatures(env, (JCTree.JCClassDecl) env.tree);
        }
    }

    public abstract class Phase {
        private final Phase next;
        private final Dependencies.CompletionCause phaseName;
        private final ListBuffer<Env<AttrContext>> queue = new ListBuffer<>();

        public Phase(Dependencies.CompletionCause completionCause, Phase phase) {
            this.phaseName = completionCause;
            this.next = phase;
        }

        public final List<Env<AttrContext>> completeEnvs(List<Env<AttrContext>> list) {
            boolean zIsEmpty = this.queue.isEmpty();
            Phase phase = TypeEnter.this.topLevelPhase;
            try {
                TypeEnter.this.topLevelPhase = this;
                doCompleteEnvs(list);
                TypeEnter.this.topLevelPhase = phase;
                if (!zIsEmpty) {
                    return List.nil();
                }
                List<Env<AttrContext>> list2 = this.queue.toList();
                this.queue.clear();
                Phase phase2 = this.next;
                return phase2 != null ? phase2.completeEnvs(list2) : list2;
            } catch (Throwable th) {
                TypeEnter.this.topLevelPhase = phase;
                if (zIsEmpty) {
                    this.queue.clear();
                }
                throw th;
            }
        }

        public void doCompleteEnvs(List<Env<AttrContext>> list) {
            for (Env<AttrContext> env : list) {
                JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) env.tree;
                this.queue.add(env);
                JavaFileObject javaFileObjectUseSource = TypeEnter.this.log.useSource(env.toplevel.sourcefile);
                try {
                    try {
                        TypeEnter.this.dependencies.push(env.enclClass.sym, this.phaseName);
                        runPhase(env);
                    } catch (Symbol.CompletionFailure e) {
                        TypeEnter.this.chk.completionError(jCClassDecl.pos(), e);
                    }
                    TypeEnter.this.dependencies.pop();
                    TypeEnter.this.log.useSource(javaFileObjectUseSource);
                } catch (Throwable th) {
                    TypeEnter.this.dependencies.pop();
                    TypeEnter.this.log.useSource(javaFileObjectUseSource);
                    throw th;
                }
            }
        }

        public abstract void runPhase(Env<AttrContext> env);
    }

    public class RecordConstructorHelper extends BasicConstructorHelper {
        boolean lastIsVarargs;
        List<JCTree.JCVariableDecl> recordFieldDecls;

        public RecordConstructorHelper(Symbol.ClassSymbol classSymbol, List<JCTree.JCVariableDecl> list) {
            super(classSymbol);
            this.recordFieldDecls = list;
            this.lastIsVarargs = classSymbol.getRecordComponents().stream().anyMatch(new Predicate() { // from class: com.sun.tools.javac.comp.k6
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Symbol.RecordComponent) obj).isVarargs();
                }
            });
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.BasicConstructorHelper, com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Symbol.MethodSymbol constructorSymbol() {
            Symbol.MethodSymbol methodSymbolConstructorSymbol = super.constructorSymbol();
            methodSymbolConstructorSymbol.flags_field |= Flags.GENERATEDCONSTR;
            ListBuffer listBuffer = new ListBuffer();
            JCTree.JCVariableDecl jCVariableDeclLast = this.recordFieldDecls.last();
            Iterator<JCTree.JCVariableDecl> it = this.recordFieldDecls.iterator();
            while (it.hasNext()) {
                JCTree.JCVariableDecl next = it.next();
                listBuffer.add(new Symbol.VarSymbol(((next == jCVariableDeclLast && this.lastIsVarargs) ? Flags.VARARGS : 0L) | 2305843017820405760L, next.name, next.sym.type, methodSymbolConstructorSymbol));
            }
            methodSymbolConstructorSymbol.params = listBuffer.toList();
            methodSymbolConstructorSymbol.flags_field |= Flags.RECORD;
            return methodSymbolConstructorSymbol;
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.BasicConstructorHelper, com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public Type constructorType() {
            if (this.constructorType == null) {
                ListBuffer listBuffer = new ListBuffer();
                JCTree.JCVariableDecl jCVariableDeclLast = this.recordFieldDecls.last();
                Iterator<JCTree.JCVariableDecl> it = this.recordFieldDecls.iterator();
                while (it.hasNext()) {
                    JCTree.JCVariableDecl next = it.next();
                    listBuffer.add((next == jCVariableDeclLast && this.lastIsVarargs) ? TypeEnter.this.types.elemtype(next.sym.type) : next.sym.type);
                }
                this.constructorType = new Type.MethodType(listBuffer.toList(), TypeEnter.this.syms.voidType, List.nil(), TypeEnter.this.syms.methodClass);
            }
            return this.constructorType;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.comp.TypeEnter.DefaultConstructorHelper
        public JCTree.JCMethodDecl finalAdjustment(JCTree.JCMethodDecl jCMethodDecl) {
            List list = this.recordFieldDecls;
            for (JCTree.JCVariableDecl jCVariableDecl : jCMethodDecl.params) {
                Symbol.RecordComponent recordComponent = ((Symbol.ClassSymbol) this.owner).getRecordComponent(jCVariableDecl.sym);
                TreeCopier treeCopier = new TreeCopier(TypeEnter.this.make.at(jCVariableDecl.pos));
                jCVariableDecl.mods.annotations = recordComponent.getOriginalAnnos().isEmpty() ? List.nil() : treeCopier.copy(recordComponent.getOriginalAnnos());
                jCVariableDecl.vartype = (JCTree.JCExpression) treeCopier.copy(((JCTree.JCVariableDecl) list.head).vartype);
                list = list.tail;
            }
            return jCMethodDecl;
        }
    }

    public final class RecordPhase extends AbstractMembersPhase {
        public RecordPhase() {
            super(Dependencies.CompletionCause.RECORD_PHASE, TypeEnter.this.new MembersPhase());
        }

        @Override // com.sun.tools.javac.comp.TypeEnter.Phase
        public void runPhase(Env<AttrContext> env) {
            JCTree.JCClassDecl jCClassDecl = env.enclClass;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            if ((classSymbol.flags_field & Flags.RECORD) != 0) {
                int i = 0;
                for (JCTree.JCVariableDecl jCVariableDecl : TreeInfo.recordFields(jCClassDecl)) {
                    Symbol.RecordComponent recordComponentAt = TypeEnter.this.getRecordComponentAt(classSymbol, i);
                    if (recordComponentAt != null && recordComponentAt.getOriginalAnnos().length() != jCVariableDecl.mods.annotations.length()) {
                        TreeCopier treeCopier = new TreeCopier(TypeEnter.this.make.at(jCVariableDecl.pos));
                        jCVariableDecl.mods.annotations = treeCopier.copy(recordComponentAt.getOriginalAnnos());
                    }
                    TypeEnter.this.memberEnter.memberEnter(jCVariableDecl, env);
                    classSymbol.createRecordComponent(recordComponentAt, (JCTree.JCVariableDecl) new TreeCopier(TypeEnter.this.make.at(jCVariableDecl.pos)).copy(jCVariableDecl), jCVariableDecl.sym);
                    i++;
                }
                enterThisAndSuper(classSymbol, env);
                for (JCTree jCTree : jCClassDecl.defs) {
                    if (TreeInfo.isConstructor(jCTree)) {
                        TypeEnter.this.memberEnter.memberEnter(jCTree, env);
                    }
                }
            }
        }
    }

    public TypeEnter(Context context) {
        context.put(typeEnterKey, this);
        this.names = Names.instance(context);
        this.enter = Enter.instance(context);
        this.memberEnter = MemberEnter.instance(context);
        this.log = Log.instance(context);
        this.chk = Check.instance(context);
        this.attr = Attr.instance(context);
        this.syms = Symtab.instance(context);
        this.make = TreeMaker.instance(context);
        this.todo = Todo.instance(context);
        this.annotate = Annotate.instance(context);
        this.typeAnnotations = TypeAnnotations.instance(context);
        this.types = Types.instance(context);
        this.typeEnvs = TypeEnvs.instance(context);
        this.dependencies = Dependencies.instance(context);
        this.allowDeprecationOnImport = Source.Feature.DEPRECATION_ON_IMPORT.allowedInSource(Source.instance(context));
    }

    public static /* synthetic */ boolean a(Name name, JCTree.JCAssign jCAssign) {
        return TreeInfo.name(jCAssign.lhs) == name;
    }

    public static /* synthetic */ void b() {
    }

    public static /* synthetic */ boolean d(Symbol symbol) {
        return symbol.kind == Kinds.Kind.MTH;
    }

    public static /* synthetic */ void e(Symbol symbol, long j, JCTree.JCAssign jCAssign) {
        JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCAssign.rhs);
        if (jCExpressionSkipParens.hasTag(JCTree.Tag.LITERAL) && Boolean.TRUE.equals(((JCTree.JCLiteral) jCExpressionSkipParens).getValue())) {
            symbol.flags_field = j | symbol.flags_field;
        }
    }

    public static /* synthetic */ JCTree.JCAssign f(JCTree.JCExpression jCExpression) {
        return (JCTree.JCAssign) jCExpression;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.RecordComponent getRecordComponentAt(Symbol.ClassSymbol classSymbol, int i) {
        int i2 = 0;
        for (Symbol.RecordComponent recordComponent : classSymbol.getRecordComponents()) {
            if (i2 == i) {
                return recordComponent;
            }
            i2++;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void handleDeprecatedAnnotations(List<JCTree.JCAnnotation> list, Symbol symbol) {
        Symbol symbol2;
        TypeEnter typeEnter;
        for (List list2 = list; !list2.isEmpty(); list2 = list2.tail) {
            JCTree.JCAnnotation jCAnnotation = (JCTree.JCAnnotation) list2.head;
            Type type = jCAnnotation.annotationType.type;
            Symtab symtab = this.syms;
            if (type == symtab.deprecatedType) {
                symbol.flags_field |= 18014398509613056L;
                typeEnter = this;
                symbol2 = symbol;
                typeEnter.setFlagIfAttributeTrue(jCAnnotation, symbol2, this.names.forRemoval, Flags.DEPRECATED_REMOVAL);
            } else {
                symbol2 = symbol;
                if (type == symtab.previewFeatureType) {
                    symbol2.flags_field |= Flags.PREVIEW_API;
                    typeEnter = this;
                    typeEnter.setFlagIfAttributeTrue(jCAnnotation, symbol2, this.names.reflective, Flags.PREVIEW_REFLECTIVE);
                } else {
                    typeEnter = this;
                }
            }
            this = typeEnter;
            symbol = symbol2;
        }
    }

    public static TypeEnter instance(Context context) {
        TypeEnter typeEnter = (TypeEnter) context.get(typeEnterKey);
        return typeEnter == null ? new TypeEnter(context) : typeEnter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.MethodSymbol lookupMethod(Symbol.TypeSymbol typeSymbol, Name name, List<Type> list) {
        for (Symbol symbol : typeSymbol.members().getSymbolsByName(name, new Predicate() { // from class: que
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TypeEnter.d((Symbol) obj);
            }
        })) {
            if (this.types.isSameTypes(symbol.type.mo71getParameterTypes(), list)) {
                return (Symbol.MethodSymbol) symbol;
            }
        }
        return null;
    }

    private void setFlagIfAttributeTrue(JCTree.JCAnnotation jCAnnotation, final Symbol symbol, final Name name, final long j) {
        jCAnnotation.args.stream().filter(new Predicate() { // from class: kue
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((JCTree.JCExpression) obj).hasTag(JCTree.Tag.ASSIGN);
            }
        }).map(new Function() { // from class: lue
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TypeEnter.f((JCTree.JCExpression) obj);
            }
        }).filter(new Predicate() { // from class: mue
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TypeEnter.a(name, (JCTree.JCAssign) obj);
            }
        }).findFirst().ifPresent(new Consumer() { // from class: nue
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                TypeEnter.e(symbol, j, (JCTree.JCAssign) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.code.Symbol.Completer
    public void complete(Symbol symbol) throws Symbol.CompletionFailure {
        if (!this.completionEnabled) {
            Assert.check((symbol.flags() & 16777216) == 0);
            symbol.completer = this;
            return;
        }
        try {
            this.annotate.blockAnnotations();
            symbol.flags_field |= 268435456;
            this.dependencies.push((Symbol.ClassSymbol) symbol, Dependencies.CompletionCause.MEMBER_ENTER);
            try {
                List<Env<AttrContext>> listCompleteEnvs = this.completeClass.completeEnvs(List.of(this.typeEnvs.get((Symbol.ClassSymbol) symbol)));
                this.dependencies.pop();
                if (!listCompleteEnvs.isEmpty()) {
                    HashSet hashSet = new HashSet();
                    for (Env<AttrContext> env : listCompleteEnvs) {
                        if (env.toplevel.defs.contains(env.enclClass) && hashSet.add(env.toplevel)) {
                            finishImports(env.toplevel, new Runnable() { // from class: oue
                                @Override // java.lang.Runnable
                                public final void run() {
                                    TypeEnter.b();
                                }
                            });
                        }
                    }
                }
                this.annotate.unblockAnnotations();
            } catch (Throwable th) {
                this.dependencies.pop();
                throw th;
            }
        } catch (Throwable th2) {
            this.annotate.unblockAnnotations();
            throw th2;
        }
    }

    public JCTree defaultConstructor(final TreeMaker treeMaker, DefaultConstructorHelper defaultConstructorHelper) {
        Type typeConstructorType = defaultConstructorHelper.constructorType();
        Symbol.MethodSymbol methodSymbolConstructorSymbol = defaultConstructorHelper.constructorSymbol();
        ListBuffer listBuffer = new ListBuffer();
        if (defaultConstructorHelper.owner().type != this.syms.objectType) {
            JCTree.JCExpression jCExpressionSelect = !defaultConstructorHelper.enclosingType().hasTag(TypeTag.NONE) ? treeMaker.Select(treeMaker.Ident(methodSymbolConstructorSymbol.params.head), this.names._super) : treeMaker.Ident(this.names._super);
            List<JCTree.JCExpression> listTypes = typeConstructorType.getTypeArguments().nonEmpty() ? treeMaker.Types(typeConstructorType.getTypeArguments()) : null;
            List<Name> listSuperArgs = defaultConstructorHelper.superArgs();
            Objects.requireNonNull(treeMaker);
            listBuffer.add(treeMaker.Exec(treeMaker.Apply(listTypes, jCExpressionSelect, listSuperArgs.map(new Function() { // from class: pue
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return treeMaker.Ident((Name) obj);
                }
            }))));
        }
        return defaultConstructorHelper.finalAdjustment(treeMaker.MethodDef(methodSymbolConstructorSymbol, treeMaker.Block(0L, listBuffer.toList())));
    }

    public void ensureImportsChecked(List<JCTree.JCCompilationUnit> list) {
        for (final JCTree.JCCompilationUnit jCCompilationUnit : list) {
            if (!jCCompilationUnit.starImportScope.isFilled()) {
                final Env<AttrContext> env = this.enter.topLevelEnv(jCCompilationUnit);
                finishImports(jCCompilationUnit, new Runnable() { // from class: jue
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.b.completeClass.resolveImports(jCCompilationUnit, env);
                    }
                });
            }
        }
    }

    public void finishImports(JCTree.JCCompilationUnit jCCompilationUnit, Runnable runnable) {
        JavaFileObject javaFileObjectUseSource = this.log.useSource(jCCompilationUnit.sourcefile);
        try {
            try {
                runnable.run();
                this.chk.checkImportsUnique(jCCompilationUnit);
                this.chk.checkImportsResolvable(jCCompilationUnit);
                this.chk.checkImportedPackagesObservable(jCCompilationUnit);
                jCCompilationUnit.namedImportScope.finalizeScope();
                jCCompilationUnit.starImportScope.finalizeScope();
                jCCompilationUnit.moduleImportScope.finalizeScope();
            } catch (Symbol.CompletionFailure e) {
                this.chk.completionError(jCCompilationUnit.pos(), e);
            }
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public void markDeprecated(Symbol symbol, List<JCTree.JCAnnotation> list, Env<AttrContext> env) {
        this.attr.attribAnnotationTypes(list, env);
        handleDeprecatedAnnotations(list, symbol);
    }

    public abstract class AbstractHeaderPhase extends Phase {
        public AbstractHeaderPhase(Dependencies.CompletionCause completionCause, Phase phase) {
            super(completionCause, phase);
        }

        public void attribSuperTypes(Env<AttrContext> env, Env<AttrContext> env2) {
            Type typeAttribBase;
            JCTree.JCExpression jCExpressionRecordBase;
            JCTree.JCClassDecl jCClassDecl = env.enclClass;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            Type.ClassType classType = (Type.ClassType) classSymbol.type;
            JCTree.JCExpression jCExpression = jCClassDecl.extending;
            ListBuffer listBufferAppendList = null;
            if (jCExpression != null) {
                jCExpressionRecordBase = clearTypeParams(jCExpression);
                typeAttribBase = TypeEnter.this.attr.attribBase(jCExpressionRecordBase, env2, true, false, true);
                if (typeAttribBase == TypeEnter.this.syms.recordType) {
                    TypeEnter.this.log.error(jCClassDecl, CompilerProperties.Errors.InvalidSupertypeRecord(typeAttribBase.tsym));
                }
            } else if ((jCClassDecl.mods.flags & 16384) != 0) {
                Attr attr = TypeEnter.this.attr;
                jCExpressionRecordBase = enumBase(jCClassDecl.pos, classSymbol);
                typeAttribBase = attr.attribBase(jCExpressionRecordBase, env2, true, false, false);
            } else {
                if (classSymbol.fullname == TypeEnter.this.names.java_lang_Object) {
                    typeAttribBase = Type.noType;
                } else {
                    boolean zIsRecord = classSymbol.isRecord();
                    TypeEnter typeEnter = TypeEnter.this;
                    if (zIsRecord) {
                        Attr attr2 = typeEnter.attr;
                        jCExpressionRecordBase = recordBase(jCClassDecl.pos, classSymbol);
                        typeAttribBase = attr2.attribBase(jCExpressionRecordBase, env2, true, false, false);
                    } else {
                        typeAttribBase = typeEnter.syms.objectType;
                    }
                }
                jCExpressionRecordBase = null;
            }
            classType.supertype_field = modelMissingTypes(env2, typeAttribBase, jCExpressionRecordBase, false);
            ListBuffer listBuffer = new ListBuffer();
            Iterator<JCTree.JCExpression> it = jCClassDecl.implementing.iterator();
            while (it.hasNext()) {
                JCTree.JCExpression jCExpressionClearTypeParams = clearTypeParams(it.next());
                Type typeAttribBase2 = TypeEnter.this.attr.attribBase(jCExpressionClearTypeParams, env2, false, true, true);
                if (typeAttribBase2.hasTag(TypeTag.CLASS)) {
                    listBuffer.append(typeAttribBase2);
                    if (listBufferAppendList != null) {
                        listBufferAppendList.append(typeAttribBase2);
                    }
                } else {
                    if (listBufferAppendList == null) {
                        listBufferAppendList = new ListBuffer().appendList(listBuffer);
                    }
                    listBufferAppendList.append(modelMissingTypes(env2, typeAttribBase2, jCExpressionClearTypeParams, true));
                }
            }
            if ((classSymbol.flags_field & 8192) != 0) {
                List<Type> listOf = List.of(TypeEnter.this.syms.annotationType);
                classType.interfaces_field = listOf;
                classType.all_interfaces_field = listOf;
            } else {
                List<Type> list = listBuffer.toList();
                classType.interfaces_field = list;
                if (listBufferAppendList != null) {
                    list = listBufferAppendList.toList();
                }
                classType.all_interfaces_field = list;
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public Env<AttrContext> baseEnv(JCTree.JCClassDecl jCClassDecl, Env<AttrContext> env) {
            Scope.WriteableScope writeableScopeCreate = Scope.WriteableScope.create(jCClassDecl.sym);
            for (Symbol symbol : env.outer.info.scope.getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                if (symbol.isDirectlyOrIndirectlyLocal()) {
                    writeableScopeCreate.enter(symbol);
                }
            }
            List list = jCClassDecl.typarams;
            if (list != null) {
                while (list.nonEmpty()) {
                    writeableScopeCreate.enter(((JCTree.JCTypeParameter) list.head).type.tsym);
                    list = list.tail;
                }
            }
            Env<A> env2 = env.outer;
            Env<AttrContext> envDup = env2.dup(jCClassDecl, (A) ((AttrContext) env2.info).dup(writeableScopeCreate));
            envDup.baseClause = true;
            envDup.outer = env2;
            return envDup;
        }

        public JCTree.JCExpression clearTypeParams(JCTree.JCExpression jCExpression) {
            return jCExpression;
        }

        public JCTree.JCExpression enumBase(int i, Symbol.ClassSymbol classSymbol) {
            return TypeEnter.this.make.at(i).TypeApply(TypeEnter.this.make.QualIdent(TypeEnter.this.syms.enumSym), List.of(TypeEnter.this.make.Type(classSymbol.type)));
        }

        public Type modelMissingTypes(final Env<AttrContext> env, Type type, final JCTree.JCExpression jCExpression, final boolean z) {
            return !type.hasTag(TypeTag.ERROR) ? type : new Type.ErrorType(this, type.getOriginalType(), type.tsym) { // from class: com.sun.tools.javac.comp.TypeEnter.AbstractHeaderPhase.1
                private Type modelType;
                final /* synthetic */ AbstractHeaderPhase this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.sun.tools.javac.code.Type
                public Type getModelType() {
                    if (this.modelType == null) {
                        this.modelType = this.this$1.new Synthesizer(env.toplevel.modle, getOriginalType(), z).visit(jCExpression);
                    }
                    return this.modelType;
                }
            };
        }

        public JCTree.JCExpression recordBase(int i, Symbol.ClassSymbol classSymbol) {
            return TypeEnter.this.make.at(i).QualIdent(TypeEnter.this.syms.recordType.tsym);
        }

        public class Synthesizer extends JCTree.Visitor {
            boolean interfaceExpected;
            Symbol.ModuleSymbol msym;
            Type originalType;
            Type result;
            List<Symbol.ClassSymbol> synthesizedSymbols = List.nil();

            public Synthesizer(Symbol.ModuleSymbol moduleSymbol, Type type, boolean z) {
                this.msym = moduleSymbol;
                this.originalType = type;
                this.interfaceExpected = z;
            }

            public Symbol.ClassSymbol synthesizeClass(Name name, Symbol symbol) {
                Symbol.ClassSymbol classSymbol = new Symbol.ClassSymbol(this.interfaceExpected ? 512 : 0, name, symbol);
                classSymbol.members_field = new Scope.ErrorScope(classSymbol);
                classSymbol.type = new Type.ErrorType(this.originalType, classSymbol) { // from class: com.sun.tools.javac.comp.TypeEnter.AbstractHeaderPhase.Synthesizer.2
                    @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                    public List<Type> getTypeArguments() {
                        return this.typarams_field;
                    }
                };
                this.synthesizedSymbols = this.synthesizedSymbols.prepend(classSymbol);
                return classSymbol;
            }

            public void synthesizeTyparams(Symbol.ClassSymbol classSymbol, int i) {
                Type.ClassType classType = (Type.ClassType) classSymbol.type;
                Assert.check(classType.typarams_field.isEmpty());
                if (i == 1) {
                    classType.typarams_field = classType.typarams_field.prepend(new Type.TypeVar(TypeEnter.this.names.fromString("T"), classSymbol, TypeEnter.this.syms.botType));
                    return;
                }
                while (i > 0) {
                    classType.typarams_field = classType.typarams_field.prepend(new Type.TypeVar(TypeEnter.this.names.fromString("T" + i), classSymbol, TypeEnter.this.syms.botType));
                    i += -1;
                }
            }

            public List<Type> visit(List<? extends JCTree> list) {
                ListBuffer listBuffer = new ListBuffer();
                Iterator<? extends JCTree> it = list.iterator();
                while (it.hasNext()) {
                    listBuffer.append(visit(it.next()));
                }
                return listBuffer.toList();
            }

            @Override // com.sun.tools.javac.tree.JCTree.Visitor
            public void visitIdent(JCTree.JCIdent jCIdent) {
                if (jCIdent.type.hasTag(TypeTag.ERROR)) {
                    this.result = synthesizeClass(jCIdent.name, this.msym.unnamedPackage).type;
                } else {
                    this.result = jCIdent.type;
                }
            }

            @Override // com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
                if (!jCFieldAccess.type.hasTag(TypeTag.ERROR)) {
                    this.result = jCFieldAccess.type;
                    return;
                }
                boolean z = this.interfaceExpected;
                try {
                    this.interfaceExpected = false;
                    Type typeVisit = visit(jCFieldAccess.selected);
                    this.interfaceExpected = z;
                    this.result = synthesizeClass(jCFieldAccess.name, typeVisit.tsym).type;
                } catch (Throwable th) {
                    this.interfaceExpected = z;
                    throw th;
                }
            }

            @Override // com.sun.tools.javac.tree.JCTree.Visitor
            public void visitTree(JCTree jCTree) {
                this.result = TypeEnter.this.syms.errType;
            }

            @Override // com.sun.tools.javac.tree.JCTree.Visitor
            public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
                if (!jCTypeApply.type.hasTag(TypeTag.ERROR)) {
                    this.result = jCTypeApply.type;
                    return;
                }
                Type.ClassType classType = (Type.ClassType) visit(jCTypeApply.clazz);
                if (this.synthesizedSymbols.contains(classType.tsym)) {
                    synthesizeTyparams((Symbol.ClassSymbol) classType.tsym, jCTypeApply.arguments.size());
                }
                final List<Type> listVisit = visit(jCTypeApply.arguments);
                this.result = new Type.ErrorType(this, jCTypeApply.type, classType.tsym) { // from class: com.sun.tools.javac.comp.TypeEnter.AbstractHeaderPhase.Synthesizer.1
                    final /* synthetic */ Synthesizer this$2;

                    {
                        this.this$2 = this;
                    }

                    @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                    public List<Type> getTypeArguments() {
                        return listVisit;
                    }
                };
            }

            public Type visit(JCTree jCTree) {
                jCTree.accept(this);
                return this.result;
            }
        }
    }
}
