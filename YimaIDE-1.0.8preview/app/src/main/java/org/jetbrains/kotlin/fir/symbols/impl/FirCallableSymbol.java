package org.jetbrains.kotlin.fir.symbols.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.DeprecationsPerUseSite;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVersionRequirementsTableKeyKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.mpp.CallableSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010B\u001a\u0004\u0018\u00010C2\u0006\u0010D\u001a\u00020EJ\b\u0010F\u001a\u00020/H\u0014J\r\u0010G\u001a\u00020/H\u0000¢\u0006\u0002\bHJ\u0012\u0010I\u001a\u00020\u00142\b\u0010J\u001a\u0004\u0018\u00010KH\u0002J\n\u0010L\u001a\u00020MH\u0096\u0080\u0004J\u0006\u0010N\u001a\u00020MR\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\b8FX\u0087\u0004r\u0002\b\u000e¢\u0006\f\u0012\u0004\b\f\u0010\u0006\u001a\u0004\b\r\u0010\nR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00168F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0018R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020'8F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b.\u00100R\u0017\u00101\u001a\b\u0012\u0004\u0012\u0002020\"8F¢\u0006\u0006\u001a\u0004\b3\u0010%R\u0017\u00104\u001a\b\u0012\u0004\u0012\u0002020\"8F¢\u0006\u0006\u001a\u0004\b5\u0010%R\u0013\u00106\u001a\u0004\u0018\u0001078F¢\u0006\u0006\u001a\u0004\b8\u00109R\u0012\u0010:\u001a\u00020;X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0013\u0010>\u001a\u0004\u0018\u00010?8F¢\u0006\u0006\u001a\u0004\b@\u0010A¨\u0006O"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/mpp/CallableSymbolMarker;", "<init>", "()V", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "getCallableId", "()Lorg/jetbrains/kotlin/name/CallableId;", "callableIdForRendering", "getCallableIdForRendering$annotations", "getCallableIdForRendering", "Lorg/jetbrains/kotlin/fir/symbols/impl/RenderingInternals;", "resolvedReturnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getResolvedReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "calculateReturnType", Argument.Delimiters.none, "resolvedReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getResolvedReturnType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "resolvedReceiverTypeRef", "getResolvedReceiverTypeRef", "resolvedReceiverType", "getResolvedReceiverType", "receiverParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "getReceiverParameterSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "contextParameterSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "getContextParameterSymbols", "()Ljava/util/List;", "resolvedStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "getResolvedStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "rawStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getRawStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "isLocal", Argument.Delimiters.none, "()Z", "typeParameterSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getTypeParameterSymbols", "ownTypeParameterSymbols", "getOwnTypeParameterSymbols", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getDeprecation", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsPerUseSite;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "deprecationsAreDefinitelyEmpty", "currentDeclarationDeprecationsAreDefinitelyEmpty", "currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree", "ensureType", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "toString", Argument.Delimiters.none, "callableIdAsString", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirCallableSymbol<D extends FirCallableDeclaration> extends FirBasedSymbol<D> implements CallableSymbolMarker {
    private final void ensureType(FirTypeRef typeRef) {
        if (typeRef == null || (typeRef instanceof FirResolvedTypeRef)) {
            return;
        }
        if (typeRef instanceof FirImplicitTypeRef) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE);
        } else {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.TYPES);
        }
    }

    @RenderingInternals
    public static /* synthetic */ void getCallableIdForRendering$annotations() {
    }

    public final void calculateReturnType() {
        ensureType(getFir().getReturnTypeRef());
        FirTypeRef returnTypeRef = getFir().getReturnTypeRef();
        if (returnTypeRef instanceof FirResolvedTypeRef) {
            return;
        }
        UtilsKt.errorInLazyResolve(this, "returnTypeRef", Reflection.getOrCreateKotlinClass(returnTypeRef.getClass()), Reflection.getOrCreateKotlinClass(FirResolvedTypeRef.class));
        wq6.a();
    }

    public final String callableIdAsString() {
        String string;
        CallableId callableId = getCallableId();
        if (callableId != null && (string = callableId.toString()) != null) {
            return string;
        }
        return "<local>/" + getName();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0062 A[Catch: all -> 0x004e, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x004e, blocks: (B:3:0x001f, B:8:0x0030, B:10:0x003b, B:12:0x0047, B:17:0x0050, B:21:0x0062), top: B:26:0x001f }] */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(getFir().getDeprecationsProvider(), org.jetbrains.kotlin.fir.declarations.EmptyDeprecationsProvider.INSTANCE) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree() {
        List<VersionRequirement> versionRequirements;
        FirLazyDeclarationResolver lazyDeclarationResolver = FirLazyDeclarationResolverKt.getLazyDeclarationResolver(getModuleData().getSession());
        Boolean bool = lazyDeclarationResolver.get_lazyResolveIsAllowed().get();
        lazyDeclarationResolver.get_lazyResolveIsAllowed().set(Boolean.FALSE);
        try {
            if (!(getOrigin() instanceof FirDeclarationOrigin.Java)) {
                if (getAnnotations().isEmpty() && ((versionRequirements = FirVersionRequirementsTableKeyKt.getVersionRequirements(getFir())) == null || versionRequirements.isEmpty())) {
                    if (getRawStatus().isOverride()) {
                    }
                }
                return true;
            }
            return false;
        } finally {
            lazyDeclarationResolver.get_lazyResolveIsAllowed().set(bool);
        }
    }

    public boolean deprecationsAreDefinitelyEmpty() {
        return currentDeclarationDeprecationsAreDefinitelyEmpty$org_jetbrains_kotlin_tree();
    }

    public abstract CallableId getCallableId();

    public final CallableId getCallableIdForRendering() {
        CallableId callableId = getCallableId();
        return callableId == null ? new CallableId(getName()) : callableId;
    }

    public final DeserializedContainerSource getContainerSource() {
        return getFir().getContainerSource();
    }

    public final List<FirValueParameterSymbol> getContextParameterSymbols() {
        List<FirValueParameter> contextParameters = getFir().getContextParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it = contextParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirValueParameter) it.next()).getSymbol());
        }
        return arrayList;
    }

    public final DeprecationsPerUseSite getDeprecation(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        if (deprecationsAreDefinitelyEmpty()) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS);
        return getFir().getDeprecationsProvider().getDeprecationsInfo(languageVersionSettings);
    }

    public final ConeSimpleKotlinType getDispatchReceiverType() {
        return getFir().getDispatchReceiverType();
    }

    public abstract Name getName();

    public final List<FirTypeParameterSymbol> getOwnTypeParameterSymbols() {
        List<FirTypeParameterRef> typeParameters = getFir().getTypeParameters();
        ArrayList arrayList = new ArrayList();
        for (FirTypeParameterRef firTypeParameterRef : typeParameters) {
            FirTypeParameter firTypeParameter = firTypeParameterRef instanceof FirTypeParameter ? (FirTypeParameter) firTypeParameterRef : null;
            FirTypeParameterSymbol symbol = firTypeParameter != null ? firTypeParameter.getSymbol() : null;
            if (symbol != null) {
                arrayList.add(symbol);
            }
        }
        return arrayList;
    }

    public final FirDeclarationStatus getRawStatus() {
        return getFir().getStatus();
    }

    public final FirReceiverParameterSymbol getReceiverParameterSymbol() {
        FirReceiverParameter receiverParameter = getFir().getReceiverParameter();
        if (receiverParameter != null) {
            return receiverParameter.getSymbol();
        }
        return null;
    }

    public final ConeKotlinType getResolvedReceiverType() {
        FirResolvedTypeRef resolvedReceiverTypeRef = getResolvedReceiverTypeRef();
        if (resolvedReceiverTypeRef != null) {
            return resolvedReceiverTypeRef.getConeType();
        }
        return null;
    }

    public final FirResolvedTypeRef getResolvedReceiverTypeRef() {
        FirReceiverParameterSymbol receiverParameterSymbol = getReceiverParameterSymbol();
        if (receiverParameterSymbol != null) {
            return receiverParameterSymbol.calculateResolvedTypeRef();
        }
        return null;
    }

    public final ConeKotlinType getResolvedReturnType() {
        return getResolvedReturnTypeRef().getConeType();
    }

    public final FirResolvedTypeRef getResolvedReturnTypeRef() {
        calculateReturnType();
        FirResolvedTypeRef returnTypeRef = getFir().getReturnTypeRef();
        returnTypeRef.getClass();
        return returnTypeRef;
    }

    public final FirResolvedDeclarationStatus getResolvedStatus() {
        return UtilsKt.resolvedStatus(getFir());
    }

    public final List<FirTypeParameterSymbol> getTypeParameterSymbols() {
        List<FirTypeParameterRef> typeParameters = getFir().getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterRef) it.next()).getSymbol());
        }
        return arrayList;
    }

    public final boolean isLocal() {
        return getFir().isLocal();
    }

    public String toString() {
        String strCallableIdAsString;
        boolean zIsBound = isBound();
        if (zIsBound) {
            strCallableIdAsString = callableIdAsString();
        } else {
            if (zIsBound) {
                bu8.a();
                return null;
            }
            strCallableIdAsString = "(unbound)";
        }
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + ' ' + strCallableIdAsString;
    }
}
