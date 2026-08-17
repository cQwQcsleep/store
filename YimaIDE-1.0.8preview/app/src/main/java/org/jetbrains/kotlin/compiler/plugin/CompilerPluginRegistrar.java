package org.jetbrains.kotlin.compiler.plugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.ExtensionPointDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b'\u0018\u0000 \u00112\u00020\u0001:\u0003\u0011\u0012\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\r\u001a\u00020\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar;", Argument.Delimiters.none, "<init>", "()V", "pluginId", Argument.Delimiters.none, "getPluginId", "()Ljava/lang/String;", "registerExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "supportsK2", Argument.Delimiters.none, "getSupportsK2", "()Z", "Companion", "ExtensionStorage", "PluginDisposable", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@ExperimentalCompilerApi
public abstract class CompilerPluginRegistrar {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final CompilerConfigurationKey<List<CompilerPluginRegistrar>> COMPILER_PLUGIN_REGISTRARS = CompilerConfigurationKey.INSTANCE.create("COMPILER_PLUGIN_REGISTRARS");

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0007\"\b\b\u0000\u0010\u000f*\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0006H\u0086\u0002J'\u0010\u0011\u001a\u00020\u0012\"\b\b\u0000\u0010\u000f*\u00020\u0001*\b\u0012\u0004\u0012\u0002H\u000f0\u00062\u0006\u0010\u0013\u001a\u0002H\u000f¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u000bR'\u0010\u0004\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", Argument.Delimiters.none, "<init>", "()V", "registeredExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/extensions/ExtensionPointDescriptor;", Argument.Delimiters.none, "getRegisteredExtensions", "()Ljava/util/Map;", "disposables", "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$PluginDisposable;", "getDisposables", "()Ljava/util/List;", "get", "T", "descriptor", "registerExtension", Argument.Delimiters.none, "extension", "(Lorg/jetbrains/kotlin/extensions/ExtensionPointDescriptor;Ljava/lang/Object;)V", "registerDisposable", "disposable", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ExtensionStorage {
        private final Map<ExtensionPointDescriptor<?>, List<Object>> registeredExtensions = new LinkedHashMap();
        private final List<PluginDisposable> disposables = new ArrayList();

        public final <T> List<T> get(ExtensionPointDescriptor<T> descriptor) {
            descriptor.getClass();
            List<T> list = (List) this.registeredExtensions.get(descriptor);
            return list == null ? CollectionsKt.emptyList() : list;
        }

        public final List<PluginDisposable> getDisposables() {
            return this.disposables;
        }

        public final Map<ExtensionPointDescriptor<?>, List<Object>> getRegisteredExtensions() {
            return this.registeredExtensions;
        }

        public final void registerDisposable(PluginDisposable disposable) {
            disposable.getClass();
            this.disposables.add(disposable);
        }

        public final <T> void registerExtension(ExtensionPointDescriptor<T> extensionPointDescriptor, T t) {
            extensionPointDescriptor.getClass();
            t.getClass();
            Map<ExtensionPointDescriptor<?>, List<Object>> map = this.registeredExtensions;
            List<Object> arrayList = map.get(extensionPointDescriptor);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(extensionPointDescriptor, arrayList);
            }
            arrayList.add(t);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$PluginDisposable;", Argument.Delimiters.none, "dispose", Argument.Delimiters.none, "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface PluginDisposable {
        void dispose();
    }

    public abstract String getPluginId();

    public abstract boolean getSupportsK2();

    public abstract void registerExtensions(ExtensionStorage extensionStorage, CompilerConfiguration compilerConfiguration);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$Companion;", Argument.Delimiters.none, "<init>", "()V", "COMPILER_PLUGIN_REGISTRARS", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar;", "getCOMPILER_PLUGIN_REGISTRARS", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CompilerConfigurationKey<List<CompilerPluginRegistrar>> getCOMPILER_PLUGIN_REGISTRARS() {
            return CompilerPluginRegistrar.COMPILER_PLUGIN_REGISTRARS;
        }

        private Companion() {
        }
    }
}
