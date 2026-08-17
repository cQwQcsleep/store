package org.eclipse.jdt.internal.compiler.env;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface ITypeAnnotationWalker {
    public static final IBinaryAnnotation[] NO_ANNOTATIONS = new IBinaryAnnotation[0];
    public static final ITypeAnnotationWalker EMPTY_ANNOTATION_WALKER = new ITypeAnnotationWalker() { // from class: org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker.1
        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public IBinaryAnnotation[] getAnnotationsAtCursor(int i, boolean z) {
            return ITypeAnnotationWalker.NO_ANNOTATIONS;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toField() {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toMethodParameter(short s) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toMethodReturn() {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toNextArrayDimension() {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toNextNestedType() {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toReceiver() {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toSupertype(short s, char[] cArr) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toThrows(int i) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toTypeArgument(int i) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toTypeBound(short s) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toTypeParameter(boolean z, int i) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toTypeParameterBounds(boolean z, int i) {
            return this;
        }

        @Override // org.eclipse.jdt.internal.compiler.env.ITypeAnnotationWalker
        public ITypeAnnotationWalker toWildcardBound() {
            return this;
        }
    };

    IBinaryAnnotation[] getAnnotationsAtCursor(int i, boolean z);

    ITypeAnnotationWalker toField();

    ITypeAnnotationWalker toMethodParameter(short s);

    ITypeAnnotationWalker toMethodReturn();

    ITypeAnnotationWalker toNextArrayDimension();

    ITypeAnnotationWalker toNextNestedType();

    ITypeAnnotationWalker toReceiver();

    ITypeAnnotationWalker toSupertype(short s, char[] cArr);

    ITypeAnnotationWalker toThrows(int i);

    ITypeAnnotationWalker toTypeArgument(int i);

    ITypeAnnotationWalker toTypeBound(short s);

    ITypeAnnotationWalker toTypeParameter(boolean z, int i);

    ITypeAnnotationWalker toTypeParameterBounds(boolean z, int i);

    ITypeAnnotationWalker toWildcardBound();
}
