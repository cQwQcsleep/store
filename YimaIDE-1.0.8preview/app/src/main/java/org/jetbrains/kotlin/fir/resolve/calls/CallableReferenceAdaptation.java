package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.expressions.CoercionStrategy;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001BQ\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\"\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nj\b\u0012\u0004\u0012\u00020\r`\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0006\u0010\u001e\u001a\u00020\u001fR\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R-\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nj\b\u0012\u0004\u0012\u00020\r`\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceAdaptation;", Argument.Delimiters.none, "argumentTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "coercionStrategy", "Lorg/jetbrains/kotlin/types/expressions/CoercionStrategy;", "defaults", Argument.Delimiters.none, "mappedArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceMappedArguments;", "suspendConversionStrategy", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy;", "<init>", "([Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/types/expressions/CoercionStrategy;ILjava/util/Map;Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy;)V", "getArgumentTypes", "()[Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "[Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getCoercionStrategy", "()Lorg/jetbrains/kotlin/types/expressions/CoercionStrategy;", "getDefaults", "()I", "getMappedArguments", "()Ljava/util/Map;", "getSuspendConversionStrategy", "()Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceConversionStrategy;", "hasFunctionKindConversion", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallableReferenceAdaptation {
    private final ConeKotlinType[] argumentTypes;
    private final CoercionStrategy coercionStrategy;
    private final int defaults;
    private final Map<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> mappedArguments;
    private final CallableReferenceConversionStrategy suspendConversionStrategy;

    /* JADX WARN: Multi-variable type inference failed */
    public CallableReferenceAdaptation(ConeKotlinType[] coneKotlinTypeArr, CoercionStrategy coercionStrategy, int i, Map<FirValueParameter, ? extends ResolvedCallArgument<? extends ConeResolutionAtom>> map, CallableReferenceConversionStrategy callableReferenceConversionStrategy) {
        coneKotlinTypeArr.getClass();
        coercionStrategy.getClass();
        map.getClass();
        callableReferenceConversionStrategy.getClass();
        this.argumentTypes = coneKotlinTypeArr;
        this.coercionStrategy = coercionStrategy;
        this.defaults = i;
        this.mappedArguments = map;
        this.suspendConversionStrategy = callableReferenceConversionStrategy;
        if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS && i == 0 && !hasFunctionKindConversion() && coercionStrategy == CoercionStrategy.NO_COERCION) {
            Collection collectionValues = map.values();
            if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                Iterator it = collectionValues.iterator();
                while (it.hasNext()) {
                    if (((ResolvedCallArgument) it.next()) instanceof ResolvedCallArgument.VarargArgument) {
                        return;
                    }
                }
            }
            w01.a("Adaptation must be non-trivial.");
            throw null;
        }
    }

    public final ConeKotlinType[] getArgumentTypes() {
        return this.argumentTypes;
    }

    public final CoercionStrategy getCoercionStrategy() {
        return this.coercionStrategy;
    }

    public final int getDefaults() {
        return this.defaults;
    }

    public final Map<FirValueParameter, ResolvedCallArgument<ConeResolutionAtom>> getMappedArguments() {
        return this.mappedArguments;
    }

    public final CallableReferenceConversionStrategy getSuspendConversionStrategy() {
        return this.suspendConversionStrategy;
    }

    public final boolean hasFunctionKindConversion() {
        return !Intrinsics.areEqual(this.suspendConversionStrategy, CallableReferenceConversionStrategy.NoConversion.INSTANCE);
    }
}
