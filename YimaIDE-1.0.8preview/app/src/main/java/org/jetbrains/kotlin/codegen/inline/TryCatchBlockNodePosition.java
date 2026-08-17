package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u000fH\u0096\u0001J\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0012\u0010\u0014\u001a\u00020\u0015X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0015X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0012\u0010\u001a\u001a\u00020\u0015X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodePosition;", "Lorg/jetbrains/kotlin/codegen/inline/IntervalWithHandler;", "nodeInfo", "Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;", "position", "Lorg/jetbrains/kotlin/codegen/inline/TryCatchPosition;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;Lorg/jetbrains/kotlin/codegen/inline/TryCatchPosition;)V", "getNodeInfo", "()Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;", "getPosition", "()Lorg/jetbrains/kotlin/codegen/inline/TryCatchPosition;", "setPosition", "(Lorg/jetbrains/kotlin/codegen/inline/TryCatchPosition;)V", "isEmpty", Argument.Delimiters.none, "verify", Argument.Delimiters.none, "processor", "Lorg/jetbrains/kotlin/codegen/inline/CoveringTryCatchNodeProcessor;", "endLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getEndLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "handler", "getHandler", "startLabel", "getStartLabel", ModuleXmlParser.TYPE, Argument.Delimiters.none, "getType", "()Ljava/lang/String;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TryCatchBlockNodePosition implements IntervalWithHandler {
    private final TryCatchBlockNodeInfo nodeInfo;
    private TryCatchPosition position;

    public TryCatchBlockNodePosition(TryCatchBlockNodeInfo tryCatchBlockNodeInfo, TryCatchPosition tryCatchPosition) {
        tryCatchBlockNodeInfo.getClass();
        tryCatchPosition.getClass();
        this.nodeInfo = tryCatchBlockNodeInfo;
        this.position = tryCatchPosition;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getEndLabel() {
        return this.nodeInfo.getEndLabel();
    }

    @Override // org.jetbrains.kotlin.codegen.inline.IntervalWithHandler
    public LabelNode getHandler() {
        return this.nodeInfo.getHandler();
    }

    public final TryCatchBlockNodeInfo getNodeInfo() {
        return this.nodeInfo;
    }

    public final TryCatchPosition getPosition() {
        return this.position;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getStartLabel() {
        return this.nodeInfo.getStartLabel();
    }

    @Override // org.jetbrains.kotlin.codegen.inline.IntervalWithHandler
    public String getType() {
        return this.nodeInfo.getType();
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public boolean isEmpty() {
        return this.nodeInfo.isEmpty();
    }

    public final void setPosition(TryCatchPosition tryCatchPosition) {
        tryCatchPosition.getClass();
        this.position = tryCatchPosition;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public void verify(CoveringTryCatchNodeProcessor processor) {
        processor.getClass();
        this.nodeInfo.verify(processor);
    }
}
