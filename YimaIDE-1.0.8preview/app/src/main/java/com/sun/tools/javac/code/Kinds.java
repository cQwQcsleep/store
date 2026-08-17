package com.sun.tools.javac.code;

import com.sun.source.tree.MemberReferenceTree;
import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.api.Messages;
import defpackage.s22;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import javax.lang.model.element.ElementKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Kinds {

    /* JADX INFO: renamed from: com.sun.tools.javac.code.Kinds$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;

        static {
            int[] iArr = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr;
            try {
                iArr[ElementKind.PACKAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ANNOTATION_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.CLASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RECORD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.INTERFACE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.TYPE_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.BINDING_VARIABLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM_CONSTANT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.LOCAL_VARIABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.EXCEPTION_PARAMETER.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RESOURCE_VARIABLE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.FIELD.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.CONSTRUCTOR.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.METHOD.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.STATIC_INIT.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.INSTANCE_INIT.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[MemberReferenceTree.ReferenceMode.values().length];
            $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode = iArr2;
            try {
                iArr2[MemberReferenceTree.ReferenceMode.INVOKE.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode[MemberReferenceTree.ReferenceMode.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public static class KindSelector {
        public static final KindSelector ASG;
        public static final KindSelector ERR;
        public static final KindSelector MDL;
        public static final KindSelector MTH;
        public static final KindSelector NIL = new KindSelector(0);
        public static final KindSelector PCK;
        public static final KindSelector POLY;
        public static final KindSelector TYP;
        public static final KindSelector TYP_PCK;
        public static final KindSelector VAL;
        public static final KindSelector VAL_MTH;
        public static final KindSelector VAL_POLY;
        public static final KindSelector VAL_TYP;
        public static final KindSelector VAL_TYP_PCK;
        public static final KindSelector VAR;
        private final byte data;

        static {
            KindSelector kindSelector = new KindSelector(1);
            PCK = kindSelector;
            KindSelector kindSelector2 = new KindSelector(2);
            TYP = kindSelector2;
            VAR = new KindSelector(4);
            KindSelector kindSelector3 = new KindSelector(12);
            VAL = kindSelector3;
            KindSelector kindSelector4 = new KindSelector(16);
            MTH = kindSelector4;
            KindSelector kindSelector5 = new KindSelector(32);
            POLY = kindSelector5;
            MDL = new KindSelector(64);
            ERR = new KindSelector(127);
            ASG = new KindSelector(132);
            TYP_PCK = of(kindSelector2, kindSelector);
            VAL_MTH = of(kindSelector3, kindSelector4);
            VAL_POLY = of(kindSelector3, kindSelector5);
            VAL_TYP = of(kindSelector3, kindSelector2);
            VAL_TYP_PCK = of(kindSelector3, kindSelector2, kindSelector);
        }

        private KindSelector(int i) {
            this.data = (byte) i;
        }

        public static KindSelector of(KindSelector... kindSelectorArr) {
            byte b = 0;
            for (KindSelector kindSelector : kindSelectorArr) {
                b = (byte) (b | kindSelector.data);
            }
            return new KindSelector(b);
        }

        public boolean contains(KindSelector kindSelector) {
            return (this.data & kindSelector.data) != 0;
        }

        public Set<KindName> kindNames() {
            EnumSet enumSetNoneOf = EnumSet.noneOf(KindName.class);
            byte b = this.data;
            byte b2 = VAL.data;
            if ((b & b2) != 0) {
                if ((b & b2) == VAR.data) {
                    enumSetNoneOf.add(KindName.VAR);
                } else {
                    enumSetNoneOf.add(KindName.VAL);
                }
            }
            if ((this.data & MTH.data) != 0) {
                enumSetNoneOf.add(KindName.METHOD);
            }
            if ((this.data & TYP.data) != 0) {
                enumSetNoneOf.add(KindName.CLASS);
            }
            if ((this.data & PCK.data) != 0) {
                enumSetNoneOf.add(KindName.PACKAGE);
            }
            if ((this.data & MDL.data) != 0) {
                enumSetNoneOf.add(KindName.MODULE);
            }
            return enumSetNoneOf;
        }

        public boolean subset(KindSelector kindSelector) {
            return (this.data & (~kindSelector.data)) == 0;
        }
    }

    private Kinds() {
    }

    public static KindName kindName(Symbol symbol) {
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[symbol.getKind().ordinal()]) {
            case 1:
                return KindName.PACKAGE;
            case 2:
                return KindName.ENUM;
            case 3:
            case 4:
                return KindName.CLASS;
            case 5:
                return KindName.RECORD;
            case 6:
                return KindName.INTERFACE;
            case 7:
                return KindName.TYPEVAR;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return KindName.VAR;
            case 14:
                return (symbol.flags_field & Flags.RECORD) != 0 ? KindName.RECORD_COMPONENT : KindName.VAR;
            case 15:
                return KindName.CONSTRUCTOR;
            case 16:
                return KindName.METHOD;
            case 17:
                return KindName.STATIC_INIT;
            case 18:
                return KindName.INSTANCE_INIT;
            default:
                pe1.a("Unexpected kind: ", symbol.getKind());
                return null;
        }
    }

    public static KindName typeKindName(Type type) {
        if (type.hasTag(TypeTag.TYPEVAR) || (type.hasTag(TypeTag.CLASS) && (type.tsym.flags() & 16777216) != 0)) {
            return KindName.BOUND;
        }
        if (type.hasTag(TypeTag.PACKAGE)) {
            return KindName.PACKAGE;
        }
        long j = type.tsym.flags_field;
        if ((8192 & j) != 0) {
            return KindName.ANNOTATION;
        }
        return (j & 512) != 0 ? KindName.INTERFACE : KindName.CLASS;
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'NIL' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class Kind {
        private static final /* synthetic */ Kind[] $VALUES;
        public static final Kind ABSENT_MTH;
        public static final Kind ABSENT_TYP;
        public static final Kind ABSENT_VAR;
        public static final Kind AMBIGUOUS;
        public static final Kind BAD_RESTRICTED_TYPE;
        public static final Kind ERR;
        public static final Kind HIDDEN;
        public static final Kind MDL;
        public static final Kind MTH;
        public static final Kind NIL;
        public static final Kind PCK;
        public static final Kind POLY;
        public static final Kind STATICERR;
        public static final Kind TYP;
        public static final Kind VAR;
        public static final Kind WRONG_MTH;
        public static final Kind WRONG_MTHS;
        private final KindName absentKind;
        private final Category category;
        private final KindName kindName;
        private final KindSelector selector;

        public enum Category {
            BASIC,
            ERROR,
            RESOLUTION,
            RESOLUTION_TARGET
        }

        private static /* synthetic */ Kind[] $values() {
            return new Kind[]{NIL, PCK, TYP, VAR, MTH, POLY, MDL, ERR, AMBIGUOUS, HIDDEN, STATICERR, BAD_RESTRICTED_TYPE, ABSENT_VAR, WRONG_MTHS, WRONG_MTH, ABSENT_MTH, ABSENT_TYP};
        }

        static {
            Category category = Category.BASIC;
            NIL = new Kind("NIL", 0, category, KindSelector.NIL);
            PCK = new Kind("PCK", 1, category, KindName.PACKAGE, KindSelector.PCK);
            KindName kindName = KindName.CLASS;
            TYP = new Kind("TYP", 2, category, kindName, KindSelector.TYP);
            KindName kindName2 = KindName.VAR;
            VAR = new Kind("VAR", 3, category, kindName2, KindSelector.VAR);
            KindName kindName3 = KindName.METHOD;
            MTH = new Kind("MTH", 4, category, kindName3, KindSelector.MTH);
            POLY = new Kind("POLY", 5, category, KindSelector.POLY);
            MDL = new Kind("MDL", 6, category, KindSelector.MDL);
            ERR = new Kind("ERR", 7, Category.ERROR, KindSelector.ERR);
            Category category2 = Category.RESOLUTION_TARGET;
            AMBIGUOUS = new Kind("AMBIGUOUS", 8, category2);
            HIDDEN = new Kind("HIDDEN", 9, category2);
            STATICERR = new Kind("STATICERR", 10, category2);
            BAD_RESTRICTED_TYPE = new Kind("BAD_RESTRICTED_TYPE", 11, Category.RESOLUTION);
            ABSENT_VAR = new Kind("ABSENT_VAR", 12, category2, kindName2);
            WRONG_MTHS = new Kind("WRONG_MTHS", 13, category2, kindName3);
            WRONG_MTH = new Kind("WRONG_MTH", 14, category2, kindName3);
            ABSENT_MTH = new Kind("ABSENT_MTH", 15, category2, kindName3);
            ABSENT_TYP = new Kind("ABSENT_TYP", 16, category2, kindName);
            $VALUES = $values();
        }

        private Kind(String str, int i, Category category, KindName kindName, KindName kindName2, KindSelector kindSelector) {
            super(str, i);
            this.category = category;
            this.kindName = kindName;
            this.absentKind = kindName2;
            this.selector = kindSelector;
        }

        public static Kind valueOf(String str) {
            return (Kind) Enum.valueOf(Kind.class, str);
        }

        public static Kind[] values() {
            return (Kind[]) $VALUES.clone();
        }

        public KindName absentKind() {
            KindName kindName = this.absentKind;
            if (kindName != null) {
                return kindName;
            }
            s22.a("Unexpected kind: ", this);
            return null;
        }

        public boolean betterThan(Kind kind) {
            return ordinal() < kind.ordinal();
        }

        public boolean isResolutionError() {
            Category category = this.category;
            return category == Category.RESOLUTION || category == Category.RESOLUTION_TARGET;
        }

        public boolean isResolutionTargetError() {
            return this.category == Category.RESOLUTION_TARGET;
        }

        public boolean isValid() {
            return this.category == Category.BASIC;
        }

        public KindName kindName() {
            KindName kindName = this.kindName;
            if (kindName != null) {
                return kindName;
            }
            s22.a("Unexpected kind: ", this);
            return null;
        }

        public boolean matches(KindSelector kindSelector) {
            return this.selector.contains(kindSelector);
        }

        public KindSelector toSelector() {
            return this.selector;
        }

        private Kind(String str, int i, Category category, KindSelector kindSelector) {
            this(str, i, category, null, null, kindSelector);
        }

        private Kind(String str, int i, Category category, KindName kindName) {
            this(str, i, category, null, kindName, null);
        }

        private Kind(String str, int i, Category category, KindName kindName, KindSelector kindSelector) {
            this(str, i, category, kindName, null, kindSelector);
        }

        private Kind(String str, int i, Category category) {
            this(str, i, category, null, null, null);
        }
    }

    public enum KindName implements Formattable {
        ANNOTATION("kindname.annotation"),
        CONSTRUCTOR("kindname.constructor"),
        INTERFACE("kindname.interface"),
        ENUM("kindname.enum"),
        STATIC("kindname.static"),
        TYPEVAR("kindname.type.variable"),
        BOUND("kindname.type.variable.bound"),
        VAR("kindname.variable"),
        VAL("kindname.value"),
        METHOD("kindname.method"),
        CLASS("kindname.class"),
        STATIC_INIT("kindname.static.init"),
        INSTANCE_INIT("kindname.instance.init"),
        PACKAGE("kindname.package"),
        MODULE("kindname.module"),
        RECORD_COMPONENT("kindname.record.component"),
        RECORD("kindname.record");

        private final String name;

        KindName(String str) {
            this.name = str;
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String getKind() {
            return "Kindname";
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String toString(Locale locale, Messages messages) {
            return messages.getLocalizedString(locale, "compiler.misc." + toString(), new Object[0]);
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public static KindName kindName(MemberReferenceTree.ReferenceMode referenceMode) {
        int i = AnonymousClass1.$SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode[referenceMode.ordinal()];
        if (i == 1) {
            return KindName.METHOD;
        }
        if (i == 2) {
            return KindName.CONSTRUCTOR;
        }
        s22.a("Unexpected mode: ", referenceMode);
        return null;
    }
}
