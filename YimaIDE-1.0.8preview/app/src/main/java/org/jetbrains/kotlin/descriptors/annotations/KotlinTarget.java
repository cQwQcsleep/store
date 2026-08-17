package org.jetbrains.kotlin.descriptors.annotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b6\b\u0086\u0081\u0002\u0018\u0000 :2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001:B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", Argument.Delimiters.none, "description", Argument.Delimiters.none, "isDefault", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getDescription", "()Ljava/lang/String;", "()Z", "CLASS", "ANNOTATION_CLASS", "TYPE_PARAMETER", "PROPERTY", "FIELD", "LOCAL_VARIABLE", "VALUE_PARAMETER", "CONSTRUCTOR", "FUNCTION", "PROPERTY_GETTER", "PROPERTY_SETTER", "TYPE", "EXPRESSION", "FILE", "TYPEALIAS", "TYPE_PROJECTION", "STAR_PROJECTION", "PROPERTY_PARAMETER", "CLASS_ONLY", "OBJECT", "STANDALONE_OBJECT", "COMPANION_OBJECT", "INTERFACE", "ENUM_CLASS", "ENUM_ENTRY", "LOCAL_CLASS", "LOCAL_FUNCTION", "MEMBER_FUNCTION", "COMPANION_MEMBER_FUNCTION", "TOP_LEVEL_FUNCTION", "COMPANION_EXTENSION_FUNCTION", "MEMBER_PROPERTY", "COMPANION_MEMBER_PROPERTY", "MEMBER_PROPERTY_WITH_BACKING_FIELD", "MEMBER_PROPERTY_WITH_DELEGATE", "MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE", "TOP_LEVEL_PROPERTY", "COMPANION_EXTENSION_PROPERTY", "TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD", "TOP_LEVEL_PROPERTY_WITH_DELEGATE", "TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE", "BACKING_FIELD", "INITIALIZER", "DESTRUCTURING_DECLARATION", "LAMBDA_EXPRESSION", "ANONYMOUS_FUNCTION", "OBJECT_LITERAL", "Companion", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum KotlinTarget {
    CLASS("class", false, 2, null),
    ANNOTATION_CLASS("annotation class", false, 2, null),
    TYPE_PARAMETER("type parameter", false),
    PROPERTY("property", false, 2, null),
    FIELD("field", false, 2, null),
    LOCAL_VARIABLE("local variable", false, 2, null),
    VALUE_PARAMETER("value parameter", false, 2, null),
    CONSTRUCTOR("constructor", false, 2, null),
    FUNCTION("function", false, 2, null),
    PROPERTY_GETTER("getter", false, 2, null),
    PROPERTY_SETTER("setter", false, 2, null),
    TYPE("type usage", false),
    EXPRESSION("expression", false),
    FILE("file", false),
    TYPEALIAS("typealias", false),
    TYPE_PROJECTION("type projection", false),
    STAR_PROJECTION("star projection", false),
    PROPERTY_PARAMETER("property constructor parameter", false),
    CLASS_ONLY("class", false),
    OBJECT("object", false),
    STANDALONE_OBJECT("standalone object", false),
    COMPANION_OBJECT("companion object", false),
    INTERFACE("interface", false),
    ENUM_CLASS("enum class", false),
    ENUM_ENTRY("enum entry", false),
    LOCAL_CLASS("local class", false),
    LOCAL_FUNCTION("local function", false),
    MEMBER_FUNCTION("member function", false),
    COMPANION_MEMBER_FUNCTION("companion member function", false),
    TOP_LEVEL_FUNCTION("top level function", false),
    COMPANION_EXTENSION_FUNCTION("companion extension function", false),
    MEMBER_PROPERTY("member property", false),
    COMPANION_MEMBER_PROPERTY("companion member property", false),
    MEMBER_PROPERTY_WITH_BACKING_FIELD("member property with backing field", false),
    MEMBER_PROPERTY_WITH_DELEGATE("member property with delegate", false),
    MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("member property without backing field or delegate", false),
    TOP_LEVEL_PROPERTY("top level property", false),
    COMPANION_EXTENSION_PROPERTY("companion extension property", false),
    TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD("top level property with backing field", false),
    TOP_LEVEL_PROPERTY_WITH_DELEGATE("top level property with delegate", false),
    TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("top level property without backing field or delegate", false),
    BACKING_FIELD("backing field", false, 2, null),
    INITIALIZER("initializer", false),
    DESTRUCTURING_DECLARATION("destructuring declaration", false),
    LAMBDA_EXPRESSION("lambda expression", false),
    ANONYMOUS_FUNCTION("anonymous function", false),
    OBJECT_LITERAL("object literal", false);

    private static final Set<KotlinTarget> ALL_TARGET_SET;
    private static final List<KotlinTarget> ANNOTATION_CLASS_LIST;
    private static final List<KotlinTarget> CLASS_LIST;
    private static final List<KotlinTarget> COMPANION_OBJECT_LIST;
    private static final Set<KotlinTarget> DEFAULT_TARGET_SET;
    private static final List<KotlinTarget> ENUM_ENTRY_LIST;
    private static final List<KotlinTarget> ENUM_LIST;
    private static final List<KotlinTarget> FILE_LIST;
    private static final List<KotlinTarget> FUNCTION_LIST;
    private static final List<KotlinTarget> INTERFACE_LIST;
    private static final List<KotlinTarget> LOCAL_CLASS_LIST;
    private static final List<KotlinTarget> OBJECT_LIST;
    private static final List<KotlinTarget> PROPERTY_GETTER_LIST;
    private static final List<KotlinTarget> PROPERTY_SETTER_LIST;
    private static final Map<AnnotationUseSiteTarget, KotlinTarget> USE_SITE_MAPPING;
    private final String description;
    private final boolean isDefault;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HashMap<String, KotlinTarget> map = new HashMap<>();

    static {
        for (KotlinTarget kotlinTarget : getEntries()) {
            map.put(kotlinTarget.name(), kotlinTarget);
        }
        EnumEntries<KotlinTarget> entries = getEntries();
        ArrayList arrayList = new ArrayList();
        for (Object obj : entries) {
            if (((KotlinTarget) obj).isDefault) {
                arrayList.add(obj);
            }
        }
        DEFAULT_TARGET_SET = CollectionsKt.toSet(arrayList);
        ALL_TARGET_SET = CollectionsKt.toSet(getEntries());
        KotlinTarget kotlinTarget2 = ANNOTATION_CLASS;
        KotlinTarget kotlinTarget3 = CLASS;
        ANNOTATION_CLASS_LIST = CollectionsKt.listOf(new KotlinTarget[]{kotlinTarget2, kotlinTarget3});
        LOCAL_CLASS_LIST = CollectionsKt.listOf(new KotlinTarget[]{LOCAL_CLASS, kotlinTarget3});
        CLASS_LIST = CollectionsKt.listOf(new KotlinTarget[]{CLASS_ONLY, kotlinTarget3});
        KotlinTarget kotlinTarget4 = COMPANION_OBJECT;
        KotlinTarget kotlinTarget5 = OBJECT;
        COMPANION_OBJECT_LIST = CollectionsKt.listOf(new KotlinTarget[]{kotlinTarget4, kotlinTarget5, kotlinTarget3});
        OBJECT_LIST = CollectionsKt.listOf(new KotlinTarget[]{STANDALONE_OBJECT, kotlinTarget5, kotlinTarget3});
        INTERFACE_LIST = CollectionsKt.listOf(new KotlinTarget[]{INTERFACE, kotlinTarget3});
        ENUM_LIST = CollectionsKt.listOf(new KotlinTarget[]{ENUM_CLASS, kotlinTarget3});
        KotlinTarget kotlinTarget6 = ENUM_ENTRY;
        KotlinTarget kotlinTarget7 = PROPERTY;
        KotlinTarget kotlinTarget8 = FIELD;
        ENUM_ENTRY_LIST = CollectionsKt.listOf(new KotlinTarget[]{kotlinTarget6, kotlinTarget7, kotlinTarget8});
        KotlinTarget kotlinTarget9 = PROPERTY_SETTER;
        PROPERTY_SETTER_LIST = CollectionsKt.listOf(kotlinTarget9);
        KotlinTarget kotlinTarget10 = PROPERTY_GETTER;
        PROPERTY_GETTER_LIST = CollectionsKt.listOf(kotlinTarget10);
        FUNCTION_LIST = CollectionsKt.listOf(FUNCTION);
        KotlinTarget kotlinTarget11 = FILE;
        FILE_LIST = CollectionsKt.listOf(kotlinTarget11);
        AnnotationUseSiteTarget annotationUseSiteTarget = AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER;
        KotlinTarget kotlinTarget12 = VALUE_PARAMETER;
        USE_SITE_MAPPING = MapsKt.mapOf(new Pair[]{TuplesKt.to(annotationUseSiteTarget, kotlinTarget12), TuplesKt.to(AnnotationUseSiteTarget.FIELD, kotlinTarget8), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY, kotlinTarget7), TuplesKt.to(AnnotationUseSiteTarget.FILE, kotlinTarget11), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_GETTER, kotlinTarget10), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_SETTER, kotlinTarget9), TuplesKt.to(AnnotationUseSiteTarget.RECEIVER, kotlinTarget12), TuplesKt.to(AnnotationUseSiteTarget.SETTER_PARAMETER, kotlinTarget12), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD, kotlinTarget8)});
    }

    /* synthetic */ KotlinTarget(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z);
    }

    public static EnumEntries<KotlinTarget> getEntries() {
        return $ENTRIES;
    }

    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: isDefault, reason: from getter */
    public final boolean getIsDefault() {
        return this.isDefault;
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0006J,\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0013R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0013R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0013R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0013R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0013R\u001d\u00101\u001a\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020\u000702¢\u0006\b\n\u0000\u001a\u0004\b4\u00105¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget$Companion;", Argument.Delimiters.none, "<init>", "()V", "map", "Ljava/util/HashMap;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "valueOrNull", ModuleXmlParser.NAME, "DEFAULT_TARGET_SET", Argument.Delimiters.none, "getDEFAULT_TARGET_SET", "()Ljava/util/Set;", "ALL_TARGET_SET", "getALL_TARGET_SET", "ANNOTATION_CLASS_LIST", Argument.Delimiters.none, "getANNOTATION_CLASS_LIST", "()Ljava/util/List;", "LOCAL_CLASS_LIST", "getLOCAL_CLASS_LIST", "CLASS_LIST", "getCLASS_LIST", "COMPANION_OBJECT_LIST", "getCOMPANION_OBJECT_LIST", "OBJECT_LIST", "getOBJECT_LIST", "INTERFACE_LIST", "getINTERFACE_LIST", "ENUM_LIST", "getENUM_LIST", "ENUM_ENTRY_LIST", "getENUM_ENTRY_LIST", "PROPERTY_SETTER_LIST", "getPROPERTY_SETTER_LIST", "PROPERTY_GETTER_LIST", "getPROPERTY_GETTER_LIST", "FUNCTION_LIST", "getFUNCTION_LIST", "FILE_LIST", "getFILE_LIST", "classActualTargets", "kind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "isInnerClass", Argument.Delimiters.none, "isCompanionObject", "isLocalClass", "USE_SITE_MAPPING", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getUSE_SITE_MAPPING", "()Ljava/util/Map;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ClassKind.values().length];
                try {
                    iArr[ClassKind.ANNOTATION_CLASS.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ClassKind.CLASS.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ClassKind.OBJECT.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[ClassKind.INTERFACE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[ClassKind.ENUM_CLASS.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[ClassKind.ENUM_ENTRY.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<KotlinTarget> classActualTargets(ClassKind kind, boolean isInnerClass, boolean isCompanionObject, boolean isLocalClass) {
            kind.getClass();
            switch (WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                case 1:
                    return getANNOTATION_CLASS_LIST();
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    return (isInnerClass || !isLocalClass) ? getCLASS_LIST() : getLOCAL_CLASS_LIST();
                case 3:
                    return isCompanionObject ? getCOMPANION_OBJECT_LIST() : getOBJECT_LIST();
                case 4:
                    return getINTERFACE_LIST();
                case 5:
                    return isLocalClass ? getLOCAL_CLASS_LIST() : getENUM_LIST();
                case 6:
                    return getENUM_ENTRY_LIST();
                default:
                    bu8.a();
                    return null;
            }
        }

        public final Set<KotlinTarget> getALL_TARGET_SET() {
            return KotlinTarget.ALL_TARGET_SET;
        }

        public final List<KotlinTarget> getANNOTATION_CLASS_LIST() {
            return KotlinTarget.ANNOTATION_CLASS_LIST;
        }

        public final List<KotlinTarget> getCLASS_LIST() {
            return KotlinTarget.CLASS_LIST;
        }

        public final List<KotlinTarget> getCOMPANION_OBJECT_LIST() {
            return KotlinTarget.COMPANION_OBJECT_LIST;
        }

        public final Set<KotlinTarget> getDEFAULT_TARGET_SET() {
            return KotlinTarget.DEFAULT_TARGET_SET;
        }

        public final List<KotlinTarget> getENUM_ENTRY_LIST() {
            return KotlinTarget.ENUM_ENTRY_LIST;
        }

        public final List<KotlinTarget> getENUM_LIST() {
            return KotlinTarget.ENUM_LIST;
        }

        public final List<KotlinTarget> getFILE_LIST() {
            return KotlinTarget.FILE_LIST;
        }

        public final List<KotlinTarget> getFUNCTION_LIST() {
            return KotlinTarget.FUNCTION_LIST;
        }

        public final List<KotlinTarget> getINTERFACE_LIST() {
            return KotlinTarget.INTERFACE_LIST;
        }

        public final List<KotlinTarget> getLOCAL_CLASS_LIST() {
            return KotlinTarget.LOCAL_CLASS_LIST;
        }

        public final List<KotlinTarget> getOBJECT_LIST() {
            return KotlinTarget.OBJECT_LIST;
        }

        public final List<KotlinTarget> getPROPERTY_GETTER_LIST() {
            return KotlinTarget.PROPERTY_GETTER_LIST;
        }

        public final List<KotlinTarget> getPROPERTY_SETTER_LIST() {
            return KotlinTarget.PROPERTY_SETTER_LIST;
        }

        public final Map<AnnotationUseSiteTarget, KotlinTarget> getUSE_SITE_MAPPING() {
            return KotlinTarget.USE_SITE_MAPPING;
        }

        public final KotlinTarget valueOrNull(String name) {
            name.getClass();
            return (KotlinTarget) KotlinTarget.map.get(name);
        }

        private Companion() {
        }
    }

    KotlinTarget(String str, boolean z) {
        this.description = str;
        this.isDefault = z;
    }
}
