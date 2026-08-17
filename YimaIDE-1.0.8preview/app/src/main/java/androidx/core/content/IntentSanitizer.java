package androidx.core.content;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.content.IntentSanitizer;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import defpackage.nt6;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class IntentSanitizer {
    private static final String TAG = "IntentSanitizer";
    private boolean mAllowAnyComponent;
    private boolean mAllowClipDataText;
    private boolean mAllowIdentifier;
    private boolean mAllowSelector;
    private boolean mAllowSourceBounds;
    private Predicate<String> mAllowedActions;
    private Predicate<String> mAllowedCategories;
    private Predicate<ClipData> mAllowedClipData;
    private Predicate<Uri> mAllowedClipDataUri;
    private Predicate<ComponentName> mAllowedComponents;
    private Predicate<Uri> mAllowedData;
    private Map<String, Predicate<Object>> mAllowedExtras;
    private int mAllowedFlags;
    private Predicate<String> mAllowedPackages;
    private Predicate<String> mAllowedTypes;

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static String getIdentifier(Intent intent) {
            return intent.getIdentifier();
        }

        public static Intent setIdentifier(Intent intent, String str) {
            return intent.setIdentifier(str);
        }
    }

    public static class Api31Impl {
        private Api31Impl() {
        }

        public static void checkOtherMembers(int i, ClipData.Item item, Consumer<String> consumer) {
            if (item.getHtmlText() == null && item.getIntent() == null && item.getTextLinks() == null) {
                return;
            }
            consumer.accept("ClipData item at position " + i + " contains htmlText, textLinks or intent: " + item);
        }
    }

    public static /* synthetic */ void a(String str) {
        throw new SecurityException(str);
    }

    public static /* synthetic */ void b(String str) {
    }

    private static void checkOtherMembers(int i, ClipData.Item item, Consumer<String> consumer) {
        if (item.getHtmlText() == null && item.getIntent() == null) {
            return;
        }
        consumer.accept("ClipData item at position " + i + " contains htmlText, textLinks or intent: " + item);
    }

    private void putExtra(Intent intent, String str, Object obj) {
        if (obj == null) {
            intent.getExtras().putString(str, null);
            return;
        }
        if (obj instanceof Parcelable) {
            intent.putExtra(str, (Parcelable) obj);
            return;
        }
        if (obj instanceof Parcelable[]) {
            intent.putExtra(str, (Parcelable[]) obj);
        } else if (obj instanceof Serializable) {
            intent.putExtra(str, (Serializable) obj);
        } else {
            z01.a("Unsupported type ", obj.getClass());
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ba  */
    public static void sanitizeClipData(Intent intent, Intent intent2, Predicate<ClipData> predicate, boolean z, Predicate<Uri> predicate2, Consumer<String> consumer) {
        CharSequence text;
        Uri uri;
        ClipData clipData = intent.getClipData();
        if (clipData == null) {
            return;
        }
        if (predicate != null && predicate.test(clipData)) {
            intent2.setClipData(clipData);
            return;
        }
        ClipData clipData2 = null;
        for (int i = 0; i < clipData.getItemCount(); i++) {
            ClipData.Item itemAt = clipData.getItemAt(i);
            Api31Impl.checkOtherMembers(i, itemAt, consumer);
            if (z) {
                text = itemAt.getText();
            } else {
                if (itemAt.getText() != null) {
                    consumer.accept("Item text cannot contain value. Item position: " + i + ". Text: " + ((Object) itemAt.getText()));
                }
                text = null;
            }
            if (predicate2 != null) {
                if (itemAt.getUri() == null || predicate2.test(itemAt.getUri())) {
                    uri = itemAt.getUri();
                } else {
                    consumer.accept("Item URI is not allowed. Item position: " + i + ". URI: " + itemAt.getUri());
                }
                if (text == null || uri != null) {
                    if (clipData2 == null) {
                        clipData2 = new ClipData(clipData.getDescription(), new ClipData.Item(text, null, uri));
                    } else {
                        clipData2.addItem(new ClipData.Item(text, null, uri));
                    }
                }
            } else if (itemAt.getUri() != null) {
                consumer.accept("Item URI is not allowed. Item position: " + i + ". URI: " + itemAt.getUri());
            }
            uri = null;
            if (text == null) {
                if (clipData2 == null) {
                    clipData2 = new ClipData(clipData.getDescription(), new ClipData.Item(text, null, uri));
                } else {
                    clipData2.addItem(new ClipData.Item(text, null, uri));
                }
            } else if (clipData2 == null) {
                clipData2 = new ClipData(clipData.getDescription(), new ClipData.Item(text, null, uri));
            } else {
                clipData2.addItem(new ClipData.Item(text, null, uri));
            }
        }
        if (clipData2 != null) {
            intent2.setClipData(clipData2);
        }
    }

    public Intent sanitize(Intent intent, Consumer<String> consumer) {
        Intent intent2 = new Intent();
        ComponentName component = intent.getComponent();
        if ((this.mAllowAnyComponent && component == null) || this.mAllowedComponents.test(component)) {
            intent2.setComponent(component);
        } else {
            consumer.accept("Component is not allowed: " + component);
            intent2.setComponent(new ComponentName("android", "java.lang.Void"));
        }
        String str = intent.getPackage();
        if (str == null || this.mAllowedPackages.test(str)) {
            intent2.setPackage(str);
        } else {
            consumer.accept("Package is not allowed: ".concat(str));
        }
        int flags = this.mAllowedFlags | intent.getFlags();
        int i = this.mAllowedFlags;
        if (flags == i) {
            intent2.setFlags(intent.getFlags());
        } else {
            intent2.setFlags(intent.getFlags() & i);
            consumer.accept("The intent contains flags that are not allowed: 0x" + Integer.toHexString(intent.getFlags() & (~this.mAllowedFlags)));
        }
        String action = intent.getAction();
        if (action == null || this.mAllowedActions.test(action)) {
            intent2.setAction(action);
        } else {
            consumer.accept("Action is not allowed: ".concat(action));
        }
        Uri data = intent.getData();
        if (data == null || this.mAllowedData.test(data)) {
            intent2.setData(data);
        } else {
            consumer.accept("Data is not allowed: " + data);
        }
        String type = intent.getType();
        if (type == null || this.mAllowedTypes.test(type)) {
            intent2.setDataAndType(intent2.getData(), type);
        } else {
            consumer.accept("Type is not allowed: ".concat(type));
        }
        Set<String> categories = intent.getCategories();
        if (categories != null) {
            for (String str2 : categories) {
                if (this.mAllowedCategories.test(str2)) {
                    intent2.addCategory(str2);
                } else {
                    consumer.accept("Category is not allowed: " + str2);
                }
            }
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            for (String str3 : extras.keySet()) {
                if (str3.equals("android.intent.extra.STREAM") && (this.mAllowedFlags & 1) == 0) {
                    consumer.accept("Allowing Extra Stream requires also allowing at least  FLAG_GRANT_READ_URI_PERMISSION Flag.");
                } else if (!str3.equals("output") || ((~this.mAllowedFlags) & 3) == 0) {
                    Object obj = extras.get(str3);
                    Predicate<Object> predicate = this.mAllowedExtras.get(str3);
                    if (predicate == null || !predicate.test(obj)) {
                        consumer.accept("Extra is not allowed. Key: " + str3 + ". Value: " + obj);
                    } else {
                        putExtra(intent2, str3, obj);
                    }
                } else {
                    consumer.accept("Allowing Extra Output requires also allowing FLAG_GRANT_READ_URI_PERMISSION and FLAG_GRANT_WRITE_URI_PERMISSION Flags.");
                }
            }
        }
        sanitizeClipData(intent, intent2, this.mAllowedClipData, this.mAllowClipDataText, this.mAllowedClipDataUri, consumer);
        if (this.mAllowIdentifier) {
            Api29Impl.setIdentifier(intent2, Api29Impl.getIdentifier(intent));
        } else if (Api29Impl.getIdentifier(intent) != null) {
            consumer.accept("Identifier is not allowed: " + Api29Impl.getIdentifier(intent));
        }
        if (this.mAllowSelector) {
            intent2.setSelector(intent.getSelector());
        } else if (intent.getSelector() != null) {
            consumer.accept("Selector is not allowed: " + intent.getSelector());
        }
        if (this.mAllowSourceBounds) {
            intent2.setSourceBounds(intent.getSourceBounds());
            return intent2;
        }
        if (intent.getSourceBounds() != null) {
            consumer.accept("SourceBounds is not allowed: " + intent.getSourceBounds());
        }
        return intent2;
    }

    public Intent sanitizeByFiltering(Intent intent) {
        return sanitize(intent, new Consumer() { // from class: lt6
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                IntentSanitizer.b((String) obj);
            }
        });
    }

    public Intent sanitizeByThrowing(Intent intent) {
        return sanitize(intent, new Consumer() { // from class: kt6
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                IntentSanitizer.a((String) obj);
            }
        });
    }

    private IntentSanitizer() {
    }

    public static final class Builder {
        private static final int HISTORY_STACK_FLAGS = 2112614400;
        private static final int RECEIVER_FLAGS = 2015363072;
        private boolean mAllowAnyComponent;
        private boolean mAllowIdentifier;
        private boolean mAllowSelector;
        private boolean mAllowSomeComponents;
        private boolean mAllowSourceBounds;
        private int mAllowedFlags;
        private Predicate<String> mAllowedActions = new Predicate() { // from class: wt6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.p((String) obj);
            }
        };
        private Predicate<Uri> mAllowedData = new Predicate() { // from class: xt6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.l((Uri) obj);
            }
        };
        private Predicate<String> mAllowedTypes = new Predicate() { // from class: yt6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.b((String) obj);
            }
        };
        private Predicate<String> mAllowedCategories = new Predicate() { // from class: zt6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.k((String) obj);
            }
        };
        private Predicate<String> mAllowedPackages = new Predicate() { // from class: au6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.f((String) obj);
            }
        };
        private Predicate<ComponentName> mAllowedComponents = new Predicate() { // from class: bu6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.a((ComponentName) obj);
            }
        };
        private Map<String, Predicate<Object>> mAllowedExtras = new HashMap();
        private boolean mAllowClipDataText = false;
        private Predicate<Uri> mAllowedClipDataUri = new Predicate() { // from class: cu6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.m((Uri) obj);
            }
        };
        private Predicate<ClipData> mAllowedClipData = new Predicate() { // from class: du6
            @Override // androidx.core.util.Predicate
            public final boolean test(Object obj) {
                return IntentSanitizer.Builder.q((ClipData) obj);
            }
        };

        public static /* synthetic */ boolean a(ComponentName componentName) {
            return false;
        }

        public static /* synthetic */ boolean b(String str) {
            return false;
        }

        public static /* synthetic */ boolean e(Class cls, Predicate predicate, Object obj) {
            return cls.isInstance(obj) && predicate.test(cls.cast(obj));
        }

        public static /* synthetic */ boolean f(String str) {
            return false;
        }

        public static /* synthetic */ boolean i(Object obj) {
            return false;
        }

        public static /* synthetic */ boolean j(ComponentName componentName) {
            return true;
        }

        public static /* synthetic */ boolean k(String str) {
            return false;
        }

        public static /* synthetic */ boolean l(Uri uri) {
            return false;
        }

        public static /* synthetic */ boolean m(Uri uri) {
            return false;
        }

        public static /* synthetic */ boolean o(Object obj) {
            return true;
        }

        public static /* synthetic */ boolean p(String str) {
            return false;
        }

        public static /* synthetic */ boolean q(ClipData clipData) {
            return false;
        }

        public Builder allowAction(String str) {
            Preconditions.checkNotNull(str);
            Objects.requireNonNull(str);
            allowAction(new nt6(str));
            return this;
        }

        public Builder allowAnyComponent() {
            this.mAllowAnyComponent = true;
            this.mAllowedComponents = new Predicate() { // from class: st6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return IntentSanitizer.Builder.j((ComponentName) obj);
                }
            };
            return this;
        }

        public Builder allowCategory(String str) {
            Preconditions.checkNotNull(str);
            Objects.requireNonNull(str);
            return allowCategory(new nt6(str));
        }

        public Builder allowClipData(Predicate<ClipData> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedClipData = this.mAllowedClipData.or(predicate);
            return this;
        }

        public Builder allowClipDataText() {
            this.mAllowClipDataText = true;
            return this;
        }

        public Builder allowClipDataUri(Predicate<Uri> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedClipDataUri = this.mAllowedClipDataUri.or(predicate);
            return this;
        }

        public Builder allowClipDataUriWithAuthority(final String str) {
            Preconditions.checkNotNull(str);
            return allowClipDataUri(new Predicate() { // from class: tt6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
        }

        public Builder allowComponent(final ComponentName componentName) {
            Preconditions.checkNotNull(componentName);
            Objects.requireNonNull(componentName);
            return allowComponent(new Predicate() { // from class: rt6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return componentName.equals((ComponentName) obj);
                }
            });
        }

        public Builder allowComponentWithPackage(final String str) {
            Preconditions.checkNotNull(str);
            return allowComponent(new Predicate() { // from class: ot6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((ComponentName) obj).getPackageName());
                }
            });
        }

        public Builder allowData(Predicate<Uri> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedData = this.mAllowedData.or(predicate);
            return this;
        }

        public Builder allowDataWithAuthority(final String str) {
            Preconditions.checkNotNull(str);
            allowData(new Predicate() { // from class: pt6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        public Builder allowExtra(String str, Predicate<Object> predicate) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(predicate);
            Predicate<Object> predicate2 = this.mAllowedExtras.get(str);
            if (predicate2 == null) {
                predicate2 = new Predicate() { // from class: ut6
                    @Override // androidx.core.util.Predicate
                    public final boolean test(Object obj) {
                        return IntentSanitizer.Builder.i(obj);
                    }
                };
            }
            this.mAllowedExtras.put(str, predicate2.or(predicate));
            return this;
        }

        public Builder allowExtraOutput(final String str) {
            allowExtra("output", Uri.class, new Predicate() { // from class: mt6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        public Builder allowExtraStream(Predicate<Uri> predicate) {
            allowExtra("android.intent.extra.STREAM", Uri.class, predicate);
            return this;
        }

        public Builder allowExtraStreamUriWithAuthority(final String str) {
            Preconditions.checkNotNull(str);
            allowExtra("android.intent.extra.STREAM", Uri.class, new Predicate() { // from class: vt6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return str.equals(((Uri) obj).getAuthority());
                }
            });
            return this;
        }

        public Builder allowFlags(int i) {
            this.mAllowedFlags = i | this.mAllowedFlags;
            return this;
        }

        public Builder allowHistoryStackFlags() {
            this.mAllowedFlags |= HISTORY_STACK_FLAGS;
            return this;
        }

        public Builder allowIdentifier() {
            this.mAllowIdentifier = true;
            return this;
        }

        public Builder allowPackage(String str) {
            Preconditions.checkNotNull(str);
            Objects.requireNonNull(str);
            return allowPackage(new nt6(str));
        }

        public Builder allowReceiverFlags() {
            this.mAllowedFlags |= RECEIVER_FLAGS;
            return this;
        }

        public Builder allowSelector() {
            this.mAllowSelector = true;
            return this;
        }

        public Builder allowSourceBounds() {
            this.mAllowSourceBounds = true;
            return this;
        }

        public Builder allowType(String str) {
            Preconditions.checkNotNull(str);
            Objects.requireNonNull(str);
            return allowType(new nt6(str));
        }

        public IntentSanitizer build() {
            boolean z = this.mAllowAnyComponent;
            if ((z && this.mAllowSomeComponents) || (!z && !this.mAllowSomeComponents)) {
                throw new SecurityException("You must call either allowAnyComponent or one or more of the allowComponent methods; but not both.");
            }
            IntentSanitizer intentSanitizer = new IntentSanitizer();
            intentSanitizer.mAllowedFlags = this.mAllowedFlags;
            intentSanitizer.mAllowedActions = this.mAllowedActions;
            intentSanitizer.mAllowedData = this.mAllowedData;
            intentSanitizer.mAllowedTypes = this.mAllowedTypes;
            intentSanitizer.mAllowedCategories = this.mAllowedCategories;
            intentSanitizer.mAllowedPackages = this.mAllowedPackages;
            intentSanitizer.mAllowAnyComponent = this.mAllowAnyComponent;
            intentSanitizer.mAllowedComponents = this.mAllowedComponents;
            intentSanitizer.mAllowedExtras = this.mAllowedExtras;
            intentSanitizer.mAllowClipDataText = this.mAllowClipDataText;
            intentSanitizer.mAllowedClipDataUri = this.mAllowedClipDataUri;
            intentSanitizer.mAllowedClipData = this.mAllowedClipData;
            intentSanitizer.mAllowIdentifier = this.mAllowIdentifier;
            intentSanitizer.mAllowSelector = this.mAllowSelector;
            intentSanitizer.mAllowSourceBounds = this.mAllowSourceBounds;
            return intentSanitizer;
        }

        public Builder allowExtraOutput(Predicate<Uri> predicate) {
            allowExtra("output", Uri.class, predicate);
            return this;
        }

        public Builder allowAction(Predicate<String> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedActions = this.mAllowedActions.or(predicate);
            return this;
        }

        public Builder allowCategory(Predicate<String> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedCategories = this.mAllowedCategories.or(predicate);
            return this;
        }

        public Builder allowComponent(Predicate<ComponentName> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowSomeComponents = true;
            this.mAllowedComponents = this.mAllowedComponents.or(predicate);
            return this;
        }

        public Builder allowPackage(Predicate<String> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedPackages = this.mAllowedPackages.or(predicate);
            return this;
        }

        public Builder allowType(Predicate<String> predicate) {
            Preconditions.checkNotNull(predicate);
            this.mAllowedTypes = this.mAllowedTypes.or(predicate);
            return this;
        }

        public <T> Builder allowExtra(String str, final Class<T> cls, final Predicate<T> predicate) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(cls);
            Preconditions.checkNotNull(predicate);
            return allowExtra(str, new Predicate() { // from class: qt6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return IntentSanitizer.Builder.e(cls, predicate, obj);
                }
            });
        }

        public Builder allowExtra(String str, Class<?> cls) {
            return allowExtra(str, cls, new Predicate() { // from class: eu6
                @Override // androidx.core.util.Predicate
                public final boolean test(Object obj) {
                    return IntentSanitizer.Builder.o(obj);
                }
            });
        }
    }
}
