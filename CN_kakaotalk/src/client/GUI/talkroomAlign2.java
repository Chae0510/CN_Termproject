package client.GUI;

public enum talkroomAlign2 {

  LEFT("left","왼"),
  RIGHT("right","오");

  private final String align;
  private final String title;

  talkroomAlign2(String align, String title) {
    this.align = align;
    this.title = title;
  }

  public String getAlign() {
    return align;
  }

  public String getTitle() {
    return title;
  }


}
