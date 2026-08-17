package org.jetbrains.kotlin.fir.declarations;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.AnnotationTargetUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a&\u0010\u000b\u001a\u00020\f*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e\" \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"useSiteTargetsFromMetaAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "findUseSiteTargets", "USE_SITE_TARGET_NAME_MAP", Argument.Delimiters.none, Argument.Delimiters.none, "DEFAULT_USE_SITE_TARGETS", "forEachAnnotationTarget", Argument.Delimiters.none, "action", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationTargetUtilsKt {
    private static final Set<AnnotationUseSiteTarget> DEFAULT_USE_SITE_TARGETS;
    private static final Map<String, Set<AnnotationUseSiteTarget>> USE_SITE_TARGET_NAME_MAP;

    static {
        Map<String, Set<AnnotationUseSiteTarget>> mapMapOf = MapsKt.mapOf(new Pair[]{TuplesKt.to("FIELD", SetsKt.setOf(new AnnotationUseSiteTarget[]{AnnotationUseSiteTarget.FIELD, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD})), TuplesKt.to("FILE", SetsKt.setOf(AnnotationUseSiteTarget.FILE)), TuplesKt.to("PROPERTY", SetsKt.setOf(AnnotationUseSiteTarget.PROPERTY)), TuplesKt.to("PROPERTY_GETTER", SetsKt.setOf(AnnotationUseSiteTarget.PROPERTY_GETTER)), TuplesKt.to("PROPERTY_SETTER", SetsKt.setOf(AnnotationUseSiteTarget.PROPERTY_SETTER)), TuplesKt.to("VALUE_PARAMETER", SetsKt.setOf(new AnnotationUseSiteTarget[]{AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER, AnnotationUseSiteTarget.RECEIVER, AnnotationUseSiteTarget.SETTER_PARAMETER}))});
        USE_SITE_TARGET_NAME_MAP = mapMapOf;
        Collection<Set<AnnotationUseSiteTarget>> collectionValues = mapMapOf.values();
        Set setEmptySet = SetsKt.emptySet();
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            setEmptySet = SetsKt.plus(setEmptySet, (Set) it.next());
        }
        DEFAULT_USE_SITE_TARGETS = SetsKt.minus(setEmptySet, SetsKt.setOf(AnnotationUseSiteTarget.FILE));
    }

    private static final Set<AnnotationUseSiteTarget> findUseSiteTargets(FirAnnotation firAnnotation, FirSession firSession) {
        final Set setCreateSetBuilder = SetsKt.createSetBuilder();
        forEachAnnotationTarget(firAnnotation, firSession, new Function1() { // from class: j90
            public final Object invoke(Object obj) {
                return AnnotationTargetUtilsKt.findUseSiteTargets$lambda$0$0(setCreateSetBuilder, (Name) obj);
            }
        });
        return SetsKt.build(setCreateSetBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit findUseSiteTargets$lambda$0$0(Set set, Name name) {
        name.getClass();
        Set<AnnotationUseSiteTarget> set2 = USE_SITE_TARGET_NAME_MAP.get(name.getIdentifier());
        if (set2 != null) {
            set.addAll(set2);
        }
        return Unit.INSTANCE;
    }

    public static final void forEachAnnotationTarget(FirAnnotation firAnnotation, FirSession firSession, Function1<? super Name, Unit> function1) {
        List<FirExpression> listUnwrapAndFlattenArgument;
        firAnnotation.getClass();
        firSession.getClass();
        function1.getClass();
        if (firAnnotation instanceof FirAnnotationCall) {
            Iterator<FirExpression> it = ((FirAnnotationCall) firAnnotation).getArgumentList().getArguments().iterator();
            while (it.hasNext()) {
                Iterator<T> it2 = FirExpressionUtilKt.unwrapAndFlattenArgument(it.next(), true).iterator();
                while (it2.hasNext()) {
                    forEachAnnotationTarget$take(firSession, function1, (FirExpression) it2.next());
                }
            }
            return;
        }
        FirExpression firExpression = firAnnotation.getArgumentMapping().getMapping().get(StandardClassIds$Annotations.ParameterNames.INSTANCE.getTargetAllowedTargets());
        if (firExpression == null || (listUnwrapAndFlattenArgument = FirExpressionUtilKt.unwrapAndFlattenArgument(firExpression, true)) == null) {
            return;
        }
        Iterator<T> it3 = listUnwrapAndFlattenArgument.iterator();
        while (it3.hasNext()) {
            forEachAnnotationTarget$take(firSession, function1, (FirExpression) it3.next());
        }
    }

    private static final void forEachAnnotationTarget$take(FirSession firSession, Function1<? super Name, Unit> function1, FirExpression firExpression) {
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firExpression, firSession);
        if (resolvedCallableSymbol == null) {
            return;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(resolvedCallableSymbol);
        if (Intrinsics.areEqual(coneClassLikeLookupTagContainingClassLookupTag != null ? coneClassLikeLookupTagContainingClassLookupTag.getClassId() : null, StandardClassIds.INSTANCE.getAnnotationTarget())) {
            function1.invoke(resolvedCallableSymbol.getName());
        }
    }

    public static final Set<AnnotationUseSiteTarget> useSiteTargetsFromMetaAnnotation(FirAnnotation firAnnotation, FirSession firSession) {
        List<FirAnnotation> annotations;
        Object next;
        Set<AnnotationUseSiteTarget> setFindUseSiteTargets;
        firAnnotation.getClass();
        firSession.getClass();
        FirRegularClass annotationClass = FirAnnotationUtilsKt.toAnnotationClass(firAnnotation, firSession);
        if (annotationClass != null && (annotations = annotationClass.getAnnotations()) != null) {
            Iterator<T> it = annotations.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassIdSafe((FirAnnotation) next, firSession), StandardClassIds$Annotations.INSTANCE.getTarget()));
            FirAnnotation firAnnotation2 = (FirAnnotation) next;
            if (firAnnotation2 != null && (setFindUseSiteTargets = findUseSiteTargets(firAnnotation2, firSession)) != null) {
                return setFindUseSiteTargets;
            }
        }
        return DEFAULT_USE_SITE_TARGETS;
    }
}
