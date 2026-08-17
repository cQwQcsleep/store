package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.signature.BothSignatureWriter;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003BG\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0013H\u0086\u0002J\u0006\u0010\u0018\u001a\u00020\nJ1\u0010\u0019\u001a\u00020\u001a2\u001e\u0010\u001b\u001a\u001a\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014\u0012\u0004\u0012\u00020\u001a0\fH\u0080\bø\u0001\u0000¢\u0006\u0002\b\u001cR6\u0010\u0011\u001a*\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00140\u0012j\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMappings;", "KT", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", Argument.Delimiters.none, "typeSystem", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "allReified", Argument.Delimiters.none, "mapType", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/codegen/signature/BothSignatureWriter;", "Lorg/jetbrains/org/objectweb/asm/Type;", "<init>", "(Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;Ljava/util/Map;ZLkotlin/jvm/functions/Function2;)V", "mappingsByName", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMapping;", "Lkotlin/collections/HashMap;", "get", ModuleXmlParser.NAME, "hasReifiedParameters", "forEach", Argument.Delimiters.none, "block", "forEach$org_jetbrains_kotlin_backend", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeParameterMappings<KT extends KotlinTypeMarker> {
    private final HashMap<String, TypeParameterMapping<KT>> mappingsByName;

    public TypeParameterMappings(TypeSystemCommonBackendContext typeSystemCommonBackendContext, Map<? extends TypeParameterMarker, ? extends KT> map, boolean z, Function2<? super KT, ? super BothSignatureWriter, Type> function2) {
        typeSystemCommonBackendContext.getClass();
        map.getClass();
        function2.getClass();
        this.mappingsByName = new HashMap<>();
        for (Map.Entry<? extends TypeParameterMarker, ? extends KT> entry : map.entrySet()) {
            TypeParameterMarker key = entry.getKey();
            KT value = entry.getValue();
            String identifier = typeSystemCommonBackendContext.getName(key).getIdentifier();
            identifier.getClass();
            BothSignatureWriter bothSignatureWriter = new BothSignatureWriter(BothSignatureWriter.Mode.TYPE);
            HashMap<String, TypeParameterMapping<KT>> map2 = this.mappingsByName;
            Type type = (Type) function2.invoke(value, bothSignatureWriter);
            String string = bothSignatureWriter.toString();
            string.getClass();
            boolean z2 = z || typeSystemCommonBackendContext.isReified(key);
            Pair<TypeParameterMarker, ReificationArgument> pairExtractReificationArgument = CodegenUtilKt.extractReificationArgument(typeSystemCommonBackendContext, value);
            map2.put(identifier, new TypeParameterMapping<>(value, type, string, z2, pairExtractReificationArgument != null ? (ReificationArgument) pairExtractReificationArgument.getSecond() : null, CodegenUtilKt.extractUsedReifiedParameters(typeSystemCommonBackendContext, value)));
        }
    }

    public final void forEach$org_jetbrains_kotlin_backend(Function2<? super String, ? super TypeParameterMapping<KT>, Unit> block) {
        block.getClass();
        Set<Map.Entry> setEntrySet = this.mappingsByName.entrySet();
        setEntrySet.getClass();
        for (Map.Entry entry : setEntrySet) {
            entry.getClass();
            Object key = entry.getKey();
            key.getClass();
            Object value = entry.getValue();
            value.getClass();
            block.invoke((String) key, (TypeParameterMapping) value);
        }
    }

    public final TypeParameterMapping<KT> get(String name) {
        name.getClass();
        return this.mappingsByName.get(name);
    }

    public final boolean hasReifiedParameters() {
        Collection<TypeParameterMapping<KT>> collectionValues = this.mappingsByName.values();
        collectionValues.getClass();
        Collection<TypeParameterMapping<KT>> collection = collectionValues;
        if (collection.isEmpty()) {
            return false;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (((TypeParameterMapping) it.next()).getIsReified()) {
                return true;
            }
        }
        return false;
    }
}
