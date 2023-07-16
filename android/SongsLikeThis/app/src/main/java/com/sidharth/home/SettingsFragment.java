package com.sidharth.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.sidharth.songslikethis.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference privacyPolicy = findPreference("privacy_policy");

        if (privacyPolicy != null) {
            privacyPolicy.setOnPreferenceClickListener(preference -> {
                Toast.makeText(getActivity(), "privacy policy", Toast.LENGTH_SHORT).show();
                if (getActivity() != null) {
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_settingsFragment_to_privacyPolicyFragment);
                }
                return false;
            });
        }
    }
}