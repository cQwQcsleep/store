package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirJvmEnumEntriesSupport;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirEnumEntriesSupport;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "canSynthesizeEnumEntriesFor", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmEnumEntriesSupport extends FirEnumEntriesSupport {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmEnumEntriesSupport(FirSession firSession) {
        super(firSession);
        firSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirEnumEntriesSupport
    public boolean canSynthesizeEnumEntriesFor(FirClass klass) {
        klass.getClass();
        return isEnumEntriesAvailable();
    }
}
