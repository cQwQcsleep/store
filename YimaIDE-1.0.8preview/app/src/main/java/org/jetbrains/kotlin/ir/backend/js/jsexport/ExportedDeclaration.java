package org.jetbrains.kotlin.ir.backend.js.jsexport;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0004\t\n\u000b\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedDeclaration;", "", "<init>", "()V", "attributes", "", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedAttribute;", "getAttributes", "()Ljava/util/Set;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedClass;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedFunction;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedNamespace;", "Lorg/jetbrains/kotlin/ir/backend/js/jsexport/ExportedProperty;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExportedDeclaration {
    private final Set<ExportedAttribute> attributes;

    private ExportedDeclaration() {
        this.attributes = new LinkedHashSet();
    }

    public final Set<ExportedAttribute> getAttributes() {
        return this.attributes;
    }

    public /* synthetic */ ExportedDeclaration(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
