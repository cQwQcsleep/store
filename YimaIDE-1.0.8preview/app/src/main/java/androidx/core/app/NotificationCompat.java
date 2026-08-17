package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.LocusId;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Annotation;
import android.text.ParcelableSpan;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.annotation.ReplaceWith;
import androidx.core.R;
import androidx.core.content.ContextCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.util.Preconditions;
import defpackage.aja;
import defpackage.bja;
import defpackage.cja;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class NotificationCompat {
    private static final String ANNOTATION_SEMANTIC_STYLE_KEY = "android.app.notification.semanticStyle";
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_LOCATION_SHARING = "location_sharing";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_MISSED_CALL = "missed_call";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_STOPWATCH = "stopwatch";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_VOICEMAIL = "voicemail";
    public static final String CATEGORY_WORKOUT = "workout";
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_ANSWER_COLOR = "android.answerColor";
    public static final String EXTRA_ANSWER_INTENT = "android.answerIntent";
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_CALL_IS_VIDEO = "android.callIsVideo";
    public static final String EXTRA_CALL_PERSON = "android.callPerson";
    public static final String EXTRA_CALL_PERSON_COMPAT = "android.callPersonCompat";
    public static final String EXTRA_CALL_TYPE = "android.callType";
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";
    public static final String EXTRA_COLORIZED = "android.colorized";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_COMPAT_TEMPLATE = "androidx.core.app.extra.COMPAT_TEMPLATE";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_DECLINE_COLOR = "android.declineColor";
    public static final String EXTRA_DECLINE_INTENT = "android.declineIntent";
    public static final String EXTRA_HANG_UP_INTENT = "android.hangUpIntent";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    private static final String EXTRA_MESSAGING_PERSON = "android.messagingUser";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";

    @Deprecated
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PICTURE_CONTENT_DESCRIPTION = "android.pictureContentDescription";
    public static final String EXTRA_PICTURE_ICON = "android.pictureIcon";
    public static final String EXTRA_PREFER_SMALL_ICON = "android.app.preferSmallIcon";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_END_ICON = "android.progressEndIcon";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_PROGRESS_POINTS = "android.progressPoints";
    public static final String EXTRA_PROGRESS_SEGMENTS = "android.progressSegments";
    public static final String EXTRA_PROGRESS_START_ICON = "android.progressStartIcon";
    public static final String EXTRA_PROGRESS_TRACKER_ICON = "android.progressTrackerIcon";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_REQUEST_PROMOTED_ONGOING = "android.requestPromotedOngoing";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHORT_CRITICAL_TEXT = "android.shortCriticalText";
    public static final String EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED = "android.showBigPictureWhenCollapsed";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_STYLED_BY_PROGRESS = "android.styledByProgress";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final String EXTRA_VERIFICATION_ICON = "android.verificationIcon";
    public static final String EXTRA_VERIFICATION_ICON_COMPAT = "android.verificationIconCompat";
    public static final String EXTRA_VERIFICATION_TEXT = "android.verificationText";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_BUBBLE = 4096;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;

    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int FOREGROUND_SERVICE_DEFAULT = 0;
    public static final int FOREGROUND_SERVICE_DEFERRED = 2;
    public static final int FOREGROUND_SERVICE_IMMEDIATE = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String GROUP_KEY_SILENT = "silent";
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    public static final int MAX_ACTION_BUTTONS = 3;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int SEMANTIC_STYLE_CAUTION = 3;
    public static final int SEMANTIC_STYLE_DANGER = 4;
    public static final int SEMANTIC_STYLE_INFO = 1;
    public static final int SEMANTIC_STYLE_SAFE = 2;
    public static final int SEMANTIC_STYLE_UNSPECIFIED = 0;
    public static final int STREAM_DEFAULT = -1;
    private static final String TAG = "NotifCompat";
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    public static class Api24Impl {
        private Api24Impl() {
        }

        public static boolean getAllowGeneratedReplies(Notification.Action action) {
            return action.getAllowGeneratedReplies();
        }
    }

    public static class Api26Impl {
        private Api26Impl() {
        }

        public static int getBadgeIconType(Notification notification) {
            return notification.getBadgeIconType();
        }

        public static String getChannelId(Notification notification) {
            return notification.getChannelId();
        }

        public static int getGroupAlertBehavior(Notification notification) {
            return notification.getGroupAlertBehavior();
        }

        public static CharSequence getSettingsText(Notification notification) {
            return notification.getSettingsText();
        }

        public static String getShortcutId(Notification notification) {
            return notification.getShortcutId();
        }

        public static long getTimeoutAfter(Notification notification) {
            return notification.getTimeoutAfter();
        }
    }

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static int getSemanticAction(Notification.Action action) {
            return action.getSemanticAction();
        }
    }

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static boolean getAllowSystemGeneratedContextualActions(Notification notification) {
            return notification.getAllowSystemGeneratedContextualActions();
        }

        public static Notification.BubbleMetadata getBubbleMetadata(Notification notification) {
            return notification.getBubbleMetadata();
        }

        public static int getEditChoicesBeforeSending(android.app.RemoteInput remoteInput) {
            return remoteInput.getEditChoicesBeforeSending();
        }

        public static LocusId getLocusId(Notification notification) {
            return notification.getLocusId();
        }

        public static boolean isContextual(Notification.Action action) {
            return action.isContextual();
        }
    }

    public static class Api36Impl {
        private Api36Impl() {
        }

        public static boolean hasPromotableCharacteristics(Notification notification) {
            return notification.hasPromotableCharacteristics();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeIconType {
    }

    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";

        public static class Api24Impl {
            private Api24Impl() {
            }

            public static Notification.Style createDecoratedCustomViewStyle() {
                return new Notification.DecoratedCustomViewStyle();
            }
        }

        private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean z) {
            int iMin;
            int i = 0;
            RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            remoteViewsApplyStandardTemplate.removeAllViews(R.id.actions);
            List<Action> nonContextualActions = getNonContextualActions(this.mBuilder.mActions);
            if (!z || nonContextualActions == null || (iMin = Math.min(nonContextualActions.size(), 3)) <= 0) {
                i = 8;
            } else {
                for (int i2 = 0; i2 < iMin; i2++) {
                    remoteViewsApplyStandardTemplate.addView(R.id.actions, generateActionButton(nonContextualActions.get(i2)));
                }
            }
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.actions, i);
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.action_divider, i);
            buildIntoRemoteViews(remoteViewsApplyStandardTemplate, remoteViews);
            return remoteViewsApplyStandardTemplate;
        }

        private RemoteViews generateActionButton(Action action) {
            boolean z = action.actionIntent == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), z ? R.layout.notification_action_tombstone : R.layout.notification_action);
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat != null) {
                remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(iconCompat, R.color.notification_action_color_filter));
            }
            remoteViews.setTextViewText(R.id.action_text, action.title);
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            remoteViews.setContentDescription(R.id.action_container, action.title);
            return remoteViews;
        }

        private static List<Action> getNonContextualActions(List<Action> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Action action : list) {
                if (!action.isContextual()) {
                    arrayList.add(action);
                }
            }
            return arrayList;
        }

        public static List<CharSequence> getTextsFromContentView(Context context, Notification notification) {
            if (!Notification.DecoratedCustomViewStyle.class.getName().equals(notification.extras.getString(NotificationCompat.EXTRA_TEMPLATE))) {
                return Collections.EMPTY_LIST;
            }
            RemoteViews remoteViews = notification.contentView;
            if (remoteViews == null && notification.bigContentView == null && notification.headsUpContentView == null) {
                return Collections.EMPTY_LIST;
            }
            RemoteViews remoteViews2 = notification.bigContentView;
            if (remoteViews2 != null) {
                remoteViews = remoteViews2;
            } else if (remoteViews == null) {
                remoteViews = notification.headsUpContentView;
            }
            String str = remoteViews.getPackage();
            try {
                Context contextCreatePackageContext = context.createPackageContext(str, 0);
                contextCreatePackageContext.setTheme(context.getPackageManager().getApplicationInfo(str, 0).theme);
                View viewApply = remoteViews.apply(contextCreatePackageContext, null);
                ArrayList arrayList = new ArrayList();
                getTextsFromViewTraversal(viewApply, arrayList);
                return arrayList;
            } catch (PackageManager.NameNotFoundException e) {
                rc6.a(e);
                return null;
            }
        }

        private static void getTextsFromViewTraversal(View view, ArrayList<CharSequence> arrayList) {
            CharSequence text;
            if (!(view instanceof ViewGroup)) {
                return;
            }
            int i = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i >= viewGroup.getChildCount()) {
                    return;
                }
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof TextView) && (text = ((TextView) childAt).getText()) != null && text.length() > 0) {
                    arrayList.add(text);
                }
                if (childAt instanceof ViewGroup) {
                    getTextsFromViewTraversal(childAt, arrayList);
                }
                i++;
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            notificationBuilderWithBuilderAccessor.getBuilder().setStyle(Api24Impl.createDecoratedCustomViewStyle());
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public boolean displayCustomViewInline() {
            return true;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }
    }

    public interface Extender {
        Builder extend(Builder builder);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GroupAlertBehavior {
    }

    public static final class MetricStyle extends Style {
        private static final int CRITICAL_METRIC_DEFAULT = 0;
        private static final String EXTRA_METRICS = "android.metrics";
        private static final String EXTRA_METRICS_CRITICAL_INDEX = "android.metrics.criticalIndex";
        public static final int METRIC_INDEX_NONE = -1;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$MetricStyle";
        private final List<Metric> mMetrics = new ArrayList();
        private int mCriticalMetric = 0;

        public static final class Api37Impl {
            private Api37Impl() {
            }

            private static Notification.Metric toPlatformMetric(Metric metric) {
                return new Notification.Metric(toPlatformMetricValue(metric.getValue()), metric.getLabel(), metric.getSemanticStyle());
            }

            private static Notification.Metric.MetricValue toPlatformMetricValue(Metric.MetricValue metricValue) {
                if (metricValue instanceof Metric.TimeDifference) {
                    Metric.TimeDifference timeDifference = (Metric.TimeDifference) metricValue;
                    if (timeDifference.getZeroTime() != null) {
                        return timeDifference.isTimer() ? Notification.Metric.TimeDifference.forTimer(timeDifference.getZeroTime(), timeDifference.getFormat()) : Notification.Metric.TimeDifference.forStopwatch(timeDifference.getZeroTime(), timeDifference.getFormat());
                    }
                    if (timeDifference.getZeroElapsedRealtime() != null) {
                        return timeDifference.isTimer() ? Notification.Metric.TimeDifference.forTimer(timeDifference.getZeroElapsedRealtime().longValue(), timeDifference.getFormat()) : Notification.Metric.TimeDifference.forStopwatch(timeDifference.getZeroElapsedRealtime().longValue(), timeDifference.getFormat());
                    }
                    if (timeDifference.getPausedDuration() != null) {
                        return timeDifference.isTimer() ? Notification.Metric.TimeDifference.forPausedTimer(timeDifference.getPausedDuration(), timeDifference.getFormat()) : Notification.Metric.TimeDifference.forPausedStopwatch(timeDifference.getPausedDuration(), timeDifference.getFormat());
                    }
                    aca.a("Unexpected TimeDifference: ", timeDifference);
                    return null;
                }
                if (metricValue instanceof Metric.FixedDate) {
                    Metric.FixedDate fixedDate = (Metric.FixedDate) metricValue;
                    return new Notification.Metric.FixedDate(fixedDate.getValue(), fixedDate.getFormat());
                }
                if (metricValue instanceof Metric.FixedFloat) {
                    Metric.FixedFloat fixedFloat = (Metric.FixedFloat) metricValue;
                    return new Notification.Metric.FixedFloat(fixedFloat.getValue(), fixedFloat.getUnit(), fixedFloat.getMinFractionDigits(), fixedFloat.getMaxFractionDigits());
                }
                if (metricValue instanceof Metric.FixedInt) {
                    Metric.FixedInt fixedInt = (Metric.FixedInt) metricValue;
                    return new Notification.Metric.FixedInt(fixedInt.getValue(), fixedInt.getUnit());
                }
                if (metricValue instanceof Metric.FixedText) {
                    Metric.FixedText fixedText = (Metric.FixedText) metricValue;
                    return new Notification.Metric.FixedText(fixedText.getValue(), fixedText.getUnit());
                }
                if (metricValue instanceof Metric.FixedTime) {
                    return new Notification.Metric.FixedTime(((Metric.FixedTime) metricValue).getValue());
                }
                aca.a("Unexpected MetricValue: ", metricValue);
                return null;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Notification.Style toPlatformStyle(MetricStyle metricStyle) {
                Notification.MetricStyle metricStyle2 = new Notification.MetricStyle();
                Iterator<Metric> it = metricStyle.getMetrics().iterator();
                while (it.hasNext()) {
                    metricStyle2.addMetric(toPlatformMetric(it.next()));
                }
                metricStyle2.setCriticalMetric(metricStyle.getMetrics().indexOf(metricStyle.getCriticalMetric()));
                return metricStyle2;
            }
        }

        private void validate() {
            if (this.mMetrics.isEmpty()) {
                w01.a("A MetricStyle must have at least one Metric");
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            if (Build.VERSION.SDK_INT >= 37) {
                return;
            }
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<Metric> it = this.mMetrics.iterator();
            while (it.hasNext()) {
                arrayList.add(Metric.toBundle(it.next()));
            }
            bundle.putParcelableArrayList(EXTRA_METRICS, arrayList);
            bundle.putInt(EXTRA_METRICS_CRITICAL_INDEX, this.mCriticalMetric);
        }

        public MetricStyle addMetric(Metric metric) {
            List<Metric> list = this.mMetrics;
            Objects.requireNonNull(metric);
            list.add(metric);
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            validate();
            if (Build.VERSION.SDK_INT >= 37) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(Api37Impl.toPlatformStyle(this));
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(EXTRA_METRICS);
            bundle.remove(EXTRA_METRICS_CRITICAL_INDEX);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MetricStyle)) {
                return false;
            }
            MetricStyle metricStyle = (MetricStyle) obj;
            if (this == metricStyle) {
                return true;
            }
            return Objects.equals(this.mMetrics, metricStyle.mMetrics) && this.mCriticalMetric == metricStyle.mCriticalMetric;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public Metric getCriticalMetric() {
            int i = this.mCriticalMetric;
            if (i < 0 || i >= this.mMetrics.size()) {
                return null;
            }
            return this.mMetrics.get(this.mCriticalMetric);
        }

        public List<Metric> getMetrics() {
            return Collections.unmodifiableList(this.mMetrics);
        }

        public int hashCode() {
            return Objects.hash(this.mMetrics, Integer.valueOf(this.mCriticalMetric));
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            Metric metricFromBundle;
            super.restoreFromCompatExtras(bundle);
            this.mMetrics.clear();
            ArrayList<Bundle> parcelableArrayList = androidx.core.os.BundleCompat.getParcelableArrayList(bundle, EXTRA_METRICS, Bundle.class);
            if (parcelableArrayList != null) {
                for (Bundle bundle2 : parcelableArrayList) {
                    if (bundle2 != null && (metricFromBundle = Metric.fromBundle(bundle2)) != null) {
                        addMetric(metricFromBundle);
                    }
                }
            }
            this.mCriticalMetric = bundle.getInt(EXTRA_METRICS_CRITICAL_INDEX, 0);
        }

        public MetricStyle setCriticalMetric(int i) {
            this.mCriticalMetric = i;
            return this;
        }

        public MetricStyle setMetrics(List<Metric> list) {
            this.mMetrics.clear();
            for (Metric metric : list) {
                List<Metric> list2 = this.mMetrics;
                Objects.requireNonNull(metric);
                list2.add(metric);
            }
            return this;
        }

        public String toString() {
            return "MetricStyle{mMetrics=" + this.mMetrics + ", mCriticalMetric=" + this.mCriticalMetric + "}";
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NotificationVisibility {
    }

    public static class ProgressStyle extends Style {
        private static final int DEFAULT_PROGRESS_MAX = 100;
        private static final String KEY_ELEMENT_COLOR = "colorInt";
        private static final String KEY_ELEMENT_ID = "id";
        private static final String KEY_ELEMENT_SEMANTIC_STYLE = "semanticStyle";
        private static final String KEY_POINT_POSITION = "position";
        private static final String KEY_SEGMENT_LENGTH = "length";
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$ProgressStyle";
        private IconCompat mEndIcon;
        private boolean mIndeterminate;
        private IconCompat mStartIcon;
        private IconCompat mTrackerIcon;
        private List<Segment> mProgressSegments = new ArrayList();
        private List<Point> mProgressPoints = new ArrayList();
        private int mProgress = 0;
        private boolean mIsStyledByProgress = true;

        public static final class Api36Impl {
            private Api36Impl() {
            }

            public static void setProgress(Notification.ProgressStyle progressStyle, int i) {
                progressStyle.setProgress(i);
            }

            public static void setProgressEndIcon(Notification.ProgressStyle progressStyle, Icon icon) {
                progressStyle.setProgressEndIcon(icon);
            }

            public static void setProgressIndeterminate(Notification.ProgressStyle progressStyle, boolean z) {
                progressStyle.setProgressIndeterminate(z);
            }

            public static void setProgressPoints(Notification.ProgressStyle progressStyle, List<Point> list) {
                Iterator<Point> it = list.iterator();
                while (it.hasNext()) {
                    progressStyle.addProgressPoint(toPlatformPoint(it.next()));
                }
            }

            public static void setProgressSegments(Notification.ProgressStyle progressStyle, List<Segment> list) {
                Iterator<Segment> it = list.iterator();
                while (it.hasNext()) {
                    progressStyle.addProgressSegment(toPlatformSegment(it.next()));
                }
            }

            public static void setProgressStartIcon(Notification.ProgressStyle progressStyle, Icon icon) {
                progressStyle.setProgressStartIcon(icon);
            }

            public static void setProgressTrackerIcon(Notification.ProgressStyle progressStyle, Icon icon) {
                progressStyle.setProgressTrackerIcon(icon);
            }

            public static void setStyledByProgress(Notification.ProgressStyle progressStyle, boolean z) {
                progressStyle.setStyledByProgress(z);
            }

            public static Notification.ProgressStyle.Point toPlatformPoint(Point point) {
                return new Notification.ProgressStyle.Point(point.getPosition()).setColor(point.getColor()).setId(point.getId());
            }

            public static Notification.ProgressStyle.Segment toPlatformSegment(Segment segment) {
                return new Notification.ProgressStyle.Segment(segment.getLength()).setColor(segment.getColor()).setId(segment.getId());
            }
        }

        public static final class Api37Impl {
            private Api37Impl() {
            }

            public static void setProgressPoints(Notification.ProgressStyle progressStyle, List<Point> list) {
                Iterator<Point> it = list.iterator();
                while (it.hasNext()) {
                    progressStyle.addProgressPoint(toPlatformPoint(it.next()));
                }
            }

            public static void setProgressSegments(Notification.ProgressStyle progressStyle, List<Segment> list) {
                Iterator<Segment> it = list.iterator();
                while (it.hasNext()) {
                    progressStyle.addProgressSegment(toPlatformSegment(it.next()));
                }
            }

            public static Notification.ProgressStyle.Point toPlatformPoint(Point point) {
                return Api36Impl.toPlatformPoint(point).setSemanticStyle(point.getSemanticStyle());
            }

            public static Notification.ProgressStyle.Segment toPlatformSegment(Segment segment) {
                return Api36Impl.toPlatformSegment(segment).setSemanticStyle(segment.getSemanticStyle());
            }
        }

        public static final class Point {
            private int mPosition;
            private int mId = 0;
            private int mColor = 0;
            private int mSemanticStyle = 0;

            public Point(int i) {
                this.mPosition = i;
            }

            public int getColor() {
                return this.mColor;
            }

            public int getId() {
                return this.mId;
            }

            public int getPosition() {
                return this.mPosition;
            }

            public int getSemanticStyle() {
                return this.mSemanticStyle;
            }

            public Point setColor(int i) {
                this.mColor = i;
                return this;
            }

            public Point setId(int i) {
                this.mId = i;
                return this;
            }

            public Point setSemanticStyle(int i) {
                this.mSemanticStyle = i;
                return this;
            }
        }

        public static final class Segment {
            private int mLength;
            private int mId = 0;
            private int mColor = 0;
            private int mSemanticStyle = 0;

            public Segment(int i) {
                this.mLength = i;
            }

            public int getColor() {
                return this.mColor;
            }

            public int getId() {
                return this.mId;
            }

            public int getLength() {
                return this.mLength;
            }

            public int getSemanticStyle() {
                return this.mSemanticStyle;
            }

            public Segment setColor(int i) {
                this.mColor = i;
                return this;
            }

            public Segment setId(int i) {
                this.mId = i;
                return this;
            }

            public Segment setSemanticStyle(int i) {
                this.mSemanticStyle = i;
                return this;
            }
        }

        private static IconCompat asIconCompat(Parcelable parcelable) {
            if (parcelable == null) {
                return null;
            }
            if (parcelable instanceof Icon) {
                return IconCompat.createFromIcon((Icon) parcelable);
            }
            if (parcelable instanceof Bitmap) {
                return IconCompat.createWithBitmap((Bitmap) parcelable);
            }
            return null;
        }

        private static ArrayList<Bundle> getProgressPointsAsBundleList(List<Point> list) {
            ArrayList<Bundle> arrayList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Point point = list.get(i);
                    if (point.getPosition() >= 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_POINT_POSITION, point.getPosition());
                        bundle.putInt(KEY_ELEMENT_ID, point.getId());
                        bundle.putInt(KEY_ELEMENT_COLOR, point.getColor());
                        bundle.putInt(KEY_ELEMENT_SEMANTIC_STYLE, point.getSemanticStyle());
                        arrayList.add(bundle);
                    }
                }
            }
            return arrayList;
        }

        private static List<Point> getProgressPointsFromBundleList(List<Bundle> list) {
            ArrayList arrayList = new ArrayList();
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Bundle bundle = list.get(i);
                    int i2 = bundle.getInt(KEY_POINT_POSITION);
                    if (i2 >= 0) {
                        int i3 = bundle.getInt(KEY_ELEMENT_ID);
                        arrayList.add(new Point(i2).setId(i3).setColor(bundle.getInt(KEY_ELEMENT_COLOR, 0)).setSemanticStyle(bundle.getInt(KEY_ELEMENT_SEMANTIC_STYLE, 0)));
                    }
                }
            }
            return arrayList;
        }

        private static ArrayList<Bundle> getProgressSegmentsAsBundleList(List<Segment> list) {
            ArrayList<Bundle> arrayList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Segment segment = list.get(i);
                    if (segment.getLength() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(KEY_SEGMENT_LENGTH, segment.getLength());
                        bundle.putInt(KEY_ELEMENT_ID, segment.getId());
                        bundle.putInt(KEY_ELEMENT_COLOR, segment.getColor());
                        bundle.putInt(KEY_ELEMENT_SEMANTIC_STYLE, segment.getSemanticStyle());
                        arrayList.add(bundle);
                    }
                }
            }
            return arrayList;
        }

        private static List<Segment> getProgressSegmentsFromBundleList(List<Bundle> list) {
            ArrayList arrayList = new ArrayList();
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Bundle bundle = list.get(i);
                    int i2 = bundle.getInt(KEY_SEGMENT_LENGTH);
                    if (i2 > 0) {
                        int i3 = bundle.getInt(KEY_ELEMENT_ID);
                        arrayList.add(new Segment(i2).setId(i3).setColor(bundle.getInt(KEY_ELEMENT_COLOR, 0)).setSemanticStyle(bundle.getInt(KEY_ELEMENT_SEMANTIC_STYLE, 0)));
                    }
                }
            }
            return arrayList;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            int i = Build.VERSION.SDK_INT;
            if (i <= 36) {
                bundle.putParcelableArrayList(NotificationCompat.EXTRA_PROGRESS_SEGMENTS, getProgressSegmentsAsBundleList(this.mProgressSegments));
                bundle.putParcelableArrayList(NotificationCompat.EXTRA_PROGRESS_POINTS, getProgressPointsAsBundleList(this.mProgressPoints));
            }
            if (i < 36) {
                bundle.putInt(NotificationCompat.EXTRA_PROGRESS, this.mProgress);
                bundle.putBoolean(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE, this.mIndeterminate);
                bundle.putInt(NotificationCompat.EXTRA_PROGRESS_MAX, getProgressMax());
                bundle.putBoolean(NotificationCompat.EXTRA_STYLED_BY_PROGRESS, this.mIsStyledByProgress);
                Builder builder = this.mBuilder;
                Context context = builder != null ? builder.mContext : null;
                if (context == null) {
                    return;
                }
                IconCompat iconCompat = this.mTrackerIcon;
                if (iconCompat != null) {
                    bundle.putParcelable(NotificationCompat.EXTRA_PROGRESS_TRACKER_ICON, iconCompat.toIcon(context));
                } else {
                    bundle.remove(NotificationCompat.EXTRA_PROGRESS_TRACKER_ICON);
                }
                IconCompat iconCompat2 = this.mStartIcon;
                if (iconCompat2 != null) {
                    bundle.putParcelable(NotificationCompat.EXTRA_PROGRESS_START_ICON, iconCompat2.toIcon(context));
                } else {
                    bundle.remove(NotificationCompat.EXTRA_PROGRESS_START_ICON);
                }
                IconCompat iconCompat3 = this.mEndIcon;
                if (iconCompat3 != null) {
                    bundle.putParcelable(NotificationCompat.EXTRA_PROGRESS_END_ICON, iconCompat3.toIcon(context));
                } else {
                    bundle.remove(NotificationCompat.EXTRA_PROGRESS_END_ICON);
                }
            }
        }

        public ProgressStyle addProgressPoint(Point point) {
            if (this.mProgressPoints == null) {
                this.mProgressPoints = new ArrayList();
            }
            if (point.getPosition() > 0) {
                this.mProgressPoints.add(point);
            }
            return this;
        }

        public ProgressStyle addProgressSegment(Segment segment) {
            if (this.mProgressSegments == null) {
                this.mProgressSegments = new ArrayList();
            }
            if (segment.getLength() > 0) {
                this.mProgressSegments.add(segment);
            }
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.Builder builder = notificationBuilderWithBuilderAccessor.getBuilder();
            int i = Build.VERSION.SDK_INT;
            if (i < 36) {
                int progressMax = getProgressMax();
                builder.setProgress(progressMax, Math.min(this.mProgress, progressMax), this.mIndeterminate);
                return;
            }
            Context context = notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).getContext() : null;
            Notification.ProgressStyle progressStyleA = aja.a();
            Api36Impl.setStyledByProgress(progressStyleA, this.mIsStyledByProgress);
            Api36Impl.setProgress(progressStyleA, this.mProgress);
            Api36Impl.setProgressIndeterminate(progressStyleA, this.mIndeterminate);
            IconCompat iconCompat = this.mStartIcon;
            Api36Impl.setProgressStartIcon(progressStyleA, iconCompat != null ? iconCompat.toIcon(context) : null);
            IconCompat iconCompat2 = this.mEndIcon;
            Api36Impl.setProgressEndIcon(progressStyleA, iconCompat2 != null ? iconCompat2.toIcon(context) : null);
            IconCompat iconCompat3 = this.mTrackerIcon;
            Api36Impl.setProgressTrackerIcon(progressStyleA, iconCompat3 != null ? iconCompat3.toIcon(context) : null);
            List<Point> list = this.mProgressPoints;
            if (i >= 37) {
                Api37Impl.setProgressPoints(progressStyleA, list);
                Api37Impl.setProgressSegments(progressStyleA, this.mProgressSegments);
            } else {
                Api36Impl.setProgressPoints(progressStyleA, list);
                Api36Impl.setProgressSegments(progressStyleA, this.mProgressSegments);
            }
            builder.setStyle(progressStyleA);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_SEGMENTS);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS);
            bundle.remove(NotificationCompat.EXTRA_STYLED_BY_PROGRESS);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_TRACKER_ICON);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_START_ICON);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_END_ICON);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_POINTS);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public boolean displayCustomViewInline() {
            return true;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public int getProgress() {
            return this.mProgress;
        }

        public IconCompat getProgressEndIcon() {
            return this.mEndIcon;
        }

        public int getProgressMax() {
            List<Segment> list = this.mProgressSegments;
            if (list == null || list.isEmpty()) {
                return 100;
            }
            int i = 0;
            int iAddExact = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                int length = list.get(i2).getLength();
                if (length > 0) {
                    try {
                        iAddExact = Math.addExact(iAddExact, length);
                        i++;
                    } catch (ArithmeticException unused) {
                        return 100;
                    }
                }
            }
            if (i == 0) {
                return 100;
            }
            return iAddExact;
        }

        public List<Point> getProgressPoints() {
            return this.mProgressPoints;
        }

        public List<Segment> getProgressSegments() {
            return this.mProgressSegments;
        }

        public IconCompat getProgressStartIcon() {
            return this.mStartIcon;
        }

        public IconCompat getProgressTrackerIcon() {
            return this.mTrackerIcon;
        }

        public boolean isProgressIndeterminate() {
            return this.mIndeterminate;
        }

        public boolean isStyledByProgress() {
            return this.mIsStyledByProgress;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mProgressSegments = getProgressSegmentsFromBundleList(androidx.core.os.BundleCompat.getParcelableArrayList(bundle, NotificationCompat.EXTRA_PROGRESS_SEGMENTS, Bundle.class));
            this.mProgress = bundle.getInt(NotificationCompat.EXTRA_PROGRESS, 0);
            this.mIndeterminate = bundle.getBoolean(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE, false);
            this.mIsStyledByProgress = bundle.getBoolean(NotificationCompat.EXTRA_STYLED_BY_PROGRESS, true);
            this.mProgressPoints = getProgressPointsFromBundleList(androidx.core.os.BundleCompat.getParcelableArrayList(bundle, NotificationCompat.EXTRA_PROGRESS_POINTS, Bundle.class));
            this.mTrackerIcon = asIconCompat((Parcelable) androidx.core.os.BundleCompat.getParcelable(bundle, NotificationCompat.EXTRA_PROGRESS_TRACKER_ICON, Icon.class));
            this.mStartIcon = asIconCompat((Parcelable) androidx.core.os.BundleCompat.getParcelable(bundle, NotificationCompat.EXTRA_PROGRESS_START_ICON, Icon.class));
            this.mEndIcon = asIconCompat((Parcelable) androidx.core.os.BundleCompat.getParcelable(bundle, NotificationCompat.EXTRA_PROGRESS_END_ICON, Icon.class));
        }

        public ProgressStyle setProgress(int i) {
            this.mProgress = i;
            return this;
        }

        public ProgressStyle setProgressEndIcon(IconCompat iconCompat) {
            this.mEndIcon = iconCompat;
            return this;
        }

        public ProgressStyle setProgressIndeterminate(boolean z) {
            this.mIndeterminate = z;
            return this;
        }

        public ProgressStyle setProgressPoints(List<Point> list) {
            if (this.mProgressPoints == null) {
                this.mProgressPoints = new ArrayList();
            }
            this.mProgressPoints.clear();
            Iterator<Point> it = list.iterator();
            while (it.hasNext()) {
                addProgressPoint(it.next());
            }
            return this;
        }

        public ProgressStyle setProgressSegments(List<Segment> list) {
            if (this.mProgressSegments == null) {
                this.mProgressSegments = new ArrayList();
            }
            this.mProgressSegments.clear();
            Iterator<Segment> it = list.iterator();
            while (it.hasNext()) {
                addProgressSegment(it.next());
            }
            return this;
        }

        public ProgressStyle setProgressStartIcon(IconCompat iconCompat) {
            this.mStartIcon = iconCompat;
            return this;
        }

        public ProgressStyle setProgressTrackerIcon(IconCompat iconCompat) {
            this.mTrackerIcon = iconCompat;
            return this;
        }

        public ProgressStyle setStyledByProgress(boolean z) {
            this.mIsStyledByProgress = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SemanticStyle {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceNotificationBehavior {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StreamType {
    }

    @Deprecated
    public NotificationCompat() {
    }

    public static ParcelableSpan createSemanticStyleAnnotation(int i) {
        return new Annotation(ANNOTATION_SEMANTIC_STYLE_KEY, String.valueOf(i));
    }

    public static Action getAction(Notification notification, int i) {
        return getActionCompatFromAction(notification.actions[i]);
    }

    public static Action getActionCompatFromAction(Notification.Action action) {
        RemoteInput[] remoteInputArr;
        int i;
        int styleHint;
        int i2;
        android.app.RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i3 = 0; i3 < remoteInputs.length; i3++) {
                android.app.RemoteInput remoteInput = remoteInputs[i3];
                remoteInputArr2[i3] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), Api29Impl.getEditChoicesBeforeSending(remoteInput), remoteInput.getExtras(), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        boolean z = action.getExtras().getBoolean("android.support.allowGeneratedReplies") || Api24Impl.getAllowGeneratedReplies(action);
        boolean z2 = action.getExtras().getBoolean("android.support.action.showsUserInterface", true);
        int semanticAction = Api28Impl.getSemanticAction(action);
        boolean zIsContextual = Api29Impl.isContextual(action);
        if (Build.VERSION.SDK_INT >= 37) {
            int emphasisHint = Action.Builder.Api37Impl.getEmphasisHint(action);
            styleHint = Action.Builder.Api37Impl.getStyleHint(action);
            i = emphasisHint;
        } else {
            i = 0;
            styleHint = 0;
        }
        boolean zIsAuthenticationRequired = Action.Builder.Api31Impl.isAuthenticationRequired(action);
        if (action.getIcon() != null || (i2 = action.icon) == 0) {
            return new Action(action.getIcon() != null ? IconCompat.createFromIconOrNullIfZeroResId(action.getIcon()) : null, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, zIsContextual, i, styleHint, zIsAuthenticationRequired);
        }
        return new Action(i2, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, zIsContextual, i, styleHint, zIsAuthenticationRequired);
    }

    public static int getActionCount(Notification notification) {
        Notification.Action[] actionArr = notification.actions;
        if (actionArr != null) {
            return actionArr.length;
        }
        return 0;
    }

    public static boolean getAllowSystemGeneratedContextualActions(Notification notification) {
        return Api29Impl.getAllowSystemGeneratedContextualActions(notification);
    }

    public static boolean getAutoCancel(Notification notification) {
        return (notification.flags & 16) != 0;
    }

    public static int getBadgeIconType(Notification notification) {
        return Api26Impl.getBadgeIconType(notification);
    }

    public static BubbleMetadata getBubbleMetadata(Notification notification) {
        return BubbleMetadata.fromPlatform(Api29Impl.getBubbleMetadata(notification));
    }

    public static String getCategory(Notification notification) {
        return notification.category;
    }

    public static String getChannelId(Notification notification) {
        return Api26Impl.getChannelId(notification);
    }

    public static int getColor(Notification notification) {
        return notification.color;
    }

    public static CharSequence getContentInfo(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_INFO_TEXT);
    }

    public static CharSequence getContentText(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TEXT);
    }

    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    @ReplaceWith(expression = "notification.extras")
    @Deprecated
    public static Bundle getExtras(Notification notification) {
        return notification.extras;
    }

    public static String getGroup(Notification notification) {
        return notification.getGroup();
    }

    public static int getGroupAlertBehavior(Notification notification) {
        return Api26Impl.getGroupAlertBehavior(notification);
    }

    public static boolean getHighPriority(Notification notification) {
        return (notification.flags & 128) != 0;
    }

    public static List<Action> getInvisibleActions(Notification notification) {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = notification.extras.getBundle("android.car.EXTENSIONS");
        if (bundle2 != null && (bundle = bundle2.getBundle("invisible_actions")) != null) {
            for (int i = 0; i < bundle.size(); i++) {
                arrayList.add(NotificationCompatJellybean.getActionFromBundle(bundle.getBundle(Integer.toString(i))));
            }
        }
        return arrayList;
    }

    public static boolean getLocalOnly(Notification notification) {
        return (notification.flags & 256) != 0;
    }

    public static LocusIdCompat getLocusId(Notification notification) {
        LocusId locusId = Api29Impl.getLocusId(notification);
        if (locusId == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(locusId);
    }

    public static Notification[] getNotificationArrayFromBundle(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Notification[] notificationArr = new Notification[parcelableArray.length];
        for (int i = 0; i < parcelableArray.length; i++) {
            notificationArr[i] = (Notification) parcelableArray[i];
        }
        bundle.putParcelableArray(str, notificationArr);
        return notificationArr;
    }

    public static boolean getOngoing(Notification notification) {
        return (notification.flags & 2) != 0;
    }

    public static boolean getOnlyAlertOnce(Notification notification) {
        return (notification.flags & 8) != 0;
    }

    public static List<Person> getPeople(Notification notification) {
        ArrayList arrayList = new ArrayList();
        ArrayList parcelableArrayList = notification.extras.getParcelableArrayList(EXTRA_PEOPLE_LIST);
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                arrayList.add(Person.fromAndroidPerson((android.app.Person) it.next()));
            }
        }
        return arrayList;
    }

    public static Notification getPublicVersion(Notification notification) {
        return notification.publicVersion;
    }

    public static CharSequence getSettingsText(Notification notification) {
        return Api26Impl.getSettingsText(notification);
    }

    public static String getShortCriticalText(Notification notification) {
        return notification.extras.getString(EXTRA_SHORT_CRITICAL_TEXT);
    }

    public static String getShortcutId(Notification notification) {
        return Api26Impl.getShortcutId(notification);
    }

    public static boolean getShowWhen(Notification notification) {
        return notification.extras.getBoolean(EXTRA_SHOW_WHEN);
    }

    public static String getSortKey(Notification notification) {
        return notification.getSortKey();
    }

    public static CharSequence getSubText(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_SUB_TEXT);
    }

    public static long getTimeoutAfter(Notification notification) {
        return Api26Impl.getTimeoutAfter(notification);
    }

    public static boolean getUsesChronometer(Notification notification) {
        return notification.extras.getBoolean(EXTRA_SHOW_CHRONOMETER);
    }

    public static int getVisibility(Notification notification) {
        return notification.visibility;
    }

    public static boolean hasPromotableCharacteristics(Notification notification) {
        if (Build.VERSION.SDK_INT >= 36) {
            return Api36Impl.hasPromotableCharacteristics(notification);
        }
        return false;
    }

    public static boolean isGroupSummary(Notification notification) {
        return (notification.flags & 512) != 0;
    }

    public static boolean isRequestPromotedOngoing(Notification notification) {
        return notification.extras.getBoolean(EXTRA_REQUEST_PROMOTED_ONGOING, false);
    }

    public static boolean isSilent(Notification notification) {
        if (GROUP_KEY_SILENT.equals(notification.getGroup())) {
            return true;
        }
        if (isGroupSummary(notification)) {
            return getGroupAlertBehavior(notification) == 2;
        }
        return getGroupAlertBehavior(notification) == 1;
    }

    public static Bitmap reduceLargeIconSize(Context context, Bitmap bitmap) {
        return bitmap;
    }

    public static final class Metric {
        private static final String KEY_LABEL = "label";
        private static final String KEY_SEMANTIC_STYLE = "semanticStyle";
        private static final String KEY_VALUE = "value";
        private final String mLabel;
        private final int mSemanticStyle;
        private final MetricValue mValue;

        public static final class FixedTime extends MetricValue {
            private static final String KEY_VALUE = "value";
            private final LocalTime mValue;

            public FixedTime(LocalTime localTime) {
                super();
                Objects.requireNonNull(localTime);
                this.mValue = localTime.truncatedTo(ChronoUnit.SECONDS);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static FixedTime fromBundle(Bundle bundle) {
                LocalTime localTimeOfSecondOfDay = bundle.containsKey(KEY_VALUE) ? LocalTime.ofSecondOfDay(bundle.getLong(KEY_VALUE)) : null;
                if (localTimeOfSecondOfDay != null) {
                    return new FixedTime(localTimeOfSecondOfDay);
                }
                return null;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof FixedTime)) {
                    return false;
                }
                FixedTime fixedTime = (FixedTime) obj;
                if (this == fixedTime) {
                    return true;
                }
                return Objects.equals(this.mValue, fixedTime.mValue);
            }

            public LocalTime getValue() {
                return this.mValue;
            }

            public int hashCode() {
                return Objects.hash(this.mValue);
            }

            @Override // androidx.core.app.NotificationCompat.Metric.MetricValue
            public void toBundle(Bundle bundle) {
                bundle.putLong(KEY_VALUE, this.mValue.toSecondOfDay());
            }

            public String toString() {
                return FixedTime.class.getSimpleName() + "{mValue=" + this.mValue + "}";
            }
        }

        public Metric(MetricValue metricValue, CharSequence charSequence, int i) {
            Objects.requireNonNull(metricValue);
            this.mValue = metricValue;
            Objects.requireNonNull(charSequence);
            String strSafeCharSequenceToString = Builder.safeCharSequenceToString(charSequence);
            this.mLabel = strSafeCharSequenceToString;
            Preconditions.checkArgument(!strSafeCharSequenceToString.isBlank(), "Metric label is required");
            this.mSemanticStyle = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Metric fromBundle(Bundle bundle) {
            MetricValue metricValueFromBundle;
            Bundle bundle2 = bundle.getBundle(KEY_VALUE);
            if (bundle2 == null || (metricValueFromBundle = MetricValue.fromBundle(bundle2)) == null) {
                return null;
            }
            return new Metric(metricValueFromBundle, bundle.getString(KEY_LABEL), bundle.getInt(KEY_SEMANTIC_STYLE, 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bundle toBundle(Metric metric) {
            Bundle bundle = new Bundle();
            bundle.putBundle(KEY_VALUE, MetricValue.toBundle(metric.mValue));
            bundle.putString(KEY_LABEL, metric.mLabel);
            bundle.putInt(KEY_SEMANTIC_STYLE, metric.mSemanticStyle);
            return bundle;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Metric)) {
                return false;
            }
            Metric metric = (Metric) obj;
            if (this == metric) {
                return true;
            }
            return Objects.equals(this.mValue, metric.mValue) && Objects.equals(this.mLabel, metric.mLabel) && this.mSemanticStyle == metric.mSemanticStyle;
        }

        public CharSequence getLabel() {
            return this.mLabel;
        }

        public int getSemanticStyle() {
            return this.mSemanticStyle;
        }

        public MetricValue getValue() {
            return this.mValue;
        }

        public int hashCode() {
            return Objects.hash(this.mValue, this.mLabel, Integer.valueOf(this.mSemanticStyle));
        }

        public String toString() {
            return "Metric{mValue=" + this.mValue + ", mLabel=" + this.mLabel + ", mSemanticStyle=" + this.mSemanticStyle + "}";
        }

        public static abstract class MetricValue {
            private static final String KEY_TYPE = "_type";
            private static final int TYPE_FIXED_DATE = 2;
            private static final int TYPE_FIXED_FLOAT = 5;
            private static final int TYPE_FIXED_INT = 4;
            private static final int TYPE_FIXED_TEXT = 6;
            private static final int TYPE_FIXED_TIME = 3;
            private static final int TYPE_TIME_DIFFERENCE = 1;

            /* JADX INFO: Access modifiers changed from: private */
            public static MetricValue fromBundle(Bundle bundle) {
                switch (bundle.getInt(KEY_TYPE)) {
                    case 1:
                        return TimeDifference.fromBundle(bundle);
                    case 2:
                        return FixedDate.fromBundle(bundle);
                    case 3:
                        return FixedTime.fromBundle(bundle);
                    case 4:
                        return FixedInt.fromBundle(bundle);
                    case 5:
                        return FixedFloat.fromBundle(bundle);
                    case 6:
                        return FixedText.fromBundle(bundle);
                    default:
                        return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Bundle toBundle(MetricValue metricValue) {
                Bundle bundle = new Bundle();
                if (metricValue instanceof TimeDifference) {
                    bundle.putInt(KEY_TYPE, 1);
                } else if (metricValue instanceof FixedDate) {
                    bundle.putInt(KEY_TYPE, 2);
                } else if (metricValue instanceof FixedTime) {
                    bundle.putInt(KEY_TYPE, 3);
                } else if (metricValue instanceof FixedInt) {
                    bundle.putInt(KEY_TYPE, 4);
                } else if (metricValue instanceof FixedFloat) {
                    bundle.putInt(KEY_TYPE, 5);
                } else {
                    if (!(metricValue instanceof FixedText)) {
                        s22.a("Impossible MetricValue subclass: ", metricValue);
                        return null;
                    }
                    bundle.putInt(KEY_TYPE, 6);
                }
                metricValue.toBundle(bundle);
                return bundle;
            }

            public abstract void toBundle(Bundle bundle);

            private MetricValue() {
            }
        }

        public static final class FixedInt extends MetricValue {
            private static final String KEY_UNIT = "unit";
            private static final String KEY_VALUE = "value";
            private final String mUnit;
            private final int mValue;

            public FixedInt(int i, CharSequence charSequence) {
                super();
                this.mValue = i;
                this.mUnit = Builder.safeCharSequenceToString(charSequence);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static FixedInt fromBundle(Bundle bundle) {
                return new FixedInt(bundle.getInt(KEY_VALUE), bundle.getString(KEY_UNIT));
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof FixedInt)) {
                    return false;
                }
                FixedInt fixedInt = (FixedInt) obj;
                if (this == fixedInt) {
                    return true;
                }
                return this.mValue == fixedInt.mValue && Objects.equals(this.mUnit, fixedInt.mUnit);
            }

            public CharSequence getUnit() {
                return this.mUnit;
            }

            public int getValue() {
                return this.mValue;
            }

            public int hashCode() {
                return Objects.hash(Integer.valueOf(this.mValue), this.mUnit);
            }

            @Override // androidx.core.app.NotificationCompat.Metric.MetricValue
            public void toBundle(Bundle bundle) {
                bundle.putInt(KEY_VALUE, this.mValue);
                bundle.putString(KEY_UNIT, this.mUnit);
            }

            public String toString() {
                return FixedInt.class.getSimpleName() + "{mValue=" + this.mValue + ", mUnit=" + this.mUnit + "}";
            }

            public FixedInt(int i) {
                this(i, null);
            }
        }

        public static final class TimeDifference extends MetricValue {
            public static final int FORMAT_ADAPTIVE = 1;
            public static final int FORMAT_CHRONOMETER = 2;
            private static final String KEY_COUNT_DOWN = "countDown";
            private static final String KEY_FORMAT = "format";
            private static final String KEY_PAUSED_DURATION = "pausedDuration";
            private static final String KEY_ZERO_ELAPSED_REALTIME = "zeroElapsedRealtime";
            private static final String KEY_ZERO_TIME = "zeroTime";
            private final boolean mCountDown;
            private final int mFormat;
            private final Duration mPausedDuration;
            private final Long mZeroElapsedRealtime;
            private final Instant mZeroTime;

            @Retention(RetentionPolicy.SOURCE)
            public @interface Format {
            }

            private TimeDifference(Instant instant, Long l, Duration duration, boolean z, int i) {
                super();
                boolean z2 = false;
                Preconditions.checkArgument(((instant != null ? 1 : 0) + (l != null ? 1 : 0)) + (duration != null ? 1 : 0) == 1, "Exactly one of zeroTime, zeroElapsedRealtime, or pausedDuration must be present; received %s,%s,%s", instant, l, duration);
                if (i >= 1 && i <= 2) {
                    z2 = true;
                }
                Preconditions.checkArgument(z2, "Invalid format: %s", Integer.valueOf(i));
                this.mZeroTime = instant;
                this.mZeroElapsedRealtime = l;
                this.mPausedDuration = duration;
                this.mCountDown = z;
                this.mFormat = i;
            }

            public static TimeDifference forPausedStopwatch(Duration duration, int i) {
                Objects.requireNonNull(duration);
                return new TimeDifference(null, null, duration, false, i);
            }

            public static TimeDifference forPausedTimer(Duration duration, int i) {
                Objects.requireNonNull(duration);
                return new TimeDifference(null, null, duration, true, i);
            }

            public static TimeDifference forStopwatch(Instant instant, int i) {
                Objects.requireNonNull(instant);
                return new TimeDifference(instant, null, null, false, i);
            }

            public static TimeDifference forTimer(Instant instant, int i) {
                Objects.requireNonNull(instant);
                return new TimeDifference(instant, null, null, true, i);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static TimeDifference fromBundle(Bundle bundle) {
                Instant instantOfEpochMilli = bundle.containsKey(KEY_ZERO_TIME) ? Instant.ofEpochMilli(bundle.getLong(KEY_ZERO_TIME)) : null;
                Long lValueOf = bundle.containsKey(KEY_ZERO_ELAPSED_REALTIME) ? Long.valueOf(bundle.getLong(KEY_ZERO_ELAPSED_REALTIME)) : null;
                Duration durationOfMillis = bundle.containsKey(KEY_PAUSED_DURATION) ? Duration.ofMillis(bundle.getLong(KEY_PAUSED_DURATION)) : null;
                if (instantOfEpochMilli == null && lValueOf == null && durationOfMillis == null) {
                    return null;
                }
                return new TimeDifference(instantOfEpochMilli, lValueOf, durationOfMillis, bundle.getBoolean(KEY_COUNT_DOWN), bundle.getInt(KEY_FORMAT));
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof TimeDifference)) {
                    return false;
                }
                TimeDifference timeDifference = (TimeDifference) obj;
                if (this == timeDifference) {
                    return true;
                }
                return Objects.equals(this.mZeroTime, timeDifference.mZeroTime) && Objects.equals(this.mZeroElapsedRealtime, timeDifference.mZeroElapsedRealtime) && Objects.equals(this.mPausedDuration, timeDifference.mPausedDuration) && this.mCountDown == timeDifference.mCountDown && this.mFormat == timeDifference.mFormat;
            }

            public int getFormat() {
                return this.mFormat;
            }

            public Duration getPausedDuration() {
                return this.mPausedDuration;
            }

            public Long getZeroElapsedRealtime() {
                return this.mZeroElapsedRealtime;
            }

            public Instant getZeroTime() {
                return this.mZeroTime;
            }

            public int hashCode() {
                return Objects.hash(this.mZeroTime, this.mZeroElapsedRealtime, this.mPausedDuration, Boolean.valueOf(this.mCountDown), Integer.valueOf(this.mFormat));
            }

            public boolean isStopwatch() {
                return !this.mCountDown;
            }

            public boolean isTimer() {
                return this.mCountDown;
            }

            @Override // androidx.core.app.NotificationCompat.Metric.MetricValue
            public void toBundle(Bundle bundle) {
                Instant instant = this.mZeroTime;
                if (instant != null) {
                    bundle.putLong(KEY_ZERO_TIME, instant.toEpochMilli());
                } else {
                    Long l = this.mZeroElapsedRealtime;
                    if (l != null) {
                        bundle.putLong(KEY_ZERO_ELAPSED_REALTIME, l.longValue());
                    } else {
                        Duration duration = this.mPausedDuration;
                        if (duration != null) {
                            bundle.putLong(KEY_PAUSED_DURATION, duration.toMillis());
                        }
                    }
                }
                bundle.putBoolean(KEY_COUNT_DOWN, this.mCountDown);
                bundle.putInt(KEY_FORMAT, this.mFormat);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder("TimeDifference{");
                if (this.mZeroTime != null) {
                    sb.append("mZeroTime=");
                    sb.append(this.mZeroTime);
                } else if (this.mZeroElapsedRealtime != null) {
                    sb.append("mZeroElapsedRealtime=");
                    sb.append(this.mZeroElapsedRealtime);
                } else if (this.mPausedDuration != null) {
                    sb.append("mPausedDuration=");
                    sb.append(this.mPausedDuration);
                }
                sb.append(", mCountDown=");
                sb.append(this.mCountDown);
                sb.append(", mFormat=");
                sb.append(this.mFormat);
                sb.append("}");
                return sb.toString();
            }

            public static TimeDifference forStopwatch(long j, int i) {
                return new TimeDifference(null, Long.valueOf(j), null, false, i);
            }

            public static TimeDifference forTimer(long j, int i) {
                return new TimeDifference(null, Long.valueOf(j), null, true, i);
            }
        }

        public static final class FixedText extends MetricValue {
            private static final String KEY_UNIT = "unit";
            private static final String KEY_VALUE = "value";
            private final String mUnit;
            private final String mValue;

            public FixedText(CharSequence charSequence, CharSequence charSequence2) {
                super();
                Objects.requireNonNull(charSequence);
                this.mValue = Builder.safeCharSequenceToString(charSequence);
                this.mUnit = Builder.safeCharSequenceToString(charSequence2);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static FixedText fromBundle(Bundle bundle) {
                return new FixedText(bundle.getString(KEY_VALUE, ""), bundle.getString(KEY_UNIT));
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof FixedText)) {
                    return false;
                }
                FixedText fixedText = (FixedText) obj;
                if (this == fixedText) {
                    return true;
                }
                return Objects.equals(this.mValue, fixedText.mValue) && Objects.equals(this.mUnit, fixedText.mUnit);
            }

            public CharSequence getUnit() {
                return this.mUnit;
            }

            public CharSequence getValue() {
                return this.mValue;
            }

            public int hashCode() {
                return Objects.hash(this.mValue, this.mUnit);
            }

            @Override // androidx.core.app.NotificationCompat.Metric.MetricValue
            public void toBundle(Bundle bundle) {
                bundle.putString(KEY_VALUE, this.mValue);
                bundle.putString(KEY_UNIT, this.mUnit);
            }

            public String toString() {
                return FixedText.class.getSimpleName() + "{mValue=" + this.mValue + ", mUnit=" + this.mUnit + "}";
            }

            public FixedText(CharSequence charSequence) {
                this(charSequence, null);
            }
        }

        public static final class FixedDate extends MetricValue {
            public static final int FORMAT_AUTOMATIC = 0;
            public static final int FORMAT_LONG_DATE = 1;
            public static final int FORMAT_SHORT_DATE = 2;
            private static final String KEY_FORMAT = "format";
            private static final String KEY_VALUE = "value";
            private final int mFormat;
            private final LocalDate mValue;

            @Retention(RetentionPolicy.SOURCE)
            public @interface Format {
            }

            public FixedDate(LocalDate localDate, int i) {
                super();
                Objects.requireNonNull(localDate);
                this.mValue = localDate;
                Preconditions.checkArgument(i >= 0 && i <= 2, "Invalid format: %s", Integer.valueOf(i));
                this.mFormat = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static FixedDate fromBundle(Bundle bundle) {
                LocalDate localDateOfEpochDay = bundle.containsKey(KEY_VALUE) ? LocalDate.ofEpochDay(bundle.getLong(KEY_VALUE)) : null;
                if (localDateOfEpochDay != null) {
                    return new FixedDate(localDateOfEpochDay, bundle.getInt(KEY_FORMAT, 0));
                }
                return null;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof FixedDate)) {
                    return false;
                }
                FixedDate fixedDate = (FixedDate) obj;
                if (this == fixedDate) {
                    return true;
                }
                return Objects.equals(this.mValue, fixedDate.mValue) && this.mFormat == fixedDate.mFormat;
            }

            public int getFormat() {
                return this.mFormat;
            }

            public LocalDate getValue() {
                return this.mValue;
            }

            public int hashCode() {
                return Objects.hash(this.mValue, Integer.valueOf(this.mFormat));
            }

            @Override // androidx.core.app.NotificationCompat.Metric.MetricValue
            public void toBundle(Bundle bundle) {
                bundle.putLong(KEY_VALUE, this.mValue.toEpochDay());
                bundle.putInt(KEY_FORMAT, this.mFormat);
            }

            public String toString() {
                return FixedDate.class.getSimpleName() + "{mValue=" + this.mValue + ", mFormat=" + this.mFormat + "}";
            }

            public FixedDate(LocalDate localDate) {
                this(localDate, 0);
            }
        }

        public Metric(MetricValue metricValue, CharSequence charSequence) {
            this(metricValue, charSequence, 0);
        }

        public static final class FixedFloat extends MetricValue {
            private static final int DEFAULT_MAX_FRACTION_DIGITS = 2;
            private static final int DEFAULT_MIN_FRACTION_DIGITS = 0;
            private static final String KEY_MAX_FRACTION_DIGITS = "maxDigits";
            private static final String KEY_MIN_FRACTION_DIGITS = "minDigits";
            private static final String KEY_UNIT = "unit";
            private static final String KEY_VALUE = "value";
            private static final int LOWER_BOUND_FRACTION_DIGITS = 0;
            private static final int UPPER_BOUND_FRACTION_DIGITS = 6;
            private final int mMaxFractionDigits;
            private final int mMinFractionDigits;
            private final String mUnit;
            private final float mValue;

            public FixedFloat(float f, CharSequence charSequence, int i, int i2) {
                super();
                this.mValue = f;
                this.mUnit = Builder.safeCharSequenceToString(charSequence);
                Preconditions.checkArgument(i >= 0 && i <= 6, "Invalid minFractionDigits: %s", Integer.valueOf(i));
                Preconditions.checkArgument(i2 >= 0 && i2 <= 6, "Invalid maxFractionDigits: %s", Integer.valueOf(i2));
                Preconditions.checkArgument(i <= i2, "Invalid minFractionDigits/maxFractionDigits: %s/%s", Integer.valueOf(i), Integer.valueOf(i2));
                this.mMinFractionDigits = i;
                this.mMaxFractionDigits = i2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static FixedFloat fromBundle(Bundle bundle) {
                return new FixedFloat(bundle.getFloat(KEY_VALUE), bundle.getString(KEY_UNIT), bundle.getInt(KEY_MIN_FRACTION_DIGITS, 0), bundle.getInt(KEY_MAX_FRACTION_DIGITS, 2));
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof FixedFloat)) {
                    return false;
                }
                FixedFloat fixedFloat = (FixedFloat) obj;
                if (this == fixedFloat) {
                    return true;
                }
                return this.mValue == fixedFloat.mValue && Objects.equals(this.mUnit, fixedFloat.mUnit) && this.mMinFractionDigits == fixedFloat.mMinFractionDigits && this.mMaxFractionDigits == fixedFloat.mMaxFractionDigits;
            }

            public int getMaxFractionDigits() {
                return this.mMaxFractionDigits;
            }

            public int getMinFractionDigits() {
                return this.mMinFractionDigits;
            }

            public CharSequence getUnit() {
                return this.mUnit;
            }

            public float getValue() {
                return this.mValue;
            }

            public int hashCode() {
                return Objects.hash(Float.valueOf(this.mValue), this.mUnit, Integer.valueOf(this.mMinFractionDigits), Integer.valueOf(this.mMaxFractionDigits));
            }

            @Override // androidx.core.app.NotificationCompat.Metric.MetricValue
            public void toBundle(Bundle bundle) {
                bundle.putFloat(KEY_VALUE, this.mValue);
                bundle.putString(KEY_UNIT, this.mUnit);
                bundle.putInt(KEY_MIN_FRACTION_DIGITS, this.mMinFractionDigits);
                bundle.putInt(KEY_MAX_FRACTION_DIGITS, this.mMaxFractionDigits);
            }

            public String toString() {
                return FixedFloat.class.getSimpleName() + "{mValue=" + this.mValue + ", mUnit=" + this.mUnit + ", mMinFractionDigits=" + this.mMinFractionDigits + ", mMaxFractionDigits=" + this.mMaxFractionDigits + "}";
            }

            public FixedFloat(float f, CharSequence charSequence) {
                this(f, charSequence, 0, 2);
            }

            public FixedFloat(float f) {
                this(f, null);
            }
        }
    }

    public static class BigPictureStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigPictureStyle";
        private IconCompat mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private CharSequence mPictureContentDescription;
        private IconCompat mPictureIcon;
        private boolean mShowBigPictureWhenCollapsed;

        public static class Api31Impl {
            private Api31Impl() {
            }

            public static void setBigPicture(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
                bigPictureStyle.bigPicture(icon);
            }

            public static void setContentDescription(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
                bigPictureStyle.setContentDescription(charSequence);
            }

            public static void showBigPictureWhenCollapsed(Notification.BigPictureStyle bigPictureStyle, boolean z) {
                bigPictureStyle.showBigPictureWhenCollapsed(z);
            }
        }

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        private static IconCompat asIconCompat(Parcelable parcelable) {
            if (parcelable == null) {
                return null;
            }
            if (parcelable instanceof Icon) {
                return IconCompat.createFromIcon((Icon) parcelable);
            }
            if (parcelable instanceof Bitmap) {
                return IconCompat.createWithBitmap((Bitmap) parcelable);
            }
            return null;
        }

        public static IconCompat getPictureIcon(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            Parcelable parcelable = bundle.getParcelable(NotificationCompat.EXTRA_PICTURE);
            return parcelable != null ? asIconCompat(parcelable) : asIconCompat(bundle.getParcelable(NotificationCompat.EXTRA_PICTURE_ICON));
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigPictureStyle bigContentTitle = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if (this.mPictureIcon != null) {
                Api31Impl.setBigPicture(bigContentTitle, this.mPictureIcon.toIcon(notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).getContext() : null));
            }
            if (this.mBigLargeIconSet) {
                if (this.mBigLargeIcon == null) {
                    bigContentTitle.bigLargeIcon((Bitmap) null);
                } else {
                    bigContentTitle.bigLargeIcon(this.mBigLargeIcon.toIcon(notificationBuilderWithBuilderAccessor instanceof NotificationCompatBuilder ? ((NotificationCompatBuilder) notificationBuilderWithBuilderAccessor).getContext() : null));
                }
            }
            if (this.mSummaryTextSet) {
                bigContentTitle.setSummaryText(this.mSummaryText);
            }
            Api31Impl.showBigPictureWhenCollapsed(bigContentTitle, this.mShowBigPictureWhenCollapsed);
            Api31Impl.setContentDescription(bigContentTitle, this.mPictureContentDescription);
        }

        public BigPictureStyle bigLargeIcon(Bitmap bitmap) {
            this.mBigLargeIcon = bitmap == null ? null : IconCompat.createWithBitmap(bitmap);
            this.mBigLargeIconSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap bitmap) {
            this.mPictureIcon = bitmap == null ? null : IconCompat.createWithBitmap(bitmap);
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_LARGE_ICON_BIG);
            bundle.remove(NotificationCompat.EXTRA_PICTURE);
            bundle.remove(NotificationCompat.EXTRA_PICTURE_ICON);
            bundle.remove(NotificationCompat.EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            if (bundle.containsKey(NotificationCompat.EXTRA_LARGE_ICON_BIG)) {
                this.mBigLargeIcon = asIconCompat(bundle.getParcelable(NotificationCompat.EXTRA_LARGE_ICON_BIG));
                this.mBigLargeIconSet = true;
            }
            this.mPictureIcon = getPictureIcon(bundle);
            this.mShowBigPictureWhenCollapsed = bundle.getBoolean(NotificationCompat.EXTRA_SHOW_BIG_PICTURE_WHEN_COLLAPSED);
        }

        public BigPictureStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigPictureStyle setContentDescription(CharSequence charSequence) {
            this.mPictureContentDescription = charSequence;
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle showBigPictureWhenCollapsed(boolean z) {
            this.mShowBigPictureWhenCollapsed = z;
            return this;
        }

        public BigPictureStyle() {
        }

        public BigPictureStyle bigPicture(Icon icon) {
            this.mPictureIcon = IconCompat.createFromIcon(icon);
            return this;
        }

        public BigPictureStyle bigLargeIcon(Icon icon) {
            this.mBigLargeIcon = icon == null ? null : IconCompat.createFromIcon(icon);
            this.mBigLargeIconSet = true;
            return this;
        }
    }

    public static class BigTextStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$BigTextStyle";
        private CharSequence mBigText;

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigTextStyle bigTextStyleBigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet) {
                bigTextStyleBigText.setSummaryText(this.mSummaryText);
            }
        }

        public BigTextStyle bigText(CharSequence charSequence) {
            this.mBigText = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_BIG_TEXT);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mBigText = bundle.getCharSequence(NotificationCompat.EXTRA_BIG_TEXT);
        }

        public BigTextStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle() {
        }
    }

    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        public ArrayList<Action> mActions;
        boolean mAllowSystemGeneratedContextualActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        BubbleMetadata mBubbleMetadata;
        String mCategory;
        String mChannelId;
        boolean mChronometerCountDown;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        int mFgsDeferBehavior;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList<Action> mInvisibleActions;
        IconCompat mLargeIcon;
        boolean mLocalOnly;
        LocusIdCompat mLocusId;
        Notification mNotification;
        int mNumber;

        @Deprecated
        public ArrayList<String> mPeople;
        public ArrayList<Person> mPersonList;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        CharSequence mSettingsText;
        String mShortCriticalText;
        String mShortcutId;
        boolean mShowWhen;
        boolean mSilent;
        Object mSmallIcon;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        public static class Api24Impl {
            private Api24Impl() {
            }

            public static RemoteViews createBigContentView(Notification.Builder builder) {
                return builder.createHeadsUpContentView();
            }

            public static RemoteViews createContentView(Notification.Builder builder) {
                return builder.createContentView();
            }

            public static RemoteViews createHeadsUpContentView(Notification.Builder builder) {
                return builder.createHeadsUpContentView();
            }

            public static Notification.Builder recoverBuilder(Context context, Notification notification) {
                return Notification.Builder.recoverBuilder(context, notification);
            }
        }

        public Builder(Context context, Notification notification) {
            this(context, NotificationCompat.getChannelId(notification));
            Bundle bundle = notification.extras;
            Style styleExtractStyleFromNotification = Style.extractStyleFromNotification(notification);
            setContentTitle(NotificationCompat.getContentTitle(notification)).setContentText(NotificationCompat.getContentText(notification)).setContentInfo(NotificationCompat.getContentInfo(notification)).setSubText(NotificationCompat.getSubText(notification)).setSettingsText(NotificationCompat.getSettingsText(notification)).setStyle(styleExtractStyleFromNotification).setGroup(NotificationCompat.getGroup(notification)).setGroupSummary(NotificationCompat.isGroupSummary(notification)).setLocusId(NotificationCompat.getLocusId(notification)).setWhen(notification.when).setShowWhen(NotificationCompat.getShowWhen(notification)).setUsesChronometer(NotificationCompat.getUsesChronometer(notification)).setAutoCancel(NotificationCompat.getAutoCancel(notification)).setOnlyAlertOnce(NotificationCompat.getOnlyAlertOnce(notification)).setOngoing(NotificationCompat.getOngoing(notification)).setLocalOnly(NotificationCompat.getLocalOnly(notification)).setLargeIcon(notification.largeIcon).setBadgeIconType(NotificationCompat.getBadgeIconType(notification)).setCategory(NotificationCompat.getCategory(notification)).setBubbleMetadata(NotificationCompat.getBubbleMetadata(notification)).setNumber(notification.number).setTicker(notification.tickerText).setContentIntent(notification.contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(notification.fullScreenIntent, NotificationCompat.getHighPriority(notification)).setSound(notification.sound, notification.audioStreamType).setSilent(NotificationCompat.isSilent(notification)).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setDefaults(notification.defaults).setPriority(notification.priority).setColor(NotificationCompat.getColor(notification)).setVisibility(NotificationCompat.getVisibility(notification)).setPublicVersion(NotificationCompat.getPublicVersion(notification)).setSortKey(NotificationCompat.getSortKey(notification)).setTimeoutAfter(NotificationCompat.getTimeoutAfter(notification)).setShortcutId(NotificationCompat.getShortcutId(notification)).setProgress(bundle.getInt(NotificationCompat.EXTRA_PROGRESS_MAX), bundle.getInt(NotificationCompat.EXTRA_PROGRESS), bundle.getBoolean(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE)).setAllowSystemGeneratedContextualActions(NotificationCompat.getAllowSystemGeneratedContextualActions(notification)).setSmallIcon(notification.icon, notification.iconLevel).addExtras(getExtrasWithoutDuplicateData(notification, styleExtractStyleFromNotification));
            this.mSmallIcon = notification.getSmallIcon();
            Icon largeIcon = notification.getLargeIcon();
            if (largeIcon != null) {
                this.mLargeIcon = IconCompat.createFromIcon(largeIcon);
            }
            Notification.Action[] actionArr = notification.actions;
            if (actionArr != null && actionArr.length != 0) {
                for (Notification.Action action : actionArr) {
                    addAction(Action.Builder.fromAndroidAction(action).build());
                }
            }
            List<Action> invisibleActions = NotificationCompat.getInvisibleActions(notification);
            if (!invisibleActions.isEmpty()) {
                Iterator<Action> it = invisibleActions.iterator();
                while (it.hasNext()) {
                    addInvisibleAction(it.next());
                }
            }
            String[] stringArray = notification.extras.getStringArray(NotificationCompat.EXTRA_PEOPLE);
            if (stringArray != null && stringArray.length != 0) {
                for (String str : stringArray) {
                    addPerson(str);
                }
            }
            ArrayList parcelableArrayList = notification.extras.getParcelableArrayList(NotificationCompat.EXTRA_PEOPLE_LIST);
            if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
                Iterator it2 = parcelableArrayList.iterator();
                while (it2.hasNext()) {
                    addPerson(Person.fromAndroidPerson((android.app.Person) it2.next()));
                }
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN)) {
                setChronometerCountDown(bundle.getBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_COLORIZED)) {
                setColorized(bundle.getBoolean(NotificationCompat.EXTRA_COLORIZED));
            }
            if (Build.VERSION.SDK_INT < 36 || !bundle.containsKey(NotificationCompat.EXTRA_SHORT_CRITICAL_TEXT)) {
                return;
            }
            setShortCriticalText(bundle.getString(NotificationCompat.EXTRA_SHORT_CRITICAL_TEXT));
        }

        private static Bundle getExtrasWithoutDuplicateData(Notification notification, Style style) {
            if (notification.extras == null) {
                return null;
            }
            Bundle bundle = new Bundle(notification.extras);
            bundle.remove(NotificationCompat.EXTRA_TITLE);
            bundle.remove(NotificationCompat.EXTRA_TEXT);
            bundle.remove(NotificationCompat.EXTRA_INFO_TEXT);
            bundle.remove(NotificationCompat.EXTRA_SUB_TEXT);
            if (Build.VERSION.SDK_INT >= 36) {
                bundle.remove(NotificationCompat.EXTRA_SHORT_CRITICAL_TEXT);
            }
            bundle.remove(NotificationCompat.EXTRA_CHANNEL_ID);
            bundle.remove(NotificationCompat.EXTRA_CHANNEL_GROUP_ID);
            bundle.remove(NotificationCompat.EXTRA_SHOW_WHEN);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_MAX);
            bundle.remove(NotificationCompat.EXTRA_PROGRESS_INDETERMINATE);
            bundle.remove(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN);
            bundle.remove(NotificationCompat.EXTRA_COLORIZED);
            bundle.remove(NotificationCompat.EXTRA_PEOPLE_LIST);
            bundle.remove(NotificationCompat.EXTRA_PEOPLE);
            bundle.remove(NotificationCompatExtras.EXTRA_SORT_KEY);
            bundle.remove(NotificationCompatExtras.EXTRA_GROUP_KEY);
            bundle.remove(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            bundle.remove(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            bundle.remove(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
            Bundle bundle2 = bundle.getBundle("android.car.EXTENSIONS");
            if (bundle2 != null) {
                Bundle bundle3 = new Bundle(bundle2);
                bundle3.remove("invisible_actions");
                bundle.putBundle("android.car.EXTENSIONS", bundle3);
            }
            if (style != null) {
                style.clearCompatExtraKeys(bundle);
            }
            return bundle;
        }

        public static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > MAX_CHARSEQUENCE_LENGTH) ? charSequence.subSequence(0, MAX_CHARSEQUENCE_LENGTH) : charSequence;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String safeCharSequenceToString(CharSequence charSequence) {
            if (charSequence == null) {
                return null;
            }
            return safeString(charSequence.toString());
        }

        private static String safeString(String str) {
            return (str != null && str.length() > MAX_CHARSEQUENCE_LENGTH) ? str.substring(0, MAX_CHARSEQUENCE_LENGTH) : str;
        }

        private void setFlag(int i, boolean z) {
            Notification notification = this.mNotification;
            if (z) {
                notification.flags = i | notification.flags;
            } else {
                notification.flags = (~i) & notification.flags;
            }
        }

        private boolean useExistingRemoteView() {
            Style style = this.mStyle;
            return style == null || !style.displayCustomViewInline();
        }

        public Builder addAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                Bundle bundle2 = this.mExtras;
                if (bundle2 == null) {
                    this.mExtras = new Bundle(bundle);
                    return this;
                }
                bundle2.putAll(bundle);
            }
            return this;
        }

        public Builder addInvisibleAction(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this.mInvisibleActions.add(new Action(i, charSequence, pendingIntent));
            return this;
        }

        @Deprecated
        public Builder addPerson(String str) {
            if (str != null && !str.isEmpty()) {
                this.mPeople.add(str);
            }
            return this;
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public Builder clearActions() {
            this.mActions.clear();
            return this;
        }

        public Builder clearInvisibleActions() {
            this.mInvisibleActions.clear();
            Bundle bundle = this.mExtras.getBundle("android.car.EXTENSIONS");
            if (bundle != null) {
                Bundle bundle2 = new Bundle(bundle);
                bundle2.remove("invisible_actions");
                this.mExtras.putBundle("android.car.EXTENSIONS", bundle2);
            }
            return this;
        }

        public Builder clearPeople() {
            this.mPersonList.clear();
            this.mPeople.clear();
            return this;
        }

        public RemoteViews createBigContentView() {
            RemoteViews remoteViewsMakeBigContentView;
            if (this.mBigContentView != null && useExistingRemoteView()) {
                return this.mBigContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            return (style == null || (remoteViewsMakeBigContentView = style.makeBigContentView(notificationCompatBuilder)) == null) ? Api24Impl.createBigContentView(Api24Impl.recoverBuilder(this.mContext, notificationCompatBuilder.build())) : remoteViewsMakeBigContentView;
        }

        public RemoteViews createContentView() {
            RemoteViews remoteViewsMakeContentView;
            if (this.mContentView != null && useExistingRemoteView()) {
                return this.mContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            return (style == null || (remoteViewsMakeContentView = style.makeContentView(notificationCompatBuilder)) == null) ? Api24Impl.createContentView(Api24Impl.recoverBuilder(this.mContext, notificationCompatBuilder.build())) : remoteViewsMakeContentView;
        }

        public RemoteViews createHeadsUpContentView() {
            RemoteViews remoteViewsMakeHeadsUpContentView;
            if (this.mHeadsUpContentView != null && useExistingRemoteView()) {
                return this.mHeadsUpContentView;
            }
            NotificationCompatBuilder notificationCompatBuilder = new NotificationCompatBuilder(this);
            Style style = this.mStyle;
            return (style == null || (remoteViewsMakeHeadsUpContentView = style.makeHeadsUpContentView(notificationCompatBuilder)) == null) ? Api24Impl.createHeadsUpContentView(Api24Impl.recoverBuilder(this.mContext, notificationCompatBuilder.build())) : remoteViewsMakeHeadsUpContentView;
        }

        public Builder extend(Extender extender) {
            extender.extend(this);
            return this;
        }

        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        public BubbleMetadata getBubbleMetadata() {
            return this.mBubbleMetadata;
        }

        public int getColor() {
            return this.mColor;
        }

        public RemoteViews getContentView() {
            return this.mContentView;
        }

        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }

        public int getForegroundServiceBehavior() {
            return this.mFgsDeferBehavior;
        }

        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public int getPriority() {
            return this.mPriority;
        }

        public long getWhenIfShowing() {
            if (this.mShowWhen) {
                return this.mNotification.when;
            }
            return 0L;
        }

        public Builder setAllowSystemGeneratedContextualActions(boolean z) {
            this.mAllowSystemGeneratedContextualActions = z;
            return this;
        }

        public Builder setAutoCancel(boolean z) {
            setFlag(16, z);
            return this;
        }

        public Builder setBadgeIconType(int i) {
            this.mBadgeIcon = i;
            return this;
        }

        public Builder setBubbleMetadata(BubbleMetadata bubbleMetadata) {
            this.mBubbleMetadata = bubbleMetadata;
            return this;
        }

        public Builder setCategory(String str) {
            this.mCategory = str;
            return this;
        }

        public Builder setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public Builder setChronometerCountDown(boolean z) {
            this.mChronometerCountDown = z;
            getExtras().putBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN, z);
            return this;
        }

        public Builder setColor(int i) {
            this.mColor = i;
            return this;
        }

        public Builder setColorized(boolean z) {
            this.mColorized = z;
            this.mColorizedSet = true;
            return this;
        }

        public Builder setContent(RemoteViews remoteViews) {
            this.mNotification.contentView = remoteViews;
            return this;
        }

        public Builder setContentInfo(CharSequence charSequence) {
            this.mContentInfo = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public Builder setContentText(CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setContentTitle(CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews remoteViews) {
            this.mBigContentView = remoteViews;
            return this;
        }

        public Builder setCustomContentView(RemoteViews remoteViews) {
            this.mContentView = remoteViews;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews remoteViews) {
            this.mHeadsUpContentView = remoteViews;
            return this;
        }

        public Builder setDefaults(int i) {
            Notification notification = this.mNotification;
            notification.defaults = i;
            if ((i & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        public Builder setDeleteIntent(PendingIntent pendingIntent) {
            this.mNotification.deleteIntent = pendingIntent;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setForegroundServiceBehavior(int i) {
            this.mFgsDeferBehavior = i;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent pendingIntent, boolean z) {
            this.mFullScreenIntent = pendingIntent;
            setFlag(128, z);
            return this;
        }

        public Builder setGroup(String str) {
            this.mGroupKey = str;
            return this;
        }

        public Builder setGroupAlertBehavior(int i) {
            this.mGroupAlertBehavior = i;
            return this;
        }

        public Builder setGroupSummary(boolean z) {
            this.mGroupSummary = z;
            return this;
        }

        public Builder setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap == null ? null : IconCompat.createWithBitmap(NotificationCompat.reduceLargeIconSize(this.mContext, bitmap));
            return this;
        }

        public Builder setLights(int i, int i2, int i3) {
            Notification notification = this.mNotification;
            notification.ledARGB = i;
            notification.ledOnMS = i2;
            notification.ledOffMS = i3;
            notification.flags = ((i2 == 0 || i3 == 0) ? 0 : 1) | (notification.flags & (-2));
            return this;
        }

        public Builder setLocalOnly(boolean z) {
            this.mLocalOnly = z;
            return this;
        }

        public Builder setLocusId(LocusIdCompat locusIdCompat) {
            this.mLocusId = locusIdCompat;
            return this;
        }

        @Deprecated
        public Builder setNotificationSilent() {
            this.mSilent = true;
            return this;
        }

        public Builder setNumber(int i) {
            this.mNumber = i;
            return this;
        }

        public Builder setOngoing(boolean z) {
            setFlag(2, z);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean z) {
            setFlag(8, z);
            return this;
        }

        public Builder setPriority(int i) {
            this.mPriority = i;
            return this;
        }

        public Builder setProgress(int i, int i2, boolean z) {
            this.mProgressMax = i;
            this.mProgress = i2;
            this.mProgressIndeterminate = z;
            return this;
        }

        public Builder setPublicVersion(Notification notification) {
            this.mPublicVersion = notification;
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] charSequenceArr) {
            this.mRemoteInputHistory = charSequenceArr;
            return this;
        }

        public Builder setRequestPromotedOngoing(boolean z) {
            getExtras().putBoolean(NotificationCompat.EXTRA_REQUEST_PROMOTED_ONGOING, z);
            return this;
        }

        public Builder setSettingsText(CharSequence charSequence) {
            this.mSettingsText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder setShortCriticalText(String str) {
            this.mShortCriticalText = str;
            if (Build.VERSION.SDK_INT < 36) {
                getExtras().putString(NotificationCompat.EXTRA_SHORT_CRITICAL_TEXT, str);
            }
            return this;
        }

        public Builder setShortcutId(String str) {
            this.mShortcutId = str;
            return this;
        }

        public Builder setShortcutInfo(ShortcutInfoCompat shortcutInfoCompat) {
            if (shortcutInfoCompat != null) {
                this.mShortcutId = shortcutInfoCompat.getId();
                if (this.mLocusId == null) {
                    if (shortcutInfoCompat.getLocusId() != null) {
                        this.mLocusId = shortcutInfoCompat.getLocusId();
                    } else if (shortcutInfoCompat.getId() != null) {
                        this.mLocusId = new LocusIdCompat(shortcutInfoCompat.getId());
                    }
                }
                if (this.mContentTitle == null) {
                    setContentTitle(shortcutInfoCompat.getShortLabel());
                }
            }
            return this;
        }

        public Builder setShowWhen(boolean z) {
            this.mShowWhen = z;
            return this;
        }

        public Builder setSilent(boolean z) {
            this.mSilent = z;
            return this;
        }

        public Builder setSmallIcon(IconCompat iconCompat) {
            this.mSmallIcon = iconCompat.toIcon(this.mContext);
            return this;
        }

        public Builder setSortKey(String str) {
            this.mSortKey = str;
            return this;
        }

        public Builder setSound(Uri uri) {
            Notification notification = this.mNotification;
            notification.sound = uri;
            notification.audioStreamType = -1;
            AudioAttributes.Builder usage = new AudioAttributes.Builder().setContentType(4).setUsage(5);
            this.mNotification.audioAttributes = usage.build();
            return this;
        }

        public Builder setStyle(Style style) {
            if (this.mStyle != style) {
                this.mStyle = style;
                if (style != null) {
                    style.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setSubText(CharSequence charSequence) {
            this.mSubText = limitCharSequenceLength(charSequence);
            return this;
        }

        @Deprecated
        public Builder setTicker(CharSequence charSequence, RemoteViews remoteViews) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            this.mTickerView = remoteViews;
            return this;
        }

        public Builder setTimeoutAfter(long j) {
            this.mTimeout = j;
            return this;
        }

        public Builder setUsesChronometer(boolean z) {
            this.mUseChronometer = z;
            return this;
        }

        public Builder setVibrate(long[] jArr) {
            this.mNotification.vibrate = jArr;
            return this;
        }

        public Builder setVisibility(int i) {
            this.mVisibility = i;
            return this;
        }

        public Builder setWhen(long j) {
            this.mNotification.when = j;
            return this;
        }

        public Builder setSmallIcon(int i) {
            this.mNotification.icon = i;
            return this;
        }

        public Builder setSmallIcon(int i, int i2) {
            Notification notification = this.mNotification;
            notification.icon = i;
            notification.iconLevel = i2;
            return this;
        }

        public Builder addAction(Action action) {
            if (action != null) {
                this.mActions.add(action);
            }
            return this;
        }

        public Builder addInvisibleAction(Action action) {
            if (action != null) {
                this.mInvisibleActions.add(action);
            }
            return this;
        }

        public Builder setTicker(CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }

        public Builder addPerson(Person person) {
            if (person != null) {
                this.mPersonList.add(person);
            }
            return this;
        }

        public Builder setLargeIcon(Icon icon) {
            this.mLargeIcon = icon == null ? null : IconCompat.createFromIcon(icon);
            return this;
        }

        public Builder setSound(Uri uri, int i) {
            Notification notification = this.mNotification;
            notification.sound = uri;
            notification.audioStreamType = i;
            AudioAttributes.Builder legacyStreamType = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(i);
            this.mNotification.audioAttributes = legacyStreamType.build();
            return this;
        }

        public Builder(Context context, String str) {
            this.mActions = new ArrayList<>();
            this.mPersonList = new ArrayList<>();
            this.mInvisibleActions = new ArrayList<>();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mFgsDeferBehavior = 0;
            Notification notification = new Notification();
            this.mNotification = notification;
            this.mContext = context;
            this.mChannelId = str;
            notification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<>();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        @Deprecated
        public Builder(Context context) {
            this(context, (String) null);
        }
    }

    public static class CallStyle extends Style {
        public static final int CALL_TYPE_INCOMING = 1;
        public static final int CALL_TYPE_ONGOING = 2;
        public static final int CALL_TYPE_SCREENING = 3;
        public static final int CALL_TYPE_UNKNOWN = 0;
        private static final String KEY_ACTION_PRIORITY = "key_action_priority";
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$CallStyle";
        private Integer mAnswerButtonColor;
        private PendingIntent mAnswerIntent;
        private int mCallType;
        private Integer mDeclineButtonColor;
        private PendingIntent mDeclineIntent;
        private PendingIntent mHangUpIntent;
        private boolean mIsVideo;
        private Person mPerson;
        private IconCompat mVerificationIcon;
        private CharSequence mVerificationText;

        public static class Api28Impl {
            private Api28Impl() {
            }

            public static Notification.Builder addPerson(Notification.Builder builder, android.app.Person person) {
                return builder.addPerson(person);
            }

            public static Parcelable castToParcelable(android.app.Person person) {
                return person;
            }
        }

        public static class Api31Impl {
            private Api31Impl() {
            }

            public static Notification.CallStyle forIncomingCall(android.app.Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
                return Notification.CallStyle.forIncomingCall(person, pendingIntent, pendingIntent2);
            }

            public static Notification.CallStyle forOngoingCall(android.app.Person person, PendingIntent pendingIntent) {
                return Notification.CallStyle.forOngoingCall(person, pendingIntent);
            }

            public static Notification.CallStyle forScreeningCall(android.app.Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
                return Notification.CallStyle.forScreeningCall(person, pendingIntent, pendingIntent2);
            }

            public static Notification.CallStyle setAnswerButtonColorHint(Notification.CallStyle callStyle, int i) {
                return callStyle.setAnswerButtonColorHint(i);
            }

            public static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder builder, boolean z) {
                return builder.setAuthenticationRequired(z);
            }

            public static Notification.CallStyle setDeclineButtonColorHint(Notification.CallStyle callStyle, int i) {
                return callStyle.setDeclineButtonColorHint(i);
            }

            public static Notification.CallStyle setIsVideo(Notification.CallStyle callStyle, boolean z) {
                return callStyle.setIsVideo(z);
            }

            public static Notification.CallStyle setVerificationIcon(Notification.CallStyle callStyle, Icon icon) {
                return callStyle.setVerificationIcon(icon);
            }

            public static Notification.CallStyle setVerificationText(Notification.CallStyle callStyle, CharSequence charSequence) {
                return callStyle.setVerificationText(charSequence);
            }
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface CallType {
        }

        private CallStyle(int i, Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3) {
            if (person == null || TextUtils.isEmpty(person.getName())) {
                w01.a("person must have a non-empty a name");
                throw null;
            }
            this.mCallType = i;
            this.mPerson = person;
            this.mAnswerIntent = pendingIntent3;
            this.mDeclineIntent = pendingIntent2;
            this.mHangUpIntent = pendingIntent;
        }

        public static CallStyle forIncomingCall(Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
            Objects.requireNonNull(pendingIntent, "declineIntent is required");
            Objects.requireNonNull(pendingIntent2, "answerIntent is required");
            return new CallStyle(1, person, null, pendingIntent, pendingIntent2);
        }

        public static CallStyle forOngoingCall(Person person, PendingIntent pendingIntent) {
            Objects.requireNonNull(pendingIntent, "hangUpIntent is required");
            return new CallStyle(2, person, pendingIntent, null, null);
        }

        public static CallStyle forScreeningCall(Person person, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
            Objects.requireNonNull(pendingIntent, "hangUpIntent is required");
            Objects.requireNonNull(pendingIntent2, "answerIntent is required");
            return new CallStyle(3, person, pendingIntent, null, pendingIntent2);
        }

        private String getDefaultText() {
            int i = this.mCallType;
            if (i == 1) {
                return this.mBuilder.mContext.getResources().getString(R.string.call_notification_incoming_text);
            }
            if (i == 2) {
                return this.mBuilder.mContext.getResources().getString(R.string.call_notification_ongoing_text);
            }
            if (i != 3) {
                return null;
            }
            return this.mBuilder.mContext.getResources().getString(R.string.call_notification_screening_text);
        }

        private boolean isActionAddedByCallStyle(Action action) {
            return action != null && action.getExtras().getBoolean(KEY_ACTION_PRIORITY);
        }

        private Action makeAction(int i, int i2, Integer num, int i3, PendingIntent pendingIntent) {
            if (num == null) {
                num = Integer.valueOf(ContextCompat.getColor(this.mBuilder.mContext, i3));
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) this.mBuilder.mContext.getResources().getString(i2));
            spannableStringBuilder.setSpan(new ForegroundColorSpan(num.intValue()), 0, spannableStringBuilder.length(), 18);
            Action actionBuild = new Action.Builder(IconCompat.createWithResource(this.mBuilder.mContext, i), spannableStringBuilder, pendingIntent).build();
            actionBuild.getExtras().putBoolean(KEY_ACTION_PRIORITY, true);
            return actionBuild;
        }

        private Action makeAnswerAction() {
            int i = R.drawable.ic_call_answer_video;
            int i2 = R.drawable.ic_call_answer;
            PendingIntent pendingIntent = this.mAnswerIntent;
            if (pendingIntent == null) {
                return null;
            }
            boolean z = this.mIsVideo;
            return makeAction(z ? i : i2, z ? R.string.call_notification_answer_video_action : R.string.call_notification_answer_action, this.mAnswerButtonColor, R.color.call_notification_answer_color, pendingIntent);
        }

        private Action makeNegativeAction() {
            int i = R.drawable.ic_call_decline;
            PendingIntent pendingIntent = this.mDeclineIntent;
            Integer num = this.mDeclineButtonColor;
            return pendingIntent == null ? makeAction(i, R.string.call_notification_hang_up_action, num, R.color.call_notification_decline_color, this.mHangUpIntent) : makeAction(i, R.string.call_notification_decline_action, num, R.color.call_notification_decline_color, pendingIntent);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putInt(NotificationCompat.EXTRA_CALL_TYPE, this.mCallType);
            bundle.putBoolean(NotificationCompat.EXTRA_CALL_IS_VIDEO, this.mIsVideo);
            Person person = this.mPerson;
            if (person != null) {
                bundle.putParcelable(NotificationCompat.EXTRA_CALL_PERSON, Api28Impl.castToParcelable(person.toAndroidPerson()));
            }
            IconCompat iconCompat = this.mVerificationIcon;
            if (iconCompat != null) {
                bundle.putParcelable(NotificationCompat.EXTRA_VERIFICATION_ICON, iconCompat.toIcon(this.mBuilder.mContext));
            }
            bundle.putCharSequence(NotificationCompat.EXTRA_VERIFICATION_TEXT, this.mVerificationText);
            bundle.putParcelable(NotificationCompat.EXTRA_ANSWER_INTENT, this.mAnswerIntent);
            bundle.putParcelable(NotificationCompat.EXTRA_DECLINE_INTENT, this.mDeclineIntent);
            bundle.putParcelable(NotificationCompat.EXTRA_HANG_UP_INTENT, this.mHangUpIntent);
            Integer num = this.mAnswerButtonColor;
            if (num != null) {
                bundle.putInt(NotificationCompat.EXTRA_ANSWER_COLOR, num.intValue());
            }
            Integer num2 = this.mDeclineButtonColor;
            if (num2 != null) {
                bundle.putInt(NotificationCompat.EXTRA_DECLINE_COLOR, num2.intValue());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.CallStyle callStyleForIncomingCall;
            int i = this.mCallType;
            if (i == 1) {
                callStyleForIncomingCall = Api31Impl.forIncomingCall(this.mPerson.toAndroidPerson(), this.mDeclineIntent, this.mAnswerIntent);
            } else if (i == 2) {
                callStyleForIncomingCall = Api31Impl.forOngoingCall(this.mPerson.toAndroidPerson(), this.mHangUpIntent);
            } else if (i != 3) {
                if (Log.isLoggable(NotificationCompat.TAG, 3)) {
                    Log.d(NotificationCompat.TAG, "Unrecognized call type in CallStyle: " + String.valueOf(this.mCallType));
                }
                callStyleForIncomingCall = null;
            } else {
                callStyleForIncomingCall = Api31Impl.forScreeningCall(this.mPerson.toAndroidPerson(), this.mHangUpIntent, this.mAnswerIntent);
            }
            if (callStyleForIncomingCall != null) {
                callStyleForIncomingCall.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
                Integer num = this.mAnswerButtonColor;
                if (num != null) {
                    Api31Impl.setAnswerButtonColorHint(callStyleForIncomingCall, num.intValue());
                }
                Integer num2 = this.mDeclineButtonColor;
                if (num2 != null) {
                    Api31Impl.setDeclineButtonColorHint(callStyleForIncomingCall, num2.intValue());
                }
                Api31Impl.setVerificationText(callStyleForIncomingCall, this.mVerificationText);
                IconCompat iconCompat = this.mVerificationIcon;
                if (iconCompat != null) {
                    Api31Impl.setVerificationIcon(callStyleForIncomingCall, iconCompat.toIcon(this.mBuilder.mContext));
                }
                Api31Impl.setIsVideo(callStyleForIncomingCall, this.mIsVideo);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public boolean displayCustomViewInline() {
            return true;
        }

        public ArrayList<Action> getActionsListWithSystemActions() {
            Action actionMakeNegativeAction = makeNegativeAction();
            Action actionMakeAnswerAction = makeAnswerAction();
            ArrayList<Action> arrayList = new ArrayList<>(3);
            arrayList.add(actionMakeNegativeAction);
            ArrayList<Action> arrayList2 = this.mBuilder.mActions;
            int i = 2;
            if (arrayList2 != null) {
                for (Action action : arrayList2) {
                    if (action.isContextual()) {
                        arrayList.add(action);
                    } else if (!isActionAddedByCallStyle(action)) {
                        arrayList.add(action);
                        i--;
                    }
                    if (actionMakeAnswerAction != null && i == 1) {
                        arrayList.add(actionMakeAnswerAction);
                        i--;
                    }
                }
            }
            if (actionMakeAnswerAction != null && i >= 1) {
                arrayList.add(actionMakeAnswerAction);
            }
            return arrayList;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mCallType = bundle.getInt(NotificationCompat.EXTRA_CALL_TYPE);
            this.mIsVideo = bundle.getBoolean(NotificationCompat.EXTRA_CALL_IS_VIDEO);
            if (bundle.containsKey(NotificationCompat.EXTRA_CALL_PERSON)) {
                this.mPerson = Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(NotificationCompat.EXTRA_CALL_PERSON));
            } else if (bundle.containsKey(NotificationCompat.EXTRA_CALL_PERSON_COMPAT)) {
                this.mPerson = Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_CALL_PERSON_COMPAT));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_VERIFICATION_ICON)) {
                this.mVerificationIcon = IconCompat.createFromIcon((Icon) bundle.getParcelable(NotificationCompat.EXTRA_VERIFICATION_ICON));
            } else if (bundle.containsKey(NotificationCompat.EXTRA_VERIFICATION_ICON_COMPAT)) {
                this.mVerificationIcon = IconCompat.createFromBundle(bundle.getBundle(NotificationCompat.EXTRA_VERIFICATION_ICON_COMPAT));
            }
            this.mVerificationText = bundle.getCharSequence(NotificationCompat.EXTRA_VERIFICATION_TEXT);
            this.mAnswerIntent = (PendingIntent) bundle.getParcelable(NotificationCompat.EXTRA_ANSWER_INTENT);
            this.mDeclineIntent = (PendingIntent) bundle.getParcelable(NotificationCompat.EXTRA_DECLINE_INTENT);
            this.mHangUpIntent = (PendingIntent) bundle.getParcelable(NotificationCompat.EXTRA_HANG_UP_INTENT);
            this.mAnswerButtonColor = bundle.containsKey(NotificationCompat.EXTRA_ANSWER_COLOR) ? Integer.valueOf(bundle.getInt(NotificationCompat.EXTRA_ANSWER_COLOR)) : null;
            this.mDeclineButtonColor = bundle.containsKey(NotificationCompat.EXTRA_DECLINE_COLOR) ? Integer.valueOf(bundle.getInt(NotificationCompat.EXTRA_DECLINE_COLOR)) : null;
        }

        public CallStyle setAnswerButtonColorHint(int i) {
            this.mAnswerButtonColor = Integer.valueOf(i);
            return this;
        }

        public CallStyle setDeclineButtonColorHint(int i) {
            this.mDeclineButtonColor = Integer.valueOf(i);
            return this;
        }

        public CallStyle setIsVideo(boolean z) {
            this.mIsVideo = z;
            return this;
        }

        public CallStyle setVerificationIcon(Icon icon) {
            this.mVerificationIcon = icon == null ? null : IconCompat.createFromIcon(icon);
            return this;
        }

        public CallStyle setVerificationText(CharSequence charSequence) {
            this.mVerificationText = charSequence;
            return this;
        }

        public CallStyle setVerificationIcon(Bitmap bitmap) {
            this.mVerificationIcon = IconCompat.createWithBitmap(bitmap);
            return this;
        }

        public CallStyle(Builder builder) {
            setBuilder(builder);
        }

        public CallStyle() {
        }
    }

    public static class InboxStyle extends Style {
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$InboxStyle";
        private ArrayList<CharSequence> mTexts = new ArrayList<>();

        public InboxStyle(Builder builder) {
            setBuilder(builder);
        }

        public InboxStyle addLine(CharSequence charSequence) {
            if (charSequence != null) {
                this.mTexts.add(Builder.limitCharSequenceLength(charSequence));
            }
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if (this.mSummaryTextSet) {
                bigContentTitle.setSummaryText(this.mSummaryText);
            }
            Iterator<CharSequence> it = this.mTexts.iterator();
            while (it.hasNext()) {
                bigContentTitle.addLine(it.next());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_TEXT_LINES);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mTexts.clear();
            if (bundle.containsKey(NotificationCompat.EXTRA_TEXT_LINES)) {
                Collections.addAll(this.mTexts, bundle.getCharSequenceArray(NotificationCompat.EXTRA_TEXT_LINES));
            }
        }

        public InboxStyle setBigContentTitle(CharSequence charSequence) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(charSequence);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence charSequence) {
            this.mSummaryText = Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle() {
        }
    }

    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        private static final String TEMPLATE_CLASS_NAME = "androidx.core.app.NotificationCompat$MessagingStyle";
        private CharSequence mConversationTitle;
        private Boolean mIsGroupConversation;
        private Person mUser;
        private final List<Message> mMessages = new ArrayList();
        private final List<Message> mHistoricMessages = new ArrayList();

        public static class Api24Impl {
            private Api24Impl() {
            }

            public static Notification.MessagingStyle addMessage(Notification.MessagingStyle messagingStyle, Notification.MessagingStyle.Message message) {
                return messagingStyle.addMessage(message);
            }

            public static Notification.MessagingStyle createMessagingStyle(CharSequence charSequence) {
                return new Notification.MessagingStyle(charSequence);
            }

            public static Notification.MessagingStyle setConversationTitle(Notification.MessagingStyle messagingStyle, CharSequence charSequence) {
                return messagingStyle.setConversationTitle(charSequence);
            }
        }

        public static class Api26Impl {
            private Api26Impl() {
            }

            public static Notification.MessagingStyle addHistoricMessage(Notification.MessagingStyle messagingStyle, Notification.MessagingStyle.Message message) {
                return messagingStyle.addHistoricMessage(message);
            }
        }

        public static class Api28Impl {
            private Api28Impl() {
            }

            public static Notification.MessagingStyle createMessagingStyle(android.app.Person person) {
                return new Notification.MessagingStyle(person);
            }

            public static Person getUserPerson(Bundle bundle, String str) {
                Parcelable parcelable = bundle.getParcelable(str);
                if (parcelable instanceof android.app.Person) {
                    return Person.fromAndroidPerson((android.app.Person) parcelable);
                }
                return null;
            }

            public static Notification.MessagingStyle setGroupConversation(Notification.MessagingStyle messagingStyle, boolean z) {
                return messagingStyle.setGroupConversation(z);
            }
        }

        public MessagingStyle(Person person) {
            if (TextUtils.isEmpty(person.getName())) {
                w01.a("User's name must not be empty.");
                throw null;
            }
            this.mUser = person;
        }

        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            Style styleExtractStyleFromNotification = Style.extractStyleFromNotification(notification);
            if (styleExtractStyleFromNotification instanceof MessagingStyle) {
                return (MessagingStyle) styleExtractStyleFromNotification;
            }
            return null;
        }

        private Message findLatestIncomingMessage() {
            int size = this.mMessages.size();
            while (true) {
                size--;
                List<Message> list = this.mMessages;
                if (size < 0) {
                    if (list.isEmpty()) {
                        return null;
                    }
                    List<Message> list2 = this.mMessages;
                    return list2.get(list2.size() - 1);
                }
                Message message = list.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
        }

        private boolean hasMessagesWithoutSender() {
            for (int size = this.mMessages.size() - 1; size >= 0; size--) {
                Message message = this.mMessages.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        private TextAppearanceSpan makeFontColorSpan(int i) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
        }

        private CharSequence makeMessageLine(Message message) {
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            CharSequence name = message.getPerson() == null ? "" : message.getPerson().getName();
            int color = -16777216;
            if (TextUtils.isEmpty(name)) {
                name = this.mUser.getName();
                if (this.mBuilder.getColor() != 0) {
                    color = this.mBuilder.getColor();
                }
            }
            CharSequence charSequenceUnicodeWrap = bidiFormatter.unicodeWrap(name);
            spannableStringBuilder.append(charSequenceUnicodeWrap);
            spannableStringBuilder.setSpan(makeFontColorSpan(color), spannableStringBuilder.length() - charSequenceUnicodeWrap.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append((CharSequence) "  ").append(bidiFormatter.unicodeWrap(message.getText() != null ? message.getText() : ""));
            return spannableStringBuilder;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle bundle) {
            super.addCompatExtras(bundle);
        }

        public MessagingStyle addHistoricMessage(Message message) {
            if (message != null) {
                this.mHistoricMessages.add(message);
                if (this.mHistoricMessages.size() > 25) {
                    this.mHistoricMessages.remove(0);
                }
            }
            return this;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
            this.mMessages.add(new Message(charSequence, j, new Person.Builder().setName(charSequence2).build()));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            setGroupConversation(isGroupConversation());
            Notification.MessagingStyle messagingStyleCreateMessagingStyle = Api28Impl.createMessagingStyle(this.mUser.toAndroidPerson());
            Iterator<Message> it = this.mMessages.iterator();
            while (it.hasNext()) {
                Api24Impl.addMessage(messagingStyleCreateMessagingStyle, it.next().toAndroidMessage());
            }
            Iterator<Message> it2 = this.mHistoricMessages.iterator();
            while (it2.hasNext()) {
                Api26Impl.addHistoricMessage(messagingStyleCreateMessagingStyle, it2.next().toAndroidMessage());
            }
            this.mIsGroupConversation.getClass();
            Api24Impl.setConversationTitle(messagingStyleCreateMessagingStyle, this.mConversationTitle);
            Api28Impl.setGroupConversation(messagingStyleCreateMessagingStyle, this.mIsGroupConversation.booleanValue());
            messagingStyleCreateMessagingStyle.setBuilder(notificationBuilderWithBuilderAccessor.getBuilder());
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void clearCompatExtraKeys(Bundle bundle) {
            super.clearCompatExtraKeys(bundle);
            bundle.remove(NotificationCompat.EXTRA_MESSAGING_STYLE_USER);
            bundle.remove(NotificationCompat.EXTRA_SELF_DISPLAY_NAME);
            bundle.remove(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            bundle.remove(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            bundle.remove(NotificationCompat.EXTRA_MESSAGES);
            bundle.remove(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            bundle.remove(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return TEMPLATE_CLASS_NAME;
        }

        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        public List<Message> getHistoricMessages() {
            return this.mHistoricMessages;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public Person getUser() {
            return this.mUser;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public boolean isGroupConversation() {
            Builder builder = this.mBuilder;
            if (builder != null && builder.mContext.getApplicationInfo().targetSdkVersion < 28 && this.mIsGroupConversation == null) {
                return this.mConversationTitle != null;
            }
            Boolean bool = this.mIsGroupConversation;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void restoreFromCompatExtras(Bundle bundle) {
            super.restoreFromCompatExtras(bundle);
            this.mMessages.clear();
            Person userPerson = bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_PERSON) ? Api28Impl.getUserPerson(bundle, NotificationCompat.EXTRA_MESSAGING_PERSON) : null;
            if (userPerson != null) {
                this.mUser = userPerson;
            } else if (bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                this.mUser = new Person.Builder().setName(bundle.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            CharSequence charSequence = bundle.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            this.mConversationTitle = charSequence;
            if (charSequence == null) {
                this.mConversationTitle = bundle.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelableArray != null) {
                this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray));
            }
            Parcelable[] parcelableArray2 = bundle.getParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            if (parcelableArray2 != null) {
                this.mHistoricMessages.addAll(Message.getMessagesFromBundleArray(parcelableArray2));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        public MessagingStyle setConversationTitle(CharSequence charSequence) {
            this.mConversationTitle = charSequence;
            return this;
        }

        public MessagingStyle setGroupConversation(boolean z) {
            this.mIsGroupConversation = Boolean.valueOf(z);
            return this;
        }

        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            private String mDataMimeType;
            private Uri mDataUri;
            private Bundle mExtras;
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public static class Api24Impl {
                private Api24Impl() {
                }

                public static Notification.MessagingStyle.Message createMessage(CharSequence charSequence, long j, CharSequence charSequence2) {
                    return new Notification.MessagingStyle.Message(charSequence, j, charSequence2);
                }

                public static Notification.MessagingStyle.Message setData(Notification.MessagingStyle.Message message, String str, Uri uri) {
                    return message.setData(str, uri);
                }
            }

            public static class Api28Impl {
                private Api28Impl() {
                }

                public static Parcelable castToParcelable(android.app.Person person) {
                    return person;
                }

                public static Notification.MessagingStyle.Message createMessage(CharSequence charSequence, long j, android.app.Person person) {
                    return new Notification.MessagingStyle.Message(charSequence, j, person);
                }

                public static void setExtras(Notification.MessagingStyle.Message message, Bundle bundle) {
                    message.getExtras().putAll(bundle);
                }
            }

            public Message(CharSequence charSequence, long j, Person person) {
                this.mExtras = new Bundle();
                this.mText = charSequence;
                this.mTimestamp = j;
                this.mPerson = person;
            }

            public static Bundle[] getBundleArrayForMessages(List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    bundleArr[i] = list.get(i).toBundle();
                }
                return bundleArr;
            }

            public static Message getMessageFromBundle(Bundle bundle) {
                Person personBuild;
                try {
                    if (bundle.containsKey(KEY_TEXT) && bundle.containsKey(KEY_TIMESTAMP)) {
                        if (bundle.containsKey(KEY_PERSON)) {
                            personBuild = Person.fromBundle(bundle.getBundle(KEY_PERSON));
                        } else if (bundle.containsKey(KEY_NOTIFICATION_PERSON)) {
                            personBuild = Person.fromAndroidPerson((android.app.Person) bundle.getParcelable(KEY_NOTIFICATION_PERSON));
                        } else {
                            personBuild = bundle.containsKey(KEY_SENDER) ? new Person.Builder().setName(bundle.getCharSequence(KEY_SENDER)).build() : null;
                        }
                        Message message = new Message(bundle.getCharSequence(KEY_TEXT), bundle.getLong(KEY_TIMESTAMP), personBuild);
                        if (bundle.containsKey("type") && bundle.containsKey(KEY_DATA_URI)) {
                            message.setData(bundle.getString("type"), (Uri) bundle.getParcelable(KEY_DATA_URI));
                        }
                        if (bundle.containsKey(KEY_EXTRAS_BUNDLE)) {
                            message.getExtras().putAll(bundle.getBundle(KEY_EXTRAS_BUNDLE));
                        }
                        return message;
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            public static List<Message> getMessagesFromBundleArray(Parcelable[] parcelableArr) {
                Message messageFromBundle;
                ArrayList arrayList = new ArrayList(parcelableArr.length);
                for (Parcelable parcelable : parcelableArr) {
                    if ((parcelable instanceof Bundle) && (messageFromBundle = getMessageFromBundle((Bundle) parcelable)) != null) {
                        arrayList.add(messageFromBundle);
                    }
                }
                return arrayList;
            }

            private Bundle toBundle() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.mText;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_TEXT, charSequence);
                }
                bundle.putLong(KEY_TIMESTAMP, this.mTimestamp);
                Person person = this.mPerson;
                if (person != null) {
                    bundle.putCharSequence(KEY_SENDER, person.getName());
                    bundle.putParcelable(KEY_NOTIFICATION_PERSON, Api28Impl.castToParcelable(this.mPerson.toAndroidPerson()));
                }
                String str = this.mDataMimeType;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.mDataUri;
                if (uri != null) {
                    bundle.putParcelable(KEY_DATA_URI, uri);
                }
                Bundle bundle2 = this.mExtras;
                if (bundle2 != null) {
                    bundle.putBundle(KEY_EXTRAS_BUNDLE, bundle2);
                }
                return bundle;
            }

            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            public Uri getDataUri() {
                return this.mDataUri;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Person getPerson() {
                return this.mPerson;
            }

            @Deprecated
            public CharSequence getSender() {
                Person person = this.mPerson;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String str, Uri uri) {
                this.mDataMimeType = str;
                this.mDataUri = uri;
                return this;
            }

            public Notification.MessagingStyle.Message toAndroidMessage() {
                Person person = getPerson();
                Notification.MessagingStyle.Message messageCreateMessage = Api28Impl.createMessage(getText(), getTimestamp(), person == null ? null : person.toAndroidPerson());
                if (getDataMimeType() != null) {
                    Api24Impl.setData(messageCreateMessage, getDataMimeType(), getDataUri());
                }
                Bundle bundle = this.mExtras;
                if (bundle != null) {
                    Api28Impl.setExtras(messageCreateMessage, bundle);
                }
                return messageCreateMessage;
            }

            @Deprecated
            public Message(CharSequence charSequence, long j, CharSequence charSequence2) {
                this(charSequence, j, new Person.Builder().setName(charSequence2).build());
            }
        }

        @Deprecated
        public MessagingStyle(CharSequence charSequence) {
            this.mUser = new Person.Builder().setName(charSequence).build();
        }

        public MessagingStyle addMessage(CharSequence charSequence, long j, Person person) {
            addMessage(new Message(charSequence, j, person));
            return this;
        }

        public MessagingStyle() {
        }

        public MessagingStyle addMessage(Message message) {
            if (message != null) {
                this.mMessages.add(message);
                if (this.mMessages.size() > 25) {
                    this.mMessages.remove(0);
                }
            }
            return this;
        }
    }

    public static final class BubbleMetadata {
        private static final int FLAG_AUTO_EXPAND_BUBBLE = 1;
        private static final int FLAG_SUPPRESS_NOTIFICATION = 2;
        private PendingIntent mDeleteIntent;
        private int mDesiredHeight;
        private int mDesiredHeightResId;
        private int mFlags;
        private IconCompat mIcon;
        private PendingIntent mPendingIntent;
        private String mShortcutId;

        public static class Api29Impl {
            private Api29Impl() {
            }

            public static BubbleMetadata fromPlatform(Notification.BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null || bubbleMetadata.getIntent() == null) {
                    return null;
                }
                Builder suppressNotification = new Builder(bubbleMetadata.getIntent(), IconCompat.createFromIcon(bubbleMetadata.getIcon())).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }

            public static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null || bubbleMetadata.getIntent() == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder suppressNotification = new Notification.BubbleMetadata.Builder().setIcon(bubbleMetadata.getIcon().toIcon()).setIntent(bubbleMetadata.getIntent()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }
        }

        public static class Api30Impl {
            private Api30Impl() {
            }

            public static BubbleMetadata fromPlatform(Notification.BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null) {
                    return null;
                }
                Builder builder = bubbleMetadata.getShortcutId() != null ? new Builder(bubbleMetadata.getShortcutId()) : new Builder(bubbleMetadata.getIntent(), IconCompat.createFromIcon(bubbleMetadata.getIcon()));
                builder.setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }

            public static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder builder = bubbleMetadata.getShortcutId() != null ? new Notification.BubbleMetadata.Builder(bubbleMetadata.getShortcutId()) : new Notification.BubbleMetadata.Builder(bubbleMetadata.getIntent(), bubbleMetadata.getIcon().toIcon());
                builder.setDeleteIntent(bubbleMetadata.getDeleteIntent()).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }
        }

        private BubbleMetadata(PendingIntent pendingIntent, PendingIntent pendingIntent2, IconCompat iconCompat, int i, int i2, int i3, String str) {
            this.mPendingIntent = pendingIntent;
            this.mIcon = iconCompat;
            this.mDesiredHeight = i;
            this.mDesiredHeightResId = i2;
            this.mDeleteIntent = pendingIntent2;
            this.mFlags = i3;
            this.mShortcutId = str;
        }

        public static BubbleMetadata fromPlatform(Notification.BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            return Api30Impl.fromPlatform(bubbleMetadata);
        }

        public static Notification.BubbleMetadata toPlatform(BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            return Api30Impl.toPlatform(bubbleMetadata);
        }

        public boolean getAutoExpandBubble() {
            return (this.mFlags & 1) != 0;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public int getDesiredHeight() {
            return this.mDesiredHeight;
        }

        public int getDesiredHeightResId() {
            return this.mDesiredHeightResId;
        }

        public IconCompat getIcon() {
            return this.mIcon;
        }

        public PendingIntent getIntent() {
            return this.mPendingIntent;
        }

        public String getShortcutId() {
            return this.mShortcutId;
        }

        public boolean isNotificationSuppressed() {
            return (this.mFlags & 2) != 0;
        }

        public void setFlags(int i) {
            this.mFlags = i;
        }

        public static final class Builder {
            private PendingIntent mDeleteIntent;
            private int mDesiredHeight;
            private int mDesiredHeightResId;
            private int mFlags;
            private IconCompat mIcon;
            private PendingIntent mPendingIntent;
            private String mShortcutId;

            public Builder(PendingIntent pendingIntent, IconCompat iconCompat) {
                if (pendingIntent == null) {
                    x0e.a("Bubble requires non-null pending intent");
                    throw null;
                }
                if (iconCompat == null) {
                    x0e.a("Bubbles require non-null icon");
                    throw null;
                }
                this.mPendingIntent = pendingIntent;
                this.mIcon = iconCompat;
            }

            private Builder setFlag(int i, boolean z) {
                int i2 = this.mFlags;
                if (z) {
                    this.mFlags = i | i2;
                    return this;
                }
                this.mFlags = (~i) & i2;
                return this;
            }

            public BubbleMetadata build() {
                String str = this.mShortcutId;
                if (str == null && this.mPendingIntent == null) {
                    x0e.a("Must supply pending intent or shortcut to bubble");
                    return null;
                }
                if (str == null && this.mIcon == null) {
                    x0e.a("Must supply an icon or shortcut for the bubble");
                    return null;
                }
                BubbleMetadata bubbleMetadata = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mFlags, str);
                bubbleMetadata.setFlags(this.mFlags);
                return bubbleMetadata;
            }

            public Builder setAutoExpandBubble(boolean z) {
                setFlag(1, z);
                return this;
            }

            public Builder setDeleteIntent(PendingIntent pendingIntent) {
                this.mDeleteIntent = pendingIntent;
                return this;
            }

            public Builder setDesiredHeight(int i) {
                this.mDesiredHeight = Math.max(i, 0);
                this.mDesiredHeightResId = 0;
                return this;
            }

            public Builder setDesiredHeightResId(int i) {
                this.mDesiredHeightResId = i;
                this.mDesiredHeight = 0;
                return this;
            }

            public Builder setIcon(IconCompat iconCompat) {
                if (this.mShortcutId != null) {
                    k2d.a("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                    return null;
                }
                if (iconCompat != null) {
                    this.mIcon = iconCompat;
                    return this;
                }
                x0e.a("Bubbles require non-null icon");
                return null;
            }

            public Builder setIntent(PendingIntent pendingIntent) {
                if (this.mShortcutId != null) {
                    k2d.a("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                    return null;
                }
                if (pendingIntent != null) {
                    this.mPendingIntent = pendingIntent;
                    return this;
                }
                x0e.a("Bubble requires non-null pending intent");
                return null;
            }

            public Builder setSuppressNotification(boolean z) {
                setFlag(2, z);
                return this;
            }

            public Builder(String str) {
                if (!TextUtils.isEmpty(str)) {
                    this.mShortcutId = str;
                } else {
                    x0e.a("Bubble requires a non-null shortcut id");
                    throw null;
                }
            }

            @Deprecated
            public Builder() {
            }
        }
    }

    public static final class ProjectedExtender implements Extender {
        static final String EXTRA_PROJECTED_EXTENDER = "android.projected.EXTENSIONS";
        static final String KEY_CONTENT_INTENT = "content_intent";
        private PendingIntent mContentIntent;

        public ProjectedExtender(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras == null ? null : extras.getBundle(EXTRA_PROJECTED_EXTENDER);
            if (bundle != null) {
                this.mContentIntent = (PendingIntent) androidx.core.os.BundleCompat.getParcelable(bundle, KEY_CONTENT_INTENT, PendingIntent.class);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            PendingIntent pendingIntent = this.mContentIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_CONTENT_INTENT, pendingIntent);
            }
            builder.getExtras().putBundle(EXTRA_PROJECTED_EXTENDER, bundle);
            return builder;
        }

        public PendingIntent getContentIntent() {
            return this.mContentIntent;
        }

        public ProjectedExtender setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public ProjectedExtender() {
        }
    }

    public static class Action {
        public static final int EMPHASIS_AUTO = 0;
        public static final int EMPHASIS_PRIMARY = 1;
        public static final int EMPHASIS_SECONDARY = 2;
        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_PAUSE = 14;
        public static final int SEMANTIC_ACTION_PLAY = 13;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_STOP = 15;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public static final int STYLE_AUTO = 0;
        public static final int STYLE_ICON_AND_TEXT = 2;
        public static final int STYLE_ICON_ONLY = 3;
        public static final int STYLE_TEXT_ONLY = 1;
        public PendingIntent actionIntent;

        @Deprecated
        public int icon;
        private boolean mAllowGeneratedReplies;
        private boolean mAuthenticationRequired;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        private final int mEmphasisHint;
        final Bundle mExtras;
        private IconCompat mIcon;
        private final boolean mIsContextual;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        private final int mStyleHint;
        public CharSequence title;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Emphasis {
        }

        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface SemanticAction {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface Style {
        }

        public Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i, boolean z2, boolean z3, int i2, int i3, boolean z4) {
            this.mShowsUserInterface = true;
            this.mIcon = iconCompat;
            if (iconCompat != null && iconCompat.getType() == 2) {
                this.icon = iconCompat.getResId();
            }
            this.title = Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = pendingIntent;
            this.mExtras = bundle == null ? new Bundle() : bundle;
            this.mRemoteInputs = remoteInputArr;
            this.mDataOnlyRemoteInputs = remoteInputArr2;
            this.mAllowGeneratedReplies = z;
            this.mSemanticAction = i;
            this.mShowsUserInterface = z2;
            this.mIsContextual = z3;
            this.mEmphasisHint = i2;
            this.mStyleHint = i3;
            this.mAuthenticationRequired = z4;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public int getEmphasisHint() {
            return this.mEmphasisHint;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        @Deprecated
        public int getIcon() {
            return this.icon;
        }

        public IconCompat getIconCompat() {
            int i;
            if (this.mIcon == null && (i = this.icon) != 0) {
                this.mIcon = IconCompat.createWithResource(null, "", i);
            }
            return this.mIcon;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public int getStyleHint() {
            return this.mStyleHint;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public boolean isAuthenticationRequired() {
            return this.mAuthenticationRequired;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private boolean mAuthenticationRequired;
            private int mEmphasisHint;
            private final Bundle mExtras;
            private final IconCompat mIcon;
            private final PendingIntent mIntent;
            private boolean mIsContextual;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private int mStyleHint;
            private final CharSequence mTitle;

            public static class Api24Impl {
                private Api24Impl() {
                }

                public static boolean getAllowGeneratedReplies(Notification.Action action) {
                    return action.getAllowGeneratedReplies();
                }
            }

            public static class Api28Impl {
                private Api28Impl() {
                }

                public static int getSemanticAction(Notification.Action action) {
                    return action.getSemanticAction();
                }
            }

            public static class Api29Impl {
                private Api29Impl() {
                }

                public static boolean isContextual(Notification.Action action) {
                    return action.isContextual();
                }
            }

            public static class Api31Impl {
                private Api31Impl() {
                }

                public static boolean isAuthenticationRequired(Notification.Action action) {
                    return action.isAuthenticationRequired();
                }
            }

            public static class Api37Impl {
                private Api37Impl() {
                }

                public static int getEmphasisHint(Notification.Action action) {
                    return action.getEmphasisHint();
                }

                public static int getStyleHint(Notification.Action action) {
                    return action.getStyleHint();
                }
            }

            private Builder(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, boolean z, int i, boolean z2, boolean z3, boolean z4) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = iconCompat;
                this.mTitle = Builder.limitCharSequenceLength(charSequence);
                this.mIntent = pendingIntent;
                this.mExtras = bundle;
                this.mRemoteInputs = remoteInputArr == null ? null : new ArrayList<>(Arrays.asList(remoteInputArr));
                this.mAllowGeneratedReplies = z;
                this.mSemanticAction = i;
                this.mShowsUserInterface = z2;
                this.mIsContextual = z3;
                this.mAuthenticationRequired = z4;
            }

            private void checkContextualActionNullFields() {
                if (this.mIsContextual && this.mIntent == null) {
                    x0e.a("Contextual Actions must contain a valid PendingIntent");
                }
            }

            public static Builder fromAndroidAction(Notification.Action action) {
                Builder builder = action.getIcon() != null ? new Builder(IconCompat.createFromIconOrNullIfZeroResId(action.getIcon()), action.title, action.actionIntent) : new Builder(action.icon, action.title, action.actionIntent);
                android.app.RemoteInput[] remoteInputs = action.getRemoteInputs();
                if (remoteInputs != null && remoteInputs.length != 0) {
                    for (android.app.RemoteInput remoteInput : remoteInputs) {
                        builder.addRemoteInput(RemoteInput.fromPlatform(remoteInput));
                    }
                }
                builder.mAllowGeneratedReplies = Api24Impl.getAllowGeneratedReplies(action);
                builder.setSemanticAction(Api28Impl.getSemanticAction(action));
                builder.setContextual(Api29Impl.isContextual(action));
                builder.setAuthenticationRequired(Api31Impl.isAuthenticationRequired(action));
                if (Build.VERSION.SDK_INT >= 37) {
                    builder.setStyleHint(Api37Impl.getStyleHint(action));
                    builder.setEmphasisHint(Api37Impl.getEmphasisHint(action));
                }
                builder.addExtras(action.getExtras());
                return builder;
            }

            public Builder addExtras(Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<>();
                }
                if (remoteInput != null) {
                    this.mRemoteInputs.add(remoteInput);
                }
                return this;
            }

            public Action build() {
                checkContextualActionNullFields();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
                if (arrayList3 != null) {
                    for (RemoteInput remoteInput : arrayList3) {
                        if (remoteInput.isDataOnly()) {
                            arrayList.add(remoteInput);
                        } else {
                            arrayList2.add(remoteInput);
                        }
                    }
                }
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayList2.isEmpty() ? null : (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]), arrayList.isEmpty() ? null : (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]), this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface, this.mIsContextual, this.mEmphasisHint, this.mStyleHint, this.mAuthenticationRequired);
            }

            public Builder extend(Extender extender) {
                extender.extend(this);
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder setAllowGeneratedReplies(boolean z) {
                this.mAllowGeneratedReplies = z;
                return this;
            }

            public Builder setAuthenticationRequired(boolean z) {
                this.mAuthenticationRequired = z;
                return this;
            }

            public Builder setContextual(boolean z) {
                this.mIsContextual = z;
                return this;
            }

            public Builder setEmphasisHint(int i) {
                this.mEmphasisHint = i;
                return this;
            }

            public Builder setSemanticAction(int i) {
                this.mSemanticAction = i;
                return this;
            }

            public Builder setShowsUserInterface(boolean z) {
                this.mShowsUserInterface = z;
                return this;
            }

            public Builder setStyleHint(int i) {
                this.mStyleHint = i;
                return this;
            }

            public Builder(int i, CharSequence charSequence, PendingIntent pendingIntent) {
                this(i != 0 ? IconCompat.createWithResource(null, "", i) : null, charSequence, pendingIntent, new Bundle(), null, true, 0, true, false, false);
            }

            public Builder(Action action) {
                this(action.getIconCompat(), action.title, action.actionIntent, new Bundle(action.mExtras), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.mShowsUserInterface, action.isContextual(), action.isAuthenticationRequired());
            }

            public Builder(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
                this(iconCompat, charSequence, pendingIntent, new Bundle(), null, true, 0, true, false, false);
            }
        }

        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;

            public WearableExtender(Action action) {
                this.mFlags = 1;
                Bundle bundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (bundle != null) {
                    this.mFlags = bundle.getInt(KEY_FLAGS, 1);
                    this.mInProgressLabel = bundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = bundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = bundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            private void setFlag(int i, boolean z) {
                int i2 = this.mFlags;
                if (z) {
                    this.mFlags = i | i2;
                } else {
                    this.mFlags = (~i) & i2;
                }
            }

            /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
            public WearableExtender m6298clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }

            @Override // androidx.core.app.NotificationCompat.Action.Extender
            public Builder extend(Builder builder) {
                Bundle bundle = new Bundle();
                int i = this.mFlags;
                if (i != 1) {
                    bundle.putInt(KEY_FLAGS, i);
                }
                CharSequence charSequence = this.mInProgressLabel;
                if (charSequence != null) {
                    bundle.putCharSequence(KEY_IN_PROGRESS_LABEL, charSequence);
                }
                CharSequence charSequence2 = this.mConfirmLabel;
                if (charSequence2 != null) {
                    bundle.putCharSequence(KEY_CONFIRM_LABEL, charSequence2);
                }
                CharSequence charSequence3 = this.mCancelLabel;
                if (charSequence3 != null) {
                    bundle.putCharSequence(KEY_CANCEL_LABEL, charSequence3);
                }
                builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
                return builder;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            public WearableExtender setAvailableOffline(boolean z) {
                setFlag(1, z);
                return this;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence charSequence) {
                this.mCancelLabel = charSequence;
                return this;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence charSequence) {
                this.mConfirmLabel = charSequence;
                return this;
            }

            public WearableExtender setHintDisplayActionInline(boolean z) {
                setFlag(4, z);
                return this;
            }

            public WearableExtender setHintLaunchesActivity(boolean z) {
                setFlag(2, z);
                return this;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence charSequence) {
                this.mInProgressLabel = charSequence;
                return this;
            }

            public WearableExtender() {
                this.mFlags = 1;
            }
        }

        public Action(IconCompat iconCompat, CharSequence charSequence, PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true, false, 0, 0, false);
        }

        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i2, boolean z2, boolean z3, int i3, int i4, boolean z4) {
            this(i != 0 ? IconCompat.createWithResource(null, "", i) : null, charSequence, pendingIntent, bundle, remoteInputArr, remoteInputArr2, z, i2, z2, z3, i3, i4, z4);
        }

        public Action(int i, CharSequence charSequence, PendingIntent pendingIntent) {
            this(i != 0 ? IconCompat.createWithResource(null, "", i) : null, charSequence, pendingIntent);
        }
    }

    public static final class CarExtender implements Extender {
        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public static class Api29Impl {
            private Api29Impl() {
            }

            public static int getEditChoicesBeforeSending(android.app.RemoteInput remoteInput) {
                return remoteInput.getEditChoicesBeforeSending();
            }
        }

        @Deprecated
        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages = new ArrayList();
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String str) {
                    this.mParticipant = str;
                }

                public Builder addMessage(String str) {
                    if (str != null) {
                        this.mMessages.add(str);
                    }
                    return this;
                }

                public UnreadConversation build() {
                    List<String> list = this.mMessages;
                    return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                }

                public Builder setLatestTimestamp(long j) {
                    this.mLatestTimestamp = j;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }
            }

            public UnreadConversation(String[] strArr, RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j) {
                this.mMessages = strArr;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = pendingIntent2;
                this.mReplyPendingIntent = pendingIntent;
                this.mParticipants = strArr2;
                this.mLatestTimestamp = j;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public String getParticipant() {
                String[] strArr = this.mParticipants;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
        }

        public CarExtender(Notification notification) {
            this.mColor = 0;
            Bundle bundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle(EXTRA_CAR_EXTENDER);
            if (bundle != null) {
                this.mLargeIcon = (Bitmap) bundle.getParcelable(EXTRA_LARGE_ICON);
                this.mColor = bundle.getInt(EXTRA_COLOR, 0);
                this.mUnreadConversation = getUnreadConversationFromBundle(bundle.getBundle(EXTRA_CONVERSATION));
            }
        }

        private static Bundle getBundleForUnreadConversation(UnreadConversation unreadConversation) {
            Bundle bundle = new Bundle();
            String str = (unreadConversation.getParticipants() == null || unreadConversation.getParticipants().length <= 1) ? null : unreadConversation.getParticipants()[0];
            int length = unreadConversation.getMessages().length;
            Parcelable[] parcelableArr = new Parcelable[length];
            for (int i = 0; i < length; i++) {
                Bundle bundle2 = new Bundle();
                bundle2.putString(KEY_TEXT, unreadConversation.getMessages()[i]);
                bundle2.putString(KEY_AUTHOR, str);
                parcelableArr[i] = bundle2;
            }
            bundle.putParcelableArray(KEY_MESSAGES, parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                android.app.RemoteInput.Builder builder = new android.app.RemoteInput.Builder(remoteInput.getResultKey());
                builder.setLabel(remoteInput.getLabel());
                builder.setChoices(remoteInput.getChoices());
                builder.setAllowFreeFormInput(remoteInput.getAllowFreeFormInput());
                builder.addExtras(remoteInput.getExtras());
                bundle.putParcelable(KEY_REMOTE_INPUT, builder.build());
            }
            bundle.putParcelable(KEY_ON_REPLY, unreadConversation.getReplyPendingIntent());
            bundle.putParcelable(KEY_ON_READ, unreadConversation.getReadPendingIntent());
            bundle.putStringArray(KEY_PARTICIPANTS, unreadConversation.getParticipants());
            bundle.putLong(KEY_TIMESTAMP, unreadConversation.getLatestTimestamp());
            return bundle;
        }

        private static UnreadConversation getUnreadConversationFromBundle(Bundle bundle) {
            String[] strArr;
            if (bundle == null) {
                return null;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray(KEY_MESSAGES);
            if (parcelableArray != null) {
                int length = parcelableArray.length;
                String[] strArr2 = new String[length];
                for (int i = 0; i < length; i++) {
                    Parcelable parcelable = parcelableArray[i];
                    if (parcelable instanceof Bundle) {
                        String string = ((Bundle) parcelable).getString(KEY_TEXT);
                        strArr2[i] = string;
                        if (string != null) {
                        }
                    }
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(KEY_ON_READ);
            PendingIntent pendingIntent2 = (PendingIntent) bundle.getParcelable(KEY_ON_REPLY);
            android.app.RemoteInput remoteInput = (android.app.RemoteInput) bundle.getParcelable(KEY_REMOTE_INPUT);
            String[] stringArray = bundle.getStringArray(KEY_PARTICIPANTS);
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            return new UnreadConversation(strArr, remoteInput != null ? new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), Api29Impl.getEditChoicesBeforeSending(remoteInput), remoteInput.getExtras(), null) : null, pendingIntent2, pendingIntent, stringArray, bundle.getLong(KEY_TIMESTAMP));
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.mLargeIcon;
            if (bitmap != null) {
                bundle.putParcelable(EXTRA_LARGE_ICON, bitmap);
            }
            int i = this.mColor;
            if (i != 0) {
                bundle.putInt(EXTRA_COLOR, i);
            }
            UnreadConversation unreadConversation = this.mUnreadConversation;
            if (unreadConversation != null) {
                bundle.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(unreadConversation));
            }
            builder.getExtras().putBundle(EXTRA_CAR_EXTENDER, bundle);
            return builder;
        }

        public int getColor() {
            return this.mColor;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        @Deprecated
        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        public CarExtender setColor(int i) {
            this.mColor = i;
            return this;
        }

        public CarExtender setLargeIcon(Bitmap bitmap) {
            this.mLargeIcon = bitmap;
            return this;
        }

        @Deprecated
        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public CarExtender() {
            this.mColor = 0;
        }
    }

    public static abstract class Style {
        CharSequence mBigContentTitle;
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet = false;

        public static class Api24Impl {
            private Api24Impl() {
            }

            public static void setChronometerCountDown(RemoteViews remoteViews, int i, boolean z) {
                remoteViews.setChronometerCountDown(i, z);
            }
        }

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float fConstrain = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - fConstrain) * dimensionPixelSize) + (fConstrain * dimensionPixelSize2));
        }

        private static float constrain(float f, float f2, float f3) {
            if (f < f2) {
                return f2;
            }
            return f > f3 ? f3 : f;
        }

        public static Style constructCompatStyleByName(String str) {
            if (str == null) {
                return null;
            }
            switch (str) {
                case "androidx.core.app.NotificationCompat$ProgressStyle":
                    return new ProgressStyle();
                case "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle":
                    return new DecoratedCustomViewStyle();
                case "androidx.core.app.NotificationCompat$BigPictureStyle":
                    return new BigPictureStyle();
                case "androidx.core.app.NotificationCompat$MetricStyle":
                    return new MetricStyle();
                case "androidx.core.app.NotificationCompat$CallStyle":
                    return new CallStyle();
                case "androidx.core.app.NotificationCompat$InboxStyle":
                    return new InboxStyle();
                case "androidx.core.app.NotificationCompat$BigTextStyle":
                    return new BigTextStyle();
                case "androidx.core.app.NotificationCompat$MessagingStyle":
                    return new MessagingStyle();
                default:
                    return null;
            }
        }

        private static Style constructCompatStyleByPlatformName(String str) {
            if (str == null) {
                return null;
            }
            if (str.equals(Notification.BigPictureStyle.class.getName())) {
                return new BigPictureStyle();
            }
            if (str.equals(Notification.BigTextStyle.class.getName())) {
                return new BigTextStyle();
            }
            if (str.equals(Notification.InboxStyle.class.getName())) {
                return new InboxStyle();
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 36 && str.equals(bja.a().getName())) {
                return new ProgressStyle();
            }
            if (str.equals(Notification.MessagingStyle.class.getName())) {
                return new MessagingStyle();
            }
            if (str.equals(Notification.DecoratedCustomViewStyle.class.getName())) {
                return new DecoratedCustomViewStyle();
            }
            if (i < 37 || !str.equals(cja.a().getName())) {
                return null;
            }
            return new MetricStyle();
        }

        public static Style constructCompatStyleForBundle(Bundle bundle) {
            Style styleConstructCompatStyleByName = constructCompatStyleByName(bundle.getString(NotificationCompat.EXTRA_COMPAT_TEMPLATE));
            if (styleConstructCompatStyleByName != null) {
                return styleConstructCompatStyleByName;
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) || bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return new MessagingStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_PICTURE) || bundle.containsKey(NotificationCompat.EXTRA_PICTURE_ICON)) {
                return new BigPictureStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_BIG_TEXT)) {
                return new BigTextStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_TEXT_LINES)) {
                return new InboxStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_CALL_TYPE)) {
                return new CallStyle();
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_PROGRESS_SEGMENTS) || bundle.containsKey(NotificationCompat.EXTRA_PROGRESS_POINTS)) {
                return new ProgressStyle();
            }
            return bundle.containsKey("android.metrics") ? new MetricStyle() : constructCompatStyleByPlatformName(bundle.getString(NotificationCompat.EXTRA_TEMPLATE));
        }

        public static Style constructStyleForExtras(Bundle bundle) {
            Style styleConstructCompatStyleForBundle = constructCompatStyleForBundle(bundle);
            if (styleConstructCompatStyleForBundle == null) {
                return null;
            }
            try {
                styleConstructCompatStyleForBundle.restoreFromCompatExtras(bundle);
                return styleConstructCompatStyleForBundle;
            } catch (ClassCastException unused) {
                return null;
            }
        }

        private Bitmap createColoredBitmap(IconCompat iconCompat, int i, int i2) {
            Drawable drawableLoadDrawable = iconCompat.loadDrawable(this.mBuilder.mContext);
            int intrinsicWidth = i2 == 0 ? drawableLoadDrawable.getIntrinsicWidth() : i2;
            if (i2 == 0) {
                i2 = drawableLoadDrawable.getIntrinsicHeight();
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, i2, Bitmap.Config.ARGB_8888);
            drawableLoadDrawable.setBounds(0, 0, intrinsicWidth, i2);
            if (i != 0) {
                drawableLoadDrawable.mutate().setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
            }
            drawableLoadDrawable.draw(new Canvas(bitmapCreateBitmap));
            return bitmapCreateBitmap;
        }

        private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
            int i5 = R.drawable.notification_icon_background;
            if (i4 == 0) {
                i4 = 0;
            }
            Bitmap bitmapCreateColoredBitmap = createColoredBitmap(i5, i4, i2);
            Canvas canvas = new Canvas(bitmapCreateColoredBitmap);
            Drawable drawableMutate = this.mBuilder.mContext.getResources().getDrawable(i).mutate();
            drawableMutate.setFilterBitmap(true);
            int i6 = (i2 - i3) / 2;
            int i7 = i3 + i6;
            drawableMutate.setBounds(i6, i6, i7, i7);
            drawableMutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            drawableMutate.draw(canvas);
            return bitmapCreateColoredBitmap;
        }

        public static Style extractStyleFromNotification(Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras == null) {
                return null;
            }
            return constructStyleForExtras(extras);
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R.id.title, 8);
            remoteViews.setViewVisibility(R.id.text2, 8);
            remoteViews.setViewVisibility(R.id.text, 8);
        }

        public void addCompatExtras(Bundle bundle) {
            if (this.mSummaryTextSet) {
                bundle.putCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT, this.mSummaryText);
            }
            CharSequence charSequence = this.mBigContentTitle;
            if (charSequence != null) {
                bundle.putCharSequence(NotificationCompat.EXTRA_TITLE_BIG, charSequence);
            }
            String className = getClassName();
            if (className != null) {
                bundle.putString(NotificationCompat.EXTRA_COMPAT_TEMPLATE, className);
            }
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        /* JADX WARN: Code duplicated, block: B:34:0x0103  */
        /* JADX WARN: Code duplicated, block: B:36:0x010e  */
        /* JADX WARN: Code duplicated, block: B:38:0x011a  */
        /* JADX WARN: Code duplicated, block: B:40:0x0130  */
        /* JADX WARN: Code duplicated, block: B:43:0x0141  */
        /* JADX WARN: Code duplicated, block: B:45:0x0147  */
        /* JADX WARN: Code duplicated, block: B:47:0x0170  */
        /* JADX WARN: Code duplicated, block: B:48:0x0176  */
        /* JADX WARN: Code duplicated, block: B:49:0x0189  */
        /* JADX WARN: Code duplicated, block: B:52:0x018e  */
        /* JADX WARN: Code duplicated, block: B:53:0x0190  */
        /* JADX WARN: Code duplicated, block: B:57:0x0199  */
        public RemoteViews applyStandardTemplate(boolean z, int i, boolean z2) {
            boolean z3;
            boolean z4;
            CharSequence charSequence;
            int i2;
            boolean z5;
            CharSequence charSequence2;
            Resources resources = this.mBuilder.mContext.getResources();
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), i);
            Builder builder = this.mBuilder;
            if (builder.mLargeIcon != null) {
                remoteViews.setViewVisibility(R.id.icon, 0);
                remoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(this.mBuilder.mLargeIcon, 0));
                if (z && this.mBuilder.mNotification.icon != 0) {
                    int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
                    int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding) * 2);
                    Builder builder2 = this.mBuilder;
                    remoteViews.setImageViewBitmap(R.id.right_icon, createIconWithBackground(builder2.mNotification.icon, dimensionPixelSize, dimensionPixelSize2, builder2.getColor()));
                    remoteViews.setViewVisibility(R.id.right_icon, 0);
                }
            } else if (z && builder.mNotification.icon != 0) {
                remoteViews.setViewVisibility(R.id.icon, 0);
                int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin);
                int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large);
                Builder builder3 = this.mBuilder;
                remoteViews.setImageViewBitmap(R.id.icon, createIconWithBackground(builder3.mNotification.icon, dimensionPixelSize3, dimensionPixelSize4, builder3.getColor()));
            }
            CharSequence charSequence3 = this.mBuilder.mContentTitle;
            if (charSequence3 != null) {
                remoteViews.setTextViewText(R.id.title, charSequence3);
            }
            CharSequence charSequence4 = this.mBuilder.mContentText;
            boolean z6 = true;
            if (charSequence4 != null) {
                remoteViews.setTextViewText(R.id.text, charSequence4);
                z3 = true;
            } else {
                z3 = false;
            }
            Builder builder4 = this.mBuilder;
            CharSequence charSequence5 = builder4.mContentInfo;
            if (charSequence5 == null) {
                if (builder4.mNumber > 0) {
                    if (this.mBuilder.mNumber > resources.getInteger(R.integer.status_bar_notification_info_maxnum)) {
                        remoteViews.setTextViewText(R.id.info, resources.getString(R.string.status_bar_notification_info_overflow));
                    } else {
                        remoteViews.setTextViewText(R.id.info, NumberFormat.getIntegerInstance().format(this.mBuilder.mNumber));
                    }
                    remoteViews.setViewVisibility(R.id.info, 0);
                } else {
                    remoteViews.setViewVisibility(R.id.info, 8);
                    z4 = false;
                }
                charSequence = this.mBuilder.mSubText;
                if (charSequence != null) {
                    remoteViews.setTextViewText(R.id.text, charSequence);
                    charSequence2 = this.mBuilder.mContentText;
                    if (charSequence2 != null) {
                        remoteViews.setTextViewText(R.id.text2, charSequence2);
                        remoteViews.setViewVisibility(R.id.text2, 0);
                        if (z2) {
                            remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                        }
                        remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
                    } else {
                        remoteViews.setViewVisibility(R.id.text2, 8);
                    }
                }
                if (this.mBuilder.getWhenIfShowing() != 0) {
                    z6 = z4;
                } else if (this.mBuilder.mUseChronometer) {
                    remoteViews.setViewVisibility(R.id.chronometer, 0);
                    remoteViews.setLong(R.id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                    z5 = this.mBuilder.mChronometerCountDown;
                    if (z5) {
                        Api24Impl.setChronometerCountDown(remoteViews, R.id.chronometer, z5);
                    }
                } else {
                    remoteViews.setViewVisibility(R.id.time, 0);
                    remoteViews.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
                }
                int i3 = R.id.right_side;
                if (z6) {
                    i2 = 0;
                } else {
                    i2 = 8;
                }
                remoteViews.setViewVisibility(i3, i2);
                remoteViews.setViewVisibility(R.id.line3, z3 ? 0 : 8);
                return remoteViews;
            }
            remoteViews.setTextViewText(R.id.info, charSequence5);
            remoteViews.setViewVisibility(R.id.info, 0);
            z3 = true;
            z4 = true;
            charSequence = this.mBuilder.mSubText;
            if (charSequence != null) {
                remoteViews.setTextViewText(R.id.text, charSequence);
                charSequence2 = this.mBuilder.mContentText;
                if (charSequence2 != null) {
                    remoteViews.setTextViewText(R.id.text2, charSequence2);
                    remoteViews.setViewVisibility(R.id.text2, 0);
                    if (z2) {
                        remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                    }
                    remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
                } else {
                    remoteViews.setViewVisibility(R.id.text2, 8);
                }
            }
            if (this.mBuilder.getWhenIfShowing() != 0) {
                z6 = z4;
            } else if (this.mBuilder.mUseChronometer) {
                remoteViews.setViewVisibility(R.id.chronometer, 0);
                remoteViews.setLong(R.id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                z5 = this.mBuilder.mChronometerCountDown;
                if (z5) {
                    Api24Impl.setChronometerCountDown(remoteViews, R.id.chronometer, z5);
                }
            } else {
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", this.mBuilder.getWhenIfShowing());
            }
            int i4 = R.id.right_side;
            if (z6) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            remoteViews.setViewVisibility(i4, i2);
            remoteViews.setViewVisibility(R.id.line3, z3 ? 0 : 8);
            return remoteViews;
        }

        public Notification build() {
            Builder builder = this.mBuilder;
            if (builder != null) {
                return builder.build();
            }
            return null;
        }

        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            hideNormalContent(remoteViews);
            remoteViews.removeAllViews(R.id.notification_main_column);
            remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
            remoteViews.setViewVisibility(R.id.notification_main_column, 0);
            remoteViews.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
        }

        public void clearCompatExtraKeys(Bundle bundle) {
            bundle.remove(NotificationCompat.EXTRA_SUMMARY_TEXT);
            bundle.remove(NotificationCompat.EXTRA_TITLE_BIG);
            bundle.remove(NotificationCompat.EXTRA_COMPAT_TEMPLATE);
        }

        public boolean displayCustomViewInline() {
            return false;
        }

        public String getClassName() {
            return null;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        public void restoreFromCompatExtras(Bundle bundle) {
            if (bundle.containsKey(NotificationCompat.EXTRA_SUMMARY_TEXT)) {
                this.mSummaryText = bundle.getCharSequence(NotificationCompat.EXTRA_SUMMARY_TEXT);
                this.mSummaryTextSet = true;
            }
            this.mBigContentTitle = bundle.getCharSequence(NotificationCompat.EXTRA_TITLE_BIG);
        }

        public void setBuilder(Builder builder) {
            if (this.mBuilder != builder) {
                this.mBuilder = builder;
                if (builder != null) {
                    builder.setStyle(this);
                }
            }
        }

        public Bitmap createColoredBitmap(IconCompat iconCompat, int i) {
            return createColoredBitmap(iconCompat, i, 0);
        }

        private Bitmap createColoredBitmap(int i, int i2, int i3) {
            return createColoredBitmap(IconCompat.createWithResource(this.mBuilder.mContext, i), i2, i3);
        }

        public Bitmap createColoredBitmap(int i, int i2) {
            return createColoredBitmap(i, i2, 0);
        }
    }

    public static final class TvExtender implements Extender {
        static final String EXTRA_CHANNEL_ID = "channel_id";
        static final String EXTRA_CONTENT_INTENT = "content_intent";
        static final String EXTRA_DELETE_INTENT = "delete_intent";
        private static final String EXTRA_FLAGS = "flags";
        static final String EXTRA_SUPPRESS_SHOW_OVER_APPS = "suppressShowOverApps";
        static final String EXTRA_TV_EXTENDER = "android.tv.EXTENSIONS";
        private static final int FLAG_AVAILABLE_ON_TV = 1;
        private static final String TAG = "TvExtender";
        private String mChannelId;
        private PendingIntent mContentIntent;
        private PendingIntent mDeleteIntent;
        private int mFlags;
        private boolean mSuppressShowOverApps;

        public TvExtender(Notification notification) {
            Bundle bundle = notification.extras;
            Bundle bundle2 = bundle == null ? null : bundle.getBundle(EXTRA_TV_EXTENDER);
            if (bundle2 != null) {
                this.mFlags = bundle2.getInt(EXTRA_FLAGS);
                this.mChannelId = bundle2.getString(EXTRA_CHANNEL_ID);
                this.mSuppressShowOverApps = bundle2.getBoolean(EXTRA_SUPPRESS_SHOW_OVER_APPS);
                this.mContentIntent = (PendingIntent) bundle2.getParcelable(EXTRA_CONTENT_INTENT);
                this.mDeleteIntent = (PendingIntent) bundle2.getParcelable(EXTRA_DELETE_INTENT);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            bundle.putInt(EXTRA_FLAGS, this.mFlags);
            bundle.putString(EXTRA_CHANNEL_ID, this.mChannelId);
            bundle.putBoolean(EXTRA_SUPPRESS_SHOW_OVER_APPS, this.mSuppressShowOverApps);
            PendingIntent pendingIntent = this.mContentIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(EXTRA_CONTENT_INTENT, pendingIntent);
            }
            PendingIntent pendingIntent2 = this.mDeleteIntent;
            if (pendingIntent2 != null) {
                bundle.putParcelable(EXTRA_DELETE_INTENT, pendingIntent2);
            }
            builder.getExtras().putBundle(EXTRA_TV_EXTENDER, bundle);
            return builder;
        }

        public String getChannelId() {
            return this.mChannelId;
        }

        public PendingIntent getContentIntent() {
            return this.mContentIntent;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public boolean isAvailableOnTv() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isSuppressShowOverApps() {
            return this.mSuppressShowOverApps;
        }

        public TvExtender setChannelId(String str) {
            this.mChannelId = str;
            return this;
        }

        public TvExtender setContentIntent(PendingIntent pendingIntent) {
            this.mContentIntent = pendingIntent;
            return this;
        }

        public TvExtender setDeleteIntent(PendingIntent pendingIntent) {
            this.mDeleteIntent = pendingIntent;
            return this;
        }

        public TvExtender setSuppressShowOverApps(boolean z) {
            this.mSuppressShowOverApps = z;
            return this;
        }

        public TvExtender() {
            this.mFlags = 1;
        }
    }

    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";

        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;

        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;

        @Deprecated
        public static final int SIZE_DEFAULT = 0;

        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;

        @Deprecated
        public static final int SIZE_LARGE = 4;

        @Deprecated
        public static final int SIZE_MEDIUM = 3;

        @Deprecated
        public static final int SIZE_SMALL = 2;

        @Deprecated
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;

        public static class Api24Impl {
            private Api24Impl() {
            }

            public static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder builder, boolean z) {
                return builder.setAllowGeneratedReplies(z);
            }
        }

        public static class Api31Impl {
            private Api31Impl() {
            }

            public static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder builder, boolean z) {
                return builder.setAuthenticationRequired(z);
            }
        }

        public WearableExtender(Notification notification) {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = DEFAULT_GRAVITY;
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras != null ? extras.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList(KEY_ACTIONS);
                if (parcelableArrayList != null) {
                    int size = parcelableArrayList.size();
                    Action[] actionArr = new Action[size];
                    for (int i = 0; i < size; i++) {
                        actionArr[i] = NotificationCompat.getActionCompatFromAction((Notification.Action) parcelableArrayList.get(i));
                    }
                    Collections.addAll(this.mActions, actionArr);
                }
                this.mFlags = bundle.getInt(KEY_FLAGS, 1);
                this.mDisplayIntent = (PendingIntent) bundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] notificationArrayFromBundle = NotificationCompat.getNotificationArrayFromBundle(bundle, KEY_PAGES);
                if (notificationArrayFromBundle != null) {
                    Collections.addAll(this.mPages, notificationArrayFromBundle);
                }
                this.mBackground = (Bitmap) bundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = bundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = bundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = bundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = bundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = bundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = bundle.getInt(KEY_GRAVITY, DEFAULT_GRAVITY);
                this.mHintScreenTimeout = bundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = bundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = bundle.getString(KEY_BRIDGE_TAG);
            }
        }

        private static Notification.Action getActionFromActionCompat(Action action) {
            IconCompat iconCompat = action.getIconCompat();
            Notification.Action.Builder builder = new Notification.Action.Builder(iconCompat == null ? null : iconCompat.toIcon(), action.getTitle(), action.getActionIntent());
            Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            Api24Impl.setAllowGeneratedReplies(builder, action.getAllowGeneratedReplies());
            Api31Impl.setAuthenticationRequired(builder, action.isAuthenticationRequired());
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (android.app.RemoteInput remoteInput : RemoteInput.fromCompat(remoteInputs)) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            return builder.build();
        }

        private void setFlag(int i, boolean z) {
            int i2 = this.mFlags;
            if (z) {
                this.mFlags = i | i2;
            } else {
                this.mFlags = (~i) & i2;
            }
        }

        public WearableExtender addAction(Action action) {
            this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }

        @Deprecated
        public WearableExtender addPage(Notification notification) {
            this.mPages.add(notification);
            return this;
        }

        @Deprecated
        public WearableExtender addPages(List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        @Deprecated
        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public WearableExtender m6299clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            wearableExtender.mBridgeTag = this.mBridgeTag;
            return wearableExtender;
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        public Builder extend(Builder builder) {
            Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.mActions.size());
                Iterator<Action> it = this.mActions.iterator();
                while (it.hasNext()) {
                    arrayList.add(getActionFromActionCompat(it.next()));
                }
                bundle.putParcelableArrayList(KEY_ACTIONS, arrayList);
            }
            int i = this.mFlags;
            if (i != 1) {
                bundle.putInt(KEY_FLAGS, i);
            }
            PendingIntent pendingIntent = this.mDisplayIntent;
            if (pendingIntent != null) {
                bundle.putParcelable(KEY_DISPLAY_INTENT, pendingIntent);
            }
            if (!this.mPages.isEmpty()) {
                ArrayList<Notification> arrayList2 = this.mPages;
                bundle.putParcelableArray(KEY_PAGES, (Parcelable[]) arrayList2.toArray(new Notification[arrayList2.size()]));
            }
            Bitmap bitmap = this.mBackground;
            if (bitmap != null) {
                bundle.putParcelable(KEY_BACKGROUND, bitmap);
            }
            int i2 = this.mContentIcon;
            if (i2 != 0) {
                bundle.putInt(KEY_CONTENT_ICON, i2);
            }
            int i3 = this.mContentIconGravity;
            if (i3 != 8388613) {
                bundle.putInt(KEY_CONTENT_ICON_GRAVITY, i3);
            }
            int i4 = this.mContentActionIndex;
            if (i4 != -1) {
                bundle.putInt(KEY_CONTENT_ACTION_INDEX, i4);
            }
            int i5 = this.mCustomSizePreset;
            if (i5 != 0) {
                bundle.putInt(KEY_CUSTOM_SIZE_PRESET, i5);
            }
            int i6 = this.mCustomContentHeight;
            if (i6 != 0) {
                bundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, i6);
            }
            int i7 = this.mGravity;
            if (i7 != DEFAULT_GRAVITY) {
                bundle.putInt(KEY_GRAVITY, i7);
            }
            int i8 = this.mHintScreenTimeout;
            if (i8 != 0) {
                bundle.putInt(KEY_HINT_SCREEN_TIMEOUT, i8);
            }
            String str = this.mDismissalId;
            if (str != null) {
                bundle.putString(KEY_DISMISSAL_ID, str);
            }
            String str2 = this.mBridgeTag;
            if (str2 != null) {
                bundle.putString(KEY_BRIDGE_TAG, str2);
            }
            builder.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, bundle);
            return builder;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        @Deprecated
        public Bitmap getBackground() {
            return this.mBackground;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        @Deprecated
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public List<Notification> getPages() {
            return this.mPages;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        @Deprecated
        public WearableExtender setBackground(Bitmap bitmap) {
            this.mBackground = bitmap;
            return this;
        }

        public WearableExtender setBridgeTag(String str) {
            this.mBridgeTag = str;
            return this;
        }

        public WearableExtender setContentAction(int i) {
            this.mContentActionIndex = i;
            return this;
        }

        @Deprecated
        public WearableExtender setContentIcon(int i) {
            this.mContentIcon = i;
            return this;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int i) {
            this.mContentIconGravity = i;
            return this;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            setFlag(1, z);
            return this;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int i) {
            this.mCustomContentHeight = i;
            return this;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int i) {
            this.mCustomSizePreset = i;
            return this;
        }

        public WearableExtender setDismissalId(String str) {
            this.mDismissalId = str;
            return this;
        }

        @Deprecated
        public WearableExtender setDisplayIntent(PendingIntent pendingIntent) {
            this.mDisplayIntent = pendingIntent;
            return this;
        }

        @Deprecated
        public WearableExtender setGravity(int i) {
            this.mGravity = i;
            return this;
        }

        @Deprecated
        public WearableExtender setHintAmbientBigPicture(boolean z) {
            setFlag(32, z);
            return this;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            setFlag(16, z);
            return this;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            setFlag(64, z);
            return this;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean z) {
            setFlag(2, z);
            return this;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int i) {
            this.mHintScreenTimeout = i;
            return this;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            setFlag(4, z);
            return this;
        }

        public WearableExtender setStartScrollBottom(boolean z) {
            setFlag(8, z);
            return this;
        }

        public WearableExtender() {
            this.mActions = new ArrayList<>();
            this.mFlags = 1;
            this.mPages = new ArrayList<>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = DEFAULT_GRAVITY;
        }
    }
}
