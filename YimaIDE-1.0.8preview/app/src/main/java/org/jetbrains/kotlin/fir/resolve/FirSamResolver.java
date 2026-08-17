package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.NullableMap;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttribute;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00012B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0012\u0010!\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001d\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&J\u0010\u0010'\u001a\u0004\u0018\u00010\u00172\u0006\u0010%\u001a\u00020&J\u0010\u0010(\u001a\u0004\u0018\u00010\u00172\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u0004\u0018\u00010\u00172\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020/*\u0006\u0012\u0002\b\u00030\u0016H\u0002J\u0018\u00100\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u00101\u001a\u00020\u0010H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R&\u0010\u0014\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0004\u0012\u00020\u00000\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "outerClassManager", "Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "resolvedFunctionType", "Lorg/jetbrains/kotlin/fir/caches/NullableMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/resolve/SAMInfo;", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "Ljava/util/Map;", "samConstructorsCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "samConversionTransformers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/FirSamConversionTransformerExtension;", "isSamType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getSamInfoForPossibleSamType", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver$SamConversionInfo;", "getFunctionTypeForPossibleSamType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "getSamConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "firClassOrTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getSamFunction", "buildSamConstructorForRegularClass", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "buildSamConstructorForTypeAlias", "typeAliasSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "createSyntheticConstructorSymbol", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticFunctionSymbol;", "resolveFunctionTypeIfSamInterface", "firRegularClass", "SamConversionInfo", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSamResolver implements SessionAndScopeSessionHolder {
    private final FirOuterClassManager outerClassManager;
    private final Map<FirRegularClass, Object> resolvedFunctionType;
    private final FirCache<FirClassLikeSymbol<?>, FirNamedFunctionSymbol, FirSamResolver> samConstructorsCache;
    private final List<FirSamConversionTransformerExtension> samConversionTransformers;
    private final ScopeSession scopeSession;
    private final FirSession session;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver$SamConversionInfo;", Argument.Delimiters.none, "functionalType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "samType", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getFunctionalType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getSamType", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SamConversionInfo {
        private final ConeKotlinType functionalType;
        private final ConeKotlinType samType;

        public SamConversionInfo(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
            coneKotlinType.getClass();
            coneKotlinType2.getClass();
            this.functionalType = coneKotlinType;
            this.samType = coneKotlinType2;
        }

        public static /* synthetic */ SamConversionInfo copy$default(SamConversionInfo samConversionInfo, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, int i, Object obj) {
            if ((i & 1) != 0) {
                coneKotlinType = samConversionInfo.functionalType;
            }
            if ((i & 2) != 0) {
                coneKotlinType2 = samConversionInfo.samType;
            }
            return samConversionInfo.copy(coneKotlinType, coneKotlinType2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ConeKotlinType getFunctionalType() {
            return this.functionalType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ConeKotlinType getSamType() {
            return this.samType;
        }

        public final SamConversionInfo copy(ConeKotlinType functionalType, ConeKotlinType samType) {
            functionalType.getClass();
            samType.getClass();
            return new SamConversionInfo(functionalType, samType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SamConversionInfo)) {
                return false;
            }
            SamConversionInfo samConversionInfo = (SamConversionInfo) other;
            return Intrinsics.areEqual(this.functionalType, samConversionInfo.functionalType) && Intrinsics.areEqual(this.samType, samConversionInfo.samType);
        }

        public final ConeKotlinType getFunctionalType() {
            return this.functionalType;
        }

        public final ConeKotlinType getSamType() {
            return this.samType;
        }

        public int hashCode() {
            return (this.functionalType.hashCode() * 31) + this.samType.hashCode();
        }

        public String toString() {
            return "SamConversionInfo(functionalType=" + this.functionalType + ", samType=" + this.samType + ')';
        }
    }

    public FirSamResolver(FirSession firSession, ScopeSession scopeSession, FirOuterClassManager firOuterClassManager) {
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.outerClassManager = firOuterClassManager;
        this.resolvedFunctionType = NullableMap.m268constructorimpl$default(null, 1, null);
        this.samConstructorsCache = FirSamResolverKt.getSamConstructorStorage(getSession()).getSamConstructors();
        this.samConversionTransformers = FirSamConversionTransformerExtensionKt.getSamConversionTransformers(FirExtensionServiceKt.getExtensionService(getSession()));
    }

    private final FirSyntheticFunctionSymbol createSyntheticConstructorSymbol(FirClassLikeSymbol<?> firClassLikeSymbol) {
        FqName packageFqName = firClassLikeSymbol.getClassId().getPackageFqName();
        FqName fqNameParent = firClassLikeSymbol.getClassId().getRelativeClassName().parent();
        if (fqNameParent.isRoot()) {
            fqNameParent = null;
        }
        return new FirSyntheticFunctionSymbol(new CallableId(packageFqName, fqNameParent, firClassLikeSymbol.getClassId().getShortClassName()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeLookupTagBasedType getFunctionTypeForPossibleSamType(ConeClassLikeType type) {
        FirRegularClass firRegularClass;
        SAMInfo<ConeLookupTagBasedType> sAMInfoResolveFunctionTypeIfSamInterface;
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, type.getLookupTag());
        if (regularClassSymbol == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null || (sAMInfoResolveFunctionTypeIfSamInterface = resolveFunctionTypeIfSamInterface(firRegularClass)) == null) {
            return null;
        }
        ConeKotlinType coneKotlinType = (ConeLookupTagBasedType) sAMInfoResolveFunctionTypeIfSamInterface.component2();
        ConeKotlinType coneKotlinTypeSubstituteOrNull = FirSamResolverKt.buildSubstitutorWithUpperBounds(this, firRegularClass, type).substituteOrNull(coneKotlinType);
        if (coneKotlinTypeSubstituteOrNull != null) {
            coneKotlinType = coneKotlinTypeSubstituteOrNull;
        }
        if (coneKotlinType instanceof ConeLookupTagBasedType) {
            return (ConeLookupTagBasedType) TypeUtilsKt.withNullabilityOf(coneKotlinType, type, TypeComponentsKt.getTypeContext(getSession()));
        }
        wec.a("Function type should always be ConeLookupTagBasedType, but ", Reflection.getOrCreateKotlinClass(coneKotlinType.getClass()), " was found");
        return null;
    }

    private final SAMInfo<ConeLookupTagBasedType> resolveFunctionTypeIfSamInterface(FirRegularClass firRegularClass) {
        FirNamedFunction singleAbstractMethodOrNull;
        Map<FirRegularClass, Object> map = this.resolvedFunctionType;
        Object sAMInfo = map.get(firRegularClass);
        ConeLookupTagBasedType functionTypeForAbstractMethod = null;
        if (sAMInfo == null) {
            if (firRegularClass.getStatus().isFun() && (singleAbstractMethodOrNull = FirSamResolverKt.getSingleAbstractMethodOrNull(this, firRegularClass)) != null) {
                Iterator<T> it = this.samConversionTransformers.iterator();
                while (it.hasNext()) {
                    ConeLookupTagBasedType customFunctionTypeForSamConversion = ((FirSamConversionTransformerExtension) it.next()).getCustomFunctionTypeForSamConversion(singleAbstractMethodOrNull);
                    if (customFunctionTypeForSamConversion != null) {
                        functionTypeForAbstractMethod = customFunctionTypeForSamConversion;
                        break;
                    }
                }
                FirNamedFunctionSymbol symbol = singleAbstractMethodOrNull.getSymbol();
                if (functionTypeForAbstractMethod == null) {
                    functionTypeForAbstractMethod = FirSamResolverKt.getFunctionTypeForAbstractMethod(singleAbstractMethodOrNull, getSession());
                }
                sAMInfo = new SAMInfo(symbol, functionTypeForAbstractMethod);
            } else {
                sAMInfo = null;
            }
            map.put(firRegularClass, sAMInfo == null ? NullableMap.NullValue.INSTANCE : sAMInfo);
        } else if (Intrinsics.areEqual(sAMInfo, NullableMap.NullValue.INSTANCE)) {
            sAMInfo = null;
        }
        return (SAMInfo) sAMInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunctionSymbol buildSamConstructorForRegularClass(FirRegularClassSymbol classSymbol) {
        ConeClassLikeLookupTag coneClassLikeLookupTag;
        KtSourceElement ktSourceElementFakeElement$default;
        FirClassLikeSymbol<?> firClassLikeSymbolOuterClass;
        classSymbol.getClass();
        FirRegularClass firRegularClass = (FirRegularClass) classSymbol.getFir();
        SAMInfo<ConeLookupTagBasedType> sAMInfoResolveFunctionTypeIfSamInterface = resolveFunctionTypeIfSamInterface(firRegularClass);
        if (sAMInfoResolveFunctionTypeIfSamInterface == null) {
            return null;
        }
        FirNamedFunctionSymbol symbol = sAMInfoResolveFunctionTypeIfSamInterface.getSymbol();
        ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) sAMInfoResolveFunctionTypeIfSamInterface.component2();
        FirSyntheticFunctionSymbol firSyntheticFunctionSymbolCreateSyntheticConstructorSymbol = createSyntheticConstructorSymbol(classSymbol);
        List<FirTypeParameterRef> typeParameters = firRegularClass.getTypeParameters();
        int i = 10;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            FirTypeParameter firTypeParameter = (FirTypeParameter) ((FirTypeParameterRef) it.next()).getSymbol().getFir();
            FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
            KtSourceElement source = firTypeParameter.getSource();
            firTypeParameterBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SamConstructor.INSTANCE, null, 2, null) : null);
            firTypeParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
            firTypeParameterBuilder.setOrigin(FirDeclarationOrigin.SamConstructor.INSTANCE);
            firTypeParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getDECLARATIONS());
            firTypeParameterBuilder.setName(firTypeParameter.getName());
            firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
            firTypeParameterBuilder.setVariance(Variance.INVARIANT);
            firTypeParameterBuilder.setReified(false);
            CollectionsKt.addAll(firTypeParameterBuilder.getAnnotations(), firTypeParameter.getAnnotations());
            firTypeParameterBuilder.setContainingDeclarationSymbol(firSyntheticFunctionSymbolCreateSyntheticConstructorSymbol);
            arrayList.add(firTypeParameterBuilder);
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new ConeTypeParameterTypeImpl(((FirTypeParameterBuilder) it2.next()).getSymbol().getLookupTag(), false, null, 4, null));
        }
        List<FirTypeParameterRef> typeParameters2 = firRegularClass.getTypeParameters();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters2, 10));
        Iterator<T> it3 = typeParameters2.iterator();
        while (it3.hasNext()) {
            arrayList3.add(((FirTypeParameterRef) it3.next()).getSymbol());
        }
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.toMap(CollectionsKt.zip(arrayList3, arrayList2)), getSession(), false, 4, null);
        for (Pair pair : CollectionsKt.zip(arrayList, firRegularClass.getTypeParameters())) {
            FirTypeParameterBuilder firTypeParameterBuilder2 = (FirTypeParameterBuilder) pair.component1();
            FirTypeParameter firTypeParameter2 = (FirTypeParameter) ((FirTypeParameterRef) pair.component2()).getSymbol().getFir();
            List<FirTypeRef> bounds = firTypeParameterBuilder2.getBounds();
            List<FirResolvedTypeRef> resolvedBounds = firTypeParameter2.getSymbol().getResolvedBounds();
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, i));
            for (FirResolvedTypeRef firResolvedTypeRef : resolvedBounds) {
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                KtSourceElement source2 = firResolvedTypeRef.getSource();
                FirRegularClass firRegularClass2 = firRegularClass;
                firResolvedTypeRefBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.SamConstructor.INSTANCE, null, 2, null) : null);
                firResolvedTypeRefBuilder.setConeType(coneSubstitutorSubstitutorByMap$default.substituteOrSelf(firResolvedTypeRef.getConeType()));
                arrayList4.add(firResolvedTypeRefBuilder.build());
                firRegularClass = firRegularClass2;
                symbol = symbol;
            }
            CollectionsKt.addAll(bounds, arrayList4);
            i = 10;
        }
        FirRegularClass firRegularClass3 = firRegularClass;
        FirNamedFunctionSymbol firNamedFunctionSymbol = symbol;
        KtSourceElement source3 = firRegularClass3.getSource();
        if (source3 != null) {
            coneClassLikeLookupTag = null;
            ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.SamConstructor.INSTANCE, null, 2, null);
        } else {
            coneClassLikeLookupTag = null;
            ktSourceElementFakeElement$default = null;
        }
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firNamedFunctionBuilder.setSource(ktSourceElementFakeElement$default);
        firNamedFunctionBuilder.setName(firSyntheticFunctionSymbolCreateSyntheticConstructorSymbol.getName());
        firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.SamConstructor.INSTANCE);
        FirDeclarationStatus status = firRegularClass3.getStatus();
        firNamedFunctionBuilder.setStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : Modality.FINAL, (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        firNamedFunctionBuilder.setLocal(firRegularClass3.getIsLocal());
        firNamedFunctionBuilder.setSymbol(firSyntheticFunctionSymbolCreateSyntheticConstructorSymbol);
        List<FirTypeParameter> typeParameters3 = firNamedFunctionBuilder.getTypeParameters();
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it4 = arrayList.iterator();
        while (it4.hasNext()) {
            arrayList5.add(((FirTypeParameterBuilder) it4.next()).mo289build());
        }
        CollectionsKt.addAll(typeParameters3, arrayList5);
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutorSubstitutorByMap$default.substituteOrSelf(coneLookupTagBasedType);
        ConeClassLikeTypeImpl coneClassLikeTypeImpl = new ConeClassLikeTypeImpl(firRegularClass3.getSymbol().getLookupTag(), (ConeTypeProjection[]) arrayList2.toArray(new ConeTypeParameterTypeImpl[0]), false, null, 8, null);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder2.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder2.setConeType(coneClassLikeTypeImpl);
        firNamedFunctionBuilder.setReturnTypeRef(firResolvedTypeRefBuilder2.build());
        List<FirValueParameter> valueParameters = firNamedFunctionBuilder.getValueParameters();
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firValueParameterBuilder.setContainingDeclarationSymbol(firSyntheticFunctionSymbolCreateSyntheticConstructorSymbol);
        firValueParameterBuilder.setOrigin(FirDeclarationOrigin.SamConstructor.INSTANCE);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder3 = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder3.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder3.setConeType(coneKotlinTypeSubstituteOrSelf);
        firValueParameterBuilder.setReturnTypeRef(firResolvedTypeRefBuilder3.build());
        firValueParameterBuilder.setName(FirSamResolverKt.SAM_PARAMETER_NAME);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setCrossinline(false);
        firValueParameterBuilder.setNoinline(false);
        firValueParameterBuilder.setVararg(false);
        FirResolvePhase firResolvePhase = FirResolvePhase.BODY_RESOLVE;
        firValueParameterBuilder.setResolvePhase(firResolvePhase);
        valueParameters.add(firValueParameterBuilder.mo289build());
        CollectionsKt.addAll(firNamedFunctionBuilder.getAnnotations(), firNamedFunctionSymbol.getAnnotations());
        firNamedFunctionBuilder.setResolvePhase(firResolvePhase);
        FirNamedFunction firNamedFunctionBuild = firNamedFunctionBuilder.mo289build();
        FirOuterClassManager firOuterClassManager = this.outerClassManager;
        ClassMembersKt.setContainingClassForStaticMemberAttr(firNamedFunctionBuild, (firOuterClassManager == null || (firClassLikeSymbolOuterClass = firOuterClassManager.outerClass(firRegularClass3.getSymbol())) == null) ? coneClassLikeLookupTag : firClassLikeSymbolOuterClass.getLookupTag());
        PublishedApiEffectiveVisibilityKt.setNonLazyPublishedApiEffectiveVisibility(firNamedFunctionBuild, PublishedApiEffectiveVisibilityKt.getNonLazyPublishedApiEffectiveVisibility(firRegularClass3));
        return firNamedFunctionBuild.getSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunctionSymbol buildSamConstructorForTypeAlias(FirTypeAliasSymbol typeAliasSymbol) {
        FirRegularClass firRegularClass;
        FirNamedFunction samConstructor;
        typeAliasSymbol.getClass();
        ConeKotlinType coneType = ((FirTypeAlias) typeAliasSymbol.getFir()).getExpandedTypeRef().getConeType();
        if (coneType == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.types.ConeClassLikeType");
            return null;
        }
        ConeClassLikeType coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, (ConeClassLikeType) coneType);
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, coneClassLikeTypeFullyExpandedType.getLookupTag());
        if (regularClassSymbol == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null || (samConstructor = getSamConstructor(firRegularClass)) == null) {
            return null;
        }
        ConeSubstitutor coneSubstitutorBuildSubstitutorWithUpperBounds = FirSamResolverKt.buildSubstitutorWithUpperBounds(this, samConstructor, coneClassLikeTypeFullyExpandedType);
        List<FirValueParameter> valueParameters = samConstructor.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(coneSubstitutorBuildSubstitutorWithUpperBounds.substituteOrSelf(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef())));
        }
        List<FirValueParameter> contextParameters = samConstructor.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it2 = contextParameters.iterator();
        while (it2.hasNext()) {
            arrayList2.add(coneSubstitutorBuildSubstitutorWithUpperBounds.substituteOrSelf(FirTypeUtilsKt.getConeType(((FirValueParameter) it2.next()).getReturnTypeRef())));
        }
        FirNamedFunction firNamedFunctionCreateCopyForFirFunction$default = FirFakeOverrideGenerator.createCopyForFirFunction$default(FirFakeOverrideGenerator.INSTANCE, createSyntheticConstructorSymbol(typeAliasSymbol), samConstructor, null, getSession(), FirDeclarationOrigin.SamConstructor.INSTANCE, false, null, arrayList, ((FirTypeAlias) typeAliasSymbol.getFir()).getTypeParameters(), null, arrayList2, (ConeClassLikeType) TypeUtilsKt.withAbbreviation(coneClassLikeTypeFullyExpandedType, new AbbreviatedTypeAttribute(ScopeUtilsKt.defaultType(typeAliasSymbol))), null, null, null, null, true, 61472, null);
        TypeAliasConstructorsSubstitutingScopeKt.setTypeAliasConstructorInfo(firNamedFunctionCreateCopyForFirFunction$default, new TypeAliasConstructorInfo(samConstructor, typeAliasSymbol, null));
        PublishedApiEffectiveVisibilityKt.setNonLazyPublishedApiEffectiveVisibility(firNamedFunctionCreateCopyForFirFunction$default, PublishedApiEffectiveVisibilityKt.getNonLazyPublishedApiEffectiveVisibility(firRegularClass));
        return firNamedFunctionCreateCopyForFirFunction$default.getSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunction getSamConstructor(FirClassLikeDeclaration firClassOrTypeAlias) {
        ConeClassLikeType coneClassLikeTypeFullyExpandedType;
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        firClassOrTypeAlias.getClass();
        if (firClassOrTypeAlias instanceof FirTypeAlias) {
            FirResolvedTypeRef resolvedExpandedTypeRef = ((FirTypeAlias) firClassOrTypeAlias).getSymbol().getResolvedExpandedTypeRef();
            if (resolvedExpandedTypeRef == null) {
                resolvedExpandedTypeRef = null;
            }
            ConeKotlinType coneType = resolvedExpandedTypeRef != null ? resolvedExpandedTypeRef.getConeType() : null;
            if (!(coneType instanceof ConeClassLikeType)) {
                coneType = null;
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
            if (coneClassLikeType != null && (coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, coneClassLikeType)) != null && (lookupTag = coneClassLikeTypeFullyExpandedType.getLookupTag()) != null && (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, lookupTag)) != null) {
                this.samConstructorsCache.getValue(symbol, this);
            }
        }
        FirNamedFunctionSymbol value = this.samConstructorsCache.getValue(firClassOrTypeAlias.getSymbol(), this);
        if (value != null) {
            return (FirNamedFunction) value.getFir();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0047  */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirNamedFunctionSymbol getSamFunction(FirClassLikeDeclaration firClassOrTypeAlias) {
        FirRegularClass firRegularClass;
        ConeClassLikeType coneClassLikeTypeFullyExpandedType;
        ConeClassLikeLookupTag lookupTag;
        FirRegularClassSymbol regularClassSymbol;
        SAMInfo<ConeLookupTagBasedType> sAMInfoResolveFunctionTypeIfSamInterface;
        firClassOrTypeAlias.getClass();
        if (firClassOrTypeAlias instanceof FirRegularClass) {
            firRegularClass = (FirRegularClass) firClassOrTypeAlias;
        } else if (firClassOrTypeAlias instanceof FirTypeAlias) {
            FirResolvedTypeRef resolvedExpandedTypeRef = ((FirTypeAlias) firClassOrTypeAlias).getSymbol().getResolvedExpandedTypeRef();
            if (resolvedExpandedTypeRef == null) {
                resolvedExpandedTypeRef = null;
            }
            ConeKotlinType coneType = resolvedExpandedTypeRef != null ? resolvedExpandedTypeRef.getConeType() : null;
            if (!(coneType instanceof ConeClassLikeType)) {
                coneType = null;
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
            if (coneClassLikeType == null || (coneClassLikeTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, coneClassLikeType)) == null || (lookupTag = coneClassLikeTypeFullyExpandedType.getLookupTag()) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) this, lookupTag)) == null) {
                firRegularClass = null;
            } else {
                firRegularClass = (FirRegularClass) regularClassSymbol.getFir();
            }
        } else {
            firRegularClass = null;
        }
        if (firRegularClass == null || (sAMInfoResolveFunctionTypeIfSamInterface = resolveFunctionTypeIfSamInterface(firRegularClass)) == null) {
            return null;
        }
        return sAMInfoResolveFunctionTypeIfSamInterface.getSymbol$org_jetbrains_kotlin_resolve();
    }

    public final SamConversionInfo getSamInfoForPossibleSamType(ConeKotlinType type) {
        ConeKotlinType functionalType;
        ConeKotlinType functionalType2;
        type.getClass();
        if (type instanceof ConeClassLikeType) {
            ConeLookupTagBasedType functionTypeForPossibleSamType = getFunctionTypeForPossibleSamType(TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, (ConeClassLikeType) type));
            if (functionTypeForPossibleSamType == null) {
                return null;
            }
            return new SamConversionInfo(functionTypeForPossibleSamType, type);
        }
        if (type instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) type;
            SamConversionInfo samInfoForPossibleSamType = getSamInfoForPossibleSamType(coneFlexibleType.getLowerBound());
            if (samInfoForPossibleSamType != null && (functionalType = samInfoForPossibleSamType.getFunctionalType()) != null) {
                if (coneFlexibleType.getIsTrivial()) {
                    return new SamConversionInfo(TypeUtilsKt.toTrivialFlexibleType(ConeTypeUtilsKt.lowerBoundIfFlexible(functionalType), TypeComponentsKt.getTypeContext(getSession())), type);
                }
                SamConversionInfo samInfoForPossibleSamType2 = getSamInfoForPossibleSamType(coneFlexibleType.getUpperBound());
                if (samInfoForPossibleSamType2 != null && (functionalType2 = samInfoForPossibleSamType2.getFunctionalType()) != null) {
                    return new SamConversionInfo(new ConeFlexibleType(ConeTypeUtilsKt.lowerBoundIfFlexible(functionalType), ConeTypeUtilsKt.upperBoundIfFlexible(functionalType2), false), type);
                }
            }
            return null;
        }
        if (!(type instanceof ConeStubType) && !(type instanceof ConeTypeParameterType) && !(type instanceof ConeTypeVariableType) && !(type instanceof ConeDefinitelyNotNullType) && !(type instanceof ConeIntersectionType) && !(type instanceof ConeIntegerLiteralType)) {
            if (type instanceof ConeCapturedType) {
                ConeKotlinType lowerType = ((ConeCapturedType) type).getConstructor().getLowerType();
                if (lowerType != null) {
                    return getSamInfoForPossibleSamType(lowerType);
                }
                return null;
            }
            if (type instanceof ConeLookupTagBasedType) {
                AddToStdlibKt.unreachableBranch(type);
                wq6.a();
                return null;
            }
            bu8.a();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isSamType(ConeKotlinType type) {
        type.getClass();
        if (type instanceof ConeClassLikeType) {
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) this, TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) this, (ConeClassLikeType) type).getLookupTag());
            return (symbol instanceof FirRegularClassSymbol) && resolveFunctionTypeIfSamInterface((FirRegularClass) ((FirRegularClassSymbol) symbol).getFir()) != null;
        }
        if (type instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) type;
            if (isSamType(coneFlexibleType.getLowerBound()) && isSamType(coneFlexibleType.getUpperBound())) {
                return true;
            }
        }
        return false;
    }

    public /* synthetic */ FirSamResolver(FirSession firSession, ScopeSession scopeSession, FirOuterClassManager firOuterClassManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, (i & 4) != 0 ? null : firOuterClassManager);
    }
}
