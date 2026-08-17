package kotlin.properties;

import defpackage.zia;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00010\u0003B\t\bF¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\b\u001a\u00028\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u00022\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0096\u0082\u0004¢\u0006\u0002\u0010\fJ-\u0010\r\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u00022\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u0006\u0010\u0006\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000fJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0013\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0012"}, d2 = {"Lkotlin/properties/NotNullVar;", "T", "", "Lkotlin/properties/ReadWriteProperty;", "<init>", "()V", "value", "Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
final class NotNullVar<T> implements ReadWriteProperty<Object, T> {
    private T value;

    public T getValue(Object thisRef, KProperty<?> property) {
        property.getClass();
        T t = this.value;
        if (t != null) {
            return t;
        }
        zia.a("Property ", property.getName(), " should be initialized before get.");
        return null;
    }

    public void setValue(Object thisRef, KProperty<?> property, T value) {
        property.getClass();
        value.getClass();
        this.value = value;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("NotNullProperty(");
        if (this.value != null) {
            str = "value=" + this.value;
        } else {
            str = "value not initialized yet";
        }
        sb.append(str);
        sb.append(')');
        return sb.toString();
    }
}
