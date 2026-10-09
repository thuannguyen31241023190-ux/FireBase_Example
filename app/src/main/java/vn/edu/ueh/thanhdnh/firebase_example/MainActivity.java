package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  private EditText edtTitle, edtContent, edtImageUrl;
  private Button btnSave, btnShowData;
  private FirebaseFirestore db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FirebaseApp.initializeApp(this);
    db = FirebaseFirestore.getInstance();

    edtTitle = findViewById(R.id.edtTitle);
    edtContent = findViewById(R.id.edtContent);
    edtImageUrl = findViewById(R.id.edtImageUrl);
    btnSave = findViewById(R.id.btnSave);
    btnShowData = findViewById(R.id.btnShowData);

    btnSave.setOnClickListener(v -> saveArticle());
    btnShowData.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);
      startActivity(intent);
    });
  }

  private void saveArticle() {
    String title = edtTitle.getText().toString().trim();
    String content = edtContent.getText().toString().trim();
    String imageUrl = edtImageUrl.getText().toString().trim();

    if (title.isEmpty() || content.isEmpty()) {
      Toast.makeText(this, "Vui lòng nhập đủ Tiêu đề và Nội dung!", Toast.LENGTH_SHORT).show();
      return;
    }

    Map<String, Object> articleData = new HashMap<>();
    articleData.put("title", title);
    articleData.put("content", content);
    articleData.put("image_url", imageUrl);

    db.collection("articles").add(articleData)
            .addOnSuccessListener(documentReference -> {
              Toast.makeText(MainActivity.this, "Đã thêm Bài viết!", Toast.LENGTH_SHORT).show();
              edtTitle.setText("");
              edtContent.setText("");
              edtImageUrl.setText("");
            })
            .addOnFailureListener(e ->
                    Toast.makeText(MainActivity.this, "Thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show()
            );
  }
}