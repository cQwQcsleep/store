package org.jetbrains.kotlin.codegen.inline;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.codegen.state.JvmBackendConfig;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSystemCommonBackendContext;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 <*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003:;<B=\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0019\u0010\u001a\u001a\u00028\u0000*\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u001cH\u0002¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001a\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0014\u0010\u001f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010 \u001a\u00020\u0011H\u0002J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001eH\u0002J5\u0010#\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\rH\u0002¢\u0006\u0002\u0010)J-\u0010*\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010+J%\u0010,\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010-J \u0010.\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u001eH\u0002J%\u0010/\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010-J\u0010\u00100\u001a\u00020\r2\u0006\u0010\u0018\u001a\u000201H\u0002J-\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00112\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\r06H\u0082\bJ \u00107\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\u0011H\u0002J\u0018\u00108\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u001eH\u0002J-\u00109\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00028\u00002\u0006\u0010\"\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010+R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner;", "KT", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", Argument.Delimiters.none, "parametersMapping", "Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMappings;", "intrinsicsSupport", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$IntrinsicsSupport;", "typeSystem", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "unifiedNullChecks", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/TypeParameterMappings;Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$IntrinsicsSupport;Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Z)V", "maxStackSize", Argument.Delimiters.none, "hasReifiedParameters", "reifyInstructions", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeParametersUsages;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "processReifyMarker", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "reify", "argument", "Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;)Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/org/objectweb/asm/Type;", "arrayOf", "arrayDepth", "processNewArray", "parameter", "processAs", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", ModuleXmlParser.TYPE, "asmType", "safe", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/InsnList;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/org/objectweb/asm/Type;Z)Z", "processIs", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/InsnList;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/org/objectweb/asm/Type;)Z", "processTypeOf", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/InsnList;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Z", "processCatch", "processPlugin", "isPluginNext", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "rewriteNextTypeInsn", "marker", "expectedNextOpcode", "rewrite", "Lkotlin/Function1;", "processNextTypeInsn", "processJavaClass", "processSpecialEnumFunction", "OperationKind", "IntrinsicsSupport", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReifiedTypeInliner<KT extends KotlinTypeMarker> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String NEED_CLASS_REIFICATION_MARKER_METHOD_NAME = "needClassReification";
    public static final String REIFIED_OPERATION_MARKER_METHOD_NAME = "reifiedOperationMarker";
    public static final String pluginIntrinsicsMarkerMethod = "voidMagicApiCall";
    public static final String pluginIntrinsicsMarkerOwner = "kotlin/jvm/internal/MagicApiIntrinsics";
    public static final String pluginIntrinsicsMarkerSignature = "(Ljava/lang/Object;)V";
    private final boolean hasReifiedParameters;
    private final IntrinsicsSupport<KT> intrinsicsSupport;
    private final LanguageVersionSettings languageVersionSettings;
    private int maxStackSize;
    private final TypeParameterMappings<KT> parametersMapping;
    private final TypeSystemCommonBackendContext typeSystem;
    private final boolean unifiedNullChecks;

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0013J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\f\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\tH&J\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH&J-\u0010\u001e\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\f\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010#R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006$À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$IntrinsicsSupport;", "KT", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", Argument.Delimiters.none, "config", "Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", "getConfig", "()Lorg/jetbrains/kotlin/codegen/state/JvmBackendConfig;", "putClassInstance", Argument.Delimiters.none, "v", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", ModuleXmlParser.TYPE, "(Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)V", "generateTypeParameterContainer", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "isMutableCollectionType", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Z", "toKotlinType", "Lorg/jetbrains/kotlin/types/KotlinType;", "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/kotlin/types/KotlinType;", "generateExternalEntriesForEnumTypeIfNeeded", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "reportSuspendTypeUnsupported", "reportNonReifiedTypeParameterWithRecursiveBoundUnsupported", "typeParameterName", "Lorg/jetbrains/kotlin/name/Name;", "rewritePluginDefinedOperationMarker", "reifiedInsn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "(Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/InsnList;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Z", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface IntrinsicsSupport<KT extends KotlinTypeMarker> {
        FieldInsnNode generateExternalEntriesForEnumTypeIfNeeded(KT type);

        void generateTypeParameterContainer(InstructionAdapter v, TypeParameterMarker typeParameter);

        JvmBackendConfig getConfig();

        boolean isMutableCollectionType(KT type);

        void putClassInstance(InstructionAdapter v, KT type);

        void reportNonReifiedTypeParameterWithRecursiveBoundUnsupported(Name typeParameterName);

        void reportSuspendTypeUnsupported();

        default boolean rewritePluginDefinedOperationMarker(InstructionAdapter v, AbstractInsnNode reifiedInsn, InsnList instructions, KT type) {
            v.getClass();
            reifiedInsn.getClass();
            instructions.getClass();
            type.getClass();
            return false;
        }

        KotlinType toKotlinType(KT type);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$OperationKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "NEW_ARRAY", "AS", "SAFE_AS", "IS", "JAVA_CLASS", "ENUM_REIFIED", "TYPE_OF", "CATCH", "id", Argument.Delimiters.none, "getId", "()I", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum OperationKind {
        NEW_ARRAY,
        AS,
        SAFE_AS,
        IS,
        JAVA_CLASS,
        ENUM_REIFIED,
        TYPE_OF,
        CATCH;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<OperationKind> getEntries() {
            return $ENTRIES;
        }

        public final int getId() {
            return ordinal();
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OperationKind.values().length];
            try {
                iArr[OperationKind.NEW_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OperationKind.AS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[OperationKind.SAFE_AS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[OperationKind.IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[OperationKind.JAVA_CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[OperationKind.ENUM_REIFIED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[OperationKind.TYPE_OF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[OperationKind.CATCH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ReifiedTypeInliner(TypeParameterMappings<KT> typeParameterMappings, IntrinsicsSupport<KT> intrinsicsSupport, TypeSystemCommonBackendContext typeSystemCommonBackendContext, LanguageVersionSettings languageVersionSettings, boolean z) {
        intrinsicsSupport.getClass();
        typeSystemCommonBackendContext.getClass();
        languageVersionSettings.getClass();
        this.parametersMapping = typeParameterMappings;
        this.intrinsicsSupport = intrinsicsSupport;
        this.typeSystem = typeSystemCommonBackendContext;
        this.languageVersionSettings = languageVersionSettings;
        this.unifiedNullChecks = z;
        this.hasReifiedParameters = typeParameterMappings != null ? typeParameterMappings.hasReifiedParameters() : false;
    }

    private final KotlinTypeMarker arrayOf(KotlinTypeMarker kotlinTypeMarker, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            kotlinTypeMarker = this.typeSystem.arrayType(kotlinTypeMarker);
        }
        return kotlinTypeMarker;
    }

    @JvmStatic
    public static final boolean isNeedClassReificationMarker(AbstractInsnNode abstractInsnNode) {
        return INSTANCE.isNeedClassReificationMarker(abstractInsnNode);
    }

    private final boolean isPluginNext(AbstractInsnNode insn) {
        AbstractInsnNode next;
        MethodInsnNode next2;
        AbstractInsnNode next3 = insn.getNext();
        if (next3 != null && (next = next3.getNext()) != null && (next2 = next.getNext()) != null && (next2 instanceof MethodInsnNode)) {
            MethodInsnNode methodInsnNode = next2;
            if (methodInsnNode.getOpcode() == 184 && Intrinsics.areEqual(methodInsnNode.owner, pluginIntrinsicsMarkerOwner) && Intrinsics.areEqual(methodInsnNode.name, pluginIntrinsicsMarkerMethod) && Intrinsics.areEqual(methodInsnNode.desc, pluginIntrinsicsMarkerSignature) && (methodInsnNode.getPrevious() instanceof LdcInsnNode)) {
                return true;
            }
        }
        return false;
    }

    private final boolean processAs(MethodInsnNode insn, InsnList instructions, KT type, Type asmType, boolean safe) {
        TypeInsnNode next = insn.getNext();
        if (next == null || next.getOpcode() != 192 || !(next instanceof TypeInsnNode)) {
            return false;
        }
        MethodNode methodNode = new MethodNode(589824);
        CodegenUtilKt.generateAsCast(new InstructionAdapter(methodNode), this.intrinsicsSupport.toKotlinType(type), asmType, safe, this.unifiedNullChecks);
        instructions.insert(insn, methodNode.instructions);
        if (Intrinsics.areEqual(next.desc, AsmTypes.OBJECT_TYPE.getInternalName())) {
            instructions.remove(next);
        }
        this.maxStackSize = Math.max(this.maxStackSize, 4);
        return true;
    }

    private final boolean processCatch(MethodInsnNode insn, MethodNode node, Type asmType) {
        Object obj;
        Object next;
        AbstractInsnNode previous = insn.getPrevious();
        while (previous != null && !(previous instanceof LabelNode)) {
            previous = previous.getPrevious();
        }
        LabelNode labelNode = (LabelNode) previous;
        if (labelNode == null) {
            k2d.a("cannot locate label of catch block handler");
            return false;
        }
        List list = node.tryCatchBlocks;
        list.getClass();
        Iterator it = list.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            TryCatchBlockNode tryCatchBlockNode = (TryCatchBlockNode) next;
            if (Intrinsics.areEqual(tryCatchBlockNode.handler, labelNode) && tryCatchBlockNode.type != null) {
                break;
            }
        }
        TryCatchBlockNode tryCatchBlockNode2 = (TryCatchBlockNode) next;
        if (tryCatchBlockNode2 == null && (labelNode.getNext() instanceof LineNumberNode)) {
            AbstractInsnNode previous2 = labelNode.getPrevious();
            while (previous2 != null && !(previous2 instanceof LabelNode)) {
                previous2 = previous2.getPrevious();
            }
            LabelNode labelNode2 = (LabelNode) previous2;
            if (labelNode2 == null) {
                k2d.a("cannot locate label of catch block handler before line number");
                return false;
            }
            List list2 = node.tryCatchBlocks;
            list2.getClass();
            for (Object obj2 : list2) {
                TryCatchBlockNode tryCatchBlockNode3 = (TryCatchBlockNode) obj2;
                if (Intrinsics.areEqual(tryCatchBlockNode3.handler, labelNode2) && tryCatchBlockNode3.type != null) {
                    obj = obj2;
                    break;
                }
            }
            tryCatchBlockNode2 = (TryCatchBlockNode) obj;
            if (tryCatchBlockNode2 == null) {
                return false;
            }
        }
        if (tryCatchBlockNode2 != null) {
            tryCatchBlockNode2.type = asmType.getInternalName();
            return true;
        }
        k2d.a("cannot identify catch block");
        return false;
    }

    private final boolean processIs(MethodInsnNode insn, InsnList instructions, KT type, Type asmType) {
        AbstractInsnNode next = insn.getNext();
        if (next == null || next.getOpcode() != 193 || !(next instanceof TypeInsnNode)) {
            return false;
        }
        MethodNode methodNode = new MethodNode(589824);
        CodegenUtilKt.generateIsCheck(new InstructionAdapter(methodNode), this.intrinsicsSupport.toKotlinType(type), asmType);
        instructions.insert(insn, methodNode.instructions);
        instructions.remove(next);
        this.maxStackSize = Math.max(this.maxStackSize, 2);
        return true;
    }

    private final boolean processJavaClass(MethodInsnNode insn, Type parameter) {
        LdcInsnNode next = insn.getNext();
        if (!(next instanceof LdcInsnNode)) {
            return false;
        }
        next.cst = parameter;
        return true;
    }

    private final boolean processNewArray(MethodInsnNode insn, Type parameter) {
        return processNextTypeInsn(insn, parameter, 189);
    }

    private final boolean processNextTypeInsn(MethodInsnNode insn, Type parameter, int expectedNextOpcode) {
        AbstractInsnNode next = insn.getNext();
        if (next == null || next.getOpcode() != expectedNextOpcode) {
            return false;
        }
        TypeInsnNode next2 = insn.getNext();
        next2.getClass();
        next2.desc = parameter.getInternalName();
        return true;
    }

    private final boolean processPlugin(MethodInsnNode insn, InsnList instructions, KT type) {
        AbstractInsnNode next = insn.getNext();
        if (next == null) {
            return false;
        }
        MethodNode methodNode = new MethodNode(589824, "fake", "()V", (String) null, (String[]) null);
        MaxStackFrameSizeAndLocalsCalculator maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc = InlineCodegenUtilsKt.wrapWithMaxLocalCalc(methodNode);
        if (!this.intrinsicsSupport.rewritePluginDefinedOperationMarker(new InstructionAdapter(maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc), next, instructions, type)) {
            return false;
        }
        maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc.visitInsn(177);
        maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc.visitMaxs(-1, -1);
        InsnList insnList = methodNode.instructions;
        insnList.remove(insnList.getLast());
        instructions.insert(insn, methodNode.instructions);
        this.maxStackSize = Math.max(this.maxStackSize, methodNode.maxStack);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x006b  */
    /* JADX WARN: Code duplicated, block: B:27:0x0076  */
    /* JADX WARN: Code duplicated, block: B:29:0x007a  */
    /* JADX WARN: Code duplicated, block: B:31:0x0080  */
    /* JADX WARN: Code duplicated, block: B:32:0x0088  */
    /* JADX WARN: Code duplicated, block: B:33:0x0090  */
    /* JADX WARN: Code duplicated, block: B:34:0x0095  */
    /* JADX WARN: Code duplicated, block: B:35:0x009d  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:37:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:39:0x00bb A[PHI: r6
      0x00bb: PHI (r6v5 org.jetbrains.org.objectweb.asm.tree.MethodInsnNode) = (r6v3 org.jetbrains.org.objectweb.asm.tree.MethodInsnNode), (r6v6 org.jetbrains.org.objectweb.asm.tree.MethodInsnNode) binds: [B:38:0x00b9, B:24:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
    private final ReifiedTypeParametersUsages processReifyMarker(MethodInsnNode insn, MethodNode node) {
        ReificationArgument reificationArgument;
        TypeParameterMappings<KT> typeParameterMappings;
        TypeParameterMapping<KT> typeParameterMapping;
        MethodInsnNode methodInsnNode;
        boolean zProcessNewArray;
        OperationKind operationKind = ReifiedTypeInlinerKt.getOperationKind(insn);
        if (operationKind == null || (reificationArgument = ReifiedTypeInlinerKt.getReificationArgument(insn)) == null || (typeParameterMappings = this.parametersMapping) == null || (typeParameterMapping = typeParameterMappings.get(reificationArgument.getParameterName())) == null) {
            return null;
        }
        Type typeReify = reify(typeParameterMapping.getAsmType(), reificationArgument);
        KotlinTypeMarker kotlinTypeMarkerReify = reify(typeParameterMapping.getType(), reificationArgument);
        InsnList insnList = node.instructions;
        if (typeParameterMapping.getReificationArgument() != null && operationKind != OperationKind.TYPE_OF) {
            ReificationArgument reificationArgumentCombine = reificationArgument.combine(typeParameterMapping.getReificationArgument());
            AbstractInsnNode previous = insn.getPrevious();
            previous.getClass();
            insnList.set(previous, new LdcInsnNode(reificationArgumentCombine.asString()));
        } else if (isPluginNext(insn)) {
            insnList.getClass();
            if (processPlugin(insn, insnList, kotlinTypeMarkerReify)) {
                methodInsnNode = insn;
            } else {
                switch (WhenMappings.$EnumSwitchMapping$0[operationKind.ordinal()]) {
                    case 1:
                        methodInsnNode = insn;
                        zProcessNewArray = processNewArray(methodInsnNode, typeReify);
                        if (zProcessNewArray) {
                        }
                        break;
                    case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                        methodInsnNode = insn;
                        insnList.getClass();
                        zProcessNewArray = processAs(methodInsnNode, insnList, kotlinTypeMarkerReify, typeReify, false);
                        if (zProcessNewArray) {
                        }
                        break;
                    case 3:
                        insnList.getClass();
                        methodInsnNode = insn;
                        zProcessNewArray = processAs(methodInsnNode, insnList, kotlinTypeMarkerReify, typeReify, true);
                        if (zProcessNewArray) {
                        }
                        break;
                    case 4:
                        insnList.getClass();
                        zProcessNewArray = processIs(insn, insnList, kotlinTypeMarkerReify, typeReify);
                        methodInsnNode = insn;
                        if (zProcessNewArray) {
                        }
                        break;
                    case 5:
                        zProcessNewArray = processJavaClass(insn, typeReify);
                        methodInsnNode = insn;
                        if (zProcessNewArray) {
                        }
                        break;
                    case 6:
                        insnList.getClass();
                        zProcessNewArray = processSpecialEnumFunction(insn, insnList, kotlinTypeMarkerReify, typeReify);
                        methodInsnNode = insn;
                        if (zProcessNewArray) {
                        }
                        break;
                    case 7:
                        insnList.getClass();
                        zProcessNewArray = processTypeOf(insn, insnList, kotlinTypeMarkerReify);
                        methodInsnNode = insn;
                        if (zProcessNewArray) {
                        }
                        break;
                    case 8:
                        zProcessNewArray = processCatch(insn, node, typeReify);
                        methodInsnNode = insn;
                        if (zProcessNewArray) {
                        }
                        break;
                    default:
                        bu8.a();
                        return null;
                }
            }
            AbstractInsnNode previous2 = methodInsnNode.getPrevious().getPrevious();
            previous2.getClass();
            insnList.remove(previous2);
            AbstractInsnNode previous3 = methodInsnNode.getPrevious();
            previous3.getClass();
            insnList.remove(previous3);
            insnList.remove(methodInsnNode);
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[operationKind.ordinal()]) {
                case 1:
                    methodInsnNode = insn;
                    zProcessNewArray = processNewArray(methodInsnNode, typeReify);
                    if (zProcessNewArray) {
                        AbstractInsnNode previous4 = methodInsnNode.getPrevious().getPrevious();
                        previous4.getClass();
                        insnList.remove(previous4);
                        AbstractInsnNode previous5 = methodInsnNode.getPrevious();
                        previous5.getClass();
                        insnList.remove(previous5);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    methodInsnNode = insn;
                    insnList.getClass();
                    zProcessNewArray = processAs(methodInsnNode, insnList, kotlinTypeMarkerReify, typeReify, false);
                    if (zProcessNewArray) {
                        AbstractInsnNode previous6 = methodInsnNode.getPrevious().getPrevious();
                        previous6.getClass();
                        insnList.remove(previous6);
                        AbstractInsnNode previous7 = methodInsnNode.getPrevious();
                        previous7.getClass();
                        insnList.remove(previous7);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case 3:
                    insnList.getClass();
                    methodInsnNode = insn;
                    zProcessNewArray = processAs(methodInsnNode, insnList, kotlinTypeMarkerReify, typeReify, true);
                    if (zProcessNewArray) {
                        AbstractInsnNode previous8 = methodInsnNode.getPrevious().getPrevious();
                        previous8.getClass();
                        insnList.remove(previous8);
                        AbstractInsnNode previous9 = methodInsnNode.getPrevious();
                        previous9.getClass();
                        insnList.remove(previous9);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case 4:
                    insnList.getClass();
                    zProcessNewArray = processIs(insn, insnList, kotlinTypeMarkerReify, typeReify);
                    methodInsnNode = insn;
                    if (zProcessNewArray) {
                        AbstractInsnNode previous10 = methodInsnNode.getPrevious().getPrevious();
                        previous10.getClass();
                        insnList.remove(previous10);
                        AbstractInsnNode previous11 = methodInsnNode.getPrevious();
                        previous11.getClass();
                        insnList.remove(previous11);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case 5:
                    zProcessNewArray = processJavaClass(insn, typeReify);
                    methodInsnNode = insn;
                    if (zProcessNewArray) {
                        AbstractInsnNode previous12 = methodInsnNode.getPrevious().getPrevious();
                        previous12.getClass();
                        insnList.remove(previous12);
                        AbstractInsnNode previous13 = methodInsnNode.getPrevious();
                        previous13.getClass();
                        insnList.remove(previous13);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case 6:
                    insnList.getClass();
                    zProcessNewArray = processSpecialEnumFunction(insn, insnList, kotlinTypeMarkerReify, typeReify);
                    methodInsnNode = insn;
                    if (zProcessNewArray) {
                        AbstractInsnNode previous14 = methodInsnNode.getPrevious().getPrevious();
                        previous14.getClass();
                        insnList.remove(previous14);
                        AbstractInsnNode previous15 = methodInsnNode.getPrevious();
                        previous15.getClass();
                        insnList.remove(previous15);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case 7:
                    insnList.getClass();
                    zProcessNewArray = processTypeOf(insn, insnList, kotlinTypeMarkerReify);
                    methodInsnNode = insn;
                    if (zProcessNewArray) {
                        AbstractInsnNode previous16 = methodInsnNode.getPrevious().getPrevious();
                        previous16.getClass();
                        insnList.remove(previous16);
                        AbstractInsnNode previous17 = methodInsnNode.getPrevious();
                        previous17.getClass();
                        insnList.remove(previous17);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                case 8:
                    zProcessNewArray = processCatch(insn, node, typeReify);
                    methodInsnNode = insn;
                    if (zProcessNewArray) {
                        AbstractInsnNode previous18 = methodInsnNode.getPrevious().getPrevious();
                        previous18.getClass();
                        insnList.remove(previous18);
                        AbstractInsnNode previous19 = methodInsnNode.getPrevious();
                        previous19.getClass();
                        insnList.remove(previous19);
                        insnList.remove(methodInsnNode);
                    }
                    break;
                default:
                    bu8.a();
                    return null;
            }
        }
        return typeParameterMapping.getReifiedTypeParametersUsages();
    }

    private final boolean processSpecialEnumFunction(MethodInsnNode insn, InsnList instructions, KT type, Type parameter) {
        AbstractInsnNode next;
        AbstractInsnNode next2 = insn.getNext();
        if (next2 == null || (next = next2.getNext()) == null) {
            return false;
        }
        if (next2.getOpcode() == 1 && next.getOpcode() == 25) {
            MethodInsnNode next3 = next.getNext();
            if (next3 != null && (next3 instanceof MethodInsnNode)) {
                MethodInsnNode methodInsnNode = next3;
                if (Intrinsics.areEqual(methodInsnNode.name, "valueOf")) {
                    instructions.remove(next2);
                    methodInsnNode.owner = parameter.getInternalName();
                    methodInsnNode.desc = InlineIntrinsicsKt.getSpecialEnumFunDescriptor(parameter, true);
                    return true;
                }
            }
        } else {
            if (next2.getOpcode() == 3 && next.getOpcode() == 189) {
                instructions.remove(next2);
                instructions.remove(next);
                instructions.insert(insn, new MethodInsnNode(184, parameter.getInternalName(), "values", InlineIntrinsicsKt.getSpecialEnumFunDescriptor(parameter, false), false));
                return true;
            }
            if (next2.getOpcode() == 1 && next.getOpcode() == 192) {
                instructions.remove(next2);
                instructions.remove(next);
                FieldInsnNode fieldInsnNodeGenerateExternalEntriesForEnumTypeIfNeeded = this.intrinsicsSupport.generateExternalEntriesForEnumTypeIfNeeded(type);
                if (fieldInsnNodeGenerateExternalEntriesForEnumTypeIfNeeded != null) {
                    instructions.insert(insn, fieldInsnNodeGenerateExternalEntriesForEnumTypeIfNeeded);
                } else {
                    instructions.insert(insn, new MethodInsnNode(184, parameter.getInternalName(), "getEntries", Type.getMethodDescriptor(AsmTypes.ENUM_ENTRIES, new Type[0]), false));
                }
                return true;
            }
        }
        return false;
    }

    private final boolean processTypeOf(MethodInsnNode insn, InsnList instructions, KT type) {
        AbstractInsnNode next = insn.getNext();
        if (next == null || next.getOpcode() != 1) {
            return false;
        }
        MethodNode methodNode = new MethodNode(589824, "fake", "()V", (String) null, (String[]) null);
        MaxStackFrameSizeAndLocalsCalculator maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc = InlineCodegenUtilsKt.wrapWithMaxLocalCalc(methodNode);
        TypeOfKt.generateTypeOf(this.typeSystem, new InstructionAdapter(maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc), type, this.intrinsicsSupport);
        maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc.visitInsn(177);
        maxStackFrameSizeAndLocalsCalculatorWrapWithMaxLocalCalc.visitMaxs(-1, -1);
        InsnList insnList = methodNode.instructions;
        insnList.remove(insnList.getLast());
        instructions.insert(insn, methodNode.instructions);
        instructions.remove(next);
        this.maxStackSize = Math.max(this.maxStackSize, methodNode.maxStack);
        return true;
    }

    @JvmStatic
    public static final void putNeedClassReificationMarker(MethodVisitor methodVisitor) {
        INSTANCE.putNeedClassReificationMarker(methodVisitor);
    }

    @JvmStatic
    public static final void putReifiedOperationMarker(OperationKind operationKind, ReificationArgument reificationArgument, InstructionAdapter instructionAdapter) {
        INSTANCE.putReifiedOperationMarker(operationKind, reificationArgument, instructionAdapter);
    }

    private final Type reify(Type type, ReificationArgument reificationArgument) {
        Type type2 = Type.getType(StringsKt.repeat("[", reificationArgument.getArrayDepth()) + type);
        type2.getClass();
        return type2;
    }

    public final ReifiedTypeParametersUsages reifyInstructions(MethodNode node) {
        ReifiedTypeParametersUsages reifiedTypeParametersUsagesProcessReifyMarker;
        node.getClass();
        if (!this.hasReifiedParameters) {
            return new ReifiedTypeParametersUsages();
        }
        this.maxStackSize = 0;
        ReifiedTypeParametersUsages reifiedTypeParametersUsages = new ReifiedTypeParametersUsages();
        MethodInsnNode[] array = node.instructions.toArray();
        array.getClass();
        for (MethodInsnNode methodInsnNode : array) {
            Companion companion = INSTANCE;
            methodInsnNode.getClass();
            if (companion.isOperationReifiedMarker(methodInsnNode) && (reifiedTypeParametersUsagesProcessReifyMarker = processReifyMarker(methodInsnNode, node)) != null) {
                reifiedTypeParametersUsages.mergeAll(reifiedTypeParametersUsagesProcessReifyMarker);
            }
        }
        node.maxStack += this.maxStackSize;
        return reifiedTypeParametersUsages;
    }

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ$\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u0010H\u0002J\u0014\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007b\u0002\b\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007b\u0002\b\u0012J$\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u001cH\u0007b\u0002\b\u0012J.\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$Companion;", Argument.Delimiters.none, "<init>", "()V", "REIFIED_OPERATION_MARKER_METHOD_NAME", Argument.Delimiters.none, "NEED_CLASS_REIFICATION_MARKER_METHOD_NAME", "pluginIntrinsicsMarkerOwner", "pluginIntrinsicsMarkerMethod", "pluginIntrinsicsMarkerSignature", "isOperationReifiedMarker", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "isReifiedMarker", "namePredicate", "Lkotlin/Function1;", "isNeedClassReificationMarker", "Lkotlin/jvm/JvmStatic;", "putNeedClassReificationMarker", Argument.Delimiters.none, "v", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "putReifiedOperationMarker", "operationKind", "Lorg/jetbrains/kotlin/codegen/inline/ReifiedTypeInliner$OperationKind;", "argument", "Lorg/jetbrains/kotlin/codegen/inline/ReificationArgument;", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "putReifiedOperationMarkerIfNeeded", "typeParameter", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "isNullable", "typeSystem", "Lorg/jetbrains/kotlin/types/TypeSystemCommonBackendContext;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static boolean a(String str) {
            str.getClass();
            return Intrinsics.areEqual(str, ReifiedTypeInliner.NEED_CLASS_REIFICATION_MARKER_METHOD_NAME);
        }

        public static boolean b(String str) {
            str.getClass();
            return Intrinsics.areEqual(str, ReifiedTypeInliner.REIFIED_OPERATION_MARKER_METHOD_NAME);
        }

        private final boolean isReifiedMarker(AbstractInsnNode insn, Function1<? super String, Boolean> namePredicate) {
            if (insn.getOpcode() == 184 && (insn instanceof MethodInsnNode)) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) insn;
                if (Intrinsics.areEqual(methodInsnNode.owner, "kotlin/jvm/internal/Intrinsics")) {
                    String str = methodInsnNode.name;
                    str.getClass();
                    if (((Boolean) namePredicate.invoke(str)).booleanValue()) {
                        return true;
                    }
                }
            }
            return false;
        }

        @JvmStatic
        public final boolean isNeedClassReificationMarker(AbstractInsnNode insn) {
            insn.getClass();
            return isReifiedMarker(insn, new Function1() { // from class: vac
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ReifiedTypeInliner.Companion.a((String) obj));
                }
            });
        }

        public final boolean isOperationReifiedMarker(AbstractInsnNode insn) {
            insn.getClass();
            return isReifiedMarker(insn, new Function1() { // from class: uac
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ReifiedTypeInliner.Companion.b((String) obj));
                }
            });
        }

        @JvmStatic
        public final void putNeedClassReificationMarker(MethodVisitor v) {
            v.getClass();
            v.visitMethodInsn(184, "kotlin/jvm/internal/Intrinsics", ReifiedTypeInliner.NEED_CLASS_REIFICATION_MARKER_METHOD_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, new Type[0]), false);
        }

        @JvmStatic
        public final void putReifiedOperationMarker(OperationKind operationKind, ReificationArgument argument, InstructionAdapter v) {
            operationKind.getClass();
            argument.getClass();
            v.getClass();
            v.iconst(operationKind.getId());
            v.visitLdcInsn(argument.asString());
            v.invokestatic("kotlin/jvm/internal/Intrinsics", ReifiedTypeInliner.REIFIED_OPERATION_MARKER_METHOD_NAME, Type.getMethodDescriptor(Type.VOID_TYPE, new Type[]{Type.INT_TYPE, AsmTypes.JAVA_STRING_TYPE}), false);
        }

        public final void putReifiedOperationMarkerIfNeeded(TypeParameterMarker typeParameter, boolean isNullable, OperationKind operationKind, InstructionAdapter v, TypeSystemCommonBackendContext typeSystem) {
            typeParameter.getClass();
            operationKind.getClass();
            v.getClass();
            typeSystem.getClass();
            if (typeSystem.isReified(typeParameter)) {
                String strAsString = typeSystem.getName(typeParameter).asString();
                strAsString.getClass();
                ReifiedTypeInliner.INSTANCE.putReifiedOperationMarker(operationKind, new ReificationArgument(strAsString, isNullable, 0), v);
            }
        }

        private Companion() {
        }
    }

    private final KT reify(KT kt, ReificationArgument reificationArgument) {
        TypeSystemCommonBackendContext typeSystemCommonBackendContext = this.typeSystem;
        KT kt2 = (KT) arrayOf(kt, reificationArgument.getArrayDepth());
        if (reificationArgument.getNullable()) {
            kt2 = (KT) typeSystemCommonBackendContext.makeNullable(kt2);
        }
        kt2.getClass();
        return kt2;
    }
}
