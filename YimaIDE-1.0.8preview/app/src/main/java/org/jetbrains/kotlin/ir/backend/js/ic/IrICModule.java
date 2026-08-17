package org.jetbrains.kotlin.ir.backend.js.ic;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/IrICModule;", "", "<init>", "()V", "moduleName", "", "getModuleName", "()Ljava/lang/String;", "fragments", "", "Lorg/jetbrains/kotlin/ir/backend/js/ic/IrICProgramFragment;", "getFragments", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrICModule {
    public abstract List<IrICProgramFragment> getFragments();

    public abstract String getModuleName();
}
