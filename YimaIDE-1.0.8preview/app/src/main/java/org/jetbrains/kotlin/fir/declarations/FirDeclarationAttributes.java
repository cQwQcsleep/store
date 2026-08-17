package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.ArrayMap;
import org.jetbrains.kotlin.util.AttributeArrayOwner;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0005B\u0017\b\u0012\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\u0004\u0010\bJ(\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0080\u0002¢\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001a\u00020\u0000R \u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\n8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "Lorg/jetbrains/kotlin/util/AttributeArrayOwner;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataKey;", Argument.Delimiters.none, "<init>", "()V", "arrayMap", "Lorg/jetbrains/kotlin/util/ArrayMap;", "(Lorg/jetbrains/kotlin/util/ArrayMap;)V", "typeRegistry", "Lorg/jetbrains/kotlin/util/TypeRegistry;", "getTypeRegistry", "()Lorg/jetbrains/kotlin/util/TypeRegistry;", "set", Argument.Delimiters.none, "key", "Lkotlin/reflect/KClass;", "value", "set$org_jetbrains_kotlin_tree", "copy", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationAttributes extends AttributeArrayOwner<FirDeclarationDataKey, Object> {
    public FirDeclarationAttributes() {
    }

    public final FirDeclarationAttributes copy() {
        return new FirDeclarationAttributes(getArrayMap().copy());
    }

    public TypeRegistry<FirDeclarationDataKey, Object> getTypeRegistry() {
        return FirDeclarationDataRegistry.INSTANCE;
    }

    public final void set$org_jetbrains_kotlin_tree(KClass<? extends FirDeclarationDataKey> key, Object value) {
        key.getClass();
        if (value == null) {
            removeComponent(key);
        } else {
            registerComponent(key, value);
        }
    }

    private FirDeclarationAttributes(ArrayMap<Object> arrayMap) {
        super(arrayMap);
    }
}
