package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindService;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000 42\u00020\u0001:\u00014B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u0017H\u0002J.\u0010\u001a\u001a\u00020\u001b2\u0010\u0010\u001c\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0017b\u0002\b#J*\u0010$\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0017b\u0002\b#J*\u0010&\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020'0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0017b\u0002\b#J\u0012\u0010(\u001a\b\u0012\u0004\u0012\u00020 0)H\u0007b\u0002\b#J\u0010\u0010*\u001a\u00020\t2\u0006\u0010+\u001a\u00020 H\u0016J\f\u00100\u001a\u00020\t*\u00020\u0019H$J\u001a\u00101\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u0019H\u0014J\u0014\u0010\u0016\u001a\u00020\u0017*\u00020\u00192\u0006\u00102\u001a\u000203H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R@\u0010,\u001a4\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b.\u0012\b\b!\u0012\u0004\b\b(\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b.\u0012\b\b!\u0012\u0004\b\b(/0-X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirSyntheticFunctionInterfaceProviderBase;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "originateFromFallbackBuiltIns", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Z)V", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getKotlinScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getAcceptableFunctionTypeKind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getFunctionKindPackageNames", Argument.Delimiters.none, "hasPackage", "fqName", "cache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lkotlin/ParameterName;", "kind", "isAcceptable", "createSyntheticFunctionInterface", "arity", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSyntheticFunctionInterfaceProviderBase extends FirSymbolProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirCache<ClassId, FirRegularClassSymbol, FunctionTypeKind> cache;
    private final FirKotlinScopeProvider kotlinScopeProvider;
    private final FirModuleData moduleData;
    private final boolean originateFromFallbackBuiltIns;
    private final FirSymbolNamesProvider symbolNamesProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirSyntheticFunctionInterfaceProviderBase(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, boolean z) {
        super(firSession);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
        this.moduleData = firModuleData;
        this.kotlinScopeProvider = firKotlinScopeProvider;
        this.originateFromFallbackBuiltIns = z;
        this.symbolNamesProvider = new FirSymbolNamesProvider() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirSyntheticFunctionInterfaceProviderBase$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificCallablePackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getMayHaveSyntheticFunctionTypes() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNames() {
                return SetsKt.emptySet();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                return SetsKt.emptySet();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                return SetsKt.emptySet();
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean mayHaveSyntheticFunctionType(ClassId classId) {
                classId.getClass();
                return this.this$0.getAcceptableFunctionTypeKind(classId) != null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean mayHaveTopLevelCallable(FqName packageFqName, Name name) {
                packageFqName.getClass();
                name.getClass();
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean mayHaveTopLevelClassifier(ClassId classId) {
                classId.getClass();
                return mayHaveSyntheticFunctionType(classId);
            }
        };
        this.cache = FirCachesFactoryKt.getFirCachesFactory(firModuleData.getSession()).createCache(new FirSyntheticFunctionInterfaceProviderBase$cache$1(this));
    }

    private final ClassId classId(FunctionTypeKind functionTypeKind, int i) {
        return new ClassId(functionTypeKind.getPackageFqName(), functionTypeKind.numberedClassName(i));
    }

    private static final FirResolvedTypeRef createSyntheticFunctionInterface$lambda$0$0$0$createSuperType(FirSyntheticFunctionInterfaceProviderBase firSyntheticFunctionInterfaceProviderBase, int i, List<? extends FirResolvedTypeRef> list, FunctionTypeKind functionTypeKind) {
        ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(firSyntheticFunctionInterfaceProviderBase.classId(functionTypeKind, i));
        List<? extends FirResolvedTypeRef> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
        }
        return UtilsKt.toFirResolvedTypeRef$default(TypeConstructionUtilsKt.constructClassType$default(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeKotlinType[0]), false, null, 6, null), null, null, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FunctionTypeKind getAcceptableFunctionTypeKind(ClassId classId) {
        FunctionTypeKind functionTypeKind = INSTANCE.getFunctionTypeKind(classId, getSession());
        if (functionTypeKind == null || !isAcceptable(functionTypeKind)) {
            return null;
        }
        return functionTypeKind;
    }

    public FirRegularClassSymbol createSyntheticFunctionInterface(ClassId classId, FunctionTypeKind kind) {
        classId.getClass();
        kind.getClass();
        FirDeclarationOrigin firDeclarationOrigin = this.originateFromFallbackBuiltIns ? FirDeclarationOrigin.BuiltInsFallback.INSTANCE : FirDeclarationOrigin.BuiltIns.INSTANCE;
        if (!isAcceptable(kind)) {
            return null;
        }
        Integer arityIfAllowedOrNull = INSTANCE.getArityIfAllowedOrNull(classId, kind);
        int iIntValue = arityIfAllowedOrNull != null ? arityIfAllowedOrNull.intValue() : 0;
        FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(classId);
        FirRegularClassBuilder firRegularClassBuilder = new FirRegularClassBuilder();
        firRegularClassBuilder.setModuleData(this.moduleData);
        firRegularClassBuilder.setOrigin(firDeclarationOrigin);
        firRegularClassBuilder.setName(classId.getRelativeClassName().shortName());
        firRegularClassBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.ABSTRACT, EffectiveVisibility.Public.INSTANCE));
        firRegularClassBuilder.setClassKind(ClassKind.INTERFACE);
        firRegularClassBuilder.setScopeProvider(this.kotlinScopeProvider);
        firRegularClassBuilder.setSymbol(firRegularClassSymbol);
        firRegularClassBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeParameterRef> typeParameters = firRegularClassBuilder.getTypeParameters();
        IntRange intRange = new IntRange(1, iIntValue);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        IntIterator it = intRange.iterator();
        while (it.hasNext()) {
            int iNextInt = it.nextInt();
            FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
            firTypeParameterBuilder.setModuleData(this.moduleData);
            firTypeParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
            firTypeParameterBuilder.setOrigin(firDeclarationOrigin);
            Name nameIdentifier = Name.identifier("P" + iNextInt);
            nameIdentifier.getClass();
            firTypeParameterBuilder.setName(nameIdentifier);
            firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
            firTypeParameterBuilder.setContainingDeclarationSymbol(firRegularClassSymbol);
            firTypeParameterBuilder.setVariance(Variance.IN_VARIANCE);
            firTypeParameterBuilder.setReified(false);
            firTypeParameterBuilder.getBounds().add(firTypeParameterBuilder.getModuleData().getSession().getBuiltinTypes().getNullableAnyType());
            arrayList.add(firTypeParameterBuilder.mo289build());
        }
        typeParameters.addAll(arrayList);
        List<FirTypeParameterRef> typeParameters2 = firRegularClassBuilder.getTypeParameters();
        FirTypeParameterBuilder firTypeParameterBuilder2 = new FirTypeParameterBuilder();
        firTypeParameterBuilder2.setModuleData(this.moduleData);
        firTypeParameterBuilder2.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        firTypeParameterBuilder2.setOrigin(firDeclarationOrigin);
        Name nameIdentifier2 = Name.identifier("R");
        nameIdentifier2.getClass();
        firTypeParameterBuilder2.setName(nameIdentifier2);
        firTypeParameterBuilder2.setSymbol(new FirTypeParameterSymbol());
        firTypeParameterBuilder2.setContainingDeclarationSymbol(firRegularClassSymbol);
        firTypeParameterBuilder2.setVariance(Variance.OUT_VARIANCE);
        firTypeParameterBuilder2.setReified(false);
        firTypeParameterBuilder2.getBounds().add(firTypeParameterBuilder2.getModuleData().getSession().getBuiltinTypes().getNullableAnyType());
        typeParameters2.add(firTypeParameterBuilder2.mo289build());
        Name name = OperatorNameConventions.INVOKE;
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.ABSTRACT, EffectiveVisibility.Public.INSTANCE);
        firResolvedDeclarationStatusImpl.setOperator(true);
        firResolvedDeclarationStatusImpl.setSuspend(FunctionTypeKindKt.isSuspendOrKSuspendFunction(kind));
        firResolvedDeclarationStatusImpl.setHasStableParameterNames(false);
        firResolvedDeclarationStatusImpl.setReturnValueStatus(ReturnValueStatus.MustUse);
        List<FirTypeParameterRef> typeParameters3 = firRegularClassBuilder.getTypeParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters3, 10));
        Iterator<T> it2 = typeParameters3.iterator();
        while (it2.hasNext()) {
            arrayList2.add(UtilsKt.toFirResolvedTypeRef$default(new ConeTypeParameterTypeImpl(((FirTypeParameterRef) it2.next()).getSymbol().getLookupTag(), false, null, 4, null), null, null, 3, null));
        }
        if (kind.isReflectType()) {
            firRegularClassBuilder.getSuperTypeRefs().add(UtilsKt.toFirResolvedTypeRef$default(TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getKFunction()), new ConeKotlinType[]{((FirResolvedTypeRef) CollectionsKt.last(arrayList2)).getConeType()}, false, null, 6, null), null, null, 3, null));
            firRegularClassBuilder.getSuperTypeRefs().add(createSyntheticFunctionInterface$lambda$0$0$0$createSuperType(this, iIntValue, arrayList2, kind.nonReflectKind()));
        } else {
            firRegularClassBuilder.getSuperTypeRefs().add(UtilsKt.toFirResolvedTypeRef$default(TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getFunction()), new ConeKotlinType[]{((FirResolvedTypeRef) CollectionsKt.last(arrayList2)).getConeType()}, false, null, 6, null), null, null, 3, null));
        }
        if (!kind.isReflectType()) {
            FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
            firNamedFunctionBuilder.setModuleData(this.moduleData);
            FirResolvePhase.Companion companion = FirResolvePhase.INSTANCE;
            firNamedFunctionBuilder.setResolvePhase(companion.getANALYZED_DEPENDENCIES());
            firNamedFunctionBuilder.setOrigin(firDeclarationOrigin);
            firNamedFunctionBuilder.setReturnTypeRef((FirTypeRef) CollectionsKt.last(arrayList2));
            firNamedFunctionBuilder.setName(name);
            firNamedFunctionBuilder.setStatus(firResolvedDeclarationStatusImpl);
            firNamedFunctionBuilder.setLocal(false);
            firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(classId.getPackageFqName(), classId.getRelativeClassName(), name)));
            firNamedFunctionBuilder.setResolvePhase(companion.getANALYZED_DEPENDENCIES());
            List<FirValueParameter> valueParameters = firNamedFunctionBuilder.getValueParameters();
            List listDropLast = CollectionsKt.dropLast(arrayList2, 1);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDropLast, 10));
            int i = 0;
            for (Object obj : listDropLast) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Name nameIdentifier3 = Name.identifier("p" + i2);
                nameIdentifier3.getClass();
                FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                firValueParameterBuilder.setModuleData(this.moduleData);
                firValueParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
                firValueParameterBuilder.setOrigin(firDeclarationOrigin);
                firValueParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
                firValueParameterBuilder.setReturnTypeRef((FirResolvedTypeRef) obj);
                firValueParameterBuilder.setName(nameIdentifier3);
                firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                firValueParameterBuilder.setDefaultValue(null);
                firValueParameterBuilder.setCrossinline(false);
                firValueParameterBuilder.setNoinline(false);
                firValueParameterBuilder.setVararg(false);
                arrayList3.add(firValueParameterBuilder.mo289build());
                i = i2;
            }
            CollectionsKt.addAll(valueParameters, arrayList3);
            List<FirTypeParameterRef> typeParameters4 = firRegularClassBuilder.getTypeParameters();
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters4, 10));
            Iterator<T> it3 = typeParameters4.iterator();
            while (it3.hasNext()) {
                arrayList4.add(((FirTypeParameterRef) it3.next()).getSymbol());
            }
            firNamedFunctionBuilder.setDispatchReceiverType(ScopeUtilsKt.defaultType(classId, arrayList4));
            ClassId annotationOnInvokeClassId = kind.getAnnotationOnInvokeClassId();
            if (annotationOnInvokeClassId != null) {
                List<FirAnnotation> annotations = firNamedFunctionBuilder.getAnnotations();
                FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
                firAnnotationBuilder.setAnnotationTypeRef(UtilsKt.toFirResolvedTypeRef$default(TypeConstructionUtilsKt.constructClassLikeType$default(annotationOnInvokeClassId, null, false, null, 7, null), null, null, 3, null));
                firAnnotationBuilder.setArgumentMapping(FirEmptyAnnotationArgumentMapping.INSTANCE);
                annotations.add(firAnnotationBuilder.mo289build());
            }
            FirDeclarationBuildingUtilsKt.addDeclaration(firRegularClassBuilder, firNamedFunctionBuilder.mo289build());
        }
        firRegularClassBuilder.mo289build();
        return firRegularClassSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirRegularClassSymbol getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        FunctionTypeKind acceptableFunctionTypeKind = getAcceptableFunctionTypeKind(classId);
        if (acceptableFunctionTypeKind == null) {
            return null;
        }
        return this.cache.getValue(classId, acceptableFunctionTypeKind);
    }

    @FirSymbolProviderInternals
    public final Set<FqName> getFunctionKindPackageNames() {
        return FirFunctionTypeKindServiceKt.getFunctionTypeService(getSession()).getFunctionKindPackageNames();
    }

    public final FirKotlinScopeProvider getKotlinScopeProvider() {
        return this.kotlinScopeProvider;
    }

    public final FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return FirFunctionTypeKindServiceKt.getFunctionTypeService(getSession()).hasKindWithSpecificPackage(fqName);
    }

    public abstract boolean isAcceptable(FunctionTypeKind functionTypeKind);

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007b\u0002\b\tJ\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0003b\u0002\b\tJ\u0010\u0010\f\u001a\u00020\u0005*\u00020\u0006H\u0007b\u0002\b\tJ\u0019\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000b¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirSyntheticFunctionInterfaceProviderBase$Companion;", Argument.Delimiters.none, "<init>", "()V", "isNameForFunctionClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getFunctionTypeKind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "mayBeSyntheticFunctionClassName", "getArityIfAllowedOrNull", Argument.Delimiters.none, "kind", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;)Ljava/lang/Integer;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @FirSymbolProviderInternals
        public final FunctionTypeKind getFunctionTypeKind(ClassId classId, FirSession firSession) {
            if (!mayBeSyntheticFunctionClassName(classId)) {
                return null;
            }
            FirFunctionTypeKindService functionTypeService = FirFunctionTypeKindServiceKt.getFunctionTypeService(firSession);
            FqName packageFqName = classId.getPackageFqName();
            String strAsString = classId.getShortClassName().asString();
            strAsString.getClass();
            return functionTypeService.getKindByClassNamePrefix(packageFqName, strAsString);
        }

        public final Integer getArityIfAllowedOrNull(ClassId classId, FunctionTypeKind functionTypeKind) {
            classId.getClass();
            functionTypeKind.getClass();
            Integer intOrNull = StringsKt.toIntOrNull(classId.getRelativeClassName().asString().substring(functionTypeKind.getClassNamePrefix().length()));
            if (intOrNull == null || intOrNull.intValue() > functionTypeKind.getMaxArity()) {
                return null;
            }
            return intOrNull;
        }

        @FirSymbolProviderInternals
        public final boolean isNameForFunctionClass(ClassId classId, FirSession firSession) {
            classId.getClass();
            firSession.getClass();
            return getFunctionTypeKind(classId, firSession) != null;
        }

        @FirSymbolProviderInternals
        public final boolean mayBeSyntheticFunctionClassName(ClassId classId) {
            classId.getClass();
            Character chLastOrNull = StringsKt.lastOrNull(classId.getRelativeClassName().asString());
            return chLastOrNull != null && Character.isDigit(chLastOrNull.charValue());
        }

        private Companion() {
        }
    }

    public /* synthetic */ FirSyntheticFunctionInterfaceProviderBase(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firModuleData, firKotlinScopeProvider, (i & 8) != 0 ? false : z);
    }
}
