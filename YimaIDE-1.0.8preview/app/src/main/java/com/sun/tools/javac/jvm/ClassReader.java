package com.sun.tools.javac.jvm;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.ClassFinder;
import com.sun.tools.javac.code.DeferredCompletionFailureHandler;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.TargetType;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotationPosition;
import com.sun.tools.javac.code.TypeMetadata;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.file.BaseFileManager;
import com.sun.tools.javac.file.PathFileObject;
import com.sun.tools.javac.jvm.ClassReader;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.InvalidUtfException;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import defpackage.s22;
import defpackage.tw1;
import defpackage.ww1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.CharBuffer;
import java.nio.file.ClosedFileSystemException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassReader {
    public static final int INITIAL_BUFFER_SIZE = 65520;
    protected static final Context.Key<ClassReader> classReaderKey = new Context.Key<>();
    protected Set<AttributeKind> CLASS_ATTRIBUTE;
    protected Set<AttributeKind> CLASS_OR_MEMBER_ATTRIBUTE;
    protected Set<AttributeKind> MEMBER_ATTRIBUTE;
    int[] allParameterAccessFlags;
    boolean allowModules;
    boolean allowRecords;
    boolean allowSealedTypes;
    private final Annotate annotate;
    protected Map<Name, AttributeReader> attributeReaders;
    protected int bp;
    DeferredCompletionFailureHandler dcfh;
    JCDiagnostic.Factory diagFactory;
    private final JavaFileManager fileManager;
    public boolean filling;
    private List<Type> foundTypeVariables;
    final Log log;
    int majorVersion;
    int minorVersion;
    private List<Type> missingTypeVariables;
    final Names names;
    int[] parameterAccessFlags;
    ParameterAnnotations[] parameterAnnotations;
    int[] parameterNameIndicesLvt;
    int[] parameterNameIndicesMp;
    PoolReader poolReader;
    Preview preview;
    boolean previewClassFile;
    public final Profile profile;
    private boolean readingClassAttr;
    CompoundAnnotationProxy repeatable;
    public boolean saveParameterNames;
    int siglimit;
    byte[] signature;
    int sigp;
    Symtab syms;
    CompoundAnnotationProxy target;
    Types types;
    protected Scope.WriteableScope typevars;
    Convert.Validation utf8validation;
    boolean verbose;
    boolean warnOnIllegalUtf8;
    private List<InterimUsesDirective> interimUses = List.nil();
    private List<InterimProvidesDirective> interimProvides = List.nil();
    protected JavaFileObject currentClassFile = null;
    protected Symbol currentOwner = null;
    protected Symbol.ModuleSymbol currentModule = null;
    ByteBuffer buf = new ByteBuffer(INITIAL_BUFFER_SIZE);
    Set<Name> warnedAttrs = new HashSet();
    boolean sigEnterPhase = false;
    byte[] signatureBuffer = new byte[0];
    int sbp = 0;

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.ClassReader$24, reason: invalid class name */
    public class AnonymousClass24 extends AttributeReader {
        public AnonymousClass24(Name name, ClassFile.Version version, Set set) {
            super(name, version, set);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Name classNameMapper(byte[] bArr, int i, int i2) throws InvalidUtfException {
            byte[] bArrInternalize = ClassFile.internalize(bArr, i, i2);
            try {
                ClassReader classReader = ClassReader.this;
                return classReader.names.fromUtf(bArrInternalize, 0, bArrInternalize.length, classReader.utf8validation);
            } catch (InvalidUtfException e) {
                ClassReader classReader2 = ClassReader.this;
                if (!classReader2.warnOnIllegalUtf8) {
                    throw e;
                }
                classReader2.log.warning(CompilerProperties.Warnings.InvalidUtf8InClassfile(classReader2.currentClassFile, CompilerProperties.Fragments.BadUtf8ByteSequenceAt(e.getOffset())));
                return ClassReader.this.names.fromUtfLax(bArrInternalize, 0, bArrInternalize.length);
            }
        }

        @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
        public boolean accepts(AttributeKind attributeKind) {
            return super.accepts(attributeKind) && ClassReader.this.allowModules;
        }

        @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
        public void read(Symbol symbol, int i) {
            ClassReader classReader;
            List list;
            List list2;
            if (symbol.kind != Kinds.Kind.TYP) {
                return;
            }
            Symbol symbol2 = symbol.owner;
            if (symbol2.kind != Kinds.Kind.MDL) {
                return;
            }
            Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) symbol2;
            ListBuffer listBuffer = new ListBuffer();
            ClassReader classReader2 = ClassReader.this;
            PoolReader poolReader = classReader2.poolReader;
            char cNextChar = classReader2.nextChar();
            final ClassReader classReader3 = ClassReader.this;
            Name name = (Name) poolReader.peekModuleName(cNextChar, new PoolReader.Utf8Mapper() { // from class: com.sun.tools.javac.jvm.a
                @Override // com.sun.tools.javac.jvm.PoolReader.Utf8Mapper
                public final Object map(byte[] bArr, int i2, int i3) {
                    return classReader3.readName(bArr, i2, i3);
                }
            });
            ClassReader classReader4 = ClassReader.this;
            Name name2 = classReader4.currentModule.name;
            if (name2 != name) {
                throw classReader4.badClassFile("module.name.mismatch", name, name2);
            }
            moduleSymbol.flags.addAll(classReader4.readModuleFlags(classReader4.nextChar()));
            ClassReader classReader5 = ClassReader.this;
            char cNextChar2 = classReader5.nextChar();
            PoolReader poolReader2 = ClassReader.this.poolReader;
            Objects.requireNonNull(poolReader2);
            moduleSymbol.version = (Name) classReader5.optPoolEntry(cNextChar2, new tw1(poolReader2), null);
            ListBuffer listBuffer2 = new ListBuffer();
            char cNextChar3 = ClassReader.this.nextChar();
            for (int i2 = 0; i2 < cNextChar3; i2++) {
                ClassReader classReader6 = ClassReader.this;
                Symbol.ModuleSymbol module = classReader6.poolReader.getModule(classReader6.nextChar());
                ClassReader classReader7 = ClassReader.this;
                Set<Directive.RequiresFlag> requiresFlags = classReader7.readRequiresFlags(classReader7.nextChar());
                ClassReader classReader8 = ClassReader.this;
                if (module == classReader8.syms.java_base && classReader8.majorVersion >= ClassFile.Version.V54.major) {
                    Directive.RequiresFlag requiresFlag = Directive.RequiresFlag.STATIC_PHASE;
                    if (requiresFlags.contains(requiresFlag)) {
                        throw ClassReader.this.badClassFile("bad.requires.flag", requiresFlag);
                    }
                }
                ClassReader.this.nextChar();
                listBuffer2.add(new Directive.RequiresDirective(module, requiresFlags));
            }
            List<Directive.RequiresDirective> list3 = listBuffer2.toList();
            moduleSymbol.requires = list3;
            listBuffer.addAll(list3);
            ListBuffer listBuffer3 = new ListBuffer();
            char cNextChar4 = ClassReader.this.nextChar();
            for (int i3 = 0; i3 < cNextChar4; i3++) {
                ClassReader classReader9 = ClassReader.this;
                Symbol.PackageSymbol packageSymbol = classReader9.poolReader.getPackage(classReader9.nextChar());
                ClassReader classReader10 = ClassReader.this;
                Set<Directive.ExportsFlag> exportsFlags = classReader10.readExportsFlags(classReader10.nextChar());
                char cNextChar5 = ClassReader.this.nextChar();
                if (cNextChar5 == 0) {
                    list2 = null;
                } else {
                    ListBuffer listBuffer4 = new ListBuffer();
                    for (int i4 = 0; i4 < cNextChar5; i4++) {
                        ClassReader classReader11 = ClassReader.this;
                        listBuffer4.append(classReader11.poolReader.getModule(classReader11.nextChar()));
                    }
                    list2 = listBuffer4.toList();
                }
                listBuffer3.add(new Directive.ExportsDirective(packageSymbol, list2, exportsFlags));
            }
            List<Directive.ExportsDirective> list4 = listBuffer3.toList();
            moduleSymbol.exports = list4;
            listBuffer.addAll(list4);
            ListBuffer listBuffer5 = new ListBuffer();
            char cNextChar6 = ClassReader.this.nextChar();
            if (cNextChar6 != 0 && moduleSymbol.flags.contains(Symbol.ModuleFlags.OPEN)) {
                ClassReader classReader12 = ClassReader.this;
                throw classReader12.badClassFile("module.non.zero.opens", classReader12.currentModule.name);
            }
            for (int i5 = 0; i5 < cNextChar6; i5++) {
                ClassReader classReader13 = ClassReader.this;
                Symbol.PackageSymbol packageSymbol2 = classReader13.poolReader.getPackage(classReader13.nextChar());
                ClassReader classReader14 = ClassReader.this;
                Set<Directive.OpensFlag> opensFlags = classReader14.readOpensFlags(classReader14.nextChar());
                char cNextChar7 = ClassReader.this.nextChar();
                if (cNextChar7 == 0) {
                    list = null;
                } else {
                    ListBuffer listBuffer6 = new ListBuffer();
                    for (int i6 = 0; i6 < cNextChar7; i6++) {
                        ClassReader classReader15 = ClassReader.this;
                        listBuffer6.append(classReader15.poolReader.getModule(classReader15.nextChar()));
                    }
                    list = listBuffer6.toList();
                }
                listBuffer5.add(new Directive.OpensDirective(packageSymbol2, list, opensFlags));
            }
            List<Directive.OpensDirective> list5 = listBuffer5.toList();
            moduleSymbol.opens = list5;
            listBuffer.addAll(list5);
            moduleSymbol.directives = listBuffer.toList();
            ListBuffer listBuffer7 = new ListBuffer();
            char cNextChar8 = ClassReader.this.nextChar();
            int i7 = 0;
            while (true) {
                classReader = ClassReader.this;
                if (i7 >= cNextChar8) {
                    break;
                }
                listBuffer7.add(new InterimUsesDirective((Name) classReader.poolReader.peekClassName(classReader.nextChar(), new PoolReader.Utf8Mapper() { // from class: com.sun.tools.javac.jvm.b
                    @Override // com.sun.tools.javac.jvm.PoolReader.Utf8Mapper
                    public final Object map(byte[] bArr, int i8, int i9) {
                        return this.a.classNameMapper(bArr, i8, i9);
                    }
                })));
                i7++;
            }
            classReader.interimUses = listBuffer7.toList();
            ListBuffer listBuffer8 = new ListBuffer();
            char cNextChar9 = ClassReader.this.nextChar();
            int i8 = 0;
            while (true) {
                ClassReader classReader16 = ClassReader.this;
                if (i8 >= cNextChar9) {
                    classReader16.interimProvides = listBuffer8.toList();
                    return;
                }
                Name name3 = (Name) classReader16.poolReader.peekClassName(classReader16.nextChar(), new PoolReader.Utf8Mapper() { // from class: com.sun.tools.javac.jvm.b
                    @Override // com.sun.tools.javac.jvm.PoolReader.Utf8Mapper
                    public final Object map(byte[] bArr, int i9, int i10) {
                        return this.a.classNameMapper(bArr, i9, i10);
                    }
                });
                char cNextChar10 = ClassReader.this.nextChar();
                ListBuffer listBuffer9 = new ListBuffer();
                for (int i9 = 0; i9 < cNextChar10; i9++) {
                    ClassReader classReader17 = ClassReader.this;
                    listBuffer9.append((Name) classReader17.poolReader.peekClassName(classReader17.nextChar(), new PoolReader.Utf8Mapper() { // from class: com.sun.tools.javac.jvm.b
                        @Override // com.sun.tools.javac.jvm.PoolReader.Utf8Mapper
                        public final Object map(byte[] bArr, int i10, int i11) {
                            return this.a.classNameMapper(bArr, i10, i11);
                        }
                    }));
                    listBuffer8.add(new InterimProvidesDirective(name3, listBuffer9.toList()));
                }
                i8++;
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.ClassReader$28, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass28 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TargetType;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TargetType.values().length];
            $SwitchMap$com$sun$tools$javac$code$TargetType = iArr;
            try {
                iArr[TargetType.INSTANCEOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.LOCAL_VARIABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.EXCEPTION_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_RECEIVER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_TYPE_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_TYPE_PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_TYPE_PARAMETER_BOUND.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_TYPE_PARAMETER_BOUND.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CLASS_EXTENDS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.THROWS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_FORMAL_PARAMETER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CAST.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_INVOCATION_TYPE_ARGUMENT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_REFERENCE_TYPE_ARGUMENT.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.METHOD_RETURN.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.FIELD.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TargetType[TargetType.UNKNOWN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            int[] iArr2 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr2;
            try {
                iArr2[TypeTag.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 6;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 9;
            } catch (NoSuchFieldError unused32) {
            }
        }
    }

    public class AnnotationCompleter extends AnnotationDeproxy implements Runnable {
        final JavaFileObject classFile;
        final List<CompoundAnnotationProxy> l;
        final Symbol sym;

        /* JADX WARN: Code duplicated, block: B:11:0x0025  */
        /* JADX WARN: Illegal instructions before constructor call */
        public AnnotationCompleter(Symbol symbol, List<CompoundAnnotationProxy> list) {
            Symbol symbol2 = ClassReader.this.currentOwner;
            super(symbol2.kind == Kinds.Kind.MTH ? symbol2.enclClass() : (Symbol.ClassSymbol) symbol2);
            if (symbol.kind == Kinds.Kind.TYP) {
                Symbol symbol3 = symbol.owner;
                if (symbol3.kind == Kinds.Kind.MDL) {
                    this.sym = symbol3;
                } else {
                    this.sym = symbol;
                }
            } else {
                this.sym = symbol;
            }
            this.l = list;
            this.classFile = ClassReader.this.currentClassFile;
        }

        @Override // java.lang.Runnable
        public void run() {
            ClassReader classReader = ClassReader.this;
            JavaFileObject javaFileObject = classReader.currentClassFile;
            try {
                classReader.currentClassFile = this.classFile;
                List<Attribute.Compound> listDeproxyCompoundList = deproxyCompoundList(this.l);
                for (Attribute.Compound compound : listDeproxyCompoundList) {
                    Symbol.TypeSymbol typeSymbol = compound.type.tsym;
                    ClassReader classReader2 = ClassReader.this;
                    if (typeSymbol == classReader2.syms.deprecatedType.tsym) {
                        this.sym.flags_field |= 18014398509613056L;
                        Attribute attributeMember = compound.member(classReader2.names.forRemoval);
                        if (attributeMember instanceof Attribute.Constant) {
                            Attribute.Constant constant = (Attribute.Constant) attributeMember;
                            if (constant.type == ClassReader.this.syms.booleanType && ((Integer) constant.value).intValue() != 0) {
                                this.sym.flags_field |= Flags.DEPRECATED_REMOVAL;
                            }
                        }
                    }
                }
                boolean zAnnotationsPendingCompletion = this.sym.annotationsPendingCompletion();
                Symbol symbol = this.sym;
                if (zAnnotationsPendingCompletion) {
                    symbol.setDeclarationAttributes(listDeproxyCompoundList);
                } else {
                    symbol.appendAttributes(listDeproxyCompoundList);
                }
                ClassReader.this.currentClassFile = javaFileObject;
            } catch (Throwable th) {
                ClassReader.this.currentClassFile = javaFileObject;
                throw th;
            }
        }

        public String toString() {
            return " ClassReader annotate " + this.sym.owner + Constants.ATTRVAL_THIS + this.sym + " with " + this.l;
        }
    }

    public class AnnotationDefaultCompleter extends AnnotationDeproxy implements Runnable {
        final JavaFileObject classFile;
        final Symbol.MethodSymbol sym;
        final Attribute value;

        /* JADX WARN: Illegal instructions before constructor call */
        public AnnotationDefaultCompleter(Symbol.MethodSymbol methodSymbol, Attribute attribute) {
            Symbol symbol = ClassReader.this.currentOwner;
            super(symbol.kind == Kinds.Kind.MTH ? symbol.enclClass() : (Symbol.ClassSymbol) symbol);
            this.classFile = ClassReader.this.currentClassFile;
            this.sym = methodSymbol;
            this.value = attribute;
        }

        @Override // java.lang.Runnable
        public void run() {
            ClassReader classReader = ClassReader.this;
            JavaFileObject javaFileObject = classReader.currentClassFile;
            try {
                Symbol.MethodSymbol methodSymbol = this.sym;
                methodSymbol.defaultValue = null;
                classReader.currentClassFile = this.classFile;
                methodSymbol.defaultValue = deproxy(methodSymbol.type.mo73getReturnType(), this.value);
            } finally {
                ClassReader.this.currentClassFile = javaFileObject;
            }
        }

        public String toString() {
            return " ClassReader store default for " + this.sym.owner + Constants.ATTRVAL_THIS + this.sym + " is " + this.value;
        }
    }

    public class AnnotationDeproxy implements ProxyVisitor {
        private Symbol.ClassSymbol requestingOwner;
        Attribute result;
        Type type;

        public AnnotationDeproxy(Symbol.ClassSymbol classSymbol) {
            this.requestingOwner = classSymbol;
        }

        public Attribute deproxy(Type type, Attribute attribute) {
            Type type2 = this.type;
            try {
                this.type = type;
                attribute.accept(this);
                return this.result;
            } finally {
                this.type = type2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Attribute.Compound deproxyCompound(CompoundAnnotationProxy compoundAnnotationProxy) {
            Type typeResolvePossibleProxyType = resolvePossibleProxyType(compoundAnnotationProxy.type);
            ListBuffer listBuffer = new ListBuffer();
            for (List list = compoundAnnotationProxy.values; list.nonEmpty(); list = list.tail) {
                Symbol.MethodSymbol methodSymbolFindAccessMethod = findAccessMethod(typeResolvePossibleProxyType, (Name) ((Pair) list.head).fst);
                listBuffer.append(new Pair(methodSymbolFindAccessMethod, deproxy(methodSymbolFindAccessMethod.type.mo73getReturnType(), (Attribute) ((Pair) list.head).snd)));
            }
            return new Attribute.Compound(typeResolvePossibleProxyType, listBuffer.toList());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public List<Attribute.Compound> deproxyCompoundList(List<CompoundAnnotationProxy> list) {
            ListBuffer listBuffer = new ListBuffer();
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                listBuffer.append(deproxyCompound((CompoundAnnotationProxy) list2.head));
            }
            return listBuffer.toList();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v6, types: [com.sun.tools.javac.util.AbstractLog, com.sun.tools.javac.util.Log] */
        /* JADX WARN: Type inference failed for: r1v2, types: [javax.tools.JavaFileObject] */
        /* JADX WARN: Type inference failed for: r1v3, types: [javax.tools.JavaFileObject] */
        /* JADX WARN: Type inference failed for: r1v6, types: [com.sun.tools.javac.code.Type] */
        /* JADX WARN: Type inference failed for: r8v0, types: [com.sun.tools.javac.jvm.ClassReader$AnnotationDeproxy] */
        /* JADX WARN: Type inference failed for: r8v1, types: [com.sun.tools.javac.jvm.ClassReader$AnnotationDeproxy] */
        /* JADX WARN: Type inference failed for: r8v3, types: [com.sun.tools.javac.util.AbstractLog, com.sun.tools.javac.util.Log] */
        /* JADX WARN: Type inference failed for: r8v6, types: [com.sun.tools.javac.code.Symbol$ClassSymbol, com.sun.tools.javac.code.Symbol$TypeSymbol] */
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
        public Symbol.MethodSymbol findAccessMethod(Type type, Name name) {
            try {
                for (Symbol symbol : type.tsym.members().getSymbolsByName(name)) {
                    if (symbol.kind == Kinds.Kind.MTH && symbol.type.mo71getParameterTypes().length() == 0) {
                        return (Symbol.MethodSymbol) symbol;
                    }
                }
                e = null;
            } catch (Symbol.CompletionFailure e) {
                e = e;
            }
            ?? UseSource = ClassReader.this.log.useSource(this.requestingOwner.classfile);
            ClassReader classReader = ClassReader.this;
            try {
                if (e == null) {
                    classReader.log.warning(CompilerProperties.LintWarnings.AnnotationMethodNotFound(type, name));
                } else {
                    classReader.log.warning(CompilerProperties.LintWarnings.AnnotationMethodNotFoundReason(type, name, e.getDetailValue()));
                }
                ClassReader.this.log.useSource(UseSource);
                List listNil = List.nil();
                UseSource = ClassReader.this.syms.botType;
                List listNil2 = List.nil();
                this = ClassReader.this.syms.methodClass;
                return new Symbol.MethodSymbol(Flags.AnnotationTypeElementMask, name, new Type.MethodType(listNil, UseSource, listNil2, this), type.tsym);
            } catch (Throwable th) {
                ClassReader.this.log.useSource(UseSource);
                throw th;
            }
        }

        public Type resolvePossibleProxyType(Type type) {
            if (!(type instanceof ProxyType)) {
                return type;
            }
            ProxyType proxyType = (ProxyType) type;
            Assert.check(this.requestingOwner.owner instanceof Symbol.ModuleSymbol);
            ClassReader classReader = ClassReader.this;
            Symbol.ModuleSymbol moduleSymbol = classReader.currentModule;
            classReader.currentModule = (Symbol.ModuleSymbol) this.requestingOwner.owner;
            try {
                return proxyType.resolve();
            } finally {
                ClassReader.this.currentModule = moduleSymbol;
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitArray(Attribute.Array array) {
            throw new AssertionError();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.jvm.ClassReader.ProxyVisitor
        public void visitArrayAttributeProxy(ArrayAttributeProxy arrayAttributeProxy) {
            Attribute[] attributeArr = new Attribute[arrayAttributeProxy.values.length()];
            Type typeElemtype = ClassReader.this.types.elemtype(this.type);
            List list = arrayAttributeProxy.values;
            int i = 0;
            while (list.nonEmpty()) {
                attributeArr[i] = deproxy(typeElemtype, (Attribute) list.head);
                list = list.tail;
                i++;
            }
            this.result = new Attribute.Array(this.type, attributeArr);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitClass(Attribute.Class r1) {
            this.result = r1;
        }

        @Override // com.sun.tools.javac.jvm.ClassReader.ProxyVisitor
        public void visitClassAttributeProxy(ClassAttributeProxy classAttributeProxy) {
            this.result = new Attribute.Class(ClassReader.this.types, resolvePossibleProxyType(classAttributeProxy.classType));
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitCompound(Attribute.Compound compound) {
            throw new AssertionError();
        }

        @Override // com.sun.tools.javac.jvm.ClassReader.ProxyVisitor
        public void visitCompoundAnnotationProxy(CompoundAnnotationProxy compoundAnnotationProxy) {
            this.result = deproxyCompound(compoundAnnotationProxy);
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitConstant(Attribute.Constant constant) {
            this.result = constant;
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitEnum(Attribute.Enum r1) {
            throw new AssertionError();
        }

        @Override // com.sun.tools.javac.jvm.ClassReader.ProxyVisitor
        public void visitEnumAttributeProxy(EnumAttributeProxy enumAttributeProxy) {
            Symbol.VarSymbol varSymbol;
            Symbol.TypeSymbol typeSymbol = resolvePossibleProxyType(enumAttributeProxy.enumType).tsym;
            Symbol.VarSymbol varSymbol2 = null;
            try {
                Iterator<Symbol> it = typeSymbol.members().getSymbolsByName(enumAttributeProxy.enumerator).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        varSymbol = null;
                        break;
                    }
                    Symbol next = it.next();
                    if (next.kind == Kinds.Kind.VAR) {
                        varSymbol = (Symbol.VarSymbol) next;
                        break;
                    }
                }
                e = null;
                varSymbol2 = varSymbol;
            } catch (Symbol.CompletionFailure e) {
                e = e;
            }
            if (varSymbol2 != null) {
                this.result = new Attribute.Enum(typeSymbol.type, varSymbol2);
                return;
            }
            ClassReader classReader = ClassReader.this;
            if (e != null) {
                classReader.log.warning(CompilerProperties.Warnings.UnknownEnumConstantReason(classReader.currentClassFile, typeSymbol, enumAttributeProxy.enumerator, e.getDiagnostic()));
            } else {
                classReader.log.warning(CompilerProperties.Warnings.UnknownEnumConstant(classReader.currentClassFile, typeSymbol, enumAttributeProxy.enumerator));
            }
            this.result = new Attribute.Enum(typeSymbol.type, new Symbol.VarSymbol(0L, enumAttributeProxy.enumerator, ClassReader.this.syms.botType, typeSymbol));
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitError(Attribute.Error error) {
            throw new AssertionError();
        }
    }

    public static class ArrayAttributeProxy extends Attribute {
        List<Attribute> values;

        public ArrayAttributeProxy(List<Attribute> list) {
            super(null);
            this.values = list;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Attribute.Visitor visitor) {
            ((ProxyVisitor) visitor).visitArrayAttributeProxy(this);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return "{" + this.values + "}";
        }
    }

    public enum AttributeKind {
        CLASS,
        MEMBER
    }

    public abstract class AttributeReader {
        protected final Set<AttributeKind> kinds;
        protected final Name name;
        protected final ClassFile.Version version;

        public AttributeReader(Name name, ClassFile.Version version, Set<AttributeKind> set) {
            this.name = name;
            this.version = version;
            this.kinds = set;
        }

        public boolean accepts(AttributeKind attributeKind) {
            if (!this.kinds.contains(attributeKind)) {
                return false;
            }
            ClassReader classReader = ClassReader.this;
            int i = classReader.majorVersion;
            ClassFile.Version version = this.version;
            int i2 = version.major;
            if (i > i2) {
                return true;
            }
            if (i == i2 && classReader.minorVersion >= version.minor) {
                return true;
            }
            if (classReader.warnedAttrs.contains(this.name)) {
                return false;
            }
            ClassReader classReader2 = ClassReader.this;
            JavaFileObject javaFileObjectUseSource = classReader2.log.useSource(classReader2.currentClassFile);
            try {
                ClassReader classReader3 = ClassReader.this;
                Log log = classReader3.log;
                Name name = this.name;
                ClassFile.Version version2 = this.version;
                log.warning(CompilerProperties.LintWarnings.FutureAttr(name, version2.major, version2.minor, classReader3.majorVersion, classReader3.minorVersion));
                ClassReader.this.log.useSource(javaFileObjectUseSource);
                ClassReader.this.warnedAttrs.add(this.name);
                return false;
            } catch (Throwable th) {
                ClassReader.this.log.useSource(javaFileObjectUseSource);
                throw th;
            }
        }

        public abstract void read(Symbol symbol, int i);
    }

    public static class ClassAttributeProxy extends Attribute {
        Type classType;

        public ClassAttributeProxy(Type type) {
            super(null);
            this.classType = type;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Attribute.Visitor visitor) {
            ((ProxyVisitor) visitor).visitClassAttributeProxy(this);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return "/*proxy class*/" + this.classType + JavaClass.EXTENSION;
        }
    }

    public class CompleterDeproxy implements Annotate.AnnotationTypeCompleter {
        Symbol.ClassSymbol proxyOn;
        CompoundAnnotationProxy repeatable;
        CompoundAnnotationProxy target;

        public CompleterDeproxy(Symbol.ClassSymbol classSymbol, CompoundAnnotationProxy compoundAnnotationProxy, CompoundAnnotationProxy compoundAnnotationProxy2) {
            this.proxyOn = classSymbol;
            this.target = compoundAnnotationProxy;
            this.repeatable = compoundAnnotationProxy2;
        }

        @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeCompleter
        public void complete(Symbol.ClassSymbol classSymbol) {
            Assert.check(this.proxyOn == classSymbol);
            try {
                Attribute.Compound compoundDeproxyCompound = this.target != null ? ClassReader.this.new AnnotationDeproxy(this.proxyOn).deproxyCompound(this.target) : null;
                Attribute.Compound compoundDeproxyCompound2 = this.repeatable != null ? ClassReader.this.new AnnotationDeproxy(this.proxyOn).deproxyCompound(this.repeatable) : null;
                classSymbol.getAnnotationTypeMetadata().setTarget(compoundDeproxyCompound);
                classSymbol.getAnnotationTypeMetadata().setRepeatable(compoundDeproxyCompound2);
            } catch (Exception e) {
                throw new Symbol.CompletionFailure(classSymbol, new Supplier() { // from class: com.sun.tools.javac.jvm.c
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return ClassReader.this.diagFactory.fragment(CompilerProperties.Fragments.ExceptionMessage(e.getMessage()));
                    }
                }, ClassReader.this.dcfh);
            }
        }
    }

    public static class CompoundAnnotationProxy extends Attribute {
        final List<Pair<Name, Attribute>> values;

        public CompoundAnnotationProxy(Type type, List<Pair<Name, Attribute>> list) {
            super(type);
            this.values = list;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Attribute.Visitor visitor) {
            ((ProxyVisitor) visitor).visitCompoundAnnotationProxy(this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            StringBuilder sb = new StringBuilder("@");
            sb.append((CharSequence) this.type.tsym.getQualifiedName());
            sb.append("/*proxy*/{");
            List list = this.values;
            boolean z = true;
            while (list.nonEmpty()) {
                Pair pair = (Pair) list.head;
                if (!z) {
                    sb.append(",");
                }
                sb.append((CharSequence) pair.fst);
                sb.append("=");
                sb.append(pair.snd);
                list = list.tail;
                z = false;
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public static class EnumAttributeProxy extends Attribute {
        Type enumType;
        Name enumerator;

        public EnumAttributeProxy(Type type, Name name) {
            super(null);
            this.enumType = type;
            this.enumerator = name;
        }

        @Override // com.sun.tools.javac.code.Attribute
        public void accept(Attribute.Visitor visitor) {
            ((ProxyVisitor) visitor).visitEnumAttributeProxy(this);
        }

        @Override // javax.lang.model.element.AnnotationValue
        public String toString() {
            return "/*proxy enum*/" + this.enumType + Constants.ATTRVAL_THIS + this.enumerator;
        }
    }

    public static final class InterimProvidesDirective {
        public final List<Name> impls;
        public final Name service;

        public InterimProvidesDirective(Name name, List<Name> list) {
            this.service = name;
            this.impls = list;
        }
    }

    public static final class InterimUsesDirective {
        public final Name service;

        public InterimUsesDirective(Name name) {
            this.service = name;
        }
    }

    public static class ParameterAnnotations {
        List<CompoundAnnotationProxy> proxies;

        public void add(List<CompoundAnnotationProxy> list) {
            List<CompoundAnnotationProxy> list2 = this.proxies;
            if (list2 == null) {
                this.proxies = list;
            } else {
                this.proxies = list2.prependList(list);
            }
        }
    }

    public class ProxyType extends Type {
        private final Name name;

        public ProxyType(int i) {
            super(ClassReader.this.syms.noSymbol, List.nil());
            this.name = ClassReader.this.poolReader.getName(i);
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.NONE;
        }

        public Type resolve() {
            Name name = this.name;
            ClassReader classReader = ClassReader.this;
            Objects.requireNonNull(classReader);
            return (Type) name.map(new ww1(classReader));
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return "<ProxyType>";
        }
    }

    public interface ProxyVisitor extends Attribute.Visitor {
        void visitArrayAttributeProxy(ArrayAttributeProxy arrayAttributeProxy);

        void visitClassAttributeProxy(ClassAttributeProxy classAttributeProxy);

        void visitCompoundAnnotationProxy(CompoundAnnotationProxy compoundAnnotationProxy);

        void visitEnumAttributeProxy(EnumAttributeProxy enumAttributeProxy);
    }

    public static class SourceFileObject implements JavaFileObject {
        private final Name name;

        public SourceFileObject(Name name) {
            this.name = name;
        }

        @Override // javax.tools.FileObject
        public boolean delete() {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SourceFileObject) && this.name.equals(((SourceFileObject) obj).name);
        }

        @Override // javax.tools.JavaFileObject
        public Modifier getAccessLevel() {
            return null;
        }

        @Override // javax.tools.FileObject
        public CharBuffer getCharContent(boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // javax.tools.JavaFileObject
        public JavaFileObject.Kind getKind() {
            return BaseFileManager.getKind(getName());
        }

        @Override // javax.tools.FileObject
        public long getLastModified() {
            throw new UnsupportedOperationException();
        }

        @Override // javax.tools.FileObject
        public String getName() {
            return this.name.toString();
        }

        @Override // javax.tools.JavaFileObject
        public NestingKind getNestingKind() {
            return null;
        }

        public int hashCode() {
            return this.name.hashCode();
        }

        @Override // javax.tools.JavaFileObject
        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            return true;
        }

        @Override // javax.tools.FileObject
        public InputStream openInputStream() {
            throw new UnsupportedOperationException();
        }

        @Override // javax.tools.FileObject
        public OutputStream openOutputStream() {
            throw new UnsupportedOperationException();
        }

        @Override // javax.tools.FileObject
        public Reader openReader(boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override // javax.tools.FileObject
        public Writer openWriter() {
            throw new UnsupportedOperationException();
        }

        @Override // javax.tools.FileObject
        public URI toUri() {
            try {
                return new URI(null, this.name.toString(), null);
            } catch (URISyntaxException e) {
                throw new PathFileObject.CannotCreateUriError(this.name.toString(), e);
            }
        }
    }

    public class TypeAnnotationCompleter extends AnnotationCompleter {
        List<TypeAnnotationProxy> proxies;

        public TypeAnnotationCompleter(Symbol symbol, List<TypeAnnotationProxy> list) {
            super(symbol, List.nil());
            this.proxies = list;
        }

        public List<Attribute.TypeCompound> deproxyTypeCompoundList(List<TypeAnnotationProxy> list) {
            ListBuffer listBuffer = new ListBuffer();
            for (TypeAnnotationProxy typeAnnotationProxy : list) {
                listBuffer.add(new Attribute.TypeCompound(deproxyCompound(typeAnnotationProxy.compound), typeAnnotationProxy.position));
            }
            return listBuffer.toList();
        }

        @Override // com.sun.tools.javac.jvm.ClassReader.AnnotationCompleter, java.lang.Runnable
        public void run() {
            ClassReader classReader = ClassReader.this;
            JavaFileObject javaFileObject = classReader.currentClassFile;
            try {
                classReader.currentClassFile = this.classFile;
                List<Attribute.TypeCompound> listDeproxyTypeCompoundList = deproxyTypeCompoundList(this.proxies);
                Symbol symbol = this.sym;
                symbol.setTypeAttributes(listDeproxyTypeCompoundList.prependList(symbol.getRawTypeAttributes()));
                ClassReader.this.addTypeAnnotationsToSymbol(this.sym, listDeproxyTypeCompoundList);
            } finally {
                ClassReader.this.currentClassFile = javaFileObject;
            }
        }
    }

    public static class TypeAnnotationProxy {
        final CompoundAnnotationProxy compound;
        final TypeAnnotationPosition position;

        public TypeAnnotationProxy(CompoundAnnotationProxy compoundAnnotationProxy, TypeAnnotationPosition typeAnnotationPosition) {
            this.compound = compoundAnnotationProxy;
            this.position = typeAnnotationPosition;
        }
    }

    public final class UsesProvidesCompleter implements Symbol.Completer {
        private final Symbol.ModuleSymbol currentModule;
        private final List<InterimProvidesDirective> interimProvidesCopy;
        private final List<InterimUsesDirective> interimUsesCopy;

        public UsesProvidesCompleter(Symbol.ModuleSymbol moduleSymbol, List<InterimUsesDirective> list, List<InterimProvidesDirective> list2) {
            this.currentModule = moduleSymbol;
            this.interimUsesCopy = list;
            this.interimProvidesCopy = list2;
        }

        @Override // com.sun.tools.javac.code.Symbol.Completer
        public void complete(Symbol symbol) throws Symbol.CompletionFailure {
            ListBuffer listBuffer = new ListBuffer();
            listBuffer.addAll(this.currentModule.directives);
            ListBuffer listBuffer2 = new ListBuffer();
            Iterator<InterimUsesDirective> it = this.interimUsesCopy.iterator();
            while (it.hasNext()) {
                Directive.UsesDirective usesDirective = new Directive.UsesDirective(ClassReader.this.syms.enterClass(this.currentModule, it.next().service));
                listBuffer2.add(usesDirective);
                listBuffer.add(usesDirective);
            }
            this.currentModule.uses = listBuffer2.toList();
            ListBuffer listBuffer3 = new ListBuffer();
            for (InterimProvidesDirective interimProvidesDirective : this.interimProvidesCopy) {
                ListBuffer listBuffer4 = new ListBuffer();
                Iterator<Name> it2 = interimProvidesDirective.impls.iterator();
                while (it2.hasNext()) {
                    listBuffer4.append(ClassReader.this.syms.enterClass(this.currentModule, it2.next()));
                }
                Directive.ProvidesDirective providesDirective = new Directive.ProvidesDirective(ClassReader.this.syms.enterClass(this.currentModule, interimProvidesDirective.service), listBuffer4.toList());
                listBuffer3.add(providesDirective);
                listBuffer.add(providesDirective);
            }
            this.currentModule.provides = listBuffer3.toList();
            this.currentModule.directives = listBuffer.toList();
        }
    }

    public ClassReader(Context context) {
        AttributeKind attributeKind = AttributeKind.CLASS;
        this.CLASS_ATTRIBUTE = EnumSet.of(attributeKind);
        AttributeKind attributeKind2 = AttributeKind.MEMBER;
        this.MEMBER_ATTRIBUTE = EnumSet.of(attributeKind2);
        this.CLASS_OR_MEMBER_ATTRIBUTE = EnumSet.of(attributeKind, attributeKind2);
        this.attributeReaders = new HashMap();
        this.readingClassAttr = false;
        this.missingTypeVariables = List.nil();
        this.foundTypeVariables = List.nil();
        this.filling = false;
        context.put(classReaderKey, this);
        this.annotate = Annotate.instance(context);
        this.names = Names.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
        JavaFileManager javaFileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.fileManager = javaFileManager;
        if (javaFileManager == null) {
            x01.a("FileManager initialization error");
            throw null;
        }
        this.diagFactory = JCDiagnostic.Factory.instance(context);
        this.dcfh = DeferredCompletionFailureHandler.instance(context);
        this.log = Log.instance(context);
        Options optionsInstance = Options.instance(context);
        this.verbose = optionsInstance.isSet(Option.VERBOSE);
        Source sourceInstance = Source.instance(context);
        this.preview = Preview.instance(context);
        this.allowModules = Source.Feature.MODULES.allowedInSource(sourceInstance);
        this.allowRecords = Source.Feature.RECORDS.allowedInSource(sourceInstance);
        this.allowSealedTypes = Source.Feature.SEALED_CLASSES.allowedInSource(sourceInstance);
        this.warnOnIllegalUtf8 = Source.Feature.WARN_ON_ILLEGAL_UTF8.allowedInSource(sourceInstance);
        this.saveParameterNames = optionsInstance.isSet(Option.PARAMETERS);
        this.profile = Profile.instance(context);
        this.typevars = Scope.WriteableScope.create(this.syms.noSymbol);
        initAttributeReaders();
    }

    public static /* synthetic */ boolean a(Symbol symbol) {
        return symbol.kind == Kinds.Kind.MTH;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTypeAnnotationsToSymbol(Symbol symbol, List<Attribute.TypeCompound> list) {
        try {
            new TypeAnnotationSymbolVisitor(list).visit(symbol, null);
        } catch (Symbol.CompletionFailure e) {
            JavaFileObject javaFileObjectUseSource = this.log.useSource(this.currentClassFile);
            try {
                this.log.error(CompilerProperties.Errors.CantAttachTypeAnnotations(list, symbol.owner, symbol.name, e.getDetailValue()));
            } finally {
                this.log.useSource(javaFileObjectUseSource);
            }
        }
    }

    private List<Type> adjustMethodParams(long j, List<Type> list) {
        if (list.isEmpty()) {
            return list;
        }
        if ((j & Flags.VARARGS) != 0) {
            Type typeLast = list.last();
            ListBuffer listBuffer = new ListBuffer();
            for (Type typeMakeVarargs : list) {
                if (typeMakeVarargs == typeLast) {
                    typeMakeVarargs = ((Type.ArrayType) typeMakeVarargs).makeVarargs();
                }
                listBuffer.append(typeMakeVarargs);
            }
            list = listBuffer.toList();
        }
        return list.tail;
    }

    private void dropParameterAnnotations() {
        this.parameterAnnotations = null;
        this.log.warning(CompilerProperties.LintWarnings.RuntimeInvisibleParameterAnnotations(this.currentClassFile));
    }

    private void enterMember(Symbol.ClassSymbol classSymbol, Symbol symbol) {
        if ((symbol.flags_field & 2147487744L) != 4096 || symbol.name.startsWith(this.names.lambda)) {
            classSymbol.members_field.enter(symbol);
        }
    }

    private Symbol.MethodSymbol findMethod(PoolConstant.NameAndType nameAndType, Scope scope, long j) {
        if (nameAndType == null) {
            return null;
        }
        Type.MethodType methodTypeAsMethodType = nameAndType.type.asMethodType();
        for (Symbol symbol : scope.getSymbolsByName(nameAndType.name)) {
            if (symbol.kind == Kinds.Kind.MTH && isSameBinaryType(symbol.type.asMethodType(), methodTypeAsMethodType)) {
                return (Symbol.MethodSymbol) symbol;
            }
        }
        if (nameAndType.name == this.names.init && (512 & j) == 0 && !nameAndType.type.mo71getParameterTypes().isEmpty()) {
            return findMethod(new PoolConstant.NameAndType(nameAndType.name, new Type.MethodType(nameAndType.type.mo71getParameterTypes().tail, nameAndType.type.mo73getReturnType(), nameAndType.type.mo74getThrownTypes(), this.syms.methodClass)), scope, j);
        }
        return null;
    }

    private void initAttributeReaders() {
        Name name = this.names.Code;
        ClassFile.Version version = ClassFile.Version.V45_3;
        AttributeReader attributeReader = new AttributeReader(name, version, this.MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.2
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader classReader = ClassReader.this;
                if (!classReader.saveParameterNames) {
                    classReader.bp += i;
                } else {
                    ((Symbol.MethodSymbol) symbol).code = classReader.readCode(symbol);
                }
            }
        };
        AttributeReader attributeReader2 = new AttributeReader(this.names.ConstantValue, version, this.MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.3
            public void checkType(Symbol symbol, Class<?> cls, Object obj) {
                if (!cls.isInstance(obj)) {
                    throw ClassReader.this.badClassFile("bad.constant.value", obj, symbol, cls.getSimpleName());
                }
            }

            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader classReader = ClassReader.this;
                Object constant = classReader.poolReader.getConstant(classReader.nextChar());
                if ((symbol.flags() & 16) == 0) {
                    return;
                }
                Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
                switch (AnonymousClass28.$SwitchMap$com$sun$tools$javac$code$TypeTag[varSymbol.type.getTag().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        checkType(varSymbol, Integer.class, constant);
                        break;
                    case 6:
                        checkType(varSymbol, Long.class, constant);
                        break;
                    case 7:
                        checkType(varSymbol, Float.class, constant);
                        break;
                    case 8:
                        checkType(varSymbol, Double.class, constant);
                        break;
                    case 9:
                        Type type = varSymbol.type;
                        Symbol.TypeSymbol typeSymbol = type.tsym;
                        ClassReader classReader2 = ClassReader.this;
                        if (typeSymbol != classReader2.syms.stringType.tsym) {
                            throw classReader2.badClassFile("bad.constant.value.type", type);
                        }
                        checkType(varSymbol, String.class, constant);
                        break;
                        break;
                    default:
                        return;
                }
                if ((constant instanceof Integer) && !varSymbol.type.getTag().checkRange(((Integer) constant).intValue())) {
                    throw ClassReader.this.badClassFile("bad.constant.range", constant, varSymbol, varSymbol.type);
                }
                varSymbol.setData(constant);
            }
        };
        AttributeReader attributeReader3 = new AttributeReader(this.names.Deprecated, version, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.4
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                Symbol symbol2 = symbol.owner;
                if (symbol2.kind == Kinds.Kind.MDL) {
                    symbol = symbol2;
                }
                symbol.flags_field |= Flags.BODY_ONLY_FINALIZE;
            }
        };
        AttributeReader attributeReader4 = new AttributeReader(this.names.Exceptions, version, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.5
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                char cNextChar = ClassReader.this.nextChar();
                List listNil = List.nil();
                for (int i2 = 0; i2 < cNextChar; i2++) {
                    ClassReader classReader = ClassReader.this;
                    listNil = listNil.prepend(classReader.poolReader.getClass(classReader.nextChar()).type);
                }
                if (symbol.type.mo74getThrownTypes().isEmpty()) {
                    symbol.type.asMethodType().thrown = listNil.reverse();
                }
            }
        };
        AttributeReader attributeReader5 = new AttributeReader(this.names.InnerClasses, version, this.CLASS_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.6
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
                ClassReader classReader = ClassReader.this;
                if (classReader.currentModule.module_info == classSymbol) {
                    classReader.skipInnerClasses();
                } else {
                    classReader.readInnerClasses(classSymbol);
                }
            }
        };
        AttributeReader attributeReader6 = new AttributeReader(this.names.LocalVariableTable, version, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.7
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader classReader = ClassReader.this;
                int i2 = classReader.bp + i;
                if (classReader.saveParameterNames) {
                    char cNextChar = classReader.nextChar();
                    for (int i3 = 0; i3 < cNextChar; i3++) {
                        char cNextChar2 = ClassReader.this.nextChar();
                        ClassReader.this.nextChar();
                        char cNextChar3 = ClassReader.this.nextChar();
                        ClassReader.this.nextChar();
                        char cNextChar4 = ClassReader.this.nextChar();
                        if (cNextChar2 == 0) {
                            int[] iArr = ClassReader.this.parameterNameIndicesLvt;
                            if (cNextChar4 >= iArr.length) {
                                int iMax = Math.max(cNextChar4 + 1, iArr.length + 8);
                                ClassReader classReader2 = ClassReader.this;
                                classReader2.parameterNameIndicesLvt = Arrays.copyOf(classReader2.parameterNameIndicesLvt, iMax);
                            }
                            ClassReader.this.parameterNameIndicesLvt[cNextChar4] = cNextChar3;
                        }
                    }
                }
                ClassReader.this.bp = i2;
            }
        };
        AttributeReader attributeReader7 = new AttributeReader(this.names.SourceFile, version, this.CLASS_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.8
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
                ClassReader classReader = ClassReader.this;
                Name name2 = classReader.poolReader.getName(classReader.nextChar());
                classSymbol.sourcefile = new SourceFileObject(name2);
                String string = name2.toString();
                if (classSymbol.owner.kind == Kinds.Kind.PCK && string.endsWith(".java")) {
                    if (string.equals(classSymbol.name.toString() + ".java")) {
                        return;
                    }
                    classSymbol.flags_field |= Flags.AUXILIARY;
                }
            }
        };
        AttributeReader attributeReader8 = new AttributeReader(this.names.Synthetic, version, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.9
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                symbol.flags_field |= 4096;
            }
        };
        Name name2 = this.names.EnclosingMethod;
        ClassFile.Version version2 = ClassFile.Version.V49;
        AttributeReader attributeReader9 = new AttributeReader(name2, version2, this.CLASS_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.10
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader classReader = ClassReader.this;
                int i2 = classReader.bp + i;
                classReader.readEnclosingMethodAttr(symbol);
                ClassReader.this.bp = i2;
            }
        };
        AttributeReader attributeReader10 = new AttributeReader(this.names.Signature, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.11
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                if (symbol.kind != Kinds.Kind.TYP) {
                    List<Type> listMo74getThrownTypes = symbol.type.mo74getThrownTypes();
                    ClassReader classReader = ClassReader.this;
                    Type type = classReader.poolReader.getType(classReader.nextChar());
                    symbol.type = type;
                    if (symbol.kind == Kinds.Kind.MTH && type.mo74getThrownTypes().isEmpty()) {
                        symbol.type.asMethodType().thrown = listMo74getThrownTypes;
                        return;
                    }
                    return;
                }
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
                boolean z = true;
                ClassReader.this.readingClassAttr = true;
                try {
                    Type.ClassType classType = (Type.ClassType) classSymbol.type;
                    if (classSymbol != ClassReader.this.currentOwner) {
                        z = false;
                    }
                    Assert.check(z);
                    ClassReader classReader2 = ClassReader.this;
                    Name name3 = classReader2.poolReader.getName(classReader2.nextChar());
                    final ClassReader classReader3 = ClassReader.this;
                    Objects.requireNonNull(classReader3);
                    classType.typarams_field = (List) name3.map(new Name.NameMapper() { // from class: vw1
                        @Override // com.sun.tools.javac.util.Name.NameMapper
                        public final Object map(byte[] bArr, int i2, int i3) {
                            return classReader3.sigToTypeParams(bArr, i2, i3);
                        }
                    });
                    classType.supertype_field = ClassReader.this.sigToType();
                    ListBuffer listBuffer = new ListBuffer();
                    while (true) {
                        ClassReader classReader4 = ClassReader.this;
                        if (classReader4.sigp == classReader4.siglimit) {
                            classType.interfaces_field = listBuffer.toList();
                            return;
                        }
                        listBuffer.append(classReader4.sigToType());
                    }
                } finally {
                    ClassReader.this.readingClassAttr = false;
                }
            }
        };
        AttributeReader attributeReader11 = new AttributeReader(this.names.AnnotationDefault, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.12
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.attachAnnotationDefault(symbol);
            }
        };
        AttributeReader attributeReader12 = new AttributeReader(this.names.RuntimeInvisibleAnnotations, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.13
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.attachAnnotations(symbol);
            }
        };
        AttributeReader attributeReader13 = new AttributeReader(this.names.RuntimeInvisibleParameterAnnotations, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.14
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.readParameterAnnotations(symbol);
            }
        };
        AttributeReader attributeReader14 = new AttributeReader(this.names.RuntimeVisibleAnnotations, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.15
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.attachAnnotations(symbol);
            }
        };
        AttributeReader attributeReader15 = new AttributeReader(this.names.RuntimeVisibleParameterAnnotations, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.16
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.readParameterAnnotations(symbol);
            }
        };
        AttributeReader attributeReader16 = new AttributeReader(this.names.Annotation, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.17
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                symbol.flags_field |= 8192;
            }
        };
        AttributeReader attributeReader17 = new AttributeReader(this.names.Bridge, version2, this.MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.18
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                symbol.flags_field |= Flags.BRIDGE;
            }
        };
        AttributeReader attributeReader18 = new AttributeReader(this.names.Enum, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.19
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                symbol.flags_field |= 16384;
            }
        };
        AttributeReader attributeReader19 = new AttributeReader(this.names.Varargs, version2, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.20
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                symbol.flags_field |= Flags.VARARGS;
            }
        };
        Name name3 = this.names.RuntimeVisibleTypeAnnotations;
        ClassFile.Version version3 = ClassFile.Version.V52;
        AttributeReader attributeReader20 = new AttributeReader(name3, version3, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.21
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.attachTypeAnnotations(symbol);
            }
        };
        AttributeReader attributeReader21 = new AttributeReader(this.names.RuntimeInvisibleTypeAnnotations, version3, this.CLASS_OR_MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.22
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader.this.attachTypeAnnotations(symbol);
            }
        };
        AttributeReader attributeReader22 = new AttributeReader(this.names.MethodParameters, version3, this.MEMBER_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.23
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                ClassReader classReader = ClassReader.this;
                int i2 = classReader.bp + i;
                if (classReader.saveParameterNames) {
                    int iNextByte = classReader.nextByte();
                    ClassReader classReader2 = ClassReader.this;
                    classReader2.allParameterAccessFlags = new int[iNextByte];
                    classReader2.parameterNameIndicesMp = new int[iNextByte];
                    classReader2.parameterAccessFlags = new int[iNextByte];
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    while (i3 < iNextByte) {
                        char cNextChar = ClassReader.this.nextChar();
                        char cNextChar2 = ClassReader.this.nextChar();
                        ClassReader classReader3 = ClassReader.this;
                        int i6 = i4 + 1;
                        classReader3.allParameterAccessFlags[i4] = cNextChar2;
                        if ((36864 & cNextChar2) == 0) {
                            classReader3.parameterNameIndicesMp[i5] = cNextChar;
                            classReader3.parameterAccessFlags[i5] = cNextChar2;
                            i5++;
                        }
                        i3++;
                        i4 = i6;
                    }
                }
                ClassReader.this.bp = i2;
            }
        };
        Name name4 = this.names.Module;
        ClassFile.Version version4 = ClassFile.Version.V53;
        AttributeReader[] attributeReaderArr = {attributeReader, attributeReader2, attributeReader3, attributeReader4, attributeReader5, attributeReader6, attributeReader7, attributeReader8, attributeReader9, attributeReader10, attributeReader11, attributeReader12, attributeReader13, attributeReader14, attributeReader15, attributeReader16, attributeReader17, attributeReader18, attributeReader19, attributeReader20, attributeReader21, attributeReader22, new AnonymousClass24(name4, version4, this.CLASS_ATTRIBUTE), new AttributeReader(this.names.ModuleResolution, version4, this.CLASS_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.25
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public boolean accepts(AttributeKind attributeKind) {
                return super.accepts(attributeKind) && ClassReader.this.allowModules;
            }

            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                if (symbol.kind == Kinds.Kind.TYP) {
                    Symbol symbol2 = symbol.owner;
                    if (symbol2.kind == Kinds.Kind.MDL) {
                        Set<Symbol.ModuleResolutionFlags> set = ((Symbol.ModuleSymbol) symbol2).resolutionFlags;
                        ClassReader classReader = ClassReader.this;
                        set.addAll(classReader.readModuleResolutionFlags(classReader.nextChar()));
                    }
                }
            }
        }, new AttributeReader(this.names.Record, ClassFile.Version.V58, this.CLASS_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.26
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public boolean accepts(AttributeKind attributeKind) {
                return super.accepts(attributeKind) && ClassReader.this.allowRecords;
            }

            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                if (symbol.kind == Kinds.Kind.TYP) {
                    symbol.flags_field |= Flags.RECORD;
                }
                char cNextChar = ClassReader.this.nextChar();
                ListBuffer listBuffer = new ListBuffer();
                for (int i2 = 0; i2 < cNextChar; i2++) {
                    ClassReader classReader = ClassReader.this;
                    Name name5 = classReader.poolReader.getName(classReader.nextChar());
                    ClassReader classReader2 = ClassReader.this;
                    Symbol.RecordComponent recordComponent = new Symbol.RecordComponent(name5, classReader2.poolReader.getType(classReader2.nextChar()), symbol);
                    ClassReader.this.readAttrs(recordComponent, AttributeKind.MEMBER);
                    listBuffer.add(recordComponent);
                }
                ((Symbol.ClassSymbol) symbol).setRecordComponents(listBuffer.toList());
            }
        }, new AttributeReader(this.names.PermittedSubclasses, ClassFile.Version.V59, this.CLASS_ATTRIBUTE) { // from class: com.sun.tools.javac.jvm.ClassReader.27
            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public boolean accepts(AttributeKind attributeKind) {
                return super.accepts(attributeKind) && ClassReader.this.allowSealedTypes;
            }

            @Override // com.sun.tools.javac.jvm.ClassReader.AttributeReader
            public void read(Symbol symbol, int i) {
                if (symbol.kind == Kinds.Kind.TYP) {
                    ListBuffer listBuffer = new ListBuffer();
                    char cNextChar = ClassReader.this.nextChar();
                    for (int i2 = 0; i2 < cNextChar; i2++) {
                        ClassReader classReader = ClassReader.this;
                        listBuffer.add(classReader.poolReader.getClass(classReader.nextChar()));
                    }
                    ((Symbol.ClassSymbol) symbol).setPermittedSubclasses(listBuffer.toList());
                }
            }
        }};
        for (int i = 0; i < 26; i++) {
            AttributeReader attributeReader23 = attributeReaderArr[i];
            this.attributeReaders.put(attributeReader23.name, attributeReader23);
        }
    }

    public static ClassReader instance(Context context) {
        ClassReader classReader = (ClassReader) context.get(classReaderKey);
        return classReader == null ? new ClassReader(context) : classReader;
    }

    private static boolean isAsciiDigit(char c) {
        return '0' <= c && c <= '9';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isSameBinaryType(Type.MethodType methodType, Type.MethodType methodType2) {
        List listPrepend = this.types.erasure(methodType.mo71getParameterTypes()).prepend(this.types.erasure(methodType.mo73getReturnType()));
        List listPrepend2 = methodType2.mo71getParameterTypes().prepend(methodType2.mo73getReturnType());
        while (!listPrepend.isEmpty() && !listPrepend2.isEmpty()) {
            if (((Type) listPrepend.head).tsym != ((Type) listPrepend2.head).tsym) {
                return false;
            }
            listPrepend = listPrepend.tail;
            listPrepend2 = listPrepend2.tail;
        }
        return listPrepend.isEmpty() && listPrepend2.isEmpty();
    }

    private Symbol.MethodSymbol lookupMethod(Symbol.TypeSymbol typeSymbol, Name name, List<Type> list) {
        for (Symbol symbol : typeSymbol.members().getSymbolsByName(name, new Predicate() { // from class: rw1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ClassReader.a((Symbol) obj);
            }
        })) {
            if (this.types.isSameTypes(symbol.type.mo71getParameterTypes(), list)) {
                return (Symbol.MethodSymbol) symbol;
            }
        }
        return null;
    }

    private Symbol.VarSymbol parameter(int i, int i2, Type type, Symbol.MethodSymbol methodSymbol, Set<Name> set) {
        Name nameFromString;
        Name name;
        int i3;
        Name name2;
        int i4;
        int i5;
        int[] iArr = this.parameterAccessFlags;
        long j = 8589934592L;
        if (iArr != null && i < iArr.length && (i5 = iArr[i]) != 0) {
            j = 8589934592L | ((long) i5);
        }
        int[] iArr2 = this.parameterNameIndicesMp;
        if (iArr2 == null || i >= iArr2.length || (i4 = iArr2[i]) == 0) {
            int[] iArr3 = this.parameterNameIndicesLvt;
            if (iArr3 == null || i2 >= iArr3.length || (i3 = iArr3[i2]) == 0) {
                String strConcat = Constants.ELEMNAME_ARG_STRING;
                while (true) {
                    nameFromString = this.names.fromString(strConcat + set.size());
                    if (!set.contains(nameFromString)) {
                        break;
                    }
                    strConcat = strConcat.concat("$");
                }
                name = nameFromString;
            } else {
                PoolReader poolReader = this.poolReader;
                Objects.requireNonNull(poolReader);
                name2 = (Name) optPoolEntry(i3, new tw1(poolReader), this.names.empty);
            }
            long j2 = j;
            set.add(name);
            return new Symbol.ParamSymbol(j2, name, type, methodSymbol);
        }
        PoolReader poolReader2 = this.poolReader;
        Objects.requireNonNull(poolReader2);
        name2 = (Name) optPoolEntry(i4, new tw1(poolReader2), this.names.empty);
        j |= 4503599627370496L;
        name = name2;
        long j3 = j;
        set.add(name);
        return new Symbol.ParamSymbol(j3, name, type, methodSymbol);
    }

    private void readClassBuffer(Symbol.ClassSymbol classSymbol) throws IOException {
        if (nextInt() != -889275714) {
            throw badClassFile("illegal.start.of.class.file", new Object[0]);
        }
        this.minorVersion = nextChar();
        this.majorVersion = nextChar();
        int i = ClassFile.Version.MAX().major;
        int i2 = ClassFile.Version.MAX().minor;
        int i3 = this.minorVersion;
        this.previewClassFile = i3 == 65535;
        int i4 = this.majorVersion;
        if (i4 > i || (i4 * 1000) + i3 < (ClassFile.Version.MIN().major * 1000) + ClassFile.Version.MIN().minor) {
            int i5 = this.majorVersion;
            if (i5 != i + 1 || this.previewClassFile) {
                throw badClassFile("wrong.version", Integer.toString(i5), Integer.toString(this.minorVersion), Integer.toString(i), Integer.toString(i2));
            }
            this.log.warning(CompilerProperties.Warnings.BigMajorVersion(this.currentClassFile, i5, i));
        }
        this.utf8validation = this.majorVersion < ClassFile.Version.V48.major ? Convert.Validation.PREJDK14 : Convert.Validation.STRICT;
        if (this.previewClassFile) {
            if (this.preview.isEnabled()) {
                this.preview.warnPreview(classSymbol.classfile, this.majorVersion);
            } else {
                this.log.error(this.preview.disabledError(this.currentClassFile, this.majorVersion));
            }
        }
        PoolReader poolReader = new PoolReader(this, this.names, this.syms);
        this.poolReader = poolReader;
        int pool = poolReader.readPool(this.buf, this.bp);
        this.bp = pool;
        if (this.signatureBuffer.length < pool) {
            this.signatureBuffer = new byte[Integer.highestOneBit(pool) << 1];
        }
        readClass(classSymbol);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Name readName(byte[] bArr, int i, int i2) {
        try {
            return this.names.fromUtf(bArr, i, i2, this.utf8validation);
        } catch (InvalidUtfException unused) {
            if (!this.warnOnIllegalUtf8) {
                throw badClassFile(CompilerProperties.Fragments.BadUtf8ByteSequenceAt(this.sigp));
            }
            this.log.warning(CompilerProperties.Warnings.InvalidUtf8InClassfile(this.currentClassFile, CompilerProperties.Fragments.BadUtf8ByteSequenceAt(this.sigp)));
            return this.names.fromUtfLax(bArr, i, i2);
        }
    }

    private void setFlagIfAttributeTrue(CompoundAnnotationProxy compoundAnnotationProxy, Symbol symbol, Name name, long j) {
        for (Pair<Name, Attribute> pair : compoundAnnotationProxy.values) {
            if (pair.fst == name) {
                Attribute attribute = pair.snd;
                if (attribute instanceof Attribute.Constant) {
                    Attribute.Constant constant = (Attribute.Constant) attribute;
                    if (constant.type == this.syms.booleanType && ((Integer) constant.value).intValue() != 0) {
                        symbol.flags_field |= j;
                    }
                }
            }
        }
    }

    private Name simpleBinaryName(Name name, Name name2) {
        if (!name.startsWith(name2)) {
            throw badClassFile("bad.enclosing.method", name);
        }
        String strSubstring = name.toString().substring(name2.toString().length());
        int i = 1;
        if (strSubstring.length() < 1 || strSubstring.charAt(0) != '$') {
            throw badClassFile("bad.enclosing.method", name);
        }
        while (i < strSubstring.length() && isAsciiDigit(strSubstring.charAt(i))) {
            i++;
        }
        return this.names.fromString(strSubstring.substring(i));
    }

    public long adjustClassFlags(long j) {
        if ((32768 & j) != 0) {
            j = (j & (-32769)) | 2251799813685248L;
        }
        return j & (-33);
    }

    public long adjustFieldFlags(long j) {
        return j;
    }

    public long adjustMethodFlags(long j) {
        if ((64 & j) != 0) {
            j = (j & (-65)) | Flags.BRIDGE;
        }
        return (128 & j) != 0 ? (j & (-129)) | Flags.VARARGS : j;
    }

    public void adjustParameterAnnotations(Symbol.MethodSymbol methodSymbol, Type type, boolean z) {
        int size;
        if (this.parameterAnnotations == null || (size = methodSymbol.type.mo71getParameterTypes().size()) == this.parameterAnnotations.length) {
            return;
        }
        int i = 0;
        if (this.allParameterAccessFlags == null) {
            if (!methodSymbol.isConstructor()) {
                dropParameterAnnotations();
                return;
            }
            if (methodSymbol.owner.isEnum()) {
                ParameterAnnotations[] parameterAnnotationsArr = this.parameterAnnotations;
                if (size == parameterAnnotationsArr.length + 2 && methodSymbol.type == type) {
                    ParameterAnnotations[] parameterAnnotationsArr2 = new ParameterAnnotations[parameterAnnotationsArr.length + 2];
                    System.arraycopy(parameterAnnotationsArr, 0, parameterAnnotationsArr2, 2, parameterAnnotationsArr.length);
                    this.parameterAnnotations = parameterAnnotationsArr2;
                    return;
                }
            } else if (methodSymbol.owner.isDirectlyOrIndirectlyLocal() || z) {
                ParameterAnnotations[] parameterAnnotationsArr3 = this.parameterAnnotations;
                if (size > parameterAnnotationsArr3.length && methodSymbol.type == type) {
                    ParameterAnnotations[] parameterAnnotationsArr4 = new ParameterAnnotations[size];
                    System.arraycopy(parameterAnnotationsArr3, 0, parameterAnnotationsArr4, 1, parameterAnnotationsArr3.length);
                    this.parameterAnnotations = parameterAnnotationsArr4;
                    return;
                }
            }
            dropParameterAnnotations();
            return;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.allParameterAccessFlags;
            if (i2 >= iArr.length) {
                break;
            }
            if ((iArr[i2] & 36864) == 0) {
                i3++;
            }
            i2++;
        }
        int size2 = type.mo71getParameterTypes().size();
        ParameterAnnotations[] parameterAnnotationsArr5 = this.parameterAnnotations;
        if (i3 == parameterAnnotationsArr5.length && this.allParameterAccessFlags.length == size2) {
            if (methodSymbol.type != type) {
                dropParameterAnnotations();
                return;
            }
            ParameterAnnotations[] parameterAnnotationsArr6 = new ParameterAnnotations[size];
            int i4 = 0;
            while (i < size) {
                if ((this.allParameterAccessFlags[i] & 36864) == 0) {
                    parameterAnnotationsArr6[i] = this.parameterAnnotations[i4];
                    i4++;
                }
                i++;
            }
            this.parameterAnnotations = parameterAnnotationsArr6;
            return;
        }
        if (i3 != size || size2 != parameterAnnotationsArr5.length || this.allParameterAccessFlags.length != size2) {
            dropParameterAnnotations();
            return;
        }
        ParameterAnnotations[] parameterAnnotationsArr7 = new ParameterAnnotations[size];
        int i5 = 0;
        while (true) {
            ParameterAnnotations[] parameterAnnotationsArr8 = this.parameterAnnotations;
            if (i >= parameterAnnotationsArr8.length) {
                this.parameterAnnotations = parameterAnnotationsArr7;
                return;
            }
            if ((this.allParameterAccessFlags[i] & 36864) == 0) {
                parameterAnnotationsArr7[i5] = parameterAnnotationsArr8[i];
                i5++;
            }
            i++;
        }
    }

    public void attachAnnotationDefault(Symbol symbol) {
        Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol;
        Attribute attributeValue = readAttributeValue();
        methodSymbol.defaultValue = attributeValue;
        this.annotate.normal(new AnnotationDefaultCompleter(methodSymbol, attributeValue));
    }

    public void attachAnnotations(Symbol symbol, List<CompoundAnnotationProxy> list) {
        ClassReader classReader;
        Symbol symbol2;
        if (list.isEmpty()) {
            return;
        }
        ListBuffer listBuffer = new ListBuffer();
        for (CompoundAnnotationProxy compoundAnnotationProxy : list) {
            if (compoundAnnotationProxy.type.tsym.flatName() == this.syms.proprietaryType.tsym.flatName()) {
                symbol.flags_field |= Flags.PROPRIETARY;
            } else {
                if (compoundAnnotationProxy.type.tsym.flatName() == this.syms.profileType.tsym.flatName()) {
                    if (this.profile != Profile.DEFAULT) {
                        for (Pair<Name, Attribute> pair : compoundAnnotationProxy.values) {
                            if (pair.fst == this.names.value) {
                                Attribute attribute = pair.snd;
                                if (attribute instanceof Attribute.Constant) {
                                    Attribute.Constant constant = (Attribute.Constant) attribute;
                                    if (constant.type == this.syms.intType && ((Integer) constant.value).intValue() > this.profile.value) {
                                        symbol.flags_field |= 35184372088832L;
                                    }
                                }
                            }
                        }
                    }
                } else if (compoundAnnotationProxy.type.tsym.flatName() == this.syms.previewFeatureInternalType.tsym.flatName()) {
                    symbol.flags_field |= Flags.PREVIEW_API;
                    classReader = this;
                    symbol2 = symbol;
                    classReader.setFlagIfAttributeTrue(compoundAnnotationProxy, symbol2, this.names.reflective, Flags.PREVIEW_REFLECTIVE);
                } else {
                    classReader = this;
                    Symbol symbol3 = symbol;
                    if (compoundAnnotationProxy.type.tsym.flatName() == classReader.syms.valueBasedInternalType.tsym.flatName()) {
                        Assert.check(symbol3.kind == Kinds.Kind.TYP);
                        symbol3.flags_field |= 9007199254740992L;
                    } else if (compoundAnnotationProxy.type.tsym.flatName() == classReader.syms.restrictedInternalType.tsym.flatName()) {
                        Assert.check(symbol3.kind == Kinds.Kind.MTH);
                        symbol3.flags_field |= 4611686018427387904L;
                    } else if (compoundAnnotationProxy.type.tsym.flatName() == classReader.syms.requiresIdentityInternalType.tsym.flatName()) {
                        Assert.check(symbol3.kind == Kinds.Kind.VAR);
                        symbol3.flags_field |= 4611686018427387904L;
                    } else {
                        Symbol.TypeSymbol typeSymbol = compoundAnnotationProxy.type.tsym;
                        Symtab symtab = classReader.syms;
                        if (typeSymbol == symtab.annotationTargetType.tsym) {
                            classReader.target = compoundAnnotationProxy;
                        } else {
                            if (typeSymbol == symtab.repeatableType.tsym) {
                                classReader.repeatable = compoundAnnotationProxy;
                            } else if (typeSymbol == symtab.deprecatedType.tsym) {
                                symbol3.flags_field |= 18014398509613056L;
                                symbol2 = symbol3;
                                classReader.setFlagIfAttributeTrue(compoundAnnotationProxy, symbol2, classReader.names.forRemoval, Flags.DEPRECATED_REMOVAL);
                            } else if (typeSymbol == symtab.previewFeatureType.tsym) {
                                symbol3.flags_field = Flags.PREVIEW_API | symbol3.flags_field;
                                symbol2 = symbol3;
                                classReader.setFlagIfAttributeTrue(compoundAnnotationProxy, symbol2, classReader.names.reflective, Flags.PREVIEW_REFLECTIVE);
                            } else {
                                symbol2 = symbol3;
                                if (typeSymbol == symtab.valueBasedType.tsym && symbol2.kind == Kinds.Kind.TYP) {
                                    symbol2.flags_field |= 9007199254740992L;
                                } else if (typeSymbol == symtab.restrictedType.tsym) {
                                    Assert.check(symbol2.kind == Kinds.Kind.MTH);
                                    symbol2.flags_field |= 4611686018427387904L;
                                } else if (typeSymbol == symtab.requiresIdentityType.tsym) {
                                    Assert.check(symbol2.kind == Kinds.Kind.VAR);
                                    symbol2.flags_field |= 4611686018427387904L;
                                }
                            }
                            listBuffer.append(compoundAnnotationProxy);
                        }
                        symbol2 = symbol3;
                        listBuffer.append(compoundAnnotationProxy);
                    }
                    symbol2 = symbol3;
                }
                this = classReader;
                symbol = symbol2;
            }
            classReader = this;
            symbol2 = symbol;
            this = classReader;
            symbol = symbol2;
        }
        ClassReader classReader2 = this;
        classReader2.annotate.normal(classReader2.new AnnotationCompleter(symbol, listBuffer.toList()));
    }

    public void attachTypeAnnotations(Symbol symbol) {
        char cNextChar = nextChar();
        if (cNextChar != 0) {
            ListBuffer listBuffer = new ListBuffer();
            for (int i = 0; i < cNextChar; i++) {
                listBuffer.append(readTypeAnnotation());
            }
            this.annotate.normal(new TypeAnnotationCompleter(symbol, listBuffer.toList()));
        }
    }

    public ClassFinder.BadClassFile badClassFile(JCDiagnostic jCDiagnostic) {
        return new ClassFinder.BadClassFile(this.currentOwner.enclClass(), this.currentClassFile, jCDiagnostic, this.diagFactory, this.dcfh);
    }

    public ClassFinder.BadEnclosingMethodAttr badEnclosingMethod(Symbol symbol) {
        return new ClassFinder.BadEnclosingMethodAttr(this.currentOwner.enclClass(), this.currentClassFile, this.diagFactory.fragment(CompilerProperties.Fragments.BadEnclosingMethod(symbol)), this.diagFactory, this.dcfh);
    }

    public Type classSigToType() {
        ClassReader classReader;
        byte[] bArr = this.signature;
        int i = this.sigp;
        if (bArr[i] != 76) {
            throw badClassFile("bad.class.signature", quoteBadSignature());
        }
        this.sigp = i + 1;
        Type.JCNoType jCNoType = Type.noType;
        int i2 = this.sbp;
        Type classType = jCNoType;
        while (true) {
            byte[] bArr2 = this.signature;
            int i3 = this.sigp;
            this.sigp = i3 + 1;
            byte b = bArr2[i3];
            if (b == 46) {
                classReader = this;
                if (classType != Type.noType) {
                    classType = new Type.ClassType(classType, List.nil(), classReader.enterClass(classReader.readName(classReader.signatureBuffer, i2, classReader.sbp - i2)));
                }
                byte[] bArr3 = classReader.signatureBuffer;
                int i4 = classReader.sbp;
                classReader.sbp = i4 + 1;
                bArr3[i4] = 36;
            } else if (b == 47) {
                classReader = this;
                byte[] bArr4 = classReader.signatureBuffer;
                int i5 = classReader.sbp;
                classReader.sbp = i5 + 1;
                bArr4[i5] = 46;
            } else {
                if (b == 59) {
                    ClassReader classReader2 = this;
                    Symbol.ClassSymbol classSymbolEnterClass = classReader2.enterClass(classReader2.readName(classReader2.signatureBuffer, i2, classReader2.sbp - i2));
                    try {
                        return classType == Type.noType ? classSymbolEnterClass.erasure(classReader2.types) : new Type.ClassType(classType, List.nil(), classSymbolEnterClass);
                    } finally {
                        classReader2.sbp = i2;
                    }
                }
                byte[] bArr5 = this.signatureBuffer;
                if (b != 60) {
                    int i6 = this.sbp;
                    this.sbp = i6 + 1;
                    bArr5[i6] = b;
                    classReader = this;
                } else {
                    final Symbol.ClassSymbol classSymbolEnterClass2 = this.enterClass(this.readName(bArr5, i2, this.sbp - i2));
                    List<Type> listSigToTypes = this.sigToTypes('>');
                    List<Type> list = ((Type.ClassType) classSymbolEnterClass2.type.tsym.type).typarams_field;
                    List<Type> list2 = (list == null || !listSigToTypes.isEmpty()) ? listSigToTypes : list;
                    final List<Type> list3 = list2;
                    classReader = this;
                    Type.ClassType classType2 = new Type.ClassType(classReader, classType, list2, classSymbolEnterClass2) { // from class: com.sun.tools.javac.jvm.ClassReader.1
                        final /* synthetic */ ClassReader this$0;
                        boolean completed = false;
                        boolean typeArgsSet = false;

                        {
                            this.this$0 = classReader;
                        }

                        @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                        public Type getEnclosingType() {
                            if (!this.completed) {
                                this.completed = true;
                                this.tsym.apiComplete();
                                Type enclosingType = this.tsym.type.getEnclosingType();
                                Type.JCNoType jCNoType2 = Type.noType;
                                if (enclosingType != jCNoType2) {
                                    List<Type> listAllparams = super.getEnclosingType().allparams();
                                    List<Type> listAllparams2 = enclosingType.allparams();
                                    int length = listAllparams2.length();
                                    int length2 = listAllparams.length();
                                    ClassReader classReader3 = this.this$0;
                                    if (length != length2) {
                                        super.setEnclosingType(classReader3.types.erasure(enclosingType));
                                    } else {
                                        super.setEnclosingType(classReader3.types.subst(enclosingType, listAllparams2, listAllparams));
                                    }
                                } else {
                                    super.setEnclosingType(jCNoType2);
                                }
                            }
                            return super.getEnclosingType();
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* JADX WARN: Type inference failed for: r2v4, types: [A, com.sun.tools.javac.code.Type] */
                        @Override // com.sun.tools.javac.code.Type.ClassType, javax.lang.model.type.DeclaredType
                        public List<Type> getTypeArguments() {
                            if (!this.typeArgsSet) {
                                this.typeArgsSet = true;
                                List list4 = ((Type.ClassType) classSymbolEnterClass2.type.tsym.type).typarams_field;
                                if (list4 != null && !list4.isEmpty() && list3.length() == list4.length()) {
                                    List list5 = list3;
                                    while (list5.nonEmpty()) {
                                        list5.head = ((Type) list5.head).withTypeVar((Type) list4.head);
                                        list5 = list5.tail;
                                        list4 = list4.tail;
                                    }
                                }
                            }
                            return super.getTypeArguments();
                        }

                        @Override // com.sun.tools.javac.code.Type.ClassType
                        public void setEnclosingType(Type type) {
                            throw new UnsupportedOperationException();
                        }
                    };
                    byte[] bArr6 = classReader.signature;
                    int i7 = classReader.sigp;
                    int i8 = i7 + 1;
                    classReader.sigp = i8;
                    byte b2 = bArr6[i7];
                    if (b2 == 46) {
                        byte[] bArr7 = classReader.signatureBuffer;
                        int i9 = classReader.sbp;
                        classReader.sbp = i9 + 1;
                        bArr7[i9] = 36;
                    } else {
                        if (b2 != 59) {
                            throw new AssertionError((int) bArr6[i7]);
                        }
                        if (i8 >= classReader.siglimit || bArr6[i8] != 46) {
                            classReader.sbp = i2;
                            return classType2;
                        }
                        int i10 = classReader.sbp;
                        classReader.sigp = i8 + (i10 - i2) + 3;
                        byte[] bArr8 = classReader.signatureBuffer;
                        classReader.sbp = i10 + 1;
                        bArr8[i10] = 36;
                    }
                    classType = classType2;
                }
            }
            this = classReader;
        }
    }

    public Symbol.ClassSymbol enterClass(Name name) {
        return this.syms.enterClass(this.currentModule, name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void enterTypevars(Symbol symbol, Type type) {
        if (type.getEnclosingType() != null) {
            if (!type.getEnclosingType().hasTag(TypeTag.NONE)) {
                enterTypevars(symbol.owner, type.getEnclosingType());
            }
        } else if (symbol.kind == Kinds.Kind.MTH && !symbol.isStatic()) {
            Symbol symbol2 = symbol.owner;
            enterTypevars(symbol2, symbol2.type);
        }
        for (List typeArguments = type.getTypeArguments(); typeArguments.nonEmpty(); typeArguments = typeArguments.tail) {
            this.typevars.enter(((Type) typeArguments.head).tsym);
        }
    }

    public Type findTypeVar(Name name) {
        Symbol symbolFindFirst = this.typevars.findFirst(name);
        if (symbolFindFirst != null) {
            return symbolFindFirst.type;
        }
        if (!this.readingClassAttr) {
            throw badClassFile("undecl.type.var", name);
        }
        Type.TypeVar typeVar = new Type.TypeVar(name, this.currentOwner, this.syms.botType);
        this.missingTypeVariables = this.missingTypeVariables.prepend(typeVar);
        return typeVar;
    }

    public void initParameterNames(Symbol.MethodSymbol methodSymbol) {
        int iWidth = Code.width(methodSymbol.type.mo71getParameterTypes()) + 4;
        int[] iArr = this.parameterNameIndicesLvt;
        if (iArr == null || iArr.length < iWidth) {
            this.parameterNameIndicesLvt = new int[iWidth];
        } else {
            Arrays.fill(iArr, 0);
        }
    }

    public int nextByte() {
        try {
            ByteBuffer byteBuffer = this.buf;
            int i = this.bp;
            this.bp = i + 1;
            return byteBuffer.getByte(i) & 255;
        } catch (ByteBuffer.UnderflowException e) {
            throw badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    public char nextChar() {
        try {
            char c = this.buf.getChar(this.bp);
            this.bp += 2;
            return c;
        } catch (ByteBuffer.UnderflowException e) {
            throw badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    public int nextInt() {
        try {
            int i = this.buf.getInt(this.bp);
            this.bp += 4;
            return i;
        } catch (ByteBuffer.UnderflowException e) {
            throw badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    public <Z> Z optPoolEntry(int i, IntFunction<Z> intFunction, Z z) {
        return i == 0 ? z : intFunction.apply(i);
    }

    public String quoteBadSignature() {
        try {
            byte[] bArr = this.signature;
            int i = this.sigp;
            String strUtf2string = Convert.utf2string(bArr, i, this.siglimit - i, Convert.Validation.NONE);
            if (strUtf2string.length() > 32) {
                strUtf2string = strUtf2string.substring(0, 32).concat("...");
            }
            return "\"" + strUtf2string + "\"";
        } catch (InvalidUtfException e) {
            x01.a(e);
            return null;
        }
    }

    public List<CompoundAnnotationProxy> readAnnotations() {
        char cNextChar = nextChar();
        ListBuffer listBuffer = new ListBuffer();
        for (int i = 0; i < cNextChar; i++) {
            listBuffer.append(readCompoundAnnotation());
        }
        return listBuffer.toList();
    }

    public Attribute readAttributeValue() {
        try {
            ByteBuffer byteBuffer = this.buf;
            int i = this.bp;
            this.bp = i + 1;
            char c = (char) byteBuffer.getByte(i);
            if (c == '@') {
                return readCompoundAnnotation();
            }
            if (c == 'F') {
                return new Attribute.Constant(this.syms.floatType, this.poolReader.getConstant(nextChar()));
            }
            if (c == 'S') {
                return new Attribute.Constant(this.syms.shortType, this.poolReader.getConstant(nextChar()));
            }
            if (c == 'c') {
                return new ClassAttributeProxy(readTypeOrClassSymbol(nextChar()));
            }
            if (c == 'e') {
                return new EnumAttributeProxy(readTypeToProxy(nextChar()), this.poolReader.getName(nextChar()));
            }
            if (c == 's') {
                return new Attribute.Constant(this.syms.stringType, this.poolReader.getName(nextChar()).toString());
            }
            if (c == 'I') {
                return new Attribute.Constant(this.syms.intType, this.poolReader.getConstant(nextChar()));
            }
            if (c == 'J') {
                return new Attribute.Constant(this.syms.longType, this.poolReader.getConstant(nextChar()));
            }
            if (c == 'Z') {
                return new Attribute.Constant(this.syms.booleanType, this.poolReader.getConstant(nextChar()));
            }
            if (c == '[') {
                char cNextChar = nextChar();
                ListBuffer listBuffer = new ListBuffer();
                for (int i2 = 0; i2 < cNextChar; i2++) {
                    listBuffer.append(readAttributeValue());
                }
                return new ArrayAttributeProxy(listBuffer.toList());
            }
            switch (c) {
                case 'B':
                    return new Attribute.Constant(this.syms.byteType, this.poolReader.getConstant(nextChar()));
                case 'C':
                    return new Attribute.Constant(this.syms.charType, this.poolReader.getConstant(nextChar()));
                case 'D':
                    return new Attribute.Constant(this.syms.doubleType, this.poolReader.getConstant(nextChar()));
                default:
                    throw new AssertionError("unknown annotation tag '" + c + "'");
            }
        } catch (ByteBuffer.UnderflowException e) {
            throw badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    public void readAttrs(Symbol symbol, AttributeKind attributeKind) {
        char cNextChar = nextChar();
        for (int i = 0; i < cNextChar; i++) {
            Name name = this.poolReader.getName(nextChar());
            int iNextInt = nextInt();
            AttributeReader attributeReader = this.attributeReaders.get(name);
            if (attributeReader == null || !attributeReader.accepts(attributeKind)) {
                this.bp += iNextInt;
            } else {
                attributeReader.read(symbol, iNextInt);
            }
        }
    }

    public void readClass(Symbol.ClassSymbol classSymbol) {
        Type.ClassType classType = (Type.ClassType) classSymbol.type;
        classSymbol.members_field = Scope.WriteableScope.create(classSymbol);
        this.typevars = this.typevars.dup(this.currentOwner);
        if (classType.getEnclosingType().hasTag(TypeTag.CLASS)) {
            enterTypevars(classSymbol.owner, classType.getEnclosingType());
        }
        long jAdjustClassFlags = adjustClassFlags(nextChar());
        long j = 2251799813685248L & jAdjustClassFlags;
        if (j == 0) {
            Kinds.Kind kind = classSymbol.owner.kind;
            if (kind == Kinds.Kind.PCK || kind == Kinds.Kind.ERR) {
                classSymbol.flags_field = jAdjustClassFlags;
            }
            this.currentModule = classSymbol.packge().modle;
            Symbol.ClassSymbol classSymbol2 = this.poolReader.getClass(nextChar());
            if (classSymbol != classSymbol2) {
                throw badClassFile("class.file.wrong.class", classSymbol2.flatname);
            }
        } else {
            int i = this.majorVersion;
            if (i < ClassFile.Version.V53.major) {
                throw badClassFile("anachronistic.module.info", Integer.toString(i), Integer.toString(this.minorVersion));
            }
            classSymbol.flags_field = jAdjustClassFlags;
            Symbol symbol = classSymbol.owner;
            if (symbol.kind != Kinds.Kind.MDL) {
                throw badClassFile("module.info.definition.expected", new Object[0]);
            }
            this.currentModule = (Symbol.ModuleSymbol) symbol;
            nextChar();
        }
        int i2 = this.bp;
        nextChar();
        this.bp += nextChar() * 2;
        char cNextChar = nextChar();
        for (int i3 = 0; i3 < cNextChar; i3++) {
            skipMember();
        }
        char cNextChar2 = nextChar();
        for (int i4 = 0; i4 < cNextChar2; i4++) {
            skipMember();
        }
        readClassAttrs(classSymbol);
        if (!classSymbol.getPermittedSubclasses().isEmpty()) {
            classSymbol.flags_field |= Flags.SEALED;
        }
        this.bp = i2;
        char cNextChar3 = nextChar();
        if (j != 0 && cNextChar3 > 0) {
            throw badClassFile("module.info.invalid.super.class", new Object[0]);
        }
        if (classType.supertype_field == null) {
            classType.supertype_field = (Type) optPoolEntry(cNextChar3, new IntFunction() { // from class: sw1
                @Override // java.util.function.IntFunction
                public final Object apply(int i5) {
                    ClassReader classReader = this.b;
                    return classReader.poolReader.getClass(i5).erasure(classReader.types);
                }
            }, Type.noType);
        }
        char cNextChar4 = nextChar();
        List listNil = List.nil();
        for (int i5 = 0; i5 < cNextChar4; i5++) {
            listNil = listNil.prepend(this.poolReader.getClass(nextChar()).erasure(this.types));
        }
        if (classType.interfaces_field == null) {
            classType.interfaces_field = listNil.reverse();
        }
        Assert.check(cNextChar == nextChar());
        for (int i6 = 0; i6 < cNextChar; i6++) {
            enterMember(classSymbol, readField());
        }
        Assert.check(cNextChar2 == nextChar());
        for (int i7 = 0; i7 < cNextChar2; i7++) {
            enterMember(classSymbol, readMethod());
        }
        if (classSymbol.isRecord()) {
            for (Symbol.RecordComponent recordComponent : classSymbol.getRecordComponents()) {
                recordComponent.accessor = lookupMethod(classSymbol, recordComponent.name, List.nil());
            }
        }
        this.typevars = this.typevars.leave();
    }

    public void readClassAttrs(Symbol.ClassSymbol classSymbol) {
        readAttrs(classSymbol, AttributeKind.CLASS);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void readClassFile(Symbol.ClassSymbol classSymbol) {
        this.currentOwner = classSymbol;
        this.currentClassFile = classSymbol.classfile;
        this.warnedAttrs.clear();
        this.filling = true;
        this.target = null;
        this.repeatable = null;
        try {
            try {
                try {
                    this.bp = 0;
                    this.buf.reset();
                    InputStream inputStreamOpenInputStream = classSymbol.classfile.openInputStream();
                    try {
                        this.buf.appendStream(inputStreamOpenInputStream);
                        if (inputStreamOpenInputStream != null) {
                            inputStreamOpenInputStream.close();
                        }
                        readClassBuffer(classSymbol);
                        if (!this.missingTypeVariables.isEmpty() && !this.foundTypeVariables.isEmpty()) {
                            List<Type> list = this.missingTypeVariables;
                            List<Type> list2 = this.foundTypeVariables;
                            this.missingTypeVariables = List.nil();
                            this.foundTypeVariables = List.nil();
                            this.interimUses = List.nil();
                            this.interimProvides = List.nil();
                            this.filling = false;
                            Type.ClassType classType = (Type.ClassType) this.currentOwner.type;
                            classType.supertype_field = this.types.subst(classType.supertype_field, list, list2);
                            classType.interfaces_field = this.types.subst(classType.interfaces_field, list, list2);
                            List listSubstBounds = this.types.substBounds(classType.typarams_field, list, list2);
                            classType.typarams_field = listSubstBounds;
                            while (listSubstBounds.nonEmpty()) {
                                A a = listSubstBounds.head;
                                ((Type) a).tsym.type = (Type) a;
                                listSubstBounds = listSubstBounds.tail;
                            }
                        } else if (this.missingTypeVariables.isEmpty() != this.foundTypeVariables.isEmpty()) {
                            throw badClassFile("undecl.type.var", this.missingTypeVariables.head.tsym.name);
                        }
                        if ((classSymbol.flags_field & 8192) != 0) {
                            classSymbol.setAnnotationTypeMetadata(new Annotate.AnnotationTypeMetadata(classSymbol, new CompleterDeproxy(classSymbol, this.target, this.repeatable)));
                        } else {
                            classSymbol.setAnnotationTypeMetadata(Annotate.AnnotationTypeMetadata.notAnAnnotationType());
                        }
                        if (classSymbol == this.currentModule.module_info) {
                            if (this.interimUses.nonEmpty() || this.interimProvides.nonEmpty()) {
                                Assert.check(this.currentModule.isCompleted());
                                this.currentModule.usesProvidesCompleter = new UsesProvidesCompleter(this.currentModule, this.interimUses, this.interimProvides);
                            } else {
                                this.currentModule.uses = List.nil();
                                this.currentModule.provides = List.nil();
                            }
                        }
                        this.interimUses = List.nil();
                        this.interimProvides = List.nil();
                        this.missingTypeVariables = List.nil();
                        this.foundTypeVariables = List.nil();
                        this.filling = false;
                    } catch (Throwable th) {
                        if (inputStreamOpenInputStream != null) {
                            try {
                                inputStreamOpenInputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw badClassFile("bad.class.file", classSymbol.flatname);
                }
            } catch (IOException | ClosedFileSystemException e) {
                throw badClassFile("unable.to.access.file", e.toString());
            }
        } catch (Throwable th3) {
            this.interimUses = List.nil();
            this.interimProvides = List.nil();
            this.missingTypeVariables = List.nil();
            this.foundTypeVariables = List.nil();
            this.filling = false;
            throw th3;
        }
    }

    public Code readCode(Symbol symbol) {
        nextChar();
        nextChar();
        this.bp += nextInt();
        this.bp += nextChar() * '\b';
        readMemberAttrs(symbol);
        return null;
    }

    public CompoundAnnotationProxy readCompoundAnnotation() {
        Type proxyType = this.currentModule.module_info == this.currentOwner ? new ProxyType(nextChar()) : readTypeOrClassSymbol(nextChar());
        char cNextChar = nextChar();
        ListBuffer listBuffer = new ListBuffer();
        for (int i = 0; i < cNextChar; i++) {
            listBuffer.append(new Pair(this.poolReader.getName(nextChar()), readAttributeValue()));
        }
        return new CompoundAnnotationProxy(proxyType, listBuffer.toList());
    }

    public void readEnclosingMethodAttr(Symbol symbol) {
        symbol.owner.members().remove(symbol);
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
        Symbol.ClassSymbol classSymbol2 = this.poolReader.getClass(nextChar());
        char cNextChar = nextChar();
        final PoolReader poolReader = this.poolReader;
        Objects.requireNonNull(poolReader);
        PoolConstant.NameAndType nameAndType = (PoolConstant.NameAndType) optPoolEntry(cNextChar, new IntFunction() { // from class: qw1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return poolReader.getNameAndType(i);
            }
        }, null);
        Scope scope = classSymbol2.members_field;
        if (scope == null || classSymbol2.kind != Kinds.Kind.TYP) {
            throw badClassFile("bad.enclosing.class", classSymbol, classSymbol2);
        }
        Symbol.MethodSymbol methodSymbolFindMethod = findMethod(nameAndType, scope, classSymbol.flags());
        if (nameAndType != null && methodSymbolFindMethod == null) {
            throw badEnclosingMethod(classSymbol);
        }
        Name nameSimpleBinaryName = simpleBinaryName(classSymbol.flatname, classSymbol2.flatname);
        classSymbol.name = nameSimpleBinaryName;
        classSymbol.owner = methodSymbolFindMethod != null ? methodSymbolFindMethod : classSymbol2;
        if (nameSimpleBinaryName.length() == 0) {
            classSymbol.fullname = this.names.empty;
        } else {
            classSymbol.fullname = Symbol.TypeSymbol.formFullName(classSymbol.name, classSymbol.owner);
        }
        if (methodSymbolFindMethod != null) {
            ((Type.ClassType) symbol.type).setEnclosingType(methodSymbolFindMethod.type);
        } else {
            long j = classSymbol.flags_field & 8;
            Type type = symbol.type;
            if (j == 0) {
                ((Type.ClassType) type).setEnclosingType(classSymbol2.type);
            } else {
                ((Type.ClassType) type).setEnclosingType(Type.noType);
            }
        }
        enterTypevars(classSymbol, classSymbol.type);
        if (this.missingTypeVariables.isEmpty()) {
            this.foundTypeVariables = List.nil();
            return;
        }
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = this.missingTypeVariables.iterator();
        while (it.hasNext()) {
            listBuffer.append(findTypeVar(it.next().tsym.name));
        }
        this.foundTypeVariables = listBuffer.toList();
    }

    public Set<Directive.ExportsFlag> readExportsFlags(int i) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(Directive.ExportsFlag.class);
        for (Directive.ExportsFlag exportsFlag : Directive.ExportsFlag.values()) {
            if ((exportsFlag.value & i) != 0) {
                enumSetNoneOf.add(exportsFlag);
            }
        }
        return enumSetNoneOf;
    }

    public Symbol.VarSymbol readField() {
        char cNextChar = nextChar();
        long j = cNextChar;
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(adjustFieldFlags(j), this.poolReader.getName(nextChar()), this.poolReader.getType(nextChar()), this.currentOwner);
        readMemberAttrs(varSymbol);
        if (Integer.bitCount(cNextChar & 7) > 1 || Integer.bitCount(cNextChar & 'P') > 1) {
            throw badClassFile("illegal.flag.combo", Flags.toString(j), "field", varSymbol);
        }
        return varSymbol;
    }

    public void readInnerClasses(Symbol.ClassSymbol classSymbol) {
        char cNextChar = nextChar();
        for (int i = 0; i < cNextChar; i++) {
            nextChar();
            char cNextChar2 = nextChar();
            char cNextChar3 = nextChar();
            final PoolReader poolReader = this.poolReader;
            Objects.requireNonNull(poolReader);
            Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) optPoolEntry(cNextChar2, new IntFunction() { // from class: uw1
                @Override // java.util.function.IntFunction
                public final Object apply(int i2) {
                    return poolReader.getClass(i2);
                }
            }, null);
            PoolReader poolReader2 = this.poolReader;
            Objects.requireNonNull(poolReader2);
            Name name = (Name) optPoolEntry(cNextChar3, new tw1(poolReader2), this.names.empty);
            if (name == null) {
                name = this.names.empty;
            }
            long jAdjustClassFlags = adjustClassFlags(nextChar());
            if (classSymbol2 != null) {
                Names names = this.names;
                if (name == names.empty) {
                    name = names.one;
                }
                Symbol.ClassSymbol classSymbolEnterClass = enterClass(name, classSymbol2);
                long j = classSymbolEnterClass.flags_field;
                if ((2097152 & j) == 0) {
                    if ((jAdjustClassFlags & 8) == 0) {
                        ((Type.ClassType) classSymbolEnterClass.type).setEnclosingType(classSymbol2.type);
                        Type type = classSymbolEnterClass.erasure_field;
                        if (type != null) {
                            ((Type.ClassType) type).setEnclosingType(this.types.erasure(classSymbol2.type));
                        }
                    }
                    if (classSymbol == classSymbol2 && classSymbolEnterClass.owner == classSymbol) {
                        classSymbolEnterClass.flags_field = jAdjustClassFlags;
                        enterMember(classSymbol, classSymbolEnterClass);
                    }
                } else if ((jAdjustClassFlags & 8) != (j & 8)) {
                    this.log.warning(CompilerProperties.LintWarnings.InconsistentInnerClasses(classSymbolEnterClass, this.currentClassFile));
                }
            }
        }
    }

    public void readMemberAttrs(Symbol symbol) {
        readAttrs(symbol, AttributeKind.MEMBER);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:38:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:43:0x0112  */
    /* JADX WARN: Code duplicated, block: B:53:0x0147  */
    public Symbol.MethodSymbol readMethod() {
        boolean z;
        Type methodType;
        Symbol.MethodSymbol methodSymbol;
        Symbol symbol;
        Type typeLast;
        char cNextChar = nextChar();
        long j = cNextChar;
        long jAdjustMethodFlags = adjustMethodFlags(j);
        Name name = this.poolReader.getName(nextChar());
        Type type = this.poolReader.getType(nextChar());
        if (this.currentOwner.isInterface() && (1024 & jAdjustMethodFlags) == 0 && !name.equals(this.names.clinit)) {
            int i = this.majorVersion;
            ClassFile.Version version = ClassFile.Version.V52;
            int i2 = version.major;
            if (i <= i2 && (i != i2 || this.minorVersion < version.minor)) {
                throw badClassFile((8 & jAdjustMethodFlags) == 0 ? "invalid.default.interface" : "invalid.static.interface", Integer.toString(i), Integer.toString(this.minorVersion));
            }
            if ((10 & jAdjustMethodFlags) == 0) {
                this.currentOwner.flags_field |= Flags.DEFAULT;
                jAdjustMethodFlags |= 8796093023232L;
            }
        }
        long j2 = jAdjustMethodFlags;
        validateMethodType(name, type);
        try {
            if (name == this.names.init && this.currentOwner.hasOuterInstance()) {
                boolean zIncludes = this.currentOwner.owner.members().includes(this.currentOwner, Scope.LookupKind.NON_RECURSIVE);
                z = !zIncludes;
                if (this.currentOwner.name.length() != 0 && zIncludes) {
                    methodType = new Type.MethodType(adjustMethodParams(j2, type.mo71getParameterTypes()), type.mo73getReturnType(), type.mo74getThrownTypes(), this.syms.methodClass);
                }
                methodSymbol = new Symbol.MethodSymbol(j2, name, methodType, this.currentOwner);
                if (this.types.isSignaturePolymorphic(methodSymbol)) {
                    methodSymbol.flags_field |= Flags.SIGNATURE_POLYMORPHIC;
                }
                if (this.saveParameterNames) {
                    initParameterNames(methodSymbol);
                }
                symbol = this.currentOwner;
                this.currentOwner = methodSymbol;
                readMemberAttrs(methodSymbol);
                this.currentOwner = symbol;
                validateMethodType(name, methodSymbol.type);
                adjustParameterAnnotations(methodSymbol, type, z);
                setParameters(methodSymbol, methodType);
                if (Integer.bitCount(cNextChar & 7) <= 1) {
                    throw badClassFile("illegal.flag.combo", Flags.toString(j), Constants.ATTRNAME_OUTPUT_METHOD, methodSymbol);
                }
                if ((Flags.VARARGS & j2) != 0 || ((typeLast = methodType.mo71getParameterTypes().last()) != null && typeLast.hasTag(TypeTag.ARRAY))) {
                    return methodSymbol;
                }
                methodSymbol.flags_field &= -17179869185L;
                throw badClassFile("malformed.vararg.method", methodSymbol);
            }
            z = false;
            readMemberAttrs(methodSymbol);
            this.currentOwner = symbol;
            validateMethodType(name, methodSymbol.type);
            adjustParameterAnnotations(methodSymbol, type, z);
            setParameters(methodSymbol, methodType);
            if (Integer.bitCount(cNextChar & 7) <= 1) {
                throw badClassFile("illegal.flag.combo", Flags.toString(j), Constants.ATTRNAME_OUTPUT_METHOD, methodSymbol);
            }
            if ((Flags.VARARGS & j2) != 0) {
            }
            return methodSymbol;
        } catch (Throwable th) {
            this.currentOwner = symbol;
            throw th;
        }
        methodType = type;
        methodSymbol = new Symbol.MethodSymbol(j2, name, methodType, this.currentOwner);
        if (this.types.isSignaturePolymorphic(methodSymbol)) {
            methodSymbol.flags_field |= Flags.SIGNATURE_POLYMORPHIC;
        }
        if (this.saveParameterNames) {
            initParameterNames(methodSymbol);
        }
        symbol = this.currentOwner;
        this.currentOwner = methodSymbol;
    }

    public Set<Symbol.ModuleFlags> readModuleFlags(int i) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(Symbol.ModuleFlags.class);
        for (Symbol.ModuleFlags moduleFlags : Symbol.ModuleFlags.values()) {
            if ((moduleFlags.value & i) != 0) {
                enumSetNoneOf.add(moduleFlags);
            }
        }
        return enumSetNoneOf;
    }

    public Set<Symbol.ModuleResolutionFlags> readModuleResolutionFlags(int i) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(Symbol.ModuleResolutionFlags.class);
        for (Symbol.ModuleResolutionFlags moduleResolutionFlags : Symbol.ModuleResolutionFlags.values()) {
            if ((moduleResolutionFlags.value & i) != 0) {
                enumSetNoneOf.add(moduleResolutionFlags);
            }
        }
        return enumSetNoneOf;
    }

    public Set<Directive.OpensFlag> readOpensFlags(int i) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(Directive.OpensFlag.class);
        for (Directive.OpensFlag opensFlag : Directive.OpensFlag.values()) {
            if ((opensFlag.value & i) != 0) {
                enumSetNoneOf.add(opensFlag);
            }
        }
        return enumSetNoneOf;
    }

    public void readParameterAnnotations(Symbol symbol) {
        try {
            ByteBuffer byteBuffer = this.buf;
            int i = this.bp;
            this.bp = i + 1;
            int i2 = byteBuffer.getByte(i) & 255;
            ParameterAnnotations[] parameterAnnotationsArr = this.parameterAnnotations;
            int i3 = 0;
            if (parameterAnnotationsArr == null) {
                this.parameterAnnotations = new ParameterAnnotations[i2];
            } else if (parameterAnnotationsArr.length != i2) {
                this.log.warning(CompilerProperties.LintWarnings.RuntimeVisibleInvisibleParamAnnotationsMismatch(this.currentClassFile));
                while (i3 < i2) {
                    readAnnotations();
                    i3++;
                }
                this.parameterAnnotations = null;
                return;
            }
            while (i3 < i2) {
                ParameterAnnotations[] parameterAnnotationsArr2 = this.parameterAnnotations;
                if (parameterAnnotationsArr2[i3] == null) {
                    parameterAnnotationsArr2[i3] = new ParameterAnnotations();
                }
                this.parameterAnnotations[i3].add(readAnnotations());
                i3++;
            }
        } catch (ByteBuffer.UnderflowException e) {
            throw badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    public TypeAnnotationPosition readPosition() {
        int iNextByte = nextByte();
        if (!TargetType.isValidTargetTypeValue(iNextByte)) {
            throw badClassFile("bad.type.annotation.value", String.format("0x%02X", Integer.valueOf(iNextByte)));
        }
        TargetType targetTypeFromTargetTypeValue = TargetType.fromTargetTypeValue(iNextByte);
        int i = 0;
        switch (AnonymousClass28.$SwitchMap$com$sun$tools$javac$code$TargetType[targetTypeFromTargetTypeValue.ordinal()]) {
            case 1:
                char cNextChar = nextChar();
                TypeAnnotationPosition typeAnnotationPositionInstanceOf = TypeAnnotationPosition.instanceOf(readTypePath());
                typeAnnotationPositionInstanceOf.offset = cNextChar;
                return typeAnnotationPositionInstanceOf;
            case 2:
                char cNextChar2 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionNewObj = TypeAnnotationPosition.newObj(readTypePath());
                typeAnnotationPositionNewObj.offset = cNextChar2;
                return typeAnnotationPositionNewObj;
            case 3:
                char cNextChar3 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionConstructorRef = TypeAnnotationPosition.constructorRef(readTypePath());
                typeAnnotationPositionConstructorRef.offset = cNextChar3;
                return typeAnnotationPositionConstructorRef;
            case 4:
                char cNextChar4 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionMethodRef = TypeAnnotationPosition.methodRef(readTypePath());
                typeAnnotationPositionMethodRef.offset = cNextChar4;
                return typeAnnotationPositionMethodRef;
            case 5:
                int iNextChar = nextChar();
                int[] iArr = new int[iNextChar];
                int[] iArr2 = new int[iNextChar];
                int[] iArr3 = new int[iNextChar];
                while (i < iNextChar) {
                    iArr[i] = nextChar();
                    iArr2[i] = nextChar();
                    iArr3[i] = nextChar();
                    i++;
                }
                TypeAnnotationPosition typeAnnotationPositionLocalVariable = TypeAnnotationPosition.localVariable(readTypePath());
                typeAnnotationPositionLocalVariable.lvarOffset = iArr;
                typeAnnotationPositionLocalVariable.lvarLength = iArr2;
                typeAnnotationPositionLocalVariable.lvarIndex = iArr3;
                return typeAnnotationPositionLocalVariable;
            case 6:
                int iNextChar2 = nextChar();
                int[] iArr4 = new int[iNextChar2];
                int[] iArr5 = new int[iNextChar2];
                int[] iArr6 = new int[iNextChar2];
                while (i < iNextChar2) {
                    iArr4[i] = nextChar();
                    iArr5[i] = nextChar();
                    iArr6[i] = nextChar();
                    i++;
                }
                TypeAnnotationPosition typeAnnotationPositionResourceVariable = TypeAnnotationPosition.resourceVariable(readTypePath());
                typeAnnotationPositionResourceVariable.lvarOffset = iArr4;
                typeAnnotationPositionResourceVariable.lvarLength = iArr5;
                typeAnnotationPositionResourceVariable.lvarIndex = iArr6;
                return typeAnnotationPositionResourceVariable;
            case 7:
                char cNextChar5 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionExceptionParameter = TypeAnnotationPosition.exceptionParameter(readTypePath());
                typeAnnotationPositionExceptionParameter.setExceptionIndex(cNextChar5);
                return typeAnnotationPositionExceptionParameter;
            case 8:
                return TypeAnnotationPosition.methodReceiver(readTypePath());
            case 9:
                return TypeAnnotationPosition.typeParameter(readTypePath(), nextByte());
            case 10:
                return TypeAnnotationPosition.methodTypeParameter(readTypePath(), nextByte());
            case 11:
                return TypeAnnotationPosition.typeParameterBound(readTypePath(), nextByte(), nextByte());
            case 12:
                return TypeAnnotationPosition.methodTypeParameterBound(readTypePath(), nextByte(), nextByte());
            case 13:
                return TypeAnnotationPosition.classExtends(readTypePath(), nextChar());
            case 14:
                return TypeAnnotationPosition.methodThrows(readTypePath(), nextChar());
            case 15:
                return TypeAnnotationPosition.methodParameter(readTypePath(), nextByte());
            case 16:
                char cNextChar6 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionTypeCast = TypeAnnotationPosition.typeCast(readTypePath(), nextByte());
                typeAnnotationPositionTypeCast.offset = cNextChar6;
                return typeAnnotationPositionTypeCast;
            case 17:
                char cNextChar7 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionConstructorInvocationTypeArg = TypeAnnotationPosition.constructorInvocationTypeArg(readTypePath(), nextByte());
                typeAnnotationPositionConstructorInvocationTypeArg.offset = cNextChar7;
                return typeAnnotationPositionConstructorInvocationTypeArg;
            case 18:
                char cNextChar8 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionMethodInvocationTypeArg = TypeAnnotationPosition.methodInvocationTypeArg(readTypePath(), nextByte());
                typeAnnotationPositionMethodInvocationTypeArg.offset = cNextChar8;
                return typeAnnotationPositionMethodInvocationTypeArg;
            case 19:
                char cNextChar9 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionConstructorRefTypeArg = TypeAnnotationPosition.constructorRefTypeArg(readTypePath(), nextByte());
                typeAnnotationPositionConstructorRefTypeArg.offset = cNextChar9;
                return typeAnnotationPositionConstructorRefTypeArg;
            case 20:
                char cNextChar10 = nextChar();
                TypeAnnotationPosition typeAnnotationPositionMethodRefTypeArg = TypeAnnotationPosition.methodRefTypeArg(readTypePath(), nextByte());
                typeAnnotationPositionMethodRefTypeArg.offset = cNextChar10;
                return typeAnnotationPositionMethodRefTypeArg;
            case 21:
                return TypeAnnotationPosition.methodReturn(readTypePath());
            case 22:
                return TypeAnnotationPosition.field(readTypePath());
            case 23:
                x01.a("jvm.ClassReader: UNKNOWN target type should never occur!");
                return null;
            default:
                s22.a("jvm.ClassReader: Unknown target type for position: ", targetTypeFromTargetTypeValue);
                return null;
        }
    }

    public Set<Directive.RequiresFlag> readRequiresFlags(int i) {
        EnumSet enumSetNoneOf = EnumSet.noneOf(Directive.RequiresFlag.class);
        for (Directive.RequiresFlag requiresFlag : Directive.RequiresFlag.values()) {
            if ((requiresFlag.value & i) != 0) {
                enumSetNoneOf.add(requiresFlag);
            }
        }
        return enumSetNoneOf;
    }

    public TypeAnnotationProxy readTypeAnnotation() {
        return new TypeAnnotationProxy(readCompoundAnnotation(), readPosition());
    }

    public Type readTypeOrClassSymbol(int i) {
        return readTypeToProxy(i);
    }

    public List<TypeAnnotationPosition.TypePathEntry> readTypePath() {
        int iNextByte = nextByte();
        ListBuffer listBuffer = new ListBuffer();
        for (int i = 0; i < iNextByte * 2; i++) {
            listBuffer = listBuffer.append(Integer.valueOf(nextByte()));
        }
        return TypeAnnotationPosition.getTypePathFromBinary(listBuffer.toList());
    }

    public Type readTypeToProxy(int i) {
        return this.currentModule.module_info == this.currentOwner ? new ProxyType(i) : this.poolReader.getType(i);
    }

    public void setParameters(Symbol.MethodSymbol methodSymbol, Type type) {
        ParameterAnnotations parameterAnnotations;
        List<CompoundAnnotationProxy> list;
        int iWidth = (methodSymbol.flags() & 8) == 0 ? 1 : 0;
        if (methodSymbol.name == this.names.init && this.currentOwner.hasOuterInstance() && this.currentOwner.name.length() != 0) {
            iWidth++;
        }
        if (methodSymbol.type != type) {
            iWidth += Code.width(type.mo71getParameterTypes()) - Code.width(methodSymbol.type.mo71getParameterTypes());
        }
        HashSet hashSet = new HashSet();
        ListBuffer listBuffer = new ListBuffer();
        int iWidth2 = iWidth;
        int i = 0;
        int i2 = 0;
        for (Type type2 : methodSymbol.type.mo71getParameterTypes()) {
            ClassReader classReader = this;
            Symbol.MethodSymbol methodSymbol2 = methodSymbol;
            Symbol.VarSymbol varSymbolParameter = classReader.parameter(i2, iWidth2, type2, methodSymbol2, hashSet);
            listBuffer.append(varSymbolParameter);
            ParameterAnnotations[] parameterAnnotationsArr = classReader.parameterAnnotations;
            if (parameterAnnotationsArr != null && (parameterAnnotations = parameterAnnotationsArr[i]) != null && (list = parameterAnnotations.proxies) != null) {
                classReader.attachAnnotations(varSymbolParameter, list);
            }
            iWidth2 += Code.width(type2);
            i2++;
            i++;
            this = classReader;
            methodSymbol = methodSymbol2;
        }
        ClassReader classReader2 = this;
        Symbol.MethodSymbol methodSymbol3 = methodSymbol;
        ParameterAnnotations[] parameterAnnotationsArr2 = classReader2.parameterAnnotations;
        Assert.check(parameterAnnotationsArr2 == null || parameterAnnotationsArr2.length == i);
        Assert.checkNull(methodSymbol3.params);
        methodSymbol3.params = listBuffer.toList();
        classReader2.parameterAnnotations = null;
        classReader2.parameterNameIndicesLvt = null;
        classReader2.parameterNameIndicesMp = null;
        classReader2.allParameterAccessFlags = null;
        classReader2.parameterAccessFlags = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type sigToType() {
        byte[] bArr;
        int i;
        byte[] bArr2 = this.signature;
        int i2 = this.sigp;
        char c = (char) bArr2[i2];
        if (c == '(') {
            this.sigp = i2 + 1;
            List<Type> listSigToTypes = sigToTypes(')');
            Type typeSigToType = sigToType();
            List listNil = List.nil();
            while (true) {
                int i3 = this.sigp;
                if (i3 >= this.siglimit || this.signature[i3] != 94) {
                    break;
                }
                this.sigp = i3 + 1;
                listNil = listNil.prepend(sigToType());
            }
            for (List list = listNil; list.nonEmpty(); list = list.tail) {
                if (((Type) list.head).hasTag(TypeTag.TYPEVAR)) {
                    ((Type) list.head).tsym.flags_field |= Flags.THROWS;
                }
            }
            return new Type.MethodType(listSigToTypes, typeSigToType, listNil.reverse(), this.syms.methodClass);
        }
        if (c == '-') {
            this.sigp = i2 + 1;
            return new Type.WildcardType(sigToType(), BoundKind.SUPER, this.syms.boundClass);
        }
        if (c == '<') {
            this.typevars = this.typevars.dup(this.currentOwner);
            Type.ForAll forAll = new Type.ForAll(sigToTypeParams(), sigToType());
            this.typevars = this.typevars.leave();
            return forAll;
        }
        if (c == 'F') {
            this.sigp = i2 + 1;
            return this.syms.floatType;
        }
        if (c == 'L') {
            Type typeClassSigToType = classSigToType();
            int i4 = this.sigp;
            if (i4 >= this.siglimit || this.signature[i4] != 46) {
                return typeClassSigToType;
            }
            throw badClassFile("deprecated inner class signature syntax (please recompile from source)", new Object[0]);
        }
        if (c == 'V') {
            this.sigp = i2 + 1;
            return this.syms.voidType;
        }
        if (c == '*') {
            this.sigp = i2 + 1;
            Symtab symtab = this.syms;
            return new Type.WildcardType(symtab.objectType, BoundKind.UNBOUND, symtab.boundClass);
        }
        if (c == '+') {
            this.sigp = i2 + 1;
            return new Type.WildcardType(sigToType(), BoundKind.EXTENDS, this.syms.boundClass);
        }
        if (c == 'I') {
            this.sigp = i2 + 1;
            return this.syms.intType;
        }
        if (c == 'J') {
            this.sigp = i2 + 1;
            return this.syms.longType;
        }
        if (c == 'S') {
            this.sigp = i2 + 1;
            return this.syms.shortType;
        }
        if (c == 'T') {
            int i5 = i2 + 1;
            this.sigp = i5;
            while (true) {
                bArr = this.signature;
                i = this.sigp;
                if (bArr[i] == 59) {
                    break;
                }
                this.sigp = i + 1;
            }
            this.sigp = i + 1;
            return this.sigEnterPhase ? Type.noType : findTypeVar(readName(bArr, i5, i - i5));
        }
        if (c == 'Z') {
            this.sigp = i2 + 1;
            return this.syms.booleanType;
        }
        if (c == '[') {
            this.sigp = i2 + 1;
            return new Type.ArrayType(sigToType(), this.syms.arrayClass);
        }
        switch (c) {
            case 'B':
                this.sigp = i2 + 1;
                return this.syms.byteType;
            case 'C':
                this.sigp = i2 + 1;
                return this.syms.charType;
            case 'D':
                this.sigp = i2 + 1;
                return this.syms.doubleType;
            default:
                throw badClassFile("bad.signature", quoteBadSignature());
        }
    }

    public Type sigToTypeParam() {
        byte[] bArr;
        int i;
        Type.TypeVar typeVar;
        boolean z;
        int i2 = this.sigp;
        while (true) {
            bArr = this.signature;
            i = this.sigp;
            if (bArr[i] == 58) {
                break;
            }
            this.sigp = i + 1;
        }
        Name name = readName(bArr, i2, i - i2);
        if (this.sigEnterPhase) {
            typeVar = new Type.TypeVar(name, this.currentOwner, this.syms.botType);
            this.typevars.enter(typeVar.tsym);
        } else {
            typeVar = (Type.TypeVar) findTypeVar(name);
        }
        List listNil = List.nil();
        byte[] bArr2 = this.signature;
        int i3 = this.sigp;
        if (bArr2[i3] == 58 && bArr2[i3 + 1] == 58) {
            z = true;
            this.sigp = i3 + 1;
        } else {
            z = false;
        }
        while (true) {
            byte[] bArr3 = this.signature;
            int i4 = this.sigp;
            if (bArr3[i4] != 58) {
                break;
            }
            this.sigp = i4 + 1;
            listNil = listNil.prepend(sigToType());
        }
        if (!this.sigEnterPhase) {
            this.types.setBounds(typeVar, listNil.reverse(), z);
        }
        return typeVar;
    }

    public List<Type> sigToTypeParams() {
        int i;
        List listNil = List.nil();
        byte[] bArr = this.signature;
        int i2 = this.sigp;
        if (bArr[i2] == 60) {
            int i3 = i2 + 1;
            this.sigp = i3;
            this.sigEnterPhase = true;
            while (this.signature[this.sigp] != 62) {
                listNil = listNil.prepend(sigToTypeParam());
            }
            this.sigEnterPhase = false;
            this.sigp = i3;
            while (true) {
                byte[] bArr2 = this.signature;
                i = this.sigp;
                if (bArr2[i] == 62) {
                    break;
                }
                sigToTypeParam();
            }
            this.sigp = i + 1;
        }
        return listNil.reverse();
    }

    public List<Type> sigToTypes(char c) {
        List listOf = List.of((Object) null);
        List tail = listOf;
        while (true) {
            byte[] bArr = this.signature;
            int i = this.sigp;
            if (bArr[i] == c) {
                this.sigp = i + 1;
                return listOf.tail;
            }
            tail = tail.setTail(List.of(sigToType()));
        }
    }

    public void skipBytes(int i) {
        this.bp += i;
    }

    public void skipInnerClasses() {
        char cNextChar = nextChar();
        for (int i = 0; i < cNextChar; i++) {
            nextChar();
            nextChar();
            nextChar();
            nextChar();
        }
    }

    public void skipMember() {
        this.bp += 6;
        char cNextChar = nextChar();
        for (int i = 0; i < cNextChar; i++) {
            this.bp += 2;
            this.bp += nextInt();
        }
    }

    public void validateMethodType(Name name, Type type) {
        if ((!type.hasTag(TypeTag.METHOD) && !type.hasTag(TypeTag.FORALL)) || (name == this.names.init && !type.mo73getReturnType().hasTag(TypeTag.VOID))) {
            throw badClassFile("method.descriptor.invalid", name);
        }
    }

    public static class TypeAnnotationSymbolVisitor extends Types.DefaultSymbolVisitor<Void, Void> {
        public static final int SUPERCLASS_INDEX = 65535;
        private final List<Attribute.TypeCompound> attributes;

        private TypeAnnotationSymbolVisitor(List<Attribute.TypeCompound> list) {
            this.attributes = list;
        }

        public static /* synthetic */ boolean a(TargetType targetType, TypeAnnotationPosition typeAnnotationPosition) {
            return typeAnnotationPosition.type == targetType;
        }

        private Type addTypeAnnotations(Type type, Predicate<TypeAnnotationPosition> predicate) {
            Assert.checkNonNull(type);
            ListBuffer listBuffer = new ListBuffer();
            for (Attribute.TypeCompound typeCompound : this.attributes) {
                if (predicate.test(typeCompound.position)) {
                    listBuffer.add(typeCompound);
                }
            }
            if (listBuffer.isEmpty()) {
                return type;
            }
            HashMap map = new HashMap();
            for (Attribute.TypeCompound typeCompound2 : listBuffer.toList()) {
                ((ListBuffer) map.computeIfAbsent(typeCompound2.position.location, new Function() { // from class: com.sun.tools.javac.jvm.f
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ClassReader.TypeAnnotationSymbolVisitor.f((List) obj);
                    }
                })).add(typeCompound2);
            }
            return new TypeAnnotationStructuralTypeMapping(map).visit(type, List.nil());
        }

        public static /* synthetic */ boolean b(int i, TypeAnnotationPosition typeAnnotationPosition) {
            return typeAnnotationPosition.type == TargetType.CLASS_EXTENDS && typeAnnotationPosition.type_index == i;
        }

        public static /* synthetic */ boolean c(TargetType targetType, int i, int i2, TypeAnnotationPosition typeAnnotationPosition) {
            return typeAnnotationPosition.type == targetType && typeAnnotationPosition.parameter_index == i && typeAnnotationPosition.bound_index == i2;
        }

        private static Predicate<TypeAnnotationPosition> classExtends(final int i) {
            return new Predicate() { // from class: com.sun.tools.javac.jvm.i
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassReader.TypeAnnotationSymbolVisitor.b(i, (TypeAnnotationPosition) obj);
                }
            };
        }

        public static /* synthetic */ boolean d(int i, TypeAnnotationPosition typeAnnotationPosition) {
            return typeAnnotationPosition.type == TargetType.METHOD_FORMAL_PARAMETER && typeAnnotationPosition.parameter_index == i;
        }

        public static /* synthetic */ boolean e(int i, TypeAnnotationPosition typeAnnotationPosition) {
            return typeAnnotationPosition.type == TargetType.THROWS && typeAnnotationPosition.type_index == i;
        }

        public static /* synthetic */ ListBuffer f(List list) {
            return new ListBuffer();
        }

        private static Predicate<TypeAnnotationPosition> methodFormalParameter(final int i) {
            return new Predicate() { // from class: com.sun.tools.javac.jvm.g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassReader.TypeAnnotationSymbolVisitor.d(i, (TypeAnnotationPosition) obj);
                }
            };
        }

        private List<Type> rewriteTypeParameters(List<Type> list, TargetType targetType) {
            int i;
            ListBuffer listBuffer = new ListBuffer();
            int i2 = 0;
            for (Type type : list) {
                Type upperBound = type.getUpperBound();
                if (upperBound.isCompound()) {
                    Type.ClassType classType = (Type.ClassType) upperBound;
                    Type type2 = classType.supertype_field;
                    if (type2 != null) {
                        classType.supertype_field = addTypeAnnotations(type2, typeParameterBound(targetType, i2, 0));
                        i = 1;
                    } else {
                        i = 0;
                    }
                    ListBuffer listBuffer2 = new ListBuffer();
                    Iterator<Type> it = classType.interfaces_field.iterator();
                    while (it.hasNext()) {
                        listBuffer2.add(addTypeAnnotations(it.next(), typeParameterBound(targetType, i2, i)));
                        i++;
                    }
                    classType.interfaces_field = listBuffer2.toList();
                } else {
                    upperBound = addTypeAnnotations(upperBound, typeParameterBound(targetType, i2, upperBound.isInterface() ? 1 : 0));
                }
                ((Type.TypeVar) type).setUpperBound(upperBound);
                listBuffer.add(type);
                i2++;
            }
            return listBuffer.toList();
        }

        private static Predicate<TypeAnnotationPosition> thrownType(final int i) {
            return new Predicate() { // from class: com.sun.tools.javac.jvm.k
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassReader.TypeAnnotationSymbolVisitor.e(i, (TypeAnnotationPosition) obj);
                }
            };
        }

        private static Predicate<TypeAnnotationPosition> typeParameterBound(final TargetType targetType, final int i, final int i2) {
            return new Predicate() { // from class: com.sun.tools.javac.jvm.j
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassReader.TypeAnnotationSymbolVisitor.c(targetType, i, i2, (TypeAnnotationPosition) obj);
                }
            };
        }

        @Override // com.sun.tools.javac.code.Types.DefaultSymbolVisitor, com.sun.tools.javac.code.Symbol.Visitor
        public Void visitClassSymbol(Symbol.ClassSymbol classSymbol, Void r6) {
            Type.ClassType classType = (Type.ClassType) classSymbol.type;
            ListBuffer listBuffer = new ListBuffer();
            Iterator<Type> it = classType.interfaces_field.iterator();
            int i = 0;
            while (it.hasNext()) {
                listBuffer.add(addTypeAnnotations(it.next(), classExtends(i)));
                i++;
            }
            classType.interfaces_field = listBuffer.toList();
            classType.supertype_field = addTypeAnnotations(classType.supertype_field, classExtends(65535));
            List<Type> list = classType.typarams_field;
            if (list == null) {
                return null;
            }
            classType.typarams_field = rewriteTypeParameters(list, TargetType.CLASS_TYPE_PARAMETER_BOUND);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultSymbolVisitor, com.sun.tools.javac.code.Symbol.Visitor
        public Void visitMethodSymbol(Symbol.MethodSymbol methodSymbol, Void r9) {
            Type typeAddTypeAnnotations;
            Type type = methodSymbol.type;
            if (type.hasTag(TypeTag.FORALL)) {
                Type.ForAll forAll = (Type.ForAll) type;
                forAll.tvars = rewriteTypeParameters(forAll.tvars, TargetType.METHOD_TYPE_PARAMETER_BOUND);
                type = forAll.qtype;
            }
            Type.MethodType methodType = (Type.MethodType) type;
            ListBuffer listBuffer = new ListBuffer();
            int i = 0;
            int i2 = 0;
            for (Symbol.VarSymbol varSymbol : methodSymbol.params) {
                int i3 = i2 + 1;
                Type typeAddTypeAnnotations2 = addTypeAnnotations(varSymbol.type, methodFormalParameter(i2));
                varSymbol.type = typeAddTypeAnnotations2;
                listBuffer.add(typeAddTypeAnnotations2);
                i2 = i3;
            }
            methodType.argtypes = listBuffer.toList();
            ListBuffer listBuffer2 = new ListBuffer();
            Iterator<Type> it = methodType.thrown.iterator();
            while (it.hasNext()) {
                listBuffer2.add(addTypeAnnotations(it.next(), thrownType(i)));
                i++;
            }
            methodType.thrown = listBuffer2.toList();
            if (!methodType.restype.hasTag(TypeTag.VOID)) {
                methodType.restype = addTypeAnnotations(methodType.restype, TargetType.METHOD_RETURN);
            }
            Type typeImplicitReceiverType = methodType.recvtype;
            if (typeImplicitReceiverType == null) {
                typeImplicitReceiverType = methodSymbol.implicitReceiverType();
            }
            if (typeImplicitReceiverType == null || (typeAddTypeAnnotations = addTypeAnnotations(typeImplicitReceiverType, TargetType.METHOD_RECEIVER)) == typeImplicitReceiverType) {
                return null;
            }
            methodType.recvtype = typeAddTypeAnnotations;
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultSymbolVisitor, com.sun.tools.javac.code.Symbol.Visitor
        public Void visitVarSymbol(Symbol.VarSymbol varSymbol, Void r3) {
            varSymbol.type = addTypeAnnotations(varSymbol.type, TargetType.FIELD);
            return null;
        }

        @Override // com.sun.tools.javac.code.Symbol.Visitor
        public Void visitSymbol(Symbol symbol, Void r2) {
            return null;
        }

        private Type addTypeAnnotations(Type type, final TargetType targetType) {
            return addTypeAnnotations(type, new Predicate() { // from class: com.sun.tools.javac.jvm.h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ClassReader.TypeAnnotationSymbolVisitor.a(targetType, (TypeAnnotationPosition) obj);
                }
            });
        }
    }

    public static final class TypeAnnotationStructuralTypeMapping extends Types.TypeMapping<List<TypeAnnotationPosition.TypePathEntry>> {
        private final Map<List<TypeAnnotationPosition.TypePathEntry>, ListBuffer<Attribute.TypeCompound>> attributesByPath;

        private TypeAnnotationStructuralTypeMapping(Map<List<TypeAnnotationPosition.TypePathEntry>, ListBuffer<Attribute.TypeCompound>> map) {
            this.attributesByPath = map;
        }

        public static /* synthetic */ int b(int i) {
            return i + 1;
        }

        public static /* synthetic */ Type c(TypeAnnotationStructuralTypeMapping typeAnnotationStructuralTypeMapping, List list, PrimitiveIterator.OfInt ofInt, Type type) {
            typeAnnotationStructuralTypeMapping.getClass();
            return typeAnnotationStructuralTypeMapping.visit(type, list.append(new TypeAnnotationPosition.TypePathEntry(TypeAnnotationPosition.TypePathEntryKind.TYPE_ARGUMENT, ofInt.nextInt())));
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.PrimitiveIterator$OfInt] */
        private List<Type> rewriteTypeParams(final List<TypeAnnotationPosition.TypePathEntry> list, List<Type> list2) {
            final ?? it = IntStream.iterate(0, new IntUnaryOperator() { // from class: com.sun.tools.javac.jvm.d
                @Override // java.util.function.IntUnaryOperator
                public final int applyAsInt(int i) {
                    return ClassReader.TypeAnnotationStructuralTypeMapping.b(i);
                }
            }).iterator();
            return list2.map(new Function() { // from class: com.sun.tools.javac.jvm.e
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ClassReader.TypeAnnotationStructuralTypeMapping.c(this.b, list, it, (Type) obj);
                }
            });
        }

        public List<Attribute.TypeCompound> attributesForPath(List<TypeAnnotationPosition.TypePathEntry> list) {
            ListBuffer<Attribute.TypeCompound> listBufferRemove = this.attributesByPath.remove(list);
            return listBufferRemove != null ? listBufferRemove.toList() : List.nil();
        }

        public Type reannotate(Type type, List<TypeAnnotationPosition.TypePathEntry> list) {
            List<Attribute.TypeCompound> listAttributesForPath = attributesForPath(list);
            if (listAttributesForPath.isEmpty()) {
                return type;
            }
            TypeMetadata.Annotations annotations = (TypeMetadata.Annotations) type.getMetadata(TypeMetadata.Annotations.class);
            if (annotations == null) {
                return type.annotatedType(listAttributesForPath);
            }
            annotations.annotationBuffer().addAll(listAttributesForPath);
            return type;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitArrayType(Type.ArrayType arrayType, List<TypeAnnotationPosition.TypePathEntry> list) {
            Type type = arrayType.elemtype;
            Type typeVisit = visit(type, list.append(TypeAnnotationPosition.TypePathEntry.ARRAY));
            if (typeVisit != type) {
                arrayType = new Type.ArrayType(typeVisit, arrayType.tsym, arrayType.getMetadata());
            }
            return reannotate(arrayType, list);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, List<TypeAnnotationPosition.TypePathEntry> list) {
            Type enclosingType = classType.getEnclosingType();
            Type typeVisit = enclosingType != Type.noType ? visit(enclosingType, list) : enclosingType;
            for (Type enclosingType2 = classType.getEnclosingType(); enclosingType2 != Type.noType; enclosingType2 = enclosingType2.getEnclosingType()) {
                list = list.append(TypeAnnotationPosition.TypePathEntry.INNER_TYPE);
            }
            List<Type> typeArguments = classType.getTypeArguments();
            List<Type> listRewriteTypeParams = rewriteTypeParams(list, typeArguments);
            if (typeVisit != enclosingType || typeArguments != listRewriteTypeParams) {
                classType = new Type.ClassType(typeVisit, listRewriteTypeParams, classType.tsym, classType.getMetadata());
            }
            return reannotate(classType, list);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitWildcardType(Type.WildcardType wildcardType, List<TypeAnnotationPosition.TypePathEntry> list) {
            Type typeVisit = wildcardType.type;
            if (typeVisit != null) {
                typeVisit = visit(typeVisit, list.append(TypeAnnotationPosition.TypePathEntry.WILDCARD));
            }
            Type type = typeVisit;
            if (type != wildcardType.type) {
                wildcardType = new Type.WildcardType(type, wildcardType.kind, wildcardType.tsym, wildcardType.bound, wildcardType.getMetadata());
            }
            return reannotate(wildcardType, list);
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, List<TypeAnnotationPosition.TypePathEntry> list) {
            return reannotate(type, list);
        }
    }

    public Symbol.ClassSymbol enterClass(Name name, Symbol.TypeSymbol typeSymbol) {
        return this.syms.enterClass(this.currentModule, name, typeSymbol);
    }

    public ClassFinder.BadClassFile badClassFile(JCDiagnostic.Fragment fragment) {
        return badClassFile(this.diagFactory.fragment(fragment));
    }

    public ClassFinder.BadClassFile badClassFile(String str, Object... objArr) {
        return badClassFile(this.diagFactory.fragment(str, objArr));
    }

    public List<Type> sigToTypeParams(byte[] bArr, int i, int i2) {
        this.signature = bArr;
        this.sigp = i;
        this.siglimit = i + i2;
        return sigToTypeParams();
    }

    public Type sigToType(byte[] bArr, int i, int i2) {
        this.signature = bArr;
        this.sigp = i;
        this.siglimit = i + i2;
        return sigToType();
    }

    public void attachAnnotations(Symbol symbol) {
        attachAnnotations(symbol, readAnnotations());
    }
}
