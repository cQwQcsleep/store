package org.jetbrains.kotlin.ir.backend.js.lower.serialization.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinExportChecker;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinMangleComputer;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleMode;
import org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorBasedKotlinManglerImpl;
import org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorExportCheckerVisitor;
import org.jetbrains.kotlin.backend.common.serialization.mangle.descriptor.DescriptorMangleComputer;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/serialization/ir/AbstractJsDescriptorMangler;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/descriptor/DescriptorBasedKotlinManglerImpl;", "<init>", "()V", "getExportChecker", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinExportChecker;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "compatibleMode", "", "getMangleComputer", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinMangleComputer;", "mode", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleMode;", "Companion", "JsDescriptorExportChecker", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class AbstractJsDescriptorMangler extends DescriptorBasedKotlinManglerImpl {
    private static final JsDescriptorExportChecker exportChecker = new JsDescriptorExportChecker();

    public KotlinExportChecker<DeclarationDescriptor> getExportChecker(boolean compatibleMode) {
        return exportChecker;
    }

    public KotlinMangleComputer<DeclarationDescriptor> getMangleComputer(MangleMode mode, boolean compatibleMode) {
        mode.getClass();
        return new DescriptorMangleComputer(new StringBuilder(256), mode);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/serialization/ir/AbstractJsDescriptorMangler$JsDescriptorExportChecker;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/descriptor/DescriptorExportCheckerVisitor;", "<init>", "()V", "isPlatformSpecificExported", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class JsDescriptorExportChecker extends DescriptorExportCheckerVisitor {
        public boolean isPlatformSpecificExported(DeclarationDescriptor declarationDescriptor) {
            declarationDescriptor.getClass();
            return false;
        }
    }
}
