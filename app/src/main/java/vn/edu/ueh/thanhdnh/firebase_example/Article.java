package vn.edu.ueh.thanhdnh.firebase_example;

import com.google.firebase.firestore.PropertyName;

public class Article {
  private String id;
  private String title;
  private String content;

  @PropertyName("image_url")
  private String imageUrl;

  // Constructor rỗng bắt buộc cho Firebase Firestore
  public Article() {
  }

  public Article(String id, String title, String content, String imageUrl) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @PropertyName("image_url")
  public String getImageUrl() {
    return imageUrl;
  }

  @PropertyName("image_url")
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}