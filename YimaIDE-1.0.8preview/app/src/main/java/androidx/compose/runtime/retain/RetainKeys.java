package androidx.compose.runtime.retain;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\bH\u0016R\u001c\u0010\u0002\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Landroidx/compose/runtime/retain/RetainKeys;", "", "keys", "", "positionalKey", "", "Landroidx/compose/runtime/CompositeKeyHashCode;", "typeHash", "", "<init>", "([Ljava/lang/Object;JI)V", "[Ljava/lang/Object;", "getPositionalKey", "()J", "getTypeHash", "()I", "equals", "", "other", "hashCode", "runtime-retain"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class RetainKeys {
    private final Object[] keys;
    private final long positionalKey;
    private final int typeHash;

    public RetainKeys(Object[] objArr, long j, int i) {
        this.keys = objArr;
        this.positionalKey = j;
        this.typeHash = i;
    }

    public boolean equals(Object other) {
        if (!(other instanceof RetainKeys)) {
            return false;
        }
        RetainKeys retainKeys = (RetainKeys) other;
        return retainKeys.positionalKey == this.positionalKey && retainKeys.typeHash == this.typeHash && Arrays.equals(retainKeys.keys, this.keys);
    }

    public final long getPositionalKey() {
        return this.positionalKey;
    }

    public final int getTypeHash() {
        return this.typeHash;
    }

    public int hashCode() {
        Object[] objArr = this.keys;
        return ((((objArr != null ? Arrays.hashCode(objArr) : 0) * 31) + Long.hashCode(this.positionalKey)) * 31) + Integer.hashCode(this.typeHash);
    }
}
