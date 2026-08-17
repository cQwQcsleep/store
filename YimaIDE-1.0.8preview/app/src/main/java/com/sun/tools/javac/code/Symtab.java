package com.sun.tools.javac.code;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.Iterators;
import com.sun.tools.javac.util.JavacMessages;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.lang.model.element.ElementVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Symtab {
    public final Type annotationTargetType;
    public final Type annotationType;
    public final Symbol.ClassSymbol arrayClass;
    public final Symbol.MethodSymbol arrayCloneMethod;
    public final Type arraysType;
    public final Type assertionErrorType;
    public final Symbol.MethodSymbol autoCloseableClose;
    public final Type autoCloseableType;
    public final Type.JCPrimitiveType booleanType;
    public final Type botType;
    public final Symbol.ClassSymbol boundClass;
    public final Name[] boxedName;
    public final Type.JCPrimitiveType byteType;
    public final Type.JCPrimitiveType charType;
    public final Type classDescType;
    private final Map<Types.UniqueType, Symbol.VarSymbol> classFields;
    public final Type classLoaderType;
    public final Type classNotFoundExceptionType;
    public final Type classType;
    private final Map<Name, Map<Symbol.ModuleSymbol, Symbol.ClassSymbol>> classes;
    public final Type cloneNotSupportedExceptionType;
    public final Type cloneableType;
    public final Type collectionsType;
    public final Type comparableType;
    public final Type comparatorType;
    public final Type constantBootstrapsType;
    public final Type deprecatedType;
    public final Type documentedType;
    public final Type.JCPrimitiveType doubleType;
    public final Type elementTypeType;
    public final Type enumDescType;
    public final Symbol.MethodSymbol enumFinalFinalize;
    public final Symbol.TypeSymbol enumSym;
    public final Symbol.ModuleSymbol errModule;
    public final Symbol.ClassSymbol errSymbol;
    public final Type errType;
    public final Type errorType;
    public final Type exactConversionsSupportType;
    public final Type exceptionType;
    public final Type externalizableType;
    public final Type.JCPrimitiveType floatType;
    public final Type functionalInterfaceType;
    public final Type illegalArgumentExceptionType;
    public final Type incompatibleClassChangeErrorType;
    public final Type inheritedType;
    private final Symbol.Completer initialCompleter;
    public final Type.JCPrimitiveType intType;
    public final Type interruptedExceptionType;
    public final Type ioExceptionType;
    public final Type iterableType;
    public final Type iteratorType;
    public final Symbol.ModuleSymbol java_base;
    public final Type lambdaMetafactory;
    public final Symbol.VarSymbol lengthVar;
    public final Type listType;
    public final Type.JCPrimitiveType longType;
    public final Type matchExceptionType;
    private final JavacMessages messages;
    public final Symbol.ClassSymbol methodClass;
    public final Type methodHandleLookupType;
    public final Type methodHandleType;
    public final Type methodHandlesType;
    public final Type methodTypeType;
    private final Symbol.Completer moduleCompleter;
    private final Map<Name, Symbol.ModuleSymbol> modules;
    private final Names names;
    public final Type nativeHeaderType;
    public final Type noClassDefFoundErrorType;
    public final Symbol.ModuleSymbol noModule;
    public final Type noSuchFieldErrorType;
    public final Symbol.TypeSymbol noSymbol;
    public final Type objectInputStreamType;
    public final Type objectInputType;
    public final Type objectMethodsType;
    public final Type objectOutputStreamType;
    public final Type objectOutputType;
    public final Type objectStreamExceptionType;
    public final Type objectStreamFieldType;
    public final Type objectType;
    public final Type objectsType;
    public final Type overrideType;
    private final Map<Name, Map<Symbol.ModuleSymbol, Symbol.PackageSymbol>> packages;
    public final Symbol.ClassSymbol predefClass;
    public final Type previewFeatureInternalType;
    public final Type previewFeatureType;
    public final Type profileType;
    public final Type proprietaryType;
    public final Type recordType;
    public final Type repeatableType;
    public final Type requiresIdentityInternalType;
    public final Type requiresIdentityType;
    public final Type restrictedInternalType;
    public final Type restrictedType;
    public final Type retentionType;
    public final Symbol.PackageSymbol rootPackage;
    public final Type runtimeExceptionType;
    public final Type serializableType;
    public final Type serializedLambdaType;
    public final Type.JCPrimitiveType shortType;
    public final Type stringBufferType;
    public final Type stringBuilderType;
    public final Type stringConcatFactory;
    public final Type stringType;
    public final Type supplierType;
    public final Type suppressWarningsType;
    public final Type switchBootstrapsType;
    public final Type systemType;
    public final Type throwableType;
    public final Type trustMeType;
    public final Type typeDescriptorType;
    public final Type[] typeOfTag;
    public final Symbol.ClassSymbol unknownSymbol;
    public final Type unknownType;
    public final Symbol.ModuleSymbol unnamedModule;
    public final Type valueBasedInternalType;
    public final Type valueBasedType;
    public final Type varHandleType;
    public final Type.JCVoidType voidType;
    protected static final Context.Key<Symtab> symtabKey = new Context.Key<>();
    private static final Map<Symbol.ModuleSymbol, Symbol.ClassSymbol> EMPTY = new HashMap();

    public Symtab(Context context) throws Symbol.CompletionFailure {
        Symbol symbol = null;
        Type.JCPrimitiveType jCPrimitiveType = new Type.JCPrimitiveType(TypeTag.BYTE, null);
        this.byteType = jCPrimitiveType;
        Type.JCPrimitiveType jCPrimitiveType2 = new Type.JCPrimitiveType(TypeTag.CHAR, null);
        this.charType = jCPrimitiveType2;
        Type.JCPrimitiveType jCPrimitiveType3 = new Type.JCPrimitiveType(TypeTag.SHORT, null);
        this.shortType = jCPrimitiveType3;
        Type.JCPrimitiveType jCPrimitiveType4 = new Type.JCPrimitiveType(TypeTag.INT, null);
        this.intType = jCPrimitiveType4;
        Type.JCPrimitiveType jCPrimitiveType5 = new Type.JCPrimitiveType(TypeTag.LONG, null);
        this.longType = jCPrimitiveType5;
        Type.JCPrimitiveType jCPrimitiveType6 = new Type.JCPrimitiveType(TypeTag.FLOAT, null);
        this.floatType = jCPrimitiveType6;
        Type.JCPrimitiveType jCPrimitiveType7 = new Type.JCPrimitiveType(TypeTag.DOUBLE, null);
        this.doubleType = jCPrimitiveType7;
        Type.JCPrimitiveType jCPrimitiveType8 = new Type.JCPrimitiveType(TypeTag.BOOLEAN, null);
        this.booleanType = jCPrimitiveType8;
        Type bottomType = new Type.BottomType();
        this.botType = bottomType;
        Type.JCVoidType jCVoidType = new Type.JCVoidType();
        this.voidType = jCVoidType;
        this.typeOfTag = new Type[TypeTag.getTypeTagCount()];
        this.boxedName = new Name[TypeTag.getTypeTagCount()];
        this.classes = new HashMap();
        this.packages = new HashMap();
        this.modules = new LinkedHashMap();
        this.classFields = new HashMap();
        context.put(symtabKey, this);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.messages = JavacMessages.instance(context);
        Symbol.RootPackageSymbol rootPackageSymbol = new Symbol.RootPackageSymbol(namesInstance.empty, null, MissingInfoHandler.instance(context), Target.instance(context).runtimeUseNestAccess());
        this.rootPackage = rootPackageSymbol;
        Symbol.ModuleSymbol moduleSymbol = new Symbol.ModuleSymbol(namesInstance.empty, symbol) { // from class: com.sun.tools.javac.code.Symtab.3
            @Override // com.sun.tools.javac.code.Symbol.ModuleSymbol
            public boolean isNoModule() {
                return true;
            }
        };
        this.noModule = moduleSymbol;
        addRootPackageFor(moduleSymbol);
        if (Source.Feature.MODULES.allowedInSource(Source.instance(context))) {
            Symbol.ModuleSymbol moduleSymbolEnterModule = enterModule(namesInstance.java_base);
            this.java_base = moduleSymbolEnterModule;
            moduleSymbolEnterModule.completer = Symbol.Completer.NULL_COMPLETER;
            moduleSymbolEnterModule.visiblePackages = Collections.EMPTY_MAP;
        } else {
            this.java_base = moduleSymbol;
        }
        Symbol.ModuleSymbol moduleSymbol2 = new Symbol.ModuleSymbol(namesInstance.empty, symbol) { // from class: com.sun.tools.javac.code.Symtab.4
            {
                this.directives = List.nil();
                this.exports = List.nil();
                this.provides = List.nil();
                this.uses = List.nil();
                this.requires = List.of(new Directive.RequiresDirective(Symtab.this.java_base, EnumSet.of(Directive.RequiresFlag.MANDATED)));
            }

            @Override // com.sun.tools.javac.code.Symbol.ModuleSymbol, com.sun.tools.javac.code.Symbol
            public String toString() {
                return Symtab.this.messages.getLocalizedString("compiler.misc.unnamed.module", new Object[0]);
            }
        };
        this.unnamedModule = moduleSymbol2;
        addRootPackageFor(moduleSymbol2);
        moduleSymbol2.enclosedPackages = moduleSymbol2.enclosedPackages.prepend(moduleSymbol2.unnamedPackage);
        Symbol.ModuleSymbol moduleSymbol3 = new Symbol.ModuleSymbol(namesInstance.empty, null) { // from class: com.sun.tools.javac.code.Symtab.5
            {
                this.directives = List.nil();
                this.exports = List.nil();
                this.provides = List.nil();
                this.uses = List.nil();
                this.requires = List.of(new Directive.RequiresDirective(Symtab.this.java_base, EnumSet.of(Directive.RequiresFlag.MANDATED)));
            }
        };
        this.errModule = moduleSymbol3;
        addRootPackageFor(moduleSymbol3);
        Kinds.Kind kind = Kinds.Kind.NIL;
        Name name = namesInstance.empty;
        Type.JCNoType jCNoType = Type.noType;
        Symbol.TypeSymbol typeSymbol = new Symbol.TypeSymbol(kind, 0L, name, jCNoType, rootPackageSymbol) { // from class: com.sun.tools.javac.code.Symtab.6
            @Override // javax.lang.model.element.Element
            public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
                return elementVisitor.visitUnknown(this, p);
            }
        };
        this.noSymbol = typeSymbol;
        Symbol.ClassSymbol classSymbol = new Symbol.ClassSymbol(1073741833L, namesInstance.any, null, rootPackageSymbol);
        this.errSymbol = classSymbol;
        Type errorType = new Type.ErrorType(classSymbol, jCNoType);
        this.errType = errorType;
        Symbol.ClassSymbol classSymbol2 = new Symbol.ClassSymbol(1073741833L, namesInstance.fromString("<any?>"), null, rootPackageSymbol);
        this.unknownSymbol = classSymbol2;
        Type errorType2 = new Type.ErrorType(classSymbol2, jCNoType);
        this.unknownType = errorType2;
        initType(jCPrimitiveType, "byte", "Byte");
        initType(jCPrimitiveType3, "short", "Short");
        initType(jCPrimitiveType2, PsiKeyword.CHAR, "Character");
        initType(jCPrimitiveType4, "int", "Integer");
        initType(jCPrimitiveType5, "long", "Long");
        initType(jCPrimitiveType6, "float", "Float");
        initType(jCPrimitiveType7, "double", "Double");
        initType(jCPrimitiveType8, "boolean", "Boolean");
        initType(jCVoidType, PsiKeyword.VOID, "Void");
        initType(bottomType, "<nulltype>");
        initType(errorType, classSymbol);
        initType(errorType2, classSymbol2);
        Symbol.ClassSymbol classSymbol3 = new Symbol.ClassSymbol(1073741825L, namesInstance.Array, typeSymbol);
        this.arrayClass = classSymbol3;
        Symbol.ClassSymbol classSymbol4 = new Symbol.ClassSymbol(1073741825L, namesInstance.Bound, typeSymbol);
        this.boundClass = classSymbol4;
        classSymbol4.members_field = new Scope.ErrorScope(classSymbol4);
        Symbol.ClassSymbol classSymbol5 = new Symbol.ClassSymbol(1073741825L, namesInstance.Method, typeSymbol);
        this.methodClass = classSymbol5;
        classSymbol5.members_field = new Scope.ErrorScope(classSymbol4);
        Symbol.ClassSymbol classSymbol6 = new Symbol.ClassSymbol(1073741825L, namesInstance.empty, rootPackageSymbol);
        this.predefClass = classSymbol6;
        Scope.WriteableScope writeableScopeCreate = Scope.WriteableScope.create(classSymbol6);
        classSymbol6.members_field = writeableScopeCreate;
        this.initialCompleter = ClassFinder.instance(context).getCompleter();
        rootPackageSymbol.members_field = Scope.WriteableScope.create(rootPackageSymbol);
        writeableScopeCreate.enter(jCPrimitiveType.tsym);
        writeableScopeCreate.enter(jCPrimitiveType3.tsym);
        writeableScopeCreate.enter(jCPrimitiveType2.tsym);
        writeableScopeCreate.enter(jCPrimitiveType4.tsym);
        writeableScopeCreate.enter(jCPrimitiveType5.tsym);
        writeableScopeCreate.enter(jCPrimitiveType6.tsym);
        writeableScopeCreate.enter(jCPrimitiveType7.tsym);
        writeableScopeCreate.enter(jCPrimitiveType8.tsym);
        writeableScopeCreate.enter(errorType.tsym);
        writeableScopeCreate.enter(classSymbol);
        final Symbol.Completer completer = Modules.instance(context).getCompleter();
        this.moduleCompleter = completer;
        Type typeEnterClass = enterClass(Constants.OBJECT_CLASS);
        this.objectType = typeEnterClass;
        this.objectMethodsType = enterClass("java.lang.runtime.ObjectMethods");
        this.exactConversionsSupportType = enterClass("java.lang.runtime.ExactConversionsSupport");
        this.objectsType = enterClass("java.util.Objects");
        this.classType = enterClass(Constants.CLASS_CLASS);
        this.stringType = enterClass("java.lang.String");
        this.stringBufferType = enterClass(Constants.STRING_BUFFER_CLASS);
        this.stringBuilderType = enterClass("java.lang.StringBuilder");
        Type typeEnterClass2 = enterClass("java.lang.Cloneable");
        this.cloneableType = typeEnterClass2;
        this.throwableType = enterClass("java.lang.Throwable");
        Type typeEnterClass3 = enterClass("java.io.Serializable");
        this.serializableType = typeEnterClass3;
        Type typeEnterClass4 = enterClass("java.lang.invoke.SerializedLambda");
        this.serializedLambdaType = typeEnterClass4;
        this.varHandleType = enterClass("java.lang.invoke.VarHandle");
        this.methodHandleType = enterClass("java.lang.invoke.MethodHandle");
        this.methodHandlesType = enterClass("java.lang.invoke.MethodHandles");
        this.methodHandleLookupType = enterClass("java.lang.invoke.MethodHandles$Lookup");
        this.methodTypeType = enterClass("java.lang.invoke.MethodType");
        this.errorType = enterClass("java.lang.Error");
        this.illegalArgumentExceptionType = enterClass("java.lang.IllegalArgumentException");
        this.interruptedExceptionType = enterClass("java.lang.InterruptedException");
        Type typeEnterClass5 = enterClass("java.lang.Exception");
        this.exceptionType = typeEnterClass5;
        this.runtimeExceptionType = enterClass("java.lang.RuntimeException");
        this.classNotFoundExceptionType = enterClass("java.lang.ClassNotFoundException");
        this.noClassDefFoundErrorType = enterClass("java.lang.NoClassDefFoundError");
        this.noSuchFieldErrorType = enterClass("java.lang.NoSuchFieldError");
        this.assertionErrorType = enterClass("java.lang.AssertionError");
        this.incompatibleClassChangeErrorType = enterClass("java.lang.IncompatibleClassChangeError");
        this.cloneNotSupportedExceptionType = enterClass("java.lang.CloneNotSupportedException");
        this.matchExceptionType = enterClass("java.lang.MatchException");
        this.annotationType = enterClass("java.lang.annotation.Annotation");
        this.classLoaderType = enterClass("java.lang.ClassLoader");
        Symbol.ClassSymbol classSymbolEnterClass = enterClass(this.java_base, namesInstance.java_lang_Enum);
        this.enumSym = classSymbolEnterClass;
        this.enumFinalFinalize = new Symbol.MethodSymbol(137438953492L, namesInstance.finalize, new Type.MethodType(List.nil(), jCVoidType, List.nil(), classSymbol5), classSymbolEnterClass);
        this.listType = enterClass("java.util.List");
        this.collectionsType = enterClass("java.util.Collections");
        this.comparableType = enterClass("java.lang.Comparable");
        this.comparatorType = enterClass("java.util.Comparator");
        this.arraysType = enterClass("java.util.Arrays");
        this.iterableType = enterClass("java.lang.Iterable");
        this.iteratorType = enterClass("java.util.Iterator");
        this.annotationTargetType = enterClass("java.lang.annotation.Target");
        this.overrideType = enterClass("java.lang.Override");
        this.retentionType = enterClass("java.lang.annotation.Retention");
        this.deprecatedType = enterClass("java.lang.Deprecated");
        this.suppressWarningsType = enterClass("java.lang.SuppressWarnings");
        this.supplierType = enterClass("java.util.function.Supplier");
        this.inheritedType = enterClass("java.lang.annotation.Inherited");
        this.repeatableType = enterClass("java.lang.annotation.Repeatable");
        this.documentedType = enterClass("java.lang.annotation.Documented");
        this.elementTypeType = enterClass("java.lang.annotation.ElementType");
        this.systemType = enterClass("java.lang.System");
        Type typeEnterClass6 = enterClass("java.lang.AutoCloseable");
        this.autoCloseableType = typeEnterClass6;
        this.autoCloseableClose = new Symbol.MethodSymbol(1L, namesInstance.close, new Type.MethodType(List.nil(), jCVoidType, List.of(typeEnterClass5), classSymbol5), typeEnterClass6.tsym);
        this.trustMeType = enterClass("java.lang.SafeVarargs");
        this.nativeHeaderType = enterClass("java.lang.annotation.Native");
        Type typeEnterClass7 = enterClass("java.lang.invoke.LambdaMetafactory");
        this.lambdaMetafactory = typeEnterClass7;
        Type typeEnterClass8 = enterClass("java.lang.invoke.StringConcatFactory");
        this.stringConcatFactory = typeEnterClass8;
        this.functionalInterfaceType = enterClass("java.lang.FunctionalInterface");
        this.previewFeatureType = enterClass("jdk.internal.javac.PreviewFeature");
        this.previewFeatureInternalType = enterSyntheticAnnotation("jdk.internal.PreviewFeature+Annotation");
        this.restrictedType = enterClass("jdk.internal.javac.Restricted");
        this.restrictedInternalType = enterSyntheticAnnotation("jdk.internal.javac.Restricted+Annotation");
        this.typeDescriptorType = enterClass("java.lang.invoke.TypeDescriptor");
        this.recordType = enterClass("java.lang.Record");
        this.switchBootstrapsType = enterClass("java.lang.runtime.SwitchBootstraps");
        this.constantBootstrapsType = enterClass("java.lang.invoke.ConstantBootstraps");
        this.valueBasedType = enterClass("jdk.internal.ValueBased");
        this.valueBasedInternalType = enterSyntheticAnnotation("jdk.internal.ValueBased+Annotation");
        this.requiresIdentityType = enterClass("jdk.internal.RequiresIdentity");
        this.requiresIdentityInternalType = enterSyntheticAnnotation(namesInstance.requiresIdentityInternal);
        this.classDescType = enterClass("java.lang.constant.ClassDesc");
        this.enumDescType = enterClass("java.lang.Enum$EnumDesc");
        this.objectStreamFieldType = enterClass("java.io.ObjectStreamField");
        this.objectInputStreamType = enterClass("java.io.ObjectInputStream");
        this.objectOutputStreamType = enterClass("java.io.ObjectOutputStream");
        this.ioExceptionType = enterClass("java.io.IOException");
        this.objectStreamExceptionType = enterClass("java.io.ObjectStreamException");
        this.externalizableType = enterClass("java.io.Externalizable");
        this.objectInputType = enterClass("java.io.ObjectInput");
        this.objectOutputType = enterClass("java.io.ObjectOutput");
        synthesizeEmptyInterfaceIfMissing(typeEnterClass6);
        synthesizeEmptyInterfaceIfMissing(typeEnterClass2);
        synthesizeEmptyInterfaceIfMissing(typeEnterClass3);
        synthesizeEmptyInterfaceIfMissing(typeEnterClass7);
        synthesizeEmptyInterfaceIfMissing(typeEnterClass4);
        synthesizeEmptyInterfaceIfMissing(typeEnterClass8);
        synthesizeBoxTypeIfMissing(jCPrimitiveType7);
        synthesizeBoxTypeIfMissing(jCPrimitiveType6);
        synthesizeBoxTypeIfMissing(jCVoidType);
        this.proprietaryType = enterSyntheticAnnotation("sun.Proprietary+Annotation");
        Type typeEnterSyntheticAnnotation = enterSyntheticAnnotation("jdk.Profile+Annotation");
        this.profileType = typeEnterSyntheticAnnotation;
        typeEnterSyntheticAnnotation.tsym.members().enter(new Symbol.MethodSymbol(Flags.AnnotationTypeElementMask, namesInstance.value, jCPrimitiveType4, typeEnterSyntheticAnnotation.tsym));
        Type.ClassType classType = (Type.ClassType) classSymbol3.type;
        classType.supertype_field = typeEnterClass;
        classType.interfaces_field = List.of(typeEnterClass2, typeEnterClass3);
        classSymbol3.members_field = Scope.WriteableScope.create(classSymbol3);
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(17L, namesInstance.length, jCPrimitiveType4, classSymbol3);
        this.lengthVar = varSymbol;
        classSymbol3.members().enter(varSymbol);
        Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(1L, namesInstance.clone, new Type.MethodType(List.nil(), typeEnterClass, List.nil(), classSymbol5), classSymbol3);
        this.arrayCloneMethod = methodSymbol;
        classSymbol3.members().enter(methodSymbol);
        Symbol.ModuleSymbol moduleSymbol4 = this.java_base;
        if (moduleSymbol4 != moduleSymbol) {
            Objects.requireNonNull(completer);
            moduleSymbol4.completer = new Symbol.Completer() { // from class: syd
                @Override // com.sun.tools.javac.code.Symbol.Completer
                public final void complete(Symbol symbol2) {
                    completer.complete(symbol2);
                }
            };
        }
    }

    private void addRootPackageFor(Symbol.ModuleSymbol moduleSymbol) {
        doEnterPackage(moduleSymbol, this.rootPackage);
        Symbol.PackageSymbol packageSymbol = new Symbol.PackageSymbol(this.names.empty, this.rootPackage) { // from class: com.sun.tools.javac.code.Symtab.7
            @Override // com.sun.tools.javac.code.Symbol.PackageSymbol, com.sun.tools.javac.code.Symbol
            public String toString() {
                return Symtab.this.messages.getLocalizedString("compiler.misc.unnamed.package", new Object[0]);
            }
        };
        packageSymbol.modle = moduleSymbol;
        packageSymbol.completer = new Symbol.Completer() { // from class: tyd
            @Override // com.sun.tools.javac.code.Symbol.Completer
            public final void complete(Symbol symbol) {
                this.b.initialCompleter.complete(symbol);
            }
        };
        packageSymbol.flags_field |= 8388608;
        moduleSymbol.unnamedPackage = packageSymbol;
    }

    public static /* synthetic */ String d(Symbol.ModuleSymbol moduleSymbol) {
        return "rootPackage missing!; currModule: " + moduleSymbol;
    }

    private void doEnterClass(Symbol.ModuleSymbol moduleSymbol, Symbol.ClassSymbol classSymbol) {
        this.classes.computeIfAbsent(classSymbol.flatname, new Function() { // from class: ryd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Symtab.g((Name) obj);
            }
        }).put(moduleSymbol, classSymbol);
    }

    private void doEnterPackage(Symbol.ModuleSymbol moduleSymbol, Symbol.PackageSymbol packageSymbol) {
        this.packages.computeIfAbsent(packageSymbol.fullname, new Function() { // from class: xyd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Symtab.i((Name) obj);
            }
        }).put(moduleSymbol, packageSymbol);
        moduleSymbol.enclosedPackages = moduleSymbol.enclosedPackages.prepend(packageSymbol);
    }

    public static /* synthetic */ boolean e(Symtab symtab, Symbol.ModuleSymbol moduleSymbol) {
        return moduleSymbol == symtab.unnamedModule;
    }

    private Type enterSyntheticAnnotation(Name name) {
        Type.ClassType classType = (Type.ClassType) enterClass(this.java_base, name).type;
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) classType.tsym;
        classSymbol.completer = Symbol.Completer.NULL_COMPLETER;
        classSymbol.flags_field = 1073750529L;
        classSymbol.erasure_field = classType;
        classSymbol.members_field = Scope.WriteableScope.create(classSymbol);
        classType.typarams_field = List.nil();
        classType.allparams_field = List.nil();
        classType.supertype_field = this.annotationType;
        classType.interfaces_field = List.nil();
        return classType;
    }

    public static /* synthetic */ Map g(Name name) {
        return new HashMap();
    }

    public static /* synthetic */ Symbol.VarSymbol h(Symtab symtab, Type type, Types types, Types.UniqueType uniqueType) {
        Type typeErasure;
        symtab.getClass();
        if (type.getTag() == TypeTag.ARRAY || type.getTag() == TypeTag.CLASS) {
            typeErasure = types.erasure(type);
        } else {
            if (!type.isPrimitiveOrVoid()) {
                x01.a(type);
                return null;
            }
            typeErasure = types.boxedClass(type).type;
        }
        return new Symbol.VarSymbol(25L, symtab.names._class, new Type.ClassType(symtab.classType.getEnclosingType(), List.of(typeErasure), symtab.classType.tsym), type.tsym);
    }

    public static /* synthetic */ Map i(Name name) {
        return new HashMap();
    }

    public static Symtab instance(Context context) {
        Symtab symtab = (Symtab) context.get(symtabKey);
        return symtab == null ? new Symtab(context) : symtab;
    }

    private Symbol.PackageSymbol lookupPackage(Symbol.ModuleSymbol moduleSymbol, Name name, boolean z) {
        Assert.checkNonNull(moduleSymbol);
        if (name.length() == 0) {
            return moduleSymbol.unnamedPackage;
        }
        if (moduleSymbol == this.noModule) {
            return enterPackage(moduleSymbol, name);
        }
        moduleSymbol.complete();
        Symbol.PackageSymbol packageSymbol = moduleSymbol.visiblePackages.get(name);
        if (packageSymbol != null) {
            return packageSymbol;
        }
        Symbol.PackageSymbol packageSymbol2 = getPackage(moduleSymbol, name);
        if ((packageSymbol2 != null && packageSymbol2.exists()) || z) {
            return packageSymbol2;
        }
        List<Directive.RequiresDirective> list = moduleSymbol.requires;
        if (list == null || !list.stream().map(new Function() { // from class: nyd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Directive.RequiresDirective) obj).module;
            }
        }).anyMatch(new Predicate() { // from class: oyd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Symtab.e(this.b, (Symbol.ModuleSymbol) obj);
            }
        })) {
            return enterPackage(moduleSymbol, name);
        }
        Symbol.PackageSymbol packageSymbol3 = getPackage(this.unnamedModule, name);
        if (packageSymbol3 != null && packageSymbol3.exists()) {
            moduleSymbol.visiblePackages.put(packageSymbol3.fullname, packageSymbol3);
            return packageSymbol3;
        }
        Symbol.PackageSymbol packageSymbolEnterPackage = enterPackage(moduleSymbol, name);
        packageSymbolEnterPackage.complete();
        if (!packageSymbolEnterPackage.exists()) {
            Symbol.PackageSymbol packageSymbolEnterPackage2 = enterPackage(this.unnamedModule, name);
            packageSymbolEnterPackage2.complete();
            if (packageSymbolEnterPackage2.exists()) {
                moduleSymbol.visiblePackages.put(packageSymbolEnterPackage2.fullname, packageSymbolEnterPackage2);
                return packageSymbolEnterPackage2;
            }
        }
        return packageSymbolEnterPackage;
    }

    public Symbol.ClassSymbol defineClass(Name name, Symbol symbol) {
        Symbol.ClassSymbol classSymbol = new Symbol.ClassSymbol(0L, name, symbol);
        classSymbol.completer = this.initialCompleter;
        return classSymbol;
    }

    public Symbol.ClassSymbol enterClass(Symbol.ModuleSymbol moduleSymbol, Name name, Symbol.TypeSymbol typeSymbol) {
        Assert.checkNonNull(moduleSymbol);
        Symbol.ClassSymbol classSymbol = getClass(moduleSymbol, Symbol.TypeSymbol.formFlatName(name, typeSymbol));
        if (classSymbol == null) {
            Symbol.ClassSymbol classSymbolDefineClass = defineClass(name, typeSymbol);
            doEnterClass(moduleSymbol, classSymbolDefineClass);
            return classSymbolDefineClass;
        }
        if ((classSymbol.name != name || classSymbol.owner != typeSymbol) && typeSymbol.kind == Kinds.Kind.TYP) {
            Symbol symbol = classSymbol.owner;
            if (symbol.kind == Kinds.Kind.PCK && (classSymbol.flags_field & 2097152) == 0) {
                symbol.members().remove(classSymbol);
                classSymbol.name = name;
                classSymbol.owner = typeSymbol;
                classSymbol.fullname = Symbol.TypeSymbol.formFullName(name, typeSymbol);
            }
        }
        return classSymbol;
    }

    public Symbol.ModuleSymbol enterModule(Name name) {
        Symbol.ModuleSymbol moduleSymbol = this.modules.get(name);
        if (moduleSymbol != null) {
            return moduleSymbol;
        }
        Symbol.ModuleSymbol moduleSymbolCreate = Symbol.ModuleSymbol.create(name, this.names.module_info);
        addRootPackageFor(moduleSymbolCreate);
        moduleSymbolCreate.completer = new Symbol.Completer() { // from class: vyd
            @Override // com.sun.tools.javac.code.Symbol.Completer
            public final void complete(Symbol symbol) {
                this.b.moduleCompleter.complete(symbol);
            }
        };
        this.modules.put(name, moduleSymbolCreate);
        return moduleSymbolCreate;
    }

    public Symbol.PackageSymbol enterPackage(final Symbol.ModuleSymbol moduleSymbol, Name name) {
        Assert.checkNonNull(moduleSymbol);
        Symbol.PackageSymbol packageSymbol = getPackage(moduleSymbol, name);
        if (packageSymbol != null) {
            return packageSymbol;
        }
        Assert.check(name.length() != 0, (Supplier<String>) new Supplier() { // from class: myd
            @Override // java.util.function.Supplier
            public final Object get() {
                return Symtab.d(moduleSymbol);
            }
        });
        Symbol.PackageSymbol packageSymbol2 = new Symbol.PackageSymbol(Convert.shortName(name), enterPackage(moduleSymbol, Convert.packagePart(name)));
        packageSymbol2.completer = this.initialCompleter;
        packageSymbol2.modle = moduleSymbol;
        doEnterPackage(moduleSymbol, packageSymbol2);
        return packageSymbol2;
    }

    public Iterable<Symbol.ClassSymbol> getAllClasses() {
        return new Iterable() { // from class: pyd
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Iterators.createCompoundIterator(this.b.classes.values(), new Function() { // from class: wyd
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((Map) obj).values().iterator();
                    }
                });
            }
        };
    }

    public Collection<Symbol.ModuleSymbol> getAllModules() {
        return this.modules.values();
    }

    public Symbol.ClassSymbol getClass(Symbol.ModuleSymbol moduleSymbol, final Name name) {
        Objects.requireNonNull(name);
        Assert.checkNonNull(moduleSymbol, (Supplier<String>) new Supplier() { // from class: uyd
            @Override // java.util.function.Supplier
            public final Object get() {
                return name.toString();
            }
        });
        return this.classes.getOrDefault(name, Collections.EMPTY_MAP).get(moduleSymbol);
    }

    public Symbol.VarSymbol getClassField(final Type type, final Types types) {
        return this.classFields.computeIfAbsent(new Types.UniqueType(type, types), new Function() { // from class: qyd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Symtab.h(this.b, type, types, (Types.UniqueType) obj);
            }
        });
    }

    public Iterable<Symbol.ClassSymbol> getClassesForName(Name name) {
        return this.classes.getOrDefault(name, Collections.EMPTY_MAP).values();
    }

    public Symbol.ModuleSymbol getModule(Name name) {
        return this.modules.get(name);
    }

    public Symbol.PackageSymbol getPackage(Symbol.ModuleSymbol moduleSymbol, Name name) {
        return this.packages.getOrDefault(name, Collections.EMPTY_MAP).get(moduleSymbol);
    }

    public Iterable<Symbol.PackageSymbol> getPackagesForName(Name name) {
        return this.packages.getOrDefault(name, Collections.EMPTY_MAP).values();
    }

    public Symbol.ModuleSymbol inferModule(Name name) {
        if (name.length() == 0) {
            Symbol.ModuleSymbol moduleSymbol = this.java_base;
            Symbol.ModuleSymbol moduleSymbol2 = this.noModule;
            return moduleSymbol == moduleSymbol2 ? moduleSymbol2 : this.unnamedModule;
        }
        Map<Symbol.ModuleSymbol, Symbol.PackageSymbol> map = this.packages.get(name);
        if (map == null) {
            return null;
        }
        Symbol.ModuleSymbol key = null;
        for (Map.Entry<Symbol.ModuleSymbol, Symbol.PackageSymbol> entry : map.entrySet()) {
            if (!entry.getValue().members().isEmpty()) {
                if (key != null) {
                    return null;
                }
                key = entry.getKey();
            }
        }
        return key;
    }

    public void initType(Type type, String str, String str2) {
        initType(type, str);
        this.boxedName[type.getTag().ordinal()] = this.names.fromString("java.lang." + str2);
    }

    public List<Symbol.ModuleSymbol> listPackageModules(Name name) {
        if (name.length() == 0) {
            return List.nil();
        }
        List<Symbol.ModuleSymbol> listNil = List.nil();
        Map<Symbol.ModuleSymbol, Symbol.PackageSymbol> map = this.packages.get(name);
        if (map != null) {
            for (Map.Entry<Symbol.ModuleSymbol, Symbol.PackageSymbol> entry : map.entrySet()) {
                if (!entry.getValue().members().isEmpty()) {
                    listNil = listNil.prepend(entry.getKey());
                }
            }
        }
        return listNil;
    }

    public boolean packageExists(Symbol.ModuleSymbol moduleSymbol, Name name) {
        Assert.checkNonNull(moduleSymbol);
        Symbol.PackageSymbol packageSymbolLookupPackage = lookupPackage(moduleSymbol, name, true);
        return packageSymbolLookupPackage != null && packageSymbolLookupPackage.exists();
    }

    public void removeClass(Symbol.ModuleSymbol moduleSymbol, Name name) {
        this.classes.getOrDefault(name, EMPTY).remove(moduleSymbol);
    }

    public void synthesizeBoxTypeIfMissing(final Type type) {
        Symbol.ClassSymbol classSymbolEnterClass = enterClass(this.java_base, this.boxedName[type.getTag().ordinal()]);
        final Symbol.Completer completer = classSymbolEnterClass.completer;
        classSymbolEnterClass.completer = new Symbol.Completer(this) { // from class: com.sun.tools.javac.code.Symtab.2
            final /* synthetic */ Symtab this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.code.Symbol.Completer
            public void complete(Symbol symbol) throws Symbol.CompletionFailure {
                try {
                    completer.complete(symbol);
                } catch (Symbol.CompletionFailure unused) {
                    symbol.flags_field |= 1;
                    ((Type.ClassType) symbol.type).supertype_field = this.this$0.objectType;
                    symbol.members().enter(new Symbol.MethodSymbol(9L, this.this$0.names.valueOf, new Type.MethodType(List.of(type), symbol.type, List.nil(), this.this$0.methodClass), symbol));
                    symbol.members().enter(new Symbol.MethodSymbol(1L, type.tsym.name.append(this.this$0.names.Value), new Type.MethodType(List.nil(), type, List.nil(), this.this$0.methodClass), symbol));
                }
            }

            @Override // com.sun.tools.javac.code.Symbol.Completer
            public boolean isTerminal() {
                return completer.isTerminal();
            }
        };
    }

    public void synthesizeEmptyInterfaceIfMissing(Type type) {
        Symbol.TypeSymbol typeSymbol = type.tsym;
        final Symbol.Completer completer = typeSymbol.completer;
        typeSymbol.completer = new Symbol.Completer(this) { // from class: com.sun.tools.javac.code.Symtab.1
            final /* synthetic */ Symtab this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.code.Symbol.Completer
            public void complete(Symbol symbol) throws Symbol.CompletionFailure {
                try {
                    completer.complete(symbol);
                } catch (Symbol.CompletionFailure unused) {
                    symbol.flags_field |= 513;
                    ((Type.ClassType) symbol.type).supertype_field = this.this$0.objectType;
                }
            }

            @Override // com.sun.tools.javac.code.Symbol.Completer
            public boolean isTerminal() {
                return completer.isTerminal();
            }
        };
    }

    public void initType(Type type, String str) {
        initType(type, new Symbol.ClassSymbol(1L, this.names.fromString(str), type, this.rootPackage));
    }

    public void initType(Type type, Symbol.ClassSymbol classSymbol) {
        type.tsym = classSymbol;
        this.typeOfTag[type.getTag().ordinal()] = type;
    }

    private Type enterSyntheticAnnotation(String str) {
        return enterSyntheticAnnotation(this.names.fromString(str));
    }

    private Type enterClass(String str) {
        return enterClass(this.java_base, this.names.fromString(str)).type;
    }

    public Symbol.ClassSymbol enterClass(Symbol.ModuleSymbol moduleSymbol, Name name) {
        Assert.checkNonNull(moduleSymbol);
        Symbol.PackageSymbol packageSymbolLookupPackage = lookupPackage(moduleSymbol, Convert.packagePart(name));
        Assert.checkNonNull(packageSymbolLookupPackage);
        Assert.checkNonNull(packageSymbolLookupPackage.modle);
        Symbol.ClassSymbol classSymbol = getClass(packageSymbolLookupPackage.modle, name);
        if (classSymbol != null) {
            return classSymbol;
        }
        Symbol.ClassSymbol classSymbolDefineClass = defineClass(Convert.shortName(name), packageSymbolLookupPackage);
        doEnterClass(packageSymbolLookupPackage.modle, classSymbolDefineClass);
        return classSymbolDefineClass;
    }

    public Symbol.PackageSymbol lookupPackage(Symbol.ModuleSymbol moduleSymbol, Name name) {
        return lookupPackage(moduleSymbol, name, false);
    }
}
