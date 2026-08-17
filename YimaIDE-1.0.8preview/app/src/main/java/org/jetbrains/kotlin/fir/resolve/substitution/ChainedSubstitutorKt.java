package org.jetbrains.kotlin.fir.resolve.substitution;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"chain", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "other", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ChainedSubstitutorKt {
    public static final ConeSubstitutor chain(ConeSubstitutor coneSubstitutor, ConeSubstitutor coneSubstitutor2) {
        coneSubstitutor.getClass();
        coneSubstitutor2.getClass();
        return ChainedSubstitutor.INSTANCE.invoke(coneSubstitutor, coneSubstitutor2);
    }
}
