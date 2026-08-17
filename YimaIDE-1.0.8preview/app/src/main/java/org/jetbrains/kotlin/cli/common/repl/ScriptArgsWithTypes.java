package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB1\u0012\u0010\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u0002\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR#\u0010\u0005\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00060\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ScriptArgsWithTypes;", "Ljava/io/Serializable;", "scriptArgs", Argument.Delimiters.none, Argument.Delimiters.none, "scriptArgsTypes", "Lkotlin/reflect/KClass;", "<init>", "([Ljava/lang/Object;[Lkotlin/reflect/KClass;)V", "getScriptArgs", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "getScriptArgsTypes", "()[Lkotlin/reflect/KClass;", "[Lkotlin/reflect/KClass;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScriptArgsWithTypes implements Serializable {
    private static final long serialVersionUID = 8529357500L;
    private final Object[] scriptArgs;
    private final KClass<? extends Object>[] scriptArgsTypes;

    public ScriptArgsWithTypes(Object[] objArr, KClass<? extends Object>[] kClassArr) {
        objArr.getClass();
        kClassArr.getClass();
        this.scriptArgs = objArr;
        this.scriptArgsTypes = kClassArr;
        int length = objArr.length;
        int length2 = kClassArr.length;
    }

    public final Object[] getScriptArgs() {
        return this.scriptArgs;
    }

    public final KClass<? extends Object>[] getScriptArgsTypes() {
        return this.scriptArgsTypes;
    }
}
