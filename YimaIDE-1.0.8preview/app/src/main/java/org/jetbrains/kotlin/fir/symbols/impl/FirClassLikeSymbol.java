package org.jetbrains.kotlin.fir.symbols.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.DeprecationsPerUseSite;
import org.jetbrains.kotlin.fir.declarations.EmptyDeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirVersionRequirementsTableKeyKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.mpp.ClassLikeSymbolMarker;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0011\b\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0011\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\n\u0010+\u001a\u00020,H\u0096\u0080\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0084\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u001c\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020&0%8F¢\u0006\u0006\u001a\u0004\b*\u0010(\u0082\u0001\u0002-.¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/mpp/ClassLikeSymbolMarker;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getLookupTag$annotations", "()V", "getLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "toLookupTag", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getOwnDeprecation", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsPerUseSite;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "deprecationsAreDefinitelyEmpty", Argument.Delimiters.none, "rawStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getRawStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "resolvedStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "getResolvedStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "typeParameterSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getTypeParameterSymbols", "()Ljava/util/List;", "ownTypeParameterSymbols", "getOwnTypeParameterSymbols", "toString", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirClassLikeSymbol<D extends FirClassLikeDeclaration> extends FirClassifierSymbol<D> implements ClassLikeSymbolMarker {
    private final ClassId classId;
    private final ConeClassLikeLookupTag lookupTag;

    private FirClassLikeSymbol(ClassId classId) {
        super(null);
        this.classId = classId;
        this.lookupTag = classId.isLocal() ? new ConeClassLikeLookupTagWithFixedSymbol(classId, this) : TypeConstructionUtilsKt.toLookupTag(classId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private final boolean deprecationsAreDefinitelyEmpty() {
        List<VersionRequirement> versionRequirements;
        if (getOrigin() instanceof FirDeclarationOrigin.Java) {
            return false;
        }
        return (getAnnotations().isEmpty() && ((versionRequirements = FirVersionRequirementsTableKeyKt.getVersionRequirements(getFir())) == null || versionRequirements.isEmpty())) || Intrinsics.areEqual(((FirClassLikeDeclaration) getFir()).getDeprecationsProvider(), EmptyDeprecationsProvider.INSTANCE);
    }

    public static /* synthetic */ void getLookupTag$annotations() {
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final ConeClassLikeLookupTag getLookupTag() {
        return this.lookupTag;
    }

    public final Name getName() {
        return this.classId.getShortClassName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final DeprecationsPerUseSite getOwnDeprecation(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        if (deprecationsAreDefinitelyEmpty()) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS);
        return ((FirClassLikeDeclaration) getFir()).getDeprecationsProvider().getDeprecationsInfo(languageVersionSettings);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirTypeParameterSymbol> getOwnTypeParameterSymbols() {
        List<FirTypeParameterRef> typeParameters = ((FirClassLikeDeclaration) getFir()).getTypeParameters();
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

    /* JADX WARN: Multi-variable type inference failed */
    public final FirDeclarationStatus getRawStatus() {
        return ((FirClassLikeDeclaration) getFir()).getStatus();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirResolvedDeclarationStatus getResolvedStatus() {
        return UtilsKt.resolvedStatus((FirMemberDeclaration) getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirTypeParameterSymbol> getTypeParameterSymbols() {
        List<FirTypeParameterRef> typeParameters = ((FirClassLikeDeclaration) getFir()).getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterRef) it.next()).getSymbol());
        }
        return arrayList;
    }

    public String toString() {
        return Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + ' ' + this.classId.asString();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol
    /* JADX INFO: renamed from: toLookupTag */
    public ConeClassLikeLookupTag getLookupTag() {
        return this.lookupTag;
    }

    public /* synthetic */ FirClassLikeSymbol(ClassId classId, DefaultConstructorMarker defaultConstructorMarker) {
        this(classId);
    }
}
