package org.jetbrains.kotlin.ir.backend.js.tsexport;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b\u0082\u0001\t\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedDeclaration;", "", "<init>", "()V", "attributes", "", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute;", "getAttributes", "()Ljava/util/Set;", "isProtected", "", "()Z", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ErrorDeclaration;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedClass;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedConstructSignature;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedConstructor;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedField;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedFunction;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedNamespace;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedPropertyGetter;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedPropertySetter;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExportedDeclaration {
    private final Set<ExportedAttribute> attributes;

    private ExportedDeclaration() {
        this.attributes = new LinkedHashSet();
    }

    public final Set<ExportedAttribute> getAttributes() {
        return this.attributes;
    }

    public boolean isProtected() {
        return false;
    }

    public /* synthetic */ ExportedDeclaration(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
