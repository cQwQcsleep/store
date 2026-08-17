package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.scopes.FirScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH&J\n\u0010\n\u001a\u0004\u0018\u00010\tH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/QualifierReceiver;", Argument.Delimiters.none, "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "classifierScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "callableScope", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class QualifierReceiver {
    private final FirResolvedQualifier explicitReceiver;

    public QualifierReceiver(FirResolvedQualifier firResolvedQualifier) {
        firResolvedQualifier.getClass();
        this.explicitReceiver = firResolvedQualifier;
    }

    public abstract FirScope callableScope();

    public abstract FirScope classifierScope();

    public final FirResolvedQualifier getExplicitReceiver() {
        return this.explicitReceiver;
    }
}
