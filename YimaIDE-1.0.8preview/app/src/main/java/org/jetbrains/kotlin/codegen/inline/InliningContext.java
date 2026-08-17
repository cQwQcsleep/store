package org.jetbrains.kotlin.codegen.inline;

import java.util.HashSet;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapperBase;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000e\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u000207J\u000e\u0010;\u001a\u00020<2\u0006\u0010:\u001a\u000207J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ4\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020\u00062\u0014\u0010A\u001a\u0010\u0012\u0004\u0012\u000207\u0012\u0006\u0012\u0004\u0018\u0001070B2\u0006\u0010C\u001a\u00020D2\u0006\u0010*\u001a\u00020+JN\u0010E\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u00062\u0016\b\u0002\u0010F\u001a\u0010\u0012\u0004\u0012\u000207\u0012\u0006\u0012\u0004\u0018\u0001070G2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007b\u0002\bHR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b#\u0010\u001eR\u0011\u0010$\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b%\u0010\u001eR\u001a\u0010&\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001e\"\u0004\b(\u0010)R\u0016\u0010*\u001a\u0004\u0018\u00010+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001e\"\u0004\b/\u0010)R\u0011\u00100\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b0\u0010\u001eR\u0011\u00101\u001a\u0002028F¢\u0006\u0006\u001a\u0004\b3\u00104R\u001e\u00105\u001a\u0012\u0012\u0004\u0012\u00020706j\b\u0012\u0004\u0012\u000207`8X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010C\u001a\u00020D8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010J¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", Argument.Delimiters.none, "parent", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "nameGenerator", "Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "typeRemapper", "Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", "lambdaInfo", "Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "classRegeneration", Argument.Delimiters.none, "inlineScopesGenerator", "Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;", "typeMapper", "Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/InliningContext;Lorg/jetbrains/kotlin/codegen/state/GenerationState;Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;ZLorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;)V", "getParent", "()Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "getState", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "getNameGenerator", "()Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "getTypeRemapper", "()Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", "getLambdaInfo", "()Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "getClassRegeneration", "()Z", "getInlineScopesGenerator", "()Lorg/jetbrains/kotlin/codegen/inline/InlineScopesGenerator;", "getTypeMapper", "()Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapperBase;", "isInliningLambda", "shouldReifyTypeParametersInObjects", "getShouldReifyTypeParametersInObjects", "generateAssertField", "getGenerateAssertField", "setGenerateAssertField", "(Z)V", "transformationInfo", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "getTransformationInfo", "()Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "isContinuation", "setContinuation", "isRoot", "root", "Lorg/jetbrains/kotlin/codegen/inline/RootInliningContext;", "getRoot", "()Lorg/jetbrains/kotlin/codegen/inline/RootInliningContext;", "regeneratedAnonymousObjects", "Ljava/util/HashSet;", Argument.Delimiters.none, "Lkotlin/collections/HashSet;", "isRegeneratedAnonymousObject", "internalName", "recordRegeneratedAnonymousObject", Argument.Delimiters.none, "subInlineLambda", "subInlineWithClassRegeneration", "Lorg/jetbrains/kotlin/codegen/inline/RegeneratedClassContext;", "generator", "newTypeMappings", Argument.Delimiters.none, "callSiteInfo", "Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "subInline", "additionalTypeMappings", Argument.Delimiters.none, "Lkotlin/jvm/JvmOverloads;", "getCallSiteInfo", "()Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class InliningContext {
    private final boolean classRegeneration;
    private boolean generateAssertField;
    private final InlineScopesGenerator inlineScopesGenerator;
    private boolean isContinuation;
    private final LambdaInfo lambdaInfo;
    private final NameGenerator nameGenerator;
    private final InliningContext parent;
    private final HashSet<String> regeneratedAnonymousObjects;
    private final GenerationState state;
    private final KotlinTypeMapperBase typeMapper;
    private final TypeRemapper typeRemapper;

    public InliningContext(InliningContext inliningContext, GenerationState generationState, NameGenerator nameGenerator, TypeRemapper typeRemapper, LambdaInfo lambdaInfo, boolean z, InlineScopesGenerator inlineScopesGenerator, KotlinTypeMapperBase kotlinTypeMapperBase) {
        generationState.getClass();
        nameGenerator.getClass();
        typeRemapper.getClass();
        kotlinTypeMapperBase.getClass();
        this.parent = inliningContext;
        this.state = generationState;
        this.nameGenerator = nameGenerator;
        this.typeRemapper = typeRemapper;
        this.lambdaInfo = lambdaInfo;
        this.classRegeneration = z;
        this.inlineScopesGenerator = inlineScopesGenerator;
        this.typeMapper = kotlinTypeMapperBase;
        this.regeneratedAnonymousObjects = new HashSet<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ InliningContext subInline$default(InliningContext inliningContext, NameGenerator nameGenerator, Map map, LambdaInfo lambdaInfo, boolean z, InlineScopesGenerator inlineScopesGenerator, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: subInline");
            return null;
        }
        if ((i & 2) != 0) {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if ((i & 4) != 0) {
            lambdaInfo = inliningContext.lambdaInfo;
        }
        LambdaInfo lambdaInfo2 = lambdaInfo;
        if ((i & 8) != 0) {
            z = inliningContext.classRegeneration;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            inlineScopesGenerator = null;
        }
        return inliningContext.subInline(nameGenerator, map2, lambdaInfo2, z2, inlineScopesGenerator);
    }

    public InlineCallSiteInfo getCallSiteInfo() {
        InliningContext inliningContext = this.parent;
        inliningContext.getClass();
        return inliningContext.getCallSiteInfo();
    }

    public final boolean getClassRegeneration() {
        return this.classRegeneration;
    }

    public final boolean getGenerateAssertField() {
        return this.generateAssertField;
    }

    public final InlineScopesGenerator getInlineScopesGenerator() {
        return this.inlineScopesGenerator;
    }

    public final LambdaInfo getLambdaInfo() {
        return this.lambdaInfo;
    }

    public final NameGenerator getNameGenerator() {
        return this.nameGenerator;
    }

    public final InliningContext getParent() {
        return this.parent;
    }

    public final RootInliningContext getRoot() {
        if (isRoot()) {
            return (RootInliningContext) this;
        }
        InliningContext inliningContext = this.parent;
        inliningContext.getClass();
        return inliningContext.getRoot();
    }

    public final boolean getShouldReifyTypeParametersInObjects() {
        LambdaInfo lambdaInfo = this.lambdaInfo;
        return lambdaInfo == null || (lambdaInfo instanceof DefaultLambda);
    }

    public final GenerationState getState() {
        return this.state;
    }

    public TransformationInfo getTransformationInfo() {
        return null;
    }

    public final KotlinTypeMapperBase getTypeMapper() {
        return this.typeMapper;
    }

    public final TypeRemapper getTypeRemapper() {
        return this.typeRemapper;
    }

    /* JADX INFO: renamed from: isContinuation, reason: from getter */
    public final boolean getIsContinuation() {
        return this.isContinuation;
    }

    public final boolean isInliningLambda() {
        return this.lambdaInfo != null;
    }

    public final boolean isRegeneratedAnonymousObject(String internalName) {
        internalName.getClass();
        if (this.regeneratedAnonymousObjects.contains(internalName)) {
            return true;
        }
        InliningContext inliningContext = this.parent;
        return inliningContext != null && inliningContext.isRegeneratedAnonymousObject(internalName);
    }

    public final boolean isRoot() {
        return this.parent == null;
    }

    public final void recordRegeneratedAnonymousObject(String internalName) {
        internalName.getClass();
        this.regeneratedAnonymousObjects.add(internalName);
    }

    public final void setContinuation(boolean z) {
        this.isContinuation = z;
    }

    public final void setGenerateAssertField(boolean z) {
        this.generateAssertField = z;
    }

    public final InliningContext subInline(NameGenerator generator, Map<String, String> additionalTypeMappings, LambdaInfo lambdaInfo, boolean classRegeneration, InlineScopesGenerator inlineScopesGenerator) {
        boolean z;
        boolean z2;
        generator.getClass();
        additionalTypeMappings.getClass();
        if (lambdaInfo != null) {
            z2 = false;
            z = true;
        } else {
            z = false;
            z2 = false;
        }
        GenerationState generationState = this.state;
        TypeRemapper.Companion companion = TypeRemapper.INSTANCE;
        TypeRemapper typeRemapper = this.typeRemapper;
        if (z && !isInliningLambda()) {
            z2 = true;
        }
        return new InliningContext(this, generationState, generator, companion.createFrom(typeRemapper, additionalTypeMappings, z2), lambdaInfo, classRegeneration, inlineScopesGenerator, this.typeMapper);
    }

    public final InliningContext subInlineLambda(LambdaInfo lambdaInfo) {
        lambdaInfo.getClass();
        NameGenerator nameGeneratorSubGenerator = this.nameGenerator.subGenerator("lambda");
        nameGeneratorSubGenerator.getClass();
        return subInline(nameGeneratorSubGenerator, MapsKt.hashMapOf(new Pair[]{TuplesKt.to(lambdaInfo.getLambdaClassType().getInternalName(), null)}), lambdaInfo, false, this.inlineScopesGenerator);
    }

    public final RegeneratedClassContext subInlineWithClassRegeneration(NameGenerator generator, Map<String, String> newTypeMappings, InlineCallSiteInfo callSiteInfo, TransformationInfo transformationInfo) {
        generator.getClass();
        newTypeMappings.getClass();
        callSiteInfo.getClass();
        transformationInfo.getClass();
        return new RegeneratedClassContext(this, this.state, generator, TypeRemapper.Companion.createFrom$default(TypeRemapper.INSTANCE, this.typeRemapper, newTypeMappings, false, 4, null), this.lambdaInfo, callSiteInfo, transformationInfo);
    }

    public final InliningContext subInline(NameGenerator nameGenerator, Map<String, String> map) {
        nameGenerator.getClass();
        map.getClass();
        return subInline$default(this, nameGenerator, map, null, false, null, 28, null);
    }

    public final InliningContext subInline(NameGenerator nameGenerator, Map<String, String> map, LambdaInfo lambdaInfo) {
        nameGenerator.getClass();
        map.getClass();
        return subInline$default(this, nameGenerator, map, lambdaInfo, false, null, 24, null);
    }

    public final InliningContext subInline(NameGenerator nameGenerator, Map<String, String> map, LambdaInfo lambdaInfo, boolean z) {
        nameGenerator.getClass();
        map.getClass();
        return subInline$default(this, nameGenerator, map, lambdaInfo, z, null, 16, null);
    }

    public final InliningContext subInline(NameGenerator nameGenerator) {
        nameGenerator.getClass();
        return subInline$default(this, nameGenerator, null, null, false, null, 30, null);
    }
}
