package org.jetbrains.kotlin.cli.common.fir;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/fir/SequentialCloseablePositionFinder;", "Ljava/io/Closeable;", "Lorg/jetbrains/kotlin/cli/common/fir/SequentialPositionFinder;", "reader", "Ljava/io/InputStreamReader;", "<init>", "(Ljava/io/InputStreamReader;)V", "close", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SequentialCloseablePositionFinder extends SequentialPositionFinder implements Closeable {
    private final InputStreamReader reader;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequentialCloseablePositionFinder(InputStreamReader inputStreamReader) {
        super(inputStreamReader);
        inputStreamReader.getClass();
        this.reader = inputStreamReader;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }
}
