package com.sun.tools.javac.comp;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Enter extends JCTree.Visitor {
    protected static final Context.Key<Enter> enterKey = new Context.Key<>();
    Annotate annotate;
    Check chk;
    JCDiagnostic.Factory diags;
    protected Env<AttrContext> env;
    JavaFileManager fileManager;
    Lint lint;
    Log log;
    TreeMaker make;
    Modules modules;
    Names names;
    Option.PkgInfo pkginfoOpt;
    private JCTree.JCClassDecl predefClassDef;
    Type result;
    Symtab syms;
    private final Todo todo;
    TypeEnter typeEnter;
    TypeEnvs typeEnvs;
    Types types;
    ListBuffer<Symbol.ClassSymbol> uncompleted;
    List<Symbol.ClassSymbol> pendingCompleter = null;
    ListBuffer<JCTree.JCCompilationUnit> unfinishedModules = new ListBuffer<>();
    private final TreeScanner setPackageSymbols = new TreeScanner() { // from class: com.sun.tools.javac.comp.Enter.1
        Symbol currentPackage;

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            Symbol symbol = this.currentPackage;
            jCIdent.sym = symbol;
            jCIdent.type = symbol.type;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
            this.currentPackage = jCPackageDecl.packge;
            scan(jCPackageDecl.pid);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            Symbol symbol = this.currentPackage;
            jCFieldAccess.sym = symbol;
            jCFieldAccess.type = symbol.type;
            this.currentPackage = symbol.owner;
            super.visitSelect(jCFieldAccess);
        }
    };

    public class UnenterScanner extends TreeScanner {
        private final Symbol.ModuleSymbol msym;

        public UnenterScanner(Symbol.ModuleSymbol moduleSymbol) {
            this.msym = moduleSymbol;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            if (classSymbol == null) {
                return;
            }
            Enter.this.typeEnvs.remove(classSymbol);
            Enter.this.chk.removeCompiled(classSymbol);
            Enter.this.chk.clearLocalClassNameIndexes(classSymbol);
            Enter.this.syms.removeClass(this.msym, classSymbol.flatname);
            super.visitClassDef(jCClassDecl);
        }
    }

    public Enter(Context context) {
        context.put(enterKey, this);
        this.log = Log.instance(context);
        this.make = TreeMaker.instance(context);
        this.syms = Symtab.instance(context);
        this.chk = Check.instance(context);
        this.typeEnter = TypeEnter.instance(context);
        this.types = Types.instance(context);
        this.annotate = Annotate.instance(context);
        this.lint = Lint.instance(context);
        this.names = Names.instance(context);
        this.modules = Modules.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        TreeMaker treeMaker = this.make;
        JCTree.JCClassDecl jCClassDeclClassDef = treeMaker.ClassDef(treeMaker.Modifiers(1L), this.syms.predefClass.name, List.nil(), null, List.nil(), List.nil());
        this.predefClassDef = jCClassDeclClassDef;
        jCClassDeclClassDef.sym = this.syms.predefClass;
        this.todo = Todo.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.pkginfoOpt = Option.PkgInfo.get(Options.instance(context));
        this.typeEnvs = TypeEnvs.instance(context);
    }

    public static /* synthetic */ boolean b(JCTree.JCCompilationUnit jCCompilationUnit, Symbol.ModuleSymbol moduleSymbol) {
        return moduleSymbol != jCCompilationUnit.modle;
    }

    public static /* synthetic */ boolean c(Enter enter, Map map, JCTree.JCCompilationUnit jCCompilationUnit, Symbol.ModuleSymbol moduleSymbol) {
        enter.getClass();
        return map.get(jCCompilationUnit.packge.fullname) == enter.syms.getPackage(moduleSymbol, jCCompilationUnit.packge.fullname);
    }

    private static boolean classNameMatchesFileName(Symbol.ClassSymbol classSymbol, Env<AttrContext> env) {
        return env.toplevel.sourcefile.isNameCompatible(classSymbol.name.toString(), JavaFileObject.Kind.SOURCE);
    }

    public static Enter instance(Context context) {
        Enter enter = (Enter) context.get(enterKey);
        return enter == null ? new Enter(context) : enter;
    }

    public Type classEnter(JCTree jCTree, Env<AttrContext> env) {
        Type typeCompletionError;
        Env<AttrContext> env2 = this.env;
        try {
            try {
                this.env = env;
                this.annotate.blockAnnotations();
                jCTree.accept(this);
                typeCompletionError = this.result;
            } catch (Symbol.CompletionFailure e) {
                typeCompletionError = this.chk.completionError(jCTree.pos(), e);
            }
            return typeCompletionError;
        } finally {
            this.annotate.unblockAnnotations();
            this.env = env2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Env<AttrContext> classEnv(JCTree.JCClassDecl jCClassDecl, Env<AttrContext> env) {
        Env<AttrContext> envDup = env.dup(jCClassDecl, ((AttrContext) env.info).dup(Scope.WriteableScope.create(jCClassDecl.sym)));
        envDup.enclClass = jCClassDecl;
        envDup.outer = env;
        AttrContext attrContext = envDup.info;
        attrContext.lint = null;
        attrContext.isAnonymousDiamond = TreeInfo.isDiamond(env.tree);
        envDup.info.ctorPrologue = false;
        return envDup;
    }

    public void complete(List<JCTree.JCCompilationUnit> list, Symbol.ClassSymbol classSymbol) {
        this.annotate.blockAnnotations();
        ListBuffer<Symbol.ClassSymbol> listBuffer = this.uncompleted;
        if (this.typeEnter.completionEnabled) {
            this.uncompleted = new ListBuffer<>();
        }
        try {
            List<Symbol.ClassSymbol> list2 = this.pendingCompleter;
            try {
                this.pendingCompleter = List.nil();
                classEnter(list, (Env<AttrContext>) null);
                Iterator<Symbol.ClassSymbol> it = this.pendingCompleter.iterator();
                while (it.hasNext()) {
                    it.next().completer = this.typeEnter;
                }
                this.pendingCompleter = list2;
                if (this.typeEnter.completionEnabled) {
                    while (this.uncompleted.nonEmpty()) {
                        Symbol.ClassSymbol next = this.uncompleted.next();
                        if (classSymbol == null || classSymbol == next || listBuffer == null) {
                            next.complete();
                        } else {
                            listBuffer.append(next);
                        }
                    }
                    if (this.modules.modulesInitialized()) {
                        this.typeEnter.ensureImportsChecked(this.unfinishedModules.toList());
                        this.unfinishedModules.clear();
                        this.typeEnter.ensureImportsChecked(list);
                    } else {
                        for (JCTree.JCCompilationUnit jCCompilationUnit : list) {
                            if (jCCompilationUnit.getModuleDecl() != null) {
                                this.unfinishedModules.append(jCCompilationUnit);
                            } else {
                                this.typeEnter.ensureImportsChecked(List.of(jCCompilationUnit));
                            }
                        }
                    }
                }
                this.uncompleted = listBuffer;
                this.annotate.unblockAnnotations();
            } catch (Throwable th) {
                this.pendingCompleter = list2;
                throw th;
            }
        } catch (Throwable th2) {
            this.uncompleted = listBuffer;
            this.annotate.unblockAnnotations();
            throw th2;
        }
    }

    public void duplicateClass(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol) {
        this.log.error(diagnosticPosition, CompilerProperties.Errors.DuplicateClass(classSymbol.fullname));
    }

    public Scope.WriteableScope enterScope(Env<AttrContext> env) {
        return env.tree.hasTag(JCTree.Tag.CLASSDEF) ? ((JCTree.JCClassDecl) env.tree).sym.members_field : env.info.scope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Env<AttrContext> getClassEnv(Symbol.TypeSymbol typeSymbol) {
        Env<AttrContext> env = getEnv(typeSymbol);
        if (env == null) {
            return null;
        }
        Env env2 = env;
        while (true) {
            A a = env2.info;
            if (((AttrContext) a).lint != null) {
                env.info.lint = ((AttrContext) a).lint.augment(typeSymbol);
                return env;
            }
            env2 = env2.next;
        }
    }

    public Env<AttrContext> getEnv(Symbol.TypeSymbol typeSymbol) {
        return this.typeEnvs.get(typeSymbol);
    }

    public Iterable<Env<AttrContext>> getEnvs() {
        return this.typeEnvs.values();
    }

    public Env<AttrContext> getTopLevelEnv(JCTree.JCCompilationUnit jCCompilationUnit) {
        Env<AttrContext> env = new Env<>(jCCompilationUnit, new AttrContext());
        env.toplevel = jCCompilationUnit;
        env.enclClass = this.predefClassDef;
        AttrContext attrContext = env.info;
        attrContext.scope = jCCompilationUnit.toplevelScope;
        attrContext.lint = this.lint;
        return env;
    }

    public void main(List<JCTree.JCCompilationUnit> list) {
        complete(list, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Env<AttrContext> moduleEnv(JCTree.JCModuleDecl jCModuleDecl, Env<AttrContext> env) {
        Assert.checkNonNull(jCModuleDecl.sym);
        Env<AttrContext> envDup = env.dup(jCModuleDecl, ((AttrContext) env.info).dup(Scope.WriteableScope.create(jCModuleDecl.sym)));
        envDup.enclClass = this.predefClassDef;
        envDup.outer = env;
        envDup.info.lint = null;
        return envDup;
    }

    public void newRound() {
        this.typeEnvs.clear();
    }

    public Env<AttrContext> topLevelEnv(JCTree.JCCompilationUnit jCCompilationUnit) {
        Env<AttrContext> env = new Env<>(jCCompilationUnit, new AttrContext());
        env.toplevel = jCCompilationUnit;
        env.enclClass = this.predefClassDef;
        jCCompilationUnit.toplevelScope = Scope.WriteableScope.create(jCCompilationUnit.packge);
        jCCompilationUnit.namedImportScope = new Scope.NamedImportScope(jCCompilationUnit.packge);
        jCCompilationUnit.starImportScope = new Scope.StarImportScope(jCCompilationUnit.packge);
        jCCompilationUnit.moduleImportScope = new Scope.StarImportScope(jCCompilationUnit.packge);
        AttrContext attrContext = env.info;
        attrContext.scope = jCCompilationUnit.toplevelScope;
        attrContext.lint = this.lint;
        return env;
    }

    public void unenter(JCTree.JCCompilationUnit jCCompilationUnit, JCTree jCTree) {
        new UnenterScanner(jCCompilationUnit.modle).scan(jCTree);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        Symbol.ClassSymbol classSymbolDefineClass;
        ListBuffer<Symbol.ClassSymbol> listBuffer;
        Env<AttrContext> env = this.env;
        final Symbol symbol = env.info.scope.owner;
        Scope.WriteableScope writeableScopeEnterScope = enterScope(env);
        if (symbol.kind == Kinds.Kind.PCK) {
            Symbol.PackageSymbol packageSymbol = (Symbol.PackageSymbol) symbol;
            for (Symbol symbol2 = packageSymbol; symbol2 != null && symbol2.kind == Kinds.Kind.PCK; symbol2 = symbol2.owner) {
                symbol2.flags_field |= 8388608;
            }
            classSymbolDefineClass = this.syms.enterClass(this.env.toplevel.modle, jCClassDecl.name, packageSymbol);
            packageSymbol.members().enterIfAbsent(classSymbolDefineClass);
            if ((jCClassDecl.mods.flags & 1) != 0 && !classNameMatchesFileName(classSymbolDefineClass, this.env)) {
                Kinds.KindName kindName = Kinds.KindName.CLASS;
                long j = jCClassDecl.mods.flags;
                if ((16384 & j) != 0) {
                    kindName = Kinds.KindName.ENUM;
                } else if ((512 & j) != 0) {
                    kindName = Kinds.KindName.INTERFACE;
                }
                this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.ClassPublicShouldBeInFile(kindName, jCClassDecl.name));
            }
            if ((jCClassDecl.mods.flags & 524288) != 0) {
                this.syms.removeClass(this.env.toplevel.modle, jCClassDecl.name);
            }
        } else {
            if (jCClassDecl.name.length() != 0 && !this.chk.checkUniqueClassName(jCClassDecl.pos(), jCClassDecl.name, writeableScopeEnterScope)) {
                this.result = null;
                return;
            }
            Kinds.Kind kind = symbol.kind;
            Kinds.Kind kind2 = Kinds.Kind.TYP;
            Symtab symtab = this.syms;
            if (kind == kind2) {
                Symbol.TypeSymbol typeSymbol = (Symbol.TypeSymbol) symbol;
                final Symbol.ClassSymbol classSymbolEnterClass = symtab.enterClass(this.env.toplevel.modle, jCClassDecl.name, typeSymbol);
                if (classSymbolEnterClass.owner != symbol) {
                    if (classSymbolEnterClass.name != jCClassDecl.name) {
                        this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.SameBinaryName(classSymbolEnterClass.name, jCClassDecl.name));
                        Type typeCreateErrorType = this.types.createErrorType(jCClassDecl.name, typeSymbol, Type.noType);
                        this.result = typeCreateErrorType;
                        jCClassDecl.sym = (Symbol.ClassSymbol) typeCreateErrorType.tsym;
                        return;
                    }
                    Assert.check(symbol.owner.kind != kind2, (Supplier<String>) new Supplier() { // from class: nb4
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return symbol.toString();
                        }
                    });
                    Assert.check(classSymbolEnterClass.owner.kind == kind2, (Supplier<String>) new Supplier() { // from class: ob4
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return classSymbolEnterClass.owner.toString();
                        }
                    });
                    Scope.WriteableScope writeableScope = ((Symbol.ClassSymbol) classSymbolEnterClass.owner).members_field;
                    if (writeableScope != null) {
                        writeableScope.remove(classSymbolEnterClass);
                    }
                    classSymbolEnterClass.owner = symbol;
                }
                if ((512 & symbol.flags_field) != 0) {
                    jCClassDecl.mods.flags |= 9;
                }
                classSymbolDefineClass = classSymbolEnterClass;
            } else {
                classSymbolDefineClass = symtab.defineClass(jCClassDecl.name, symbol);
                classSymbolDefineClass.flatname = this.chk.localClassName(classSymbolDefineClass);
                if (classSymbolDefineClass.name.length() != 0) {
                    this.chk.checkTransparentClass(jCClassDecl.pos(), classSymbolDefineClass, this.env.info.scope);
                }
            }
        }
        jCClassDecl.sym = classSymbolDefineClass;
        if (this.chk.getCompiled(classSymbolDefineClass) != null) {
            duplicateClass(jCClassDecl.pos(), classSymbolDefineClass);
            Type typeCreateErrorType2 = this.types.createErrorType(jCClassDecl.name, (Symbol.TypeSymbol) symbol, Type.noType);
            this.result = typeCreateErrorType2;
            jCClassDecl.sym = (Symbol.ClassSymbol) typeCreateErrorType2.tsym;
            return;
        }
        this.chk.putCompiled(classSymbolDefineClass);
        writeableScopeEnterScope.enter(classSymbolDefineClass);
        Env<AttrContext> envClassEnv = classEnv(jCClassDecl, this.env);
        this.typeEnvs.put(classSymbolDefineClass, envClassEnv);
        classSymbolDefineClass.completer = Symbol.Completer.NULL_COMPLETER;
        classSymbolDefineClass.flags_field = this.chk.checkFlags(jCClassDecl.mods.flags, classSymbolDefineClass, jCClassDecl) | 2097152;
        JavaFileObject javaFileObject = this.env.toplevel.sourcefile;
        classSymbolDefineClass.sourcefile = javaFileObject;
        classSymbolDefineClass.classfile = javaFileObject;
        classSymbolDefineClass.members_field = Scope.WriteableScope.create(classSymbolDefineClass);
        classSymbolDefineClass.isPermittedExplicit = jCClassDecl.permitting.nonEmpty();
        classSymbolDefineClass.clearAnnotationMetadata();
        Type.ClassType classType = (Type.ClassType) classSymbolDefineClass.type;
        if (symbol.kind != Kinds.Kind.PCK && (classSymbolDefineClass.flags_field & 8) == 0) {
            while (symbol.kind.matches(Kinds.KindSelector.VAL_MTH) && (symbol.flags_field & 8) == 0) {
                symbol = symbol.owner;
            }
            if (symbol.kind == Kinds.Kind.TYP) {
                classType.setEnclosingType(symbol.type);
            }
        }
        classType.typarams_field = classEnter(jCClassDecl.typarams, envClassEnv);
        classType.allparams_field = null;
        List<Symbol.ClassSymbol> list = this.pendingCompleter;
        if (list != null) {
            this.pendingCompleter = list.prepend(classSymbolDefineClass);
        } else {
            classSymbolDefineClass.completer = this.typeEnter;
        }
        if (!classSymbolDefineClass.isDirectlyOrIndirectlyLocal() && (listBuffer = this.uncompleted) != null) {
            listBuffer.append(classSymbolDefineClass);
        }
        classEnter(jCClassDecl.defs, envClassEnv);
        Type type = classSymbolDefineClass.type;
        jCClassDecl.type = type;
        this.result = type;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        Env<AttrContext> envModuleEnv = moduleEnv(jCModuleDecl, this.env);
        this.typeEnvs.put(jCModuleDecl.sym, envModuleEnv);
        if (this.modules.isInModuleGraph(jCModuleDecl.sym)) {
            this.todo.append(envModuleEnv);
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:28:0x00e1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:29:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:30:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:33:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:35:0x0102  */
    /* JADX WARN: Code duplicated, block: B:37:0x0106  */
    /* JADX WARN: Code duplicated, block: B:38:0x010d  */
    /* JADX WARN: Code duplicated, block: B:46:0x0178  */
    /* JADX WARN: Code duplicated, block: B:49:0x0180  */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTopLevel(final JCTree.JCCompilationUnit jCCompilationUnit) {
        boolean z;
        Optional<Symbol.ModuleSymbol> optionalFindAny;
        Env<AttrContext> env;
        Env<AttrContext> envDup;
        JCTree.JCCompilationUnit jCCompilationUnit2;
        Env<AttrContext> env2;
        Symbol symbol;
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos;
        JavaFileObject javaFileObjectUseSource = this.log.useSource(jCCompilationUnit.sourcefile);
        boolean zIsNameCompatible = jCCompilationUnit.sourcefile.isNameCompatible("package-info", JavaFileObject.Kind.SOURCE);
        if (TreeInfo.isModuleInfo(jCCompilationUnit)) {
            JCTree.JCPackageDecl jCPackageDecl = jCCompilationUnit.getPackage();
            if (jCPackageDecl != null) {
                this.log.error(jCPackageDecl.pos(), CompilerProperties.Errors.NoPkgInModuleInfoJava);
            }
            jCCompilationUnit.packge = this.syms.rootPackage;
            classEnter(jCCompilationUnit.defs, topLevelEnv(jCCompilationUnit));
            jCCompilationUnit.modle.usesProvidesCompleter = this.modules.getUsesProvidesCompleter();
        } else {
            JCTree.JCPackageDecl jCPackageDecl2 = jCCompilationUnit.getPackage();
            if (jCPackageDecl2 != null) {
                Symbol.PackageSymbol packageSymbolEnterPackage = this.syms.enterPackage(jCCompilationUnit.modle, TreeInfo.fullName(jCPackageDecl2.pid));
                jCPackageDecl2.packge = packageSymbolEnterPackage;
                jCCompilationUnit.packge = packageSymbolEnterPackage;
                this.setPackageSymbols.scan(jCPackageDecl2);
                if (jCPackageDecl2.annotations.nonEmpty() || this.pkginfoOpt == Option.PkgInfo.ALWAYS || jCCompilationUnit.docComments != null) {
                    if (zIsNameCompatible) {
                        z = true;
                    } else if (jCPackageDecl2.annotations.nonEmpty()) {
                        this.log.error(jCPackageDecl2.annotations.head.pos(), CompilerProperties.Errors.PkgAnnotationsSbInPackageInfoJava);
                    }
                }
                final Map<Name, Symbol.PackageSymbol> map = jCCompilationUnit.modle.visiblePackages;
                optionalFindAny = this.syms.listPackageModules(jCCompilationUnit.packge.fullname).stream().filter(new Predicate() { // from class: lb4
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Enter.b(jCCompilationUnit, (Symbol.ModuleSymbol) obj);
                    }
                }).filter(new Predicate() { // from class: mb4
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Enter.c(this.b, map, jCCompilationUnit, (Symbol.ModuleSymbol) obj);
                    }
                }).findAny();
                if (optionalFindAny.isPresent()) {
                    this.log.error(jCPackageDecl2, CompilerProperties.Errors.PackageInOtherModule(optionalFindAny.get()));
                }
                jCCompilationUnit.packge.complete();
                env = topLevelEnv(jCCompilationUnit);
                if (zIsNameCompatible) {
                    if (jCPackageDecl2 != null) {
                        jCCompilationUnit2 = jCPackageDecl2;
                    } else {
                        jCCompilationUnit2 = jCCompilationUnit;
                    }
                    envDup = env.dup(jCCompilationUnit2);
                    env2 = this.typeEnvs.get(jCCompilationUnit.packge);
                    if (env2 != null) {
                        if (!this.fileManager.isSameFile(jCCompilationUnit.sourcefile, env2.toplevel.sourcefile)) {
                            Log log = this.log;
                            if (jCPackageDecl2 != null) {
                                diagnosticPositionPos = jCPackageDecl2.pid.pos();
                            } else {
                                diagnosticPositionPos = null;
                            }
                            log.warning(diagnosticPositionPos, CompilerProperties.Warnings.PkgInfoAlreadySeen(jCCompilationUnit.packge));
                        }
                    }
                    this.typeEnvs.put(jCCompilationUnit.packge, envDup);
                    for (symbol = jCCompilationUnit.packge; symbol != null && symbol.kind == Kinds.Kind.PCK; symbol = symbol.owner) {
                        symbol.flags_field |= 8388608;
                    }
                    Name name = this.names.package_info;
                    Symbol.ClassSymbol classSymbolEnterClass = this.syms.enterClass(jCCompilationUnit.modle, name, jCCompilationUnit.packge);
                    classSymbolEnterClass.flatname = this.names.fromString(jCCompilationUnit.packge + Constants.ATTRVAL_THIS + name);
                    JavaFileObject javaFileObject = jCCompilationUnit.sourcefile;
                    classSymbolEnterClass.sourcefile = javaFileObject;
                    classSymbolEnterClass.classfile = javaFileObject;
                    classSymbolEnterClass.completer = Symbol.Completer.NULL_COMPLETER;
                    classSymbolEnterClass.members_field = Scope.WriteableScope.create(classSymbolEnterClass);
                    Symbol.PackageSymbol packageSymbol = jCCompilationUnit.packge;
                    packageSymbol.package_info = classSymbolEnterClass;
                    packageSymbol.sourcefile = jCCompilationUnit.sourcefile;
                } else {
                    envDup = null;
                }
                classEnter(jCCompilationUnit.defs, env);
                if (z) {
                    this.todo.append(envDup);
                }
            } else {
                jCCompilationUnit.packge = jCCompilationUnit.modle.unnamedPackage;
            }
            z = false;
            final Map map2 = jCCompilationUnit.modle.visiblePackages;
            optionalFindAny = this.syms.listPackageModules(jCCompilationUnit.packge.fullname).stream().filter(new Predicate() { // from class: lb4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Enter.b(jCCompilationUnit, (Symbol.ModuleSymbol) obj);
                }
            }).filter(new Predicate() { // from class: mb4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Enter.c(this.b, map2, jCCompilationUnit, (Symbol.ModuleSymbol) obj);
                }
            }).findAny();
            if (optionalFindAny.isPresent()) {
                this.log.error(jCPackageDecl2, CompilerProperties.Errors.PackageInOtherModule(optionalFindAny.get()));
            }
            jCCompilationUnit.packge.complete();
            env = topLevelEnv(jCCompilationUnit);
            if (zIsNameCompatible) {
                if (jCPackageDecl2 != null) {
                    jCCompilationUnit2 = jCPackageDecl2;
                } else {
                    jCCompilationUnit2 = jCCompilationUnit;
                }
                envDup = env.dup(jCCompilationUnit2);
                env2 = this.typeEnvs.get(jCCompilationUnit.packge);
                if (env2 != null) {
                    if (!this.fileManager.isSameFile(jCCompilationUnit.sourcefile, env2.toplevel.sourcefile)) {
                        Log log2 = this.log;
                        if (jCPackageDecl2 != null) {
                            diagnosticPositionPos = jCPackageDecl2.pid.pos();
                        } else {
                            diagnosticPositionPos = null;
                        }
                        log2.warning(diagnosticPositionPos, CompilerProperties.Warnings.PkgInfoAlreadySeen(jCCompilationUnit.packge));
                    }
                }
                this.typeEnvs.put(jCCompilationUnit.packge, envDup);
                while (symbol != null) {
                    symbol.flags_field |= 8388608;
                }
                Name name2 = this.names.package_info;
                Symbol.ClassSymbol classSymbolEnterClass2 = this.syms.enterClass(jCCompilationUnit.modle, name2, jCCompilationUnit.packge);
                classSymbolEnterClass2.flatname = this.names.fromString(jCCompilationUnit.packge + Constants.ATTRVAL_THIS + name2);
                JavaFileObject javaFileObject2 = jCCompilationUnit.sourcefile;
                classSymbolEnterClass2.sourcefile = javaFileObject2;
                classSymbolEnterClass2.classfile = javaFileObject2;
                classSymbolEnterClass2.completer = Symbol.Completer.NULL_COMPLETER;
                classSymbolEnterClass2.members_field = Scope.WriteableScope.create(classSymbolEnterClass2);
                Symbol.PackageSymbol packageSymbol2 = jCCompilationUnit.packge;
                packageSymbol2.package_info = classSymbolEnterClass2;
                packageSymbol2.sourcefile = jCCompilationUnit.sourcefile;
            } else {
                envDup = null;
            }
            classEnter(jCCompilationUnit.defs, env);
            if (z) {
                this.todo.append(envDup);
            }
        }
        this.log.useSource(javaFileObjectUseSource);
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
        Type type = jCTypeParameter.type;
        Type.TypeVar typeVar = type != null ? (Type.TypeVar) type : new Type.TypeVar(jCTypeParameter.name, this.env.info.scope.owner, this.syms.botType);
        jCTypeParameter.type = typeVar;
        if (this.chk.checkUnique(jCTypeParameter.pos(), typeVar.tsym, this.env.info.scope)) {
            this.env.info.scope.enter(typeVar.tsym);
        }
        this.result = typeVar;
    }

    public <T extends JCTree> List<Type> classEnter(List<T> list, Env<AttrContext> env) {
        ListBuffer listBuffer = new ListBuffer();
        while (list.nonEmpty()) {
            Type typeClassEnter = classEnter(list.head, env);
            if (typeClassEnter != null) {
                listBuffer.append(typeClassEnter);
            }
            list = list.tail;
        }
        return listBuffer.toList();
    }
}
