public class FloorOrMax{
	public static void main(String[] args){
	double myRand=Math.random()*(54-4)+4;
	System.out.printf("The value is: %.2f\n",myRand);
	if (Math.max(Math.floor(myRand),15)!=15 && Math.floor(myRand)%2==0){
		System.out.println((int)Math.floor(myRand));
	}
	else if (Math.floor(myRand)%2==1){
		int s=32;
		System.out.println(Math.max(myRand,s));
	}
	else{
		System.out.println(myRand);
	}
	}
}