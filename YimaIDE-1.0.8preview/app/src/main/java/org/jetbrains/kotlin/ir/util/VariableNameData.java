package org.jetbrains.kotlin.ir.util;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/VariableNameData;", "", "normalizeNames", "", "<init>", "(Z)V", "getNormalizeNames", "()Z", "nameMap", "", "Lorg/jetbrains/kotlin/ir/symbols/IrVariableSymbol;", "", "getNameMap", "()Ljava/util/Map;", "temporaryIndex", "", "getTemporaryIndex", "()I", "setTemporaryIndex", "(I)V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class VariableNameData {
    private final Map<IrVariableSymbol, String> nameMap = new LinkedHashMap();
    private final boolean normalizeNames;
    private int temporaryIndex;

    public VariableNameData(boolean z) {
        this.normalizeNames = z;
    }

    public final Map<IrVariableSymbol, String> getNameMap() {
        return this.nameMap;
    }

    public final boolean getNormalizeNames() {
        return this.normalizeNames;
    }

    public final int getTemporaryIndex() {
        return this.temporaryIndex;
    }

    public final void setTemporaryIndex(int i) {
        this.temporaryIndex = i;
    }
}
