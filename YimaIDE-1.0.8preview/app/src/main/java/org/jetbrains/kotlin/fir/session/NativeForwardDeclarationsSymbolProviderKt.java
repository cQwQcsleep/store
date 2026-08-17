package org.jetbrains.kotlin.fir.session;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.session.NativeForwardDeclarationsSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.NativeForwardDeclarationKind;
import org.jetbrains.kotlin.name.NativeStandardInteropNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001aC\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0019\b\u0002\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010¨\u0006\u0011"}, d2 = {"mayBeForwardDeclarationClassId", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "createSyntheticForwardDeclarationClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "classId", "firModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "performAdditionalSetup", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NativeForwardDeclarationsSymbolProviderKt {
    public static Unit a(FirRegularClassBuilder firRegularClassBuilder) {
        firRegularClassBuilder.getClass();
        return Unit.INSTANCE;
    }

    public static final FirRegularClassSymbol createSyntheticForwardDeclarationClass(ClassId classId, FirModuleData firModuleData, FirSession firSession, FirScopeProvider firScopeProvider, Function1<? super FirRegularClassBuilder, Unit> function1) {
        classId.getClass();
        firModuleData.getClass();
        firSession.getClass();
        firScopeProvider.getClass();
        function1.getClass();
        NativeForwardDeclarationKind nativeForwardDeclarationKind = (NativeForwardDeclarationKind) NativeForwardDeclarationKind.Companion.getPackageFqNameToKind().get(classId.getPackageFqName());
        if (nativeForwardDeclarationKind == null) {
            return null;
        }
        FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(classId);
        FirRegularClassBuilder firRegularClassBuilder = new FirRegularClassBuilder();
        firRegularClassBuilder.setModuleData(firModuleData);
        firRegularClassBuilder.setOrigin(FirDeclarationOrigin.Synthetic.ForwardDeclaration.INSTANCE);
        if (classId.isNestedClass()) {
            cpa.a("Expected top-level class when building forward declaration, got ", classId);
            return null;
        }
        firRegularClassBuilder.setName(classId.getShortClassName());
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE);
        firResolvedDeclarationStatusImpl.setExpect(false);
        firResolvedDeclarationStatusImpl.setActual(false);
        firResolvedDeclarationStatusImpl.setCompanion(false);
        firResolvedDeclarationStatusImpl.setInner(false);
        firResolvedDeclarationStatusImpl.setData(false);
        firResolvedDeclarationStatusImpl.setInline(false);
        firResolvedDeclarationStatusImpl.setExternal(false);
        firResolvedDeclarationStatusImpl.setFun(false);
        firRegularClassBuilder.setStatus(firResolvedDeclarationStatusImpl);
        firRegularClassBuilder.setClassKind(nativeForwardDeclarationKind.getClassKind());
        firRegularClassBuilder.setScopeProvider(firScopeProvider);
        firRegularClassBuilder.setSymbol(firRegularClassSymbol);
        firRegularClassBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        List<FirTypeRef> superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(TypeConstructionUtilsKt.constructClassType$default(new ConeClassLikeLookupTagImpl(nativeForwardDeclarationKind.getSuperClassId()), null, false, null, 7, null));
        superTypeRefs.add(firResolvedTypeRefBuilder.build());
        List<FirAnnotation> annotations = firRegularClassBuilder.getAnnotations();
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
        NativeStandardInteropNames nativeStandardInteropNames = NativeStandardInteropNames.INSTANCE;
        firResolvedTypeRefBuilder2.setConeType(TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(new ClassId(nativeStandardInteropNames.getCInteropPackage(), nativeStandardInteropNames.getExperimentalForeignApi())), null, false, null, 7, null));
        firAnnotationBuilder.setAnnotationTypeRef(firResolvedTypeRefBuilder2.build());
        firAnnotationBuilder.setArgumentMapping(FirEmptyAnnotationArgumentMapping.INSTANCE);
        annotations.add(firAnnotationBuilder.mo288build());
        function1.invoke(firRegularClassBuilder);
        FirRegularClass firRegularClassMo288build = firRegularClassBuilder.mo288build();
        firRegularClassMo288build.replaceDeprecationsProvider(DeprecationUtilsKt.getDeprecationsProvider(firRegularClassMo288build, firSession));
        return firRegularClassSymbol;
    }

    public static /* synthetic */ FirRegularClassSymbol createSyntheticForwardDeclarationClass$default(ClassId classId, FirModuleData firModuleData, FirSession firSession, FirScopeProvider firScopeProvider, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: dca
                public final Object invoke(Object obj2) {
                    return NativeForwardDeclarationsSymbolProviderKt.a((FirRegularClassBuilder) obj2);
                }
            };
        }
        return createSyntheticForwardDeclarationClass(classId, firModuleData, firSession, firScopeProvider, function1);
    }

    public static final boolean mayBeForwardDeclarationClassId(ClassId classId) {
        classId.getClass();
        return NativeForwardDeclarationKind.Companion.getPackageFqNameToKind().containsKey(classId.getPackageFqName());
    }
}
