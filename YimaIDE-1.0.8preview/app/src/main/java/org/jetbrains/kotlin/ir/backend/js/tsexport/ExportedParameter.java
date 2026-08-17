package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedParameter;", "", "name", "", "type", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "hasDefaultValue", "", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;Z)V", "getName", "()Ljava/lang/String;", "getType", "()Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedType;", "getHasDefaultValue", "()Z", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ExportedParameter {
    private final boolean hasDefaultValue;
    private final String name;
    private final ExportedType type;

    public ExportedParameter(String str, ExportedType exportedType, boolean z) {
        exportedType.getClass();
        this.name = str;
        this.type = exportedType;
        this.hasDefaultValue = z;
    }

    public final boolean getHasDefaultValue() {
        return this.hasDefaultValue;
    }

    public final String getName() {
        return this.name;
    }

    public final ExportedType getType() {
        return this.type;
    }

    public /* synthetic */ ExportedParameter(String str, ExportedType exportedType, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, exportedType, (i & 4) != 0 ? false : z);
    }
}
