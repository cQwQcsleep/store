package org.jetbrains.kotlin.codegen.inline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB5\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0000\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004J\u0016\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0004J\u001a\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00042\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u0010J\u0014\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00102\u0006\u0010\u001b\u001a\u00020\u0004R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\rj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\u000f\u001a*\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00100\rj\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0010`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", Argument.Delimiters.none, "typeMapping", Argument.Delimiters.none, Argument.Delimiters.none, "parent", "isRootInlineLambda", Argument.Delimiters.none, "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;Z)V", "getParent", "()Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", "additionalMappings", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "typeParametersMapping", "Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMapping;", "addMapping", Argument.Delimiters.none, ModuleXmlParser.TYPE, "newType", "hasNoAdditionalMapping", "map", "addAdditionalMappings", "oldName", "newName", "registerTypeParameter", ModuleXmlParser.NAME, "mapping", "mapTypeParameter", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeRemapper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final HashMap<String, String> additionalMappings;
    private final boolean isRootInlineLambda;
    private final TypeRemapper parent;
    private final Map<String, String> typeMapping;
    private final HashMap<String, TypeParameterMapping<?>> typeParametersMapping;

    private TypeRemapper(Map<String, String> map, TypeRemapper typeRemapper, boolean z) {
        this.typeMapping = map;
        this.parent = typeRemapper;
        this.isRootInlineLambda = z;
        this.additionalMappings = new HashMap<>();
        this.typeParametersMapping = new HashMap<>();
    }

    @JvmStatic
    public static final TypeRemapper createFrom(Map<String, String> map) {
        return INSTANCE.createFrom(map);
    }

    @JvmStatic
    public static final TypeRemapper createRoot(TypeParameterMappings<?> typeParameterMappings) {
        return INSTANCE.createRoot(typeParameterMappings);
    }

    public final void addAdditionalMappings(String oldName, String newName) {
        oldName.getClass();
        newName.getClass();
        this.additionalMappings.put(oldName, newName);
    }

    public final void addMapping(String type, String newType) {
        type.getClass();
        newType.getClass();
        this.typeMapping.put(type, newType);
    }

    public final TypeRemapper getParent() {
        return this.parent;
    }

    public final boolean hasNoAdditionalMapping(String type) {
        type.getClass();
        return this.typeMapping.containsKey(type);
    }

    public final String map(String type) {
        type.getClass();
        String str = this.typeMapping.get(type);
        if (str != null) {
            return str;
        }
        String str2 = this.additionalMappings.get(type);
        return str2 == null ? type : str2;
    }

    public final TypeParameterMapping<?> mapTypeParameter(String name) {
        TypeRemapper typeRemapper;
        name.getClass();
        if (this.typeParametersMapping.containsKey(name)) {
            return this.typeParametersMapping.get(name);
        }
        if (this.isRootInlineLambda || (typeRemapper = this.parent) == null) {
            return null;
        }
        return typeRemapper.mapTypeParameter(name);
    }

    public final void registerTypeParameter(String name) {
        name.getClass();
        this.typeParametersMapping.get(name);
        this.typeParametersMapping.put(name, null);
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0007b\u0002\b\bJ\"\u0010\t\u001a\u00020\u00052\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0007b\u0002\b\bJ8\u0010\t\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007b\u0002\b\bb\u0002\b\u0011J4\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u0006\u0010\u0013\u001a\u00020\u00052\u0014\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000eH\u0002¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper$Companion;", Argument.Delimiters.none, "<init>", "()V", "createRoot", "Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", "formalTypeParameters", "Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMappings;", "Lkotlin/jvm/JvmStatic;", "createFrom", "mappings", Argument.Delimiters.none, Argument.Delimiters.none, "parentRemapper", Argument.Delimiters.none, "isRootInlineLambda", Argument.Delimiters.none, "Lkotlin/jvm/JvmOverloads;", "createNewAndMerge", "remapper", "additionalTypeMappings", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ TypeRemapper createFrom$default(Companion companion, TypeRemapper typeRemapper, Map map, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.createFrom(typeRemapper, map, z);
        }

        private final Map<String, String> createNewAndMerge(TypeRemapper remapper, Map<String, String> additionalTypeMappings) {
            HashMap map = new HashMap(remapper.typeMapping);
            map.putAll(additionalTypeMappings);
            return map;
        }

        @JvmStatic
        public final TypeRemapper createFrom(TypeRemapper typeRemapper, Map<String, String> map) {
            typeRemapper.getClass();
            map.getClass();
            return createFrom$default(this, typeRemapper, map, false, 4, null);
        }

        @JvmStatic
        public final TypeRemapper createRoot(TypeParameterMappings<?> formalTypeParameters) {
            formalTypeParameters.getClass();
            TypeRemapper typeRemapper = new TypeRemapper(new HashMap(), null, false, 6, null);
            Set<Map.Entry> setEntrySet = ((TypeParameterMappings) formalTypeParameters).mappingsByName.entrySet();
            setEntrySet.getClass();
            for (Map.Entry entry : setEntrySet) {
                entry.getClass();
                Object key = entry.getKey();
                key.getClass();
                Object value = entry.getValue();
                value.getClass();
                typeRemapper.registerTypeParameter((String) key, (TypeParameterMapping) value);
            }
            return typeRemapper;
        }

        private Companion() {
        }

        @JvmStatic
        public final TypeRemapper createFrom(Map<String, String> mappings) {
            mappings.getClass();
            return new TypeRemapper(mappings, null, false, 6, null);
        }

        @JvmStatic
        public final TypeRemapper createFrom(TypeRemapper parentRemapper, Map<String, String> mappings, boolean isRootInlineLambda) {
            parentRemapper.getClass();
            mappings.getClass();
            return new TypeRemapper(createNewAndMerge(parentRemapper, mappings), parentRemapper, isRootInlineLambda, null);
        }
    }

    @JvmStatic
    public static final TypeRemapper createFrom(TypeRemapper typeRemapper, Map<String, String> map) {
        return INSTANCE.createFrom(typeRemapper, map);
    }

    @JvmStatic
    public static final TypeRemapper createFrom(TypeRemapper typeRemapper, Map<String, String> map, boolean z) {
        return INSTANCE.createFrom(typeRemapper, map, z);
    }

    public final void registerTypeParameter(String name, TypeParameterMapping<?> mapping) {
        name.getClass();
        mapping.getClass();
        this.typeParametersMapping.put(name, mapping);
    }

    public /* synthetic */ TypeRemapper(Map map, TypeRemapper typeRemapper, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, typeRemapper, z);
    }

    public /* synthetic */ TypeRemapper(Map map, TypeRemapper typeRemapper, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, (i & 2) != 0 ? null : typeRemapper, (i & 4) != 0 ? false : z);
    }
}
