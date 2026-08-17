package org.jetbrains.kotlin.compiler.plugin;

import com.intellij.mock.MockProject;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Deprecated(level = DeprecationLevel.ERROR, message = "ComponentRegistrar is deprecated. Please use CompilerPluginRegistrar instead. Check https://youtrack.jetbrains.com/issue/KT-52665 for more details", replaceWith = @ReplaceWith(expression = "CompilerPluginRegistrar", imports = {"org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar"}))
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bg\u0018\u0000 \f2\u00020\u0001:\u0001\fJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/ComponentRegistrar;", Argument.Delimiters.none, "registerProjectComponents", Argument.Delimiters.none, "project", "Lcom/intellij/mock/MockProject;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "supportsK2", Argument.Delimiters.none, "getSupportsK2", "()Z", "Companion", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@ExperimentalCompilerApi
public interface ComponentRegistrar {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/compiler/plugin/ComponentRegistrar$Companion;", Argument.Delimiters.none, "<init>", "()V", "PLUGIN_COMPONENT_REGISTRARS", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/compiler/plugin/ComponentRegistrar;", "getPLUGIN_COMPONENT_REGISTRARS$annotations", "getPLUGIN_COMPONENT_REGISTRARS", "()Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", "org.jetbrains.kotlin:plugin-api"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final CompilerConfigurationKey<List<ComponentRegistrar>> PLUGIN_COMPONENT_REGISTRARS = CompilerConfigurationKey.INSTANCE.create("PLUGIN_COMPONENT_REGISTRARS");

        private Companion() {
        }

        public static /* synthetic */ void getPLUGIN_COMPONENT_REGISTRARS$annotations() {
        }

        public final CompilerConfigurationKey<List<ComponentRegistrar>> getPLUGIN_COMPONENT_REGISTRARS() {
            return PLUGIN_COMPONENT_REGISTRARS;
        }
    }

    default boolean getSupportsK2() {
        return false;
    }

    void registerProjectComponents(MockProject project, CompilerConfiguration configuration);
}
