package org.jetbrains.kotlin.codegen;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.StringConcatGenerator;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.JvmStringConcat;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Handle;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u0000 !2\u00020\u0001:\u0003\u001f !B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0013J$\u0010\u0019\u001a\u00060\u001aj\u0002`\u001b2\u0016\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rH\u0002J&\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/codegen/StringConcatGenerator;", Argument.Delimiters.none, "mode", "Lorg/jetbrains/kotlin/config/JvmStringConcat;", "mv", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "<init>", "(Lorg/jetbrains/kotlin/config/JvmStringConcat;Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;)V", "items", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$Item;", "Lkotlin/collections/ArrayList;", "paramSlots", Argument.Delimiters.none, "justFlushed", Argument.Delimiters.none, "putValueOrProcessConstant", Argument.Delimiters.none, "value", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "invokeAppend", "genToString", "buildRecipe", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "itemForGeneration", "fitRestrictions", Argument.Delimiters.none, "ItemType", "Item", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StringConcatGenerator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Set<Type> STRING_BUILDER_OBJECT_APPEND_ARG_TYPES;
    private final ArrayList<Item> items;
    private boolean justFlushed;
    private final JvmStringConcat mode;
    private final InstructionAdapter mv;
    private int paramSlots;
    private final KotlinTypeMapperBase typeMapper;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$ItemType;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "PARAMETER", "CONSTANT", "INLINED_CONSTANT", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ItemType {
        PARAMETER,
        CONSTANT,
        INLINED_CONSTANT;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ItemType> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ItemType.values().length];
            try {
                iArr[ItemType.PARAMETER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ItemType.CONSTANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ItemType.INLINED_CONSTANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        HashSet hashSetNewHashSet = Sets.newHashSet(new Type[]{AsmTypes.getType(String.class), AsmTypes.getType(StringBuffer.class), AsmTypes.getType(CharSequence.class)});
        hashSetNewHashSet.getClass();
        STRING_BUILDER_OBJECT_APPEND_ARG_TYPES = hashSetNewHashSet;
    }

    public StringConcatGenerator(JvmStringConcat jvmStringConcat, InstructionAdapter instructionAdapter, KotlinTypeMapperBase kotlinTypeMapperBase) {
        jvmStringConcat.getClass();
        instructionAdapter.getClass();
        kotlinTypeMapperBase.getClass();
        this.mode = jvmStringConcat;
        this.mv = instructionAdapter;
        this.typeMapper = kotlinTypeMapperBase;
        this.items = new ArrayList<>();
    }

    private final StringBuilder buildRecipe(ArrayList<Item> itemForGeneration) {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemForGeneration) {
            int i = WhenMappings.$EnumSwitchMapping$0[item.getItemType().ordinal()];
            if (i == 1) {
                sb.append("\u0001");
            } else if (i == 2) {
                sb.append("\u0002");
            } else {
                if (i != 3) {
                    bu8.a();
                    return null;
                }
                sb.append(item.getValue());
            }
        }
        return sb;
    }

    private final ArrayList<Item> fitRestrictions(List<Item> items) {
        Object obj;
        ArrayList<Item> arrayList = new ArrayList<>();
        List<Item> list = items;
        for (Item item : list) {
            int i = WhenMappings.$EnumSwitchMapping$0[item.getItemType().ordinal()];
            if (i != 2 && i != 3) {
                arrayList.add(item);
            } else if (item.fitEncodingLimit()) {
                arrayList.add(item);
            } else {
                Iterator<T> it = CodegenUtilKt.splitStringConstant(item.getValue()).iterator();
                while (it.hasNext()) {
                    arrayList.add(new Item(item.getType(), ItemType.CONSTANT, (String) it.next()));
                }
            }
        }
        StringBuilder sbBuildRecipe = buildRecipe(arrayList);
        while (CodegenUtilKt.encodedUTF8Size(sbBuildRecipe.toString()) > 65535) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                if (((Item) obj2).getItemType() == ItemType.INLINED_CONSTANT) {
                    arrayList2.add(obj2);
                }
            }
            Iterator it2 = arrayList2.iterator();
            if (it2.hasNext()) {
                Object next = it2.next();
                if (it2.hasNext()) {
                    int encodedUTF8Size = ((Item) next).getEncodedUTF8Size();
                    do {
                        Object next2 = it2.next();
                        int encodedUTF8Size2 = ((Item) next2).getEncodedUTF8Size();
                        if (encodedUTF8Size < encodedUTF8Size2) {
                            next = next2;
                            encodedUTF8Size = encodedUTF8Size2;
                        }
                    } while (it2.hasNext());
                }
                obj = next;
            } else {
                obj = null;
            }
            Item item2 = (Item) obj;
            if (item2 == null) {
                break;
            }
            item2.setItemType(ItemType.CONSTANT);
            sbBuildRecipe = buildRecipe(arrayList);
        }
        return arrayList;
    }

    public final void genToString() {
        if (!this.mode.isDynamic()) {
            this.mv.invokevirtual("java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            return;
        }
        if (this.justFlushed) {
            return;
        }
        if (this.mode == JvmStringConcat.INDY_WITH_CONSTANTS) {
            Handle handle = new Handle(6, "java/lang/invoke/StringConcatFactory", "makeConcatWithConstants", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;", false);
            ArrayList<Item> arrayListFitRestrictions = fitRestrictions(this.items);
            StringBuilder sbBuildRecipe = buildRecipe(arrayListFitRestrictions);
            ArrayList arrayList = new ArrayList();
            for (Object obj : arrayListFitRestrictions) {
                if (((Item) obj).getItemType() == ItemType.CONSTANT) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((Item) it.next()).getValue());
            }
            InstructionAdapter instructionAdapter = this.mv;
            Type type = AsmTypes.JAVA_STRING_TYPE;
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayListFitRestrictions) {
                if (((Item) obj2).getItemType() == ItemType.PARAMETER) {
                    arrayList3.add(obj2);
                }
            }
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((Item) it2.next()).getType());
            }
            Type[] typeArr = (Type[]) arrayList4.toArray(new Type[0]);
            instructionAdapter.invokedynamic("makeConcatWithConstants", Type.getMethodDescriptor(type, (Type[]) Arrays.copyOf(typeArr, typeArr.length)), handle, ArraysKt.plus(new String[]{sbBuildRecipe.toString()}, arrayList2));
        } else {
            Handle handle2 = new Handle(6, "java/lang/invoke/StringConcatFactory", "makeConcat", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", false);
            ArrayList<Item> arrayList5 = this.items;
            if (arrayList5 == null || !arrayList5.isEmpty()) {
                Iterator<T> it3 = arrayList5.iterator();
                while (it3.hasNext() && ((Item) it3.next()).getItemType() == ItemType.PARAMETER) {
                }
            }
            InstructionAdapter instructionAdapter2 = this.mv;
            Type type2 = AsmTypes.JAVA_STRING_TYPE;
            ArrayList<Item> arrayList6 = this.items;
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
            Iterator<T> it4 = arrayList6.iterator();
            while (it4.hasNext()) {
                arrayList7.add(((Item) it4.next()).getType());
            }
            Type[] typeArr2 = (Type[]) arrayList7.toArray(new Type[0]);
            instructionAdapter2.invokedynamic("makeConcat", Type.getMethodDescriptor(type2, (Type[]) Arrays.copyOf(typeArr2, typeArr2.length)), handle2, new Object[0]);
        }
        this.items.clear();
        ArrayList<Item> arrayList8 = this.items;
        Item.Companion companion = Item.INSTANCE;
        Type type3 = AsmTypes.JAVA_STRING_TYPE;
        type3.getClass();
        arrayList8.add(companion.parameter(type3));
        this.paramSlots = type3.getSize();
    }

    public final void invokeAppend(Type type) {
        type.getClass();
        if (!this.mode.isDynamic()) {
            this.mv.invokevirtual("java/lang/StringBuilder", "append", "(" + INSTANCE.stringBuilderAppendType(type) + ")Ljava/lang/StringBuilder;", false);
            return;
        }
        this.justFlushed = false;
        this.items.add(Item.INSTANCE.parameter(type));
        int size = this.paramSlots + type.getSize();
        this.paramSlots = size;
        if (size >= 199) {
            genToString();
            this.justFlushed = true;
        }
    }

    public final void putValueOrProcessConstant(Object value, Type type) {
        type.getClass();
        this.justFlushed = false;
        if (this.mode != JvmStringConcat.INDY_WITH_CONSTANTS) {
            new StackValue.Constant(value, type).put(type, null, this.mv, this.typeMapper);
            invokeAppend(type);
            return;
        }
        if (value instanceof String) {
            CharSequence charSequence = (CharSequence) value;
            if (StringsKt.contains$default(charSequence, "\u0001", false, 2, (Object) null) || StringsKt.contains$default(charSequence, "\u0002", false, 2, (Object) null)) {
                this.items.add(Item.INSTANCE.constant((String) value));
                return;
            }
        }
        if ((value instanceof Character) && (Intrinsics.areEqual(value, (Object) (char) 1) || Intrinsics.areEqual(value, (Object) (char) 2))) {
            this.items.add(Item.INSTANCE.constant(String.valueOf(((Character) value).charValue())));
        } else {
            this.items.add(Item.INSTANCE.inlinedConstant(String.valueOf(value)));
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0018\u001a\u00020\u0019J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J'\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$Item;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "itemType", "Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$ItemType;", "value", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$ItemType;Ljava/lang/String;)V", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getItemType", "()Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$ItemType;", "setItemType", "(Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$ItemType;)V", "getValue", "()Ljava/lang/String;", "encodedUTF8Size", Argument.Delimiters.none, "getEncodedUTF8Size", "()I", "encodedUTF8Size$delegate", "Lkotlin/Lazy;", "fitEncodingLimit", Argument.Delimiters.none, "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Item {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: encodedUTF8Size$delegate, reason: from kotlin metadata */
        private final Lazy encodedUTF8Size;
        private ItemType itemType;
        private final Type type;
        private final String value;

        public Item(Type type, ItemType itemType, String str) {
            type.getClass();
            itemType.getClass();
            str.getClass();
            this.type = type;
            this.itemType = itemType;
            this.value = str;
            this.encodedUTF8Size = LazyKt.lazy(new Function0() { // from class: hqd
                public final Object invoke() {
                    return Integer.valueOf(StringConcatGenerator.Item.a(this.b));
                }
            });
        }

        public static int a(Item item) {
            return CodegenUtilKt.encodedUTF8Size(item.value);
        }

        public static /* synthetic */ Item copy$default(Item item, Type type, ItemType itemType, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                type = item.type;
            }
            if ((i & 2) != 0) {
                itemType = item.itemType;
            }
            if ((i & 4) != 0) {
                str = item.value;
            }
            return item.copy(type, itemType, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Type getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ItemType getItemType() {
            return this.itemType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final Item copy(Type type, ItemType itemType, String value) {
            type.getClass();
            itemType.getClass();
            value.getClass();
            return new Item(type, itemType, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Item)) {
                return false;
            }
            Item item = (Item) other;
            return Intrinsics.areEqual(this.type, item.type) && this.itemType == item.itemType && Intrinsics.areEqual(this.value, item.value);
        }

        public final boolean fitEncodingLimit() {
            return CodegenUtilKt.isDefinitelyFitEncodingLimit(this.value) || getEncodedUTF8Size() <= 65535;
        }

        public final int getEncodedUTF8Size() {
            return ((Number) this.encodedUTF8Size.getValue()).intValue();
        }

        public final ItemType getItemType() {
            return this.itemType;
        }

        public final Type getType() {
            return this.type;
        }

        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            return (((this.type.hashCode() * 31) + this.itemType.hashCode()) * 31) + this.value.hashCode();
        }

        public final void setItemType(ItemType itemType) {
            itemType.getClass();
            this.itemType = itemType;
        }

        public String toString() {
            return "Item(type=" + this.type + ", itemType=" + this.itemType + ", value=" + this.value + ')';
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$Item$Companion;", Argument.Delimiters.none, "<init>", "()V", "inlinedConstant", "Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$Item;", "value", Argument.Delimiters.none, "constant", "parameter", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Item constant(String value) {
                value.getClass();
                Type type = AsmTypes.JAVA_STRING_TYPE;
                type.getClass();
                return new Item(type, ItemType.CONSTANT, value);
            }

            public final Item inlinedConstant(String value) {
                value.getClass();
                Type type = AsmTypes.JAVA_STRING_TYPE;
                type.getClass();
                return new Item(type, ItemType.INLINED_CONSTANT, value);
            }

            public final Item parameter(Type type) {
                type.getClass();
                return new Item(type, ItemType.PARAMETER, "\u0001");
            }

            private Companion() {
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/codegen/StringConcatGenerator$Companion;", Argument.Delimiters.none, "<init>", "()V", "STRING_BUILDER_OBJECT_APPEND_ARG_TYPES", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", "stringBuilderAppendType", ModuleXmlParser.TYPE, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Type stringBuilderAppendType(Type type) {
            int sort = type.getSort();
            if (sort == 3 || sort == 4) {
                Type type2 = Type.INT_TYPE;
                type2.getClass();
                return type2;
            }
            if (sort == 9) {
                Type type3 = AsmTypes.OBJECT_TYPE;
                type3.getClass();
                return type3;
            }
            if (sort != 10 || StringConcatGenerator.STRING_BUILDER_OBJECT_APPEND_ARG_TYPES.contains(type)) {
                return type;
            }
            Type type4 = AsmTypes.OBJECT_TYPE;
            type4.getClass();
            return type4;
        }

        private Companion() {
        }
    }
}
