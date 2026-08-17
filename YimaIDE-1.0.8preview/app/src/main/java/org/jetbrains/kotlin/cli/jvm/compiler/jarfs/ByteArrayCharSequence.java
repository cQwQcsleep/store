package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\t\u001a\u00020\u0005H\u0096\u0080\u0004J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0082\u0004J\u0012\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0096\u0082\u0004J\u001a\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u000e\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ByteArrayCharSequence;", Argument.Delimiters.none, "bytes", Argument.Delimiters.none, "start", Argument.Delimiters.none, "end", "<init>", "([BII)V", "hashCode", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "length", "getLength", "()I", "get", Argument.Delimiters.none, "index", "subSequence", "startIndex", "endIndex", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ByteArrayCharSequence implements CharSequence {
    private final byte[] bytes;
    private final int end;
    private final int start;

    public /* synthetic */ ByteArrayCharSequence(byte[] bArr, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? bArr.length : i2);
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i) {
        return get(i);
    }

    public boolean equals(Object other) {
        throw new IllegalStateException("Do not try comparing ByteArrayCharSequence");
    }

    public char get(int index) {
        return (char) this.bytes[index + this.start];
    }

    public int getLength() {
        return this.end - this.start;
    }

    public int hashCode() {
        throw new IllegalStateException("Do not try computing hashCode ByteArrayCharSequence");
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int startIndex, int endIndex) {
        if (startIndex == 0 && endIndex == length()) {
            return this;
        }
        byte[] bArr = this.bytes;
        int i = this.start;
        return new ByteArrayCharSequence(bArr, startIndex + i, i + endIndex);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        char[] cArr = new char[length()];
        int length = length();
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) this.bytes[this.start + i];
        }
        return new String(cArr);
    }

    public ByteArrayCharSequence(byte[] bArr, int i, int i2) {
        bArr.getClass();
        this.bytes = bArr;
        this.start = i;
        this.end = i2;
    }
}
