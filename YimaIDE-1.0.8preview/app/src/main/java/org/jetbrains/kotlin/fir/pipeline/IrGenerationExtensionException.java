package org.jetbrains.kotlin.fir.pipeline;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/IrGenerationExtensionException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "cause", Argument.Delimiters.none, "extensionClass", "Ljava/lang/Class;", "Lorg/jetbrains/kotlin/backend/common/extensions/IrGenerationExtension;", "<init>", "(Ljava/lang/Throwable;Ljava/lang/Class;)V", "getExtensionClass", "()Ljava/lang/Class;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrGenerationExtensionException extends RuntimeException {
    private final Class<? extends IrGenerationExtension> extensionClass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IrGenerationExtensionException(Throwable th, Class<? extends IrGenerationExtension> cls) {
        super(th.getMessage(), th);
        th.getClass();
        cls.getClass();
        this.extensionClass = cls;
    }

    public final Class<? extends IrGenerationExtension> getExtensionClass() {
        return this.extensionClass;
    }
}
