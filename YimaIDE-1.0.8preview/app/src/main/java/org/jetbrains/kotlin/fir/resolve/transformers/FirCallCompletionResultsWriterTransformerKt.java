package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.calls.CallToDeprecatedOverrideOfHidden;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionResultOverridesOtherToPreserveCompatibility;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallableReferenceInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeCallToDeprecatedOverrideOfHidden;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeResolutionResultOverridesOtherToPreserveCompatibility;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a \u0010\u0005\u001a\u00020\u0002*\u00020\u00012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\u001a\u0014\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\u0010\u001a\u00020\r*\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u001a#\u0010\u0010\u001a\u00020\r*\u00020\u000e2\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0014\"\u00020\u0013¢\u0006\u0002\u0010\u0015\u001a#\u0010\u0010\u001a\u00020\r*\u00020\u00162\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u0014\"\u00020\u0013¢\u0006\u0002\u0010\u0017\u001a$\u0010\u0018\u001a\u00020\r*\u00020\u000b2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\r0\u001aH\u0080\bø\u0001\u0000\u001a`\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u001f0\u001dj\u000e\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u001f` \"\u0004\b\u0000\u0010\u001e\"\b\b\u0001\u0010\u001f*\u00020!**\u0012\u0006\b\u0001\u0012\u0002H\u001e\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u001f0\u001dj\u0014\u0012\u0006\b\u0001\u0012\u0002H\u001e\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u001f` H\u0002\u001aH\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u001f` \"\u0004\b\u0000\u0010\u001f*\u001e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u0002H\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u0002H\u001f` \u001an\u0010$\u001a\u001e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002H\u001f0\u001dj\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002H\u001f` \"\u0004\b\u0000\u0010&\"\u0004\b\u0001\u0010%\"\u0004\b\u0002\u0010\u001f*\u001e\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H\u001f0\u001dj\u000e\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H\u001f` 2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H%0\u001aH\u0086\bø\u0001\u0000\u001a\u0018\u0010\"\u001a\b\u0012\u0004\u0012\u00020\b0\u0012*\b\u0012\u0004\u0012\u00020#0(H\u0002\u001a\f\u0010)\u001a\u00020\b*\u00020#H\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006*"}, d2 = {"getExpectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;", "argument", "Lorg/jetbrains/kotlin/fir/FirElement;", "toExpectedType", "argumentReplacements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "doesResolutionResultOverrideOtherToPreserveCompatibility", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "addNonFatalDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "candidate", "appendNonFatalDiagnostics", "newDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;[Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;[Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "ifLHSResolvedToType", "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Type;", "filterValuesNotNull", "Ljava/util/LinkedHashMap;", "K", "V", "Lkotlin/collections/LinkedHashMap;", Argument.Delimiters.none, "unwrapAtoms", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "mapKeysToLinkedMap", "K2", "K1", "transform", Argument.Delimiters.none, "unwrapAtom", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallCompletionResultsWriterTransformerKt {
    public static final void addNonFatalDiagnostics(FirQualifiedAccessExpression firQualifiedAccessExpression, Candidate candidate) {
        ConeDiagnostic diagnostic;
        firQualifiedAccessExpression.getClass();
        candidate.getClass();
        ArrayList arrayList = new ArrayList();
        CallInfo callInfo = candidate.getCallInfo();
        CallableReferenceInfo callableReferenceInfo = callInfo instanceof CallableReferenceInfo ? (CallableReferenceInfo) callInfo : null;
        if (callableReferenceInfo != null) {
            DoubleColonLHS lhs = callableReferenceInfo.getLhs();
            DoubleColonLHS.Type type = lhs instanceof DoubleColonLHS.Type ? (DoubleColonLHS.Type) lhs : null;
            if (type != null && (diagnostic = type.getDiagnostic()) != null) {
                arrayList.add(diagnostic);
            }
        }
        if (doesResolutionResultOverrideOtherToPreserveCompatibility(candidate)) {
            arrayList.add(ConeResolutionResultOverridesOtherToPreserveCompatibility.INSTANCE);
        }
        Iterator<ResolutionDiagnostic> it = candidate.getDiagnostics().iterator();
        while (it.hasNext()) {
            if (it.next() instanceof CallToDeprecatedOverrideOfHidden) {
                arrayList.add(ConeCallToDeprecatedOverrideOfHidden.INSTANCE);
            }
        }
        appendNonFatalDiagnostics(firQualifiedAccessExpression, arrayList);
    }

    public static final void appendNonFatalDiagnostics(FirQualifiedAccessExpression firQualifiedAccessExpression, List<? extends ConeDiagnostic> list) {
        firQualifiedAccessExpression.getClass();
        list.getClass();
        if (list.isEmpty()) {
            return;
        }
        firQualifiedAccessExpression.replaceNonFatalDiagnostics(CollectionsKt.plus(firQualifiedAccessExpression.getNonFatalDiagnostics(), list));
    }

    public static final boolean doesResolutionResultOverrideOtherToPreserveCompatibility(Candidate candidate) {
        candidate.getClass();
        return candidate.getDiagnostics().contains(ResolutionResultOverridesOtherToPreserveCompatibility.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <K, V> LinkedHashMap<K, V> filterValuesNotNull(LinkedHashMap<? extends K, ? extends V> linkedHashMap) {
        LinkedHashMap<K, V> linkedHashMap2 = new LinkedHashMap<>();
        for (Map.Entry<? extends K, ? extends V> entry : linkedHashMap.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (value != null) {
                linkedHashMap2.put(key, value);
            }
        }
        return linkedHashMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeKotlinType getExpectedType(ExpectedArgumentType expectedArgumentType, FirElement firElement) {
        if (expectedArgumentType instanceof ExpectedArgumentType.ArgumentsMap) {
            return ((ExpectedArgumentType.ArgumentsMap) expectedArgumentType).getMap().get(firElement);
        }
        if (expectedArgumentType instanceof ExpectedArgumentType.ExpectedType) {
            return ((ExpectedArgumentType.ExpectedType) expectedArgumentType).getType();
        }
        bu8.a();
        return null;
    }

    public static final void ifLHSResolvedToType(Candidate candidate, Function1<? super DoubleColonLHS.Type, Unit> function1) {
        candidate.getClass();
        function1.getClass();
        CallInfo callInfo = candidate.getCallInfo();
        CallableReferenceInfo callableReferenceInfo = callInfo instanceof CallableReferenceInfo ? (CallableReferenceInfo) callInfo : null;
        if (callableReferenceInfo == null) {
            return;
        }
        DoubleColonLHS lhs = callableReferenceInfo.getLhs();
        DoubleColonLHS.Type type = lhs instanceof DoubleColonLHS.Type ? (DoubleColonLHS.Type) lhs : null;
        if (type != null) {
            function1.invoke(type);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K1, K2, V> LinkedHashMap<K2, V> mapKeysToLinkedMap(LinkedHashMap<K1, V> linkedHashMap, Function1<? super K1, ? extends K2> function1) {
        linkedHashMap.getClass();
        function1.getClass();
        LinkedHashMap<K2, V> linkedHashMap2 = (LinkedHashMap<K2, V>) new LinkedHashMap();
        Iterator<T> it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap2.put(function1.invoke(entry.getKey()), entry.getValue());
        }
        return linkedHashMap2;
    }

    public static final ExpectedArgumentType toExpectedType(ConeKotlinType coneKotlinType, Map<FirElement, ? extends FirExpression> map) {
        coneKotlinType.getClass();
        return new ExpectedArgumentType.ExpectedType(coneKotlinType, map);
    }

    private static final FirExpression unwrapAtom(ConeResolutionAtom coneResolutionAtom) {
        FirExpression firExpressionUnwrapAtom;
        FirExpression firExpressionUnwrapAtom2;
        if (coneResolutionAtom instanceof ConeCollectionLiteralAtom) {
            ConeCollectionLiteralAtom coneCollectionLiteralAtom = (ConeCollectionLiteralAtom) coneResolutionAtom;
            ConeAtomWithCandidate subAtom = coneCollectionLiteralAtom.getSubAtom();
            return (subAtom == null || (firExpressionUnwrapAtom2 = unwrapAtom(subAtom)) == null) ? coneCollectionLiteralAtom.getExpression() : firExpressionUnwrapAtom2;
        }
        if (!(coneResolutionAtom instanceof ConeResolutionAtomWithPostponedChild)) {
            return coneResolutionAtom.getExpression();
        }
        ConeResolutionAtomWithPostponedChild coneResolutionAtomWithPostponedChild = (ConeResolutionAtomWithPostponedChild) coneResolutionAtom;
        ConeResolutionAtom subAtom2 = coneResolutionAtomWithPostponedChild.getSubAtom();
        return (subAtom2 == null || (firExpressionUnwrapAtom = unwrapAtom(subAtom2)) == null) ? coneResolutionAtomWithPostponedChild.getExpression() : firExpressionUnwrapAtom;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <V> LinkedHashMap<FirExpression, V> unwrapAtoms(LinkedHashMap<ConeResolutionAtom, V> linkedHashMap) {
        linkedHashMap.getClass();
        LinkedHashMap<FirExpression, V> linkedHashMap2 = (LinkedHashMap<FirExpression, V>) new LinkedHashMap();
        Iterator<T> it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap2.put(unwrapAtom((ConeResolutionAtom) entry.getKey()), entry.getValue());
        }
        return linkedHashMap2;
    }

    public static final void appendNonFatalDiagnostics(FirQualifiedAccessExpression firQualifiedAccessExpression, ConeDiagnostic... coneDiagnosticArr) {
        firQualifiedAccessExpression.getClass();
        coneDiagnosticArr.getClass();
        if (coneDiagnosticArr.length == 0) {
            return;
        }
        firQualifiedAccessExpression.replaceNonFatalDiagnostics(CollectionsKt.plus(firQualifiedAccessExpression.getNonFatalDiagnostics(), coneDiagnosticArr));
    }

    public static final void appendNonFatalDiagnostics(FirResolvedQualifier firResolvedQualifier, ConeDiagnostic... coneDiagnosticArr) {
        firResolvedQualifier.getClass();
        coneDiagnosticArr.getClass();
        if (coneDiagnosticArr.length == 0) {
            return;
        }
        firResolvedQualifier.replaceNonFatalDiagnostics(CollectionsKt.plus(firResolvedQualifier.getNonFatalDiagnostics(), coneDiagnosticArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<FirExpression> unwrapAtoms(Collection<? extends ConeResolutionAtom> collection) {
        Collection<? extends ConeResolutionAtom> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            arrayList.add(unwrapAtom((ConeResolutionAtom) it.next()));
        }
        return arrayList;
    }
}
