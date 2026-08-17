package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.ArgumentInfo;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;", Argument.Delimiters.none, "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "userType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;)V", "getArgument", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getUserType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getOriginalType", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "smartCastType", "getSmartCastType", "smartCastType$delegate", "Lkotlin/Lazy;", "originalTypeInfo", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "getOriginalTypeInfo", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "smartCastTypeInfo", "getSmartCastTypeInfo", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArgumentInfo {
    private final FirExpression argument;
    private final ConeKotlinType originalType;
    private final FirSession session;

    /* JADX INFO: renamed from: smartCastType$delegate, reason: from kotlin metadata */
    private final Lazy smartCastType;
    private final ConeKotlinType userType;

    public ArgumentInfo(FirExpression firExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession) {
        firExpression.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firSession.getClass();
        this.argument = firExpression;
        this.userType = coneKotlinType;
        this.originalType = coneKotlinType2;
        this.session = firSession;
        this.smartCastType = LazyKt.lazy(new Function0() { // from class: qe0
            public final Object invoke() {
                return ArgumentInfo.a(this.b);
            }
        });
    }

    public static ConeKotlinType a(ArgumentInfo argumentInfo) {
        return TypeExpansionUtilsKt.fullyExpandedType$default(argumentInfo.userType, argumentInfo.session, (Function1) null, 2, (Object) null);
    }

    public final FirExpression getArgument() {
        return this.argument;
    }

    public final ConeKotlinType getOriginalType() {
        return this.originalType;
    }

    public final TypeInfo getOriginalTypeInfo() {
        return FirTypeCompatibilityHelpersKt.toTypeInfo(this.originalType, this.session);
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final ConeKotlinType getSmartCastType() {
        return (ConeKotlinType) this.smartCastType.getValue();
    }

    public final TypeInfo getSmartCastTypeInfo() {
        return FirTypeCompatibilityHelpersKt.toTypeInfo(getSmartCastType(), this.session);
    }

    public final ConeKotlinType getUserType() {
        return this.userType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        KtSourceElement source = this.argument.getSource();
        sb.append((Object) (source != null ? KtSourceElementKt.getText(source) : null));
        sb.append(" :: ");
        sb.append(this.userType);
        return sb.toString();
    }
}
