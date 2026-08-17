package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.StackValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0001\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ \u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\fH\u0016J\u0018\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\fH\u0002J \u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eH\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u001c\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u001c2\b\u0010$\u001a\u0004\u0018\u00010\"H\u0016R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/RegeneratedLambdaFieldRemapper;", "Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;", "originalLambdaInternalName", Argument.Delimiters.none, "newLambdaInternalName", "parameters", "Lorg/jetbrains/kotlin/codegen/inline/Parameters;", "recapturedLambdas", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "remapper", "isConstructor", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/inline/Parameters;Ljava/util/Map;Lorg/jetbrains/kotlin/codegen/inline/FieldRemapper;Z)V", "getNewLambdaInternalName", "()Ljava/lang/String;", "getRecapturedLambdas", "()Ljava/util/Map;", "canProcess", "fieldOwner", "fieldName", "isFolding", "isRecapturedLambdaType", "owner", "findField", "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamInfo;", "fieldInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "captured", Argument.Delimiters.none, "shouldProcessNonAload0FieldAccessChains", "findFieldInSuper", "getFieldForInline", "Lorg/jetbrains/kotlin/codegen/StackValue;", "node", "prefix", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RegeneratedLambdaFieldRemapper extends FieldRemapper {
    private final boolean isConstructor;
    private final String newLambdaInternalName;
    private final Map<String, LambdaInfo> recapturedLambdas;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public RegeneratedLambdaFieldRemapper(String str, String str2, Parameters parameters, Map<String, ? extends LambdaInfo> map, FieldRemapper fieldRemapper, boolean z) {
        super(str, fieldRemapper, parameters);
        str.getClass();
        str2.getClass();
        parameters.getClass();
        map.getClass();
        fieldRemapper.getClass();
        this.newLambdaInternalName = str2;
        this.recapturedLambdas = map;
        this.isConstructor = z;
    }

    private final CapturedParamInfo findFieldInSuper(FieldInsnNode fieldInsnNode) {
        return super.findField(fieldInsnNode, getParameters().getCaptured());
    }

    private final boolean isRecapturedLambdaType(String owner, boolean isFolding) {
        if (this.recapturedLambdas.containsKey(owner)) {
            return isFolding || !(this.parent instanceof InlinedLambdaRemapper);
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public boolean canProcess(String fieldOwner, String fieldName, boolean isFolding) {
        fieldOwner.getClass();
        fieldName.getClass();
        return super.canProcess(fieldOwner, fieldName, isFolding) || isRecapturedLambdaType(fieldOwner, isFolding);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public CapturedParamInfo findField(FieldInsnNode fieldInsnNode, Collection<CapturedParamInfo> captured) {
        fieldInsnNode.getClass();
        captured.getClass();
        String str = fieldInsnNode.owner;
        str.getClass();
        String str2 = fieldInsnNode.name;
        str2.getClass();
        if (canProcess(str, str2, false)) {
            return findFieldInSuper(fieldInsnNode);
        }
        FieldRemapper fieldRemapper = this.parent;
        fieldRemapper.getClass();
        return FieldRemapper.findField$default(fieldRemapper, fieldInsnNode, null, 2, null);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public StackValue getFieldForInline(FieldInsnNode node, StackValue prefix) {
        boolean z;
        Type type;
        node.getClass();
        String str = node.name;
        str.getClass();
        StringsKt.startsWith$default(str, InlineCodegenUtilsKt.CAPTURED_FIELD_FOLD_PREFIX, false, 2, (Object) null);
        if (Intrinsics.areEqual(str, "$$$this")) {
            Intrinsics.areEqual(getOriginalLambdaInternalName(), node.owner);
            return new StackValue.Local(0, AsmTypes.OBJECT_TYPE, null);
        }
        CapturedParamInfo capturedParamInfoFindFieldInSuper = findFieldInSuper(new FieldInsnNode(node.getOpcode(), node.owner, StringsKt.substringAfter$default(str, InlineCodegenUtilsKt.CAPTURED_FIELD_FOLD_PREFIX, (String) null, 2, (Object) null), node.desc));
        if (capturedParamInfoFindFieldInSuper == null) {
            String originalLambdaInternalName = getOriginalLambdaInternalName();
            FieldRemapper fieldRemapper = this.parent;
            fieldRemapper.getClass();
            String originalLambdaInternalName2 = fieldRemapper.getOriginalLambdaInternalName();
            originalLambdaInternalName2.getClass();
            capturedParamInfoFindFieldInSuper = findFieldInSuper(new FieldInsnNode(178, originalLambdaInternalName, "this$0", Type.getObjectType(originalLambdaInternalName2).getDescriptor()));
            if (capturedParamInfoFindFieldInSuper != null) {
                z = true;
            } else {
                z = false;
                capturedParamInfoFindFieldInSuper = null;
            }
            if (capturedParamInfoFindFieldInSuper == null) {
                mu3.a("Couldn't find captured this ", getOriginalLambdaInternalName(), " for ", str);
                return null;
            }
        } else {
            z = false;
        }
        if (capturedParamInfoFindFieldInSuper.getIsSkipped() && (capturedParamInfoFindFieldInSuper.getFunctionalArgument() instanceof LambdaInfo)) {
            FieldRemapper fieldRemapper2 = this.parent;
            fieldRemapper2.getClass();
            FieldRemapper fieldRemapper3 = fieldRemapper2.parent;
            fieldRemapper3.getClass();
            type = Type.getObjectType(fieldRemapper3.getNewLambdaInternalName());
        } else {
            type = capturedParamInfoFindFieldInSuper.getType();
        }
        Type objectType = Type.getObjectType(getNewLambdaInternalName());
        String newFieldName = capturedParamInfoFindFieldInSuper.getNewFieldName();
        if (prefix == null) {
            prefix = new StackValue.Local(0, AsmTypes.OBJECT_TYPE, null);
        }
        StackValue.Field field = new StackValue.Field(type, objectType, newFieldName, prefix);
        if (!z) {
            return field;
        }
        FieldRemapper fieldRemapper4 = this.parent;
        fieldRemapper4.getClass();
        return fieldRemapper4.getFieldForInline(node, field);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    public String getNewLambdaInternalName() {
        return this.newLambdaInternalName;
    }

    public final Map<String, LambdaInfo> getRecapturedLambdas() {
        return this.recapturedLambdas;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.FieldRemapper
    /* JADX INFO: renamed from: shouldProcessNonAload0FieldAccessChains, reason: from getter */
    public boolean getIsConstructor() {
        return this.isConstructor;
    }
}
