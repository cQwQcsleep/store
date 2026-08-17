package org.jetbrains.kotlin.config;

import com.intellij.openapi.util.Key;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\")\u0010\u0000\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00050\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"APPEND_JAVA_SOURCE_ROOTS_HANDLER_KEY", "Lcom/intellij/openapi/util/Key;", "Lkotlin/Function1;", Argument.Delimiters.none, "Ljava/io/File;", Argument.Delimiters.none, "getAPPEND_JAVA_SOURCE_ROOTS_HANDLER_KEY", "()Lcom/intellij/openapi/util/Key;", "org.jetbrains.kotlin:util"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AppendJavaSourceRootsHandlerKeyKt {
    private static final Key<Function1<List<? extends File>, Unit>> APPEND_JAVA_SOURCE_ROOTS_HANDLER_KEY = new Key<>("AppendJavaSourceRootsHandlerKey");

    public static final Key<Function1<List<? extends File>, Unit>> getAPPEND_JAVA_SOURCE_ROOTS_HANDLER_KEY() {
        return APPEND_JAVA_SOURCE_ROOTS_HANDLER_KEY;
    }
}
