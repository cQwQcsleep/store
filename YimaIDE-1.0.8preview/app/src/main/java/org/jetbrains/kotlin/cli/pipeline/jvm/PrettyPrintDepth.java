package org.jetbrains.kotlin.cli.pipeline.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/jvm/PrettyPrintDepth;", Argument.Delimiters.none, "value", Argument.Delimiters.none, "<init>", "(I)V", "getValue", "()I", "setValue", "component1", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class PrettyPrintDepth {
    private int value;

    public PrettyPrintDepth(int i) {
        this.value = i;
    }

    public static /* synthetic */ PrettyPrintDepth copy$default(PrettyPrintDepth prettyPrintDepth, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = prettyPrintDepth.value;
        }
        return prettyPrintDepth.copy(i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getValue() {
        return this.value;
    }

    public final PrettyPrintDepth copy(int value) {
        return new PrettyPrintDepth(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PrettyPrintDepth) && this.value == ((PrettyPrintDepth) other).value;
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final void setValue(int i) {
        this.value = i;
    }

    public String toString() {
        return "PrettyPrintDepth(value=" + this.value + ')';
    }
}
