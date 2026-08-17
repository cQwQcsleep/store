package org.jetbrains.kotlin.serialization.js;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.js.config.ModuleKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\r\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b\u0012\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\t\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/serialization/js/JsModuleDescriptor;", "T", "", "name", "", "kind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "imported", "", "data", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/js/config/ModuleKind;Ljava/util/List;Ljava/lang/Object;)V", "getName", "()Ljava/lang/String;", "getKind", "()Lorg/jetbrains/kotlin/js/config/ModuleKind;", "getImported", "()Ljava/util/List;", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "org.jetbrains.kotlin:js.serializer"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsModuleDescriptor<T> {
    private final T data;
    private final List<String> imported;
    private final ModuleKind kind;
    private final String name;

    public JsModuleDescriptor(String str, ModuleKind moduleKind, List<String> list, T t) {
        str.getClass();
        moduleKind.getClass();
        list.getClass();
        this.name = str;
        this.kind = moduleKind;
        this.imported = list;
        this.data = t;
    }

    public final T getData() {
        return this.data;
    }

    public final List<String> getImported() {
        return this.imported;
    }

    public final ModuleKind getKind() {
        return this.kind;
    }

    public final String getName() {
        return this.name;
    }
}
