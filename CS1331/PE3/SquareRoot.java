import java.util.Random;
public class SquareRoot{
	public static void main(String[] args){
		Random rand=new Random();
		long bigRandNum=rand.nextInt(301)+1000000000000000L;
		System.out.printf("The value is: %d\n",bigRandNum);
		int iter=0;
		long temp=1;
		while(bigRandNum>1){
		for(long i=1;i*i<bigRandNum;i++){
			temp=i;
		}
		bigRandNum=temp;
		System.out.println(bigRandNum);
		iter+=1;
		}
		System.out.printf("Count: %d", iter);
	}
}