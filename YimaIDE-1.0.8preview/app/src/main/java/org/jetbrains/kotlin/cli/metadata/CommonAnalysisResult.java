package org.jetbrains.kotlin.cli.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.resolve.BindingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/CommonAnalysisResult;", Argument.Delimiters.none, "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Lorg/jetbrains/kotlin/resolve/BindingContext;)V", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getBindingContext", "()Lorg/jetbrains/kotlin/resolve/BindingContext;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CommonAnalysisResult {
    private final BindingContext bindingContext;
    private final ModuleDescriptor moduleDescriptor;

    public CommonAnalysisResult(ModuleDescriptor moduleDescriptor, BindingContext bindingContext) {
        moduleDescriptor.getClass();
        bindingContext.getClass();
        this.moduleDescriptor = moduleDescriptor;
        this.bindingContext = bindingContext;
    }

    public static /* synthetic */ CommonAnalysisResult copy$default(CommonAnalysisResult commonAnalysisResult, ModuleDescriptor moduleDescriptor, BindingContext bindingContext, int i, Object obj) {
        if ((i & 1) != 0) {
            moduleDescriptor = commonAnalysisResult.moduleDescriptor;
        }
        if ((i & 2) != 0) {
            bindingContext = commonAnalysisResult.bindingContext;
        }
        return commonAnalysisResult.copy(moduleDescriptor, bindingContext);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final BindingContext getBindingContext() {
        return this.bindingContext;
    }

    public final CommonAnalysisResult copy(ModuleDescriptor moduleDescriptor, BindingContext bindingContext) {
        moduleDescriptor.getClass();
        bindingContext.getClass();
        return new CommonAnalysisResult(moduleDescriptor, bindingContext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommonAnalysisResult)) {
            return false;
        }
        CommonAnalysisResult commonAnalysisResult = (CommonAnalysisResult) other;
        return Intrinsics.areEqual(this.moduleDescriptor, commonAnalysisResult.moduleDescriptor) && Intrinsics.areEqual(this.bindingContext, commonAnalysisResult.bindingContext);
    }

    public final BindingContext getBindingContext() {
        return this.bindingContext;
    }

    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public int hashCode() {
        return (this.moduleDescriptor.hashCode() * 31) + this.bindingContext.hashCode();
    }

    public String toString() {
        return "CommonAnalysisResult(moduleDescriptor=" + this.moduleDescriptor + ", bindingContext=" + this.bindingContext + ')';
    }
}
