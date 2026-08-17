package org.jetbrains.kotlin.wasm.ir.convertors;

import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;
import org.snakeyaml.engine.v2.tokens.DirectiveToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/convertors/WasmBinary;", "", "<init>", "()V", "MAGIC", "Lkotlin/UInt;", "I", "VERSION", "EMPTY_TYPE_FOR_BLOCK", "", "FUNC_TYPE", "STRUCT_TYPE", "ARRAY_TYPE", "SUB_TYPE", "SUB_FINAL_TYPE", "REC_GROUP", "Section", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
final class WasmBinary {
    public static final byte ARRAY_TYPE = -34;
    public static final byte EMPTY_TYPE_FOR_BLOCK = -64;
    public static final byte FUNC_TYPE = -32;
    public static final WasmBinary INSTANCE = new WasmBinary();
    public static final int MAGIC = 1836278016;
    public static final byte REC_GROUP = -50;
    public static final byte STRUCT_TYPE = -33;
    public static final byte SUB_FINAL_TYPE = -49;
    public static final byte SUB_TYPE = -48;
    public static final int VERSION = 1;

    private WasmBinary() {
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0087@\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002Ê\u0001\u0002\b\u0018¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/convertors/WasmBinary$Section;", "", "id", "Lkotlin/UShort;", "constructor-impl", "(S)S", "getId-Mh2AYeg", "()S", "S", "equals", "", "other", "equals-impl", "(SLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(S)I", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:wasm.ir", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    @JvmInline
    public static final class Section {
        private final short id;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final short CUSTOM = m1330constructorimpl(0);
        private static final short TYPE = m1330constructorimpl(1);
        private static final short IMPORT = m1330constructorimpl(2);
        private static final short FUNCTION = m1330constructorimpl(3);
        private static final short TABLE = m1330constructorimpl(4);
        private static final short MEMORY = m1330constructorimpl(5);
        private static final short GLOBAL = m1330constructorimpl(6);
        private static final short EXPORT = m1330constructorimpl(7);
        private static final short START = m1330constructorimpl(8);
        private static final short ELEMENT = m1330constructorimpl(9);
        private static final short CODE = m1330constructorimpl(10);
        private static final short DATA = m1330constructorimpl(11);
        private static final short DATA_COUNT = m1330constructorimpl(12);
        private static final short TAG = m1330constructorimpl(13);

        private /* synthetic */ Section(short s) {
            this.id = s;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Section m1329boximpl(short s) {
            return new Section(s);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static short m1330constructorimpl(short s) {
            return s;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m1331equalsimpl(short s, Object obj) {
            return (obj instanceof Section) && s == ((Section) obj).m1336unboximpl();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m1332equalsimpl0(short s, short s2) {
            return UShort.equals-impl0(s, s2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m1333hashCodeimpl(short s) {
            return UShort.hashCode-impl(s);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m1334toStringimpl(short s) {
            return "Section(id=" + ((Object) UShort.toString-impl(s)) + ')';
        }

        public boolean equals(Object obj) {
            return m1331equalsimpl(this.id, obj);
        }

        /* JADX INFO: renamed from: getId-Mh2AYeg, reason: not valid java name and from getter */
        public final short getId() {
            return this.id;
        }

        public int hashCode() {
            return m1333hashCodeimpl(this.id);
        }

        public String toString() {
            return m1334toStringimpl(this.id);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ short m1336unboximpl() {
            return this.id;
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007R\u0013\u0010\u0019\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001a\u0010\u0007R\u0013\u0010\u001b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001c\u0010\u0007R\u0013\u0010\u001d\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u001e\u0010\u0007R\u0013\u0010\u001f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b \u0010\u0007R\u0013\u0010!\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\"\u0010\u0007¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/convertors/WasmBinary$Section$Companion;", "", "<init>", "()V", "CUSTOM", "Lorg/jetbrains/kotlin/wasm/ir/convertors/WasmBinary$Section;", "getCUSTOM-Esw0DI4", "()S", "S", "TYPE", "getTYPE-Esw0DI4", "IMPORT", "getIMPORT-Esw0DI4", "FUNCTION", "getFUNCTION-Esw0DI4", "TABLE", "getTABLE-Esw0DI4", "MEMORY", "getMEMORY-Esw0DI4", "GLOBAL", "getGLOBAL-Esw0DI4", "EXPORT", "getEXPORT-Esw0DI4", "START", "getSTART-Esw0DI4", "ELEMENT", "getELEMENT-Esw0DI4", "CODE", "getCODE-Esw0DI4", "DATA", "getDATA-Esw0DI4", "DATA_COUNT", "getDATA_COUNT-Esw0DI4", DirectiveToken.TAG_DIRECTIVE, "getTAG-Esw0DI4", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            /* JADX INFO: renamed from: getCODE-Esw0DI4, reason: not valid java name */
            public final short m1337getCODEEsw0DI4() {
                return Section.CODE;
            }

            /* JADX INFO: renamed from: getCUSTOM-Esw0DI4, reason: not valid java name */
            public final short m1338getCUSTOMEsw0DI4() {
                return Section.CUSTOM;
            }

            /* JADX INFO: renamed from: getDATA-Esw0DI4, reason: not valid java name */
            public final short m1339getDATAEsw0DI4() {
                return Section.DATA;
            }

            /* JADX INFO: renamed from: getDATA_COUNT-Esw0DI4, reason: not valid java name */
            public final short m1340getDATA_COUNTEsw0DI4() {
                return Section.DATA_COUNT;
            }

            /* JADX INFO: renamed from: getELEMENT-Esw0DI4, reason: not valid java name */
            public final short m1341getELEMENTEsw0DI4() {
                return Section.ELEMENT;
            }

            /* JADX INFO: renamed from: getEXPORT-Esw0DI4, reason: not valid java name */
            public final short m1342getEXPORTEsw0DI4() {
                return Section.EXPORT;
            }

            /* JADX INFO: renamed from: getFUNCTION-Esw0DI4, reason: not valid java name */
            public final short m1343getFUNCTIONEsw0DI4() {
                return Section.FUNCTION;
            }

            /* JADX INFO: renamed from: getGLOBAL-Esw0DI4, reason: not valid java name */
            public final short m1344getGLOBALEsw0DI4() {
                return Section.GLOBAL;
            }

            /* JADX INFO: renamed from: getIMPORT-Esw0DI4, reason: not valid java name */
            public final short m1345getIMPORTEsw0DI4() {
                return Section.IMPORT;
            }

            /* JADX INFO: renamed from: getMEMORY-Esw0DI4, reason: not valid java name */
            public final short m1346getMEMORYEsw0DI4() {
                return Section.MEMORY;
            }

            /* JADX INFO: renamed from: getSTART-Esw0DI4, reason: not valid java name */
            public final short m1347getSTARTEsw0DI4() {
                return Section.START;
            }

            /* JADX INFO: renamed from: getTABLE-Esw0DI4, reason: not valid java name */
            public final short m1348getTABLEEsw0DI4() {
                return Section.TABLE;
            }

            /* JADX INFO: renamed from: getTAG-Esw0DI4, reason: not valid java name */
            public final short m1349getTAGEsw0DI4() {
                return Section.TAG;
            }

            /* JADX INFO: renamed from: getTYPE-Esw0DI4, reason: not valid java name */
            public final short m1350getTYPEEsw0DI4() {
                return Section.TYPE;
            }

            private Companion() {
            }
        }
    }
}
