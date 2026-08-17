package org.jetbrains.kotlin.fir.resolve;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001a\u0010\u0004\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u0006J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0004\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirOuterClassManager;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "outerLocalClassForNested", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/Map;)V", "outerClass", "classSymbol", "outerType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "classLikeType", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOuterClassManager {
    private final Map<FirClassLikeSymbol<?>, FirClassLikeSymbol<?>> outerLocalClassForNested;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public FirOuterClassManager(FirSession firSession, Map<FirClassLikeSymbol<?>, ? extends FirClassLikeSymbol<?>> map) {
        firSession.getClass();
        map.getClass();
        this.session = firSession;
        this.outerLocalClassForNested = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirClassLikeSymbol<?> outerClass(FirClassLikeSymbol<?> classSymbol) {
        classSymbol.getClass();
        if (!(classSymbol instanceof FirClassSymbol)) {
            return null;
        }
        if (((FirClassLikeDeclaration) classSymbol.getFir()).getIsLocal()) {
            return this.outerLocalClassForNested.get(classSymbol);
        }
        ClassId outerClassId = ((FirClassSymbol) classSymbol).getClassId().getOuterClassId();
        if (outerClassId == null) {
            return null;
        }
        return FirSymbolProviderKt.getSymbolProvider(this.session).getClassLikeSymbolByClassId(outerClassId);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0078  */
    /* JADX WARN: Multi-variable type inference failed */
    public final ConeClassLikeType outerType(ConeClassLikeType classLikeType) {
        FirClassLikeSymbol<?> firClassLikeSymbolOuterClass;
        int i;
        FirRegularClass firRegularClass;
        List<FirTypeParameterRef> typeParameters;
        classLikeType.getClass();
        FirSession firSession = this.session;
        ConeClassLikeType coneClassLikeTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(classLikeType, firSession, (Function1) null, 2, (Object) null);
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeTypeFullyExpandedType$default.getLookupTag(), firSession);
        if (symbol == null) {
            return null;
        }
        boolean z = symbol instanceof FirRegularClassSymbol;
        if ((z && !((FirMemberDeclaration) ((FirRegularClassSymbol) symbol).getFir()).getStatus().isInner()) || (firClassLikeSymbolOuterClass = outerClass(symbol)) == null) {
            return null;
        }
        FirRegularClassSymbol firRegularClassSymbol = z ? (FirRegularClassSymbol) symbol : null;
        if (firRegularClassSymbol == null || (firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir()) == null || (typeParameters = firRegularClass.getTypeParameters()) == null) {
            i = 0;
        } else {
            List<FirTypeParameterRef> list = typeParameters;
            if ((list instanceof Collection) && list.isEmpty()) {
                i = 0;
            } else {
                Iterator<T> it = list.iterator();
                i = 0;
                while (it.hasNext()) {
                    if ((((FirTypeParameterRef) it.next()) instanceof FirTypeParameter) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        }
        return TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) firClassLikeSymbolOuterClass, (ConeTypeProjection[]) ArraysKt.drop(coneClassLikeTypeFullyExpandedType$default.getTypeArguments(), i).toArray(new ConeTypeProjection[0]), false, (ConeAttributes) null, 6, (Object) null);
    }
}
