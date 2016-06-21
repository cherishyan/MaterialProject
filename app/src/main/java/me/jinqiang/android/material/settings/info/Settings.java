/*
 * Copyright (c) 2015 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.settings.info;

import me.jinqiang.android.material.R;

public class Settings {

    public static final SettingsEntries.StringSettingsEntry ACTIVE_ACCOUNT_NAME = new SettingsEntries.StringSettingsEntry(
            R.string.pref_key_active_account_name, R.string.pref_default_value_active_account_name);

    public static final SettingsEntries.BooleanSettingsEntry AUTO_REFRESH_HOME = new SettingsEntries.BooleanSettingsEntry(
            R.string.pref_key_auto_refresh_home, R.bool.pref_default_value_auto_refresh_home);

    public static final SettingsEntries.BooleanSettingsEntry SHOW_TITLE_FOR_LINK_ENTITY = new SettingsEntries.BooleanSettingsEntry(
            R.string.pref_key_show_title_for_link_entity,
            R.bool.pref_default_value_show_title_for_link_entity);

    public enum OpenUrlWithMethod {
        WEBVIEW,
        INTENT,
        CUSTOM_TABS
    }

    public static final SettingsEntries.EnumSettingsEntry<OpenUrlWithMethod> OPEN_URL_WITH_METHOD =
            new SettingsEntries.EnumSettingsEntry<>(R.string.pref_key_open_url_with,
                    R.string.pref_default_value_open_url_with, OpenUrlWithMethod.class);

    public static final SettingsEntries.BooleanSettingsEntry ALWAYS_COPY_TO_CLIPBOARD_AS_TEXT =
            new SettingsEntries.BooleanSettingsEntry(R.string.pref_key_always_copy_to_clipboard_as_text,
                    R.bool.pref_default_value_always_copy_to_clipboard_as_text);
}
