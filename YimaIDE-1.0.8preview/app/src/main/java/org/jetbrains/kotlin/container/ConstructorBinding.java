package org.jetbrains.kotlin.container;

import java.lang.reflect.Constructor;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/container/ConstructorBinding;", Argument.Delimiters.none, "constructor", "Ljava/lang/reflect/Constructor;", "argumentDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/container/ValueDescriptor;", "<init>", "(Ljava/lang/reflect/Constructor;Ljava/util/List;)V", "getConstructor", "()Ljava/lang/reflect/Constructor;", "getArgumentDescriptors", "()Ljava/util/List;", "org.jetbrains.kotlin:container"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstructorBinding {
    private final List<ValueDescriptor> argumentDescriptors;
    private final Constructor<?> constructor;

    /* JADX WARN: Multi-variable type inference failed */
    public ConstructorBinding(Constructor<?> constructor, List<? extends ValueDescriptor> list) {
        constructor.getClass();
        list.getClass();
        this.constructor = constructor;
        this.argumentDescriptors = list;
    }

    public final List<ValueDescriptor> getArgumentDescriptors() {
        return this.argumentDescriptors;
    }

    public final Constructor<?> getConstructor() {
        return this.constructor;
    }
}
