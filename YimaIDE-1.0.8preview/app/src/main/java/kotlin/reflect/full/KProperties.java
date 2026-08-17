package kotlin.reflect.full;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.jvm.internal.DescriptorKProperty;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002H\u0007b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u001a=\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0006*\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u0002H\u0006H\u0007b\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"getExtensionDelegate", "", "Lkotlin/reflect/KProperty1;", "Lkotlin/SinceKotlin;", "version", "1.1", "D", "Lkotlin/reflect/KProperty2;", "receiver", "(Lkotlin/reflect/KProperty2;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class KProperties {
    public static final Object getExtensionDelegate(KProperty1<?, ?> kProperty1) {
        kProperty1.getClass();
        return kProperty1.getDelegate(DescriptorKProperty.Companion.getEXTENSION_PROPERTY_DELEGATE());
    }

    public static final <D> Object getExtensionDelegate(KProperty2<D, ?, ?> kProperty2, D d) {
        kProperty2.getClass();
        return kProperty2.getDelegate(d, DescriptorKProperty.Companion.getEXTENSION_PROPERTY_DELEGATE());
    }
}
