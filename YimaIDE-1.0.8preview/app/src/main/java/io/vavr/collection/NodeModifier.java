package io.vavr.collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
interface NodeModifier {
    public static final NodeModifier COPY_NODE = new NodeModifier() { // from class: io.vavr.collection.f2
        @Override // io.vavr.collection.NodeModifier
        public final Object apply(Object obj, int i) {
            return ArrayType.obj().copy(obj, i + 1);
        }
    };
    public static final NodeModifier IDENTITY = new NodeModifier() { // from class: io.vavr.collection.g2
        @Override // io.vavr.collection.NodeModifier
        public final Object apply(Object obj, int i) {
            return NodeModifier.a(obj, i);
        }
    };

    static /* synthetic */ Object a(Object obj, int i) {
        return obj;
    }

    Object apply(Object obj, int i);
}
