package org.jetbrains.kotlin.builtins;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@DefaultImplementation(impl = Default.class)
public interface PlatformToKotlinClassMapper {

    public static class Default implements PlatformToKotlinClassMapper {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "classDescriptor";
            } else {
                objArr[0] = "org/jetbrains/kotlin/builtins/PlatformToKotlinClassMapper$Default";
            }
            if (i != 1) {
                objArr[1] = "org/jetbrains/kotlin/builtins/PlatformToKotlinClassMapper$Default";
            } else {
                objArr[1] = "mapPlatformClass";
            }
            if (i != 1) {
                objArr[2] = "mapPlatformClass";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        @Override // org.jetbrains.kotlin.builtins.PlatformToKotlinClassMapper
        public Collection<ClassDescriptor> mapPlatformClass(ClassDescriptor classDescriptor) {
            if (classDescriptor == null) {
                $$$reportNull$$$0(0);
            }
            List list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(1);
            }
            return list;
        }
    }

    Collection<ClassDescriptor> mapPlatformClass(ClassDescriptor classDescriptor);
}
