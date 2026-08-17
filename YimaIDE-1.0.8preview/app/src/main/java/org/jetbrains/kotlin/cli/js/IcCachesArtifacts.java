package org.jetbrains.kotlin.cli.js;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.ic.ModuleArtifact;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/IcCachesArtifacts;", Argument.Delimiters.none, "artifacts", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/backend/js/ic/ModuleArtifact;", "<init>", "(Ljava/util/List;)V", "getArtifacts", "()Ljava/util/List;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IcCachesArtifacts {
    private final List<ModuleArtifact> artifacts;

    public IcCachesArtifacts(List<? extends ModuleArtifact> list) {
        list.getClass();
        this.artifacts = list;
    }

    public final List<ModuleArtifact> getArtifacts() {
        return this.artifacts;
    }
}
