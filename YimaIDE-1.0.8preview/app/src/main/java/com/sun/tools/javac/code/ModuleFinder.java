package com.sun.tools.javac.code;

import com.sun.tools.javac.jvm.ModuleNameReader;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleFinder {
    protected static final Context.Key<ModuleFinder> moduleFinderKey = new Context.Key<>();
    private final ClassFinder classFinder;
    private final DeferredCompletionFailureHandler dcfh;
    private final JCDiagnostic.Factory diags;
    private final JavaFileManager fileManager;
    private final Log log;
    ModuleLocationIterator moduleLocationIterator = new ModuleLocationIterator();
    public ModuleNameFromSourceReader moduleNameFromSourceReader;
    private ModuleNameReader moduleNameReader;
    private final Names names;
    private final Symtab syms;

    /* JADX INFO: renamed from: com.sun.tools.javac.code.ModuleFinder$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$javax$tools$JavaFileObject$Kind;
        static final /* synthetic */ int[] $SwitchMap$javax$tools$StandardLocation;

        static {
            int[] iArr = new int[StandardLocation.values().length];
            $SwitchMap$javax$tools$StandardLocation = iArr;
            try {
                iArr[StandardLocation.MODULE_PATH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$tools$StandardLocation[StandardLocation.MODULE_SOURCE_PATH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$tools$StandardLocation[StandardLocation.SYSTEM_MODULES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$tools$StandardLocation[StandardLocation.UPGRADE_MODULE_PATH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[JavaFileObject.Kind.values().length];
            $SwitchMap$javax$tools$JavaFileObject$Kind = iArr2;
            try {
                iArr2[JavaFileObject.Kind.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$tools$JavaFileObject$Kind[JavaFileObject.Kind.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public class ModuleLocationIterator implements Iterator<Set<JavaFileManager.Location>> {
        StandardLocation outer;
        Set<JavaFileManager.Location> next = null;
        Iterator<StandardLocation> outerIter = Arrays.asList(StandardLocation.MODULE_SOURCE_PATH, StandardLocation.UPGRADE_MODULE_PATH, StandardLocation.SYSTEM_MODULES, StandardLocation.MODULE_PATH).iterator();
        Iterator<Set<JavaFileManager.Location>> innerIter = null;

        public ModuleLocationIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.next == null) {
                while (true) {
                    Iterator<Set<JavaFileManager.Location>> it = this.innerIter;
                    if (it == null || !it.hasNext()) {
                        if (!this.outerIter.hasNext()) {
                            return false;
                        }
                        this.outer = this.outerIter.next();
                        try {
                            this.innerIter = ModuleFinder.this.fileManager.listLocationsForModules(this.outer).iterator();
                        } catch (IOException e) {
                            System.err.println("error listing module locations for " + this.outer + ": " + e);
                        }
                    }
                }
                if (this.innerIter.hasNext()) {
                    this.next = this.innerIter.next();
                }
            }
            return true;
        }

        @Override // java.util.Iterator
        public Set<JavaFileManager.Location> next() {
            hasNext();
            Set<JavaFileManager.Location> set = this.next;
            if (set != null) {
                this.next = null;
                return set;
            }
            z0e.a();
            return null;
        }
    }

    public interface ModuleNameFromSourceReader {
        Name readModuleName(JavaFileObject javaFileObject);
    }

    public ModuleFinder(Context context) {
        context.put(moduleFinderKey, this);
        this.names = Names.instance(context);
        this.syms = Symtab.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.log = Log.instance(context);
        this.classFinder = ClassFinder.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.dcfh = DeferredCompletionFailureHandler.instance(context);
    }

    private void findModuleInfo(final Symbol.ModuleSymbol moduleSymbol) {
        JavaFileManager.Location location;
        try {
            JavaFileManager.Location location2 = moduleSymbol.patchOutputLocation;
            JavaFileObject.Kind kind = JavaFileObject.Kind.CLASS;
            JavaFileObject javaFileObjectPreferredFileObject = preferredFileObject(getModuleInfoFromLocation(moduleSymbol.patchLocation, kind), getModuleInfoFromLocation(location2, kind));
            JavaFileManager.Location location3 = moduleSymbol.patchLocation;
            JavaFileObject.Kind kind2 = JavaFileObject.Kind.SOURCE;
            JavaFileObject javaFileObjectPreferredFileObject2 = preferredFileObject(getModuleInfoFromLocation(location3, kind2), javaFileObjectPreferredFileObject);
            if (javaFileObjectPreferredFileObject2 == null) {
                javaFileObjectPreferredFileObject2 = preferredFileObject(getModuleInfoFromLocation(moduleSymbol.sourceLocation, kind2), getModuleInfoFromLocation(moduleSymbol.classLocation, kind));
            }
            if (javaFileObjectPreferredFileObject2 != null) {
                Symbol.ClassSymbol classSymbol = moduleSymbol.module_info;
                classSymbol.classfile = javaFileObjectPreferredFileObject2;
                classSymbol.completer = new Symbol.Completer(this) { // from class: com.sun.tools.javac.code.ModuleFinder.1
                    final /* synthetic */ ModuleFinder this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // com.sun.tools.javac.code.Symbol.Completer
                    public void complete(Symbol symbol) throws Symbol.CompletionFailure {
                        this.this$0.classFinder.fillIn(moduleSymbol.module_info);
                    }

                    public String toString() {
                        return "ModuleInfoCompleter";
                    }
                };
            } else {
                if (((moduleSymbol.sourceLocation != null || (location = moduleSymbol.classLocation) == null) ? null : this.fileManager.inferModuleName(location)) == null) {
                    moduleSymbol.kind = Kinds.Kind.ERR;
                } else {
                    moduleSymbol.module_info.classfile = null;
                    moduleSymbol.flags_field |= 4503599627370496L;
                }
            }
        } catch (IOException unused) {
            moduleSymbol.kind = Kinds.Kind.ERR;
        }
    }

    private JavaFileObject getModuleInfoFromLocation(JavaFileManager.Location location, JavaFileObject.Kind kind) throws IOException {
        if (location == null || !this.fileManager.hasLocation(location)) {
            return null;
        }
        return this.fileManager.getJavaFileForInput(location, this.names.module_info.toString(), kind);
    }

    public static ModuleFinder instance(Context context) {
        ModuleFinder moduleFinder = (ModuleFinder) context.get(moduleFinderKey);
        return moduleFinder == null ? new ModuleFinder(context) : moduleFinder;
    }

    private JavaFileObject preferredFileObject(JavaFileObject javaFileObject, JavaFileObject javaFileObject2) {
        if (javaFileObject == null) {
            return javaFileObject2;
        }
        return javaFileObject2 == null ? javaFileObject : this.classFinder.preferredFileObject(javaFileObject, javaFileObject2);
    }

    private Symbol.ModuleSymbol readModule(JavaFileObject javaFileObject) throws IOException {
        Name moduleName;
        int i = AnonymousClass2.$SwitchMap$javax$tools$JavaFileObject$Kind[javaFileObject.getKind().ordinal()];
        if (i == 1) {
            moduleName = this.moduleNameFromSourceReader.readModuleName(javaFileObject);
            if (moduleName == null) {
                JCDiagnostic jCDiagnosticFragment = this.diags.fragment(CompilerProperties.Fragments.FileDoesNotContainModule);
                Symtab symtab = this.syms;
                throw new ClassFinder.BadClassFile(symtab.defineClass(this.names.module_info, symtab.errModule), javaFileObject, jCDiagnosticFragment, this.diags, this.dcfh);
            }
        } else if (i != 2) {
            Assert.error();
            moduleName = this.names.error;
        } else {
            try {
                moduleName = this.names.fromString(readModuleName(javaFileObject));
            } catch (ModuleNameReader.BadClassFile | IOException unused) {
                moduleName = this.names.error;
            }
        }
        Symbol.ModuleSymbol moduleSymbolEnterModule = this.syms.enterModule(moduleName);
        Symbol.ClassSymbol classSymbol = moduleSymbolEnterModule.module_info;
        if (classSymbol.classfile == null) {
            classSymbol.classfile = javaFileObject;
            JavaFileManager javaFileManager = this.fileManager;
            StandardLocation standardLocation = StandardLocation.PATCH_MODULE_PATH;
            if (javaFileManager.hasLocation(standardLocation) && moduleName != this.names.error) {
                JavaFileManager.Location locationForModule = this.fileManager.getLocationForModule(standardLocation, moduleName.toString());
                moduleSymbolEnterModule.patchLocation = locationForModule;
                if (locationForModule != null) {
                    StandardLocation standardLocation2 = StandardLocation.CLASS_OUTPUT;
                    JavaFileObject.Kind kind = JavaFileObject.Kind.CLASS;
                    JavaFileObject javaFileObjectPreferredFileObject = preferredFileObject(getModuleInfoFromLocation(moduleSymbolEnterModule.patchLocation, JavaFileObject.Kind.SOURCE), preferredFileObject(getModuleInfoFromLocation(moduleSymbolEnterModule.patchLocation, kind), getModuleInfoFromLocation(standardLocation2, kind)));
                    if (javaFileObjectPreferredFileObject != null) {
                        moduleSymbolEnterModule.module_info.classfile = javaFileObjectPreferredFileObject;
                    }
                }
            }
            moduleSymbolEnterModule.completer = Symbol.Completer.NULL_COMPLETER;
            this.classFinder.fillIn(moduleSymbolEnterModule.module_info);
        }
        return moduleSymbolEnterModule;
    }

    private String readModuleName(JavaFileObject javaFileObject) throws ModuleNameReader.BadClassFile, IOException {
        if (this.moduleNameReader == null) {
            this.moduleNameReader = new ModuleNameReader();
        }
        return this.moduleNameReader.readModuleName(javaFileObject);
    }

    private List<Symbol.ModuleSymbol> scanModulePath(Symbol.ModuleSymbol moduleSymbol) {
        ListBuffer listBuffer = new ListBuffer();
        HashMap map = new HashMap();
        boolean zHasLocation = this.fileManager.hasLocation(StandardLocation.MODULE_SOURCE_PATH);
        while (this.moduleLocationIterator.hasNext()) {
            Set<JavaFileManager.Location> next = this.moduleLocationIterator.next();
            map.clear();
            for (JavaFileManager.Location location : next) {
                try {
                    Name nameFromString = this.names.fromString(this.fileManager.inferModuleName(location));
                    if (map.put(nameFromString, location) == null) {
                        Symbol.ModuleSymbol moduleSymbolEnterModule = this.syms.enterModule(nameFromString);
                        if (moduleSymbolEnterModule.sourceLocation == null && moduleSymbolEnterModule.classLocation == null) {
                            JavaFileManager javaFileManager = this.fileManager;
                            StandardLocation standardLocation = StandardLocation.PATCH_MODULE_PATH;
                            if (javaFileManager.hasLocation(standardLocation) && moduleSymbolEnterModule.patchLocation == null) {
                                JavaFileManager.Location locationForModule = this.fileManager.getLocationForModule(standardLocation, moduleSymbolEnterModule.name.toString());
                                moduleSymbolEnterModule.patchLocation = locationForModule;
                                if (locationForModule != null && zHasLocation) {
                                    JavaFileManager javaFileManager2 = this.fileManager;
                                    StandardLocation standardLocation2 = StandardLocation.CLASS_OUTPUT;
                                    if (javaFileManager2.hasLocation(standardLocation2)) {
                                        moduleSymbolEnterModule.patchOutputLocation = this.fileManager.getLocationForModule(standardLocation2, moduleSymbolEnterModule.name.toString());
                                    }
                                }
                            }
                            if (this.moduleLocationIterator.outer == StandardLocation.MODULE_SOURCE_PATH) {
                                moduleSymbolEnterModule.sourceLocation = location;
                                JavaFileManager javaFileManager3 = this.fileManager;
                                StandardLocation standardLocation3 = StandardLocation.CLASS_OUTPUT;
                                if (javaFileManager3.hasLocation(standardLocation3)) {
                                    moduleSymbolEnterModule.classLocation = this.fileManager.getLocationForModule(standardLocation3, moduleSymbolEnterModule.name.toString());
                                }
                            } else {
                                moduleSymbolEnterModule.classLocation = location;
                            }
                            StandardLocation standardLocation4 = this.moduleLocationIterator.outer;
                            if (standardLocation4 == StandardLocation.SYSTEM_MODULES || standardLocation4 == StandardLocation.UPGRADE_MODULE_PATH) {
                                moduleSymbolEnterModule.flags_field |= 9007199254740992L;
                            }
                            if (moduleSymbol == null || (moduleSymbol == moduleSymbolEnterModule && (moduleSymbolEnterModule.sourceLocation != null || moduleSymbolEnterModule.classLocation != null))) {
                                listBuffer.add(moduleSymbolEnterModule);
                            }
                        }
                    } else {
                        this.log.error(CompilerProperties.Errors.DuplicateModuleOnPath(getDescription(this.moduleLocationIterator.outer), nameFromString));
                    }
                } catch (IOException unused) {
                }
            }
            if (moduleSymbol != null && listBuffer.nonEmpty()) {
                return listBuffer.toList();
            }
        }
        return listBuffer.toList();
    }

    public List<Symbol.ModuleSymbol> findAllModules() {
        List<Symbol.ModuleSymbol> listScanModulePath = scanModulePath(null);
        for (Symbol.ModuleSymbol moduleSymbol : listScanModulePath) {
            if (moduleSymbol.kind != Kinds.Kind.ERR) {
                Symbol.ClassSymbol classSymbol = moduleSymbol.module_info;
                if (classSymbol.sourcefile == null && classSymbol.classfile == null) {
                    findModuleInfo(moduleSymbol);
                }
            }
        }
        return listScanModulePath;
    }

    public Symbol.ModuleSymbol findModule(Symbol.ModuleSymbol moduleSymbol) {
        Kinds.Kind kind = moduleSymbol.kind;
        Kinds.Kind kind2 = Kinds.Kind.ERR;
        if (kind != kind2 && moduleSymbol.sourceLocation == null && moduleSymbol.classLocation == null && scanModulePath(moduleSymbol).isEmpty()) {
            moduleSymbol.kind = kind2;
        }
        if (moduleSymbol.kind != kind2) {
            Symbol.ClassSymbol classSymbol = moduleSymbol.module_info;
            if (classSymbol.sourcefile == null && classSymbol.classfile == null) {
                findModuleInfo(moduleSymbol);
            }
        }
        return moduleSymbol;
    }

    public Symbol.ModuleSymbol findSingleModule() {
        try {
            JavaFileObject moduleInfoFromLocation = getModuleInfoFromLocation(StandardLocation.SOURCE_PATH, JavaFileObject.Kind.SOURCE);
            StandardLocation standardLocation = StandardLocation.CLASS_OUTPUT;
            JavaFileObject moduleInfoFromLocation2 = getModuleInfoFromLocation(standardLocation, JavaFileObject.Kind.CLASS);
            if (moduleInfoFromLocation == null) {
                moduleInfoFromLocation = moduleInfoFromLocation2;
            } else if (moduleInfoFromLocation2 != null) {
                moduleInfoFromLocation = this.classFinder.preferredFileObject(moduleInfoFromLocation, moduleInfoFromLocation2);
            }
            Symbol.ModuleSymbol module = moduleInfoFromLocation == null ? this.syms.unnamedModule : readModule(moduleInfoFromLocation);
            if (module.patchLocation == null) {
                module.classLocation = standardLocation;
                return module;
            }
            module.patchOutputLocation = standardLocation;
            return module;
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public JCDiagnostic.Fragment getDescription(StandardLocation standardLocation) {
        int i = AnonymousClass2.$SwitchMap$javax$tools$StandardLocation[standardLocation.ordinal()];
        if (i == 1) {
            return CompilerProperties.Fragments.LocnModule_path;
        }
        if (i == 2) {
            return CompilerProperties.Fragments.LocnModule_source_path;
        }
        if (i == 3) {
            return CompilerProperties.Fragments.LocnSystem_modules;
        }
        if (i == 4) {
            return CompilerProperties.Fragments.LocnUpgrade_module_path;
        }
        x1f.a();
        return null;
    }

    public Symbol.ModuleSymbol findModule(Name name) {
        return findModule(this.syms.enterModule(name));
    }
}
