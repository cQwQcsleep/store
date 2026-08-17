package com.sun.tools.javac.code;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.ClassFinder;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.file.JRTIndex;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.jvm.ClassReader;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.main.DelegatingJavaFileManager;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.Dependencies;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.lang.model.SourceVersion;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassFinder {
    protected static final Context.Key<ClassFinder> classFinderKey = new Context.Key<>();
    private final Annotate annotate;
    private boolean cacheCompletionFailure;
    private final Symbol.CompletionFailure cachedCompletionFailure;
    final Name completionFailureName;
    protected JavaFileManager.Location currentLoc;
    final DeferredCompletionFailureHandler dcfh;
    private final Dependencies dependencies;
    JCDiagnostic.Factory diagFactory;
    private final JavaFileManager fileManager;
    private final JRTIndex jrtIndex;
    final Log log;
    final Names names;
    private boolean preferCurrent;
    protected boolean preferSource;
    private final Profile profile;
    ClassReader reader;
    private Map<Symbol.PackageSymbol, Long> supplementaryFlags;
    Symtab syms;
    protected boolean userPathsFirst;
    boolean verbose;
    public Symbol.Completer sourceCompleter = Symbol.Completer.NULL_COMPLETER;
    protected JavaFileObject currentClassFile = null;
    protected Symbol currentOwner = null;
    private final Symbol.Completer thisCompleter = new Symbol.Completer() { // from class: dv1
        @Override // com.sun.tools.javac.code.Symbol.Completer
        public final void complete(Symbol symbol) {
            this.b.complete(symbol);
        }
    };
    private boolean verbosePath = true;

    /* JADX INFO: renamed from: com.sun.tools.javac.code.ClassFinder$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$javax$tools$JavaFileObject$Kind;

        static {
            int[] iArr = new int[JavaFileObject.Kind.values().length];
            $SwitchMap$javax$tools$JavaFileObject$Kind = iArr;
            try {
                iArr[JavaFileObject.Kind.OTHER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$tools$JavaFileObject$Kind[JavaFileObject.Kind.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$tools$JavaFileObject$Kind[JavaFileObject.Kind.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class BadClassFile extends Symbol.CompletionFailure {
        private static final long serialVersionUID = 0;

        public BadClassFile(Symbol.TypeSymbol typeSymbol, final JavaFileObject javaFileObject, final JCDiagnostic jCDiagnostic, final JCDiagnostic.Factory factory, DeferredCompletionFailureHandler deferredCompletionFailureHandler) {
            super(typeSymbol, new Supplier() { // from class: iv1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ClassFinder.BadClassFile.createBadClassFileDiagnostic(javaFileObject, jCDiagnostic, factory);
                }
            }, deferredCompletionFailureHandler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static JCDiagnostic createBadClassFileDiagnostic(JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic, JCDiagnostic.Factory factory) {
            return factory.fragment(javaFileObject.getKind() == JavaFileObject.Kind.SOURCE ? "bad.source.file.header" : "bad.class.file.header", javaFileObject, jCDiagnostic);
        }
    }

    public static class BadEnclosingMethodAttr extends BadClassFile {
        private static final long serialVersionUID = 0;

        public BadEnclosingMethodAttr(Symbol.TypeSymbol typeSymbol, JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic, JCDiagnostic.Factory factory, DeferredCompletionFailureHandler deferredCompletionFailureHandler) {
            super(typeSymbol, javaFileObject, jCDiagnostic, factory, deferredCompletionFailureHandler);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x00c3  */
    public ClassFinder(Context context) {
        JRTIndex sharedInstance;
        context.put(classFinderKey, this);
        this.reader = ClassReader.instance(context);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.syms = Symtab.instance(context);
        JavaFileManager javaFileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.fileManager = javaFileManager;
        this.dependencies = Dependencies.instance(context);
        if (javaFileManager == null) {
            x01.a("FileManager initialization error");
            throw null;
        }
        this.diagFactory = JCDiagnostic.Factory.instance(context);
        DeferredCompletionFailureHandler deferredCompletionFailureHandlerInstance = DeferredCompletionFailureHandler.instance(context);
        this.dcfh = deferredCompletionFailureHandlerInstance;
        this.log = Log.instance(context);
        this.annotate = Annotate.instance(context);
        Options optionsInstance = Options.instance(context);
        this.verbose = optionsInstance.isSet(Option.VERBOSE);
        this.cacheCompletionFailure = optionsInstance.isUnset("dev");
        this.preferSource = "source".equals(optionsInstance.get("-Xprefer"));
        this.userPathsFirst = optionsInstance.isSet(Option.XXUSERPATHSFIRST);
        this.completionFailureName = optionsInstance.isSet("failcomplete") ? namesInstance.fromString(optionsInstance.get("failcomplete")) : null;
        JavaFileManager baseFileManager = (JavaFileManager) context.get(JavaFileManager.class);
        baseFileManager = baseFileManager instanceof DelegatingJavaFileManager ? ((DelegatingJavaFileManager) baseFileManager).getBaseFileManager() : baseFileManager;
        if (baseFileManager instanceof JavacFileManager) {
            JavacFileManager javacFileManager = (JavacFileManager) baseFileManager;
            if (javacFileManager.isDefaultBootClassPath() && javacFileManager.isSymbolFileEnabled() && JRTIndex.isAvailable()) {
                sharedInstance = JRTIndex.getSharedInstance();
            } else {
                sharedInstance = null;
            }
        } else {
            sharedInstance = null;
        }
        this.jrtIndex = sharedInstance;
        this.profile = Profile.instance(context);
        Symbol.CompletionFailure completionFailure = new Symbol.CompletionFailure(null, new Supplier() { // from class: ev1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ClassFinder.f();
            }
        }, deferredCompletionFailureHandlerInstance);
        this.cachedCompletionFailure = completionFailure;
        completionFailure.setStackTrace(new StackTraceElement[0]);
    }

    public static /* synthetic */ void a(Symbol symbol) {
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
        Symbol.PackageSymbol packageSymbolPackge = symbol.packge();
        classSymbol.owner = packageSymbolPackge;
        packageSymbolPackge.members().enter(symbol);
        classSymbol.fullname = symbol.flatName();
        classSymbol.name = Convert.shortName(symbol.flatName());
        classSymbol.reset();
    }

    public static /* synthetic */ Iterator c(ClassFinder classFinder, Iterable iterable, Symbol.PackageSymbol packageSymbol, Set set) {
        classFinder.getClass();
        return new Iterator<JavaFileObject>(classFinder, iterable, packageSymbol, set) { // from class: com.sun.tools.javac.code.ClassFinder.1
            private JavaFileObject next;
            private final Iterator<JavaFileObject> original;
            final /* synthetic */ ClassFinder this$0;
            final /* synthetic */ Set val$kinds;
            final /* synthetic */ Iterable val$listed;
            final /* synthetic */ Symbol.PackageSymbol val$p;

            {
                this.val$listed = iterable;
                this.val$p = packageSymbol;
                this.val$kinds = set;
                this.this$0 = classFinder;
                this.original = iterable.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.next == null) {
                    while (this.original.hasNext()) {
                        JavaFileObject next = this.original.next();
                        if (next.getKind() != JavaFileObject.Kind.CLASS && next.getKind() != JavaFileObject.Kind.SOURCE) {
                            this.val$p.flags_field |= 4503599627370496L;
                        }
                        if (this.val$kinds.contains(next.getKind())) {
                            this.next = next;
                            break;
                        }
                    }
                }
                return this.next != null;
            }

            @Override // java.util.Iterator
            public JavaFileObject next() {
                if (!hasNext()) {
                    z0e.a();
                    return null;
                }
                JavaFileObject javaFileObject = this.next;
                this.next = null;
                return javaFileObject;
            }
        };
    }

    private Symbol.CompletionFailure classFileNotFound(final Symbol.ClassSymbol classSymbol) {
        return newCompletionFailure(classSymbol, new Supplier() { // from class: av1
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.diagFactory.fragment(CompilerProperties.Fragments.ClassFileNotFound(classSymbol.flatname));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void complete(Symbol symbol) throws Symbol.CompletionFailure {
        Kinds.Kind kind = symbol.kind;
        if (kind == Kinds.Kind.TYP) {
            try {
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
                this.dependencies.push(classSymbol, Dependencies.CompletionCause.CLASS_READER);
                this.annotate.blockAnnotations();
                Scope.ErrorScope errorScope = new Scope.ErrorScope(classSymbol);
                classSymbol.members_field = errorScope;
                completeOwners(classSymbol.owner);
                completeEnclosing(classSymbol);
                if (classSymbol.members_field == errorScope) {
                    fillIn(classSymbol);
                }
                this.annotate.unblockAnnotationsNoFlush();
                this.dependencies.pop();
            } catch (Throwable th) {
                this.annotate.unblockAnnotationsNoFlush();
                this.dependencies.pop();
                throw th;
            }
        } else if (kind == Kinds.Kind.PCK) {
            try {
                fillIn((Symbol.PackageSymbol) symbol);
            } catch (IOException e) {
                throw new Symbol.CompletionFailure(symbol, new Supplier() { // from class: bv1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return this.b.diagFactory.fragment(CompilerProperties.Fragments.ExceptionMessage(e.getLocalizedMessage()));
                    }
                }, this.dcfh).initCause((Throwable) e);
            }
        }
        if (this.reader.filling) {
            return;
        }
        this.annotate.flush();
    }

    private void completeEnclosing(Symbol.ClassSymbol classSymbol) {
        Symbol symbol = classSymbol.owner;
        if (symbol.kind == Kinds.Kind.PCK) {
            for (Name name : Convert.enclosingCandidates(Convert.shortName(classSymbol.name))) {
                Symbol symbolFindFirst = symbol.members().findFirst(name);
                if (symbolFindFirst == null) {
                    symbolFindFirst = this.syms.getClass(classSymbol.packge().modle, Symbol.TypeSymbol.formFlatName(name, symbol));
                }
                if (symbolFindFirst != null) {
                    symbolFindFirst.complete();
                }
            }
        }
    }

    private void completeOwners(Symbol symbol) {
        if (symbol.kind != Kinds.Kind.PCK) {
            completeOwners(symbol.owner);
        }
        symbol.complete();
    }

    public static /* synthetic */ String d(Symbol.ModuleSymbol moduleSymbol, Name name) {
        return "msym=" + moduleSymbol + "; flatName=" + name;
    }

    public static /* synthetic */ JCDiagnostic f() {
        return null;
    }

    public static /* synthetic */ boolean h(Symbol symbol) {
        return symbol.kind == Kinds.Kind.TYP;
    }

    public static ClassFinder instance(Context context) {
        ClassFinder classFinder = (ClassFinder) context.get(classFinderKey);
        return classFinder == null ? new ClassFinder(context) : classFinder;
    }

    private Symbol.CompletionFailure newCompletionFailure(Symbol.TypeSymbol typeSymbol, Supplier<JCDiagnostic> supplier) {
        if (!this.cacheCompletionFailure) {
            return new Symbol.CompletionFailure(typeSymbol, supplier, this.dcfh);
        }
        Symbol.CompletionFailure completionFailure = this.cachedCompletionFailure;
        completionFailure.sym = typeSymbol;
        completionFailure.resetDiagnostic(supplier);
        return completionFailure;
    }

    private void scanModulePaths(Symbol.PackageSymbol packageSymbol, Symbol.ModuleSymbol moduleSymbol) throws IOException {
        EnumSet<JavaFileObject.Kind> packageFileKinds = getPackageFileKinds();
        Set<JavaFileObject.Kind> setCopyOf = EnumSet.copyOf((Collection) packageFileKinds);
        setCopyOf.remove(JavaFileObject.Kind.SOURCE);
        boolean zIsEmpty = setCopyOf.isEmpty();
        Set<JavaFileObject.Kind> setCopyOf2 = EnumSet.copyOf((Collection) packageFileKinds);
        setCopyOf2.remove(JavaFileObject.Kind.CLASS);
        boolean zIsEmpty2 = setCopyOf2.isEmpty();
        String string = packageSymbol.fullname.toString();
        JavaFileManager.Location location = moduleSymbol.classLocation;
        JavaFileManager.Location location2 = moduleSymbol.sourceLocation;
        JavaFileManager.Location location3 = moduleSymbol.patchLocation;
        JavaFileManager.Location location4 = moduleSymbol.patchOutputLocation;
        boolean z = this.preferCurrent;
        try {
            this.preferCurrent = false;
            if (!zIsEmpty && location4 != null) {
                fillIn(packageSymbol, location4, list(location4, packageSymbol, string, setCopyOf));
            }
            if ((!zIsEmpty || !zIsEmpty2) && location3 != null) {
                Set<JavaFileObject.Kind> setNoneOf = EnumSet.noneOf(JavaFileObject.Kind.class);
                setNoneOf.addAll(setCopyOf);
                setNoneOf.addAll(setCopyOf2);
                fillIn(packageSymbol, location3, list(location3, packageSymbol, string, setNoneOf));
            }
            this.preferCurrent = true;
            if (!zIsEmpty && location != null) {
                fillIn(packageSymbol, location, list(location, packageSymbol, string, setCopyOf));
            }
            if (!zIsEmpty2 && location2 != null) {
                fillIn(packageSymbol, location2, list(location2, packageSymbol, string, setCopyOf2));
            }
        } finally {
            this.preferCurrent = z;
        }
    }

    private void scanPlatformPath(Symbol.PackageSymbol packageSymbol) throws IOException {
        StandardLocation standardLocation = StandardLocation.PLATFORM_CLASS_PATH;
        fillIn(packageSymbol, standardLocation, list(standardLocation, packageSymbol, packageSymbol.fullname.toString(), EnumSet.of(JavaFileObject.Kind.CLASS)));
    }

    private void scanUserPaths(Symbol.PackageSymbol packageSymbol, boolean z) throws IOException {
        EnumSet<JavaFileObject.Kind> packageFileKinds = getPackageFileKinds();
        EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) packageFileKinds);
        enumSetCopyOf.remove(JavaFileObject.Kind.SOURCE);
        boolean zIsEmpty = enumSetCopyOf.isEmpty();
        EnumSet enumSetCopyOf2 = EnumSet.copyOf((Collection) packageFileKinds);
        enumSetCopyOf2.remove(JavaFileObject.Kind.CLASS);
        boolean zIsEmpty2 = enumSetCopyOf2.isEmpty();
        boolean z2 = z && this.fileManager.hasLocation(StandardLocation.SOURCE_PATH);
        if (this.verbose && this.verbosePath) {
            this.verbosePath = false;
            JavaFileManager javaFileManager = this.fileManager;
            if (javaFileManager instanceof StandardJavaFileManager) {
                StandardJavaFileManager standardJavaFileManager = (StandardJavaFileManager) javaFileManager;
                if (z2 && !zIsEmpty2) {
                    List listNil = List.nil();
                    Iterator<? extends Path> it = standardJavaFileManager.getLocationAsPaths(StandardLocation.SOURCE_PATH).iterator();
                    while (it.hasNext()) {
                        listNil = listNil.prepend(it.next());
                    }
                    this.log.printVerbose("sourcepath", listNil.reverse().toString());
                } else if (!zIsEmpty2) {
                    List listNil2 = List.nil();
                    Iterator<? extends Path> it2 = standardJavaFileManager.getLocationAsPaths(StandardLocation.CLASS_PATH).iterator();
                    while (it2.hasNext()) {
                        listNil2 = listNil2.prepend(it2.next());
                    }
                    this.log.printVerbose("sourcepath", listNil2.reverse().toString());
                }
                if (!zIsEmpty) {
                    List listNil3 = List.nil();
                    Iterator<? extends Path> it3 = standardJavaFileManager.getLocationAsPaths(StandardLocation.PLATFORM_CLASS_PATH).iterator();
                    while (it3.hasNext()) {
                        listNil3 = listNil3.prepend(it3.next());
                    }
                    Iterator<? extends Path> it4 = standardJavaFileManager.getLocationAsPaths(StandardLocation.CLASS_PATH).iterator();
                    while (it4.hasNext()) {
                        listNil3 = listNil3.prepend(it4.next());
                    }
                    this.log.printVerbose("classpath", listNil3.reverse().toString());
                }
            }
        }
        String string = packageSymbol.fullname.toString();
        if (!zIsEmpty2 && !z2) {
            StandardLocation standardLocation = StandardLocation.CLASS_PATH;
            fillIn(packageSymbol, standardLocation, list(standardLocation, packageSymbol, string, packageFileKinds));
            return;
        }
        if (!zIsEmpty) {
            StandardLocation standardLocation2 = StandardLocation.CLASS_PATH;
            fillIn(packageSymbol, standardLocation2, list(standardLocation2, packageSymbol, string, enumSetCopyOf));
        }
        if (zIsEmpty2) {
            return;
        }
        StandardLocation standardLocation3 = StandardLocation.SOURCE_PATH;
        fillIn(packageSymbol, standardLocation3, list(standardLocation3, packageSymbol, string, enumSetCopyOf2));
    }

    public void extraFileActions(Symbol.PackageSymbol packageSymbol, JavaFileObject javaFileObject) {
    }

    public void fillIn(Symbol.ClassSymbol classSymbol) {
        Name name = this.completionFailureName;
        Name name2 = classSymbol.fullname;
        if (name == name2) {
            throw new Symbol.CompletionFailure(classSymbol, new Supplier() { // from class: fv1
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.diagFactory.fragment(CompilerProperties.Fragments.UserSelectedCompletionFailure);
                }
            }, this.dcfh);
        }
        this.currentOwner = classSymbol;
        JavaFileObject javaFileObject = classSymbol.classfile;
        if (javaFileObject == null) {
            throw classFileNotFound(classSymbol);
        }
        JavaFileObject javaFileObject2 = this.currentClassFile;
        Symbol symbol = classSymbol.owner;
        try {
            try {
                if (this.reader.filling) {
                    Assert.error("Filling " + javaFileObject.toUri() + " during " + javaFileObject2);
                }
                this.currentClassFile = javaFileObject;
                if (this.verbose) {
                    this.log.printVerbose("loading", javaFileObject.getName());
                }
                if (javaFileObject.getKind() == JavaFileObject.Kind.CLASS) {
                    this.reader.readClassFile(classSymbol);
                    classSymbol.flags_field |= getSupplementaryFlags(classSymbol);
                } else {
                    if (this.sourceCompleter.isTerminal()) {
                        throw new IllegalStateException("Source completer required to read " + javaFileObject.toUri());
                    }
                    this.sourceCompleter.complete(classSymbol);
                }
                this.currentClassFile = javaFileObject2;
            } catch (BadClassFile e) {
                classSymbol.owner = symbol;
                classSymbol.members_field.getSymbols(new Predicate() { // from class: gv1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ClassFinder.h((Symbol) obj);
                    }
                }).forEach(new Consumer() { // from class: hv1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ClassFinder.a((Symbol) obj);
                    }
                });
                classSymbol.fullname = name2;
                classSymbol.name = Convert.shortName(name2);
                classSymbol.reset();
                throw e;
            }
        } catch (Throwable th) {
            this.currentClassFile = javaFileObject2;
            throw th;
        }
    }

    public Symbol.Completer getCompleter() {
        return this.thisCompleter;
    }

    public EnumSet<JavaFileObject.Kind> getPackageFileKinds() {
        return EnumSet.of(JavaFileObject.Kind.CLASS, JavaFileObject.Kind.SOURCE);
    }

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
    public long getSupplementaryFlags(Symbol.ClassSymbol classSymbol) {
        JRTIndex jRTIndex = this.jrtIndex;
        long j = 0;
        if (jRTIndex == null || !jRTIndex.isInJRT(classSymbol.classfile) || classSymbol.name == this.names.module_info) {
            return 0L;
        }
        if (this.supplementaryFlags == null) {
            this.supplementaryFlags = new HashMap();
        }
        Symbol.PackageSymbol packageSymbolPackge = classSymbol.packge();
        Long lValueOf = this.supplementaryFlags.get(packageSymbolPackge);
        if (lValueOf == null) {
            try {
                Symbol.ModuleSymbol moduleSymbol = packageSymbolPackge.modle;
                if (moduleSymbol == this.syms.noModule) {
                    JRTIndex.CtSym ctSym = this.jrtIndex.getCtSym(packageSymbolPackge.flatName());
                    Profile profile = Profile.DEFAULT;
                    j = ctSym.proprietary ? 274877906944L : 0L;
                    String str = ctSym.minProfile;
                    Profile profileLookup = str != null ? Profile.lookup(str) : profile;
                    Profile profile2 = this.profile;
                    if (profile2 != profile && profileLookup.value > profile2.value) {
                        j |= 35184372088832L;
                    }
                } else if (moduleSymbol.name == this.names.jdk_unsupported) {
                    j = 274877906944L;
                }
            } catch (IOException unused) {
            }
            Map<Symbol.PackageSymbol, Long> map = this.supplementaryFlags;
            lValueOf = Long.valueOf(j);
            map.put(packageSymbolPackge, lValueOf);
        }
        return lValueOf.longValue();
    }

    public void includeClassFile(Symbol.PackageSymbol packageSymbol, JavaFileObject javaFileObject) {
        JavaFileObject javaFileObject2;
        if ((packageSymbol.flags_field & 8388608) == 0) {
            for (Symbol symbol = packageSymbol; symbol != null && symbol.kind == Kinds.Kind.PCK; symbol = symbol.owner) {
                symbol.flags_field |= 8388608;
            }
        }
        int i = javaFileObject.getKind() == JavaFileObject.Kind.CLASS ? 33554432 : 67108864;
        String strInferBinaryName = this.fileManager.inferBinaryName(this.currentLoc, javaFileObject);
        Name nameFromString = this.names.fromString(strInferBinaryName.substring(strInferBinaryName.lastIndexOf(Constants.ATTRVAL_THIS) + 1));
        boolean z = nameFromString == this.names.package_info;
        Symbol.ClassSymbol classSymbolEnterClass = z ? packageSymbol.package_info : (Symbol.ClassSymbol) packageSymbol.members_field.findFirst(nameFromString);
        if (classSymbolEnterClass == null) {
            classSymbolEnterClass = this.syms.enterClass(packageSymbol.modle, nameFromString, packageSymbol);
            if (classSymbolEnterClass.classfile == null) {
                classSymbolEnterClass.classfile = javaFileObject;
            }
            if (z) {
                packageSymbol.package_info = classSymbolEnterClass;
            } else if (classSymbolEnterClass.owner == packageSymbol) {
                packageSymbol.members_field.enter(classSymbolEnterClass);
            }
        } else if (!this.preferCurrent && (javaFileObject2 = classSymbolEnterClass.classfile) != null) {
            long j = classSymbolEnterClass.flags_field;
            if ((((long) i) & j) == 0 && (j & 100663296) != 0) {
                classSymbolEnterClass.classfile = preferredFileObject(javaFileObject, javaFileObject2);
            }
        }
        classSymbolEnterClass.flags_field |= (long) i;
    }

    public Iterable<JavaFileObject> list(JavaFileManager.Location location, final Symbol.PackageSymbol packageSymbol, String str, final Set<JavaFileObject.Kind> set) throws IOException {
        final Iterable<JavaFileObject> list = this.fileManager.list(location, str, EnumSet.allOf(JavaFileObject.Kind.class), false);
        return new Iterable() { // from class: cv1
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ClassFinder.c(this.b, list, packageSymbol, set);
            }
        };
    }

    public Symbol.ClassSymbol loadClass(final Symbol.ModuleSymbol moduleSymbol, final Name name) throws Symbol.CompletionFailure {
        Assert.checkNonNull(moduleSymbol);
        Symbol.PackageSymbol packageSymbolLookupPackage = this.syms.lookupPackage(moduleSymbol, Convert.packagePart(name));
        Assert.checkNonNull(packageSymbolLookupPackage.modle, (Supplier<String>) new Supplier() { // from class: zu1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ClassFinder.d(moduleSymbol, name);
            }
        });
        boolean z = this.syms.getClass(packageSymbolLookupPackage.modle, name) == null;
        Symbol.ClassSymbol classSymbolEnterClass = this.syms.enterClass(packageSymbolLookupPackage.modle, name);
        if (classSymbolEnterClass.members_field != null) {
            return classSymbolEnterClass;
        }
        try {
            classSymbolEnterClass.complete();
            return classSymbolEnterClass;
        } catch (Symbol.CompletionFailure e) {
            if (z) {
                this.syms.removeClass(packageSymbolLookupPackage.modle, name);
                e.dcfh.classSymbolRemoved(classSymbolEnterClass);
            }
            throw e;
        }
    }

    public JavaFileObject preferredFileObject(JavaFileObject javaFileObject, JavaFileObject javaFileObject2) {
        if (this.preferSource) {
            return javaFileObject.getKind() == JavaFileObject.Kind.SOURCE ? javaFileObject : javaFileObject2;
        }
        return javaFileObject.getLastModified() > javaFileObject2.getLastModified() ? javaFileObject : javaFileObject2;
    }

    private void fillIn(final Symbol.PackageSymbol packageSymbol) throws IOException {
        if (packageSymbol.members_field == null) {
            packageSymbol.members_field = Scope.WriteableScope.create(packageSymbol);
        }
        Symbol.ModuleSymbol moduleSymbol = packageSymbol.modle;
        Assert.checkNonNull(moduleSymbol, (Supplier<String>) new Supplier() { // from class: yu1
            @Override // java.util.function.Supplier
            public final Object get() {
                return packageSymbol.toString();
            }
        });
        moduleSymbol.complete();
        if (moduleSymbol == this.syms.noModule) {
            this.preferCurrent = false;
            if (this.userPathsFirst) {
                scanUserPaths(packageSymbol, true);
                this.preferCurrent = true;
                scanPlatformPath(packageSymbol);
                return;
            } else {
                scanPlatformPath(packageSymbol);
                scanUserPaths(packageSymbol, true);
                return;
            }
        }
        if (moduleSymbol.classLocation == StandardLocation.CLASS_PATH) {
            scanUserPaths(packageSymbol, moduleSymbol.sourceLocation == StandardLocation.SOURCE_PATH);
        } else {
            scanModulePaths(packageSymbol, moduleSymbol);
        }
    }

    private void fillIn(Symbol.PackageSymbol packageSymbol, JavaFileManager.Location location, Iterable<JavaFileObject> iterable) {
        this.currentLoc = location;
        for (JavaFileObject javaFileObject : iterable) {
            int i = AnonymousClass2.$SwitchMap$javax$tools$JavaFileObject$Kind[javaFileObject.getKind().ordinal()];
            if (i == 1) {
                extraFileActions(packageSymbol, javaFileObject);
            } else if (i != 2 && i != 3) {
                extraFileActions(packageSymbol, javaFileObject);
            } else {
                String strInferBinaryName = this.fileManager.inferBinaryName(this.currentLoc, javaFileObject);
                String strSubstring = strInferBinaryName.substring(strInferBinaryName.lastIndexOf(Constants.ATTRVAL_THIS) + 1);
                if (SourceVersion.isIdentifier(strSubstring) || strSubstring.equals("package-info")) {
                    includeClassFile(packageSymbol, javaFileObject);
                }
            }
        }
    }
}
