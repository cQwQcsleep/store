package androidx.compose.compiler.plugins.kotlin;

import androidx.compose.compiler.plugins.kotlin.ComposeRuntimeVersion;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000f"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "v1_8", "v1_9", "supportsFeature", "", "feature", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeFeature;", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ComposeRuntimeVersion {
    v1_8("1.8"),
    v1_9("1.9");

    private final String value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    ComposeRuntimeVersion(String str) {
        this.value = str;
    }

    public static EnumEntries<ComposeRuntimeVersion> getEntries() {
        return $ENTRIES;
    }

    public final String getValue() {
        return this.value;
    }

    public final boolean supportsFeature(ComposeRuntimeFeature feature) {
        feature.getClass();
        return compareTo(feature.getTargetVersion()) >= 0;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion$Companion;", "", "<init>", "()V", "fromString", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "version", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static CharSequence a(ComposeRuntimeVersion composeRuntimeVersion) {
            composeRuntimeVersion.getClass();
            return composeRuntimeVersion.getValue();
        }

        public final ComposeRuntimeVersion fromString(String version) {
            Object next;
            version.getClass();
            Iterator it = ComposeRuntimeVersion.getEntries().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((ComposeRuntimeVersion) next).getValue(), version));
            ComposeRuntimeVersion composeRuntimeVersion = (ComposeRuntimeVersion) next;
            if (composeRuntimeVersion != null) {
                return composeRuntimeVersion;
            }
            s0g.a("Unknown target runtime version: ", version, ". Supported versions are: ", CollectionsKt.joinToString$default(ComposeRuntimeVersion.getEntries(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: nm2
                public final Object invoke(Object obj) {
                    return ComposeRuntimeVersion.Companion.a((ComposeRuntimeVersion) obj);
                }
            }, 31, (Object) null));
            return null;
        }

        private Companion() {
        }
    }
}
