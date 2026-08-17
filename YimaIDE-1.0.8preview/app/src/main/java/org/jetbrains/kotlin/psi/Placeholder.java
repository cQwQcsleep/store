package org.jetbrains.kotlin.psi;

import com.intellij.openapi.util.TextRange;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/psi/Placeholder;", "", "range", "Lcom/intellij/openapi/util/TextRange;", "text", "", "<init>", "(Lcom/intellij/openapi/util/TextRange;Ljava/lang/String;)V", "getRange", "()Lcom/intellij/openapi/util/TextRange;", "getText", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
final /* data */ class Placeholder {
    private final TextRange range;
    private final String text;

    public Placeholder(TextRange textRange, String str) {
        textRange.getClass();
        str.getClass();
        this.range = textRange;
        this.text = str;
    }

    public static /* synthetic */ Placeholder copy$default(Placeholder placeholder, TextRange textRange, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            textRange = placeholder.range;
        }
        if ((i & 2) != 0) {
            str = placeholder.text;
        }
        return placeholder.copy(textRange, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TextRange getRange() {
        return this.range;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getText() {
        return this.text;
    }

    public final Placeholder copy(TextRange range, String text) {
        range.getClass();
        text.getClass();
        return new Placeholder(range, text);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Placeholder)) {
            return false;
        }
        Placeholder placeholder = (Placeholder) other;
        return Intrinsics.areEqual(this.range, placeholder.range) && Intrinsics.areEqual(this.text, placeholder.text);
    }

    public final TextRange getRange() {
        return this.range;
    }

    public final String getText() {
        return this.text;
    }

    public int hashCode() {
        return (this.range.hashCode() * 31) + this.text.hashCode();
    }

    public String toString() {
        return "Placeholder(range=" + this.range + ", text=" + this.text + ')';
    }
}
