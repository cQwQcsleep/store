package org.jetbrains.kotlin.resolve;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorNonRoot;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@DefaultImplementation(impl = Default.class)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0006J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&Ê\u0001\f\b\b\u0012\b\b\t\u0012\u0004\b\t0\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/OverloadFilter;", "", "filterPackageMemberOverloads", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorNonRoot;", "overloads", "Default", "org.jetbrains.kotlin:frontend", "Lorg/jetbrains/kotlin/container/DefaultImplementation;", "impl", "Lorg/jetbrains/kotlin/resolve/OverloadFilter$Default;"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface OverloadFilter {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/OverloadFilter$Default;", "Lorg/jetbrains/kotlin/resolve/OverloadFilter;", "<init>", "()V", "filterPackageMemberOverloads", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorNonRoot;", "overloads", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Default implements OverloadFilter {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.resolve.OverloadFilter
        public Collection<DeclarationDescriptorNonRoot> filterPackageMemberOverloads(Collection<? extends DeclarationDescriptorNonRoot> overloads) {
            overloads.getClass();
            return overloads;
        }
    }

    Collection<DeclarationDescriptorNonRoot> filterPackageMemberOverloads(Collection<? extends DeclarationDescriptorNonRoot> overloads);
}
