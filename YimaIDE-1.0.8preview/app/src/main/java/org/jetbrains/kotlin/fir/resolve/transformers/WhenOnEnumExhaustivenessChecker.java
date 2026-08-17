package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ7\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016R\u00020\u0006j\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnEnumExhaustivenessChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker;", "<init>", "()V", "isApplicable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "subjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "computeMissingCases", Argument.Delimiters.none, "c", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Collection;)V", "ConditionChecker", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class WhenOnEnumExhaustivenessChecker extends WhenExhaustivenessChecker {
    public static final WhenOnEnumExhaustivenessChecker INSTANCE = new WhenOnEnumExhaustivenessChecker();

    private WhenOnEnumExhaustivenessChecker() {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public void computeMissingCases(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType, Collection<WhenMissingCase> collection) {
        Object objEmptyList;
        FirVariable subjectVariable;
        FirExpression initializer;
        Collection<DfaType> lowerTypesFromSmartCast;
        FirBasedSymbol<?> symbol;
        sessionHolder.getClass();
        firWhenExpression.getClass();
        coneKotlinType.getClass();
        collection.getClass();
        if (WhenSelfTypeExhaustivenessChecker.INSTANCE.isExhaustiveThroughSelfTypeCheck(sessionHolder, firWhenExpression, coneKotlinType)) {
            return;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, coneKotlinType);
        regularClassSymbol.getClass();
        List<FirDeclaration> declarations = ((FirRegularClass) regularClassSymbol.getFir()).getDeclarations();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = declarations.iterator();
        while (true) {
            objEmptyList = null;
            if (!it.hasNext()) {
                break;
            }
            Object obj = (FirDeclaration) it.next();
            objEmptyList = obj instanceof FirEnumEntry ? (FirEnumEntry) obj : null;
            if (objEmptyList != null) {
                linkedHashSet.add(objEmptyList);
            }
        }
        if (LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.DataFlowBasedExhaustiveness) && (subjectVariable = firWhenExpression.getSubjectVariable()) != null && (initializer = subjectVariable.getInitializer()) != null) {
            FirSmartCastExpression firSmartCastExpression = initializer instanceof FirSmartCastExpression ? (FirSmartCastExpression) initializer : null;
            if (firSmartCastExpression != null && (lowerTypesFromSmartCast = firSmartCastExpression.getLowerTypesFromSmartCast()) != null) {
                ArrayList arrayList = new ArrayList();
                for (DfaType dfaType : lowerTypesFromSmartCast) {
                    DfaType.Symbol symbol2 = dfaType instanceof DfaType.Symbol ? (DfaType.Symbol) dfaType : null;
                    FirDeclaration fir = (symbol2 == null || (symbol = symbol2.getSymbol()) == null) ? null : symbol.getFir();
                    if (fir != null) {
                        arrayList.add(fir);
                    }
                }
                objEmptyList = arrayList;
            }
            if (objEmptyList == null) {
                objEmptyList = CollectionsKt.emptyList();
            }
            TypeIntrinsics.asMutableCollection(linkedHashSet).removeAll((Collection) objEmptyList);
        }
        firWhenExpression.accept(ConditionChecker.INSTANCE, linkedHashSet);
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            collection.add(new WhenMissingCase.EnumCheckIsMissing(((FirEnumEntry) it2.next()).getSymbol().getCallableId()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.transformers.WhenExhaustivenessChecker
    public boolean isApplicable(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, coneKotlinType);
        return regularClassSymbol != null && ((FirRegularClass) regularClassSymbol.getFir()).getClassKind() == ClassKind.ENUM_CLASS;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenOnEnumExhaustivenessChecker$ConditionChecker;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker$AbstractConditionChecker;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "<init>", "()V", "visitEqualityOperatorCall", Argument.Delimiters.none, "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "data", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ConditionChecker extends WhenExhaustivenessChecker.AbstractConditionChecker<Set<FirEnumEntry>> {
        public static final ConditionChecker INSTANCE = new ConditionChecker();

        private ConditionChecker() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        public void visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Set<FirEnumEntry> data) throws KotlinIllegalArgumentExceptionWithAttachments {
            equalityOperatorCall.getClass();
            data.getClass();
            FirOperation operation = equalityOperatorCall.getOperation();
            if (operation == FirOperation.EQ || operation == FirOperation.IDENTITY) {
                FirResolvedNamedReference resolvedCallableReferenceUnsafe = ReferenceUtilsKt.toResolvedCallableReferenceUnsafe(equalityOperatorCall.getArgumentList().getArguments().get(1));
                FirBasedSymbol<?> resolvedSymbol = resolvedCallableReferenceUnsafe != null ? resolvedCallableReferenceUnsafe.getResolvedSymbol() : null;
                FirVariableSymbol firVariableSymbol = resolvedSymbol instanceof FirVariableSymbol ? (FirVariableSymbol) resolvedSymbol : null;
                if (firVariableSymbol == null) {
                    return;
                }
                D fir = firVariableSymbol.getFir();
                FirEnumEntry firEnumEntry = fir instanceof FirEnumEntry ? (FirEnumEntry) fir : null;
                if (firEnumEntry == null) {
                    return;
                }
                data.remove(firEnumEntry);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitEqualityOperatorCall(FirEqualityOperatorCall firEqualityOperatorCall, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
            visitEqualityOperatorCall(firEqualityOperatorCall, (Set<FirEnumEntry>) obj);
            return Unit.INSTANCE;
        }
    }
}
