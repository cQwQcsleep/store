package org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.LegacyK2CliPipeline;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.jvm.compiler.VfsBasedProjectEnvironment;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/MinimizedFrontendContext;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/legacy/pipeline/FrontendContext;", "projectEnvironment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "extensionRegistrars", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionRegistrar;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;Ljava/util/List;Lorg/jetbrains/kotlin/config/CompilerConfiguration;)V", "getProjectEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/VfsBasedProjectEnvironment;", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "getExtensionRegistrars", "()Ljava/util/List;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "org.jetbrains.kotlin:cli-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@LegacyK2CliPipeline
public final class MinimizedFrontendContext implements FrontendContext {
    private final CompilerConfiguration configuration;
    private final List<FirExtensionRegistrar> extensionRegistrars;
    private final MessageCollector messageCollector;
    private final VfsBasedProjectEnvironment projectEnvironment;

    /* JADX WARN: Multi-variable type inference failed */
    public MinimizedFrontendContext(VfsBasedProjectEnvironment vfsBasedProjectEnvironment, MessageCollector messageCollector, List<? extends FirExtensionRegistrar> list, CompilerConfiguration compilerConfiguration) {
        vfsBasedProjectEnvironment.getClass();
        messageCollector.getClass();
        list.getClass();
        compilerConfiguration.getClass();
        this.projectEnvironment = vfsBasedProjectEnvironment;
        this.messageCollector = messageCollector;
        this.extensionRegistrars = list;
        this.configuration = compilerConfiguration;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.FrontendContext
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.FrontendContext
    public List<FirExtensionRegistrar> getExtensionRegistrars() {
        return this.extensionRegistrars;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.FrontendContext
    public MessageCollector getMessageCollector() {
        return this.messageCollector;
    }

    @Override // org.jetbrains.kotlin.cli.jvm.compiler.legacy.pipeline.FrontendContext
    public VfsBasedProjectEnvironment getProjectEnvironment() {
        return this.projectEnvironment;
    }
}
