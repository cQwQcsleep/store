package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/config/LoggingContext;", Argument.Delimiters.none, "inVerbosePhase", Argument.Delimiters.none, "getInVerbosePhase", "()Z", "setInVerbosePhase", "(Z)V", K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_LOG, Argument.Delimiters.none, "message", "Lkotlin/Function0;", Argument.Delimiters.none, "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface LoggingContext {
    boolean getInVerbosePhase();

    default void log(Function0<String> message) {
        message.getClass();
        if (getInVerbosePhase()) {
            System.err.println((String) message.invoke());
        }
    }

    void setInVerbosePhase(boolean z);
}
