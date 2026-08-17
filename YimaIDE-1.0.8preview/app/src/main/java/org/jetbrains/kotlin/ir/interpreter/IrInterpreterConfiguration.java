package org.jetbrains.kotlin.ir.interpreter;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.konan.library.NativeLibraryConstantsKt;
import org.jetbrains.kotlin.konan.util.DependencyDownloader;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003JG\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/ir/interpreter/IrInterpreterConfiguration;", "", NativeLibraryConstantsKt.KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR, "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "maxStack", "", "maxCommands", "createNonCompileTimeObjects", "", "printOnlyExceptionMessage", "collapseStackTraceFromJDK", "<init>", "(Lorg/jetbrains/kotlin/platform/TargetPlatform;IIZZZ)V", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getMaxStack", "()I", "getMaxCommands", "getCreateNonCompileTimeObjects", "()Z", "getPrintOnlyExceptionMessage", "getCollapseStackTraceFromJDK", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:ir.interpreter"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class IrInterpreterConfiguration {
    private final boolean collapseStackTraceFromJDK;
    private final boolean createNonCompileTimeObjects;
    private final int maxCommands;
    private final int maxStack;
    private final TargetPlatform platform;
    private final boolean printOnlyExceptionMessage;

    public /* synthetic */ IrInterpreterConfiguration(TargetPlatform targetPlatform, int i, int i2, boolean z, boolean z2, boolean z3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : targetPlatform, (i3 & 2) != 0 ? DependencyDownloader.DEFAULT_CONNECT_TIMEOUT_MS : i, (i3 & 4) != 0 ? 1000000 : i2, (i3 & 8) != 0 ? false : z, (i3 & 16) != 0 ? false : z2, (i3 & 32) != 0 ? true : z3);
    }

    public static /* synthetic */ IrInterpreterConfiguration copy$default(IrInterpreterConfiguration irInterpreterConfiguration, TargetPlatform targetPlatform, int i, int i2, boolean z, boolean z2, boolean z3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            targetPlatform = irInterpreterConfiguration.platform;
        }
        if ((i3 & 2) != 0) {
            i = irInterpreterConfiguration.maxStack;
        }
        if ((i3 & 4) != 0) {
            i2 = irInterpreterConfiguration.maxCommands;
        }
        if ((i3 & 8) != 0) {
            z = irInterpreterConfiguration.createNonCompileTimeObjects;
        }
        if ((i3 & 16) != 0) {
            z2 = irInterpreterConfiguration.printOnlyExceptionMessage;
        }
        if ((i3 & 32) != 0) {
            z3 = irInterpreterConfiguration.collapseStackTraceFromJDK;
        }
        boolean z4 = z2;
        boolean z5 = z3;
        return irInterpreterConfiguration.copy(targetPlatform, i, i2, z, z4, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TargetPlatform getPlatform() {
        return this.platform;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getMaxStack() {
        return this.maxStack;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getMaxCommands() {
        return this.maxCommands;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getCreateNonCompileTimeObjects() {
        return this.createNonCompileTimeObjects;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getPrintOnlyExceptionMessage() {
        return this.printOnlyExceptionMessage;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getCollapseStackTraceFromJDK() {
        return this.collapseStackTraceFromJDK;
    }

    public final IrInterpreterConfiguration copy(TargetPlatform platform, int maxStack, int maxCommands, boolean createNonCompileTimeObjects, boolean printOnlyExceptionMessage, boolean collapseStackTraceFromJDK) {
        return new IrInterpreterConfiguration(platform, maxStack, maxCommands, createNonCompileTimeObjects, printOnlyExceptionMessage, collapseStackTraceFromJDK);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IrInterpreterConfiguration)) {
            return false;
        }
        IrInterpreterConfiguration irInterpreterConfiguration = (IrInterpreterConfiguration) other;
        return Intrinsics.areEqual(this.platform, irInterpreterConfiguration.platform) && this.maxStack == irInterpreterConfiguration.maxStack && this.maxCommands == irInterpreterConfiguration.maxCommands && this.createNonCompileTimeObjects == irInterpreterConfiguration.createNonCompileTimeObjects && this.printOnlyExceptionMessage == irInterpreterConfiguration.printOnlyExceptionMessage && this.collapseStackTraceFromJDK == irInterpreterConfiguration.collapseStackTraceFromJDK;
    }

    public final boolean getCollapseStackTraceFromJDK() {
        return this.collapseStackTraceFromJDK;
    }

    public final boolean getCreateNonCompileTimeObjects() {
        return this.createNonCompileTimeObjects;
    }

    public final int getMaxCommands() {
        return this.maxCommands;
    }

    public final int getMaxStack() {
        return this.maxStack;
    }

    public final TargetPlatform getPlatform() {
        return this.platform;
    }

    public final boolean getPrintOnlyExceptionMessage() {
        return this.printOnlyExceptionMessage;
    }

    public int hashCode() {
        TargetPlatform targetPlatform = this.platform;
        return ((((((((((targetPlatform == null ? 0 : targetPlatform.hashCode()) * 31) + Integer.hashCode(this.maxStack)) * 31) + Integer.hashCode(this.maxCommands)) * 31) + Boolean.hashCode(this.createNonCompileTimeObjects)) * 31) + Boolean.hashCode(this.printOnlyExceptionMessage)) * 31) + Boolean.hashCode(this.collapseStackTraceFromJDK);
    }

    public String toString() {
        return "IrInterpreterConfiguration(platform=" + this.platform + ", maxStack=" + this.maxStack + ", maxCommands=" + this.maxCommands + ", createNonCompileTimeObjects=" + this.createNonCompileTimeObjects + ", printOnlyExceptionMessage=" + this.printOnlyExceptionMessage + ", collapseStackTraceFromJDK=" + this.collapseStackTraceFromJDK + ')';
    }

    public IrInterpreterConfiguration(TargetPlatform targetPlatform, int i, int i2, boolean z, boolean z2, boolean z3) {
        this.platform = targetPlatform;
        this.maxStack = i;
        this.maxCommands = i2;
        this.createNonCompileTimeObjects = z;
        this.printOnlyExceptionMessage = z2;
        this.collapseStackTraceFromJDK = z3;
    }

    public IrInterpreterConfiguration() {
        this(null, 0, 0, false, false, false, 63, null);
    }
}
