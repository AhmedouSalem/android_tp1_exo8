package fr.umontpellier.tp_1_8;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class OnboardingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstLaunch = prefs.getBoolean("isFirstLaunch", true);

        if (!isFirstLaunch) {
            redirectToHome();
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isFirstLaunch", false);
        editor.apply();

        findViewById(R.id.btnAction).setOnClickListener(v -> {
            redirectToHome();
        });

        findViewById(R.id.tvLoginLink).setOnClickListener(v -> {
            redirectToLogin();
        });
    }

    private void redirectToHome() {
        Intent intent = new Intent(OnboardingActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(OnboardingActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}