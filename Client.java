public abstract class Client {
  String name;
  int yearjoined;
  int rewardsNum;

  int getRewardsNumber(){
    return rewardsNum;
  }
  
  abstract boolean isPlatinum();

  abstract String getInfo();

  abstract int getDiscount();
}
