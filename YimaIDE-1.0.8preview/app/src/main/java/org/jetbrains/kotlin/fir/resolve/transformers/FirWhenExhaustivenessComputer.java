package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.fir.FirEnumWhenTrackerComponent;
import org.jetbrains.kotlin.fir.FirEnumWhenTrackerComponentKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatus;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirWhenExhaustivenessComputer;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\u000b\u001a\u00020\fR\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\rJ#\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u0012*\u00020\u000fH\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u0013J#\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015*\u00020\u000fH\u0002R\u00020\tj\u0006\u0010\u0016\u001a\u00020\t¢\u0006\u0002\u0010\u0017J'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0019\u001a\u00020\u000fH\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u001aJA\u0010\u001b\u001a\u00020\u001c*\b\u0012\u0004\u0012\u00020\b0\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u001fJ'\u0010 \u001a\u00020!2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#R\u00020\tj\u0006\u0010\u0016\u001a\u00020\t¢\u0006\u0002\u0010$J!\u0010%\u001a\u00020!2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010&J\f\u0010'\u001a\u00020(*\u00020\fH\u0002J)\u0010 \u001a\u00020!2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002R\u00020\tj\u0006\u0010\u0016\u001a\u00020\t¢\u0006\u0002\u0010)J+\u0010*\u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u00020\tj\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010,R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-²\u0006\n\u0010.\u001a\u00020!X\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirWhenExhaustivenessComputer;", Argument.Delimiters.none, "<init>", "()V", "exhaustivenessCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/transformers/WhenExhaustivenessChecker;", "computeAllMissingCases", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)Ljava/util/List;", "getSubjectType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "minimumBoundIfFlexible", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "unwrapTypeParameterAndIntersectionTypes", Argument.Delimiters.none, "c", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Ljava/util/Collection;", "getCheckers", "subjectType", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Ljava/util/List;", "collectMissingCases", Argument.Delimiters.none, Argument.Delimiters.none, "checkers", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "computeExhaustivenessStatus", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "processExhaustivenessCheck", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "hasElseBranch", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "computeStatusForNonIntersectionType", "unwrappedSubjectType", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;)Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "org.jetbrains.kotlin:resolve", "minimumStatus"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWhenExhaustivenessComputer {
    public static final FirWhenExhaustivenessComputer INSTANCE = new FirWhenExhaustivenessComputer();
    private static final List<WhenExhaustivenessChecker> exhaustivenessCheckers = CollectionsKt.listOf(new WhenExhaustivenessChecker[]{WhenOnBooleanExhaustivenessChecker.INSTANCE, WhenOnEnumExhaustivenessChecker.INSTANCE, WhenOnSealedClassExhaustivenessChecker.INSTANCE, WhenOnNothingExhaustivenessChecker.INSTANCE});

    private FirWhenExhaustivenessComputer() {
    }

    public static ExhaustivenessStatus a(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeRigidType coneRigidType) {
        return INSTANCE.computeExhaustivenessStatus(sessionHolder, firWhenExpression, coneRigidType);
    }

    private final void collectMissingCases(SessionHolder sessionHolder, List<WhenMissingCase> list, List<? extends WhenExhaustivenessChecker> list2, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType) {
        Iterator<? extends WhenExhaustivenessChecker> it = list2.iterator();
        while (it.hasNext()) {
            it.next().computeMissingCases(sessionHolder, firWhenExpression, coneKotlinType, list);
        }
        if (list.isEmpty() && firWhenExpression.getBranches().isEmpty()) {
            list.add(WhenMissingCase.Unknown.INSTANCE);
        }
    }

    private final ExhaustivenessStatus computeExhaustivenessStatus(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, ConeKotlinType coneKotlinType) {
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(sessionHolder.getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE);
        if (coneKotlinTypeApproximateToSuperType != null) {
            coneKotlinType = coneKotlinTypeApproximateToSuperType;
        }
        if (firWhenExpression.getBranches().isEmpty() && ConeBuiltinTypeUtilsKt.isNothing(coneKotlinType)) {
            return ExhaustivenessStatus.ExhaustiveAsNothing.INSTANCE;
        }
        ExhaustivenessStatus exhaustivenessStatus = null;
        for (ConeKotlinType coneKotlinType2 : unwrapTypeParameterAndIntersectionTypes(sessionHolder, coneKotlinType)) {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(sessionHolder, coneKotlinType2);
            if (regularClassSymbol == null || !regularClassSymbol.getRawStatus().isExpect() || Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType2), StandardClassIds.INSTANCE.getBoolean())) {
                ExhaustivenessStatus exhaustivenessStatusComputeStatusForNonIntersectionType = computeStatusForNonIntersectionType(sessionHolder, coneKotlinType2, firWhenExpression);
                if (exhaustivenessStatusComputeStatusForNonIntersectionType == ExhaustivenessStatus.ProperlyExhaustive.INSTANCE) {
                    exhaustivenessStatus = exhaustivenessStatusComputeStatusForNonIntersectionType;
                    break;
                }
                if (exhaustivenessStatus == null && exhaustivenessStatusComputeStatusForNonIntersectionType != null) {
                    exhaustivenessStatus = exhaustivenessStatusComputeStatusForNonIntersectionType;
                }
            }
        }
        return exhaustivenessStatus == null ? ExhaustivenessStatus.NotExhaustive.INSTANCE.noElseBranch(coneKotlinType) : exhaustivenessStatus;
    }

    private final ExhaustivenessStatus computeStatusForNonIntersectionType(SessionHolder sessionHolder, ConeKotlinType coneKotlinType, FirWhenExpression firWhenExpression) {
        List<WhenExhaustivenessChecker> checkers = getCheckers(sessionHolder, coneKotlinType);
        if (checkers.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        collectMissingCases(sessionHolder, arrayList, checkers, firWhenExpression, coneKotlinType);
        return arrayList.isEmpty() ? ExhaustivenessStatus.ProperlyExhaustive.INSTANCE : new ExhaustivenessStatus.NotExhaustive(arrayList, coneKotlinType);
    }

    private final List<WhenExhaustivenessChecker> getCheckers(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        for (Object obj : exhaustivenessCheckers) {
            if (((WhenExhaustivenessChecker) obj).isApplicable(sessionHolder, coneKotlinType)) {
                list.add(obj);
            }
        }
        if (!list.isEmpty() && ConeTypeUtilsKt.isMarkedNullable(coneKotlinType)) {
            listCreateListBuilder.add(WhenOnNullableExhaustivenessChecker.INSTANCE);
        }
        if (listCreateListBuilder.isEmpty()) {
            listCreateListBuilder.add(WhenSelfTypeExhaustivenessChecker.INSTANCE);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:16:0x002b  */
    /* JADX WARN: Code duplicated, block: B:23:0x003b  */
    private final ConeKotlinType getSubjectType(SessionHolder sessionHolder, FirWhenExpression firWhenExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirVariable subjectVariable;
        FirExpression initializer;
        ConeKotlinType resolvedType;
        FirTypeRef returnTypeRef;
        FirVariable subjectVariable2 = firWhenExpression.getSubjectVariable();
        if (subjectVariable2 == null) {
            subjectVariable = firWhenExpression.getSubjectVariable();
            if (subjectVariable != null || (initializer = subjectVariable.getInitializer()) == null) {
                return null;
            }
            resolvedType = FirTypeUtilsKt.getResolvedType(initializer);
        } else {
            if (FirExpressionUtilKt.isImplicitWhenSubjectVariable(subjectVariable2)) {
                subjectVariable2 = null;
            } else if (LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.ImprovedExhaustivenessCheckForSubjectVariable24)) {
                FirResolvedTypeRef returnTypeRef2 = subjectVariable2.getReturnTypeRef();
                FirResolvedTypeRef firResolvedTypeRef = returnTypeRef2 instanceof FirResolvedTypeRef ? returnTypeRef2 : null;
                if ((firResolvedTypeRef != null ? firResolvedTypeRef.getDelegatedTypeRef() : null) == null) {
                    subjectVariable2 = null;
                }
            }
            if (subjectVariable2 == null || (returnTypeRef = subjectVariable2.getReturnTypeRef()) == null || (resolvedType = FirTypeUtilsKt.getConeType(returnTypeRef)) == null) {
                subjectVariable = firWhenExpression.getSubjectVariable();
                if (subjectVariable != null) {
                }
                return null;
            }
        }
        return TypeExpansionUtilsKt.fullyExpandedType(sessionHolder, resolvedType);
    }

    private final boolean hasElseBranch(FirWhenExpression firWhenExpression) {
        List<FirWhenBranch> branches = firWhenExpression.getBranches();
        if ((branches instanceof Collection) && branches.isEmpty()) {
            return false;
        }
        Iterator<T> it = branches.iterator();
        while (it.hasNext()) {
            if (((FirWhenBranch) it.next()).getCondition() instanceof FirElseIfTrueCondition) {
                return true;
            }
        }
        return false;
    }

    private final ConeRigidType minimumBoundIfFlexible(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        if (!(coneKotlinType instanceof ConeDynamicType)) {
            if (coneKotlinType instanceof ConeFlexibleType) {
                return ((ConeFlexibleType) coneKotlinType).getLowerBound();
            }
            if (coneKotlinType instanceof ConeRigidType) {
                return (ConeRigidType) coneKotlinType;
            }
            bu8.a();
            return null;
        }
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.ImprovedExhaustivenessChecksIn21);
        if (zIsEnabled) {
            return ((ConeDynamicType) coneKotlinType).getUpperBound();
        }
        if (!zIsEnabled) {
            return ((ConeDynamicType) coneKotlinType).getLowerBound();
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final ExhaustivenessStatus processExhaustivenessCheck(final SessionHolder sessionHolder, final FirWhenExpression firWhenExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType subjectType = getSubjectType(sessionHolder, firWhenExpression);
        if (subjectType == null) {
            return hasElseBranch(firWhenExpression) ? ExhaustivenessStatus.ProperlyExhaustive.INSTANCE : ExhaustivenessStatus.NotExhaustive.INSTANCE.noElseBranch(null);
        }
        final ConeRigidType coneRigidTypeMinimumBoundIfFlexible = minimumBoundIfFlexible(sessionHolder, subjectType);
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: rg5
            public final Object invoke() {
                return FirWhenExhaustivenessComputer.a(sessionHolder, firWhenExpression, coneRigidTypeMinimumBoundIfFlexible);
            }
        });
        if (!hasElseBranch(firWhenExpression)) {
            return processExhaustivenessCheck$lambda$1(lazy);
        }
        ExhaustivenessStatus exhaustivenessStatusProcessExhaustivenessCheck$computeUpperBoundStatus = processExhaustivenessCheck$computeUpperBoundStatus(subjectType, coneRigidTypeMinimumBoundIfFlexible, sessionHolder, firWhenExpression, lazy);
        ExhaustivenessStatus.ProperlyExhaustive properlyExhaustive = ExhaustivenessStatus.ProperlyExhaustive.INSTANCE;
        return Intrinsics.areEqual(exhaustivenessStatusProcessExhaustivenessCheck$computeUpperBoundStatus, properlyExhaustive) ? ExhaustivenessStatus.RedundantlyExhaustive.INSTANCE : properlyExhaustive;
    }

    private static final ExhaustivenessStatus processExhaustivenessCheck$computeUpperBoundStatus(ConeKotlinType coneKotlinType, ConeRigidType coneRigidType, SessionHolder sessionHolder, FirWhenExpression firWhenExpression, Lazy<? extends ExhaustivenessStatus> lazy) {
        ConeRigidType coneRigidTypeUpperBoundIfFlexible = ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinType);
        return Intrinsics.areEqual(coneRigidTypeUpperBoundIfFlexible, coneRigidType) ? processExhaustivenessCheck$lambda$1(lazy) : INSTANCE.computeExhaustivenessStatus(sessionHolder, firWhenExpression, coneRigidTypeUpperBoundIfFlexible);
    }

    private static final ExhaustivenessStatus processExhaustivenessCheck$lambda$1(Lazy<? extends ExhaustivenessStatus> lazy) {
        return (ExhaustivenessStatus) lazy.getValue();
    }

    private final Collection<ConeKotlinType> unwrapTypeParameterAndIntersectionTypes(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeIntersectionType) {
            return ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
        }
        if ((coneKotlinType instanceof ConeTypeParameterType) && LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.ImprovedExhaustivenessChecksIn21)) {
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            List list = listCreateListBuilder;
            Iterator<T> it = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getResolvedBounds().iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(list, INSTANCE.unwrapTypeParameterAndIntersectionTypes(sessionHolder, ((FirResolvedTypeRef) it.next()).getConeType()));
            }
            listCreateListBuilder.add(coneKotlinType);
            return CollectionsKt.build(listCreateListBuilder);
        }
        if (!(coneKotlinType instanceof ConeDefinitelyNotNullType) || !LanguageVersionUtilsKt.isEnabled(sessionHolder, LanguageFeature.ImprovedExhaustivenessChecksIn21)) {
            return CollectionsKt.listOf(coneKotlinType);
        }
        Collection<ConeKotlinType> collectionUnwrapTypeParameterAndIntersectionTypes = unwrapTypeParameterAndIntersectionTypes(sessionHolder, ((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionUnwrapTypeParameterAndIntersectionTypes, 10));
        Iterator<T> it2 = collectionUnwrapTypeParameterAndIntersectionTypes.iterator();
        while (it2.hasNext()) {
            arrayList.add(TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default((ConeKotlinType) it2.next(), (ConeTypeContext) TypeComponentsKt.getTypeContext(sessionHolder.getSession()), false, false, 6, (Object) null));
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final List<WhenMissingCase> computeAllMissingCases(SessionHolder sessionHolder, FirWhenExpression firWhenExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeRigidType coneRigidTypeMinimumBoundIfFlexible;
        sessionHolder.getClass();
        firWhenExpression.getClass();
        ConeKotlinType subjectType = getSubjectType(sessionHolder, firWhenExpression);
        if (subjectType == null || (coneRigidTypeMinimumBoundIfFlexible = minimumBoundIfFlexible(sessionHolder, subjectType)) == null) {
            return ExhaustivenessStatus.NotExhaustive.INSTANCE.getNO_ELSE_BRANCH_REASONS();
        }
        List<WhenMissingCase> listCreateListBuilder = CollectionsKt.createListBuilder();
        for (ConeKotlinType coneKotlinType : INSTANCE.unwrapTypeParameterAndIntersectionTypes(sessionHolder, coneRigidTypeMinimumBoundIfFlexible)) {
            FirWhenExhaustivenessComputer firWhenExhaustivenessComputer = INSTANCE;
            firWhenExhaustivenessComputer.collectMissingCases(sessionHolder, listCreateListBuilder, firWhenExhaustivenessComputer.getCheckers(sessionHolder, coneKotlinType), firWhenExpression, coneKotlinType);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final ExhaustivenessStatus computeExhaustivenessStatus(SessionHolder sessionHolder, FirWhenExpression firWhenExpression, FirFile firFile) throws KotlinIllegalArgumentExceptionWithAttachments {
        sessionHolder.getClass();
        firWhenExpression.getClass();
        firFile.getClass();
        ExhaustivenessStatus exhaustivenessStatusProcessExhaustivenessCheck = processExhaustivenessCheck(sessionHolder, firWhenExpression);
        FirEnumWhenTrackerComponent enumWhenTracker = FirEnumWhenTrackerComponentKt.getEnumWhenTracker(sessionHolder.getSession());
        if (enumWhenTracker != null) {
            KtSourceFile sourceFile = firFile.getSourceFile();
            String path = sourceFile != null ? sourceFile.getPath() : null;
            FirWhenExhaustivenessComputer firWhenExhaustivenessComputer = INSTANCE;
            ConeKotlinType subjectType = firWhenExhaustivenessComputer.getSubjectType(sessionHolder, firWhenExpression);
            FirEnumWhenTrackerComponentKt.reportEnumUsageInWhen(enumWhenTracker, path, subjectType != null ? firWhenExhaustivenessComputer.minimumBoundIfFlexible(sessionHolder, subjectType) : null);
        }
        return exhaustivenessStatusProcessExhaustivenessCheck;
    }
}
