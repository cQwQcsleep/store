package org.jetbrains.kotlin.codegen.inline;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.WhenMappingTransformationInfo;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0016J&\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/WhenMappingTransformationInfo;", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "oldClassName", Argument.Delimiters.none, "parentNameGenerator", "Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "alreadyRegenerated", Argument.Delimiters.none, "fieldNode", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;ZLorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;)V", "getOldClassName", "()Ljava/lang/String;", "getFieldNode", "()Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "nameGenerator", "getNameGenerator", "()Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "nameGenerator$delegate", "Lkotlin/Lazy;", "shouldRegenerate", "sameModule", "canRemoveAfterTransformation", "createTransformer", "Lorg/jetbrains/kotlin/codegen/inline/ObjectTransformer;", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "continuationClassName", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WhenMappingTransformationInfo implements TransformationInfo {
    public static final String TRANSFORMED_WHEN_MAPPING_MARKER = "$wm$";
    private final boolean alreadyRegenerated;
    private final FieldInsnNode fieldNode;

    /* JADX INFO: renamed from: nameGenerator$delegate, reason: from kotlin metadata */
    private final Lazy nameGenerator;
    private final String oldClassName;

    public WhenMappingTransformationInfo(String str, final NameGenerator nameGenerator, boolean z, FieldInsnNode fieldInsnNode) {
        str.getClass();
        nameGenerator.getClass();
        fieldInsnNode.getClass();
        this.oldClassName = str;
        this.alreadyRegenerated = z;
        this.fieldNode = fieldInsnNode;
        this.nameGenerator = LazyKt.lazy(new Function0() { // from class: zlf
            public final Object invoke() {
                return WhenMappingTransformationInfo.a(nameGenerator, this);
            }
        });
    }

    public static NameGenerator a(NameGenerator nameGenerator, WhenMappingTransformationInfo whenMappingTransformationInfo) {
        return nameGenerator.subGenerator(false, StringsKt.substringAfterLast$default(StringsKt.substringAfterLast$default(whenMappingTransformationInfo.getOldClassName(), "/", (String) null, 2, (Object) null), TRANSFORMED_WHEN_MAPPING_MARKER, (String) null, 2, (Object) null));
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public boolean canRemoveAfterTransformation() {
        return true;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public ObjectTransformer<?> createTransformer(InliningContext inliningContext, boolean sameModule, String continuationClassName) {
        inliningContext.getClass();
        return new WhenMappingTransformer(this, inliningContext);
    }

    public final FieldInsnNode getFieldNode() {
        return this.fieldNode;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public NameGenerator getNameGenerator() {
        return (NameGenerator) this.nameGenerator.getValue();
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public String getOldClassName() {
        return this.oldClassName;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public boolean shouldRegenerate(boolean sameModule) {
        return (this.alreadyRegenerated || sameModule) ? false : true;
    }
}
