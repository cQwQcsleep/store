package org.jetbrains.kotlin.load.java;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;
import org.jetbrains.kotlin.load.java.descriptors.JavaClassDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\nJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaClassesTracker;", "", "reportClass", "", "classDescriptor", "Lorg/jetbrains/kotlin/load/java/descriptors/JavaClassDescriptor;", "onCompletedAnalysis", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "clear", "Default", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface JavaClassesTracker {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaClassesTracker$Default;", "Lorg/jetbrains/kotlin/load/java/JavaClassesTracker;", "<init>", "()V", "reportClass", "", "classDescriptor", "Lorg/jetbrains/kotlin/load/java/descriptors/JavaClassDescriptor;", "onCompletedAnalysis", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "clear", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Default implements JavaClassesTracker {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.load.java.JavaClassesTracker
        public void clear() {
        }

        @Override // org.jetbrains.kotlin.load.java.JavaClassesTracker
        public void onCompletedAnalysis(ModuleDescriptor module) {
            module.getClass();
        }

        @Override // org.jetbrains.kotlin.load.java.JavaClassesTracker
        public void reportClass(JavaClassDescriptor classDescriptor) {
            classDescriptor.getClass();
        }
    }

    void clear();

    void onCompletedAnalysis(ModuleDescriptor module);

    void reportClass(JavaClassDescriptor classDescriptor);
}
