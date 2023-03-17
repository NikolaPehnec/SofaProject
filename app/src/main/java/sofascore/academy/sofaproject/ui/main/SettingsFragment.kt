package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.preference.ListPreference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
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

        //Setting current language selected
        val defaultLang = AppCompatDelegate.getApplicationLocales().toLanguageTags()
        val languageListPreference =
            preferenceManager.findPreference<ListPreference>(getString(R.string.language_pref_key))
        when (defaultLang) {
            "en" -> languageListPreference?.setValueIndex(0)
            "hr" -> languageListPreference?.setValueIndex(1)
        }

        languageListPreference?.onPreferenceChangeListener =
            OnPreferenceChangeListener { _, newValue ->
                changeLocalization(newValue.toString())
                true
            }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun changeLocalization(language: String) {
        var appLocale: LocaleListCompat? = null
        when (language) {
            //English
            resources.getStringArray(R.array.languages)[0] -> {
                appLocale = LocaleListCompat.forLanguageTags("en")
            }
            //Croatian
            resources.getStringArray(R.array.languages)[1] -> {
                appLocale = LocaleListCompat.forLanguageTags("hr")
            }
        }

        appLocale?.let {
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
    }

}