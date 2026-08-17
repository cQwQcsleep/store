package org.jetbrains.kotlin.fir.extensions;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.extensions.ExtensionPointDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&JB\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\tH&¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/CollectAdditionalSourceFilesExtension;", Argument.Delimiters.none, "<init>", "()V", "isApplicable", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "collectSources", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "environment", "findVirtualFile", "Lkotlin/Function1;", "Ljava/io/File;", "Lcom/intellij/openapi/vfs/VirtualFile;", ModuleXmlParser.SOURCES, "Companion", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CollectAdditionalSourceFilesExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract Iterable<KtSourceFile> collectSources(Object environment, CompilerConfiguration configuration, Function1<? super File, ? extends VirtualFile> findVirtualFile, Iterable<? extends KtSourceFile> sources);

    public abstract boolean isApplicable(CompilerConfiguration configuration);

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/CollectAdditionalSourceFilesExtension$Companion;", "Lorg/jetbrains/kotlin/extensions/ExtensionPointDescriptor;", "Lorg/jetbrains/kotlin/fir/extensions/CollectAdditionalSourceFilesExtension;", "<init>", "()V", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ExtensionPointDescriptor<CollectAdditionalSourceFilesExtension> {
        private Companion() {
            super("org.jetbrains.kotlin.fir.collectAdditionalSourceFilesExtension", CollectAdditionalSourceFilesExtension.class);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
