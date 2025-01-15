public class PlatinumClient extends Client{

  public PlatinumClient(String n, int yj, int rn){
    name = n;
    yearjoined = yj;
    rewardsNum = rn;
  }

  String getInfo(){
    return name + ", platinum rewards client since " + yearjoined + ". Rewards ID: " + rewardsNum;
  }

  boolean isPlatinum(){
    return true;
  }

  int getDiscount(){
    return 10 + (2022-yearjoined);
  }
  
}
