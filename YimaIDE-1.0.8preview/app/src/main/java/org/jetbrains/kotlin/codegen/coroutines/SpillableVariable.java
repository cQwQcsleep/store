package org.jetbrains.kotlin.codegen.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0000\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u000b\u001a\u00020\f¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u000b\u0010\u001bR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001b¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/SpillableVariable;", Argument.Delimiters.none, "value", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "normalizedType", "fieldName", Argument.Delimiters.none, "slot", Argument.Delimiters.none, "isVisible", Argument.Delimiters.none, "isAlive", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/Type;Ljava/lang/String;IZZ)V", "getValue", "()Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getNormalizedType", "getFieldName", "()Ljava/lang/String;", "getSlot", "()I", "isVisible$annotations", "()V", "()Z", "isNull", "shouldSpillNull", "getShouldSpillNull", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SpillableVariable {
    private final String fieldName;
    private final boolean isAlive;
    private final boolean isVisible;
    private final Type normalizedType;
    private final int slot;
    private final Type type;
    private final BasicValue value;

    public SpillableVariable(BasicValue basicValue, Type type, Type type2, String str, int i, boolean z, boolean z2) {
        basicValue.getClass();
        type.getClass();
        type2.getClass();
        this.value = basicValue;
        this.type = type;
        this.normalizedType = type2;
        this.fieldName = str;
        this.slot = i;
        this.isVisible = z;
        this.isAlive = z2;
        if (isNull() == (str == null)) {
            return;
        }
        rza.a("Value is ", basicValue, ", fieldName is ", str);
        throw null;
    }

    public static /* synthetic */ void isVisible$annotations() {
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public final Type getNormalizedType() {
        return this.normalizedType;
    }

    public final boolean getShouldSpillNull() {
        return Intrinsics.areEqual(this.normalizedType, AsmTypes.OBJECT_TYPE) && !this.isAlive;
    }

    public final int getSlot() {
        return this.slot;
    }

    public final Type getType() {
        return this.type;
    }

    public final BasicValue getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: isAlive, reason: from getter */
    public final boolean getIsAlive() {
        return this.isAlive;
    }

    public final boolean isNull() {
        return this.value == StrictBasicValue.NULL_VALUE;
    }

    /* JADX INFO: renamed from: isVisible, reason: from getter */
    public final boolean getIsVisible() {
        return this.isVisible;
    }
}
