package org.jetbrains.kotlin.fir.backend.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrCommonMemberStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.builders.IrGeneratorContextBase;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.SymbolTable;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.DataClassResolver;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator;", Argument.Delimiters.none, "irBuiltins", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "<init>", "(Lorg/jetbrains/kotlin/ir/IrBuiltIns;)V", "generateBodiesForClassesWithSyntheticDataClassMembers", Argument.Delimiters.none, "members", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage$DataValueClassGeneratedMembersInfo;", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "MyDataClassMethodsGenerator", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrDataClassGeneratedMemberBodyGenerator {
    private final IrBuiltIns irBuiltins;

    @Metadata(d1 = {"\u0000\u009f\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001d\b\u0082\u0004\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018J\f\u0010\u001a\u001a\u00020\u001b*\u00020\u001bH\u0002R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u0012\u0010\u001f\u001a\u00020 X\u0096\u0005¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0012\u0010#\u001a\u00020$X\u0096\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0012\u0010'\u001a\u00020(X\u0096\u0005¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u0010+\u001a\u00020,X\u0096\u0005¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0012\u0010/\u001a\u000200X\u0096\u0005¢\u0006\u0006\u001a\u0004\b1\u00102R\u0012\u00103\u001a\u000204X\u0096\u0005¢\u0006\u0006\u001a\u0004\b5\u00106R\u0012\u00107\u001a\u000208X\u0096\u0005¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0012\u0010;\u001a\u00020<X\u0096\u0005¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020@X\u0096\u0005¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0012\u0010C\u001a\u00020DX\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0012\u0010G\u001a\u00020HX\u0096\u0005¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0012\u0010K\u001a\u00020LX\u0096\u0005¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0012\u0010O\u001a\u00020PX\u0096\u0005¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u001a\u0010S\u001a\n\u0012\u0004\u0012\u00020U\u0018\u00010TX\u0096\u0005¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0012\u0010X\u001a\u00020YX\u0096\u0005¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0012\u0010\\\u001a\u00020]X\u0096\u0005¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0012\u0010`\u001a\u00020aX\u0096\u0005¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0018\u0010d\u001a\b\u0012\u0004\u0012\u00020e0\u0018X\u0096\u0005¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0012\u0010h\u001a\u00020iX\u0096\u0005¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0012\u0010l\u001a\u00020mX\u0096\u0005¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0012\u0010p\u001a\u00020qX\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010sR\u0012\u0010t\u001a\u00020uX\u0096\u0005¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0012\u0010x\u001a\u00020yX\u0096\u0005¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0014\u0010|\u001a\u0004\u0018\u00010}X\u0096\u0005¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001¨\u0006\u008c\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "symbolTable", "Lorg/jetbrains/kotlin/ir/util/SymbolTable;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator;Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Lorg/jetbrains/kotlin/ir/util/SymbolTable;)V", "getC", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "getIrClass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getKlass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getOrigin", "()Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "generateBodies", Argument.Delimiters.none, "functions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "coerceToAny", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "irDataClassMembersGenerator", "org/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class MyDataClassMethodsGenerator implements Fir2IrComponents {
        private final Fir2IrComponents c;
        private final IrClass irClass;
        private final Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1 irDataClassMembersGenerator;
        private final FirRegularClass klass;
        private final IrDeclarationOrigin origin;
        final /* synthetic */ Fir2IrDataClassGeneratedMemberBodyGenerator this$0;

        public MyDataClassMethodsGenerator(Fir2IrDataClassGeneratedMemberBodyGenerator fir2IrDataClassGeneratedMemberBodyGenerator, Fir2IrComponents fir2IrComponents, IrClass irClass, FirRegularClass firRegularClass, IrDeclarationOrigin irDeclarationOrigin, SymbolTable symbolTable) {
            fir2IrComponents.getClass();
            irClass.getClass();
            firRegularClass.getClass();
            irDeclarationOrigin.getClass();
            symbolTable.getClass();
            this.this$0 = fir2IrDataClassGeneratedMemberBodyGenerator;
            this.c = fir2IrComponents;
            this.irClass = irClass;
            this.klass = firRegularClass;
            this.origin = irDeclarationOrigin;
            this.irDataClassMembersGenerator = new Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1(symbolTable, this, new IrGeneratorContextBase(fir2IrDataClassGeneratedMemberBodyGenerator.irBuiltins), irClass, AdditionalIrUtilsKt.getKotlinFqName(irClass), irDeclarationOrigin, !getConfiguration().getSkipBodies());
        }

        public static boolean b(IrProperty irProperty) {
            irProperty.getClass();
            return irProperty.getBackingField() != null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ConeKotlinType coerceToAny(ConeKotlinType coneKotlinType) {
            return (ConeBuiltinTypeUtilsKt.isNothingOrNullableNothing(coneKotlinType) || (coneKotlinType instanceof ConeDynamicType)) ? getSession().getBuiltinTypes().getAnyType().getConeType() : coneKotlinType;
        }

        public final void generateBodies(List<? extends IrSimpleFunction> functions) {
            List parameters;
            functions.getClass();
            IrConstructor primaryConstructor = IrUtilsKt.getPrimaryConstructor(this.irClass);
            List list = SequencesKt.toList(SequencesKt.take(SequencesKt.filter(IrUtilsKt.getProperties(this.irClass), new Function1() { // from class: org.jetbrains.kotlin.fir.backend.generators.a
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Fir2IrDataClassGeneratedMemberBodyGenerator.MyDataClassMethodsGenerator.b((IrProperty) obj));
                }
            }), (primaryConstructor == null || (parameters = primaryConstructor.getParameters()) == null) ? 0 : parameters.size()));
            IrConstructor primaryConstructor2 = IrUtilsKt.getPrimaryConstructor(this.irClass);
            primaryConstructor2.getClass();
            List parameters2 = primaryConstructor2.getParameters();
            ArrayList arrayList = new ArrayList();
            for (Object obj : parameters2) {
                if (((IrValueParameter) obj).getKind() == IrParameterKind.Regular) {
                    arrayList.add(obj);
                }
            }
            for (IrSimpleFunction irSimpleFunction : functions) {
                Name name = irSimpleFunction.getName();
                if (Intrinsics.areEqual(name, OperatorNameConventions.TO_STRING)) {
                    this.irDataClassMembersGenerator.generateToStringMethod(irSimpleFunction, list);
                } else if (Intrinsics.areEqual(name, StandardNames.HASHCODE_NAME)) {
                    this.irDataClassMembersGenerator.generateHashCodeMethod(irSimpleFunction, list);
                } else if (Intrinsics.areEqual(name, OperatorNameConventions.EQUALS)) {
                    this.irDataClassMembersGenerator.generateEqualsMethod(irSimpleFunction, list);
                } else if (Intrinsics.areEqual(name, StandardNames.DATA_CLASS_COPY)) {
                    irSimpleFunction.setOrigin(IrDeclarationOrigin.Companion.getGENERATED_DATA_CLASS_MEMBER());
                    Fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1 fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1 = this.irDataClassMembersGenerator;
                    IrConstructor primaryConstructor3 = IrUtilsKt.getPrimaryConstructor(this.irClass);
                    primaryConstructor3.getClass();
                    fir2IrDataClassGeneratedMemberBodyGenerator$MyDataClassMethodsGenerator$irDataClassMembersGenerator$1.generateCopyFunction(irSimpleFunction, primaryConstructor3.getSymbol());
                } else {
                    DataClassResolver dataClassResolver = DataClassResolver.INSTANCE;
                    if (!dataClassResolver.isComponentLike(name)) {
                        dt1.a("Unknown data class member: ", name);
                        return;
                    }
                    irSimpleFunction.setOrigin(IrDeclarationOrigin.Companion.getGENERATED_DATA_CLASS_MEMBER());
                    String strAsString = irSimpleFunction.getName().asString();
                    strAsString.getClass();
                    this.irDataClassMembersGenerator.generateComponentFunction(irSimpleFunction, this.irDataClassMembersGenerator.getProperty((IrValueParameter) arrayList.get(dataClassResolver.getComponentIndex(strAsString) - 1)));
                }
            }
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public AdapterGenerator getAdapterGenerator() {
            return this.c.getAdapterGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public AnnotationGenerator getAnnotationGenerator() {
            return this.c.getAnnotationGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
            return this.c.getAnnotationsFromPluginRegistrar();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrBuiltinSymbolsContainer getBuiltins() {
            return this.c.getBuiltins();
        }

        public final Fir2IrComponents getC() {
            return this.c;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public CallAndReferenceGenerator getCallGenerator() {
            return this.c.getCallGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
            return this.c.getCallablesGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrClassifierStorage getClassifierStorage() {
            return this.c.getClassifierStorage();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrClassifiersGenerator getClassifiersGenerator() {
            return this.c.getClassifiersGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrConfiguration getConfiguration() {
            return this.c.getConfiguration();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrConverter getConverter() {
            return this.c.getConverter();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
            return this.c.getDataClassMembersGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrDeclarationStorage getDeclarationStorage() {
            return this.c.getDeclarationStorage();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrExtensions getExtensions() {
            return this.c.getExtensions();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Set<FirFile> getFilesBeingCompiled() {
            return this.c.getFilesBeingCompiled();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public FirProviderWithGeneratedFiles getFirProvider() {
            return this.c.getFirProvider();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrImplicitCastInserter getImplicitCastInserter() {
            return this.c.getImplicitCastInserter();
        }

        public final IrClass getIrClass() {
            return this.irClass;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public KotlinMangler.IrMangler getIrMangler() {
            return this.c.getIrMangler();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public List<IrProvider> getIrProviders() {
            return this.c.getIrProviders();
        }

        public final FirRegularClass getKlass() {
            return this.klass;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
            return this.c.getLazyDeclarationsGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
            return this.c.getLazyFakeOverrideGenerator();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public IrLock getLock() {
            return this.c.getLock();
        }

        public final IrDeclarationOrigin getOrigin() {
            return this.origin;
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
        public ScopeSession getScopeSession() {
            return this.c.getScopeSession();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
        public FirSession getSession() {
            return this.c.getSession();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
            return this.c.getSpecialAnnotationsProvider();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
            return this.c.getSymbolsMappingForLazyClasses();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrTypeConverter getTypeConverter() {
            return this.c.getTypeConverter();
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
        public Fir2IrVisibilityConverter getVisibilityConverter() {
            return this.c.getVisibilityConverter();
        }
    }

    public Fir2IrDataClassGeneratedMemberBodyGenerator(IrBuiltIns irBuiltIns) {
        irBuiltIns.getClass();
        this.irBuiltins = irBuiltIns;
    }

    public final void generateBodiesForClassesWithSyntheticDataClassMembers(Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> members, SymbolTable symbolTable) {
        members.getClass();
        symbolTable.getClass();
        for (Map.Entry<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> entry : members.entrySet()) {
            IrClass key = entry.getKey();
            Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo value = entry.getValue();
            Fir2IrComponents components = value.getComponents();
            FirRegularClass firClass = value.getFirClass();
            IrDeclarationOrigin origin = value.getOrigin();
            new MyDataClassMethodsGenerator(this, components, key, firClass, origin, symbolTable).generateBodies(value.component4());
        }
    }
}
