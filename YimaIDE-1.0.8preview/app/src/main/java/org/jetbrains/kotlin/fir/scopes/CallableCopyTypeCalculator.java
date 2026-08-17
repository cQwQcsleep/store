package org.jetbrains.kotlin.fir.scopes;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.CopyUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0004\n\u000b\f\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", Argument.Delimiters.none, "<init>", "()V", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "computeReturnTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "DoNothing", "DeferredCallableCopyTypeCalculator", "CalculateDeferredForceLazyResolution", "CalculateDeferredWhenPossible", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CallableCopyTypeCalculator {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$CalculateDeferredForceLazyResolution;", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;", "<init>", "()V", "getResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CalculateDeferredForceLazyResolution extends DeferredCallableCopyTypeCalculator {
        public static final CalculateDeferredForceLazyResolution INSTANCE = new CalculateDeferredForceLazyResolution();

        private CalculateDeferredForceLazyResolution() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator
        public FirResolvedTypeRef getResolvedTypeRef(FirCallableDeclaration firCallableDeclaration) {
            firCallableDeclaration.getClass();
            return firCallableDeclaration.getSymbol().getResolvedReturnTypeRef();
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$CalculateDeferredWhenPossible;", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;", "<init>", "()V", "getResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CalculateDeferredWhenPossible extends DeferredCallableCopyTypeCalculator {
        public static final CalculateDeferredWhenPossible INSTANCE = new CalculateDeferredWhenPossible();

        private CalculateDeferredWhenPossible() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator.DeferredCallableCopyTypeCalculator
        public FirResolvedTypeRef getResolvedTypeRef(FirCallableDeclaration firCallableDeclaration) {
            firCallableDeclaration.getClass();
            FirResolvedTypeRef returnTypeRef = firCallableDeclaration.getReturnTypeRef();
            if (returnTypeRef instanceof FirResolvedTypeRef) {
                return returnTypeRef;
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\b\u001a\u0004\u0018\u00010\u0005*\u00020\u0007H$¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DeferredCallableCopyTypeCalculator;", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "<init>", "()V", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getResolvedTypeRef", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class DeferredCallableCopyTypeCalculator extends CallableCopyTypeCalculator {
        @Override // org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator
        /* JADX INFO: renamed from: computeReturnType, reason: merged with bridge method [inline-methods] */
        public FirResolvedTypeRef mo617computeReturnType(FirCallableDeclaration declaration) {
            List<FirValueParameter> valueParameters;
            FirValueParameter firValueParameter;
            declaration.getClass();
            DeferredCallableCopyReturnType deferredCallableCopyReturnType = CallableCopyTypeCalculatorKt.getDeferredCallableCopyReturnType(declaration.getAttributes());
            if (deferredCallableCopyReturnType == null) {
                return getResolvedTypeRef(declaration);
            }
            synchronized (deferredCallableCopyReturnType) {
                if (CallableCopyTypeCalculatorKt.getDeferredCallableCopyReturnType(declaration.getAttributes()) == null) {
                    FirResolvedTypeRef returnTypeRef = declaration.getReturnTypeRef();
                    returnTypeRef.getClass();
                    return returnTypeRef;
                }
                ConeKotlinType coneKotlinTypeComputeReturnType = deferredCallableCopyReturnType.computeReturnType(this);
                if (coneKotlinTypeComputeReturnType == null) {
                    return null;
                }
                FirTypeRef returnTypeRef2 = declaration.getReturnTypeRef();
                KtSourceElement source = declaration.getSource();
                FirTypeRef firTypeRefResolvedTypeFromPrototype = CopyUtilsKt.resolvedTypeFromPrototype(returnTypeRef2, coneKotlinTypeComputeReturnType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE, null, 2, null) : null);
                declaration.replaceReturnTypeRef(firTypeRefResolvedTypeFromPrototype);
                if (declaration instanceof FirProperty) {
                    FirPropertyAccessor getter = ((FirProperty) declaration).getGetter();
                    if (getter != null) {
                        getter.replaceReturnTypeRef(firTypeRefResolvedTypeFromPrototype);
                    }
                    FirPropertyAccessor setter = ((FirProperty) declaration).getSetter();
                    if (setter != null && (valueParameters = setter.getValueParameters()) != null && (firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(valueParameters)) != null) {
                        firValueParameter.replaceReturnTypeRef(firTypeRefResolvedTypeFromPrototype);
                    }
                }
                CallableCopyTypeCalculatorKt.setDeferredCallableCopyReturnType(declaration.getAttributes(), null);
                return firTypeRefResolvedTypeFromPrototype;
            }
        }

        public abstract FirResolvedTypeRef getResolvedTypeRef(FirCallableDeclaration firCallableDeclaration);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator$DoNothing;", "Lorg/jetbrains/kotlin/fir/scopes/CallableCopyTypeCalculator;", "<init>", "()V", "computeReturnType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DoNothing extends CallableCopyTypeCalculator {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
        }

        @Override // org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator
        /* JADX INFO: renamed from: computeReturnType */
        public FirTypeRef mo617computeReturnType(FirCallableDeclaration declaration) {
            declaration.getClass();
            return declaration.getReturnTypeRef();
        }
    }

    /* JADX INFO: renamed from: computeReturnType */
    public abstract FirTypeRef mo617computeReturnType(FirCallableDeclaration declaration);

    public final ConeKotlinType computeReturnTypeOrNull(FirCallableDeclaration declaration) {
        declaration.getClass();
        FirTypeRef firTypeRefMo617computeReturnType = mo617computeReturnType(declaration);
        if (firTypeRefMo617computeReturnType != null) {
            return FirTypeUtilsKt.getConeTypeOrNull(firTypeRefMo617computeReturnType);
        }
        return null;
    }
}
