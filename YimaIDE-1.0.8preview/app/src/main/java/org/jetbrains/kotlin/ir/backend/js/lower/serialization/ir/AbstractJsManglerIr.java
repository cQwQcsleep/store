package org.jetbrains.kotlin.ir.backend.js.lower.serialization.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinExportChecker;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinMangleComputer;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleMode;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrBasedKotlinManglerImpl;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrExportCheckerVisitor;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrMangleComputer;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/serialization/ir/AbstractJsManglerIr;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/ir/IrBasedKotlinManglerImpl;", "<init>", "()V", "getExportChecker", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinExportChecker;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "compatibleMode", "", "getMangleComputer", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinMangleComputer;", "mode", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleMode;", "JsIrExportChecker", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class AbstractJsManglerIr extends IrBasedKotlinManglerImpl {
    public KotlinExportChecker<IrDeclaration> getExportChecker(boolean compatibleMode) {
        return new JsIrExportChecker(compatibleMode);
    }

    public KotlinMangleComputer<IrDeclaration> getMangleComputer(MangleMode mode, boolean compatibleMode) {
        mode.getClass();
        return new IrMangleComputer(new StringBuilder(256), mode, compatibleMode, false, 8, (DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/serialization/ir/AbstractJsManglerIr$JsIrExportChecker;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/ir/IrExportCheckerVisitor;", "compatibleMode", "", "<init>", "(Z)V", "isPlatformSpecificExported", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class JsIrExportChecker extends IrExportCheckerVisitor {
        public JsIrExportChecker(boolean z) {
            super(z);
        }

        public boolean isPlatformSpecificExported(IrDeclaration irDeclaration) {
            irDeclaration.getClass();
            return false;
        }
    }
}
