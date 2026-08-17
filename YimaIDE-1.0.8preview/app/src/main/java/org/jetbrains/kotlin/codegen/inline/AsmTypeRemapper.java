package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.commons.Remapper;
import org.jetbrains.org.objectweb.asm.commons.SignatureRemapper;
import org.jetbrains.org.objectweb.asm.signature.SignatureReader;
import org.jetbrains.org.objectweb.asm.signature.SignatureVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/AsmTypeRemapper;", "Lorg/jetbrains/org/objectweb/asm/commons/Remapper;", "typeRemapper", "Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;Lorg/jetbrains/kotlin/codegen/inline/InlineResult;)V", "getTypeRemapper", "()Lorg/jetbrains/kotlin/codegen/inline/TypeRemapper;", "getResult", "()Lorg/jetbrains/kotlin/codegen/inline/InlineResult;", "map", Argument.Delimiters.none, ModuleXmlParser.TYPE, "createSignatureRemapper", "Lorg/jetbrains/org/objectweb/asm/signature/SignatureVisitor;", "v", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AsmTypeRemapper extends Remapper {
    private final InlineResult result;
    private final TypeRemapper typeRemapper;

    public AsmTypeRemapper(TypeRemapper typeRemapper, InlineResult inlineResult) {
        typeRemapper.getClass();
        inlineResult.getClass();
        this.typeRemapper = typeRemapper;
        this.result = inlineResult;
    }

    public SignatureVisitor createSignatureRemapper(SignatureVisitor v) {
        return new SignatureRemapper(v, this) { // from class: org.jetbrains.kotlin.codegen.inline.AsmTypeRemapper.createSignatureRemapper.1
            final /* synthetic */ SignatureVisitor $v;
            final /* synthetic */ AsmTypeRemapper this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(v, this);
                this.$v = v;
                this.this$0 = this;
            }

            public void visitFormalTypeParameter(String name) {
                name.getClass();
                this.this$0.getTypeRemapper().registerTypeParameter(name);
                super.visitFormalTypeParameter(name);
            }

            public void visitTypeVariable(String name) {
                name.getClass();
                TypeParameterMapping<?> typeParameterMappingMapTypeParameter = this.this$0.getTypeRemapper().mapTypeParameter(name);
                if (typeParameterMappingMapTypeParameter == null) {
                    super.visitTypeVariable(name);
                    return;
                }
                if (typeParameterMappingMapTypeParameter.getIsReified()) {
                    this.this$0.getResult().getReifiedTypeParametersUsages().mergeAll(typeParameterMappingMapTypeParameter.getReifiedTypeParametersUsages());
                }
                new SignatureReader(typeParameterMappingMapTypeParameter.getSignature()).acceptType(this.$v);
            }
        };
    }

    public final InlineResult getResult() {
        return this.result;
    }

    public final TypeRemapper getTypeRemapper() {
        return this.typeRemapper;
    }

    public String map(String type) {
        type.getClass();
        return this.typeRemapper.map(type);
    }
}
