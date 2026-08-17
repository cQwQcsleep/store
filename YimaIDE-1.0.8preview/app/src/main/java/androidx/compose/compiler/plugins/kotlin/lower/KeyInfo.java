package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0011\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000e¨\u0006\u0018"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/KeyInfo;", "", "name", "", "startOffset", "", "endOffset", "hasDuplicates", "", "<init>", "(Ljava/lang/String;IIZ)V", "getName", "()Ljava/lang/String;", "getStartOffset", "()I", "getEndOffset", "getHasDuplicates", "()Z", "used", "getUsed", "setUsed", "(Z)V", "key", "getKey", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KeyInfo {
    private final int endOffset;
    private final boolean hasDuplicates;
    private final String name;
    private final int startOffset;
    private boolean used;

    public KeyInfo(String str, int i, int i2, boolean z) {
        str.getClass();
        this.name = str;
        this.startOffset = i;
        this.endOffset = i2;
        this.hasDuplicates = z;
    }

    public final int getEndOffset() {
        return this.endOffset;
    }

    public final boolean getHasDuplicates() {
        return this.hasDuplicates;
    }

    public final int getKey() {
        return this.name.hashCode();
    }

    public final String getName() {
        return this.name;
    }

    public final int getStartOffset() {
        return this.startOffset;
    }

    public final boolean getUsed() {
        return this.used;
    }

    public final void setUsed(boolean z) {
        this.used = z;
    }
}
