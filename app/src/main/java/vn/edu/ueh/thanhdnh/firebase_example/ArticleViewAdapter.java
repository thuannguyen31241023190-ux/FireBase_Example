package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

  private Context context;
  private List<Article> articleList;

  // Constructor khởi tạo Adapter với Context và Danh sách Bài viết
  public ArticleViewAdapter(Context context, List<Article> articleList) {
    this.context = context;
    this.articleList = articleList;
  }

  // Phương thức cập nhật lại danh sách bài viết khi dữ liệu từ Firebase thay đổi
  public void update(List<Article> newList) {
    this.articleList = newList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // Inflate giao diện item từ file contact_list.xml
    View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
    return new ArticleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article article = articleList.get(position);

    if (article != null) {
      // 1. Hiển thị ID bài viết
      if (holder.tvId != null) {
        holder.tvId.setText("ID: " + (article.getId() != null ? article.getId() : ""));
      }

      // 2. Hiển thị Tiêu đề bài viết
      if (holder.tvTitle != null) {
        holder.tvTitle.setText(article.getTitle() != null ? article.getTitle() : "");
      }

      // 3. Hiển thị Nội dung bài viết
      if (holder.tvContent != null) {
        holder.tvContent.setText(article.getContent() != null ? article.getContent() : "");
      }

      // 4. Hiển thị Hình ảnh bằng thư viện Glide
      String imageUrl = article.getImageUrl();
      if (imageUrl != null && !imageUrl.trim().isEmpty()) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery) // Ảnh hiển thị khi đang tải
                .error(android.R.drawable.stat_notify_error)      // Ảnh hiển thị khi tải bị lỗi
                .into(holder.imgArticle);
      } else {
        // Nếu bài viết không có link ảnh thì đặt ảnh mặc định
        holder.imgArticle.setImageResource(android.R.drawable.ic_menu_gallery);
      }
    }
  }

  @Override
  public int getItemCount() {
    return articleList != null ? articleList.size() : 0;
  }
}