package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.psi.search.GlobalSearchScope;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.MetadataFinderFactory;
import org.jetbrains.kotlin.serialization.deserialization.KotlinMetadataFinder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/CliMetadataFinderFactory;", "Lorg/jetbrains/kotlin/load/kotlin/MetadataFinderFactory;", "fileFinderFactory", "Lorg/jetbrains/kotlin/cli/jvm/compiler/CliVirtualFileFinderFactory;", "<init>", "(Lorg/jetbrains/kotlin/cli/jvm/compiler/CliVirtualFileFinderFactory;)V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/serialization/deserialization/KotlinMetadataFinder;", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliMetadataFinderFactory implements MetadataFinderFactory {
    private final CliVirtualFileFinderFactory fileFinderFactory;

    public CliMetadataFinderFactory(CliVirtualFileFinderFactory cliVirtualFileFinderFactory) {
        cliVirtualFileFinderFactory.getClass();
        this.fileFinderFactory = cliVirtualFileFinderFactory;
    }

    public KotlinMetadataFinder create(GlobalSearchScope scope) {
        scope.getClass();
        return this.fileFinderFactory.create(scope);
    }
}
