package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.AnonymousObjectTransformationInfo;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001Ba\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0012B1\b\u0016\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0015J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\u0016J\b\u00101\u001a\u00020\u0005H\u0016J&\u00102\u001a\u0006\u0012\u0002\b\u0003032\u0006\u00104\u001a\u0002052\u0006\u00100\u001a\u00020\u00052\b\u00106\u001a\u0004\u0018\u00010\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\u00020\u000f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001f\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\"R \u0010#\u001a\b\u0012\u0004\u0012\u00020%0$X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R&\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020+0\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0019\"\u0004\b-\u0010.¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/AnonymousObjectTransformationInfo;", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "oldClassName", Argument.Delimiters.none, "needReification", Argument.Delimiters.none, "functionalArguments", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "capturedOuterRegenerated", "alreadyRegenerated", "constructorDesc", "isStaticOrigin", "parentNameGenerator", "Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "capturesAnonymousObjectThatMustBeRegenerated", "<init>", "(Ljava/lang/String;ZLjava/util/Map;ZZLjava/lang/String;ZLorg/jetbrains/kotlin/codegen/inline/NameGenerator;Z)V", "ownerInternalName", "nameGenerator", "(Ljava/lang/String;ZZZLorg/jetbrains/kotlin/codegen/inline/NameGenerator;)V", "getOldClassName", "()Ljava/lang/String;", "getFunctionalArguments", "()Ljava/util/Map;", "getConstructorDesc", "getNameGenerator", "()Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "nameGenerator$delegate", "Lkotlin/Lazy;", "newConstructorDescriptor", "getNewConstructorDescriptor", "setNewConstructorDescriptor", "(Ljava/lang/String;)V", "allRecapturedParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/CapturedParamDesc;", "getAllRecapturedParameters", "()Ljava/util/List;", "setAllRecapturedParameters", "(Ljava/util/List;)V", "capturedLambdasToInline", "Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "getCapturedLambdasToInline", "setCapturedLambdasToInline", "(Ljava/util/Map;)V", "shouldRegenerate", "sameModule", "canRemoveAfterTransformation", "createTransformer", "Lorg/jetbrains/kotlin/codegen/inline/ObjectTransformer;", "inliningContext", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "continuationClassName", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnonymousObjectTransformationInfo implements TransformationInfo {
    public List<CapturedParamDesc> allRecapturedParameters;
    private final boolean alreadyRegenerated;
    public Map<String, ? extends LambdaInfo> capturedLambdasToInline;
    private final boolean capturedOuterRegenerated;
    private final boolean capturesAnonymousObjectThatMustBeRegenerated;
    private final String constructorDesc;
    private final Map<Integer, FunctionalArgument> functionalArguments;
    private final boolean isStaticOrigin;

    /* JADX INFO: renamed from: nameGenerator$delegate, reason: from kotlin metadata */
    private final Lazy nameGenerator;
    private final boolean needReification;
    public String newConstructorDescriptor;
    private final String oldClassName;

    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousObjectTransformationInfo(String str, boolean z, Map<Integer, ? extends FunctionalArgument> map, boolean z2, boolean z3, String str2, boolean z4, final NameGenerator nameGenerator, boolean z5) {
        str.getClass();
        map.getClass();
        nameGenerator.getClass();
        this.oldClassName = str;
        this.needReification = z;
        this.functionalArguments = map;
        this.capturedOuterRegenerated = z2;
        this.alreadyRegenerated = z3;
        this.constructorDesc = str2;
        this.isStaticOrigin = z4;
        this.capturesAnonymousObjectThatMustBeRegenerated = z5;
        this.nameGenerator = LazyKt.lazy(new Function0() { // from class: la0
            public final Object invoke() {
                return AnonymousObjectTransformationInfo.a(nameGenerator);
            }
        });
    }

    public static NameGenerator a(NameGenerator nameGenerator) {
        return nameGenerator.subGenerator(true, null);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public boolean canRemoveAfterTransformation() {
        return !this.isStaticOrigin;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public ObjectTransformer<?> createTransformer(InliningContext inliningContext, boolean sameModule, String continuationClassName) {
        inliningContext.getClass();
        return new AnonymousObjectTransformer(this, inliningContext, sameModule, continuationClassName);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final List<CapturedParamDesc> getAllRecapturedParameters() throws UninitializedPropertyAccessException {
        List<CapturedParamDesc> list = this.allRecapturedParameters;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("allRecapturedParameters");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Map<String, LambdaInfo> getCapturedLambdasToInline() throws UninitializedPropertyAccessException {
        Map map = this.capturedLambdasToInline;
        if (map != null) {
            return map;
        }
        Intrinsics.throwUninitializedPropertyAccessException("capturedLambdasToInline");
        return null;
    }

    public final String getConstructorDesc() {
        return this.constructorDesc;
    }

    public final Map<Integer, FunctionalArgument> getFunctionalArguments() {
        return this.functionalArguments;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public NameGenerator getNameGenerator() {
        return (NameGenerator) this.nameGenerator.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final String getNewConstructorDescriptor() throws UninitializedPropertyAccessException {
        String str = this.newConstructorDescriptor;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("newConstructorDescriptor");
        return null;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public String getOldClassName() {
        return this.oldClassName;
    }

    public final void setAllRecapturedParameters(List<CapturedParamDesc> list) {
        list.getClass();
        this.allRecapturedParameters = list;
    }

    public final void setCapturedLambdasToInline(Map<String, ? extends LambdaInfo> map) {
        map.getClass();
        this.capturedLambdasToInline = map;
    }

    public final void setNewConstructorDescriptor(String str) {
        str.getClass();
        this.newConstructorDescriptor = str;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.TransformationInfo
    public boolean shouldRegenerate(boolean sameModule) {
        if (this.alreadyRegenerated || !sameModule || this.capturedOuterRegenerated || this.needReification || this.capturesAnonymousObjectThatMustBeRegenerated) {
            return true;
        }
        Collection<FunctionalArgument> collectionValues = this.functionalArguments.values();
        if ((collectionValues instanceof Collection) && collectionValues.isEmpty()) {
            return false;
        }
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            if (((FunctionalArgument) it.next()) != NonInlineArgumentForInlineSuspendParameter.INLINE_LAMBDA_AS_VARIABLE) {
                return true;
            }
        }
        return false;
    }

    public /* synthetic */ AnonymousObjectTransformationInfo(String str, boolean z, Map map, boolean z2, boolean z3, String str2, boolean z4, NameGenerator nameGenerator, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, map, z2, z3, str2, z4, nameGenerator, (i & 256) != 0 ? false : z5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AnonymousObjectTransformationInfo(String str, boolean z, boolean z2, boolean z3, NameGenerator nameGenerator) {
        this(str, z, new HashMap(), false, z2, null, z3, nameGenerator, false, 256, null);
        str.getClass();
        nameGenerator.getClass();
    }
}
