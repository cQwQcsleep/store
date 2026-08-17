package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.model.RegularArgumentConstraintPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/RegularArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/fir/FirElement;", "argument", "<init>", "(Lorg/jetbrains/kotlin/fir/FirElement;)V", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeArgumentConstraintPosition extends RegularArgumentConstraintPosition<FirElement> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeArgumentConstraintPosition(FirElement firElement) {
        super(firElement);
        firElement.getClass();
    }

    public String toString() {
        return "Argument " + UtilsKt.render((FirElement) getArgument());
    }
}
