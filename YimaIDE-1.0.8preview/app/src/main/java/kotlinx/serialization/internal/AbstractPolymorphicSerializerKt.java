package kotlinx.serialization.internal;

import androidx.compose.compiler.plugins.kotlin.analysis.StabilityExternalClassNameMatchingKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import kotlinx.serialization.SerializationException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0001\u001a \u0010\u0000\u001a\u00020\u00012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0001¨\u0006\u0007"}, d2 = {"throwSubtypeNotRegistered", HttpUrl.FRAGMENT_ENCODE_SET, "subClassName", HttpUrl.FRAGMENT_ENCODE_SET, "baseClass", "Lkotlin/reflect/KClass;", "subClass", "kotlinx-serialization-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AbstractPolymorphicSerializerKt {
    public static final Void throwSubtypeNotRegistered(String str, KClass<?> kClass) {
        String str2;
        kClass.getClass();
        String str3 = "in the polymorphic scope of '" + kClass.getSimpleName() + '\'';
        if (str == null) {
            str2 = "Class discriminator was missing and no default serializers were registered " + str3 + StabilityExternalClassNameMatchingKt.STABILITY_PACKAGE_SEPARATOR;
        } else {
            str2 = "Serializer for subclass '" + str + "' is not found " + str3 + ".\nCheck if class with serial name '" + str + "' exists and serializer is registered in a corresponding SerializersModule.\nTo be registered automatically, class '" + str + "' has to be '@Serializable', and the base class '" + kClass.getSimpleName() + "' has to be sealed and '@Serializable'.";
        }
        throw new SerializationException(str2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    public static final Void throwSubtypeNotRegistered(KClass<?> kClass, KClass<?> kClass2) throws KotlinNothingValueException {
        kClass.getClass();
        kClass2.getClass();
        String simpleName = kClass.getSimpleName();
        if (simpleName == null) {
            simpleName = String.valueOf(kClass);
        }
        throwSubtypeNotRegistered(simpleName, kClass2);
        throw new KotlinNothingValueException();
    }
}
