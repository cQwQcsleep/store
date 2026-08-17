package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 -2\u00020\u0001:\u0001-B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000fH\u0014J\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u000fH\u0016J(\u0010\u0016\u001a\u0004\u0018\u00010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0014J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020!2\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020#0&H\u0017b\u0002\b'J\u001c\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\u001a\u001a\u00020!2\b\u0010,\u001a\u0004\u0018\u00010+H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00008\u0006X\u0087\u0004\u0092\u0002\u0002\b\u000b¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010(\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\n¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", Argument.Delimiters.none, "originalLambdaInternalName", Argument.Delimiters.none, "parent", "parameters", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;Lorg/jetbrains/kotlin/codegen/inline/Parameters;)V", "getOriginalLambdaInternalName", "()Ljava/lang/String;", "Lkotlin/jvm/JvmField;", "getParameters", "()Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "isRoot", Argument.Delimiters.none, "()Z", "isInsideInliningLambda", "canProcess", "fieldOwner", "fieldName", "isFolding", "foldFieldAccessChainIfNeeded", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "capturedFieldAccess", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "shouldProcessNonAload0FieldAccessChains", "currentInstruction", Argument.Delimiters.none, "getFieldNameForFolding", "insnNode", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "findField", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "fieldInsnNode", "captured", Argument.Delimiters.none, "Lkotlin/jvm/JvmOverloads;", "newLambdaInternalName", "getNewLambdaInternalName", "getFieldForInline", "Lorg/jetbrains/kotlin/codegen/StackValue;", "prefix", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FieldRemapper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isInsideInliningLambda;
    private final boolean isRoot;
    private final String originalLambdaInternalName;
    private final Parameters parameters;
    public final FieldRemapper parent;

    public FieldRemapper(String str, FieldRemapper fieldRemapper, Parameters parameters) {
        parameters.getClass();
        this.originalLambdaInternalName = str;
        this.parent = fieldRemapper;
        this.parameters = parameters;
        this.isRoot = fieldRemapper == null;
        this.isInsideInliningLambda = fieldRemapper != null ? fieldRemapper.getIsInsideInliningLambda() : false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CapturedParamInfo findField$default(FieldRemapper fieldRemapper, FieldInsnNode fieldInsnNode, Collection collection, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: findField");
            return null;
        }
        if ((i & 2) != 0) {
            collection = fieldRemapper.parameters.getCaptured();
        }
        return fieldRemapper.findField(fieldInsnNode, collection);
    }

    private final AbstractInsnNode foldFieldAccessChainIfNeeded(List<? extends AbstractInsnNode> capturedFieldAccess, int currentInstruction, MethodNode node) {
        FieldRemapper fieldRemapper;
        AbstractInsnNode abstractInsnNodeFoldFieldAccessChainIfNeeded;
        if (currentInstruction < CollectionsKt.getLastIndex(capturedFieldAccess) && (fieldRemapper = this.parent) != null && (abstractInsnNodeFoldFieldAccessChainIfNeeded = fieldRemapper.foldFieldAccessChainIfNeeded(capturedFieldAccess, currentInstruction + 1, node)) != null) {
            return abstractInsnNodeFoldFieldAccessChainIfNeeded;
        }
        AbstractInsnNode abstractInsnNode = capturedFieldAccess.get(currentInstruction);
        abstractInsnNode.getClass();
        FieldInsnNode fieldInsnNode = (FieldInsnNode) abstractInsnNode;
        String str = fieldInsnNode.owner;
        str.getClass();
        String str2 = fieldInsnNode.name;
        str2.getClass();
        if (!canProcess(str, str2, true)) {
            return null;
        }
        fieldInsnNode.name = INSTANCE.foldName(getFieldNameForFolding(fieldInsnNode));
        fieldInsnNode.setOpcode(178);
        MethodInlinerUtilKt.remove(node, new InsnSequence(capturedFieldAccess.get(0), fieldInsnNode));
        return capturedFieldAccess.get(capturedFieldAccess.size() - 1);
    }

    public boolean canProcess(String fieldOwner, String fieldName, boolean isFolding) {
        fieldOwner.getClass();
        fieldName.getClass();
        return Intrinsics.areEqual(fieldOwner, this.originalLambdaInternalName) && InlineCodegenUtilsKt.isCapturedFieldName(fieldName);
    }

    public CapturedParamInfo findField(FieldInsnNode fieldInsnNode, Collection<CapturedParamInfo> captured) {
        fieldInsnNode.getClass();
        captured.getClass();
        for (CapturedParamInfo capturedParamInfo : captured) {
            if (Intrinsics.areEqual(capturedParamInfo.getOriginalFieldName(), fieldInsnNode.name) && Intrinsics.areEqual(capturedParamInfo.getContainingLambdaName(), fieldInsnNode.owner)) {
                return capturedParamInfo;
            }
        }
        return null;
    }

    public StackValue getFieldForInline(FieldInsnNode node, StackValue prefix) {
        node.getClass();
        return MethodInliner.Companion.findCapturedField(node, this).getRemapValue();
    }

    public String getFieldNameForFolding(FieldInsnNode insnNode) {
        insnNode.getClass();
        String str = insnNode.name;
        str.getClass();
        return str;
    }

    public String getNewLambdaInternalName() {
        String str = this.originalLambdaInternalName;
        str.getClass();
        return str;
    }

    public final String getOriginalLambdaInternalName() {
        return this.originalLambdaInternalName;
    }

    public final Parameters getParameters() {
        return this.parameters;
    }

    /* JADX INFO: renamed from: isInsideInliningLambda, reason: from getter */
    public boolean getIsInsideInliningLambda() {
        return this.isInsideInliningLambda;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    public boolean shouldProcessNonAload0FieldAccessChains() {
        return false;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper$Companion;", Argument.Delimiters.none, "<init>", "()V", "foldName", Argument.Delimiters.none, "fieldName", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String foldName(String fieldName) {
            fieldName.getClass();
            return InlineCodegenUtilsKt.CAPTURED_FIELD_FOLD_PREFIX + fieldName;
        }

        private Companion() {
        }
    }

    public final CapturedParamInfo findField(FieldInsnNode fieldInsnNode) {
        fieldInsnNode.getClass();
        return findField$default(this, fieldInsnNode, null, 2, null);
    }

    public final AbstractInsnNode foldFieldAccessChainIfNeeded(List<? extends AbstractInsnNode> capturedFieldAccess, MethodNode node) {
        capturedFieldAccess.getClass();
        node.getClass();
        if (capturedFieldAccess.size() == 1) {
            return null;
        }
        return foldFieldAccessChainIfNeeded(capturedFieldAccess, 1, node);
    }
}
