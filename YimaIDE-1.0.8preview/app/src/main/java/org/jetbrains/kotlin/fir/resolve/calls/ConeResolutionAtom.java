package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedErrorAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.UnsafeExpressionUtility;
import org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \b2\u00020\u0001:\u0001\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractConeResolutionAtom;", "<init>", "()V", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Companion", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeAtomWithCandidate;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithPostponedChild;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtomWithSingleChild;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeSimpleLeafResolutionAtom;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeResolutionAtom extends AbstractConeResolutionAtom {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ ConeResolutionAtom(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom
    public abstract FirExpression getExpression();

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007b\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\b¢\u0006\u0002\b\bJ\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0014\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007b\u0002\b\fJ\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002J\f\u0010\u000f\u001a\u00020\u000e*\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom$Companion;", Argument.Delimiters.none, "<init>", "()V", "createRawAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "createRawAtomNullable", "Lkotlin/jvm/JvmName;", ModuleXmlParser.NAME, "createRawAtomForPotentiallyUnresolvedExpression", "Lorg/jetbrains/kotlin/fir/expressions/UnsafeExpressionUtility;", "allowUnresolvedExpression", Argument.Delimiters.none, "shouldAlternativeBeResolved", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "createRawAtomForResolvable", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final ConeResolutionAtom createRawAtom(FirExpression expression, boolean allowUnresolvedExpression) {
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            Object[] objArr4 = 0;
            Object[] objArr5 = 0;
            Object[] objArr6 = 0;
            if (expression == 0) {
                return null;
            }
            int i = 2;
            if (expression instanceof FirAnonymousFunctionExpression) {
                return new ConeResolutionAtomWithPostponedChild(expression, objArr6 == true ? 1 : 0, i, objArr5 == true ? 1 : 0);
            }
            if (expression instanceof FirCallableReferenceAccess) {
                return FirTypeUtilsKt.getHasResolvedType(expression) ? new ConeSimpleLeafResolutionAtom(expression, allowUnresolvedExpression) : new ConeResolutionAtomWithPostponedChild(expression, objArr4 == true ? 1 : 0, i, objArr3 == true ? 1 : 0);
            }
            if (expression instanceof FirPropertyAccessExpression) {
                return (ContextSensitiveResolutionUtilsKt.shouldBeResolvedInContextSensitiveMode((FirPropertyAccessExpression) expression) || shouldAlternativeBeResolved((FirQualifierWithContextSensitiveAlternative) expression)) ? new ConeResolutionAtomWithPostponedChild(expression, createRawAtomForResolvable(expression, allowUnresolvedExpression)) : createRawAtomForResolvable(expression, allowUnresolvedExpression);
            }
            if ((expression instanceof FirResolvedQualifier) && shouldAlternativeBeResolved((FirQualifierWithContextSensitiveAlternative) expression)) {
                return new ConeResolutionAtomWithPostponedChild(expression, createRawAtomForResolvable(expression, allowUnresolvedExpression));
            }
            if (expression instanceof FirCollectionLiteral) {
                return new ConeResolutionAtomWithPostponedChild(expression, objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
            }
            if (expression instanceof FirResolvable) {
                return createRawAtomForResolvable(expression, allowUnresolvedExpression);
            }
            if (expression instanceof FirSafeCallExpression) {
                FirStatement selector = ((FirSafeCallExpression) expression).getSelector();
                FirExpression firExpression = selector instanceof FirExpression ? (FirExpression) selector : null;
                return createRawAtom$createConeResolutionAtomWithSingleChild(expression, allowUnresolvedExpression, firExpression != null ? FirExpressionUtilKt.unwrapSmartcastExpression(firExpression) : null);
            }
            if (expression instanceof FirWrappedArgumentExpression) {
                return createRawAtom$createConeResolutionAtomWithSingleChild(expression, allowUnresolvedExpression, ((FirWrappedArgumentExpression) expression).getExpression());
            }
            if (expression instanceof FirErrorExpression) {
                return createRawAtom$createConeResolutionAtomWithSingleChild(expression, allowUnresolvedExpression, ((FirErrorExpression) expression).getExpression());
            }
            if (expression instanceof FirQualifiedErrorAccessExpression) {
                return createRawAtom$createConeResolutionAtomWithSingleChild(expression, allowUnresolvedExpression, ((FirQualifiedErrorAccessExpression) expression).getSelector());
            }
            return expression instanceof FirBlock ? createRawAtom$createConeResolutionAtomWithSingleChild(expression, allowUnresolvedExpression, UtilsKt.getLastExpression((FirBlock) expression)) : new ConeSimpleLeafResolutionAtom(expression, allowUnresolvedExpression);
        }

        private static final ConeResolutionAtomWithSingleChild createRawAtom$createConeResolutionAtomWithSingleChild(FirExpression firExpression, boolean z, FirExpression firExpression2) {
            return new ConeResolutionAtomWithSingleChild(firExpression, ConeResolutionAtom.INSTANCE.createRawAtom(firExpression2, z));
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final ConeResolutionAtom createRawAtomForResolvable(FirExpression expression, boolean allowUnresolvedExpression) {
            FirResolvable firResolvable = expression instanceof FirResolvable ? (FirResolvable) expression : null;
            Candidate candidate = firResolvable != null ? CandidateFactoryKt.candidate(firResolvable) : null;
            return candidate == null ? new ConeSimpleLeafResolutionAtom(expression, allowUnresolvedExpression) : new ConeAtomWithCandidate(expression, candidate);
        }

        private final boolean shouldAlternativeBeResolved(FirQualifierWithContextSensitiveAlternative firQualifierWithContextSensitiveAlternative) {
            return firQualifierWithContextSensitiveAlternative.getContextSensitiveAlternative() != null;
        }

        @UnsafeExpressionUtility
        public final ConeResolutionAtom createRawAtomForPotentiallyUnresolvedExpression(FirExpression expression) {
            expression.getClass();
            ConeResolutionAtom coneResolutionAtomCreateRawAtom = createRawAtom(expression, true);
            coneResolutionAtomCreateRawAtom.getClass();
            return coneResolutionAtomCreateRawAtom;
        }

        public final ConeResolutionAtom createRawAtomNullable(FirExpression expression) {
            return createRawAtom(expression, false);
        }

        private Companion() {
        }

        public final ConeResolutionAtom createRawAtom(FirExpression expression) {
            expression.getClass();
            ConeResolutionAtom coneResolutionAtomCreateRawAtom = createRawAtom(expression, false);
            coneResolutionAtomCreateRawAtom.getClass();
            return coneResolutionAtomCreateRawAtom;
        }
    }

    private ConeResolutionAtom() {
    }
}
