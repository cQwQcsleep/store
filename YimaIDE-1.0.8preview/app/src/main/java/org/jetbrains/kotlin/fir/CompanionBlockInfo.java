package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/CompanionBlockInfo;", Argument.Delimiters.none, "validCompanionBlocks", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "nestedCompanionBlocks", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getValidCompanionBlocks", "()Ljava/util/List;", "getNestedCompanionBlocks", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompanionBlockInfo {
    private final List<KtSourceElement> nestedCompanionBlocks;
    private final List<KtSourceElement> validCompanionBlocks;

    public CompanionBlockInfo(List<? extends KtSourceElement> list, List<? extends KtSourceElement> list2) {
        list.getClass();
        list2.getClass();
        this.validCompanionBlocks = list;
        this.nestedCompanionBlocks = list2;
    }

    public final List<KtSourceElement> getNestedCompanionBlocks() {
        return this.nestedCompanionBlocks;
    }

    public final List<KtSourceElement> getValidCompanionBlocks() {
        return this.validCompanionBlocks;
    }

    public String toString() {
        return "[valid=" + this.validCompanionBlocks.size() + " nested=" + this.nestedCompanionBlocks.size() + ']';
    }
}
