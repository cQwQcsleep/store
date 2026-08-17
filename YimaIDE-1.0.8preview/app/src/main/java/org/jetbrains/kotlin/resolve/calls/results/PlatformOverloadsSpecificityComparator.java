package org.jetbrains.kotlin.resolve.calls.results;

import kotlin.Metadata;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@DefaultImplementation(impl = None.class)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0007J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&Ê\u0001\f\b\t\u0012\b\b\n\u0012\u0004\b\t0\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/results/PlatformOverloadsSpecificityComparator;", "", "isMoreSpecificShape", "", "specific", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "general", "None", "org.jetbrains.kotlin:resolution", "Lorg/jetbrains/kotlin/container/DefaultImplementation;", "impl", "Lorg/jetbrains/kotlin/resolve/calls/results/PlatformOverloadsSpecificityComparator$None;"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface PlatformOverloadsSpecificityComparator {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/results/PlatformOverloadsSpecificityComparator$None;", "Lorg/jetbrains/kotlin/resolve/calls/results/PlatformOverloadsSpecificityComparator;", "<init>", "()V", "isMoreSpecificShape", "", "specific", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "general", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class None implements PlatformOverloadsSpecificityComparator {
        public static final None INSTANCE = new None();

        private None() {
        }

        @Override // org.jetbrains.kotlin.resolve.calls.results.PlatformOverloadsSpecificityComparator
        public boolean isMoreSpecificShape(CallableDescriptor specific, CallableDescriptor general) {
            specific.getClass();
            general.getClass();
            return false;
        }
    }

    boolean isMoreSpecificShape(CallableDescriptor specific, CallableDescriptor general);
}
