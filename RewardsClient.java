public class RewardsClient extends Client{

  public RewardsClient(String n, int yj, int rn){
    name = n;
    yearjoined = yj;
    rewardsNum = rn;
  }

  String getInfo(){
    return name + ", rewards client since " + yearjoined + ". Rewards ID: " + rewardsNum;
  }

  boolean isPlatinum(){
    return false;
  }

  int getDiscount(){
    return 5 + (2022-yearjoined);
  }

  
}
