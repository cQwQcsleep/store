package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ \u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J \u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017H\u0016J\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001bH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlinedLambdaRemapper;", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "originalLambdaInternalName", Argument.Delimiters.none, "parent", "methodParams", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "isDefaultBoundCallableReference", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;Lorg/jetbrains/kotlin/codegen/inline/Parameters;Z)V", "canProcess", "fieldOwner", "fieldName", "isFolding", "isMyBoundReceiverForDefaultLambda", "getFieldNameForFolding", "insnNode", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "findField", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "fieldInsnNode", "captured", Argument.Delimiters.none, "isInsideInliningLambda", "()Z", "getFieldForInline", "Lorg/jetbrains/kotlin/codegen/StackValue;", "node", "prefix", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlinedLambdaRemapper extends FieldRemapper {
    private final boolean isDefaultBoundCallableReference;
    private final boolean isInsideInliningLambda;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InlinedLambdaRemapper(String str, FieldRemapper fieldRemapper, Parameters parameters, boolean z) {
        super(str, fieldRemapper, parameters);
        str.getClass();
        fieldRemapper.getClass();
        parameters.getClass();
        this.isDefaultBoundCallableReference = z;
        this.isInsideInliningLambda = true;
    }

    private final boolean isMyBoundReceiverForDefaultLambda(String fieldOwner, String fieldName) {
        return this.isDefaultBoundCallableReference && Intrinsics.areEqual(fieldName, "receiver") && Intrinsics.areEqual(fieldOwner, getOriginalLambdaInternalName());
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public boolean canProcess(String fieldOwner, String fieldName, boolean isFolding) {
        fieldOwner.getClass();
        fieldName.getClass();
        if (isFolding) {
            return isMyBoundReceiverForDefaultLambda(fieldOwner, fieldName) || super.canProcess(fieldOwner, fieldName, true);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public CapturedParamInfo findField(FieldInsnNode fieldInsnNode, Collection<CapturedParamInfo> captured) {
        fieldInsnNode.getClass();
        captured.getClass();
        FieldRemapper fieldRemapper = this.parent;
        fieldRemapper.getClass();
        return fieldRemapper.findField(fieldInsnNode, captured);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public StackValue getFieldForInline(FieldInsnNode node, StackValue prefix) {
        node.getClass();
        FieldRemapper fieldRemapper = this.parent;
        fieldRemapper.getClass();
        return fieldRemapper.getIsRoot() ? super.getFieldForInline(node, prefix) : this.parent.getFieldForInline(node, prefix);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public String getFieldNameForFolding(FieldInsnNode insnNode) {
        insnNode.getClass();
        String str = insnNode.owner;
        str.getClass();
        String str2 = insnNode.name;
        str2.getClass();
        if (isMyBoundReceiverForDefaultLambda(str, str2)) {
            return "$receiver";
        }
        String str3 = insnNode.name;
        str3.getClass();
        return str3;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    /* JADX INFO: renamed from: isInsideInliningLambda, reason: from getter */
    public boolean getIsInsideInliningLambda() {
        return this.isInsideInliningLambda;
    }
}
