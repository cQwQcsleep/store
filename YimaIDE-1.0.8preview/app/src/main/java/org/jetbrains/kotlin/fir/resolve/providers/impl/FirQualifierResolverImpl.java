package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.resolve.FirQualifierResolver;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J(\u0010\u0010\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0012\u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirQualifierResolverImpl;", "Lorg/jetbrains/kotlin/fir/resolve/FirQualifierResolver;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "resolveSymbolWithPrefix", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "prefix", "Lorg/jetbrains/kotlin/name/ClassId;", "remainingParts", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "resolveFullyQualifiedSymbol", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "parts", "toFqName", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirQualifierResolverImpl extends FirQualifierResolver implements SessionHolder {
    private final FirSession session;

    public FirQualifierResolverImpl(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    private final FqName toFqName(List<? extends FirQualifierPart> list) {
        FqName fqNameChild = FqName.ROOT;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            fqNameChild = fqNameChild.child(((FirQualifierPart) it.next()).getName());
        }
        return fqNameChild;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.FirQualifierResolver
    public Pair<FirClassifierSymbol<?>, FirResolvedSymbolOrigin> resolveFullyQualifiedSymbol(List<? extends FirQualifierPart> parts) {
        Name name;
        parts.getClass();
        FirQualifierResolver.Companion companion = FirQualifierResolver.INSTANCE;
        if (companion.isRootIdePackageAllowed(this)) {
            FirQualifierPart firQualifierPart = (FirQualifierPart) CollectionsKt.firstOrNull(parts);
            if (Intrinsics.areEqual((firQualifierPart == null || (name = firQualifierPart.getName()) == null) ? null : name.asString(), "_root_ide_package_")) {
                Pair<FirClassifierSymbol<?>, FirResolvedSymbolOrigin> pairResolveFullyQualifiedSymbol = resolveFullyQualifiedSymbol(CollectionsKt.drop(parts, 1));
                if (pairResolveFullyQualifiedSymbol != null) {
                    return companion.isRootIdePackageDeprecated(this) ? Pair.copy$default(pairResolveFullyQualifiedSymbol, (Object) null, FirResolvedSymbolOrigin.QualifiedWithDeprecatedRootIdePackage, 1, (Object) null) : pairResolveFullyQualifiedSymbol;
                }
                return null;
            }
        }
        FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(getSession());
        if (parts.size() > 1) {
            ArrayList arrayList = new ArrayList();
            List<? extends FirQualifierPart> mutableList = CollectionsKt.toMutableList(parts);
            while (!mutableList.isEmpty()) {
                arrayList.add(0, CollectionsKt.last(mutableList));
                mutableList.remove(CollectionsKt.getLastIndex(mutableList));
                FirClassLikeSymbol<?> classLikeSymbolByClassId = symbolProvider.getClassLikeSymbolByClassId(new ClassId(toFqName(mutableList), toFqName(arrayList), false));
                if (classLikeSymbolByClassId != null) {
                    return TuplesKt.to(classLikeSymbolByClassId, FirResolvedSymbolOrigin.Qualified);
                }
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.FirQualifierResolver
    public FirClassifierSymbol<?> resolveSymbolWithPrefix(ClassId prefix, List<? extends FirQualifierPart> remainingParts) {
        prefix.getClass();
        remainingParts.getClass();
        FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(getSession());
        FqName packageFqName = prefix.getPackageFqName();
        FqName relativeClassName = prefix.getRelativeClassName();
        Iterator<T> it = remainingParts.iterator();
        while (it.hasNext()) {
            relativeClassName = relativeClassName.child(((FirQualifierPart) it.next()).getName());
        }
        return symbolProvider.getClassLikeSymbolByClassId(new ClassId(packageFqName, relativeClassName, false));
    }
}
