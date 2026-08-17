package org.jetbrains.kotlin.fir.resolve.dfa;

import defpackage.f2f;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueStorage;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014R\u0014\u0010\u0002\u001a\u00020\u00038TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"org/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "receiverStack", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getReceiverStack", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "visibilityChecker", "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "implicitUpdated", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "logicSystem", "Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;", "getLogicSystem", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1 extends FirDataFlowAnalyzer {
    final /* synthetic */ FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents $components;
    private final LogicSystem logicSystem;
    private final ConeInferenceContext typeContext;
    private final FirVisibilityChecker visibilityChecker;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1(final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents, final DataFlowAnalyzerContext dataFlowAnalyzerContext) {
        super(bodyResolveTransformerComponents, dataFlowAnalyzerContext);
        this.$components = bodyResolveTransformerComponents;
        this.visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(bodyResolveTransformerComponents.getSession());
        this.typeContext = TypeComponentsKt.getTypeContext(bodyResolveTransformerComponents.getSession());
        final ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(bodyResolveTransformerComponents.getSession());
        this.logicSystem = new LogicSystem(typeContext) { // from class: org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1$logicSystem$1
            @Override // org.jetbrains.kotlin.fir.resolve.dfa.LogicSystem
            public VariableStorage getVariableStorage() {
                return dataFlowAnalyzerContext.getVariableStorage();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // org.jetbrains.kotlin.fir.resolve.dfa.LogicSystem
            public boolean isAcceptableForSmartcast(ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
                coneKotlinType.getClass();
                if (ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinType)) {
                    return false;
                }
                if (coneKotlinType instanceof ConeClassLikeType) {
                    FirDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1 firDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1 = this;
                    FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) firDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1, TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) firDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1, (ConeClassLikeType) coneKotlinType).getLookupTag());
                    if (symbol == null) {
                        return false;
                    }
                    E fir = symbol.getFir();
                    FirRegularClass firRegularClass = fir instanceof FirRegularClass ? (FirRegularClass) fir : null;
                    if (firRegularClass == null) {
                        return true;
                    }
                    return this.visibilityChecker.isClassLikeVisible(firRegularClass, bodyResolveTransformerComponents.getSession(), bodyResolveTransformerComponents.getContext().getFile(), bodyResolveTransformerComponents.getContext().getContainers());
                }
                if (coneKotlinType instanceof ConeTypeParameterType) {
                    return true;
                }
                if (coneKotlinType instanceof ConeFlexibleType) {
                    ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                    return isAcceptableForSmartcast(coneFlexibleType.getLowerBound()) && isAcceptableForSmartcast(coneFlexibleType.getUpperBound());
                }
                if (!(coneKotlinType instanceof ConeIntersectionType)) {
                    if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                        return isAcceptableForSmartcast(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
                    }
                    return false;
                }
                Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
                if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                    return true;
                }
                Iterator<T> it = intersectedTypes.iterator();
                while (it.hasNext()) {
                    if (!isAcceptableForSmartcast((ConeKotlinType) it.next())) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer
    public LogicSystem getLogicSystem() {
        return this.logicSystem;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer
    public ImplicitValueStorage getReceiverStack() {
        return this.$components.getImplicitValueStorage();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer
    public void implicitUpdated(TypeStatement info) {
        info.getClass();
        DataFlowVariable variable = info.getVariable();
        RealVariable realVariable = variable instanceof RealVariable ? (RealVariable) variable : null;
        if (realVariable != null) {
            getReceiverStack().replaceImplicitValueType(realVariable.getSymbol(), UtilKt.smartCastedType(info, this.typeContext));
        } else {
            f2f.a("Not a real variable: ", info.getVariable());
        }
    }
}
