package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tH&¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirExceptionHandler;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "handleExceptionOnElementAnalysis", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "throwable", Argument.Delimiters.none, "handleExceptionOnFileAnalysis", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirExceptionHandler implements FirSessionComponent {
    public abstract Void handleExceptionOnElementAnalysis(FirElement element, Throwable throwable);

    public abstract Void handleExceptionOnFileAnalysis(FirFile file, Throwable throwable);
}
