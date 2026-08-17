package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.model.PostponedResolvedAtomMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\f\u0082\u0001\u0004\u0015\u0016\u0017\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "Lorg/jetbrains/kotlin/resolve/calls/model/PostponedResolvedAtomMarker;", "<init>", "()V", "inputTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getInputTypes", "()Ljava/util/Collection;", "outputType", "getOutputType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "analyzed", Argument.Delimiters.none, "getAnalyzed", "()Z", "setAnalyzed", "(Z)V", "expectedType", "getExpectedType", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeCollectionLiteralAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeContextSensitiveAlternativeForQualifierAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeFunctionTypeRelatedPostponedResolvedAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeSimpleNameForContextSensitiveResolution;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConePostponedResolvedAtom extends ConeResolutionAtom implements PostponedResolvedAtomMarker {
    private boolean analyzed;

    private ConePostponedResolvedAtom() {
        super(null);
    }

    public boolean getAnalyzed() {
        return this.analyzed;
    }

    @Override // 
    /* JADX INFO: renamed from: getExpectedType, reason: merged with bridge method [inline-methods] */
    public abstract ConeKotlinType mo581getExpectedType();

    public abstract Collection<ConeKotlinType> getInputTypes();

    @Override // 
    /* JADX INFO: renamed from: getOutputType, reason: merged with bridge method [inline-methods] */
    public abstract ConeKotlinType mo582getOutputType();

    public void setAnalyzed(boolean z) {
        this.analyzed = z;
    }

    public /* synthetic */ ConePostponedResolvedAtom(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
