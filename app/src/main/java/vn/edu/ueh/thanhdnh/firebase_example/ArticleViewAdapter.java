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

  public ArticleViewAdapter(Context context, List<Article> articleList) {
    this.context = context;
    this.articleList = articleList;
  }

  public void update(List<Article> newList) {
    this.articleList = newList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
    return new ArticleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    Article article = articleList.get(position);
    holder.tvTitle.setText(article.getTitle());
    holder.tvContent.setText(article.getContent());

    if (article.getImageUrl() != null && !article.getImageUrl().trim().isEmpty()) {
      Glide.with(context)
              .load(article.getImageUrl())
              .placeholder(android.R.drawable.ic_menu_gallery)
              .error(android.R.drawable.stat_notify_error)
              .into(holder.imgArticle);
    } else {
      holder.imgArticle.setImageResource(android.R.drawable.ic_menu_gallery);
    }
  }

  @Override
  public int getItemCount() {
    return articleList != null ? articleList.size() : 0;
  }
}