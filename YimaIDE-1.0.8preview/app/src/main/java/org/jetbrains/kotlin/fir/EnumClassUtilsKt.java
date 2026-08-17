package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusWithLazyEffectiveVisibility;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.builder.FirEmptyExpressionBlockBuilderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001aT\u0010\u0000\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a6\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001aT\u0010\u0015\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a6\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001aT\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0002¨\u0006\u001b"}, d2 = {"generateValuesFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "classFqName", "makeExpect", Argument.Delimiters.none, "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "classSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "classStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "classResolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "generateValueOfFunction", "generateEntriesGetter", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "createStatus", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "parentStatus", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EnumClassUtilsKt {
    private static final FirDeclarationStatusImpl createStatus(FirDeclarationStatus firDeclarationStatus) {
        if (firDeclarationStatus instanceof FirResolvedDeclarationStatusImpl) {
            return new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, ((FirResolvedDeclarationStatusImpl) firDeclarationStatus).getEffectiveVisibility());
        }
        return firDeclarationStatus instanceof FirResolvedDeclarationStatusWithLazyEffectiveVisibility ? new FirResolvedDeclarationStatusWithLazyEffectiveVisibility(Visibilities.Public.INSTANCE, Modality.FINAL, ((FirResolvedDeclarationStatusWithLazyEffectiveVisibility) firDeclarationStatus).getLazyEffectiveVisibility()) : new FirDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL);
    }

    public static final FirProperty generateEntriesGetter(FirRegularClassSymbol firRegularClassSymbol, KtSourceElement ktSourceElement, FirDeclarationStatus firDeclarationStatus, FirResolvePhase firResolvePhase, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        firRegularClassSymbol.getClass();
        firDeclarationStatus.getClass();
        firResolvePhase.getClass();
        firModuleData.getClass();
        fqName.getClass();
        fqName2.getClass();
        firDeclarationOrigin.getClass();
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE, null, 2, null) : null;
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setSource(ktSourceElementFakeElement$default);
        firPropertyBuilder.setVar(false);
        firPropertyBuilder.setOrigin(firDeclarationOrigin);
        firPropertyBuilder.setModuleData(firModuleData);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getEnumEntries()), new ConeClassLikeTypeImpl[]{new ConeClassLikeTypeImpl(firRegularClassSymbol.getLookupTag(), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null)}, false, null, 8, null));
        firPropertyBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
        Name name = StandardNames.ENUM_ENTRIES;
        firPropertyBuilder.setName(name);
        FirDeclarationStatusImpl firDeclarationStatusImplCreateStatus = createStatus(firDeclarationStatus);
        firDeclarationStatusImplCreateStatus.setStatic(true);
        firDeclarationStatusImplCreateStatus.setExpect(z);
        firPropertyBuilder.setStatus(firDeclarationStatusImplCreateStatus);
        firPropertyBuilder.setLocal(Intrinsics.areEqual(firDeclarationStatus.getVisibility(), Visibilities.Local.INSTANCE));
        firPropertyBuilder.setSymbol(new FirRegularPropertySymbol(new CallableId(fqName, fqName2, name)));
        firPropertyBuilder.setResolvePhase(firResolvePhase);
        FirDefaultPropertyGetter firDefaultPropertyGetter = new FirDefaultPropertyGetter(ktSourceElementFakeElement$default != null ? KtSourceElementKt.fakeElement$default(ktSourceElementFakeElement$default, KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE, null, 2, null) : null, firModuleData, firDeclarationOrigin, UtilsKt.copyWithNewSourceKind(firPropertyBuilder.getReturnTypeRef(), KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE), Visibilities.Public.INSTANCE, firPropertyBuilder.getSymbol(), Modality.FINAL, null, false, false, null, firResolvePhase, null, 6016, null);
        FirDeclarationStatusImpl firDeclarationStatusImplCreateStatus2 = createStatus(firDeclarationStatus);
        firDeclarationStatusImplCreateStatus2.setStatic(true);
        firDefaultPropertyGetter.setStatus(firDeclarationStatusImplCreateStatus2);
        firPropertyBuilder.setGetter(firDefaultPropertyGetter);
        FirProperty firPropertyBuild = firPropertyBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firPropertyBuild, firRegularClassSymbol.getLookupTag());
        DeclarationAttributesKt.setHasBackingFieldAttr(firPropertyBuild, Boolean.FALSE);
        return firPropertyBuild;
    }

    public static final FirNamedFunction generateValueOfFunction(FirRegularClassSymbol firRegularClassSymbol, KtSourceElement ktSourceElement, FirDeclarationStatus firDeclarationStatus, FirResolvePhase firResolvePhase, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        firRegularClassSymbol.getClass();
        firDeclarationStatus.getClass();
        firResolvePhase.getClass();
        firModuleData.getClass();
        fqName.getClass();
        fqName2.getClass();
        firDeclarationOrigin.getClass();
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE, null, 2, null) : null;
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setSource(ktSourceElementFakeElement$default);
        firNamedFunctionBuilder.setOrigin(firDeclarationOrigin);
        firNamedFunctionBuilder.setModuleData(firModuleData);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(firRegularClassSymbol.getLookupTag(), new ConeTypeProjection[0], false, null, 8, null));
        FirTypeRef firTypeRefBuild = firResolvedTypeRefBuilder.build();
        firNamedFunctionBuilder.setReturnTypeRef(firTypeRefBuild);
        Name name = StandardNames.ENUM_VALUE_OF;
        firNamedFunctionBuilder.setName(name);
        FirDeclarationStatusImpl firDeclarationStatusImplCreateStatus = createStatus(firDeclarationStatus);
        firDeclarationStatusImplCreateStatus.setStatic(true);
        firDeclarationStatusImplCreateStatus.setExpect(z);
        firNamedFunctionBuilder.setStatus(firDeclarationStatusImplCreateStatus);
        firNamedFunctionBuilder.setLocal(Intrinsics.areEqual(firDeclarationStatus.getVisibility(), Visibilities.Local.INSTANCE));
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(fqName, fqName2, name)));
        List<FirValueParameter> valueParameters = firNamedFunctionBuilder.getValueParameters();
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setSource(ktSourceElementFakeElement$default);
        firValueParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
        firValueParameterBuilder.setOrigin(firDeclarationOrigin);
        firValueParameterBuilder.setModuleData(firModuleData);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder2.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder2.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getString()), new ConeTypeProjection[0], false, null, 8, null));
        firValueParameterBuilder.setReturnTypeRef(firResolvedTypeRefBuilder2.build());
        firValueParameterBuilder.setName(StandardNames.DEFAULT_VALUE_PARAMETER);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setCrossinline(false);
        firValueParameterBuilder.setNoinline(false);
        firValueParameterBuilder.setVararg(false);
        firValueParameterBuilder.setResolvePhase(firResolvePhase);
        valueParameters.add(firValueParameterBuilder.mo288build());
        firNamedFunctionBuilder.setResolvePhase(firResolvePhase);
        FirBlock firBlockBuildEmptyExpressionBlock = FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
        firBlockBuildEmptyExpressionBlock.replaceConeTypeOrNull(firTypeRefBuild.getConeType());
        firNamedFunctionBuilder.setBody(firBlockBuildEmptyExpressionBlock);
        FirNamedFunction firNamedFunctionBuild = firNamedFunctionBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firNamedFunctionBuild, firRegularClassSymbol.getLookupTag());
        return firNamedFunctionBuild;
    }

    public static final FirNamedFunction generateValuesFunction(FirRegularClassSymbol firRegularClassSymbol, KtSourceElement ktSourceElement, FirDeclarationStatus firDeclarationStatus, FirResolvePhase firResolvePhase, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        firRegularClassSymbol.getClass();
        firDeclarationStatus.getClass();
        firResolvePhase.getClass();
        firModuleData.getClass();
        fqName.getClass();
        fqName2.getClass();
        firDeclarationOrigin.getClass();
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE, null, 2, null) : null;
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setSource(ktSourceElementFakeElement$default);
        firNamedFunctionBuilder.setOrigin(firDeclarationOrigin);
        firNamedFunctionBuilder.setModuleData(firModuleData);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getArray()), new ConeClassLikeTypeImpl[]{new ConeClassLikeTypeImpl(firRegularClassSymbol.getLookupTag(), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null)}, false, null, 8, null));
        FirTypeRef firTypeRefBuild = firResolvedTypeRefBuilder.build();
        firNamedFunctionBuilder.setReturnTypeRef(firTypeRefBuild);
        Name name = StandardNames.ENUM_VALUES;
        firNamedFunctionBuilder.setName(name);
        FirDeclarationStatusImpl firDeclarationStatusImplCreateStatus = createStatus(firDeclarationStatus);
        firDeclarationStatusImplCreateStatus.setStatic(true);
        firDeclarationStatusImplCreateStatus.setExpect(z);
        firNamedFunctionBuilder.setStatus(firDeclarationStatusImplCreateStatus);
        firNamedFunctionBuilder.setLocal(Intrinsics.areEqual(firDeclarationStatus.getVisibility(), Visibilities.Local.INSTANCE));
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(fqName, fqName2, name)));
        firNamedFunctionBuilder.setResolvePhase(firResolvePhase);
        FirBlock firBlockBuildEmptyExpressionBlock = FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
        firBlockBuildEmptyExpressionBlock.replaceConeTypeOrNull(firTypeRefBuild.getConeType());
        firNamedFunctionBuilder.setBody(firBlockBuildEmptyExpressionBlock);
        FirNamedFunction firNamedFunctionBuild = firNamedFunctionBuilder.mo288build();
        ClassMembersKt.setContainingClassForStaticMemberAttr(firNamedFunctionBuild, firRegularClassSymbol.getLookupTag());
        return firNamedFunctionBuild;
    }

    public static /* synthetic */ void generateEntriesGetter$default(FirRegularClassBuilder firRegularClassBuilder, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Source.INSTANCE;
        }
        generateEntriesGetter(firRegularClassBuilder, firModuleData, fqName, fqName2, z2, firDeclarationOrigin);
    }

    public static /* synthetic */ void generateValueOfFunction$default(FirRegularClassBuilder firRegularClassBuilder, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Source.INSTANCE;
        }
        generateValueOfFunction(firRegularClassBuilder, firModuleData, fqName, fqName2, z2, firDeclarationOrigin);
    }

    public static /* synthetic */ void generateValuesFunction$default(FirRegularClassBuilder firRegularClassBuilder, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            firDeclarationOrigin = FirDeclarationOrigin.Source.INSTANCE;
        }
        generateValuesFunction(firRegularClassBuilder, firModuleData, fqName, fqName2, z2, firDeclarationOrigin);
    }

    public static final void generateValuesFunction(FirRegularClassBuilder firRegularClassBuilder, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        firRegularClassBuilder.getClass();
        firModuleData.getClass();
        fqName.getClass();
        fqName2.getClass();
        firDeclarationOrigin.getClass();
        firRegularClassBuilder.getDeclarations().add(generateValuesFunction(firRegularClassBuilder.getSymbol(), firRegularClassBuilder.getSource(), firRegularClassBuilder.getStatus(), firRegularClassBuilder.getResolvePhase(), firModuleData, fqName, fqName2, z, firDeclarationOrigin));
    }

    public static final void generateEntriesGetter(FirRegularClassBuilder firRegularClassBuilder, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        firRegularClassBuilder.getClass();
        firModuleData.getClass();
        fqName.getClass();
        fqName2.getClass();
        firDeclarationOrigin.getClass();
        firRegularClassBuilder.getDeclarations().add(generateEntriesGetter(firRegularClassBuilder.getSymbol(), firRegularClassBuilder.getSource(), firRegularClassBuilder.getStatus(), firRegularClassBuilder.getResolvePhase(), firModuleData, fqName, fqName2, z, firDeclarationOrigin));
    }

    public static final void generateValueOfFunction(FirRegularClassBuilder firRegularClassBuilder, FirModuleData firModuleData, FqName fqName, FqName fqName2, boolean z, FirDeclarationOrigin firDeclarationOrigin) {
        firRegularClassBuilder.getClass();
        firModuleData.getClass();
        fqName.getClass();
        fqName2.getClass();
        firDeclarationOrigin.getClass();
        firRegularClassBuilder.getDeclarations().add(generateValueOfFunction(firRegularClassBuilder.getSymbol(), firRegularClassBuilder.getSource(), firRegularClassBuilder.getStatus(), firRegularClassBuilder.getResolvePhase(), firModuleData, fqName, fqName2, z, firDeclarationOrigin));
    }
}
