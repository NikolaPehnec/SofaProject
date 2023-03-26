package sofascore.academy.sofaproject.view.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.ListPreference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import sofascore.academy.sofaproject.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = getString(R.string.settings_title)

        val languageListPreference =
            preferenceManager.findPreference<ListPreference>(getString(R.string.language_pref_key))
        val themePreference =
            preferenceManager.findPreference<SwitchPreferenceCompat>(getString(R.string.dark_theme_pref_key))

        // Setting current language selected
        when (AppCompatDelegate.getApplicationLocales().toLanguageTags()) {
            "en" -> languageListPreference?.setValueIndex(0)
            "hr" -> languageListPreference?.setValueIndex(1)
        }

        languageListPreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                changeLocalization(newValue.toString())
                true
            }
        themePreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                changeTheme(newValue as Boolean)
                true
            }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun changeLocalization(language: String) {
        val appLocale = when (language) {
            // English
            resources.getStringArray(R.array.languages)[0] -> {
                LocaleListCompat.forLanguageTags("en")
            }
            // Croatian
            resources.getStringArray(R.array.languages)[1] -> {
                LocaleListCompat.forLanguageTags("hr")
            }
            else -> null
        }

        appLocale?.let {
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

    private fun changeTheme(darkTheme: Boolean) {
        val editor =
            activity?.getSharedPreferences(getString(R.string.shared_pref_name), MODE_PRIVATE)
                ?.edit()

        if (darkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor?.putBoolean(getString(R.string.shared_pref_dark_mode), true)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor?.putBoolean(getString(R.string.shared_pref_dark_mode), false)
        }

        editor?.apply()
    }
}
