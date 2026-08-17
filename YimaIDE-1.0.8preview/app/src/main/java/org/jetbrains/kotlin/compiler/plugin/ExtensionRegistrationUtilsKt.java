package org.jetbrains.kotlin.compiler.plugin;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.extensions.ExtensionPoint;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.compiler.plugin.ExtensionRegistrationUtilsKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.ExtensionPointDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a(\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u001a7\u0010\t\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u001d\u0010\f\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000eH\u0007\"#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u00060\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"#\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00060\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u0015"}, d2 = {"registerInProject", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/CompilerPluginRegistrar$ExtensionStorage;", "project", "Lcom/intellij/openapi/project/Project;", "errorMessage", "Lkotlin/Function1;", Argument.Delimiters.none, Argument.Delimiters.none, "registerExtensionsForTest", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "register", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "TEST_ONLY_PLUGIN_REGISTRATION_CALLBACK", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "getTEST_ONLY_PLUGIN_REGISTRATION_CALLBACK", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "TEST_ONLY_PROJECT_CONFIGURATION_CALLBACK", "getTEST_ONLY_PROJECT_CONFIGURATION_CALLBACK", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExtensionRegistrationUtilsKt {
    private static final CompilerConfigurationKey<Function1<CompilerPluginRegistrar.ExtensionStorage, Unit>> TEST_ONLY_PLUGIN_REGISTRATION_CALLBACK;
    private static final CompilerConfigurationKey<Function1<Project, Unit>> TEST_ONLY_PROJECT_CONFIGURATION_CALLBACK;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        TEST_ONLY_PLUGIN_REGISTRATION_CALLBACK = companion.create("TEST_ONLY_PLUGIN_REGISTRATION_CALLBACK");
        TEST_ONLY_PROJECT_CONFIGURATION_CALLBACK = companion.create("TEST_ONLY_PROJECT_CONFIGURATION_CALLBACK");
    }

    public static void a(CompilerPluginRegistrar.PluginDisposable pluginDisposable) {
        pluginDisposable.dispose();
    }

    public static String b(Object obj) {
        obj.getClass();
        return "Error while registering " + obj.getClass().getName() + ' ';
    }

    public static final CompilerConfigurationKey<Function1<CompilerPluginRegistrar.ExtensionStorage, Unit>> getTEST_ONLY_PLUGIN_REGISTRATION_CALLBACK() {
        return TEST_ONLY_PLUGIN_REGISTRATION_CALLBACK;
    }

    public static final CompilerConfigurationKey<Function1<Project, Unit>> getTEST_ONLY_PROJECT_CONFIGURATION_CALLBACK() {
        return TEST_ONLY_PROJECT_CONFIGURATION_CALLBACK;
    }

    public static final void registerExtensionsForTest(Project project, CompilerConfiguration compilerConfiguration, Function2<? super CompilerPluginRegistrar.ExtensionStorage, ? super CompilerConfiguration, Unit> function2) {
        project.getClass();
        compilerConfiguration.getClass();
        function2.getClass();
        CompilerPluginRegistrar.ExtensionStorage extensionsStorage = FrontendConfigurationKeysKt.getExtensionsStorage(compilerConfiguration);
        if (extensionsStorage == null) {
            k2d.a("Extensions storage is not registered");
        } else {
            function2.invoke(extensionsStorage, compilerConfiguration);
            registerInProject$default(extensionsStorage, project, null, 2, null);
        }
    }

    public static final void registerInProject(CompilerPluginRegistrar.ExtensionStorage extensionStorage, Project project, Function1<Object, String> function1) {
        extensionStorage.getClass();
        project.getClass();
        function1.getClass();
        for (Map.Entry<ExtensionPointDescriptor<?>, List<Object>> entry : extensionStorage.getRegisteredExtensions().entrySet()) {
            ExtensionPointDescriptor<?> key = entry.getKey();
            for (Object obj : entry.getValue()) {
                try {
                    ExtensionPoint extensionPointIfRegistered = project.getExtensionArea().getExtensionPointIfRegistered(key.getName());
                    if (extensionPointIfRegistered != null) {
                        Object[] extensions = extensionPointIfRegistered.getExtensions();
                        extensions.getClass();
                        int length = extensions.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                extensionPointIfRegistered.registerExtension(obj, project);
                                break;
                            } else if (Intrinsics.areEqual(extensions[i].getClass(), obj.getClass())) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                } catch (AbstractMethodError e) {
                    mg9.a((String) function1.invoke(obj), e);
                    return;
                }
            }
        }
        for (final CompilerPluginRegistrar.PluginDisposable pluginDisposable : extensionStorage.getDisposables()) {
            Disposer.register(project, new Disposable() { // from class: wj4
                public final void dispose() {
                    ExtensionRegistrationUtilsKt.a(pluginDisposable);
                }
            });
        }
    }

    public static /* synthetic */ void registerInProject$default(CompilerPluginRegistrar.ExtensionStorage extensionStorage, Project project, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: xj4
                public final Object invoke(Object obj2) {
                    return ExtensionRegistrationUtilsKt.b(obj2);
                }
            };
        }
        registerInProject(extensionStorage, project, function1);
    }
}
