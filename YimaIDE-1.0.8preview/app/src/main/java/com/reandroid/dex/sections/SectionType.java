package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.NumberIntegerReference;
import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.data.AnnotationGroup;
import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.data.AnnotationSet;
import com.reandroid.dex.data.AnnotationsDirectory;
import com.reandroid.dex.data.ClassData;
import com.reandroid.dex.data.CodeItem;
import com.reandroid.dex.data.DebugInfo;
import com.reandroid.dex.data.EncodedArray;
import com.reandroid.dex.data.HiddenApiRestrictions;
import com.reandroid.dex.data.StringData;
import com.reandroid.dex.data.TypeList;
import com.reandroid.dex.header.DexHeader;
import com.reandroid.dex.id.CallSiteId;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.MethodHandleId;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.id.ProtoId;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.CallSiteKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.CollectionUtil;
import com.sun.jna.platform.win32.WinUser;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SectionType<T extends SectionItem> implements Creator<T> {
    public static final SectionType<AnnotationsDirectory> ANNOTATION_DIRECTORY;
    public static final SectionType<AnnotationGroup> ANNOTATION_GROUP;
    public static final SectionType<AnnotationItem> ANNOTATION_ITEM;
    public static final SectionType<AnnotationSet> ANNOTATION_SET;
    public static final SectionType<CallSiteId> CALL_SITE_ID;
    public static final SectionType<ClassData> CLASS_DATA;
    public static final SectionType<ClassId> CLASS_ID;
    public static final SectionType<CodeItem> CODE;
    private static final SectionType<?>[] DATA_REMOVE_ORDER;
    public static final SectionType<DebugInfo> DEBUG_INFO;
    private static final SectionType<?>[] DEX_LIB2_ORDER;
    public static final SectionType<EncodedArray> ENCODED_ARRAY;
    public static final SectionType<FieldId> FIELD_ID;
    public static final SectionType<DexHeader> HEADER;
    public static final SectionType<HiddenApiRestrictions> HIDDEN_API;
    public static final SectionType<MapList> MAP_LIST;
    public static final SectionType<MethodHandleId> METHOD_HANDLE;
    public static final SectionType<MethodId> METHOD_ID;
    public static final SectionType<ProtoId> PROTO_ID;
    private static final SectionType<?>[] R8_ORDER;
    private static final SectionType<?>[] READ_ORDER;
    private static final SectionType<?>[] SORT_SECTIONS_ORDER;
    public static final SectionType<StringData> STRING_DATA;
    public static final SectionType<StringId> STRING_ID;
    public static final SectionType<TypeId> TYPE_ID;
    public static final SectionType<TypeList> TYPE_LIST;
    private final String name;
    private final int type;

    static {
        SectionType<DexHeader> sectionType = new SectionType<DexHeader>("HEADER", 0) { // from class: com.reandroid.dex.sections.SectionType.1
            @Override // com.reandroid.dex.sections.SectionType
            public Section<DexHeader> createSection(IntegerPair integerPair) {
                return new SpecialSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public SpecialSection<DexHeader> createSpecialSection(IntegerReference integerReference) {
                return new SpecialSection<>(integerReference, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isSpecialSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public DexHeader newInstance() {
                return new DexHeader();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        HEADER = sectionType;
        SectionType<MapList> sectionType2 = new SectionType<MapList>("MAP_LIST", 4096) { // from class: com.reandroid.dex.sections.SectionType.2
            @Override // com.reandroid.dex.sections.SectionType
            public Section<MapList> createSection(IntegerPair integerPair) {
                return new SpecialSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public SpecialSection<MapList> createSpecialSection(IntegerReference integerReference) {
                return new SpecialSection<>(integerReference, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isSpecialSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public MapList newInstance() {
                return new MapList(new NumberIntegerReference());
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        MAP_LIST = sectionType2;
        SectionType<StringId> sectionType3 = new SectionType<StringId>("STRING_ID", 1) { // from class: com.reandroid.dex.sections.SectionType.3
            @Override // com.reandroid.dex.sections.SectionType
            public StringIdSection createSection(IntegerPair integerPair) {
                return new StringIdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 0;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public StringId newInstance() {
                return new StringId();
            }
        };
        STRING_ID = sectionType3;
        SectionType<StringData> sectionType4 = new SectionType<StringData>("STRING_DATA", WinUser.SM_CARETBLINKINGENABLED) { // from class: com.reandroid.dex.sections.SectionType.4
            @Override // com.reandroid.dex.sections.SectionType
            public StringDataSection createSection(IntegerPair integerPair) {
                return new StringDataSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public StringData newInstance() {
                return new StringData();
            }
        };
        STRING_DATA = sectionType4;
        SectionType<TypeId> sectionType5 = new SectionType<TypeId>("TYPE_ID", 2) { // from class: com.reandroid.dex.sections.SectionType.5
            @Override // com.reandroid.dex.sections.SectionType
            public Section<TypeId> createSection(IntegerPair integerPair) {
                return new IdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 1;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public TypeId newInstance() {
                return new TypeId();
            }
        };
        TYPE_ID = sectionType5;
        SectionType<TypeList> sectionType6 = new SectionType<TypeList>("TYPE_LIST", 4097) { // from class: com.reandroid.dex.sections.SectionType.6
            @Override // com.reandroid.dex.sections.SectionType
            public Section<TypeList> createSection(IntegerPair integerPair) {
                return new DataSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public TypeList newInstance() {
                return new TypeList();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        TYPE_LIST = sectionType6;
        SectionType<ProtoId> sectionType7 = new SectionType<ProtoId>("PROTO_ID", 3) { // from class: com.reandroid.dex.sections.SectionType.7
            @Override // com.reandroid.dex.sections.SectionType
            public Section<ProtoId> createSection(IntegerPair integerPair) {
                return new IdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 4;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public ProtoId newInstance() {
                return new ProtoId();
            }
        };
        PROTO_ID = sectionType7;
        SectionType<FieldId> sectionType8 = new SectionType<FieldId>("FIELD_ID", 4) { // from class: com.reandroid.dex.sections.SectionType.8
            @Override // com.reandroid.dex.sections.SectionType
            public Section<FieldId> createSection(IntegerPair integerPair) {
                return new IdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 2;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public FieldId newInstance() {
                return new FieldId();
            }
        };
        FIELD_ID = sectionType8;
        SectionType<MethodId> sectionType9 = new SectionType<MethodId>("METHOD_ID", 5) { // from class: com.reandroid.dex.sections.SectionType.9
            @Override // com.reandroid.dex.sections.SectionType
            public Section<MethodId> createSection(IntegerPair integerPair) {
                return new IdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 3;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public MethodId newInstance() {
                return new MethodId();
            }
        };
        METHOD_ID = sectionType9;
        SectionType<AnnotationItem> sectionType10 = new SectionType<AnnotationItem>("ANNOTATION_ITEM", 8196) { // from class: com.reandroid.dex.sections.SectionType.10
            @Override // com.reandroid.dex.sections.SectionType
            public Section<AnnotationItem> createSection(IntegerPair integerPair) {
                return new DataSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public AnnotationItem newInstance() {
                return new AnnotationItem();
            }
        };
        ANNOTATION_ITEM = sectionType10;
        SectionType<AnnotationSet> sectionType11 = new SectionType<AnnotationSet>("ANNOTATION_SET", 4099) { // from class: com.reandroid.dex.sections.SectionType.11
            @Override // com.reandroid.dex.sections.SectionType
            public AnnotationSetSection createSection(IntegerPair integerPair) {
                return new AnnotationSetSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public AnnotationSet newInstance() {
                return new AnnotationSet();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        ANNOTATION_SET = sectionType11;
        SectionType<AnnotationGroup> sectionType12 = new SectionType<AnnotationGroup>("ANNOTATION_GROUP", 4098) { // from class: com.reandroid.dex.sections.SectionType.12
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<AnnotationGroup> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public AnnotationGroup newInstance() {
                return new AnnotationGroup();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        ANNOTATION_GROUP = sectionType12;
        SectionType<AnnotationsDirectory> sectionType13 = new SectionType<AnnotationsDirectory>("ANNOTATIONS_DIRECTORY", 8198) { // from class: com.reandroid.dex.sections.SectionType.13
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<AnnotationsDirectory> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public AnnotationsDirectory newInstance() {
                return new AnnotationsDirectory();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        ANNOTATION_DIRECTORY = sectionType13;
        SectionType<CallSiteId> sectionType14 = new SectionType<CallSiteId>("CALL_SITE_ID", 7) { // from class: com.reandroid.dex.sections.SectionType.14
            @Override // com.reandroid.dex.sections.SectionType
            public Section<CallSiteId> createSection(IntegerPair integerPair) {
                return new IdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 5;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public CallSiteId newInstance() {
                return new CallSiteId();
            }
        };
        CALL_SITE_ID = sectionType14;
        SectionType<MethodHandleId> sectionType15 = new SectionType<MethodHandleId>("METHOD_HANDLE", 8) { // from class: com.reandroid.dex.sections.SectionType.15
            @Override // com.reandroid.dex.sections.SectionType
            public Section<MethodHandleId> createSection(IntegerPair integerPair) {
                return new IdSection(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 6;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public MethodHandleId newInstance() {
                return new MethodHandleId();
            }
        };
        METHOD_HANDLE = sectionType15;
        SectionType<DebugInfo> sectionType16 = new SectionType<DebugInfo>("DEBUG_INFO", 8195) { // from class: com.reandroid.dex.sections.SectionType.16
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<DebugInfo> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public DebugInfo newInstance() {
                return new DebugInfo();
            }
        };
        DEBUG_INFO = sectionType16;
        SectionType<CodeItem> sectionType17 = new SectionType<CodeItem>("CODE", 8193) { // from class: com.reandroid.dex.sections.SectionType.17
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<CodeItem> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public CodeItem newInstance() {
                return new CodeItem();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        CODE = sectionType17;
        SectionType<EncodedArray> sectionType18 = new SectionType<EncodedArray>("ENCODED_ARRAY", 8197) { // from class: com.reandroid.dex.sections.SectionType.18
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<EncodedArray> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public EncodedArray newInstance() {
                return new EncodedArray();
            }
        };
        ENCODED_ARRAY = sectionType18;
        SectionType<ClassData> sectionType19 = new SectionType<ClassData>("CLASS_DATA", 8192) { // from class: com.reandroid.dex.sections.SectionType.19
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<ClassData> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public ClassData newInstance() {
                return new ClassData();
            }
        };
        CLASS_DATA = sectionType19;
        SectionType<ClassId> sectionType20 = new SectionType<ClassId>("CLASS_ID", 6) { // from class: com.reandroid.dex.sections.SectionType.20
            @Override // com.reandroid.dex.sections.SectionType
            public ClassIdSection createSection(IntegerPair integerPair) {
                return new ClassIdSection(integerPair);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int getReferenceType() {
                return 7;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isIdSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public ClassId newInstance() {
                return new ClassId();
            }
        };
        CLASS_ID = sectionType20;
        SectionType<HiddenApiRestrictions> sectionType21 = new SectionType<HiddenApiRestrictions>("HIDDEN_API", 61440) { // from class: com.reandroid.dex.sections.SectionType.21
            @Override // com.reandroid.dex.sections.SectionType
            public DataSection<HiddenApiRestrictions> createSection(IntegerPair integerPair) {
                return new DataSection<>(integerPair, this);
            }

            @Override // com.reandroid.dex.sections.SectionType
            public boolean isDataSection() {
                return true;
            }

            @Override // com.reandroid.dex.sections.SectionType
            public HiddenApiRestrictions newInstance() {
                return new HiddenApiRestrictions();
            }

            @Override // com.reandroid.dex.sections.SectionType
            public int sectionAlignment() {
                return 4;
            }
        };
        HIDDEN_API = sectionType21;
        READ_ORDER = new SectionType[]{sectionType, sectionType2, sectionType3, sectionType4, sectionType5, sectionType6, sectionType7, sectionType8, sectionType9, sectionType15, sectionType10, sectionType11, sectionType12, sectionType13, sectionType18, sectionType14, sectionType16, sectionType17, sectionType19, sectionType20, sectionType21};
        DATA_REMOVE_ORDER = new SectionType[]{sectionType19, sectionType17, sectionType16, sectionType13, sectionType12, sectionType11, sectionType10, sectionType18, sectionType14, sectionType15, sectionType9, sectionType8, sectionType7, sectionType6, sectionType5, sectionType3, sectionType4};
        R8_ORDER = new SectionType[]{sectionType, sectionType3, sectionType5, sectionType7, sectionType8, sectionType9, sectionType20, sectionType14, sectionType15, sectionType17, sectionType16, sectionType6, sectionType4, sectionType10, sectionType19, sectionType18, sectionType11, sectionType12, sectionType13, sectionType21, sectionType2};
        SORT_SECTIONS_ORDER = new SectionType[]{sectionType4, sectionType3, sectionType5, sectionType6, sectionType7, sectionType8, sectionType9, sectionType15, sectionType10, sectionType11, sectionType12, sectionType13, sectionType18, sectionType14, sectionType16, sectionType17, sectionType19, sectionType20, sectionType21, sectionType2, sectionType};
        DEX_LIB2_ORDER = new SectionType[]{sectionType, sectionType3, sectionType5, sectionType7, sectionType8, sectionType9, sectionType20, sectionType4, sectionType6, sectionType18, sectionType14, sectionType10, sectionType11, sectionType12, sectionType13, sectionType16, sectionType17, sectionType19, sectionType21, sectionType2};
    }

    private SectionType(String str, int i) {
        this.name = str;
        this.type = i;
    }

    public static /* synthetic */ boolean a(SectionType[] sectionTypeArr, SectionType sectionType) {
        for (SectionType sectionType2 : sectionTypeArr) {
            if (sectionType == sectionType2) {
                return false;
            }
        }
        return true;
    }

    public static <T1> Comparator<T1> comparator(SectionType<?>[] sectionTypeArr, Function<? super T1, SectionType<?>> function) {
        return new OrderBasedComparator(sectionTypeArr, function);
    }

    public static Predicate<SectionType<?>> except(final SectionType<?>... sectionTypeArr) {
        return (sectionTypeArr == null || sectionTypeArr.length == 0) ? CollectionUtil.getAcceptAll() : new Predicate() { // from class: n1d
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SectionType.a(sectionTypeArr, (SectionType) obj);
            }
        };
    }

    public static Predicate<SectionType<?>> exceptDebug() {
        return except(DEBUG_INFO);
    }

    public static Predicate<SectionType<?>> exceptDebugCodeAnnotations() {
        return except(DEBUG_INFO, CODE, ANNOTATION_ITEM, ANNOTATION_SET, ANNOTATION_GROUP, ANNOTATION_DIRECTORY);
    }

    public static <T1 extends SectionItem> SectionType<T1> get(int i) {
        for (Object obj : R8_ORDER) {
            SectionType<T1> sectionType = (SectionType<T1>) obj;
            if (i == ((SectionType) sectionType).type) {
                return sectionType;
            }
        }
        return null;
    }

    public static SectionType<?>[] getDexLib2Order() {
        return (SectionType[]) DEX_LIB2_ORDER.clone();
    }

    public static Iterator<SectionType<?>> getIdSectionTypes() {
        return ArrayIterator.of(R8_ORDER, new Predicate() { // from class: m1d
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((SectionType) obj).isIdSection();
            }
        });
    }

    public static SectionType<?>[] getR8Order() {
        return (SectionType[]) R8_ORDER.clone();
    }

    public static <T1> Comparator<T1> getReadComparator(Function<? super T1, SectionType<?>> function) {
        return comparator(READ_ORDER, function);
    }

    public static SectionType<? extends IdItem> getReferenceType(int i) {
        switch (i) {
            case 0:
                return STRING_ID;
            case 1:
                return TYPE_ID;
            case 2:
                return FIELD_ID;
            case 3:
                return METHOD_ID;
            case 4:
                return PROTO_ID;
            case 5:
                return CALL_SITE_ID;
            case 6:
                return METHOD_HANDLE;
            default:
                return null;
        }
    }

    public static SectionType<?>[] getRemoveOrderList() {
        return (SectionType[]) DATA_REMOVE_ORDER.clone();
    }

    public static SectionType<? extends IdItem> getSectionType(Key key) {
        if (key instanceof StringKey) {
            return STRING_ID;
        }
        if (key instanceof TypeKey) {
            return TYPE_ID;
        }
        if (key instanceof ProtoKey) {
            return PROTO_ID;
        }
        if (key instanceof FieldKey) {
            return FIELD_ID;
        }
        if (key instanceof MethodKey) {
            return METHOD_ID;
        }
        if (key instanceof MethodHandleKey) {
            return METHOD_HANDLE;
        }
        if (key instanceof CallSiteKey) {
            return CALL_SITE_ID;
        }
        return null;
    }

    public static Iterator<SectionType<?>> getSectionTypes() {
        return new ArrayIterator(R8_ORDER);
    }

    public static SectionType<?>[] getSortSectionsOrder() {
        return (SectionType[]) SORT_SECTIONS_ORDER.clone();
    }

    public static Predicate<SectionType<?>> minimal() {
        return except(DEBUG_INFO, CODE, ANNOTATION_ITEM, ANNOTATION_SET, ANNOTATION_GROUP, ANNOTATION_DIRECTORY, ENCODED_ARRAY, METHOD_HANDLE, CALL_SITE_ID, HIDDEN_API);
    }

    public Section<T> createSection(IntegerPair integerPair) {
        return null;
    }

    public Section<T> createSpecialSection(IntegerReference integerReference) {
        throw new RuntimeException("Not implemented for: " + getName());
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public Creator<T> getCreator() {
        return this;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type;
    }

    public boolean isDataSection() {
        return false;
    }

    public boolean isIdSection() {
        return false;
    }

    public boolean isSpecialSection() {
        return false;
    }

    @Override // com.reandroid.arsc.base.Creator
    public abstract T newInstance();

    public int sectionAlignment() {
        return 0;
    }

    public String toString() {
        return getName();
    }

    public static class OrderBasedComparator<T1> implements Comparator<T1> {
        private final Function<? super T1, SectionType<?>> function;
        private final SectionType<?>[] sortOrder;

        public OrderBasedComparator(SectionType<?>[] sectionTypeArr, Function<? super T1, SectionType<?>> function) {
            this.sortOrder = sectionTypeArr;
            this.function = function;
        }

        private int getOrder(T1 t1) {
            return t1 == null ? this.sortOrder.length - 1 : getOrder(this.function.apply(t1));
        }

        @Override // java.util.Comparator
        public int compare(T1 t1, T1 t2) {
            return Integer.compare(getOrder(t1), getOrder(t2));
        }

        private int getOrder(SectionType<?> sectionType) {
            SectionType<?>[] sectionTypeArr = this.sortOrder;
            int length = sectionTypeArr.length;
            for (int i = 0; i < length; i++) {
                if (sectionTypeArr[i] == sectionType) {
                    return i;
                }
            }
            return length - 2;
        }
    }

    public int getReferenceType() {
        return 7;
    }
}
