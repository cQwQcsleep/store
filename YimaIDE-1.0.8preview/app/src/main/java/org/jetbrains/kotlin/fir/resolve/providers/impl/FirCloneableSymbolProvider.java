package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProviderWithoutCallables;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J.\u0010\u0014\u001a\u00020\u00152\u0010\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dJ*\u0010\u001e\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dJ*\u0010 \u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020!0\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0017b\u0002\b\u001dJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001aH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCloneableSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;)V", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", Argument.Delimiters.none, "fqName", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCloneableSymbolProvider extends FirSymbolProvider {
    private final FirRegularClass klass;
    private final FirSymbolNamesProvider symbolNamesProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirCloneableSymbolProvider(FirSession firSession, FirModuleData firModuleData, FirScopeProvider firScopeProvider) {
        super(firSession);
        firSession.getClass();
        firModuleData.getClass();
        firScopeProvider.getClass();
        FirRegularClassBuilder firRegularClassBuilder = new FirRegularClassBuilder();
        FirResolvePhase.Companion companion = FirResolvePhase.INSTANCE;
        firRegularClassBuilder.setResolvePhase(companion.getANALYZED_DEPENDENCIES());
        FirDeclarationOrigin.Library library = FirDeclarationOrigin.Library.INSTANCE;
        firRegularClassBuilder.setOrigin(library);
        firRegularClassBuilder.setModuleData(firModuleData);
        firRegularClassBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.ABSTRACT, EffectiveVisibility.Public.INSTANCE));
        firRegularClassBuilder.setClassKind(ClassKind.INTERFACE);
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(standardClassIds.getCloneable());
        firRegularClassBuilder.setSymbol(firRegularClassSymbol);
        List<FirTypeRef> superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(firSession.getBuiltinTypes().getAnyType().getConeType());
        superTypeRefs.add(firResolvedTypeRefBuilder.build());
        List<FirDeclaration> declarations = firRegularClassBuilder.getDeclarations();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setModuleData(firModuleData);
        firNamedFunctionBuilder.setResolvePhase(companion.getANALYZED_DEPENDENCIES());
        firNamedFunctionBuilder.setOrigin(library);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder2.setConeType(firSession.getBuiltinTypes().getAnyType().getConeType());
        firNamedFunctionBuilder.setReturnTypeRef(firResolvedTypeRefBuilder2.build());
        Visibilities.Protected r4 = Visibilities.Protected.INSTANCE;
        firNamedFunctionBuilder.setStatus(new FirResolvedDeclarationStatusImpl(r4, Modality.OPEN, EffectiveVisibilityUtilsKt.toEffectiveVisibility$default((Visibility) r4, (FirClassLikeSymbol) firRegularClassSymbol, false, false, 6, (Object) null)));
        firNamedFunctionBuilder.setLocal(false);
        StandardClassIds.Callables callables = StandardClassIds.Callables.INSTANCE;
        firNamedFunctionBuilder.setName(callables.getClone().getCallableName());
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(callables.getClone()));
        firNamedFunctionBuilder.setDispatchReceiverType(TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) firRegularClassBuilder.getSymbol(), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null));
        declarations.add(firNamedFunctionBuilder.mo288build());
        firRegularClassBuilder.setScopeProvider(firScopeProvider);
        firRegularClassBuilder.setName(standardClassIds.getCloneable().getShortClassName());
        this.klass = firRegularClassBuilder.mo288build();
        this.symbolNamesProvider = new FirSymbolNamesProviderWithoutCallables() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCloneableSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelClassifiers() {
                return SetsKt.setOf(StandardClassIds.INSTANCE.getCloneable().getPackageFqName().asString());
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                StandardClassIds standardClassIds2 = StandardClassIds.INSTANCE;
                return Intrinsics.areEqual(packageFqName, standardClassIds2.getCloneable().getPackageFqName()) ? SetsKt.setOf(standardClassIds2.getCloneable().getShortClassName()) : SetsKt.emptySet();
            }
        };
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        if (Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getCloneable())) {
            return this.klass.getSymbol();
        }
        return null;
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
        return false;
    }
}
