package org.jetbrains.kotlin.fir.extensions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\tj\u0002`\n0\b2\u0006\u0010\u000b\u001a\u00020\u0001\u001aX\u0010\f\u001a\u00020\u0001*\u0004\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u00060\tj\u0002`\n0\b2\u0006\u0010\u000b\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u001a\b\u0002\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00130\u0012¨\u0006\u0014"}, d2 = {"markedWithMetaAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "containingDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "metaAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "includeItself", "markedWithMetaAnnotationImpl", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "visited", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "resolvedCompilerAnnotations", "Lkotlin/Function1;", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPredicateBasedProviderImplKt {
    public static final boolean markedWithMetaAnnotation(FirAnnotation firAnnotation, FirSession firSession, FirDeclaration firDeclaration, Set<FqName> set, boolean z) {
        firAnnotation.getClass();
        firSession.getClass();
        firDeclaration.getClass();
        set.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firDeclaration.getSymbol(), FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS);
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        return markedWithMetaAnnotationImpl$default(coneType == null ? null : coneType, firSession, set, z, new LinkedHashSet(), null, 16, null);
    }

    public static final boolean markedWithMetaAnnotationImpl(ConeKotlinType coneKotlinType, FirSession firSession, Set<FqName> set, boolean z, Set<FirRegularClassSymbol> set2, Function1<? super FirRegularClassSymbol, ? extends List<? extends FirAnnotation>> function1) {
        firSession.getClass();
        set.getClass();
        set2.getClass();
        function1.getClass();
        if (coneKotlinType == null) {
            return false;
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneKotlinType, firSession);
        FirRegularClassSymbol firRegularClassSymbol = symbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) symbol : null;
        if (firRegularClassSymbol == null || !set2.add(firRegularClassSymbol)) {
            return false;
        }
        if (set.contains(firRegularClassSymbol.getClassId().asSingleFqName())) {
            return z;
        }
        Iterable iterable = (Iterable) function1.invoke(firRegularClassSymbol);
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) it.next()).getAnnotationTypeRef();
            FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType == null) {
                coneType = null;
            }
            if (coneType != null) {
                arrayList.add(coneType);
            }
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            FirSession firSession2 = firSession;
            Set<FqName> set3 = set;
            Set<FirRegularClassSymbol> set4 = set2;
            Function1<? super FirRegularClassSymbol, ? extends List<? extends FirAnnotation>> function2 = function1;
            if (markedWithMetaAnnotationImpl((ConeKotlinType) it2.next(), firSession2, set3, true, set4, function2)) {
                return true;
            }
            firSession = firSession2;
            set = set3;
            set2 = set4;
            function1 = function2;
        }
        return false;
    }

    public static /* synthetic */ boolean markedWithMetaAnnotationImpl$default(ConeKotlinType coneKotlinType, FirSession firSession, Set set, boolean z, Set set2, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.extensions.FirPredicateBasedProviderImplKt.markedWithMetaAnnotationImpl.1
                public Object get(Object obj2) {
                    return ((FirBasedSymbol) obj2).getResolvedCompilerAnnotationsWithClassIds();
                }
            };
        }
        return markedWithMetaAnnotationImpl(coneKotlinType, firSession, set, z, set2, function1);
    }
}
