package org.jetbrains.kotlin.analyzer;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.resolve.BindingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/AnalysisResult$RetryWithAdditionalRoots;", "Lorg/jetbrains/kotlin/analyzer/AnalysisResult;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "additionalJavaRoots", Argument.Delimiters.none, "Ljava/io/File;", "additionalKotlinRoots", "additionalClassPathRoots", "addToEnvironment", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/resolve/BindingContext;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;Ljava/util/List;Ljava/util/List;Ljava/util/List;Z)V", "getAdditionalJavaRoots", "()Ljava/util/List;", "getAdditionalKotlinRoots", "getAdditionalClassPathRoots", "getAddToEnvironment", "()Z", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnalysisResult$RetryWithAdditionalRoots extends AnalysisResult {
    private final boolean addToEnvironment;
    private final List<File> additionalClassPathRoots;
    private final List<File> additionalJavaRoots;
    private final List<File> additionalKotlinRoots;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AnalysisResult$RetryWithAdditionalRoots(BindingContext bindingContext, ModuleDescriptor moduleDescriptor, List<? extends File> list, List<? extends File> list2, List<? extends File> list3, boolean z) {
        super(bindingContext, moduleDescriptor, false, 4, (DefaultConstructorMarker) null);
        bindingContext.getClass();
        moduleDescriptor.getClass();
        list.getClass();
        list2.getClass();
        list3.getClass();
        this.additionalJavaRoots = list;
        this.additionalKotlinRoots = list2;
        this.additionalClassPathRoots = list3;
        this.addToEnvironment = z;
    }

    public final boolean getAddToEnvironment() {
        return this.addToEnvironment;
    }

    public final List<File> getAdditionalClassPathRoots() {
        return this.additionalClassPathRoots;
    }

    public final List<File> getAdditionalJavaRoots() {
        return this.additionalJavaRoots;
    }

    public final List<File> getAdditionalKotlinRoots() {
        return this.additionalKotlinRoots;
    }

    public /* synthetic */ AnalysisResult$RetryWithAdditionalRoots(BindingContext bindingContext, ModuleDescriptor moduleDescriptor, List list, List list2, List list3, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(bindingContext, moduleDescriptor, list, list2, (i & 16) != 0 ? CollectionsKt.emptyList() : list3, (i & 32) != 0 ? true : z);
    }
}
