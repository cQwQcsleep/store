package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirEnumWhenTrackerComponent;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/IncrementalPassThroughEnumWhenTrackerComponent;", "Lorg/jetbrains/kotlin/fir/FirEnumWhenTrackerComponent;", "enumWhenTracker", "Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;", "<init>", "(Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;)V", "report", Argument.Delimiters.none, "whenExpressionFilePath", Argument.Delimiters.none, "enumClassFqName", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IncrementalPassThroughEnumWhenTrackerComponent extends FirEnumWhenTrackerComponent {
    private final EnumWhenTracker enumWhenTracker;

    public IncrementalPassThroughEnumWhenTrackerComponent(EnumWhenTracker enumWhenTracker) {
        enumWhenTracker.getClass();
        this.enumWhenTracker = enumWhenTracker;
    }

    @Override // org.jetbrains.kotlin.fir.FirEnumWhenTrackerComponent
    public void report(String whenExpressionFilePath, String enumClassFqName) {
        whenExpressionFilePath.getClass();
        enumClassFqName.getClass();
        this.enumWhenTracker.report(whenExpressionFilePath, enumClassFqName);
    }
}
