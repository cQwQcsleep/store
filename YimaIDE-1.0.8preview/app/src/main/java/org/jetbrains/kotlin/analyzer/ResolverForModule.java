package org.jetbrains.kotlin.analyzer;

import kotlin.Metadata;
import org.jetbrains.kotlin.container.ComponentProvider;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/ResolverForModule;", "", "packageFragmentProvider", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "componentProvider", "Lorg/jetbrains/kotlin/container/ComponentProvider;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;Lorg/jetbrains/kotlin/container/ComponentProvider;)V", "getPackageFragmentProvider", "()Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "getComponentProvider", "()Lorg/jetbrains/kotlin/container/ComponentProvider;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ResolverForModule {
    private final ComponentProvider componentProvider;
    private final PackageFragmentProvider packageFragmentProvider;

    public ResolverForModule(PackageFragmentProvider packageFragmentProvider, ComponentProvider componentProvider) {
        packageFragmentProvider.getClass();
        componentProvider.getClass();
        this.packageFragmentProvider = packageFragmentProvider;
        this.componentProvider = componentProvider;
    }

    public final ComponentProvider getComponentProvider() {
        return this.componentProvider;
    }

    public final PackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }
}
