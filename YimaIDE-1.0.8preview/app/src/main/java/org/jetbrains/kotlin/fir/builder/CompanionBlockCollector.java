package org.jetbrains.kotlin.fir.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.CompanionBlockInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/CompanionBlockCollector;", Argument.Delimiters.none, "<init>", "()V", "validBlocks", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "getValidBlocks", "()Ljava/util/List;", "nestedBlocks", "getNestedBlocks", "collect", Argument.Delimiters.none, "source", "isNested", Argument.Delimiters.none, "toCompanionBlockInfoOrNull", "Lorg/jetbrains/kotlin/fir/CompanionBlockInfo;", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompanionBlockCollector {
    private final List<KtSourceElement> validBlocks = new ArrayList();
    private final List<KtSourceElement> nestedBlocks = new ArrayList();

    public final void collect(KtSourceElement source, boolean isNested) {
        source.getClass();
        (isNested ? this.nestedBlocks : this.validBlocks).add(source);
    }

    public final List<KtSourceElement> getNestedBlocks() {
        return this.nestedBlocks;
    }

    public final List<KtSourceElement> getValidBlocks() {
        return this.validBlocks;
    }

    public final CompanionBlockInfo toCompanionBlockInfoOrNull() {
        if (this.validBlocks.isEmpty()) {
            return null;
        }
        return new CompanionBlockInfo(this.validBlocks, this.nestedBlocks);
    }
}
