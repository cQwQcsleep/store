package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'COMPANION_GENERATION' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\u0081\u0002\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u0013\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", Argument.Delimiters.none, "noProcessor", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;IZ)V", "getNoProcessor", "()Z", "RAW_FIR", "IMPORTS", "COMPILER_REQUIRED_ANNOTATIONS", "COMPANION_GENERATION", "SUPER_TYPES", "SEALED_CLASS_INHERITORS", "TYPES", "STATUS", "EXPECT_ACTUAL_MATCHING", "CONTRACTS", "IMPLICIT_TYPES_BODY_RESOLVE", "CONSTANT_EVALUATION", "ANNOTATION_ARGUMENTS", "BODY_RESOLVE", "next", "getNext", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "previous", "getPrevious", "Companion", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvePhase {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ FirResolvePhase[] $VALUES;
    private static final FirResolvePhase ANALYZED_DEPENDENCIES;
    public static final FirResolvePhase ANNOTATION_ARGUMENTS;
    public static final FirResolvePhase BODY_RESOLVE;
    public static final FirResolvePhase COMPANION_GENERATION;
    public static final FirResolvePhase CONSTANT_EVALUATION;
    public static final FirResolvePhase CONTRACTS;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final FirResolvePhase DECLARATIONS;
    public static final FirResolvePhase EXPECT_ACTUAL_MATCHING;
    public static final FirResolvePhase IMPLICIT_TYPES_BODY_RESOLVE;
    public static final FirResolvePhase STATUS;
    private final boolean noProcessor;
    public static final FirResolvePhase RAW_FIR = new FirResolvePhase("RAW_FIR", 0, true);
    public static final FirResolvePhase IMPORTS = new FirResolvePhase("IMPORTS", 1, false, 1, null);
    public static final FirResolvePhase COMPILER_REQUIRED_ANNOTATIONS = new FirResolvePhase("COMPILER_REQUIRED_ANNOTATIONS", 2, false, 1, null);
    public static final FirResolvePhase SUPER_TYPES = new FirResolvePhase("SUPER_TYPES", 4, false, 1, null);
    public static final FirResolvePhase SEALED_CLASS_INHERITORS = new FirResolvePhase("SEALED_CLASS_INHERITORS", 5, false, 1, null);
    public static final FirResolvePhase TYPES = new FirResolvePhase("TYPES", 6, false, 1, null);

    private static final /* synthetic */ FirResolvePhase[] $values() {
        return new FirResolvePhase[]{RAW_FIR, IMPORTS, COMPILER_REQUIRED_ANNOTATIONS, COMPANION_GENERATION, SUPER_TYPES, SEALED_CLASS_INHERITORS, TYPES, STATUS, EXPECT_ACTUAL_MATCHING, CONTRACTS, IMPLICIT_TYPES_BODY_RESOLVE, CONSTANT_EVALUATION, ANNOTATION_ARGUMENTS, BODY_RESOLVE};
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        COMPANION_GENERATION = new FirResolvePhase("COMPANION_GENERATION", 3, false, 1, defaultConstructorMarker);
        FirResolvePhase firResolvePhase = new FirResolvePhase("STATUS", 7, false, 1, null);
        STATUS = firResolvePhase;
        EXPECT_ACTUAL_MATCHING = new FirResolvePhase("EXPECT_ACTUAL_MATCHING", 8, false, 1, null);
        CONTRACTS = new FirResolvePhase("CONTRACTS", 9, false, 1, null);
        IMPLICIT_TYPES_BODY_RESOLVE = new FirResolvePhase("IMPLICIT_TYPES_BODY_RESOLVE", 10, false, 1, defaultConstructorMarker);
        CONSTANT_EVALUATION = new FirResolvePhase("CONSTANT_EVALUATION", 11, false, 1, null);
        ANNOTATION_ARGUMENTS = new FirResolvePhase("ANNOTATION_ARGUMENTS", 12, false, 1, null);
        FirResolvePhase firResolvePhase2 = new FirResolvePhase("BODY_RESOLVE", 13, false, 1, null);
        BODY_RESOLVE = firResolvePhase2;
        FirResolvePhase[] firResolvePhaseArr$values = $values();
        $VALUES = firResolvePhaseArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(firResolvePhaseArr$values);
        INSTANCE = new Companion(null);
        DECLARATIONS = firResolvePhase;
        ANALYZED_DEPENDENCIES = firResolvePhase2;
    }

    public /* synthetic */ FirResolvePhase(String str, int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 1) != 0 ? false : z);
    }

    public static EnumEntries<FirResolvePhase> getEntries() {
        return $ENTRIES;
    }

    public static FirResolvePhase valueOf(String str) {
        return (FirResolvePhase) Enum.valueOf(FirResolvePhase.class, str);
    }

    public static FirResolvePhase[] values() {
        return (FirResolvePhase[]) $VALUES.clone();
    }

    public final FirResolvePhase getNext() {
        return (FirResolvePhase) getEntries().get(ordinal() + 1);
    }

    public final boolean getNoProcessor() {
        return this.noProcessor;
    }

    public final FirResolvePhase getPrevious() {
        return (FirResolvePhase) getEntries().get(ordinal() - 1);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase$Companion;", Argument.Delimiters.none, "<init>", "()V", "DECLARATIONS", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getDECLARATIONS", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "ANALYZED_DEPENDENCIES", "getANALYZED_DEPENDENCIES", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirResolvePhase getANALYZED_DEPENDENCIES() {
            return FirResolvePhase.ANALYZED_DEPENDENCIES;
        }

        public final FirResolvePhase getDECLARATIONS() {
            return FirResolvePhase.DECLARATIONS;
        }

        private Companion() {
        }
    }

    private FirResolvePhase(String str, int i, boolean z) {
        super(str, i);
        this.noProcessor = z;
    }
}
