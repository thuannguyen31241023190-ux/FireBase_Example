package vn.edu.ueh.thanhdnh.firebase_example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleViewAdapter adapter;
    private List<Article> articles;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articles = new ArrayList<>();
        adapter = new ArticleViewAdapter(this, articles);
        recyclerView.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();

        loadArticlesRealtime();
    }

    private void loadArticlesRealtime() {
        db.collection("articles").addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                Toast.makeText(ShowDataActivity.this, "Lỗi kết nối: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            if (snapshots != null) {
                articles.clear();
                for (DocumentSnapshot doc : snapshots.getDocuments()) {
                    Map<String, Object> data = doc.getData();
                    if (data != null) {
                        String id = doc.getId();
                        String title = (String) data.get("title");
                        String content = (String) data.get("content");
                        String imageUrl = (String) data.get("image_url");

                        articles.add(new Article(id, title, content, imageUrl));
                    }
                }
                adapter.update(articles);
            }
        });
    }
}