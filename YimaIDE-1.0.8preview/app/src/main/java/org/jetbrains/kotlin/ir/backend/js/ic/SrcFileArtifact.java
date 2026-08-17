package org.jetbrains.kotlin.ir.backend.js.ic;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/SrcFileArtifact;", "", "<init>", "()V", "loadIrFragments", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IrICProgramFragments;", "isModified", "", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class SrcFileArtifact {
    public abstract boolean isModified();

    public abstract IrICProgramFragments loadIrFragments();
}
