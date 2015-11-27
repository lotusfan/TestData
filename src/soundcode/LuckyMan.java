package soundcode;
 
import java.io.*;
public class LuckyMan{
    public static void main(String []args){
        int coins , i , j, bets,rand,rewards,unit;
        char choiceID ,luckyID;
        boolean flag = true;
        coins = 10;
        bets = 0;
        unit = 1;
        choiceID = 'N';
        luckyID = 'N';
        BufferedReader br,bw;
        intro();////?????????????????方法。。。
        while(flag ==true){
            System.out.println("您当前的金币总计为：$"+coins);
            if(coins<=0){
                System.out.println("您当前金币已用完，游戏结束。");
                flag = false;
                return;
            }
            System.out.println("欢迎进入幸运苹果机，新的一轮游戏马上开始！！");
            System.out.println("猜中物品和对应的奖励如下：");
            System.out.println("A.苹果----2金币");
            System.out.println("B.木瓜----5金币");
            System.out.println("C.西瓜----10金币");
            System.out.println("D.香蕉----20金币");
            System.out.println("E.橙子----50金币");
            System.out.println("F.葡萄----100金币");
            System.out.println("结束游戏，请按Q！");
            try{
                System.out.println("请输入您选中的水果（输入大写字母A~F）：");
                br = new BufferedReader (new InputStreamReader(System.in));
                choiceID = (char)br.read();
                if(choiceID=='Q'){
                    flag = false;
                    return;
                }
                do{
                    System.out.print("请输入您要押的金币数（最多");
                    System.out.print(coins + "金币：）");
                    bw = new BufferedReader(new InputStreamReader(System.in));
                    bets = Integer.parseInt(bw.readLine());/////????????????
                    if(bets>coins){
                        System.out.println("您的金币不足");
                        flag = false ;
                    }
                    else{
                        flag = true;
                    }
                }while(flag==false);
                     
                     
                }catch(IOException e ){
                    e.printStackTrace();
                }
                System.out.println("幸运苹果机开始运转·····Good luck!");
                for(i=0;i<8;i++){
                    for(j=0;j<555555555;j++){}//延迟
                    rand = (int)(Math.random()*10);
                    switch(rand){
                        case 1:System.out.println("···苹果···");
                        luckyID = 'A';
                        break;
                        case 2:System.out.println("···木瓜···");
                        luckyID = 'B';
                        break;
                        case 3:System.out.println("···西瓜···");
                        luckyID = 'C';
                        break;
                        case 4:System.out.println("···香蕉···");
                        luckyID = 'D';
                        break;
                        case 5:System.out.println("···橙子···");
                        luckyID = 'E';
                        break;
                        case 6:System.out.println("···葡萄···");
                        luckyID = 'F';
                        break;
                        default:System.out.println("···水果盘···");
                        luckyID = 'N';
                        break;
                    }
                }
                if(choiceID == luckyID){
                    unit = unitJudge(luckyID);//判断单注的奖励金币数。。
                    rewards = bets*unit;
                    coins += rewards;//
                    System.out.print("恭喜您猜对了！幸运之神给您的奖励为：");
                    System.out.println(rewards + "金币！");
                }
                else{
                    coins -=bets;
                    System.out.print("很遗憾，您没有才对······您损失了：");
                    System.out.println(bets + "金币！");
                }
                 
                System.out.println("本轮游戏结束----------------------");
            }
         
    }  
         
        public static void intro(){
            System.out.println("*************************************************");
            System.out.println("*幸运机的规则如下：*");
            System.out.println("*  选择水果种类，每次只能选一次；    *");
            System.out.println("*  对你所选的水果押注；              *");
            System.out.println("*  不同水果奖励不同；                 *");
            System.out.println("*  每个初始玩家赠送10金币；             *");
            System.out.println("*  运转后，若停止在您选择的水果上；  *");
            System.out.println("*  则获得奖励；否则损失您押注的金币。*");
            System.out.println("**************************************************");
        }
    public static int unitJudge(char ID){
        int unit;
        switch(ID){
            case 'A':unit = 2;
            break;
            case 'B':unit = 5;
            break;
            case 'C':unit = 10;
            break;
            case 'D':unit = 20;
            break;
            case 'E':unit = 50;
            break;
            case 'F':unit = 100;
            break;
            default:unit = 1;
             
        }
        return unit;
    }  
     
}