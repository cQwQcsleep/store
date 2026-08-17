package org.jetbrains.kotlin.codegen.inline;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.IntervalWithHandler;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00018\u00008F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TryBlockCluster;", "T", "Lorg/jetbrains/kotlin/codegen/inline/IntervalWithHandler;", Argument.Delimiters.none, "blocks", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getBlocks", "()Ljava/util/List;", "defaultHandler", "getDefaultHandler", "()Lorg/jetbrains/kotlin/codegen/inline/IntervalWithHandler;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TryBlockCluster<T extends IntervalWithHandler> {
    private final List<T> blocks;

    public TryBlockCluster(List<T> list) {
        list.getClass();
        this.blocks = list;
    }

    public final List<T> getBlocks() {
        return this.blocks;
    }

    public final T getDefaultHandler() {
        Object next;
        Iterator<T> it = this.blocks.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (((IntervalWithHandler) next).getType() == null) {
                return (T) next;
            }
        }
        next = null;
        return (T) next;
    }
}
