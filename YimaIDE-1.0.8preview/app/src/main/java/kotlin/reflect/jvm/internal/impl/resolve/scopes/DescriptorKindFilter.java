package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class DescriptorKindFilter {
    public static final DescriptorKindFilter ALL;
    private static final int ALL_KINDS_MASK;
    public static final DescriptorKindFilter CALLABLES;
    private static final int CALLABLES_MASK;
    public static final DescriptorKindFilter CLASSIFIERS;
    private static final int CLASSIFIERS_MASK;
    public static final Companion Companion;
    private static final Lazy<List<Companion.MaskToName>> DEBUG_MASK_BIT_NAMES$delegate;
    private static final Lazy<List<Companion.MaskToName>> DEBUG_PREDEFINED_FILTERS_MASK_NAMES$delegate;
    public static final DescriptorKindFilter FUNCTIONS;
    private static final int FUNCTIONS_MASK;
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS;
    private static final int NON_SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter PACKAGES;
    private static final int PACKAGES_MASK;
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS;
    private static final int SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter TYPE_ALIASES;
    private static final int TYPE_ALIASES_MASK;
    public static final DescriptorKindFilter VALUES;
    private static final int VALUES_MASK;
    public static final DescriptorKindFilter VARIABLES;
    private static final int VARIABLES_MASK;
    private static int nextMaskValue;
    private final List<DescriptorKindExclude> excludes;
    private final int kindMask;

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        Companion companion = new Companion(defaultConstructorMarker);
        Companion = companion;
        nextMaskValue = 1;
        int iNextMask = companion.nextMask();
        NON_SINGLETON_CLASSIFIERS_MASK = iNextMask;
        int iNextMask2 = companion.nextMask();
        SINGLETON_CLASSIFIERS_MASK = iNextMask2;
        int iNextMask3 = companion.nextMask();
        TYPE_ALIASES_MASK = iNextMask3;
        int iNextMask4 = companion.nextMask();
        PACKAGES_MASK = iNextMask4;
        int iNextMask5 = companion.nextMask();
        FUNCTIONS_MASK = iNextMask5;
        int iNextMask6 = companion.nextMask();
        VARIABLES_MASK = iNextMask6;
        int iNextMask7 = companion.nextMask() - 1;
        ALL_KINDS_MASK = iNextMask7;
        int i = iNextMask | iNextMask2 | iNextMask3;
        CLASSIFIERS_MASK = i;
        int i2 = iNextMask2 | iNextMask5 | iNextMask6;
        VALUES_MASK = i2;
        int i3 = iNextMask5 | iNextMask6;
        CALLABLES_MASK = i3;
        int i4 = 2;
        ALL = new DescriptorKindFilter(iNextMask7, defaultConstructorMarker, i4, defaultConstructorMarker);
        CALLABLES = new DescriptorKindFilter(i3, defaultConstructorMarker, i4, defaultConstructorMarker);
        NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(iNextMask, defaultConstructorMarker, i4, defaultConstructorMarker);
        SINGLETON_CLASSIFIERS = new DescriptorKindFilter(iNextMask2, defaultConstructorMarker, i4, defaultConstructorMarker);
        TYPE_ALIASES = new DescriptorKindFilter(iNextMask3, defaultConstructorMarker, i4, defaultConstructorMarker);
        CLASSIFIERS = new DescriptorKindFilter(i, defaultConstructorMarker, i4, defaultConstructorMarker);
        PACKAGES = new DescriptorKindFilter(iNextMask4, defaultConstructorMarker, i4, defaultConstructorMarker);
        FUNCTIONS = new DescriptorKindFilter(iNextMask5, defaultConstructorMarker, i4, defaultConstructorMarker);
        VARIABLES = new DescriptorKindFilter(iNextMask6, defaultConstructorMarker, i4, defaultConstructorMarker);
        VALUES = new DescriptorKindFilter(i2, defaultConstructorMarker, i4, defaultConstructorMarker);
        DEBUG_PREDEFINED_FILTERS_MASK_NAMES$delegate = LazyKt.lazy(new Function0() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter$$Lambda$0
            public Object invoke() {
                return DescriptorKindFilter.DEBUG_PREDEFINED_FILTERS_MASK_NAMES_delegate$lambda$0();
            }
        });
        DEBUG_MASK_BIT_NAMES$delegate = LazyKt.lazy(new Function0() { // from class: kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter$$Lambda$1
            public Object invoke() {
                return DescriptorKindFilter.DEBUG_MASK_BIT_NAMES_delegate$lambda$0();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DescriptorKindFilter(int i, List<? extends DescriptorKindExclude> list) {
        list.getClass();
        this.excludes = list;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            i &= ~((DescriptorKindExclude) it.next()).getFullyExcludedDescriptorKinds();
        }
        this.kindMask = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List DEBUG_MASK_BIT_NAMES_delegate$lambda$0() throws IllegalAccessException {
        Field[] fields = DescriptorKindFilter.class.getFields();
        fields.getClass();
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        ArrayList<Field> arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (Intrinsics.areEqual(((Field) obj).getType(), Integer.TYPE)) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Field field2 : arrayList2) {
            Companion.MaskToName maskToName = null;
            Object obj2 = field2.get(null);
            obj2.getClass();
            int iIntValue = ((Integer) obj2).intValue();
            if (iIntValue == ((-iIntValue) & iIntValue)) {
                String name = field2.getName();
                name.getClass();
                maskToName = new Companion.MaskToName(iIntValue, name);
            }
            if (maskToName != null) {
                arrayList3.add(maskToName);
            }
        }
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List DEBUG_PREDEFINED_FILTERS_MASK_NAMES_delegate$lambda$0() throws IllegalAccessException {
        Field[] fields = DescriptorKindFilter.class.getFields();
        fields.getClass();
        ArrayList<Field> arrayList = new ArrayList();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                arrayList.add(field);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Field field2 : arrayList) {
            Companion.MaskToName maskToName = null;
            Object obj = field2.get(null);
            DescriptorKindFilter descriptorKindFilter = obj instanceof DescriptorKindFilter ? (DescriptorKindFilter) obj : null;
            if (descriptorKindFilter != null) {
                int i = descriptorKindFilter.kindMask;
                String name = field2.getName();
                name.getClass();
                maskToName = new Companion.MaskToName(i, name);
            }
            if (maskToName != null) {
                arrayList2.add(maskToName);
            }
        }
        return arrayList2;
    }

    public final boolean acceptsKinds(int i) {
        return (this.kindMask & i) != 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(DescriptorKindFilter.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        obj.getClass();
        DescriptorKindFilter descriptorKindFilter = (DescriptorKindFilter) obj;
        return Intrinsics.areEqual(this.excludes, descriptorKindFilter.excludes) && this.kindMask == descriptorKindFilter.kindMask;
    }

    public final List<DescriptorKindExclude> getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    public int hashCode() {
        return (this.excludes.hashCode() * 31) + this.kindMask;
    }

    public final DescriptorKindFilter restrictedToKindsOrNull(int i) {
        int i2 = i & this.kindMask;
        if (i2 == 0) {
            return null;
        }
        return new DescriptorKindFilter(i2, this.excludes);
    }

    public String toString() {
        Object next;
        Iterator it = Companion.getDEBUG_PREDEFINED_FILTERS_MASK_NAMES().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((Companion.MaskToName) next).getMask() != this.kindMask);
        Companion.MaskToName maskToName = (Companion.MaskToName) next;
        String name = maskToName != null ? maskToName.getName() : null;
        if (name == null) {
            List<Companion.MaskToName> debug_mask_bit_names = Companion.getDEBUG_MASK_BIT_NAMES();
            ArrayList arrayList = new ArrayList();
            for (Companion.MaskToName maskToName2 : debug_mask_bit_names) {
                String name2 = acceptsKinds(maskToName2.getMask()) ? maskToName2.getName() : null;
                if (name2 != null) {
                    arrayList.add(name2);
                }
            }
            name = CollectionsKt.joinToString$default(arrayList, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        return "DescriptorKindFilter(" + name + ", " + this.excludes + ')';
    }

    public static final class Companion {

        public static final class MaskToName {
            private final int mask;
            private final String name;

            public MaskToName(int i, String str) {
                str.getClass();
                this.mask = i;
                this.name = str;
            }

            public final int getMask() {
                return this.mask;
            }

            public final String getName() {
                return this.name;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<MaskToName> getDEBUG_MASK_BIT_NAMES() {
            return (List) DescriptorKindFilter.DEBUG_MASK_BIT_NAMES$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<MaskToName> getDEBUG_PREDEFINED_FILTERS_MASK_NAMES() {
            return (List) DescriptorKindFilter.DEBUG_PREDEFINED_FILTERS_MASK_NAMES$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int nextMask() {
            int i = DescriptorKindFilter.nextMaskValue;
            DescriptorKindFilter.nextMaskValue <<= 1;
            return i;
        }

        public final int getALL_KINDS_MASK() {
            return DescriptorKindFilter.ALL_KINDS_MASK;
        }

        public final int getCLASSIFIERS_MASK() {
            return DescriptorKindFilter.CLASSIFIERS_MASK;
        }

        public final int getFUNCTIONS_MASK() {
            return DescriptorKindFilter.FUNCTIONS_MASK;
        }

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getTYPE_ALIASES_MASK() {
            return DescriptorKindFilter.TYPE_ALIASES_MASK;
        }

        public final int getVARIABLES_MASK() {
            return DescriptorKindFilter.VARIABLES_MASK;
        }

        private Companion() {
        }
    }

    public /* synthetic */ DescriptorKindFilter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list);
    }
}
