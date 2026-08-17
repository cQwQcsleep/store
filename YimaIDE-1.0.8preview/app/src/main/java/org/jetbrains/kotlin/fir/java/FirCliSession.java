package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.PrivateSessionConstructor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0002\b\u0006¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirCliSession;", "Lorg/jetbrains/kotlin/fir/FirSession;", "kind", "Lorg/jetbrains/kotlin/fir/FirSession$Kind;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession$Kind;)V", "Lorg/jetbrains/kotlin/fir/PrivateSessionConstructor;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCliSession extends FirSession {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @PrivateSessionConstructor
    public FirCliSession(FirSession.Kind kind) {
        super(kind);
        kind.getClass();
    }
}
