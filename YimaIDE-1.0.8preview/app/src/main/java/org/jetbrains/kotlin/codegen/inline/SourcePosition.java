package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", Argument.Delimiters.none, "line", Argument.Delimiters.none, "file", Argument.Delimiters.none, ModuleXmlParser.PATH, "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "getLine", "()I", "getFile", "()Ljava/lang/String;", "getPath", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SourcePosition {
    private final String file;
    private final int line;
    private final String path;

    public SourcePosition(int i, String str, String str2) {
        str.getClass();
        str2.getClass();
        this.line = i;
        this.file = str;
        this.path = str2;
    }

    public static /* synthetic */ SourcePosition copy$default(SourcePosition sourcePosition, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = sourcePosition.line;
        }
        if ((i2 & 2) != 0) {
            str = sourcePosition.file;
        }
        if ((i2 & 4) != 0) {
            str2 = sourcePosition.path;
        }
        return sourcePosition.copy(i, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLine() {
        return this.line;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final SourcePosition copy(int line, String file, String path) {
        file.getClass();
        path.getClass();
        return new SourcePosition(line, file, path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourcePosition)) {
            return false;
        }
        SourcePosition sourcePosition = (SourcePosition) other;
        return this.line == sourcePosition.line && Intrinsics.areEqual(this.file, sourcePosition.file) && Intrinsics.areEqual(this.path, sourcePosition.path);
    }

    public final String getFile() {
        return this.file;
    }

    public final int getLine() {
        return this.line;
    }

    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.line) * 31) + this.file.hashCode()) * 31) + this.path.hashCode();
    }

    public String toString() {
        return "SourcePosition(line=" + this.line + ", file=" + this.file + ", path=" + this.path + ')';
    }
}
