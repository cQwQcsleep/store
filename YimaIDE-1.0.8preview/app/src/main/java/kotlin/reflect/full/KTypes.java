package kotlin.reflect.full;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.SystemPropertiesKt;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypePreparator;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.TypeCheckerState;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.types.AbstractKType;
import kotlin.reflect.jvm.internal.types.DescriptorKType;
import kotlin.reflect.jvm.internal.types.ReflectTypeSystemContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\u0007\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0007b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u001a\"\u0010\t\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0007b\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¨\u0006\n"}, d2 = {"withNullability", "Lkotlin/reflect/KType;", "nullable", "", "Lkotlin/SinceKotlin;", "version", "1.1", "isSubtypeOf", "other", "isSupertypeOf", "kotlin-reflection"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class KTypes {
    public static final boolean isSubtypeOf(KType kType, KType kType2) {
        kType.getClass();
        kType2.getClass();
        if (SystemPropertiesKt.getUseK1Implementation()) {
            return TypeUtilsKt.isSubtypeOf(((DescriptorKType) kType).getType(), ((DescriptorKType) kType2).getType());
        }
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, new TypeCheckerState(false, false, false, false, ReflectTypeSystemContext.INSTANCE, AbstractTypePreparator.Default.INSTANCE, AbstractTypeRefiner.Default.INSTANCE), (AbstractKType) kType, (AbstractKType) kType2, false, 8, (Object) null);
    }

    public static final boolean isSupertypeOf(KType kType, KType kType2) {
        kType.getClass();
        kType2.getClass();
        return isSubtypeOf(kType2, kType);
    }

    public static final KType withNullability(KType kType, boolean z) {
        kType.getClass();
        return ((AbstractKType) kType).makeNullableAsSpecified(z);
    }
}
