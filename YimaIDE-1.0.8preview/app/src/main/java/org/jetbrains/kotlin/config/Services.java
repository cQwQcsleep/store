package org.jetbrains.kotlin.config;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB!\b\u0002\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0086\u0002¢\u0006\u0002\u0010\nR\u001e\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/config/Services;", Argument.Delimiters.none, "map", Argument.Delimiters.none, "Ljava/lang/Class;", "<init>", "(Ljava/util/Map;)V", "get", "T", "interfaceClass", "(Ljava/lang/Class;)Ljava/lang/Object;", "Companion", "Builder", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Services {
    public static final Services EMPTY = new Builder().build();
    private final Map<Class<?>, Object> map;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0007\u001a\u00020\u0000\"\b\b\u0000\u0010\b*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\n\u001a\u0002H\b¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rR\u001e\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/Services$Builder;", Argument.Delimiters.none, "<init>", "()V", "map", "Ljava/util/HashMap;", "Ljava/lang/Class;", "register", "T", "interfaceClass", "implementation", "(Ljava/lang/Class;Ljava/lang/Object;)Lorg/jetbrains/kotlin/config/Services$Builder;", "build", "Lorg/jetbrains/kotlin/config/Services;", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Builder {
        private final HashMap<Class<?>, Object> map = new HashMap<>();

        public final Services build() {
            return new Services(this.map, null);
        }

        public final <T> Builder register(Class<T> interfaceClass, T implementation) {
            interfaceClass.getClass();
            implementation.getClass();
            this.map.put(interfaceClass, implementation);
            return this;
        }
    }

    private Services(Map<Class<?>, ? extends Object> map) {
        this.map = map;
    }

    public final <T> T get(Class<T> interfaceClass) {
        interfaceClass.getClass();
        return (T) this.map.get(interfaceClass);
    }

    public /* synthetic */ Services(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }
}
