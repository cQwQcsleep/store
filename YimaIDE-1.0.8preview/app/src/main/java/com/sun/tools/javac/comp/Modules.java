package com.sun.tools.javac.comp;

import com.sun.source.tree.ModuleTree;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.FlagsEnum;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.ModuleFinder;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.jvm.ClassWriter;
import com.sun.tools.javac.jvm.JNIWriter;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.lang.model.SourceVersion;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import nbjavac.ModuleWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Modules extends JCTree.Visitor {
    private static final String ALL_MODULE_PATH = "ALL-MODULE-PATH";
    private static final String ALL_SYSTEM = "ALL-SYSTEM";
    private static final Predicate<Symbol.ModuleSymbol> IS_AUTOMATIC = new Predicate() { // from class: x5a
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return Modules.y((Symbol.ModuleSymbol) obj);
        }
    };
    private Map<Symbol.ModuleSymbol, Set<Directive.ExportsDirective>> addExports;
    private final String addExportsOpt;
    private final String addModsOpt;
    private Map<Symbol.ModuleSymbol, Set<Directive.RequiresDirective>> addReads;
    private final String addReadsOpt;
    private Set<Symbol.ModuleSymbol> allModules;
    private final boolean allowAccessIntoSystem;
    private final boolean allowModules;
    private final Attr attr;
    private final Check chk;
    Symbol.ModuleSymbol defaultModule;
    private final JavaFileManager fileManager;
    public PackageNameFinder findPackageInFile;
    boolean inInitModules;
    private final Name java_;
    private final Name java_se;
    private final String limitModsOpt;
    private final Lint lint;
    private final Log log;
    private final ModuleFinder moduleFinder;
    private final String moduleVersionOpt;
    public final boolean multiModuleMode;
    private final Names names;
    private final Preview preview;
    private final Source source;
    private final boolean sourceLauncher;
    private final Symtab syms;
    private final Target target;
    private final TypeEnvs typeEnvs;
    private final Types types;
    private final Set<String> extraAddMods = new HashSet();
    private final Set<String> extraLimitMods = new HashSet();
    private Set<Symbol.ModuleSymbol> rootModules = null;
    private final Set<Symbol.ModuleSymbol> warnedMissing = new HashSet();
    int depth = -1;
    private final Symbol.Completer mainCompleter = new Symbol.Completer() { // from class: com.sun.tools.javac.comp.Modules.1
        private void initErrModule(Symbol.ModuleSymbol moduleSymbol) {
            moduleSymbol.directives = List.nil();
            moduleSymbol.exports = List.nil();
            moduleSymbol.provides = List.nil();
            moduleSymbol.requires = List.nil();
            moduleSymbol.uses = List.nil();
        }

        @Override // com.sun.tools.javac.code.Symbol.Completer
        public void complete(Symbol symbol) throws Symbol.CompletionFailure {
            Symbol.ModuleSymbol moduleSymbolFindModule = Modules.this.moduleFinder.findModule((Symbol.ModuleSymbol) symbol);
            if (moduleSymbolFindModule.kind == Kinds.Kind.ERR) {
                initErrModule(moduleSymbolFindModule);
            } else if ((moduleSymbolFindModule.flags_field & 4503599627370496L) != 0) {
                Modules.this.setupAutomaticModule(moduleSymbolFindModule);
            } else {
                try {
                    moduleSymbolFindModule.module_info.complete();
                } catch (Symbol.CompletionFailure e) {
                    moduleSymbolFindModule.kind = Kinds.Kind.ERR;
                    initErrModule(moduleSymbolFindModule);
                    Modules.this.completeModule(moduleSymbolFindModule);
                    throw e;
                }
            }
            JavaFileObject javaFileObject = moduleSymbolFindModule.module_info.classfile;
            if (javaFileObject == null || javaFileObject.getKind() == JavaFileObject.Kind.CLASS) {
                Modules.this.completeModule(moduleSymbolFindModule);
            }
        }

        public String toString() {
            return "mainCompleter";
        }
    };
    private final Map<Symbol.ModuleSymbol, Set<Symbol.ModuleSymbol>> requiresTransitiveCache = new HashMap();

    public class ModuleVisitor extends JCTree.Visitor {
        private Symbol.ModuleSymbol sym;
        private final Set<Symbol.ModuleSymbol> allRequires = new HashSet();
        private final Map<Symbol.PackageSymbol, List<Directive.ExportsDirective>> allExports = new HashMap();
        private final Map<Symbol.PackageSymbol, List<Directive.OpensDirective>> allOpens = new HashMap();

        public ModuleVisitor() {
        }

        public static /* synthetic */ void b(ModuleVisitor moduleVisitor, JCTree.JCDirective jCDirective) {
            moduleVisitor.getClass();
            jCDirective.accept(moduleVisitor);
        }

        private void checkDuplicateExportsToModule(JCTree.JCExpression jCExpression, Symbol.ModuleSymbol moduleSymbol, Directive.ExportsDirective exportsDirective) {
            List<Symbol.ModuleSymbol> list = exportsDirective.modules;
            if (list != null) {
                Iterator<Symbol.ModuleSymbol> it = list.iterator();
                while (it.hasNext()) {
                    if (moduleSymbol == it.next()) {
                        reportExportsConflictToModule(jCExpression, moduleSymbol);
                    }
                }
            }
        }

        private void checkDuplicateOpensToModule(JCTree.JCExpression jCExpression, Symbol.ModuleSymbol moduleSymbol, Directive.OpensDirective opensDirective) {
            List<Symbol.ModuleSymbol> list = opensDirective.modules;
            if (list != null) {
                Iterator<Symbol.ModuleSymbol> it = list.iterator();
                while (it.hasNext()) {
                    if (moduleSymbol == it.next()) {
                        reportOpensConflictToModule(jCExpression, moduleSymbol);
                    }
                }
            }
        }

        private void ensureJavaBase() {
            if (this.sym.name == Modules.this.names.java_base) {
                return;
            }
            Iterator<Directive.RequiresDirective> it = this.sym.requires.iterator();
            while (it.hasNext()) {
                if (it.next().module.name == Modules.this.names.java_base) {
                    return;
                }
            }
            Directive.RequiresDirective requiresDirective = new Directive.RequiresDirective(Modules.this.syms.enterModule(Modules.this.names.java_base), EnumSet.of(Directive.RequiresFlag.MANDATED));
            Symbol.ModuleSymbol moduleSymbol = this.sym;
            moduleSymbol.requires = moduleSymbol.requires.prepend(requiresDirective);
        }

        private Symbol.ModuleSymbol lookupModule(JCTree.JCExpression jCExpression) {
            Symbol.ModuleSymbol moduleSymbolFindModule = Modules.this.moduleFinder.findModule(TreeInfo.fullName(jCExpression));
            TreeInfo.setSymbol(jCExpression, moduleSymbolFindModule);
            return moduleSymbolFindModule;
        }

        private void reportExportsConflict(JCTree.JCExports jCExports, Symbol.PackageSymbol packageSymbol) {
            Modules.this.log.error(jCExports.qualid.pos(), CompilerProperties.Errors.ConflictingExports(packageSymbol));
        }

        private void reportExportsConflictToModule(JCTree.JCExpression jCExpression, Symbol.ModuleSymbol moduleSymbol) {
            Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ConflictingExportsToModule(moduleSymbol));
        }

        private void reportOpensConflict(JCTree.JCOpens jCOpens, Symbol.PackageSymbol packageSymbol) {
            Modules.this.log.error(jCOpens.qualid.pos(), CompilerProperties.Errors.ConflictingOpens(packageSymbol));
        }

        private void reportOpensConflictToModule(JCTree.JCExpression jCExpression, Symbol.ModuleSymbol moduleSymbol) {
            Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ConflictingOpensToModule(moduleSymbol));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitExports(JCTree.JCExports jCExports) {
            List listFrom;
            Symbol.PackageSymbol packageSymbolEnterPackage = Modules.this.syms.enterPackage(this.sym, TreeInfo.fullName(jCExports.qualid));
            Modules.this.attr.setPackageSymbols(jCExports.qualid, packageSymbolEnterPackage);
            List<Directive.ExportsDirective> listComputeIfAbsent = this.allExports.computeIfAbsent(packageSymbolEnterPackage, new Function() { // from class: com.sun.tools.javac.comp.o1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return List.nil();
                }
            });
            for (Directive.ExportsDirective exportsDirective : listComputeIfAbsent) {
                reportExportsConflict(jCExports, packageSymbolEnterPackage);
            }
            if (jCExports.moduleNames != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (JCTree.JCExpression jCExpression : jCExports.moduleNames) {
                    Symbol.ModuleSymbol moduleSymbolLookupModule = lookupModule(jCExpression);
                    Modules.this.chk.checkModuleExists(jCExpression.pos(), moduleSymbolLookupModule);
                    Iterator<Directive.ExportsDirective> it = listComputeIfAbsent.iterator();
                    while (it.hasNext()) {
                        checkDuplicateExportsToModule(jCExpression, moduleSymbolLookupModule, it.next());
                    }
                    if (!linkedHashSet.add(moduleSymbolLookupModule)) {
                        reportExportsConflictToModule(jCExpression, moduleSymbolLookupModule);
                    }
                }
                listFrom = List.from(linkedHashSet);
            } else {
                listFrom = null;
            }
            if (listFrom == null || !listFrom.isEmpty()) {
                Directive.ExportsDirective exportsDirective2 = new Directive.ExportsDirective(packageSymbolEnterPackage, listFrom, EnumSet.noneOf(Directive.ExportsFlag.class));
                Symbol.ModuleSymbol moduleSymbol = this.sym;
                moduleSymbol.exports = moduleSymbol.exports.prepend(exportsDirective2);
                jCExports.directive = exportsDirective2;
                this.allExports.put(packageSymbolEnterPackage, listComputeIfAbsent.prepend(exportsDirective2));
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
            this.sym = (Symbol.ModuleSymbol) Assert.checkNonNull(jCModuleDecl.sym);
            if (jCModuleDecl.getModuleType() == ModuleTree.ModuleKind.OPEN) {
                this.sym.flags.add(Symbol.ModuleFlags.OPEN);
            }
            Symbol.ModuleSymbol moduleSymbol = this.sym;
            moduleSymbol.flags_field |= jCModuleDecl.mods.flags & Flags.BODY_ONLY_FINALIZE;
            moduleSymbol.requires = List.nil();
            this.sym.exports = List.nil();
            this.sym.opens = List.nil();
            jCModuleDecl.directives.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.q1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Modules.ModuleVisitor.b(this.b, (JCTree.JCDirective) obj);
                }
            });
            Symbol.ModuleSymbol moduleSymbol2 = this.sym;
            moduleSymbol2.requires = moduleSymbol2.requires.reverse();
            Symbol.ModuleSymbol moduleSymbol3 = this.sym;
            moduleSymbol3.exports = moduleSymbol3.exports.reverse();
            Symbol.ModuleSymbol moduleSymbol4 = this.sym;
            moduleSymbol4.opens = moduleSymbol4.opens.reverse();
            ensureJavaBase();
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitOpens(JCTree.JCOpens jCOpens) {
            List listFrom;
            Symbol.PackageSymbol packageSymbolEnterPackage = Modules.this.syms.enterPackage(this.sym, TreeInfo.fullName(jCOpens.qualid));
            Modules.this.attr.setPackageSymbols(jCOpens.qualid, packageSymbolEnterPackage);
            if (this.sym.flags.contains(Symbol.ModuleFlags.OPEN)) {
                Modules.this.log.error(jCOpens.pos(), CompilerProperties.Errors.NoOpensUnlessStrong);
            }
            List<Directive.OpensDirective> listComputeIfAbsent = this.allOpens.computeIfAbsent(packageSymbolEnterPackage, new Function() { // from class: com.sun.tools.javac.comp.p1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return List.nil();
                }
            });
            for (Directive.OpensDirective opensDirective : listComputeIfAbsent) {
                reportOpensConflict(jCOpens, packageSymbolEnterPackage);
            }
            if (jCOpens.moduleNames != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (JCTree.JCExpression jCExpression : jCOpens.moduleNames) {
                    Symbol.ModuleSymbol moduleSymbolLookupModule = lookupModule(jCExpression);
                    Modules.this.chk.checkModuleExists(jCExpression.pos(), moduleSymbolLookupModule);
                    Iterator<Directive.OpensDirective> it = listComputeIfAbsent.iterator();
                    while (it.hasNext()) {
                        checkDuplicateOpensToModule(jCExpression, moduleSymbolLookupModule, it.next());
                    }
                    if (!linkedHashSet.add(moduleSymbolLookupModule)) {
                        reportOpensConflictToModule(jCExpression, moduleSymbolLookupModule);
                    }
                }
                listFrom = List.from(linkedHashSet);
            } else {
                listFrom = null;
            }
            if (listFrom == null || !listFrom.isEmpty()) {
                Directive.OpensDirective opensDirective2 = new Directive.OpensDirective(packageSymbolEnterPackage, listFrom, EnumSet.noneOf(Directive.OpensFlag.class));
                Symbol.ModuleSymbol moduleSymbol = this.sym;
                moduleSymbol.opens = moduleSymbol.opens.prepend(opensDirective2);
                jCOpens.directive = opensDirective2;
                this.allOpens.put(packageSymbolEnterPackage, listComputeIfAbsent.prepend(opensDirective2));
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitProvides(JCTree.JCProvides jCProvides) {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitRequires(JCTree.JCRequires jCRequires) {
            Symbol.ModuleSymbol moduleSymbolLookupModule = lookupModule(jCRequires.moduleName);
            if (moduleSymbolLookupModule.kind != Kinds.Kind.MDL) {
                Modules.this.log.error(jCRequires.moduleName.pos(), CompilerProperties.Errors.ModuleNotFound(moduleSymbolLookupModule));
                Modules.this.warnedMissing.add(moduleSymbolLookupModule);
                return;
            }
            if (this.allRequires.contains(moduleSymbolLookupModule)) {
                Modules.this.log.error(jCRequires.moduleName.pos(), CompilerProperties.Errors.DuplicateRequires(moduleSymbolLookupModule));
                return;
            }
            this.allRequires.add(moduleSymbolLookupModule);
            EnumSet enumSetNoneOf = EnumSet.noneOf(Directive.RequiresFlag.class);
            if (jCRequires.isTransitive) {
                if (moduleSymbolLookupModule == Modules.this.syms.java_base && !Modules.this.preview.participatesInPreview(Modules.this.syms, this.sym) && Modules.this.source.compareTo(Source.JDK10) >= 0) {
                    Modules.this.preview.checkSourceLevel(jCRequires.pos(), Source.Feature.JAVA_BASE_TRANSITIVE);
                }
                enumSetNoneOf.add(Directive.RequiresFlag.TRANSITIVE);
            }
            if (jCRequires.isStaticPhase) {
                if (moduleSymbolLookupModule != Modules.this.syms.java_base || Modules.this.source.compareTo(Source.JDK10) < 0) {
                    enumSetNoneOf.add(Directive.RequiresFlag.STATIC_PHASE);
                } else {
                    Modules.this.log.error(jCRequires.pos(), CompilerProperties.Errors.ModNotAllowedHere(EnumSet.of(FlagsEnum.STATIC)));
                }
            }
            Directive.RequiresDirective requiresDirective = new Directive.RequiresDirective(moduleSymbolLookupModule, enumSetNoneOf);
            jCRequires.directive = requiresDirective;
            Symbol.ModuleSymbol moduleSymbol = this.sym;
            moduleSymbol.requires = moduleSymbol.requires.prepend(requiresDirective);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUses(JCTree.JCUses jCUses) {
        }
    }

    public interface PackageNameFinder {
        Name findPackageNameOf(JavaFileObject javaFileObject);
    }

    public class UsesProvidesVisitor extends JCTree.Visitor {
        private final Env<AttrContext> env;
        private final Symbol.ModuleSymbol msym;
        private final Set<Symbol.ClassSymbol> allUses = new HashSet();
        private final Map<Symbol.ClassSymbol, Set<Symbol.ClassSymbol>> allProvides = new HashMap();
        Map<Directive.ProvidesDirective, JCTree.JCProvides> directiveToTreeMap = new HashMap();

        public UsesProvidesVisitor(Symbol.ModuleSymbol moduleSymbol, Env<AttrContext> env) {
            this.msym = moduleSymbol;
            this.env = env;
        }

        public static /* synthetic */ void a(UsesProvidesVisitor usesProvidesVisitor, JCTree.JCDirective jCDirective) {
            usesProvidesVisitor.getClass();
            jCDirective.accept(usesProvidesVisitor);
        }

        public static /* synthetic */ Set b(Symbol.ClassSymbol classSymbol) {
            return new HashSet();
        }

        public static /* synthetic */ boolean c(Symbol symbol) {
            return symbol.kind == Kinds.Kind.MTH;
        }

        private void checkForCorrectness() {
            for (Directive.ProvidesDirective providesDirective : this.msym.provides) {
                JCTree.JCProvides jCProvides = this.directiveToTreeMap.get(providesDirective);
                Iterator<Symbol.ClassSymbol> it = providesDirective.impls.iterator();
                while (it.hasNext()) {
                    Symbol.PackageSymbol packageSymbolPackge = it.next().packge();
                    if (packageSymbolPackge.modle != this.msym) {
                        Modules.this.log.error(jCProvides.pos(), CompilerProperties.Errors.ServiceImplementationNotInRightModule(packageSymbolPackge.modle));
                    }
                    Symbol.PackageSymbol packageSymbolPackge2 = providesDirective.service.packge();
                    Symbol.ModuleSymbol moduleSymbol = packageSymbolPackge2.modle;
                    Symbol.ModuleSymbol moduleSymbol2 = this.msym;
                    boolean z = true;
                    boolean z2 = false;
                    boolean z3 = moduleSymbol == moduleSymbol2;
                    boolean z4 = moduleSymbol2.visiblePackages.get(packageSymbolPackge2.fullname) == packageSymbolPackge2;
                    if (z3 && !z4) {
                        Iterator<Directive.ExportsDirective> it2 = this.msym.exports.iterator();
                        while (it2.hasNext()) {
                            if (packageSymbolPackge2 == it2.next().packge) {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            z2 = z;
                            break;
                        }
                        Iterator<Directive.UsesDirective> it3 = this.msym.uses.iterator();
                        do {
                            if (!it3.hasNext()) {
                                z2 = z;
                                break;
                            }
                        } while (providesDirective.service != it3.next().service);
                        if (z2) {
                            Modules.this.log.warning(jCProvides.pos(), CompilerProperties.Warnings.ServiceProvidedButNotExportedOrUsed(providesDirective.service));
                        }
                    }
                }
            }
        }

        public Symbol.MethodSymbol factoryMethod(Symbol.ClassSymbol classSymbol) {
            Iterator<Symbol> it = classSymbol.members().getSymbolsByName(Modules.this.names.provider, new Predicate() { // from class: com.sun.tools.javac.comp.s1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Modules.UsesProvidesVisitor.c((Symbol) obj);
                }
            }).iterator();
            while (it.hasNext()) {
                Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) it.next();
                if (methodSymbol.isStatic() && (methodSymbol.flags() & 1) != 0 && methodSymbol.params().isEmpty()) {
                    return methodSymbol;
                }
            }
            return null;
        }

        public Symbol.MethodSymbol noArgsConstructor(Symbol.ClassSymbol classSymbol) {
            Iterator<Symbol> it = classSymbol.members().getSymbolsByName(Modules.this.names.init).iterator();
            while (it.hasNext()) {
                Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) it.next();
                if (methodSymbol.params().isEmpty()) {
                    return methodSymbol;
                }
            }
            return null;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitExports(JCTree.JCExports jCExports) {
            boolean z;
            boolean z2;
            Iterable<Symbol> symbols = jCExports.directive.packge.members().getSymbols();
            List listNil = List.nil();
            Iterator<Symbol> it = symbols.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                Symbol next = it.next();
                if (next.kind == Kinds.Kind.TYP) {
                    Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) next;
                    if (next.completer.isTerminal() || classSymbol.classfile.getKind() == JavaFileObject.Kind.CLASS) {
                        listNil = List.nil();
                        z2 = true;
                        break;
                    } else if (classSymbol.classfile.getKind() == JavaFileObject.Kind.SOURCE) {
                        listNil = listNil.prepend(classSymbol.classfile);
                    }
                }
            }
            Iterator it2 = listNil.iterator();
            do {
                if (!it2.hasNext()) {
                    z = z2;
                    break;
                }
            } while (Modules.this.findPackageInFile.findPackageNameOf((JavaFileObject) it2.next()) != jCExports.directive.packge.fullname);
            if (!z) {
                Modules.this.log.error(jCExports.qualid.pos(), CompilerProperties.Errors.PackageEmptyOrNotFound(jCExports.directive.packge));
            }
            Symbol.ModuleSymbol moduleSymbol = this.msym;
            moduleSymbol.directives = moduleSymbol.directives.prepend(jCExports.directive);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
            this.msym.directives = List.nil();
            this.msym.provides = List.nil();
            this.msym.uses = List.nil();
            jCModuleDecl.directives.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.t1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Modules.UsesProvidesVisitor.a(this.b, (JCTree.JCDirective) obj);
                }
            });
            Symbol.ModuleSymbol moduleSymbol = this.msym;
            moduleSymbol.directives = moduleSymbol.directives.reverse();
            Symbol.ModuleSymbol moduleSymbol2 = this.msym;
            moduleSymbol2.provides = moduleSymbol2.provides.reverse();
            Symbol.ModuleSymbol moduleSymbol3 = this.msym;
            moduleSymbol3.uses = moduleSymbol3.uses.reverse();
            if (this.msym.requires.nonEmpty() && this.msym.requires.head.flags.contains(Directive.RequiresFlag.MANDATED)) {
                Symbol.ModuleSymbol moduleSymbol4 = this.msym;
                moduleSymbol4.directives = moduleSymbol4.directives.prepend(moduleSymbol4.requires.head);
            }
            Symbol.ModuleSymbol moduleSymbol5 = this.msym;
            moduleSymbol5.directives = moduleSymbol5.directives.appendList(List.from((Iterable) Modules.this.addReads.getOrDefault(this.msym, Collections.EMPTY_SET)));
            checkForCorrectness();
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitOpens(JCTree.JCOpens jCOpens) {
            Modules.this.chk.checkPackageExistsForOpens(jCOpens.qualid, jCOpens.directive.packge);
            Symbol.ModuleSymbol moduleSymbol = this.msym;
            moduleSymbol.directives = moduleSymbol.directives.prepend(jCOpens.directive);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitProvides(JCTree.JCProvides jCProvides) {
            Type typeAttribType = Modules.this.attr.attribType(jCProvides.serviceName, this.env, Modules.this.syms.objectType);
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) typeAttribType.tsym;
            if (this.allProvides.containsKey(classSymbol)) {
                Modules.this.log.error(jCProvides.serviceName.pos(), CompilerProperties.Errors.RepeatedProvidesForService(classSymbol));
            }
            ListBuffer listBuffer = new ListBuffer();
            for (JCTree.JCExpression jCExpression : jCProvides.implNames) {
                AttrContext attrContext = this.env.info;
                boolean z = attrContext.visitingServiceImplementation;
                try {
                    attrContext.visitingServiceImplementation = true;
                    Type typeAttribType2 = Modules.this.attr.attribType(jCExpression, this.env, Modules.this.syms.objectType);
                    this.env.info.visitingServiceImplementation = z;
                    TypeTag typeTag = TypeTag.CLASS;
                    if (typeAttribType2.hasTag(typeTag)) {
                        Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) typeAttribType2.tsym;
                        if ((classSymbol2.flags_field & 1) == 0) {
                            Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.NotDefPublic(classSymbol2, classSymbol2.location()));
                        }
                        Symbol.MethodSymbol methodSymbolFactoryMethod = factoryMethod(classSymbol2);
                        if (methodSymbolFactoryMethod != null) {
                            if (!Modules.this.types.isSubtype(methodSymbolFactoryMethod.type.mo73getReturnType(), typeAttribType)) {
                                Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ServiceImplementationProviderReturnMustBeSubtypeOfServiceInterface);
                            }
                        } else if (!Modules.this.types.isSubtype(typeAttribType2, typeAttribType)) {
                            Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ServiceImplementationMustBeSubtypeOfServiceInterface);
                        } else if ((classSymbol2.flags() & 1024) != 0) {
                            Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ServiceImplementationIsAbstract(classSymbol2));
                        } else if (classSymbol2.isInner()) {
                            Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ServiceImplementationIsInner(classSymbol2));
                        } else {
                            Symbol.MethodSymbol methodSymbolNoArgsConstructor = noArgsConstructor(classSymbol2);
                            if (methodSymbolNoArgsConstructor == null) {
                                Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ServiceImplementationDoesntHaveANoArgsConstructor(classSymbol2));
                            } else if ((methodSymbolNoArgsConstructor.flags() & 1) == 0) {
                                Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.ServiceImplementationNoArgsConstructorNotPublic(classSymbol2));
                            }
                        }
                        if (typeAttribType2.hasTag(typeTag)) {
                            if (this.allProvides.computeIfAbsent(classSymbol, new Function() { // from class: com.sun.tools.javac.comp.r1
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return Modules.UsesProvidesVisitor.b((Symbol.ClassSymbol) obj);
                                }
                            }).add(classSymbol2)) {
                                listBuffer.append(classSymbol2);
                            } else {
                                Modules.this.log.error(jCExpression.pos(), CompilerProperties.Errors.DuplicateProvides(classSymbol, classSymbol2));
                            }
                        }
                    }
                } catch (Throwable th) {
                    this.env.info.visitingServiceImplementation = z;
                    throw th;
                }
            }
            if (!typeAttribType.hasTag(TypeTag.CLASS) || listBuffer.isEmpty()) {
                return;
            }
            Directive.ProvidesDirective providesDirective = new Directive.ProvidesDirective(classSymbol, listBuffer.toList());
            Symbol.ModuleSymbol moduleSymbol = this.msym;
            moduleSymbol.provides = moduleSymbol.provides.prepend(providesDirective);
            Symbol.ModuleSymbol moduleSymbol2 = this.msym;
            moduleSymbol2.directives = moduleSymbol2.directives.prepend(providesDirective);
            this.directiveToTreeMap.put(providesDirective, jCProvides);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitRequires(JCTree.JCRequires jCRequires) {
            if (jCRequires.directive == null || !Modules.this.allModules().contains(jCRequires.directive.module)) {
                return;
            }
            Modules.this.chk.checkDeprecated(jCRequires.moduleName.pos(), this.msym, jCRequires.directive.module);
            Modules.this.chk.checkPreview(jCRequires.moduleName.pos(), this.msym, jCRequires.directive.module);
            Modules.this.chk.checkModuleRequires(jCRequires.moduleName.pos(), jCRequires.directive);
            Symbol.ModuleSymbol moduleSymbol = this.msym;
            moduleSymbol.directives = moduleSymbol.directives.prepend(jCRequires.directive);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUses(JCTree.JCUses jCUses) {
            Type typeAttribType = Modules.this.attr.attribType(jCUses.qualid, this.env, Modules.this.syms.objectType);
            if ((TreeInfo.symbol(jCUses.qualid).flags() & 16384) != 0) {
                Modules.this.log.error(jCUses.qualid.pos(), CompilerProperties.Errors.ServiceDefinitionIsEnum(typeAttribType.tsym));
                return;
            }
            if (typeAttribType.hasTag(TypeTag.CLASS)) {
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) typeAttribType.tsym;
                if (!this.allUses.add(classSymbol)) {
                    Modules.this.log.error(jCUses.pos(), CompilerProperties.Errors.DuplicateUses(classSymbol));
                    return;
                }
                Directive.UsesDirective usesDirective = new Directive.UsesDirective(classSymbol);
                Symbol.ModuleSymbol moduleSymbol = this.msym;
                moduleSymbol.uses = moduleSymbol.uses.prepend(usesDirective);
                Symbol.ModuleSymbol moduleSymbol2 = this.msym;
                moduleSymbol2.directives = moduleSymbol2.directives.prepend(usesDirective);
            }
        }
    }

    public Modules(Context context) {
        context.put((Class<Modules>) Modules.class, this);
        this.log = Log.instance(context);
        this.lint = Lint.instance(context);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.syms = Symtab.instance(context);
        this.attr = Attr.instance(context);
        this.chk = Check.instance(context);
        this.preview = Preview.instance(context);
        this.typeEnvs = TypeEnvs.instance(context);
        this.moduleFinder = ModuleFinder.instance(context);
        this.types = Types.instance(context);
        JavaFileManager javaFileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.fileManager = javaFileManager;
        Source sourceInstance = Source.instance(context);
        this.source = sourceInstance;
        this.target = Target.instance(context);
        this.allowModules = Source.Feature.MODULES.allowedInSource(sourceInstance);
        Options optionsInstance = Options.instance(context);
        this.allowAccessIntoSystem = optionsInstance.isUnset(Option.RELEASE);
        boolean zHasLocation = javaFileManager.hasLocation(StandardLocation.MODULE_SOURCE_PATH);
        this.multiModuleMode = zHasLocation;
        ClassWriter.instance(context).multiModuleMode = zHasLocation;
        JNIWriter.instance(context).multiModuleMode = zHasLocation;
        this.java_se = namesInstance.fromString("java.se");
        this.java_ = namesInstance.fromString("java.");
        this.addExportsOpt = optionsInstance.get(Option.ADD_EXPORTS);
        this.addReadsOpt = optionsInstance.get(Option.ADD_READS);
        this.addModsOpt = optionsInstance.get(Option.ADD_MODULES);
        this.limitModsOpt = optionsInstance.get(Option.LIMIT_MODULES);
        this.moduleVersionOpt = optionsInstance.get(Option.MODULE_VERSION);
        this.sourceLauncher = optionsInstance.isSet("sourceLauncher");
    }

    public static /* synthetic */ boolean a(Symbol.ModuleSymbol moduleSymbol) {
        return true;
    }

    private void addVisiblePackages(Symbol.ModuleSymbol moduleSymbol, Map<Name, Symbol.ModuleSymbol> map, Symbol.ModuleSymbol moduleSymbol2, Collection<Directive.ExportsDirective> collection) {
        for (Directive.ExportsDirective exportsDirective : collection) {
            List<Symbol.ModuleSymbol> list = exportsDirective.modules;
            if (list == null || list.contains(moduleSymbol)) {
                Name name = exportsDirective.packge.fullname;
                Symbol.ModuleSymbol moduleSymbol3 = map.get(name);
                if (moduleSymbol3 == null || moduleSymbol3 == moduleSymbol2) {
                    map.put(name, moduleSymbol2);
                    Map<Name, Symbol.PackageSymbol> map2 = moduleSymbol.visiblePackages;
                    Symbol.PackageSymbol packageSymbol = exportsDirective.packge;
                    map2.put(packageSymbol.fullname, packageSymbol);
                } else {
                    Env<AttrContext> env = this.typeEnvs.get(moduleSymbol);
                    JavaFileObject javaFileObjectUseSource = env != null ? this.log.useSource(env.toplevel.sourcefile) : null;
                    JCDiagnostic.DiagnosticPosition diagnosticPositionPos = env != null ? env.tree.pos() : null;
                    try {
                        boolean zIsUnnamed = moduleSymbol.isUnnamed();
                        Log log = this.log;
                        if (zIsUnnamed) {
                            log.error(diagnosticPositionPos, CompilerProperties.Errors.PackageClashFromRequiresInUnnamed(name, moduleSymbol3, moduleSymbol2));
                        } else {
                            log.error(diagnosticPositionPos, CompilerProperties.Errors.PackageClashFromRequires(moduleSymbol, name, moduleSymbol3, moduleSymbol2));
                        }
                        if (env != null) {
                            this.log.useSource(javaFileObjectUseSource);
                        }
                    } catch (Throwable th) {
                        if (env != null) {
                            this.log.useSource(javaFileObjectUseSource);
                        }
                        throw th;
                    }
                }
            }
        }
    }

    public static /* synthetic */ void b(Modules modules, Symbol symbol) {
        modules.getClass();
        modules.completeModule((Symbol.ModuleSymbol) symbol);
    }

    public static /* synthetic */ void c(Modules modules, Symbol.ModuleSymbol moduleSymbol, Map map, Symbol.ModuleSymbol moduleSymbol2, Set set) {
        modules.getClass();
        if (moduleSymbol.readModules.contains(moduleSymbol2)) {
            modules.addVisiblePackages(moduleSymbol, map, moduleSymbol2, set);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void checkCyclicDependencies(JCTree.JCModuleDecl jCModuleDecl) {
        for (JCTree.JCDirective jCDirective : jCModuleDecl.directives) {
            if (jCDirective.hasTag(JCTree.Tag.REQUIRES)) {
                JCTree.JCRequires jCRequires = (JCTree.JCRequires) jCDirective;
                if (jCRequires.directive != null) {
                    HashSet hashSet = new HashSet();
                    List listOf = List.of(jCRequires.directive.module);
                    while (listOf.nonEmpty()) {
                        final Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) listOf.head;
                        listOf = listOf.tail;
                        if (hashSet.add(moduleSymbol)) {
                            moduleSymbol.complete();
                            if ((moduleSymbol.flags() & 4503599627370496L) == 0) {
                                Assert.checkNonNull(moduleSymbol.requires, (Supplier<String>) new Supplier() { // from class: o6a
                                    @Override // java.util.function.Supplier
                                    public final Object get() {
                                        return moduleSymbol.toString();
                                    }
                                });
                                for (Directive.RequiresDirective requiresDirective : moduleSymbol.requires) {
                                    if (!requiresDirective.flags.contains(Directive.RequiresFlag.EXTRA)) {
                                        listOf = listOf.prepend(requiresDirective.module);
                                    }
                                }
                            }
                        }
                    }
                    if (hashSet.contains(jCModuleDecl.sym)) {
                        this.log.error(jCRequires.moduleName.pos(), CompilerProperties.Errors.CyclicRequires(jCRequires.directive.module));
                    }
                }
            }
        }
    }

    private void checkNoAllModulePath() {
        String str = this.addModsOpt;
        if (str == null || !Arrays.asList(str.split(",")).contains(ALL_MODULE_PATH)) {
            return;
        }
        this.log.error(CompilerProperties.Errors.AddmodsAllModulePathInvalid);
    }

    private void checkSourceLocation(JCTree.JCCompilationUnit jCCompilationUnit, Symbol.ModuleSymbol moduleSymbol) {
        try {
            JavaFileObject javaFileObject = jCCompilationUnit.sourcefile;
            if (this.fileManager.contains(moduleSymbol.sourceLocation, javaFileObject)) {
                return;
            }
            JavaFileManager.Location location = moduleSymbol.patchLocation;
            if (location == null || !this.fileManager.contains(location, javaFileObject)) {
                JavaFileManager javaFileManager = this.fileManager;
                StandardLocation standardLocation = StandardLocation.SOURCE_OUTPUT;
                boolean zHasLocation = javaFileManager.hasLocation(standardLocation);
                JavaFileManager javaFileManager2 = this.fileManager;
                if (zHasLocation) {
                    if (javaFileManager2.contains(standardLocation, javaFileObject)) {
                        return;
                    }
                } else if (javaFileManager2.contains(StandardLocation.CLASS_OUTPUT, javaFileObject)) {
                    return;
                }
                JavaFileObject javaFileObjectUseSource = this.log.useSource(jCCompilationUnit.sourcefile);
                try {
                    this.log.error(jCCompilationUnit.pos(), CompilerProperties.Errors.FileSbOnSourceOrPatchPathForModule);
                } finally {
                    this.log.useSource(javaFileObjectUseSource);
                }
            }
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    private void completeAutomaticModule(Symbol.ModuleSymbol moduleSymbol) throws Symbol.CompletionFailure {
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.addAll(moduleSymbol.directives);
        ListBuffer listBuffer2 = new ListBuffer();
        for (Symbol.ModuleSymbol moduleSymbol2 : allModules()) {
            if (moduleSymbol2 != this.syms.unnamedModule && moduleSymbol2 != moduleSymbol) {
                Directive.RequiresDirective requiresDirective = new Directive.RequiresDirective(moduleSymbol2, (moduleSymbol2.flags_field & 4503599627370496L) != 0 ? EnumSet.of(Directive.RequiresFlag.TRANSITIVE) : EnumSet.noneOf(Directive.RequiresFlag.class));
                listBuffer.add(requiresDirective);
                listBuffer2.add(requiresDirective);
            }
        }
        Directive.RequiresDirective requiresDirective2 = new Directive.RequiresDirective(this.syms.unnamedModule);
        listBuffer.add(requiresDirective2);
        listBuffer2.add(requiresDirective2);
        moduleSymbol.requires = listBuffer2.toList();
        moduleSymbol.directives = listBuffer.toList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void completeModule(final Symbol.ModuleSymbol moduleSymbol) {
        if (this.inInitModules) {
            moduleSymbol.completer = new Symbol.Completer() { // from class: e6a
                @Override // com.sun.tools.javac.code.Symbol.Completer
                public final void complete(Symbol symbol) {
                    this.b.completeModule(moduleSymbol);
                }
            };
            return;
        }
        if ((moduleSymbol.flags_field & 4503599627370496L) != 0) {
            completeAutomaticModule(moduleSymbol);
        }
        Assert.checkNonNull(moduleSymbol.requires);
        initAddReads();
        List listAppendList = moduleSymbol.requires.appendList(List.from(this.addReads.getOrDefault(moduleSymbol, Collections.EMPTY_SET)));
        moduleSymbol.requires = listAppendList;
        while (listAppendList.nonEmpty()) {
            if (!allModules().contains(((Directive.RequiresDirective) listAppendList.head).module)) {
                Env<AttrContext> env = this.typeEnvs.get(moduleSymbol);
                if (env != null) {
                    JavaFileObject javaFileObjectUseSource = this.log.useSource(env.toplevel.sourcefile);
                    try {
                        this.log.error(env.tree, CompilerProperties.Errors.ModuleNotFound(((Directive.RequiresDirective) listAppendList.head).module));
                        this.log.useSource(javaFileObjectUseSource);
                    } catch (Throwable th) {
                        this.log.useSource(javaFileObjectUseSource);
                        throw th;
                    }
                } else {
                    Assert.check((moduleSymbol.flags() & 4503599627370496L) == 0);
                }
                moduleSymbol.requires = List.filter(moduleSymbol.requires, (Directive.RequiresDirective) listAppendList.head);
            }
            listAppendList = listAppendList.tail;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        HashSet hashSet = new HashSet();
        for (final Directive.RequiresDirective requiresDirective : moduleSymbol.requires) {
            requiresDirective.module.complete();
            linkedHashSet.add(requiresDirective.module);
            Set<Symbol.ModuleSymbol> setRetrieveRequiresTransitive = retrieveRequiresTransitive(requiresDirective.module);
            Assert.checkNonNull(setRetrieveRequiresTransitive, (Supplier<String>) new Supplier() { // from class: n6a
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Modules.p(requiresDirective);
                }
            });
            linkedHashSet.addAll(setRetrieveRequiresTransitive);
            if (requiresDirective.flags.contains(Directive.RequiresFlag.TRANSITIVE)) {
                hashSet.add(requiresDirective.module);
                hashSet.addAll(setRetrieveRequiresTransitive);
            }
        }
        this.requiresTransitiveCache.put(moduleSymbol, hashSet);
        initVisiblePackages(moduleSymbol, linkedHashSet);
        Iterator<Directive.ExportsDirective> it = moduleSymbol.exports.iterator();
        while (it.hasNext()) {
            Symbol.PackageSymbol packageSymbol = it.next().packge;
            if (packageSymbol != null) {
                packageSymbol.modle = moduleSymbol;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Set<Symbol.ModuleSymbol> computeTransitiveClosure(Set<? extends Symbol.ModuleSymbol> set, Set<? extends Symbol.ModuleSymbol> set2, Set<Symbol.ModuleSymbol> set3) {
        Symbol.ModuleSymbol moduleSymbol;
        boolean z;
        List listNil = List.nil();
        List listNil2 = List.nil();
        for (Symbol.ModuleSymbol moduleSymbol2 : set) {
            if (set2.contains(moduleSymbol2)) {
                listNil = listNil.prepend(moduleSymbol2);
            } else {
                listNil2 = listNil2.prepend(moduleSymbol2);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(this.syms.java_base);
        while (true) {
            if (!listNil.nonEmpty() && !listNil2.nonEmpty()) {
                return linkedHashSet;
            }
            try {
                if (listNil.nonEmpty()) {
                    moduleSymbol = (Symbol.ModuleSymbol) listNil.head;
                    listNil = listNil.tail;
                    z = true;
                } else {
                    moduleSymbol = (Symbol.ModuleSymbol) listNil2.head;
                    listNil2 = listNil2.tail;
                    z = false;
                }
                if (set3 == null || set3.contains(moduleSymbol)) {
                    if (linkedHashSet.add(moduleSymbol) && moduleSymbol != this.syms.unnamedModule && (moduleSymbol.flags_field & 4503599627370496L) == 0) {
                        moduleSymbol.complete();
                        if (moduleSymbol.kind == Kinds.Kind.ERR && ((z || set.contains(moduleSymbol)) && this.warnedMissing.add(moduleSymbol))) {
                            this.log.error(CompilerProperties.Errors.ModuleNotFound(moduleSymbol));
                        }
                        for (Directive.RequiresDirective requiresDirective : moduleSymbol.requires) {
                            if (requiresDirective.module != this.syms.java_base) {
                                if ((requiresDirective.isTransitive() && z) || set2.contains(moduleSymbol)) {
                                    listNil = listNil.prepend(requiresDirective.module);
                                } else {
                                    listNil2 = listNil2.prepend(requiresDirective.module);
                                }
                            }
                        }
                    }
                }
            } catch (Symbol.CompletionFailure e) {
                this.chk.completionError(null, e);
            }
        }
    }

    public static /* synthetic */ Set d(Symbol.ModuleSymbol moduleSymbol) {
        return new HashSet();
    }

    public static /* synthetic */ boolean e(Modules modules, Symbol.ModuleSymbol moduleSymbol) {
        modules.getClass();
        moduleSymbol.complete();
        return !moduleSymbol.name.startsWith(modules.java_) && moduleSymbol.exports.stream().anyMatch(new Predicate() { // from class: m6a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Modules.t((Directive.ExportsDirective) obj);
            }
        });
    }

    private boolean enter(List<JCTree.JCCompilationUnit> list, Function<Set<Symbol.ModuleSymbol>, Set<Symbol.ModuleSymbol>> function, Symbol.ClassSymbol classSymbol) {
        if (!this.allowModules) {
            Iterator<JCTree.JCCompilationUnit> it = list.iterator();
            while (it.hasNext()) {
                it.next().modle = this.syms.noModule;
            }
            this.defaultModule = this.syms.noModule;
            return true;
        }
        int i = this.log.nerrors;
        this.depth++;
        try {
            try {
                Set<Symbol.ModuleSymbol> setEnterModules = enterModules(list, classSymbol);
                setCompilationUnitModules(list, setEnterModules, classSymbol);
                for (Symbol.ModuleSymbol moduleSymbol : function.apply(setEnterModules)) {
                    Symbol.ModuleSymbol moduleSymbol2 = this.syms.unnamedModule;
                    if (moduleSymbol != moduleSymbol2 || setEnterModules.contains(moduleSymbol2)) {
                        moduleSymbol.complete();
                    }
                }
            } catch (Symbol.CompletionFailure e) {
                this.chk.completionError(null, e);
            }
            this.depth--;
            return this.log.nerrors == i;
        } catch (Throwable th) {
            this.depth--;
            throw th;
        }
    }

    private void enterModule(JCTree.JCCompilationUnit jCCompilationUnit, Symbol.ClassSymbol classSymbol, Set<Symbol.ModuleSymbol> set) {
        JCTree jCTree;
        Symbol.ModuleSymbol moduleSymbolEnterModule;
        boolean zIsNameCompatible = jCCompilationUnit.sourcefile.isNameCompatible("module-info", JavaFileObject.Kind.SOURCE);
        if (jCCompilationUnit.getModuleDecl() == null) {
            if (zIsNameCompatible && this.multiModuleMode) {
                if (!jCCompilationUnit.defs.isEmpty()) {
                    jCTree = jCCompilationUnit;
                    jCTree = jCCompilationUnit.defs.head;
                }
                jCTree = jCCompilationUnit;
                this.log.error(jCTree.pos(), CompilerProperties.Errors.ExpectedModule);
                return;
            }
            return;
        }
        JCTree.JCModuleDecl moduleDecl = jCCompilationUnit.getModuleDecl();
        if (!zIsNameCompatible) {
            this.log.error(moduleDecl.pos(), CompilerProperties.Errors.ModuleDeclSbInModuleInfoJava);
        }
        Name nameFullName = TreeInfo.fullName(moduleDecl.qualId);
        if (classSymbol != null) {
            moduleSymbolEnterModule = (Symbol.ModuleSymbol) classSymbol.owner;
            Assert.checkNonNull(moduleSymbolEnterModule.name);
            if (moduleSymbolEnterModule.name != TreeInfo.fullName(moduleDecl.qualId)) {
                this.log.error(moduleDecl.pos(), CompilerProperties.Errors.ModuleNameMismatch(nameFullName, moduleSymbolEnterModule.name));
            }
        } else {
            moduleSymbolEnterModule = this.syms.enterModule(nameFullName);
            JavaFileObject javaFileObject = moduleSymbolEnterModule.module_info.sourcefile;
            if (javaFileObject != null && javaFileObject != jCCompilationUnit.sourcefile) {
                moduleDecl.sym = this.syms.errModule;
                this.log.error(moduleDecl.pos(), CompilerProperties.Errors.DuplicateModule(moduleSymbolEnterModule));
                return;
            }
        }
        moduleSymbolEnterModule.completer = getSourceCompleter(jCCompilationUnit);
        Symbol.ClassSymbol classSymbol2 = moduleSymbolEnterModule.module_info;
        JavaFileObject javaFileObject2 = jCCompilationUnit.sourcefile;
        classSymbol2.sourcefile = javaFileObject2;
        classSymbol2.classfile = javaFileObject2;
        moduleDecl.sym = moduleSymbolEnterModule;
        if (this.multiModuleMode || set.isEmpty()) {
            set.add(moduleSymbolEnterModule);
        } else {
            this.log.error(jCCompilationUnit.pos(), CompilerProperties.Errors.TooManyModules);
        }
        Env<AttrContext> env = new Env<>(moduleDecl, null);
        env.toplevel = jCCompilationUnit;
        this.typeEnvs.put(moduleSymbolEnterModule, env);
    }

    private Set<Symbol.ModuleSymbol> enterModules(List<JCTree.JCCompilationUnit> list, Symbol.ClassSymbol classSymbol) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (JCTree.JCCompilationUnit jCCompilationUnit : list) {
            JavaFileObject javaFileObjectUseSource = this.log.useSource(jCCompilationUnit.sourcefile);
            try {
                enterModule(jCCompilationUnit, classSymbol, linkedHashSet);
                this.log.useSource(javaFileObjectUseSource);
            } catch (Throwable th) {
                this.log.useSource(javaFileObjectUseSource);
                throw th;
            }
        }
        return linkedHashSet;
    }

    private Stream<String> filterAlreadyWarnedIncubatorModules(Stream<String> stream) {
        if (!this.sourceLauncher) {
            return stream;
        }
        final Set set = (Set) ModuleWrapper.ModuleLayer.boot().modules().stream().map(new Function() { // from class: u6a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ModuleWrapper) obj).getName();
            }
        }).collect(Collectors.toSet());
        return stream.filter(new Predicate() { // from class: u5a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Modules.s(set, (String) obj);
            }
        });
    }

    public static /* synthetic */ Set g(Modules modules, Set set) {
        modules.syms.java_base.complete();
        return set;
    }

    private JavaFileManager.Location getModuleLocation(JCTree.JCCompilationUnit jCCompilationUnit) throws IOException {
        JavaFileObject javaFileObject = jCCompilationUnit.sourcefile;
        JavaFileManager.Location locationForModule = this.fileManager.getLocationForModule(StandardLocation.MODULE_SOURCE_PATH, javaFileObject);
        if (locationForModule != null) {
            return locationForModule;
        }
        JavaFileManager javaFileManager = this.fileManager;
        StandardLocation standardLocation = StandardLocation.SOURCE_OUTPUT;
        if (!javaFileManager.hasLocation(standardLocation)) {
            standardLocation = StandardLocation.CLASS_OUTPUT;
        }
        return this.fileManager.getLocationForModule(standardLocation, javaFileObject);
    }

    private Symbol.Completer getSourceCompleter(final JCTree.JCCompilationUnit jCCompilationUnit) {
        return new Symbol.Completer(this) { // from class: com.sun.tools.javac.comp.Modules.2
            final /* synthetic */ Modules this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.code.Symbol.Completer
            public void complete(Symbol symbol) throws Symbol.CompletionFailure {
                Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) symbol;
                moduleSymbol.flags_field |= 268435456;
                ModuleVisitor moduleVisitor = this.this$0.new ModuleVisitor();
                JavaFileObject javaFileObjectUseSource = this.this$0.log.useSource(jCCompilationUnit.sourcefile);
                JCTree.JCModuleDecl moduleDecl = jCCompilationUnit.getModuleDecl();
                try {
                    moduleDecl.accept(moduleVisitor);
                    this.this$0.completeModule(moduleSymbol);
                    this.this$0.checkCyclicDependencies(moduleDecl);
                } finally {
                    this.this$0.log.useSource(javaFileObjectUseSource);
                    moduleSymbol.flags_field &= -268435457;
                }
            }

            public String toString() {
                return "SourceCompleter: " + jCCompilationUnit.sourcefile.getName();
            }
        };
    }

    private Symbol.Completer getUnnamedModuleCompleter() {
        this.moduleFinder.findAllModules();
        return new Symbol.Completer() { // from class: com.sun.tools.javac.comp.Modules.3
            @Override // com.sun.tools.javac.code.Symbol.Completer
            public void complete(Symbol symbol) throws Symbol.CompletionFailure {
                if (Modules.this.inInitModules) {
                    symbol.completer = this;
                    return;
                }
                Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) symbol;
                HashSet hashSet = new HashSet(Modules.this.allModules());
                hashSet.remove(Modules.this.syms.unnamedModule);
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    ((Symbol.ModuleSymbol) it.next()).complete();
                }
                Modules.this.initVisiblePackages(moduleSymbol, hashSet);
            }

            public String toString() {
                return "unnamedModule Completer";
            }
        };
    }

    public static /* synthetic */ void h(Set set, Set set2, Symbol.ModuleSymbol moduleSymbol) {
        set.add(moduleSymbol);
        if (set2 != null) {
            set2.add(moduleSymbol);
        }
    }

    private void initAddExports() {
        Symbol.ModuleSymbol moduleSymbolEnterModule;
        if (this.addExports != null) {
            return;
        }
        this.addExports = new LinkedHashMap();
        Set<Symbol.ModuleSymbol> hashSet = new HashSet<>();
        if (this.addExportsOpt == null) {
            return;
        }
        Pattern patternCompile = Pattern.compile("([^/]+)/([^=]+)=(.*)");
        for (String str : this.addExportsOpt.split("\u0000+")) {
            if (str.length() != 0) {
                Matcher matcher = patternCompile.matcher(str);
                if (matcher.matches()) {
                    String strGroup = matcher.group(1);
                    String strGroup2 = matcher.group(2);
                    String strGroup3 = matcher.group(3);
                    if (isValidName(strGroup)) {
                        Symbol.ModuleSymbol moduleSymbolEnterModule2 = this.syms.enterModule(this.names.fromString(strGroup));
                        if (isKnownModule(moduleSymbolEnterModule2, hashSet) && isValidName(strGroup2)) {
                            if (this.allowAccessIntoSystem || (moduleSymbolEnterModule2.flags() & 9007199254740992L) == 0) {
                                Symbol.PackageSymbol packageSymbolEnterPackage = this.syms.enterPackage(moduleSymbolEnterModule2, this.names.fromString(strGroup2));
                                packageSymbolEnterPackage.modle = moduleSymbolEnterModule2;
                                List listNil = List.nil();
                                for (String str2 : strGroup3.split("[ ,]+")) {
                                    if (str2.equals("ALL-UNNAMED")) {
                                        moduleSymbolEnterModule = this.syms.unnamedModule;
                                    } else {
                                        if (isValidName(str2)) {
                                            moduleSymbolEnterModule = this.syms.enterModule(this.names.fromString(str2));
                                            if (!isKnownModule(moduleSymbolEnterModule, hashSet)) {
                                            }
                                        }
                                    }
                                    listNil = listNil.prepend(moduleSymbolEnterModule);
                                }
                                this.addExports.computeIfAbsent(moduleSymbolEnterModule2, new Function() { // from class: s6a
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj) {
                                        return Modules.n((Symbol.ModuleSymbol) obj);
                                    }
                                }).add(new Directive.ExportsDirective(packageSymbolEnterPackage, listNil));
                            } else {
                                this.log.error(CompilerProperties.Errors.AddExportsWithRelease(moduleSymbolEnterModule2));
                            }
                        }
                    }
                }
            }
        }
    }

    private void initAddReads() {
        Symbol.ModuleSymbol moduleSymbolEnterModule;
        if (this.addReads != null) {
            return;
        }
        this.addReads = new LinkedHashMap();
        if (this.addReadsOpt == null) {
            return;
        }
        Pattern patternCompile = Pattern.compile("([^=]+)=(.*)");
        for (String str : this.addReadsOpt.split("\u0000+")) {
            if (str.length() != 0) {
                Matcher matcher = patternCompile.matcher(str);
                if (matcher.matches()) {
                    String strGroup = matcher.group(1);
                    String strGroup2 = matcher.group(2);
                    if (isValidName(strGroup)) {
                        Symbol.ModuleSymbol moduleSymbolEnterModule2 = this.syms.enterModule(this.names.fromString(strGroup));
                        if (!this.allModules.contains(moduleSymbolEnterModule2)) {
                            this.log.warning(CompilerProperties.LintWarnings.ModuleForOptionNotFound(Option.ADD_READS, moduleSymbolEnterModule2));
                        } else if (this.allowAccessIntoSystem || (moduleSymbolEnterModule2.flags() & 9007199254740992L) == 0) {
                            for (String str2 : strGroup2.split("[ ,]+", -1)) {
                                if (str2.equals("ALL-UNNAMED")) {
                                    moduleSymbolEnterModule = this.syms.unnamedModule;
                                } else {
                                    if (isValidName(str2)) {
                                        moduleSymbolEnterModule = this.syms.enterModule(this.names.fromString(str2));
                                        if (!this.allModules.contains(moduleSymbolEnterModule)) {
                                            this.log.warning(CompilerProperties.LintWarnings.ModuleForOptionNotFound(Option.ADD_READS, moduleSymbolEnterModule));
                                        }
                                    }
                                }
                                this.addReads.computeIfAbsent(moduleSymbolEnterModule2, new Function() { // from class: t5a
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj) {
                                        return Modules.d((Symbol.ModuleSymbol) obj);
                                    }
                                }).add(new Directive.RequiresDirective(moduleSymbolEnterModule, EnumSet.of(Directive.RequiresFlag.EXTRA)));
                            }
                        } else {
                            this.log.error(CompilerProperties.Errors.AddReadsWithRelease(moduleSymbolEnterModule2));
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initVisiblePackages(final Symbol.ModuleSymbol moduleSymbol, Collection<Symbol.ModuleSymbol> collection) {
        initAddExports();
        moduleSymbol.visiblePackages = new LinkedHashMap();
        moduleSymbol.readModules = new HashSet(collection);
        final HashMap map = new HashMap();
        for (Symbol.ModuleSymbol moduleSymbol2 : collection) {
            if (moduleSymbol2 != this.syms.unnamedModule) {
                addVisiblePackages(moduleSymbol, map, moduleSymbol2, moduleSymbol2.exports);
            }
        }
        this.addExports.forEach(new BiConsumer() { // from class: k6a
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Modules.c(this.a, moduleSymbol, map, (Symbol.ModuleSymbol) obj, (Set) obj2);
            }
        });
        moduleSymbol.readModules.add(moduleSymbol);
    }

    public static Modules instance(Context context) {
        Modules modules = (Modules) context.get(Modules.class);
        return modules == null ? new Modules(context) : modules;
    }

    private boolean isKnownModule(Symbol.ModuleSymbol moduleSymbol, Set<Symbol.ModuleSymbol> set) {
        if (this.allModules.contains(moduleSymbol)) {
            return true;
        }
        if (set.contains(moduleSymbol)) {
            return false;
        }
        this.log.warning(CompilerProperties.LintWarnings.ModuleForOptionNotFound(Option.ADD_EXPORTS, moduleSymbol));
        set.add(moduleSymbol);
        return false;
    }

    private boolean isValidName(CharSequence charSequence) {
        return SourceVersion.isName(charSequence, Source.toSourceVersion(this.source));
    }

    public static /* synthetic */ boolean j(Symbol.ModuleSymbol moduleSymbol) {
        return (moduleSymbol.flags() & 9007199254740992L) != 0;
    }

    public static /* synthetic */ String k(Symbol.ModuleSymbol moduleSymbol, Symbol.ModuleSymbol moduleSymbol2) {
        return moduleSymbol + ".requires == null; " + moduleSymbol2;
    }

    public static /* synthetic */ void l(Modules modules, Symbol symbol) {
        modules.getClass();
        modules.completeModule((Symbol.ModuleSymbol) symbol);
    }

    public static /* synthetic */ boolean m(Modules modules, Set set, Symbol.ModuleSymbol moduleSymbol) {
        if (set == null) {
            return modules.moduleFinder.findModule(moduleSymbol).kind != Kinds.Kind.ERR;
        }
        modules.getClass();
        return set.contains(moduleSymbol);
    }

    public static /* synthetic */ Set n(Symbol.ModuleSymbol moduleSymbol) {
        return new LinkedHashSet();
    }

    public static /* synthetic */ boolean o(Directive.ExportsDirective exportsDirective) {
        return exportsDirective.modules == null;
    }

    public static /* synthetic */ String p(Directive.RequiresDirective requiresDirective) {
        return "no entry in cache for " + requiresDirective.module;
    }

    public static /* synthetic */ void q(Modules modules, Symbol symbol) {
        modules.getClass();
        Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) symbol;
        moduleSymbol.complete();
        Env<AttrContext> env = modules.typeEnvs.get(moduleSymbol);
        UsesProvidesVisitor usesProvidesVisitor = modules.new UsesProvidesVisitor(moduleSymbol, env);
        JavaFileObject javaFileObjectUseSource = modules.log.useSource(env.toplevel.sourcefile);
        try {
            env.toplevel.getModuleDecl().accept(usesProvidesVisitor);
        } finally {
            modules.log.useSource(javaFileObjectUseSource);
        }
    }

    public static /* synthetic */ boolean r(Symbol.ModuleSymbol moduleSymbol) {
        moduleSymbol.complete();
        return moduleSymbol.exports.stream().anyMatch(new Predicate() { // from class: v5a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Modules.o((Directive.ExportsDirective) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Set<Symbol.ModuleSymbol> retrieveRequiresTransitive(final Symbol.ModuleSymbol moduleSymbol) {
        Set<Symbol.ModuleSymbol> hashSet = this.requiresTransitiveCache.get(moduleSymbol);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            HashSet hashSet2 = new HashSet();
            List listOf = List.of(moduleSymbol);
            while (listOf.nonEmpty()) {
                final Symbol.ModuleSymbol moduleSymbol2 = (Symbol.ModuleSymbol) listOf.head;
                listOf = listOf.tail;
                if (hashSet2.add(moduleSymbol2)) {
                    hashSet.add(moduleSymbol2);
                    moduleSymbol2.complete();
                    if (moduleSymbol2 != this.syms.unnamedModule) {
                        Assert.checkNonNull(moduleSymbol2.requires, (Supplier<String>) new Supplier() { // from class: l6a
                            @Override // java.util.function.Supplier
                            public final Object get() {
                                return Modules.k(moduleSymbol2, moduleSymbol);
                            }
                        });
                        for (Directive.RequiresDirective requiresDirective : moduleSymbol2.requires) {
                            if (requiresDirective.isTransitive()) {
                                listOf = listOf.prepend(requiresDirective.module);
                            }
                        }
                    } else {
                        Iterator<Symbol.ModuleSymbol> it = allModules().iterator();
                        while (it.hasNext()) {
                            listOf = listOf.prepend(it.next());
                        }
                    }
                }
            }
            hashSet.remove(moduleSymbol);
        }
        return hashSet;
    }

    public static /* synthetic */ boolean s(Set set, String str) {
        return !set.contains(str);
    }

    /* JADX WARN: Code duplicated, block: B:64:0x0159 A[Catch: all -> 0x004e, IOException -> 0x0051, TRY_LEAVE, TryCatch #1 {IOException -> 0x0051, blocks: (B:11:0x0035, B:13:0x0043, B:20:0x0059, B:24:0x007a, B:26:0x007d, B:28:0x008b, B:31:0x009f, B:33:0x00a5, B:35:0x00b1, B:37:0x00bb, B:38:0x00c6, B:40:0x00d8, B:42:0x00e0, B:47:0x0105, B:49:0x0109, B:51:0x0113, B:52:0x0121, B:54:0x012b, B:56:0x013b, B:57:0x013e, B:58:0x0140, B:43:0x00ec, B:45:0x00f4, B:46:0x00ff, B:61:0x014a, B:63:0x0156, B:64:0x0159, B:67:0x0161, B:69:0x0174, B:68:0x016b), top: B:135:0x0035, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0161 A[Catch: all -> 0x004e, IOException -> 0x0051, TRY_ENTER, TryCatch #1 {IOException -> 0x0051, blocks: (B:11:0x0035, B:13:0x0043, B:20:0x0059, B:24:0x007a, B:26:0x007d, B:28:0x008b, B:31:0x009f, B:33:0x00a5, B:35:0x00b1, B:37:0x00bb, B:38:0x00c6, B:40:0x00d8, B:42:0x00e0, B:47:0x0105, B:49:0x0109, B:51:0x0113, B:52:0x0121, B:54:0x012b, B:56:0x013b, B:57:0x013e, B:58:0x0140, B:43:0x00ec, B:45:0x00f4, B:46:0x00ff, B:61:0x014a, B:63:0x0156, B:64:0x0159, B:67:0x0161, B:69:0x0174, B:68:0x016b), top: B:135:0x0035, outer: #3 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x016b A[Catch: all -> 0x004e, IOException -> 0x0051, TryCatch #1 {IOException -> 0x0051, blocks: (B:11:0x0035, B:13:0x0043, B:20:0x0059, B:24:0x007a, B:26:0x007d, B:28:0x008b, B:31:0x009f, B:33:0x00a5, B:35:0x00b1, B:37:0x00bb, B:38:0x00c6, B:40:0x00d8, B:42:0x00e0, B:47:0x0105, B:49:0x0109, B:51:0x0113, B:52:0x0121, B:54:0x012b, B:56:0x013b, B:57:0x013e, B:58:0x0140, B:43:0x00ec, B:45:0x00f4, B:46:0x00ff, B:61:0x014a, B:63:0x0156, B:64:0x0159, B:67:0x0161, B:69:0x0174, B:68:0x016b), top: B:135:0x0035, outer: #3 }] */
    private void setCompilationUnitModules(List<JCTree.JCCompilationUnit> list, Set<Symbol.ModuleSymbol> set, Symbol.ClassSymbol classSymbol) {
        long j;
        JavaFileManager.Location locationForModule;
        long j2;
        JCTree.JCModuleDecl moduleDecl;
        Log log;
        Symbol.ModuleSymbol moduleSymbolEnterModule;
        JavaFileObject javaFileForInput;
        Name nameFromString;
        long j3 = 4503599627370496L;
        Symbol.ModuleSymbol next = null;
        if (!this.multiModuleMode) {
            if (this.defaultModule == null) {
                String strSingleModuleOverride = singleModuleOverride(list);
                int size = set.size();
                if (size == 0) {
                    try {
                        this.defaultModule = this.moduleFinder.findSingleModule();
                    } catch (Symbol.CompletionFailure e) {
                        this.chk.completionError(null, e);
                        this.defaultModule = this.syms.unnamedModule;
                    }
                    Symbol.ModuleSymbol moduleSymbol = this.defaultModule;
                    if (moduleSymbol != this.syms.unnamedModule) {
                        checkNoAllModulePath();
                        this.defaultModule.complete();
                        this.defaultModule.completer = new Symbol.Completer() { // from class: p6a
                            @Override // com.sun.tools.javac.code.Symbol.Completer
                            public final void complete(Symbol symbol) {
                                Modules.b(this.b, symbol);
                            }
                        };
                        this.defaultModule.sourceLocation = StandardLocation.SOURCE_PATH;
                    } else if (strSingleModuleOverride != null) {
                        Symbol.ModuleSymbol moduleSymbolFindModule = this.moduleFinder.findModule(this.names.fromString(strSingleModuleOverride));
                        this.defaultModule = moduleSymbolFindModule;
                        moduleSymbolFindModule.patchOutputLocation = StandardLocation.CLASS_OUTPUT;
                        if ((moduleSymbolFindModule.flags_field & 4503599627370496L) == 0) {
                            checkNoAllModulePath();
                        }
                    } else {
                        moduleSymbol.completer = getUnnamedModuleCompleter();
                        Symbol.ModuleSymbol moduleSymbol2 = this.defaultModule;
                        moduleSymbol2.sourceLocation = StandardLocation.SOURCE_PATH;
                        moduleSymbol2.classLocation = StandardLocation.CLASS_PATH;
                    }
                    set.add(this.defaultModule);
                } else if (size != 1) {
                    Assert.error("too many modules");
                } else {
                    checkNoAllModulePath();
                    Symbol.ModuleSymbol next2 = set.iterator().next();
                    this.defaultModule = next2;
                    next2.sourceLocation = StandardLocation.SOURCE_PATH;
                    JavaFileManager javaFileManager = this.fileManager;
                    StandardLocation standardLocation = StandardLocation.PATCH_MODULE_PATH;
                    if (javaFileManager.hasLocation(standardLocation)) {
                        try {
                            Symbol.ModuleSymbol moduleSymbol3 = this.defaultModule;
                            moduleSymbol3.patchLocation = this.fileManager.getLocationForModule(standardLocation, moduleSymbol3.name.toString());
                        } catch (IOException e2) {
                            throw new Error(e2);
                        }
                    }
                    Symbol.ModuleSymbol moduleSymbol4 = this.defaultModule;
                    if (moduleSymbol4.patchLocation == null) {
                        moduleSymbol4.classLocation = StandardLocation.CLASS_OUTPUT;
                    } else {
                        moduleSymbol4.patchOutputLocation = StandardLocation.CLASS_OUTPUT;
                    }
                }
            } else if (set.size() == 1) {
                next = set.iterator().next();
                next.complete();
                next.completer = new Symbol.Completer() { // from class: q6a
                    @Override // com.sun.tools.javac.code.Symbol.Completer
                    public final void complete(Symbol symbol) {
                        Modules.l(this.b, symbol);
                    }
                };
            } else {
                Assert.check(set.isEmpty());
                Assert.checkNonNull(classSymbol);
                next = classSymbol.packge().modle;
                set.add(next);
            }
            Symbol.ModuleSymbol moduleSymbol5 = this.defaultModule;
            Symbol.ModuleSymbol moduleSymbol6 = this.syms.unnamedModule;
            if (moduleSymbol5 != moduleSymbol6) {
                moduleSymbol6.completer = getUnnamedModuleCompleter();
                this.syms.unnamedModule.classLocation = StandardLocation.CLASS_PATH;
            }
            if (next == null) {
                next = this.defaultModule;
            }
            for (JCTree.JCCompilationUnit jCCompilationUnit : list) {
                Symbol.ModuleSymbol moduleSymbol7 = this.defaultModule;
                if (moduleSymbol7 != this.syms.unnamedModule) {
                    JavaFileManager.Location location = moduleSymbol7.sourceLocation;
                    StandardLocation standardLocation2 = StandardLocation.SOURCE_PATH;
                    if (location == standardLocation2 && this.fileManager.hasLocation(standardLocation2)) {
                        checkSourceLocation(jCCompilationUnit, next);
                    }
                }
                jCCompilationUnit.modle = next;
            }
            return;
        }
        boolean z = false;
        for (JCTree.JCCompilationUnit jCCompilationUnit2 : list) {
            if (jCCompilationUnit2.defs.isEmpty()) {
                jCCompilationUnit2.modle = this.syms.unnamedModule;
            } else {
                JavaFileObject javaFileObjectUseSource = this.log.useSource(jCCompilationUnit2.sourcefile);
                try {
                    try {
                        JavaFileManager.Location moduleLocation = getModuleLocation(jCCompilationUnit2);
                        JavaFileManager javaFileManager2 = this.fileManager;
                        StandardLocation standardLocation3 = StandardLocation.PATCH_MODULE_PATH;
                        if (javaFileManager2.hasLocation(standardLocation3)) {
                            j = 0;
                            locationForModule = this.fileManager.getLocationForModule(standardLocation3, jCCompilationUnit2.sourcefile);
                        } else {
                            j = 0;
                            locationForModule = null;
                        }
                        if (locationForModule != null) {
                            Name nameFromString2 = this.names.fromString(this.fileManager.inferModuleName(locationForModule));
                            Symbol.ModuleSymbol moduleSymbolFindModule2 = this.moduleFinder.findModule(nameFromString2);
                            jCCompilationUnit2.modle = moduleSymbolFindModule2;
                            set.add(moduleSymbolFindModule2);
                            boolean z2 = ((moduleSymbolFindModule2.flags_field & j3) != j) | z;
                            if (moduleLocation != null && nameFromString2 != (nameFromString = this.names.fromString(this.fileManager.inferModuleName(moduleLocation)))) {
                                this.log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.FilePatchedAndMsp(nameFromString2, nameFromString));
                            }
                            z = z2;
                            j2 = j3;
                        } else if (moduleLocation != null) {
                            if (jCCompilationUnit2.getModuleDecl() != null && ((javaFileForInput = this.fileManager.getJavaFileForInput(moduleLocation, "module-info", JavaFileObject.Kind.SOURCE)) == null || !this.fileManager.isSameFile(javaFileForInput, jCCompilationUnit2.sourcefile))) {
                                this.log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.ModuleNotFoundOnModuleSourcePath);
                            }
                            Name nameFromString3 = this.names.fromString(this.fileManager.inferModuleName(moduleLocation));
                            JCTree.JCModuleDecl moduleDecl2 = jCCompilationUnit2.getModuleDecl();
                            if (moduleDecl2 != null) {
                                moduleSymbolEnterModule = moduleDecl2.sym;
                                j2 = j3;
                                Name name = moduleSymbolEnterModule.name;
                                if (name != nameFromString3) {
                                    this.log.error(moduleDecl2.qualId, CompilerProperties.Errors.ModuleNameMismatch(name, nameFromString3));
                                }
                            } else {
                                j2 = j3;
                                if (jCCompilationUnit2.getPackage() == null) {
                                    this.log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.UnnamedPkgNotAllowedNamedModules);
                                }
                                moduleSymbolEnterModule = this.syms.enterModule(nameFromString3);
                            }
                            if (moduleSymbolEnterModule.sourceLocation == null) {
                                moduleSymbolEnterModule.sourceLocation = moduleLocation;
                                if (this.fileManager.hasLocation(standardLocation3)) {
                                    moduleSymbolEnterModule.patchLocation = this.fileManager.getLocationForModule(standardLocation3, moduleSymbolEnterModule.name.toString());
                                }
                                JavaFileManager javaFileManager3 = this.fileManager;
                                StandardLocation standardLocation4 = StandardLocation.CLASS_OUTPUT;
                                if (javaFileManager3.hasLocation(standardLocation4)) {
                                    JavaFileManager.Location locationForModule2 = this.fileManager.getLocationForModule(standardLocation4, moduleSymbolEnterModule.name.toString());
                                    if (moduleSymbolEnterModule.patchLocation == null) {
                                        moduleSymbolEnterModule.classLocation = locationForModule2;
                                    } else {
                                        moduleSymbolEnterModule.patchOutputLocation = locationForModule2;
                                    }
                                }
                            }
                            jCCompilationUnit2.modle = moduleSymbolEnterModule;
                            set.add(moduleSymbolEnterModule);
                        } else {
                            j2 = j3;
                            if (classSymbol != null) {
                                Symbol.ModuleSymbol moduleSymbol8 = classSymbol.packge().modle;
                                Symbol.ModuleSymbol moduleSymbol9 = this.syms.unnamedModule;
                                if (moduleSymbol8 == moduleSymbol9) {
                                    jCCompilationUnit2.modle = moduleSymbol9;
                                } else {
                                    moduleDecl = jCCompilationUnit2.getModuleDecl();
                                    log = this.log;
                                    if (moduleDecl != null) {
                                        log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.ModuleNotFoundOnModuleSourcePath);
                                    } else {
                                        log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.NotInModuleOnModuleSourcePath);
                                    }
                                    jCCompilationUnit2.modle = this.syms.errModule;
                                }
                            } else {
                                moduleDecl = jCCompilationUnit2.getModuleDecl();
                                log = this.log;
                                if (moduleDecl != null) {
                                    log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.ModuleNotFoundOnModuleSourcePath);
                                } else {
                                    log.error(jCCompilationUnit2.pos(), CompilerProperties.Errors.NotInModuleOnModuleSourcePath);
                                }
                                jCCompilationUnit2.modle = this.syms.errModule;
                            }
                        }
                        this.log.useSource(javaFileObjectUseSource);
                        j3 = j2;
                    } catch (IOException e3) {
                        throw new Error(e3);
                    }
                } catch (Throwable th) {
                    this.log.useSource(javaFileObjectUseSource);
                    throw th;
                }
            }
        }
        if (!z) {
            checkNoAllModulePath();
        }
        Symbol.ModuleSymbol moduleSymbol10 = this.syms.unnamedModule;
        if (moduleSymbol10.sourceLocation == null) {
            moduleSymbol10.completer = getUnnamedModuleCompleter();
            Symbol.ModuleSymbol moduleSymbol11 = this.syms.unnamedModule;
            moduleSymbol11.sourceLocation = StandardLocation.SOURCE_PATH;
            moduleSymbol11.classLocation = StandardLocation.CLASS_PATH;
        }
        this.defaultModule = this.syms.unnamedModule;
    }

    private void setupAllModules() {
        final Set<Symbol.ModuleSymbol> setComputeTransitiveClosure;
        Stream streamFilter;
        Predicate predicate;
        Assert.checkNonNull(this.rootModules);
        Assert.checkNull(this.allModules);
        this.syms.java_base.complete();
        if (this.limitModsOpt == null && this.extraLimitMods.isEmpty()) {
            setComputeTransitiveClosure = null;
        } else {
            Set<? extends Symbol.ModuleSymbol> hashSet = new HashSet<>();
            String str = this.limitModsOpt;
            if (str != null) {
                for (String str2 : str.split(",")) {
                    if (isValidName(str2)) {
                        hashSet.add(this.syms.enterModule(this.names.fromString(str2)));
                    }
                }
            }
            Iterator<String> it = this.extraLimitMods.iterator();
            while (it.hasNext()) {
                hashSet.add(this.syms.enterModule(this.names.fromString(it.next())));
            }
            setComputeTransitiveClosure = computeTransitiveClosure(hashSet, this.rootModules, null);
            setComputeTransitiveClosure.addAll(this.rootModules);
            for (Symbol.ModuleSymbol moduleSymbol : hashSet) {
                if (!setComputeTransitiveClosure.contains(moduleSymbol)) {
                    this.log.warning(CompilerProperties.LintWarnings.ModuleForOptionNotFound(Option.LIMIT_MODULES, moduleSymbol));
                }
            }
        }
        Predicate predicate2 = new Predicate() { // from class: y5a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Modules.m(this.b, setComputeTransitiveClosure, (Symbol.ModuleSymbol) obj);
            }
        };
        Predicate predicate3 = new Predicate() { // from class: a6a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Modules.j((Symbol.ModuleSymbol) obj);
            }
        };
        final Set<? extends Symbol.ModuleSymbol> linkedHashSet = new LinkedHashSet<>();
        if (this.rootModules.contains(this.syms.unnamedModule)) {
            if (this.target.allApiModulesAreRoots()) {
                predicate = new Predicate() { // from class: b6a
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Modules.r((Symbol.ModuleSymbol) obj);
                    }
                };
            } else {
                Symbol.ModuleSymbol module = this.syms.getModule(this.java_se);
                if (module == null || !(setComputeTransitiveClosure == null || setComputeTransitiveClosure.contains(module))) {
                    predicate = new Predicate() { // from class: d6a
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return Modules.a((Symbol.ModuleSymbol) obj);
                        }
                    };
                } else {
                    Predicate predicate4 = new Predicate() { // from class: c6a
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return Modules.e(this.b, (Symbol.ModuleSymbol) obj);
                        }
                    };
                    linkedHashSet.add(module);
                    predicate = predicate4;
                }
            }
            Predicate predicate5 = new Predicate() { // from class: f6a
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Modules.w((Symbol.ModuleSymbol) obj);
                }
            };
            for (Symbol.ModuleSymbol moduleSymbol2 : new HashSet(this.syms.getAllModules())) {
                try {
                    if (predicate3.test(moduleSymbol2) && predicate2.test(moduleSymbol2) && predicate.test(moduleSymbol2) && predicate5.test(moduleSymbol2)) {
                        linkedHashSet.add(moduleSymbol2);
                    }
                } catch (Symbol.CompletionFailure e) {
                    this.chk.completionError(null, e);
                }
            }
        }
        linkedHashSet.addAll(this.rootModules);
        if (this.addModsOpt != null || !this.extraAddMods.isEmpty()) {
            HashSet<String> hashSet2 = new HashSet();
            hashSet2.addAll(this.extraAddMods);
            String str3 = this.addModsOpt;
            if (str3 != null) {
                hashSet2.addAll(Arrays.asList(str3.split(",")));
            }
            for (String str4 : hashSet2) {
                str4.getClass();
                if (str4.equals(ALL_SYSTEM)) {
                    streamFilter = new HashSet(this.syms.getAllModules()).stream().filter(predicate3.and(predicate2));
                } else if (str4.equals(ALL_MODULE_PATH)) {
                    streamFilter = new HashSet(this.syms.getAllModules()).stream().filter(predicate3.negate().and(predicate2));
                } else if (isValidName(str4)) {
                    streamFilter = Stream.of(this.syms.enterModule(this.names.fromString(str4)));
                }
                streamFilter.forEach(new Consumer() { // from class: g6a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Modules.h(linkedHashSet, setComputeTransitiveClosure, (Symbol.ModuleSymbol) obj);
                    }
                });
            }
        }
        final Set<Symbol.ModuleSymbol> setComputeTransitiveClosure2 = computeTransitiveClosure(linkedHashSet, this.rootModules, setComputeTransitiveClosure);
        setComputeTransitiveClosure2.add(this.syms.unnamedModule);
        Stream<Symbol.ModuleSymbol> stream = setComputeTransitiveClosure2.stream();
        Predicate<? super Symbol.ModuleSymbol> predicate6 = IS_AUTOMATIC;
        if (stream.anyMatch(predicate6)) {
            this.syms.getAllModules().stream().filter(predicate6).forEach(new Consumer() { // from class: h6a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    setComputeTransitiveClosure2.add((Symbol.ModuleSymbol) obj);
                }
            });
        }
        if (this.lint.isEnabled(Lint.LintCategory.INCUBATING)) {
            String str5 = (String) filterAlreadyWarnedIncubatorModules(setComputeTransitiveClosure2.stream().filter(new Predicate() { // from class: i6a
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Symbol.ModuleSymbol) obj).resolutionFlags.contains(Symbol.ModuleResolutionFlags.WARN_INCUBATING);
                }
            }).map(new Function() { // from class: j6a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Symbol.ModuleSymbol) obj).name.toString();
                }
            })).collect(Collectors.joining(","));
            if (str5.length() != 0) {
                this.log.warning(CompilerProperties.LintWarnings.IncubatingModules(str5));
            }
        }
        this.allModules = setComputeTransitiveClosure2;
        String str6 = this.moduleVersionOpt;
        if (str6 != null) {
            final Name nameFromString = this.names.fromString(str6);
            this.rootModules.forEach(new Consumer() { // from class: z5a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Symbol.ModuleSymbol) obj).version = nameFromString;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupAutomaticModule(Symbol.ModuleSymbol moduleSymbol) throws Symbol.CompletionFailure {
        try {
            ListBuffer listBuffer = new ListBuffer();
            ListBuffer listBuffer2 = new ListBuffer();
            HashSet hashSet = new HashSet();
            Iterator<JavaFileObject> it = this.fileManager.list(moduleSymbol.classLocation, "", EnumSet.of(JavaFileObject.Kind.CLASS), true).iterator();
            while (it.hasNext()) {
                String strInferBinaryName = this.fileManager.inferBinaryName(moduleSymbol.classLocation, it.next());
                String strSubstring = strInferBinaryName.lastIndexOf(46) != -1 ? strInferBinaryName.substring(0, strInferBinaryName.lastIndexOf(46)) : "";
                if (hashSet.add(strSubstring)) {
                    Directive.ExportsDirective exportsDirective = new Directive.ExportsDirective(this.syms.enterPackage(moduleSymbol, this.names.fromString(strSubstring)), null);
                    listBuffer.add(exportsDirective);
                    listBuffer2.add(exportsDirective);
                }
            }
            moduleSymbol.exports = listBuffer2.toList();
            moduleSymbol.provides = List.nil();
            moduleSymbol.requires = List.nil();
            moduleSymbol.uses = List.nil();
            moduleSymbol.directives = listBuffer.toList();
        } catch (IOException e) {
            e7f.a(e);
        }
    }

    private String singleModuleOverride(List<JCTree.JCCompilationUnit> list) {
        if (!this.fileManager.hasLocation(StandardLocation.PATCH_MODULE_PATH)) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<JCTree.JCCompilationUnit> it = list.iterator();
        while (it.hasNext()) {
            try {
                JavaFileManager.Location locationForModule = this.fileManager.getLocationForModule(StandardLocation.PATCH_MODULE_PATH, it.next().sourcefile);
                if (locationForModule != null) {
                    linkedHashSet.add(this.fileManager.inferModuleName(locationForModule));
                }
            } catch (IOException e) {
                throw new Error(e);
            }
        }
        int size = linkedHashSet.size();
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            return (String) linkedHashSet.iterator().next();
        }
        this.log.error(CompilerProperties.Errors.TooManyPatchedModules(linkedHashSet));
        return null;
    }

    public static /* synthetic */ boolean t(Directive.ExportsDirective exportsDirective) {
        return exportsDirective.modules == null;
    }

    private String toString(Symbol.ModuleSymbol moduleSymbol) {
        return moduleSymbol.name + "[kind:" + moduleSymbol.kind + ";locn:" + toString(moduleSymbol.sourceLocation) + "," + toString(moduleSymbol.classLocation) + ";info:" + toString(moduleSymbol.module_info.sourcefile) + "," + toString(moduleSymbol.module_info.classfile) + "," + moduleSymbol.module_info.completer + "]";
    }

    public static /* synthetic */ boolean w(Symbol.ModuleSymbol moduleSymbol) {
        moduleSymbol.complete();
        return !moduleSymbol.resolutionFlags.contains(Symbol.ModuleResolutionFlags.DO_NOT_RESOLVE_BY_DEFAULT);
    }

    public static /* synthetic */ Set x(Modules modules, Set set) {
        Assert.checkNull(modules.rootModules);
        Assert.checkNull(modules.allModules);
        modules.rootModules = set;
        modules.setupAllModules();
        Assert.checkNonNull(modules.allModules);
        modules.inInitModules = false;
        return modules.allModules;
    }

    public static /* synthetic */ boolean y(Symbol.ModuleSymbol moduleSymbol) {
        return (moduleSymbol.flags_field & 4503599627370496L) != 0;
    }

    public void addExtraAddModules(String... strArr) {
        this.extraAddMods.addAll(Arrays.asList(strArr));
    }

    public Set<Symbol.ModuleSymbol> allModules() {
        Assert.checkNonNull(this.allModules);
        return this.allModules;
    }

    public Symbol.Completer getCompleter() {
        return this.mainCompleter;
    }

    public Symbol.ModuleSymbol getDefaultModule() {
        return this.defaultModule;
    }

    public Symbol.ModuleSymbol getObservableModule(Name name) {
        Symbol.ModuleSymbol module = this.syms.getModule(name);
        if (allModules().contains(module)) {
            return module;
        }
        return null;
    }

    public Set<Symbol.ModuleSymbol> getRootModules() {
        Assert.checkNonNull(this.rootModules);
        return this.rootModules;
    }

    public Symbol.Completer getUsesProvidesCompleter() {
        return new Symbol.Completer() { // from class: w5a
            @Override // com.sun.tools.javac.code.Symbol.Completer
            public final void complete(Symbol symbol) {
                Modules.q(this.b, symbol);
            }
        };
    }

    public void initModules(List<JCTree.JCCompilationUnit> list) {
        Assert.check(!this.inInitModules);
        try {
            this.inInitModules = true;
            Assert.checkNull(this.rootModules);
            enter(list, new Function() { // from class: r6a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Modules.x(this.b, (Set) obj);
                }
            }, null);
        } finally {
            this.inInitModules = false;
        }
    }

    public boolean isInModuleGraph(Symbol.ModuleSymbol moduleSymbol) {
        Set<Symbol.ModuleSymbol> set = this.allModules;
        return set == null || set.contains(moduleSymbol);
    }

    public boolean isRootModule(Symbol.ModuleSymbol moduleSymbol) {
        Assert.checkNonNull(this.rootModules);
        return this.rootModules.contains(moduleSymbol);
    }

    public boolean modulesInitialized() {
        return this.allModules != null;
    }

    public void newRound() {
        this.allModules = null;
        this.rootModules = null;
        this.defaultModule = null;
        this.warnedMissing.clear();
    }

    public String toString(JavaFileManager.Location location) {
        return location == null ? "--" : location.getName();
    }

    public String toString(JavaFileObject javaFileObject) {
        return javaFileObject == null ? "--" : javaFileObject.getName();
    }

    public boolean enter(List<JCTree.JCCompilationUnit> list, Symbol.ClassSymbol classSymbol) {
        Assert.check((this.rootModules == null && !this.inInitModules && this.allowModules) ? false : true);
        return enter(list, new Function() { // from class: t6a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Modules.g(this.b, (Set) obj);
            }
        }, classSymbol);
    }
}
