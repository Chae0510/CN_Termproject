package etc;


public enum CommonWord {
  SIGN_UP_MEMBERSHIP("Sign UP", 0),
  SignIn("Sign In", 1),
  ID("ID", 2),
  PASSWORD("PWD", 3),
  NAME("Name", 4),
  NICKNAME("Nickname", 5),
  EMAIL("Email", 6),
  BIRTH("birth", 7),
  MYPROFILE("My Information", 8),
  FRIEND("friend", 9),
  CANCEL("cancel", 10),
  ADD("Search Friend",11),

  ONLINE("online",13);


  private final String text;
  private final int num;


  CommonWord(String text, int num) {
    this.text = text;
    this.num = num;
  }

  public String getText() {
    return text;
  }

  public int getNum() {
    return num;
  }


}
