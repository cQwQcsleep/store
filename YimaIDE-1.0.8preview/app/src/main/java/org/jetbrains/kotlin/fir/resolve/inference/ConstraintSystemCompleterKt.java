package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"csBuilder", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getCsBuilder", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstraintSystemCompleterKt {
    public static final NewConstraintSystemImpl getCsBuilder(Candidate candidate) {
        candidate.getClass();
        return candidate.getSystem().getBuilder();
    }
}
