package org.jetbrains.kotlin.fir.visitors;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirConstructedClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirErrorFunction;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorLoop;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ\u001d\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020%2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010+J\u001d\u0010,\u001a\u00020(2\u0006\u0010-\u001a\u00020.2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010/J\u001d\u00100\u001a\u00020(2\u0006\u00101\u001a\u0002022\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00103J\u001d\u00104\u001a\u00020(2\u0006\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u001d\u00108\u001a\u00020(2\u0006\u00109\u001a\u00020:2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010;J\u001d\u0010<\u001a\u00020(2\u0006\u0010=\u001a\u00020>2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010?J\u001d\u0010@\u001a\u00020(2\u0006\u0010A\u001a\u00020B2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010CJ\u001d\u0010D\u001a\u00020(2\u0006\u0010E\u001a\u00020F2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010GJ\u001d\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u00020J2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010KJ\u001d\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020O2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010PJ\u001d\u0010Q\u001a\u00020(2\u0006\u0010R\u001a\u00020S2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010TJ\u001d\u0010U\u001a\u00020(2\u0006\u0010V\u001a\u00020W2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010XJ\u001d\u0010Y\u001a\u00020(2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\\J\u001d\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020`2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010aJ\u001d\u0010b\u001a\u00020^2\u0006\u0010c\u001a\u00020d2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010e¨\u0006f"}, d2 = {"Lorg/jetbrains/kotlin/fir/visitors/FirDefaultTransformer;", "D", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "<init>", "()V", "transformImplicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "data", "(Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformErrorTypeRef", "errorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformUnresolvedTypeRef", "unresolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformDynamicTypeRef", "dynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformFunctionTypeRef", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformUserTypeRef", "userTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformIntersectionTypeRef", "intersectionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformCallableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformComponentCall", "componentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformErrorLoop", "errorLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformErrorNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "errorNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/references/FirReference;", "transformErrorFunction", "errorFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformImplicitInvokeCall", "implicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "transformConstructedClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "constructedClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "transformOuterClassTypeParameterRef", "outerClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDefaultTransformer<D> extends FirTransformer<D> {
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBreakExpression(FirBreakExpression breakExpression, D data) {
        breakExpression.getClass();
        return transformJump(breakExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, D data) {
        callableReferenceAccess.getClass();
        return transformQualifiedAccessExpression(callableReferenceAccess, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformComponentCall(FirComponentCall componentCall, D data) {
        componentCall.getClass();
        return transformFunctionCall(componentCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeParameterRef transformConstructedClassTypeParameterRef(FirConstructedClassTypeParameterRef constructedClassTypeParameterRef, D data) {
        constructedClassTypeParameterRef.getClass();
        return transformTypeParameterRef(constructedClassTypeParameterRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformContinueExpression(FirContinueExpression continueExpression, D data) {
        continueExpression.getClass();
        return transformJump(continueExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformDynamicTypeRef(FirDynamicTypeRef dynamicTypeRef, D data) {
        dynamicTypeRef.getClass();
        return transformUnresolvedTypeRef(dynamicTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorExpression(FirErrorExpression errorExpression, D data) {
        errorExpression.getClass();
        return transformExpression(errorExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorFunction(FirErrorFunction errorFunction, D data) {
        errorFunction.getClass();
        return transformFunction(errorFunction, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorLoop(FirErrorLoop errorLoop, D data) {
        errorLoop.getClass();
        return transformLoop(errorLoop, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReference transformErrorNamedReference(FirErrorNamedReference errorNamedReference, D data) {
        errorNamedReference.getClass();
        return transformNamedReference(errorNamedReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorResolvedQualifier(FirErrorResolvedQualifier errorResolvedQualifier, D data) {
        errorResolvedQualifier.getClass();
        return transformResolvedQualifier(errorResolvedQualifier, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformErrorTypeRef(FirErrorTypeRef errorTypeRef, D data) {
        errorTypeRef.getClass();
        return transformResolvedTypeRef(errorTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformFunctionTypeRef(FirFunctionTypeRef functionTypeRef, D data) {
        functionTypeRef.getClass();
        return transformUnresolvedTypeRef(functionTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformImplicitInvokeCall(FirImplicitInvokeCall implicitInvokeCall, D data) {
        implicitInvokeCall.getClass();
        return transformFunctionCall(implicitInvokeCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformImplicitTypeRef(FirImplicitTypeRef implicitTypeRef, D data) {
        implicitTypeRef.getClass();
        return mo600transformTypeRef(implicitTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformIntersectionTypeRef(FirIntersectionTypeRef intersectionTypeRef, D data) {
        intersectionTypeRef.getClass();
        return mo600transformTypeRef(intersectionTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, D data) {
        namedArgumentExpression.getClass();
        return transformWrappedArgumentExpression(namedArgumentExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeParameterRef transformOuterClassTypeParameterRef(FirOuterClassTypeParameterRef outerClassTypeParameterRef, D data) {
        outerClassTypeParameterRef.getClass();
        return transformTypeParameterRef(outerClassTypeParameterRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, D data) {
        resolvedTypeRef.getClass();
        return mo600transformTypeRef(resolvedTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReturnExpression(FirReturnExpression returnExpression, D data) {
        returnExpression.getClass();
        return transformJump(returnExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSpreadArgumentExpression(FirSpreadArgumentExpression spreadArgumentExpression, D data) {
        spreadArgumentExpression.getClass();
        return transformWrappedArgumentExpression(spreadArgumentExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformUnresolvedTypeRef(FirUnresolvedTypeRef unresolvedTypeRef, D data) {
        unresolvedTypeRef.getClass();
        return mo600transformTypeRef(unresolvedTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformUserTypeRef(FirUserTypeRef userTypeRef, D data) {
        userTypeRef.getClass();
        return transformUnresolvedTypeRef(userTypeRef, data);
    }
}
