package androidx.compose.compiler.plugins.kotlin;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeFeature;", "", "targetVersion", "Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "<init>", "(Ljava/lang/String;ILandroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;)V", "getTargetVersion", "()Landroidx/compose/compiler/plugins/kotlin/ComposeRuntimeVersion;", "SourceInfoParameterNames", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum ComposeRuntimeFeature {
    SourceInfoParameterNames(ComposeRuntimeVersion.v1_9);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final ComposeRuntimeVersion targetVersion;

    ComposeRuntimeFeature(ComposeRuntimeVersion composeRuntimeVersion) {
        this.targetVersion = composeRuntimeVersion;
    }

    public static EnumEntries<ComposeRuntimeFeature> getEntries() {
        return $ENTRIES;
    }

    public final ComposeRuntimeVersion getTargetVersion() {
        return this.targetVersion;
    }
}
