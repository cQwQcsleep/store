package org.jetbrains.kotlin.container;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/container/ContainerConsistencyException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContainerConsistencyException extends Exception {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContainerConsistencyException(String str) {
        super(str);
        str.getClass();
    }
}
