package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/CompiledClassData;", "Ljava/io/Serializable;", ModuleXmlParser.PATH, Argument.Delimiters.none, "bytes", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;[B)V", "getPath", "()Ljava/lang/String;", "getBytes", "()[B", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "component1", "component2", "copy", "toString", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CompiledClassData implements Serializable {
    private static final long serialVersionUID = 8228357578L;
    private final byte[] bytes;
    private final String path;

    public CompiledClassData(String str, byte[] bArr) {
        str.getClass();
        bArr.getClass();
        this.path = str;
        this.bytes = bArr;
    }

    public static /* synthetic */ CompiledClassData copy$default(CompiledClassData compiledClassData, String str, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            str = compiledClassData.path;
        }
        if ((i & 2) != 0) {
            bArr = compiledClassData.bytes;
        }
        return compiledClassData.copy(str, bArr);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getBytes() {
        return this.bytes;
    }

    public final CompiledClassData copy(String path, byte[] bytes) {
        path.getClass();
        bytes.getClass();
        return new CompiledClassData(path, bytes);
    }

    public boolean equals(Object other) {
        CompiledClassData compiledClassData = other instanceof CompiledClassData ? (CompiledClassData) other : null;
        return compiledClassData != null && Intrinsics.areEqual(this.path, compiledClassData.path) && Arrays.equals(this.bytes, compiledClassData.bytes);
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        return this.path.hashCode() + Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "CompiledClassData(path=" + this.path + ", bytes=" + Arrays.toString(this.bytes) + ')';
    }
}
