package org.jetbrains.kotlin.cli.common.fir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000e\u001a\u00020\u0006H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/fir/KtSourceFileDiagnosticPos;", Argument.Delimiters.none, "line", Argument.Delimiters.none, "column", "lineContent", Argument.Delimiters.none, "<init>", "(IILjava/lang/String;)V", "getLine", "()I", "getColumn", "getLineContent", "()Ljava/lang/String;", "toString", "Companion", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtSourceFileDiagnosticPos {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KtSourceFileDiagnosticPos NONE = new KtSourceFileDiagnosticPos(-1, -1, null);
    private final int column;
    private final int line;
    private final String lineContent;

    public KtSourceFileDiagnosticPos(int i, int i2, String str) {
        this.line = i;
        this.column = i2;
        this.lineContent = str;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getLine() {
        return this.line;
    }

    public final String getLineContent() {
        return this.lineContent;
    }

    public String toString() {
        StringBuilder sb;
        if (this.line < 0) {
            sb = new StringBuilder("(offset: ");
            sb.append(this.column);
            sb.append(" line unknown)");
        } else {
            sb = new StringBuilder("(");
            sb.append(this.line);
            sb.append(',');
            sb.append(this.column);
            sb.append(')');
        }
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/fir/KtSourceFileDiagnosticPos$Companion;", Argument.Delimiters.none, "<init>", "()V", "NONE", "Lorg/jetbrains/kotlin/cli/common/fir/KtSourceFileDiagnosticPos;", "getNONE", "()Lorg/jetbrains/kotlin/cli/common/fir/KtSourceFileDiagnosticPos;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KtSourceFileDiagnosticPos getNONE() {
            return KtSourceFileDiagnosticPos.NONE;
        }

        private Companion() {
        }
    }
}
