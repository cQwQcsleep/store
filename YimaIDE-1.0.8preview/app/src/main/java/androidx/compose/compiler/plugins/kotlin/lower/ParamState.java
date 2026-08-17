package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u0010"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ParamState;", "", "bits", "", "<init>", "(Ljava/lang/String;II)V", "getBits", "()I", "Uncertain", "Same", "Different", "Static", "Unknown", "Mask", "bitsForSlot", "slot", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ParamState {
    Uncertain(0),
    Same(1),
    Different(2),
    Static(3),
    Unknown(4),
    Mask(7);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int bits;

    ParamState(int i) {
        this.bits = i;
    }

    public static EnumEntries<ParamState> getEntries() {
        return $ENTRIES;
    }

    public final int bitsForSlot(int slot) {
        return ComposableFunctionBodyTransformerKt.bitsForSlot(this.bits, slot);
    }

    public final int getBits() {
        return this.bits;
    }
}
