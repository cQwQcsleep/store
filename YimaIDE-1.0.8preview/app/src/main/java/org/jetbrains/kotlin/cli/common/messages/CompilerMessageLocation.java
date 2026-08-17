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
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB+\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0010\u001a\u00020\u0003H\u0096\u0080\u0004J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÂ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", ModuleXmlParser.PATH, Argument.Delimiters.none, "line", Argument.Delimiters.none, "column", "lineContent", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getLine", "()I", "getColumn", "getLineContent", "toString", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "Companion", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CompilerMessageLocation implements CompilerMessageSourceLocation {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long serialVersionUID = 8228357578L;
    private final int column;
    private final int line;
    private final String lineContent;
    private final String path;

    private CompilerMessageLocation(String str, int i, int i2, String str2) {
        this.path = str;
        this.line = i;
        this.column = i2;
        this.lineContent = str2;
    }

    @JvmStatic
    public static final CompilerMessageLocation create(String str) {
        return INSTANCE.create(str);
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
    public final String getLineContent() {
        return this.lineContent;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompilerMessageLocation)) {
            return false;
        }
        CompilerMessageLocation compilerMessageLocation = (CompilerMessageLocation) other;
        return Intrinsics.areEqual(this.path, compilerMessageLocation.path) && this.line == compilerMessageLocation.line && this.column == compilerMessageLocation.column && Intrinsics.areEqual(this.lineContent, compilerMessageLocation.lineContent);
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
    public int getColumn() {
        return this.column;
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
    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        int iHashCode = ((((this.path.hashCode() * 31) + Integer.hashCode(this.line)) * 31) + Integer.hashCode(this.column)) * 31;
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

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J.\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0007R\u0014\u0010\f\u001a\u00020\rX\u0082D¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", ModuleXmlParser.PATH, Argument.Delimiters.none, "line", Argument.Delimiters.none, "column", "lineContent", "serialVersionUID", Argument.Delimiters.none, "getSerialVersionUID$annotations", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final CompilerMessageLocation create(String path, int line, int column, String lineContent) {
            if (path == null) {
                return null;
            }
            return new CompilerMessageLocation(path, line, column, lineContent, null);
        }

        private Companion() {
        }

        @JvmStatic
        public final CompilerMessageLocation create(String path) {
            return create(path, -1, -1, null);
        }
    }

    @JvmStatic
    public static final CompilerMessageLocation create(String str, int i, int i2, String str2) {
        return INSTANCE.create(str, i, i2, str2);
    }

    public /* synthetic */ CompilerMessageLocation(String str, int i, int i2, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, str2);
    }
}
