package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

    // 1. Tự động tạo DocumentReference mới để lấy ID độc nhất từ Firestore
    DocumentReference docRef = db.collection("articles").document();
    String generatedId = docRef.getId();

    // 2. Khởi tạo đối tượng Article với ID vừa tạo
    Article article = new Article(generatedId, title, content, imageUrl);

    // 3. Lưu object Article lên Firebase với ID đó
    docRef.set(article)
            .addOnSuccessListener(aVoid -> {
              Toast.makeText(MainActivity.this, "Đã thêm Bài viết (ID: " + generatedId + ")", Toast.LENGTH_SHORT).show();
              edtTitle.setText("");
              edtContent.setText("");
              edtImageUrl.setText("");
            })
            .addOnFailureListener(e ->
                    Toast.makeText(MainActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show()
            );
  }
}