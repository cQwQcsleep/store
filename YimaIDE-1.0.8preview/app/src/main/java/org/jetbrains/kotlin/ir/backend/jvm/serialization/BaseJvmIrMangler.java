package org.jetbrains.kotlin.ir.backend.jvm.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinExportChecker;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinMangleComputer;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleMode;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrBasedKotlinManglerImpl;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrExportCheckerVisitor;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrMangleComputer;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/jvm/serialization/BaseJvmIrMangler;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/ir/IrBasedKotlinManglerImpl;", "<init>", "()V", "getExportChecker", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinExportChecker;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "compatibleMode", "", "getMangleComputer", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinMangleComputer;", "mode", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleMode;", "JvmIrExportChecker", "JvmIrManglerComputer", "org.jetbrains.kotlin:ir.serialization.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class BaseJvmIrMangler extends IrBasedKotlinManglerImpl {
    public KotlinExportChecker<IrDeclaration> getExportChecker(boolean compatibleMode) {
        return new JvmIrExportChecker(compatibleMode);
    }

    public KotlinMangleComputer<IrDeclaration> getMangleComputer(MangleMode mode, boolean compatibleMode) {
        mode.getClass();
        return new JvmIrManglerComputer(new StringBuilder(256), mode, compatibleMode);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/jvm/serialization/BaseJvmIrMangler$JvmIrExportChecker;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/ir/IrExportCheckerVisitor;", "compatibleMode", "", "<init>", "(Z)V", "isPlatformSpecificExported", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "org.jetbrains.kotlin:ir.serialization.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class JvmIrExportChecker extends IrExportCheckerVisitor {
        public JvmIrExportChecker(boolean z) {
            super(z);
        }

        public boolean isPlatformSpecificExported(IrDeclaration irDeclaration) {
            irDeclaration.getClass();
            return false;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B#\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u001c\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\u0014\u001a\u00060\u0003j\u0002`\u0004H\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/jvm/serialization/BaseJvmIrMangler$JvmIrManglerComputer;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/ir/IrMangleComputer;", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "mode", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleMode;", "compatibleMode", "", "<init>", "(Ljava/lang/StringBuilder;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/MangleMode;Z)V", "copy", "newMode", "addReturnTypeSpecialCase", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "mangleTypePlatformSpecific", "", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "tBuilder", "org.jetbrains.kotlin:ir.serialization.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static class JvmIrManglerComputer extends IrMangleComputer {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public JvmIrManglerComputer(StringBuilder sb, MangleMode mangleMode, boolean z) {
            super(sb, mangleMode, z, false, 8, (DefaultConstructorMarker) null);
            sb.getClass();
            mangleMode.getClass();
        }

        public IrMangleComputer copy(MangleMode newMode) {
            newMode.getClass();
            return new JvmIrManglerComputer(getBuilder(), newMode, getCompatibleMode());
        }

        public void mangleTypePlatformSpecific(IrType type, StringBuilder tBuilder) {
            type.getClass();
            tBuilder.getClass();
            FqName fqName = JvmAnnotationNames.ENHANCED_NULLABILITY_ANNOTATION;
            fqName.getClass();
            if (IrUtilsKt.hasAnnotation(type, fqName)) {
                tBuilder.append("{EnhancedNullability}");
            }
        }

        public boolean addReturnTypeSpecialCase(IrFunction function) {
            function.getClass();
            return true;
        }
    }
}
