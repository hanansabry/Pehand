package com.pehand.app.common;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.pehand.app.R;

import java.util.Locale;

public class LocaleManager {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    private static final String DEFAULT_LANG = "ar";

    public static Context onAttach(Context context) {
        return setLocale(context, DEFAULT_LANG);
    }

//    public static String getLanguage(Context context) {
//        return getPersistedData(context, Locale.getDefault().getLanguage());
//    }

    public static String getRegion(Context context) {
        String country = context.getResources().getConfiguration().locale.getCountry();
        return country;
    }

    private static Context setLocale(Context context, String language) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }

        return updateResourcesLegacy(context, language);
    }

//    private static String getPersistedData(Context context, String defaultLanguage) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String selectedLang = context.getString(R.string.lang_pref_key);
////        String defaultLang = context.getString(R.string.pref_lang_default_value);
//        String locale = preferences.getString(selectedLang, defaultLanguage);
//        return preferences.getString(selectedLang, defaultLanguage);
//    }

//    public static void persist(Context context, String language) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = preferences.edit();
//        String selectedLang = context.getString(R.string.lang_pref_key);
//        editor.putString(selectedLang, language);
//        editor.apply();
//    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Log.d("LocaleManager", "language : " + language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        configuration.setLayoutDirection(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }
}
