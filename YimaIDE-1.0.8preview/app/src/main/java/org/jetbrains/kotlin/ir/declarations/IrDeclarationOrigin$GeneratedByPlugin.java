package org.jetbrains.kotlin.ir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.GeneratedDeclarationKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\bJ\n\u0010\u000f\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\n¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin$GeneratedByPlugin;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "pluginId", "", "pluginKey", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;)V", "(Lorg/jetbrains/kotlin/GeneratedDeclarationKey;)V", "getPluginId", "()Ljava/lang/String;", "getPluginKey", "()Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "name", "getName", "toString", "equals", "", "other", "", "hashCode", "", "Companion", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IrDeclarationOrigin$GeneratedByPlugin implements IrDeclarationOrigin {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String pluginId;
    private final GeneratedDeclarationKey pluginKey;

    /* JADX WARN: Illegal instructions before constructor call */
    public IrDeclarationOrigin$GeneratedByPlugin(GeneratedDeclarationKey generatedDeclarationKey) {
        generatedDeclarationKey.getClass();
        String qualifiedName = Reflection.getOrCreateKotlinClass(generatedDeclarationKey.getClass()).getQualifiedName();
        qualifiedName.getClass();
        this(qualifiedName, generatedDeclarationKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof IrDeclarationOrigin$GeneratedByPlugin) {
            return Intrinsics.areEqual(this.pluginKey, ((IrDeclarationOrigin$GeneratedByPlugin) other).pluginKey);
        }
        return false;
    }

    public String getName() {
        return "GENERATED[" + this.pluginId + ']';
    }

    public final String getPluginId() {
        return this.pluginId;
    }

    public final GeneratedDeclarationKey getPluginKey() {
        return this.pluginKey;
    }

    public int hashCode() {
        GeneratedDeclarationKey generatedDeclarationKey = this.pluginKey;
        if (generatedDeclarationKey != null) {
            return generatedDeclarationKey.hashCode();
        }
        return 0;
    }

    public String toString() {
        return getName();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin$GeneratedByPlugin$Companion;", "", "<init>", "()V", "fromSerializedString", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin$GeneratedByPlugin;", "name", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IrDeclarationOrigin$GeneratedByPlugin fromSerializedString(String name) {
            name.getClass();
            String strRemoveSurrounding = StringsKt.removeSurrounding(name, "GENERATED[", "]");
            GeneratedDeclarationKey generatedDeclarationKey = null;
            if (Intrinsics.areEqual(strRemoveSurrounding, name)) {
                strRemoveSurrounding = null;
            }
            if (strRemoveSurrounding == null) {
                return null;
            }
            return new IrDeclarationOrigin$GeneratedByPlugin(strRemoveSurrounding, generatedDeclarationKey, generatedDeclarationKey);
        }

        private Companion() {
        }
    }

    private IrDeclarationOrigin$GeneratedByPlugin(String str, GeneratedDeclarationKey generatedDeclarationKey) {
        this.pluginId = str;
        this.pluginKey = generatedDeclarationKey;
    }

    public /* synthetic */ IrDeclarationOrigin$GeneratedByPlugin(String str, GeneratedDeclarationKey generatedDeclarationKey, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, generatedDeclarationKey);
    }
}
