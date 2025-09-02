package com.example.aiinnovation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView ocrTextView;
    private Button openMapsPageBtn;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ocrTextView = findViewById(R.id.ocrTextView);
        openMapsPageBtn = findViewById(R.id.openMapsPageBtn);

        // Initialize TTS
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.US);
            }
        });

        // Simulate scanning for 5 seconds
        new Handler().postDelayed(() -> {
            String dummyText = "Bus Stop: Central Station";
            ocrTextView.setText(dummyText);
            tts.speak(dummyText, TextToSpeech.QUEUE_FLUSH, null, null);
        }, 5000);

        // Navigate to Maps screen
        openMapsPageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
