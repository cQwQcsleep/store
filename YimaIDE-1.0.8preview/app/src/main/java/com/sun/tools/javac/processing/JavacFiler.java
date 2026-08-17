package com.sun.tools.javac.processing;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacFiler;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import defpackage.aca;
import defpackage.s22;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FilterOutputStream;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.FileObject;
import javax.tools.ForwardingFileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import nbjavac.ObjectsWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacFiler implements Filer, Closeable {
    private static final String ALREADY_OPENED = "Output stream or writer has already been opened.";
    private static final String NOT_FOR_READING = "FileObject was not opened for reading.";
    private static final String NOT_FOR_WRITING = "FileObject was not opened for writing.";
    Context context;
    private final String defaultTargetModule;
    JavacElements elementUtils;
    JavaFileManager fileManager;
    boolean lastRound;
    Log log;
    Modules modules;
    Names names;
    Symtab syms;
    private final Set<FileObject> initialInputs = Collections.synchronizedSet(new LinkedHashSet());
    private final Set<FileObject> fileObjectHistory = Collections.synchronizedSet(new LinkedHashSet());
    private Set<String> generatedSourceNames = Collections.synchronizedSet(new LinkedHashSet());
    private Set<JavaFileObject> generatedSourceFileObjects = Collections.synchronizedSet(new LinkedHashSet());
    private final Map<Symbol.ModuleSymbol, Map<String, JavaFileObject>> generatedClasses = Collections.synchronizedMap(new LinkedHashMap());
    private final Set<String> openTypeNames = Collections.synchronizedSet(new LinkedHashSet());
    private final Set<Pair<Symbol.ModuleSymbol, String>> aggregateGeneratedSourceNames = new LinkedHashSet();
    private final Set<Pair<Symbol.ModuleSymbol, String>> aggregateGeneratedClassNames = new LinkedHashSet();
    private final Set<String> initialClassNames = new LinkedHashSet();

    /* JADX INFO: renamed from: com.sun.tools.javac.processing.JavacFiler$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$tools$JavaFileObject$Kind;

        static {
            int[] iArr = new int[JavaFileObject.Kind.values().length];
            $SwitchMap$javax$tools$JavaFileObject$Kind = iArr;
            try {
                iArr[JavaFileObject.Kind.SOURCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$tools$JavaFileObject$Kind[JavaFileObject.Kind.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public class FilerInputFileObject extends ForwardingFileObject<FileObject> {
        public FilerInputFileObject(FileObject fileObject) {
            super(fileObject);
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public boolean delete() {
            return false;
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public OutputStream openOutputStream() throws IOException {
            throw new IllegalStateException(JavacFiler.NOT_FOR_WRITING);
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public Writer openWriter() throws IOException {
            throw new IllegalStateException(JavacFiler.NOT_FOR_WRITING);
        }
    }

    public class FilerInputJavaFileObject extends FilerInputFileObject implements JavaFileObject {
        private final JavaFileObject javaFileObject;

        public FilerInputJavaFileObject(JavaFileObject javaFileObject) {
            super(javaFileObject);
            this.javaFileObject = javaFileObject;
        }

        @Override // javax.tools.JavaFileObject
        public Modifier getAccessLevel() {
            return this.javaFileObject.getAccessLevel();
        }

        @Override // javax.tools.JavaFileObject
        public JavaFileObject.Kind getKind() {
            return this.javaFileObject.getKind();
        }

        @Override // javax.tools.JavaFileObject
        public NestingKind getNestingKind() {
            return this.javaFileObject.getNestingKind();
        }

        @Override // javax.tools.JavaFileObject
        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            return this.javaFileObject.isNameCompatible(str, kind);
        }
    }

    public class FilerOutputFileObject extends ForwardingFileObject<FileObject> {
        private Symbol.ModuleSymbol mod;
        private String name;
        private boolean opened;

        public FilerOutputFileObject(Symbol.ModuleSymbol moduleSymbol, String str, FileObject fileObject) {
            super(fileObject);
            this.opened = false;
            this.mod = moduleSymbol;
            this.name = str;
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public boolean delete() {
            return false;
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public CharSequence getCharContent(boolean z) throws IOException {
            throw new IllegalStateException(JavacFiler.NOT_FOR_READING);
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public InputStream openInputStream() throws IOException {
            throw new IllegalStateException(JavacFiler.NOT_FOR_READING);
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public synchronized OutputStream openOutputStream() throws IOException {
            if (this.opened) {
                throw new IOException(JavacFiler.ALREADY_OPENED);
            }
            this.opened = true;
            return JavacFiler.this.new FilerOutputStream(this.mod, this.name, this.fileObject);
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public Reader openReader(boolean z) throws IOException {
            throw new IllegalStateException(JavacFiler.NOT_FOR_READING);
        }

        @Override // javax.tools.ForwardingFileObject, javax.tools.FileObject
        public synchronized Writer openWriter() throws IOException {
            if (this.opened) {
                throw new IOException(JavacFiler.ALREADY_OPENED);
            }
            this.opened = true;
            return JavacFiler.this.new FilerWriter(this.mod, this.name, this.fileObject);
        }
    }

    public class FilerOutputJavaFileObject extends FilerOutputFileObject implements JavaFileObject {
        private final JavaFileObject javaFileObject;

        public FilerOutputJavaFileObject(Symbol.ModuleSymbol moduleSymbol, String str, JavaFileObject javaFileObject) {
            super(moduleSymbol, str, javaFileObject);
            this.javaFileObject = javaFileObject;
        }

        @Override // javax.tools.JavaFileObject
        public Modifier getAccessLevel() {
            return this.javaFileObject.getAccessLevel();
        }

        @Override // javax.tools.JavaFileObject
        public JavaFileObject.Kind getKind() {
            return this.javaFileObject.getKind();
        }

        @Override // javax.tools.JavaFileObject
        public NestingKind getNestingKind() {
            return this.javaFileObject.getNestingKind();
        }

        @Override // javax.tools.JavaFileObject
        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            return this.javaFileObject.isNameCompatible(str, kind);
        }
    }

    public class FilerOutputStream extends FilterOutputStream {
        boolean closed;
        FileObject fileObject;
        Symbol.ModuleSymbol mod;
        String typeName;

        public FilerOutputStream(Symbol.ModuleSymbol moduleSymbol, String str, FileObject fileObject) throws IOException {
            super(fileObject.openOutputStream());
            this.closed = false;
            this.mod = moduleSymbol;
            this.typeName = str;
            this.fileObject = fileObject;
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                JavacFiler.this.closeFileObject(this.mod, this.typeName, this.fileObject);
                ((FilterOutputStream) this).out.close();
            }
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            ObjectsWrapper.checkFromIndexSize(i, i2, bArr.length);
            ((FilterOutputStream) this).out.write(bArr, i, i2);
        }
    }

    public class FilerWriter extends FilterWriter {
        boolean closed;
        FileObject fileObject;
        Symbol.ModuleSymbol mod;
        String typeName;

        public FilerWriter(Symbol.ModuleSymbol moduleSymbol, String str, FileObject fileObject) throws IOException {
            super(fileObject.openWriter());
            this.closed = false;
            this.mod = moduleSymbol;
            this.typeName = str;
            this.fileObject = fileObject;
        }

        @Override // java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                JavacFiler.this.closeFileObject(this.mod, this.typeName, this.fileObject);
                ((FilterWriter) this).out.close();
            }
        }
    }

    public static final class Tuple3<A, B, C> {
        final A a;
        final B b;
        final C c;

        public Tuple3(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public JavacFiler(Context context) {
        this.context = context;
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.elementUtils = JavacElements.instance(context);
        this.log = Log.instance(context);
        this.modules = Modules.instance(context);
        this.names = Names.instance(context);
        this.syms = Symtab.instance(context);
        this.defaultTargetModule = Options.instance(context).get(Option.DEFAULT_MODULE_FOR_CREATED_FILES);
    }

    public static /* synthetic */ boolean c(JavaFileObject javaFileObject) {
        return javaFileObject != null;
    }

    private void checkFileReopening(FileObject fileObject, boolean z) throws FilerException {
        if (!isInFileObjectHistory(fileObject, z)) {
            if (z) {
                this.fileObjectHistory.add(fileObject);
            }
        } else {
            this.log.warning(CompilerProperties.LintWarnings.ProcFileReopening(fileObject.getName()));
            throw new FilerException("Attempt to reopen a file for path " + fileObject.getName());
        }
    }

    private void checkName(String str, boolean z) throws FilerException {
        if (SourceVersion.isName(str) || isPackageInfo(str, z)) {
            return;
        }
        this.log.warning(CompilerProperties.LintWarnings.ProcIllegalFileName(str));
        throw new FilerException("Illegal name " + str);
    }

    private void checkNameAndExistence(Symbol.ModuleSymbol moduleSymbol, String str, boolean z) throws FilerException {
        checkName(str, z);
        Symbol.ClassSymbol typeElement = this.elementUtils.getTypeElement((CharSequence) str);
        if (this.aggregateGeneratedSourceNames.contains(Pair.of(moduleSymbol, str)) || this.aggregateGeneratedClassNames.contains(Pair.of(moduleSymbol, str)) || this.initialClassNames.contains(str) || containedInInitialInputs(str)) {
            this.log.warning(CompilerProperties.LintWarnings.ProcTypeRecreate(str));
            throw new FilerException("Attempt to recreate a file for type " + str);
        }
        if (typeElement != null) {
            this.log.warning(CompilerProperties.LintWarnings.ProcTypeAlreadyExists(str));
        }
        if (!moduleSymbol.isUnnamed() && !str.contains(Constants.ATTRVAL_THIS)) {
            throw new FilerException("Attempt to create a type in unnamed package of a named module: ".concat(str));
        }
    }

    private Tuple3<JavaFileManager.Location, Symbol.ModuleSymbol, String> checkOrInferModule(JavaFileManager.Location location, CharSequence charSequence, boolean z) throws IOException {
        String str;
        Symbol.ModuleSymbol moduleSymbolInferModule;
        String string = charSequence.toString();
        int iIndexOf = string.indexOf(47);
        boolean z2 = location.isModuleOrientedLocation() || (this.modules.multiModuleMode && location.isOutputLocation());
        if (iIndexOf != -1) {
            String strSubstring = string.substring(0, iIndexOf);
            string = string.substring(iIndexOf + 1);
            str = strSubstring;
        } else {
            if (!z2) {
                return new Tuple3<>(location, this.modules.getDefaultModule(), string);
            }
            if (location.isOutputLocation() && (moduleSymbolInferModule = inferModule(string)) != null) {
                return new Tuple3<>(this.fileManager.getLocationForModule(location, moduleSymbolInferModule.name.toString()), moduleSymbolInferModule, string);
            }
            str = this.defaultTargetModule;
            if (str == null) {
                throw new FilerException("No module specified and the location is either a module-oriented location, or a multi-module output location.");
            }
        }
        if (!z2) {
            throw new FilerException("Module specified but the location is neither a module-oriented location, nor a multi-module output location.");
        }
        Symbol.ModuleSymbol module = this.syms.getModule(this.names.fromString(str));
        if (module != null) {
            if (!z || this.modules.isRootModule(module)) {
                return new Tuple3<>(this.fileManager.getLocationForModule(location, str), module, string);
            }
            throw new FilerException("Cannot write to the given module.");
        }
        throw new FilerException("Module: " + str + " does not exist.");
    }

    private void clearRoundState() {
        this.generatedSourceNames.clear();
        this.generatedSourceFileObjects.clear();
        this.generatedClasses.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFileObject(Symbol.ModuleSymbol moduleSymbol, String str, FileObject fileObject) {
        if (str != null) {
            if (!(fileObject instanceof JavaFileObject)) {
                s22.a("JavaFileObject not found for ", fileObject);
                return;
            }
            JavaFileObject javaFileObject = (JavaFileObject) fileObject;
            int i = AnonymousClass1.$SwitchMap$javax$tools$JavaFileObject$Kind[javaFileObject.getKind().ordinal()];
            if (i == 1) {
                this.generatedSourceNames.add(str);
                this.generatedSourceFileObjects.add(javaFileObject);
                this.openTypeNames.remove(str);
            } else {
                if (i != 2) {
                    return;
                }
                this.generatedClasses.computeIfAbsent(moduleSymbol, new Function() { // from class: mm7
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Collections.synchronizedMap(new LinkedHashMap());
                    }
                }).put(str, javaFileObject);
                this.openTypeNames.remove(str);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0029  */
    private boolean containedInInitialInputs(String str) {
        JavaFileObject javaFileObject;
        Symbol.ClassSymbol typeElement = this.elementUtils.getTypeElement((CharSequence) str);
        if (typeElement != null) {
            javaFileObject = typeElement.sourcefile;
        } else if (str.endsWith(".package-info")) {
            Symbol.PackageSymbol packageElement = this.elementUtils.getPackageElement((CharSequence) str.substring(0, str.length() - 13));
            if (packageElement != null) {
                javaFileObject = packageElement.sourcefile;
            } else {
                javaFileObject = null;
            }
        } else {
            javaFileObject = null;
        }
        if (javaFileObject == null) {
            return false;
        }
        return this.initialInputs.contains(javaFileObject);
    }

    private JavaFileObject createSourceOrClassFile(Symbol.ModuleSymbol moduleSymbol, boolean z, String str, Element... elementArr) throws IOException {
        Assert.checkNonNull(moduleSymbol);
        int iLastIndexOf = str.lastIndexOf(Constants.ATTRVAL_THIS);
        if (iLastIndexOf != -1) {
            String strSubstring = str.substring(iLastIndexOf);
            String str2 = z ? ".java" : JavaClass.EXTENSION;
            if (strSubstring.equals(str2)) {
                this.log.warning(CompilerProperties.LintWarnings.ProcSuspiciousClassName(str, str2));
            }
        }
        checkNameAndExistence(moduleSymbol, str, z);
        JavaFileManager.Location locationForModule = z ? StandardLocation.SOURCE_OUTPUT : StandardLocation.CLASS_OUTPUT;
        if (this.modules.multiModuleMode) {
            locationForModule = this.fileManager.getLocationForModule(locationForModule, moduleSymbol.name.toString());
        }
        JavaFileObject javaFileForOutputForOriginatingFiles = this.fileManager.getJavaFileForOutputForOriginatingFiles(locationForModule, str, z ? JavaFileObject.Kind.SOURCE : JavaFileObject.Kind.CLASS, originatingFiles(elementArr));
        checkFileReopening(javaFileForOutputForOriginatingFiles, true);
        if (this.lastRound) {
            this.log.warning(CompilerProperties.Warnings.ProcFileCreateLastRound(str));
        }
        if (z) {
            this.aggregateGeneratedSourceNames.add(Pair.of(moduleSymbol, str));
        } else {
            this.aggregateGeneratedClassNames.add(Pair.of(moduleSymbol, str));
        }
        this.openTypeNames.add(str);
        return new FilerOutputJavaFileObject(moduleSymbol, str, javaFileForOutputForOriginatingFiles);
    }

    public static /* synthetic */ JavaFileObject[] d(int i) {
        return new JavaFileObject[i];
    }

    private Symbol.ModuleSymbol inferModule(String str) {
        Symbol.ModuleSymbol moduleSymbol;
        Symbol.ModuleSymbol defaultModule = this.modules.getDefaultModule();
        Symbol.ModuleSymbol moduleSymbol2 = this.syms.noModule;
        Modules modules = this.modules;
        if (defaultModule == moduleSymbol2) {
            return modules.getDefaultModule();
        }
        Set<Symbol.ModuleSymbol> rootModules = modules.getRootModules();
        if (rootModules.size() == 1) {
            return rootModules.iterator().next();
        }
        Symbol.PackageSymbol packageElement = this.elementUtils.getPackageElement((CharSequence) str);
        if (packageElement == null || (moduleSymbol = packageElement.modle) == this.syms.unnamedModule) {
            return null;
        }
        return moduleSymbol;
    }

    private boolean isInFileObjectHistory(FileObject fileObject, boolean z) {
        JavaFileObject javaFileObject;
        JavaFileObject javaFileObject2;
        if (z) {
            Iterator<FileObject> it = this.initialInputs.iterator();
            while (it.hasNext()) {
                try {
                    if (this.fileManager.isSameFile(it.next(), fileObject)) {
                        return true;
                    }
                } catch (IllegalArgumentException unused) {
                }
            }
            Iterator<String> it2 = this.initialClassNames.iterator();
            while (it2.hasNext()) {
                try {
                    Symbol.ClassSymbol typeElement = this.elementUtils.getTypeElement((CharSequence) it2.next());
                    if (typeElement != null && (((javaFileObject = typeElement.sourcefile) != null && this.fileManager.isSameFile(javaFileObject, fileObject)) || ((javaFileObject2 = typeElement.classfile) != null && this.fileManager.isSameFile(javaFileObject2, fileObject)))) {
                        return true;
                    }
                } catch (IllegalArgumentException unused2) {
                }
            }
        }
        Iterator<FileObject> it3 = this.fileObjectHistory.iterator();
        while (it3.hasNext()) {
            if (this.fileManager.isSameFile(it3.next(), fileObject)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPackageInfo(String str, boolean z) {
        int iLastIndexOf = str.lastIndexOf(Constants.ATTRVAL_THIS);
        if (iLastIndexOf != -1) {
            return SourceVersion.isName(str.substring(0, iLastIndexOf)) && str.substring(iLastIndexOf + 1).equals("package-info");
        }
        if (z) {
            return str.equals("package-info");
        }
        return false;
    }

    private void locationCheck(JavaFileManager.Location location) {
        if (location instanceof StandardLocation) {
            StandardLocation standardLocation = (StandardLocation) location;
            if (standardLocation.isOutputLocation()) {
                return;
            }
            aca.a("Resource creation not supported in location ", standardLocation);
        }
    }

    private JavaFileObject[] originatingFiles(Element[] elementArr) {
        if (elementArr == null) {
            return new JavaFileObject[0];
        }
        Stream streamOf = Stream.of((Object[]) elementArr);
        final JavacElements javacElements = this.elementUtils;
        Objects.requireNonNull(javacElements);
        return (JavaFileObject[]) streamOf.map(new Function() { // from class: jm7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return javacElements.getFileObjectOf((Element) obj);
            }
        }).filter(new Predicate() { // from class: km7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return JavacFiler.c((JavaFileObject) obj);
            }
        }).toArray(new IntFunction() { // from class: lm7
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return JavacFiler.d(i);
            }
        });
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        clearRoundState();
        this.initialClassNames.clear();
        this.initialInputs.clear();
        this.fileObjectHistory.clear();
        this.openTypeNames.clear();
        this.aggregateGeneratedSourceNames.clear();
        this.aggregateGeneratedClassNames.clear();
    }

    @Override // javax.annotation.processing.Filer
    public JavaFileObject createClassFile(CharSequence charSequence, Element... elementArr) throws IOException {
        Pair<Symbol.ModuleSymbol, String> pairCheckOrInferModule = checkOrInferModule(charSequence);
        return createSourceOrClassFile(pairCheckOrInferModule.fst, false, pairCheckOrInferModule.snd, elementArr);
    }

    @Override // javax.annotation.processing.Filer
    public FileObject createResource(JavaFileManager.Location location, CharSequence charSequence, CharSequence charSequence2, Element... elementArr) throws IOException {
        Tuple3<JavaFileManager.Location, Symbol.ModuleSymbol, String> tuple3CheckOrInferModule = checkOrInferModule(location, charSequence, true);
        JavaFileManager.Location location2 = tuple3CheckOrInferModule.a;
        Symbol.ModuleSymbol moduleSymbol = tuple3CheckOrInferModule.b;
        String str = tuple3CheckOrInferModule.c;
        locationCheck(location2);
        if (str.length() > 0) {
            checkName(str);
        }
        FileObject fileForOutputForOriginatingFiles = this.fileManager.getFileForOutputForOriginatingFiles(location2, str, charSequence2.toString(), originatingFiles(elementArr));
        checkFileReopening(fileForOutputForOriginatingFiles, true);
        return fileForOutputForOriginatingFiles instanceof JavaFileObject ? new FilerOutputJavaFileObject(moduleSymbol, null, (JavaFileObject) fileForOutputForOriginatingFiles) : new FilerOutputFileObject(moduleSymbol, null, fileForOutputForOriginatingFiles);
    }

    @Override // javax.annotation.processing.Filer
    public JavaFileObject createSourceFile(CharSequence charSequence, Element... elementArr) throws IOException {
        Pair<Symbol.ModuleSymbol, String> pairCheckOrInferModule = checkOrInferModule(charSequence);
        return createSourceOrClassFile(pairCheckOrInferModule.fst, true, pairCheckOrInferModule.snd, elementArr);
    }

    public void displayState() {
        PrintWriter writer = ((Log) this.context.get(Log.logKey)).getWriter(Log.WriterKind.STDERR);
        writer.println("File Object History : " + this.fileObjectHistory);
        writer.println("Open Type Names     : " + this.openTypeNames);
        writer.println("Gen. Src Names      : " + this.generatedSourceNames);
        writer.println("Gen. Cls Names      : " + this.generatedClasses.keySet());
        writer.println("Agg. Gen. Src Names : " + this.aggregateGeneratedSourceNames);
        writer.println("Agg. Gen. Cls Names : " + this.aggregateGeneratedClassNames);
    }

    public Map<Symbol.ModuleSymbol, Map<String, JavaFileObject>> getGeneratedClasses() {
        return this.generatedClasses;
    }

    public Set<JavaFileObject> getGeneratedSourceFileObjects() {
        return this.generatedSourceFileObjects;
    }

    public Set<String> getGeneratedSourceNames() {
        return this.generatedSourceNames;
    }

    @Override // javax.annotation.processing.Filer
    public FileObject getResource(JavaFileManager.Location location, CharSequence charSequence, CharSequence charSequence2) throws IOException {
        String string;
        Tuple3<JavaFileManager.Location, Symbol.ModuleSymbol, String> tuple3CheckOrInferModule = checkOrInferModule(location, charSequence, false);
        JavaFileManager.Location location2 = tuple3CheckOrInferModule.a;
        String str = tuple3CheckOrInferModule.c;
        if (str.length() > 0) {
            checkName(str);
        }
        boolean zIsOutputLocation = location2.isOutputLocation();
        JavaFileManager javaFileManager = this.fileManager;
        FileObject fileForOutputForOriginatingFiles = zIsOutputLocation ? javaFileManager.getFileForOutputForOriginatingFiles(location2, str, charSequence2.toString(), new FileObject[0]) : javaFileManager.getFileForInput(location2, str, charSequence2.toString());
        if (fileForOutputForOriginatingFiles != null) {
            checkFileReopening(fileForOutputForOriginatingFiles, false);
            return new FilerInputFileObject(fileForOutputForOriginatingFiles);
        }
        if (str.length() == 0) {
            string = charSequence2.toString();
        } else {
            string = str + PsuedoNames.PSEUDONAME_ROOT + ((Object) charSequence2);
        }
        throw new FileNotFoundException(string);
    }

    public boolean newFiles() {
        return (this.generatedSourceNames.isEmpty() && this.generatedClasses.isEmpty()) ? false : true;
    }

    public void newRound() {
        clearRoundState();
    }

    public void setInitialState(Collection<? extends JavaFileObject> collection, Collection<String> collection2) {
        this.initialInputs.addAll(collection);
        this.initialClassNames.addAll(collection2);
    }

    public void setLastRound(boolean z) {
        this.lastRound = z;
    }

    public String toString() {
        return "javac Filer";
    }

    public void warnIfUnclosedFiles() {
        if (this.openTypeNames.isEmpty()) {
            return;
        }
        this.log.warning(CompilerProperties.Warnings.ProcUnclosedTypeFiles(this.openTypeNames));
    }

    private void checkName(String str) throws FilerException {
        checkName(str, false);
    }

    private Pair<Symbol.ModuleSymbol, String> checkOrInferModule(CharSequence charSequence) throws FilerException {
        String str;
        String string = charSequence.toString();
        int iIndexOf = string.indexOf(47);
        if (iIndexOf == -1) {
            int iLastIndexOf = string.lastIndexOf(46);
            Symbol.ModuleSymbol moduleSymbolInferModule = inferModule(iLastIndexOf != -1 ? string.substring(0, iLastIndexOf) : "");
            if (moduleSymbolInferModule != null) {
                return Pair.of(moduleSymbolInferModule, string);
            }
            str = this.defaultTargetModule;
            if (str == null) {
                throw new FilerException("Cannot determine target module.");
            }
        } else {
            String strSubstring = string.substring(0, iIndexOf);
            string = string.substring(iIndexOf + 1);
            str = strSubstring;
        }
        Symbol.ModuleSymbol module = this.syms.getModule(this.names.fromString(str));
        if (module != null) {
            if (this.modules.isRootModule(module)) {
                return Pair.of(module, string);
            }
            throw new FilerException("Cannot write to the given module.");
        }
        throw new FilerException("Module: " + str + " does not exist.");
    }
}
