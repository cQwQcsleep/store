package org.jetbrains.kotlin.fir.symbols;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0007b\u0002\b\u0006\u001a\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0007b\u0002\b\u0006\u001a\u001e\u0010\b\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a\u0018\u0010\n\u001a\u00020\u000b*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0007b\u0002\b\u0006\u001a\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002¨\u0006\u000f"}, d2 = {"resolvedCompilerRequiredAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "anchorElement", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "resolvedAnnotationsWithArguments", "resolveAnnotationsWithArguments", Argument.Delimiters.none, "isDefinitelyEmpty", Argument.Delimiters.none, "resolvedAnnotationsWithClassIds", "resolvedAnnotationClassIds", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBasedSymbolKt {
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean isDefinitelyEmpty(FirAnnotationContainer firAnnotationContainer, FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (!firAnnotationContainer.getAnnotations().isEmpty()) {
            return false;
        }
        if (!(firBasedSymbol instanceof FirBackingFieldSymbol)) {
            return true;
        }
        List<FirAnnotation> annotations = ((FirBackingFieldSymbol) firBasedSymbol).getPropertySymbol().getAnnotations();
        if (!(annotations instanceof Collection) || !annotations.isEmpty()) {
            Iterator<T> it = annotations.iterator();
            while (it.hasNext()) {
                if (((FirAnnotation) it.next()).getUseSiteTarget() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private static final void resolveAnnotationsWithArguments(List<? extends FirAnnotation> list, FirBasedSymbol<?> firBasedSymbol) {
        FirResolvePhase firResolvePhase;
        List<? extends FirAnnotation> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            firResolvePhase = FirResolvePhase.TYPES;
        } else {
            for (FirAnnotationContainer firAnnotationContainer : list2) {
                if ((firAnnotationContainer instanceof FirAnnotationCall) && !((FirCall) firAnnotationContainer).getArgumentList().getArguments().isEmpty()) {
                    firResolvePhase = FirResolvePhase.ANNOTATION_ARGUMENTS;
                }
            }
            firResolvePhase = FirResolvePhase.TYPES;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, firResolvePhase);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<ClassId> resolvedAnnotationClassIds(FirAnnotationContainer firAnnotationContainer, FirBasedSymbol<?> firBasedSymbol) {
        List<FirAnnotation> listResolvedAnnotationsWithClassIds = resolvedAnnotationsWithClassIds(firAnnotationContainer, firBasedSymbol);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listResolvedAnnotationsWithClassIds.iterator();
        while (it.hasNext()) {
            ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(((FirAnnotation) it.next()).getAnnotationTypeRef()));
            ClassId classId = classLikeLookupTagIfAny != null ? classLikeLookupTagIfAny.getClassId() : null;
            if (classId != null) {
                arrayList.add(classId);
            }
        }
        return arrayList;
    }

    @SymbolInternals
    public static final List<FirAnnotation> resolvedAnnotationsWithArguments(FirAnnotationContainer firAnnotationContainer, FirBasedSymbol<?> firBasedSymbol) {
        firAnnotationContainer.getClass();
        firBasedSymbol.getClass();
        if (isDefinitelyEmpty(firAnnotationContainer, firBasedSymbol)) {
            return CollectionsKt.emptyList();
        }
        if (FirResolveStateKt.getResolvePhase(firBasedSymbol.getFir()).compareTo(FirResolvePhase.ANNOTATION_ARGUMENTS) >= 0) {
            return firAnnotationContainer.getAnnotations();
        }
        resolveAnnotationsWithArguments(firAnnotationContainer.getAnnotations(), firBasedSymbol);
        return firAnnotationContainer.getAnnotations();
    }

    @SymbolInternals
    public static final List<FirAnnotation> resolvedAnnotationsWithClassIds(FirAnnotationContainer firAnnotationContainer, FirBasedSymbol<?> firBasedSymbol) {
        firAnnotationContainer.getClass();
        firBasedSymbol.getClass();
        if (isDefinitelyEmpty(firAnnotationContainer, firBasedSymbol)) {
            return CollectionsKt.emptyList();
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, FirResolvePhase.TYPES);
        return firAnnotationContainer.getAnnotations();
    }

    @SymbolInternals
    public static final List<FirAnnotation> resolvedCompilerRequiredAnnotations(FirAnnotationContainer firAnnotationContainer, FirBasedSymbol<?> firBasedSymbol) {
        firAnnotationContainer.getClass();
        firBasedSymbol.getClass();
        if (firAnnotationContainer.getAnnotations().isEmpty()) {
            return CollectionsKt.emptyList();
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS);
        return firAnnotationContainer.getAnnotations();
    }
}
