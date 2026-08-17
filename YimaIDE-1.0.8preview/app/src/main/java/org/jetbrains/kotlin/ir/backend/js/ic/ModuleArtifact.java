package org.jetbrains.kotlin.ir.backend.js.ic;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/ModuleArtifact;", "", "<init>", "()V", "fileArtifacts", "", "Lorg/jetbrains/kotlin/ir/backend/js/ic/SrcFileArtifact;", "getFileArtifacts", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ModuleArtifact {
    public abstract List<SrcFileArtifact> getFileArtifacts();
}
