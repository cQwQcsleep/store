package org.jetbrains.kotlin.codegen.inline;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/RegeneratedClassContext;", "Lorg/jetbrains/kotlin/codegen/inline/InliningContext;", "parent", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "nameGenerator", "Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;", "typeRemapper", "Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", "lambdaInfo", "Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;", "callSiteInfo", "Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "transformationInfo", "Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/InliningContext;Lorg/jetbrains/kotlin/codegen/state/GenerationState;Lorg/jetbrains/kotlin/codegen/inline/NameGenerator;Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;Lorg/jetbrains/kotlin/codegen/inline/LambdaInfo;Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;)V", "getCallSiteInfo", "()Lorg/jetbrains/kotlin/codegen/inline/InlineCallSiteInfo;", "getTransformationInfo", "()Lorg/jetbrains/kotlin/codegen/inline/TransformationInfo;", "continuationBuilders", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "getContinuationBuilders", "()Ljava/util/Map;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RegeneratedClassContext extends InliningContext {
    private final InlineCallSiteInfo callSiteInfo;
    private final Map<String, ClassBuilder> continuationBuilders;
    private final TransformationInfo transformationInfo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegeneratedClassContext(InliningContext inliningContext, GenerationState generationState, NameGenerator nameGenerator, TypeRemapper typeRemapper, LambdaInfo lambdaInfo, InlineCallSiteInfo inlineCallSiteInfo, TransformationInfo transformationInfo) {
        super(inliningContext, generationState, nameGenerator, typeRemapper, lambdaInfo, true, null, inliningContext.getTypeMapper());
        inliningContext.getClass();
        generationState.getClass();
        nameGenerator.getClass();
        typeRemapper.getClass();
        inlineCallSiteInfo.getClass();
        transformationInfo.getClass();
        this.callSiteInfo = inlineCallSiteInfo;
        this.transformationInfo = transformationInfo;
        this.continuationBuilders = new HashMap();
    }

    @Override // org.jetbrains.kotlin.codegen.inline.InliningContext
    public InlineCallSiteInfo getCallSiteInfo() {
        return this.callSiteInfo;
    }

    public final Map<String, ClassBuilder> getContinuationBuilders() {
        return this.continuationBuilders;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.InliningContext
    public TransformationInfo getTransformationInfo() {
        return this.transformationInfo;
    }
}
