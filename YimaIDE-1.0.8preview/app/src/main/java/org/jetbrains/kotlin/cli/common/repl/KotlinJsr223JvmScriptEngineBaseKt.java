package org.jetbrains.kotlin.cli.common.repl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"KOTLIN_SCRIPT_STATE_BINDINGS_KEY", Argument.Delimiters.none, "KOTLIN_SCRIPT_ENGINE_BINDINGS_KEY", "locationString", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompileResult$Error;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinJsr223JvmScriptEngineBaseKt {
    public static final String KOTLIN_SCRIPT_ENGINE_BINDINGS_KEY = "kotlin.script.engine";
    public static final String KOTLIN_SCRIPT_STATE_BINDINGS_KEY = "kotlin.script.state";

    /* JADX INFO: Access modifiers changed from: private */
    public static final String locationString(ReplCompileResult.Error error) {
        if (error.getLocation() == null) {
            return Argument.Delimiters.none;
        }
        return " at " + error.getLocation().getLine() + ':' + error.getLocation().getColumn();
    }
}
