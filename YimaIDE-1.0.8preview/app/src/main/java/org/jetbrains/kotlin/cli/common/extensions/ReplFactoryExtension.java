package org.jetbrains.kotlin.cli.common.extensions;

import com.intellij.core.JavaCoreProjectEnvironment;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.repl.ReplCompiler;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.ProjectExtensionDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/extensions/ReplFactoryExtension;", Argument.Delimiters.none, "makeReplCompiler", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCompiler;", "templateClassName", Argument.Delimiters.none, "templateClasspath", Argument.Delimiters.none, "Ljava/io/File;", "baseClassLoader", "Ljava/lang/ClassLoader;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "projectEnvironment", "Lcom/intellij/core/JavaCoreProjectEnvironment;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ReplFactoryExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/extensions/ReplFactoryExtension$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/cli/common/extensions/ReplFactoryExtension;", "<init>", "()V", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ProjectExtensionDescriptor<ReplFactoryExtension> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("org.jetbrains.kotlin.replFactoryExtension", ReplFactoryExtension.class);
        }
    }

    ReplCompiler makeReplCompiler(String templateClassName, List<? extends File> templateClasspath, ClassLoader baseClassLoader, CompilerConfiguration configuration, JavaCoreProjectEnvironment projectEnvironment);
}
