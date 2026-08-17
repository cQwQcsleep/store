package com.android.tools.r8.experimental.graphinfo;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class AnnotationGraphNode extends GraphNode {
    private final GraphNode c;
    private final ClassGraphNode d;

    public AnnotationGraphNode(GraphNode graphNode, ClassGraphNode classGraphNode) {
        super(graphNode.isLibraryNode());
        this.c = graphNode;
        this.d = classGraphNode;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotationGraphNode)) {
            return false;
        }
        AnnotationGraphNode annotationGraphNode = (AnnotationGraphNode) obj;
        return this.c.equals(annotationGraphNode.c) && this.d.equals(annotationGraphNode.d);
    }

    public GraphNode getAnnotatedNode() {
        return this.c;
    }

    public ClassGraphNode getAnnotationClassNode() {
        return this.d;
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public int hashCode() {
        return Objects.hash(this.c, this.d);
    }

    @Override // com.android.tools.r8.experimental.graphinfo.GraphNode
    public String toString() {
        return "annotated " + this.c.toString();
    }
}
