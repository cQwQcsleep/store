package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J&\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u00060\u0013j\u0002`\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u0003J\n\u0010\u0017\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f¨\u0006\u0019"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo;", "", "key", "", "<init>", "(Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "parent", "getParent", "()Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo;", "setParent", "(Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo;)V", "prev", "getPrev", "setPrev", "print", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "pathSeparator", "siblingSeparator", "toString", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PathPartInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PathPartInfo ROOT = new PathPartInfo("ROOT");
    private final String key;
    private PathPartInfo parent;
    private PathPartInfo prev;

    public PathPartInfo(String str) {
        str.getClass();
        this.key = str;
    }

    public static /* synthetic */ void print$default(PathPartInfo pathPartInfo, StringBuilder sb, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "/";
        }
        if ((i & 4) != 0) {
            str2 = ":";
        }
        pathPartInfo.print(sb, str, str2);
    }

    public final String getKey() {
        return this.key;
    }

    public final PathPartInfo getParent() {
        return this.parent;
    }

    public final PathPartInfo getPrev() {
        return this.prev;
    }

    public final void print(StringBuilder builder, String pathSeparator, String siblingSeparator) {
        builder.getClass();
        pathSeparator.getClass();
        siblingSeparator.getClass();
        if (Intrinsics.areEqual(this, ROOT)) {
            builder.append("<ROOT>");
            return;
        }
        while (!Intrinsics.areEqual(this, ROOT)) {
            builder.append(pathSeparator);
            builder.append(this.key);
            String str = this.key;
            int i = 0;
            while (true) {
                PathPartInfo pathPartInfo = this.prev;
                if (pathPartInfo == null) {
                    break;
                }
                if (Intrinsics.areEqual(pathPartInfo != null ? pathPartInfo.key : null, str)) {
                    i++;
                }
                this = this.prev;
                this.getClass();
            }
            if (i > 0) {
                builder.append(siblingSeparator);
                builder.append(i);
            }
            this = this.parent;
            if (this == null) {
                this = ROOT;
            }
        }
    }

    public final void setParent(PathPartInfo pathPartInfo) {
        this.parent = pathPartInfo;
    }

    public final void setPrev(PathPartInfo pathPartInfo) {
        this.prev = pathPartInfo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        print$default(this, sb, null, null, 6, null);
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo$Companion;", "", "<init>", "()V", "ROOT", "Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo;", "getROOT", "()Landroidx/compose/compiler/plugins/kotlin/lower/PathPartInfo;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PathPartInfo getROOT() {
            return PathPartInfo.ROOT;
        }

        private Companion() {
        }
    }
}
