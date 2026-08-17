package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/UpdateReference;", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "<init>", "()V", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "reference", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class UpdateReference extends FirTransformer<FirNamedReferenceWithCandidate> {
    public static final UpdateReference INSTANCE = new UpdateReference();

    private UpdateReference() {
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, FirNamedReferenceWithCandidate data) {
        element.getClass();
        data.getClass();
        return element;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReference transformReference(FirReference reference, FirNamedReferenceWithCandidate data) {
        reference.getClass();
        data.getClass();
        return data;
    }
}
