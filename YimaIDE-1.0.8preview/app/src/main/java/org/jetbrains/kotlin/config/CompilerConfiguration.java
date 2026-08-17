package org.jetbrains.kotlin.config;

import com.intellij.openapi.util.Key;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 12\u00020\u0001:\u000212B\t\b\u0017¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\f\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0086\u0002¢\u0006\u0002\u0010\u0010J.\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0011\u001a\u0002H\rH\u0086\u0002¢\u0006\u0002\u0010\u0012J1\u0010\u0013\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\r0\u0014¢\u0006\u0002\u0010\u0015J#\u0010\u0016\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0017\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fJ&\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\r0\u0019\"\u0004\b\u0000\u0010\r2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00190\u000fJ8\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001d0\u001b\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u001d2\u0018\u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001d0\u001b0\u000fJ+\u0010\u001e\u001a\u00020\u001f\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010 \u001a\u0002H\r¢\u0006\u0002\u0010!J+\u0010\"\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010 \u001a\u0002H\r¢\u0006\u0002\u0010\u0012J-\u0010#\u001a\u00020\u001f\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f2\b\u0010 \u001a\u0004\u0018\u0001H\r¢\u0006\u0002\u0010!J-\u0010$\u001a\u00020\u001f\"\u0004\b\u0000\u0010\r2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00190\u000f2\u0006\u0010 \u001a\u0002H\r¢\u0006\u0002\u0010!JA\u0010\u001e\u001a\u00020\u001f\"\u0004\b\u0000\u0010\u001c\"\u0004\b\u0001\u0010\u001d2\u0018\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001d0\u001b0\u000f2\u0006\u0010\u000e\u001a\u0002H\u001c2\u0006\u0010 \u001a\u0002H\u001d¢\u0006\u0002\u0010&J4\u0010'\u001a\u00020\u001f\"\b\b\u0000\u0010\r*\u00020\u00012\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00190\u000f2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u0002H\r\u0018\u00010)J:\u0010'\u001a\u00020\u001f\"\b\b\u0000\u0010\r*\u00020\u00012\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00190\u000f2\u0006\u0010*\u001a\u00020+2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H\r0)J\u0006\u0010,\u001a\u00020\u0000J'\u0010-\u001a\u0004\u0018\u0001H\r\"\b\b\u0000\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fH\u0002¢\u0006\u0002\u0010\u0010J\n\u0010.\u001a\u00020/H\u0096\u0080\u0004J\b\u00100\u001a\u00020\u001fH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000b¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/config/CompilerConfiguration;", Argument.Delimiters.none, "<init>", "()V", "map", Argument.Delimiters.none, "Lcom/intellij/openapi/util/Key;", "isReadOnly", Argument.Delimiters.none, "()Z", "setReadOnly", "(Z)V", "get", "T", "key", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "(Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;)Ljava/lang/Object;", "defaultValue", "(Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;Ljava/lang/Object;)Ljava/lang/Object;", "getOrDefault", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getNotNull", "getBoolean", "getList", Argument.Delimiters.none, "getMap", Argument.Delimiters.none, "K", "V", "put", Argument.Delimiters.none, "value", "(Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;Ljava/lang/Object;)V", "putIfAbsent", "putIfNotNull", "add", "configurationKey", "(Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;Ljava/lang/Object;Ljava/lang/Object;)V", "addAll", "values", Argument.Delimiters.none, "index", Argument.Delimiters.none, "copy", "getValue", "toString", Argument.Delimiters.none, "checkReadOnly", "Companion", "Internals", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerConfiguration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean isReadOnly;
    private final Map<Key<?>, Object> map = new LinkedHashMap();

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/config/CompilerConfiguration$Internals;", Argument.Delimiters.none, "message", Argument.Delimiters.none, "()Ljava/lang/String;", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface Internals {
        String message();
    }

    @Internals(message = "Consider using `CompilerConfiguration.create()` from :cli-base module instead")
    public CompilerConfiguration() {
    }

    private final void checkReadOnly() {
        if (this.isReadOnly) {
            k2d.a("CompilerConfiguration is read-only");
        }
    }

    private final <T> T getValue(CompilerConfigurationKey<? extends T> key) {
        return (T) this.map.get(key.getIdeaKey$org_jetbrains_kotlin_config());
    }

    public final <T> void add(CompilerConfigurationKey<? extends List<? extends T>> key, T value) {
        key.getClass();
        checkReadOnly();
        Map<Key<?>, Object> map = this.map;
        Key<? extends List<? extends T>> ideaKey$org_jetbrains_kotlin_config = key.getIdeaKey$org_jetbrains_kotlin_config();
        Object arrayList = map.get(ideaKey$org_jetbrains_kotlin_config);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(ideaKey$org_jetbrains_kotlin_config, arrayList);
        }
        TypeIntrinsics.asMutableList(arrayList).add(value);
    }

    public final <T> void addAll(CompilerConfigurationKey<? extends List<? extends T>> key, int index, Collection<? extends T> values) {
        key.getClass();
        values.getClass();
        checkReadOnly();
        Map<Key<?>, Object> map = this.map;
        Key<? extends List<? extends T>> ideaKey$org_jetbrains_kotlin_config = key.getIdeaKey$org_jetbrains_kotlin_config();
        Object arrayList = map.get(ideaKey$org_jetbrains_kotlin_config);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map.put(ideaKey$org_jetbrains_kotlin_config, arrayList);
        }
        TypeIntrinsics.asMutableList(arrayList).addAll(index, values);
    }

    public final CompilerConfiguration copy() {
        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.map.putAll(this.map);
        return compilerConfiguration;
    }

    public final <T> T get(CompilerConfigurationKey<? extends T> key) {
        key.getClass();
        Object value = getValue(key);
        if (value != null) {
            return (T) INSTANCE.unmodifiable(value);
        }
        return null;
    }

    public final boolean getBoolean(CompilerConfigurationKey<Boolean> key) {
        key.getClass();
        return ((Boolean) get(key, Boolean.FALSE)).booleanValue();
    }

    public final <T> List<T> getList(CompilerConfigurationKey<? extends List<? extends T>> key) {
        key.getClass();
        return (List) get(key, CollectionsKt.emptyList());
    }

    public final <K, V> Map<K, V> getMap(CompilerConfigurationKey<? extends Map<K, ? extends V>> key) {
        key.getClass();
        return (Map) get(key, MapsKt.emptyMap());
    }

    public final <T> T getNotNull(CompilerConfigurationKey<? extends T> key) {
        key.getClass();
        T t = (T) getValue(key);
        if (t != null) {
            return t;
        }
        w04.a("No value for configuration key: ", key);
        return null;
    }

    public final <T> T getOrDefault(CompilerConfigurationKey<? extends T> key, Function0<? extends T> defaultValue) {
        key.getClass();
        defaultValue.getClass();
        T t = (T) getValue(key);
        return t == null ? (T) defaultValue.invoke() : t;
    }

    /* JADX INFO: renamed from: isReadOnly, reason: from getter */
    public final boolean getIsReadOnly() {
        return this.isReadOnly;
    }

    public final <K, V> void put(CompilerConfigurationKey<? extends Map<K, ? extends V>> configurationKey, K key, V value) {
        configurationKey.getClass();
        checkReadOnly();
        Map<Key<?>, Object> map = this.map;
        Key<? extends Map<K, ? extends V>> ideaKey$org_jetbrains_kotlin_config = configurationKey.getIdeaKey$org_jetbrains_kotlin_config();
        Object linkedHashMap = map.get(ideaKey$org_jetbrains_kotlin_config);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
            map.put(ideaKey$org_jetbrains_kotlin_config, linkedHashMap);
        }
        TypeIntrinsics.asMutableMap(linkedHashMap).put(key, value);
    }

    public final <T> T putIfAbsent(CompilerConfigurationKey<? extends T> key, T value) {
        key.getClass();
        value.getClass();
        T t = (T) getValue(key);
        if (t != null) {
            return t;
        }
        checkReadOnly();
        put(key, value);
        return value;
    }

    public final <T> void putIfNotNull(CompilerConfigurationKey<? extends T> key, T value) {
        key.getClass();
        if (value != null) {
            put(key, value);
        }
    }

    public final void setReadOnly(boolean z) {
        this.isReadOnly = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Key<?>, Object> entry : this.map.entrySet()) {
            Key<?> key = entry.getKey();
            Object value = entry.getValue();
            sb.append(key);
            sb.append(":");
            if (value instanceof Collection) {
                sb.append('\n');
                for (Object obj : (Collection) value) {
                    sb.append("  ");
                    sb.append(obj);
                    sb.append('\n');
                }
            } else if (value instanceof Map) {
                sb.append('\n');
                for (Map.Entry entry2 : ((Map) value).entrySet()) {
                    Object key2 = entry2.getKey();
                    Object value2 = entry2.getValue();
                    sb.append("  ");
                    sb.append(key2);
                    sb.append("=");
                    sb.append(value2);
                    sb.append('\n');
                }
            } else {
                sb.append(Argument.Delimiters.space);
                sb.append(value);
                sb.append('\n');
            }
        }
        return StringsKt.trim(sb.toString()).toString();
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0005*\u0002H\u0005H\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/config/CompilerConfiguration$Companion;", Argument.Delimiters.none, "<init>", "()V", "unmodifiable", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <T> T unmodifiable(T t) {
            if (t instanceof List) {
                return (T) Collections.unmodifiableList((List) t);
            }
            if (t instanceof Map) {
                return (T) Collections.unmodifiableMap((Map) t);
            }
            if (t instanceof Set) {
                return (T) Collections.unmodifiableSet((Set) t);
            }
            return t instanceof Collection ? (T) Collections.unmodifiableCollection((Collection) t) : t;
        }

        private Companion() {
        }
    }

    public final <T> T get(CompilerConfigurationKey<? extends T> key, T defaultValue) {
        key.getClass();
        defaultValue.getClass();
        T t = (T) getValue(key);
        return t == null ? defaultValue : t;
    }

    public final <T> void put(CompilerConfigurationKey<? extends T> key, T value) {
        key.getClass();
        value.getClass();
        checkReadOnly();
        this.map.put(key.getIdeaKey$org_jetbrains_kotlin_config(), value);
    }

    public final <T> void addAll(CompilerConfigurationKey<? extends List<? extends T>> key, Collection<? extends T> values) {
        key.getClass();
        if (values != null) {
            addAll(key, getList(key).size(), values);
        }
    }
}
