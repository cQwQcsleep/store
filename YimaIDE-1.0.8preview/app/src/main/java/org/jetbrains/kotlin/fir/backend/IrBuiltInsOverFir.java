package org.jetbrains.kotlin.fir.backend;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.IrBuiltInsOverFir;
import org.jetbrains.kotlin.fir.descriptors.FirModuleDescriptor;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.IrBuiltInsOverSymbolFinder;
import org.jetbrains.kotlin.ir.builders.declarations.DeclarationBuildersKt;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOriginImpl;
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragmentsKt;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrAnnotationImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrTypeParameterSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.SimpleTypeNullability;
import org.jetbrains.kotlin.ir.types.impl.IrSimpleTypeImplKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010K\u001a\u00020\u00192\u0006\u0010L\u001a\u00020MH\u0002Je\u0010N\u001a\u00020'2\u0006\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010'2\u0006\u0010R\u001a\u00020S2\u001a\u0010T\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020S0V0U2\u000e\b\u0002\u0010W\u001a\b\u0012\u0004\u0012\u00020Y0X2\b\b\u0002\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]H\u0002¢\u0006\u0002\u0010^J:\u0010_\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%*\b\u0012\u0004\u0012\u00020`0X2\u0006\u0010O\u001a\u00020P2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u00020'0%H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R \u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010,R\u0014\u0010/\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010,R\u0014\u00101\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010,R\u0014\u00103\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010,R\u0014\u00105\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010,R\u0014\u00107\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010,R\u0014\u00109\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010,R\u0014\u0010;\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010,R\u0014\u0010=\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010,R\u0014\u0010?\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010,R\u0014\u0010A\u001a\u00020'X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010,R \u0010C\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010)R \u0010E\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010)R \u0010G\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010)R \u0010I\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010)¨\u0006b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/IrBuiltInsOverFir;", "Lorg/jetbrains/kotlin/ir/IrBuiltInsOverSymbolFinder;", "c", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "syntheticSymbolsContainer", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;)V", "moduleDescriptor", "Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "irFactory", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "getIrFactory", "()Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "fir2irBuiltins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "kotlinInternalPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "getKotlinInternalPackageFragment", "()Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "operatorsPackageFragment", "getOperatorsPackageFragment", "intrinsicConstAnnotation", "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "getIntrinsicConstAnnotation", "()Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "intrinsicConstAnnotation$delegate", "Lkotlin/Lazy;", "ieee754equalsFunByOperandType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "getIeee754equalsFunByOperandType", "()Ljava/util/Map;", "eqeqeqSymbol", "getEqeqeqSymbol", "()Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "eqeqSymbol", "getEqeqSymbol", "throwCceSymbol", "getThrowCceSymbol", "throwIseSymbol", "getThrowIseSymbol", "andandSymbol", "getAndandSymbol", "ororSymbol", "getOrorSymbol", "noWhenBranchMatchedExceptionSymbol", "getNoWhenBranchMatchedExceptionSymbol", "illegalArgumentExceptionSymbol", "getIllegalArgumentExceptionSymbol", "dataClassArrayMemberHashCodeSymbol", "getDataClassArrayMemberHashCodeSymbol", "dataClassArrayMemberToStringSymbol", "getDataClassArrayMemberToStringSymbol", "checkNotNullSymbol", "getCheckNotNullSymbol", "linkageErrorSymbol", "getLinkageErrorSymbol", "lessFunByOperandType", "getLessFunByOperandType", "lessOrEqualFunByOperandType", "getLessOrEqualFunByOperandType", "greaterOrEqualFunByOperandType", "getGreaterOrEqualFunByOperandType", "greaterFunByOperandType", "getGreaterFunByOperandType", "createPackage", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "createFunction", ModuleXmlParser.NAME, Argument.Delimiters.none, "symbol", "returnType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "valueParameterTypes", Argument.Delimiters.none, "Lkotlin/Pair;", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "isIntrinsicConst", Argument.Delimiters.none, "(Ljava/lang/String;Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;Lorg/jetbrains/kotlin/ir/types/IrType;[Lkotlin/Pair;Ljava/util/List;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;Z)Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "defineComparisonOperatorForEachIrType", "Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "symbols", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrBuiltInsOverFir extends IrBuiltInsOverSymbolFinder {
    private final IrSimpleFunctionSymbol andandSymbol;
    private final Fir2IrComponents c;
    private final IrSimpleFunctionSymbol checkNotNullSymbol;
    private final IrSimpleFunctionSymbol dataClassArrayMemberHashCodeSymbol;
    private final IrSimpleFunctionSymbol dataClassArrayMemberToStringSymbol;
    private final IrSimpleFunctionSymbol eqeqSymbol;
    private final IrSimpleFunctionSymbol eqeqeqSymbol;
    private final Fir2IrBuiltinSymbolsContainer fir2irBuiltins;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> greaterFunByOperandType;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> greaterOrEqualFunByOperandType;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> ieee754equalsFunByOperandType;
    private final IrSimpleFunctionSymbol illegalArgumentExceptionSymbol;

    /* JADX INFO: renamed from: intrinsicConstAnnotation$delegate, reason: from kotlin metadata */
    private final Lazy intrinsicConstAnnotation;
    private final IrFactory irFactory;
    private final IrExternalPackageFragment kotlinInternalPackageFragment;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> lessFunByOperandType;
    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> lessOrEqualFunByOperandType;
    private final IrSimpleFunctionSymbol linkageErrorSymbol;
    private final FirModuleDescriptor moduleDescriptor;
    private final IrSimpleFunctionSymbol noWhenBranchMatchedExceptionSymbol;
    private final IrExternalPackageFragment operatorsPackageFragment;
    private final IrSimpleFunctionSymbol ororSymbol;
    private final Fir2IrSyntheticIrBuiltinsSymbolsContainer syntheticSymbolsContainer;
    private final IrSimpleFunctionSymbol throwCceSymbol;
    private final IrSimpleFunctionSymbol throwIseSymbol;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrBuiltInsOverFir(Fir2IrComponents fir2IrComponents, Fir2IrSyntheticIrBuiltinsSymbolsContainer fir2IrSyntheticIrBuiltinsSymbolsContainer) {
        FirModuleData moduleData;
        super(new SymbolFinderOverFir(fir2IrComponents.getBuiltins()));
        fir2IrComponents.getClass();
        fir2IrSyntheticIrBuiltinsSymbolsContainer.getClass();
        this.c = fir2IrComponents;
        this.syntheticSymbolsContainer = fir2IrSyntheticIrBuiltinsSymbolsContainer;
        FirSession session = fir2IrComponents.getSession();
        boolean zBooleanValue = ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).getFlag(AnalysisFlags.getStdlibCompilation())).booleanValue();
        if (!zBooleanValue) {
            moduleData = (FirModuleData) CollectionsKt.first(FirModuleDataKt.getModuleData(session).getDependencies());
        } else {
            if (!zBooleanValue) {
                bu8.a();
                throw null;
            }
            moduleData = FirModuleDataKt.getModuleData(session);
        }
        this.moduleDescriptor = fir2IrComponents.getDeclarationStorage().getDependenciesModuleDescriptor(moduleData);
        this.irFactory = IrFactoryImpl.INSTANCE;
        this.fir2irBuiltins = fir2IrComponents.getBuiltins();
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        this.kotlinInternalPackageFragment = createPackage(standardClassIds.getBASE_INTERNAL_PACKAGE());
        this.operatorsPackageFragment = createPackage(standardClassIds.getBASE_INTERNAL_IR_PACKAGE());
        this.intrinsicConstAnnotation = LazyKt.lazy(new Function0() { // from class: xx6
            public final Object invoke() {
                return IrBuiltInsOverFir.e(this.b);
            }
        });
        List<PrimitiveType> primitiveFloatingPointTypes = fir2IrSyntheticIrBuiltinsSymbolsContainer.getPrimitiveFloatingPointTypes();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(primitiveFloatingPointTypes, 10)), 16));
        for (PrimitiveType primitiveType : primitiveFloatingPointTypes) {
            IrType irType = (IrType) MapsKt.getValue(getPrimitiveTypeToIrType(), primitiveType);
            Pair pair = TuplesKt.to(IrTypesKt.getClassifierOrFail(irType), createFunction$default(this, "ieee754equals", (IrSimpleFunctionSymbol) MapsKt.getValue(this.syntheticSymbolsContainer.getIeee754equalsFunByOperandType(), primitiveType), getBooleanType(), new Pair[]{TuplesKt.to("arg0", IrTypesKt.makeNullable(irType)), TuplesKt.to("arg1", IrTypesKt.makeNullable(irType))}, null, null, true, 48, null));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        this.ieee754equalsFunByOperandType = linkedHashMap;
        this.eqeqeqSymbol = createFunction$default(this, "EQEQEQ", this.syntheticSymbolsContainer.getEqeqeqSymbol(), getBooleanType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getAnyNType()), TuplesKt.to(Argument.Delimiters.none, getAnyNType())}, null, null, false, 48, null);
        this.eqeqSymbol = createFunction$default(this, "EQEQ", this.syntheticSymbolsContainer.getEqeqSymbol(), getBooleanType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getAnyNType()), TuplesKt.to(Argument.Delimiters.none, getAnyNType())}, null, null, true, 48, null);
        this.throwCceSymbol = createFunction$default(this, "THROW_CCE", null, getNothingType(), new Pair[0], null, null, false, 48, null);
        this.throwIseSymbol = createFunction$default(this, "THROW_ISE", null, getNothingType(), new Pair[0], null, null, false, 48, null);
        this.andandSymbol = createFunction$default(this, "ANDAND", null, getBooleanType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getBooleanType()), TuplesKt.to(Argument.Delimiters.none, getBooleanType())}, null, null, true, 48, null);
        this.ororSymbol = createFunction$default(this, "OROR", null, getBooleanType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getBooleanType()), TuplesKt.to(Argument.Delimiters.none, getBooleanType())}, null, null, true, 48, null);
        this.noWhenBranchMatchedExceptionSymbol = createFunction$default(this, "noWhenBranchMatchedException", this.syntheticSymbolsContainer.getNoWhenBranchMatchedExceptionSymbol(), getNothingType(), new Pair[0], null, null, false, 48, null);
        this.illegalArgumentExceptionSymbol = createFunction$default(this, "illegalArgumentException", null, getNothingType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getStringType())}, null, null, false, 48, null);
        this.dataClassArrayMemberHashCodeSymbol = createFunction$default(this, "dataClassArrayMemberHashCode", null, getIntType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getAnyType())}, null, null, false, 48, null);
        this.dataClassArrayMemberToStringSymbol = createFunction$default(this, "dataClassArrayMemberToString", null, getStringType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getAnyNType())}, null, null, false, 48, null);
        IrFactory irFactory = getIrFactory();
        IrBuiltIns.Companion companion = IrBuiltIns.Companion;
        IrDeclarationOriginImpl builtin_operator = companion.getBUILTIN_OPERATOR();
        Name nameIdentifier = Name.identifier("T0");
        nameIdentifier.getClass();
        IrTypeParameter irTypeParameterCreateTypeParameter = irFactory.createTypeParameter(-1, -1, builtin_operator, nameIdentifier, new IrTypeParameterSymbolImpl((TypeParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), Variance.INVARIANT, 0, true);
        irTypeParameterCreateTypeParameter.setSuperTypes(CollectionsKt.listOf(getAnyType()));
        this.checkNotNullSymbol = createFunction("CHECK_NOT_NULL", this.syntheticSymbolsContainer.getCheckNotNullSymbol(), IrSimpleTypeImplKt.IrSimpleTypeImpl$default(irTypeParameterCreateTypeParameter.getSymbol(), SimpleTypeNullability.DEFINITELY_NOT_NULL, CollectionsKt.emptyList(), CollectionsKt.emptyList(), (KotlinType) null, 16, (Object) null), new Pair[]{TuplesKt.to(Argument.Delimiters.none, IrSimpleTypeImplKt.IrSimpleTypeImpl(irTypeParameterCreateTypeParameter.getSymbol(), true, CollectionsKt.emptyList(), CollectionsKt.emptyList()))}, CollectionsKt.listOf(irTypeParameterCreateTypeParameter), companion.getBUILTIN_OPERATOR(), false);
        this.linkageErrorSymbol = createFunction$default(this, "linkageErrorSymbol", null, getNothingType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, getAnyNType())}, null, null, false, 48, null);
        this.lessFunByOperandType = defineComparisonOperatorForEachIrType(this.syntheticSymbolsContainer.getPrimitiveIrTypesWithComparisons(), "less", this.syntheticSymbolsContainer.getLessFunByOperandType());
        this.lessOrEqualFunByOperandType = defineComparisonOperatorForEachIrType(this.syntheticSymbolsContainer.getPrimitiveIrTypesWithComparisons(), "lessOrEqual", this.syntheticSymbolsContainer.getLessOrEqualFunByOperandType());
        this.greaterOrEqualFunByOperandType = defineComparisonOperatorForEachIrType(this.syntheticSymbolsContainer.getPrimitiveIrTypesWithComparisons(), "greaterOrEqual", this.syntheticSymbolsContainer.getGreaterOrEqualFunByOperandType());
        this.greaterFunByOperandType = defineComparisonOperatorForEachIrType(this.syntheticSymbolsContainer.getPrimitiveIrTypesWithComparisons(), "greater", this.syntheticSymbolsContainer.getGreaterFunByOperandType());
    }

    private final IrSimpleFunctionSymbol createFunction(String name, IrSimpleFunctionSymbol symbol, IrType returnType, Pair<String, ? extends IrType>[] valueParameterTypes, List<? extends IrTypeParameter> typeParameters, IrDeclarationOrigin origin, boolean isIntrinsicConst) {
        IrFactory irFactory = getIrFactory();
        Name nameIdentifier = Name.identifier(name);
        nameIdentifier.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PUBLIC;
        descriptorVisibility.getClass();
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction = irFactory.createSimpleFunction(-1, -1, origin, nameIdentifier, descriptorVisibility, false, false, returnType, Modality.FINAL, symbol == null ? new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null) : symbol, false, false, false, false, false, (DeserializedContainerSource) null, false);
        int length = valueParameterTypes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Pair<String, ? extends IrType> pair = valueParameterTypes[i];
            int i3 = i2 + 1;
            String str = (String) pair.component1();
            IrType irType = (IrType) pair.component2();
            if (StringsKt.isBlank(str)) {
                str = "arg" + i2;
            }
            Name nameIdentifier2 = Name.identifier(str);
            nameIdentifier2.getClass();
            DeclarationBuildersKt.addValueParameter(irSimpleFunctionCreateSimpleFunction, nameIdentifier2, irType, origin);
            i++;
            i2 = i3;
        }
        irSimpleFunctionCreateSimpleFunction.setTypeParameters(typeParameters);
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            ((IrTypeParameter) it.next()).setParent(irSimpleFunctionCreateSimpleFunction);
        }
        if (isIntrinsicConst) {
            irSimpleFunctionCreateSimpleFunction.setAnnotations(CollectionsKt.plus(irSimpleFunctionCreateSimpleFunction.getAnnotations(), getIntrinsicConstAnnotation()));
        }
        irSimpleFunctionCreateSimpleFunction.setParent(getOperatorsPackageFragment());
        getOperatorsPackageFragment().getDeclarations().add(irSimpleFunctionCreateSimpleFunction);
        return irSimpleFunctionCreateSimpleFunction.getSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrSimpleFunctionSymbol createFunction$default(IrBuiltInsOverFir irBuiltInsOverFir, String str, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrType irType, Pair[] pairArr, List list, IrDeclarationOrigin irDeclarationOrigin, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list2 = list;
        if ((i & 32) != 0) {
            irDeclarationOrigin = IrBuiltIns.Companion.getBUILTIN_OPERATOR();
        }
        return irBuiltInsOverFir.createFunction(str, irSimpleFunctionSymbol, irType, pairArr, list2, irDeclarationOrigin, z);
    }

    private final IrExternalPackageFragment createPackage(FqName fqName) {
        return IrPackageFragmentsKt.createEmptyExternalPackageFragment(this.moduleDescriptor, fqName);
    }

    private final Map<IrClassifierSymbol, IrSimpleFunctionSymbol> defineComparisonOperatorForEachIrType(List<? extends PrimitiveType> list, String str, Map<PrimitiveType, ? extends IrSimpleFunctionSymbol> map) {
        List<? extends PrimitiveType> list2 = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
        for (PrimitiveType primitiveType : list2) {
            IrType irType = (IrType) MapsKt.getValue(getPrimitiveTypeToIrType(), primitiveType);
            Pair pair = TuplesKt.to(IrTypesKt.getClassifierOrFail(irType), createFunction$default(this, str, (IrSimpleFunctionSymbol) MapsKt.getValue(map, primitiveType), getBooleanType(), new Pair[]{TuplesKt.to(Argument.Delimiters.none, irType), TuplesKt.to(Argument.Delimiters.none, irType)}, null, null, true, 48, null));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    public static IrAnnotationImpl e(IrBuiltInsOverFir irBuiltInsOverFir) {
        FirSession session = irBuiltInsOverFir.getSession();
        StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
        IrClassSymbol symbol = FirSymbolProviderKt.getRegularClassSymbolByClassId(session, standardClassIds$Annotations.getIntrinsicConstEvaluation()) == null ? irBuiltInsOverFir.createIntrinsicConstEvaluationClass().getSymbol() : irBuiltInsOverFir.fir2irBuiltins.loadClass$org_jetbrains_kotlin_fir2ir(standardClassIds$Annotations.getIntrinsicConstEvaluation());
        return BuildersKt.IrAnnotationImpl$default(-1, -1, IrSimpleTypeImplKt.IrSimpleTypeImpl$default(symbol, SimpleTypeNullability.DEFINITELY_NOT_NULL, CollectionsKt.emptyList(), CollectionsKt.emptyList(), (KotlinType) null, 16, (Object) null), ((IrConstructor) SequencesKt.single(IrUtilsKt.getConstructors(symbol.getOwner()))).getSymbol(), 0, 0, (IrStatementOrigin) null, (SourceElement) null, 192, (Object) null);
    }

    private final IrAnnotation getIntrinsicConstAnnotation() {
        return (IrAnnotation) this.intrinsicConstAnnotation.getValue();
    }

    private final FirSession getSession() {
        return this.c.getSession();
    }

    public IrSimpleFunctionSymbol getAndandSymbol() {
        return this.andandSymbol;
    }

    public IrSimpleFunctionSymbol getCheckNotNullSymbol() {
        return this.checkNotNullSymbol;
    }

    public IrSimpleFunctionSymbol getDataClassArrayMemberHashCodeSymbol() {
        return this.dataClassArrayMemberHashCodeSymbol;
    }

    public IrSimpleFunctionSymbol getDataClassArrayMemberToStringSymbol() {
        return this.dataClassArrayMemberToStringSymbol;
    }

    public IrSimpleFunctionSymbol getEqeqSymbol() {
        return this.eqeqSymbol;
    }

    public IrSimpleFunctionSymbol getEqeqeqSymbol() {
        return this.eqeqeqSymbol;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getGreaterFunByOperandType() {
        return this.greaterFunByOperandType;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getGreaterOrEqualFunByOperandType() {
        return this.greaterOrEqualFunByOperandType;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getIeee754equalsFunByOperandType() {
        return this.ieee754equalsFunByOperandType;
    }

    public IrSimpleFunctionSymbol getIllegalArgumentExceptionSymbol() {
        return this.illegalArgumentExceptionSymbol;
    }

    public IrFactory getIrFactory() {
        return this.irFactory;
    }

    public IrExternalPackageFragment getKotlinInternalPackageFragment() {
        return this.kotlinInternalPackageFragment;
    }

    public LanguageVersionSettings getLanguageVersionSettings() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession());
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getLessFunByOperandType() {
        return this.lessFunByOperandType;
    }

    public Map<IrClassifierSymbol, IrSimpleFunctionSymbol> getLessOrEqualFunByOperandType() {
        return this.lessOrEqualFunByOperandType;
    }

    public IrSimpleFunctionSymbol getLinkageErrorSymbol() {
        return this.linkageErrorSymbol;
    }

    public IrSimpleFunctionSymbol getNoWhenBranchMatchedExceptionSymbol() {
        return this.noWhenBranchMatchedExceptionSymbol;
    }

    public IrExternalPackageFragment getOperatorsPackageFragment() {
        return this.operatorsPackageFragment;
    }

    public IrSimpleFunctionSymbol getOrorSymbol() {
        return this.ororSymbol;
    }

    public IrSimpleFunctionSymbol getThrowCceSymbol() {
        return this.throwCceSymbol;
    }

    public IrSimpleFunctionSymbol getThrowIseSymbol() {
        return this.throwIseSymbol;
    }
}
