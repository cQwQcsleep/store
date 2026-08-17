package org.jetbrains.kotlin.cli.common.messages;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B;\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\n\u0010\u0014\u001a\u00020\u0003H\u0096\u0080\u0004J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003JG\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÂ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0016\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocationWithRange;", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", ModuleXmlParser.PATH, Argument.Delimiters.none, "line", Argument.Delimiters.none, "column", "lineEnd", "columnEnd", "lineContent", "<init>", "(Ljava/lang/String;IIIILjava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getLine", "()I", "getColumn", "getLineEnd", "getColumnEnd", "getLineContent", "toString", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CompilerMessageLocationWithRange implements CompilerMessageSourceLocation {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = 8228357578L;
    private final int column;
    private final int columnEnd;
    private final int line;
    private final String lineContent;
    private final int lineEnd;
    private final String path;

    private CompilerMessageLocationWithRange(String str, int i, int i2, int i3, int i4, String str2) {
        this.path = str;
        this.line = i;
        this.column = i2;
        this.lineEnd = i3;
        this.columnEnd = i4;
        this.lineContent = str2;
    }

    @JvmStatic
    public static final CompilerMessageLocationWithRange create(String str, int i, int i2, Integer num, Integer num2, String str2) {
        return INSTANCE.create(str, i, i2, num, num2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLine() {
        return this.line;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getColumn() {
        return this.column;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getLineEnd() {
        return this.lineEnd;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getColumnEnd() {
        return this.columnEnd;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLineContent() {
        return this.lineContent;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompilerMessageLocationWithRange)) {
            return false;
        }
        CompilerMessageLocationWithRange compilerMessageLocationWithRange = (CompilerMessageLocationWithRange) other;
        return Intrinsics.areEqual(this.path, compilerMessageLocationWithRange.path) && this.line == compilerMessageLocationWithRange.line && this.column == compilerMessageLocationWithRange.column && this.lineEnd == compilerMessageLocationWithRange.lineEnd && this.columnEnd == compilerMessageLocationWithRange.columnEnd && Intrinsics.areEqual(this.lineContent, compilerMessageLocationWithRange.lineContent);
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public int getColumn() {
        return this.column;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public int getColumnEnd() {
        return this.columnEnd;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public int getLine() {
        return this.line;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public String getLineContent() {
        return this.lineContent;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public int getLineEnd() {
        return this.lineEnd;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.path.hashCode() * 31) + Integer.hashCode(this.line)) * 31) + Integer.hashCode(this.column)) * 31) + Integer.hashCode(this.lineEnd)) * 31) + Integer.hashCode(this.columnEnd)) * 31;
        String str = this.lineContent;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(getPath());
        if (getLine() == -1 && getColumn() == -1) {
            str = Argument.Delimiters.none;
        } else {
            str = " (" + getLine() + ':' + getColumn() + ')';
        }
        sb.append(str);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JG\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocationWithRange$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocationWithRange;", ModuleXmlParser.PATH, Argument.Delimiters.none, "lineStart", Argument.Delimiters.none, "columnStart", "lineEnd", "columnEnd", "lineContent", "(Ljava/lang/String;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocationWithRange;", "serialVersionUID", Argument.Delimiters.none, "getSerialVersionUID$annotations", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CompilerMessageLocationWithRange create(String path, int lineStart, int columnStart, Integer lineEnd, Integer columnEnd, String lineContent) {
            if (path == null) {
                return null;
            }
            return new CompilerMessageLocationWithRange(path, lineStart, columnStart, lineEnd != null ? lineEnd.intValue() : -1, columnEnd != null ? columnEnd.intValue() : -1, lineContent, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ CompilerMessageLocationWithRange(String str, int i, int i2, int i3, int i4, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, i3, i4, str2);
    }
}
