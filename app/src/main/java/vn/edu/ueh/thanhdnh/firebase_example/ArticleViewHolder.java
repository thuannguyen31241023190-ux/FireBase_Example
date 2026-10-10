package vn.edu.ueh.thanhdnh.firebase_example;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
  public TextView tvId, tvTitle, tvContent;
  public ImageView imgArticle;

  public ArticleViewHolder(@NonNull View itemView) {
    super(itemView);
    tvId = itemView.findViewById(R.id.tvId);
    tvTitle = itemView.findViewById(R.id.tvTitle);
    tvContent = itemView.findViewById(R.id.tvContent);
    imgArticle = itemView.findViewById(R.id.imgArticle);
  }
}