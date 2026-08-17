package org.jetbrains.kotlin.container;

import java.lang.reflect.Type;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J!\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00070\bH&¢\u0006\u0002\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/container/ComponentProvider;", Argument.Delimiters.none, "resolve", "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "request", "Ljava/lang/reflect/Type;", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "T", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ComponentProvider {
    <T> T create(Class<T> request);

    ValueDescriptor resolve(Type request);
}
