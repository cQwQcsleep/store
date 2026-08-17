package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\u0001H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/DelegatedDescriptorVisibility;", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "delegate", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/Visibility;)V", "getDelegate", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", "mustCheckInImports", Argument.Delimiters.none, "internalDisplayName", Argument.Delimiters.none, "getInternalDisplayName", "()Ljava/lang/String;", "externalDisplayName", "getExternalDisplayName", "normalize", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DelegatedDescriptorVisibility extends DescriptorVisibility {
    private final Visibility delegate;

    public DelegatedDescriptorVisibility(Visibility visibility) {
        visibility.getClass();
        this.delegate = visibility;
    }

    @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
    public Visibility getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
    public String getExternalDisplayName() {
        return getDelegate().getExternalDisplayName();
    }

    @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
    public String getInternalDisplayName() {
        return getDelegate().getName();
    }

    @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
    public boolean mustCheckInImports() {
        return getDelegate().mustCheckInImports();
    }

    @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
    public DescriptorVisibility normalize() {
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.toDescriptorVisibility(getDelegate().normalize());
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }
}
